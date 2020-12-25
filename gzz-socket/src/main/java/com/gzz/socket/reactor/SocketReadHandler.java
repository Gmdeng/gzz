package com.gzz.socket.reactor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class SocketReadHandler extends SocketHandler {
    private final static Logger log = LogManager.getLogger(SocketAcceptHandler.class);

    private  int BLOCK = 4096;
    private  ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);

    public SocketReadHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector) throws IOException {
        super(dispatcher, sc, selector);
    }

    @Override
    public void runnerExecute(int readyKeyOps) throws IOException {
        log.debug("Server : Readable.。。。。。。。。。。。。。。。。。。。。。。。。。{}", readyKeyOps);
        int count = 0;
        if (SelectionKey.OP_READ == readyKeyOps)
        {
            receivebuffer.clear();
            count = socketChannel.read(receivebuffer);
            if (count > 0) {
                log.debug("Server : Readable.");
                receivebuffer.flip();
                byte[] bytes = new byte[receivebuffer.remaining()];
                receivebuffer.get(bytes);
                String body = new String(bytes, "UTF-8");
                log.debug("Server : Receive Data:" + body);
                socketChannel.register(dispatcher.getWriteSelector(), SelectionKey.OP_WRITE);
            }
        }

    }
}
