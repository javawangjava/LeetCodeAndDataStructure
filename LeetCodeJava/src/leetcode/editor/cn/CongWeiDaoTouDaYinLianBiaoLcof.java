/**
<p>输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>head = [1,3,2]
<strong>输出：</strong>[2,3,1]</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 链表长度 &lt;= 10000</code></p>
<div><div>Related Topics</div><div><li>栈</li><li>递归</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍 331</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 06
 * 从尾到头打印链表
 * @author wangweizhou
 * @date 2022-09-14 14:51:00
 */

public class CongWeiDaoTouDaYinLianBiaoLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new CongWeiDaoTouDaYinLianBiaoLcof().new Solution();
		 ListNode node1 = new ListNode(1);
		 ListNode node2 = new ListNode(2);
		 ListNode node3 = new ListNode(3);
		 ListNode node4 = new ListNode(4);
		 ListNode node5 = new ListNode(5);
		 node1.next = node2;
		 node2.next = node3;
		 node3.next = node4;
		 node4.next = node5;
		 print(node1);
		 System.out.println("==========");
		 int[] ans=solution.reversePrint(node1);
		 if(ans.length==0){
			 System.out.println("结果为空");
		 }
		 for (int i = 0; i < ans.length; i++) {
			 System.out.println(ans[i]);
		 }
		 print(node1);
	 }


	private static  void print(ListNode head) {
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
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {


	// 解法2：原地反转链表+计数器获取链表节点个数+反转后并恢复原链表
	public int[] reversePrint(ListNode head) {
		if(head==null){// 判空
			return new int[0];
		}
		ListNode node=head;
		ListNode newHead=null;// 反转后链表的第一个数据节点

		int count=0;// 计数器统计链表节点个数
		// 反转链表
		while(node!=null){
			count++;
			ListNode next=node.next;// 保存当前节点的后继节点
			node.next=newHead;// 将反转后的链表链接到当前节点之后
			newHead=node;// 更新反转后链表的头结点
			node=next;// 当前节点后移
		}

		// 创建结果数组，并将反转后链表的结果加入到结果数组中，并复原链表
		int[] ans=new int[count];
		head=null;// 原链表的头结点，也是复原后链表的头结点
		// 将链表的数据域添加到结果数组中，并将反转后的链表复原
		for (int i = 0; i < count; i++) {
			ans[i]=newHead.val;// 将链表的数据域从后向前加入数组
			ListNode next=newHead.next;
			newHead.next=head;
			head=newHead;
			newHead=next;
		}
		return ans;
	}






	/*

	// 解法1：栈
    public int[] reversePrint(ListNode head) {
		if(head==null){// 判空
			return new int[0];
		}
		Deque<ListNode> stack=new LinkedList<>();
		ListNode node=head;
		// 这里栈中压栈链表节点或者链表节点的数据域都可以，注意前后对应就可以
		// 将链表节点加入栈
		while(node!=null){
			stack.push(node);
			node=node.next;
		}

		int[] nums=new int[stack.size()];// 栈中元素数目就是链表节点数目
		int index=0;
		while(!stack.isEmpty()){
			node=stack.pop();
			nums[index]=node.val;
			index++;
		}
		return nums;
    }
	*/


}
//leetcode submit region end(Prohibit modification and deletion)

}
