/**
<p>输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。</p>

<p>例如，一个链表有 <code>6</code> 个节点，从头节点开始，它们的值依次是 <code>1、2、3、4、5、6</code>。这个链表的倒数第 <code>3</code> 个节点是值为 <code>4</code> 的节点。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
给定一个链表: <strong>1->2->3->4->5</strong>, 和 <em>k </em><strong>= 2</strong>.

返回链表 4<strong>->5</strong>.</pre>
<div><div>Related Topics</div><div><li>链表</li><li>双指针</li></div></div><br><div><li>👍 368</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 链表中倒数第k个节点
 * @author wangweizhou
 * @date 2022-06-26 00:04:35
 */

public class LianBiaoZhongDaoShuDiKgeJieDianLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new LianBiaoZhongDaoShuDiKgeJieDianLcof().new Solution();
		  ListNode node1=new ListNode(1);
		  ListNode node2=new ListNode(2);
		  ListNode node3=new ListNode(3);
		  ListNode node4=new ListNode(4);
		  node1.next=node2;
		  node2.next=node3;
		  node3.next=node4;
		  ListNode ans=solution.getKthFromEnd(node1,5);
		  if(ans!=null){
			  System.out.println(ans.val);
		  }else{
			  System.out.println(ans);
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


	// 其他解法：用栈或者扫描两遍用计数器都可以

	// 解法1：快慢指针 写法2
	public ListNode getKthFromEnd(ListNode head, int k) {
		if(head==null||k<=0){// 链表判空和k的规范性
			return null;
		}
		ListNode fast=head;
		ListNode slow=head;
		// 从左向右fast从0开始，fast比slow提前k步，这里注意判别链表节点数目大于k,不然fast会指向空指针。
		// 所以在遍历的时候要注意判别是否遍历到空指针
		for (int i = 0; i < k; i++) {
			// 这里注意判别链表节点数目大于k,不然fast会指向空指针。
			if(fast!=null){
				fast=fast.next;
			}else{// 这里设定k>链表的节点数时，返回null。
				return null;
			}
		}
		// 注意这里fast!=null，所以fast最后会移动链表末尾的空指针域，slow指向倒数第k个节点
		// 从右向左看fast移动到链表的空指针域，相当于0位置，slow比fast提前k步
		while(fast!=null){
			fast=fast.next;
			slow=slow.next;
		}
		return slow;
	}





	/*

	// 解法1：快慢指针
	// 找到倒数第K个节点的前一个节点，这样能避免删除最后一个节点时空节点的处理
	// 1.首先遍历链表，使得右指针与左指针差k个位置
	// 2.左右指针一起移动，直至右指针移动到链表末尾，那么左指针就是链表倒数第k个节点的前一个节点

    public ListNode getKthFromEnd(ListNode head, int k) {
		// 使用哨兵节点方便找倒数第K个节点的前一个节点
		ListNode dummy=new ListNode(-1,head);
		ListNode left=dummy;
		ListNode right=dummy;

		for (int i = 0; i <k ; i++) {
			if(right!=null){
				right=right.next;
			}else{
				return null;
			}

		}
		//判断右指针是否遍历到链表尾部数据节点，
		while(right.next!=null){//这个移动完，right是最后一个
			right=right.next;
			left=left.next;
		}
		return left.next;
    }

	*/

}
//leetcode submit region end(Prohibit modification and deletion)

}
