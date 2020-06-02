package top.ybq87.node;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/22
 */
public class SingleLinkedNode {
    
    public static void main(String[] args) {
        SingleLinkedNode1 node1 = new SingleLinkedNode1("1");
        SingleLinkedNode1 node2 = new SingleLinkedNode1("2");
        SingleLinkedNode1 node3 = new SingleLinkedNode1("3");
        SingleLinkedNode1 node4 = new SingleLinkedNode1("4");
        SingleLinkedNode1 node5 = new SingleLinkedNode1("5");
        SingleLinkedNode1 node6 = new SingleLinkedNode1("6");
        SingleLinkedNode1 node7 = new SingleLinkedNode1("7");
        SingleLinkedNode1 node8 = new SingleLinkedNode1("8");
        SingleLinkedNode1 node9 = new SingleLinkedNode1("9");
        SingleLinkedNode1 node10 = new SingleLinkedNode1("10");
        
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);
        node8.setNext(node9);
        node9.setNext(node10);
        // node10.setNext(node3);
        
        // 测试是否有环
        boolean b = hasCircle(node1);
        System.out.println(b);
        
        // 找到环的入口, 参考:https://blog.csdn.net/sinat_35261315/article/details/79205157
        SingleLinkedNode1 node = getEntrance(node1);
        System.out.println(node.getVal());
        
    }
    
    /**
     * 任意等分点
     * @param headNode
     * @param k
     * @return
     */
    public static String findNodeK(SingleLinkedNode1 headNode, int k) {
        SingleLinkedNode1 slow = headNode;
        SingleLinkedNode1 fast = headNode;
        /*
        假设查找 3 等分点, fast 一次步进 3 个点
         */
        while (fast != null && fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext().getNext();
            if (fast == null) {
                break;
            }
        }
        return slow.getVal();
    }
    
    /**
     * 找到环的入口
     * @param node
     * @return
     */
    private static SingleLinkedNode1 getEntrance(SingleLinkedNode1 node) {
        SingleLinkedNode1 slow = node;
        SingleLinkedNode1 fast = node;
        //确保有环
        if (hasCircle(node)) {
            /*
            环的入口,用 2 个指针走,slow 和 fast,当他们相遇的时候,一般 slow 没有走完一圈环.
            而 fast 可能走完了 n 圈.假设从头步进 L 步进环,那么 slow = fast 的节点到环的节点为 M
            L = M,也就是遇到环的时候,slow 指向 head,然后每次步进一步,fast 也改为步进一步.2 个再次相遇的点就是环入口
             */
            // 确保至少有 2 个元素,环的节点至少有 2 个.
            while (fast != null && fast.getNext() != null) {
                slow = slow.getNext();
                fast = fast.getNext().getNext();
                if (fast == slow) {
                    // 有环
                    break;
                }
            }
            // 然后 slow 指向 head
            slow = node;
            while (fast != slow) {
                slow = slow.getNext();
                fast = fast.getNext();
            }
        }
        return slow;
    }
    
    private static boolean hasCircle(SingleLinkedNode1 node) {
        /*
        思路:
        假设有数据: 1 ----> 2 ----> 3 ----> 4
                          ^               |
                          |               |
                          6 <------------ 5
        这个环,设置 2 个步进,slow 每次进一步,fast 每次进 2 步, 用到了 while,注意用 return 跳出或者 fast 的 next = null
        初始时, slow = fast = head = 1
        第一个循环: slow = 2, fast = 3
        第二个循环: slow = 3, fast = 5
        第三个循环: slow = 4, fast = 2
        第四个循环: slow = 5, fast = 3
        第五个循环: slow = 6, fast = 5
        相遇,退出循环.
         */
        // if (node == null || node.getNext() == null) {
        //     return false;
        // }
        SingleLinkedNode1 slow = node;
        SingleLinkedNode1 fast = node;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                System.out.println(slow.getVal());
                return true;
            }
        }
        return false;
    }
    
}


class SingleLinkedNode1 {
    
    private String val;
    private SingleLinkedNode1 next;
    
    public SingleLinkedNode1(String val) {
        this.val = val;
    }
    
    public String getVal() {
        return val;
    }
    
    public void setVal(String val) {
        this.val = val;
    }
    
    public SingleLinkedNode1 getNext() {
        return next;
    }
    
    public void setNext(SingleLinkedNode1 next) {
        this.next = next;
    }
    
    @Override
    public String toString() {
        return "SingleLinkedNode1{" +
                "val='" + val + '\'' +
                ", next=" + next +
                '}';
    }
}
