/**
 * <p>给你二叉树的根节点 <code>root</code> ，返回其节点值 <strong>自底向上的层序遍历</strong> 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg" style="width: 277px; height: 302px;" />
 * <pre>
 * <strong>输入：</strong>root = [3,9,20,null,null,15,7]
 * <strong>输出：</strong>[[15,7],[9,20],[3]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1]
 * <strong>输出：</strong>[[1]]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数目在范围 <code>[0, 2000]</code> 内</li>
 * <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 611</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 107
 * 二叉树的层序遍历 II
 * @author wangweizhou
 * @date 2022-09-03 20:23:35
 */

public class BinaryTreeLevelOrderTraversalIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new BinaryTreeLevelOrderTraversalIi().new Solution();

        //创建需要的结点
        TreeNode rootnode = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        // 手动创建二叉树
        rootnode.left = node2;
        rootnode.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        List<List<Integer>> lists = solution.levelOrderBottom(rootnode);
        System.out.println(lists);

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


        // 解法1：层序遍历   注意这里只是在层序遍历每次添加每一层的时候将每个元素添加到最外层集合的首位置
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> lists = new LinkedList<>();
            if (root == null) {
                return lists;
            }
            TreeNode node = root;
            Deque<TreeNode> queue = new LinkedList<>();
            queue.add(node);
            while (queue.size() > 0) {
                int levelSize = queue.size();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < levelSize; i++) {
                    node = queue.poll();
                    list.add(node.val);
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                // List中的添加方法 void add(int index, E element) 将指定元素插入此列表中的指定位置
                lists.add(0, list);// 注意这里添加的时候，将最新的一层添加到输出列表的第一位
            }
            return lists;
        }



    }
//leetcode submit region end(Prohibit modification and deletion)

}
