package com.gzz.demo.multithread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平/非公平锁
 */
public class ReentrantLockDebugDemo {
    private int i =0;
    private ReentrantLock reentrantLock = new ReentrantLock();
    public void inCreate(){
        reentrantLock.lock();
        try {
            i++;
            System.out.println(i);
        }finally {
            reentrantLock.unlock();
        }

    }

    public static void main(String[] args) {
        ReentrantLockDebugDemo reentrantLockDebugDemo =new ReentrantLockDebugDemo();
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                reentrantLockDebugDemo.inCreate();
            }).start();
        }
    }
}
