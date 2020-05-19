package top.ybq87.adapter;

import java.lang.annotation.Target;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/14
 */
public class ClassAdapterPattern {
    
    /**
     * target：被 client 调用的
     * Adaper：中间适配器
     * Adaptee：被适配者，
     * client：调用者，调用 target 的方法,实际通过适配器调用了 adaptee 的方法.
     *
     * 类适配器: 使用的主要是继承.
     *
     * client
     *   |
     *   V
     * target <|--implement-- adapter --extends--|> adaptee
     * @param args
     */
    public static void main(String[] args) {
        AdapterTarget target = new ClassAdaper();
        target.hello();
        
    }
}

interface AdapterTarget {
    
    public void hello();
}

class AdapterAdaptee {
    
    public void hello2() {
        System.out.println(" AdapterAdaptee >>>>");
    }
}

class ClassAdaper extends AdapterAdaptee implements AdapterTarget {
    
    @Override
    public void hello() {
        System.out.println(">>>start");
        super.hello2();
        System.out.println(">>>end");
    }
}
