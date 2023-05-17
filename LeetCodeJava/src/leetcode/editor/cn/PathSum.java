/**
 * <p>给你二叉树的根节点&nbsp;<code>root</code> 和一个表示目标和的整数&nbsp;<code>targetSum</code> 。判断该树中是否存在 <strong>根节点到叶子节点</strong>
 * 的路径，这条路径上所有节点值相加等于目标和&nbsp;<code>targetSum</code> 。如果存在，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p><strong>叶子节点</strong> 是指没有子节点的节点。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/01/18/pathsum1.jpg" style="width: 500px; height: 356px;" />
 * <pre>
 * <strong>输入：</strong>root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * <strong>输出：</strong>true
 * <strong>解释：</strong>等于目标和的根节点到叶节点路径如上图所示。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/01/18/pathsum2.jpg" />
 * <pre>
 * <strong>输入：</strong>root = [1,2,3], targetSum = 5
 * <strong>输出：</strong>false
 * <strong>解释：</strong>树中存在两条根节点到叶子节点的路径：
 * (1 --&gt; 2): 和为 3
 * (1 --&gt; 3): 和为 4
 * 不存在 sum = 5 的根节点到叶子节点的路径。</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [], targetSum = 0
 * <strong>输出：</strong>false
 * <strong>解释：</strong>由于树是空的，所以不存在根节点到叶子节点的路径。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点的数目在范围 <code>[0, 5000]</code> 内</li>
 * <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
 * <li><code>-1000 &lt;= targetSum &lt;= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 935</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;


/**
 * 112
 * 路径总和
 *
 * @author wangweizhou
 * @date 2022-07-10 15:59:31
 */

public class PathSum {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new PathSum().new Solution();

        //创建需要的结点
        TreeNode rootnode = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);


        // 手动创建二叉树
        rootnode.left = node2;
        rootnode.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node6.right = node9;

        boolean ans = solution.hasPathSum(rootnode, 22);
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


        //// 方法二：递归  DFS（深度优先搜索）前序遍历递归算法  存在性问题只要有一个就可以
        //// 假定从根节点到当前节点的值之和为 val，我们可以将这个大问题转化为一个小问题：是否存在从当前节点的子节点到叶子的路径，满足其路径和为 sum - val。
        //// 这里使用的是递减的方法
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) {//空结点
                return false;
            }
            // 处理当前节点
            if (root.left == null && root.right == null) {// 叶⼦结点，且路径和为sum
                return sum == root.val;// 当前的节点值是否等于剩余的sum
            }
            // 写法1：存在性问题只要有一个就可以
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);

            //// 以下两种写法和上面return的作用一样
            //// 注意前序遍历有返回值时左右子树的写法
            //if (hasPathSum(root.left, sum - root.val)) {// 递归遍历左子树，注意向下递归的时候减去当前节点值
            //    return true;
            //}
            //if (hasPathSum(root.right, sum - root.val)) {
            //    return true;
            //}
            //return false;


            // 写法2：
            //递归进⼊⼦结点
            //boolean ans1=hasPathSum(root.left, sum - root.val);
            //boolean ans2=hasPathSum(root.right, sum - root.val);
            //return ans1||ans2;
        }




        //// 方法一：广度优先搜索+队列存储记忆化
        //// 使用广度优先搜索的方式，记录从根节点到当前节点的路径和，以防止重复计算。
        //// 这样我们使用两个队列，分别存储将要遍历的节点，以及根节点到这些节点的路径和即可。

        //public boolean hasPathSum(TreeNode root, int sum) {
        //    if (root == null) {// 空节点找不到路径
        //        return false;
        //    }
        //    // 这里是利用队列实现的，层次遍历,队列queVal保存的是从根节点到当前节点的路径节点和
        //    Queue<TreeNode> queNode = new LinkedList<>();// 队列辅助广度优先遍历
        //    Queue<Integer> queVal = new LinkedList<>();// 队列queVal用来存储到相应节点为止的路径和
        //    queNode.offer(root);
        //    queVal.offer(root.val);
        //    while (!queNode.isEmpty()) {
        //        TreeNode node = queNode.poll();//弹出相应节点
        //        int nodeSum = queVal.poll();// 截止到该点为⽌的当前路径和nodeSum
        //        // 叶⼦节点且当前路径和等于sum
        //        if (node.left == null && node.right == null && nodeSum == sum) {
        //            return true;
        //        }
        //        // 因为要用到当前节点的左右子节点的值，
        //        if (node.left != null) {// 左节点及路径和⼊队列
        //            queNode.offer(node.left);
        //            queVal.offer(node.left.val + nodeSum);
        //        }
        //        if (node.right != null) {// 右节点及路径和⼊队列
        //            queNode.offer(node.right);
        //            queVal.offer(node.right.val + nodeSum);
        //        }
        //    }
        //    return false;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
