/**
<p>给你二叉树的根结点&nbsp;<code>root</code>&nbsp;，此外树的每个结点的值要么是 <code>0</code> ，要么是 <code>1</code> 。</p>

<p>返回移除了所有不包含 <code>1</code> 的子树的原二叉树。</p>

<p>节点 <code>node</code> 的子树为 <code>node</code> 本身加上所有 <code>node</code> 的后代。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/06/1028_2.png" style="width: 500px; height: 140px;" />
<pre>
<strong>输入：</strong>root = [1,null,0,0,1]
<strong>输出：</strong>[1,null,0,null,1]
<strong>解释：</strong>
只有红色节点满足条件“所有不包含 1 的子树”。 右图为返回的答案。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/06/1028_1.png" style="width: 500px; height: 115px;" />
<pre>
<strong>输入：</strong>root = [1,0,1,0,0,0,1]
<strong>输出：</strong>[1,null,1,null,1]
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/05/1028.png" style="width: 500px; height: 134px;" />
<pre>
<strong>输入：</strong>root = [1,1,0,1,1,0,1,0]
<strong>输出：</strong>[1,1,0,1,1,null,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 200]</code> 内</li>
	<li><code>Node.val</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>
<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 305</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 814
 * 二叉树剪枝
 * @author wangweizhou
 * @date 2022-09-05 09:54:31
 */

public class BinaryTreePruning{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new BinaryTreePruning().new Solution();

		 TreeNode node1 = new TreeNode(1);
		 TreeNode node2 = new TreeNode(0);
		 TreeNode node3 = new TreeNode(0);
		 TreeNode node4 = new TreeNode(4);
		 TreeNode node5 = new TreeNode(5);
		 TreeNode node6 = new TreeNode(0);
		 TreeNode node7 = new TreeNode(1);


		 // 手动创建二叉树
		 //node1.left=node2;
		 node1.right=node3;
		 //node2.left=node4;
		 //node2.right=node5;
		 node3.left=node6;
		 node3.right=node7;
		 solution.pruneTree(node1);

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


	// 也就是说，如果一个节点可以被删除，那么它的子树的所有节点都可以被删除。
	// 由此发现，后序遍历最适合用来解决这个问题。如果用后序遍历的顺序遍历到某个节点，那么它的左右子树的节点一定已经遍历过了。
	// 每遍历到一个节点，就要确定它是否有左右子树，如果左右子树都是空的，并且节点的值是0，那么也就可以删除这个节点。
	// 所谓删除一个节点，就是返回null给它的父节点，这样这个节点就从这棵二叉树中消失。


	//// 解法1：递归DFS  必须是后序遍历  自底向上剪枝，先要递归左右子树，然后才能判断当前节点是否保留
	//// 剪枝的条件是到达叶子节点并且叶子节点的值是0，这时候删除叶子节点。从下到上剪枝才能保证每次遇到的是叶子节点并且叶子节点的值是0。


    public TreeNode pruneTree(TreeNode root) {
		if(root==null){
			return root;
		}
		root.left=pruneTree(root.left);
		root.right=pruneTree(root.right);
		if(root.left==null&&root.right==null&&root.val==0){
			return null;
		}
		return root;
    }



	//// 注意下面这种写法错着
	//public TreeNode pruneTree(TreeNode root) {
	//	if(root==null){
	//		return null;
	//	}
	//	dfs(root);
	//	return root;
	//}
	//
	//private void dfs(TreeNode root){
	//	if(root==null){
	//		return;
	//	}
	//	dfs(root.left);
	//	dfs(root.right);
	//	if(root.left==null&&root.right==null&&root.val==0){
	//      // 二叉树的某一个节点置空，只能通过该节点的父节点来置空
	//		root=null;// 这里只是将变量root指向了一个空引用，然后二叉树中该节点并没有改变。并不能修改元素root指向的那个变量
	//	}
	//}



}
//leetcode submit region end(Prohibit modification and deletion)

}
