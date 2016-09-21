package com.ligeng.test.staticadvisor;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by dev on 16-4-19.
 */
public class WelcomeAdvice2 implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
//        String type=(String)args[0];
        System.out.println("1 1 1 1 1 1 1");
    }
}
