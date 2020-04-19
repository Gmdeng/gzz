package com.gzz.demo.multithread.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockDemo实例
 */
public class LockDemo {
    private static int num =0;
    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    private static Lock lock = new ReentrantLock();
    /**
     * 每次调用对num进行++操作
     */
    public static void inCreate(){
        lock.lock();
        num ++;
        lock.unlock();
    }

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            new Thread(()-> {
                for (int j = 0; j < 100; j++) {
                    inCreate();
                    try {
                        // TimeUnit.SECONDS.sleep(1000L);
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                countDownLatch.countDown();
            }).start();
        }
        while (true){
            if(countDownLatch.getCount() ==0){
                System.out.println(num);
                break;
            }
        }

    }
}
