/**
<p>假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。</p>

<p>你需要帮助他们用<strong>最少的索引和</strong>找出他们<strong>共同喜爱的餐厅</strong>。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
<strong>输出:</strong> ["Shogun"]
<strong>解释:</strong> 他们唯一共同喜爱的餐厅是“Shogun”。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
<strong>输出:</strong> ["Shogun"]
<strong>解释:</strong> 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= list1.length, list2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= list1[i].length, list2[i].length &lt;= 30</code>&nbsp;</li>
	<li><code>list1[i]</code> 和 <code>list2[i]</code> 由空格<meta charset="UTF-8" />&nbsp;<code>' '</code>&nbsp;和英文字母组成。</li>
	<li><code>list1</code> 的所有字符串都是 <strong>唯一</strong> 的。</li>
	<li><code>list2</code> 中的所有字符串都是 <strong>唯一</strong> 的。</li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 222</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 599
 * 两个列表的最小索引总和
 * @author wangweizhou
 * @date 2022-07-07 01:58:24
 */
public class MinimumIndexSumOfTwoLists{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new MinimumIndexSumOfTwoLists().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


	//	 解法1：哈希表
	//   为了快速判断某个字符串是否在另外一个数组中出现，我们可以先使用「哈希表」对 list1 中的字符串进行处理，以 (list1[i]:i) 键值对形式进行存储。
	//然后遍历 list2，判断每个 list2[i] 是否在哈希表中出现过，同时维护一个当前的 最小索引总和 min，以及 用于存储能够取得最小索引总和的字符串数组 ans。
	//假设当前遍历到的元素是 list2[i]，根据list2[i] 是否在哈希表中出现」以及「当前索引和与 min 的大小关系」分情况讨论：
	//如果 list2[i] 不在哈希表中，跳过：
	//如果 list2[i] 在哈希表中：
	//索引之和等于 min，将 list2[i] 加入 ans；
	//索引之和小于 min，更新 min，清空 ans，将 list2[i] 加入 ans；
	//索引之和大于 min，跳过。

	//使用一个哈希表记录 list1中每个餐厅对应的索引下标，然后遍历 list2，如果 list2中的餐厅存在于哈希表中，那么说明该餐厅是两人共同喜爱的，计算它的索引和。
	// 如果该索引和比最小索引和小，则清空结果，将该餐厅加入结果中，该索引和作为最小索引和；如果该索引和等于最小索引和，则直接将该餐厅加入结果中。


    public String[] findRestaurant(String[] list1, String[] list2) {
		int length1=list1.length;
		int length2=list2.length;
		// 只需要加这三行代码
		if (length1 > length2) {
			return findRestaurant(list2, list1);
		}
		// 先使用「哈希表」对 list1 中的字符串进行处理，以 (list1[i]:i) 键值对形式进行存储
		Map<String,Integer> map=new HashMap<>();

		for (int i = 0; i < length1; i++) {
			map.put(list1[i],i);
		}

		List<String> list=new ArrayList<>();//可变数组来存储喜欢的餐馆
		int minIndexSum = Integer.MAX_VALUE;

		for (int i = 0; i < length2; i++) {
			//如果 list2中的餐厅存在于哈希表中，那么说明该餐厅是两人共同喜爱的，计算它的索引和。
			if(map.containsKey(list2[i])){
				//在列表2中寻找餐厅时，当前索引大于最小索引时，后续肯定大于最小索引，没必要再寻找了
				if(map.get(list2[i])>minIndexSum){
					break;
				}
				int indexSum=i+map.get(list2[i]);//index2是列表2的索引
				//如果该索引和比最小索引和小，则清空结果，将该餐厅加入结果中，该索引和作为最小索引和
				if(indexSum< minIndexSum){
					list.clear();
					list.add(list2[i]);
					minIndexSum =indexSum;
				}else if(indexSum== minIndexSum){//如果该索引和等于最小索引和，则直接将该餐厅加入结果中。
					list.add(list2[i]);
				}
			}
		}
		return list.toArray(new String[list.size()]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
