package com.ligeng.test.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dev on 16-6-22.
 */
public class Buffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();// 写
    final Condition notEmpty = lock.newCondition();// 读

    final Object[] items = new Object[100];
    int putptr,takeptr,count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try{
            while(count==100){
                notFull.await();
            }
            items[putptr] = x;
            count++;
            notEmpty.signal();

        }finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lock();
        try{
            while (count == 0){
                notEmpty.await();
            }
            //
            --count;
            notFull.signal();
        }finally {
            lock.unlock();
        }
    }


}
