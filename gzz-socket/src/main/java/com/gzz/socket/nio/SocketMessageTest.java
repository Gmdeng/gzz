package com.gzz.socket.nio;

import com.gzz.socket.utils.ByteUtil;

import java.net.Socket;
import java.nio.ByteBuffer;

public class SocketMessageTest {
    /**
     * 监听端口
     */
    public static int PORT = 2000;

    /**
     * 监听主机
     */
    public static String HOST = "127.0.0.1";

    /**
     * 消息正文
     * 2521234253 = [00 FC 00 01 00 02 00 03 00 FD]
     */
    public static int[] MESSAGES_INT = {252, 1, 2, 3, 253};


    public SocketMessageTest() {
    }

    // 心跳检测间隔, 默认0-关闭心跳检测
    public long heartRate() {
        // 0-关闭心跳检测
        return 0;
    }

    // 心跳检测数据, 默认0xff
    public int heartRateData() {
        return 0xff;
    }

    // 心跳检测错误, 默认false
    public boolean heartRateError(Exception e) {
        // true=启动自动重连
        return true;
    }

    // 完成连接时
    public void finishConnect(Socket socket) {
        // 可完成些其他方法在完成连接时调用
    }

    // 连接错误, 默认false, true=连接失败自动重连
    public boolean connectError(Exception e) {
        // true=启动自动重连
        return true;
    }

    // 消息发送时
    public void send(ByteBuffer buffer) throws Exception {
        System.err.print(String.format("Send message: [ length: %d, hex: %s]", buffer.limit(), ByteUtil.toHex(buffer.array())));
    }

    // 消息发送时错误打印, 默认false, true=发送失败自动重连
    public boolean sendError(Exception e) {
        System.err.println("Sending message error: " + e.getMessage());
        return true;
    }

    // 消息接收时
    public void receive(ByteBuffer buffer) {
        System.err.print(String.format("Received message: [ length: %d, hex: %s]", buffer.limit(), ByteUtil.toHex(buffer.array())));
    }

    // 消息接收时错误打印, 默认false, true=尝试重新接收
    public boolean receiveError(Exception e) {
        System.err.println("Receiving message error: " + e.getMessage());
        return false;
    }
}