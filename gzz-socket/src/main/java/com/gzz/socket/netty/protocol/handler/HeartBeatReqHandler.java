package com.gzz.socket.netty.protocol.handler;

/**
 * 心跳机制检测
 * 握手成功之后，由客户端主动发送心跳消息，服务端接收到心跳消息之后，返回应答，由于心跳消息的目的是为了检测链路的可用性，因此不需要携带消息体。
 */

import com.gzz.socket.netty.protocol.message.Header;
import com.gzz.socket.netty.protocol.message.MessageType;
import com.gzz.socket.netty.protocol.message.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 客户端定时发送心跳，处理器
 */
public class HeartBeatReqHandler extends ChannelHandlerAdapter {


    private final static Logger LOG = LogManager.getLogger(HeartBeatReqHandler.class);

    //使用定时任务发送
    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 当握手成功后，Login响应向下透传，主动发送心跳消息
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.LOGIN_RESP
                .value()) {
            //NioEventLoop是一个Schedule,因此支持定时器的执行，创建心跳计时器
            heartBeat = ctx.executor().scheduleAtFixedRate(
                    new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000,
                    TimeUnit.MILLISECONDS);
        } else if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.HEARTBEAT_RESP
                .value()) {
            LOG.info("Client receive server heart beat message : ---> "
                    + message);
        } else
            ctx.fireChannelRead(msg);
    }

    //Ping消息任务类
    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage heatBeat = buildHeatBeat();
            LOG.info("Client send heart beat messsage to server : ---> "
                    + heatBeat);
            ctx.writeAndFlush(heatBeat);
        }

        private NettyMessage buildHeatBeat() {
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            header.setType(MessageType.HEARTBEAT_REQ.value());
            message.setHeader(header);
            return message;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }
}