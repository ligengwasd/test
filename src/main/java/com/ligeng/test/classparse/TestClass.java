package com.ligeng.test.classparse;

/**
 * Created by dev on 16-6-29.
 */
public final class TestClass implements Runnable{
    private volatile int m = 10;
    private final String s = "ligeng";
    private String str = "ssssss";
    public synchronized int inc(){
        System.out.println(s);
        System.out.println(str);
        return m+1;
    }

    @Override
    public void run() {
        System.out.println(1);
    }
}
//
//interface ITestClass {
//}