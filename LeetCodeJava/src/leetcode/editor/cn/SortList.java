/**
 * <p>给你链表的头结点&nbsp;<code>head</code>&nbsp;，请将其按 <strong>升序</strong> 排列并返回 <strong>排序后的链表</strong> 。</p>
 *
 * <ul>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/14/sort_list_1.jpg" style="width: 450px;" />
 * <pre>
 * <b>输入：</b>head = [4,2,1,3]
 * <b>输出：</b>[1,2,3,4]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/14/sort_list_2.jpg" style="width: 550px;" />
 * <pre>
 * <b>输入：</b>head = [-1,5,3,4,0]
 * <b>输出：</b>[-1,0,3,4,5]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <b>输入：</b>head = []
 * <b>输出：</b>[]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><b>提示：</b></p>
 *
 * <ul>
 * <li>链表中节点的数目在范围&nbsp;<code>[0, 5 * 10<sup>4</sup>]</code>&nbsp;内</li>
 * <li><code>-10<sup>5</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>5</sup></code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><b>进阶：</b>你可以在&nbsp;<code>O(n&nbsp;log&nbsp;n)</code> 时间复杂度和常数级空间复杂度下，对链表进行排序吗？</p>
 * <div><div>Related Topics</div><div><li>链表</li><li>双指针</li><li>分治</li><li>排序</li><li>归并排序</li></div></div><br><div
 * ><li>👍 1755</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import org.w3c.dom.ls.LSInput;

/**
 * 148
 * 排序链表
 *
 * @author wangweizhou
 * @date 2022-08-26 17:15:04
 */

public class SortList {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new SortList().new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode ans = solution.sortList(node1);
        solution.print(ans);
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


        // 结合 23一起看
        // 归并排序是否适合链表？归并排序的主要思想是将链表分成两个子链表，在对两个子链表排序后再将它们合并成一个排序的链表。
        // 这看起来没有什么问题，所以可以尝试基于归并排序算法对链表进行排序。
        // 链表的归并排序不需要额外的副本来保存，链表的归并只要把原链表拆分开然后重新连接就可以。

        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode secondHalf=getSecondHalf(head);// 获取右半段的开始节点
            // 递归左右半段
            ListNode left=sortList(head);
            ListNode right=sortList(secondHalf);
            return merge(left,right);// 合并左右半段
        }


        // 快慢指针获取下半段开始节点的前一个节点
        private ListNode getSecondHalf(ListNode head){
            if(head==null||head.next==null){
                return head;
            }
            ListNode fast=head.next;
            ListNode slow=head;
            while (fast!=null&&fast.next!=null){
                fast=fast.next.next;
                slow=slow.next;
            }
            ListNode secondHalf=slow.next;
            slow.next=null;// 断开左右半段
            return secondHalf;
        }



        private ListNode merge(ListNode head1,ListNode head2){
            ListNode dummy=new ListNode(-1);
            ListNode node=dummy;
            // 两个链表非空时，将较小的节点连接到新链表上
            while (head1!=null&&head2!=null){
                if(head1.val<head2.val){
                    node.next=head1;
                    head1=head1.next;
                }else {
                    node.next=head2;
                    head2=head2.next;
                }
                node=node.next;
            }
            // 当两个节点中至少一个遍历完时，那么直接将剩余的那一个没有遍历完的链表连接在新链表尾部
            if(head1==null){
                node.next=head2;
            }
            if(head2==null){
                node.next=head1;
            }
            return dummy.next;
        }





        //// 解法1：递归实现归并排序  写法2
        //// 代码中的函数split将链表分成两半并返回后半部分链表的头节点。
        //// 再将链表分成两半后分别递归地将它们排序，然后调用函数merge将它们合并起来。
        //public ListNode sortList(ListNode head) {
        //    if (head == null || head.next == null) {
        //        return head;
        //    }
        //    ListNode head1 = head;
        //    ListNode head2 = splitMid(head);// 找出后半部分链表头部
        //    head1 = sortList(head1);// 递归左半段
        //    head2 = sortList(head2);// 递归右半段
        //    return merge(head1, head2);// 合并左右半段
        //}
        //
        //
        //// 从中间分割链表并返回后半部分链表的头部
        //// 可以用快慢双指针的思路将链表分成两半。
        //// 如果慢指针一次走一步，快指针一次走两步，当快指针走到链表尾部时，慢指针只走到链表的中央，这样也就找到了链表后半部分的头节点。
        //private ListNode splitMid(ListNode head) {
        //    if (head == null) {
        //        return null;
        //    }
        //    //注意这里fast和slow不是同一个节点开始的
        //    ListNode fast = head.next;
        //    ListNode slow = head;
        //    while (fast != null && fast.next != null) {
        //        fast = fast.next.next;
        //        slow = slow.next;
        //    }
        //    ListNode secondHead = slow.next;
        //    slow.next = null;// 断开前后两部分链表
        //    return secondHead;
        //}
        //
        //
        //// 合并链表  //此处是Leetcode21 --> 合并两个有序链表
        //// 和合并两个排序的子数组类似，也可以用两个指针分别指向两个排序子链表的节点，然后每次选择其中值较小的节点。
        //// 与合并数组不同的是，不需要另外一个链表来保存合并之后的节点，而只需要调整指针的指向。
        //private ListNode merge(ListNode head1, ListNode head2) {
        //    ListNode dummyHead = new ListNode(-1);// 合并后链表的哨兵节点
        //    ListNode node = dummyHead;// 合并后链表的遍历指针
        //    while (head1 != null && head2 != null) {// 两个待合并的子链表都没有遍历完
        //        if (head1.val < head2.val) {// head1 小 ， node的指针域指向head1
        //            node.next = head1;// node的指针域指向head1
        //            head1 = head1.next; // head1 向后走一位
        //        } else {
        //            node.next = head2;
        //            head2 = head2.next;
        //        }
        //        node = node.next;// node后移一位
        //    }
        //    // 执行到这里两个子链表至少有一个已经遍历完了，最多有一个没有遍历完，将没有遍历完的那个子链表直接连接到合并后链表的末尾
        //    if (head1 == null) {
        //        node.next = head2;
        //    }
        //    if (head2 == null) {
        //        node.next = head1;
        //    }
        //    return dummyHead.next;// 最后返回合并后有序的链表
        //}
        //





        // 遍历打印以head开头的链表
        private void print(ListNode head) {
            //  判断链表是否为空
            if (head == null) {
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
//leetcode submit region end(Prohibit modification and deletion)

}


