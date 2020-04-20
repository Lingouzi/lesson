package top.ybq87.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、传统版的 生产者和消费者，生产一个消费一个。
 *
 * 线程               操作（方法）        资源类
 * 判断（必须用 while） 干活(实际业务)      通知
 * 防止虚假唤醒机制（多线程的判断用 while）
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class ProducerConsumerReentrantLock {
    
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.put();
                System.out.println(Thread.currentThread().getName() + " put ");
            }
        }, "aa").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.pop();
                System.out.println(Thread.currentThread().getName() + " pop ");
            }
        }, "bb").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.put();
                System.out.println(Thread.currentThread().getName() + " put ");
            }
        }, "cc").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.pop();
                System.out.println(Thread.currentThread().getName() + " pop ");
            }
        }, "dd").start();
    }
}

/**
 * 资源类
 */
class ShareData {
    
    /**
     * 掌握主要资源
     */
    private Integer number = 0;
    /**
     * 控制安全
     */
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    
    public void put() {
        // 负责生产面包
        lock.lock();
        try {
            // 1 判断，
            // 假设这里使用的是 if 方法判断，在多线程会出现问题。比如 main 方法有 4 个线程时。测试下。
            while (number != 0) {
                // 当前线程等待
                condition.await();
            }
            // 2 干活
            number++;
            
            // 3 通知唤醒，唤醒消费者消费面包
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            
        }
    }
    
    public void pop() {
        // 消费面包
        lock.lock();
        try {
            // 1 判断
            while (number == 0) {
                // 没有能够消费的，当前线程等待
                condition.await();
            }
            // 2 干活
            number--;
            
            // 3 唤醒生产者去生产面包
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            
        }
    }
    
}


