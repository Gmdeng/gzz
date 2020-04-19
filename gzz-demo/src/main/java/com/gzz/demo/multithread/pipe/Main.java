package com.gzz.demo.multithread.pipe;

import java.io.*;

/**
 * 通道DEMO
 */
public class Main {
    public static void main(String[] args) throws IOException {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();

        try {
            // 建立通道
            pipedOutputStream.connect(pipedInputStream);

            new Thread(new Reader(pipedInputStream)).start();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            pipedOutputStream.write(bufferedReader.readLine().getBytes());

            bufferedReader.close();
        } finally {

            pipedOutputStream.close();
        }

    }
}
