package myspring;

import myspring.aop.SmartAnimalable;
import myspring.component.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    @Test
    public void iocTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("myspringbeans.xml");
        UserService userService = ioc.getBean("userService", UserService.class);
        userService.m1();
    }

    @Test
    public void postProcessorTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("myspringbeans.xml");
    }

    @Test
    public void aopTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("myspringbeans.xml");
        SmartAnimalable smartDog = ioc.getBean("smartDog", SmartAnimalable.class);
        System.out.println(smartDog.getClass());
        smartDog.getSum(1,2);
    }
}
