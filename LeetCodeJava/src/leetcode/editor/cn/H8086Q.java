/**
<p>写一个&nbsp;<code>RecentCounter</code>&nbsp;类来计算特定时间范围内最近的请求。</p>

<p>请实现 <code>RecentCounter</code> 类：</p>

<ul>
	<li><code>RecentCounter()</code> 初始化计数器，请求数为 0 。</li>
	<li><code>int ping(int t)</code> 在时间 <code>t</code> 添加一个新请求，其中 <code>t</code> 表示以毫秒为单位的某个时间，并返回过去 <code>3000</code> 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 <code>[t-3000, t]</code> 内发生的请求数。</li>
</ul>

<p><strong>保证</strong> 每次对 <code>ping</code> 的调用都使用比之前更大的 <code>t</code> 值。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
inputs = [&quot;RecentCounter&quot;, &quot;ping&quot;, &quot;ping&quot;, &quot;ping&quot;, &quot;ping&quot;]
inputs = [[], [1], [100], [3001], [3002]]
<strong>输出：</strong>
[null, 1, 2, 3, 3]

<strong>解释：</strong>
RecentCounter recentCounter = new RecentCounter();
recentCounter.ping(1);     // requests = [<strong>1</strong>]，范围是 [-2999,1]，返回 1
recentCounter.ping(100);   // requests = [<strong>1</strong>, <strong>100</strong>]，范围是 [-2900,100]，返回 2
recentCounter.ping(3001);  // requests = [<strong>1</strong>, <strong>100</strong>, <strong>3001</strong>]，范围是 [1,3001]，返回 3
recentCounter.ping(3002);  // requests = [1, <strong>100</strong>, <strong>3001</strong>, <strong>3002</strong>]，范围是 [2,3002]，返回 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= t &lt;= 10<sup>9</sup></code></li>
	<li>保证每次对 <code>ping</code> 调用所使用的 <code>t</code> 值都 <strong>严格递增</strong></li>
	<li>至多调用 <code>ping</code> 方法 <code>10<sup>4</sup></code> 次</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 933&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/number-of-recent-calls/">https://leetcode-cn.com/problems/number-of-recent-calls/</a></p>
<div><div>Related Topics</div><div><li>设计</li><li>队列</li><li>数据流</li></div></div><br><div><li>👍 30</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer II 042
 * 最近请求次数
 * @author wangweizhou
 * @date 2022-11-15 22:18:54
 */
public class H8086Q{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 //Solution solution = new H8086Q().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class RecentCounter {

	//    解法2：数组实现滑动窗口
	private int[] times = new int[10010];
	private int windowSize;
	private int left = 0, right = -1;

	public RecentCounter() {// 构造器初始化计数器，滑动窗口长度为3000
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
