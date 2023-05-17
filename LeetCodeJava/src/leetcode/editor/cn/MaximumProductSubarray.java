/**
 * <p>给你一个整数数组 <code>nums</code>&nbsp;，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。</p>
 *
 * <p>测试用例的答案是一个&nbsp;<strong>32-位</strong> 整数。</p>
 *
 * <p><strong>子数组</strong> 是数组的连续子序列。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [2,3,-2,4]
 * <strong>输出:</strong> <code>6</code>
 * <strong>解释:</strong>&nbsp;子数组 [2,3] 有最大乘积 6。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [-2,0,-1]
 * <strong>输出:</strong> 0
 * <strong>解释:</strong>&nbsp;结果不能为 2, 因为 [-2,-1] 不是子数组。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
 * <li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
 * <li><code>nums</code> 的任何前缀或后缀的乘积都 <strong>保证</strong>&nbsp;是一个 <strong>32-位</strong> 整数</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1741</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 152
 * 乘积最大子数组
 * @author wangweizhou
 * @date 2022-08-06 22:32:14
 */

public class MaximumProductSubarray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MaximumProductSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


/*
	// 解法1：动态规划 写法2
	public int maxProduct(int[] nums) {
		if(nums==null||nums.length==0){
			return 0;
		}
		int max = Integer.MIN_VALUE, imax = 1, imin = 1;
		for(int i=0; i<nums.length; i++){
			if(nums[i] < 0){
				int tmp = imax;
				imax = imin;
				imin = tmp;
			}
			imax = Math.max(imax*nums[i], nums[i]);
			imin = Math.min(imin*nums[i], nums[i]);

			max = Math.max(max, imax);
		}
		return max;
	}
*/



        // 方法一：动态规划
        // 遍历数组时计算当前最大值，不断更新;
        // 令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i]);
        // 由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i]);
        // 当负数出现时则imax与imin进行交换再进行下一步计算;

        // 定义状态（定义子问题）dp[i]：表示以 nums[i] 结尾的连续子数组的最大乘积,子数组一定要包含以i结尾的元素;
        // 状态转移方程：maxdp[i]=max{nums[i],dp[i−1]*nums[i]};
        // mindp[i]=min{nums[i],dp[i−1]*nums[i]};


        public int maxProduct(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            // 两个mDP分别定义为以i结尾的子数组的最大积与最小积；
            int[] maxDP = new int[nums.length];
            int[] minDP = new int[nums.length];
            // 初始化DP；
            maxDP[0] = nums[0];
            minDP[0] = nums[0];
            int maxProduct = nums[0];

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > 0) {// 当当前元素为正时
                    maxDP[i] = Math.max(maxDP[i - 1] * nums[i], nums[i]);
                    minDP[i] = Math.min(minDP[i - 1] * nums[i], nums[i]);
                } else if (nums[i] == 0) {
                    maxDP[i] = 0;
                    minDP[i] = 0;
                } else {// 由于存在负数，那么会导致最大的变最小的，最小的变最大的。
                    minDP[i] = Math.min(maxDP[i - 1] * nums[i], nums[i]);
                    maxDP[i] = Math.max(minDP[i - 1] * nums[i], nums[i]);
                }
                ////最大积的可能情况有：元素i自己本身，上一个最大积与i元素累乘，上一个最小积与i元素累乘；
                ////与i元素自己进行比较是为了处理i元素之前全都是0的情况；
                //maxDP[i] = Math.max(nums[i], Math.max(maxDP[i-1]*nums[i], minDP[i-1]*nums[i]));
                //minDP[i] = Math.min(nums[i], Math.min(maxDP[i-1]*nums[i], minDP[i-1]*nums[i]));
            }
            for (int i = 0; i < nums.length; i++) {
                maxProduct = Math.max(maxProduct, maxDP[i]);
            }
            return maxProduct;
        }



    }
//leetcode submit region end(Prohibit modification and deletion)

}
