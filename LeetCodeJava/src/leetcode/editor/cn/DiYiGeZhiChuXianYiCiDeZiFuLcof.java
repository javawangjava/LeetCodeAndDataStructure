/**
<p>在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。</p>

<p><strong>示例 1:</strong></p>

<pre>
输入：s = "abaccdeff"
输出：'b'
</pre>

<p><strong>示例 2:</strong></p>

<pre>
输入：s = "" 
输出：' '
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= s 的长度 &lt;= 50000</code></p>
<div><div>Related Topics</div><div><li>队列</li><li>哈希表</li><li>字符串</li><li>计数</li></div></div><br><div><li>👍 264</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 50
 * 第一个只出现一次的字符
 * @author wangweizhou
 * @date 2022-09-14 20:23:32
 */

public class DiYiGeZhiChuXianYiCiDeZiFuLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new DiYiGeZhiChuXianYiCiDeZiFuLcof().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

	// 解法1：数组模拟哈希表，
    public char firstUniqChar(String s) {
		if(s==null||s.length()==0){
			return ' ';
		}
		int[] map=new int[26];
		char[] chArr=s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			map[chArr[i]-'a']++;
		}

		for (int i = 0; i < s.length(); i++) {
			if(map[chArr[i]-'a']==1){
				return chArr[i];
			}
		}
		return ' ';
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
