/**
 * <p>设计一个支持 <code>push</code> ，<code>pop</code> ，<code>top</code> 操作，并能在常数时间内检索到最小元素的栈。</p>
 *
 * <p>实现 <code>MinStack</code> 类:</p>
 *
 * <ul>
 * <li><code>MinStack()</code> 初始化堆栈对象。</li>
 * <li><code>void push(int val)</code> 将元素val推入堆栈。</li>
 * <li><code>void pop()</code> 删除堆栈顶部的元素。</li>
 * <li><code>int top()</code> 获取堆栈顶部的元素。</li>
 * <li><code>int getMin()</code> 获取堆栈中的最小元素。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * <strong>输出：</strong>
 * [null,null,null,null,-3,null,0,-2]
 *
 * <strong>解释：</strong>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --&gt; 返回 -3.
 * minStack.pop();
 * minStack.top();      --&gt; 返回 0.
 * minStack.getMin();   --&gt; 返回 -2.
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>-2<sup>31</sup>&nbsp;&lt;= val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
 * <li><code>pop</code>、<code>top</code> 和 <code>getMin</code> 操作总是在 <strong>非空栈</strong> 上调用</li>
 * <li><code>push</code>,&nbsp;<code>pop</code>,&nbsp;<code>top</code>, and&nbsp;<code>getMin</code>最多被调用&nbsp;
 * <code>3 * 10<sup>4</sup></code>&nbsp;次</li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>栈</li><li>设计</li></div></div><br><div><li>👍 1471</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 155
 * 最小栈
 * @author wangweizhou
 * @date 2022-12-18 17:36:46
 */

public class MinStack {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new MinStackFunc().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

// 只能在网站提交，这里类名重复

// 类似单调栈，最小栈每一个位置要保存与数据栈中从栈底到数据栈当前位置的元素中最小的元素。注意只要数据栈有进出栈，那么最小栈一定要同步变化。
// 每次都把最小栈和待压入元素中的较小值压入辅助栈，那么就能保证辅助栈的栈顶一直都是最小元素。
// 当最小元素从数据栈内被弹出之后，同时弹出辅助栈的栈顶元素，此时辅助栈的新栈顶元素就是下一个最小值。

    //
    //class MinStack {
    //    // 解题思路：
    //    // 常规元素栈：就是普通栈
    //    // 最小元素栈：保存与常规元素栈当前元素对应的常规元素栈中的最小值
    //    // 在构造器中初始化栈。对于数据栈和最小数据栈的操作应该同步。
    //    // push(int val)入栈操作，对于数据栈直接入栈就可以；对于最小栈要比较当前元素和最小栈栈顶元素的大小，将较小值入栈。
    //    // pop()出栈：将数据栈和最小栈的栈顶元素弹出
    //    // top():获取数据栈的栈顶元素
    //    // getMin()：获取最小栈的栈顶元素
    //
    //
    //    // 写法1： 这里采用在最小栈中预存不可能的上限
    //    // 最小元素栈与元素栈同步插入与删除，定义的新的数据结构要同步进行处理
    //    private Deque<Integer> stack;// 定义常规元素栈
    //    private Deque<Integer> minStack;// 定义最小数据栈：与常规数据栈的每个元素对应的当前元素的最小数据栈
    //
    //    public MinStack() {// 初始化栈
    //        stack = new LinkedList<>();
    //        minStack = new LinkedList<>();
    //        minStack.push(Integer.MAX_VALUE);// 注意这里一定要向最小栈中加入一个上限，这样才能在第一次向栈中加入元素时进行比较获得目前的较小值。
    //        // 或者第一次向空栈中加入时先判栈是否为空，也可以进行处理。
    //    }
    //
    //    // 最小元素栈与元素栈同步插入
    //    // 最小元素栈栈顶元素是元素栈中所有元素的最小值
    //    public void push(int val) {
    //        stack.push(val);
    //        // 新加入数据栈的元素和最小栈的栈顶元素中的较小数是要加入最小栈的元素// 确定最小元素栈栈顶元素的值然后再入栈
    //        if (minStack.peek() > val) {
    //            minStack.push(val);
    //        } else {
    //            minStack.push(minStack.peek());
    //        }
    //    }
    //
    //    //// 最小元素栈与元素栈同步插入 // 写法2：下面时最小栈为空时，进行判断
    //    //// 最小元素栈栈顶元素是元素栈中所有元素的最小值
    //    //public void push(int val) {
    //    //	stack.push(val);
    //    //	if(minStack.size()==0){
    //    //		minStack.push(val);
    //    //	}else{
    //    //		// 新加入数据栈的元素和最小栈的栈顶元素中的较小数是要加入最小栈的元素// 确定最小元素栈栈顶元素的值然后再入栈
    //    //		if(minStack.peek()>val){
    //    //			minStack.push(val);
    //    //		}else {
    //    //			minStack.push(minStack.peek());
    //    //		}
    //    //	}
    //    //}
    //
    //    // 最小元素栈与元素栈同步删除
    //    public void pop() {
    //        stack.pop();// 弹出数据栈的栈顶元素
    //        minStack.pop();// 弹出数据栈的栈顶元素
    //    }
    //
    //    // 获取元素栈的栈顶元素并不删除
    //    public int top() {
    //        return stack.peek();
    //    }
    //
    //    // 获取元素栈中当前的最小值，获取最小元素栈的栈顶元素但并不删除该元素
    //    public int getMin() {
    //        return minStack.peek();
    //    }
    //}


//leetcode submit region end(Prohibit modification and deletion)

}


