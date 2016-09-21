package com.ligeng.test.declareparents;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dev on 16-4-7.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        Chinese liLei = (LiLei)ctx.getBean("lilei");
        liLei.Say();

        Add lilei2 = (Add)ctx.getBean("lilei");
        lilei2.Todo();
    }
}
