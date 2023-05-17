/**
<p>定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> 1-&gt;2-&gt;3-&gt;4-&gt;5-&gt;NULL
<strong>输出:</strong> 5-&gt;4-&gt;3-&gt;2-&gt;1-&gt;NULL</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 节点个数 &lt;= 5000</code></p>

<p>&nbsp;</p>

<p><strong>注意</strong>：本题与主站 206 题相同：<a href="https://leetcode-cn.com/problems/reverse-linked-list/">https://leetcode-cn.com/problems/reverse-linked-list/</a></p>
<div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 484</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 24
 * 反转链表
 * @author wangweizhou
 * @date 2022-09-22 15:26:49
 */
public class FanZhuanLianBiaoLcof{

	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new FanZhuanLianBiaoLcof().new Solution();
		 ListNode node1=new ListNode(1);
		 ListNode node2=new ListNode(2);
		 ListNode node3=new ListNode(3);
		 ListNode node4=new ListNode(4);
		 node1.next=node2;
		 node2.next=node3;
		 node3.next=node4;
		 ListNode newHead=solution.reverseList(null);
		 if(newHead==null){
			 System.out.println(newHead);
		 }
		 while(newHead!=null){
			 System.out.println(newHead.val);
			 newHead=newHead.next;
		 }
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {

	//
    public ListNode reverseList(ListNode head) {
		if(head==null||head.next==null){
			return head;
		}
		ListNode newhead=null;// 反转后链表的头结点
		while(head!=null){
			ListNode next=head.next;
			head.next= newhead;
			newhead=head;
			head=next;
		}
		return newhead;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
