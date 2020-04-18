package top.ybq87;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/17
 */
public class TestClass {
    
    /**
     * this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)
     * @param args
     */
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();
        // A and A，A 没有 show(B obj) 方法，但是 B 是继承 A，所以找到 show(A obj)。
        System.out.println(a1.show(b));
        // A and A，同理 C 继承 B，B 继承 A，可以找到 show(A obj)
        System.out.println(a1.show(c));
        // A and D，直接找到 show(D obj)
        System.out.println(a1.show(d));
        // B and A，a2 编译时类型是 A，但是 A 没有 show(B obj) 方法，但是 b 继承 a，
        // 可以转为 show(A obj)，但是因为多态原因，这里调用的是运行时类 B 的 show(A obj)方法。
        System.out.println(a2.show(b));
        // B and A，同上 c 转为了 a，调用运行时类的 show 方法
        System.out.println(a2.show(c));
        // A and D，编译时类的方法可以直接调用，所以这里直接调用 show(D obj)
        System.out.println(a2.show(d));
        // B and B，直接找到 show(B obj)
        System.out.println(b.show(b));
        // B and B，c 继承自 b，找到 show(B obj)
        System.out.println(b.show(c));
        // A and D，因为 B 继承自 A，但是 b 又没有重写 show(D obj),而且 A 中 show(D obj)是个 public 的方法
        // 所以此方法被 B 继承过来，可以直接调用。
        System.out.println(b.show(d));
    }
}

class A {
    
    public String show(D obj) {
        return ("A and D");
    }
    
    public String show(A obj) {
        return ("A and A");
    }
}

class B extends A {
    
    public String show(B obj) {
        return ("B and B");
    }
    
    @Override
    public String show(A obj) {
        return ("B and A");
    }
}

class C extends B {

}

class D extends B {

}