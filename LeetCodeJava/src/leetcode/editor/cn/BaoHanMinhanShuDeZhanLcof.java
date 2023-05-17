/**
<p>定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre>MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --&gt; 返回 -3.
minStack.pop();
minStack.top();      --&gt; 返回 0.
minStack.min();   --&gt; 返回 -2.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>各函数的调用总次数不超过 20000 次</li>
</ol>

<p>&nbsp;</p>

<p>注意：本题与主站 155 题相同：<a href="https://leetcode-cn.com/problems/min-stack/">https://leetcode-cn.com/problems/min-stack/</a></p>
<div><div>Related Topics</div><div><li>栈</li><li>设计</li></div></div><br><div><li>👍 389</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;
import java.util.*;

/**
 * 剑指 Offer 30
 * 包含min函数的栈
 * @author wangweizhou
 * @date 2022-09-14 16:16:26
 */
public class BaoHanMinhanShuDeZhanLcof{
	 public static void main(String[] args) {
	 	 //测试代码
         MinStack solution = new BaoHanMinhanShuDeZhanLcof().new MinStack();

	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class MinStack {

    // 看的不太明白
    // 注意这个和主站155不同，这个最小栈里面只保存当前栈的最小值，只保存一个值
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    /** initialize your data structure here. */
    public MinStack() {
        //
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.add(x);
        if(stack2.empty() || stack2.peek() >= x) {
            stack2.add(x);
        }

    }

    public void pop() {
        if(stack1.peek().equals(stack2.peek())) {
            stack2.pop();
        }
        stack1.pop();

    }

    public int top() {
        return stack1.peek();
    }

    public int min() {
        return stack2.peek();
    }



    ///** initialize your data structure here. */
    //Deque<Integer> dataStack;// 常规元素栈
    //// 最小元素栈与元素栈同步插入与删除，用于存储与元素栈中每个元素对应的当前栈的最小值。
    //Deque<Integer> minStack;// 与常规数据栈的每个元素对应的当前元素的最小数据栈
    //
    //public MinStack() {// 初始化栈
    //    dataStack = new LinkedList<Integer>();
    //    minStack = new LinkedList<Integer>();
    //
    //}
    //
    //// 最小元素栈与元素栈同步插入
    //// 最小元素栈栈顶元素是元素栈中所有元素的最小值
    //public void push(int val) {
    //    dataStack.push(val);
    //    if(minStack.isEmpty() || minStack.peek() >= val) {
    //        minStack.add(val);
    //    }
    //}
    //
    ////// 最小元素栈与元素栈同步删除
    //public void pop() {
    //    if(dataStack.pop().equals(minStack.peek())) {
    //        minStack.pop();
    //    }
    //}
    //
    //// 获取元素栈的栈顶元素并不删除
    //public int top() {
    //    return dataStack.peek();
    //}
    //
    //// 获取元素栈中当前的最小值，获取最小元素栈的栈顶元素但并不删除该元素
    //public int getMin() {
    //    return minStack.peek();
    //}
    //


}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
