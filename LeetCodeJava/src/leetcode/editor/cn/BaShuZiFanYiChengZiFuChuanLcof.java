/**
<p>给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 &ldquo;a&rdquo; ，1 翻译成 &ldquo;b&rdquo;，&hellip;&hellip;，11 翻译成 &ldquo;l&rdquo;，&hellip;&hellip;，25 翻译成 &ldquo;z&rdquo;。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 12258
<strong>输出:</strong> <code>5
</code><strong>解释:</strong> 12258有5种不同的翻译，分别是&quot;bccfi&quot;, &quot;bwfi&quot;, &quot;bczi&quot;, &quot;mcfi&quot;和&quot;mzi&quot;</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= num &lt; 2<sup>31</sup></code></li>
</ul>
<div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 468</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 46
 * 把数字翻译成字符串
 * @author wangweizhou
 * @date 2022-08-05 19:40:47
 */

public class BaShuZiFanYiChengZiFuChuanLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new BaShuZiFanYiChengZiFuChuanLcof().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


	// 状态定义： 设动态规划列表 dp ，dp[i] 代表以 xi为结尾的数字的翻译方案数量。
	// 转移方程： 若 xi和xi−1组成的两位数字可以被翻译，则 dp[i]=dp[i−1]*1+dp[i−2]*1 ；否则dp[i]=dp[i−1]*1 。

	// 根据状态定义，dp[i]与当前位的前两位有关系
    public int translateNum(int num) {
		// 数字转字符串
		String s=String.valueOf(num);
		int[] dp=new int[s.length()+1];// 动态规划数组右移一位
		dp[0]=1;// 字符串第一位之前没有数字，这里也处理为1种，因为后面要乘法
		dp[1]=1;// 字符串的第一位数字肯定是0-9之间的数，所以翻译方法只有一种
		for (int i = 2; i <=s.length(); i++) {
			//获取子字符串[i-2,i-1]。若这两个数字组成的数字在10-25之间，那么就可以翻译成一个以第i位结束两位数或者以第i位结束一位数
			String temp=s.substring(i-2,i);
			if(temp.compareTo("10")>=0&&temp.compareTo("25")<=0){
				dp[i]=dp[i-1]+dp[i-2];
			}else{
				dp[i]=dp[i-1];
			}
		}
		return dp[s.length()];
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
