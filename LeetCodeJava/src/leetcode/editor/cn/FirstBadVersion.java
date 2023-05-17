/**
<p>你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。</p>

<p>假设你有 <code>n</code> 个版本 <code>[1, 2, ..., n]</code>，你想找出导致之后所有版本出错的第一个错误的版本。</p>

<p>你可以通过调用 <code>bool isBadVersion(version)</code> 接口来判断版本号 <code>version</code> 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。</p>
 

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 5, bad = 4
<strong>输出：</strong>4
<strong>解释：</strong>
<code>调用 isBadVersion(3) -> false 
调用 isBadVersion(5) -> true 
调用 isBadVersion(4) -> true</code>
<code>所以，4 是第一个错误的版本。</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1, bad = 1
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= bad <= n <= 2<sup>31</sup> - 1</code></li>
</ul>
<div><div>Related Topics</div><div><li>二分查找</li><li>交互</li></div></div><br><div><li>👍 732</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 第一个错误的版本
 * @author wangweizhou
 * @date 2022-06-27 23:18:33
 */
public class FirstBadVersion{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 //Solution solution = new FirstBadVersion().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
/*
public class Solution extends VersionControl {
	// 方法一：二分查找
	// 当一个版本为正确版本，则该版本之前的所有版本均为正确版本；当一个版本为错误版本，则该版本之后的所有版本均为错误版本。我们可以利用这个性质进行二分查找。
    public int firstBadVersion(int n) {
		int left=1;
		int right=n;
		while(left<right){// 循环直至区间左右端点相同
			int mid=(left+right)>>>1;
			if(isBadVersion(mid)){//mid是错误版本，则正确版本在左边
				right=mid;// 答案在区间 [left, mid] 中
			}else{//mid是正确版本，则错误版本只能在（mid+1）之后
				left = mid + 1; // 答案在区间 [mid+1, right] 中
			}
		}
		// 此时有 left == right，区间缩为一个点，即为答案
        return left;
    }
}*/
//leetcode submit region end(Prohibit modification and deletion)

}
