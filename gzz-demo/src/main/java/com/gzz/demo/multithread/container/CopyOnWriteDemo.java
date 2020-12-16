package com.gzz.demo.multithread.container;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add("Test===" + i);
        }

        strings.forEach(e-> {
            if (e.equals("Test==6")) {
                strings.remove(e);
            }
        });

        // 不支持在迭代器里移去元素
//        Iterator<String> stringIterator = strings.iterator();
//        while (stringIterator.hasNext()){
//            String next  =stringIterator.next();
//            if (next.equals("Test==6")) {
//                stringIterator.remove();
//            }
//        }

        for (int i = 0; i < 5; i++) {
            new Thread(()-> {
                strings.forEach(e -> {
                    if (e.equals("Test==6")) {
                        strings.remove(e);
                    }
                });
            }).start();
        }
    }
}
