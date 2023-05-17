/**
 * 给你单链表的头节点 <code>head</code> ，请你反转链表，并返回反转后的链表。
 * <div class="original__bRMd">
 * <div>
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg" style="width: 542px; height: 222px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4,5]
 * <strong>输出：</strong>[5,4,3,2,1]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg" style="width: 182px; height: 222px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2]
 * <strong>输出：</strong>[2,1]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点的数目范围是 <code>[0, 5000]</code></li>
 * <li><code>-5000 <= Node.val <= 5000</code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？</p>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 2589</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 206
 * 反转链表
 *
 * @author wangweizhou
 * @date 2022-06-26 01:03:06
 */

public class ReverseLinkedList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ReverseLinkedList().new Solution();
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

        // 由于节点j的next指针指向了它的前一个节点i，因此链表在节点j和k之间断开，无法在链表中遍历到节点k。
        // 为了避免链表断开，需要在调整节点j的next指针之前把节点k保存下来。
        // 也就是说，在调整节点j的next指针时，除了需要知道节点j本身，还需要知道节点j的前一个节点i，这是因为需要把节点j的next指针指向节点i。
        // 同时，还需要事先保存节点j的下一个节点k，以防止链表断开。
        // 因此，在遍历链表逐个反转每个节点的next指针时需要用到3个指针，分别指向当前遍历到的节点、它的前一个节点及后一个节点。


        //// 解法1：迭代
        //// 反转后链表使用哨兵节点 头插法
        public ListNode reverseList(ListNode head) {
            //头节点为空，或者只有一个头节点,不需要反转
            if (head == null || head.next == null) {
                return head;
            }

            ListNode dummyHead = new ListNode(-1);//反转后链表的哨兵节点
            ListNode curr = head;//curr用来表示从原链表上每次要断开的那个节点
            while (curr != null) {
                ListNode next = curr.next;//临时变量用来保存当前节点的下一个节点
                curr.next = dummyHead.next;//将反转后链表的全部数据节点连接到待插入节点之后
                dummyHead.next = curr;//将新插入的节点curr连接到反转后链表的哨兵节点之后
                curr = next;//curr指向断开节点的后一个
            }
            return dummyHead.next;
        }



        ////   解法1：迭代2 原地反转
        //// 变量cur指向当前遍历到的节点，变量prev指向当前节点的前一个节点，而变量next指向下一个节点。
        //// 每遍历一个节点之后，都让变量prev指向该节点。
        //// 在遍历到尾节点之后，变量prev最后一次被更新，因此，变量prev最终指向原始链表的尾节点，也就是反转链表的头节点。
        //
        //// 因为反转原链表之后，原链表发生了变化，所以下面就用了head来遍历，表示原链表修改后的新头结点
        //public ListNode reverseList(ListNode head) {
        //    // 头节点为空，或者只有一个头节点,不需要反转
        //    if (head == null || head.next == null) {
        //        return head;
        //    }
        //    ListNode curr = head;//curr用来表示从原链表上每次要断开的那个节点
        //    ListNode prev = null;//表示反转后新链表的头结点
        //    while (curr != null) {
        //        ListNode next = curr.next;//临时变量用来保存当前节点的下一个节点
        //        curr.next = prev;//原链表每次断开取下的那个节点使用头插法连接到新链表上
        //        prev = curr;//新链表的头结点指向新插入的数据节点
        //        curr = next;//curr指向断开节点的后一个
        //    }
        //    return prev;//最终遍历完之后,返回反转后链表的头结点newHead
        //}





/*
    //    解法2：递归，没看明白，后面再看

    public ListNode reverseList(ListNode head) {
        //递归终止条件是当前为空，或者下一个节点为空
        if (head==null||head.next == null) {
            return head;
        }

        ListNode newHead=reverseList(head.next);
        //head的下一个节点的指针域指向head,在链表这块其实也就是head的下一个节点指向head
        // head的下一个节点的数据域指向head,下面两步合起来这时候就将head和head的下一个进行反转了。
        head.next.next=head;//(head.next)就是head的下一个节点，（head.next.next）就是head的下一个节点的下一个节点是head.
        head.next = null; //防止链表循环，需要将head.next设置为空，不置空可能最后一步有个循环
        //每层递归函数都返回cur，也就是最后一个节点
        return newHead;
    }*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
