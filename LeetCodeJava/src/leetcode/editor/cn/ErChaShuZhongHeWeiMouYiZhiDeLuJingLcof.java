/**
<p>给你二叉树的根节点 <code>root</code> 和一个整数目标和 <code>targetSum</code> ，找出所有 <strong>从根节点到叶子节点</strong> 路径总和等于给定目标和的路径。</p>

<p><strong>叶子节点</strong> 是指没有子节点的节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/01/18/pathsumii1.jpg" /></p>

<pre>
<strong>输入：</strong>root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
<strong>输出：</strong>[[5,4,11,2],[5,8,4,5]]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/01/18/pathsum2.jpg" /></p>

<pre>
<strong>输入：</strong>root = [1,2,3], targetSum = 5
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [1,2], targetSum = 0
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点总数在范围 <code>[0, 5000]</code> 内</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
	<li><code>-1000 &lt;= targetSum &lt;= 1000</code></li>
</ul>

<p>注意：本题与主站 113&nbsp;题相同：<a href="https://leetcode-cn.com/problems/path-sum-ii/">https://leetcode-cn.com/problems/path-sum-ii/</a></p>
<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>回溯</li><li>二叉树</li></div></div><br><div><li>👍 375</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 34
 * 二叉树中和为某一值的路径
 * @author wangweizhou
 * @date 2022-09-25 11:13:20
 */
public class ErChaShuZhongHeWeiMouYiZhiDeLuJingLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new ErChaShuZhongHeWeiMouYiZhiDeLuJingLcof().new Solution();
		 TreeNode node1 = new TreeNode(1);
		 TreeNode node2 = new TreeNode(2);
		 TreeNode node3 = new TreeNode(3);
		 TreeNode node4 = new TreeNode(4);
		 TreeNode node5 = new TreeNode(5);
		 TreeNode node6 = new TreeNode(6);

		 TreeNode node7 = new TreeNode(7);
		 TreeNode node8 = new TreeNode(8);
		 TreeNode node9 = new TreeNode(9);
		 TreeNode node10 = new TreeNode(10);

		 node1.left = node2;
		 node1.right = node3;
		 node2.left = node4;
		 node2.right = node5;
		 node3.left = node6;
		 node3.right = node7;
		 node4.left = node8;
		 node4.right = node9;



		 List<List<Integer>> lists = solution.pathSum(node1, 8);
		 if(lists==null||lists.size()==0){
			 System.out.println("[]");
		 }
		 for (int i = 0; i < lists.size(); i++) {
			 LinkedList<Integer> list=new LinkedList<>();
			 if(list==null||list.size()==0){
				 System.out.println("[]");
			 }
			 for (int j = 0; j < list.size(); j++) {
				 System.out.println(list.poll());
			 }
		 }
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

	// 解法1：深度遍历 递归
	// 采用深度优先搜索的方式，枚举每一条从根节点到叶子节点的路径。
	// 当我们遍历到叶子节点，且此时路径和恰为目标和时，我们就找到了一条满足条件的路径。
	List<List<Integer>> lists=new LinkedList<>();  // 用来存储结果
	LinkedList<Integer> list=new LinkedList<>(); // 用来存贮路径
    public List<List<Integer>> pathSum(TreeNode root, int target) {
		if(root==null){
			return lists;
		}
		findPath(root,target);
		return lists;
    }

	// 前序遍历
	// 这里基本数据类型是值传递，只把值复制进去了一份进行递归，进入下一层的值和当前层的值互不影响。所以回溯的时候不用加上target的值。
	private void findPath(TreeNode root,int target){
		if(root==null){
			return;
		}
		//当前序遍历访问到某一个节点时，将该节点添加到路径上，并累加该节点的值（在这里使用的是累减该节点的值）
		list.add(root.val);
		target-=root.val;

		// 如果该节点是叶子节点并且路径节点值的和刚好等于输入的目标值，则符合要求，将该路径加入到结果中
		if(target==0&&root.left==null&&root.right==null){
			// 因为直接加入，加入的是引用(指向的堆中数据会变化)，需要克隆一份加入
			lists.add(new LinkedList<>(list));// 注意这里是以list为参数创建了新的list,不然后续list变化会影响到已经加入lists中的引用变量
		}

		//如果该节点不是叶子节点，继续访问他的子节点
		findPath(root.left,target);
		findPath(root.right,target);
		// 当子节点访问结束后，递归函数返回到他的父节点
		// 删除当期节点，确保返回父节点的路径恰好是从根节点到父节点
		list.removeLast();
	}



}
//leetcode submit region end(Prohibit modification and deletion)

}
