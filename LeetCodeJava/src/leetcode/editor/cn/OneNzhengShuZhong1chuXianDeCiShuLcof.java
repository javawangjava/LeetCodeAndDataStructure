/**
<p>输入一个整数 <code>n</code> ，求1～n这n个整数的十进制表示中1出现的次数。</p>

<p>例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 12
<strong>输出：</strong>5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 13
<strong>输出：</strong>6</pre>

<p> </p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 <= n < 2^31</code></li>
</ul>

<p>注意：本题与主站 233 题相同：<a href="https://leetcode-cn.com/problems/number-of-digit-one/">https://leetcode-cn.com/problems/number-of-digit-one/</a></p>
<div><div>Related Topics</div><div><li>递归</li><li>数学</li><li>动态规划</li></div></div><br><div><li>👍 373</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 43
 * 1～n 整数中 1 出现的次数
 * @author wangweizhou
 * @date 2022-09-29 18:27:38
 */
public class OneNzhengShuZhong1chuXianDeCiShuLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new OneNzhengShuZhong1chuXianDeCiShuLcof().new Solution();


	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

	public int countDigitOne(int n) {
		// mulk 表示 10^k
		// 在下面的代码中，可以发现 k 并没有被直接使用到（都是使用 10^k），但为了让代码看起来更加直观，这里保留了 k
		long mulk = 1;
		int ans = 0;
		for (int k = 0; n >= mulk; ++k) {
			ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
			mulk *= 10;
		}
		return ans;
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}
