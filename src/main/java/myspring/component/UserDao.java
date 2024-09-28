package myspring.component;

import myspring.annotation.Component;
import myspring.annotation.Lazy;

@Component(value = "myUserDao")
public class UserDao {

    public void hi() {
        System.out.println("UserDao--hi");
    }
}
