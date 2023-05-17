/**
 * <p>给定一个二叉树，找出其最小深度。</p>
 *
 * <p>最小深度是从根节点到最近叶子节点的最短路径上的节点数量。</p>
 *
 * <p><strong>说明：</strong>叶子节点是指没有子节点的节点。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/12/ex_depth.jpg" style="width: 432px; height: 302px;" />
 * <pre>
 * <strong>输入：</strong>root = [3,9,20,null,null,15,7]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [2,null,3,null,4,null,5,null,6]
 * <strong>输出：</strong>5
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数的范围在 <code>[0, 10<sup>5</sup>]</code> 内</li>
 * <li><code>-1000 <= Node.val <= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 830</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 111
 * 二叉树的最小深度
 *
 * @author wangweizhou
 * @date 2022-09-04 17:44:36
 */

public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MinimumDepthOfBinaryTree().new Solution();

        //创建需要的结点
        TreeNode rootnode = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(8);
        TreeNode node7 = new TreeNode(10);

        // 手动创建二叉树
        rootnode.left = node2;
        rootnode.right = node3;
        node3.left = node4;
        node3.right = node5;
        node2.left = node6;
        node2.right = node7;

        int ans = solution.minDepth(rootnode);
        System.out.println(ans);

    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    class Solution {


        //// 解法2：层次遍历 当第一次遇到叶子节点时就得到最小深度，结束返回最小深度
        //// 注意题干深度定义，leetcode深度定义指的是从根节点到指定节点经过的最少节点数
        //public int minDepth(TreeNode root) {
        //    if (root == null) {
        //        return 0;
        //    }
        //    Queue<TreeNode> queue = new ArrayDeque<>();//用队列实现
        //    TreeNode node = root;
        //    queue.add(node);
        //    int minDepth = 1;
        //    while (queue.size() > 0) {// !queue.isEmpty()和queue.size() > 0在这里一个意思
        //        int levelSize = queue.size();// 获取当前队列的长度，其实就是二叉树当前这一层的节点个数
        //        //将队列中的元素都拿出来(也就是获取这一层的节点)，放到临时List的level中
        //        for (int i = 0; i < levelSize; i++) {//  levelSize是为了控制分层
        //            node = queue.poll();//队首元素出队
        //            // 当第一次遇到叶子节点时就结束，找到了最小深度
        //            if (node.left == null && node.right == null) {
        //                return minDepth;
        //            }
        //            //队首元素出队，如果该节点的左/右子树不为空，也放入队列中
        //            if (node.left != null) {
        //                queue.add(node.left);
        //            }
        //            if (node.right != null) {
        //                queue.add(node.right);
        //            }
        //        }
        //        minDepth++;
        //    }
        //    return minDepth;
        //}





        /*

        // 解法3：DFS 深度遍历
        private int minDepth = Integer.MAX_VALUE;// 全局变量，记录截止当前位置的最小深度
        public int minDepth(TreeNode root) {
            if (root == null) {// 判空
                return 0;
            }
            getDepth(root, 1);
            return minDepth;
        }


        // DFS  递归遍历    depth表示以节点root为根节点的二叉树的depth
        // 前序，中序，后序都可以。 但是剪枝肯定是越靠前越好，所以前序相对较好
        public void getDepth(TreeNode root, int depth) {
            if (root == null) {// 判空
                return;
            }
            if (root.left == null && root.right == null) {// 当遍历到叶子节点,更新最小深度
                minDepth = Math.min(minDepth, depth);
            }
            if (depth >= minDepth) {// 当遍历到的二叉树深度>=二叉树的最小深度时，就没有必要再向下遍历了
                return;
            }
            getDepth(root.left, depth + 1);// 递归求左右子树的深度，没有必要判空，因为进入下一层，刚开始就有判空
            getDepth(root.right, depth + 1);
        }

        */



        // 解法1：递归 写法2   DFS 深度遍历   后序遍历
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            // 计算左右子树的高度
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            // 情况1：当左右孩子都是空子树时，那么左右子树的深度都是0。那么深度就是只有根节点的深度即1。
            // 情况2：左孩子和右孩子有一个节点为空，说明左右子树深度left和right中一定有一个为0，所以可以返回left + right + 1;返回比较大的那个孩子的深度
            // 写法1：
            //if (root.left == null || root.right == null) {// 情况1和2合并
            //    return left + right + 1;
            //} else {// 左右子树都不为空，返回子树较小高度+1
            //    return Math.min(left, right) + 1;
            //}

            // 写法2：
            if (root.left == null && root.right == null) {// 左右子树都不为空，返回子树较小高度+1
                return Math.min(left, right) + 1;
            } else {// 情况1和2合并
                return left + right + 1;
            }
            //// 写法3：
            //if (root.left != null && root.right != null) {
            //    return Math.min(left, right) + 1;
            //} else if (root.left == null) {
            //    return right + 1;
            //} else {
            //    return left + 1;
            //}
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
