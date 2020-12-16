package com.gzz.socket.nio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class NIOClientTest {

    private final static Logger log = LogManager.getLogger(NIOClientTest.class);
    public static void main(String[] args) throws IOException, InterruptedException {
        // 初始化客户端Socket
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        // 注册连接成功事件监听
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        // 发起连接
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9988));
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            if (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();;
                if (!key.isValid()) {
                    continue;
                }
                // 当连接到服务端成功时，变更监听为 可写
                if (key.isConnectable()) {
                    log.info("连接成功。。");
                    SocketChannel channel = (SocketChannel) key.channel();
                    channel.finishConnect();
                    key.interestOps(SelectionKey.OP_WRITE);
                }

                // 写入心跳信息，然后变更监听为 可读，即等待读取服务端 回执
                if (key.isWritable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    //byte[] heatBeat = {(byte) 0xff,(byte) 0xFF, (byte) 0xbb};
                    String heatBeat = "im100000001";
                    channel.write(ByteBuffer.wrap(heatBeat.getBytes()));
                    // channel.write(ByteBuffer.wrap("heartBeatClient_1__END".getBytes()));
                    key.interestOps(SelectionKey.OP_READ);
                }
                // 读取服务端回执后，然后变更监听为 可写，准备下一次写入心跳信息
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = channel.read(buffer);
                    buffer.flip();
                    if(len < 0){
                        System.out.println("服务端{"+ channel.getRemoteAddress()+"} 关闭通道了。。。 ");
                        channel.close();
                        break;
                    }else {
                        System.out.println("接收到{" + len + "}：" + new String(buffer.array(), 0, buffer.limit()));
                        key.interestOps(SelectionKey.OP_WRITE);
                        TimeUnit.MILLISECONDS.sleep(5000);
                    }
                    //Thread.sleep(2000);
                }
            }
        }
    }
}
