package com.gzz.demo.proandcus;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * JAVA并发实现五(生产者和消费者模式Condition方式实现)
 *
 * Condition是在java 1.5中才出现的，它用来替代传统的Object的wait()、notify()实现线程间的协作，相比使用Object的wait()、notify()，
 * 使用Condition的await()、signal()这种方式实现线程间协作更加安全和高效。
 Condition是个接口，基本的方法就是await()和signal()方法；
 Condition依赖于Lock接口，生成一个Condition的基本代码是lock.newCondition()
 调用Condition的await()和signal()方法，都必须在lock保护之内，就是说必须在lock.lock()和lock.unlock之间才可以使用
 Conditon中的await()对应Object的wait()；

 　　Condition中的signal()对应Object的notify()；

 　　Condition中的signalAll()对应Object的notifyAll()。
 * @author 孙涛
 * 2016年5月12日
 */
public class CustormerAndProducterByCondition {

    private int queueSize = 10 ;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    class Consumer implements Runnable{

        @Override
        public void run() {
            consume();
        }
        /**
         * 消费者
         * @author suntao
         * 2016年5月12日
         */
        private void consume() {
            while(true){
                lock.lock();
                try {
                    while(queue.size() == 0){
                        try {
                            System.out.println("队列空，等待数据");
                            empty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    full.signal();
                    System.out.println("从队列取走一个元素，队列剩余"+queue.size()+"个元素");
                } finally{
                    lock.unlock();
                }
            }

        }
    }
    /**
     * 消费者
     * @author 孙涛
     * 2016年5月12日
     */
    class Producer implements Runnable{

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while(true){
                lock.lock();
                try {
                    while(queue.size()== queueSize){
                        try {
                            System.out.println("队列满，等待有空余空间");
                            full.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);
                    empty.signal();
                } finally{
                    lock.unlock();
                }
            }
        }

    }

    public static void main(String[] args) {
        CustormerAndProducterByCondition cap = new CustormerAndProducterByCondition();
        Consumer cus = cap.new Consumer();
        Producer pro = cap.new Producer();
        Thread cusT = new Thread(cus);
        Thread proT = new Thread(pro);

        proT.start();
        cusT.start();
    }
}