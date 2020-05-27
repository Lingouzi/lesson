package top.ybq87.num;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/24
 */
public class ReverseNum {
    
    /**
     * 反转数字
     * 123 -> 321
     * -231 -> -132
     * 120 -> 21
     *
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     *
     * @param args
     */
    public static void main(String[] args) {
        int x = -12342;
        //1. 位取模
        int reverse = reverse(x);
        System.out.println(reverse);
        
        //2. 字符串方法
        int s = reverseByString(x);
        System.out.println(s);
        
    }
    
    public static int reverse(int target) {
        /*
        得到第一个个位数,对 10 取模,就是新数字的最高位
        然后 target/10 得到新的一个最低位, 循环. 直到 target = 0
        
        有 bug: 1534236469 超出 int 范围,未处理
         */
        int n = 0;
        while (target != 0) {
            // 模 10 得到最低位的数字,然后最低位赋值给目标结果,为了每次进位需要结果*10,
            n = n * 10 + target % 10;
            target = target / 10;
        }
        return n;
    }
    
    public static int reverseByString(int target) {
        try {
            String val = Integer.toString(target);
            if (target < 0) {
                // 去掉负数符号
                val = val.substring(1);
            }
            // 反转字符串
            StringBuffer sb = new StringBuffer(val).reverse();
            // StringBuilder sbb = new StringBuilder(val).reverse();
            return target < 0 ? -Integer.parseInt(sb.toString()) : Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
}
