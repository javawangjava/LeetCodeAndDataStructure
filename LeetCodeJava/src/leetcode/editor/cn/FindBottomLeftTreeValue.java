/**
<p>给定一个二叉树的 <strong>根节点</strong> <code>root</code>，请找出该二叉树的 <strong>最底层 最左边 </strong>节点的值。</p>

<p>假设二叉树中至少有一个节点。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2020/12/14/tree1.jpg" style="width: 182px; " /></p>

<pre>
<strong>输入: </strong>root = [2,1,3]
<strong>输出: </strong>1
</pre>

<p><strong>示例 2:</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2020/12/14/tree2.jpg" style="width: 242px; " /><strong> </strong></p>

<pre>
<strong>输入: </strong>[1,2,3,4,null,5,6,null,null,7]
<strong>输出: </strong>7
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li>二叉树的节点个数的范围是 <code>[1,10<sup>4</sup>]</code></li>
	<li><meta charset="UTF-8" /><code>-2<sup>31</sup> <= Node.val <= 2<sup>31</sup> - 1</code> </li>
</ul>
<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 381</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 513
 * 找树左下角的值
 * @author wangweizhou
 * @date 2022-09-04 21:11:33
 */

public class FindBottomLeftTreeValue{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new FindBottomLeftTreeValue().new Solution();
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


	// 通常，广度优先算法是从上到下遍历二叉树的每一层，并且从左到右遍历同一层中的每个节点。
	// 位于某一层最左边的节点也就是该层中第1个遍历到的节点，最低层最左边的节点就是最后一层的第1个节点。
	// 可以用一个变量bottomLeft来保存每一层最左边的节点的值。在遍历二叉树时，每当遇到新的一层时就将变量bottomLeft的值更新为该层第1个节点的值。
	// 当整棵二叉树都被遍历完之后，变量bottomLeft的值就是最后一次更新的值，也就是最后一层的第1个节点的值。


	// 解法1：层序遍历  从左向右进入
	// 每进入一层，记录当前层最左侧的数
    public int findBottomLeftValue(TreeNode root) {
		if(root==null){
			return Integer.MIN_VALUE;
		}
		Deque<TreeNode> queue=new LinkedList<>();
		queue.add(root);
		int ans=0;
		while(queue.size()>0){
			int levelSize= queue.size();
			ans=queue.peek().val;// 这个和下面内层循环if(i==0)语句作用一样，只是在每进入一层，直接记录每次的第一个元素，
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





	//// 解法1：写法2 层序遍历，不同的队列存储不同的层
	//// 由于用广度优先的顺序遍历二叉树时需要区分不同的层，因此可以用两个队列分别存放不同层的节点，一个队列存放当前遍历层的节点，另一个队列存放下一层的节点。
	//// 由于这个题目假设输入的二叉树至少有一个节点，因此根节点总是存在的。二叉树的第1层只有一个节点，即根节点，因此可以把变量bottomLeft初始化为根节点的值。
	//// 接下来按照广度优先的顺序逐层遍历二叉树。当队列queue1被清空时，当前这一层都已经被遍历完，接下来可以开始下一层的遍历。
	//// 如果下一层还有节点，则用下一层的第1个节点的值更新变量bottomLeft。在整棵二叉树的遍历完成之后，变量bottomLeft的值就是最低层最左边节点的值。
	//
	//public int findBottomLeftValue(TreeNode root) {
	//	if(root==null){
	//		return Integer.MIN_VALUE;
	//	}
	//	Deque<TreeNode> queue1=new LinkedList<>();
	//	Deque<TreeNode> queue2=new LinkedList<>();
	//	queue1.offer(root);
	//	int bottomLeft=root.val;
	//	while(!queue1.isEmpty()){
	//		TreeNode node=queue1.poll();
	//		if(node.left!=null){
	//			queue2.push(node.left);
	//		}
	//
	//		if(node.right!=null){
	//			queue2.push(node.right);
	//		}
	//
	//		if(queue1.isEmpty()){
	//			queue1=queue2;
	//			queue2=new LinkedList<>();
	//			if(!queue1.isEmpty()){// 位于某一层最左边的节点也就是该层中第1个遍历到的节点，最低层最左边的节点就是最后一层的第1个节点。
	//				bottomLeft=queue1.peek().val;
	//			}
	//		}
	//	}
	//	return bottomLeft;
	//}






	//// 解法2： DFS
	//int mostLeft, depth;// 定义全局变量，depth表示已经遍历过的部分子树的深度
	//public int findBottomLeftValue(TreeNode root) {
	//	if(root==null){
	//		return 0;
	//	}
	//	mostLeft = root.val;// 记录二叉树最左下的数字
	//	depth = -1;
	//	dfs(root, 0);// 这里根节点深度0,或者是1对后续没影响，题目只需要比较更深
	//	return mostLeft;
	//}
	//
	//
	//// DFS 深度遍历  三种深度遍历都可以
	//// 标准的深度遍历不管是先序，中序，后序遍历，都是先遍历左子树，然后再遍历右子树，所以对同一高度的所有节点，最左节点肯定是最先被遍历到的。
	//// currDepth表示截止当前节点的深度
	//private void dfs(TreeNode node, int currDepth) {
	//	if (node == null) {
	//		return;
	//	}
	//	dfs(node.left, currDepth + 1);// 递归遍历左子树 。处理当前节点的左子节点。处理左子树
	//	dfs(node.right, currDepth + 1);
	//	// 标准的深度遍历都是先遍历左子树，然后再遍历右子树。所以第一次进入某一层，从左向右添加，第一个元素一定是二叉树最左侧的值
	//	if (currDepth > depth) {// 就是递归时进入新的一层，更新深度和
	//		depth = currDepth;//截止当前节点，子树深度已经大于记录的子树深度，更新最大深度
	//		mostLeft = node.val;// 先遍历左子树，更深了，截止目前最下面的，深度刚比原来的大就更新，就是最左的
	//	}
	//}


}
//leetcode submit region end(Prohibit modification and deletion)

}
