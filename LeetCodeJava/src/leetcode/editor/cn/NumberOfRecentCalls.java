/**
 * <p>写一个&nbsp;<code>RecentCounter</code>&nbsp;类来计算特定时间范围内最近的请求。</p>
 *
 * <p>请你实现 <code>RecentCounter</code> 类：</p>
 *
 * <ul>
 * <li><code>RecentCounter()</code> 初始化计数器，请求数为 0 。</li>
 * <li><code>int ping(int t)</code> 在时间 <code>t</code> 添加一个新请求，其中 <code>t</code> 表示以毫秒为单位的某个时间，并返回过去
 * <code>3000</code> 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 <code>[t-3000, t]</code> 内发生的请求数。</li>
 * </ul>
 *
 * <p><strong>保证</strong> 每次对 <code>ping</code> 的调用都使用比之前更大的 <code>t</code> 值。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * <strong>输出：</strong>
 * [null, 1, 2, 3, 3]
 *
 * <strong>解释：</strong>
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [<strong>1</strong>]，范围是 [-2999,1]，返回 1
 * recentCounter.ping(100);   // requests = [<strong>1</strong>, <strong>100</strong>]，范围是 [-2900,100]，返回 2
 * recentCounter.ping(3001);  // requests = [<strong>1</strong>, <strong>100</strong>, <strong>3001</strong>]，范围是 [1,3001]，返回 3
 * recentCounter.ping(3002);  // requests = [1, <strong>100</strong>, <strong>3001</strong>, <strong>3002</strong>]，范围是 [2,3002]，返回 3
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= t &lt;= 10<sup>9</sup></code></li>
 * <li>保证每次对 <code>ping</code> 调用所使用的 <code>t</code> 值都 <strong>严格递增</strong></li>
 * <li>至多调用 <code>ping</code> 方法 <code>10<sup>4</sup></code> 次</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>设计</li><li>队列</li><li>数据流</li></div></div><br><div><li>👍 204</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 933
 * 最近的请求次数
 *
 * @author wangweizhou
 * @date 2022-11-16 10:22:04
 */
public class NumberOfRecentCalls {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new NumberOfRecentCalls().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class RecentCounter {


        // 在ping（1）、ping（10）、ping（3001）发生时，先后将时间1、10、3001记录到一个数据容器中。
        // 接下来发生了ping（3002），此时时间1已经超出当前的时间范围，时间1发生的请求不被计数，因此时间1需要从数据容器中删除。
        // 事实上，可以将某个时间范围的所有时间看成一个关于时间的滑动窗口。每当一个新的请求发生时，该滑动窗口包含一个新的时间。
        // 如果某个时间由于太早而超出了时间范围，那么它将滑出该时间窗口。队列非常适合用来实现滑动窗口。
        // 每当请求ping在时间t发生时，时间t就被记录到队列times中。如果之前的某些请求的时间已经滑出了目前的时间窗口，则将它们从队列中删除。队列的长度就是当前时间窗口内请求的数目。


        // 函数ping（int t）在时间t添加一个新请求（t表示以毫秒为单位的时间），并返回过去3000ms内（时间范围为[t-3000，t]）发生的所有请求数。
        // 队列中保存的是请求时的时间点。



        // 解法1：滑动窗口
        //private Deque<Integer> times;// 队列用来存储数据，注意队列中保存的是请求发生的时间点。
        //private int windowSize;// 滑动窗口的大小

        //public RecentCounter() {// 构造器初始化计数器，滑动窗口长度为3000
        //    times = new LinkedList<>();
        //    windowSize = 3000;
        //}
        //
        //
        //public int ping(int t) {
        //    times.offer(t);// 元素加入到队列尾部
        //    // 队列的实际长度：t - times.peek()
        //    while (windowSize < t - times.peek()) {// 队列长度超过滑动窗口大小，队列头部元素出队
        //        times.poll();
        //    }
        //    return times.size();
        //}




        // 解法2：数组实现滑动窗口
        private int[] times;
        private int windowSize;
        private int left = 0, right = -1;

        public RecentCounter() {// 构造器初始化计数器，滑动窗口长度为3000
            times = new int[10010];
            windowSize = 3000;
        }

        public int ping(int t) {
            right++;
            times[right] = t;
            while (times[right] - times[left] > windowSize) {
                left++;
            }
            return right - left + 1;
        }


    }

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */

//leetcode submit region end(Prohibit modification and deletion)

}
