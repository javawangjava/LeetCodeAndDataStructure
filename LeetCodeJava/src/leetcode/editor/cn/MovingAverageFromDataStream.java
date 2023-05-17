/**
 * <p>给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。</p>
 *
 * <p>实现 <code>MovingAverage</code> 类：</p>
 *
 * <ul>
 * <li><code>MovingAverage(int size)</code> 用窗口大小 <code>size</code> 初始化对象。</li>
 * <li><code>double next(int val)</code> 计算并返回数据流中最后 <code>size</code> 个值的移动平均值。</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * <strong>输出：</strong>
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * <strong>解释：</strong>
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // 返回 1.0 = 1 / 1
 * movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= size <= 1000</code></li>
 * <li><code>-10<sup>5</sup> <= val <= 10<sup>5</sup></code></li>
 * <li>最多调用 <code>next</code> 方法 <code>10<sup>4</sup></code> 次</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>设计</li><li>队列</li><li>数组</li><li>数据流</li></div></div><br><div><li>👍
 * 94</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 346
 * 数据流中的移动平均值
 *
 * @author wangweizhou
 * @date 2022-11-15 22:04:32
 */

public class MovingAverageFromDataStream {

    public static void main(String[] args) {
        //测试代码
        //Solution solution = new MovingAverageFromDataStream().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MovingAverage {


        // 按照题目的描述，可以在窗口中添加数字，当窗口中数字的数目超过限制时，还可以从窗口中删除数字。
        // 题目给出的例子中的删除规则是把最早添加进来的数字删除，因此这是一种“先入先出”的顺序，由此想到应该采用队列这种数据结构来表示滑动窗口。
        // 可以把数字添加到队列的尾部，并从队列的头部删除数字。

        // 自然需要保存窗口的大小限制。每当在窗口中添加一个数字之后，都需要判断是否超出了窗口的大小限制。
        // 如果超出了限制，就需要从队列中删除一个数字。
        // 如果记录当前窗口中的所有数字之和（用sum表示），那么插入一个新的数字v1之后，窗口中的所有数字之和就是sum+v1。
        // 如果此时窗口的大小超出了限制，还需要删除一个数字v2，那么窗口中的所有数字之和是sum+v1-v2。
        // 因此，最多只需要一次加法和一次减法就能求出窗口中的所有数字之和。



        // 解法1：队列模拟滑动窗口  写法2
        private Deque<Integer> nums;//
        private int capacity;
        private int sum;

        public MovingAverage(int size) {// 初始化队列和滑动窗口的容量
            capacity = size;
            nums = new ArrayDeque<>();
        }

        public double next(int val) {
            if (nums.size() == capacity) {// 如果当前滑动窗口中的元素个数等于指定的滑动窗口大小,移出队列中最早进入滑动窗口的元素
                sum -= nums.poll();//
            }
            // 将新加入的元素加入滑动窗口，累加该元素的和
            nums.offer(val);
            sum += val;
            return (double) sum / nums.size();// 返回滑动窗口的平均值
        }




        //// 解法1：队列模拟滑动窗口  写法1
        //private Deque<Integer> nums;// 用队列来模拟滑动窗口
        //private int capacity;// 滑动窗口的大小限制
        //private int sum;// 滑动窗口中的元素和
        //
        //public MovingAverage(int size) {// 构造器初始化队列和滑动窗口的大小
        //	nums=new ArrayDeque<>();
        //	capacity=size;
        //}
        //
        //// 每次调用next(int val)表示在队列中插入val,并返回滑动窗口的平均值
        //public double next(int val) {
        //	nums.offer(val);// 在窗口中添加一个数字
        //	sum+=val;// 如果记录当前窗口中的所有数字之和（用sum表示），那么插入一个新的数字v1之后，窗口中的所有数字之和就是sum+v1。
        //	if(nums.size()>capacity){// 如果此时窗口的大小超出了限制，还需要删除一个数字v2，那么窗口中的所有数字之和是sum+v1-v2。
        //		sum-=nums.poll();
        //	}
        //	return (double) sum/nums.size();
        //}




        //// 解法2：用数组实现滑动窗口
        //private int[] nums = new int[10010];
        //private int capacity;
        //private int left = 0, right = 0;
        //private int sum;
        //
        //public MovingAverage(int size) {// 构造器size表示对象的容量
        //    capacity = size;
        //}
        //
        //
        //// 每次调用next(int val)表示在队列中插入val,并返回滑动窗口的平均值
        //public double next(int val) {
        //    // 元素进入滑动窗口，更新滑动窗口的元素和和滑动窗口右边界
        //    sum += val;
        //    nums[right] = val;
        //    right++;
        //    if (right - left > capacity) {// 加入元素后超出容量
        //        sum -= nums[left];
        //        left++;
        //    }
        //    return sum * 1.0 / (right - left);// 获取滑动窗口平均值
        //}
        //


    }

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */

//leetcode submit region end(Prohibit modification and deletion)

}
