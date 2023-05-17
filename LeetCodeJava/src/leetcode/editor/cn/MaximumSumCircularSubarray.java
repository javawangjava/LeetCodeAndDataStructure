/**
 * <p>给定一个长度为 <code>n</code> 的<strong>环形整数数组</strong>&nbsp;<code>nums</code>&nbsp;，返回<em>&nbsp;
 * <code>nums</code>&nbsp;的非空 <strong>子数组</strong> 的最大可能和&nbsp;</em>。</p>
 *
 * <p><strong>环形数组</strong><em>&nbsp;</em>意味着数组的末端将会与开头相连呈环状。形式上， <code>nums[i]</code> 的下一个元素是 <code>nums[(i + 1) %
 * n]</code> ， <code>nums[i]</code>&nbsp;的前一个元素是 <code>nums[(i - 1 + n) % n]</code> 。</p>
 *
 * <p><strong>子数组</strong> 最多只能包含固定缓冲区&nbsp;<code>nums</code>&nbsp;中的每个元素一次。形式上，对于子数组&nbsp;<code>nums[i], nums[i +
 * 1], ..., nums[j]</code>&nbsp;，不存在&nbsp;<code>i &lt;= k1, k2 &lt;= j</code>&nbsp;其中&nbsp;<code>k1 % n == k2 %
 * n</code>&nbsp;。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,-2,3,-2]
 * <strong>输出：</strong>3
 * <strong>解释：</strong>从子数组 [3] 得到最大和 3
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [5,-3,5]
 * <strong>输出：</strong>10
 * <strong>解释：</strong>从子数组 [5,5] 得到最大和 5 + 5 = 10
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,-2,2,-3]
 * <strong>输出：</strong>3
 * <strong>解释：</strong>从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == nums.length</code></li>
 * <li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li>
 * <li><code>-3 * 10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 3 * 10<sup>4</sup></code>​​​​​​​</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>队列</li><li>数组</li><li>分治</li><li>动态规划</li><li>单调队列</li></div></div><br><div
 * ><li>👍 386</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 918
 * 环形子数组的最大和
 * @author wangweizhou
 * @date 2022-08-07 11:45:52
 */
public class MaximumSumCircularSubarray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MaximumSumCircularSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法一：动态规划
        // 定义状态：dpMax[i]：到nums[i]为止的最大子数组和。dpMin[i]：到nums[i]为止的最小子数组和

        //情况一 最大子序列和位于中间位置。最大子数组不成环 --- 53题 也就是maxSum为答案
        //情况二 最大子序列和位于两边。最大子数组成环 ，那么最小子数组就不会成环 --- (total - minSum) 则为答案
        //情况三 全为负数。   数组全为负数的情况下，情况二的值等于 0 这是小显而易见的，所以当数组全为负数的情况下最大值就应该是 maxSum（第一种情况）
        //如果数组不全是负值，则比较 第一种情况和第二种情侣 的 maxSum 大小大的就是我们想要的结果。

        //1. 若未组成环，则直接遍历一遍即可
        //2. 若组成环，我们需要考虑环状问题
        //组成环，则最大子数组必然包括 nums[0] 与 nums[n-1] 并向两端延伸若干元素
        //可以看出，我们很难正向求得带环的最大子数组，但由于最大子数组已成环状，故从整个环中扣去最大子数组的剩余部分必不为环状。
        // 正难则反，我们可以求出这一部分，并将其从环总和中减去，即可得出最大子数组


        public int maxSubarraySumCircular(int[] nums) {
            if (nums == null || nums.length == 0) {//判空
                return 0;
            }
            int length = nums.length;
            if (length == 1) {
                return nums[0];
            }

            int[] dpMax = new int[length];//dpMax[i]：到nums[i]为止的最大子数组和。
            dpMax[0] = nums[0];// dpMax[0] 根据定义，只有 1 个数，一定以 nums[0] 结尾，因此 dpMax[0]=nums[0]
            // max存储最大子数组和，min存储最小子数组和，sum存储数组总和
            int max = nums[0], sum = nums[0];
            for (int i = 1; i < length; i++) {
                dpMax[i] = Math.max(dpMax[i - 1] + nums[i], nums[i]);//计算出dpMax[i]
                max = Math.max(dpMax[i], max);//更新max
                sum += nums[i];//计算数组总和
            }

            int[] dpMin = new int[length];//dpMin[i]：到nums[i]为止的最小子数组和
            dpMin[0] = nums[0];// dpMin[0] 根据定义，只有 1 个数，一定以 nums[0] 结尾，因此 dpMin[0]=nums[0]
            int min = nums[0];
            for (int i = 1; i < length; i++) {
                dpMin[i] = Math.min(dpMin[i - 1] + nums[i], nums[i]);
                min = Math.min(dpMin[i], min);
            }
            if (sum == min) {// 此处要注意全为负数
                return max;
            }
            return Math.max(max, sum - min);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
