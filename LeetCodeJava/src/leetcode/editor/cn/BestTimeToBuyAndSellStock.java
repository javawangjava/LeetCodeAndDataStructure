/**
 * <p>给定一个数组 <code>prices</code> ，它的第 <code>i</code> 个元素 <code>prices[i]</code> 表示一支给定股票第 <code>i</code> 天的价格。</p>
 *
 * <p>你只能选择 <strong>某一天</strong> 买入这只股票，并选择在 <strong>未来的某一个不同的日子</strong> 卖出该股票。设计一个算法来计算你所能获取的最大利润。</p>
 *
 * <p>返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 <code>0</code> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>[7,1,5,3,6,4]
 * <strong>输出：</strong>5
 * <strong>解释：</strong>在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>prices = [7,6,4,3,1]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>在这种情况下, 没有交易完成, 所以最大利润为 0。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= prices.length <= 10<sup>5</sup></code></li>
 * <li><code>0 <= prices[i] <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 2407</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 121
 * 买卖股票的最佳时机
 *
 * @author wangweizhou
 * @date 2022-06-24 12:09:03
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new BestTimeToBuyAndSellStock().new Solution();
        int[] prices = {7, 1, 5, 3, 6, 4, 3};
        int a = solution.maxProfit(prices);
        System.out.println(a);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //解法1：记录当前位置前面的价格中最低的价格
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int minPrice = Integer.MAX_VALUE;
            int maxProfit = 0;
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minPrice) {// 如果当前位置的价格低于当前位置前面的最低价格，则更新截至当前位置的最低价格
                    minPrice = prices[i];// 更新最低价格
                } else if (prices[i] - minPrice > maxProfit) {// 如果当前位置的价格与当前位置前面的最低价格的差值大于最大利润，则更新最大利润
                    maxProfit = prices[i] - minPrice;// 更新最大利润
                }
                //minPrice =Math.min(minPrice ,prices[i]);
                //maxProfit=Math.max(maxProfit,prices[i]-minPrice );
            }
            return maxProfit;
        }


        //解法2：暴力法
	/*
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            int length = prices.length;
            for (int left = 0; left < length - 1; left++) {
                for (int right = left + 1; right < length; right++) {
                    if (prices[right] - prices[left] > maxProfit) {
                        maxProfit = prices[right] - prices[left];// 更新最大利润
                    }
                }
            }
            return maxProfit;
        }*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
