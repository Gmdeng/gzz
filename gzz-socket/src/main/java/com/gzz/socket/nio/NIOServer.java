package com.gzz.socket.nio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NIO 服务端
 */
public class NIOServer {
    private final static Logger log = LogManager.getLogger(NIOServer.class);
    private static final AtomicInteger selectorNo =new AtomicInteger(0);
    private static final AtomicInteger handleDataNo =new AtomicInteger(0);
    private static final AtomicInteger resDataNo=new AtomicInteger(0);

    //private final static String HOST=  "192.168.100.163";
    private final static int PORT=  9988;
    // 通道管理器
    // Selector是一个多路复用器，它负责管理被注册到其上的SelectableChannel
    private Selector selector;
    /**
     *
     */
    public NIOServer() throws IOException {

        //工厂方法创建ServerSocketChannel
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //获取channel对应的ServerSocket
        ServerSocket serverSocket = ssChannel.socket();
        //使得在同一个主机上关闭了服务器程序，紧接着再启动该服务器程序时，可以顺利绑定到相同的端口
        serverSocket.setReuseAddress(true);
        //绑定地址 把服务器进程与一个本地端口绑定
        //serverSocket.bind(new InetSocketAddress(HOST, PORT));
        serverSocket.bind(new InetSocketAddress(PORT));
        //设置ServerSocketChannel非阻塞模式
        ssChannel.configureBlocking(false);

        //工厂方法创建Selector
        // 创建一个Selector对象无阻塞网络套接
        selector = Selector.open();
        //通道注册选择器，接受连接就绪状态。
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 服务监听中
     * 多路复用(selector)
     * @throws IOException
     */
    public void listen() throws IOException {
        log.info("NIO服务监听中， 等待接收数据... ");
        // 监听 循环检查
        //阻塞检查，当有就绪状态发生，返回键集合.
        // select(timeout) 为非阻塞的。
        while (true) {
            log.info("第【{}】轮监听===============================================", selectorNo.incrementAndGet());
            // 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
            int num = selector.select();
            log.info("监听到{}个事件0###########", num);
            if(num ==0){
                log.info("继续监听{}");
                continue;
            }
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
                        log.info("IO 异常关闭");
                        if( selectionKey != null ) {
                            //使这个SelectionKey失效, 使得Selector不再监控这个SelectionKey感兴趣的事件
                            selectionKey.cancel();
                            if(selectionKey.channel() !=null) {
                                //关闭与这个SelectionKey关联的SocketChannel
                                selectionKey.channel().close();
                            }
                        }
                    }
                    catch( Exception ex )
                    {
                        ex.printStackTrace();
                    }
                }

            }

        }
    }
    public static void main(String[] args) {

        try {
            NIOServer serv = new NIOServer();
            serv.listen();
        } catch (IOException e) {
            e.printStackTrace();
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