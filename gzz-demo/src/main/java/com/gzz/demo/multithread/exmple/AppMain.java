package com.gzz.demo.multithread.exmple;

import com.gzz.demo.multithread.exmple.biz.DealHandleBiz;
import com.gzz.demo.multithread.exmple.thread.Consumer;
import com.gzz.demo.multithread.exmple.thread.Producer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 一个生产者对多个消费者。
 */
public class AppMain {
    public static void main(String[] args) {
        DealHandleBiz dealHandleBiz = new DealHandleBiz();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        LinkedBlockingQueue<Consumer> runnables = new LinkedBlockingQueue<>(10);

        for (int i = 0; i < 10; i++) {
            try {
                runnables.put(new Consumer(dealHandleBiz, runnables));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //
        new Thread(new Producer(threadPoolExecutor, runnables, dealHandleBiz)).start();
    }
}
