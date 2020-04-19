package com.gzz.demo.multithread.bank;

/**
 * 账号
 */
public class Account {
    private  int balance =0;
    private int minLimit = 12;
    private int maxLimit = 50;

    /**
     * 存款操作
     */
    public synchronized void put() {
        if(balance >= maxLimit) {
            System.out.println("老公[ " + Thread.currentThread().getName() +" ]赚钱存入，余额："+ balance + " ,可以休息一下了");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("老公[ " + Thread.currentThread().getName() +" ]赚钱存入，余额："+ balance + " ,继续工作");
            balance++;
            try {
                Thread.sleep(800l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyAll();
        }

    }

    /**
     * 取款操作
     */
    public synchronized void out() {
        if(balance > minLimit) {
            balance--;
            System.out.println("妻子开始[ " + Thread.currentThread().getName() +" ] 消费后，余额："+ balance );
            try {
                Thread.sleep(500l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyAll();
        }else {
            System.out.println("不够钱["+ Thread.currentThread().getName() +"]妻子消费暂停，等老公赚啦。。，余额："+ balance );
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
