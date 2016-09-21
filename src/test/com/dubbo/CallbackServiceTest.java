package com.dubbo;

import com.ligeng.common.listener.CallbackListener;
import com.ligeng.service.dubbo.api.CallbackService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dev on 16-8-18.
 */
public class CallbackServiceTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubboxml/consumer.xml");
        context.start();

        CallbackService callbackService = (CallbackService)context.getBean("callbackService");
//        System.out.println(callbackService.getClass());
        for (int i=1; i<=10; i++) {
            callbackService.addListener(i+"", new CallbackListener() {
                @Override
                public void changed(String msg) {
                    System.out.println("callback1: "+msg);
                }
            });
        }
    }
}
