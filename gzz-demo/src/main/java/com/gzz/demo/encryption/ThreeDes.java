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
 * 3DES（DES ede）（或称为Triple DES）——是三重数据加密算法（TDEA，Triple Data Encryption Algorithm）的通称 ：
 *
 * 算法介绍
 *  1,三重 DES 加密算法；
 *  2, 每个数据块用三次 DES 加密；
 *  3,是 DES 向 AES 过渡的加密算法。
 *
 * 算法流程
 *
 * 加密过程：
 * 解密过程：

 * 破解方式
 *   难度较大。
 *
 * 安全性
 *   较高。
 */
public class ThreeDes {
    // KeyGenerator 提供对称密钥生成器的功能，支持各种算法
    private KeyGenerator keygen;
    // SecretKey 负责保存对称密钥
    private SecretKey deskey;
    // Cipher负责完成加密或解密工作
    private Cipher c;
    // 该字节数组负责保存加密的结果
    private byte [] cipherByte;

    public ThreeDes() throws NoSuchAlgorithmException, NoSuchPaddingException {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        // 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
        keygen = KeyGenerator.getInstance("DESede" );
        // 生成密钥
        deskey = keygen.generateKey();
        // 生成Cipher对象,指定其支持的DES算法
        c = Cipher.getInstance("DESede" );
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
        // 加密，结果保存进cipherByte
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
        return cipherByte;
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
        ThreeDes de1 = new ThreeDes();
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
        System.out.println("----3DES 加密结果----");
        System.out.println();
        System.out.println("待加密的明文: " + msg);
        //System.out.println("加密后:" + new String(encontent, "utf-8"));
        System.out.printf("加密的结果为: " + encontent);
        System.out.println("解密的结果为: " + new String(decontent));
        //获取结束时间的时间戳
        long endTime = System.currentTimeMillis();
        //输出程序运行时间
        System.out.println("3DES 加密的时间消耗为：" + (endTime - startTime) + " ms");
    }
}