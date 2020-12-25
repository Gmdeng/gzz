package com.gzz.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static void main(String[] args) {
        TimeServerHandlerExecutePool timeServerHandlerExecutePool = new TimeServerHandlerExecutePool(20, 9);
        try (ServerSocket ssk = new ServerSocket(9998)) {
            while (true){
                Socket sk = ssk.accept();
                //
                new Thread(new TimeServerHandler(sk)).start();
                
                //伪NIO写法
                //timeServerHandlerExecutePool.execute(new TimeServerHandler(sk));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
