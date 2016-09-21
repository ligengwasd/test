package com.ligeng.service.dubbo.impl.consumer;

import com.ligeng.common.validation.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dev on 16-8-20.
 */
public class NotifyImpl implements Notify{
//    public Map<Integer, Person> ret    = new HashMap<Integer, Person>();
//    public Map<Integer, Throwable> errors = new HashMap<Integer, Throwable>();

    public void onreturn(Person msg, Integer id1, Integer id2) {
//        System.out.println(111);
        System.out.println("onreturn:" + msg.getName()+", "+id1+", "+id2);
//        ret.put(id1, msg);
    }
    public void onthrow(Throwable ex, Integer id) {
        System.out.println(ex);
//        errors.put(id, ex);
    }
}
