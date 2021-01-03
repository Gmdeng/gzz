package com.gzz.socket.packet;


import com.gzz.socket.utils.ByteUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 请求数据处理
 *
 */
public class ReqData {
    private final static Logger log = LogManager.getLogger(ReqData.class);
    //private static final int HEART_BEAT = 0xff;
    private static final String HEART_BEAT = "pong";
    private static final String REG_HEAD = "im";
    //是否为心跳标记
    private boolean isHeartBeat = false;
    //是否为注册包
    private boolean isRegister = false;
    public ReqData(byte[] data){
        log.info("数据包：{}", ByteUtil.dumpHex(data));
        String dest = new String(data);
        // 是否心跳包
        this.isHeartBeat = HEART_BEAT.equalsIgnoreCase(dest);
        // 是否注册包
        if(StringUtils.startsWith(dest, REG_HEAD)){
            this.isRegister = true;
        }


//        if((data[0]&0xff) == 0xff){
//            //System.out.println("data = " + "相等的");
//            this.isHeartBeat = true;
//        }else if(data[0] ==0x00){
//            this.isRegister = true;
//        }
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
