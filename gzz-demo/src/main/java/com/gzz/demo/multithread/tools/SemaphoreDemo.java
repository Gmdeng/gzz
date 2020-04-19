package com.gzz.demo.multithread.tools;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(8);
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "开始执行");
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
