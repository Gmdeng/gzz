package com.gzz.socket.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class NIOHandler {
    public void receive(SelectionKey key) throws IOException {
        //获得与SelectionKey关联的附件
        ByteBuffer buffer = (ByteBuffer)key.attachment();

        //获得与SelectionKey关联的SocketChannel
        SocketChannel socketChannel = (SocketChannel)key.channel();

        //创建一个ByteBuffer，用于存放读到的数据
        ByteBuffer readBuff = ByteBuffer.allocate(32);
        socketChannel.read(readBuff);
        readBuff.flip();

        //把buffer的极限设为容量
        buffer.limit(buffer.capacity());

        //把readBuff中的内容拷贝到buffer中，假定buffer的容量足够大，不会出现缓冲区溢出异常
        buffer.put(readBuff);
    }
}
