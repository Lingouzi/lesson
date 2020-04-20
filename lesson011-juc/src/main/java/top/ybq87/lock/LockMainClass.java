package top.ybq87.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、公平锁和非公平锁？
 *    公平锁：队列，先进先出，按照申请锁的顺序来获取锁
 *    非公平：允许加塞，后面申请锁的线程比之前申请的线程能够优先获取锁，
 *           如果没有获取到，就进入公平锁
 *
 *    ReentrantLock lock = new ReentrantLock(); 默认非公平锁
 *    ReentrantLock lock = new ReentrantLock(false); 指定为公平锁
 *
 *    Synchronized：非公平锁
 *
 * 2、可重入锁（递归锁）
 * 定义：同一把锁，方法调用时可以自动获取到锁
 * ReentrantLock、Synchronized 就是可重入;
 * 作用：可避免死锁
 * 【假设定义了 2 次锁
 * lock.lock();
 * lock.lock();
 * try{}finaly{
 *     lock.unlock();
 *     lock.unlock();
 * }，这样正确嘛？
 * 结论：没错，只要加锁解锁配对，就可以一直加。
 * 】
 *
 * 3、自旋锁
 * 尝试获取所的线程不会立即阻塞，而是采取玄幻的方式去尝试获取锁。
 * 减少上下文切换消耗，但是消耗 cpu
 * 自写自旋锁，SpinLock
 *
 * 4、独占锁（写锁）、共享锁（读锁）、互斥锁
 * 独占：锁只能被一个线程所有，ReentrantLock 和 sync 都是独占锁
 * 共享：多个线程可以读，
 * 自写 缓存，测试读写锁：读可以多读，写只允许一个写。
 *
 *
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class LockMainClass {
    
    public static void main(String[] args) {
        Phone phone = new Phone();
        
        // 可重入锁。
        new Thread(() -> {
            phone.sendSms();
        }, "t1").start();
        new Thread(() -> {
            phone.sendSms();
        }, "t2").start();
    }
}

class Phone {
    
    public synchronized void sendSms() {
        System.out.println("send sms " + Thread.currentThread().getName());
        sendEmail();
    }
    
    public synchronized void sendEmail() {
        System.out.println("send email " + Thread.currentThread().getName());
    }
    
    Lock lock = new ReentrantLock();
    
    public void get() {
        lock.lock();
        try {
        
        } finally {
            lock.unlock();
        }
        
    }
}