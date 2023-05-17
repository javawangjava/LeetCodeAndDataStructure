/**
 * <p>给定两个 <strong>非空链表</strong> <code>l1</code>和 <code>l2</code>&nbsp;
 * 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。</p>
 *
 * <p>可以假设除了数字 0 之外，这两个数字都不会以零开头。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例1：</strong></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626420025-fZfzMX-image.png" style="width: 302px; " /></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [7,2,4,3], l2 = [5,6,4]
 * <strong>输出：</strong>[7,8,0,7]
 * </pre>
 *
 * <p><strong>示例2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [2,4,3], l2 = [5,6,4]
 * <strong>输出：</strong>[8,0,7]
 * </pre>
 *
 * <p><strong>示例3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [0], l2 = [0]
 * <strong>输出：</strong>[0]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表的长度范围为<code> [1, 100]</code></li>
 * <li><code>0 &lt;= node.val &lt;= 9</code></li>
 * <li>输入数据保证链表代表的数字无前导 0</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><meta charset="UTF-8" />注意：本题与主站 445&nbsp;题相同：
 * <a href="https://leetcode-cn.com/problems/add-two-numbers-ii/">https://leetcode-cn.com/problems/add-two-numbers-ii/</a></p>
 * <div><div>Related Topics</div><div><li>栈</li><li>链表</li><li>数学</li></div></div><br><div><li>👍 69</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;


/**
 * 剑指 Offer II 025
 * 链表中的两数相加
 *
 * @author wangweizhou
 * @date 2022-11-08 21:50:33
 */
public class LMSNwu {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LMSNwu().new Solution();

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        //node2.next = node3;
        //node3.next=node4;
        node4.next = node5;
        node5.next = node6;

        ListNode ans = solution.addTwoNumbers(node1, node4);
        print(ans);


    }


    private static void print(ListNode head) {
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


        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1== null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            }

            ListNode revL1 = reverseList(l1);
            ListNode revL2 = reverseList(l2);

            int sum = 0;
            int carry = 0;
            ListNode node = new ListNode(0);
            ListNode sumNode = node;

            while (revL1 != null || revL2 != null) {
                int val1 = revL1 != null ? revL1.val : 0;
                int val2 = revL2 != null ? revL2.val : 0;
                sum = val1 + val2 + carry;
                carry = sum >= 10 ? 1 : 0;
                sum = sum >= 10 ? sum - 10 : sum;
                sumNode.next = new ListNode(sum);
                sumNode = sumNode.next;
                if(revL1!=null){
                    revL1 = revL1.next;
                }
                if(revL2!=null){
                    revL2 = revL2.next;
                }
            }
            if (carry == 1) {
                sumNode.next = new ListNode(1);
                sumNode = sumNode.next;
            }
            return reverseList(node.next);
        }


        private ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode newHead = null;
            while (head != null) {
                ListNode next = head.next;
                head.next = newHead;
                newHead = head;
                head = next;
            }
            return newHead;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
