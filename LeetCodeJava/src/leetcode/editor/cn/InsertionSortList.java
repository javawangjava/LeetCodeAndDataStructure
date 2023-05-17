/**
<p>给定单个链表的头<meta charset="UTF-8" />&nbsp;<code>head</code>&nbsp;，使用 <strong>插入排序</strong> 对链表进行排序，并返回&nbsp;<em>排序后链表的头</em>&nbsp;。</p>

<p><strong>插入排序</strong>&nbsp;算法的步骤:</p>

<ol>
	<li>插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。</li>
	<li>每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。</li>
	<li>重复直到所有输入数据插入完为止。</li>
</ol>

<p>下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。</p>

<p>对链表进行插入排序。</p>

<p><img alt="" src="https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif" /></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/04/sort1linked-list.jpg" /></p>

<pre>
<strong>输入:</strong> head = [4,2,1,3]
<strong>输出:</strong> [1,2,3,4]</pre>

<p><strong>示例&nbsp;2：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/04/sort2linked-list.jpg" /></p>

<pre>
<strong>输入:</strong> head = [-1,5,3,4,0]
<strong>输出:</strong> [-1,0,3,4,5]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li>列表中的节点数在&nbsp;<code>[1, 5000]</code>范围内</li>
	<li><code>-5000 &lt;= Node.val &lt;= 5000</code></li>
</ul>
<div><div>Related Topics</div><div><li>链表</li><li>排序</li></div></div><br><div><li>👍 543</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 147
 * 对链表进行插入排序
 * @author wangweizhou
 * @date 2022-08-26 18:51:02
 */

public class InsertionSortList{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new InsertionSortList().new Solution();
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


	// 解法1：遍历找插入点
	// 其实就是插入排序的思路应用在链表这里。链表前一部分有序，后一部分无序。
	// 从无序部分拿第一个和有序的进行比较【head,lastSorted】是有序链表，【lastSorted.next，完】是无序链表
	// 注意从无序部分新拿的节点要是在有序链表中间，链表只能从头开始遍历。

	public ListNode insertionSortList(ListNode head) {
		// 1. 首先判断给定的链表是否为空，若为空，则不需要进行排序，直接返回。
		if(head == null){
			return head;
		}

		// 2. 链表初始化操作
		ListNode dummyHead = new ListNode(0); // 引入哑节点
		dummyHead.next = head;                // 目的是在head之前插入节点
		ListNode lastSorted = head;           // 维护lastSorted为链表已经排好序的最后一个数据节点并初始化，链表插入必须知道待插入位置的前一个节点
		ListNode insert = head.next;            // 维护curr 为待插入的元素并初始化，即待插入元素是有序链表部分的下一个

		// 3. 插入排序
		while(insert != null){
			if(lastSorted.val <= insert.val){ // 说明curr应该位于lastSorted之后，也就是有序链表末尾，待插入节点紧跟着已经排好序的节点的后面
				// lastSorted = curr; // 和下面一个意思
				lastSorted = lastSorted.next;   // 将lastSorted后移一位,curr变成新的lastSorted
			}else{ //  lastSorted.val > curr.val;否则,curr应该位于lastSorted之前，也就是有序链表中间。从链表头结点开始向后遍历链表中的节点
				ListNode prev = dummyHead;      // 从链表头开始遍历 prev是插入节点curr位置的前一个节点
				while(prev.next.val <= insert.val){ // 循环退出的条件是找到curr应该插入的位置
					prev = prev.next;
				}

				// 以下三行是为了完成对curr的插入（配合题解动图可以直观看出）画图
				lastSorted.next = insert.next;// 将带插入元素之后的链表连接到已经排好序的链表节点后面
				insert.next = prev.next;// 将待插入位置之后的链表连接在待插入节点之后
				prev.next = insert;// 将待插入节点链接在待插入位置
			}
			insert = lastSorted.next; // 此时 curr 为下一个待插入的元素
		}
		// 返回排好序的链表
		return dummyHead.next;
	}


}
//leetcode submit region end(Prohibit modification and deletion)

}
