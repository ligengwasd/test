package com.ligeng.test.aqs.note;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by dev on 16-8-4.
 */
public class PoolTest {
    public static void main(String[] args){
        System.out.println("==================start==================");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        int n = 10;
        int sleep = 10 * 1000;  //10s
        Random rm = new Random();
        for(int i=0; i<n; i++) {
            executor.execute(new MyTask(rm.nextInt(sleep)+1));
        }

        executor.shutdown();
//        executor.wait();
        try {
            boolean loop;
            do {    //等待所有任务完成
                loop = !executor.awaitTermination(2, TimeUnit.SECONDS);
            } while(loop);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("==================end====================");
    }

    public static class MyTask implements Runnable {
        private static int id = 0;

        private String name = "task-"+(++id);
        private int sleep;

        public MyTask(int sleep) {
            super();
            this.sleep = sleep;
        }

        public void run() {
            System.out.println(name+" -----start-----");
            try {
                Thread.sleep(sleep);    //模拟任务执行.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+" -----end "+sleep+"-----");
        }

    }
}
