package com.gzz.redis.service;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 基准测试
 */

@BenchmarkMode(Mode.Throughput) // 吞吐量
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 结果所使用的时间单位
@State(Scope.Thread) // 每个测试线程分配一个实例
@Fork(2) // Fork进行的数目, 2个线程
@Warmup(iterations = 4, time = 1, timeUnit = TimeUnit.SECONDS) // 先预热4轮，（迭代5次，每次1秒）
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS) // 进行10轮测试
public class BenchMark {

    @Param({"10", "40", "70", "100"}) // 定义四个参数，之后会分别对这四个参数进行测试
    private int n;

    private List<Integer> array;
    private List<Integer> list;

    @Setup(Level.Trial) // 初始化方法，在全部Benchmark运行之前进行
    public void init() {
        array = new ArrayList<>(0);
        list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            array.add(i);
            list.add(i);
        }
    }

    @Benchmark
    public void arrayTraverse() {
        for (int i = 0; i < n; i++) {
            array.get(i);
        }
    }

    @Benchmark
    public void listTraverse() {
        for (int i = 0; i < n; i++) {
            list.get(i);
        }
    }

    @TearDown(Level.Trial) // 结束方法，在全部Benchmark运行之后进行
    public void arrayRemove() {
        for (int i = 0; i < n; i++) {
            array.remove(0);
            list.remove(0);
        }
    }

    /**
     *   启动基准测试（ 测试时会比较长）
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                // 导入要测试的类
                .include(BenchMark.class.getSimpleName())
//                // 预热5轮
//                .warmupIterations(5)
//                // 度量10轮
//                .measurementIterations(10)
//                .mode(Mode.Throughput)
//                .forks(3)
                .build();
        new Runner(options).run();
    }
}