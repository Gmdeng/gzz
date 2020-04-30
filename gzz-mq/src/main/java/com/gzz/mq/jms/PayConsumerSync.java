package com.gzz.mq.jms;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;

/**
 * 单线程接收方式DEMO
 */
// @Component
public class PayConsumerSync {
    private final static String CONSUMER_GROUP = "pay_consumer_group";
    private DefaultMQPushConsumer consumer;

    public PayConsumerSync() throws MQClientException {
        consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        consumer.setNamesrvAddr(JMSConfig.NAME_SERVER_ADDR);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        // 默认是集群方式，可以改为广播，但广播不支持重试。
        // consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.subscribe(JMSConfig.TOPIC, "*");
        // JDK8 写法
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) ->{
            System.out.printf("%s Receive Mew Message: %s %n", Thread.currentThread().getName(), new String(msgs.get(0).getBody()));
            MessageExt msg = msgs.get(0);
            // 重试次数
            int times = msg.getReconsumeTimes();
            System.out.printf("重试次数" + times);
            try {
                String topic = msg.getTopic();
                String body = new String(msg.getBody(),"UTF-8");
                String tags = msg.getTags();
                String keys = msg.getKeys();
                System.out.printf("Topic={%S},Body={%S},Tags={%S}, Keys={%S}", topic, body, tags, keys);
                return ConsumeOrderlyStatus.SUCCESS;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                if( times > 5){
                    // TODO 发信息给开发人员
                    return ConsumeOrderlyStatus.SUCCESS;
                }
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }
        });
        consumer.start();
        System.out.printf("Consumer start.....");
    }
}
