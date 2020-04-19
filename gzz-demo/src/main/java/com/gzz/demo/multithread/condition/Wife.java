package com.gzz.demo.multithread.condition;

/**
 * 老婆花钱
 */
public class Wife implements  Runnable{

    private Account account;
    public Wife(Account account) {
        this.account =account;
    }
    @Override
    public void run() {
        while (true) {
            account.out();
        }

    }
}
