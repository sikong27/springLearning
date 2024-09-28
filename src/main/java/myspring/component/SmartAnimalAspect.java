package myspring.component;

import myspring.annotation.Component;

@Component
public class SmartAnimalAspect {

    public static void showBeginLog() {
        System.out.println("前置通知");
    }

    public static void showSuccessLog() {
        System.out.println("返回通知");
    }
}
