package com.gzz.mq.jms;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

/**
 * 生产者
 */
@Component
public class PayProducer {
    private final static String PRODUCER_GROUP = "pay_group";


    private DefaultMQProducer producer;
    public PayProducer(){
        producer = new DefaultMQProducer(PRODUCER_GROUP);
        // 发送失败重试次数
        producer.setRetryTimesWhenSendFailed(8);
        // 指定NameServer地址
        producer.setNamesrvAddr(JMSConfig.NAME_SERVER_ADDR);
        start();
    }

    public void start()  {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对象关闭
     */
    public void shutdown(){
        this.producer.shutdown();
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }
}

