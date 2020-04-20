package top.ybq87;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 以占用车位为例，只有 3 个车位，但是有 10 个线程。他们去竞争这 3 个位置，最终都执行完毕。
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class SemaphoreTest {
    
    public static void main(String[] args) {
        // 1、同时只允许 3 个线程跑
        Semaphore semaphore = new Semaphore(3);
        
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    // 2、拿到锁资源
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " : 占到这位");
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.println(Thread.currentThread().getName() + " : 释放车位");
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 3、释放锁资源
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
        
        
    }
    
}
