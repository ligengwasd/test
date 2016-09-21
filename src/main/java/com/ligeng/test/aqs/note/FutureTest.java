package com.ligeng.test.aqs.note;

import java.util.concurrent.*;

/**
 * Created by dev on 16-8-10.
 */
public class FutureTest {
    public static void main(String[] args){
        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(2000);
                return 100;
            }
        };
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(futureTask);

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
