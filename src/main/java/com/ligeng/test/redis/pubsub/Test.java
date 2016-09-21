package com.ligeng.test.redis.pubsub;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by dev on 16-5-28.
 */
public class Test {
    public static void main(String[] args){

        System.out.println(1);

        new ClassPathXmlApplicationContext("spring-context.xml");;
        while (true) { //这里是一个死循环,目的就是让进程不退出,用于接收发布的消息
            try {
//                System.out.println("current time: " + new Date());

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
