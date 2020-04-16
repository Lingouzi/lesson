package top.ybq87.singleton;

/**
 * 适合于单线程的
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/16
 */
public class Singleton4 {
    
    /**
     * 1、构造器私有
     * 2、私有参数
     * 3、对外提供访问参数
     * 4、但是要延迟创建
     * 5、单线程没毛病，多线程不保证
     */
    private Singleton4() {
        System.out.println("我被创建了..");
    }
    
    private static Singleton4 instance;
    
    public static Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
