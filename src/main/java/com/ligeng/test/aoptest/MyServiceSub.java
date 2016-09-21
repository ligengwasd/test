package com.ligeng.test.aoptest;

import org.springframework.stereotype.Service;

/**
 * Created by dev on 16-4-28.
 */
@Service("myServiceSub")
public abstract class MyServiceSub extends MyService {

    public void service(int age){
        System.out.println("service1111");
    }


    abstract void test();
}
