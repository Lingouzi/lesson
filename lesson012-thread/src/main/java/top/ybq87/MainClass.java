package top.ybq87;

/**
 * 线程的几个创建方式
 * 1、实现 runnable 接口，无返回
 * 2、实现 Callable 接口，有返回值
 *    2.1、futureTask.get() 方法要尽量放在方法的最后，因为会强制获取结果，如果没有，就等待，直到取得结果，所以会阻塞方法。
 *    2.2、对个接口对同一个 futuretask 竞争，只执行一个。
 * 3、executor 连接池
 *     3.1、Executors.newFixedThreadPool: 固定线程数，执行长期任务
 *     3.2、Executors.newSingleThreadExecutor:单个线程，任务一个一个执行的场景
 *     3.3、Executors.newCachedThreadPool: n 线程，动态扩容，适用于执行很多短小功能
 * 4、以几个上底层都是 ThreadPoolExecutor
 * 构造方法的 7 个参数讲解
 * ExecutorService singleThreadPool = new ThreadPoolExecutor(5, 10,
 *                 0L, TimeUnit.MILLISECONDS,
 *                 new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
 *     4.1、int corePoolSize,
 *         线程池中的常驻核心线程数，也就是会一直存在的线程数【直到线程池被关闭】，哪怕他不执行任何任务都会一直存在的。
 *         核心数线程满了，多出的先进入 queue 候客厅
 *     4.2、int maximumPoolSize,
 *         4.2.1、定义
 *             线程池能够容纳的同时执行的最大线程数，必须大于 1。因为线程池会依据请求数，在池中创建新的线程去处理任务，假设原来 corePoolSize = 5，
 *             但是此时来了 100 个任务，5 个线程不能很快的处理任务，线程池就扩容到一定的容量去执行任务，这里的 maximumPoolSize 就是限制它最多能扩容到多少。
 *             处理完毕后，假设线程数多于 5 个，那么多出的线程会销毁掉。
 *         4.2.2、扩容的时机
 *             当核心线程被占用满了，且候客区 queue 也满了，那么就启动创建新的线程到 maximumPoolSize 个去处理任务【先等待的先去处理】。
 *
 *     4.3、long keepAliveTime,
 *          多余的空闲线程的存活时间，当前线程池中的线程数量大于 corePoolSize 时【一般是进行了扩容后】，当空闲时间到达 keepAliveTime 时
 *          多余的空闲线程会被销毁，直到剩余 corePoolSize 个为止
 *
 *     4.4、TimeUnit unit,
 *          keepAliveTime 的时间单位
 *
 *     4.5、BlockingQueue<Runnable> workQueue,
 *         阻塞队列，相当于银行的候客区，因为核心 corePoolSize = 5，而设置了最大线程数为 maximumPoolSize = 10，所以只能同时处理 10 个任务，
 *         但是现在来了 100 个线程，那么剩余的 90 个任务就在 queue 等待，有线程空出，就进入线程处理。阻塞队列就是存放任务的。
 *         *** 不允许使用无界队列,LinkedBlockingQueue必须设置具体的值.
 *         因为如果设置了无界队列,核心线程数满了,会进入队列,又因为队列是无界的,所以最大线程数的设置不会生效.
 *
 *     4.6、ThreadFactory threadFactory,
 *         一般默认，线程工厂。设定一些线程创建的基本规则。
 *
 *     4.7、RejectedExecutionHandler handler
 *         拒接策略，在我们的 核心线程满了，maximumPoolSize 也满了，而且 queue 候客区也满了，handler 决定进来的请求怎么处理。
 *         有 4 个拒绝策略：
 *         4.7.1、AbortPolicy：
 *                抛出异常，阻止系统正常运行
 *         4.7.2、DiscardPolicy：
 *                直接丢弃任务,不处理也不报异常,
 *         4.7.3、CallerRunsPolicy
 *                调用者运行；一种调节机制，此策略不会抛弃任务，也不抛异常，而是将某些任务回退到调用者的线程去处理. 谁调用线程就找谁去执行.
 *         4.7.4、DiscardOldestPolicy
 *               丢弃队列中等待最久的任务.
 *
 * 任务来了 ---->   核心线程数满了   ---- 是 ---->  等待队列满了？   ---- 是 ---->  扩容线程满了 --- 是 ---> 拒绝策略
 *                      |                           |                           |
 *                      否                          否                          否
 *                      V                           V                           V
 *                    执行任务                     缓存任务                   扩容线程，执行任务
 * 5. 死锁
 *    5.1 概念:2 个或以上线程执行时,争夺资源,导致相互等待的现象.
 *    5.2 写代码
 *    5.3 排查
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/20
 */
public class MainClass {
    
    public static void main(String[] args) {
    }
}