package com.gzz.netty.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * TCP 粘包/ 拆包
 * TCP 是个“流”协议，所谓流，就是没有界限的一串数据，可以想象河里的流水连成一片，其间并没有分界线
 * TCP 底层并不了解上层业务数据的具体含义，它会根据 TCP 缓冲区的实际情况进行包的划分，
 * 业务上认为，一个完整的包可能会被 TCP 拆分为多个包，也可能把多个小的包封装成一个大的数据包，这就是所谓的拆包与粘包。
 * <p>
 * 一次接收到了两个完整的数据包，D1 与 D2 粘合在一起，被称为 TCP 粘包
 * 分两次接收到了两个数据包（其中一次收到D1或D2的一部份），这被称为 TCP 拆包
 * 数据包 D1 与 D2 比较大，会发生多次拆包
 */
public class TimeClient {

    /**
     * 使用 100 个线程模拟 100 个客户端
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                /**配置客户端 NIO 线程组/池*/
                EventLoopGroup group = new NioEventLoopGroup();
                try {
                    /**Bootstrap 与 ServerBootstrap 都继承(extends)于 AbstractBootstrap
                     * 创建客户端辅助启动类,并对其配置,与服务器稍微不同，这里的 Channel 设置为 NioSocketChannel
                     * 然后为其添加 Handler，这里直接使用匿名内部类，实现 initChannel 方法
                     * 作用是当创建 NioSocketChannel 成功后，在进行初始化时,将它的ChannelHandler设置到ChannelPipeline中，用于处理网络I/O事件*/
                    Bootstrap b = new Bootstrap();
                    b.group(group).channel(NioSocketChannel.class)
                            .option(ChannelOption.TCP_NODELAY, true)
                            .handler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                public void initChannel(SocketChannel ch) throws Exception {
                                    /**
                                     * 添加 LineBasedFrameDecoder、StringDecoder 解码器
                                     */
                                    ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                                    ch.pipeline().addLast(new StringDecoder());
                                    ch.pipeline().addLast(new TimeClientHandler());
                                }
                            });

                    /**connect：发起异步连接操作，调用同步方法 sync 等待连接成功*/
                    ChannelFuture channelFuture = b.connect("127.0.0.1", 9898).sync();
//                System.out.println(Thread.currentThread().getName() + ",客户端发起异步连接..........");

                    /**等待客户端链路关闭*/
                    channelFuture.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    /**优雅退出，释放NIO线程组*/
                    group.shutdownGracefully();
                }
            }).start();
        }
    }
}