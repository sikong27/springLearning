package myspring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SmartAnimalAspect {

    @Before(value = "execution(public float myspring.aop.SmartDog.getSum(float ,float))")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect before() " + signature.getName() + " is called");
    }

    @AfterReturning(value = "execution(public float myspring.aop.SmartDog.*(..))")
    public void showSuccessEndLog(){
        System.out.println("返回通知");
    }

    @AfterThrowing(value = "execution(public float myspring.aop.SmartDog.*(..))")
    public void showExceptionLog() {
        System.out.println("异常通知");
    }

    @After(value = "execution(public float myspring.aop.SmartDog.*(..))")
    public void showFinallyEndLog() {
        System.out.println("最终通知");
    }
}
