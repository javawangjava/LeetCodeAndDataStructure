/**
<p>请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入: </strong>&quot;abcabcbb&quot;
<strong>输出: </strong>3 
<strong>解释:</strong> 因为无重复字符的最长子串是 <code>&quot;abc&quot;，所以其</code>长度为 3。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>&quot;bbbbb&quot;
<strong>输出: </strong>1
<strong>解释: </strong>因为无重复字符的最长子串是 <code>&quot;b&quot;</code>，所以其长度为 1。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入: </strong>&quot;pwwkew&quot;
<strong>输出: </strong>3
<strong>解释: </strong>因为无重复字符的最长子串是&nbsp;<code>&quot;wke&quot;</code>，所以其长度为 3。
&nbsp;    请注意，你的答案必须是 <strong>子串 </strong>的长度，<code>&quot;pwke&quot;</code>&nbsp;是一个<em>子序列，</em>不是子串。
</pre>

<p>&nbsp;</p>

<p>提示：</p>

<ul>
	<li><code>s.length &lt;= 40000</code></li>
</ul>

<p>注意：本题与主站 3 题相同：<a href="https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/">https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/</a></p>
<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 510</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 48
 * 最长不含重复字符的子字符串
 * @author wangweizhou
 * @date 2022-11-15 09:54:38
 */
public class ZuiChangBuHanZhongFuZiFuDeZiZiFuChuanLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new ZuiChangBuHanZhongFuZiFuDeZiZiFuChuanLcof().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	public int lengthOfLongestSubstring(String s) {
		if(s==null||s.length()==0){//判空
			return 0;
		}
		int length = s.length();//length表示字符串长度
		int max = 0;//保存满足当前要求的子串长度的最大值
		Map<Character, Integer> map = new HashMap<>();  //map 的key是用来保存字符，value保存字符在字符串中对应的下标也就是位置

		int left = 0;//left指向滑动窗口最左端的元素
		int right = 0;//right指向滑动窗口最右端的元素

		// 因为滑动窗口的左指针可以一次移动到目标位置，每次循环都有新元素进入滑动窗口，所以需要更新最大长度和map中元素
		while (right < length) {//这个就不需要判断左边界了，根据程序，左边界一定在右边界左边或者相等。
			char ch=s.charAt(right);

			// 先判断map中有没有right指向的元素，这时候right指向的元素还没有加入map集合中
			if (map.containsKey(ch)) {// 若map中已经添加该元素了，那么更新滑动窗口的左边界。左边界应该取下面两种情况的较大的。
				// 情况1：若滑动窗口中没有该元素，也就是说明该元素在左边界的左边，那么左边界不变，
				// 情况2：若滑动窗口中有该元素，那么左边界就要移动到charAt(right)所指向的元素的下一位
				left = Math.max(1 + map.get(ch), left);
			}

			max = Math.max(max, right - left +1);//更新子串的最大长度
			map.put(s.charAt(right), right);// 若是第一次遇到的新字符就加入，若是第二次遇到的那么就更新value也就是更新位置。
			right++;//滑动窗口最右端指针后移
		}
		return max;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}
