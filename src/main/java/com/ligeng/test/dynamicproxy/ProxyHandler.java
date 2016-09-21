package com.ligeng.test.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by dev on 16-4-20.
 */
public class ProxyHandler implements InvocationHandler{
    private Object target;
    public ProxyHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("111111");
        return method.invoke(target,args);
    }
}
