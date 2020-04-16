package top.ybq87;

/**
 * son 要初始化，必须先初始化父类
 * 父类初始化 先调用父类的 <clinit>()方法：
 * 1、父类的 j = method();                  // 5
 * 2、父类的 静态代码块                       // 1
 * 然后调用子类的初始化方法 <clinit>():
 * 3、子类的 j = method();                  // 10
 * 4、子类的 静态代码块                       // 6
 *
 * 下面开始实例初始化。
 * 5、子类实例化开始，调用构造器，第一句是 super()，所以走了父类的 init 方法
 * 6、父类的 i = test()                     // 9 *** 这里是个考点
 * 7、父类的 非静态代码块                     // 3
 * 8、父类的构造器                           // 2
 * 9、下面是子类的 init 方法
 * 10、子类的 i = test()                    // 9
 * 11、子类的 非静态代码块                    // 8
 * 12、子类的 构造器                         // 7
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/16
 */
public class Son extends Father {
    
    private int i = test();
    
    private static int j = method();
    
    static {
        System.out.println("6 子类静态代码块");
    }
    
    Son() {
        // 这句写或者不写，都一定会执行，子类构造器一定会调用父类构造器。
        // super();
        System.out.println("7 子类无参构造函数");
    }
    
    {
        System.out.println("8 子类代码块");
    }
    
    @Override
    public int test() {
        System.out.println("9 子类 test 方法");
        return 1;
    }
    
    public static int method() {
        System.out.println("10 子类 method 方法");
        return 1;
    }
    
    
}
