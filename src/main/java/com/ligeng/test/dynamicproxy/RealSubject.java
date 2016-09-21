package com.ligeng.test.dynamicproxy;

/**
 * Created by dev on 16-4-20.
 */
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("doSomething doSomething doSomething");
    }
}
