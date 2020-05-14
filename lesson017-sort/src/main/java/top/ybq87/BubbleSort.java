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
public class BubbleSort {
    
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 7, 0, 1, 2, 7, 9};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * 思路
     * 每次比较两个相邻的元素，如果他们的顺序错误就把他们交换过来。
     * 冒泡排序的核心部分是双重嵌套循环。
     * @param arr
     */
    private static void sort(int[] arr) {
        // n 个数排序，只用进行 n-1 次对比
        for (int i = 0; i < arr.length - 1; i++) {
            // 对比完了，就缩小下次对比的数组，因为最小（大）的已经在最前（末）的位置了。不需要参与排序
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
