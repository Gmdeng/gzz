package com.gzz.demo.multithread.pool;

import java.util.concurrent.*;

/**
 *
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LinkedBlockingQueue<Runnable> runnableQueues = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 3l
                , TimeUnit.SECONDS, runnableQueues);

        Future<String> submits = null;
        for (int i = 0; i < 100; i++) {
            submits =threadPoolExecutor.submit(new CallableDemo());
        }
        for (int i = 0; i <100 ; i++) {
            System.out.println("获取" + submits.get());
        }
    }

}
