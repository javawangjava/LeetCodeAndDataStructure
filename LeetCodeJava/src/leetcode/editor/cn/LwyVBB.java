/**
<p>某种外星语也使用英文小写字母，但可能顺序 <code>order</code> 不同。字母表的顺序（<code>order</code>）是一些小写字母的排列。</p>

<p>给定一组用外星语书写的单词 <code>words</code>，以及其字母表的顺序 <code>order</code>，只有当给定的单词在这种外星语中按字典序排列时，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;hello&quot;,&quot;leetcode&quot;], order = &quot;hlabcdefgijkmnopqrstuvwxyz&quot;
<strong>输出：</strong>true
<strong>解释：</strong>在该语言的字母表中，&#39;h&#39; 位于 &#39;l&#39; 之前，所以单词序列是按字典序排列的。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;word&quot;,&quot;world&quot;,&quot;row&quot;], order = &quot;worldabcefghijkmnpqstuvxyz&quot;
<strong>输出：</strong>false
<strong>解释：</strong>在该语言的字母表中，&#39;d&#39; 位于 &#39;l&#39; 之后，那么 words[0] &gt; words[1]，因此单词序列不是按字典序排列的。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;apple&quot;,&quot;app&quot;], order = &quot;abcdefghijklmnopqrstuvwxyz&quot;
<strong>输出：</strong>false
<strong>解释：</strong>当前三个字符 &quot;app&quot; 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 &quot;apple&quot; &gt; &quot;app&quot;，因为 &#39;l&#39; &gt; &#39;&empty;&#39;，其中 &#39;&empty;&#39; 是空白字符，定义为比任何其他字符都小（<a href="https://baike.baidu.com/item/%E5%AD%97%E5%85%B8%E5%BA%8F" target="_blank">更多信息</a>）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>order.length == 26</code></li>
	<li>在&nbsp;<code>words[i]</code>&nbsp;和&nbsp;<code>order</code>&nbsp;中的所有字符都是英文小写字母。</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 953&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/verifying-an-alien-dictionary/">https://leetcode-cn.com/problems/verifying-an-alien-dictionary/</a></p>
<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 33</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer II 034
 * 外星语言是否排序
 * @author wangweizhou
 * @date 2022-11-10 23:16:03
 */
public class LwyVBB{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new LwyVBB().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
		if(words==null||words.length==0){
			return false;
		}

		int[] orderArray=new int[order.length()];

		for (int i = 0; i < order.length(); i++) {
			orderArray[order.charAt(i)-'a']=i;
		}

		for (int i = 0; i < words.length-1; i++) {
			if(!isSorted(words[i],words[i+1],orderArray)){
				return false;
			}
		}
		return true;
    }

	private boolean isSorted(String word1,String word2,int[] orderArray){
		int i=0;
		int minLen=Math.min(word1.length(),word2.length());
		for (; i <minLen ; i++) {
			char ch1=word1.charAt(i);
			char ch2=word2.charAt(i);
			if(orderArray[ch1-'a']>orderArray[ch2-'a']){
				return false;
			}else if(orderArray[ch1-'a']<orderArray[ch2-'a']){
				return true;
			}
		}
		return i==word1.length();
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}
