/**
 * <p>给定一个单链表 <code>L</code><em> </em>的头节点 <code>head</code> ，单链表 <code>L</code> 表示为：</p>
 *
 * <p><code>&nbsp;L<sub>0&nbsp;</sub>&rarr; L<sub>1&nbsp;</sub>&rarr; &hellip; &rarr; L<sub>n-1&nbsp;</sub>&rarr;
 * L<sub>n&nbsp;</sub></code><br />
 * 请将其重新排列后变为：</p>
 *
 * <p><code>L<sub>0&nbsp;</sub>&rarr;&nbsp;L<sub>n&nbsp;</sub>&rarr;&nbsp;L<sub>1&nbsp;</sub>&rarr;&nbsp;
 * L<sub>n-1&nbsp;</sub>&rarr;&nbsp;L<sub>2&nbsp;</sub>&rarr;&nbsp;L<sub>n-2&nbsp;</sub>&rarr; &hellip;</code></p>
 *
 * <p>不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626420311-PkUiGI-image.png" style="width: 240px; " /></p>
 *
 * <pre>
 * <strong>输入: </strong>head = [1,2,3,4]
 * <strong>输出: </strong>[1,4,2,3]</pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626420320-YUiulT-image.png" style="width: 320px; " /></p>
 *
 * <pre>
 * <strong>输入: </strong>head = [1,2,3,4,5]
 * <strong>输出: </strong>[1,5,2,4,3]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表的长度范围为 <code>[1, 5 * 10<sup>4</sup>]</code></li>
 * <li><code>1 &lt;= node.val &lt;= 1000</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><meta charset="UTF-8" />注意：本题与主站 143&nbsp;题相同：
 * <a href="https://leetcode-cn.com/problems/reorder-list/">https://leetcode-cn.com/problems/reorder-list/</a>&nbsp;</p>
 * <div><div>Related Topics</div><div><li>栈</li><li>递归</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍
 * 80</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer II 026
 * 重排链表
 * @author wangweizhou
 * @date 2022-11-08 23:56:56
 */
public class LGjMqU {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LGjMqU().new Solution();

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        //node1.next = node2;
        //node2.next = node3;
        //node3.next = node4;
        //node4.next = node5;
        //node5.next = node6;

		solution.reorderList(node1);
		print(node1);
	}


	private static void print(ListNode head) {
		//   判断链表是否为空
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


//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    class Solution {

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
            secondHalf = reverseList(secondHalf);
            ListNode firstHalf = head;// 前半段
            slow.next = null;

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


    }
//leetcode submit region end(Prohibit modification and deletion)

}
