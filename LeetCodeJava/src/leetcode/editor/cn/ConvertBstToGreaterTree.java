/**
 * <p>给出二叉<strong> 搜索 </strong>树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 <code>node</code>&nbsp;
 * 的新值等于原树中大于或等于&nbsp;<code>node.val</code>&nbsp;的值之和。</p>
 *
 * <p>提醒一下，二叉搜索树满足下列约束条件：</p>
 *
 * <ul>
 * <li>节点的左子树仅包含键<strong> 小于 </strong>节点键的节点。</li>
 * <li>节点的右子树仅包含键<strong> 大于</strong> 节点键的节点。</li>
 * <li>左右子树也必须是二叉搜索树。</li>
 * </ul>
 *
 * <p><strong>注意：</strong>本题和 1038:&nbsp;
 * <a href="https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/">https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/</a> 相同</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/05/03/tree.png"
 * style="height: 364px; width: 534px;"></strong></p>
 *
 * <pre><strong>输入：</strong>[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * <strong>输出：</strong>[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>root = [0,null,1]
 * <strong>输出：</strong>[1,null,1]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>root = [1,0,2]
 * <strong>输出：</strong>[3,3,2]
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre><strong>输入：</strong>root = [3,2,4,1]
 * <strong>输出：</strong>[7,9,4,10]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中的节点数介于 <code>0</code>&nbsp;和 <code>10<sup>4</sup></code><sup>&nbsp;</sup>之间。</li>
 * <li>每个节点的值介于 <code>-10<sup>4</sup></code>&nbsp;和&nbsp;<code>10<sup>4</sup></code>&nbsp;之间。</li>
 * <li>树中的所有值 <strong>互不相同</strong> 。</li>
 * <li>给定的树为二叉搜索树。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉搜索树</li><li>二叉树</li></div></div><br><div><li>👍
 * 780</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 538
 * 把二叉搜索树转换为累加树
 * @author wangweizhou
 * @date 2022-09-05 17:17:46
 */

public class ConvertBstToGreaterTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ConvertBstToGreaterTree().new Solution();

        //创建需要的结点
        TreeNode rootnode = new TreeNode(4);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(0);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(3);
        TreeNode node9 = new TreeNode(8);


        // 手动创建二叉树
        rootnode.left = node2;
        rootnode.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.right = node8;
        node7.right = node9;
        solution.convertBST(rootnode);

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

        // 因为要找出比某节点的值大的所有节点。在二叉搜索树的常用遍历算法中，只有中序遍历是按照节点值递增的顺序遍历所有节点的。

        // 通常，二叉搜索树的中序遍历按照节点的值从小到大按顺序遍历，也就是当遍历到某个节点时比该节点的值小的节点都已经遍历过，因此也就知道了所有比该节点的值小的所有节点的值之和sum。
        // 可是题目要求把每个节点的值替换成大于或等于该节点的值的所有节点的值之和。因此，可以先遍历一遍二叉树求出所有节点的值之和total，再用total减去sum即可。
        // 如果能够按照节点值从大到小按顺序遍历二叉搜索树，那么只需要遍历一次就够了，因为遍历到一个节点之前值大于该节点的值的所有节点已经遍历过。
        // 通常的中序遍历是先遍历左子树，再遍历根节点，最后遍历右子树，由于左子树节点的值较小，右子树节点的值较大，因此总体上就是按照节点的值从小到大遍历的。
        // 如果要按照节点的值从大到小遍历，那么只需要改变中序遍历的顺序，先遍历右子树，再遍历根节点，最后遍历左子树，这样遍历的顺序就颠倒过来了。

        // 与常规的中序遍历相比，上述代码的不同点在于左右交换。在常规的中序遍历中，第2个while循环是顺着指向左子节点的指针向下移动的，在上述代码中则是顺着指向右子节点的指针向下移动的。
        // 常规的中序遍历在当前节点出栈遍历之后接着前往它的右子节点，在上述代码中遍历完当前节点后将前往它的左子节点。
        // 上述代码中的变量sum用来累加遍历过的节点的值。
        // 当遍历到一个节点时，值比它大的所有节点都已经遍历过，因此sum就是所有大于或等于当前节点的值之和，按照题目的规则，用sum替换当前节点的值即可。



        //// 解法1： 二叉搜索树的中序遍历变形   右子节点-根节点-左子节点
        //public TreeNode convertBST(TreeNode root) {
        //    if (root == null) {
        //        return root;
        //    }
        //    Deque<TreeNode> stack = new LinkedList<>();
        //    TreeNode node = root;
        //    int sum = 0;
        //    while (!stack.isEmpty() || node != null) {
        //        while (node != null) {
        //            stack.push(node);
        //            node = node.right;// 遍历右子树
        //        }
        //        // 处理当前节点
        //        node = stack.pop();
        //        sum = sum + node.val;// 截至到当前节点的节点和，其实就是截至当前节点所有比当前节点大的节点的节点和
        //        node.val = sum;// 将当前节点值设置为比所有大于等于该节点的值之和
        //        node = node.left;// 遍历左子树
        //    }
        //    return root;
        //}



        //// 解法1：深度遍历 递归  DFS 中序递归遍 从右向左   右子节点-根节点-左子节点,这个使用引用变量传递参数的递归更好理解
        public TreeNode convertBST(TreeNode root) {
            if(root==null){
                return null;
            }
            int[] sum={0};
            dfs(root,sum);
            return root;
        }

        private void dfs(TreeNode root,int[] sum){
            if(root==null){
                return;
            }
            dfs(root.right,sum);
            sum[0]+=root.val;
            root.val=sum[0];
            dfs(root.left,sum);
        }



        //// 解法1：深度遍历 递归  DFS 中序递归遍 从右向左   右子节点-根节点-左子节点
        //int sum=0;
        //public TreeNode convertBST(TreeNode root) {
        //	if (root == null) {
        //		return root;
        //	}
        //	convertBST(root.right);
        //	sum=root.val+sum;
        //	root.val=sum;
        //	convertBST(root.left);
        //	return root;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
