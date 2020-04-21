package top.ybq87.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 阻塞队列,
 *
 * 多线程编程口诀
 * 线程 操作 资源类
 * 判断 干活 后通知
 *
 *
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/20
 */
public class BlockingQueueTest {
    
    public static void main(String[] args) {
        BlockingQueueResource resource = new BlockingQueueResource(new LinkedBlockingDeque<String>());
        new Thread(() -> {
            for (int i = 0; i < 52; i = i + 2) {
                //1. 因为是 01 先打印01，所以这里打印
                //11. i+2 了，现在打印23.。。
                System.out.print(i + "" + (i + 1));
                //2. 然后压一个 2 进入，激活字符的打印
                resource.queue.offer("2");
                //3. 本线程阻塞在这里，因为顶部是 2
                //9. 队列顶部是 1，破坏循环，下一条语句执行
                while (!"1".equals(resource.queue.peek())) {
                }
                //10. 弹出 1，然后进入下一个 for 循环
                resource.queue.poll();
            }
        }, "线程名称1").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < resource.str.length; i++) {
                    //0.线程启动，判定顶部不是 2 所以循环
                    //5.循环到某个时间点，顶部为 2，跳出循环了
                    while (!"2".equals(resource.queue.peek())) {
                    }
                    //6.弹出 2，队列为空，为了下一步打印数字准备
                    resource.queue.poll();
                    //7. 然后打印字符
                    System.out.print(resource.str[i]);
                    //8. 将 1 压入，激活数字的打印【让他跳出循环】
                    resource.queue.offer("1");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "线程名称1").start();
        
        
    }
}

/**
 * 资源类
 */
class BlockingQueueResource {
    
    String[] str = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"
    };
    
    BlockingQueue<String> queue;
    
    public BlockingQueueResource(BlockingQueue<String> queue) {
        this.queue = queue;
    }
}


