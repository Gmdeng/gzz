package com.gzz.demo.multithread;

/**
 * Synchronized
 */
public class SynchronizedDemo {
    public synchronized void out() throws InterruptedException {
        for (int i = 0; i < 10 ; i++) {
            System.out.println(Thread.currentThread().getName() + ">> " + i);
            Thread.sleep(2000L);
        }
    }

    public static synchronized void staticOut() throws InterruptedException {
        for (int i = 0; i < 10 ; i++) {
            System.out.println(Thread.currentThread().getName() + ">> " + i);
            Thread.sleep(2000L);
        }
    }

    private Object lock = new Object();
    public void objectOut() throws InterruptedException {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ">> " + i);
                Thread.sleep(2000L);
            }
        }
        System.out.println("能不能锁住我呀，" + Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        SynchronizedDemo sync1 = new SynchronizedDemo();
        // SynchronizedDemo sync2 = new SynchronizedDemo();
        new Thread(()->{
            try{
                // sync1.out();
                // sync1.staticOut();
                sync1.objectOut();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try{
                //sync1.out();
                // sync2.out();
                // sync2.staticOut();
                sync1.objectOut();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}
