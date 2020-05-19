package top.ybq87.bridge;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/19
 */
public class Bridge2Pattern {
    
    /**
     * https://www.runoob.com/w3cnote/bridge-pattern2.html
     * 中的 demo 为例
     * @param args
     */
    public static void main(String[] args) {
        Shape shape = new Circle();
        shape.setColor(new ColorBlack());
        shape.draw();
    }
}

interface Color {
    
    void print(String shape);
}

abstract class Shape {
    
    Color color;
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public abstract void draw();
}

class Circle extends Shape {
    
    @Override
    public void draw() {
        color.print("Circle");
    }
}

class Square extends Shape {
    
    @Override
    public void draw() {
        color.print("Square");
    }
}

class Rectangle extends Shape {
    
    @Override
    public void draw() {
        color.print("Rectangle");
    }
}

class ColorBlack implements Color {
    
    @Override
    public void print(String shape) {
        System.out.println(shape + " black");
    }
}

class ColorGreen implements Color {
    
    @Override
    public void print(String shape) {
        System.out.println(shape + " Green");
    }
}

class ColorRed implements Color {
    
    @Override
    public void print(String shape) {
        System.out.println(shape + " Red");
    }
}
