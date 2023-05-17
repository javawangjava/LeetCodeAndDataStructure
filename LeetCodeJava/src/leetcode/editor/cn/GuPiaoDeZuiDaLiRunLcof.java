/**
<p>假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [7,1,5,3,6,4]
<strong>输出:</strong> 5
<strong>解释: </strong>在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [7,6,4,3,1]
<strong>输出:</strong> 0
<strong>解释: </strong>在这种情况下, 没有交易完成, 所以最大利润为 0。</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 数组长度 &lt;= 10^5</code></p>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与主站 121 题相同：<a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/">https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/</a></p>
<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 287</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 63
 * 股票的最大利润
 * @author wangweizhou
 * @date 2022-09-25 01:18:39
 */
public class GuPiaoDeZuiDaLiRunLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new GuPiaoDeZuiDaLiRunLcof().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

/*
	// 股票只能买卖一次
	// 解法2：动态规划
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int len=prices.length;
		int[] dp=new int[len];// dp[i]表示截止下标i的最低价格
		int maxProfit=0;
		dp[0]=prices[0];
		for (int i = 1; i < len; i++) {
			dp[i]=Math.min(dp[i-1],prices[i]);// 更新最低价格
			maxProfit=Math.max(maxProfit,prices[i]-dp[i]);// 更新最大收益
		}
		return maxProfit;
	}

	*/



	// 解法1：一次遍历  动态规划+动态数组
    public int maxProfit(int[] prices) {
		if(prices==null||prices.length<2){
			return 0;
		}
		int min=Integer.MAX_VALUE;// 保存截止当前位置的最低价格
		int maxProfit=0;// 截止当前的最大收益
		for (int i = 0; i < prices.length; i++) {
			//if(prices[i]<min){// 当价格低于当前最低价格时，更新最低价格
			//	min=prices[i];
			//}else if(prices[i]-min>maxProfit){// 当利润大于当前最大利润时，更新最大利润
			//	maxProfit=prices[i]-min;
			//}
			min=Math.min(min,prices[i]);
			maxProfit=Math.max(maxProfit,prices[i]-min);
		}
		return maxProfit;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
