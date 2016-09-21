package com.ligeng.service.dubbo.impl.provider;

import com.ligeng.common.validation.Person;
import com.ligeng.service.dubbo.api.EventService;

/**
 * Created by dev on 16-8-20.
 */
public class EventServiceImpl implements EventService{
    public Person getPerson(int id, int id2) {
        Person p = new Person();
        p.setName("ligeng");
//        throw new RuntimeException("ssss");
        return p;
    }
}
