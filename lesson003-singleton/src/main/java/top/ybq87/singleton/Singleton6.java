package top.ybq87.singleton;

/**
 * 线程安全，效率高
 *
 * 内部类被加载初始化时，才创建 instance,
 * 如果静态内部类不会随着外部类的加载和初始化而初始化，只在被用到的时候，才初始化。
 * 所以只有在用到它的时候，才初始化。
 *
 * 外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化INSTANCE，故而不占内存。
 * 即当SingleTon第一次被加载时，并不需要去加载SingleTonHoler，只有当getInstance()方法第一次被调用时，才会去初始化INSTANCE,
 * 第一次调用getInstance()方法会导致虚拟机加载SingleTonHoler类，这种方法不仅能确保线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化。
 *
 * 那么，静态内部类又是如何实现线程安全的呢？首先，我们先了解下类的加载时机。
 * JAVA虚拟机在有且仅有的5种场景下会对类进行初始化。
 * 1、遇到 new、getstatic、setstatic、invokestatic 这 4 个字节码指令时会去对类进行初始化。
 *  1.1、new 实例化一个对象时；
 *  1.2、读取或者设置一个静态字段时；但是如果这个字段被 final 修饰了，那么就不会去进行类初始化，因为 final 的已经在常量池了。
 *  1.3、调用一个类的静态方法时；
 * 2、使用反射创建时，如果类没有进行初始化，就会先去初始化
 * 3、初始化一个类，当它的父类还没有初始化时，会先触发父类的初始化
 * 4、虚拟机启动时，需要指定一个要执行的主类（main 方法的类），虚拟机会先初始化这个类
 * 5、当使用JDK 1.7等动态语言支持时，如果一个java.lang.invoke.MethodHandle 实例最后的解析结果
 * REF_getStatic、REF_putStatic、REF_invokeStatic 的方法句柄，并且这个方法句柄所对应的类没有进行过初始化，则需要先触发其初始化。
 *
 * 虚拟机会保证一个类的<clinit>()方法在多线程环境中被正确地加锁、同步。
 *
 * 参考：
 * https://blog.csdn.net/mnb65482/article/details/80458571
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/16
 */
public class Singleton6 {
    
    private Singleton6() {
    }
    
    private static class Inner {
        
        public static final Singleton6 instance = new Singleton6();
    }
    
    public static Singleton6 getInstance() {
        return Inner.instance;
    }
}
