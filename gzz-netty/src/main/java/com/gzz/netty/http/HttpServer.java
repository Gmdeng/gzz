package com.gzz.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * HTTP服务器
 * 请使用 http://localhost:8899访问
 */
public class HttpServer {


    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerinitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            System.out.println(Thread.currentThread().getName() + ",服务器开始监听端口，等待客户端连接.........");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    /**
     *
     */
    private static class HttpServerinitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            ChannelPipeline pipe = socketChannel.pipeline();
            //编码解码合二为一
            pipe.addLast("httpServerCodec", new HttpServerCodec());
            pipe.addLast("httpServerHandler", new HttpServerHandler());

            pipe.addLast("http-decoder", new HttpRequestDecoder());
            pipe.addLast("http-aggregator", new HttpObjectAggregator(65536));
            pipe.addLast("http-encoder", new HttpResponseEncoder());
            pipe.addLast("http-chunked", new ChunkedWriteHandler());
            pipe.addLast("fileServerHandler", new ChunkedWriteHandler());

        }

    }

}
