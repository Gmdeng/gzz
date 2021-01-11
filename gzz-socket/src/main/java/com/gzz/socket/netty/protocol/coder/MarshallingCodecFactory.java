package com.gzz.socket.netty.protocol.coder;
import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.*;

import java.io.IOException;

public final class MarshallingCodecFactory {
    /**
     * 创建Jboss Marshaller
     *
     * @return
     * @throws IOException
     */
    protected static Marshaller buildMarshalling() throws IOException {
        //首先通过Marshalling工具类的精通方法获取Marshalling实例对象 参数serial标识创建的是java序列化工厂对象。
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        //创建了MarshallingConfiguration对象，配置了版本号为5
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
//        Marshaller marshaller = marshallerFactory
//                .createMarshaller(configuration);
//        return marshaller;
        return marshallerFactory.createMarshaller(configuration);
    }

    /**
     * 创建Jboss Unmarshaller
     *
     * @return
     * @throws IOException
     */
    protected static Unmarshaller buildUnMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling
                .getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        final Unmarshaller unmarshaller = marshallerFactory
                .createUnmarshaller(configuration);
        return unmarshaller;
    }

    /**
     * 创建Jboss Marshalling解码器MarshallingDecoder
     */
    public static MarshallingDecoder buildMarshallingDecoder() {
        //首先通过Marshalling工具类的getProvidedMarshallerFactory静态方法获取MarshallerFactory实例
        //参数“serial”表示创建的是Java序列化工厂对象，它由jboss-marshalling-serial-1.3.0.CR9.jar提供。
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        //创建了MarshallingConfiguration对象
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        //将它的版本号设置为5
        configuration.setVersion(5);
        //然后根据MarshallerFactory和MarshallingConfiguration创建UnmarshallerProvider实例
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
        //最后通过构造函数创建Netty的MarshallingDecoder对象
        //它有两个参数，分别是UnmarshallerProvider和单个消息序列化后的最大长度。
        MarshallingDecoder decoder = new MarshallingDecoder(provider, 1024);
        return decoder;
    }

    /**
     * 创建Jboss Marshalling编码器MarshallingEncoder
     */
    public static MarshallingEncoder buildMarshallingEncoder() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        //创建MarshallerProvider对象，它用于创建Netty提供的MarshallingEncoder实例
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
        //MarshallingEncoder用于将实现序列化接口的POJO对象序列化为二进制数组。
        MarshallingEncoder encoder = new MarshallingEncoder(provider);
        return encoder;
    }
}
