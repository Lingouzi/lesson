package top.ybq87.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 编写两个线程，一个线程打印1~25，另一个线程打印字母A~Z，打印顺序为12A34B56C……5152Z，要求使用线程间的通信。
 *
 * 思路：使用 Synchronized 关键字，和基本的 wait、notify 方法
 *
 * 解法：
 * 1、2 个线程，一个线程打印字母，一个线程打印数字，
 * 2、打印数字的，打2 次之后，数字线程 wait，唤醒字母线程
 * 3、打印 1 个字母，字母线程暂停，数字线程唤醒
 * 4、直到打印完毕。
 *
 * 多线程编写的规则：
 * 线程 操作 资源类
 * 判断 干活 通知
 * 注意虚假唤醒
 *
 * 总结下，想要执行某个对象的notify(), notifyAll(),wait(), wait(long), wait(long, int)方法就必须获取该对象的锁，
 * 需要使用 synchronized ，不然就会抛出 IllegalMonitorStateException异常
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/20
 */
public class SynchronizedTest {
    
    static AtomicInteger atomic = new AtomicInteger();
    
    public static void main(String[] args) {
        
        SynchronizedData data = new SynchronizedData();
        
        new Thread(() -> {
            // 打印的是数字
            try {
                for (int i = 1; i <= 52; i = i + 2) {
                    synchronized (data) {
                        // 判断
                        if (!data.flag) {
                            data.wait();
                        }
                        // 干活
                        System.out.print(i + "" + (i + 1));
                        data.flag = false;
                        // 通知
                        data.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程名称 num").start();
        
        new Thread(() -> {
            // 打印的是数字
            try {
                for (int i = 0; i < data.by.length; i++) {
                    synchronized (data) {
                        // 判断
                        if (data.flag) {
                            data.wait();
                        }
                        // 干活
                        System.out.print(data.by[i]);
                        data.flag = true;
                        // 通知
                        data.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程名称 by").start();
        
        
    }
}

/**
 * 资源类
 */
class SynchronizedData {
    
    public boolean flag = true;
    
    String[] by = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"
    };
    
    
}
