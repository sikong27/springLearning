package myspring.component;

import myspring.annotation.Component;
import myspring.processor.BeanPostProcessor;
import org.springframework.beans.BeansException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //System.out.println("postProcessAfterInitialization..." + beanName + "---" + bean);
        // 实现AOP，对bean进行包装，返回代理对象
        if ("smartDog".equals(beanName)) {
            return Proxy.newProxyInstance(MyBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println("method = " + method.getName());
                        Object res;
                        if ("getSum".equals(method.getName())) {
                            SmartAnimalAspect.showBeginLog();
                            res = method.invoke(bean, args);
                            SmartAnimalAspect.showSuccessLog();
                        } else {
                            res = method.invoke(bean, args);
                        }
                        return res;
                    }
            );
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //System.out.println("postProcessBeforeInitialization..." + beanName + "---" + bean);
        // 可以针对不同类型加相应的业务
        if (bean instanceof Test) {
            System.out.println("bean is Test");
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
