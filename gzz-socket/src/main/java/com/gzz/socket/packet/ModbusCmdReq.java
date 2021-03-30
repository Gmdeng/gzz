package com.gzz.socket.packet;

/**
 * 指令请求
 */
public class ModbusCmdReq {
    private int mi;     // 设备码
    private int func;    // 命令号
    private int pos;    // 寄存器地址
    private int len;    // 长度
    private int crc;    // 校检码


    public static void main(String[] args) {
        byte h = (byte) 3;
        byte[] header = {(byte) 0xE,(byte) 9, (byte) 6};

        System.out.println(h);
    }
}
