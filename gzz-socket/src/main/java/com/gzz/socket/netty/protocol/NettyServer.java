package com.gzz.socket.netty.protocol;
import com.gzz.socket.netty.protocol.coder.NettyMessageDecoder;
import com.gzz.socket.netty.protocol.coder.NettyMessageEncoder;
import com.gzz.socket.netty.protocol.handler.HeartBeatRespHandler;
import com.gzz.socket.netty.protocol.handler.LoginAuthRespHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Private protocol stack DEMO
 * 私有协议栈 DEMO
 *
 */
public class NettyServer {
    private final static Logger LOG = LogManager.getLogger(NettyServer.class);

    public void bind() throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
//                .option(ChannelOption.SO_BACKLOG, 128)
//                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
//                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
//                .option(ChannelOption.SO_KEEPALIVE, true)
                    //设置日志
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws IOException {
                            ch.pipeline().addLast(
                                    new NettyMessageDecoder(1024 * 1024, 4, 4));
                            ch.pipeline().addLast(new NettyMessageEncoder());
                            // 实现50秒钟，如果两端，没有数据读取，直接断开连接
                            ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                            // 实现50秒钟，如果两端，没有数据写入，直接断开连接
                            // ch.pipeline().addLast("writeTimeoutHandler", new WriteTimeoutHandler(50));
                            ch.pipeline().addLast("LoginAuthRespHandler", new LoginAuthRespHandler());
                            ch.pipeline().addLast("HeartBeatRespHandler", new HeartBeatRespHandler());
                        }
                    });

            // 绑定端口，同步等待成功
            ChannelFuture f = b.bind(NettyConstant.REMOTEIP, NettyConstant.PORT).sync();
            LOG.info("Netty server start ok : "
                    + (NettyConstant.REMOTEIP + " : " + NettyConstant.PORT));

            // 等到服务端监听端口关闭
            f.channel().closeFuture().sync();
        }catch (Exception ex){

        }finally {
            // 优雅释放线程资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        new NettyServer().bind();
    }
}