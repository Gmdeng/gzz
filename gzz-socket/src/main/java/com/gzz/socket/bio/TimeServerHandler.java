package com.gzz.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

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
            String body= null;
            while (true){
                body = br.readLine();
                if(body == null){
                    break;
                }
                System.out.println("The time server receive order : "+ body);
                String currentTime ="Query Time order".equalsIgnoreCase(body)? new Date(System.currentTimeMillis()).toString(): "BAD ORDER";
                System.out.println(currentTime);
            }
        }catch (Exception ex){
            if(br !=null){
                try {
                    br.close();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
                if(pw !=null){
                    pw.close();
                    pw = null;
                }
                if(this.sk != null){
                    this.sk = null;
                }
            }
        }
    }
}
