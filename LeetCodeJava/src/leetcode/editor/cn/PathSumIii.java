/**
 * <p>给定一个二叉树的根节点 <code>root</code> ，和一个整数 <code>targetSum</code> ，求该二叉树里节点值之和等于 <code>targetSum</code> 的
 * <strong>路径</strong> 的数目。</p>
 *
 * <p><strong>路径</strong> 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/04/09/pathsum3-1-tree.jpg" style="width: 452px; " /></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * <strong>输出：</strong>3
 * <strong>解释：</strong>和等于 8 的路径有 3 条，如图所示。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li>二叉树的节点个数的范围是 <code>[0,1000]</code></li>
 * <li><meta charset="UTF-8" /><code>-10<sup>9</sup> <= Node.val <= 10<sup>9</sup></code> </li>
 * <li><code>-1000 <= targetSum <= 1000</code> </li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 1501</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 437
 * 路径总和 III
 *
 * @author wangweizhou
 * @date 2022-11-19 16:05:22
 */

public class PathSumIii {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new PathSumIii().new Solution();
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


        // 注意这个路径是从任意一个节点向下一个节点就可以。不一定包含根节点和叶子节点
        // 如果在路径上移动时把所有累加的节点值之和都保存下来，就容易知道是否存在从任意节点出发的值为给定sum 的路径

        //// 在同一条路径之下（可以理解成二叉树从root节点出发，到叶子节点的某一条路径），如果两个数的前缀总和是相同的，那么这些节点之间的元素总和为零。
        //// 进一步扩展相同的想法，如果前缀总和currSum，在节点A和节点B处前缀总和相差target，则位于节点A和节点B之间的元素之和是target。
        //// 抵达当前节点(即B节点)后，将前缀和累加，然后查找在前缀和中有没有前缀和currSum-target的节点(即A节点)，存在即表示从A到B有一条路径之和满足条件的情况。
        //// 结果加上满足前缀和currSum-target的节点的数量。
        //// 然后递归进入左右子树。左右子树遍历完成之后，回到当前层，需要把当前节点添加的前缀和去除，避免回溯之后影响上一层。因为思想是前缀和，不属于前缀的，我们就要去掉它。


        //// 虽然路径不一定从根节点开始，但仍然可以求得从根节点开始到达当前遍历节点的路径所经过的节点值之和。
        //// 如果在路径上移动时把所有累加的节点值之和都保存下来，就容易知道是否存在从任意节点出发的值为给定sum的路径。
        //// 当遍历到一个节点时，先累加从根节点开始的路径上的节点值之和，再计算到它的左右子节点的路径的节点值之和。

        //// 哈希表的键key是累加的节点值之和，哈希表的值value是每个节点值之和出现的次数。
        //// 当遍历到一个节点时，就把当前的节点值累加到参数path。
        //// 如果这个和之前出现过，则将出现的次数加1；如果这个和之前没有出现过，那么这是它第1次出现,也就是这个和的出现次数是0。
        //// 然后更新哈希表map保存累加节点值之和path及出现的次数。
        //// 辅助函数dfs实现了递归的前序遍历，该函数遍历到二叉树的一个节点时将递归地遍历它的子节点。
        //// 因此，当该函数结束时，程序将回到节点的父节点，也就是说，在函数结束之前需要将当前节点从路径中删除，从根节点到当前节点累加的节点值之和也要从哈希表map中删除。
        //// 这是在函数dfs返回之前更新哈希表map把参数path出现的次数减1的原因。


        // 解法1：写法2 这个没有返回值的比下面那个有返回值的更好理解
        // 只有在处理当前节点的时候，才累加路径个数的结果
        public int pathSum(TreeNode root, int sum) {
            if (root == null) {// 判空
                return 0;
            }
            int[] count = {0};// 使用数组元素来传递参数，引用变量在递归的时候可以传递
            // 哈希表的键key是累加的节点值之和，哈希表的值value是每个节点值之和出现的次数
            Map<Long, Integer> prefixSum = new HashMap<>();// 注意这个key是Long类型
            // 预处理，前缀和为0的一条路径，还没有遍历二叉树，所以可以认为前缀和是0，并且是第一次出现
            prefixSum.put(0L, 1);
            dfs(root, sum, 0, prefixSum, count);
            // 前缀和的递归回溯思路
            return count[0];
        }



        // 前序遍历 前缀和的递归回溯思路    因为这里只是对保存前缀和的哈希表的进行处理，所以回溯的时候也只是对保存前缀和的哈希表进行回溯处理。
        // node：树节点；targetSum：目标值；pathSum：当前路径和；prefixSumCount：前缀和Map；counts：二叉树中路径和等于targetSum的路径数目
        private void dfs(TreeNode node, int targetSum, long pathSum, Map<Long, Integer> prefixSum, int[] counts) {
            if (node == null) {
                return;
            }
            // 当前路径上的和,也就是从根节点到当前节点的路径上的节点和
            pathSum += node.val;// 用参数pathSum表示从根节点开始的路径已经累加的节点值之和，并保存到哈希表map中。
            // 看看root到当前节点这条路上是否存在节点前缀和加target为pathSum的路径。
            // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为pathSum-target,而当前的和又为pathSum,两者的差就肯定为target了。
            // pathSum-target相当于找路径的起点，起点的sum+target=pathSum，当前点到起点的距离就是target。
            counts[0] += prefixSum.getOrDefault(pathSum - targetSum, 0);// 获取以当前节点结束的满足条件的路径节点值之和
            // 如果这个路径和pathSum之前出现过，则将出现的次数加1；如果这个和之前没有出现过，那么这是它第1次出现，则次数为0。
            prefixSum.put(pathSum, prefixSum.getOrDefault(pathSum, 0) + 1);
            //　递归遍历左右子树
            dfs(node.left, targetSum, pathSum, prefixSum, counts);
            dfs(node.right, targetSum, pathSum, prefixSum, counts);
            //　回溯
            // 恢复状态，去除当前节点的前缀和数量，因为这里是引用类型变量，所以需要回溯清除状态。
            // 因此，当该函数结束时，程序将回到节点的父节点，也就是说，在函数结束之前需要将当前节点从路径中删除，从根节点到当前节点累加的节点值之和也要从哈希表map中删除。
            // 左右子树递归完要将当前节点从路径中删除，从根节点到当前节点累加的节点值之和也要从哈希表map中删除。
            // 所以函数在返回之前要更新哈希表map把参toSum出现的次数减1。
            prefixSum.put(pathSum, prefixSum.get(pathSum) - 1);//
        }





        // 解法1：写法1
        //public int pathSum(TreeNode root, int sum) {
        //    if (root == null) {
        //        return 0;
        //    }
        //    // 哈希表的键key是累加的节点值之和，哈希表的值value是每个节点值之和出现的次数
        //    Map<Long, Integer> prefixSumCount = new HashMap<>();
        //    // 预处理，前缀和为0的一条路径，还没有遍历二叉树，所以可以认为前缀和是0，并且是第一次出现
        //    prefixSumCount.put(0L, 1);
        //    // 前缀和的递归回溯思路
        //    return recursionPathSum(root, sum, 0L, prefixSumCount);
        //}
        //
        //
        //
        //// 前序遍历 前缀和的递归回溯思路    因为这里只是对保存前缀和的哈希表的进行处理，所以回溯的时候也只是对保存前缀和的哈希表进行回溯处理。
        //// node：树节点；targetSum：目标值；pathSum：当前路径和；prefixSumCount：前缀和Map。
        //private int recursionPathSum(TreeNode node, int targetSum, long pathSum, Map<Long, Integer> prefixSumCount) {
        //    if (node == null) {
        //        return 0;
        //    }
        //    int count = 0;// 二叉树中路径和等于targetSum的路径数目
        //    // 当前路径上的和,也就是从根节点到当前节点的路径上的节点和
        //    pathSum += node.val;// 用参数pathSum表示从根节点开始的路径已经累加的节点值之和，并保存到哈希表map中。
        //
        //    // 看看root到当前节点这条路上是否存在节点前缀和加target为pathSum的路径
        //    // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为pathSum-target,而当前的和又为pathSum,两者的差就肯定为target了
        //    // pathSum-target相当于找路径的起点，起点的sum+target=pathSum，当前点到起点的距离就是target
        //    count += prefixSumCount.getOrDefault(pathSum - targetSum, 0);// 获取以当前节点结束的满足条件的路径节点值之和
        //
        //    // 如果这个路径和pathSum之前出现过，则将出现的次数加1；如果这个和之前没有出现过，那么这是它第1次出现，则次数为0。
        //    prefixSumCount.put(pathSum, prefixSumCount.getOrDefault(pathSum, 0) + 1);// 更新路径上当前节点前缀和的个数
        //    // 辅助函数recursionPathSum实现了递归的前序遍历，该函数遍历到二叉树的一个节点时将递归地遍历它的子节点。
        //    // 递归遍历当前节点的左右子节点，并将左右子树中符合目标和targetSum的路径数计入总数
        //    count += recursionPathSum(node.left, targetSum, pathSum, prefixSumCount);
        //    count += recursionPathSum(node.right, targetSum, pathSum, prefixSumCount);
        //
        //    // 恢复状态，去除当前节点的前缀和数量，因为这里是引用类型变量，所以需要回溯清除状态。
        //    // 因此，当该函数结束时，程序将回到节点的父节点，也就是说，在函数结束之前需要将当前节点从路径中删除，从根节点到当前节点累加的节点值之和也要从哈希表map中删除。
        //    // 左右子树递归完要将当前节点从路径中删除，从根节点到当前节点累加的节点值之和也要从哈希表map中删除。
        //    // 所以函数在返回之前要更新哈希表map把参toSum出现的次数减1。
        //    prefixSumCount.put(pathSum, prefixSumCount.get(pathSum) - 1);
        //    return count;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
