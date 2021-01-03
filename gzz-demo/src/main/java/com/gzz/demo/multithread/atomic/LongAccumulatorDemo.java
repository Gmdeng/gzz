package com.gzz.demo.multithread.atomic;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * 原子基本类型使用2
 */
public class LongAccumulatorDemo {
    public static void main(String[] args) {
//        int[] arr = new int[] {3,2};
//        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(arr);
//        System.out.println(atomicIntegerArray.addAndGet(1,8));
//        int i = atomicIntegerArray.accumulateAndGet(0,2, (left, right)-> left >right? left: right);
        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x>y?x:y, 0l);

        longAccumulator.accumulate(3l);
        System.out.println(longAccumulator.get());
        longAccumulator.accumulate(5l);
        System.out.println(longAccumulator.get());
        longAccumulator.accumulate(4l);
        System.out.println(longAccumulator.get());
    }
}
