/**
<p>从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。</p>

<p>&nbsp;</p>

<p>例如:<br>
给定二叉树:&nbsp;<code>[3,9,20,null,null,15,7]</code>,</p>

<pre>    3
   / \
  9  20
    /  \
   15   7
</pre>

<p>返回其层次遍历结果：</p>

<pre>[
  [3],
  [9,20],
  [15,7]
]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>节点总数 &lt;= 1000</code></li>
</ol>

<p>注意：本题与主站 102 题相同：<a href="https://leetcode-cn.com/problems/binary-tree-level-order-traversal/">https://leetcode-cn.com/problems/binary-tree-level-order-traversal/</a></p>
<div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 249</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.*;

/**
 * 剑指 Offer 32 - II
 * 从上到下打印二叉树 II
 * @author wangweizhou
 * @date 2022-09-15 01:17:23
 */
public class CongShangDaoXiaDaYinErChaShuIiLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new CongShangDaoXiaDaYinErChaShuIiLcof().new Solution();
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

	// 解法1：层次遍历 注意分层存储
    public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> lists=new ArrayList<>();
		if(root==null){
			return lists;
		}
		Deque<TreeNode> queue=new LinkedList<>();
		queue.add(root);
		while(queue.size()>0){
			int levelSize=queue.size();
			List<Integer> list=new ArrayList<>();
			while(levelSize>0){
				TreeNode node=queue.poll();
				levelSize--;
				list.add(node.val);
				if(node.left!=null){
					queue.add(node.left);
				}
				if(node.right!=null){
					queue.add(node.right);
				}
			}
			lists.add(list);
		}
		return lists;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
