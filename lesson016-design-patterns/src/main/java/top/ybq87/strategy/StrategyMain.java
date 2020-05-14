package top.ybq87.strategy;

import java.util.Arrays;

/**
 * 策略模式， strategy
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/11
 */
public class StrategyMain {
    
    public static void main(String[] args) {
        int[] arr = {9, 2, 4, 1, 5, 6};
        // 使用快速排序方法
        StrategyContext context = new StrategyContext(new QuickSorter());
        
        context.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
