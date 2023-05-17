/**
<p>给定一个二叉树的 <strong>根节点</strong> <code>root</code>，请找出该二叉树的&nbsp;<strong>最底层&nbsp;最左边&nbsp;</strong>节点的值。</p>

<p>假设二叉树中至少有一个节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2020/12/14/tree1.jpg" style="width: 182px; " /></p>

<pre>
<strong>输入: </strong>root = [2,1,3]
<strong>输出: </strong>1
</pre>

<p><strong>示例 2: </strong></p>

<p><img src="https://assets.leetcode.com/uploads/2020/12/14/tree2.jpg" style="width: 242px; " /><strong> </strong></p>

<pre>
<strong>输入: </strong>[1,2,3,4,null,5,6,null,null,7]
<strong>输出: </strong>7
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>二叉树的节点个数的范围是 <code>[1,10<sup>4</sup>]</code></li>
	<li><meta charset="UTF-8" /><code>-2<sup>31</sup>&nbsp;&lt;= Node.val &lt;= 2<sup>31</sup>&nbsp;- 1</code>&nbsp;</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 513&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/find-bottom-left-tree-value/">https://leetcode-cn.com/problems/find-bottom-left-tree-value/</a></p>
<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 33</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer II 045
 * 二叉树最底层最左边的值
 * @author wangweizhou
 * @date 2022-11-15 22:00:38
 */
public class LwUNpT{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new LwUNpT().new Solution();
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
	// 解法1：层序遍历  从左向右进入
	// 每进入一层，记录当前层最左侧的数

	public int findBottomLeftValue(TreeNode root) {
		if(root==null){
			return 0;
		}
		Deque<TreeNode> queue=new LinkedList<>();
		queue.add(root);
		int ans=0;
		while(queue.size()>0){
			int levelSize= queue.size();
			ans=queue.peekFirst().val;// 这个和下面内层循环if(i==0)语句作用一样，只是在每进入一层，直接记录每次的第一个元素，
			for (int i = 0; i < levelSize; i++) {
				TreeNode node=queue.poll();
				//if(i==0){// 每进入一层，判断当前节点是否是每层的第一个节点，这个就是在该层遍历是多了判断的步骤
				//	ans=node.val;
				//}
				if(node.left!=null){
					queue.add(node.left);
				}
				if(node.right!=null){
					queue.add(node.right);
				}
			}
		}
		return ans;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}
