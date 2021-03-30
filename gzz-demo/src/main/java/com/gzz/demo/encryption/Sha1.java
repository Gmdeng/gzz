package com.gzz.demo.encryption;

import  java.security.MessageDigest;
import  java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * SHA1：
 * 算法介绍
 *      1,属于消息摘要算法；
 *      2,用于签名算法，保护数据的完整性；
 *      3,算法不可逆；
 *      4,消息算法：512 位。
 *
 * 算法流程
 *   把原始信息变换成位（二进制）字符串，5 个步骤计算：
 *   1）补位：消息满足长度在对 512 取模后余数是 448，否则补位；
 *   2）补长度：原始数据长度补到补位操作的后面，如果大于 512，补成 512 的倍数；
 *   3）使用常量和相关的函数；
 *   4）计算消息摘要。
 *
 * 优点
 *   保密性强。
 *
 * 缺点
 *   效率较低；难度大。
 *
 * 应用场景
 *      数字签名；
 *      数据完整性保护。
 * 安全性
 *      高。
 *
 */
public class Sha1 {
    public byte [] eccrypt(String info) throws NoSuchAlgorithmException{
        //根据SHA1算法生成MessageDigest对象
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
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
        Sha1 sha = new Sha1();
        byte [] resultBytes = sha.eccrypt(msg);
        String encontent = null;
        encontent = encryptBase64(resultBytes);
        System.out.println("----SHA1 哈希结果----");
        System.out.println();
        System.out.println("待 Hash 的明文： " + msg);
        System.out.printf("Hash 的结果为：  " + encontent);
        //获取结束时间的时间戳
        long endTime = System.currentTimeMillis();
        //输出程序运行时间
        System.out.println("SHA1 哈希的时间消耗为：" + (endTime - startTime) + " ms");
    }
}
