package com.gzz.demo.encryption;


import  java.security.MessageDigest;
import  java.security.NoSuchAlgorithmException;
import java.util.Base64;


/**
 *
 */
public class Sha256 {
    public byte [] eccrypt(String info) throws NoSuchAlgorithmException{
        //根据SHA256算法生成MessageDigest对象
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte [] srcBytes = info.getBytes();
        //使用srcBytes更新摘要
        sha.update(srcBytes);
        //完成哈希计算，得到result
        byte [] resultBytes = sha.digest();
        return resultBytes;
    }

    public static String encryptBase64(byte[] key) throws Exception {
        return Base64.getEncoder().encodeToString(key);
    }

    public static void main(String args[]) throws Exception{
        //获取开始时间的时间戳
        long startTime = System.currentTimeMillis();
        String msg = "In doing we learn." ;
        Sha256 sha = new Sha256();
        byte [] resultBytes = sha.eccrypt(msg);
        String encontent = null;
        encontent = encryptBase64(resultBytes);
        System.out.println("----SHA256 哈希结果----");
        System.out.println();
        System.out.println("待 Hash 的明文： " + msg);
        System.out.printf("Hash 的结果为：  " + encontent);
        //获取结束时间的时间戳
        long endTime = System.currentTimeMillis();
        //输出程序运行时间
        System.out.println("SHA256 哈希的时间消耗为：" + (endTime - startTime) + " ms");
    }
}
