package com.gzz.demo.multithread.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子更新引用
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<Student> studentAtomicReference = new AtomicReference<>();
        Student student = new Student(1L, "G-m");
        Student student2 = new Student(2L, "G444m");
        studentAtomicReference.set(student);  //先设置要引用的对象
        studentAtomicReference.compareAndSet(student, student2);
        Student student1 = studentAtomicReference.get();
        System.out.println(student1.getName());
    }
}
