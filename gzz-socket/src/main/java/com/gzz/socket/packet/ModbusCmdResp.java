package com.gzz.socket.packet;

/**
 * 指令响应
 */
public class ModbusCmdResp {
    private int mi;     // 设备码
    private int len;    // 长度
    private int val;    // 数值
    private int crc;    // 校检码
}
