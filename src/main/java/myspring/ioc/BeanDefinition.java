package myspring.ioc;

/**
 * 封装bean的信息：生命周期scope，class对象
 * @author Kyan27
 * @date 2024/09/28
 */
public class BeanDefinition {
    private String scope;
    private Class<?> beanClass;

    private boolean isLazy;

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "scope='" + scope + '\'' +
                ", beanClass=" + beanClass +
                ", isLazy=" + isLazy +
                '}';
    }
}
