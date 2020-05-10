package top.ybq87.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * //
 * // 示例 1:
 * //
 * // 输入: "abcabcbb"
 * //输出: 3
 * //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * //
 * //
 * // 示例 2:
 * //
 * // 输入: "bbbbb"
 * //输出: 1
 * //解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * //
 * //
 * // 示例 3:
 * //
 * // 输入: "pwwkew"
 * //输出: 3
 * //解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * //     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * //
 * // Related Topics 哈希表 双指针 字符串 Sliding Window
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/6
 */
public class LengthOfLongestSubstring {
    
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("str"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("dvdf"));
        System.out.println(lengthOfLongestSubstring("tmmzuxt"));
    }
    
    /**
     * 思路：
     * 1、使用滑块的概念，设定滑块的头部 start 和尾部 end
     * 2、end 从头开始遍历字符串，每次移动一个字符，并且使用map记录当前字符和所处的位置
     * 3、start 开始 = 0，当 map 中遇到已经存在的字符时，滑动start到最新的位置
     *   那么记录 len = end - start > oldLen 就替换
     * 4、
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int len = 0;
        int start = 0;
        int end = 0;
        Map<Character, Integer> map = new HashMap<>(16);
        for (Character car : s.toCharArray()) {
            if (map.containsKey(car)) {
                // 将start移动到最近的一个位置
                start = Math.max(map.get(car), start);
            }
            // 计算长度
            len = Math.max(len, end - start + 1);
            // 存储当前字符的位置。
            map.put(car, end + 1);
            end++;
        }
        return len;
    }
}
