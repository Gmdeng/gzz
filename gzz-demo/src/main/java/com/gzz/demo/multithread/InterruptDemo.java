package com.gzz.demo.multithread;

import java.util.concurrent.TimeUnit;

public class InterruptDemo implements Runnable {
    @Override
    public void run() {
        while (Thread.currentThread().isInterrupted() ==false){
            System.out.println(Thread.currentThread().getName() +" 运行中。。");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptDemo());
        thread.start();
        Thread.sleep(5000L);
        Thread.interrupted();
    }
}
