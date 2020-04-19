package com.gzz.demo.multithread;

public class SuspendDemo implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +"执行RUN方法，准备调用Suspend方法");
        Thread.currentThread().suspend();
        System.out.println(Thread.currentThread().getName() +"执行RUN方法，准备调用Suspend结束");

    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SuspendDemo());
        thread.start();
        Thread.sleep(3000L);
        thread.resume();

    }
}
