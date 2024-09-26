package org.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class VehicleProxyProvider {

    private Vehicle targetVehicle;

    public VehicleProxyProvider(Vehicle targetVehicle) {
        this.targetVehicle = targetVehicle;
    }

    public Vehicle getProxy() {
        // 动态代理体现在，实现类与执行方法
        ClassLoader classLoader = targetVehicle.getClass().getClassLoader();
        Class<?>[] interfaces = targetVehicle.getClass().getInterfaces();
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            Object res = null;
            try {
                //硬编码，不好，引出aop，希望把通知以方法的形式实现
                //前置通知
                System.out.println("begin");
                res = method.invoke(targetVehicle, args);
                //返回通知
                System.out.println(res);
                //后置通知
                System.out.println("end");
            } catch (Exception e) {
                //异常通知
                System.out.println("error");
                throw new RuntimeException(e);
            } finally {
                //最终通知
                System.out.println("finally");
            }
            return res;
        };
        Object o = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        return (Vehicle) o;
    }
}
