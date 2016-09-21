package com.ligeng.test.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by dev on 16-6-22.
 */
public class LockSupportTest {
    public static void main(String[] args) {
//        LockSupport.park();
        final Thread thread1 = new Thread() {
            @Override
            public void run() {
                LockSupport.park(Thread.currentThread());

                System.out.println("thread1 thread1 thread1");
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                try {

                    TimeUnit.MILLISECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                LockSupport.unpark(thread1);
                thread1.interrupt();

            }
        };
        thread1.start();
        thread2.start();

        String s = "";
    }
}
