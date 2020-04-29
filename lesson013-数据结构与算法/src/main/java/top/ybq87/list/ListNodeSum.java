package top.ybq87.list;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/29
 */
public class ListNodeSum {
    
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l11 = new ListNode(4);
        ListNode l12 = new ListNode(3);
        //
        l1.next = l11;
        l11.next = l12;
        
        ListNode l2 = new ListNode(9);
        ListNode l21 = new ListNode(6);
        ListNode l22 = new ListNode(4);
        
        l2.next = l21;
        l21.next = l22;
        //
        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(listNode);
    }
    
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
        // 输出：7 -> 0 -> 8
        // 原因：342 + 465 = 807
        if ((l1.val == 0 && l1.next == null) && (l2.val == 0 && l2.next == null)) {
            return new ListNode(0);
        }
        if (l1.next == null) {
            l1.next = new ListNode(0);
        }
        if (l2.next == null) {
            l2.next = new ListNode(0);
        }
        int sum = l1.val + l2.val;
        ListNode result = new ListNode(sum % 10);
        l1.next.val = l1.next.val + (sum / 10 >= 1 ? 1 : 0);
        result.next = addTwoNumbers(l1.next, l2.next);
        if(result.next.val == 0 && result.next.next == null){
            result.next = null;
        }
        return result;
    }
}

class ListNode {
    
    int val;
    ListNode next;
    
    ListNode(int x) {
        val = x;
    }
    
    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
