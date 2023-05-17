/**
<p>输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。</p>

<p>&nbsp;</p>

<p>为了让您更好地理解问题，以下面的二叉搜索树为例：</p>

<p>&nbsp;</p>

<p><img src="https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png"></p>

<p>&nbsp;</p>

<p>我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。</p>

<p>下图展示了上面的二叉搜索树转化成的链表。&ldquo;head&rdquo; 表示指向链表中有最小元素的节点。</p>

<p>&nbsp;</p>

<p><img src="https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png"></p>

<p>&nbsp;</p>

<p>特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。</p>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与主站 426 题相同：<a href="https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/">https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/</a></p>

<p><strong>注意：</strong>此题对比原题有改动。</p>
<div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>二叉搜索树</li><li>链表</li><li>二叉树</li><li>双向链表</li></div></div><br><div><li>👍 586</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 36
 * 二叉搜索树与双向链表
 * @author wangweizhou
 * @date 2022-09-28 21:41:22
 */
public class ErChaSouSuoShuYuShuangXiangLianBiaoLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new ErChaSouSuoShuYuShuangXiangLianBiaoLcof().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {

	/*

	// 解法1： DFS中序遍历递归
	Node head=null;
	Node pre=null;
    public Node treeToDoublyList(Node root) {
		if(root==null){
			return root;
		}
		dfs(root);
		//进行头节点和尾节点的相互指向
		head.left=pre;
		pre.right=head;
		return head;
    }



	private void dfs(Node curr){
		if(curr ==null){
			return;
		}
		dfs(curr.left);
		//pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,
		if(pre!=null){//pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur的操作
			pre.right= curr;
		}else{//当pre==null时，其实就是第一次到达二叉树的最左侧节点，cur左侧没有节点,即此时cur为双向链表中的头节点
			head= curr;
		}
		curr.left=pre;//pre是否为null对这句没有影响,且这句放在上面两句if else之前也是可以的。
		pre= curr;//pre指向当前的cur
		dfs(curr.right);//全部迭代完成后，pre指向双向链表中的尾节点
	}

	*/





	// 解法2：DFS 中序遍历 迭代
	public Node treeToDoublyList(Node root) {
		if(root==null){
			return root;
		}
		Deque<Node> stack=new LinkedList<>(); //设置栈⽤于遍历
		Node node=root;
		Node first=null;// first表示二叉搜索树中最小的元素
		Node pre=null;// pre表示二叉搜索树中上一个元素
		//boolean firstRound=true;// 标志符标识是否第一次到达二叉树最左侧的节点
		while(node!=null||!stack.isEmpty()){
			while(node!=null){//直到没有左节点，向左遍历到最左的叶子节点的子节点。
				stack.push(node);
				node=node.left;
			}

			node=stack.pop();
			// 处理当前节点值，这里pre也相当于标志符
			if(pre==null){// 找到二叉树最左边的节点，也就是递归到最左边，初始化first与pre
				first=node;// 最左元素即链表的第一个数据元素节点
				pre=node;
			} else{//
				pre.right=node;// 前一个节点的右指针域指向当前节点
				node.left=pre;// 当前节点的左指针域指向前一个节点
				pre=node;//pre后移
			}

			// // 下面这种使用标识符思路也很好
			//if(firstRound){//最左元素即链表的第一个数据元素。//找到最⼩值，也就是递归到最左边，初始化first与pre
			//    first=node;//最左元素即链表的第一个数据元素节点
			//    pre=node;
			//    firstRound=false;
			//} else{//
			//    pre.right=node;// 前一个节点的右指针域指向当前节点
			//    node.left=pre;// 当前节点的左指针域指向前一个节点
			//    pre=node;//pre后移
			//}

			node=node.right;
		}

		//将链表成环
		first.left=pre;
		pre.right=first;
		return first;
	}



}
//leetcode submit region end(Prohibit modification and deletion)

}


//class Node {
//	public int val;
//	public Node left;
//	public Node right;
//
//	public Node() {}
//
//	public Node(int val) {
//		this.val = val;
//	}
//
//	public Node(int val, Node left, Node right) {
//		this.val = val;
//		this.left = left;
//		this.right = right;
//	}
//}


