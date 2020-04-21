package top.ybq87.concurrent;

import java.util.Collections;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

/**
 * CycliBarrier 特性：
 * 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/20
 */
public class CyclicBarrierTest {
    
    /**
     * 将打印顺序存储到 list，保证写锁
     * 获取之后，打印就好了。
     */
    static CopyOnWriteArrayList list = new CopyOnWriteArrayList();
    
    static CyclicBarrier c1 = new CyclicBarrier(2, () -> {
        // TODO 因为得到的 list 顺序不一定正确，排个序
        Collections.sort(list);
        
        list.forEach(System.out::println);
        list.clear();
    });
    
    public static void main(String[] args) {
        CycliBarrierSource r = new CycliBarrierSource();
        new Thread(() -> {
            try {
                // TODO 这里要用 string 数组
                for (int i = 0; i < 52; i = i + 2) {
                    list.add(i);
                    list.add(i + 1);
                    c1.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }, "线程名称1").start();
        
        new Thread(() -> {
            try {
                for (int i = 0; i < r.by.length; i++) {
                    list.add(r.by[i]);
                    c1.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }, "线程名称2").start();
    }
}

/**
 * 资源类
 */
class CycliBarrierSource {
    
    String[] by = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"
    };
}
