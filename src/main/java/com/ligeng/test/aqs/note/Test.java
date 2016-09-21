package com.ligeng.test.aqs.note;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dev on 16-7-23.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        Thread thread3 = new Thread("thread3"){
            @Override
            public void run() {
//                while(true){
                    lock.lock();
                    try{
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }finally {
                        lock.unlock();
                    }

//                }
            }
        };

        final Thread thread1 = new Thread("thread1"){
            @Override
            public void run() {
                lock.lock();
                try{
                    try {
                        condition.await();
                        System.out.println("thread1 thread1 thread1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally {
                    lock.unlock();
                }
            }
        };


        Thread thread2 = new Thread("thread2"){
            @Override
            public void run() {
                try{
                    try {
                        System.out.println("thread2 thread2 thread2");
                        TimeUnit.MILLISECONDS.sleep(10000);
//                        condition.signal();
                        thread1.interrupt();
                        System.out.println("sssss");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally {

                }
            }
        };
//        thread3.start();
//        TimeUnit.MILLISECONDS.sleep(100);
        thread1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        thread2.start();
//        TimeUnit.MILLISECONDS.sleep(10000);
//        thread3.start();
    }

}
