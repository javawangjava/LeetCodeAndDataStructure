/**
 * <p>请定义一个队列并实现函数 <code>max_value</code> 得到队列里的最大值，要求函数<code>max_value</code>、<code>push_back</code> 和
 * <code>pop_front</code> 的<strong>均摊</strong>时间复杂度都是O(1)。</p>
 *
 * <p>若队列为空，<code>pop_front</code> 和 <code>max_value</code>&nbsp;需要返回 -1</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入:</strong>
 * [&quot;MaxQueue&quot;,&quot;push_back&quot;,&quot;push_back&quot;,&quot;max_value&quot;,&quot;pop_front&quot;,&quot;max_value&quot;]
 * [[],[1],[2],[],[],[]]
 * <strong>输出:&nbsp;</strong>[null,null,null,2,1,2]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入:</strong>
 * [&quot;MaxQueue&quot;,&quot;pop_front&quot;,&quot;max_value&quot;]
 * [[],[],[]]
 * <strong>输出:&nbsp;</strong>[null,-1,-1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= push_back,pop_front,max_value的总操作数&nbsp;&lt;= 10000</code></li>
 * <li><code>1 &lt;= value &lt;= 10^5</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>设计</li><li>队列</li><li>单调队列</li></div></div><br><div><li>👍 413</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 面试题59 - II
 * 队列的最大值
 *
 * @author wangweizhou
 * @date 2022-09-14 18:58:49
 */

public class DuiLieDeZuiDaZhiLcof {

    public static void main(String[] args) {
        //测试代码
        MaxQueue solution = new DuiLieDeZuiDaZhiLcof().new MaxQueue();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MaxQueue {


        // 该数据结构包括一个数据队列和一个与数据队列对应的最大值队列。两个数据结构组合起来形成一个新定义的数据结构，所以对于数据的操作应该同步。
        // 因为数据队列queue是普通存储数据的队列结构，也就是数据从队尾进入，从队首弹出。
        // 数据队列对应的最大值队列是双向队列deque，双向队列的队首保存队列中的最大值，双向队列的队尾保存队列中的最小值。
        // 最大值队列【双端队列】deque是与数据队列同步的数据结构，双端队列的队首保存当前数据队列中的最大值，也是每次从双端队列的尾部加入元素。
        // 那么访问队列当前的最大值元素时，只需要访问双端队列的头部元素即可；
        // 将数据加入队列时，两个队列一起操作，数据队列从尾部加入元素，最大值队列的队尾元素小于待加入元素，
        // 则将最大值队列队尾元素小于待加入元素的元素出队，直到最大值队列中队尾元素大于待加入元素，则将待加入元素加入到最大值队列的尾部。


        private Queue<Integer> queue;// 数据队列
        private Deque<Integer> maxDeque;// 数据队列对应的最大值队列

        public MaxQueue() {// 构造器初始化队列
            queue = new LinkedList<>();
            maxDeque = new LinkedList<>();
        }


        // max_value()获取队列中的最大值
        // 获取当前数据结构的最大值，那么就要从双端队列的头部获取
        public int max_value() {
            // 双端队列的队首元素就是队列目前的最大值，这里只是获取双端队列的头部元素，但是不会弹出队列的头部元素
            return maxDeque.isEmpty() ? -1 : maxDeque.peekFirst();
        }


        // push_back(int value)将数据value加入到数据结构中，
        // 两个容器要同时操作，普通队列queue中正常加入，双端队列中要先检查队尾元素与当前的待加入元素的大小。
        // 因为双端队列中保存当前队列中的最大元素，那么当双端队列的尾部元素小于待加入元素时，要将双端队列的尾部较小的元素全部出队。其实也就是要将最大值队列中的元素是倒序排序的
        public void push_back(int value) {
            queue.offer(value);// 普通的数据队列中加入数据
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < value) {
                // 最大值队列不空并且最大值队列的队尾元素小于待加入元素，将最大值队列【双端队列】的尾部元素弹出
                maxDeque.pollLast();
            }
            // 上面while循环结束，那么就将当前待加入元素加入到最大值队列的尾部
            maxDeque.offerLast(value);// 将待加入元素加入到最大值队列的尾部
        }


        // pop_front()从数据队列头部弹出元素
        // 从数据容器中弹出头部元素。两个容器要一起操作，弹出普通队列头部元素的时候要检查普通队列头部和最大值队列头部是否相同，相同的话要最大值队列要同步操作。
        public int pop_front() {
            if (queue.isEmpty()) {// 队列为空，返回-1。
                return -1;
            }
            // 当数据队列出队的元素和最大数据队列的队首元素相同时，最大数据队列元素队首元素出队
            //if(queue.peek().equals(deque.peekFirst())){
            if (maxDeque.size() > 0 && maxDeque.peekFirst().equals(queue.peek())) {
                maxDeque.pollFirst();
            }
            return queue.poll();
        }


    }

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */

//leetcode submit region end(Prohibit modification and deletion)

}
