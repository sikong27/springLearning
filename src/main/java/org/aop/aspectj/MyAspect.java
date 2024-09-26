package org.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Before(value = "execution(public float org.aop.aspectj.SmartDog.getSum(float, float))," +
            "execution(public float org.aop.aspectj.SmartCat.getSum(float, float))")
    public void before(JoinPoint joinPoint) {
        System.out.println("1111");
    }
}
