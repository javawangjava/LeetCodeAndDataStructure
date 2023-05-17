/**
<p>给定两个字符串 <code>s</code> 和 <code>t</code> ，编写一个函数来判断它们是不是一组变位词（字母异位词）。</p>

<p><strong>注意：</strong>若&nbsp;<code><em>s</em></code> 和 <code><em>t</em></code><em>&nbsp;</em>中每个字符出现的次数都相同且<strong>字符顺序不完全相同</strong>，则称&nbsp;<code><em>s</em></code> 和 <code><em>t</em></code><em>&nbsp;</em>互为变位词（字母异位词）。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;anagram&quot;, t = &quot;nagaram&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;rat&quot;, t = &quot;car&quot;
<strong>输出: </strong>false</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;a&quot;, t = &quot;a&quot;
<strong>输出: </strong>false</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;and&nbsp;<code>t</code>&nbsp;仅包含小写字母</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶:&nbsp;</strong>如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？</p>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 242&nbsp;题相似（字母异位词定义不同）：<a href="https://leetcode-cn.com/problems/valid-anagram/">https://leetcode-cn.com/problems/valid-anagram/</a></p>
<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 33</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer II 032
 * 有效的变位词
 * @author wangweizhou
 * @date 2022-11-10 22:43:32
 */
public class DKk3P7{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new DKk3P7().new Solution();
		  boolean ans= solution.isAnagram("asd", "da");
		 System.out.println(ans);
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean isAnagram(String s, String t) {
		if ((s == null && t != null) || (s != null && t == null)) {
			return false;
		}

		if((s.length()!=t.length())||(s.equals(t))){
			return false;
		}

		int[] counts=new int[26];
		char[] charS=s.toCharArray();
		char[] charT=t.toCharArray();

		for (char ch:charS) {
			counts[ch-'a']++;
		}

		for (char ch:charT) {
			if(counts[ch-'a']==0){
				return false;
			}
			counts[ch-'a']--;
		}
		return true;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
