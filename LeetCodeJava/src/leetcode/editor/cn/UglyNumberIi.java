/**
 * <p>给你一个整数 <code>n</code> ，请你找出并返回第 <code>n</code> 个 <strong>丑数</strong> 。</p>
 *
 * <p><strong>丑数 </strong>就是只包含质因数 <code>2</code>、<code>3</code> 和/或 <code>5</code> 的正整数。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 10
 * <strong>输出：</strong>12
 * <strong>解释：</strong>[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1
 * <strong>输出：</strong>1
 * <strong>解释：</strong>1 通常被视为丑数。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= n <= 1690</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>数学</li><li>动态规划</li><li>堆（优先队列）</li></div></div><br><div><li>👍
 * 968</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 264
 * 丑数 II
 * @author wangweizhou
 * @date 2022-09-15 15:48:12
 */
public class UglyNumberIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new UglyNumberIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //状态定义： dp[n] 表示第 n 个丑数，a 表示 2 倍数字的索引用于 dp[a]*2,b 表示 3 倍数字的索引用于 dp[b]*3,c 表示 5 倍数字的索引用于 dp[c]*5
        //转移方程：
        //dp[n] = min(dp[a]*2, dp[b]*3, dp[c]*5)
        //每次计算之后，如果 2 倍的数字最小，则 a++，如果 3 倍的数字最小，则 b++，如果 5 倍的数字最小，则 c++
        //

        public int nthUglyNumber(int n) {
            if (n < 1 || n > 1690) {
                return -1;
            }

            int a = 0, b = 0, c = 0;
            int[] dp = new int[n];
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                int n2 = dp[a] * 2;
                int n3 = dp[b] * 3;
                int n5 = dp[c] * 5;
                dp[i] = Math.min(Math.min(n2, n3), n5);
                if (dp[i] == n2) {
                    a++;
                }
                if (dp[i] == n3) {
                    b++;
                }
                if (dp[i] == n5) {
                    c++;
                }
            }
            return dp[n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
