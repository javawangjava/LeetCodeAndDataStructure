/**
 * <p>给定单链表的头节点&nbsp;<code>head</code>&nbsp;，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。</p>
 *
 * <p><strong>第一个</strong>节点的索引被认为是 <strong>奇数</strong> ， <strong>第二个</strong>节点的索引为&nbsp;<strong>偶数</strong> ，以此类推。</p>
 *
 * <p>请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。</p>
 *
 * <p>你必须在&nbsp;<code>O(1)</code>&nbsp;的额外空间复杂度和&nbsp;<code>O(n)</code>&nbsp;的时间复杂度下解决这个问题。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/03/10/oddeven-linked-list.jpg" style="height: 123px; width:
 * 300px;" /></p>
 *
 * <pre>
 * <strong>输入: </strong>head = [1,2,3,4,5]
 * <strong>输出:</strong>&nbsp;[1,3,5,2,4]</pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/03/10/oddeven2-linked-list.jpg" style="height: 142px; width:
 * 500px;" /></p>
 *
 * <pre>
 * <strong>输入:</strong> head = [2,1,3,5,6,4,7]
 * <strong>输出:</strong> [2,3,6,7,1,5,4]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>n ==&nbsp;</code> 链表中的节点数</li>
 * <li><code>0 &lt;= n &lt;= 10<sup>4</sup></code></li>
 * <li><code>-10<sup>6</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>6</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>链表</li></div></div><br><div><li>👍 600</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 328
 * 奇偶链表
 *
 * @author wangweizhou
 * @date 2022-06-30 15:18:35
 */

public class OddEvenLinkedList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new OddEvenLinkedList().new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        print(node1);
        ListNode ans = solution.oddEvenList(node1);
        //solution.print(node1);
        print(ans);

    }

    // 打印链表节点
    private static void print(ListNode head) {
        //   判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //遍历打印节点不包含头结点，要从真正的第一个元素节点开始遍历
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + ",");//  输出节点信息
            temp = temp.next;//curr后移，遍历当前链表
        }
        System.out.println();
    }


//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    class Solution {


        // 解法1：遍历  写法3
        public ListNode oddEvenList(ListNode head) {
            // 链表为空，或者链表只有一个或者两个节点
            if (head == null || head.next == null || head.next.next == null) {
                return head;
            }

            // 奇偶链表的哨兵节点
            ListNode oddDummy = new ListNode(-1);
            ListNode evenDummy = new ListNode(-2);
            ListNode odd = oddDummy;// 辅助遍历指针
            ListNode even = evenDummy;

            // 原链表的哨兵节点
            ListNode dummy = new ListNode(-3,head);
            ListNode pre = dummy;// 辅助遍历指针，注意这里pre是后面两个待拆分节点的前一个节点，pre指向哨兵节点或者已经拆分的一组节点中的第二个节点

            while (pre.next != null && pre.next.next != null) {//处理前偶数个节点
                odd.next = pre.next;// 奇数组链表
                odd = odd.next;
                pre=pre.next;
                even.next = pre.next;// 偶数组链表
                even = even.next;
                pre=pre.next;
            }

            // 若节点个数是奇数个节点，处理最后一个奇数节点
            if(pre.next!=null){//
                odd.next=pre.next;// 最后一个奇数节点连接到奇数链表上
                odd=odd.next;// 奇数节点后移
            }

            even.next = null;// 偶数组节点后面置空，断开链表，防止成环
            odd.next = evenDummy.next;// 偶数组链表连接在奇数组链表之后
            return oddDummy.next;
        }



        /*
        // 解法1：遍历 写法2
        // 对于原始链表，每个节点都是奇数节点或偶数节点。
        // 头节点是奇数节点，头节点的后一个节点是偶数节点，相邻节点的奇偶性不同。

        // 创建两个指针分别指向奇偶链表
        // 奇指针每次指向偶指针的next，偶指针每次指向奇指针的next
        // 终止条件是偶数指针为空（一共有奇数个节点），或者偶数指针是最后一个节点（一共有偶数个节点）

        public ListNode oddEvenList(ListNode head) {
            // 如果链表为空，则直接返回链表。
            if (head == null ) {//这个不能少，因为后面while循环是用的even来做循环遍历
                return head;
            }
            ListNode odd = head;//遍历奇数节点
            ListNode oddHead = head;//奇数节点的头结点
            ListNode even = head.next;////遍历偶数节点
            ListNode evenHead = head.next;//偶数节点的头结点
            while (even != null && even.next != null) {//偶数指针和偶数指针的下一个奇数指针不为空 继续循环
                odd.next = even.next;// 奇数指针指向偶数指针的next,即奇数节点连接到奇数节点后面
                odd = odd.next;//移动奇数指针
                even.next = odd.next;//偶数指针指向奇数指针的next，偶数节点连接到偶数节点后面
                even = even.next;//移动偶数指针
            }
            odd.next = evenHead;//奇数指针结尾连接上偶数指针的开始
            return oddHead;
        }
        */





        /*
        // 解法1： 遍历  写法1
        // 遍历连接奇数节点和偶数节点，然后将偶数节点连接到奇数节点之后

        public ListNode oddEvenList(ListNode head) {
            // 链表为空，或者链表只有一个或者两个节点
            if (head == null || head.next == null || head.next.next == null) {
                return head;
            }

            // 创建哨兵节点
            ListNode oddDummy = new ListNode(-1);
            ListNode evenDummy = new ListNode(-1);
            ListNode odd = oddDummy;// 辅助遍历奇数节点
            ListNode even= evenDummy;
            ListNode curr = head;// 辅助遍历原链表

            // 奇数节点和后面的偶数节点不为空
            while (curr != null && curr.next != null) {
                odd.next = curr;//将奇数节点连接到奇数链表上
                odd = odd.next;// 奇数节点后移
                curr = curr.next;// 当前节点后移

                even.next = curr;
                even = even.next;
                curr = curr.next;
            }

            odd.next=null;// 避免成环，断开末尾
            even.next=null;

            // 如果原链表为奇数个节点，则将最后一个奇数节点连接到奇数节点链表的后面
            //if (curr != null && curr.next == null) {
            if (curr != null) {// 若链表为奇数节点，则最后一个节点不空
                odd.next = curr;// 将最后一个奇数节点链接到奇数链表末尾
                odd = odd.next;// 奇数节点后移
            }

            odd.next = evenDummy.next;// 将偶数节点链表连接到奇数节点后面
            return oddDummy.next;
        }
        */




    }
//leetcode submit region end(Prohibit modification and deletion)

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