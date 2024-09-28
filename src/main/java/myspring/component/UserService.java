package myspring.component;

import myspring.annotation.Component;
import myspring.annotation.Resource;
import myspring.annotation.Scope;
import myspring.processor.InitializingBean;

@Component
@Scope(value = "prototype")
public class UserService implements InitializingBean {

    @Resource(type = UserDao.class)
    private UserDao userDao;

    public void hi() {
        System.out.println("UserService hi");
        userDao.hi();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserService init..");
    }
}
