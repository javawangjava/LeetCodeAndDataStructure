/**
 * <p>给你一个整数数组 <code>nums</code>，有一个大小为&nbsp;<code>k</code><em>&nbsp;</em>的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的
 * <code>k</code>&nbsp;个数字。滑动窗口每次只向右移动一位。</p>
 *
 * <p>返回 <em>滑动窗口中的最大值 </em>。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <b>输入：</b>nums = [1,3,-1,-3,5,3,6,7], k = 3
 * <b>输出：</b>[3,3,5,5,6,7]
 * <b>解释：</b>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       <strong>3</strong>
 * 1 [3  -1  -3] 5  3  6  7       <strong>3</strong>
 * 1  3 [-1  -3  5] 3  6  7      <strong> 5</strong>
 * 1  3  -1 [-3  5  3] 6  7       <strong>5</strong>
 * 1  3  -1  -3 [5  3  6] 7       <strong>6</strong>
 * 1  3  -1  -3  5 [3  6  7]      <strong>7</strong>
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <b>输入：</b>nums = [1], k = 1
 * <b>输出：</b>[1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><b>提示：</b></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= k &lt;= nums.length</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>队列</li><li>数组</li><li>滑动窗口</li><li>单调队列</li><li>堆（优先队列）</li></div></div><br
 * ><div><li>👍 1861</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 239
 * 滑动窗口最大值
 *
 * @author wangweizhou
 * @date 2022-09-14 17:51:44
 */

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new SlidingWindowMaximum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 参照面试题 59-2 队列的最大值

        // 思路：双端队列实现单调栈：双向队列用来保存有可能是滑动窗口最大值的数字的下标。双端队列从队首到队尾是按照从大到小的顺序排序的。
        // 滑动窗口对应的最大值队列是双向队列deque，双向队列的队首保存队列中的最大值，双向队列的队尾保存队列中的最小值。
        // 最大值队列【双端队列】deque是与滑动窗口同步的数据结构，双端队列的队首保存当前数据队列中的最大值，也是每次从双端队列的尾部加入元素。

        // 在代码中，deque是一个两端开口的队列，用来保存有可能是滑动窗口最大值的数字的下标。双端队列从队首到队尾是按照从大到小的顺序排序的。
        // 应该在队列里存入数字在数组里的下标，而不是数值【通过数组下标可以直接访问数组元素，而且这里需要通过数组下标来确定元素是否已经移出了滑动窗口】。

        // 0.将数据加入滑动窗口时， 在双端队列中存入一个数字的下标之前，首先要判断双端队列里已有数字是否小于待存入的数字。
        // 1.如果双端队列中已有的数字大于待存入的数字，那么将待存入数字加入到双端队列的尾部。
        // 2.如果双端队列中已有的数字小于待存入的数字，那么这些数字已经不可能是滑动窗口的最大值，因此它们将会被依次从双端队列的尾部删除。
        // 3.如果待存入的数字大于双端队列中所有值，则删除双端队列中所有的值，然后把新增值放到队列首位，保证队列一直是从大到小。

        // 当最大值队列中一个数字的下标与当前处理的数字的下标之差大于或者等于滑动窗口的大小时，这个数字己经从窗口中滑出，可以从队列中删除了。
        // 同时，如果队列头部的数字已经从窗口里滑出，那么滑出的数字也需要从队列的头部删除。
        // 由于队列的头部和尾部都有可能删除数字，这也是需要两端开口的队列的原因。


        //// 滑动窗口是使用指针遍历实现的，也就是滑动窗口左右边界指针来模拟滑动窗口【假想的】

        // 使用双端队列来实现单调栈。从队首到队尾是单调递减的。

        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return new int[0];
            }
            int[] arr = new int[nums.length - k + 1];
            Deque<Integer> queue = new LinkedList<>();
            for (int i = 0; i < k; i++) {
                while (queue.size() > 0 && queue.peekLast() < nums[i]) {
                    queue.pollLast();
                }
                queue.offerLast(nums[i]);
            }

            int index = 0;
            arr[index++] = queue.peekFirst();

            for (int i = k; i < nums.length; i++) {
                if (queue.peekFirst() == nums[i - k]) {
                    queue.pollFirst();
                }
                while (queue.size() > 0 && queue.peekLast() < nums[i]) {
                    queue.pollLast();
                }
                queue.offerLast(nums[i]);
                arr[index++] = queue.peekFirst();
            }
            return arr;
        }




        //public int[] maxSlidingWindow(int[] nums, int k) {
        //    if (nums == null || nums.length == 0 || nums.length < k) {
        //        return nums;
        //    }
        //    Deque<Integer> deque = new LinkedList<>();// 双端队列中保存有可能成为滑动窗口最大值的数值
        //    int[] arr = new int[nums.length - k + 1];// 保存滑动窗口中最大值的数组arr
        //    int index = 0;  // 保存滑动窗口中最大值的数组arr的遍历指针
        //
        //    // 滑动窗口长度为k，当滑动窗口没有装满时，数组nums的元素逐渐进入队列
        //    for (int i = 0; i < k; i++) {// 滑动窗口遍历指针i右移，当滑动窗口遍历指针指向（i-1）时，滑动窗口形成。
        //        // 滑动窗口对应的最大值队列是双向队列deque，双向队列的队首保存队列中的最大值，双向队列的队尾保存队列中的最小值。
        //        // 当最大值队列不为空且当前元素大于队尾元素，则当前元素可能成为滑动窗口的最大值；
        //        // 一直循环删除直到队列中的值都大于当前值，或者删到队列为空。
        //        while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
        //            deque.removeLast();
        //        }
        //        // 执行完上面的循环后，队列中要么为空，要么队列中的值都比当前值大，然后就把当前值添加到队列中
        //        deque.addLast(nums[i]);
        //    }
        //
        //
        //    // 滑动窗口中第一次有k个元素了，滑动窗口形成后，双端队列的队首元素就是当前滑动窗口的最大值，将双端队列的队首元素添加到数组中
        //    arr[index++] = deque.peekFirst();// 窗口第一次填满后，双端队列的队首元素就是第一个滑动窗口中的最大值
        //
        //    // 第一个滑动窗口[0,k-1]。窗口区间形成后，遍历指针i向右每移动一位，那么该滑动窗口整体向右移动一位，这时候右边有新的元素进入队列，左边有元素移出。
        //    for (int i = k; i < nums.length; i++) {
        //        // 这里i从K开始，i-k 表示nums[i - k]已经在滑动窗口外了，如果双端队列的首位等于nums[i-k]，那么说明此时首位值已经不再区间内了，需要删除
        //        // 滑动窗口左侧移出元素时，需要判断该移出的元素是否是队首元素
        //        if (deque.peekFirst() == nums[i - k]) {// 当从滑动窗口左端移出的元素等于双端队列的队首元素时，那么双端队列的队首元素要移出。
        //            deque.removeFirst();
        //        }
        //        // 滑动窗口右侧移入元素时，需要判断该移入的元素是否比最大值队列中的队尾元素大。
        //        // 当队列不为空且当前元素大于队尾元素，则当前元素可能成为滑动窗口的最大值；一直循环删除直到队列中的值都大于当前值，或者删到队列为空。
        //        while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
        //            deque.removeLast();
        //        }
        //        // 执行完上面的循环后，队列中要么为空，要么队列中的值都比当前值大，然后就把当前值添加到队列中
        //        deque.addLast(nums[i]);
        //        //把队列的首位值添加到arr数组中
        //        arr[index++] = deque.peekFirst();// 每次新形成一个新的滑动窗口时，就将最大值队列的队首元素加入到数组最大值队列中。
        //    }
        //    return arr;
        //}
        //
        //


        ////	 没看懂
        //// 方法1：优先队列
        //public int[] maxSlidingWindow(int[] nums, int k) {
        //	int n = nums.length;
        //	PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
        //		public int compare(int[] pair1, int[] pair2) {
        //			return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
        //		}
        //	});
        //
        //	// 将数组的前k个加入到优先队列中
        //	for (int i = 0; i < k; ++i) {
        //		pq.offer(new int[]{nums[i], i});
        //	}
        //
        //	int[] ans = new int[n - k + 1];
        //	ans[0] = pq.peek()[0];
        //	for (int i = k; i < n; ++i) {
        //		pq.offer(new int[]{nums[i], i});
        //		while (pq.peek()[1] <= i - k) {
        //			pq.poll();
        //		}
        //		ans[i - k + 1] = pq.peek()[0];
        //	}
        //	return ans;
        //}


        //public int[] maxSlidingWindow(int[] nums, int k) {
        //	int[] res = new int[nums.length + 1 - k];
        //	Deque<Integer> queue = new LinkedList();
        //	for (int i = 0; i < nums.length; i++){
        //		// 后面小于改成小于等于也可以，之前的写法是小于等于
        //		// 有等于的时候，提前让栈内相同的数出栈，就不会在判断queue.peek() < L 时出栈了
        //		// 没有等于写法更容易理解
        //		while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]){
        //			queue.pollLast();
        //		}
        //		queue.offerLast(i);
        //		// [L,R]实际上就是[i+1-k,i]，判断队首元素的位置是否小于L，之前的写法不容易理解
        //		if (queue.peek() < i + 1 - k){
        //			queue.poll();
        //		}
        //		if (i + 1 >= k){
        //			res[i + 1 - k] = nums[queue.peek()];
        //		}
        //	}
        //	return res;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
