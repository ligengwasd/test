package com.ligeng.test.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * Created by dev on 16-4-20.
 */
public class DynamicProxy {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        Subject proxySubject = (Subject)Proxy.newProxyInstance(
                Subject.class.getClassLoader(),new Class[]{Subject.class},new ProxyHandler(realSubject));
        proxySubject.doSomething();
        System.out.println(proxySubject.getClass());
    }
}
