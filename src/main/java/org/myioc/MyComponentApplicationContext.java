package org.myioc;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kyan27
 * @date 2024/09/26
 * 自定义spring容器
 */
public class MyComponentApplicationContext {
    // 底层反射创建，必须要class对象
    private Class configClass;
    // 存放基于注解,通过反射创建的对象
    private final ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 通过配置类获取注解，进而获取路径
     *
     * @param configClass 配置类class对象
     */
    public MyComponentApplicationContext(Class configClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.configClass = configClass;
        MyComponentScan annotation = (MyComponentScan) this.configClass.getDeclaredAnnotation(MyComponentScan.class);
        String path = annotation.value();
        // 定义的path以 . 分割，而url以 / 分割
        path = path.replace(".", "/");
        ClassLoader classLoader = MyComponentApplicationContext.class.getClassLoader();
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
                        if (aClass.isAnnotationPresent(Component.class) || aClass.isAnnotationPresent(Service.class)
                                || aClass.isAnnotationPresent(Controller.class) || aClass.isAnnotationPresent(Repository.class)) {
                            String singletonName = "";
                            if (aClass.isAnnotationPresent(Component.class)) {
                                String value = aClass.getDeclaredAnnotation(Component.class).value();
                                if (!value.isEmpty()) {
                                    singletonName = value;
                                } else {
                                    singletonName = StringUtils.uncapitalize(className);
                                }
                            }
                            Class<?> clazz = Class.forName(classFullName);
                            singletonObjects.put(singletonName, clazz.newInstance());
                        }
                    }
                }
            }
        }

    }

    public MyComponentApplicationContext() {
    }

    public Object getBean(String beanName) {
        return singletonObjects.get(beanName);
    }
}
