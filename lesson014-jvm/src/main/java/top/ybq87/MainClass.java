package top.ybq87;

/**
 * 1. 什么是垃圾? 怎么判断是垃圾? 什么是  GC Root?
 * 从 gc root 的对象作为起点, 从它开始向下搜索,如果一个对象链路可达,表示活跃,否则就是是垃圾;
 * 什么是 gc root 对象:
 *     1.1 虚拟机栈(栈帧中的局部变量去, 局部变量)中的引用的对象; 但是栈回收之后,消失
 *     1.2 方法区中类的静态属性引用的对象; 被 static 修饰的 class 中的变量,一旦 class 加载旧一直存在
 *     1.3 方法区常量引用的对象;
 *     1.4 本地方法栈 native 方法引用的对象
 *
 * 2. jvm 参数默认查看,jvm 调优,如何盘点 jvm 系统默认
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
 -Xmx1024m: 设置 jvm 最大可用内存
 -Xms1024m: 设置 jvm 初始内存,可以与 xmx 相同,避免垃圾回收后重新分配内存.
 -Xmn512m: 设置年轻代大小为 512m,
 整体 jvm 内存 = 年轻代+老年代+持久代[持久代固定为 64m]
 1024m         = 512m + 老年 + 64m,推算出老年代
 -Xss128k: 设置每个线程的堆栈大小
 -XX:NewRatio=4:
 *
 * 3.
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/21
 */
public class MainClass {
    
    public static void main(String[] args) {
        System.out.println("hello");
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
