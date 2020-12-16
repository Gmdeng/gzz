package com.gzz.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer {
    public static void main(String[] args) {
        //EventLoopGroup是用来处理IO操作的多线程事件循环器
        //bossGroup 用于服务端接受客户端连接，接收客户端连接线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup 用于进行 SocketChannel 网络读写,
        // 负责处理客户端i/o事件、task任务、监听任务组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // ServerBootstrap 是 Netty 用于启动 NIO 服务端的辅助启动类，用于降低开发难度
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    //
                    .channel(NioServerSocketChannel.class)
                    //BACKLOG用于构造服务端套接字ServerSocket对象，
                    // 标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //是否启用心跳保活机制
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChildChannelHandler());
            /**服务器启动辅助类配置完成后，调用 bind 方法绑定监听端口，调用 sync 方法同步等待绑定操作完成*/
            ChannelFuture f = b.bind(8888).sync();

            System.out.println(Thread.currentThread().getName() + ",服务器开始监听端口，等待客户端连接.........");

            //下面会进行阻塞，等待服务器连接关闭之后 main 方法退出，程序结束
            // channel.closeFuture().sync()实际是如何工作:
            // channel.closeFuture()不做任何操作，只是简单的返回channel对象中的closeFuture对象，
            // 对于每个Channel对象，都会有唯一的一个CloseFuture，用来表示关闭的Future，
            // 所有执行channel.closeFuture().sync()就是执行的CloseFuturn的sync方法，从上面的解释可以知道，
            // 这步是会将当前线程阻塞在CloseFuture上
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /**优雅退出，释放线程池资源*/
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 服务器初始化
     */
    private static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            //注册管道
            socketChannel.pipeline()
                    .addLast()
                    //服务通道处理
                    .addLast(new HelloServerHandler());
        }
    }
}
