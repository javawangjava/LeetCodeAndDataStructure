/**
<p>输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。</p>

<p><strong>示例1：</strong></p>

<pre><strong>输入：</strong>1-&gt;2-&gt;4, 1-&gt;3-&gt;4
<strong>输出：</strong>1-&gt;1-&gt;2-&gt;3-&gt;4-&gt;4</pre>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 链表长度 &lt;= 1000</code></p>

<p>注意：本题与主站 21 题相同：<a href="https://leetcode-cn.com/problems/merge-two-sorted-lists/">https://leetcode-cn.com/problems/merge-two-sorted-lists/</a></p>
<div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 282</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.List;

/**
 * 剑指 Offer 25
 * 合并两个排序的链表
 * @author wangweizhou
 * @date 2022-09-22 15:35:59
 */
public class HeBingLiangGePaiXuDeLianBiaoLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new HeBingLiangGePaiXuDeLianBiaoLcof().new Solution();
		 ListNode node1=new ListNode(1);
		 ListNode node2=new ListNode(2);
		 ListNode node3=new ListNode(3);
		 ListNode node4=new ListNode(9);

		 ListNode node5=new ListNode(3);
		 ListNode node6=new ListNode(6);
		 ListNode node7=new ListNode(7);
		 ListNode node8=new ListNode(9);

		 //node1.next=node2;
		 //node2.next=node3;
		 //node3.next=node4;
		 //
		 //node5.next=node6;
		 //node6.next=node7;
		 //node7.next=node8;

		 ListNode newHead=solution.mergeTwoLists(null,null);
		 //ListNode newHead=solution.mergeTwoLists(node1,node5);
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		// 后面的写法包含链表为空的情况，这里只是单独写出来了，
		if(l1==null){
			return l2;
		}
		if(l2==null){
			return l1;
		}
		ListNode node1=l1;
		ListNode node2=l2;
		ListNode dummy=new ListNode(-1);
		ListNode node=dummy;
		while(node1!=null&&node2!=null){
			if(node1.val<node2.val){
				//node.next=new ListNode(node1.val);
				node.next=node1;//直接连接到合并后的链表上，没必要创建新的数据节点
				node1=node1.next;
			}else {
				//node.next=new ListNode(node2.val);
				node.next=node2;
				node2=node2.next;
			}
			node=node.next;
		}

		if(node1==null){
			node.next=node2;
		}else{
			node.next=node1;
		}
		return dummy.next;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
