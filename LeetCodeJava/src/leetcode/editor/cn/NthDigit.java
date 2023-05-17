/**
<p>给你一个整数 <code>n</code> ，请你在无限的整数序列&nbsp;<code>[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...]</code> 中找出并返回第&nbsp;<code>n</code><em> </em>位上的数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 11
<strong>输出：</strong>0
<strong>解释：</strong>第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 <strong>0 </strong>，它是 10 的一部分。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li> 
</ul>

<div><div>Related Topics</div><div><li>数学</li><li>二分查找</li></div></div><br><div><li>👍 355</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 400
 * 第 N 位数字
 * @author wangweizhou
 * @date 2022-12-24 22:58:09
 */

public class NthDigit{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new NthDigit().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

	// 将 10,11,12,⋯ 称为 数字 ，记为 num ；
	// 数字 10 是一个两位数，称此数字的位数为 2 ，记为 digit ；
	// 每 digit 位数的起始数字（即：1,10,100,⋯），记为 start 。


	// 根据以上分析，可将求解分为三步：
	// 确定 n 所在数字的位数 ，记为 digit ；
	// 确定 n 所在的数字 ，记为 num ；
	// 确定 n 是 num 中的哪一数位，并返回结果。


    public int findNthDigit(int n) {
		if(n<=0){
			return -1;
		}
		int digit=1;// 数字的位数
		long start=1;// 数组范围的起始点
		long count=9;// 数位数量
		while(n>count){
			n-=count;// 每次减去一位数，两位数，...数位数量count。
			digit+=1;
			start*=10;
			count=digit*start*9;
		}

		// 由于 n 已经减去了一位数、两位数、...、(digit−1) 位数的数位数量 count ，因而此时的 n 是从起始数字 start 开始计数的。
		// 从[start,end]中寻找，
		long num=start+(n-1)/digit;
		return Long.toString(num).charAt((n-1)%digit)-'0';
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
