package top.ybq87.bridge;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/19
 */
public class BridgePattern {
    
    /**
     * 桥接模式的基本思想:
     * 将 类的 功能层次和类的 实现层次分开,
     * 因为都放在一起就使类的功能和实现混合, 我们在编写子类的时候就不知道怎么实现(拓展).
     *
     * 测试 demo:
     * 假设有一个显示类 Display,本身是个基本类.包含各种方法,为了层次清晰,将该类的方法个具体实现分离.
     * 那么就设计一个抽象的实现类 DisplayImpl, 拥有 Display 的一些基本抽象方法.
     * 然后设计一个具体的实现方法,去实现这个抽象类.
     * 那么 display 类中持有这个抽象方法, display 的方法就可以自己不写具体实现,而交给成员变量去调用对应的实现方法.
     * 如果要对 display 进行拓展 CountDisplay ,只需要继承 display.增加方法就可以
     * 当然 countdisplay 中的方法也是由总的 display 中的方法组成.
     *
     * 这样一个结构中,display 所持有的引用就是相当于一个桥梁. display 和 countdisplay 都是功能层次.
     * impl 都是实现层次.他们通过这个持有的引用产生关联.
     *
     * ***
     * 这里的 display 可以改为抽象方法, displayImpl 可以改为接口,那么就更加抽象
     *
     * @param args
     */
    public static void main(String[] args) {
        Display display = new Display(new SimpleDisplayImpl());
        display.open();
        System.out.println(">>>>>>>>>>>");
        Display display1 = new CountDisplay(new SimpleDisplayImpl());
        display1.open();
        System.out.println(">>>>>>>>>>>");
        CountDisplay display2 = new CountDisplay(new SimpleDisplayImpl());
        display2.count();
        
        // 假设我们要拓展方法, 只需要继承 display 类或者 countdisplay.
        
        // 假设我们要拓展实现,那么就继承 displayimpl,或者继承 simpledisplayimpl,重写方法就可以
        CountDisplay display3 = new CountDisplay(new Simple2DisplayImpl());
        display3.count();
    }
}

abstract class DisplayImpl {
    
    /**
     * 抽象方法
     */
    abstract void open();
    
    abstract void close();
}

class Display {
    
    DisplayImpl impl;
    
    public Display(DisplayImpl impl) {
        this.impl = impl;
    }
    
    public void open() {
        impl.open();
    }
    
    public void close() {
        impl.close();
    }
}

class CountDisplay extends Display {
    
    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }
    
    public void count() {
        // 调用父级方法
        open();
        close();
    }
}

class SimpleDisplayImpl extends DisplayImpl {
    
    @Override
    void open() {
        System.out.println("open >>>>");
    }
    
    @Override
    void close() {
        System.out.println("close >>>>");
    }
}

class Simple2DisplayImpl extends SimpleDisplayImpl {
    @Override
    void open() {
        System.out.println(" hello >>>>>");
    }
}
