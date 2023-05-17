/**
 * <p>给定一个单链表 <code>L</code><em> </em>的头节点 <code>head</code> ，单链表 <code>L</code> 表示为：</p>
 *
 * <pre>
 * L<sub>0</sub> → L<sub>1</sub> → … → L<sub>n - 1</sub> → L<sub>n</sub>
 * </pre>
 *
 * <p>请将其重新排列后变为：</p>
 *
 * <pre>
 * L<sub>0</sub> → L<sub>n</sub> → L<sub>1</sub> → L<sub>n - 1</sub> → L<sub>2</sub> → L<sub>n - 2</sub> → …</pre>
 *
 * <p>不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626420311-PkUiGI-image.png" style="width: 240px; " /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4]
 * <strong>输出：</strong>[1,4,2,3]</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626420320-YUiulT-image.png" style="width: 320px; " /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4,5]
 * <strong>输出：</strong>[1,5,2,4,3]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表的长度范围为 <code>[1, 5 * 10<sup>4</sup>]</code></li>
 * <li><code>1 &lt;= node.val &lt;= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>递归</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍
 * 1006</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 143
 * 重排链表
 *
 * @author wangweizhou
 * @date 2022-08-24 20:45:26
 */

public class ReorderList {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ReorderList().new Solution();
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


        // 首先把链表分成前后两半。然后把后半段链表反转。最后从前半段链表和后半段链表的头节点开始，逐个把它们的节点连接起来形成一个新的链表。
        // 需要首先解决的问题是如何把一个链表分成两半。如果能够找到链表的中间节点，那么就能根据中间节点把链表分割成前后两半。
        // 位于中间节点之前的是链表的前半段，位于中间节点之后的是链表的后半段。
        // 可以使用双节点来寻找链表的中间节点。如果一快一慢两个指针同时从链表的头节点出发，快的指针一次顺着next指针向前走两步，
        // 而慢的指针一次只走一步，那么当快的指针走到链表的尾节点时慢的指针刚好走到链表的中间节点。
        // 一个值得注意的问题是，链表的节点总数既可能是奇数也可能是偶数。当链表的节点总数是奇数时，就要确保链表的前半段比后半段多一个节点。


        // 解法2： 写法2 链表分段 + 链表逆序 + 合并链表
        public void reorderList(ListNode head) {
            if (head == null || head.next == null) {
                return;
            }
            // 链表从中间断开：偶数个节点找前半段的最后一个节点，奇数个节点找中间的节点
            ListNode fast = head.next;
            ListNode slow = head;//偶数个节点找前半段的最后一个节点，奇数个节点找中间的节点
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            ListNode secondHalf = slow.next;// 后半段
            slow.next = null;// 断开
            secondHalf = reverseList(secondHalf);
            ListNode firstHalf = head;// 前半段


            // 将前半段和反转后的后半段交叉连接在一起
            while (secondHalf != null) {
                ListNode firstHalfNext = firstHalf.next;
                ListNode secondHalfNext = secondHalf.next;
                firstHalf.next = secondHalf;
                secondHalf.next = firstHalfNext;
                firstHalf = firstHalfNext;
                secondHalf = secondHalfNext;
            }
        }


        // 反转链表
        private ListNode reverseList(ListNode node) {
            if (node == null || node.next == null) {
                return node;
            }
            ListNode newHead = null;
            while (node != null) {
                ListNode next = node.next;
                node.next = newHead;
                newHead = node;
                node = next;
            }
            return newHead;
        }





        //// 解法3：写法3 链表分段 + 链表逆序 + 合并链表
        //public void reorderList(ListNode head) {
        //    if (head == null || head.next == null) {
        //        return;
        //    }
        //    ListNode dummy = new ListNode(-1);
        //    dummy.next = head;
        //    // 注意若节点总数为奇数，那么slow指向中间节点；若节点总数为偶数，那么slow指向前半段的最后一个节点。起始节点如何设置，这里画图模拟一下就可以
        //    // 变量fast表示走得快的指针，一次走两步，变量slow表示走得慢的指针，一次只走一步。
        //    // 当变量fast指向尾节点时，变量slow指向前半段的最后一个节点。
        //    ListNode fast = dummy;
        //    ListNode slow = dummy;
        //    while (fast != null && fast.next != null) {
        //        fast = fast.next.next;
        //        slow = slow.next;
        //    }
        //    ListNode secondHalf = slow.next;
        //    slow.next = null;
        //
        //    linkList(head, reversrListNode(secondHalf), dummy);
        //}
        //
        //// 合并链表 node1的节点在前，node2的节点在后
        //private void linkList(ListNode node1, ListNode node2, ListNode dummy) {
        //    ListNode prev = dummy;// 哨兵节点
        //    while (node1 != null && node2 != null) {
        //        ListNode node1Next = node1.next;
        //
        //        prev.next = node1;
        //        node1.next = node2;
        //        prev = node2;
        //
        //        node1 = node1Next;
        //        node2 = node2.next;
        //    }
        //    if (node1 != null) {
        //        prev.next = node1;
        //    }
        //}
        //
        //
        //
        //// 反转链表
        //private ListNode reversrListNode(ListNode head) {
        //    if (head == null || head.next == null) {
        //        return head;
        //    }
        //    ListNode prev = null;
        //    ListNode curr = head;
        //    while (curr != null) {
        //        ListNode next = curr.next;
        //        curr.next = prev;
        //        prev = curr;
        //        curr = next;
        //    }
        //    return prev;
        //}





//        // 解法2：写法1 链表分段 + 链表逆序 + 合并链表
//
//        public void reorderList(ListNode head) {
//            if (head == null) {
//                return;
//            }
//            ListNode mid = middleNode(head);//mid是右半端第一个数据节点的前一个
//            ListNode head1 = head;//左半段头结点
//            ListNode head2 = mid.next;//右半段头结点
//            mid.next = null;//将左半段从整个链表中截出，并左半段最后一个节点后面置空
//            head2=reverseList(head2);
//            mergeList(head1, head2);//交替合并链表
//        }
//
//
//        // 找到链表下半场开始位置的前一个，注意while判断条件不同，结果不同
//        private ListNode middleNode(ListNode head) {
//            if (head == null || head.next == null) {
//                return head;
//            }
//            ListNode fast = head;
//            ListNode slow = head;
//            while (fast.next != null && fast.next.next != null) {//找到链表下半场开始位置的前一个
//                //while(fast!=null&&fast.next!=null){ //这个判断条件的话，找到的是下半场开始位置的前一个【链表奇数个】，或者就是下半场开始位置【链表偶数个】
//                fast = fast.next.next;
//                slow = slow.next;
//            }
//            return slow;
//        }
//
//
//        // 反转链表 头插法有哨兵节点
//        private ListNode reverseList(ListNode head) {
//            if (head == null || head.next == null) {
//                return head;
//            }
//            ListNode curr = head;
//            ListNode dummy = new ListNode(-1);//这里使用了哨兵节点，后面有dummy.next，所以dummy一定不能是空引用，不然就空引用异常
//            while (curr != null) {
//                ListNode next = curr.next;
//                curr.next = dummy.next;
//                dummy.next = curr;
//                curr = next;
//            }
//            return dummy.next;
//        }
//
//
//        // 交替合并两个链表
//        private ListNode mergeList(ListNode head1, ListNode head2) {
//            if (head1 == null) {
//                return head2;
//            }
//            if (head2 == null) {
//                return head1;
//            }
//            ListNode curr1 = head1;//链表head1的遍历指针
//            ListNode curr2 = head2;
//            while (curr1 != null && curr2 != null) {
//                ListNode next1 = curr1.next;//临时变量保存curr1的下一个节点
//                ListNode next2 = curr2.next;
//                curr1.next = curr2;
//                curr2.next = next1;
//                curr1 = next1;//链表1节点后移至原链表的后一个
//                curr2 = next2;//链表2节点后移至原链表的后一个
//            }
//            return head1;
//        }
//





         /*

        // 解法1：线性表  写法1
        // 利用线性表存储该链表，然后利用线性表可以下标访问的特点，直接按顺序访问指定元素，重建该链表即可
        public void reorderList(ListNode head) {
            if (head == null) {
                return;
            }
            List<ListNode> list = new ArrayList<>();
            ListNode curr = head;
            while (curr != null) {//遍历链表将链表节点存储在线性表中
                list.add(curr);
                curr = curr.next;
            }

            int len = list.size();
            // 双指针从两侧遍历重建链表
            int left = 0;
            int right = len - 1;
            while (left < right) {
                list.get(left).next = list.get(right);//将原来的节点（n-i）连接到原来节点i之后。
                left++;//左指针后移
                if (left == right) {
                    break;
                }
                list.get(right).next = list.get(left);//将原来的节点（i+1）连接到原来节点（n-i）之后
                right--;
            }
            list.get(left).next = null;//断开已经插入的节点
        }
        */


    }

//leetcode submit region end(Prohibit modification and deletion)

}
