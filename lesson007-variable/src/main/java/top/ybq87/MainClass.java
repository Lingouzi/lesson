package top.ybq87;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/16
 */
public class MainClass {
    
    public static void main(String[] args) {
        // 局部变量 m1
        Variable m1 = new Variable();
    
        // 局部变量 m2
        Variable m2 = new Variable();
        System.out.println(m1.i + "," + m1.j + "," + m1.s);
        System.out.println(m2.i + "," + m2.j + "," + m2.s);
        m1.add(10);
        System.out.println(m1.i + "," + m1.j + "," + m1.s);
        m1.add(20);
        m2.add(30);
        
        System.out.println(m1.i + "," + m1.j + "," + m1.s);
        System.out.println(m2.i + "," + m2.j + "," + m2.s);
    }
    
}

class Variable {
    
    // 静态成员变量，类变量
    static int s;
    // 成员变量，实例变量
    int i;
    // 成员变量，实例变量
    int j;
    
    {
        // 局部变量
        int i = 1;
        // 就近原则，这个 i 指向的是上面这个 i
        this.i++;
        j++;
        s++;
    }
    
    // j 是局部变量
    public void add(int j) {
        // 就近原则，这个 j 指向的是局部变量 j，形参 j
        j++;
        i++;
        s++;
    }
}
