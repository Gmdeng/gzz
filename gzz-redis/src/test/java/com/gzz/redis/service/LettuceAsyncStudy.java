package com.gzz.redis.service;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput) // 吞吐量
@Warmup(iterations = 4) // 先预热4轮
@Threads(100)
@State(Scope.Benchmark)  // 每个测试线程分配一个实例
@Measurement(iterations = 2, time = 600, timeUnit = TimeUnit.MILLISECONDS)  // 进行10轮测试
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 结果所使用的时间单位
public class LettuceAsyncStudy {
    private static final int LOOP = 1;
    private StatefulRedisConnection<String, String> connection;
    @Setup
    public void setup() {
        RedisClient client = RedisClient.create("redis://localhost");
        connection = client.connect();
    }
    @Benchmark
    public void get() throws ExecutionException, InterruptedException {
        RedisAsyncCommands<String, String> commands = connection.async();
        List<RedisFuture<String>> redisFutureList = new ArrayList<>();
        for (int i = 0; i < LOOP; ++i) {
            RedisFuture<String> future = commands.get("a");
            redisFutureList.add(future);
            future.get();
        }
        redisFutureList.forEach(f -> {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 启动基准测试（ 测试时会比较长）
     * @param args
     */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                // 导入要测试的类
                .include(BenchMark.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
}