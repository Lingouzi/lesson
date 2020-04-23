package top.ybq87.error;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/23
 */
public class StatckOverErrorTest {
    
    public static void main(String[] args) {
        // 栈溢出，一般是方法调用深度溢出，默认的栈内存大小一般为 1024k
        // 循环调用迭代就可以了
        // Exception in thread "main" java.lang.StackOverflowError
        stack();
    }
    
    public static void stack() {
        stack();
    }
    
}
