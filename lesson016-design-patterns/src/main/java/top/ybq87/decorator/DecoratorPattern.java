package top.ybq87.decorator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/19
 */
public class DecoratorPattern {
    
    /**
     * 装饰者模式,
     * 核心类,一般是接口.
     * 核心实现类,一般是被增强的实现类.
     *
     * 装饰器,与核心实现类实现了相同接口的抽象类
     * 具体的增强方法,集成抽象类装饰器.
     *
     * 比如给蛋糕加奶油
     *
     * @param args
     */
    public static void main(String[] args) {
        // 多层装饰, 类比下: FileInputStream f = new FileInputStream(new File("path"));
        DecoratorCake cake = new Decorator2(new Decorator1(new BasicCake()));
        cake.cake();
        
        // 一层装饰
        DecoratorCake cake2 = new Decorator1(new BasicCake());
        cake2.cake();
        
    }
}

interface DecoratorCake {
    
    public void cake();
}

class BasicCake implements DecoratorCake {
    
    @Override
    public void cake() {
        System.out.println("做了一个蛋糕");
    }
}

// 要增强都继承这个方法
abstract class AbstractDecorator implements DecoratorCake {
    
    private DecoratorCake cake;
    
    public AbstractDecorator(DecoratorCake cake) {
        this.cake = cake;
    }
    
    @Override
    public void cake() {
        cake.cake();
    }
}

class Decorator1 extends AbstractDecorator {
    
    public Decorator1(DecoratorCake cake) {
        super(cake);
    }
    
    @Override
    public void cake() {
        super.cake();
        System.out.println("放蜡烛");
    }
}

class Decorator2 extends AbstractDecorator {
    
    public Decorator2(DecoratorCake cake) {
        super(cake);
    }
    
    @Override
    public void cake() {
        super.cake();
        System.out.println("吹蜡烛");
    }
}
