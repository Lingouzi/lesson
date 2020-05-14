package top.ybq87;

import java.util.Arrays;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/13
 */
public class SelectionSort {
    
    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 7, 1, 6, 4, 9, 8, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * 思路：
     * 2 重循环，大循环的时候相当于是移动数组的角标，比如原来数组是：
     * i = 0，tmpLen = 10，arr = {5, 2, 3, 7, 1, 6, 4, 9, 8, 0}
     * 相当于一个小数组，第一次找到了最小数 0，然后交换到头，第二次进入循环时；在运算上忽略第一位的，
     * 那么不就是下面这样
     * i = 1，tmpLen = 9，arr = {2,3,7,1,6,4,9,8,5} // 之前的 0 和 5 交换，然后第一位的 0 被移除
     * i = 2，tmpLen = 8，arr = {3,7,2,6,4,9,8,5} // 2 和 1 交换，然后 1 移除
     * ...
     * @param arr
     */
    private static void sort(int[] arr) {
        // 这里用 length - 1 其实是一层优化，因为最后一轮的时候，其实数组只有一个数了，就是最大（小）的那个了，不需要再对比了。
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            // 从 i + 1 开始循环，假设第 1 个位置就是最小值，当进入第二次循环的时候，因为第一位数据已经是最小的了
            for (int j = i + 1; j < arr.length; j++) {
                // 当小循环中遇到比当前值还小的时候，记录这个位置。
                min = arr[j] < arr[min] ? j : min;
            }
            // 交换一下位置
            swap(arr, i, min);
        }
    }
    
    /**
     * 交换
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
