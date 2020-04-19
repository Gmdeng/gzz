package com.gzz.demo.multithread.tools;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        String str1 = "xdclass";
        String str2 = "wiggin";
        //
        new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + "初始化----->" + str1);
            try {
                String exchange = exchanger.exchange(str1);
                System.out.println(Thread.currentThread().getName() + "交换后----->" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程一").start();

        new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + "初始化----->" + str2);
            try {
                String exchange = exchanger.exchange(str2);
                System.out.println(Thread.currentThread().getName() + "交换后----->" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程二").start();
    }
}
