import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTemplateTest {

    @Test
    public void getDataSource() throws SQLException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("jdbcTemplate.xml");
        DataSource dataSource = ioc.getBean("dataSource", DataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    public void addData() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("jdbcTemplate.xml");
        JdbcTemplate jdbcTemplate = ioc.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "insert into monster values (400,'黑悟空','变化')";
        jdbcTemplate.execute(sql);
        System.out.println("ok");
    }
}
