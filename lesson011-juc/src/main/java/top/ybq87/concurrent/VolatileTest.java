package top.ybq87.concurrent;

/**
 * 利用 volatile 的即可可见性，将 2 个线程阻塞，获得资源就打印。flag 必须是可见性的
 *
 * 多线程编程要素：
 * 线程 操作 资源类
 * 判断 干活 通知
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/20
 */
public class VolatileTest {
    
    public static void main(String[] args) {
        VolatileResource resource = new VolatileResource();
        new Thread(() -> {
            for (int i = 0; i < 52; i = i + 2) {
                // 判断
                while (!resource.flag) {
                }
                // 干活
                System.out.print(i + "" + (i + 1));
                // 通知
                resource.flag = false;
            }
        }, "线程名称1").start();
        new Thread(() -> {
            for (int i = 0; i < resource.by.length; i++) {
                // 判断
                while (resource.flag) {
                }
                // 干活
                System.out.print(resource.by[i]);
                // 通知
                resource.flag = true;
            }
        }, "线程名称2").start();
    }
}

class VolatileResource {
    
    /**
     * 保证可见性，不保证原子性【一致性】
     */
    public volatile boolean flag = true;
    String[] by = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"
    };
    
}
