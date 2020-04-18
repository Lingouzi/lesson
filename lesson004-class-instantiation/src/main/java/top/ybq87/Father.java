package top.ybq87;

/**
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/16
 */
public class Father {
    
    private int i = test(this);
    
    private static int j = method();
    
    static {
        System.out.println("1 父类静态代码块");
    }
    
    private String str;
    
    Father() {
        str = "father";
        System.out.println(getClass().getSuperclass().getName());
        System.out.println("2 父类无参构造器 >>> str:" + str);
    }
    
    {
        System.out.println("3 父类代码块");
    }
    
    public int test(Father father) {
        System.out.println("4 父类 test 方法 father.getClass:" + father.getClass());
        return 1;
    }
    
    public static int method() {
        System.out.println("5 父类 method 方法");
        return 1;
    }
    
}
