/**
<p>给定一个二叉树的 <strong>根节点</strong> <code>root</code>，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2021/02/14/tree.jpg" style="width: 270px; " /></p>

<pre>
<strong>输入:</strong>&nbsp;[1,2,3,null,5,null,4]
<strong>输出:</strong>&nbsp;[1,3,4]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>&nbsp;[1,null,3]
<strong>输出:</strong>&nbsp;[1,3]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong>&nbsp;[]
<strong>输出:</strong>&nbsp;[]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>二叉树的节点个数的范围是 <code>[0,100]</code></li>
	<li><meta charset="UTF-8" /><code>-100&nbsp;&lt;= Node.val &lt;= 100</code>&nbsp;</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 199&nbsp;题相同：<a href="https://leetcode-cn.com/problems/binary-tree-right-side-view/">https://leetcode-cn.com/problems/binary-tree-right-side-view/</a></p>
<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 37</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer II 046
 * 二叉树的右侧视图
 * @author wangweizhou
 * @date 2022-11-15 22:00:36
 */
public class WNC0Lk{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new WNC0Lk().new Solution();
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

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> lists = new ArrayList<>();
		if (root == null) {
			return lists;
		}
		Deque<TreeNode> queue=new LinkedList<>();
		queue.add(root);
		while(queue.size()>0){
			lists.add(queue.peek().val);// 在这里加入和进入内层循环时通过if(i==0)来控制是一样的
			int levelSize=queue.size();
			for (int i = 0; i < levelSize; i++) {
				TreeNode node=queue.poll();
				//if(i==0){
				//	lists.add(node.val);
				//}
				if(node.right!=null){
					queue.add(node.right);
				}
				if(node.left!=null){
					queue.add(node.left);
				}
			}
		}
		return lists;
	}


}
//leetcode submit region end(Prohibit modification and deletion)

}
