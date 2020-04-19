package com.gzz.demo.multithread.container;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> strings=new LinkedBlockingQueue<>();
        // 往队列里存放元素
        strings.add("testi"); // add实际是指向offer
        strings.offer("testi");  //如队列满了就不入列。失败
        strings.put("testi");

        //从队列里读取元素
        String remove = strings.remove();
        strings.poll();
        strings.take();
    }
}
