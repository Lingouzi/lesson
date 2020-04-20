package top.ybq87;

import java.util.concurrent.CountDownLatch;

/**
 * 控制关键步骤最后执行的时候，
 * 比如锁门的 demo，
 * 自习室有人自习，班长最后走锁门
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class CountDownLatchTest {
    
    public static void main(String[] args) {
        // 如果不使用 CountDownLatch，线程执行有先后顺序，虽然班长在主线程，但是也可能先执行完。
        CountDownLatch countDownLatch = new CountDownLatch(6);
        
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("学生" + finalI + "走了");
                // 计数 -1
                countDownLatch.countDown();
            }, "threadname").start();
        }
        try {
            // 等待计数为 0，结束 await。主线程唤醒继续执行。
            countDownLatch.await();
            System.out.println("班长最后走");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
