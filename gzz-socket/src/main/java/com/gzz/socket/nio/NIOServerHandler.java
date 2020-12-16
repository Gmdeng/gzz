package com.gzz.socket.nio;

import com.gzz.socket.packet.CommandSet;
import com.gzz.socket.packet.ReqData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务端事件处理
 *
 */
public class NIOServerHandler {
    private final static Logger log = LogManager.getLogger(NIOServerHandler.class);
    /**
     * 接收数据处理
     * @param selector
     * @param key
     * @throws IOException
     */
    public static void receiveHandle(Selector selector,SelectionKey key) throws IOException {
        log.info("receive接收数据处理。。。。");
        //获得与SelectionKey关联的附件
        CommandSet commandSet = (CommandSet)key.attachment();
        //获得与SelectionKey关联的SocketChannel
        SocketChannel socketChannel = (SocketChannel)key.channel();
        //创建一个ByteBuffer，用于存放读到的数据
        int readLen = 0;
        ByteBuffer readBuff = ByteBuffer.allocate(512);
        readLen = socketChannel.read(readBuff);
        readBuff.flip();
        //byte[]  data= readBuff.array();
        byte[] data = new byte[readLen];
        readBuff.get(data, 0, readLen);
        readBuff.clear();//每次清空 对应上面flip()
       log.info("receive收到数据包{}：{}",readLen, new String(data));
        ReqData reqData = new ReqData(data);
        if(reqData.isHeartBeat()){
            log.info("receive收到是心跳包：");
            socketChannel.register(selector, SelectionKey.OP_READ, commandSet);
        }else{
            // TODO 获取数据后业务处理
            if(commandSet.isNew()){
                log.info("receive 设置请求指令");
                List<String> commandList = new ArrayList<>();
                commandList.add("Hello Word..");
                commandList.add("I am Come on..");
                commandList.add("Welcome to me..");
                commandSet.setCommands(commandList);
            }
            try {
                if (commandSet.hasNext()) {
                    log.info("receive转向一下条指令");
                    //继续发送下一条指令
                    socketChannel.register(selector, SelectionKey.OP_WRITE, commandSet);
                } else {
                    log.info("receive最后一条指令关闭通道");
                    socketChannel.shutdownInput();
                    socketChannel.shutdownOutput();
                    socketChannel.close();
                    key.cancel();
                }
            }catch (Exception ex){
                log.error(ex.getMessage());
            }
        }



//        //把buffer的极限设为容量
//        buffer.limit(buffer.capacity());
//
//        //把readBuff中的内容拷贝到buffer中，假定buffer的容量足够大，不会出现缓冲区溢出异常
//        buffer.put(readBuff);
    }

    /**
     * 发送数据处理
     * @param selector
     * @param key
     * @throws IOException
     */
    public static void sendHandle(Selector selector,SelectionKey key) throws IOException {
        log.info("send发送数据处理。。。。");
        //获得与SelectionKey关联的附件
        CommandSet commandSet = (CommandSet)key.attachment();
        //获得与SelectionKey关联的SocketChannel
        SocketChannel socketChannel = (SocketChannel)key.channel();
        log.info("是否最后一个指令： {}",  commandSet.isLast());
        if(commandSet.hasNext()) {
            String sendStr = commandSet.getCommad() + Math.random();
            //ByteBuffer send = ByteBuffer.wrap(sendStr.getBytes());
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf.clear();
            buf.put(sendStr.getBytes());
            buf.flip();
            while (buf.hasRemaining()) {
                socketChannel.write(buf);
            }
            log.info("响应数据["+ sendStr +"]到【"+socketChannel.getRemoteAddress()+"】");
            socketChannel.register(selector, SelectionKey.OP_READ, commandSet);
        }else{
            socketChannel.shutdownInput();
            socketChannel.shutdownOutput();
            socketChannel.close();
            key.cancel();
        }

    }

    /**
     * 注册处理
     * @param selector
     * @param key
     * @throws IOException
     */
    public static void acceptHandle(Selector selector, SelectionKey key) throws IOException {
        log.info("Accept 注册");
        //获得与SelectionKey关联的SocketChannel
        ServerSocketChannel sersverSocketChannel = (ServerSocketChannel)key.channel();
        //就绪后的操作，刚到达的socket句柄
        SocketChannel socketChannel = sersverSocketChannel.accept();
        //需要和ServerSocketChannel一样。。
        socketChannel.configureBlocking(false);
        log.info("Accept  收到了来自于【{}】的连接请求", socketChannel.getRemoteAddress());
        // 注意！不要注册SelectionKey.OP_WRITE.否则每次都能select到它，性能降低。
        // socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        socketChannel.register(selector, SelectionKey.OP_READ,  new CommandSet());
    }
}
