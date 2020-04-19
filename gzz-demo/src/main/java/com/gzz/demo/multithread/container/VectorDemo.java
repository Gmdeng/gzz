package com.gzz.demo.multithread.container;

import java.util.Iterator;
import java.util.Vector;

public class VectorDemo {
    public static void main(String[] args) {
        Vector<String> stringVector = new Vector<>();
        for (int i = 0; i < 1000; i++) {
            stringVector.add("Test==" +i);
        }
        // 错误迭代
//        stringVector.forEach( e ->{
//            if (e.equals("Test==5")){
//                stringVector.remove(e);
//            }
//            System.out.println(e);
//        });

        // 正确迭代
        Iterator<String> stringIterator = stringVector.iterator();
//        while (stringIterator.hasNext()){
//            String next = stringIterator.next();
//            if(next.equals("Test==5")){
//                System.out.println(next);
//                stringIterator.remove();
//            }
//        }

        // 多线程
        for (int i = 0; i < 4; i++) {
            new Thread(()-> {
                synchronized (stringIterator) { // 必须加上锁才行
                    while (stringIterator.hasNext()) {
                        String next = stringIterator.next();
                        if (next.equals("Test==5")) {
                            System.out.println(next);
                            stringIterator.remove();
                        }
                    }
                }
            }).start();
        }

    }
}
