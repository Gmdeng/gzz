package com.gzz.socket.nio;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient3 {
    public static void main(String[] args) {
        InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("192.168.2.200", 8811);
        String sendSome="7777777777777";
        int count=-1;
        try{
            // 打开socket通道
            SocketChannel socketChannel = SocketChannel.open();
            // 设置为非阻塞方式
            socketChannel.configureBlocking(false);
            // 打开选择器
            Selector selector = Selector.open();
            // 注册连接服务端socket动作
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            // 连接
            socketChannel.connect(SERVER_ADDRESS);
            // 分配缓冲区大小内存
            Set<SelectionKey> selectionKeys;
            Iterator iterator;
            SelectionKey selectionKey;
            SocketChannel clientChannel;

            ByteBuffer input = ByteBuffer.allocate(1024*100);
            ByteBuffer header=ByteBuffer.allocate(4);
            ByteBuffer sendbuffer = ByteBuffer.allocate(sendSome.getBytes().length);
            while (true) {
                //选择一组键，其相应的通道已为 I/O 操作准备就绪。
                //此方法执行处于阻塞模式的选择操作。
                selector.select();
                //返回此选择器的已选择键集。
                selectionKeys = selector.selectedKeys();
                //System.out.println(selectionKeys.size());
                iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    selectionKey = (SelectionKey) iterator.next();
                    clientChannel = (SocketChannel) selectionKey.channel();
                    if (selectionKey.isConnectable()) {
                        // 判断此通道上是否正在进行连接操作。
                        // 完成套接字通道的连接过程。
                        if (clientChannel.isConnectionPending()) {
                            clientChannel.finishConnect();
                            System.out.println("connect finished");
                            sendbuffer.clear();
                            sendbuffer.put((sendSome).getBytes());
                            //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
                            sendbuffer.flip();
                            clientChannel.write(sendbuffer);
                        }
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    }
                    else if (selectionKey.isReadable()) {
                        if(count==-1){
                            clientChannel.read(header);
                            //并且缓存区的长度大于4(包头部分已经接受完毕)
                            if(!header.hasRemaining()){
                                count= byteArrayToInt(header.array());
                                System.out.println("dataSize ......"+count);
                                header.clear();
                                input = ByteBuffer.allocate(count);
                                clientChannel.register(selector, SelectionKey.OP_READ);
                            }
                        }
                        else{
                            System.out.println("wait......"+input.remaining());
                            // 尝试读取数据区域
                            clientChannel.read(input);
                            //input.mark();
                            if(!input.hasRemaining()){
                                // 这个时候可以解析数据
                                System.out.println("data full");
                                input.clear();
                                clientChannel.register(selector, SelectionKey.OP_WRITE);
                            }
                            else{
                                // 数据还没有填充满，继续接受数据
                                clientChannel.register(selector, SelectionKey.OP_READ);
                            }
                        }
                    }
                    else if(selectionKey.isWritable()){
                        clientChannel = (SocketChannel) selectionKey.channel();
                        count=-1;
                        sendbuffer.clear();
                        sendbuffer.put(sendSome.getBytes());
                        sendbuffer.flip();
                        clientChannel.write(sendbuffer);
                        System.out.println("send Message "+sendSome);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    }
                }
                selectionKeys.clear();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16
                | (b[0] & 0xFF) << 24;
    }
}
