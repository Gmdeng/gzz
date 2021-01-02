package com.gzz.socket.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Formatter;

/**
 * 字节操作工具类
 */
public class ByteUtil {
    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
            "E", "F" };

    /**
     * char 字符转byte[]字节数组
     * @param c
     * @return
     */
    public static byte[] char2Bytes(char c){
        byte[] b= new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    /**
     * byte[]字节数组转 char 字符
     * @param b
     * @return
     */
    public static char byte2Chars(byte[] b){
        char c= (char) (((b[0] & 0xFF) << 8)|(b[1] & 0xFF));
        return c;
    }

    /**
     * char[] 转byte[]
     * @param chars
     * @return
     */
    public static byte[] toBytes(char[] chars){
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb =cs.encode(cb);
        return bb.array();
    }

    /**
     * byte[] 转 char[]
     * @param bytes
     * @return
     */
    public static char[] toChars(byte[] bytes){
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb =cs.decode(bb);
        return cb.array();
    }
    /**
     * byte数组转16进制
     *
     * @param hash
     * @return
     */
    public static String toHex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
            formatter.format("%02x", b);
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * byte数组转16进制字符串（带空格显示）
     * @param hash
     * @return
     */
    public static String dumpHex(byte[] hash){
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                //算法一
//			int n = hash[i];
//			if (n < 0)
//				n += 256;
//			int d1 = n / 16; // 等于 (n >>> 4) & 0x0F
//			int d2 = n % 16; // 等于 n & 0x0F
//			buf.append(hexDigits[d1] + hexDigits[d2]);
                //算法二
                if ((hash[i] & 0xff) < 0x10)
                    buf.append("0");
                buf.append(Long.toString(hash[i] & 0xff, 16));
                buf.append(" ");
            }
            return buf.toString().toUpperCase();

    }
    /**
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     * @param bytes
     * @return
     */
    public static byte[] HexToByte(String bytes) {
        String hexString = StringUtils.join(hexDigits);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        return baos.toByteArray();
    }

    /**
     * 字节转整型
     * @param b
     * @return
     */
    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16
                | (b[0] & 0xFF) << 24;
    }

}
