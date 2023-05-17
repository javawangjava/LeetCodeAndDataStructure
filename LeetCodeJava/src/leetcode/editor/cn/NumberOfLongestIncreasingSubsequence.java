/**
<p>给定一个未排序的整数数组<meta charset="UTF-8" />&nbsp;<code>nums</code>&nbsp;，&nbsp;<em>返回最长递增子序列的个数</em>&nbsp;。</p>

<p><strong>注意</strong>&nbsp;这个数列必须是 <strong>严格</strong> 递增的。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [1,3,5,4,7]
<strong>输出:</strong> 2
<strong>解释:</strong> 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> [2,2,2,2,2]
<strong>输出:</strong> 5
<strong>解释:</strong> 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong>&nbsp;</p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>-10<sup>6</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>
<div><div>Related Topics</div><div><li>树状数组</li><li>线段树</li><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 638</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 673
 * 最长递增子序列的个数
 * @author wangweizhou
 * @date 2022-08-06 10:42:57
 */

public class NumberOfLongestIncreasingSubsequence{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new NumberOfLongestIncreasingSubsequence().new Solution();

		  int[] nums={1,3,5,4,7};
		  int ans=solution.findNumberOfLIS(nums);
		 System.out.println(ans);

	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


	// 解法一：动态规划 写法1
	// 定义状态：dp[i]：到nums[i]为止的最长递增子序列长度
	//         count[i]：到nums[i]为止的最长递增子序列个数
	// 初始化状态：dp[i]=1; count[i]=1.

	// 状态转移:
	// 对于每一个数nums[i]，看在它之前的数nums[j](0<= j < i)是否比当前数nums[i]小，
	// 如果nums[i] >nums[j]，那么相当于到nums[j]为止的最长递增子序列长度到nums[i]增加了1，则到nums[i]为止的最长递增子序列长度就变成了dp[i] = dp[j] + 1；
	// 但是因为满足nums[i] >nums[j] 的nums[j]不止一个，dp[i]应该取这些dp[j] + 1的最大值，并且这些dp[j] + 1还会有相等的情况，一旦相等，到nums[i]为止的最长递增子序列个数就应该增加了。

	//在nums[i] > nums[j]的大前提下：
	//如果dp[j] + 1 > dp[i]，说明最长递增子序列的长度增加了，dp[i] = dp[j] + 1，长度增加，数量不变 count[i] = count[j]
	//如果dp[j] + 1 == dp[i]，说明最长递增子序列的长度并没有增加，但是出现了长度一样的情况，数量增加 count[i] += count[j]
	//


    public int findNumberOfLIS(int[] nums) {
		if(nums==null||nums.length==0){//判空
			return 0;
		}
		int maxLength=0;
		int maxCount =0;
		int[] dp=new int[nums.length];// dp[i]：到nums[i]为止的最长递增子序列长度
		int[] count=new int[nums.length];// count[i]：到nums[i]为止的最长递增子序列个数

		Arrays.fill(dp,1);// 初始化状态
		Arrays.fill(count,1);// 初始化状态

		for (int i = 1; i < nums.length; i++) { // i是遍历nums数组的变量
			for (int j = 0; j <i ; j++) { // j是遍历nums数组中当前元素nums[i]前面元素的变量
				if(nums[i]>nums[j]){// 寻找 nums[0..j-1] 中比 nums[i] 小的元素,其实是在nums[0..i-1] 中比 nums[i] 小的元素
					if(dp[j]+1>dp[i]){
						dp[i]=dp[j]+1;//说明最长递增子序列的长度增加了，dp[i] = dp[j] + 1，长度增加，数量不变 count[i] = count[j]
						count[i]=count[j];
					}else if(dp[j]+1==dp[i]) {//说明最长递增子序列的长度并没有增加，那么长度dp[i]不用修改但是出现了长度一样的情况，数量增加 count[i] += count[j]
						count[i] += count[j];
					}
				}
			}
		}


		for (int i = 0; i < nums.length; i++) {
			maxLength=Math.max(maxLength,dp[i]);
			//maxLength=(maxLength>dp[i]?maxLength:dp[i]);
		}

		for (int i = 0; i < nums.length; i++) {
			if(maxLength==dp[i]){
				maxCount+=count[i];
			}
		}
		return maxCount;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
