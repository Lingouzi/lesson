package top.ybq87;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 1. 什么是垃圾? 怎么判断是垃圾? 什么是  GC Root?
 * 从 gc root 的对象作为起点, 从它开始向下搜索,如果一个对象链路可达,表示活跃,否则就是是垃圾;
 * 什么是 gc root 对象:
 *     1.1 虚拟机栈(栈帧中的局部变量去, 局部变量)中的引用的对象; 但是栈回收之后,消失
 *     1.2 方法区中类的静态属性引用的对象; 被 static 修饰的 class 中的变量,一旦 class 加载旧一直存在
 *     1.3 方法区常量引用的对象;
 *     1.4 本地方法栈 native 方法引用的对象
 *
 * 2、jvm 参数默认查看,jvm 调优,如何盘点 jvm 系统默认
 *     2.1 jvm 参数类型:
 *         2.1.1 标配参数 :-version,-help 等
 *         2.1.2 x 参数 : -Xint [解释执行],-Xcomp [第一次使用就编译成本地代码], -Xmixed [混合模式,先编译后执行],
 *         2.1.3 xx 参数 :
 *             2.1.3.1 Boolean 类型, -XX:+或者-某个属性,开启或者关闭特性
 *                      jinfo: 查看正在运行的 java 程序的属性, jinfo -flag PrintGCDetails pid :打印 PrintGCDetails 属性信息
 *             2.1.3.2 KV 设置类型
 *                      公式: -XX:属性 Key=属性值 value;
 缩写参数: -Xms1024m = -XX:InitizeHeapSize; -Xmx1024m = -XX:Max;
 jvm 常用参数:
 -Xms1024m: 设置 jvm 初始内存,可以与 xmx 相同,避免垃圾回收后重新分配内存. 默认为物理内存的 1/64;等价于 -XX:InitialHeampSize
 -Xmx1024m: 设置 jvm 最大可用内存 默认为物理内存的 1/4;等价于-XX:MaxHeapSize
 -Xmn512m: 设置年轻代大小为 512m,
 整体 jvm 内存 = 年轻代+老年代+持久代[持久代固定为 64m]
 1024m         = 512m + 老年 + 64m,推算出老年代
 -Xss128k: 设置每个线程的堆栈大小, 一般为 512k~1024k;-XX:ThreadStackSize
 -XX:MetaspaceSize : 设置元空间大小,元空间本质和永久代类似,都是对 jvm 规范中方法区的实现.
 元空间并不在虚拟机中,而是使用本地内存
 *
 * 3、gc 日志阅读
 * 使用 System.gc() 之后打印这个
 * [GC (System.gc()) [PSYoungGen: 2662K->496K(38400K)] 2662K->504K(125952K), 0.0115382 secs] [Times: user=0.00 sys=0.00, real=0.02 secs]
 * [Full GC (System.gc()) [PSYoungGen: 496K->0K(38400K)] [ParOldGen: 8K->402K(87552K)] 504K->402K(125952K), [Metaspace: 3087K->3087K(1056768K)], 0.0127579 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
 *      标识 gc        [年轻代:大小从496K清理为0k(一共 38400K)][老年代:8k涨到 402k(一共87552K)] heap区大小(总大小)    [元空间大小:基本不变(总大小1g)]            耗时
 * Heap
 *  PSYoungGen      total 38400K, used 333K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
 *   eden space 33280K, 1% used [0x0000000795580000,0x00000007955d34a8,0x0000000797600000)
 *   from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 *   to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 *  ParOldGen       total 87552K, used 402K [0x0000000740000000, 0x0000000745580000, 0x0000000795580000)
 *   object space 87552K, 0% used [0x0000000740000000,0x0000000740064bc8,0x0000000745580000)
 *  Metaspace       used 3094K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 339K, capacity 388K, committed 512K, reserved 1048576K
 *
 * 4、强/软/弱/虚 引用
 *    4.1 应用: 读取图片的时候, gc 时回收图片信息.
 *    4.2
 *    强引用[95%] Reference: 当内存不足,jvm 开始回收内存,对于强引用的对象, 出现 oom 也不会对该对象进行回收,死都不回收.
 *    软引用 SoftReference: 内存足够不回收,不够了回收,尽量保证不发生 oom
 *    弱引用 WeakReference: 只要遇到 gc,就回收
 *    虚引用:
 *    4.3 WeakHashMap
 * 5、OOM 错误，【非异常】
 *      Error：一般是指虚拟机相关的问题，如系统崩溃、虚拟机错误、内存空间不足，方法调用栈溢出，这类错误导致应用程序中断；
 *      Exception：标识程序可以处理的异常，可以捕获且可能恢复，不应该导致程序终止。
 *          运行时异常（Runtime Exception）：编译不错，但是跑起来异常，程序会终止
 *          受检查的异常（Checked Exception）：要么用 try 捕获，要么同 throws 抛出，交给父类，否则编译不通过。
 * 5.1、StackOverflowError：
 *      方法深度调用【迭代】，导致栈异常。
 * 5.2、OutOfMemoryError：java heap space
 *      堆内存爆了，申请大内存数据，byte[10* 1024*1024],
 * 5.3、OutOfMemoryError：GC overhead limit exceeded
 *      gc 回收时间过长时抛出
 * 5.4、OutOfMemoryError：Direct buffer memory
 * 5.5、OutOfMemoryError：unable to create new native thread
 * 5.6、OutOfMemoryError：Metaspace
 *
 * 6、生产环境服务器变慢，诊断思路和性能评估？
 * 6.1、整机性能 top，看 cpu 和内存，load average：1，5，15 分钟负载，平均值大于 60%，说明系统负担重
 *      uptime ：简化命令
 * 6.2、cpu：vmstat -n 2 3 查看 cpu
 *      mpstat -P ALL 2 所有 cpu 进程数
 *      pidstat
 * 6.3、free -m :单位为 m
 * 6.4、iostat：磁盘 io，最后一个参数看磁盘率
 * 6.5、ifstat：网络 io
 *
 * 7、生产环境 cpu 占用过高，怎么定位
 * 7.1、top 找到占用高的 pid
 * 7.2、jps 或者 ps -ef，找到程序
 * 7.3、定位线程或者代码，哪一行： ps -mp pid -o THREAD,tid,time 找到线程 id
 * 7.4、线程 id 转为 16 进制格式：printf "%x\n" 线程id
 * 7.5、jstack pid | grep tid -A60，打印前 60 行
 * 7.6、找到问题。。
 *
 * 8、
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/21
 */
public class MainClass {
    
    static String s0 = "0123456789";
    static String s1 = "0123456789";
    
    public static void main(String[] args) {
        String s2 = s1.substring(5);
        String s3 = new String(s2);
        String s4 = new String(s3.toCharArray());
        s0 = null;
        // 加或者不加下面一句，堆区剩余的字符个数多少？
        // s2 = null;
        System.gc();
        
        // try {
        //     System.in.read();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
}
