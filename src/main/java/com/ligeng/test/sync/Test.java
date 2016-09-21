package com.ligeng.test.sync;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dev on 16-6-18.
 */
public class Test{
    public static void main(String[] args) throws InterruptedException {
        final Incr incr = new Incr();
        ExecutorService executor = Executors.newCachedThreadPool();
        final ReentrantLock lock = new ReentrantLock();
        final CountDownLatch latch = new CountDownLatch(100);
        for (int j=0; j<100; j++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try{
                        incr.incrr();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                        lock.unlock();
                    }
                }
            });
        }
        latch.await();
//        TimeUnit.MILLISECONDS.sleep(100000000);
        System.out.println("-------------"+incr.i);
    }
}
class Incr{
    int i=0;
    Random random = new Random();
    public void incrr() throws InterruptedException {
        int m = i;
        int c = random.nextInt(3);
        TimeUnit.MILLISECONDS.sleep(c);
        this.i = m + 1;

    }
}
