package com.gzz.demo.multithread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟OOM
 *  = 在运行 VM options 里添加以下 =====
 *  -Xms60m
 *  -Xmx60
 *  -XX:+HeapDumpOnOutOfMemoryError
 *  -XX:HeapDumpPath=c:\opt\
 */
public class OOMDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        while (true) {
            executorService.submit(() ->{
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
