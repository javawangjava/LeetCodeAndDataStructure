/**
<p>给定一个字符串<meta charset="UTF-8" />&nbsp;<code>s</code>&nbsp;，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "Let's take LeetCode contest"
<strong>输出：</strong>"s'teL ekat edoCteeL tsetnoc"
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong> s = "God Ding"
<strong>输出：</strong>"doG gniD"
</pre>

<p>&nbsp;</p>

<p><strong><strong><strong><strong>提示：</strong></strong></strong></strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;包含可打印的 <strong>ASCII</strong> 字符。</li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;不包含任何开头或结尾空格。</li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;里 <strong>至少</strong> 有一个词。</li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;中的所有单词都用一个空格隔开。</li>
</ul>
<div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 454</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 557
 * 反转字符串中的单词 III
 * @author wangweizhou
 * @date 2022-07-02 01:20:27
 */
public class ReverseWordsInAStringIii{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new ReverseWordsInAStringIii().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)

class Solution {

	//	 开辟一个新字符串。然后从头到尾遍历原字符串，直到找到空格为止，此时找到了一个单词，并能得到单词的起止位置。
	//	 随后，根据单词的起止位置，可以将该单词逆序放到新字符串当中。如此循环多次，直到遍历完原字符串，就能得到翻转后的结果。

    public String reverseWords(String s) {
		if(s==null||s.length()==0){
			return s;
		}
		int length=s.length();
		StringBuilder sb=new StringBuilder();
		int right =0; //right 遍历找到每个单词的最后一个字母的后面【本题也就是空格】
		while(right <length){
			int left = right;//left指向每个单词的首字母
			while(right <length&&s.charAt(right)!=' '){//遇到字符
				right++;
			}

		//	循环完之后，right指向单词的最后一个字母后面的空格
		//	把遇到的字符添加到sb中
		//	[left，right)是每个单词的范围

			for (int j = left; j < right; j++) {
				sb.append(s.charAt(left + right -1-j));//（left + right -1）指向单词的最后一个字母，j每次后移一个位置，（left + right -1-j）前移一个位置
			}
			while(right <length&&s.charAt(right)==' '){//遇到空格
				sb.append(' ');
				right++;
			}
		}
		return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
