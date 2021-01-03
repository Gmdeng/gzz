package com.gzz.socket.reactor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 接受连接处理器
 */
public class SocketAcceptHandler extends SocketHandler {
    private final static Logger log = LogManager.getLogger(SocketAcceptHandler.class);

    public SocketAcceptHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector) throws IOException {
        super(dispatcher, sc, selector);
        serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT, this);
    }

    @Override
    public void runnerExecute(int readyKeyOps) throws IOException {
        log.debug("Server : accept.。。。。。。。。。。。。。。。。。。。。。。。。。{}", readyKeyOps);
        if (readyKeyOps == SelectionKey.OP_ACCEPT)
        {
            socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            log.debug("Server accept");
            log.info("Accept  收到了来自于【{}】的连接请求", socketChannel.getRemoteAddress());
            socketChannel.register(dispatcher.getReadSelector(), SelectionKey.OP_READ);
        }
    }
}
