/**
<p>给定一个链表的 <strong>头节点&nbsp;</strong><code>head</code><strong>&nbsp;，</strong>请判断其是否为回文链表。</p>

<p>如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://pic.leetcode-cn.com/1626421737-LjXceN-image.png" /></strong></p>

<pre>
<strong>输入:</strong> head = [1,2,3,3,2,1]
<strong>输出:</strong> true</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://pic.leetcode-cn.com/1626422231-wgvnWh-image.png" style="width: 138px; height: 62px;" /></strong></p>

<pre>
<strong>输入:</strong> head = [1,2]
<strong>输出:</strong> false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表 L 的长度范围为 <code>[1, 10<sup><span style="font-size: 9.449999809265137px;">5</span></sup>]</code></li>
	<li><code>0&nbsp;&lt;= node.val &lt;= 9</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>能否用&nbsp;O(n) 时间复杂度和 O(1) 空间复杂度解决此题？</p>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 234&nbsp;题相同：<a href="https://leetcode-cn.com/problems/palindrome-linked-list/">https://leetcode-cn.com/problems/palindrome-linked-list/</a></p>
<div><div>Related Topics</div><div><li>栈</li><li>递归</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍 84</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer II 027
 * 回文链表
 * @author wangweizhou
 * @date 2022-11-09 00:45:18
 */
public class AMhZSa{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new AMhZSa().new Solution();
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
    public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
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


		// 对比前半段和后半段的节点
		//while (secondHalf!=null&&firstHalf!=null) {
		while (secondHalf!=null) {
			if(secondHalf.val==firstHalf.val){
				secondHalf=secondHalf.next;
				firstHalf=firstHalf.next;
			}else{
				return false;
			}
		}
		return true;
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
