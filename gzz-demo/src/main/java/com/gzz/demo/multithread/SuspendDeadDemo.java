package com.gzz.demo.multithread;

public class SuspendDeadDemo implements Runnable {
    private static Object object = new Object();
    @Override
    public void run() {
        // 持有资源
        synchronized (object){
            System.out.println(Thread.currentThread().getName()+ "占用资源");
            Thread.currentThread().suspend();
        }
        System.out.println(Thread.currentThread().getName()+ "释放资源");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SuspendDeadDemo(), "对比线程");
        thread.start();
        Thread.sleep(3000L);
        thread.resume();
        Thread deadThread = new Thread(new SuspendDeadDemo(), "死锁线程");
        deadThread.start();
        // Thread.sleep(3000L); 不睡时，会死锁的
        deadThread.resume();
    }
}
