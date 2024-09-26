package org.aop;

public class Car implements Vehicle{
    @Override
    public void run() {
        System.out.println("冲");
    }

    @Override
    public void stop() {
        System.out.println("不冲了");
    }
}
