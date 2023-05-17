/**
 * <p>给你一个整数数组 <code>nums</code> ，找到其中最长严格递增子序列的长度。</p>
 *
 * <p><strong>子序列&nbsp;</strong>是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，<code>[3,6,2,7]</code> 是数组 <code>[0,3,1,6,2,
 * 2,7]</code> 的子序列。</p>
 * &nbsp;
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [10,9,2,5,3,7,101,18]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,1,0,3,2,3]
 * <strong>输出：</strong>4
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [7,7,7,7,7,7,7]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 2500</code></li>
 * <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><b>进阶：</b></p>
 *
 * <ul>
 * <li>你能将算法的时间复杂度降低到&nbsp;<code>O(n log(n))</code> 吗?</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>动态规划</li></div></div><br><div><li>👍
 * 2677</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 300
 * 最长递增子序列
 *
 * @author wangweizhou
 * @date 2022-08-05 23:42:17
 */

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new LongestIncreasingSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法一：动态规划 写法2

        // 状态定义：dp[i] 表示：以 nums[i] 结尾 的「上升子序列」的长度。注意：这个定义中 nums[i] 必须被选取，且必须是这个子序列的最后一个元素；
        // 转移方程： 设 j∈[0,i)，考虑每轮计算新 dp[i] 时，遍历 [0,i) 列表区间，做以下判断：
        //      1.当 nums[i] > nums[j] 时： nums[i] 可以接在 nums[j] 之后（此题要求严格递增），此情况下最长上升子序列长度为 dp[j]+1 ；
        //      2.当 nums[i] <= nums[j] 时： nums[i]无法接在 nums[j] 之后，此情况上升子序列不成立，跳过。dp[i]=1。
        //      上述所有情况1.下计算出的 dp[j]+1 的最大值，为直到 i 的最长上升子序列长度（即 dp[i] ）。实现方式为遍历 j 时，每轮执行 dp[i]=max(dp[i],dp[j]+1)。
        //
        // 转移方程： dp[i] = max(dp[i], dp[j] + 1) 0<=j<i 且nums[j]<nums[i]。
        // 初始状态：dp[i] 所有元素置 1，含义是每个元素都至少可以单独成为子序列，此时长度都为 1。


        public int lengthOfLIS(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];// 定义：dp[i] 到nums[i]为止的最长递增子序列长度，到数组下标i为止的最长递增子序列长度。
            Arrays.fill(dp, 1);// base case：dp 数组全都初始化为 1，单个元素的子序列长度为1。
            int maxLen = 1;
            for (int i = 0; i < nums.length; i++) {// i是遍历nums数组的变量
                // 遍历下标i之前的所有元素j
                for (int j = 0; j < i; j++) {// j是遍历nums数组中当前元素nums[i]前面元素的变量
                    // 寻找 nums[0..i-1] 中比 nums[i] 小的元素,其实是在nums[0..i-1] 中比 nums[i] 小的元素
                    if (nums[i] > nums[j]) {// 当下标i之前的元素比下标i的元素小时
                        dp[i] = Math.max(dp[i], dp[j] + 1);//位置i的最长递增子序列长度 等于j从0到i-1各个位置的最长升序子序列 + 1的最大值。
                    }
                }
            }

            //最后单独使用for循环来查找最大值，放在双层循环里面的话就会使得时间复杂度上升
            for (int i = 0; i < dp.length; i++) {
                maxLen = Math.max(dp[i], maxLen);
            }
            return maxLen;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
