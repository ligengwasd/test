package com.ligeng.test.aqs.note;

import com.dream.utils.DateUtil;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 16-7-25.
 */
public class SharedTest {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(3);
        for (int i=1; i<=3; i++){
            Thread thread = new Thread("thread-"+i) {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    try {
//                        System.out.println(DateUtil.dateToString(new Date(), DateUtil.DATETIME_FORMAT) + " " + name);
                        TimeUnit.SECONDS.sleep(10);
                        latch.countDown();
                        System.out.println(DateUtil.dateToString(new Date(), DateUtil.DATETIME_FORMAT) + " " + name);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();

        }
        for (int i=1; i<=3; i++){
            TimeUnit.SECONDS.sleep(1);
            Thread thread = new Thread("thread-#-"+i) {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    try {
//                        TimeUnit.SECONDS.sleep(3);
                        latch.await();
                        System.out.println(DateUtil.dateToString(new Date(), DateUtil.DATETIME_FORMAT) + " " + name);
//                        System.out.println(DateUtil.dateToString(new Date(), DateUtil.DATETIME_FORMAT) + " " + name);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();

        }
//        Thread thread1 = new Thread("thread-s") {
//            @Override
//            public void run() {
//                String name = Thread.currentThread().getName();
//                try {
//                    latch.await();
//                    System.out.println(DateUtil.dateToString(new Date(), DateUtil.DATETIME_FORMAT) + " " + name);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        thread1.start();

    }
}
