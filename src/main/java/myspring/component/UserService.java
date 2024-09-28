package myspring.component;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class UserService {

    @Resource
    private UserDao userDao;

    public void m1() {
        userDao.hi();
    }

    @PostConstruct
    private void init() {
        System.out.println("UserService init()");
    }
}
