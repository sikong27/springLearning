package myspring.component;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class UserDao {
    public void hi() {
        System.out.println("UserDao--hi()");
    }
}
