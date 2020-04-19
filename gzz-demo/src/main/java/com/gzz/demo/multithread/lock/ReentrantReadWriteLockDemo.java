package com.gzz.demo.multithread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReentrantReadWriteLockDemo {
    private int i=0, j=0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();
    public void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "i的值： " + i + "    j的值： " + j);
        }finally {
            readLock.unlock();
        }
    }
    public void write() {
        writeLock.lock();
        try {
            i++;
            Thread.sleep(500L);
            j++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo reentrantReadWriteLockDemo = new ReentrantReadWriteLockDemo();
        for (int i = 0; i <3 ; i++) {
            new Thread(()-> {
                reentrantReadWriteLockDemo.write();
                reentrantReadWriteLockDemo.read();
            }).start();
        }
    }

}
