package com.ligeng.test.staticadvisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by dev on 16-4-22.
 */
public class ShoppingMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println(method.getDeclaringClass());
        System.out.println(method.getName());
        System.out.println("ShoppingMethodInterceptor is executing");

        return invocation.proceed();
    }
}
