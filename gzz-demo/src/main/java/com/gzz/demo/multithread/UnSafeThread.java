package com.gzz.demo.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 线程不安全操作代码实例
 */
public class UnSafeThread {
    private static int num =0;
    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    /**
     * 每次调用对num进行++操作
     */
    public static void inCreate(){
        num ++;
    }

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            new Thread(()-> {
                for (int j = 0; j < 100; j++) {
                    inCreate();
                    try {
                        TimeUnit.SECONDS.sleep(1l);
                        // Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                countDownLatch.countDown();
            }).start();
        }
        System.out.println(countDownLatch.getCount());
        while (true){
            if(countDownLatch.getCount() ==0){
                System.out.println(num);
                break;
            }
        }

    }
}
