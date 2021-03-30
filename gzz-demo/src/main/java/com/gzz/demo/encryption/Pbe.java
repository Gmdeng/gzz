package com.gzz.demo.encryption;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

/**
 *  PBE（Password Based Encryption，基于口令加密）：
 * 算法原理
 *   PBE（Password Based Encryption，基于口令加密）是一种基于口令的加密算法，其特点是使用口令代替了密钥，而口令由用户自己掌管，采用随机数、杂凑、多重加密等方法保证数据的安全性。
 *
 *   PBE 算法在加密过程中并不是直接使用口令来加密，而是加密的密钥由口令生成，这个功能由 PBE 算法中的 KDF 函数完成。
 *
 *
 * 算法流程
 *   KDF 函数的实现过程为：
 *
 * 将用户输入的口令首先通过“盐”（salt）的扰乱产生准密钥；
 * 将准密钥经过散列函数，多次迭代后，生成最终的加密密钥；
 * 密钥生成后，PBE 算法再使用对称加密算法对数据进行加密，可以选择 DES、3DES、RC5 等对称加密算法。
 */
public class Pbe {
    /**
     * 定义加密方式
     * 支持以下任意一种算法
     * PBEWithMD5AndDES
     * PBEWithMD5AndTripleDES
     * PBEWithSHA1AndDESede
     * PBEWithSHA1AndRC2_40
     */
    private final static String KEY_PBE = "PBEWITHMD5andDES";

    private final static int SALT_COUNT = 100;
    /**
     * 初始化盐（salt）
     *
     * @return
     */
    public static byte[] init() {
        byte[] salt = new byte[8];
        Random random = new Random();
        random.nextBytes(salt);
        return salt;
    }

    /**
     * 转换密钥
     *
     * @param key 字符串
     * @return
     */
    public static Key stringToKey(String key) {
        SecretKey secretKey = null;
        try {
            PBEKeySpec keySpec = new PBEKeySpec(key.toCharArray());
            SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_PBE);
            secretKey = factory.generateSecret(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return secretKey;
    }

    /**
     * PBE 加密
     *
     * @param data 需要加密的字节数组
     * @param key  密钥
     * @param salt 盐
     * @return
     */
    public static byte[] encryptPBE(byte[] data, String key, byte[] salt) {
        byte[] bytes = null;
        try {
            // 获取密钥
            Key k = stringToKey(key);
            PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, SALT_COUNT);
            Cipher cipher = Cipher.getInstance(KEY_PBE);
            cipher.init(Cipher.ENCRYPT_MODE, k, parameterSpec);
            bytes = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * PBE 解密
     *
     * @param data 需要解密的字节数组
     * @param key  密钥
     * @param salt 盐
     * @return
     */
    public static byte[] decryptPBE(byte[] data, String key, byte[] salt) {
        byte[] bytes = null;
        try {
            // 获取密钥
            Key k = stringToKey(key);
            PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, SALT_COUNT);
            Cipher cipher = Cipher.getInstance(KEY_PBE);
            cipher.init(Cipher.DECRYPT_MODE, k, parameterSpec);
            bytes = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return bytes;
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

    public static void main(String[] args) {
        //获取开始时间的时间戳
        long startTime = System.currentTimeMillis();
        // 加密前的原文
        String msg = "In doing we learn." ;
        // 口令
        String key = "qwert";
        // 初始化盐
        byte[] salt = init();
        // 采用PBE算法加密
        byte[] encData = encryptPBE(msg.getBytes(), key, salt);
        // 采用PBE算法解密
        byte[] decData = decryptPBE(encData, key, salt);
        String encontent = null;
        String decontent = null;
        try {
            encontent = encryptBase64(encData);
            decontent = new String(decData, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("----PBE 加密结果----");
        System.out.println();
        System.out.println("待加密的明文: " + msg);
        System.out.printf("加密的结果为: " + encontent);
        System.out.println("解密的结果为: " + decontent);
        //获取结束时间的时间戳
        long endTime = System.currentTimeMillis();
        //输出程序运行时间
        System.out.println("PBE 加密的时间消耗为：" + (endTime - startTime) + " ms");
    }
}
