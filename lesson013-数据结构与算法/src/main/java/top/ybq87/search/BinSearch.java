package top.ybq87.search;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/23
 */
public class BinSearch {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 思路:
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int start = 0;
        int len = nums.length - 1;
        while (start <= len) {
            // 取得中间角标.
            int mid = (start + len) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                len = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
