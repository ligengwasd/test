package com.ligeng.test.introduction;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Created by dev on 16-4-22.
 */
public class Test {
    public static void main(String[] args){
        IDeveloper developer = new Developer();

        ITester tester = new Tester();

        DelegatingIntroductionInterceptor interceptor = new DelegatingIntroductionInterceptor(tester);

        ProxyFactory factory = new ProxyFactory(developer);
        factory.addAdvice(interceptor);
        ITester tester1 = (ITester)factory.getProxy();
//        tester1.testSoftWare();

        developer.developSoftWare();
    }
}
