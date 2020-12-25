package com.gzz.socket.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeServerHandler implements Runnable {
    private Socket sk;
    public TimeServerHandler(Socket sk){
        this.sk =sk;
    }
    @Override
    public void run() {
        // socket 读写
        BufferedReader br= null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new InputStreamReader(this.sk.getInputStream()));
            pw = new PrintWriter(this.sk.getOutputStream(), true);
        }catch (Exception ex){

        }
    }
}
