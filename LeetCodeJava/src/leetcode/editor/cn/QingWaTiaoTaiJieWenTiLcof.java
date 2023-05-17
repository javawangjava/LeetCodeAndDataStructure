/**
 * <p>一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 <code>n</code>&nbsp;级的台阶总共有多少种跳法。</p>
 *
 * <p>答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>n = 2
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>n = 7
 * <strong>输出：</strong>21
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>n = 0
 * <strong>输出：</strong>1</pre>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= n &lt;= 100</code></li>
 * </ul>
 *
 * <p>注意：本题与主站 70 题相同：
 * <a href="https://leetcode-cn.com/problems/climbing-stairs/">https://leetcode-cn.com/problems/climbing-stairs/</a></p>
 *
 * <p>&nbsp;</p>
 * <div><div>Related Topics</div><div><li>记忆化搜索</li><li>数学</li><li>动态规划</li></div></div><br><div><li>👍
 * 325</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 10- II
 * 青蛙跳台阶问题
 * @author wangweizhou
 * @date 2022-09-15 11:31:17
 */

public class QingWaTiaoTaiJieWenTiLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new QingWaTiaoTaiJieWenTiLcof().new Solution();
        int ans = solution.numWays(2);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法1： 动态规划
        // 因为状态转移方程只与前两个元素有关，所以可以利用动态数组优化空间
        // 状态定义：dp[i]表示青蛙跳到第i阶的方法
        public int numWays(int n) {
            if(n<0){
            	return -1;
            }
            if(n==0||n==1){
                return 1;
            }
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i < n + 1; i++) {
                dp[i] = (dp[i - 2] + dp[i - 1]) % 1000000007;
            }
            return dp[n];
        }



    }
//leetcode submit region end(Prohibit modification and deletion)

}
