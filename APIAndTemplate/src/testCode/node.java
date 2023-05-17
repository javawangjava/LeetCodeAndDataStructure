/*
import java.util.List;
*/
/*

class ListNodeText {
    private void test() {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(5);
        ListNode node6=new ListNode(6);
        //node1.next=node2;
        //node2.next=node3;
        //node3.next=node4;
        //node4.next=node5;
        //node5.next=node6;
    }

    // 打印链表
    private static  void print(ListNode head) {
        //   判断链表是否为空
        if (head== null) {
            System.out.println("链表为空");
            return;
        }
        //遍历打印节点不包含头结点，要从真正的第一个元素节点开始遍历
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + ",");//  输出节点信息
            temp = temp.next;//curr后移，遍历当前链表
        }
    }

}


class ListNode{
    int val;
    ListNode next;
    public ListNode(){}
    public ListNode(int val){
        this.val=val;
    }
    public ListNode(int val,ListNode next){
        this.val=val;
        this.next=next;
    }
}

*/


//
//class Node {
//    public int val;
//    public Node prev;
//    public Node next;
//    public Node child;
//
//    public Node(int val) {
//        this.val = val;
//    }
//
//    public Node(int val, Node next) {
//        this.val = val;
//        this.next = next;
//    }
//
//    public Node(Node prev, Node next) {
//        this.prev = prev;
//        this.next = next;
//    }
//
//    public Node(Node child) {
//        this.child = child;
//    }
//
//    public Node(int val, Node prev, Node next, Node child) {
//        this.val = val;
//        this.prev = prev;
//        this.next = next;
//        this.child = child;
//    }
//}
//
//
//class Node {
//	public int val;
//	public Node left;
//	public Node right;
//
//	public Node() {}
//
//	public Node(int val) {
//		this.val = val;
//	}
//
//	public Node(int val, Node left, Node right) {
//		this.val = val;
//		this.left = left;
//		this.right = right;
//	}
//}
//
//
//class Node {
//    public int val;
//    public Node prev;
//    public Node next;
//    public Node child;
//
//    public Node left;
//    public Node right;
//
//    public Node(int val) {
//        this.val = val;
//    }
//
//
//    public Node(int val, Node left, Node right) {
//        this.val = val;
//        this.left = left;
//        this.right = right;
//    }
//
//
//    public Node(int val, Node next) {
//        this.val = val;
//        this.next = next;
//    }
//
//    public Node(Node prev, Node next) {
//        this.prev = prev;
//        this.next = next;
//    }
//
//    public Node(Node child) {
//        this.child = child;
//    }
//
//    public Node(int val, Node prev, Node next, Node child) {
//        this.val = val;
//        this.prev = prev;
//        this.next = next;
//        this.child = child;
//    }
//}