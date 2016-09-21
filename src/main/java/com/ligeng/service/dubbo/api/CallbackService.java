package com.ligeng.service.dubbo.api;

import com.ligeng.common.listener.CallbackListener;

/**
 * Created by dev on 16-8-18.
 */
public interface CallbackService {
    void addListener(String key, CallbackListener listener);
}
