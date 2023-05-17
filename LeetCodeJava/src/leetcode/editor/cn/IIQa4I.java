/**
<p>请根据每日 <code>气温</code> 列表 <code>temperatures</code>&nbsp;，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用&nbsp;<code>0</code> 来代替。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> <code>temperatures</code> = [73,74,75,71,69,72,76,73]
<strong>输出:</strong>&nbsp;[1,1,4,2,1,1,0,0]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> temperatures = [30,40,50,60]
<strong>输出:</strong>&nbsp;[1,1,1,0]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> temperatures = [30,60,90]
<strong>输出: </strong>[1,1,0]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;temperatures.length &lt;= 10<sup>5</sup></code></li>
	<li><code>30 &lt;=&nbsp;temperatures[i]&nbsp;&lt;= 100</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 739&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/daily-temperatures/">https://leetcode-cn.com/problems/daily-temperatures/</a></p>
<div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>单调栈</li></div></div><br><div><li>👍 68</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer II 038
 * 每日温度
 * @author wangweizhou
 * @date 2022-11-15 11:45:41
 */
public class IIQa4I{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new IIQa4I().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	// 解法3：单调栈：存入栈中的数据应该是温度在数组中的下标。等待的天数是两个温度在数组中的下标之差。

	// 从数组中读出某一天的温度，并且将其与之前的温度进行比较。将后进入数据容器中的温度先拿出来比较，符合“后入先出”。
	// 使用栈来实现这个数据容器，同时要计算出现更高温度的等待天数；存入栈中的数据应该是温度在数组中的下标。等待的天数是两个温度在数组中的下标之差。

	// 每次从数组中读取一个温度，然后将其与栈中保存的温度进行比较。如果当前温度比位于栈顶的温度高，那么就能知道位于栈顶的那一天需要等待几天才会出现更高的温度。
	// 然后出栈1次，将当前温度与下一个位于栈顶的温度进行比较，如果栈中已经没有比当前温度更低的温度，则将当前温度在数组中的下标入栈。

	public int[] dailyTemperatures(int[] temperatures) {
		if (temperatures == null || temperatures.length == 0) {// 判空
			return null;
		}
		int length = temperatures.length;
		int[] result = new int[length];
		Deque<Integer> stack = new LinkedList<>();
		for (int i = 0; i < length; i++) {
			// 栈不空且当前位置的温度大于栈顶元素位置温度
			while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
				// 获取栈顶元素的下标，那么栈顶元素之后下一个更高温度是在（i - prevIndex）位置。
				int prevIndex = stack.pop();// 获取栈顶元素的下标并将 prevIndex移除
				result[prevIndex] = i - prevIndex; // prevIndex 对应的等待天数赋为（i - prevIndex）,
			}
			stack.push(i);// 如果栈为空或者栈顶元素的温度大于当前的温度，则直接将 i 进栈
		}
		return result;
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}
