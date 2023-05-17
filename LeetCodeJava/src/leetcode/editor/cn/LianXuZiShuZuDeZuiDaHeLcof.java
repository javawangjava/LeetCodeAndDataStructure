/**
<p>输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。</p>

<p>要求时间复杂度为O(n)。</p>

<p>&nbsp;</p>

<p><strong>示例1:</strong></p>

<pre><strong>输入:</strong> nums = [-2,1,-3,4,-1,2,1,-5,4]
<strong>输出:</strong> 6
<strong>解释:</strong>&nbsp;连续子数组&nbsp;[4,-1,2,1] 的和最大，为&nbsp;6。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;arr.length &lt;= 10^5</code></li>
	<li><code>-100 &lt;= arr[i] &lt;= 100</code></li>
</ul>

<p>注意：本题与主站 53 题相同：<a href="https://leetcode-cn.com/problems/maximum-subarray/">https://leetcode-cn.com/problems/maximum-subarray/</a></p>

<p>&nbsp;</p>
<div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>动态规划</li></div></div><br><div><li>👍 605</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 42
 * 连续子数组的最大和
 * @author wangweizhou
 * @date 2022-09-24 22:36:46
 */

public class LianXuZiShuZuDeZuiDaHeLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new LianXuZiShuZuDeZuiDaHeLcof().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

	//	解法1:动态规划

	// 状态定义： 设动态规划列表 dp ，dp[i]表示以第i个元素结尾的子数组的最大和，也就是必须包含第i个元素
	// 为何定义最大和 dp[i] 中必须包含元素 nums[i] ：保证 dp[i] 递推到 dp[i+1] 的正确性；如果不包含 nums[i] ，递推时则不满足题目的 连续子数组要求。

	// 状态转移方程：dp[i]=max(dp[i-1]+nums[i],nums[i]);
	// 转移方程： 若 dp[i−1]≤0 ，说明 dp[i−1] 对 dp[i] 产生负贡献，即 dp[i−1]+nums[i] 还不如 nums[i] 本身大。
	// 当 dp[i−1]>0 时：执行 dp[i]=dp[i−1]+nums[i] ；
	// 当 dp[i−1]≤0 时：执行 dp[i]=nums[i] 。

    public int maxSubArray(int[] nums) {
		if(nums==null||nums.length==0){
			return 0;
		}
		int len=nums.length;
		int[] dp=new int[len];
		dp[0]=nums[0];
		int ans=nums[0];
		for (int i = 1; i < len; i++) {
			dp[i]=Math.max(dp[i-1]+nums[i],nums[i]);
			ans=Math.max(ans,dp[i]);
		}
		return ans;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
