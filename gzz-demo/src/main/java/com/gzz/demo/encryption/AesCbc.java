package com.gzz.demo.encryption;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理
 * 对原始数据进行AES加密后，进行Base64编码转化；
 */
public class AesCbc {
    /*
     * 加密用的Key 用26个字母和数字组成
     * 使用AES-128-CBC加密模式，key需要为16位。
     */
    private static String sKey="1234567800123656";
    private static String ivParameter="1236567890123456";
    private static AesCbc instance=null;
    //private static
    private AesCbc(){

    }
    public static AesCbc getInstance(){
        if (instance==null)
            instance= new AesCbc();
        return instance;
    }
    // 加密
    public String encrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
        return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码。
    }

    // 解密
    public String decrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,encodingFormat);
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        //获取开始时间的时间戳
        long startTime = System.currentTimeMillis();
        // 加密前的原文
        String msg = "In doing we learn.";
        // 加密
        String enString = AesCbc.getInstance().encrypt(msg,"utf-8",sKey,ivParameter);
        // 解密
        String DeString = AesCbc.getInstance().decrypt(enString,"utf-8",sKey,ivParameter);
        System.out.println("----CBC 加密结果----");
        System.out.println();
        System.out.println("待加密的明文: " + msg);
        System.out.println("加密的结果为: " + enString);
        System.out.println("解密的结果为: " + DeString);
        //获取结束时间的时间戳
        long endTime = System.currentTimeMillis();
        //输出程序运行时间
        System.out.println("CBC 加密的时间消耗为：" + (endTime - startTime) + " ms");
    }
}