package top.ybq87;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/21
 */
public class MainClass {
    
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;
    
    public static void main(String[] args) {
        // // 线程安全的 ArrayList
        // List arrayList = Collections.synchronizedList(new ArrayList<>());
        // arrayList = new CopyOnWriteArrayList();
        // arrayList.add("2");
        // System.out.println(arrayList);
        System.out.println(COUNT_BITS);
        System.out.println(CAPACITY);
        
        // ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
        //         .setNameFormat("my-pool-%d").build();
        // ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
        //         0L, TimeUnit.MILLISECONDS,
        //         new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        // // 执行 thread
        // singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
        // singleThreadPool.submit()
        // // 关闭线程池
        // singleThreadPool.shutdown();
        
    }
}
