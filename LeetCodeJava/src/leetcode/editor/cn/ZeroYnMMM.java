/**
<p>给定非负整数数组 <code>heights</code>&nbsp;，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 <code>1</code> 。</p>

<p>求在该柱状图中，能够勾勒出来的矩形的最大面积。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg" /></p>

<pre>
<strong>输入：</strong>heights = [2,1,5,6,2,3]
<strong>输出：</strong>10
<strong>解释：</strong>最大的矩形为图中红色区域，面积为 10
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2021/01/04/histogram-1.jpg" /></p>

<pre>
<strong>输入：</strong> heights = [2,4]
<b>输出：</b> 4</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;=10<sup>5</sup></code></li>
	<li><code>0 &lt;= heights[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 84&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/largest-rectangle-in-histogram/">https://leetcode-cn.com/problems/largest-rectangle-in-histogram/</a></p>
<div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>单调栈</li></div></div><br><div><li>👍 66</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer II 039
 * 直方图最大矩形面积
 * @author wangweizhou
 * @date 2022-11-15 16:44:59
 */
public class ZeroYnMMM{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new ZeroYnMMM().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	public int largestRectangleArea(int[] heights) {//
		if (heights == null || heights.length == 0) {
			return 0;
		}
		Deque<Integer> stack=new LinkedList<>();
		stack.push(-1);
		int maxArea=0;
		for (int i = 0; i < heights.length; i++) {
			// 以某根柱子为顶的最大矩形，一定是从该柱子向两侧延伸直到遇到比它矮的柱子，这个最大矩形的高是该柱子的高，最大矩形的宽是两侧比它矮的柱子中间的间隔。
			// 如果当前柱子的高度小于位于栈顶的柱子的高度，将位于栈顶的柱子的下标出栈，并且计算以位于栈顶的柱子为顶的最大矩形面积。下面是个循环，所以会计算出以heights[i]为顶的最大矩形面积
			while(stack.peek()!=-1&&heights[stack.peek()]>=heights[i]){
				int height=heights[stack.pop()];// 栈顶柱子高度，当前柱子高度小于等于栈顶柱子高度
				int width=i- stack.peek()-1;// 以栈顶柱子为高的矩形宽度
				maxArea=Math.max(maxArea,height*width);// 更新最大面积
			}
			// 执行到这里：stack.peek()==-1||heights[stack.peek()]<heights[i]
			stack.push(i);// 栈中的柱子高度升序排列
		}

		while(stack.peek()!=-1){
			int height=heights[stack.pop()];
			int width=heights.length-stack.peek()-1;
			maxArea=Math.max(maxArea,height*width);
		}
		return maxArea;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}
