package com.gzz.demo.encryption;
import  java.io.UnsupportedEncodingException;
import  java.security.MessageDigest;
import  java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * MD5：
 * 算法介绍
 *      1,信息摘要算法；
 *      2,压缩性：任意长度的数据，可以算出固定长度；
 *      3,容易计算：从原数据计算 MD5 很容易；
 *      4,抗修改性：对原数据修改 1 个字节，MD5 值的变化都很大；
 *      5,强碰撞性：找一个具有相同 MD5 值的数据（伪造）比较困难；
 *      6,具有不可逆性。
 *
 *
 * 算法流程
 *   按照 512 位分组处理，每一个分组分为 16 个 32 位子分组，处理后输出 4 个 32 位分组，将这 4 个分组级联后生成 128 位散列值。
 *
 * 优点
 *   简单、难以伪造。
 *
 * 缺点
 *   具有潜在的冲突；有破解的案例。
 *
 * 应用场景
 *      登录密码保护；
 *      防止文件篡改；
 *      HTTP 传输内容加密防篡改；
 *      用于数字签名。
 *
 * 安全性
 *      较高。

 */
public class Md5 {

    public byte [] eccrypt(String info) throws NoSuchAlgorithmException{
        //根据MD5算法生成MessageDigest对象
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte [] srcBytes = info.getBytes();
        //使用srcBytes更新摘要
        md5.update(srcBytes);
        //完成哈希计算，得到result
        byte [] resultBytes = md5.digest();
        return resultBytes;
    }

    public static String encryptBase64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static void main(String args[]) throws Exception {
        //获取开始时间的时间戳
        long startTime = System.currentTimeMillis();
        String msg = "In doing we learn.";
        Md5 md5 = new Md5();
        byte[] resultBytes = md5.eccrypt(msg);
        String encontent = null;
        encontent = encryptBase64(resultBytes);
        System.out.println("----MD5 哈希结果----");
        System.out.println();
        System.out.println("待 Hash 的明文： " + msg);
        System.out.printf("Hash 的结果为：  " + encontent);
        //获取结束时间的时间戳
        long endTime = System.currentTimeMillis();
        //输出程序运行时间
        System.out.println("MD5 哈希的时间消耗为：" + (endTime - startTime) + " ms");
    }
}