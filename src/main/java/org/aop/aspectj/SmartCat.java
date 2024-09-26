package org.aop.aspectj;

import org.springframework.stereotype.Component;

@Component
public class SmartCat implements SmartAnimal{
    @Override
    public float getSum(float i, float j) {
        System.out.println("1111111111111111111111111111111");
        return 0;
    }

    @Override
    public float getSub(float i, float j) {
        return 0;
    }
}
