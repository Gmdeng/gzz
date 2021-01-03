package com.gzz.socket.reactor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * 多个selector来配合完成NIO的操作。这样做的好处是可以最大限度的榨取服务器的资源。
 * 我们使用一个selector来处理accept的工作，完成绑定，监听，一个selector完成读写，发送的工作
 */
public class ServerReactor {
    private final static Logger log = LogManager.getLogger(ServerReactor.class);

    private SelectorProvider selectorProvider = SelectorProvider.provider();
    private ServerSocketChannel serverSocketChannel;

    /**
     * 启动入口
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int port = 9988;
        new ServerReactor(port);
    }

    public ServerReactor(int port) throws IOException {
        // 打开方式与单一个区别在这里
        serverSocketChannel = selectorProvider.openServerSocketChannel(); //ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port), 1024);
        serverSocketChannel.configureBlocking(false);
        log.debug(String.format("Server : Server Start.----%d", port));
        try {
            new ServerDispatcher(serverSocketChannel, SelectorProvider.provider()).execute();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
