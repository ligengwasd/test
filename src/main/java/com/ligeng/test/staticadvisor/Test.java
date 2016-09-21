package com.ligeng.test.staticadvisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {
    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        ctx = new FileSystemXmlApplicationContext("/src/main/resources/spring-context.xml");
//        Shopping shopping=null;

//        shopping=(Shopping)ctx.getBean("StaticAdvisorTest");
//        shopping.buyAnything("111");
//        shopping.sellAnything("222");

        Customer customer = (Customer)ctx.getBean("customerInner");
        System.out.println(customer.getName());

//        ShoppingImpl shopping = (ShoppingImpl)ctx.getBean("shoppingImpl");
//        shopping.test();




//        BeanFactoryPostProcessor
//        ctx.;
//        RuntimeException

//        JoinPoint
//        AopProxyFactory
//        DefaultListableBeanFactory
//        ShoppingImpl shoppingImpl = new ShoppingImpl();
//        shoppingImpl.setCustomer(new Customer(){{
//            setName("ligeng");
//            setAge("33");
//        }});

        //构造advisor
//        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
//        advisor.setAdvice(new WelcomeAdvice());
////        advisor.;
//        advisor.setMappedName("sell*");
//        advisor.

        //构造Target
//        HotSwappableTargetSource targetSource = new HotSwappableTargetSource(shoppingImpl);
//        CommonsPoolTargetSource targetSource = new CommonsPoolTargetSource();
//        targetSource.setWhenExhaustedAction();

//        ITask task1 = new ITask() {
//            @Override
//            public void execute() {
//                System.out.println("11111");
//            }
//        };
//
//        ITask task2 = new ITask() {
//            @Override
//            public void execute() {
//                System.out.println("22222");
//            }
//        };
//        TargetSource targetSource = new AlternativeTargetSource(task1,task2);
        //构造ProxyFactory
//        ProxyFactory factory = new ProxyFactory();
//        factory.setTargetSource(targetSource);
//        factory.addAdvisor(advisor);
//        ITask task = (ITask) factory.getProxy();
//        task.execute();
//        task.execute();
//        task.execute();


//        factory.inter
//        factory.addAdvice(new WelcomeAdvice());
//        factory.setInterfaces();


//        factory.getProxy()
//        Shopping shopping1 = (Shopping)factory.getProxy();
//        shopping1.sellSomething("苹果");
//        shopping1.buyAnything("例子");

//        NameMatchMethodPointcut
//        JdkRegexpMethodPointcut
//        AnnotationMatchingPointcut
//        ComposablePointcut
//        ControlFlowPointcut
//        MethodBeforeAdviceInterceptor
//        DebugInterceptor



   }
}
