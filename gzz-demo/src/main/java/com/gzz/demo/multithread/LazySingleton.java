package com.gzz.demo.multithread;

/**
 * 懒汉式线性安全写法
 * 必须用volatile修饰，防止指令重排序。
 * 懒汉式 使用时才创建
 *
 */
public class LazySingleton {
    private static volatile LazySingleton lazySingleton = null;
    private LazySingleton(){

    }
    public static LazySingleton getInstance(){
        //判断实例是否为空，为空则实例化
        if(lazySingleton ==null) {
            synchronized (LazySingleton.class) {
                if(null == lazySingleton) {
                    lazySingleton =new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}
