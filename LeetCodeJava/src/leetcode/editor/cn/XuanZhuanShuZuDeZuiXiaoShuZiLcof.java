/**
<p>把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。</p>

<p>给你一个可能存在&nbsp;<strong>重复</strong>&nbsp;元素值的数组&nbsp;<code>numbers</code>&nbsp;，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的<strong>最小元素</strong>。例如，数组&nbsp;<code>[3,4,5,1,2]</code> 为 <code>[1,2,3,4,5]</code> 的一次旋转，该数组的最小值为 1。&nbsp;&nbsp;</p>

<p>注意，数组 <code>[a[0], a[1], a[2], ..., a[n-1]]</code> 旋转一次 的结果为数组 <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong><code>numbers = </code>[3,4,5,1,2]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong><code>numbers = </code>[2,2,2,0,1]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == numbers.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>-5000 &lt;= numbers[i] &lt;= 5000</code></li>
	<li><code>numbers</code> 原来是一个升序排序的数组，并进行了 <code>1</code> 至 <code>n</code> 次旋转</li>
</ul>

<p>注意：本题与主站 154 题相同：<a href="https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/">https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/</a></p>
<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 702</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 11
 * 旋转数组的最小数字
 * @author wangweizhou
 * @date 2022-09-13 22:24:54
 */


public class XuanZhuanShuZuDeZuiXiaoShuZiLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new XuanZhuanShuZuDeZuiXiaoShuZiLcof().new Solution();
		  //int[] numbers={3,4,5,1,2};
		 int[] numbers={1,1,1,0,1};
		 // int[] numbers={3,4,4,4,1,1,2};
		 int ans=solution.minArray(numbers);
		 System.out.println(ans);

	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {



	public int minArray(int[] numbers) {
		if (numbers == null || numbers.length == 0) {
			return Integer.MAX_VALUE;
		}
		int left=0;
		int right=numbers.length-1;

		while(left<right){
			int mid=(left+right)>>1;
			if(numbers[left]<numbers[right]){// 【left,right】是单调区间，则left指向的是最小值
				return numbers[left];
			}
			if(numbers[left]>numbers[mid]){//mid在右排序数组，并且右排序数组长
				right=mid;
			}else if(numbers[left]<numbers[mid]){// mid在左排序数组，并且左排序数组长
				left=mid+1;
			}else{
				left++;// 向增加的方向变
			}
		}
		return numbers[left];
	}




	/*

	public int minArray(int[] numbers) {
		if(numbers==null||numbers.length==0){
			return Integer.MAX_VALUE;
		}
		int left = 0;
		int right = numbers.length - 1;
		while (left < right) {
			int mid = (right + left) / 2;
			if (numbers[mid] < numbers[right]) {
				right = mid;
			} else if (numbers[mid] > numbers[right]) {
				left = mid + 1;
			} else {
				right --;
			}
		}
		return numbers[left];
	}

	*/
}
//leetcode submit region end(Prohibit modification and deletion)

}
