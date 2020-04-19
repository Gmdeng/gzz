package com.gzz.demo.multithread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池DEMO
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
         LinkedBlockingQueue<Runnable> runnableQueues = new LinkedBlockingQueue<>(); //

        for (int i = 0; i <100 ; i++) {
            runnableQueues.put(()-> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 3l
                , TimeUnit.SECONDS, runnableQueues , new CustomPolicy());
        // 不调用这个不会输出
        threadPoolExecutor.prestartAllCoreThreads();

    }

}
