import org.bean.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class SpringBeanTest {
    @Test
    public void getMonster() {
        // ioc里面有个beanFactory, 里面的beanDefinitionMap（ConcurrentHashMap）存放着配置中定义的类信息
        // beanFactory里还有个singletonObjects（ConcurrentHashMap）存放单例实例对象
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        // getBean，先拿ID去beanDefinitionMap查，如果是单例直接去singletonObjects获取，否则创建一个新的返回
        Object monster01 = ioc.getBean("monster01");
        Monster monster = (Monster) monster01;
        System.out.println(monster);
        Monster monster1 = ioc.getBean("monster01", Monster.class);
        System.out.println(monster1);
        for (String beanDefinitionName : ioc.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void classPath() {
        File file = new File(this.getClass().getResource("/").getPath());
        System.out.println(file);
    }
}
