package org.myioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Kyan27
 * @date 2024/09/26
 * 自定义注解
 * Target指示该注解可以用于Type类型
 * Retention指示注解的保留范围
 * String value() default "";表示注解可以传入value
 */
@Target(ElementType.TYPE)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface MyComponentScan {
    String value() default "";
}
