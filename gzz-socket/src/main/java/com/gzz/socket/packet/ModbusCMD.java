package com.gzz.socket.packet;

import java.nio.ByteBuffer;

/**
 * Modbus指令
 */
public class ModbusCMD {
    private int mi;     // 设备码
    private int len;    // 长度
    private int val;    // 数值
    private int crc;    // 校检码

    public ModbusCMD(byte[] data){
        this.mi = data[0] & 0xff;
        this.len = data[1] & 0xff;
        ByteBuffer buf = ByteBuffer.wrap(data, 2, this.len);
        System.out.println(new String(buf.array()));
    }

}
