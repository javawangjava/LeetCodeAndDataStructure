/**
 * <p><strong>路径</strong> 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 <strong>至多出现一次</strong> 。该路径<strong>
 * 至少包含一个
 * </strong>节点，且不一定经过根节点。</p>
 *
 * <p><strong>路径和</strong> 是路径中各节点值的总和。</p>
 *
 * <p>给你一个二叉树的根节点 <code>root</code> ，返回其 <strong>最大路径和</strong> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/13/exx1.jpg" style="width: 322px; height: 182px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,2,3]
 * <strong>输出：</strong>6
 * <strong>解释：</strong>最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6</pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/13/exx2.jpg" />
 * <pre>
 * <strong>输入：</strong>root = [-10,9,20,null,null,15,7]
 * <strong>输出：</strong>42
 * <strong>解释：</strong>最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数目范围是 <code>[1, 3 * 10<sup>4</sup>]</code></li>
 * <li><code>-1000 <= Node.val <= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>动态规划</li><li>二叉树</li></div></div><br><div><li>👍
 * 1785</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 124
 * 二叉树中的最大路径和
 *
 * @author wangweizhou
 * @date 2022-11-17 20:48:42
 */

public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new BinaryTreeMaximumPathSum().new Solution();
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


        // 注意本题的路径指：从一个节点沿着一条路径到达另一个节点（注意不能重复经过同一个节点）
        // 这里的路径最主要的特点是路径有可能同时经过一个节点的左右子节点。如果一条路径同时经过某个节点的左右子节点，那么该路径一定不能经过它的父节点。
        // 也就是说，当路径到达某个节点时，该路径既可以前往它的左子树，也可以前往它的右子树。但如果路径同时经过它的左右子树，那么就不能经过它的父节点。

        // 由于路径可能只经过左子树或右子树而不经过根节点，为了求得二叉树的路径上节点值之和的最大值，
        // 需要先求出左右子树中路径节点值之和的最大值（左右子树中的路径不经过当前节点），再求出经过根节点的路径节点值之和的最大值，最后对三者进行比较得到最大值。
        // 由于需要先求出左右子树的路径节点值之和的最大值，再求根节点，这看起来就是后序遍历。


        // 后序遍历
        // 函数pathSum的返回值是经过当前节点root并前往其左子树或右子树【两个最多只能选一个】的路径的节点值之和的最大值。
        // 最大路径和maxSum表示二叉树中的最大路径和。所以每遍历一个节点就要更新经过该节点的最大路径和。
        // 返回值：返回以当前节点root为根节点的子树能向父节点“提供”的最大路径和。即，一条从父节点延伸下来的路径，能在当前子树中捞取的最大收益。分三种情况：
        // 1.路径停在当前子树的根节点，在当前子树的最大收益：root.val；
        // 2.走入左子树，在当前子树的最大收益：root.val + pathSum(root.left)；
        // 3.走入右子树，在当前子树的最大收益：root.val + pathSum(root.right)；
        // 这对应了前面所说的三种选择，最大收益取三者最大：root.val+max(0, dfs(root.left), dfs(root.right))。
        // 具体而言：不进入左右子树时最大贡献值等于 0。非空节点的最大贡献值等于节点值与其子节点中的最大贡献值之和（对于叶节点而言，最大贡献值等于节点值）。
        //
        //
        // 根据函数 pathSum得到每个节点的最大贡献值之后，如何得到二叉树的最大路径和？
        // 对于二叉树中的一个节点，该节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值，
        // 如果子节点的最大贡献值为正，则计入该节点的最大路径和，否则不计入该节点的最大路径和。
        //
        // 最大路径和：维护一个全局变量 maxSum 存储最大路径和，在递归过程中更新 maxSum 的值，最后得到的 maxSum 的值即为二叉树中的最大路径和。
        // 根据题目中路径的定义，一个节点在一条路径中只能出现一次，那么需要考虑下面这几种情况，在下述情况中取最大路径更新到全局maxSum存储：
        // 1.当前节点
        // 2.左边路径 + 当前节点 + 右边路径
        // 3.左边路径 + 当前节点
        // 4.当前节点 + 右边路径
        // 要计算左边路径、右边路径，再加上当前节点这个过程，就需要先知道左边路径和右边路径，所以考虑用递归的后序遍历来处理这个过程。


        //// 解法1： 后续遍历  写法3
        public int maxPathSum(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int[] maxSum = {Integer.MIN_VALUE};// 引用变量用于方法递归时传递参数，maxSum[0]存储最大路径和
            pathSum(root, maxSum);
            return maxSum[0];
        }


        // 求以node为根节点的二叉树中路径值最大的路径的路径值。这里的路径是传统的定义，从根节点出发的路径。
        // maxSum[0]保存二叉树中的最大路径和，maxSum[0]中所指的路径是从一个二叉树的一个节点到另一个节点的路径。
        private int pathSum(TreeNode node, int[] maxSum) {
            if (node == null) {
                return 0;
            }
            int leftPathSum = Math.max(pathSum(node.left, maxSum), 0);// 当前节点node的左子树向node节点的最大贡献值
            int rightPathSum = Math.max(pathSum(node.right, maxSum), 0);
            // 通过当前节点 node 的路径路径的节点和的最大值
            maxSum[0] = Math.max(maxSum[0], leftPathSum + rightPathSum + node.val);// 更新当前二叉树中的路径和的最大值
            return Math.max(leftPathSum, rightPathSum) + node.val;// 返回从node节点出发向下的路径能向上贡献的最大值
        }


        //// 解法1： 后续遍历  写法1
        //int maxSum = Integer.MIN_VALUE;// 全局变量 存储最大路径和
        //public int maxPathSum(TreeNode root) {
        //    if (root == null) {
        //        return 0;
        //    }
        //    pathSum(root);
        //    return maxSum;
        //}
        //
        //
        //// 后续遍历  函数pathSum的返回值是经过当前节点root并前往其左子树或右子树【两个最多只能选一个】的路径的节点值之和的最大值。
        //private int pathSum(TreeNode root) {// pathSum：在以该节点为根节点的子树中寻找以该节点为起点的一条路径，使得该路径上的节点值之和最大。
        //    if(root == null) {// 当遍历到null节点时，null 子树提供不了收益，返回 0。
        //        return 0;
        //    }
        //    int leftPath = pathSum(root.left);  // leftPath表示经过root.left并前往其左子树或者右子树的路径的节点值之和的最大值
        //    int rightPath = pathSum(root.right);
        //
        //    // 维护一个全局变量 maxSum 存储最大路径和，在递归过程中更新 maxSum 的值。
        //    // 找出路径中包含该节点的节点值之和最大的路径。注意这里的maxSum是整个子数中的最大路径和，与方法的返回值区分开，两个没关系
        //    // 其中 root.val + left + right 表示通过该节点的A型子树的节点路径和
        //    maxSum = Math.max(maxSum, root.val);// 当前节点 单独组成的路径
        //    maxSum = Math.max(maxSum, leftPath + root.val + rightPath);// 左+当前+右 组成的路径
        //    maxSum = Math.max(maxSum, leftPath + root.val);// 左+当前
        //    maxSum = Math.max(maxSum, root.val + rightPath);// 当前+右
        //
        //    // 返回以当前节点node为根节点的子树能向父节点“提供”的最大路径和。即，一条从父节点延伸下来的路径，能在当前子树中捞取的最大收益。
        //    // 路径每到一个节点，有 3 种选择：1.路径截至到当前节点；2.路径经过当前节点进入左子树；3.路径经过当前节点进入右子树；
        //    // 最终返回的为三种情况的最大值。
        //    //  return Math.max(root.val, Math.max(root.val + leftPath, root.val + rightPath));
        //    return root.val+ Math.max(0,Math.max(leftPath,rightPath));// 这里0表示不进入左右子路，
        //}




        //// 解法1： 后续遍历  写法1优化 写法2,注意写法1和2对应起来看
        //// 如果左边路径或者右边路径返回负数，而负数加入当前路径会让路径和变小，那么就不考虑返回负数的路径。
        //// 所以如果左边路径或者右边路径返回值为负数的话，就让左边路径或者右边路径的值为0，这样也就是通过该节点，但是不进入路径返回值为负数的左边路径或者右边路径。

        //int maxSum = Integer.MIN_VALUE;// 维护一个全局变量 maxSum 存储最大路径和
        //public int maxPathSum(TreeNode root) {
        //    if (root == null) {// 当遍历到null节点时，null 子树提供不了收益，返回 0。
        //        return 0;
        //    }
        //    pathSum(root);
        //    return maxSum;
        //}
        //
        //
        //// 函数pathSum的返回值是经过当前节点root并前往其左子树或右子树【两个最多只能选一个】的路径的节点值之和的最大值。
        //private int pathSum(TreeNode root) {
        //    if (root == null) {// 当遍历到null节点时，null 子树提供不了收益，返回 0。
        //        return 0;
        //    }
        //    // 对于二叉树中的一个节点，该节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值，
        //    // 如果子节点的最大贡献值为正，则计入该节点的最大路径和，否则不计入该节点的最大路径和。
        //    // 如果当前节点的左子节点的最大路径和是负数，那么就选择不进入当前节点的左子节点，则左子节点的贡献就可以认为是0。
        //    // 0：表示在该节点时不选择进入左子树，也就是左子树的贡献是负数，所以不选择该路线；
        //    // pathSum(root.left)：表示从该节点的左子节点进入以左子节点为根节点的子树中的路径。
        //
        //    int left = Math.max(0, pathSum(root.left));//  这里left就相当于两种情况，不进入左子树和进入左子树。
        //    int right = Math.max(0, pathSum(root.right));
        //    // 维护一个全局变量 maxSum 存储最大路径和，在递归过程中更新 maxSum 的值。
        //    // 找出路径中包含该节点的节点值之和最大的路径
        //    // 注意这里的maxSum是整个子树中的最大路径和，与方法的返回值区分开，两个没关系
        //    // 其中 root.val + left + right 表示通过该节点的A型子树的节点路径和
        //    maxSum = Math.max(maxSum, root.val + left + right);
        //    // 返回以当前节点node为根节点的子树能向父节点“提供”的最大路径和。即，一条从父节点延伸下来的路径，能在当前子树中捞取的最大收益。
        //    // 路径每到一个节点，有 3 种选择：1.路径截至到当前节点；2.路径经过当前节点进入左子树；3.路径经过当前节点进入右子树；
        //    // 最终返回的为三种情况的最大值。这里left,right包含了可能为0的情况，也就是不选择进入左右子树的情况。
        //    return Math.max(left, right) + root.val;
        //}






        //// 解法2：这个理解起来没有前面的解法好理解

        //// 由于求左右子树的路径节点值之和的最大值与求整棵二叉树的路径节点值之和的最大值是同一个问题，因此用递归的代码解决这个问题最直观。
        //// 代码中的参数maxSum是路径节点值之和的最大值。由于递归函数dfs需要把这个最大值传给它的调用者，因此参数maxSum被定义为长度为1的数组。
        //// 先递归调用函数dfs求得左右子树的路径节点值之和的最大值maxSumLeft及maxSumRight，再求出经过当前节点root的路径的节点值之和的最大值，那么参数maxSum就是这3个值的最大值。
        //// 函数的返回值是经过当前节点root并前往其左子树或右子树的路径的节点值之和的最大值。
        //// 它的父节点要根据这个返回值求路径的节点值之和。
        //// 由于同时经过左右子树的路径不能经过父节点，因此返回值是变量left与right的较大值加上当前节点root的值。
        //
        //public int maxPathSum(TreeNode root) {
        //    if (root == null) {
        //        return 0;
        //    }
        //    // 参数maxSum是路径节点值之和的最大值。由于递归函数dfs需要把这个最大值传给它的调用者，因此参数maxSum被定义为长度为1的数组。
        //    int[] maxSum = {Integer.MIN_VALUE};// 维护一个全局变量 maxSum 存储最大路径和
        //    pathSum(root, maxSum);
        //    return maxSum[0];
        //}
        //
        //
        // 后序遍历
        // 返回以当前节点node为根节点的子树能向父节点“提供”的最大路径和。即，一条从父节点延伸下来的路径，能在当前子树中捞取的最大收益。
        // 路径每到一个节点，有 3 种选择：1.路径截至到当前节点；2.路径经过当前节点进入左子树；3.路径经过当前节点进入右子树；
        // 最终返回的为三种情况的最大值。
        // 函数pathSum的返回值是经过当前节点root并前往其左子树或右子树的路径的节点值之和的最大值。
        //private int pathSum(TreeNode root, int[] maxSum) {
        //    if (root == null) {// 当遍历到null节点时，null 子树提供不了收益，返回 0。
        //        return 0;
        //    }
        //    // 递归遍历左子树
        //    int[] maxSumLeft = {Integer.MIN_VALUE};
        //    // 如果当前节点的左子节点的最大路径和是负数，那么就选择不进入当前节点的左子节点，则左子节点的贡献就可以认为是0。
        //    // 0：表示在该节点不选择进入左子树，也就是当左子树的贡献是负数时不选择进入左子树；
        //    // pathSum(root.left, maxSumLeft)：表示从该节点root出发进入左子树root.left但是不包含该节点root。
        //    int left = Math.max(0, pathSum(root.left, maxSumLeft));// 求出左子树中路径节点值之和的最大值（左右子树中的路径不经过当前节点）
        //
        //    // 递归遍历右子树
        //    int[] maxSumRight = {Integer.MIN_VALUE};
        //    int right = Math.max(0, pathSum(root.right, maxSumRight)); // 求出右子树中路径节点值之和的最大值（左右子树中的路径不经过当前节点）
        //
        //    // 由于路径可能只经过左子树或右子树而不经过根节点，为了求得二叉树的路径上节点值之和的最大值，需要先求出左右子树中路径节
        //    // 点值之和的最大值（左右子树中的路径不经过当前节点），再求出经过根节点的路径节点值之和的最大值，最后对三者进行比较得到最大值。
        //    // 需要先求出左右子树中路径节点值之和的最大值（左右子树中的路径不经过当前节点）
        //    // maxSumLeft[0], maxSumRight[0] 求出左右子树中路径节点值之和的最大值（左右子树中的路径不经过当前节点）
        //    maxSum[0] = Math.max(maxSumLeft[0], maxSumRight[0]);
        //    // root.val + left + right：// 求出经过根节点的路径节点值之和的最大值
        //    maxSum[0] = Math.max(maxSum[0], root.val + left + right);
        //    // 返回以当前节点node为根节点的子树能向父节点“提供”的最大路径和。即，一条从父节点延伸下来的路径，能在当前子树中捞取的最大收益。
        //    // 路径每到一个节点，有 3 种选择：1.路径截至到当前节点；2.路径经过当前节点进入左子树；3.路径经过当前节点进入右子树；
        //    // 最终返回的为三种情况的最大值。
        //    return root.val + Math.max(left, right);// 处理当前节点
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
