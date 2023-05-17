/**
 * <p>如果序列 <code>X_1, X_2, ..., X_n</code> 满足下列条件，就说它是 <em>斐波那契式 </em>的：</p>
 *
 * <ul>
 * <li><code>n >= 3</code></li>
 * <li>对于所有 <code>i + 2 <= n</code>，都有 <code>X_i + X_{i+1} = X_{i+2}</code></li>
 * </ul>
 *
 * <p>给定一个<strong>严格递增</strong>的正整数数组形成序列 arr ，找到 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier
 * New, monospace"><span style="font-size:12.600000381469727px"><span style="caret-color:#c7254e"><span
 * style="background-color:#f9f2f4">arr</span></span></span></font></font> 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。</p>
 *
 * <p><em>（回想一下，子序列是从原序列 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span
 * style="font-size:12.600000381469727px"><span style="caret-color:#c7254e"><span
 * style="background-color:#f9f2f4">arr</span></span></span></font></font> 中派生出来的，它从 <font color="#c7254e"><font
 * face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.600000381469727px"><span
 * style="caret-color:#c7254e"><span style="background-color:#f9f2f4">arr</span></span></span></font></font>
 * 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， <code>[3, 5, 8]</code> 是 <code>[3, 4, 5, 6, 7, 8]</code> 的一个子序列）</em></p>
 *
 * <p> </p>
 *
 * <ul>
 * </ul>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>arr =<strong> </strong>[1,2,3,4,5,6,7,8]
 * <strong>输出: </strong>5
 * <strong>解释: </strong>最长的斐波那契式子序列为 [1,2,3,5,8] 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>arr =<strong> </strong>[1,3,7,11,12,14,18]
 * <strong>输出: </strong>3
 * <strong>解释</strong>: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>3 <= arr.length <= 1000</code></li>
 * <li>
 * <p><code>1 <= arr[i] < arr[i + 1] <= 10^9</code></p>
 * </li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>动态规划</li></div></div><br><div><li>👍 332</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;


/**
 * 873
 * 最长的斐波那契子序列的长度
 *
 * @author wangweizhou
 * @date 2022-08-08 01:07:40
 */

public class LengthOfLongestFibonacciSubsequence {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LengthOfLongestFibonacciSubsequence().new Solution();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        //System.out.println(solution.lenLongestFibSubseq(nums));
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 所谓斐波那契数列，是指数列中从第三个数字开始每个数字都等于前面两个数字之和。
        // 可以从左至右每次从输入的数组中取出一个数字，使之和前面的若干数字组成斐波那契数列。一个数字可能和前面不同的数字组成不同的斐波那契数列。

        // 将数组记为A，A[i]表示数组中下标为i的数字。对于每个j（0≤j＜i），A[j]都有可能是在某个斐波那契数列中A[i]前面的一个数字。
        // 如果存在一个k（0≤k＜j）满足A[k]+A[j]=A[i]，那么这3个数字就组成了一个斐波那契数列。
        // 这个以A[i]为结尾、前一个数字是A[j]的斐波那契数列是在以A[j]为结尾、前一个数字是A[k]的序列的基础上增加一个数字A[i]，
        // 因此前者的长度是在后者的长度的基础上加1。
        // 由于以A[i]为结尾的斐波那契数列的长度依赖于它前一个数字A[j]，不同的A[j]能和A[i]形成不同的斐波那契数列，它们的长度也可能不同。

        // 定义状态：因此，状态转移方程有两个参数i和j，f（i，j）表示以A[i]为最后一个数字、A[j]为倒数第2个数字的斐波那契数列的长度。
        // 状态转移方程：如果数组中存在一个数字k，使A[i]=A[j]+A[k]（0≤k＜j＜i），那么f（i，j）=f（j，k）+1，即在以A[j]为最后一个数字、A[k]为倒数第2
        // 个数字的斐波那契数列的基础上增加一个数字A[i]，形成更长的一个数列。
        // f（i，j）的值可能是2，此时虽然A[i]和A[j]这两个数字现在还不能形成一个有效的斐波那契数列，但可能会在之后增加一个新的数字使之形成长度为3甚至更长的斐波那契数列。

        // 由于状态转移方程有两个参数i和j，因此需要一个二维数组来缓存f（i，j）的计算结果。
        // i对应二维数组的行号，j对应二维数组的列号。由于i大于j，因此实际上只用到了二维数组的左下角部分。
        // 如果数组的长度是n，那么i的取值范围为1～n-1，而j的取值范围为0～n-2。


        // 解法1：动态规划
        public int lenLongestFibSubseq(int[] nums) {
            // 判空，至少3个数才可以组成斐波拉契数列
            if (nums == null || nums.length < 3) {
                return 0;
            }
            int len = nums.length;
            // 记忆化，将数组元素和对应的下标保存在map中，哈希表的键key存储数组元素值，哈希表的值value存储数组下标。
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < len; i++) {
                map.put(nums[i], i);
            }

            int[][] dp = new int[len][len];// dp[i][j]表示以A[i]为最后一个数字、A[j]为倒数第2个数字的斐波那契数列的长度。
            int maxLen = 0;// 保存最大长度的结果
            // 定义状态：状态转移方程有两个参数i和j，f（i，j）表示以A[i]为最后一个数字、A[j]为倒数第2个数字的斐波那契数列的长度。
            // 状态转移方程：如果数组中存在一个数字k，使A[i]=A[j]+A[k]（0≤k＜j＜i），那么f（i，j）=f（j，k）+1，
            // 即在以A[j]为最后一个数字、A[k]为倒数第2个数字的斐波那契数列的基础上增加一个数字A[i]，形成更长的一个数列。
            for (int i = 1; i < len; i++) {
                for (int j = 0; j < i; j++) {
                    // A[i]=A[j]+A[k]（0≤k＜j＜i）
                    // 查找是否在A[j]之前存在A[k]使得A[i]-A[j]=A[k]
                    int k = map.getOrDefault(nums[i] - nums[j], -1);
                    if (k >= 0 && k < j) {// 当在A[j]之前存在A[k]使得A[i]-A[j]=A[k]
                        // 如果数组中存在一个数字k，使A[i]=A[j]+A[k]（0≤k＜j＜i），那么f（i，j）=f（j，k）+1，
                        // 即在以A[j]为最后一个数字、A[k]为倒数第2个数字的斐波那契数列的基础上增加一个数字A[i]，形成更长的一个数列。
                        dp[i][j] = dp[j][k] + 1;
                    } else {
                        // 如果不存在，那么此时虽然A[i]和A[j]这两个数字现在还不能形成一个有效的斐波那契数列，
                        // 但可能会在之后增加一个新的数字使之形成长度为3甚至更长的斐波那契数列。
                        dp[i][j] = 2;// 这里就相当于初始化
                    }
                    //dp[i][j]=(k>=0&&k<j)?dp[j][k]+1:2;// 和上面的if-else语句等价
                    maxLen = Math.max(maxLen, dp[i][j]);// 更新截至目前的的斐波拉契数组的最大长度
                }
            }
            return maxLen > 2 ? maxLen : 0;
        }





        //// 解法1：动态规划 写法1
        //// 状态定义：dp[i][j]为使用 arr[i]为斐波那契数列的最后一位，使用 arr[j]为倒数第二位（即 arr[i]的前一位）时的最长数列长度。
        //// 状态转移方程：
        //
        //public int lenLongestFibSubseq(int[] nums) {
        //    // 判空，至少3个数才可以组成斐波拉契数列
        //    if (nums == null || nums.length < 3) {
        //        return 0;
        //    }
        //    int len = nums.length;
        //    // 记忆化，将数组元素和对应的下标保存在map中，哈希表的键key存储数组元素值，哈希表的值value存储数组下标。
        //    Map<Integer, Integer> counter = new HashMap<>();
        //    for (int i = 0; i < len; i++) {
        //        counter.put(nums[i], i);
        //    }
        //
        //    // 初始状态dp[1][0] = 0，因为位置0之前没有其他元素了。另外其他的dp[i][j]也都为0
        //    int[][] dp = new int[len][len];
        //    int maxLen = 0;//保存最大长度的结果
        //
        //    // 至少3个数才可以组成斐波拉契数列,所以这里i=2
        //    // 注意这里是从位置i向前找位置j和位置k，
        //    for (int i = 2; i < len; i++) {//「从小到大」枚举 i
        //        // 从0到j有j+1个元素，再加上arr[i]就是j+2，如果[0...i]的长度不大于当前已有的斐波那契数列最大的长度maxLen，就没必要继续往下找了
        //        //for (int j = i - 1; j >= 0 && j + 2 > maxLen; j--) {// j是在i前面的。从i的前面「从大到小」枚举 j时
        //        for (int j = i - 1; j >= 0 ; j--) {// j是在i前面的。从i的前面「从大到小」枚举 j时，这里没有j + 2 > maxLen。
        //            // 由于数组严格递增，如果最后两个数的差值大于等于倒数第二个数，则再往前找肯定不会存在了
        //            // nums[i] - nums[j] >= nums[j] > nums[k],前面不可能有nums[i] - nums[j] =nums[k]，j向前移动一位，剪枝。
        //            if (nums[i] - nums[j] >= nums[j]) {
        //                break;
        //            }
        //            // 能运行到这里就是nums[i] - nums[j]< nums[j],说明在nums[j]之前可能存在nums[k]使得nums[i] - nums[j] =nums[k]
        //            int k = counter.getOrDefault(nums[i] - nums[j], -1);
        //            if (k == -1) {// 不存在nums[k]，nums[i] - nums[j] =nums[k]
        //                continue;
        //            }else{// 存在nums[k]，nums[i] - nums[j] =nums[k]
        //                // dp[i][j]为使用 arr[i]为斐波那契数列的最后一位，使用 arr[j]为倒数第二位（即 arr[i]的前一位）时的最长数列长度。
        //                dp[i][j] = Math.max(3, dp[j][k] + 1);
        //            }
        //            maxLen = Math.max(maxLen, dp[i][j]);
        //        }
        //    }
        //    return maxLen;
        //}
        //


        //// 动态规划解法2：注意和前面的动态规划的状态定义不一样
        //// dp[i][j]表示以A[j]为最后一个数字，以A[i]为倒数第二个数字的斐波拉契数列的长度
        //public int lenLongestFibSubseq(int[] nums) {
        //    // 判空，至少3个数才可以组成斐波拉契数列
        //    if (nums == null || nums.length < 3) {
        //        return 0;
        //    }
        //    int len = nums.length;
        //
        //    // 记忆化，将数组元素和对应的下标保存在map中，哈希表的键key存储数组元素值，哈希表的值value存储数组下标。
        //    Map<Integer, Integer> map = new HashMap<>();
        //    map.put(nums[0],0);
        //
        //    // 初始状态dp[i][0] = 0，因为位置0【nums[0]】nums[0]之前没有其他元素了。另外其他的dp[i][j]也都为0
        //    int[][] dp = new int[len][len];// dp[i][j]表示以A[j]为最后一个数字、A[i]为倒数第2个数字的斐波那契数列的长度。
        //    int maxLen = 0;//保存最大长度的结果
        //
        //    for (int i = 1; i < len; i++) {
        //        for (int j = i+1; j <len ; j++) {
        //            int temp =nums[j]-nums[i];
        //            if(map.containsKey(temp)){
        //                // 如果数组中存在一个数字A[k]，使A[j]-A[i]=A[k]（0≤k＜i＜j），那么dp（i，j）=dp（k，i）+1，
        //                // 即在以A[i]为最后一个数字、A[k]为倒数第2个数字的斐波那契数列的基础上增加一个数字A[j]，形成更长的一个数列。
        //                dp[i][j]=dp[map.get(temp)][i]+1;
        //                //dp[i][j]=Math.max(dp[map.get(target)][i]+1,dp[i][j]);
        //                maxLen=Math.max(maxLen,dp[i][j]);
        //            }
        //        }
        //        map.put(nums[i],i);
        //    }
        //    return maxLen==0?0:maxLen+2;
        //}


    }

//leetcode submit region end(Prohibit modification and deletion)

}
