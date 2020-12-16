package com.gzz.socket.nio;

import com.gzz.socket.packet.CommandSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NIO 服务端
 */
public class NIOServer {
    private final static Logger log = LogManager.getLogger(NIOServer.class);
    private static final AtomicInteger selectorNos=new AtomicInteger(0);
    private static final AtomicInteger handleDataNo=new AtomicInteger(0);
    private static final AtomicInteger resDataNo=new AtomicInteger(0);

    private final static String HOST=  "192.168.100.129";
    private final static int PORT=  9988;
    public static void main(String[] args) {
        //ServerSocketChannel serverSocketChannel = null;
        ServerSocket serverSocket = null;
        Selector selector = null;
        //工厂方法创建ServerSocketChannel
        try(ServerSocketChannel ssChannel = ServerSocketChannel.open()) {

            //获取channel对应的ServerSocket
            serverSocket = ssChannel.socket();
            //使得在同一个主机上关闭了服务器程序，紧接着再启动该服务器程序时，可以顺利绑定到相同的端口
            serverSocket.setReuseAddress(true);
            //绑定地址 把服务器进程与一个本地端口绑定
            serverSocket.bind(new InetSocketAddress(HOST, PORT));
            //设置ServerSocketChannel非阻塞模式
            ssChannel.configureBlocking(false);

            // selector = Selector.open();//工厂方法创建Selector
            // 创建一个Selector对象无阻塞网络套接
            selector = Selector.open();

            //通道注册选择器，接受连接就绪状态。
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 监听 循环检查
            while (true) {
                log.info("NIO服务等待接收数据... ");
                //阻塞检查，当有就绪状态发生，返回键集合.
                // select(timeout) 为非阻塞的。
                if(selector.select()==0){
                    continue;
                }
               log.info("第【{}】轮监听===============================================", selectorNos.incrementAndGet());
                //获得Selector的selected-keys集合
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator it = selectedKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey selectionKey = null;
                    try {
                        selectionKey = (SelectionKey) it.next();
                        //把SelectionKey从Selector的selected-key集合中删除
                        it.remove();
                        // 处理IO事件
                        //处理接收连接就绪事件;
                        if (selectionKey.isAcceptable()) {
                            NIOServerHandler.acceptHandle(selector, selectionKey);
                        }
                        //处理读就绪事件;
                        else if (selectionKey.isReadable()) {
                            NIOServerHandler.receiveHandle(selector, selectionKey);
                        }
                        //处理写就绪事件;
                        else if (selectionKey.isWritable()) {
                             NIOServerHandler.sendHandle(selector,selectionKey);
                        }

                    }catch(IOException iox){
                        try {
                            if( selectionKey != null ) {
                                //使这个SelectionKey失效, 使得Selector不再监控这个SelectionKey感兴趣的事件
                                selectionKey.cancel();
                                selectionKey.channel().close(); //关闭与这个SelectionKey关联的SocketChannel
                            }
                        }
                        catch( Exception ex )
                        {
                            ex.printStackTrace();
                        }
                    }

                }
            }

        }catch (Exception e){
            log.error("断哦嘎：{}" , e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleAdv(){
        ServerSocketChannel serverSocketChannel = null;
        ServerSocket serverSocket = null;
        Selector selector = null;
        try {
            //工厂方法创建ServerSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            //获取channel对应的ServerSocket
            serverSocket = serverSocketChannel.socket();
            //使得在同一个主机上关闭了服务器程序，紧接着再启动该服务器程序时，可以顺利绑定到相同的端口
            serverSocket.setReuseAddress(true);
            //绑定地址 把服务器进程与一个本地端口绑定
            serverSocket.bind(new InetSocketAddress(HOST, PORT));
            //设置ServerSocketChannel非阻塞模式
            serverSocketChannel.configureBlocking(false);

            // selector = Selector.open();//工厂方法创建Selector
            // 创建一个Selector对象无阻塞网络套接
            selector = Selector.open();

            //通道注册选择器，接受连接就绪状态。
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 监听 循环检查
            while (true) {
                log.info("NIO服务等待接收数据 = ");
                //阻塞检查，当有就绪状态发生，返回键集合
                if(selector.select() == 0){
                    continue;
                }
                log.info("第【{}】轮监听===============================================", selectorNos.incrementAndGet());
                //获得Selector的selected-keys集合
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator it = selectedKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey selectionKey = null;
                    try {
                        selectionKey = (SelectionKey) it.next();
                        //把SelectionKey从Selector的selected-key集合中删除
                        it.remove();
                        // 处理IO事件
                        //处理接收连接就绪事件;
                        if (selectionKey.isAcceptable()) {
                            // Accept the new connection
                            //只负责监听，阻塞，管理，不发送、接收数据
                            ServerSocketChannel schannel = (ServerSocketChannel) selectionKey.channel();
                            //就绪后的操作，刚到达的socket句柄
                            SocketChannel socketChannel = schannel.accept();
                            if (null == socketChannel) {
                                continue;
                            }
                            socketChannel.configureBlocking(false);
                            log.info("Accept  收到了来自于【{}】的连接请求", socketChannel.getRemoteAddress());

                            // 注册读操作,以进行下一步的读操作
                            // 告知选择器关心的通道，准备好读数据
                            // 将客户端的可读事件注册到selector中监听
                            SelectionKey key =socketChannel.register(selector, SelectionKey.OP_READ);
                            // 附加一个参数变量到下个操作
                            key.attach(new Object());
                        }
                        //处理读就绪事件;
                        else if (selectionKey.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                            if (socketChannel.isOpen()) {
                                ByteBuffer byteBuffer = ByteBuffer.allocate(4 * 1024);

                                StringBuilder result = new StringBuilder();
                                while (socketChannel.read(byteBuffer) > 0) {//确保读完
                                    byteBuffer.flip();
                                    result.append(new String(byteBuffer.array()));
                                    byteBuffer.clear();//每次清空 对应上面flip()
                                }

                                // System.out.println("server receive: " + result.toString());
                                socketChannel.register(selector, SelectionKey.OP_WRITE);
                            }else{
                                System.out.println("channel 已经关闭！");
                                socketChannel.shutdownInput();
                                socketChannel.shutdownOutput();
                                socketChannel.close();
                                selectionKey.cancel();
                            }

                        }
                        //处理写就绪事件;
                        else if (selectionKey.isWritable()) {
                            //获得与SelectionKey关联的附件
                            //ByteBuffer buffer = (ByteBuffer)selectionKey.attachment();
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            String sendStr = "server send data: " + Math.random();
                            ByteBuffer send = ByteBuffer.wrap(sendStr.getBytes());
                            while (send.hasRemaining()) {
                                socketChannel.write(send);
                            }
                            // socketChannel.write(ByteBuffer.wrap(resData.getBytes()));
                            //socketChannel.register(selector, SelectionKey.OP_READ);
                            socketChannel.close();
                            System.out.println("响应数据["+ sendStr +"]到【"+socketChannel.getRemoteAddress()+"】");
                        }

                    }catch(IOException iox){
                        try {
                            if( selectionKey != null ) {
                                //使这个SelectionKey失效, 使得Selector不再监控这个SelectionKey感兴趣的事件
                                selectionKey.cancel();
                                selectionKey.channel().close(); //关闭与这个SelectionKey关联的SocketChannel
                            }
                        }
                        catch( Exception ex )
                        {
                            ex.printStackTrace();
                        }
                    }

                }
            }

        }catch (Exception e){
            log.error("断哦嘎：{}" , e.getMessage());
        }
    }
    /**
     * 创建主控服务线程
     * @param port 服务端口
     * @throws java.lang.Exception
     */
    public static int MAX_THREADS = 4;
    public NIOServer(int port) throws Exception {
       // this.port = port;

        // 获取事件触发器
        //notifier = Notifier.getNotifier();

        // 创建读写线程池
//        for (int i = 0; i < MAX_THREADS; i++) {
//            Thread r = new Reader();
//            Thread w = new Writer();
//            Thread sys = new Syslog();
//            r.start();
//            w.start();
//            sys.start();
//        }

    }
}