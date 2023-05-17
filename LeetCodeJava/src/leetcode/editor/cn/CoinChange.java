/**
 * <p>给你一个整数数组 <code>coins</code> ，表示不同面额的硬币；以及一个整数 <code>amount</code> ，表示总金额。</p>
 *
 * <p>计算并返回可以凑成总金额所需的 <strong>最少的硬币个数</strong> 。如果没有任何一种硬币组合能组成总金额，返回&nbsp;<code>-1</code> 。</p>
 *
 * <p>你可以认为每种硬币的数量是无限的。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = <code>[1, 2, 5]</code>, amount = <code>11</code>
 * <strong>输出：</strong><code>3</code>
 * <strong>解释：</strong>11 = 5 + 5 + 1</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = <code>[2]</code>, amount = <code>3</code>
 * <strong>输出：</strong>-1</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = [1], amount = 0
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= coins.length &lt;= 12</code></li>
 * <li><code>1 &lt;= coins[i] &lt;= 2<sup>31</sup> - 1</code></li>
 * <li><code>0 &lt;= amount &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>动态规划</li></div></div><br><div><li>👍
 * 2075</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 322
 * 零钱兑换
 *
 * @author wangweizhou
 * @date 2022-08-05 20:26:06
 */

public class CoinChange {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new CoinChange().new Solution();
        int[] coins = {1,2,5};
        int ans = solution.coinChange(coins, 11);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        ////  如果将每种面额的硬币看成一种物品，而将目标总额看成背包的容量，那么这个问题等价于求将背包放满时物品的最少件数。
        ////  值得注意的是，这里每种面额的硬币可以使用任意多次，因此这个问题不再是0-1背包问题，而是一个无界背包问题（也叫完全背包问题）。
        ////  分析和解决完全背包问题的思路与0-1背包问题的思路类似。

        ////  状态定义：用函数f（i，j）表示用前i种硬币（coins[0，…，i-1]）凑出总额为j需要的硬币的最少数目。
        ////  当使用0枚标号为i-1的硬币时，f（i，j）等于f（i-1，j）（用前i-1种硬币凑出总额j需要的最少硬币数目，再加上0枚标号为i-1的硬币）；
        ////  当使用1枚标号为i-1的硬币时，f（i，j）等于f（i-1，j-coins[i-1]）加1（用前i-1种硬币凑出总额j-coins[i-1]需要的最少硬币数目，再加上1枚标号为i-1的硬币）；
        ////  以此类推，当使用k枚标号为i-1的硬币时，f（i，j）等于f（i-1，j-k×coins[i-1]）加k（用前i-1种硬币凑出总额j-k×coins[i-1]需要的最少硬币数目，再加上k枚标号为i-1的硬币）。

        ////  由于目标是求出硬币数目的最小值，因此f（i，j）是上述所有情况的最小值。
        ////  该状态转移方程可以用如下等式表示：f（i，j）=min(f(i-1,j-k×coins[i-1]）;j>=k×coins[i-1];
        ////  当j等于0（即总额等于0）时，f（i，0）都等于0，即从前i种硬币中选出0个硬币，使总额等于0。
        ////  当i等于0且j大于0时，即用0种硬币凑出大于0的总额，这显然是不可能的，但可以用一个特殊值表示。


        // 状态定义：定义二维数组 dp[i][j] 表示：从前 i 种硬币中组成金额 j 所需最少的硬币数量。
        // 初始化：记整数数组 coins 的长度为 n。为便于状态更新，减少对边界的判断，初始二维 dp 数组维度为 (n+1)×(∗)，其中第一维为 n+1。
        // 也意味着：第 i 种硬币为 coins[i−1]，第 1 种硬币为 coins[0]，第 0 种硬币为空。

        // 初始化时，不合法的或未定义的状态则可以设置为正无穷或一个不可能取到的较大值：
        // dp[0][0]=0：表示从前 0 种硬币中选出若干个组成金额 0 所对应的最小硬币数目为 0，即「空集合」不选任何硬币即可得到金额 0。
        // 对于其他 dp[0][j],j≥1，则可将其设置为正无穷或一个不可能取到的较大值，例如 dp[0][j]=+∞：「空集合」中无法选出任何硬币组成金额 j≥1。
        // 此外，其他的 dp[i][0]=0，表示从前 i 个硬币中凑出金额 0 所需要的硬币数目为 0。这在程序迭代实现中已有体现，在此无需提前重复定义。


        // 状态定义：用函数f（i，j）表示用前i种硬币（coins[0，…，i-1]）凑出总额为j需要的硬币的最少数目。
        //public int coinChange(int[] coins, int amount) {
        //    if (coins == null || coins.length == 0) {
        //        return -1;
        //    }
        //    if (amount <= 0) {// ⼩于1的都返回0
        //        return 0;
        //    }
        //    int len = coins.length;
        //    int[][] dp = new int[len + 1][amount + 1];
        //    for (int i = 0; i <= len; i++) {// 从[0,i]中凑出总额为0时，这时候不选任何硬币就可以。
        //        //  当j等于0（即总额等于0）时，f（i，0）都等于0，即从前i种硬币中选出0个硬币，使总额等于0。
        //        dp[i][0] = 0;
        //    }
        //    for (int j = 1; j <= amount; j++) {
        //        //  当i等于0且j大于0时，即用0种硬币凑出大于0的总额，这显然是不可能的，但可以用一个特殊值表示。
        //        dp[0][j] = Integer.MAX_VALUE;//
        //    }
        //    // dp[i][j]表示前i个硬币组成amount=j的最小硬币数
        //    // dp[i][j]=Math.min(dp[i-1][j-n*coins[i]]+n,dp[i-1][j])
        //    for (int i = 1; i <= len; i++) {
        //        for (int j = 1; j <= amount; j++) {
        //            dp[i][j] = dp[i - 1][j];// 不选coins[i-1]
        //            for (int k = 1; k * coins[i - 1] <= j; k++) {// 选多个coins[i-1]
        //                // 因为k*coins[i-1] <=j，所以j-k*coins[i-1]肯定属于[0，amount]。
        //                if (dp[i - 1][j - k * coins[i - 1]] != Integer.MAX_VALUE) {// 排除i=1的情况，
        //                    // 当i=1时，dp[i-1][j-k*coins[i-1]]==dp[0][j-k*coins[i-1]]==Integer.MAX_VALUE
        //                    dp[i][j] = Math.min(dp[i - 1][j - k * coins[i - 1]] + k, dp[i][j]);
        //                }
        //            }
        //        }
        //    }
        //    // 因为递推公式里是 min，所以 dp 里除了 dp[0] 都需要设置成一个比较大且无效的值。
        //    // 最后判断 dp[amount] 是不是有效的，就知道需要返回 -1 还是 dp[amount] 了。
        //    return dp[len][amount] != Integer.MAX_VALUE ? dp[len][amount] : -1;
        //}



        // 解法2：
        public int coinChange(int[] coins, int amount) {
            if (coins == null || coins.length == 0) {
                return -1;
            }
            if (amount <= 0) {// ⼩于1的都返回0
                return 0;
            }
            int len = coins.length;
            // 数组下标从0开始，dp的第一维右移一位是为了好处理。第二维右移一位是为了第二维下标的字面数值就是背包容量，
            int[][] dp = new int[len + 1][amount + 1];// 注意这里对dp数组右移了一位
            for (int i = 0; i <= len; i++) {// 从[0,i]中凑出总额为0时，这时候不选任何硬币就可以。
                //  当j等于0（即总额等于0）时，f（i，0）都等于0，即从前i种硬币中选出0个硬币，使总额等于0。
                dp[i][0] = 0;
            }
            for (int j = 1; j <= amount; j++) {
                //  当i等于0且j大于0时，即用前0种硬币凑出大于0的总额，这显然是不可能的，但可以用一个特殊值表示。
                dp[0][j] = amount + 1;// 因为后面是求最小值，所以这里要取一个比可能的最大值还要大的值。
            }
            // dp[i][j]表示前i个硬币组成amount=j的最小硬币数
            // dp[i][j]=Math.min(dp[i-1][j-n*coins[i]]+n,dp[i-1][j])
            for (int i = 1; i <= len; i++) {
                for (int j =1; j <= amount; j++) {
                   if(j>=coins[i-1]){
                       // 因为是无界限背包，每种面额的硬币可以使用任意多次，所以在第i个硬币可以再次取（dp[i][j-coins[i-1]]+1）。
                       dp[i][j]=Math.min(dp[i-1][j],dp[i][j-coins[i-1]]+1);
                   }else {// 因为 j<coins[i-1],也就是不能选第（i-1）枚硬币。
                       dp[i][j]=dp[i-1][j];
                   }
                }
            }
            // 因为递推公式里是 min，所以 dp 里除了 dp[0] 都需要设置成一个比较大且无效的值。
            // 最后判断 dp[amount] 是不是有效的，就知道需要返回 -1 还是 dp[amount] 了。
            return dp[len][amount] != amount + 1 ? dp[len][amount] : -1;
        }





        ////	⽅法⼀：动态规划 没看懂
        //// 状态定义：用函数f（i）表示凑出总额为i的硬币需要的最少数目。
        //// 需要注意的是，这个函数只有一个参数，表示硬币的总额。如果目标总额为t，那么f（t）就是整个问题的解。
        //// 为了凑出总额为i的硬币，有如下选择：在总额为i-coins[0]的硬币中添加1枚标号为0的硬币，
        // 此时f（i）等于f（i-coins[0]）+1（在凑出总额为i-coins[0]的最少硬币数的基础上加1枚标号为0的硬币）；
        //// 在总额为i-coins[1]的硬币中添加1枚标号为1的硬币，此时f（i）等于f（i-coins[1]）+1。
        //// 以此类推，在总额为i-coins[n-1]的硬币中添加1枚标号为n-1的硬币，此时f（i）等于f（i-coins[n-1]）+1。
        //// 因为目标是计算凑出总额为i的硬币，所以f（i）是上述所有情况的最小值。该状态转移方程可以表示为
        //// f (i)=min(f(i-coins[j])+1)(coins[j]≤i)
        //// 显然，f（0）等于0，即凑出总额0至少需要0枚硬币。

        //public int coinChange(int[] coins, int amount) {
        //    if (coins == null || coins.length == 0) {
        //        return -1;
        //    }
        //    if (amount <= 0) {//⼩于1的都返回0
        //        return 0;
        //    }
        //    int[] dp = new int[amount + 1];//dp[i]表示凑⻬i元最少需要多少货币数
        //    // 给dp赋初值，最多的硬币数就是全部使用面值1的硬币进行换，amount + 1 是不可能达到的换取数量，于是使用其进行填充
        //    Arrays.fill(dp, amount + 1);//dp数组全部初始化为amount + 1，amount + 1 是不可能达到的换取数量，数值不会和后面更新的数字产生混乱
        //    dp[0] = 0;
        //    for (int i = 1; i <= amount ; i++) { //遍历1-amount元
        //        for (int j = 0; j < coins.length; j++) {//每种⾯值的货币个数都要枚举
        //            if (coins[j] <= i) {
        //                // 为了凑出总额为i的硬币，有如下选择：在总额为i-coins[j]的硬币中添加1枚标号为j的硬币，此时f（i）等于f（i-coins[j]）+1。
        //                // （在凑出总额为i-coins[j]的最少硬币数的基础上加1枚标号为j的硬币）；
        //                // 另一种就是不添加1枚标号为j的硬币，要兑换的硬币数是dp[i]
        //                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        //            }
        //        }
        //    }
        //    return dp[amount] > amount ? -1 : dp[amount];
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
