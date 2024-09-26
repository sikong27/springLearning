package org.aop;

public class VehicleTest {
    public static void main(String[] args) {
        Vehicle bike = new Bike();
        VehicleProxyProvider provider = new VehicleProxyProvider(bike);
        // 代理类型
        Vehicle proxy = provider.getProxy();
        proxy.run();
        proxy.stop();
    }
}
