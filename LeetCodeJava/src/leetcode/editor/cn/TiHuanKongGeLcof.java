/**
<p>请实现一个函数，把字符串 <code>s</code> 中的每个空格替换成&quot;%20&quot;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;We are happy.&quot;
<strong>输出：</strong>&quot;We%20are%20happy.&quot;</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= s 的长度 &lt;= 10000</code></p>
<div><div>Related Topics</div><div><li>字符串</li></div></div><br><div><li>👍 345</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 05
 * 替换空格
 * @author wangweizhou
 * @date 2022-09-13 17:01:51
 */

public class TiHuanKongGeLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new TiHuanKongGeLcof().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	/*

	//	解法2：API
	public String replaceSpace(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		return s.replace(" ","%20");
	}

	*/





	//	解法1：可变字符串遍历替换
	// java 的底层charAt也是将字符串转换成字符数组
    public String replaceSpace(String s) {
		if(s==null||s.length()==0){
			return s;
		}
		StringBuilder sb=new StringBuilder();
		int len=s.length();
		for (int i = 0; i < len; i++) {
			 char ch=s.charAt(i);
			if(ch!=' '){
				sb.append(ch);
			}else{
				sb.append("%20");
			}
		}
		return sb.toString();
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
