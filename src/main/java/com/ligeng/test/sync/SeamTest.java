package com.ligeng.test.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by dev on 16-6-23.
 */
public class SeamTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        final Semaphore semaphore   = new Semaphore(3);
        for (int i=0; i<10; i++){
            final int NO = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire(2);
                        System.out.println("accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 1000));
                        // 访问完后，释放
                        System.out.println("release: " + NO);
                        semaphore.release(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
    }
}
