package com.gzz.demo.encryption;
/**
 * 一般化的凯撒加密算法为： C = E(k, p) = (p + k) mod 26
 * 一般化的凯撒解密算法为： p = D(k, C) = (C - k) mod 26
 *
 *  凯撒密码：
 * 算法介绍
 *   作为一种最为古老的对称加密体制，凯撒密码在古罗马的时候就已经很流行了。它的基本思想是：通过把字母移动一定的位数来实现加密和解密。
 *      例如，如果字母的位数是 3，明文字母 B 就变成了密文的 E，依次类推，X 将变成 A，Y 变成 B，Z 变成 C……由此可见，位数就是凯撒密码加密和解密的密钥。
 *
 * 算法流程
 *   一般化的凯撒加密算法为： C = E ( k , P ) = ( P + k ) mod 26 ；
 *
 *   一般化的凯撒解密算法为： P = D ( k , C ) = ( C − k ) mod 26。
 *
 * 由于字母表中共有 26 个字符，因此移位前先将移动的位数 (k e y keykey) 和 26 取模。将字符加上一个正整数即代表在字母表中右移多少位；
 * 如果移动的位数是负值，则代表在字母表中左移多少位。尽管在移动之前已经将移动的位数和 26 取模，但通过这种方式实现右移或左移仍可能发生超界；
 * 移位后进行判断，如果向左超界（c <‘a’）则增加 26；向右超界（c >‘z’）则减去 26。
 *
 */

public class Caesar {
    /**
     * 对单个字母进行加密
     * @param ch 字母
     * @param n 密钥
     * @return 加密后的字母
     */
    public static char encrypt(char ch,int n){
        int unicode;
        int c=ch-'a';
        if(c+n>'z') unicode=c+n-26;
        else unicode=c+n;
        return (char)(unicode%26+'a');
    }
    /**
     * 对明文进行加密
     * @param str 明文字符串
     * @param n 密钥
     * @return  对明文加密后的密文
     */
    public static String encrypt(String str,int n){
        char[] ch=str.toCharArray();
        StringBuilder sb=new StringBuilder();
        for(char c: ch){
            sb.append(encrypt(c,n));
        }
        return sb.toString();
    }
    /**
     * 将加密后的字母解密
     * @param ch 加密后的字母
     * @param n 密钥
     * @return 解密后的字母
     */
    public static char decrypt(char ch,int n){
        int unicode;
        int c=ch-'a';
        if(c-n<'a') unicode=c-n+26;
        else unicode=c-n;
        return (char)(unicode%26+'a');
    }
    /**
     * 将密文解密
     * @param str 密文
     * @param n 密钥
     * @return 解密后的明文
     */
    public static String decrypt(String str,int n){
        char[] ch=str.toCharArray();
        StringBuilder sb=new StringBuilder();
        for(char c: ch){
            sb.append(decrypt(c,n));
        }
        return sb.toString();
    }

    public static void main(String args[]){
        //获取开始时间的时间戳
        long startTime = System.currentTimeMillis();
        String str="in doing we learn";
        String[] words=str.toLowerCase().split(" ");
        String result = "";
        for(String word: words){
            result = result + encrypt(word, 21) + " ";
        }
        System.out.println("----Caesar 加密结果----");
        System.out.println();
        System.out.println("待加密的明文: " + str);
        System.out.print("加密的结果为: ");
        System.out.print(result);
        System.out.println();
        String[] ws=result.split(" ");
        System.out.print("解密的结果为: ");
        for(String w: ws){
            System.out.print(decrypt(w, 21)+" ");
        }
        //获取结束时间的时间戳
        long endTime = System.currentTimeMillis();
        //输出程序运行时间
        System.out.println();
        System.out.println("Caesar 加密的时间消耗为：" + (endTime - startTime) + " ms");
    }
}