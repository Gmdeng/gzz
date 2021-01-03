package com.gzz.socket.reactor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * 分发管理器
 * 启动了三个线程，ServerAcceptHandler，ServerReadHandler 和 ServerWriteHandler。
 * accept使用了一个selector，read和write各使用了一个selector。
 */
public class ServerDispatcher {
    private ServerSocketChannel ssc;
    private Selector[] selectors = new Selector[3];
    //private SelectorProvider selectorProvider;

    private final static Logger log = LogManager.getLogger(ServerDispatcher.class);

    public ServerDispatcher(ServerSocketChannel ssc, SelectorProvider selectorProvider) throws IOException {
        this.ssc = ssc;
        //this.selectorProvider = selectorProvider;
        // 创建三个通道管理器
        for (int i = 0; i < 3; i++)
        {
            // 这种方式直接调用SelectorProvider类来获取操作系统底层的Selector来创建Selector
            selectors[i] = selectorProvider.openSelector();
        }
    }

    public Selector getAcceptSelector() {
        return selectors[0];
    }

    public Selector getReadSelector() {
        return selectors[1];
    }

    public Selector getWriteSelector() {
        return selectors[1];
    }

    public void execute() throws IOException
    {
        SocketHandler r = new SocketAcceptHandler(this, ssc, getAcceptSelector());
        new Thread(r).start();

        r = new SocketReadHandler(this, ssc, getReadSelector());
        new Thread(r).start();

        r = new SocketWriteHandler(this, ssc, getWriteSelector());
        new Thread(r).start();
    }
}
