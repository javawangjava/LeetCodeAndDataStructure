/**
 * <p>给定一棵二叉搜索树，请找出其中第 <code>k</code> 大的节点的值。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 * &nbsp;  2
 * <strong>输出:</strong> 4</pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * <strong>输出:</strong> 4</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <ul>
 * <li>1 ≤ k ≤ 二叉搜索树元素个数</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉搜索树</li><li>二叉树</li></div></div><br><div><li>👍
 * 334</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 54
 * 二叉搜索树的第k大节点
 *
 * @author wangweizhou
 * @date 2022-09-06 22:44:43
 */

public class ErChaSouSuoShuDeDiKdaJieDianLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ErChaSouSuoShuDeDiKdaJieDianLcof().new Solution();

        ////创建需要的结点
        TreeNode rootnode = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
        //TreeNode node5 = new TreeNode(5);
        //TreeNode node6 = new TreeNode(6);
        //TreeNode node7 = new TreeNode(7);


        // 手动创建二叉树
        rootnode.left = node2;
        rootnode.right = node3;
        //node2.left=node4;
        node2.right = node4;
        //node3.left=node6;
        //node3.right=node7;

        int ans = solution.kthLargest(rootnode, 1);
        System.out.println(ans);


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


        // 解法2： 右子树-父节点-左子树
        private TreeNode ans;// 全局变量
        private int count = 0;// 二叉搜索树中序遍历计数器
        public int kthLargest(TreeNode root, int k) {
            if (root == null) {
                return Integer.MIN_VALUE;
            }
            inorder(root, k);
            return ans.val;
        }



        // DFS    注意这里是：右子树-父节点-左子树
        // 二叉搜索树的中序遍历的逆序是降序
        private void inorder(TreeNode node, int k) {
            if (node == null) {
                return;
            }
            inorder(node.right, k);
            // 处理当前节点
            // 找到倒数第k个就是
            count++;
            if (count == k) {
                ans = node;
                return;
            }
            inorder(node.left, k);
        }




	/*

	//解法1:DFS中序递归+记忆数组
	// 二叉搜索树的中序遍历是递增序列
	List<Integer> lists=new ArrayList<>();
    public int kthLargest(TreeNode root, int k) {
		if(root==null){
			return Integer.MIN_VALUE;
		}
		inorder(root);
		int size=lists.size();
		return lists.get(size-k);
    }


	// 中序遍历
	private void inorder(TreeNode node){
		if(node==null){
			return;
		}
		inorder(node.left);
		lists.add(node.val);
		inorder(node.right);
	}

	*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
