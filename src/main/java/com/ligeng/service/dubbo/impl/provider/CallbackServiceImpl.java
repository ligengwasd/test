package com.ligeng.service.dubbo.impl.provider;

import com.ligeng.common.listener.CallbackListener;
import com.ligeng.service.dubbo.api.CallbackService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 16-8-18.
 */
public class CallbackServiceImpl implements CallbackService{
    final private Map<String, CallbackListener> listeners = new ConcurrentHashMap<String, CallbackListener>();

    public CallbackServiceImpl() {
//        System.out.println("CallbackServiceImpl");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
//                    System.out.println(System.currentTimeMillis());
                    try{
                        for (Map.Entry<String, CallbackListener> entry : listeners.entrySet()) {
                            try {
//                                System.out.println(entry.getKey());
                                String s = getChanged(entry.getKey());
                                entry.getValue().changed(s);
                            } catch (Throwable t) {
                                listeners.remove(entry.getKey());
                            }
                        }
                        TimeUnit.MILLISECONDS.sleep(3000);
//                        Thread.sleep(5000);
                    }catch (Throwable t){
                        t.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }
    public void addListener(String key, CallbackListener listener) {
        listeners.put(key, listener);
        listener.changed(getChanged(key)); // 发送变更通知
    }
    private String getChanged(String key) {
        return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ " "+key;
    }

}
