/**
<p>给定一个字符串&nbsp;<code>s</code>&nbsp;，找到 <em>它的第一个不重复的字符，并返回它的索引</em> 。如果不存在，则返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> s = "leetcode"
<strong>输出:</strong> 0
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "loveleetcode"
<strong>输出:</strong> 2
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "aabb"
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写字母</li>
</ul>
<div><div>Related Topics</div><div><li>队列</li><li>哈希表</li><li>字符串</li><li>计数</li></div></div><br><div><li>👍 559</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串中的第一个唯一字符
 * @author wangweizhou
 * @date 2022-06-25 09:10:47
 */
public class FirstUniqueCharacterInAString{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new FirstUniqueCharacterInAString().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

	// 方法一：使用哈希表存储频数
	// 在第一次遍历时，我们使用哈希映射统计出字符串中每个字符出现的次数。
	// 在第二次遍历时，我们只要遍历到了一个只出现一次的字符，那么就返回它的索引，否则在遍历结束后返回 -1。

	/*

    public int firstUniqChar(String s) {
		int length=s.length();
		Map<Character,Integer> map=new HashMap<>();
		for (int i = 0; i < length; i++) {
			char curr=s.charAt(i);
			map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
		}

		for (int i = 0; i < length; i++) {
			if(map.get(s.charAt(i))==1){
				return i;
			}
		}
		return -1;
    }
	*/

//	方法二：使用哈希表存储索引


//	对于哈希映射中的每一个键值对，键表示一个字符，值表示它的首次出现的索引（如果该字符只出现一次）或者 −1（如果该字符出现多次）。
//  当我们第一次遍历字符串时，设当前遍历到的字符为 c，如果 c 不在哈希映射中，我们就将 c 与它的索引作为一个键值对加入哈希映射中，否则我们将 c 在哈希映射中对应的值修改为 −1。
//  在第一次遍历结束后，我们只需要再遍历一次哈希映射中的所有值，找出其中不为 -1的最小值，即为第一个不重复字符的索引。如果哈希映射中的所有值均为 -1，我们就返回 −1。

	public int firstUniqChar(String s) {
		Map<Character,Integer> map=new HashMap<>();
		int length=s.length();
		for (int i = 0; i < length; i++) {
			char ch=s.charAt(i);
			if(map.containsKey(ch)){
				map.put(ch,-1);
			}else{
				map.put(ch,i);
			}
		}

		int ans=length;
		// 遍历键值对
		for (Map.Entry<Character,Integer> entry:map.entrySet()){
			int position=entry.getValue();//获取键值对的值也就是索引
			if(position!=-1&&position<ans){// 有效索引的最小值，也就是第一个不重复字符的索引
				ans=position;
			}
		}

		return ans==length?-1:ans;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}
