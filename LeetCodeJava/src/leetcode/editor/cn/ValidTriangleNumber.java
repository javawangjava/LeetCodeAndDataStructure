/**
<p>给定一个包含非负整数的数组&nbsp;<code>nums</code> ，返回其中可以组成三角形三条边的三元组个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,2,3,4]
<strong>输出:</strong> 3
<strong>解释:</strong>有效的组合是: 
2,3,4 (使用第一个 2)
2,3,4 (使用第二个 2)
2,2,3
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,2,3,4]
<strong>输出:</strong> 4</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>
<div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>双指针</li><li>二分查找</li><li>排序</li></div></div><br><div><li>👍 432</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 611
 * 有效三角形的个数
 * @author wangweizhou
 * @date 2022-09-01 15:38:44
 */
public class ValidTriangleNumber{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new ValidTriangleNumber().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


	// 形成三角形的条件：三角形任意两边长度之和大于第三边

	// 解法1： 数组排序+固定最长边+双指针相向遍历
    public int triangleNumber(int[] nums) {
		if(nums==null||nums.length<3){
			return 0;
		}
		int len=nums.length;
		int count=0;
		Arrays.sort(nums);
		for (int i=len-1;i>=0;i--){// 遍历固定最长边
			int left=0;// 双指针相向遍历
			int right=i-1;
			while(left<right){
				if(nums[left]+nums[right]>nums[i]){
					// nums[left]+nums[right]>nums[i],那么,nums[left],nums[left+1]一直到nums[right-1]和nums[right]的和都大于nums[i]
					count=count+right-left;
					right--;// 两边和已经比最大边大了，那么次长边减小，看还有没有
				}else{// nums[left]+nums[right]<nums[i]，那么两边和要增大，那么最短边要增大
					left++;
				}
			}
		}
		return count;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
