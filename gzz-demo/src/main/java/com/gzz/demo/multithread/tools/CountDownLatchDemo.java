package com.gzz.demo.multithread.tools;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch =new CountDownLatch(8);
        new Thread(()-> {
            try {
                countDownLatch.await(); //进入等待的状态。
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("900米比赛结束，准备清空跑道并继续跨栏比赛。");
        }).start();

        //
        for (int i = 0; i < 8 ; i++) {
            int finalI = i;
            new Thread(()-> {
                try {
                    Thread.sleep(finalI *1000l);
                    System.out.println(Thread.currentThread().getName() +" 完成比赛");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown(); //计数器减一，
                }

            }).start();
        }
    }
}
