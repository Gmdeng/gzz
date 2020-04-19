package com.gzz.demo.multithread.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子数组类型
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        int[] arr = new int[] {3,2};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(arr);
        System.out.println(atomicIntegerArray.addAndGet(1,3)); // 数组第二个元素值加上3
        int i = atomicIntegerArray.accumulateAndGet(0,8, (left, right)-> left >right? left: right);
        System.out.println("i:" + i);
        int j = atomicIntegerArray.accumulateAndGet(0,8, (left, right)-> left * right);
        System.out.println("j:" + i);
    }
}
