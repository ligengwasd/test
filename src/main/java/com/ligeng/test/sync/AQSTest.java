package com.ligeng.test.sync;

import com.dream.utils.DateUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dev on 16-6-22.
 */
public class AQSTest {
    public static void main(String[] args) {
        final Lock lock = new Mutex();
        final Thread thread1 = new Thread(){
            public void run() {
                lock.lock();
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    System.out.println("thread1 : "+DateUtil.dateToString(new Date(),"yyyy-MM-dd mm-ss"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        Thread thread2 = new Thread(){
            public void run() {
                try {
                    try {
                        lock.lockInterruptibly();
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
//                    TimeUnit.MILLISECONDS.sleep(3000);
                    System.out.println("thread2 : "+DateUtil.dateToString(new Date(),"yyyy-MM-dd mm-ss"));
                } finally {
                    lock.unlock();
                }
            }
        };
//        Thread thread3 = new Thread(){
//            public void run() {
//                lock.lock();
//                try {
//                    TimeUnit.MILLISECONDS.sleep(3000);
//                    System.out.println(DateUtil.dateToString(new Date(),"yyyy-MM-dd mm-ss"));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    lock.unlock();
//                }
//            }
//        };
        System.out.println("main    : "+DateUtil.dateToString(new Date(),"yyyy-MM-dd mm-ss"));
        thread1.start();
        thread2.start();

        thread2.interrupt();
    }
}
