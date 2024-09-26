package org.myioc;

public class MyComponentTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Object beginComponent = new MyComponentApplicationContext(MyComponentConfig.class).getBean("mjk");
        System.out.println(beginComponent);
    }
}
