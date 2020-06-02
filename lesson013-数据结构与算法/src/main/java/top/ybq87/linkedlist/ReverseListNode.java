package top.ybq87.linkedlist;


/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/23
 */
public class ReverseListNode {
    
    public static void main(String[] args) {
        LinkedListNode1 n1 = new LinkedListNode1("1");
        LinkedListNode1 n2 = new LinkedListNode1("2");
        LinkedListNode1 n3 = new LinkedListNode1("3");
        LinkedListNode1 n4 = new LinkedListNode1("4");
        LinkedListNode1 n5 = new LinkedListNode1("5");
        
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        n5.setNext(n2);
        
        // reversePrint(n1);
        
        // LinkedListNode1 linkedListNode1 = reverseNode(n1);
        LinkedListNode1 linkedListNode1 = middleNode(n1);
        // System.out.println(linkedListNode1.toString());
    }
    
    public static LinkedListNode1 middleNode(LinkedListNode1 head) {
        LinkedListNode1 slow = head;
        LinkedListNode1 fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (fast == slow) {
                System.out.println("相遇");
                break;
            }
        }
        return slow;
    }
    
    /**
     * 反转一个单向链表
     *
     * @param curr
     * @return
     */
    public static LinkedListNode1 reverseNode(LinkedListNode1 curr) {
        /*
        需求: 1->2->3->4->5 反转为: 5->4->3->2->1
        思路: 分析反转的情况, 假设直接交换 next 就只能修改第一个 2->1 3->4->5,
        这样,那么就会产生 2 个断开的链表(newLink,oldLink),为了得到第二个链表,我们需要一个引用指向它的头结点 3
        而新产生的链表我们分析也是从头部加入新数据,需要第二个引用指向它的头结点 2, 进的节点的 next 指向这个 2,就是头部插入.
        那么我们需要一个搬运工,将旧链表的头结点 curr 搬运到新链表的头部,curr.next = newLink,
        oldLink 每次都向后移动一格
         */
        LinkedListNode1 prev = null;
        LinkedListNode1 next;
        while (curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        return prev;
    }
    
    /**
     * 逆序单向链表
     * @param head
     * @return
     */
    public static LinkedListNode1 reverseList(LinkedListNode1 head) {
        LinkedListNode1 next;
        LinkedListNode1 pre = null;
        
        while (head != null) {
            // 将head.next赋值给next变量，也就是说next指向了节点2，先将节点2保存起来。
            next = head.getNext();
            // 将pre变量赋值给了head.next，即节点1指向了null。
            head.setNext(pre);
            // 将head赋值给了pre，即pre指向节点1，将节点1设为“上一个节点”。
            pre = head;
            // 将next赋值给head，即head指向了节点2。将节点2设置为“头结点”。
            head = next;
        }
        return pre;
    }
    
    /**
     * 倒序打印链表:
     * 循环迭代,先迭代,再打印
     * @param head
     * @return
     */
    public static void reversePrint(LinkedListNode1 head) {
        if (head != null) {
            reversePrint(head.getNext());
            System.out.println(head.getVal());
        }
    }
}

class LinkedListNode1 {
    
    private String val;
    private LinkedListNode1 next;
    
    public LinkedListNode1(String val) {
        this.val = val;
    }
    
    public String getVal() {
        return val;
    }
    
    public void setVal(String val) {
        this.val = val;
    }
    
    public LinkedListNode1 getNext() {
        return next;
    }
    
    public void setNext(LinkedListNode1 next) {
        this.next = next;
    }
    
    @Override
    public String toString() {
        return "LinkedListNode1{" +
                "val='" + val + '\'' +
                ", next=" + next +
                '}';
    }
}
