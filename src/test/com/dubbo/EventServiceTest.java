package com.dubbo;

import com.ligeng.common.validation.Person;
import com.ligeng.service.dubbo.api.EventService;
import com.ligeng.service.dubbo.impl.consumer.Notify;
import com.ligeng.service.dubbo.impl.consumer.NotifyImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 16-8-20.
 */
public class EventServiceTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubboxml/consumer.xml");
        context.start();

        EventService eventService = (EventService) context.getBean("eventService");
        NotifyImpl notify = (NotifyImpl) context.getBean("notify");

        int id =1;
        Person p = eventService.getPerson(id,2);
//        System.out.println("p: "+p);
//        for (int i=0; i<10; i++) {
//            if (!notify.ret.containsKey(id)) {
//                Thread.sleep(200);
//            } else {
//                break;
//            }
//        }
        TimeUnit.MILLISECONDS.sleep(1000);

//        System.out.println("ss: "+notify.ret.get(id));

    }
}
