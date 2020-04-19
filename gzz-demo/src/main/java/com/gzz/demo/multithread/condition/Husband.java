package com.gzz.demo.multithread.condition;

/**
 * 老公存钱
 */
public class Husband implements Runnable{
    private Account account;
    public Husband(Account account) {
        this.account =account;
    }
    @Override
    public void run() {
        while (true) {
            account.put();
        }

    }
}
