package top.ybq87.hash;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/9
 */
public class Heapify {
    
    
    public static void main(String[] args) {
        /*
        思路：
        1、大文件划分为小文件【比如划分为 1024 个小文件，使用 AtomicLong 记录当前读取的行数，对 1024 取模得到 fileIndex】，
        使用 file 的 io 流读取写入；或者如果知道明确的数据条数，直接顺序划分。。。
        2、第一个文件读取前 100 个不重复的数【为了保证小堆里面的数都是不同的，用 hashmap 记录一下读取的数，
        在 hashmap 中有记录的就丢弃，直到 hashmap.size = 100】
        3、对 100 个数进行小顶堆排序，那么顶部的就是最小的数字了
        4、然后循环剩下的数【循环读取小文件】，读到一个数对比顶部的数
        5、读到的数 readNum < heapTopNum，小于堆顶的【因为是堆顶，那么其他节点都比堆顶的大】，直接抛弃这个数
        6、读到的数 readNum > heapTopNum ，交换堆顶的数，然后对堆再次排序
        7、循环第 4 步，就得到了最后的 100 个数。
        这个题目应该考点是最小堆的问题吧，文件流部分代码省略了。。。
        
        优化：
        1、可以用线程池，对每个小文件同步进行 100 的小堆排序，最后得到每个文件的最大 100 个数（num <= 100），然后合并这些结果，再次读取到内存排序就很快了。
        2、另外我记得 PriorityQueue 这个队列好像也可以排序，默认是小顶堆，
         */
        int[] arr = new int[]{
                4, 10, 3, 5, 1, 2
        };
        heapSort(arr, 6);
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * 小顶堆
     * @param arr 数组
     * @param n   节点个数
     */
    public static void heapSort(int[] arr, int n) {
        // 先要构建一个 heap，顶部是最大数
        buildHeapify(arr, n);
        
        // 数组角标
        for (int i = n - 1; i >= 0; i--) {
            // 将最后一个节点和顶部的节点交换。
            swap(arr, i, 0);
            // heap 进行 heapify 操作，
            heapify(arr, i, 0);
        }
        
    }
    
    /**
     * 将乱序数组变为 heap：
     * 找到最后一个父节点，然后向上进行 heapify。
     * @param arr 数组
     * @param n   树有几个节点
     */
    public static void buildHeapify(int[] arr, int n) {
        // 最后一个节点的角标
        int lastNode = n - 1;
        // 最后一个节点的父节点的角标，取整
        int parent = (lastNode - 1) / 2;
        int i;
        // 然后从最后的父节点往上递归做 heapify
        // heapify:就是 parent 和 2 个 child 对比找到最大的放到 parent 里面。
        for (i = parent; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }
    
    /**
     * 已有数组变为 heap
     * 顶堆，小顶堆，heap 的定义：
     * 1、完全二叉树
     * 2、parent 大于等于子节点
     * parent 和子节点的关系
     * parent: (i - 1) / 2，向下取整
     * child1: 2 * i + 1
     * child2: 2 * i + 2
     * @param arr 数组
     * @param n   树中有多少节点
     * @param i   对那个节点(角标)进行 heapify
     * @return
     */
    public static void heapify(int[] arr, int n, int i) {
        // 出口
        if (i >= n) {
            return;
        }
        // 2 个子节点的位置
        int c1 = 2 * i + 1;
        int c2 = 2 * i + 2;
        // 假设当前节点 i 的值是最大值
        int max = i;
        // 找到最大值
        // c1 < n 不能越界，查看子节点是否大于父节点，大于就交换 max 的值，max 指向大数
        if (c1 < n && arr[c1] > arr[max]) {
            // 子节点比父节点大，那么记录子节点的角标
            max = c1;
        }
        if (c2 < n && arr[c2] > arr[max]) {
            max = c2;
        }
        // 说明找到了最大的值的角标
        if (max != i) {
            // 交换到 parent 去【i 原来假设是最大值。】
            swap(arr, max, i);
            // 递归子节点
            heapify(arr, n, max);
        }
    }
    
    /**
     * 交换 2 个数组的值
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
}
