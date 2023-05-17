/**
 * <p>给你一个整数数组 <code>nums</code> ，你可以对它进行一些操作。</p>
 *
 * <p>每次操作中，选择任意一个 <code>nums[i]</code> ，删除它并获得 <code>nums[i]</code> 的点数。之后，你必须删除 <strong>所有 </strong>等于
 * <code>nums[i] - 1</code> 和 <code>nums[i] + 1</code> 的元素。</p>
 *
 * <p>开始你拥有 <code>0</code> 个点数。返回你能通过这些操作获得的最大点数。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,4,2]
 * <strong>输出：</strong>6
 * <strong>解释：</strong>
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,2,3,3,3,4]
 * <strong>输出：</strong>9
 * <strong>解释：</strong>
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 2 * 10<sup>4</sup></code></li>
 * <li><code>1 <= nums[i] <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>动态规划</li></div></div><br><div><li>👍 656</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 740
 * 删除并获得点数
 *
 * @author wangweizhou
 * @date 2022-08-08 00:05:05
 */


public class DeleteAndEarn {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new DeleteAndEarn().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法1：动态规划+新构建数组


        // 新构建数组counter用于表示原数组中元素的个数。
        // 注意新构建数组下标范围[0,max(nums[i])+1]，即从0开始到原数组的最大值+1截止的所有数。而不是只有原数组中有的数
        // 新构建数组的下标对应的数组值counter[j]是原数组nums中nums[i]的值对应的数组元素的个数。
        // 这样就可以转换为新构建数组的打家劫舍了。不能抢相邻的两个房间。
        // 和打家劫舍一样，nums[i]-1和nums[i]+1是nums[i]的邻居不能偷，nums[i]的总和sum[nums[i]]就是nums[i]家的总财产，
        // 有些数字没出现就当这些数字所充当的“家”总财产为0 //，nums数组里最大的数字就是最后一家，因为数组计数一般从0开始，所以官解里的sum的大小为最大的数字+1.
        //状态定义：设动态规划列表dp ，dp[i] 代表到counter[i]为止在满足条件下的能获得的最大点数。
        //状态转移方程：  dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * counter[i])

        //   // 解法1：动态规划+新构建数组
        public int deleteAndEarn(int[] nums) {
            if (nums == null || nums.length == 0) {//判空
                return 0;
            }
            if (nums.length == 1) {// 数组只有一个整数
                return nums[0];
            }
            int len = nums.length;
            int max = nums[0];
            for (int i = 0; i < len; i++) {//找出数组nums中的最大值
                max = Math.max(max, nums[i]);
            }

            //数组counter下标i对应原数组nums中的数组值nums[j]，下标对应的数组值counter[i]表示在原数组中nums[j]的个数
            int[] counter = new int[max + 1];
            for (int i = 0; i < len; i++) {
                counter[nums[i]]++;
            }

            int[] dp = new int[max + 1];
            dp[1] = counter[1] * 1;
            for (int i = 2; i <= max; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * counter[i]);
            }
            return dp[max];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
