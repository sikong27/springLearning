package myspring.ioc;

import myspring.annotation.*;
import myspring.processor.BeanPostProcessor;
import myspring.processor.InitializingBean;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MySpringApplicationContext {
    // 底层反射创建，必须要class对象
    private Class<?> configClass;

    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * class-beanName映射
     */
    private final ConcurrentHashMap<Class<?>, String> beanNameMap = new ConcurrentHashMap<>();

    /**
     * 存储所有后置处理器
     * 原生中还是通过getBean和createBean，以及在singletonObjects中加入业务逻辑，比较麻烦。
     * 这里只是简化处理
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * 通过配置类获取注解，进而获取路径
     *
     * @param configClass 配置类class对象
     */
    public MySpringApplicationContext(Class<?> configClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        beanDefinitionsScan(configClass);
        initSingletonObjects();
    }

    private void initSingletonObjects() {// 4.把单例对象加入到singletonObjects
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            if (beanDefinition.getScope().equals("singleton") && !beanDefinition.isLazy()) {
                if (!BeanPostProcessor.class.isAssignableFrom(beanDefinition.getBeanClass())) {
                    singletonObjects.put(beanName, Objects.requireNonNull(createBean(beanDefinition, beanName)));
                }
            }
        });
    }

    private Object createBean(BeanDefinition beanDefinition, String beanName) {
        Class<?> clazz = beanDefinition.getBeanClass();
        try {
            // 调无参构造器
            Object instance = clazz.newInstance();
            // 遍历所有字段，看是否需要依赖注入
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Resource.class)) {
                    field.setAccessible(true);
                    Resource resource = field.getDeclaredAnnotation(Resource.class);
                    if(!"".equals(resource.name())) {
                        field.set(instance, getBean(resource.name()));
                    } else if (resource.type() != Object.class) {
                        field.set(instance, getBean(beanNameMap.get(resource.type())));
                    } else {
                        Object bean = getBean(field.getName());
                        if (bean != null) {
                            field.set(instance, bean);
                        } else {
                            field.set(instance, getBean(beanNameMap.get(field.getType())));
                        }
                    }
                }
            }
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                Object cur = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
                // instance经过处理后不能变成null
                // 原生比这个复杂
                if (cur != null) {
                    instance = cur;
                }
            }
            // 判断是否需要执行init方法
            if (instance instanceof InitializingBean) {
                ((InitializingBean) instance).afterPropertiesSet();
            }
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                Object cur = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
                if (cur != null) {
                    instance = cur;
                }
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 扫描target下的bean，并把信息放入definitionMap中
     *
     * @param configClass
     * @throws ClassNotFoundException
     */
    private void beanDefinitionsScan(Class configClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.configClass = configClass;
        ComponentScan annotation = (ComponentScan) this.configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = annotation.value();
        // 定义的path以 . 分割，而url以 / 分割
        path = path.replace(".", "/");
        ClassLoader classLoader = MySpringApplicationContext.class.getClassLoader();
        URL url = classLoader.getResource(path);
        File file = new File(url.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    // fileName以 / 分割，而反射需要的路径以 . 分割，且需要类的全路径，不要class后缀
                    String fileAbsolutePath = file1.getAbsolutePath();
                    if (fileAbsolutePath.endsWith(".class")) {
                        String className = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("\\") + 1, fileAbsolutePath.indexOf(".class"));
                        String classFullName = path.replace("/", ".") + "." + className;
                        // classLoader.loadClass不会调类的静态方法，Class.forName会
                        Class<?> aClass = classLoader.loadClass(classFullName);
                        // 判断是否是bean
                        if (aClass.isAnnotationPresent(Component.class)) {
                            // 1.先得到beanName
                            Component component = aClass.getDeclaredAnnotation(Component.class);
                            String beanName = component.value();
                            if ("".equals(beanName)) {
                                beanName = StringUtils.uncapitalize(className);
                            }
                            // 2.把bean信息封装到beanDefinition
                            BeanDefinition beanDefinition = getBeanDefinition(aClass);
                            beanDefinitionMap.put(beanName, beanDefinition);
                            beanNameMap.put(aClass, beanName);
                            // 3.看当前class是不是后置处理器，是的话直接初始化，就不需要加入单例池了
                            beanPostProcessorProcess(beanName, aClass);
                        }
                    }
                }
            }
        }
    }

    private void beanPostProcessorProcess(String beanName, Class <?> aClass) throws InstantiationException, IllegalAccessException {
        if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
            BeanPostProcessor beanPostProcessor = (BeanPostProcessor) aClass.newInstance();
            beanPostProcessors.add(beanPostProcessor);
        }
    }

    private static BeanDefinition getBeanDefinition(Class<?> aClass) {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClass(aClass);
        beanDefinition.setScope(getScope(aClass));
        beanDefinition.setLazy(getLazy(aClass));
        return beanDefinition;
    }

    private static boolean getLazy(Class<?> aClass) {
        boolean isLazy = false;
        if (aClass.isAnnotationPresent(Lazy.class)) {
            isLazy = aClass.getDeclaredAnnotation(Lazy.class).value();
        }
        return isLazy;
    }

    private static String getScope(Class<?> aClass) {
        String scope = "singleton";
        if (aClass.isAnnotationPresent(Scope.class)) {
            scope = aClass.getDeclaredAnnotation(Scope.class).value();
        }
        return scope;
    }

    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new RuntimeException("beanName不存在");
        }
        Class<?> clazz = beanDefinition.getBeanClass();
        if ("singleton".equals(getScope(clazz))) {
            Object o = singletonObjects.get(beanName);
            if (o == null) {
                Object bean = createBean(beanDefinition, beanName);
                singletonObjects.put(beanName, bean);
                return bean;
            }
            return o;
        } else if ("prototype".equals(getScope(clazz))) {
            return createBean(beanDefinition, beanName);
        } else {
            throw new RuntimeException("scope不存在");
        }
    }
}
