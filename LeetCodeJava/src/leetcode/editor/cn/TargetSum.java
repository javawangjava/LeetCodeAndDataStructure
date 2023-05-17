/**
 * <p>给你一个整数数组 <code>nums</code> 和一个整数 <code>target</code> 。</p>
 *
 * <p>向数组中的每个整数前添加 <code>'+'</code> 或 <code>'-'</code> ，然后串联起所有整数，可以构造一个 <strong>表达式</strong> ：</p>
 *
 * <ul>
 * <li>例如，<code>nums = [2, 1]</code> ，可以在 <code>2</code> 之前添加 <code>'+'</code> ，在 <code>1</code> 之前添加
 * <code>'-'</code> ，然后串联起来得到表达式 <code>"+2-1"</code> 。</li>
 * </ul>
 *
 * <p>返回可以通过上述方法构造的、运算结果等于 <code>target</code> 的不同 <strong>表达式</strong> 的数目。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,1,1,1,1], target = 3
 * <strong>输出：</strong>5
 * <strong>解释：</strong>一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1], target = 1
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 20</code></li>
 * <li><code>0 <= nums[i] <= 1000</code></li>
 * <li><code>0 <= sum(nums[i]) <= 1000</code></li>
 * <li><code>-1000 <= target <= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>回溯</li></div></div><br><div><li>👍 1449</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 494
 * 目标和
 *
 * @author wangweizhou
 * @date 2022-12-03 09:46:32
 */

public class TargetSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new TargetSum().new Solution();
        int ans = solution.findTargetSumWays(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, 1);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 分析：在分析解决这个问题之前，需要先做数学运算。为输入的数组中的有些数字添加“+”，有些数字添加“-”。
        // 如果所有添加“+”的数字之和为p，所有添加“-”的数字之和为q，按照题目的要求，p-q=S。
        // 如果累加数字中的所有数字，就能得到整个数组的数字之和，记为sum，即p+q=sum。将这两个式子相减，2q=sum-S,即q=（sum-S）/2。

        // 为什么求非正数就错了？没搞懂
        // 将这两个等式的左右两边分别相加，就可以得到2p=S+sum，即p=（S+sum）/2。
        // 上面的等式表明，如果能够找出数组中和为（S+sum）/2的数字，并给它们添加“+”，然后给其他数字添加“-”，那么最终的计算结果就是S。
        // 因此，这个题目等价于计算从数组中选出和为（S+sum）/2的数字的方法的数目。这是和前面的面试题非常类似的题目，是一个典型的0-1背包问题，可以用动态规划解决。

        // 用动态规划求解问题的关键在于确定状态转移方程。
        // 可以用函数f（i，j）表示在数组的前i个数字（即nums[0..i-1]）中选出若干数字使和等于j的方法的数目。如果数组的长度为n，目标和为t，那么f（n，t）就是整个问题的解。
        // 这个问题的状态转移方程和前面的非常类似，唯一的区别在于这里的f（i，j）的值不再只是一个true或false的标识，而是一个数值。


        //// 动态规划 参考 416
        public int findTargetSumWays(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int sum = 0;
            for (int num : nums) {// 求数组和
                sum += num;
            }
            // 2q=sum - target,所以sum - target是偶数。
            // sum < target：所有数是正数都不可以。
            if ((sum - target) % 2 == 1 || sum < target) {
                return 0;
            }
            return subsetSum(nums, (sum - target) / 2);
        }


        //// 可以用函数f（i，j）表示从前i个物品（物品标号分别为0，1，…，i-1）中选择若干物品放满容量为j的背包的方法数。
        //// 因为状态转移方程有f(-1,j)或者f(i,-1)，所以将状态方程统一右移一个单位。
        //// 因为参数是数组，数组下标从0到i，已经有（i+1）个元素。
        //
        //// dp[i+1][j+1]表示从[0,i]个物品中选择若干物品放满容量为j的背包的方法数。
        //// 定义二维数组 dp，其中 dp[i][j] 表示在数组 nums 的前 i 个数【0，i-1】中选取元素，使得这些元素之和等于 j 的方案数。
        //// 假设数组 nums 的长度为 n，则最终答案为 dp[n][neg]。
        //
        //// 当没有任何元素可以选取时，元素和只能是 0，对应的方案数是 1，因此动态规划的边界条件是：
        //// dp[0][j] = 1,j==0; dp[0][j] = 0,j>0;

        private int subsetSum(int[] nums, int target) {
            int[][] dp = new int[nums.length + 1][target + 1];
            // 当没有任何元素可以选取时，元素和只能是 0，对应的方案数是 1，因此动态规划的边界条件是：
            // dp[0][j] = 1,j==0; dp[0][j] = 0,j>0;

            // 初始状态：目标和为0，0个元素，那么也就没有任何元素可以选，方法数为1。
            dp[0][0] = 1;

            for (int i = 1; i <= nums.length; i++) {
                for (int j = 0; j <= target; j++) {
                    if (j >= nums[i - 1]) {
                        // 如果 j≥num，则如果不选 num，方案数是 dp[i−1][j]，如果选num，方案数是 dp[i−1][j−num]，
                        // 此时有dp[i][j]=dp[i−1][j]+dp[i−1][j−num]。
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                    } else {// 如果 j<num，则不能选 num，此时有 dp[i][j]=dp[i−1][j]；
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[nums.length][target];
        }





        //// 优化空间  将二维动态数组优化为一维动态数组
        //private int subsetSum(int[] nums, int target) {
        //    int[] dp = new int[target + 1];
        //    // 当没有任何元素可以选取时，元素和只能是 0，对应的方案数是 1，因此动态规划的边界条件是：
        //    // 初始状态：目标和为0，0个元素，那么也就没有任何元素可以选，方法数为1。
        //    dp[0] = 1;
        //
        //    for (int i = 1; i <= nums.length; i++) {
        //        for (int j = target; j >=0; j--) {
        //            // 优化空间效率之后，代码中的f（i，j）和f（i-1，j）都保存在“dp[j]”中。
        //            // 上述代码看起来只考虑了当选择下标为i-1的数字时f（i，j）等于f（i-1，j-nums[i-1]）的场景。
        //            // 这是因为当不选择下标为i-1的数字时，f（i，j）等于f（i-1，j），
        //            // 而f（i，j）和f（i-1，j）都保存在“dp[j]”中，写成代码就是“dp[j]=dp[j]”，这一行代码被省略了。
        //            // dp[j]为false,就说明没有找到等和子集
        //            if(j>=nums[i-1]){
        //                dp[j]=dp[j]+dp[j-nums[i-1]];
        //            }
        //        }
        //    }
        //    return dp[target];
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
