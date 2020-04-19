package top.ybq87;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 1、定义，什么是 CAS？
 *      compareAndSwap，比较交换
 * 2、底层原理？
 * AtomicInteger.getAndIncrement()方法 为什么能保证原子性
 * 底层是 unsafe 类
 * 查看 atomicinteger 的源码 ：
 *     private static final Unsafe unsafe = Unsafe.getUnsafe();
 *     private static final long valueOffset;
 *
 *     static {
 *         try {
 *             valueOffset = unsafe.objectFieldOffset
 *                 (AtomicInteger.class.getDeclaredField("value"));
 *         } catch (Exception ex) { throw new Error(ex); }
 *     }
 *
 *     private volatile int value;
 * 在对象初始化时，获取到了值和偏移量，利用 unsafe 的 native 方法直接操作内存
 * unsafe 的 cas 方法是jvm 帮我们实现出 cas 汇编指令，直接操作硬件，操作系统级别保证原子性。
 *
 * 3、unsafe 底层汇编
 *
 * 4、CAS 缺点
 *     4.1、循环时间长，cpu 开销大：因为是死循环
 *     4.2、只能保证一个共享变量的原子性，多个的只能加锁
 *     4.3、aba 问题
 *
 * 5、什么是 ABA 问题？原子引用更新？如何规避 aba 问题
 *     5.1、ABA 的产生：。。。
 *     5.2、原子引用：jdk 默认提供了 AtomicInteger ，但是我们如果想要原子的 user，order，product？
 *     可以使用： AtomicReference<User> ..
 *     5.3、解决 aba 问题？
 *          加入 version，每次修改 version + 1，那么我们不止要对比值，还要对比 version。
 *          juc 提供了 AtomicStampedReference 方法，解决 aba 问题。
 *
 *
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class MainClass {
    
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(10);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);
    
    
    public static void main(String[] args) {
        
        //
        AtomicInteger integer = new AtomicInteger(3);
        boolean compareAndSet = integer.compareAndSet(2, 2);
        System.out.println(compareAndSet);
        
        // 原子引用
        User tom = new User("tom");
        User jack = new User("jack");
        AtomicReference<User> atomicUser = new AtomicReference<>();
        atomicUser.set(tom);
        
        boolean b1 = atomicUser.compareAndSet(tom, jack);
        boolean b2 = atomicUser.compareAndSet(tom, jack);
        System.out.println(b1 + " " + b2);
        
        // 解决 aba 问题
        
        // 产生：
        new Thread(() -> {
            // 先改为 11，然后从 11 改为 10
            atomicReference.compareAndSet(10, 11);
            atomicReference.compareAndSet(11, 10);
        }, "t1").start();
        
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                // 保证 t1 执行后，再去修改。
                System.out.println(Thread.currentThread().getName() + atomicReference.compareAndSet(10, 12) + "\t"
                        + atomicReference.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
        
        // 解决 aba
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " version:" + stamp);
            atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + " version:" + atomicStampedReference.getStamp());
        }, "t3").start();
        
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " version:" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
                boolean b = atomicStampedReference.compareAndSet(100, 222, stamp, stamp + 1);
                System.out.println(Thread.currentThread().getName() + " cas:" + b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }, "t4").start();
    }
    
    
}

class User {
    
    private String name;
    
    public User(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}