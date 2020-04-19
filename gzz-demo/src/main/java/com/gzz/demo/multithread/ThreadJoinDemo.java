package com.gzz.demo.multithread;

/**
 * ThreadJoin
 */
public class ThreadJoinDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(()-> {
            System.out.println(Thread.currentThread().getName() +" 开始运行");
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +" 运行结束");
        }, "待处理线程");

        //正式运行线程
        new Thread(()-> {
            System.out.println(Thread.currentThread().getName() +" 开始运行");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +" 运行结束");
        }, "正式线程").start();

    }
}
