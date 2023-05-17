/**
 * <p>给你一个整数数组 <code>prices</code> ，其中&nbsp;<code>prices[i]</code> 表示某支股票第 <code>i</code> 天的价格。</p>
 *
 * <p>在每一天，你可以决定是否购买和/或出售股票。你在任何时候&nbsp;<strong>最多</strong>&nbsp;只能持有 <strong>一股</strong> 股票。你也可以先购买，然后在
 * <strong>同一天</strong> 出售。</p>
 *
 * <p>返回 <em>你能获得的 <strong>最大</strong> 利润</em>&nbsp;。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>prices = [7,1,5,3,6,4]
 * <strong>输出：</strong>7
 * <strong>解释：</strong>在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 * &nbsp;    随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
 * 总利润为 4 + 3 = 7 。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>prices = [1,2,3,4,5]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 * &nbsp;    总利润为 4 。</pre>
 *
 * <p><strong>示例&nbsp;3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>prices = [7,6,4,3,1]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= prices.length &lt;= 3 * 10<sup>4</sup></code></li>
 * <li><code>0 &lt;= prices[i] &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1742</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 买卖股票的最佳时机 II
 *
 * @author wangweizhou
 * @date 2022-06-24 12:08:58
 */
public class BestTimeToBuyAndSellStockIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new BestTimeToBuyAndSellStockIi().new Solution();
        int[] prices = {7, 1, 5, 3, 6, 4, 3};
        int a = solution.maxProfit(prices);
        System.out.println(a);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 解法1：贪心算法,贪心算法只能用于计算最大利润，计算的过程并不是实际的交易过程。
        //public int maxProfit(int[] prices) {
        //    if (prices == null || prices.length == 0) {
        //        return 0;
        //    }
        //    int length = prices.length;
        //    int maxProfit = 0;
        //    for (int i = 1; i < length; i++) {
        //        maxProfit+=Math.max(0,prices[i]-prices[i-1]);//有利润就往上加
        //
        //    }
        //    return maxProfit;
        //}




        // 解法2：因为交易次数不受限，如果可以把所有的上坡全部收集到，一定是利益最大化的
        // 只要当天比前一天的价格高，那么就可以前一天购买，后一天卖出。
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int length = prices.length;
            int ans = 0;
            for (int i = 1; i < length; i++) {
                if (prices[i] > prices[i - 1]) {  // 卖出有利可图
                    ans += (prices[i] - prices[i - 1]);
                }
            }
            return ans;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
