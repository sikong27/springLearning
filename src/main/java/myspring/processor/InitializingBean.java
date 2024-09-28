package myspring.processor;

/**
 * @author Kyan27
 * @date 2024/09/28
 * Spring原生的接口，作用就是init方法。只要实现了这个方法，创建好bean后就必须调用afterPropertiesSet
 */
public interface InitializingBean {
    /**
     * init方法，执行完构造器和set方法后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
