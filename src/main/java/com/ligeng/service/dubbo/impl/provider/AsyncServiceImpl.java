package com.ligeng.service.dubbo.impl.provider;

import com.ligeng.service.dubbo.api.AsyncService;

import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 16-8-18.
 */
public class AsyncServiceImpl implements AsyncService{
    @Override
    public int findPersonList(int time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return time;
    }
}
