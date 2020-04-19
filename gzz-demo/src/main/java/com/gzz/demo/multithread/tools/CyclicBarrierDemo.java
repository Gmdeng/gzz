package com.gzz.demo.multithread.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        for (int i = 0; i < 10 ; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(3000l);
                    System.out.println(Thread.currentThread().getName() +"准备就绪");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("开始比赛");
            }).start();
        }
    }
}
