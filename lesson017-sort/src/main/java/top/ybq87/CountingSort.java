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
public class CountingSort {
    
    /**
     * 计数排序（简单桶排序）
     * 只适用于比较简单的数字排序
     * 比如要排序 n 个 0~k 的整数，那么时间复杂度是 O(n + k)
     * 思路：
     * 得到这一组数的最大值 max，然后申请一块 max + 1 大小的数组，数组的每个值为 0
     * 然后循环这 n 个数，与角标相同的数，那么数组的角标位置的值+1
     * 最后得到了有值的数组，就是排好序的了。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {5, 4, 6, 3, 1, 8, 0, 2, 5, 20, 2, 7, 9, 12};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    
    private static void sort(int[] arr) {
        // 得到数组最大的值 max
        int max = maxValue(arr);
        // 申请一块 max + 1 的空间的数组
        int[] temp = new int[max + 1];
        // 对 arr 循环，对应的 arr[index] +1
        for (int i : arr) {
            temp[i]++;
        }
        // 得到有值的 arr，取出有值的得到结果。
        int idx = 0;
        // 循环桶，
        for (int i = 0; i < temp.length; i++) {
            // 抛弃为 0 的，然后每个位置的值是多少，说明有多少个对应的 index 的数，那么 arr 的值就是这个 index
            while (temp[i] > 0) {
                arr[idx++] = i;
                // 用完了减去数量
                temp[i]--;
            }
        }
    }
    
    /**
     * 得到最大的数
     * @param arr
     * @return
     */
    private static int maxValue(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}
