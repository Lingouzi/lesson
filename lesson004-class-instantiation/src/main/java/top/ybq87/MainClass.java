package top.ybq87;

/**
 * 类的初始化、实例化、静态代码块、构造器、等知识
 * 考点：
 * 1、类初始化过程
 * 1.1、一个类要创建实例，需要先加载并初始化该类
 *     1.1.1、main 方法所在的类需要先加载和初始化
 * 1.2、子类初始化需要先将父类初始化
 * 1.3、一个类的初始化就是执行 <clinit>() 方法，每个类都有这个方法，字节码。且是线程安全的，
 *     1.3.1、<clinit>() 方法由静态类变量显式赋值代码【注意变量还没创建】和静态代码块组成。【包含且只包含这些。】
 *     1.3.2、类变量显式赋值代码和静态代码块从上到下顺序执行，谁在前谁先执行
 *     1.2.3、<clinit>() 方法只执行一次。
 *
 * 2、实例初始化, 调用 <init>() 方法
 *      2.1、可能有多个，几个构造方法就有几个 init 方法，可能有参，可能无参
 *      2.2、由非静态实例变量显式赋值代码、非静态代码块、对应构造器代码组成
 *      2.3、构造器最后执行，另外 2 个谁在上面谁先执行
 *      2.4、每 new 一次对象，调用对应的构造器，就是执行对应的<init>()方法
 *      2.5、init 方法首行是 super()或者 super(参数), 代表要先执行父类的 <init>() 方法
 *
 * 3、方法重写，什么情况下会有重写
 *   3.1、不被重写的方法有：
 *      3.1.1、final 方法
 *      3.1.2、静态方法
 *      3.1.3、private 等子类中不可见的方法
 *   3.2、对象的多态
 *      3.2.1、子类如果重写了父类的方法，通过子类对象调用的一定是子类重写过的代码
 *      3.2.2、非静态方法默认的调用对象是 this
 *      3.2.3、this 对象在构造器或者说 <init> 方法中就是正在创建的对象
 *   3.3、方法的重写
 *      非静态方法前面其实有一个 this 对象，被隐藏了。
 *      this 在构造器（或 <init>）它表示的是正在创建的对象，因为这里是在创建 son，所以
 *      父类中 test() 执行的是子类重写的代码（面向对象的多态）
 *      i = test(); ==> i = this.test(); // 这里的 this 指的是 son
 * 4、super 和 this 关键字
 *      4.1、this 关键字和 super 不能一起使用
 *          按照官方的定义，this()调用构造器方法必须放在构造器的第一行，但是 super 也必须放在第一行，所以 2 个关键字调用不允许出现在同一个方法中
 *          且都不能出现多次。
 *      4.2、继承关系下的 this 关键字，子类中的 this 还是子类本身，但是父类中如果用到了 this，就有一定的区别
 *          注意前提：
 *              【前提是我们使用子类进行实例化一个对象，而不是直接实例化父类时。】
 *              【分析父类中的 this 的作用】
 *          4.2.1、this(1,2); 访问的是本类的其他构造方法，无论子类是否有相同参数的构造方法
 *          4.2.2、this.paramName; 可以访问类中的成员变量，在父类中使用 this.param 访问的始终是父类的成员变量
 *          4.2.3、this.func(params..);访问类中的成员方法时
 *              4.2.3.1、子类重写了父类方法，那么这里就是调用的子类的方法。
 *              4.2.3.2、子类没有重写，那么这里调用的还是父类自己的方法。
 *          4.2.4、this; 当前类【或者叫运行时类型】对象的引用
 *              4.2.4.1、这个怎么理解，比如在父类的构造器 System.out.println(this); 那么这个 this 其实是指向的子类。
 *              4.2.4.2、或者 System.out.println(this.getClass()); this 也是子类。
 *
 *      4.3、super和this的异同
 *          super 关键字的作用是在于当子类覆盖了父类的某个成员变量或者方法，还想要访问到父类的这个变量或者方法时，用 super
 *          super在一个类中用来引用其父类的成员，它是在子类中访问父类成员的一个桥梁，并不是任何一个对象的引用，
 *          而this则表示当前类对象的引用。在代码中Object o = super;是错误的，Object o = this;则是允许的。
 *      4.4、super.getClass() 和 this.getClass()
 *          java 中 getClass() 方法返回的是该对象的运行时类，那么什么叫对象的运行时类？
 *          一个对象的运行时类，简单讲就是是该对象通过 new 关键字被创建时指定的类。
 *          比如 Son s1 = new Son();那么 Son 就是 对象的运行时类
 * 5、编译时类型和运行时类型
 *
 * 6. 题目
 * Student s = new Student(); 做了哪些事情:
 * 6.1 加载 Student.class 文件到内存
 * 6.2 在栈内存为 s 开辟空间
 * 6.3 在堆内存为 student 对象开辟空间
 * 6.4 student 成员变量进行默认初始化
 * 6.5 student 成员变量进行显示初始化
 * 6.6 构造方法对 student 对象成员变量赋值
 * 6.7 student 初始化完毕,将对象地址赋值给变量 s
 *
 * 参考：
 * 1、this 关键字：https://blog.csdn.net/ccpat/article/details/44515335
 * 2、super 和 this：https://bbs.csdn.net/topics/370010127
 * 3、编译时类型和运行时类型：https://blog.csdn.net/u014207606/article/details/53363986
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/16
 */
public class MainClass {
    
    public static void main(String[] args) {
        Father son1 = new Son();
        System.out.println(son1.getClass().getName());
        
        Son s1 = new Son();
        System.out.println();
        Son s2 = new Son();
    }
}
