package myspring.component;


import myspring.annotation.Component;

@Component
public class SmartDog implements SmartAnimal {
    @Override
    public float getSum(float i, float j) {
        float res = i + j;
        System.out.println("方法内部 结果" + res);
        return res;
    }

    @Override
    public float getSub(float i, float j) {
        float res = i - j;
        System.out.println("方法内部 结果" + res);
        return res;
    }
}
