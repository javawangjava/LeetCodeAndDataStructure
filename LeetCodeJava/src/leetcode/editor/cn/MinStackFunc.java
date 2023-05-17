/**
<p>设计一个支持 <code>push</code> ，<code>pop</code> ，<code>top</code> 操作，并能在常数时间内检索到最小元素的栈。</p>

<p>实现 <code>MinStack</code> 类:</p>

<ul>
	<li><code>MinStack()</code> 初始化堆栈对象。</li>
	<li><code>void push(int val)</code> 将元素val推入堆栈。</li>
	<li><code>void pop()</code> 删除堆栈顶部的元素。</li>
	<li><code>int top()</code> 获取堆栈顶部的元素。</li>
	<li><code>int getMin()</code> 获取堆栈中的最小元素。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

<strong>输出：</strong>
[null,null,null,null,-3,null,0,-2]

<strong>解释：</strong>
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --&gt; 返回 -3.
minStack.pop();
minStack.top();      --&gt; 返回 0.
minStack.getMin();   --&gt; 返回 -2.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
	<li><code>pop</code>、<code>top</code> 和 <code>getMin</code> 操作总是在 <strong>非空栈</strong> 上调用</li>
	<li><code>push</code>,&nbsp;<code>pop</code>,&nbsp;<code>top</code>, and&nbsp;<code>getMin</code>最多被调用&nbsp;<code>3 * 10<sup>4</sup></code>&nbsp;次</li>
</ul>
<div><div>Related Topics</div><div><li>栈</li><li>设计</li></div></div><br><div><li>👍 1411</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;
import java.util.*;

/**
 * 155
 * 最小栈
 * @author wangweizhou
 * @date 2022-09-14 15:54:03
 */

// 这个题目类名冲突，在网页提交了

//
//public class MinStack{
//
//	 public static void main(String[] args) {
//	 	 //测试代码
//		 //MinStack solution = new MinStack().new MinStack();
//	 }
//
//class MinStack {
//
//	Deque<Integer> dataStack;// 常规元素栈
//	// 最小元素栈与元素栈同步插入与删除，用于存储与元素栈中每个元素对应的当前栈的最小值。
//	Deque<Integer> minStack;// 与常规数据栈的每个元素对应的当前元素的最小数据栈
//
//    public MinStack() {// 初始化栈
//		dataStack = new LinkedList<Integer>();
//		minStack = new LinkedList<Integer>();
//		minStack.push(Integer.MAX_VALUE);
//    }
//
//	// 最小元素栈与元素栈同步插入
//	// 最小元素栈栈顶元素是元素栈中所有元素的最小值
//    public void push(int val) {
//		dataStack.push(val);
//		minStack.push(Math.min(minStack.peek(), val));// 确定最小元素栈栈顶元素的值然后再入栈
//    }
//
//	//// 最小元素栈与元素栈同步删除
//    public void pop() {
//		dataStack.pop();
//		minStack.pop();
//    }
//
//	// 获取元素栈的栈顶元素并不删除
//    public int top() {
//		return dataStack.peek();
//    }
//
//	// 获取元素栈中当前的最小值，获取最小元素栈的栈顶元素但并不删除该元素
//    public int getMin() {
//		return minStack.peek();
//    }
//}
//
///**
// * Your MinStack object will be instantiated and called as such:
// * MinStack obj = new MinStack();
// * obj.push(val);
// * obj.pop();
// * int param_3 = obj.top();
// * int param_4 = obj.getMin();
// */
//
//
//}
