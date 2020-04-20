package top.ybq87;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 1、集合类的不安全问题
 * 1.1、ArrayList：new ArrayList() 的时候，创建了一个默认的空的初始值为 10 的数组。
 *      add 的时候初始化和扩容（如果有需要）
 * 1.2、HashSet：底层是 hashMap，但是只用它的key 作为存储，value 使用 present 常量，都一样
 * 2、导致原因
 * 并发导致资源修改同时进行，导致修改异常
 * 3、解决方案
 * 3.1、Vector，加锁，但是效率不高
 * 3.2、Collections.synchronizedList(new ArrayList<>());
 * 3.3、new CopyOnWriteArrayList<>(); 写时复制，
 *      3.3.1、就是在修改数据（数组）的时候，拿到修改权限了，操作加锁 ReentrantLock.lock()
 *      3.3.2、然后得到数据数组，新建一个长度+1 的数组：Object[] newElements = Arrays.copyOf(elements, len + 1);
 *      3.3.3、然后将数据加到最后一位：newElements[len] = e;
 *      3.3.4、将原来数组引用指向新的数组
 *      3.3.5、释放锁。
 * 3.4、new CopyOnWriteArraySet<>()，底层是 CopyOnWriteArrayList
 * 3.5、
 * 4、优化
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class MainClass {
    
    public static void main(String[] args) {
        //1、 arraylist 的不安全测试
        List<String> list = new CopyOnWriteArrayList<>();
        // 多线程同时修改，循环多了之后报错：java.util.concurrentModifyExc..
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }, "threadname").start();
        }
        
        //2、原因
        
        //3、解决
        
        //3.1 线程安全的
        List<String> list2 = new Vector<>();
        //3.2 封装一层
        List<String> list3 = Collections.synchronizedList(new ArrayList<>());
        //3.3 写时复制
        List<String> lust4 = new CopyOnWriteArrayList<>();
        //4、优化
        
    }
}
