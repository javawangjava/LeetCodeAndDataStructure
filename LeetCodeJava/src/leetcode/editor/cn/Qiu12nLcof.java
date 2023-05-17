/**
<p>求 <code>1+2+...+n</code> ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> n = 3
<strong>输出:&nbsp;</strong>6
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> n = 9
<strong>输出:&nbsp;</strong>45
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= n&nbsp;&lt;= 10000</code></li>
</ul>
<div><div>Related Topics</div><div><li>位运算</li><li>递归</li><li>脑筋急转弯</li></div></div><br><div><li>👍 540</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 64
 * 求1+2+…+n
 * @author wangweizhou
 * @date 2022-09-25 01:09:30
 */
public class Qiu12nLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new Qiu12nLcof().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {



	// 解法2：递归的改造递归，这里递归形式改造了
    public int sumNums(int n) {
		boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
		return n;
    }



/*
	// 常规的递归形式：
	int sum=0;
	public int sumNums(int n) {
		if(n==1) {
			return 1;
		}else{
			sum= sumNums(n-1)+n;
		}
		return sum;
	}

	*/

}
//leetcode submit region end(Prohibit modification and deletion)

}
