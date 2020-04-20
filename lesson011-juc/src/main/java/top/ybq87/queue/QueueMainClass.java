package top.ybq87.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 1、队列
 *
 * 2、阻塞队列
 * 2.1、阻塞队列有么有好的一面，（海底捞，有休息室，不能进餐的就在这里候着，就是阻塞，店家肯定是希望阻塞的）
 *
 * 2.2、不得不阻塞，如何管理，（去医院，排队看病，不能不堵。）
 *
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class QueueMainClass {
    
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        // 超时阻塞
        try {
            System.out.println(queue.offer("1", 2L, TimeUnit.SECONDS));
            System.out.println(queue.offer("2", 2L, TimeUnit.SECONDS));
            System.out.println(queue.offer("3", 2L, TimeUnit.SECONDS));
            System.out.println(queue.offer("4", 2L, TimeUnit.SECONDS));
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // // 一直等待阻塞
        // try {
        //     queue.put("1");
        //     queue.put("2");
        //     queue.put("3");
        //     // 超出大小的插入，如果队列没位置，就一直等待插入
        //     queue.put("4");
        //
        //     // 同理 take，等待取。
        //     queue.take();
        //
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        
        // 非异常组 offer、poll
        // System.out.println(queue.offer("11"));
        // System.out.println(queue.offer("12"));
        // System.out.println(queue.offer("13"));
        // // 多加的，超过队列大小的，不报错，返回 false
        // System.out.println(queue.offer("14"));
        //
        // // 当前head 的元素，没有不报错
        // System.out.println("当前元素" + queue.peek());
        //
        // // 没有返回 null
        // System.out.println(queue.poll());
        // System.out.println(queue.poll());
        // System.out.println(queue.poll());
        // System.out.println(queue.poll());
        
        // 异常组 api
        // BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        // System.out.println(queue.add("1"));
        // System.out.println(queue.add("2"));
        // System.out.println(queue.add("3"));
        // // 因为队列只有 3 个位置，加入更多时：java.lang.IllegalStateException: Queue full
        // // System.out.println(queue.add("4"));
        //
        // // 返回队列的第一个元素，但是如果队列是空的，那么就报错：java.util.NoSuchElementException
        // System.out.println(queue.element());
        //
        // System.out.println(queue.remove());
        // System.out.println(queue.remove());
        // System.out.println(queue.remove());
        // // 取出超过限制大小的：java.util.NoSuchElementException
        // // System.out.println(queue.remove());
        
    }
}
