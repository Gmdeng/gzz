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
 * AES 高级加密标准(Advanced Encryption Standard，AES)：
 *
 * 算法介绍
 *      1,属于对称加密算法；
 *      2,基于排列置换算法；
 *      3,易于软硬件实现；
 *      4,属于分组密码体制；
 *      5,用于取代原来的 DES。
 *
 *
 * 算法流程
 *      1.对数据进行 128 位（16 字节）的分组加密，每次对一组数据加密需要多轮；
 *      2.输入密钥长度为：128、192 或 256，如果不够则补齐；
 *      3.加密基本流程：
 *          1）生成各轮的扩展密钥，存于 key 数组中，包含用户的输入密钥和扩展密钥；
 *          2）将待加密数组与第一组密钥异或；
 *          3）最后一轮前的变换操作：
 *            SubBytes（state）——对数据进行 S 字节变换；
 *            ShiftRows（state）——进行行变换；
 *            MixColumns( state )——进行列混合变换；
 *            AddRoundKey( state，Keys [当前轮密钥组] )——与当前轮密钥异或。
 *          4）最后一轮变换操作
 *            invShiftRows（state）——进行反行变换；
 *            invSubBytes（state）——对数据进行反 S 字节变换；
 *            AddRoundKey( state， Keys [第一组] )——与第一组密钥进行异或。
 *      4.解密流程：与加密相反。
 *      5.分组模式：
 *          1）ECB（电码本模式）：
 *              优点：简单、并行计算、误差不会传递；
 *              缺点：不能隐藏明文的模式、可能造成对明文的主动攻击。
 *          2）CBC（密码分组链接）：
 *              优点：能抵抗主动攻击、安全性好于 ECB、适合传输较长报文、是 SSL，IPSec 的标准；
 *              缺点：不利于并行计算、有误差传递、需要初始化向量；
 *          3）CFB（密码反馈模式）：
 *              优点：隐藏明文的模式、将分组密码转化为流模式、可以及时加密传送分组的数据；
 *              缺点：不利于并行计算、有误差传递、I V IVIV值唯一。
 *          4）OFB（输出反馈模式）：
 *              优点：隐藏明文、将分组密码转化流模式、可以及时加密传送分组的数据；
 *              缺点：不利于并行计算、可能对明文产生主动攻击、误差传递。
 *          5）CTR（计数器模式）：
 *              优点：并行计算、仅要求实现加密算法而无需解密算法、无需填充、可以作为流进行高效加密。
 *      6.常用填充方式：
 *          NoPadding——不填充；
 *          ZerosPadding——0 填充；
 *          PKCS5Padding——每个填充都记录了填充的总数。
 *
 * 优点
 *   分组模式选择多，加密安全。
 *
 * 缺点
 *      同 DES 类似，存在密钥管理问题；
 *      曾遭受线性密码攻击、差分密码攻击。
 * 安全性
 *   较高。

 */
public class Aes {
    //KeyGenerator 提供对称密钥生成器的功能，支持各种算法
    private KeyGenerator keygen;
    //SecretKey 负责保存对称密钥
    private SecretKey deskey;
    //Cipher负责完成加密或解密工作
    private Cipher c;
    //该字节数组负责保存加密的结果
    private byte [] cipherByte;

    public Aes() throws NoSuchAlgorithmException, NoSuchPaddingException{
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        //实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
        keygen = KeyGenerator.getInstance("AES");
        //生成密钥
        deskey = keygen.generateKey();
        //生成Cipher对象,指定其支持的DES算法
        c = Cipher.getInstance("AES");
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
        Aes de1 = new Aes();
        //源码文件是 GBK 格式，或者这个字符串是从 GBK 文件中读取出来的, 转换为 string 变成 unicode 格式
        String msg1 = "In doing we learn.";
        //利用 getBytes 将 unicode 字符串转成 UTF-8 格式的字节数组
        byte[] utf8Bytes = msg1.getBytes("UTF-8");
        //用 utf-8 对这个字节数组解码成新的字符串
        String msg = new String(utf8Bytes, "UTF-8");
        byte [] encData = de1.Encrytor(msg);
        byte [] decontent = de1.Decryptor(encData);
        String encontent = null;
        encontent = encryptBase64(encData);
        System.out.println("----AES 加密结果----");
        System.out.println();
        System.out.println("待加密的明文: " + msg);
        //System.out.println("加密后:" + new String(encontent, "utf-8"));
        System.out.printf("加密的结果为: " + encontent);
        System.out.println("解密的结果为: " + new String(decontent));
        //获取结束时间的时间戳
        long endTime = System.currentTimeMillis();
        //输出程序运行时间
        System.out.println("AES 加密的时间消耗为：" + (endTime - startTime) + " ms");
    }
}