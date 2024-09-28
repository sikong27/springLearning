package myspring;

import myspring.component.SmartAnimal;
import myspring.component.SmartDog;
import myspring.component.UserService;
import myspring.ioc.MySpringApplicationContext;
import myspring.ioc.MySpringConfig;

public class AppMain {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        MySpringApplicationContext ioc = new MySpringApplicationContext(MySpringConfig.class);
        //UserService bean =(UserService) ioc.getBean("userService");
        //bean.hi();
        SmartAnimal bean = (SmartAnimal)ioc.getBean("smartDog");
        System.out.println(bean.getClass());
        bean.getSum(1, 2);
        bean.getSub(1, 2);
    }
}
