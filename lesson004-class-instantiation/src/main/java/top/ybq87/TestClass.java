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
    
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();
        System.out.println(a1.show(b));
        System.out.println(a1.show(c));
        System.out.println(a1.show(d));
        System.out.println(a2.show(b));
        System.out.println(a2.show(c));
        System.out.println(a2.show(d));
        System.out.println(b.show(b));
        System.out.println(b.show(c));
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