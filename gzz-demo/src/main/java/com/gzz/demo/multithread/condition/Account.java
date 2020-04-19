package com.gzz.demo.multithread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 账号
 */
public class Account {
    private  int balance =0;
    private int minLimit = 12;
    private int maxLimit = 50;

    private Lock lock = new ReentrantLock();
    private Condition husbandCondition = lock.newCondition();
    private Condition wifeCondition = lock.newCondition();

    /**
     * 存款操作
     */
    public void put() {
        lock.lock();
        try {
            if (balance >= maxLimit) {
                System.out.println("老公[ " + Thread.currentThread().getName() + " ]赚钱存入，余额：" + balance + " ,可以休息一下了");
                try {
                    // 够多钱了，可以先休息一下
                    // wait();
                    husbandCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("老公[ " + Thread.currentThread().getName() + " ]赚钱存入，余额：" + balance + " ,继续工作");
                balance++;
                try {
                    Thread.sleep(500l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 通知妻子，老公赚钱了
                //notifyAll();

                // System.out.println("通知妻子，老公赚钱了");
                wifeCondition.signalAll();
            }
        }finally {
            lock.unlock();
        }

    }

    /**
     * 妻子取款操作
     */
    public void out() {
        lock.lock();
        try {
            if (balance > minLimit) {
                balance--;
                System.out.println("妻子开始[ " + Thread.currentThread().getName() + " ] 消费后，余额：" + balance);
                try {
                    Thread.sleep(500l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 通知老公，继续努力工作赚钱。
                // notifyAll();
                husbandCondition.signalAll();
            } else {
                System.out.println("不够钱[" + Thread.currentThread().getName() + "]妻子消费暂停，等老公赚啦。。，余额：" + balance);
                try {
                    // 家里没有粮了，等老公通知
                    //wait();
                    wifeCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }
}
