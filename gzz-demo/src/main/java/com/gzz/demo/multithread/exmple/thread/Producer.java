package com.gzz.demo.multithread.exmple.thread;

import com.gzz.demo.multithread.exmple.biz.DealHandleBiz;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 生产者
 */
public class Producer implements  Runnable{
    private DealHandleBiz dealHandleBiz;
    private ThreadPoolExecutor executor;
    private LinkedBlockingQueue<Consumer> consumers;


    public Producer(ThreadPoolExecutor executor, LinkedBlockingQueue<Consumer> consumers, DealHandleBiz dealHandleBiz){
        this.executor =executor;
        this.consumers = consumers;
        this.dealHandleBiz =dealHandleBiz;

    }
    @Override
    public void run() {
        while (true) {
            List<String> dataList = dealHandleBiz.getWaitHandleList(10);
            if(dataList != null && dataList.size() >0) {
                // 先标记一下读出来的数据

                try {
                    // 读出一个消费者
                    Consumer consumer = consumers.take();
                    consumer.setWaitList(dataList);
                    // 放入线程池
                    executor.execute(consumer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }else {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
