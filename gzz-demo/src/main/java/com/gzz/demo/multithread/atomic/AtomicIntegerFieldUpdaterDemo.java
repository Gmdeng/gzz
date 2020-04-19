package com.gzz.demo.multithread.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 原子对象属性
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) {
        // LONG类型
       AtomicLongFieldUpdater<Student> idField = AtomicLongFieldUpdater.newUpdater(Student.class,"id");
        Student student = new Student(1L, "G-m");
        idField.compareAndSet(student, 1L, 2345L);
        System.out.println("ID=" +student.getId());
        // 字符类型
        AtomicReferenceFieldUpdater<Student, String> nameUpdater = AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");
        nameUpdater.compareAndSet(student, "G-m", "Gmdeng");
        System.out.println("Name=" +student.getName());
    }


}

class Student {
    volatile long id;
    volatile String name;
    public  Student(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}