package org.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class SmartAnimalAspect {

    @Before(value = "execution(public float org.aop.aspectj.SmartDog.getSum(float, float))")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("Before method " + signature.getName() + " is called on " + Arrays.toString(joinPoint.getArgs()));
    }
}
