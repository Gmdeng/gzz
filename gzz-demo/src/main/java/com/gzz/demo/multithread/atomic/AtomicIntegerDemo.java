package com.gzz.demo.multithread.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子基本类型使用
 */
public class AtomicIntegerDemo {
    private static AtomicInteger sum = new AtomicInteger(0);

    public static void inCreate(){
        sum.incrementAndGet();
    }

    public static void main(String[] args) {
//        for (int i = 0; i <10 ; i++) {
//            for (int j = 0; j < 10; j++) {
//                inCreate();
//                System.out.println("Sum: " +sum);
//            }
//        }
        for (int i = 0; i < 10 ; i++) {
            new Thread(()-> {
                for (int j = 0; j < 10; j++) {
                    inCreate();
                    System.out.println("Sum: " +sum);
                    try {
                        TimeUnit.SECONDS.sleep(2l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
