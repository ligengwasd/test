package com.ligeng.common.listener;

import org.springframework.context.ApplicationEvent;

/**
 * Created by dev on 16-5-3.
 */
public class BlackListEvent extends ApplicationEvent{
    private final String address;
    private final String test;

    public BlackListEvent(Object source, String address, String test) {
        super(source);
        this.address = address;
        this.test = test;
    }
}
