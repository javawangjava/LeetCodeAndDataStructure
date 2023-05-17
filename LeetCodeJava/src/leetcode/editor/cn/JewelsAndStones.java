/**
<p>&nbsp;给你一个字符串 <code>jewels</code>&nbsp;代表石头中宝石的类型，另有一个字符串 <code>stones</code> 代表你拥有的石头。&nbsp;<code>stones</code>&nbsp;中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。</p>

<p>字母区分大小写，因此 <code>"a"</code> 和 <code>"A"</code> 是不同类型的石头。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>jewels = "aA", stones = "aAAbbbb"
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>jewels = "z", stones = "ZZ"
<strong>输出：</strong>0<strong>
</strong></pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;jewels.length, stones.length &lt;= 50</code></li>
	<li><code>jewels</code> 和 <code>stones</code> 仅由英文字母组成</li>
	<li><code>jewels</code> 中的所有字符都是 <strong>唯一的</strong></li>
</ul>
<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 707</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * 771
 * 宝石与石头
 * @author wangweizhou
 * @date 2022-07-06 14:57:37
 */
public class JewelsAndStones{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new JewelsAndStones().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {



	//	 方法1：用数组模拟哈希表  因为字符串只由英文大小写字母组成
	//1 <=jewels.length, stones.length <= 50
	//jewels 和 stones 仅由英文字母组成
	//jewels 中的所有字符都是 唯一的

	public int numJewelsInStones(String jewels, String stones) {

		int len = jewels.length();// len设置为jewels长度

		// Java中，unicode编码统一了全球所有的文字，支持所有文字， char字符和整型在范围内可以相互转换
		// 原本'A'->'z'是[65,122],jewels.charAt(i)-'A'就转换成[0-64]
		int[] type = new int[64];
		for(int i = 0; i < len; i++){
			//jewels.charAt(i)-'A'
			type[jewels.charAt(i)-'A'] = 1;// 将jewels数组中有的字符对应的数组值设置为1。其余数组值默认设置为0。
		}
		int ans = 0;
		len = stones.length();// len重设为stones长度
		for(int i = 0; i < len; i++){// 遍历stones字符串有jewels中的字符看数字1的个数，那么求和就可以
			ans += type[stones.charAt(i)-'A'];
		}
		return ans;
	}




	/*

	//	方法二：哈希集合  调用API
	//  遍历字符串 jewels，使用哈希集合存储其中的字符，然后遍历字符串 stones，对于其中的每个字符，如果其在哈希集合中，则是宝石。

    public int numJewelsInStones(String jewels, String stones) {

		Set<Character> set=new HashSet<>();
		//  遍历字符串 jewels，使用哈希集合存储其中的字符
		for (int i = 0; i < jewels.length(); i++) {
			set.add(jewels.charAt(i));
		}
		int jewelCount=0;

		// 遍历字符串 stones，对于其中的每个字符，如果其在哈希集合中，则是宝石。
		for (int i = 0; i < stones.length(); i++) {
			if(set.contains(stones.charAt(i))){
				jewelCount++;
			}
		}
		return jewelCount;
    }
	*/
}
//leetcode submit region end(Prohibit modification and deletion)

}
