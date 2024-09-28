package myspring.aop;

import org.springframework.stereotype.Component;

@Component
public class SmartDog implements SmartAnimalable {
    @Override
    public float getSum(float a, float b) {
        float res = a + b;
        System.out.println("SmartDog--getSum--" + res);
        return res;
    }

    @Override
    public float getSub(float a, float b) {
        float res = a - b;
        System.out.println("SmartDog--getSub--" + res);
        return res;
    }
}
