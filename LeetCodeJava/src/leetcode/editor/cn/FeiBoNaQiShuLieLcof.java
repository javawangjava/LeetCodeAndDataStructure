/**
<p>写一个函数，输入 <code>n</code> ，求斐波那契（Fibonacci）数列的第 <code>n</code> 项（即 <code>F(N)</code>）。斐波那契数列的定义如下：</p>

<pre>
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.</pre>

<p>斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。</p>

<p>答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>5
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= n <= 100</code></li>
</ul>
<div><div>Related Topics</div><div><li>记忆化搜索</li><li>数学</li><li>动态规划</li></div></div><br><div><li>👍 412</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 10- I
 * 斐波那契数列
 * @author wangweizhou
 * @date 2022-09-15 01:35:02
 */
public class FeiBoNaQiShuLieLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new FeiBoNaQiShuLieLcof().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int fib(int n) {
		if(n<=1){
			return n;
		}
		int[] dp=new int[n+1];
		dp[0]=0;
		dp[1]=1;
		for (int i = 2; i <n+1 ; i++) {
			dp[i]=(dp[i-2]+dp[i-1])%1000000007;
		}
		return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
