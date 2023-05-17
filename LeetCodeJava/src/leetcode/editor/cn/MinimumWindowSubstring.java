/**
<p>给你一个字符串 <code>s</code> 、一个字符串 <code>t</code> 。返回 <code>s</code> 中涵盖 <code>t</code> 所有字符的最小子串。如果 <code>s</code> 中不存在涵盖 <code>t</code> 所有字符的子串，则返回空字符串 <code>""</code> 。</p>

<p> </p>

<p><strong>注意：</strong></p>

<ul>
	<li>对于 <code>t</code> 中重复字符，我们寻找的子字符串中该字符数量必须不少于 <code>t</code> 中该字符数量。</li>
	<li>如果 <code>s</code> 中存在这样的子串，我们保证它是唯一的答案。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ADOBECODEBANC", t = "ABC"
<strong>输出：</strong>"BANC"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a", t = "a"
<strong>输出：</strong>"a"
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "a", t = "aa"
<strong>输出:</strong> ""
<strong>解释:</strong> t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length, t.length <= 10<sup>5</sup></code></li>
	<li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
</ul>

<p> </p>
<strong>进阶：</strong>你能设计一个在 <code>o(n)</code> 时间内解决此问题的算法吗？<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 2240</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 76
 * 最小覆盖子串
 * @author wangweizhou
 * @date 2022-11-14 11:11:12
 */
public class MinimumWindowSubstring{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new MinimumWindowSubstring().new Solution();

	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


	//	解法1：哈希表+滑动窗口
	//Map<Character, Integer> tmap = new HashMap<Character, Integer>();
	//Map<Character, Integer> smap = new HashMap<Character, Integer>();
	//
	//public String minWindow(String s, String t) {
	//	int tLen = t.length();
	//	// 遍历字符串t,将字符串t的每一个字符添加到tmap中
	//	for (int i = 0; i < tLen; i++) {
	//		char c = t.charAt(i);
	//		tmap.put(c, tmap.getOrDefault(c, 0) + 1);
	//	}
	//
	//	int left = 0, right = -1;
	//	int len = Integer.MAX_VALUE, ansLeft = -1, ansRight = -1;
	//	int sLen = s.length();
	//	while (right < sLen) {
	//		++right;
	//		if (right < sLen && tmap.containsKey(s.charAt(right))) {
	//			smap.put(s.charAt(right), smap.getOrDefault(s.charAt(right), 0) + 1);
	//		}
	//		while (check() && left <= right) {
	//			if (right - left + 1 < len) {
	//				len = right - left + 1;
	//				ansLeft = left;
	//				ansRight = left + len;
	//			}
	//			if (tmap.containsKey(s.charAt(left))) {
	//				smap.put(s.charAt(left), smap.getOrDefault(s.charAt(left), 0) - 1);
	//			}
	//			++left;
	//		}
	//	}
	//	return ansLeft == -1 ? "" : s.substring(ansLeft, ansRight);
	//}
	//
	//
	////
	//public boolean check() {
	//	Iterator iter = tmap.entrySet().iterator();
	//	while (iter.hasNext()) {
	//		Map.Entry entry = (Map.Entry) iter.next();
	//		Character key = (Character) entry.getKey();
	//		Integer val = (Integer) entry.getValue();
	//		if (smap.getOrDefault(key, 0) < val) {
	//			return false;
	//		}
	//	}
	//	return true;
	//}




	// 解法2：数组模拟哈希表+滑动窗口
	public String minWindow(String s, String t) {
		if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) {// 特例
			return "";
		}

		// 维护两个数组，记录已有字符串指定字符的出现次数，和目标字符串指定字符的出现次数。ASCII表总长128。数组模拟哈希表
		int[] tArr = new int[128];// 目标字符串
		int[] sArr = new int[128];// 待匹配字符串

		// 遍历目标字符串，记录目标字符串中指定字符的出现次数
		for (int i = 0; i < t.length(); i++) {
			tArr[t.charAt(i)]++;
		}


		int left = 0, right = 0;// 滑动窗口的左右指针
		int min = s.length() + 1;// 滑动窗口的最小长度(初始值为一定不可达到的长度)
		int count = 0;// 滑动窗口中有效字符的个数，即目标字符串中的字符在滑动窗口中的出现的次数
		int start = 0;// 最小覆盖子串在原字符串中的起始位置


		while (right < s.length()) {
			char r = s.charAt(right);// 待匹配字符串中待加入滑动窗口的字符
			if (tArr[r] == 0) {// 目标字符串中没有该字符，则滑动窗口右边界右移，滑动窗口中加入该无关字符，进入下一轮
				right++;
				continue;
			}


			// 之后就是待匹配字符串中有该字符
			sArr[r]++;// 待匹配字符串中目标字符出现的次数+1
			right++;// 移动右指针

			// 待匹配字符串中有该字符且当待匹配字符串中该字符的出现次数小于目标字符串该字符的出现次数时，count才会+1
			// 也就是滑动窗口中没有完全包含目标字符串
			if (sArr[r] < tArr[r]) {
				count++;
			}

			// 当滑动窗口中已经包含了所有目标字符串的字符，那么待匹配字符串的目标字符的出现次数一定等于目标字符串中目标字符的出现次数。
			// 即目标字符的长度等于计数器，也就是滑动窗口中恰好含有目标字符串中的所有字符
			while (count == t.length()) {

				if (right - left < min) {// 当窗口的长度比已有的最短值小时，更改最小值，并记录起始位置
					min = right - left;
					start = left;
				}

				char l = s.charAt(left);// 滑动窗口左边界的字符
				if (tArr[l] == 0) {// 如果滑动窗口最左边的字符不是目标字符串中的字符，直接可以移动左指针，移出最左边的字符，进入下一轮
					left++;
					continue;
				}

				// 如果滑动窗口左边即将要去掉的字符是目标字符串中的字符，且出现的频次正好等于指定频次，
				// 那么如果去掉了这个字符，就不满足覆盖子串的条件，此时要破坏循环条件跳出循环，即控制目标字符串指定字符的出现总频次(count）-1
				if (sArr[l] == tArr[l]) {
					count--;
				}

				sArr[l]--;// 滑动窗口中目标字符出现的次数-1
				left++;// 移动左指针
			}
		}
		//如果最小长度还为初始值，说明没有符合条件的子串
		if (min == s.length() + 1) {
			return "";
		}
		//返回的为以记录的起始位置为起点，记录的最短长度为距离的指定字符串中截取的子串
		return s.substring(start, start + min);
	}



}
//leetcode submit region end(Prohibit modification and deletion)

}
