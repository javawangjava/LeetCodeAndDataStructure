/**
 * <p>给定一个头结点为 <code>head</code> 的非空单链表，返回链表的中间结点。</p>
 *
 * <p>如果有两个中间结点，则返回第二个中间结点。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>[1,2,3,4,5]
 * <strong>输出：</strong>此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>[1,2,3,4,5,6]
 * <strong>输出：</strong>此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>给定链表的结点数介于 <code>1</code> 和 <code>100</code> 之间。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>链表</li><li>双指针</li></div></div><br><div><li>👍 670</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 876
 * 链表的中间结点
 * @author wangweizhou
 * @date 2022-08-26 20:37:43
 */

public class MiddleOfTheLinkedList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MiddleOfTheLinkedList().new Solution();
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
        //解法1：快慢指针
        public ListNode middleNode(ListNode head) {

            //if(head==null){// 其实后面已经包含了判空，所以这里可以不用单独写
            //    return head;
            //}
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
