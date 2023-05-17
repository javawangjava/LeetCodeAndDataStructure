/**
<p>给你一个数组，将数组中的元素向右轮转 <code>k</code><em>&nbsp;</em>个位置，其中&nbsp;<code>k</code><em>&nbsp;</em>是非负数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4,5,6,7], k = 3
<strong>输出:</strong> <code>[5,6,7,1,2,3,4]</code>
<strong>解释:</strong>
向右轮转 1 步: <code>[7,1,2,3,4,5,6]</code>
向右轮转 2 步: <code>[6,7,1,2,3,4,5]
</code>向右轮转 3 步: <code>[5,6,7,1,2,3,4]</code>
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,-100,3,99], k = 2
<strong>输出：</strong>[3,99,-1,-100]
<strong>解释:</strong> 
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>尽可能想出更多的解决方案，至少有 <strong>三种</strong> 不同的方法可以解决这个问题。</li>
	<li>你可以使用空间复杂度为&nbsp;<code>O(1)</code> 的&nbsp;<strong>原地&nbsp;</strong>算法解决这个问题吗？</li>
</ul>

<ul>
</ul>

<ul>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>数学</li><li>双指针</li></div></div><br><div><li>👍 1520</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 轮转数组
 * @author wangweizhou
 * @date 2022-06-24 17:08:42
 */
public class RotateArray{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new RotateArray().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

	//	 方法1:使用额外的数组
	// 我们可以使用额外的数组来将每个元素放至正确的位置。
	// 用 n 表示数组的长度，我们遍历原数组，将原数组下标为 i 的元素放至新数组下标为(i+k)modn 的位置，最后将新数组拷贝至原数组即可
/*
    public void rotate(int[] nums, int k) {
		int length=nums.length;
		int[] temp=new int[length];
		for (int i = 0; i < length; i++) {
			temp[(i+k)%length]=nums[i];
		}
		for (int i = 0; i < length; i++) {
			nums[i]=temp[i];
		}
    }*/

//	方法2：数组反转

	public void rotate(int[] nums,int k){
		int length=nums.length;
		k%=length;//注意对k取余
		reverse(nums,0,length-1);
		reverse(nums,0,k-1);
		reverse(nums,k,length-1);
	}

	// 双指针交换
	// 该方法基于如下的事实：当我们将数组的元素向右移动 k 次后，尾部 kmodn 个元素会移动至数组头部，其余元素向后移动kmodn 个位置。
	// 该方法为数组的翻转：我们可以先将所有元素翻转，这样尾部的 kmodn 个元素就被移至数组头部，然后我们再翻转 [0,kmodn−1] 区间的元素和[kmodn,n−1] 区间的元素即能得到最后的答案。

	public void reverse(int[] nums,int start,int end){
		while(start<end){
			int temp=nums[start];
			nums[start]=nums[end];
			nums[end]=temp;
			start++;
			end--;
		}
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}
