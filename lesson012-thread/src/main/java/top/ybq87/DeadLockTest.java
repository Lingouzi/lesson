package top.ybq87;

import java.util.concurrent.TimeUnit;

/**
 * 死锁测试,解决
 *
 * 排查
 * 1. jps -l 列出当前的 java 线程,找到 pid
 * 2. jstack pid 找到异常
 *
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/21
 */
public class DeadLockTest {
    
    public static void main(String[] args) {
        final String lockA = "lockA";
        final String lockB = "lockB";
        new Thread(new HoldThread(lockA, lockB), "线程名称1").start();
        new Thread(new HoldThread(lockB, lockA), "线程名称2").start();
    }
}

class HoldThread implements Runnable {
    
    final String lockA;
    final String lockB;
    
    public HoldThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    
    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " 持有 " + lockA + " ,希望获取 " + lockB);
            try {
                // 延迟,模拟等待资源,
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + " 持有 " + lockB + " ,希望获取 " + lockA);
            }
        }
    }
}