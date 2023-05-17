/**
<p>完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 <code>n</code> 层有 <code>2<sup>n-1</sup></code>&nbsp;个节点）的，并且所有的节点都尽可能地集中在左侧。</p>

<p>设计一个用完全二叉树初始化的数据结构&nbsp;<code>CBTInserter</code>，它支持以下几种操作：</p>

<ul>
	<li><code>CBTInserter(TreeNode root)</code>&nbsp;使用根节点为&nbsp;<code>root</code>&nbsp;的给定树初始化该数据结构；</li>
	<li><code>CBTInserter.insert(int v)</code>&nbsp; 向树中插入一个新节点，节点类型为 <code>TreeNode</code>，值为 <code>v</code> 。使树保持完全二叉树的状态，<strong>并返回插入的新节点的父节点的值</strong>；</li>
	<li><code>CBTInserter.get_root()</code> 将返回树的根节点。</li>
</ul>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>inputs = [&quot;CBTInserter&quot;,&quot;insert&quot;,&quot;get_root&quot;], inputs = [[[1]],[2],[]]
<strong>输出：</strong>[null,1,[1,2]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>inputs = [&quot;CBTInserter&quot;,&quot;insert&quot;,&quot;insert&quot;,&quot;get_root&quot;], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
<strong>输出：</strong>[null,3,4,[1,2,3,4,5,6,7,8]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>最初给定的树是完全二叉树，且包含&nbsp;<code>1</code>&nbsp;到&nbsp;<code>1000</code>&nbsp;个节点。</li>
	<li>每个测试用例最多调用&nbsp;<code>CBTInserter.insert</code>&nbsp; 操作&nbsp;<code>10000</code>&nbsp;次。</li>
	<li>给定节点或插入节点的每个值都在&nbsp;<code>0</code>&nbsp;到&nbsp;<code>5000</code>&nbsp;之间。</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 919&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/complete-binary-tree-inserter/">https://leetcode-cn.com/problems/complete-binary-tree-inserter/</a></p>
<div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li><li>设计</li><li>二叉树</li></div></div><br><div><li>👍 44</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer II 043
 * 往完全二叉树添加节点
 * @author wangweizhou
 * @date 2022-11-16 11:03:14
 */

public class NaqhDT{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 //Solution solution = new NaqhDT().new Solution();

	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class CBTInserter {

	private Queue<TreeNode> queue;
	private TreeNode root;

	// 初始化队列，保存二叉树的根，并将有左右子节点的节点全部加入队列中
	public CBTInserter(TreeNode root) {// 构造器初始化
		this.root = root;
		queue = new LinkedList<>();
		queue.offer(root);
		// 把将左右节点双全的移出队列，队列中保留最多只有一个子节点的节点
		// 因为是完全二叉树，所以左右子树都不空等价于右子树不空。
		while (queue.peek().left != null && queue.peek().right != null) {
			TreeNode node = queue.poll();
			queue.offer(node.left);
			queue.offer(node.right);
		}
	}


	// 进行到这里，队列中的节点都是最多只有一个子节点，也就是左子节点。
	public int insert(int v) {
		// 当前队列的第一个，一定是没有子节点或者只有左子节点
		// 如果当前节点缺少左子节点，则待插入节点连接到当前节点【栈顶节点】的左子节点；
		// 如果当前节点缺少右子节点，则待插入节点连接到当前节点【栈顶节点】的右子节点；
		// 那么这个栈顶节点就左右节点齐全了，将栈顶元素出栈，栈顶元素的子节点入栈。
		TreeNode parent = queue.peek();// 节点出队，待插入节点的父节点
		TreeNode node = new TreeNode(v);// 待插入节点

		if (parent.left == null) {
			parent.left = node;
		} else {
			parent.right = node;

			queue.poll();
			queue.offer(parent.left);
			queue.offer(parent.right);
		}
		return parent.val;
	}


	// 获取二叉树的根节点
	public TreeNode get_root() {
		return this.root;
	}


}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
