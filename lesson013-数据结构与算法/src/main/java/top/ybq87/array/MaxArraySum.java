package top.ybq87.array;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/23
 */
public class MaxArraySum {
    
    /**
     * 题目: 给定任意的 int 型的数组, A[0...n-1], 求 A 的连续子数组
     * 使得该子数组的和最大.
     * 比如: [1,-2,3,10,-4,7,2,-5]
     * 求得: [3,10,-4,7,2] 这个子数组的和在所有子数组中最大.
     *
     * 思路:
     * 1. 暴力法:
     * 求 A[i ~ j] ; 0 <= i < n ; i <= j < n; 复杂度 O(n^3)
     *
     * 2. 分治法:
     * 将数组从中间切成左右 2 块,那么 我们要求的最大和的子数组有几种存在可能:
     * 1). 要么全部落在左边的子数组,
     * 2). 要么落在右边的子数组.
     * 3). 要么就是跨越了中间节点
     * 在左边或者右边的可以迭代方法继续获取.那么最重要的就是落在中间的时候的算法.
     * 落在中间时,其实可以先算左边的,以 0~middle,循环每个元素,++,然后记录期间得到的最大值 max1.
     * 右边同理,middle~length - 1,记录最大值 max2.
     * 然后 2 个最大值相加 maxMiddle = max1 + max2 ,就是 落在中间时的,子数组的最大值.
     * 这样我们对比 max 左, maxMiddle,max 右,就得到了最大的数.
     *
     * 3.分析法
     *
     *
     * 4. 动态规划
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, -2, 3, 10, -4, 7, 2, -5};
        int i = maxSubArray1(arr);
        System.out.println(i);
        
    }
    
    /**
     * 暴力法
     * @param arr
     * @return
     */
    public static int maxSubArray1(int[] arr) {
        int max = arr[0];
        int len = arr.length;
        int curr = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                curr = 0;
                for (int k = i; k < j; k++) {
                    curr += arr[k];
                }
                if (curr > max) {
                    max = curr;
                }
            }
        }
        
        return max;
    }
}
