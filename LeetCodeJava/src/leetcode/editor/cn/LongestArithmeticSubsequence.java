/**
 * <p>给你一个整数数组&nbsp;<code>nums</code>，返回 <code>nums</code>&nbsp;中最长等差子序列的<strong>长度</strong>。</p>
 *
 * <p>回想一下，<code>nums</code> 的子序列是一个列表&nbsp;<code>nums[i<sub>1</sub>], nums[i<sub>2</sub>], ...,
 * nums[i<sub>k</sub>]</code> ，且&nbsp;<code>0 &lt;= i<sub>1</sub> &lt; i<sub>2</sub> &lt; ... &lt; i<sub>k</sub> &lt;
 * = nums.length - 1</code>。并且如果&nbsp;<code>seq[i+1] - seq[i]</code>(&nbsp;<code>0 &lt;= i &lt; seq.length -
 * 1</code>) 的值都相同，那么序列&nbsp;<code>seq</code>&nbsp;是等差的。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,6,9,12]
 * <strong>输出：</strong>4
 * <strong>解释： </strong>
 * 整个数组是公差为 3 的等差数列。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [9,4,7,2,10]
 * <strong>输出：</strong>3
 * <strong>解释：</strong>
 * 最长的等差子序列是 [4,7,10]。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [20,1,15,3,10,5,8]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>
 * 最长的等差子序列是 [20,15,10,5]。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= nums.length &lt;= 1000</code></li>
 * <li><code>0 &lt;= nums[i] &lt;= 500</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>二分查找</li><li>动态规划</li></div></div><br><div><li>👍
 * 200</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 1027
 * 最长等差数列
 *
 * @author wangweizhou
 * @date 2022-08-08 19:49:59
 */

public class LongestArithmeticSubsequence {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LongestArithmeticSubsequence().new Solution();
        int[] nums = {3,6,9,12};
        int ans=solution.longestArithSeqLength(nums);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：动态规划
        //等差数列至少包含 2 个数，也就是说 1 个数不能构成等差数列，任意 2 个元素都能构成长度为 2 的等差数列。

        //状态定义：dp[i][d]: 表示以数组下标 i 处的元素结尾、公差为 d 的等差数列的最大长度。
        // 状态转移方程：
        // int d = nums[k] - nums[j] + 500;//统一加偏移量，使下标非负，公差为d。
        // dp[k][d] = dp[j][d] + 1;//公差为d的等差数列再添加一个元素

        //由于 0 <= nums[i] <= 500, 综合递增和递减的两个极端，得到 d 的范围 -500<=d<=500.
        //我们需要 d 作为下标，而现在 d 又可能是负值，对此，我们统一增加一个偏移量 500，把负的抵消掉，0<=d’<=1000 。
        // d = nums[k] - nums[j]，我们枚举 nums[j] 和 nums[k] 是一个不错的选择，我们只要将 nums[k] - nums[j] 的值作为 d
        // 去填充dp[k][d]=dp[j][d]+1, 在此期间维护一个最大值作为结果


        // 初始化 ：我们需要两层 for 循环给所有 dp[i][j]初始化为 1。
        // 为什么初始化为 1，因为 dp[i][j] 以 nums[i] 结尾，nums[i] 就自己一个人，还没有小伙伴加入，所以长度就是1。
        // 初始化完了就可进行计算再返回结果，另外比较特殊的是，由于是统一初始化成相同的值，“地位平等”，使得也可以不用先初始化，在没有显式的初始化的基础上，算完之后，再将结果+1
        // ，也能得到相同的结果，并且后者效率高于前者（后者相较于前者少了 2 层 for 循环的时间）.


        public int longestArithSeqLength(int[] nums) {

            if (nums == null || nums.length <= 1) {//处理特殊情况
                return 0;
            }
            int len = nums.length;
            int[][] dp = new int[len][1001];
            int maxLen = 0;//保存结果
            for (int i = 1; i < len; i++) {//k遍历整个数组nums
                for (int j = 0; j < i; j++) {//j遍历数组nums在k之前的元素
                    int d = nums[i] - nums[j] + 500;//统一加偏移量，使下标非负
                    //dp[k][d] = dp[j][d] + 1; //根据 d 去填充dp[k][d],上面不知道为什么和下面的效果一样，后面思考明白
                    dp[i][d]=Math.max(dp[i][d],dp[j][d]+1);
                    maxLen = Math.max(maxLen, dp[i][d]);//维护最大值
                }
            }
            return maxLen + 1;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
