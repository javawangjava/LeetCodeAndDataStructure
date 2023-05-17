package template;

class FindMidListNode {

    // 注意类型1和类型2的不同
    // 找到链表中间节点  快慢指针法。类型1
    // 若节点有奇数个，则中间节点只有一个，则返回中间节点。
    // 若节点时偶数个，则中间节点有两个，则返回的是第一个中间节点
    private ListNode findMid(ListNode head){
        if(head==null){
            return head;
        }
        ListNode fast=head.next;//注意这里fast和slow不是同一个节点开始的
        ListNode slow=head;

        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }



    // 快慢指针法。找到链表中间节点  类型2 和上面不同
    // 若节点有奇数个，则中间节点只有一个，则返回中间节点。
    // 若节点时偶数个，则中间节点有两个，则返回的是第二个中间节点

    private ListNode findMid2(ListNode head){
        //if(head==null||head.next==null)
        if(head==null){
            return head;
        }
        ListNode fast=head;// 注意这里fast和slow是同一个节点开始的
        ListNode slow=head;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
}



class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


//
//class Node {
//    public int val;
//    public Node next;
//    public Node() {}
//    public Node(int val) {
//        this.val = val;
//    }
//
//    public Node(int val, Node next) {
//        this.val = val;
//        this.next = next;
//    }
//}