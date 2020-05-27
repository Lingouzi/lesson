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
        LinkedListNode1 n2 = new LinkedListNode1("3");
        LinkedListNode1 n3 = new LinkedListNode1("5");
        LinkedListNode1 n4 = new LinkedListNode1("7");
        LinkedListNode1 n5 = new LinkedListNode1("9");
        
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        
        reversePrint(n1);
        
        LinkedListNode1 linkedListNode1 = reverseList(n1);
        System.out.println(linkedListNode1.toString());
    }
    
    /**
     * 反转一个单向链表
     *
     * @param head
     * @return
     */
    public static LinkedListNode1 fan(LinkedListNode1 head) {
        // 涉及到 3 个坐标,
        return head;
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
