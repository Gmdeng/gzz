package com.gzz.demo.multithread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级DEMO
 */
public class LockDegradeDemo {
    private int i =0;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();
    public void doSomething() {
        writeLock.lock();
        try {
            i++;
            readLock.lock(); //先获取锁
        }finally {
            writeLock.unlock();
        }


        //睡两秒模拟其他复杂的操作
        try {
            Thread.sleep(2000l);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // readLock.lock(); //
        try {
        if(i==1){
            System.out.println("i值是================》1");
        }else {
            System.out.println("i值是：" +i);
        }
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDegradeDemo lockDegradeDemo = new LockDegradeDemo();
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                lockDegradeDemo.doSomething();
            }).start();
        }
    }
}
