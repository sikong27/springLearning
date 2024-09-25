import org.bean.Monster;
import org.junit.jupiter.api.Test;
import org.service.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class SpringBeanTest {

    ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
    @Test
    public void getMonsterByID() {
        // ioc里面有个beanFactory, 里面的beanDefinitionMap（ConcurrentHashMap）存放着配置中定义的类信息
        // beanFactory里还有个singletonObjects（ConcurrentHashMap）存放单例实例对象
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

    /**
     * 以类型获取，bean必须只有这一个类型的配置，否则报NoUniqueBeanDefinitionException
     * 用于单例，Controller或Service
     */
    @Test
    public void getMonsterByType() {
        Monster monster = ioc.getBean(Monster.class);
        System.out.println(monster);
    }

    @Test
    public void setBeanByRef() {
        MemberServiceImpl memberService = ioc.getBean("memberService", MemberServiceImpl.class);
        memberService.add();
    }

    @Test
    public void getBeanByFactoryBean() {
        Monster monster = ioc.getBean("monster06", Monster.class);
        System.out.println(monster);
    }

    @Test
    public void beanPostProcessor() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans01.xml");
        ioc.getBean("monster01", Monster.class);
    }
}
