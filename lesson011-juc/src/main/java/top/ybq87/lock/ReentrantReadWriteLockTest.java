package top.ybq87.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁，只允许一个写，允许多个读
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class ReentrantReadWriteLockTest {
    
    /**
     * 1、使用 map 存储数据，
     * 2、使用一个锁，允许多读，不允许多写
     */
    
    private static volatile Map<String, Object> map = new HashMap<>();
    /**
     * 如果不加 读写锁，会导致 多些多读
     */
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    public static void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写数据 :" + key);
            map.put(key, value);
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName() + "写数据 end >>>>>");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public static Object get(String key) {
        lock.readLock().lock();
        Object o;
        try {
            o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读数据:" + o);
        } finally {
            lock.readLock().unlock();
        }
        return o;
    }
    
    public static void main(String[] args) {
        ReentrantReadWriteLockTest myChache = new ReentrantReadWriteLockTest();
        
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myChache.put(finalI + "", finalI + "");
            }, "t1" + i).start();
        }
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                Object o = myChache.get(finalI + "");
            }, "t2" + i).start();
        }
    }
}
