package top.ybq87.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * 题目：
 * 给一组词，统计出现频率最高的 k 个。
 * 比如说 “I love leetcode, I love coding” 中频率最高的 2 个就是 I 和 love 了。
 * [i,love,leetcode,i,love coding], k = 2 从给定数组，求 k 个最高频的词
 * 拓展题目：天猫关键词一天内搜索最好频的几个词是什么
 *
 * 本人分析思路
 * 1、数据循环后，存入 map，以 key=word，value=词出现的次数。
 * 2、对比 map 的 value 的大小，排序
 * 3、得到前面 k 个。
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/29
 */
public class TopWordNum {
    
    public static void main(String[] args) {
        String[] str = new String[]{
                "i", "love", "leetcode", "i", "love", "coding"
        };
        int k = 2;
        
        top1(str, k);
    }
    
    public static List<String> top1(String[] words, int k) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(16);
        //1. 计算每个单词出现的次数
        for (String word : words) {
            map.merge(word, 1, Integer::sum);
        }
        
        System.out.println(map);
        
        //2. value 排序
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(k, (o1, o2) -> {
            System.out.println("o1" + o1);
            System.out.println("o2" + o2);
            Integer v1 = o1.getValue();
            Integer v2 = o2.getValue();
            if (v1.equals(v2)) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                return v1.compareTo(v2);
            }
        });
        for (Entry<String, Integer> entry : map.entrySet()) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        System.out.println(queue.size());
        
        //3. 取出前 k 个
        Iterator<Entry<String, Integer>> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Entry<String, Integer> next = iterator.next();
            result.add(next.getKey());
        }
        System.out.println(result);
        // 结果要反转一下，因为 PriorityQueue 是最小的排在前面的
        Collections.reverse(result);
        System.out.println(result);
        
        return result;
    }
    
}
