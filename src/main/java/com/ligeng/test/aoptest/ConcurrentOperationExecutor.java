package com.ligeng.test.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Component;

/**
 * Created by dev on 16-4-7.
 */

@Component("concurrentOperationExecutor")
@Aspect
//@Order(value = 20)
public class ConcurrentOperationExecutor{
    private static final int DEFAULT_MAX_RETRIES = 2;

    private int maxRetries = DEFAULT_MAX_RETRIES;
//    private int order = 20;

    public void setMaxRetries(int maxRetries){
        this.maxRetries = maxRetries;
    }

//    public int getOrder() {
//        return this.order;
//    }
//    public void setOrder(int order){
//        this.order = order;
//    }
//    @AfterReturning("execution(* com.ligeng.aoptest.MyService.*(..))")
    public void afterReturn() {
        System.out.println("ConcurrentOperationExecutor 后置通知:方法正常结束了[order]=20");
    }
//    @Around("execution(* com.ligeng.aoptest.MyService.*(..))")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp)throws Throwable {
        System.out.println("doConcurrentOperation aop [order]=");
        int numAttempts = 0;
        PessimisticLockingFailureException lockFailureException;
        do {
            numAttempts++;
            try {
                return pjp.proceed();
            }
            catch(PessimisticLockingFailureException ex){
                System.out.println("111111");
                lockFailureException = ex;
            }
        }while(numAttempts <= this.maxRetries);


        throw lockFailureException;
    }

}
