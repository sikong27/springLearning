package jdbctemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("jdbcTemplate.xml");


    }
}
