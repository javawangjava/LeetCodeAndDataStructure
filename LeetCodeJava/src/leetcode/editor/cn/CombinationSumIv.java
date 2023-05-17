/**
 * <p>给你一个由 <strong>不同</strong> 整数组成的数组 <code>nums</code> ，和一个目标整数 <code>target</code> 。请你从 <code>nums</code>
 * 中找出并返回总和为 <code>target</code> 的元素组合的个数。</p>
 *
 * <p>题目数据保证答案符合 32 位整数范围。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3], target = 4
 * <strong>输出：</strong>7
 * <strong>解释：</strong>
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [9], target = 3
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 200</code></li>
 * <li><code>1 <= nums[i] <= 1000</code></li>
 * <li><code>nums</code> 中的所有元素 <strong>互不相同</strong></li>
 * <li><code>1 <= target <= 1000</code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 722</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 377
 * 组合总和 Ⅳ
 * @author wangweizhou
 * @date 2022-12-03 15:42:14
 */

public class CombinationSumIv {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new CombinationSumIv().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public int combinationSum4(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            if (target < 0) {
                return 0;
            }
            int[][] dp = new int[nums.length + 1][target + 1];// 注意这里对dp数组右移了一位
            for (int i = 0; i <= nums.length; i++) {
                dp[i][0] = 1;// 凑一个目标整数为0的组合，这时候就可以什么都不选，只有一种选法
            }
            for (int j = 1; j <= target; j++) {
                dp[0][j] = 0;// 表示从前 0 种硬币中选出若干个组成金额 j 所对应的选法种数为0。
            }

            //
            for (int j = 1; j <= target; j++) {
                for (int i = 1; i <= nums.length; i++) {
                    if (j >= nums[i - 1]) {
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[nums.length][target];
        }






        //	用f（i）表示和为i的排列的数目。为了得到和为i的排列，有如下选择：在和为i-nums[0]的排列中添加标号为0的数字，此时f（i）等于f（i-nums[0]）；
        //	在和为i-nums[1]的排列中添加标号为1的数字，此时f（i）等于f（i-nums[1]）。
        //	以此类推，在和为i-nums[n-1]的排列中添加标号为n-1的数字（n为数组的长度），此时f（i）等于f（i-nums[n-1]）。
        // 因为目标是求出所有和为i的排列的数目，所以将上述所有情况全部累加起来。
        // 该状态转移方程可以表示为f (i)=∑f (i-nums[j])(nums[j]≤i)由于只有一个空排列的数字之和等于0，因此f（0）等于1。

        // 解法1：动态规划
        //public int combinationSum4(int[] nums, int target) {
        //	if(nums==null||nums.length==0){
        //		return -1;
        //	}
        //	if(target<0){
        //		return 0;
        //	}
        //	int[] dp=new int[target+1];
        //
        //	dp[0]=1;
        //	for (int i = 1; i <=target ; i++) {
        //		for (int num:nums) {
        //			if(i>=num){
        //				dp[i]+=dp[i-num];
        //			}
        //		}
        //	}
        //	return dp[target];
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
