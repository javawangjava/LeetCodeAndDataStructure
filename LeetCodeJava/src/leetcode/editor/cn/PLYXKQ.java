/**
<p>给定一个由&nbsp;<code>0</code> 和 <code>1</code>&nbsp;组成的矩阵 <code>matrix</code>&nbsp;，找出只包含 <code>1</code> 的最大矩形，并返回其面积。</p>

<p><strong>注意：</strong>此题 <code>matrix</code>&nbsp;输入格式为一维 <code>01</code> 字符串数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/09/14/maximal.jpg" style="width: 402px; height: 322px;" /></p>

<pre>
<strong>输入：</strong>matrix = ["10100","10111","11111","10010"]
<strong>输出：</strong>6
<strong>解释：</strong>最大矩形如上图所示。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>matrix = []
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = ["0"]
<strong>输出：</strong>0
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>matrix = ["1"]
<strong>输出：</strong>1
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>matrix = ["00"]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>rows == matrix.length</code></li>
	<li><code>cols == matrix[0].length</code></li>
	<li><code>0 &lt;= row, cols &lt;= 200</code></li>
	<li><code>matrix[i][j]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

<p>&nbsp;</p>

<p>注意：本题与主站 85 题相同（输入参数格式不同）：&nbsp;<a href="https://leetcode-cn.com/problems/maximal-rectangle/">https://leetcode-cn.com/problems/maximal-rectangle/</a></p>
<div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>动态规划</li><li>矩阵</li><li>单调栈</li></div></div><br><div><li>👍 66</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer II 040
 * 矩阵中最大的矩形
 * @author wangweizhou
 * @date 2022-11-15 19:48:41
 */
public class PLYXKQ{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new PLYXKQ().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	// 解法1： 单调栈+将矩阵转化为直方图

	// 直方图是由排列在同一基线上的相邻的柱子组成的图形。
	// 因为题目要求矩阵中只包含数字1，所以将矩阵中上下相邻的值为1的格子看成直方图中的柱子。分别以矩阵的每行为基线。
	// 将矩阵转换为直方图之后，就可以计算并比较每个直方图中的最大矩形面积。所有直方图中的最大矩形面积就是整个矩阵中的最大面积。

	public int maximalRectangle(String[] matrix) {
		if (matrix==null||matrix.length == 0) {
			return 0;
		}

		int[] heights = new int[matrix[0].length()];
		int maxArea = 0;

		for (int row = 0; row < matrix.length; row++) {
			//遍历每一列，更新高度
			for (int col = 0; col < matrix[0].length(); col++) {
				if (matrix[row].charAt(col) == '1') {// 当矩阵中当前元素是1时，该位置直方图的高度相较上一个直方图的对应列高度+1
					heights[col] += 1;
				} else {// 当矩阵中当前元素是0时，该位置直方图的高度为0.
					heights[col] = 0;
				}
			}
			//调用上一题的解法，更新函数
			maxArea = Math.max(maxArea, largestRectangleArea(heights));
		}
		return maxArea;
	}


	// 获取直方图中矩形的最大面积
	private int largestRectangleArea(int[] heights) {//
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
