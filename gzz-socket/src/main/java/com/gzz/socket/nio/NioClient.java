package com.gzz.socket.nio;
import com.github.javafaker.Faker;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class NioClient {
    private static final int SERVER_PORT =9988;
    private static final String SERVER_ADDR = "127.0.0.1";
    private static final ExecutorService executor = Executors.newCachedThreadPool();
    private static final AtomicLong countSend=new AtomicLong(0L);

    public static  final Locale locale=Locale.SIMPLIFIED_CHINESE;
    public static  final Faker faker =new Faker(locale);
    public static void main(String[] args) throws InterruptedException {
        clientStart();
    }
    public static void clientStart() throws InterruptedException {
        OnlyNosClients();
    }

    private static void OnlyNosClients() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            executor.submit(
                    new RequestRemoteServerWithTcp(SERVER_ADDR,SERVER_PORT,countSend)
            );
        }
        //10秒钟后中断所有线程
        TimeUnit.SECONDS.sleep(10);
        executor.shutdownNow();
    }

    private static void startNewClients()  {
        while (!Thread.currentThread().isInterrupted())
        {
            executor.submit(
                    new RequestRemoteServerWithTcp(SERVER_ADDR,SERVER_PORT,countSend)
            );
        }
    }


    /**
     *
     */
    private static class RequestRemoteServerWithTcp implements Runnable {
        private final String serverAddress;
        private final int serverPoint;
        private int localPoint;
        private final AtomicLong countSend ;

        public RequestRemoteServerWithTcp(String serverAddress,int serverPoint,AtomicLong countSend){
            this.serverAddress=serverAddress;
            this.serverPoint=serverPoint;
            this.countSend=countSend;
        }
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            try {
                this.doRequest();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        private void doRequest() throws IOException {
            SocketChannel socketChannel = null;
            try {
                socketChannel = SocketChannel.open(new InetSocketAddress(this.serverAddress,this.serverPoint));
                socketChannel.configureBlocking(false);
                SocketAddress localAddress = socketChannel.getLocalAddress();
                while(!socketChannel.isConnected()){}
                //logger.debug("[{}]完成连接",socketChannel.getLocalAddress());
                this.localPoint = ((InetSocketAddress) socketChannel.getLocalAddress()).getPort();
                while (!Thread.currentThread().isInterrupted())
                {
                    ByteBuffer buffer = this.prepareData(this.localPoint);
                    socketChannel.write(buffer);
                    buffer.clear();
                    long currentSendCount = this.countSend.incrementAndGet();
                    //logger.info("客户端[{}]的第【{}】条数据发送完成",localAddress,currentSendCount);
                    TimeUnit.SECONDS.sleep(3);
                    ByteBuffer readBuffer = ByteBuffer.allocate(2*1024);
                    getResponseData(socketChannel, readBuffer);
                }
            } catch (IOException ioException) {
                //logger.error("ioException:",ioException);
            }catch (Exception e)
            {
                //logger.error("端口为{}的客户端报错",this.localPoint,e);
            }finally {
                if (!Objects.isNull(socketChannel)&&socketChannel.isOpen())
                {
                    //关闭输出通道
                    socketChannel.shutdownOutput();
                    socketChannel.shutdownInput();
                    socketChannel.close();
                    System.out.println("【"+ this.localPoint +"】的输出通道关闭！");
                }
            }

        }

        private void getResponseData(SocketChannel socketChannel, ByteBuffer readBuffer) throws IOException {
            //接收客户端数据
            int readBytes = 0;
            StringBuilder content = new StringBuilder();
            while ((readBytes=socketChannel.read(readBuffer))>0) {
                //处理数据
                content.append(new String(readBuffer.array(), StandardCharsets.UTF_8));
                readBuffer.clear();
            }
            //判断服务端断开连接
            //当服务端没有断开连接的时候readBytes=0这样保持长连接
            //当服务端断开连接的时候readBytes=-1
            if (readBytes<0)
            {
                System.out.println("服务端断开连接.....");
                socketChannel.close();
            }
            //处理数据
            else {
                System.out.println("收到的内容是： <"+content.toString()+">");
            }
        }

        private ByteBuffer prepareData(int channelPoint)  {
            StringBuilder name =new StringBuilder(faker.university().name());
            name.insert(0,(channelPoint+"==========>"));
            return ByteBuffer.wrap(name.toString().getBytes(StandardCharsets.UTF_8));
        }
    }
}