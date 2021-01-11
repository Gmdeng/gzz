package com.gzz.socket.netty.demo;

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
/**
 * 服务端接送类
 * @author Administrator
 *
 */
public class Server {

    public static void main(String[] args) throws Exception{
        // 配置NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
        // 服务器辅助启动类配置
        ServerBootstrap b = new ServerBootstrap();
        b.group(workGroup, bossGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)

                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                .option(ChannelOption.SO_KEEPALIVE, true)
                //设置日志
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    // 网络事件处理器
                    protected void initChannel(SocketChannel sc) throws Exception {
                        // 添加Jboss的序列化，编解码工具
                        sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());

                        //实现5秒钟，如果两端，如果数据读取，直接断开连接
                        sc.pipeline().addLast(new ReadTimeoutHandler(5));

                        //实现5秒钟，如果两端，如果数据写入，直接断开连接
                        //sc.pipeline().addLast(new WriteTimeoutHandler(5));

                        // 自定义处理网络IO
                        sc.pipeline().addLast(new ServerHandler());
                    }
                });
// 绑定端口 同步等待绑定成功
        ChannelFuture cf = b.bind(8765).sync();
// 等到服务端监听端口关闭
        cf.channel().closeFuture().sync();
        }catch (Exception ex){}
        finally {
            // 优雅释放线程资源
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }
}