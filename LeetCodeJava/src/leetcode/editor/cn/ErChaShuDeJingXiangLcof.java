/**
<p>请完成一个函数，输入一个二叉树，该函数输出它的镜像。</p>

<p>例如输入：</p>

<p><code>&nbsp; &nbsp; &nbsp;4<br>
&nbsp; &nbsp;/ &nbsp; \<br>
&nbsp; 2 &nbsp; &nbsp; 7<br>
&nbsp;/ \ &nbsp; / \<br>
1 &nbsp; 3 6 &nbsp; 9</code><br>
镜像输出：</p>

<p><code>&nbsp; &nbsp; &nbsp;4<br>
&nbsp; &nbsp;/ &nbsp; \<br>
&nbsp; 7 &nbsp; &nbsp; 2<br>
&nbsp;/ \ &nbsp; / \<br>
9 &nbsp; 6 3&nbsp; &nbsp;1</code></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>root = [4,2,7,1,3,6,9]
<strong>输出：</strong>[4,7,2,9,6,3,1]
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 节点个数 &lt;= 1000</code></p>

<p>注意：本题与主站 226 题相同：<a href="https://leetcode-cn.com/problems/invert-binary-tree/">https://leetcode-cn.com/problems/invert-binary-tree/</a></p>
<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 307</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 27
 * 二叉树的镜像
 * @author wangweizhou
 * @date 2022-09-15 00:45:41
 */
public class ErChaShuDeJingXiangLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new ErChaShuDeJingXiangLcof().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

	/*

	//解法2：深度遍历 递归 前序遍历    从上到下好理解
	public TreeNode mirrorTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		// 交换当前节点的左右节点，处理当前节点
		TreeNode temp=root.left;// 临时变量保存左子节点
		root.left=root.right;
		root.right=temp;

		mirrorTree(root.left);// 遍历左子树
		mirrorTree(root.right);
		return root;
	}

	*/


/*
	//解法2：深度遍历  递归 后序遍历   从下到上
	public TreeNode mirrorTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		TreeNode left=mirrorTree(root.left);// 遍历左子树，注意要保留当前节点遍历后的左子树
		TreeNode right=mirrorTree(root.right);
		// 交换当前左右子树
		root.left=right;
		root.right=left;
		return root;
	}

	*/



	// 解法2： 层序遍历   从上到下
	// 将当前节点的左右子节点添加到队列之后，再交换当前节点的左右子节点
    public TreeNode mirrorTree(TreeNode root) {
		if(root==null){
			return root;
		}
		Deque<TreeNode> queue=new LinkedList<>();
		queue.add(root);
		while(queue.size()>0){
			TreeNode node=queue.poll();
			if(node.left!=null){
				queue.add(node.left);
			}
			if(node.right!=null){
				queue.add(node.right);
			}
			TreeNode temp=node.left;
			node.left=node.right;
			node.right=temp;
		}
		return root;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
