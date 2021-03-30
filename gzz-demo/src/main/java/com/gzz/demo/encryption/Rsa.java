package com.gzz.demo.encryption;
import java.io.UnsupportedEncodingException;
import  java.security.InvalidKeyException;
import  java.security.KeyPair;
import  java.security.KeyPairGenerator;
import  java.security.NoSuchAlgorithmException;
import  java.security.interfaces.RSAPrivateKey;
import  java.security.interfaces.RSAPublicKey;

import  javax.crypto.BadPaddingException;
import  javax.crypto.Cipher;
import  javax.crypto.IllegalBlockSizeException;
import  javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Encoder;

/**
 *
 * 算法介绍
 *      非对称加密；
 *      密钥长度决定了其复杂度；
 *      简单原理：公钥加密、私钥解密；
 *               私钥签名、公钥解密验证。
 * 算法流程
 *      1, 随意选择两个大的质数 p 和 q ，p  不等于 q ，计算 N = p × q ；
 *      2, 根据欧拉函数，求得 r = ( p − 1 ) ( q − 1 ) ；
 *      3, 选择一个小于 r 的整数 e ，求得 e 关于模 r  的模反元素，命名为 d （模反元素存在，当且仅当 e  与 r  互质）；
 *      4, 将 p 和 q  的记录销毁；
 *      5, ( N , e )是公钥，( N , d ) 是私钥。
 *
 * 优点
 *   原理简单。
 *
 * 缺点
 *      密钥生成较为麻烦，受到素数产生技术的限制，因此难以做到一次一密；
 *      分组长度太大，不利于数据格式标准化；
 *      加密难度大。
 * 应用场景
 *      数字签名；
 *      公钥加密；
 *      防止数据篡改；
 *      用于通讯领域较多。
 * 安全性
 *   高。
 *
 */
public class Rsa {
    /**
     * 加密
     * @param publicKey
     * @param srcBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    protected byte [] encrypt(RSAPublicKey publicKey, byte [] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        if (publicKey != null ){
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte [] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null ;
    }

    /**
     * 解密
     * @param privateKey
     * @param srcBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    protected byte [] decrypt(RSAPrivateKey privateKey, byte [] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        if (privateKey != null ){
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte [] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null ;
    }
    public static String encryptBase64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //获取开始时间的时间戳
        long startTime = System.currentTimeMillis();
        Rsa rsa = new Rsa();
        String msg = "In doing we learn.";
        //KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        //初始化密钥对生成器，密钥大小为1024位
        keyPairGen.initialize(1024);
        //生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        //得到公钥
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        //用公钥加密
        byte [] srcBytes = msg.getBytes();
        byte [] resultBytes = rsa.encrypt(publicKey, srcBytes);
        String encontent = null;
        encontent = encryptBase64(resultBytes);
        //用私钥解密
        byte [] decBytes = rsa.decrypt(privateKey, resultBytes);
        System.out.println("----RSA 加密结果----");
        System.out.println();
        System.out.println("待加密的明文: " + msg);
        System.out.printf("加密的结果为: " + encontent);
        System.out.println("解密的结果为: " + new String(decBytes, "utf-8"));
        //获取结束时间的时间戳
        long endTime = System.currentTimeMillis();
        //输出程序运行时间
        System.out.println("RSA 加密的时间消耗为：" + (endTime - startTime) + " ms");
    }
}
