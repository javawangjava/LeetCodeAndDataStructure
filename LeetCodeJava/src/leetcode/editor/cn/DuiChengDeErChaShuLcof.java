/**
<p>请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。</p>

<p>例如，二叉树&nbsp;[1,2,2,3,4,4,3] 是对称的。</p>

<p><code>&nbsp; &nbsp; 1<br>
&nbsp; &nbsp;/ \<br>
&nbsp; 2 &nbsp; 2<br>
&nbsp;/ \ / \<br>
3 &nbsp;4 4 &nbsp;3</code><br>
但是下面这个&nbsp;[1,2,2,null,3,null,3] 则不是镜像对称的:</p>

<p><code>&nbsp; &nbsp; 1<br>
&nbsp; &nbsp;/ \<br>
&nbsp; 2 &nbsp; 2<br>
&nbsp; &nbsp;\ &nbsp; \<br>
&nbsp; &nbsp;3 &nbsp; &nbsp;3</code></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>root = [1,2,2,3,4,4,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>root = [1,2,2,null,3,null,3]
<strong>输出：</strong>false</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 节点个数 &lt;= 1000</code></p>

<p>注意：本题与主站 101 题相同：<a href="https://leetcode-cn.com/problems/symmetric-tree/">https://leetcode-cn.com/problems/symmetric-tree/</a></p>
<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 382</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 28
 * 对称的二叉树
 * @author wangweizhou
 * @date 2022-09-15 00:49:59
 */
public class DuiChengDeErChaShuLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new DuiChengDeErChaShuLcof().new Solution();
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

	// 解法2： DFS 深度遍历 前序遍历
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		return isSymmetricFunc(root,root);
		//return isSymmetricFunc(root.left,root.right);
	}



	// 判断以node1和node2为根节点的两个树是否对称
	private boolean isSymmetricFunc(TreeNode node1,TreeNode node2){
		if(node1==null&&node2==null){// 两个节点为空
			return true;
		}
		if(node1==null||node2==null){// 有一个节点为空
			return false;
		}
		if(node1.val!=node2.val){// 两个节点非空，但是两个节点的数据值不相同
			return false;
		}
		//递归遍历左右子树
		return isSymmetricFunc(node1.left,node2.right)&&isSymmetricFunc(node1.right,node2.left);
	}




/*
	// 解法1： 层次遍历， // 因为要判断是否对称，所以这里需要将数据节点的空叶子节点加入队列，占位
    public boolean isSymmetric(TreeNode root) {
		if(root==null){
			return true;
		}
		Deque<TreeNode> queue=new LinkedList<>();
		queue.add(root.left);
		queue.add(root.right);
		while(queue.size()>0){
			TreeNode left=queue.poll();
			TreeNode right=queue.poll();
			if(left==null&&right==null){// 左右节点都是空引用，那么就下一轮
				continue;
			}
			// 结束条件：左右节点有一个为空或者左右节点都不为空但是左右节点的值不一样
			if(left==null||right==null||left.val!=right.val){// 左右节点至少一个非空，但是左右节点的值不一样，不对称
				return false;
			}
			// 因为要判断是否对称，所以这里需要将数据节点的空叶子节点加入队列，占位
			queue.add(left.left);
			queue.add(right.right);
			queue.add(left.right);
			queue.add(right.left);
		}
		return true;
    }

	*/
}
//leetcode submit region end(Prohibit modification and deletion)

}
