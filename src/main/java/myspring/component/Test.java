package myspring.component;

import myspring.annotation.Component;
import myspring.processor.InitializingBean;

import javax.annotation.Resource;

@Component
public class Test implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        //System.out.println("Test init..");
    }
}
