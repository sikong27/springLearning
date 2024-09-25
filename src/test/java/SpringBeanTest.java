import org.bean.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanTest {
    @Test
    public void getMonster() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Object monster01 = ioc.getBean("monster01");
        Monster monster = (Monster) monster01;
        System.out.println(monster);

    }
}
