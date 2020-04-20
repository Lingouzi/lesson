package top.ybq87.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class SynchronousQueueTest {
    
    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<>();
        
        // put 之后，只有当 take 之后，才能会继续 put，否则一直等待
        
        new Thread(() -> {
            try {
                System.out.println("put 了 1");
                queue.put("1");
                System.out.println("put 了 2");
                queue.put("2");
                System.out.println("put 了 3");
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadname").start();
        
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(queue.take() + " 取出");
                
                TimeUnit.SECONDS.sleep(2);
                System.out.println(queue.take() + " 取出");
                
                TimeUnit.SECONDS.sleep(2);
                System.out.println(queue.take() + " 取出");
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadname").start();
        
    }
}
