package com.gzz.demo.multithread;

public class WaitDemo implements Runnable{

    private static Object waitObj = new Object();

    @Override
    public void run() {
        // 持有资源
        synchronized (waitObj){
            System.out.println(Thread.currentThread().getName()+ "占用资源");
            try {
                waitObj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+ "释放资源");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SuspendDeadDemo(), "对比线程1");
        thread.start();

        Thread thread2 = new Thread(new SuspendDeadDemo(), "对比线程2");
        thread2.start();
        Thread.sleep(3000L);
        synchronized (waitObj) {
            waitObj.notify();
        }
    }
}
