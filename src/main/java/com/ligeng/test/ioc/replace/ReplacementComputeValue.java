package com.ligeng.test.ioc.replace;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * Created by dev on 16-4-29.
 */
public class ReplacementComputeValue implements MethodReplacer{
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("111");
        return method.invoke(args);
    }
}
