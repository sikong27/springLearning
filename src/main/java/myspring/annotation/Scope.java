package myspring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 指定bean作用范围  singleton / prototype
 * @author Kyan27
 * @date 2024/09/28
 */
@Target(ElementType.TYPE)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Scope {
    String value() default "singleton";
}
