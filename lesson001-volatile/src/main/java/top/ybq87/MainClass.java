package top.ybq87;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 没有被 volatile 修饰的
 */
class MyDataWithOutVolatile {
    
    Integer i = 0;
    
    public void add() {
        i = 2;
    }
}

/**
 * 被 volatile 修饰的，保证可见性测试
 */
class MyDataWithVolatile {
    
    volatile Integer i = 0;
    
    public void add() {
        i = 2;
    }
}

class MyDataTest {
    
    volatile int i = 0;
    
    /**
     * 为什么 atomic 能够保证原子性，因为底层是使用 unsafe 操作的。
     */
    AtomicInteger j = new AtomicInteger(0);
    
    public void addPlus() {
        i++;
        /*
        i++,的指令有 4 步，从主内存获取 i，然后加，然后写回主内存。
        GETFIELD top/ybq87/MyDataTest.i : I
        ICONST_1
        IADD
        PUTFIELD top/ybq87/MyDataTest.i : I
         */
    }
    
    public void addAtomic() {
        // j++，
        j.getAndIncrement();
    }
}

class SingletonWithOutVolatile {
    
    private static SingletonWithOutVolatile instance;
    
    public SingletonWithOutVolatile() {
        System.out.println("SingletonWithOutVolatile 我被创建");
    }
    
    public static SingletonWithOutVolatile getInstance() {
        if (instance == null) {
            instance = new SingletonWithOutVolatile();
        }
        return instance;
    }
}

/**
 * DCL, double check lock, 双端检索
 * 如果不加 volatile时，还可能出现问题的。
 * 我们分析下  instance = new SingletonDclWithOutVolatile(); 她可以分解为 3 步
 * 1、划分内存
 * 2、初始化对象
 * 3、将 instance 指向内存地址（此时 instance != null）
 * 这是正常的顺序，但是 2 和 3 没有数据依赖，可能出现指令重排优化。导致：
 * 1、3、2 的顺序执行。也就是说，在 instance 初始化之前将地址赋值给了他导致 instance!=null
 * 假设在 2 完成之前，另外一个线程抢到资源，它去使用了这个 instance，因为它不为 null，但是没有初始化，
 * 所以操作就会出错。
 *
 */
class SingletonDclWithOutVolatile {
    
    private SingletonDclWithOutVolatile() {
        System.out.println("SingletonDclWithOutVolatile 构建");
    }
    
    // 非禁止指令重排，可能出现 instance != null，但是没有初始化就被别的线程使用导致报错。
    // public static SingletonDclWithOutVolatile instance;
    
    // 多线程环境下 禁止指令重排
    public static volatile SingletonDclWithOutVolatile instance;
    
    public static SingletonDclWithOutVolatile getInstance() {
        if (instance == null) {
            synchronized (SingletonDclWithOutVolatile.class) {
                if (instance == null) {
                    instance = new SingletonDclWithOutVolatile();
                }
            }
        }
        return instance;
    }
}


/**
 * 1、验证 volatile 的可见性
 * 1.1、mydata 变量 i 不添加 volatile 时，线程调用 add 然后主线程调用 add，打印结果是 2，1.
 * 因为 main 线程不知道mydata 已经被修改了，她还是基于原来的 1 进行修改
 * 1.2、volatile 保证可见性
 *
 * 2、不保证原子性，以 i++ 为例
 * 2.1、原子性：某个操作，要么成功，要么失败，不可分割，不能被加塞。
 * 2.2、volatile 是否保证原子性：不保证
 * 2.3、为什么不保证？++ 操作是被分为 3 个步骤的。
 * 2.4、怎么解决原子性
 *     * sync 方法
 *     * atomic
 * 3、在哪里用到 volatile？
 * 3.1 单例，多线程下的单例
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/15
 */
public class MainClass {
    
    // 1. 不加 volatile 关键字，数据不保证可见性
    // public static void main(String[] args) {
    //     MyDataWithOutVolatile my = new MyDataWithOutVolatile();
    //     new Thread(() -> {
    //         my.add();
    //
    //     }).start();
    //     while (my.i == 0){
    //         // 会循环等待，i 变量没有volatile修饰，所以 main 线程不知道他被修改了，所以一直等待
    //     }
    //     System.out.println("main" + my.i);
    // }
    
    // 2. 有 volatile 关键字 保证可见性
    // public static void main(String[] args) {
    //     MyDataWithVolatile myData = new MyDataWithVolatile();
    //     new Thread(() -> {
    //         try {
    //             TimeUnit.SECONDS.sleep(2);
    //             myData.add();
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }).start();
    //     while (myData.i == 0) {
    //         // 等待 2 秒会退出因为 i 被 volatile 修饰，main 线程会实时看到她被修改。
    //     }
    //     System.out.println("main:" + myData.i);
    // }
    
    // 3. atomic 保证原子操作，多线程没问题
    // public static void main(String[] args) {
    //     MyDataTest myDataTest = new MyDataTest();
    //     for (int i = 0; i < 20; i++) {
    //         new Thread(() -> {
    //             for (int j = 0; j < 1000; j++) {
    //                 myDataTest.addPlus();
    //                 myDataTest.addAtomic();
    //             }
    //         }, "test:" + i).start();
    //     }
    //     // 得到结果，理论值是 20000，实际不到
    //     // 不能直接打印，因为可能线程没有执行完毕，取到的是中间的值。
    //     // System.out.println(myDataTest.i);
    //
    //     // 用下面的方法, 为什么是大于 2 呢？因为默认程序起 2 个线程，一个是 main 线程，一个是 gc 线程
    //     while (Thread.activeCount() > 2) {
    //         // 如果发现当前线程数大于 2，说明还有线程没有跑完，
    //         // 调用 yield 让出当前的 main 线程的资源占用，让其他线程继续跑。
    //         // 直到总线程 = 2，就会执行下面的打印方法。
    //         Thread.yield();
    //     }
    //     System.out.println(Thread.currentThread().getName() + ":" + myDataTest.i);
    //     System.out.println(Thread.currentThread().getName() + ":" + myDataTest.j);
    //
    // }
    
    // 4. 单线程下的 volatile 单例没有问题，多线程下出现异常。
    // public static void main(String[] args) {
    //     // 单线程单例没有错误。
    //     for (int i = 0; i < 10; i++) {
    //         System.out.println(SingletonWithOutVolatile.getInstacnce());
    //     }
    //     // 多线程模式下，构造器被调用多次，不是单例了。
    //     for (int i = 0; i < 10; i++) {
    //         new Thread(() -> {
    //             System.out.println(
    //                     Thread.currentThread().getName() + SingletonWithOutVolatile.getInstance());
    //         },"thread" + i).start();
    //     }
    // }
    
    // 5、dcl 测试
    public static void main(String[] args) {
        // 多线程模式下，虽然很多次不会出现问题，但是可能 99.999% 正常，
        // 但是因为存在指令重排，所以还是会出现问题
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(
                        Thread.currentThread().getName() + SingletonDclWithOutVolatile.getInstance());
            }, "thread" + i).start();
        }
    }
}
