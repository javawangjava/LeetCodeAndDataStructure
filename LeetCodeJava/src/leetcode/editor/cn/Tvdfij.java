/**
<p>给你一个整数数组&nbsp;<code>nums</code> ，请计算数组的 <strong>中心下标 </strong>。</p>

<p>数组<strong> 中心下标</strong><strong> </strong>是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。</p>

<p>如果中心下标位于数组最左端，那么左侧数之和视为 <code>0</code> ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。</p>

<p>如果数组有多个中心下标，应该返回 <strong>最靠近左边</strong> 的那一个。如果数组不存在中心下标，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,7,3,6,5,6]
<strong>输出：</strong>3
<strong>解释：</strong>
中心下标是 3 。
左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1, 2, 3]
<strong>输出：</strong>-1
<strong>解释：</strong>
数组中不存在满足此条件的中心下标。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2, 1, -1]
<strong>输出：</strong>0
<strong>解释：</strong>
中心下标是 0 。
左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 724&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/find-pivot-index/">https://leetcode-cn.com/problems/find-pivot-index/</a></p>
<div><div>Related Topics</div><div><li>数组</li><li>前缀和</li></div></div><br><div><li>👍 38</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 左右两边子数组的和相等
 * @author wangweizhou
 * @date 2022-06-22 23:17:02
 */
public class Tvdfij{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new Tvdfij().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	//	解法1：前缀和改进

	public int pivotIndex(int[] nums) {
		int totalSum=0;
		//遍历求数组的总和
		for (int num:nums) {
			totalSum+=num;
		}
		int sum=0;// 表示前i项和
		for (int i = 0; i < nums.length; i++) {
			sum+=nums[i];//累加求前i项和，单并没有创建数组保存
			if(sum-nums[i]==totalSum-sum){//sum-nums[i]是前（i-1）项的子数组和，totalSum-sum是从(i+1)项开始的子数组和
				return i;
			}
		}
		return -1;
	}


	//	 解法2：前缀和
/*
    public int pivotIndex(int[] nums) {
		int length=nums.length;
		//创建数组，求前i项和并保存
		int[] sums=new int[length];//sum[i]表示数组的前i项和
		sums[0]=nums[0];
		for (int i = 1; i <length ; i++) {
			sums[i]+=nums[i];
			sums[i]=sums[i-1]+nums[i];//前i项和等于前（i-1）项和+第i项
		}

		int totalSum=sums[length-1];

		// 当前下标curr,假定当前下标是中心下标
		int curr=0;
		for (curr = 0; curr < length; curr++) {
			// 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。
			if(sums[curr]-nums[curr]==totalSum-sums[curr]){
				return curr;
			}
		}
		return -1;
    }*/
}
//leetcode submit region end(Prohibit modification and deletion)

}
