package com.ligeng.service.dubbo.impl.consumer;

import com.ligeng.common.validation.Person;

/**
 * Created by dev on 16-8-20.
 */
public interface Notify {
    public void onreturn(Person msg, Integer id, Integer id2);
    public void onthrow(Throwable ex, Integer id);
}
