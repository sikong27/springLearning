package org.aop;

public class Bike implements Vehicle{

    @Override
    public void run() {
        System.out.println("骑车");
    }

    @Override
    public void stop() {
        System.out.println("停车");
    }
}
