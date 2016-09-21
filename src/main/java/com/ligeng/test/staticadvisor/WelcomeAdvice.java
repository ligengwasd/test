package com.ligeng.test.staticadvisor;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by dev on 16-4-13.
 */
public class WelcomeAdvice implements MethodBeforeAdvice{

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
//        String type=(String)args[0];
//        System.out.println("Hello welcome to buy "+type);
        System.out.println("2 2 2 2 2 2 2");
    }
}
