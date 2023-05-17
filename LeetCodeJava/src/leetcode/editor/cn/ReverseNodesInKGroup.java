/**
 * <p>给你链表的头节点 <code>head</code> ，每&nbsp;<code>k</code><em>&nbsp;</em>个节点一组进行翻转，请你返回修改后的链表。</p>
 *
 * <p><code>k</code> 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是&nbsp;<code>k</code><em>&nbsp;</em>的整数倍，那么请将最后剩余的节点保持原有顺序。</p>
 *
 * <p>你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/reverse_ex1.jpg" style="width: 542px; height:
 * 222px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4,5], k = 2
 * <strong>输出：</strong>[2,1,4,3,5]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/reverse_ex2.jpg" style="width: 542px; height:
 * 222px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4,5], k = 3
 * <strong>输出：</strong>[3,2,1,4,5]
 * </pre>
 *
 * <p>&nbsp;</p>
 * <strong>提示：</strong>
 *
 * <ul>
 * <li>链表中的节点数目为 <code>n</code></li>
 * <li><code>1 &lt;= k &lt;= n &lt;= 5000</code></li>
 * <li><code>0 &lt;= Node.val &lt;= 1000</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你可以设计一个只用 <code>O(1)</code> 额外内存空间的算法解决此问题吗？</p>
 *
 * <ul>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 1771</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 25
 * K 个一组翻转链表
 *
 * @author wangweizhou
 * @date 2022-08-25 15:56:34
 */

public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ReverseNodesInKGroup().new Solution();

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        //node5.next=node6;

        ListNode ans = solution.reverseKGroup(node1, 2);
        print(ans);

        //ListNode[] ans2=solution.reverseList(node1,node3);
        //print(ans2[0]);

    }

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


        // 写法1：遍历 + 反转子串[left,right]
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null) {// 判空
                return head;
            }
            ListNode dummy = new ListNode(-1, head);
            ListNode node = dummy;// 遍历指针
            ListNode prev = dummy;// 待反转子串的前一个节点，方便后续反转后要连接到原链表上
            ListNode left = dummy;//  待反转子串的左节点
            ListNode right = dummy;//  待反转子串的右节点
            int count = 0;// 计数器

            while (node != null) {
                node = node.next;
                count++;
                // count==k 待反转链表长度为k。 node != null：表示只反转链表实际长度为k的子链表。
                if (count == k && node != null) {// 子串中包含k个数据节点，反转这个子串
                    ListNode kNext = node.next;// 保留子串的后一个节点，后续将反转链表要连接上去
                    // 待反转子串的左右子节点
                    left = prev.next;
                    right = node;
                    ListNode[] reverseList = reverseList(left, right);// 反转子串
                    // 将反转后子串连接到原链表上
                    prev.next = reverseList[0];
                    reverseList[1].next = kNext;

                    // 待反转子串的前一个节点后移，链表的遍历指针node后移
                    prev = reverseList[1];// 新的子串的前一个节点就是刚才反转后子串的尾节点
                    // 根据链表的遍历指针node指针的初始值【指向待反转链表的前一个位置】，链表的遍历指针 node 指向待反转子链表的前一个位置。
                    node = prev;// node=reverseList[1];这两个语句作用一样。遍历节点指向待反转子串的前一个。
                    count = 0;// 计数器归零
                }
            }
            return dummy.next;
        }


        // 反转链表【head,tail】  没有哨兵节点
        private ListNode[] reverseList(ListNode head, ListNode tail) {
            ListNode node = head;
            ListNode newHead = null;// 反转后链表的头节点
            while (node != null) {
                ListNode next = node.next;
                node.next = newHead;
                newHead = node;
                node = next;
                if (newHead == tail) {// 反转后链表的头节点是原链表的尾节点，则反转结束
                    break;
                }
            }
            return new ListNode[]{tail, head};
        }





        //// 解法1：链表节点形成的滑动窗口是双闭的[start，end]
        //public ListNode reverseKGroup(ListNode head, int k) {
        //    if (head == null) {
        //        return head;
        //    }
        //    ListNode dummy = new ListNode(-1, head);
        //    ListNode left = head;// start是待反转子串的第一个节点
        //    ListNode prev = dummy;//待反转子串的前一个节点
        //
        //    // 子串[left,right],left是链表开始节点，right是链表结束节点
        //    while (left != null) {
        //        ListNode right = prev;//滑动窗口右边界是从该组元素节点的前一个开始的
        //        // 移动一次加入一个节点，所以若是从该组的前一个节点开始，那么就移动k次。若是从该组的第一个节点开始移动，那么就移动（k-1）次
        //        // 循环加入一个k长度的链表[start,end]
        //        for (int i = 0; i < k; i++) {
        //            right = right.next;
        //            if (right == null) {
        //                return dummy.next;
        //            }
        //        }
        //
        //        ListNode next = right.next;// 保留子串的后一个节点，后续将反转链表要连接上去
        //        ListNode[] reverse = reverseListNode(left, right);
        //        left = reverse[0];//反转后第K组链表的头结点
        //        right = reverse[1];//反转后第K组链表的尾结点
        //
        //        // 将反转后的第k组链表连接到原链表中
        //        prev.next = left;
        //        right.next = next;
        //
        //        prev = right;  // pre后移至下一个待反转子串的前一个节点
        //        left = right.next;// 待反转子串的开始节点后移
        //    }
        //    return dummy.next;
        //}
        //
        //
        //
        //
        //// 反转链表  没有哨兵节点
        //private ListNode[] reverseListNode(ListNode head, ListNode tail) {
        //    ListNode curr = head;
        //    ListNode newHead = null;
        //    while (curr != tail) {
        //        ListNode next = curr.next;
        //        curr.next = newHead;
        //        newHead = curr;
        //        curr = next;
        //    }
        //    curr.next = newHead;// 其实就是curr.next=newHead
        //    return new ListNode[]{tail, head};
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
