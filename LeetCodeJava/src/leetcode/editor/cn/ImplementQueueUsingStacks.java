/**
 * <p>请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（<code>push</code>、<code>pop</code>、<code>peek</code>、<code>empty</code>）：</p>
 *
 * <p>实现 <code>MyQueue</code> 类：</p>
 *
 * <ul>
 * <li><code>void push(int x)</code> 将元素 x 推到队列的末尾</li>
 * <li><code>int pop()</code> 从队列的开头移除并返回元素</li>
 * <li><code>int peek()</code> 返回队列开头的元素</li>
 * <li><code>boolean empty()</code> 如果队列为空，返回 <code>true</code> ；否则，返回 <code>false</code></li>
 * </ul>
 *
 * <p><strong>说明：</strong></p>
 *
 * <ul>
 * <li>你 <strong>只能</strong> 使用标准的栈操作 —— 也就是只有&nbsp;<code>push to top</code>,&nbsp;<code>peek/pop from top</code>,
 * &nbsp;<code>size</code>, 和&nbsp;<code>is empty</code>&nbsp;操作是合法的。</li>
 * <li>你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * <strong>输出：</strong>
 * [null, null, null, 1, 1, false]
 *
 * <strong>解释：</strong>
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 * </pre>
 *
 * <ul>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= x &lt;= 9</code></li>
 * <li>最多调用 <code>100</code> 次 <code>push</code>、<code>pop</code>、<code>peek</code> 和 <code>empty</code></li>
 * <li>假设所有操作都是有效的 （例如，一个空的队列不会调用 <code>pop</code> 或者 <code>peek</code> 操作）</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>你能否实现每个操作均摊时间复杂度为 <code>O(1)</code> 的队列？换句话说，执行 <code>n</code> 个操作的总时间复杂度为 <code>O(n)</code>
 * ，即使其中一个操作可能花费较长时间。</li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>栈</li><li>设计</li><li>队列</li></div></div><br><div><li>👍 842</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 232
 * 用栈实现队列
 * @author wangweizhou
 * @date 2023-02-24 15:31:22
 */

public class ImplementQueueUsingStacks {

    public static void main(String[] args) {
        //测试代码
        //Solution solution = new ImplementQueueUsingStacks().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MyQueue {


        // 题干并没有说明队列为空时如何处理，下面约定不额外处理。
        // 两次先进后出就转换成先进先出了。

        private Deque<Integer> stack1;// 栈1中保存入队的元素，
        private Deque<Integer> stack2;// 栈2中保存要出队的元素

        public MyQueue() {
            stack1 = new LinkedList<>();
            stack2 = new LinkedList<>();
        }


        public void push(int x) {
            stack1.push(x);
        }


        public int pop() {
            if (stack2.size() == 0) {
                while (stack1.size() > 0) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }


        public int peek() {
            if (stack2.size() == 0) {
                while (stack1.size() > 0) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.peek();
        }


        public boolean empty() {
            if (stack1.size() == 0 && stack2.size() == 0) {
                return true;
            } else {
                return false;
            }
        }


    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
