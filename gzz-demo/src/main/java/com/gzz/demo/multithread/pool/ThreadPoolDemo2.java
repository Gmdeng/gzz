package com.gzz.demo.multithread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池DEMO
 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) throws InterruptedException {
         LinkedBlockingQueue<Runnable> runnableQueues = new LinkedBlockingQueue<>(); //
        //LinkedBlockingQueue<Runnable> runnableQueues = new LinkedBlockingQueue<>(20); //修改队列容量为10，

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 3l
                , TimeUnit.SECONDS, runnableQueues , new CustomPolicy());

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.submit(()->{
                try {
                    TimeUnit.SECONDS.sleep(2l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });

        }
    }

}
