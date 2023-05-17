/**
<p>给你一个由 <code>n</code> 个整数组成的数组&nbsp;<code>nums</code> ，和一个目标值 <code>target</code> 。请你找出并返回满足下述全部条件且<strong>不重复</strong>的四元组&nbsp;<code>[nums[a], nums[b], nums[c], nums[d]]</code>&nbsp;（若两个四元组元素一一对应，则认为两个四元组重复）：</p>

<ul>
	<li><code>0 &lt;= a, b, c, d&nbsp;&lt; n</code></li>
	<li><code>a</code>、<code>b</code>、<code>c</code> 和 <code>d</code> <strong>互不相同</strong></li>
	<li><code>nums[a] + nums[b] + nums[c] + nums[d] == target</code></li>
</ul>

<p>你可以按 <strong>任意顺序</strong> 返回答案 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,0,-1,0,-2,2], target = 0
<strong>输出：</strong>[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2,2,2], target = 8
<strong>输出：</strong>[[2,2,2,2]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 1274</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 */
public class FourSum{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new FourSum().new Solution();

	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);//数组重新排序
		List<List<Integer>> ans =new ArrayList<>();
		int length=nums.length;//数组长度

		if(nums==null||length<4){
			return ans;
		}
		for (int i = 0; i < length-3; i++) {
			if((long)nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target){
				// nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target，则后面的数字和都大于target，所以循环结束
				break;
			}
			if((long)nums[i]+nums[length-3]+nums[length-2]+nums[length-1]<target){
				// nums[i]+nums[length-3]+nums[length-2]+nums[length-1]<target,则其他组合肯定小于target
				continue;
			}
			if(i>0&&nums[i]==nums[i-1]){//注意这里是（i-1）因为先要算一次，看后面的左右指针能否使用nums[i]
				continue;
			}

			for (int j = i+1; j <length-2 ; j++) {

				if(j>i+1&&nums[j]==nums[j-1]){
					continue;
				}

				if((long)nums[i]+nums[j]+nums[j+1]+nums[j+2]>target){
					// nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target，则后面的数字和都大于target，所以循环结束
					break;
				}
				if((long)nums[i]+nums[j]+nums[length-2]+nums[length-1]<target){
					// 先处理边界，能减少不必要的运算
					continue;
				}


				int left=j+1;
				int right=length-1;
				while(left<right){
					int sum=nums[i]+nums[j]+nums[left]+nums[right];
					if(sum==target){
						ans.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
						while(left<right&&nums[left]==nums[left+1]){
							left++;
						}
						left++;
						while(left<right&&nums[right]==nums[right-1]){
							right--;
						}
						right--;
					}else if(sum>target){
						right--;
					}else if(sum<target){
						left++;
					}
				}
			}
		}
		return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
