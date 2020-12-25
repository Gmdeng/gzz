package com.gzz.socket.bio;

import java.util.concurrent.*;

public class TimeServerHandlerExecutePool {
    private ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 20, 120L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(5));

    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize){
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 20, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5));
    }
    public void execute(Runnable task){
        executorService.execute(task);
    }
}
