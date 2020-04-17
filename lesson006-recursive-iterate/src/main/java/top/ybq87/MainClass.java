package top.ybq87;

/**
 * 递归与迭代
 * 题目：
 *      有 n 阶的一个台阶，你一次可以上 1 个台阶或者 2 个台阶，请问一共有几种走法？
 * 1、递归
 * 一般思路
 * n = 1     一步                 f(1) = 1 种走法
 * n = 2     11|2                f(2) = 2
 * n = 3     111|12|21           f(3) = 3
 * n = 4     1111|112|121|22|211 f(4) = 5
 * n = 5  。。。
 * 进阶思路
 * n = 1     1步                            f(1) = 1
 * n = 2     11|2                           f(2) = 2
 * n = 3     想办法到达 f(2)，最后走 1 阶
 *           想办法到达 f(1)，最后走 2 阶       f(3) = f(1) + f(2)
 * n = 4     到达倒数第 1 阶 f(3)，最后走 1 步
 *           到达倒数第 2 阶 f(2)，最后再走2 步  f(4) = f(3) + f(2)
 * ...
 * 2、迭代
 * a[0] = 0
 * a[1] = 1
 * a[2] = 2
 * a[3] = a[1] + a[2]
 * a[4] = a[2] + a[3]
 * ...
 *
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/16
 */
public class MainClass {
    
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        // 165580141 ,499ms
        // System.out.println(step(40));
        System.out.println(step2(40));
        long l2 = System.currentTimeMillis();
        long l1 = l2 - l;
        System.out.println(l1);
    }
    
    /**
     * 方法 1，迭代
     * @param n
     * @return
     */
    public static int step(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return step(n - 2) + step(n - 1);
    }
    
    /**
     * 数组的方法
     * @param n
     * @return
     */
    public static int step2(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 2;
        for (int i = 0; i < n - 2; i++) {
            arr[i + 2] = arr[i] + arr[i + 1];
        }
        return arr[n - 1];
    }
}

