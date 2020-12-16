package com.gzz.socket.packet;


import com.gzz.socket.utils.ByteUtil;

import java.util.Arrays;

/**
 * 请求数据处理
 *
 */
public class ReqData {
    private static final int HEART_BEAT = 0xff;
    //是否为心跳标记
    private boolean isHeartBeat = false;
    //是否为注册包
    private boolean isRegister = false;
    public ReqData(byte[] data){
        System.out.println(ByteUtil.dumpHex(data));
        System.out.println(ByteUtil.toHex(data));
        System.out.println("data1 = " + (data[0]*0xff));
        System.out.println("data2 = " + (data[0]&0xff));
        System.out.println("data3 = " + (data[0]|0xff));

        System.out.println("1data1 = " + (data[1]*0xff));
        System.out.println("1data2 = " + (data[1]&0xff));
        System.out.println("1data3 = " + (data[1]|0xff));

        System.out.println("2data1 = " + (data[2]*0xff));
        System.out.println("2data2 = " + (data[2]&0xff));
        System.out.println("2data3 = " + (data[2]|0xff));

        if((data[0]&0xff) == 0xff){
            //System.out.println("data = " + "相等的");
            this.isHeartBeat = true;
        }else if(data[0] ==0x00){
            this.isRegister = true;
        }
        //
    }
    //输出ＨＥＸ格式
    public String toHex(){
        return null;
    }

    //是否为心跳包
    public boolean isHeartBeat() {
        return this.isHeartBeat;
    }
    // 是否为注册包
    public boolean isRegister(){
        return this.isRegister;
    }
}
