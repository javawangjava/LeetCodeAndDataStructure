/**
<p>字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串&quot;abcdefg&quot;和数字2，该函数将返回左旋转两位得到的结果&quot;cdefgab&quot;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> s = &quot;abcdefg&quot;, k = 2
<strong>输出:&nbsp;</strong>&quot;cdefgab&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> s = &quot;lrloseumgh&quot;, k = 6
<strong>输出:&nbsp;</strong>&quot;umghlrlose&quot;
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt; s.length &lt;= 10000</code></li>
</ul>
<div><div>Related Topics</div><div><li>数学</li><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 299</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 58 - II
 * 左旋转字符串
 * @author wangweizhou
 * @date 2022-09-14 11:10:00
 */

public class ZuoXuanZhuanZiFuChuanLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new ZuoXuanZhuanZiFuChuanLcof().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


	//	解法1：遍历拼接  两次遍历拼接
    public String reverseLeftWords(String s, int n) {
		if(s==null||s.length()==0){
			return s;
		}

		StringBuilder sb=new StringBuilder();
		int len=s.length();
		int newN=n%len;// 当n大于字符串长度时，那么就要取余这样就可以获取移动的最少次数
		for (int i =newN ; i < len; i++) {
			sb.append(s.charAt(i));
		}
		for (int i = 0; i < newN; i++) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
    }




	//// 解法2:调用系统API
	//public String reverseLeftWords(String s, int n) {
	//	if (s == null || s.length() == 0) {
	//		return s;
	//	}
	//	StringBuilder sb = new StringBuilder();
	//	int len = s.length();
	//	int newN = n % len;
	//
	//	String str=s.substring(newN,len)+s.substring(0,newN);
	//	return str;
	//}



}
//leetcode submit region end(Prohibit modification and deletion)

}
