/**
 * <p>给定一棵二叉搜索树和其中的一个节点 <code>p</code> ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 <code>null</code> 。</p>
 *
 * <p>节点 <code>p</code> 的后继是值比 <code>p.val</code> 大的节点中键值最小的节点。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2019/01/23/285_example_1.PNG" style="height: 117px; width:
 * 122px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [2,1,3], p = 1
 * <strong>输出：</strong>2
 * <strong>解释：</strong>这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2019/01/23/285_example_2.PNG" style="height: 229px; width:
 * 246px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [5,3,6,2,4,null,null,1], p = 6
 * <strong>输出：</strong>null
 * <strong>解释：</strong>因为给出的节点没有中序后继，所以答案就返回 <code>null 了。</code>
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点的数目在范围 <code>[1, 10<sup>4</sup>]</code> 内。</li>
 * <li><code>-10<sup>5</sup> <= Node.val <= 10<sup>5</sup></code></li>
 * <li>树中各节点的值均保证唯一。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉搜索树</li><li>二叉树</li></div></div><br><div><li>👍
 * 179</li><li>👎 0</li></div>
 */


package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 285
 * 二叉搜索树中的中序后继
 *
 * @author wangweizhou
 * @date 2022-09-05 15:50:04
 */

public class InorderSuccessorInBst {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new InorderSuccessorInBst().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    class Solution {


        //// 二叉搜索树的中序遍历是一个递增的数组
        //// 解法2：迭代法  二叉搜索树的二分查找

        // 首先二叉搜索树中下一个节点的值一定不会小于节点p的值，而且还是大于或等于节点p的值的所有节点中值最小的一个。
        // 按照在二叉搜索树中根据节点的值查找节点的思路来分析。从根节点开始，每到达一个节点就比较根节点的值和节点p的值。
        // 如果当前节点的值小于或等于节点p的值，那么节点p的下一个节点应该在它的右子树。
        // 如果当前节点的值大于节点p的值，那么当前节点有可能是它的下一个节点。
        // 此时当前节点的值比节点p的值大，但节点p的下一个节点是所有比它大的节点中值最小的一个，因此接下来前往当前节点的左子树，确定是否能找到值更小但仍然大于节点p的值的节点。
        // 重复这样的比较，直至找到最后一个大于节点p的值的节点，就是节点p的下一个节点。
        // 代码用变量result记录节点p的下一个节点。每当找到一个值大于p的节点，就更新变量result，并接着前往左子树看能否找到值更小但仍然大于节点p的值的节点。
        // 那么最后result就是所有大于节点p的值的节点中值最小的节点，也就是节点p的下一个节点。

        // 寻找p节点的中序后继，其实就是找搜索二叉树中第一个比p节点大的节点
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            if (root == null || p == null) {
                return null;
            }
            TreeNode node = root;
            TreeNode result = null;// result 表示上一个访问的比目标节点p大的节点,result 最终会指向大于等于节点p的节点中最小的一个
            while (node != null) {
                if (node.val > p.val) { // 若当前结点更大，p节点一定在左子树，
                    result = node; // 更新上一个访问过的节点
                    node = node.left;// 节点向左遍历
                } else {// cur.val <= p.val 向右遍历
                    node = node.right; // 若当前结点等于或小于p，则向右寻找
                }
            }
            return result;
        }


        //// 解法2：中序遍历 迭代法  二叉搜索树的中序遍历是升序
        //// 既然是二叉搜索树中某节点的中序后继，那么肯定不是二叉树最左侧的节点,
        //// 当当前节点的前一个节点是目标节点时，那么当前节点就是目标节点的中序后继
        //public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //	if (root==null||p==null) {
        //		return null;
        //	}
        //	TreeNode node=root;
        //	TreeNode prev =null;// prev表示上一个访问过的节点
        //	Deque<TreeNode> stack=new LinkedList<>();
        //	while(!stack.isEmpty()||node!=null){
        //		while(node!=null){
        //			stack.push(node);
        //			node=node.left;
        //		}
        //		node=stack.pop();
        //		// 处理当前节点，若上一个访问的节点是p，那么当前节点就是中序后继。这里是直接到达目标节点的下一个节点的位置。
        //		if(prev ==p) {
        //			return node;
        //		}
        //		prev =node;// 更新上一个访问的节点
        //		node=node.right;
        //	}
        //	return null;
        //}


        //// 解法2：中序遍历 迭代法  写法2  二叉搜索树的中序遍历是升序
        //// 采用二叉树的中序遍历。可以用一个布尔变量found来记录已经遍历到节点p。
        //// 该变量初始化为false，遍历到节点p就将它设为true。在这个变量变成true之后遍历到的第1个节点就是要找的节点。
        //
        //public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //	if(root==null||p==null){
        //		return null;
        //	}
        //	Deque<TreeNode> stack=new LinkedList<>();
        //	boolean isFound=false;
        //	TreeNode node=root;
        //	while (node!=null||!stack.isEmpty()){
        //		while (node!=null){
        //			stack.push(node);
        //			node=node.left;
        //		}
        //		node=stack.pop();
        //		// 注意这里和上面用prev标记的不同，
        //		if(isFound) {
        //			break;
        //		}else if(p==node){// 在这里找到目标节点时，后面还会进入目标节点的右子树进行遍历，这时候在下一层循环时才结束，这时候当前节点就是目标节点的下一个
        //			isFound=true;
        //		}
        //		node=node.right;
        //	}
        //	return node;
        //}




		/*

        // 解法1：DFS中序遍历递归  写法1   二叉搜索树的中序遍历是升序

        private TreeNode nextNode = null;// nextNode表示结果
        private TreeNode last = null;// 上一个访问过的节点
        private boolean isFound = false;// 标识符
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
			if (root==null||p==null) {
				return null;
			}
            inorder(root, p);
            return nextNode;
        }


        // DFS中序遍历  当当前位置的前一个节点就是节点p时，那么当前节点就是所求的后继节点。
        // 因为二叉搜索树有序，所以当找到后继节点就结束遍历，没必要遍历完整个二叉树。
        private void inorder(TreeNode node, TreeNode p) {
            if (node == null || isFound) {
                return;
            }
            inorder(node.left, p);
            // 处理当前节点
            if (last == p) {// 如果上一个访问的节点==当前节点，那么中序后继就是当前节点，
                nextNode = node;// 记录中序后继
                isFound = true;// 修改标识符，找到之后剪枝，每次进入新的一层就返回，没必要搜索完整个二叉树
            }
            last = node;// 更新上一个访问的节点
            inorder(node.right, p);
        }
		*/



    }
//leetcode submit region end(Prohibit modification and deletion)

}
