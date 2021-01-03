package com.gzz.socket.packet;

import java.nio.ByteBuffer;

public class ModbusCMD {
    private int mi;
    private int len;
    private int val;
    private int crc;

    public ModbusCMD(byte[] data){
        this.mi = data[0] & 0xff;
        this.len = data[1] & 0xff;
        ByteBuffer buf = ByteBuffer.wrap(data, 2, this.len);
        System.out.println(new String(buf.array()));
    }
}
