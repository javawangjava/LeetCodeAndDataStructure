/**
 * <p>给你一个整数数组 <code>nums</code> ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。</p>
 *
 * <p><strong>子数组 </strong>是数组中的一个连续部分。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [-2,1,-3,4,-1,2,1,-5,4]
 * <strong>输出：</strong>6
 * <strong>解释：</strong>连续子数组&nbsp;[4,-1,2,1] 的和最大，为&nbsp;6 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [5,4,-1,7,8]
 * <strong>输出：</strong>23
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>如果你已经实现复杂度为 <code>O(n)</code> 的解法，尝试使用更为精妙的 <strong>分治法</strong> 求解。</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>动态规划</li></div></div><br><div><li>👍 5196</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 53
 * 最大子数组和
 *
 * @author wangweizhou
 * @date 2022-08-07 10:37:57
 */

public class MaximumSubarray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MaximumSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length == 0) {// 判空
                return 0;
            }
            int len = nums.length;
            int[] dp = new int[len];// dp[i]：表示以 nums[i] 结尾的连续子数组的最大和，所以nums[i]一定会被选取。
            int maxSum = nums[0];
            dp[0] = nums[0];
            for (int i = 1; i < len; i++) {
                if (dp[i - 1] > 0) {
                    dp[i] = dp[i - 1] + nums[i];
                } else {
                    dp[i] = nums[i];
                }
                maxSum = Math.max(maxSum, dp[i]);// 更新最大的子数组和
            }
            return maxSum;
        }


        //// 方法一：动态规划 写法1
        //// 定义状态（定义子问题）dp[i]：表示以 nums[i] 结尾的连续子数组的最大和，所以nums[i]一定会被选取。
        //// 并且以 nums[i] 结尾的连续子数组与以 nums[i - 1] 结尾的连续子数组只相差一个元素 nums[i] 。
        //// 状态转移方程（描述子问题之间的联系） dp[i]=max{nums[i],dp[i−1]+nums[i]}
        //// 当 dp[i - 1] > 0时，则 dp[i] = dp[i-1] + nums[i]，因为此时 dp[i - 1] 产生正向增益，所以要加上去
        //// 当 dp[i - 1] ≤ 0 时，则 dp[i] = nums[i]，因为此时 dp[i - 1] 产生负向增益，所以不需要添加


        //public int maxSubArray(int[] nums) {
        //    if (nums == null || nums.length == 0) {// 判空
        //        return 0;
        //    }
        //    int[] dp = new int[nums.length];// dp[i]：表示以 nums[i] 结尾的连续子数组的最大和，所以nums[i]一定会被选取。
        //    dp[0] = nums[0];// dp[0] 根据定义，只有 1 个数，一定以 nums[0] 结尾，因此 dp[0] = nums[0]。
        //
        //    for (int i = 1; i < nums.length; i++) {
        //        //dp[i]：表示以 nums[i] 结尾的连续子数组的最大和,所以nums[i]一定会被选取.。
        //        if(dp[i-1]>0){
        //            dp[i] = dp[i - 1] + nums[i];// 当 dp[i - 1] > 0时，则 dp[i] = dp[i-1] + nums[i]，因为此时 dp[i - 1]
        //            产生正向增益，所以要加上去
        //        }else {
        //            dp[i] = nums[i];// 当 dp[i - 1] ≤ 0 时，则 dp[i] = nums[i]，因为此时 dp[i - 1] 产生负向增益，所以不需要添加
        //        }
        //    }
        //
        //    // 循环遍历找出dp数组中的最大值
        //    int maxSum = dp[0];//maxSum最大和
        //    for (int i = 0; i < dp.length; i++) {
        //        if (dp[i] > maxSum) {
        //            maxSum = dp[i];
        //        }
        //    }
        //    return maxSum;
        //}




        //// 方法一：动态规划+动态数组 写法2
        //public int maxSubArray(int[] nums) {
        //    if (nums == null || nums.length == 0) {// 判空
        //        return 0;
        //    }
        //    int currSum=0;// 表示包含当前位置元素的子数组和。刚开始没有元素所以初始化为0。
        //    int maxSum=Integer.MIN_VALUE;// [left,right]数组的连续子数组的最大和
        //    int len=nums.length;
        //
        //    for (int i = 0; i < len; i++) {
        //        if (currSum<=0){// 当当前的累加子数组和小于等于0时，那么就重新赋值为当前位置的元素
        //            // 因为累加子数组和为负数时，当前位置元素加上负数会更小，所以包含当前位置元素的子数组和就是当前元素
        //            currSum=nums[i];
        //        }else {// 当当前的累加子数组和大于0时，
        //            // 当前位置元素加上正数会变大
        //            currSum+=nums[i];
        //        }
        //        // 更新最大子数组和
        //        if(currSum>maxSum){
        //            maxSum=currSum;
        //        }
        //    }
        //    return maxSum;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}

