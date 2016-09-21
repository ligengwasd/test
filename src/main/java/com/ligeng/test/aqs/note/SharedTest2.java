package com.ligeng.test.aqs.note;

import com.dream.utils.DateUtil;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 16-7-25.
 */
public class SharedTest2 {
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(1);
        for (int i=1; i<=5; i++){
            TimeUnit.MILLISECONDS.sleep(100);
            Thread thread = new Thread("thread-"+i) {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    try {
                        semaphore.acquire(1);
                        System.out.println(DateUtil.dateToString(new Date(), DateUtil.DATETIME_FORMAT) + " " + name + " start");
                        TimeUnit.SECONDS.sleep(3);
                        semaphore.release(1);
                        System.out.println(DateUtil.dateToString(new Date(), DateUtil.DATETIME_FORMAT) + " " + name + " end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();

        }

    }

}
