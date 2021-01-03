package com.gzz.netty.timetcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

public class TimeTCPClientHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf firstMessage1;
    private int counter;
    public TimeTCPClientHandler() throws UnsupportedEncodingException {
        byte[] req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes("UTF-8");
        firstMessage1 = Unpooled.buffer(req.length);
        firstMessage1.writeBytes(req);

        //req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes("UTF-8");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //ctx.writeAndFlush(firstMessage1);

        byte[]  req;
        String reqMsg2 = "QUERY TIME ORDER";
        ByteBuf reqBuf = null;
        for(int i =0; i<100;i++){
            String reqMsg =reqMsg2+ i +System.getProperty("line.separator");
            req = reqMsg.getBytes("UTF-8");
            reqBuf = Unpooled.buffer(req.length);
            reqBuf.writeBytes(req);
            ctx.writeAndFlush(reqBuf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req, "UTF-8");
        System.out.println("当前时间Now is :" + body +" ; the counter is :" + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Unexpected Exception from downstream : " + cause.getMessage());
        ctx.close();
    }
}
