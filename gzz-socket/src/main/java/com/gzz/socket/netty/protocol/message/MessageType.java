package com.gzz.socket.netty.protocol.message;

/**
 * 消息类型
 */
public enum MessageType {
    HEARTBEAT_RESP(1), HEARTBEAT_REQ(2),
    LOGIN_REQ(4), LOGIN_RESP(8);
    private int val;
    MessageType(int val){
        this.val =val;
    }
    public byte value(){
        return (byte)this.val;
    }
}
