/**
 * <p>设计一个找到数据流中第 <code>k</code> 大元素的类（class）。注意是排序后的第 <code>k</code> 大元素，不是第 <code>k</code> 个不同的元素。</p>
 *
 * <p>请实现 <code>KthLargest</code> 类：</p>
 *
 * <ul>
 * <li><code>KthLargest(int k, int[] nums)</code> 使用整数 <code>k</code> 和整数流 <code>nums</code> 初始化对象。</li>
 * <li><code>int add(int val)</code> 将 <code>val</code> 插入数据流 <code>nums</code> 后，返回当前数据流中第 <code>k</code> 大的元素。</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * <strong>输出：</strong>
 * [null, 4, 5, 5, 8, 8]
 *
 * <strong>解释：</strong>
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 * </pre>
 *
 * <p> </p>
 * <strong>提示：</strong>
 *
 * <ul>
 * <li><code>1 <= k <= 10<sup>4</sup></code></li>
 * <li><code>0 <= nums.length <= 10<sup>4</sup></code></li>
 * <li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
 * <li><code>-10<sup>4</sup> <= val <= 10<sup>4</sup></code></li>
 * <li>最多调用 <code>add</code> 方法 <code>10<sup>4</sup></code> 次</li>
 * <li>题目数据保证，在查找第 <code>k</code> 大元素时，数组中至少有 <code>k</code> 个元素</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>设计</li><li>二叉搜索树</li><li>二叉树</li><li>数据流</li><li>堆（优先队列）</li
 * ></div></div><br><div><li>👍 396</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.PriorityQueue;

/**
 * 703
 * 数据流中的第 K 大元素
 * @author wangweizhou
 * @date 2022-11-19 19:48:26
 */
public class KthLargestElementInAStream {
    public static void main(String[] args) {
        //测试代码
        // Solution solution = new KthLargestElementInAStream().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class KthLargest {


        // 和215对应起来看
        // 与数据流相关的题目的特点是输入的数据是动态添加的，也就是说，可以不断地从数据流中读取新的数据，数据流的数据量是无限的。
        // 在这个题目中，类型KthLargest的函数add用来添加从数据流中读出的新数据。
        // 第k大的数字。如果能够找出k个最大的数字，那么第k大的数字就是这k个最大数字中最小的一个。


        // 由于每次都需要找出k个数字中的最小值，因此可以把这k个数字保存到最小堆中。
        // 每当从数据流中读出一个数字，就先判断这个新的数字是不是有必要添加到最小堆中。
        // 如果最小堆中元素的数目还小于k，那么直接将它添加到最小堆中。
        // 如果最小堆中已经有k个元素，那么将其和位于堆顶的最小值进行比较。
        // 如果新读出的数字小于或等于堆中的最小值，那么堆中的k个数字都比它大，因此它不可能是k个最大的数字中的一个。
        // 由于只需要保存最大的k个数字，因此新读出的数字可以忽略。
        // 如果新的数字大于堆顶的数字，那么堆顶的数字就是第k+1大的数字，可以将它从堆中删除，并将新的数字添加到堆中，
        // 这样堆中保存的仍然是到目前为止从数据流中读出的最大的k个数字，此时第k大的数字正好位于最小堆的堆顶。



        //// 解法1： 最小堆  中保存数据流中最大的k个数据
        // 最小堆的堆顶是堆中最小的元素，当数据流中的元素大于堆顶元素时，将堆顶元素删除，将数据流中元素加入堆中。
        //// 堆中始终保持已经遍历过的数据中最大的k个元素
        //
        //// Java提供了类型PriorityQueue实现数据结构堆。
        //// PriorityQueue在默认情况下是一个最小堆，如果使用最大堆调用构造函数就需要传入Comparator改变比较排序的规则。


        private PriorityQueue<Integer> minHeap;// 最小堆
        private int size;// 最小堆的容量

        public KthLargest(int k, int[] nums) {// 构造器初始化成员
            size=k;
            minHeap=new PriorityQueue<>(k);
            for (int num:nums) {// 遍历数据流元素将符合条件的元素添加到最小堆中
                add(num);
            }
        }


        public int add(int val) {
            if(minHeap.size()<size){  // 当最小堆中元素的数目还小于k，那么直接将它添加到最小堆中。
                minHeap.add(val);// 将当前元素添加到堆中
            }else {// 如果最小堆中已经有k个元素
                // 如果新读出的数字小于或等于堆中的最小值，那么堆中的k个数字都比它大，因此它不可能是k个最大的数字中的一个。由于只需要保存最大的k个数字，因此新读出的数字可以忽略。
                if(val>minHeap.peek()){ // 如果当前元素大于栈顶元素，那么堆顶元素就是第k+1大的元素，将堆顶元素删除，将新的元素加入到堆中，
                    minHeap.poll();// 将堆顶元素删除
                    minHeap.offer(val);// 将当前元素添加到堆中
                }
            }
            return minHeap.peek();
        }


    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
