package com.gzz.demo.multithread.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsSynchronizedXXX {
    public static void main(String[] args) {
        ArrayList<String> stringArrayList =new ArrayList<>();
        List<String> stringList = Collections.synchronizedList(stringArrayList);

    }
}
