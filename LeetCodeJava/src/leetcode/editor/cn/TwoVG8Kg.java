/**
<p>给定一个含有&nbsp;<code>n</code><strong>&nbsp;</strong>个正整数的数组和一个正整数 <code>target</code><strong> 。</strong></p>

<p>找出该数组中满足其和<strong> </strong><code>&ge; target</code><strong> </strong>的长度最小的 <strong>连续子数组</strong>&nbsp;<code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code> ，并返回其长度<strong>。</strong>如果不存在符合条件的子数组，返回 <code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = 7, nums = [2,3,1,2,4,3]
<strong>输出：</strong>2
<strong>解释：</strong>子数组&nbsp;<code>[4,3]</code>&nbsp;是该条件下的长度最小的子数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = 4, nums = [1,4,4]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = 11, nums = [1,1,1,1,1,1,1,1]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p>提示：</p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p>进阶：</p>

<ul>
	<li>如果你已经实现<em> </em><code>O(n)</code> 时间复杂度的解法, 请尝试设计一个 <code>O(n log(n))</code> 时间复杂度的解法。</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 209&nbsp;题相同：<a href="https://leetcode-cn.com/problems/minimum-size-subarray-sum/">https://leetcode-cn.com/problems/minimum-size-subarray-sum/</a></p>
<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>前缀和</li><li>滑动窗口</li></div></div><br><div><li>👍 70</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 和大于等于 target 的最短子数组
 */
public class TwoVG8Kg{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new TwoVG8Kg().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	//解法1：双指针 滑动窗口

	public int minSubArrayLen(int target, int[] nums) {
		int length = nums.length;
		if (length == 0) {
			return 0;
		}

		int left = 0;
		int right = 0;
		int sum = 0;
		int minLength = Integer.MAX_VALUE;
		for (; right < length; right++) {
			sum += nums[right];//right右移一位，加上[right]
			while (left <= right && sum >= target) {
				minLength = Math.min(minLength, right - left + 1);
				sum -= nums[left];
				left++;
			}
		}
		return minLength == Integer.MAX_VALUE ? 0 : minLength;
	}
}

//leetcode submit region end(Prohibit modification and deletion)

}
