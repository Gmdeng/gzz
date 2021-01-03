package com.gzz.console;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStopWatch {
    @Test
    public void testStop() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("任务耗时秒表工具");

        stopWatch.start("task1");
        Thread.sleep(1000);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        stopWatch.start("task2");
        // Thread.sleep(3000);
        TimeUnit.MILLISECONDS.sleep(3000);
        stopWatch.stop();
        //所有任务耗时时间
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println(stopWatch.prettyPrint());

        StopWatch stopWatch2 = new StopWatch("任务耗时秒表工具2");
        stopWatch2.start("task3");
        Thread.sleep(3000);
        stopWatch2.stop();
        //所有任务耗时时间
        System.out.println(stopWatch2.getTotalTimeMillis());
        System.out.println(stopWatch2.prettyPrint());
    }
    @Test
    public void TestSteam(){
        String[] array = {"youk u1327", "ABC C987"};
        // 存放的是一个个数组 [Ljava.lang.String;@61f3fbb8
//        Arrays.stream(array).map(s -> s.split(""))
//                .forEach(System.out::print);
        // 将一个个数组流合并为一个流输出：youku1327
        Arrays.stream(array).map(s ->{
            System.out.println(s);
            return s.split("|");

        } )
                .flatMap(Arrays::stream)
                .forEach(System.out::print);
    }
}
