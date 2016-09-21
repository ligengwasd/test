package com.ligeng.service;

import com.ligeng.common.listener.BlackListEvent;
import com.ligeng.entity.nochange.Mytb2;
import com.ligeng.mapper.Mytb2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 16-5-3.
 */

@Service
public class EmailService  {
    @Autowired
    private Mytb2Mapper mapper;

    private List<String> blackList = new ArrayList<String>(){{
        add("123@ligeng.com");
        add("456@ligeng.com");
        add("789@ligeng.com");
    }};

    @Autowired
    private ApplicationEventPublisher publisher;

//    public void setBlackList(List<String> blackList) {
//        this.blackList = blackList;
//    }

//    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
//        this.publisher = publisher;
//    }

    public void sendEmail(String address, String text) {
        if (blackList.contains(address)) {
            BlackListEvent event = new BlackListEvent(this, address, text);
            publisher.publishEvent(event);
            return;
        }
        // send email...
    }

    public void testMapper(){
        Mytb2 mytb2 = mapper.selectByPrimaryKey("ligeng3");
        System.out.println(mytb2);
    }

}

