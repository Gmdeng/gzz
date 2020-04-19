package com.gzz.demo.multithread.exmple.thread;

import com.gzz.demo.multithread.exmple.biz.DealHandleBiz;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable {
    private List<String> waitList;
    private DealHandleBiz dealHandleBiz;
    private LinkedBlockingQueue<Consumer> consumers;

    public Consumer(DealHandleBiz dealHandleBiz, LinkedBlockingQueue<Consumer> consumers) {
        this.dealHandleBiz = dealHandleBiz;
        this.consumers = consumers;
    }

    public void setWaitList(List<String> waitList) {
        this.waitList = waitList;
    }

    @Override
    public void run() {
        try{
            dealHandleBiz.makeHandle(waitList);
        }finally {
            try {
                // 将自己放入队列中
                consumers.put(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
