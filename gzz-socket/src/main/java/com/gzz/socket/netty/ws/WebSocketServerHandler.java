package com.gzz.socket.netty.ws;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;

import static io.netty.handler.codec.http.HttpHeaderUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaderUtil.setContentLength;

public class WebSocketServerHandler  extends SimpleChannelInboundHandler<Object> {
    private WebSocketServerHandshaker handshaker;
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        // HTTP方式接入
        if(msg instanceof FullHttpRequest){
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }// WEBSOKET方式接入
        else if(msg instanceof WebSocketFrame){
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.flush();
    }

    /**
     * HTTP方式处理
     * @param ctx
     * @param req
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req){
        // 如果HTTP解码失败， 返回HTTP异常
       if( (!req.decoderResult().isSuccess())||(!"websocket".equals(req.headers().get("Upgrade")))){
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
       }
       // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:10086/websocket", null, false);
        handshaker = wsFactory.newHandshaker(req);
        if(handshaker ==null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else{
            handshaker.handshake(ctx.channel(), req);
        }

    }

    /**
     * websocket 方式处理
     * @param ctx
     * @param frame
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame){
        // 判断是否关闭链路的指令
        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        //判断是否是ping消息。
        if(frame instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 仅支持文本消息，
        if(!(frame instanceof TextWebSocketFrame)){
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }
        // 返回响应信息
        String requst = ((TextWebSocketFrame)frame).text();

        ctx.channel().write(new TextWebSocketFrame(requst
                +", 欢迎使用WS NETTY服务，现在时刻："
                + new Date().toString()
        ));

    }

    /**
     * 响应发送
     * @param ctx
     * @param req
     * @param resp
     */
    private void  sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse resp){
        if(resp.status().code() != 200){
            ByteBuf buf = Unpooled.copiedBuffer(resp.status().toString(), CharsetUtil.UTF_8);
            resp.content().writeBytes(buf);
            buf.release();
            //
            setContentLength(resp, resp.content().readableBytes());
        }
        // 如果非keep-alive, 关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(resp);
        if(isKeepAlive(req)|| resp.status().code() !=200){
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void  exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception{
        cause.printStackTrace();
        ctx.close();
    }
}
