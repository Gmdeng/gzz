package com.gzz.socket.netty.ws;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.Arrays;

/**
 * 用于和客户端进行连接
 *
 * @author phubing
 */
public class WebSocketServer {
    public static void main(String[] args) throws InterruptedException {
        //定义线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup)
                    //channel类型
                    .channel(NioServerSocketChannel.class)
                    //针对subGroup做的子处理器，childHandler针对WebSokect的初始化器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //从Channel中获取对应的pipeline
                            ChannelPipeline pipe = socketChannel.pipeline();

                            pipe.addLast("http-codec",new HttpServerCodec());
                            pipe.addLast("http-chunked", new ChunkedWriteHandler());
                            pipe.addLast("Aggregator", new HttpObjectAggregator(65536));

                            //================华丽的分割线：以上是用于支持Http协议================
                            //================华丽的分割线：以下是用于支持WebSoket==================

//                            // /ws：一开始建立连接的时候会使用到，可自定义
//                            //WebSocketServerProtocolHandler：给客户端指定访问的路由（/ws），是服务器端处理的协议，当前的处理器处理一些繁重的复杂的东西，运行在一个WebSocket服务端
//                            //另外也会管理一些握手的动作：handshaking(close，ping，pong) ping + pong = 心跳，对于WebSocket来讲，是以frames进行传输的，不同的数据类型对应的frames也不同
//                            pipe.addLast(new WebSocketServerProtocolHandler("/ws"));

                            //添加自动handler，读取客户端消息并进行处理，处理完毕之后将相应信息传输给对应客户端
                            pipe.addLast(new WebSocketServerHandler());
                        }
                    });

            //绑定端口并以同步方式进行使用
            ChannelFuture f = server.bind(10086).sync();
            System.out.println("Web Socket server started at port: 10086 .");
            System.out.println("Open your browser and navigate to http://localhost:10086/");
            //针对channelFuture，进行相应的监听
            f.channel().closeFuture().sync();

        } finally {
            //针对两个group进行优雅地关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}