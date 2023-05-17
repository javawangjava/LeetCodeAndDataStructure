/**
<p>多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。</p>

<p>给定位于列表第一级的头节点，请扁平化列表，即将这样的多级双向链表展平成普通的双向链表，使所有结点出现在单级双链表中。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
<strong>输出：</strong>[1,2,3,7,8,11,12,9,10,4,5,6]
<strong>解释：
</strong>
输入的多级列表如下图所示：

<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/multilevellinkedlist.png" style="height: 363px; width: 640px;" />

扁平化后的链表如下图：

<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/multilevellinkedlistflattened.png" style="height: 80px; width: 1100px;" />
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [1,2,null,3]
<strong>输出：</strong>[1,3,2]
<strong>解释：

</strong>输入的多级列表如下图所示：

  1---2---NULL
  |
  3---NULL
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>head = []
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>如何表示测试用例中的多级链表？</strong></p>

<p>以 <strong>示例 1</strong> 为例：</p>

<pre>
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL</pre>

<p>序列化其中的每一级之后：</p>

<pre>
[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
</pre>

<p>为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。</p>

<pre>
[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
</pre>

<p>合并所有序列化结果，并去除末尾的 null 。</p>

<pre>
[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>节点数目不超过 <code>1000</code></li>
	<li><code>1 &lt;= Node.val &lt;= 10^5</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 430&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/">https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/</a></p>
<div><div>Related Topics</div><div><li>深度优先搜索</li><li>链表</li><li>双向链表</li></div></div><br><div><li>👍 53</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer II 028
 * 展平多级双向链表
 * @author wangweizhou
 * @date 2022-11-09 00:55:20
 */
public class Qv1Da2{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new Qv1Da2().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
	// 写法1:链表要断开，可以用临时变量保留下一个节点
	public Node flatten(Node head) {
		flattenGetTail(head);
		return head;
	}


	// 展开以head为头节点的链表，展开之后头节点是head,尾节点是tail
	private Node flattenGetTail(Node head){
		Node node=head;// 遍历节点
		Node tail=null;// 当前遍历到的链表的尾节点
		while (node!=null){
			Node next=node.next;// 保留当前节点的下一个节点
			// 链表有子链，展开之后头节点是child,尾节点是childTail
			if(node.child!=null){
				Node child=node.child;// 子链的头节点
				Node childTail=flattenGetTail(node.child);// 子链的尾节点
				node.child=null;// 断开子链

				// 将整个子链插入到节点node和node的下一个节点之间
				// 将子链头节点插入node之后
				node.next=child;
				child.prev=node;

				// 将尾节点和node的下一个节点连接起来
				childTail.next=next;
				if(next!=null){
					next.prev=childTail;
				}
				tail=childTail;// 子链插入主链之后更新尾节点
			}else{
				tail=node;// 更新当前遍历到的链表的尾节点
			}
			node=next;// 遍历节点后移
		}
		return tail;
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}
