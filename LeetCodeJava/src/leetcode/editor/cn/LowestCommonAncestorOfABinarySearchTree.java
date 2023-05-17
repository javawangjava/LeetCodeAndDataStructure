/**
<p>给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。</p>

<p><a href="https://baike.baidu.com/item/%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/8918834?fr=aladdin" target="_blank">百度百科</a>中最近公共祖先的定义为：&ldquo;对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（<strong>一个节点也可以是它自己的祖先</strong>）。&rdquo;</p>

<p>例如，给定如下二叉搜索树:&nbsp; root =&nbsp;[6,2,8,0,4,7,9,null,null,3,5]</p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png" style="height: 190px; width: 200px;"></p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
<strong>输出:</strong> 6 
<strong>解释: </strong>节点 <code>2 </code>和节点 <code>8 </code>的最近公共祖先是 <code>6。</code>
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
<strong>输出:</strong> 2
<strong>解释: </strong>节点 <code>2</code> 和节点 <code>4</code> 的最近公共祖先是 <code>2</code>, 因为根据定义最近公共祖先节点可以为节点本身。</pre>

<p>&nbsp;</p>

<p><strong>说明:</strong></p>

<ul>
	<li>所有节点的值都是唯一的。</li>
	<li>p、q 为不同节点且均存在于给定的二叉搜索树中。</li>
</ul>
<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉搜索树</li><li>二叉树</li></div></div><br><div><li>👍 889</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.*;

/**
 * 235
 * 二叉搜索树的最近公共祖先
 * @author wangweizhou
 * @date 2022-07-26 09:58:53
 */

public class LowestCommonAncestorOfABinarySearchTree{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new LowestCommonAncestorOfABinarySearchTree().new Solution();

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


	////⽅法⼀：两次遍历（推荐使⽤）  深度遍历+记忆数组  这个是通用的方法
	////step1:根据二叉搜索树的性质，从根节点开始查找目标节点，当前节点比目标小,则进入右子树，
	//// 当前节点比目标大则进入左子树，直到找到目标节点。这个过程成用数组记录遇到的元素。
	////step2:分别在搜索二叉树中找到p和q两个点，并记录各自的路径为数组。
	////stp3:同时遍历两个数组，比较元素值，最后一个相等的元素就是最近的公共祖先。
	//
	////在二叉搜索树求得根节点到⽬标节点的路径 写法1
	//private List<TreeNode> getPath(TreeNode root, TreeNode target){
	//	List<TreeNode> path=new ArrayList<>();
	//	TreeNode node=root;
	//	while(node!=target){ //节点不同
	//		path.add(node);
	//		// 利用二叉搜索树的性质来确定向左或者向右查找
	//		if(target.val<node.val){//⼩的在左⼦树
	//			node=node.left;
	//		}else{//⼤的在右⼦树
	//			node=node.right;
	//		}
	//	}
	//	// 上面循环结束就找找到了目标节点
	//	path.add(node);// 将目标节点添加到路径里面
	//	return path;
	//}


	////在二叉搜索树求得根节点到⽬标节点的路径 写法2
	private List<TreeNode> getPath(TreeNode root,TreeNode targetNode){
		List<TreeNode> list=new LinkedList<>();
		if(root==null){
			return list;
		}
		while (true){
			list.add(root);
			if (targetNode.val>root.val){
				root=root.right;
			}else if(targetNode.val<root.val){
				root=root.left;
			}else if(targetNode.val==root.val) {
				break;
			}
		}
		return list;
	}



    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root==null){
			return null;
		}
		//求根节点到两个节点的路径
		List<TreeNode> pathP=getPath(root,p);
		List<TreeNode> pathQ=getPath(root,q);
		TreeNode ancestor=null;
		//⽐较两个路径，找到第⼀个不同的点，最后⼀个相同的节点就是最近公共祖先
		for (int i = 0; i < pathP.size()&&i<pathQ.size(); i++) {
			if(pathP.get(i)==pathQ.get(i)){//最后⼀个相同的节点就是最近公共祖先
				ancestor=pathP.get(i);
			}else{
				break;
			}
		}
		return ancestor;
    }




/*
	//⽅法⼆：⼀次遍历（扩展思路）  利用二叉搜索树的性质对二叉搜索树的遍历
	//step1:首先检查空节点，空树没有公共祖先。
	//step2:对于某个节点，比较与p、q的大小，若p、q在该节点两边说明这就是最近公共祖先。
	//step3:如果p、q都在该节点的左边，则递归进入左子树。
	//step4:如果p、q都在该节点的右边，则递归进入右子树。

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root==null){
			return null;
		}
		TreeNode node =root;
		while(true){
			if(p.val< node.val&&q.val< node.val){// p、q都在该节点的左边，则递归进入左子树。
				node = node.left;
			}else if(p.val> node.val&&q.val> node.val){// p、q都在该节点的右边，则递归进入右子树
				node = node.right;
			}else{// p和q位于根节点的两侧或者p,q中至少有有个就是node,结束循环，这时候node就是最近的公共祖先
				// 补集思想：两个都大，两个都小的补集是一大一小和至少一个相等
				break;
			}
		}
		return node;
	}
	*/





}
//leetcode submit region end(Prohibit modification and deletion)

}
