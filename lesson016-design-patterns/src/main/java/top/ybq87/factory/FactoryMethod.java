package top.ybq87.factory;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/14
 */
public class FactoryMethod {
    
    /**
     * 主要角色：
     * 抽象工厂
     * 具体工厂
     * 抽象产品（Abstract Product）
     * 具体产品（Concrete Product）
     *
     * 这么做的目的是为了让 client 不需要知道具体的产品和工厂的实现
     * @param args
     */
    public static void main(String[] args) {
        // client 在调用的时候，不需要知道你怎么创建产品的，主要知道产品的工厂就可以了，不用关心怎么创建，
        AbstractFactory factory = new ConcreteFactory1();
        factory.getProduct().show();
        // 缺点就需要知道 product 对应的工厂是什么，而且不同的产品对应不同的 factory，代码会比较复杂。
        factory = new ConcreteFactory2();
        factory.getProduct().show();
    }
}

interface Product {
    
    /**
     * 产品方法
     */
    void show();
}

class ConcreteProduct1 implements Product {
    
    
    @Override
    public void show() {
        System.out.println(">>>>> ConcreteProduct1.show()");
    }
}

class ConcreteProduct2 implements Product {
    
    @Override
    public void show() {
        System.out.println(">>>> ConcreteProduct2.show()");
    }
}

interface AbstractFactory {
    
    /**
     * 创建产品
     * @return
     */
    Product getProduct();
}

class ConcreteFactory1 implements AbstractFactory {
    
    
    @Override
    public Product getProduct() {
        System.out.println("ConcreteFactory1 >> create product");
        return new ConcreteProduct1();
    }
}

class ConcreteFactory2 implements AbstractFactory {
    
    @Override
    public Product getProduct() {
        System.out.println("ConcreteFactory2 >> create product");
        return new ConcreteProduct2();
    }
}
