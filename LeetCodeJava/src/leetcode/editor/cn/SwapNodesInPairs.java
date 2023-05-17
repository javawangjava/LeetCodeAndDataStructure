/**
 * <p>给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/swap_ex1.jpg" style="width: 422px; height: 222px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4]
 * <strong>输出：</strong>[2,1,4,3]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点的数目在范围 <code>[0, 100]</code> 内</li>
 * <li><code>0 &lt;= Node.val &lt;= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 1429</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 24
 * 两两交换链表中的节点
 */

public class SwapNodesInPairs {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new SwapNodesInPairs().new Solution();

        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(5);
        ListNode node6=new ListNode(6);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        //node5.next=node6;

        ListNode ans=solution.swapPairs(node1);
        print(ans);

    }

    private static  void print(ListNode head) {
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



        // 解法2：遍历
        // 因为要交换节点，所以需要找到待两个待交换节点的前一个节点。
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummy = new ListNode(-1, head);
            ListNode pre = dummy;// pre表示待交换的两个节点前面的节点。也就是每次需要交换pre后面的两个节点

            // 终止条件 pre 的后面没有节点或者只有一个节点，则没有更多的节点需要交换
            while (pre.next != null && pre.next.next != null) {
                // 创建交换辅助指针，帮助暂存部分数据
                ListNode first = pre.next;// first表示待交换的两个节点中的第一个
                ListNode second = pre.next.next;// second表示待交换的两个节点中的第二个

                // 交换两个相邻节点，示意图看着很清晰
                first.next = second.next;// 将第二个节点之后的连接到第一个节点之后
                second.next = first;// 将第一个节点连接到第二个节点之后
                pre.next = second;// 将交换后的节点连接到这两个节点前面的节点之后

                pre = first;// pre后移到已经完成交换的链表的末尾。
            }
            return dummy.next;
        }





        //// 遍历 写法2：记忆保留断开时的节点
        //public ListNode swapPairs(ListNode head) {
        //    if(head==null||head.next==null){// 空链表和只有一个节点
        //        return head;
        //    }
        //    ListNode dummy=new ListNode(-1,head);// 哨兵节点
        //    ListNode node=dummy;//
        //    while(node.next!=null&&node.next.next!=null){
        //        ListNode prev =node;
        //        ListNode first =node.next;
        //        ListNode second =node.next.next;
        //        ListNode next=second.next;
        //
        //        prev.next= second;
        //        second.next= first;
        //        first.next= next;
        //        node=first;
        //    }
        //    return dummy.next;
        //}




        /*
        // 方法1：递归
        // 递归的终止条件是链表中没有节点，或者链表中只有一个节点，此时无法进行交换。
        // 如果链表中至少有两个节点，则在两两交换链表中的节点之后，原始链表的头节点变成新的链表的第二个节点，原始链表的第二个节点变成新的链表的头节点。
        // 链表中的其余节点的两两交换可以递归地实现。
        // 在对链表中的其余节点递归地两两交换之后，更新节点之间的指针关系，即可完成整个链表的两两交换。

        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {// 递归的终止条件是链表中没有节点，或者链表中只有一个节点
                return head;
            }
            // 第一个节点之后的第二个节点会成为新的第一个节点。
            ListNode newHead = head.next;// 用 newHead 表示新的链表的头节点，原始链表的第二个节点
            // 本来把head.next =newHead.next，就完成了交换，但是这是递归，所以要连接交换后的节点
            head.next = swapPairs(newHead.next);// 将第二个节点之后的节点全部进行交换之后再连接到第一个节点之后。
            newHead.next = head;// 将第一个节点连接到第二个节点之后
            return newHead;
        }

        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
