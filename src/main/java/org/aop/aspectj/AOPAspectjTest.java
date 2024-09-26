package org.aop.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPAspectjTest {
    public static void main(String[] args) {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("aopbeans.xml");
        // 经过动态代理后拿到的是代理对象，需要用SmartAnimal get，用的是jdk的proxy代理
        // 若对于没有实现接口的类，则用的是spring的cglib代理
        SmartAnimal bean = ioc.getBean("smartCat", SmartAnimal.class);
        //已经没有SmartDog类型的对象了，已经变成了代理对象
        //ioc.getBean(SmartDog.class);
        System.out.println(bean.getClass());
        bean.getSum(1, 2);
    }
}
