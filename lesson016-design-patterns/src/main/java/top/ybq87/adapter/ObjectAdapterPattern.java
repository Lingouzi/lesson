package top.ybq87.adapter;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/19
 */
public class ObjectAdapterPattern {
    
    /**
     * 对象适配器模式的关键点是,adapter 持有被适配者的引用,
     * @param args
     */
    public static void main(String[] args) {
        ObjectAadapterTarget target = new ObjectAdapter(new ObjectAdaptee());
        target.hello();
    }
}

interface ObjectAadapterTarget {
    
    void hello();
}

class ObjectAdaptee {
    
    public void hello() {
        System.out.println("被适配者的调用 hello");
    }
}

class ObjectAdapter implements ObjectAadapterTarget {
    
    private ObjectAdaptee adaptee;
    
    public ObjectAdapter(ObjectAdaptee adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void hello() {
        System.out.println("适配器作用");
        adaptee.hello();
    }
}
