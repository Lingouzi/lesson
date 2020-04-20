package top.ybq87.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写自旋锁
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class SpinLock {
    
    AtomicReference<Thread> atomic = new AtomicReference<>();
    
    public void lock() {
        // 得到锁
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "得到 lock");
        while (!atomic.compareAndSet(null, thread)) {
            System.out.println(thread.getName() + " try to get lock");
        }
    }
    
    public void unlock() {
        System.out.println(Thread.currentThread().getName() + "释放 lock");
        atomic.compareAndSet(Thread.currentThread(), null);
    }
    
    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        
        new Thread(() -> {
            try {
                spinLock.lock();
                TimeUnit.SECONDS.sleep(3);
                System.out.println("a 执行操作");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
            }
        }, "a").start();
        
        try {
            // 保证 a 已经执行，然后 b 才开始执行，测试 b 是否能够得到锁
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        new Thread(() -> {
            try {
                spinLock.lock();
                TimeUnit.SECONDS.sleep(1);
                System.out.println("b 执行操作");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
            }
        }, "b").start();
    }
    
}
