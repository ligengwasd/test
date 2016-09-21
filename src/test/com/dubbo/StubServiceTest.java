package com.dubbo;

import com.ligeng.service.dubbo.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dev on 16-8-21.
 */
public class StubServiceTest {
    // 本地存根
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubboxml/consumer.xml");
        context.start();

        final DemoService demoService = (DemoService) context.getBean("demoService");
        for (int i=0; i<12; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                    String ret = demoService.sayHello("ligeng");
                    System.out.println(ret);
                }
            }).start();
        }
    }
}
