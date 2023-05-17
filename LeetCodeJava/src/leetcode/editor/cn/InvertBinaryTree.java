/**
<p>给你一棵二叉树的根节点 <code>root</code> ，翻转这棵二叉树，并返回其根节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/14/invert1-tree.jpg" style="height: 165px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>root = [4,2,7,1,3,6,9]
<strong>输出：</strong>[4,7,2,9,6,3,1]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/14/invert2-tree.jpg" style="width: 500px; height: 120px;" /></p>

<pre>
<strong>输入：</strong>root = [2,1,3]
<strong>输出：</strong>[2,3,1]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目范围在 <code>[0, 100]</code> 内</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>
<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 1362</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.*;

/**
 * 226
 * 翻转二叉树
 * @author wangweizhou
 * @date 2022-07-25 08:56:18
 */

public class InvertBinaryTree{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new InvertBinaryTree().new Solution();
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

class Solution {


	//求一棵树的镜像的过程：DFS遍历这棵树的每个节点，如果遍历到的节点有子节点，就交换它的两个子节点。
	// 当交换完所有非叶节点的左、右子节点之后，就得到了树的镜像。

	//// 方法1：DFS 深度遍历递归  从下到上依次交换节点的左右子树
    //public TreeNode invertTree(TreeNode root) {
	//	if(root==null){// 空树
	//		return null;
	//	}
	//	TreeNode left=invertTree(root.left);// 先递归⼦树
	//	TreeNode right=invertTree(root.right);
	//
	//	// 处理当前节点，交换左右子树
	//	root.left=right; //交换
	//	root.right=left;
	//	return root;
    //}




	////  方法2：DFS  自上而下依次交换节点的左右子树
	//public TreeNode invertTree(TreeNode root) {
	//	if(root==null){// 空树
	//		return null;
	//	}
	//	// 交换当前节点的左右子树
	//	TreeNode temp=root.right;
	//	root.right=root.left;
	//	root.left=temp;
	////  然后在递归遍历左右子树
	//	invertTree(root.left);//先递归⼦树
	//	invertTree(root.right);
	//	return root;
	//}




	//// 方法3：BFS广度优先变量改编  画图模拟
	//// 在数据子节点进入队列之后将父节点的值交换，这样就能保证从上到下的交换，并且也能保证上一层的交换不会影响下一层的交换

	//public TreeNode invertTree(TreeNode root) {
	//	if(root==null){//空树
	//		return root;
	//	}
	//	Deque<TreeNode> queue=new LinkedList<>();//辅助队列
	//	queue.offer(root);//根节点先进栈
	//	while(!queue.isEmpty()){
	//		TreeNode node=queue.poll();
	//		// 首先当前节点的左右子树判空，非空左右子节点则进入队列
	//		if(node.left!=null){
	//			queue.offer(node.left);
	//		}
	//		if(node.right!=null){
	//			queue.offer(node.right);
	//		}
	//		// 当左右子节点进入队列之后，交换当前节点的左右子节点,这样不会影响下一层的顺序
	//		TreeNode temp=node.left;
	//		node.left=node.right;
	//		node.right=temp;
	//	}
	//	return root;
	//}



	//// 方法3：BFS广度优先变量改编  画图模拟 先交换后进入队列和后进入队列再交换效果是一样的，只是中间过程有些不一样。
	public TreeNode invertTree(TreeNode root) {
		if(root==null){
			return null;
		}
		Deque<TreeNode> nodeDeque=new ArrayDeque<>();
		nodeDeque.offer(root);
		while (!nodeDeque.isEmpty()){
			TreeNode node=nodeDeque.pop();
			TreeNode temp=node.left;
			node.left=node.right;
			node.right=temp;
			if(node.left!=null){
				nodeDeque.offer(node.left);
			}
			if(node.right!=null){
				nodeDeque.offer(node.right);
			}
		}
		return root;
	}



}
//leetcode submit region end(Prohibit modification and deletion)

}
