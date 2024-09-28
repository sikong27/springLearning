package myspring.processor;

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

/**
 * Spring原生的处理器方法
 * @author Kyan27
 * @date 2024/09/28
 */
public interface BeanPostProcessor {
    /**
     * bean init前调用
     * @param bean
     * @param beanName
     * @return {@link Object }
     * @throws BeansException
     */
    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * bean init后调用
     * @param bean
     * @param beanName
     * @return {@link Object }
     * @throws BeansException
     */
    @Nullable
    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
