package com.ligeng.common.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by dev on 16-5-3.
 */

@Component("blackListNotifier")
public class BlackListNotifier implements ApplicationListener<BlackListEvent> {

//    private String notificationAddress;
//
//    public void setNotificationAddress(String notificationAddress) {
//        this.notificationAddress = "000@ligeng.com";
//    }

    public void onApplicationEvent(BlackListEvent event) {
//        if (event.get)
        System.out.println("onApplicationEvent onApplicationEvent onApplicationEvent");
    }
}
