package top.ybq87;

import java.util.Arrays;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/11
 */
public class QuickSort {
    
    /**
     * 快速排序的实现
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 7, 1, 6, 3, 5, 3, 4, 10, 24, 16, 9, 8, 0};
        // sort(arr);
        // System.out.println(Arrays.toString(arr));
        
        // 第二种快速排序的思路
        sort2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        
    }
    
    /**
     * 思路：
     * 以起点为基数，数组的开始节点为 left，数组结尾为 right，
     * 先从 right 开始往左，找到第一个比基数小的数，记住他的位置 i
     * 然后从 left 开始往右，找到第一个大于等于基数的数，记住他的位置 j
     * 然后交换 i 和 j，
     * 然后继续从 right 上次的位置的 i - 1 处开始往左找第一个比基数小的数，记录位置 i
     * 然后从 left 上次的位置 j + 1 开始往右找到第一个大于等于基数的数，记住位置 j
     * 交换 i 和 j，
     * ...
     * 以上查找过程中，只要有一个查询过程中 i <= j 就停止查找。
     * 这样排列后，基数左侧的就都比他小，右侧的都比他大。
     * 然后将划分的数组左侧右侧的再次迭代排序就得到最后的正确排序了
     * @param arr
     */
    private static void sort2(int[] arr, int start, int end) {
        int base = arr[start];
        int left = start, right = end;
        // 从最右边往左找的话，因为要记录小的数的位置，所以需要一个变量 i
        // 只要 2 个位置不碰撞，那么就一直找。
        while (right > left) {
            // 第一步，从右往左找, 主要当前 arr[right] 是比基数大的，就继续往下找，记住 right 的值要减
            while (left < right && arr[right] > base) {
                //
                right--;
            }
            // 如果跳出的循环，说明找到了一个比基数小的数，而且得到了她的位置 right
            
            // 第二步，从左往右找，找到大于基数的，就停止，
            while (left < right && arr[left] < base) {
                left++;
            }
            // 保证相同的数过滤掉，步进加 1.（不判定会导致死循环）
            if (left < right && arr[left] == arr[right]) {
                left++;
            }
            // 然后交换 2 个位置的值。
            swap(arr, left, right);
        }
        // 得到左边的和右边的
        if (left - 1 > start) {
            sort2(arr, start, left - 1);
        }
        if (left + 1 < end) {
            sort2(arr, left + 1, end);
        }
    }
    
    /**
     * 思路
     * 选定一个基准数，在数组中找到比他小的排到基准数的左边，
     * 找到比基准数大的数，排到右边
     * 然后 2 边的数据再次递归，最后得到正确的数。
     *
     * @param arr
     */
    private static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    
    private static void quickSort(int[] arr, int left, int right) {
        // 如果 2 个数的角标没有重合
        if (left < right) {
            // 得到基数的角标
            int partitionIndex = partition(arr, left, right);
            // 计算左侧的
            quickSort(arr, left, partitionIndex - 1);
            // 计算右侧的
            quickSort(arr, partitionIndex + 1, right);
        }
    }
    
    /**
     * 将数组按照基数排序，小于基数的在左边，大于基数的在右侧
     * @param arr
     * @param left
     * @param right
     * @return 返回基数最后的位置
     */
    private static int partition(int[] arr, int left, int right) {
        // 基准值
        int pivot = left;
        // 记录遇到的比基数大的数据的位置，用来和右侧比基数小的数据交换的
        int index = pivot + 1;
        // 从 第 index = 1 的数据开始往右循环
        for (int i = index; i <= right; i++) {
            // 第一次 if 不成立的条件是找到了第一个比基数大的数据，然后记录到了这个数的 index。
            // 下次循环到第一个比他小的数据时，交换大数和小数的位置。
            // 然后 index 继续找第一个比基数大的数。
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}
