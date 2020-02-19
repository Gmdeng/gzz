package com.gzz.mq.action;

import com.gzz.mq.jms.JMSConfig;
import com.gzz.mq.jms.PayProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 *
 */
@RestController
public class IndexAction {
    @Value("${spring.data.user.age}")
    private String age;
    @Autowired
    private PayProducer payProducer;

    /**
     * 同步发送
     *
     * @param text
     * @return
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     */
    @RequestMapping("/api/v1/paySync")
    public Object syncSend(String text) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        // 为保证重试，消息要设置唯一的KEY值。
        Message msg = new Message(JMSConfig.TOPIC, "TagA", UUID.randomUUID().toString(), ("Hello G-m Studio." + text).getBytes());
        // Message msg = new Message(JMSConfig.TOPIC, "taga",("Hello G-m Studio." + text).getBytes());
        SendResult sendResult = null;

        //sendResult = payProducer.getProducer().send(msg);
        //指定投递的队列3
        sendResult = payProducer.getProducer().send(msg, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                int queueNum = Integer.parseInt(arg.toString());
                return mqs.get(queueNum);
            }
        }, 3);
        System.out.print(sendResult);

        return "OK";
    }

    /**
     * 异步发送
     *
     * @param text
     * @return
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     */
    @RequestMapping("/api/v1/mqCallback")
    public Object callbackSend(String text) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        // 为保证重试，消息要设置唯一的KEY值。
        Message msg = new Message(JMSConfig.TOPIC, "TagA", UUID.randomUUID().toString(), ("Hello G-m Studio." + text).getBytes());
        // Message msg = new Message(JMSConfig.TOPIC, "taga",("Hello G-m Studio." + text).getBytes());
//        payProducer.getProducer().send(msg, new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                System.out.print(sendResult);
//            }
//
//            @Override
//            public void onException(Throwable e) {
//
//            }
//        });
        //指定投递的队列3
        payProducer.getProducer().send(msg, (mqs, meg, arg) -> {
                    //int queueNum = Integer.parseInt(arg.toString());
                    // 用Hashcode 作为订单号唯一的int数字
                    int orderId = arg.hashCode();
                    int queueNum = orderId % mqs.size();
                    return mqs.get(queueNum);
                }, "ORDERID0111111",
                new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.print(sendResult);
                    }
                    @Override
                    public void onException(Throwable e) {

                    }
                });

        return "OK";
    }

}
