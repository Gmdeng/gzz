package com.gzz.demo.encryption;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

/**
 * IDEA（国际数据加密算法）：
 * 算法介绍
 *   国际数据加密算法（IDEA）是上海交通大学教授来学嘉与瑞士学者 James Massey 联合提出的，它在 1990 年正式公布并得到增强。这种算法是在 DES 算法的基础上发展出来的，类似于三重 DES。发展 IDEA 也是因为 DES 密钥太短等缺点，IDEA 的密钥为 128 位，在今后若干年内应该是安全的。
 *
 * 算法特点
 *   类似于 DES，IDEA 算法也是一种分组加密算法，它设计了一系列加密轮次，每轮加密都使用从完整的加密密钥中生成的一个子密钥。与 DES 的不同之处在于，它在软件和硬件实现上同样快速。
 *
 *   由于 IDEA 是在美国之外提出并发展起来的，避开了美国法律上对加密技术的诸多限制，因此，有关 IDEA 算法和实现技术的书籍都可以自由出版和交流，极大地促进了 IDEA 的发展和完善。
 *
 * 应用领域
 *   目前 IDEA 在工程中已有大量应用实例：
 *      1, PGP ( Pretty Good Privacy）使用 IDEA 作为其分组加密算法；
 *      2, 安全套接字层 SSL（Secure Socket Layer）将 IDEA 包含在其加密算法库 SSLRef 中；
 *      3, 基于 IDEA 的 Exchange 安全插件；
 *      4, IDEA 加密芯片；
 *      5, IDEA 加密软件包等。
 */
public class Idea {
    public static final String ALGORITHM = "IDEA";
    public static final String CIPHER_ALGORITHM = "IDEA/ECB/ISO10126Padding";

    // 获取 IEDA Key
    public static byte[] getDesKey() throws NoSuchAlgorithmException, NoSuchPaddingException{
        try {
            // 1、创建密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(128);
            // 2、产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 3、获取密钥
            byte[] key = secretKey.getEncoded();
            return key;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // IDEA 解密
    public static byte[] encryptIdea(byte[] data, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
            // 加工作模式和填充方式
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] rsData = cipher.doFinal(data);
            return rsData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decryptIdea(byte[] data, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
            // 加工作模式和填充方式
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] rsData = cipher.doFinal(data);
            return rsData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encryptBase64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static void main(String[] args) throws Exception {
        //获取开始时间的时间戳
        long startTime = System.currentTimeMillis();
        String msg = "In doing we learn.";
        byte[] data = msg.getBytes(Charset.forName("UTF-8"));
        byte[] key = getDesKey();
        String encontent = null;
        byte[] encData = encryptIdea(data, key);
        encontent = encryptBase64(encData);
        byte[] decData = decryptIdea(encData, key);
        System.out.println("----IDEA 加密结果----");
        System.out.println();
        System.out.println("待加密的明文: " + msg);
        System.out.printf("加密的结果为: " + encontent);
        System.out.println("解密的结果为: " + new String(decData));
        //获取结束时间的时间戳
        long endTime = System.currentTimeMillis();
        //输出程序运行时间
        System.out.println("IDEA 加密的时间消耗为：" + (endTime - startTime) + " ms");
    }
}