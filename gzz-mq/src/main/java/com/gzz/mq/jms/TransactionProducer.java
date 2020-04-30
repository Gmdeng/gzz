package com.gzz.mq.jms;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 事务-生产者
 */
@Component
public class TransactionProducer {
    private final static String PRODUCER_GROUP = "trac_pay_group";


    private TransactionListener listener = new TransactionListenerImpl();
    private TransactionMQProducer producer = null;

    private ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS
            , new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("client-transaction-msg-check-thread");
            return thread;
        }
    });


    public TransactionProducer(){
        producer = new TransactionMQProducer(PRODUCER_GROUP);
        // 指定NameServer地址
        producer.setNamesrvAddr(JMSConfig.NAME_SERVER_ADDR);
        producer.setTransactionListener(listener);
        producer.setExecutorService(executorService);
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

    public TransactionMQProducer getProducer() {
        return producer;
    }

     public class TransactionListenerImpl implements TransactionListener {
         /**
          * 本地事务处（）
          * @param msg
          * @param arg
          * @return
          */
        @Override
        public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            System.out.println("=====executeLocalTransaction=================");
            String body = new String(msg.getBody());
            String key = msg.getKeys();
            String transactionId =msg.getTransactionId();
            System.out.printf("transactionId:"+ transactionId + ", key:" +key+ ", body:"+ body);
            // 本地事务理处理TODO
            // 本地事务理处理END

            int status =Integer.parseInt(arg.toString());
            // 二次确认消息，然后消费可以消费
            if(status ==1){
                return LocalTransactionState.COMMIT_MESSAGE;
            }
            // 回滚消息，broker端会删除半消息
            if(status ==2){
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
            // broker端会进行回查消息
            if(status ==3){
                return LocalTransactionState.UNKNOW;
            }
            return null;
        }

         /**
          * 回查消息处理
          * @param msg
          * @return
          */
        @Override
        public LocalTransactionState checkLocalTransaction(MessageExt msg) {
            System.out.println("=====checkLocalTransaction=================");
            String body = new String(msg.getBody());
            String key = msg.getKeys();
            String transactionId =msg.getTransactionId();
            System.out.printf("transactionId:"+ transactionId + ", key:" +key+ ", body:"+ body);
            // 要么commit ，要么rollback
            // 可以根据key去检查本地事务消息是否完成
            return LocalTransactionState.COMMIT_MESSAGE;
        }
    }
}

