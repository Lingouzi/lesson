package top.ybq87.singleton;

/**
 * 懒汉
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/16
 */
public class Singleton5 {
    
    /**
     * 1、构造器私有
     * 2、私有参数
     * 3、对外提供访问参数
     * 4、但是要延迟创建
     */
    private Singleton5() {
        System.out.println("我被创建了..");
    }
    
    private static Singleton5 instance;
    
    public static Singleton5 getInstance() {
        // 双重检查，提高效率
        if (instance == null) {
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
