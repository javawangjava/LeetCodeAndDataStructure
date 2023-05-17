/**
<p>给定一个二叉搜索树的根节点 <strong>root </strong>和一个值 <strong>key</strong>，删除二叉搜索树中的&nbsp;<strong>key&nbsp;</strong>对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。</p>

<p>一般来说，删除节点可分为两个步骤：</p>

<ol>
	<li>首先找到需要删除的节点；</li>
	<li>如果找到了，删除它。</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2020/09/04/del_node_1.jpg" style="width: 800px;" /></p>

<pre>
<strong>输入：</strong>root = [5,3,6,2,4,null,7], key = 3
<strong>输出：</strong>[5,4,6,2,null,null,7]
<strong>解释：</strong>给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
另一个正确答案是 [5,2,6,null,4,null,7]。

<img src="https://assets.leetcode.com/uploads/2020/09/04/del_node_supp.jpg" style="width: 350px;" />
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> root = [5,3,6,2,4,null,7], key = 0
<strong>输出:</strong> [5,3,6,2,4,null,7]
<strong>解释:</strong> 二叉树不包含值为 0 的节点
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> root = [], key = 0
<strong>输出:</strong> []</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>节点数的范围&nbsp;<code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li>节点值唯一</li>
	<li><code>root</code>&nbsp;是合法的二叉搜索树</li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= key &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong> 要求算法时间复杂度为&nbsp;O(h)，h 为树的高度。</p>
<div><div>Related Topics</div><div><li>树</li><li>二叉搜索树</li><li>二叉树</li></div></div><br><div><li>👍 905</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 450
 * 删除二叉搜索树中的节点
 * @author wangweizhou
 * @date 2022-07-15 18:58:24
 */
public class DeleteNodeInABst{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new DeleteNodeInABst().new Solution();
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

	// 方法一：递归
	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) {//root 为空，代表未搜索到值为 key 的节点，返回空。
			return null;
		}
		TreeNode node=root;
		if (node.val > key) {// 左递归
			node.left = deleteNode(node.left, key);
		}
		if (node.val < key) {// 右递归
			node.right = deleteNode(node.right, key);
		}

		if (node.val == key) {// 找到待删除节点了
			if (node.left == null && node.right == null) {// 情况1，待删除节点为叶子节点，没有子树。此时可以直接将它删除，即返回空。
				return null;
			}
			if (node.right == null) {// 情况2，待删除节点只有左子树，没有右子树。此时可以将它的左子树作为新的子树，返回它的左子节点
				return node.left;
			}
			if (node.left == null) {// 情况3，待删除节点只有右子树，没有左子树。此时可以将它的右子树作为新的子树，返回它的右子节点
				return node.right;
			}
			//情况4，待删除节点有左右子树，这时可以将 root 的后继节点（比root 大的最小节点，即它的右子树中的最小节点，记为successor）作为新的根节点替代root，
			// 并将successor 从 root 的右子树中删除，使得在保持有序性的情况下合并左右子树。


			TreeNode successor = node.right;
			while (successor.left != null) {// 寻找欲删除节点右子树的最左节点
				successor = successor.left;
			}
			successor.left = node.left;    // 将欲删除节点的左子树成为其右子树的最左节点的左子树
			node = node.right;         // 欲删除节点的右子顶替其位置，节点被删除
		}
		return root;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}
