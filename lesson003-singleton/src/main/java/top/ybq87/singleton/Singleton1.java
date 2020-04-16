package top.ybq87.singleton;

/**
 * 饿汉模式：不管你用不用，我都先创建，
 * 直接通过 变量对外提供访问。
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/16
 */
public class Singleton1 {
    
    /**
     * 1、构造器私有化
     * 2、内部创建，保存，用静态变量保存
     * 3、对外提供访问方法
     */
    
    private Singleton1() {
    }
    
    public static final Singleton1 singleton1 = new Singleton1();
    
}
