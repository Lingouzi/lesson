package top.ybq87.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 juc 包下的 lock，api 级别的锁，需要手动加锁和释放锁
 *
 * 多线程步骤要点
 * 线程 方法 资源类
 * 判断 干活 唤醒
 * 防止虚拟唤醒
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/20
 */
public class ReentrantLockTest {
    
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    
    public static void main(String[] args) {
        
        ReentrantLockResource resource = new ReentrantLockResource();
        new Thread(() -> {
            // 一次步进 2
            try {
                for (int i = 0; i < 52; i = i + 2) {
                    lock.lock();
                    try {
                        // 判断
                        if (!resource.flag) {
                            condition.await();
                        }
                        // 干活
                        System.out.print(i + "" + (i + 1));
                        resource.flag = false;
                        // 唤醒
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程名称1").start();
        
        new Thread(() -> {
            // 一次步进 2
            try {
                for (int i = 0; i < resource.str.length; i++) {
                    lock.lock();
                    try {
                        // 判断
                        if (resource.flag) {
                            condition.await();
                        }
                        // 干活
                        System.out.print(resource.str[i]);
                        resource.flag = true;
                        // 唤醒
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程名称2").start();
    }
}

/**
 * 资源类
 */
class ReentrantLockResource {
    
    public boolean flag = true;
    
    String[] str = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"
    };
}