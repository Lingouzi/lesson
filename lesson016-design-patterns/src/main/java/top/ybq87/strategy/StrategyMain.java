package top.ybq87.strategy;

import java.util.Arrays;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/11
 */
public class StrategyMain {
    
    /**
     * 策略模式， strategy
     * 定义：
     * @param args
     */
    public static void main(String[] args) {
        // 对 a 排序
        int[] a = {9, 2, 4, 1, 5, 6};
        // 排序
        Sorter sorter = new Sorter();
        sorter.sort(a);
        // 打印
        System.out.println(Arrays.toString(a));
        
    }
}
