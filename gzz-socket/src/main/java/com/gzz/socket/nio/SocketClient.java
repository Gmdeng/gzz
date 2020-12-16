package com.gzz.socket.nio;

import java.io.*;
import java.net.Socket;
import java.util.stream.Stream;

/**
 * java 实现SocketClient非阻塞模式，自动重连、接收、发送、心跳包
 *
 */
public class SocketClient {
    private String serverIp;
    private int serverPort;
    private boolean keepAlive;
    private  SocketMessageTest socketMessageTest;
    private Socket sk;
    private byte[] message;
    /**
     * 构造
     */
    public SocketClient(){

    }

    /**
     * 打开连接
     */
    public void open() throws IOException {
        sk = new Socket(serverIp, serverPort);
        sk.setKeepAlive(true);

    }
    /**
     * 发送
     */
    public void send() throws IOException {
//      OutputStream os = sk.getOutputStream();
//      OutputStreamWriter writer = new OutputStreamWriter(os);
        //传输字节流
        DataOutputStream output=new DataOutputStream(sk.getOutputStream());
        output.write(this.message);
        output.flush();//将缓存中的所有数据推送到输出流
        output.close();//关闭流
        // Socket关闭输出流的方式
        sk.shutdownOutput();

    }

    public void receive(){
//        abstract int read();//从流中读取一个字节的数据
//        int read(byte[] data);//从输入流中获取长度为data.length的字节序列，返回值是实际的字节数
//        int read(byte[] data,int offset,int length)//从一个数组的偏移量offset开始，输出长度为length的字节,返回成功读取的字节数
//        int available();//返回当前可读字节的总数
//        void close();//关闭流

        
    }
    public void close(){

    }
    public void setSocketMessage(Class obj){

    }

    /**
     * 发送内空
     * @param msg
     * @return
     */
    public SocketClient setMessage(int[] msg){
        this.message = new byte[msg.length];
//        Stream.of(msg).forEach((it)->{
//            this.message[]
//        });
        // TODO
        return null;
    }





    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }


}
