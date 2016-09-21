package com.ligeng.test.aoptest;

import com.ligeng.entity.Spittle;
import com.ligeng.service.jms.IAlertService;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dev on 16-4-7.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        IAlertService service = (IAlertService) ctx.getBean("alertServiceImpl");
        ActiveMQConnectionFactory connectionFactory = (ActiveMQConnectionFactory) ctx.getBean("connectionFactory");
//        connectionFactory.i

//        connectionFactory.
//        MyService2 service2 = (MyService2) ctx.getBean("myService2");
        service.sendSpitterAlert(new Spittle("ssss"));
//        service.service2();
//        service2.service(100);




//        MethodInvocation invocation;
//        invocation.proceed()
//        ProxyFactoryBean


//        DefaultAopProxyFactory factory = new DefaultAopProxyFactory();
        
//        factory.createAopProxy()
//        ProxyFactoryBean factory2 = new ProxyFactoryBean();
//        factory.addAdvisor();
//        BeforeAdvice


//        MethodParameterContext
//        JdkRegexpMethodPointcut
//        DefaultPointcutAdvisor
//        ProxyFactoryfffP
//        MethodInterceptorc
//        AfterReturningAdvice
//        java.lang.reflect.Proxy
//        Pointcut

    }


}
