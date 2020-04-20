package top.ybq87.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程按照顺序调用， 实现 a，b，c 三个线程按照如下
 * a 5 次，b 15 次，c 8 次。
 * 轮询 10 次
 *
 * 多线程开发口诀：
 * 线程 操作 资源类
 * 判断 干活 通知
 * 防止虚假唤醒
 *
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class SynchronizedAndReentrantLockTest {
    
    public static void main(String[] args) {
        ResourceData resourceData = new ResourceData();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resourceData.print5();
            }
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resourceData.print15();
            }
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resourceData.print8();
            }
        }, "c").start();
    }
}

/**
 * 资源类
 */
class ResourceData {
    
    private Integer number = 1;
    
    private Lock lock = new ReentrantLock();
    
    /**
     * 3  把钥匙
     */
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    
    public void print5() {
        lock.lock();
        try {
            try {
                //1. 判断
                while (number != 1) {
                    c1.await();
                }
                //2. 干活
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " ... " + i);
                }
                //3. 通知
                number = 2;
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }
    
    public void print15() {
        lock.lock();
        try {
            try {
                //1. 判断
                while (number != 2) {
                    c2.await();
                }
                //2. 干活
                for (int i = 0; i < 15; i++) {
                    System.out.println(Thread.currentThread().getName() + " ... " + i);
                }
                //3. 通知
                number = 3;
                c3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }
    
    public void print8() {
        lock.lock();
        try {
            try {
                //1. 判断
                while (number != 3) {
                    c3.await();
                }
                //2. 干活
                for (int i = 0; i < 8; i++) {
                    System.out.println(Thread.currentThread().getName() + " ... " + i);
                }
                //3. 通知
                number = 1;
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }
    
}