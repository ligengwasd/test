package com.ligeng.test.aoptest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by dev on 16-4-7.
 */

@Component("myAspect")
@Scope("prototype")
@Aspect
//@Aspect("perthis(this(com.ligeng.aoptest.MyService))")
//@Order(30)
public class MyAspect{
    private static int count = 0;
    private final int id = count++;
//    @Pointcut(value = "execution(* com.ligeng.aoptest.MyService.*(..)) && args(age)",argNames = "age")
//    @Pointcut(value = "execution(@java.lang.Deprecated * *(..)) && args(age)",argNames = "age")
//    public void beforePointcut(Person age){};

//    @Pointcut(argNames = )
//    @Before("execution(* com.ligeng.aoptest.MyService.*(..)) && args(age)")
//    @Around("execution(* com.ligeng.aoptest.MyService.*(..))")
//    @Before(value = "beforePointcut(age)")

    @Before("execution(* com.ligeng.test.aoptest.MyService.*(..)) && args(age)")
    public void before(JoinPoint call,int age) {
        //获取目标对象对应的类名
        String className = call.getTarget().getClass().getName();
        //获取目标对象上正在执行的方法名
        String methodName = call.getSignature().getName();
        System.out.println("前置通知:" + className + "类的" + methodName + "方法开始了[age]=");
    }




//    @AfterReturning("execution(* com.ligeng.aoptest.*.*(..)) && @annotation(IsAfter)" )
    @AfterReturning("@annotation(IsAfter)")
    public void afterReturn() {
        System.out.println("MyAspect 后置通知:方法正常结束了    "+id);
    }



    public void after(){
        System.out.println("最终通知:不管方法有没有正常执行完成，一定会返回的");
    }




    public void afterThrowing() {
        System.out.println("异常抛出后通知:方法执行时出异常了");
    }


//    @Override
//    public int getOrder() {
//        return 10;
//    }
    //用来做环绕通知的方法可以第一个参数定义为org.aspectj.lang.ProceedingJoinPoint类型
    /*
    public Object doAround(ProceedingJoinPoint call) throws Throwable {
        Object result = null;
        this.before(call);//相当于前置通知
        try {
            result = call.proceed();
            this.afterReturn(); //相当于后置通知
        } catch (Throwable e) {
            this.afterThrowing();  //相当于异常抛出后通知
            throw e;
        }finally{
            this.after();  //相当于最终通知
        }
        return result;
    }*/
}
