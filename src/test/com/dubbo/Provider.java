package com.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dev on 16-8-18.
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubboxml/provider.xml");
        context.start();

        System.in.read(); // 按任意键退出
    }

}
