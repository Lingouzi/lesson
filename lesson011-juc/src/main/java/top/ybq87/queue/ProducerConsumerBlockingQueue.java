package top.ybq87.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者，BlockingQueue 版本
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class ProducerConsumerBlockingQueue {
    
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println("producer start");
            myResource.put();
        }, "producer").start();
        new Thread(() -> {
            System.out.println("consumer start");
            myResource.pop();
        }, "consumer").start();
        
        try {
            TimeUnit.SECONDS.sleep(5);
            myResource.stop();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}

class MyResource {
    
    private volatile Boolean FLAG = true;
    
    private final AtomicInteger atomicInteger = new AtomicInteger();
    
    private BlockingQueue<String> queue = null;
    
    /**
     * 传接口，有 7 个实现类，因为不确定使用的人传递的是什么类型的 queue，兼容
     * @param queue
     */
    public MyResource(BlockingQueue<String> queue) {
        this.queue = queue;
        System.out.println(Thread.currentThread().getName() + " : " + queue.getClass().getName());
    }
    
    public void stop() {
        FLAG = false;
        System.out.println("大boss 宣布停止。");
    }
    
    public void put() {
        try {
            String data;
            boolean offer;
            while (FLAG) {
                data = atomicInteger.incrementAndGet() + "";
                offer = queue.offer(data, 2L, TimeUnit.SECONDS);
                if (offer) {
                    System.out.println("put success " + data);
                } else {
                    System.out.println("put fail");
                }
                TimeUnit.MILLISECONDS.sleep(500);
            }
            System.out.println("停止 生产");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void pop() {
        try {
            String poll;
            while (FLAG) {
                System.out.println(".........获取");
                poll = queue.poll(2L, TimeUnit.SECONDS);
                if (poll == null || "".equals(poll)) {
                    FLAG = false;
                    System.out.println("2s 没有新的内容了，退出");
                    // ？？为什么要用 return？
                    // 既然调用 stop 方法设置 flag 为 false，而且还是 volatile 的就说明这里会立即退出循环，执行最后的打印啊？
                    return;
                }
                System.out.println("消费:" + poll);
            }
            System.out.println("停止 消费");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}