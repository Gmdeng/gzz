package com.gzz.demo.encryption;
import sun.misc.BASE64Encoder;

import  java.security.InvalidKeyException;
import  java.security.NoSuchAlgorithmException;
import  java.security.Security;

import  javax.crypto.BadPaddingException;
import  javax.crypto.Cipher;
import  javax.crypto.IllegalBlockSizeException;
import  javax.crypto.KeyGenerator;
import  javax.crypto.NoSuchPaddingException;
import  javax.crypto.SecretKey;

/**
 * DES 数据加密标准（Data Encryption Standard）：
 *
 * 算法介绍
 * 1, 属于对称加密算法；
 * 2, 数据分组（64 位）用密钥（64 位；其中 56 位有效位，8 位校验位）加密；
 * 3, 算法公开，对密钥保护。
 *
 * 算法流程
 * 1,根据用户输入，取得一个 64 位的密钥，然后进行等分、移位、选取和迭代形成一套 16 个加密密钥，分别提供每轮运算使用；
 * 2,对 64 位明文分组 M 进行操作，M 经过初期置换 IP ，置换为 m0，将 m0 分为左右各 32 位长，并进行 16 轮相同的运算（迭代），每轮运算都和相应的密钥结合；
 * 3,在每一轮中，密码位移位，从密钥的 56 位中选出 48 位，通过一个扩展置换将数据右半边扩展成 48 位，并通过异或操作替代成新的 48 位；然后压缩至 32 位，
 *   并通过一个异或与左半边结合，其结果为右半边，原来的右半边成为左半边，该操作执行 16 次；
 * 4,经过 16 轮迭代，左右部分合在一起进行一个末置换（数据整理），完成加密过程；
 * 5, 解密时同样使用此算法。
 *
 * 优点
 *   算法公开、计算量小、加密速度快、效率高。
 *
 * 缺点
 *   如果双方都持有密钥，安全性无法保证；
 *   密钥安全的保护成本高，管理困难。
 *   破解方式
 *   暴力破解、穷举。
 *
 * 适用场景
 *   普通数据加密。
 *
 * 安全性
 *   低。
 */
public class Des {
    //KeyGenerator 提供对称密钥生成器的功能，支持各种算法
    private KeyGenerator keygen;
    //SecretKey 负责保存对称密钥
    private SecretKey deskey;
    //Cipher负责完成加密或解密工作
    private Cipher c;
    //该字节数组负责保存加密的结果
    private byte [] cipherByte;

    public Des() throws NoSuchAlgorithmException, NoSuchPaddingException{
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        //实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
        keygen = KeyGenerator.getInstance("DES");
        //生成密钥
        deskey = keygen.generateKey();
        //生成Cipher对象,指定其支持的DES算法
        c = Cipher.getInstance("DES");
    }
    /**
     * 对字符串加密
     *
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte [] Encrytor(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
        c.init(Cipher.ENCRYPT_MODE, deskey);
        byte [] src = str.getBytes();
        // 加密，结果保存在cipherByte中
        cipherByte = c.doFinal(src);
        return cipherByte;
    }

    /**
     * 对字符串解密
     *
     * @param buff
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte [] Decryptor(byte [] buff) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
        c.init(Cipher.DECRYPT_MODE, deskey);
        cipherByte = c.doFinal(buff);
        return  cipherByte;
    }

    public static String ByteToString(byte[] bytes) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i <bytes.length ; i++) {
            if (bytes[i]!=0){
                strBuilder.append((char)bytes[i]);
            }
            else {
                break;
            }
        }
        return strBuilder.toString();
    }

    /**
     * BASE64 加密
     *
     * @param key 需要加密的字节数组
     * @return 字符串
     * @throws Exception
     */
    public static String encryptBase64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * @param args
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    public static void main(String[] args) throws Exception {
        //获取开始时间的时间戳
        long startTime = System.currentTimeMillis();
        Des de1 = new Des();
        //源码文件是GBK格式，或者这个字符串是从GBK文件中读取出来的, 转换为string 变成unicode格式
        String msg1 = "In doing we learn.";
        //利用getBytes将unicode字符串转成UTF-8格式的字节数组
        byte[] utf8Bytes = msg1.getBytes("UTF-8");
        //用utf-8 对这个字节数组解码成新的字符串
        String msg = new String(utf8Bytes, "UTF-8");
        byte [] encData = de1.Encrytor(msg);
        String encontent = null;
        encontent = encryptBase64(encData);
        byte [] decontent = de1.Decryptor(encData);
        System.out.println("----DES 加密结果----");
        System.out.println();
        System.out.println("待加密的明文: " + msg);
        //System.out.println("加密后:" + new String(encontent, "utf-8"));
        System.out.printf("加密的结果为: " + encontent);
        System.out.println("解密的结果为: " + new String(decontent));
        //获取结束时间的时间戳
        long endTime = System.currentTimeMillis();
        //输出程序运行时间
        System.out.println("DES 加密的时间消耗为：" + (endTime - startTime) + " ms");
    }
}