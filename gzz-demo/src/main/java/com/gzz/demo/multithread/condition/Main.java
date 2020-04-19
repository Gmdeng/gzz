package com.gzz.demo.multithread.condition;

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        Husband husband =new Husband(account);
        Wife wife = new Wife(account);
        //老公赚钱活动
        new Thread(new Husband(account),"上班").start();
        new Thread(new Husband(account),"做小贩").start();
        new Thread(new Husband(account),"搬砖头").start();
        new Thread(new Husband(account),"洗脚").start();
        //老婆花钱活动
        new Thread(new Wife(account),"买包").start();
        new Thread(new Wife(account),"花装").start();
        new Thread(new Wife(account),"买菜").start();
    }
}
