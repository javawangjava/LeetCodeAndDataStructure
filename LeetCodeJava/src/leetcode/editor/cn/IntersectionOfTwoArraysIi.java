/**
<p>给你两个整数数组&nbsp;<code>nums1</code> 和 <code>nums2</code> ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,2,1], nums2 = [2,2]
<strong>输出：</strong>[2,2]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong>nums1 = [4,9,5], nums2 = [9,4,9,8,4]
<strong>输出：</strong>[4,9]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong><strong>进阶</strong>：</strong></p>

<ul>
	<li>如果给定的数组已经排好序呢？你将如何优化你的算法？</li>
	<li>如果&nbsp;<code>nums1</code><em>&nbsp;</em>的大小比&nbsp;<code>nums2</code> 小，哪种方法更优？</li>
	<li>如果&nbsp;<code>nums2</code><em>&nbsp;</em>的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？</li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>双指针</li><li>二分查找</li><li>排序</li></div></div><br><div><li>👍 777</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.*;

/**
 * 350
 * 两个数组的交集 II
 * @author wangweizhou
 * @date 2022-06-24 22:45:47
 */

public class IntersectionOfTwoArraysIi{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new IntersectionOfTwoArraysIi().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	//	解法1：哈希表




	//    解法2：排序+双指针


	public int[] intersect(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null || nums1.length == 0 ||nums2.length == 0) {
			return null;
		}
		int length1 = nums1.length;
		int length2 = nums2.length;

		// 先排序
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int curr1 = 0;
		int curr2 = 0;
		int[] intersection = new int[Math.max(length1, length2)];//存储共有元素的数组
		int index = 0;
		while (curr1 < length1 && curr2 < length2) {
			if (nums1[curr1] == nums2[curr2]) {
				intersection[index++] = nums1[curr1];// 存储共有元素
				curr1++;
				curr2++;
			} else if (nums1[curr1] < nums2[curr2]) {// 数组元素小的指针后移
				curr1++;
			} else {
				curr2++;
			}
		}
		return Arrays.copyOfRange(intersection, 0, index);
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}
