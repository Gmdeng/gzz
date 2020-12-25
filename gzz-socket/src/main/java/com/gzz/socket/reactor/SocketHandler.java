package com.gzz.socket.reactor;

import com.gzz.socket.nio.NIOServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class SocketHandler implements Runnable {
    private final static Logger log = LogManager.getLogger(SocketHandler.class);
    protected Selector selector;
    protected SocketChannel socketChannel = null;
    protected ServerSocketChannel serverSocketChannel;
    protected ServerDispatcher dispatcher;
    // 读写锁
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();;

    public SocketHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector) throws IOException{
        this.selector = selector;
        this.serverSocketChannel = sc;
        this.dispatcher = dispatcher;
    }

    public abstract void runnerExecute(int readyKeyOps) throws IOException;

    public final void run()
    {
        while(true)
        {
            readWriteLock.readLock().lock();
            try {
                int keyOps = this.Select();
                // log.info("睡。。。。。。。"+ this.getClass().getName());
                runnerExecute(keyOps);
                TimeUnit.SECONDS.sleep(1L);

                //Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
                log.error(e.getMessage());
                e.printStackTrace();
            }
            finally {
                readWriteLock.readLock().unlock();
            }
        }
    }

    private int Select() throws IOException
    {
        //不会阻塞，不管什么通道就绪都立刻返回
        int keyOps = this.selector.selectNow();

        boolean flag = keyOps > 0;

        if (flag)
        {
            Set readyKeySet = selector.selectedKeys();
            Iterator iterator = readyKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();
                iterator.remove();
                keyOps = key.readyOps();
                if (keyOps == SelectionKey.OP_READ || keyOps == SelectionKey.OP_WRITE) {
                    socketChannel = (SocketChannel)key.channel();
                    socketChannel.configureBlocking(false);
                }
            }
        }

        return keyOps;
    }
}
