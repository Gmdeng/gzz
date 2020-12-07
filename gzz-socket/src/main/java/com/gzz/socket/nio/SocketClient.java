package com.gzz.socket.nio;

/**
 * java 实现SocketClient非阻塞模式，自动重连、接收、发送、心跳包
 *
 */
public class SocketClient {
    private String serverIp;
    private int serverPort;
    private boolean keepAlive;
    private  SocketMessageTest socketMessageTest;


    public SocketClient(){

    }
    public void setSocketMessage(Class obj){

    }
    public SocketClient setMessage(int[] msg){
        return null;
    }

    public void open(){

    }
    public void send(){

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
