/**
 * <p><strong>中位数</strong>是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。</p>
 *
 * <ul>
 * <li>例如 <code>arr = [2,3,4]</code>&nbsp;的中位数是 <code>3</code>&nbsp;。</li>
 * <li>例如&nbsp;<code>arr = [2,3]</code> 的中位数是 <code>(2 + 3) / 2 = 2.5</code> 。</li>
 * </ul>
 *
 * <p>实现 MedianFinder 类:</p>
 *
 * <ul>
 * <li> <p><code>MedianFinder() </code>初始化 <code>MedianFinder</code>&nbsp;对象。</p> </li>
 * <li> <p><code>void addNum(int num)</code> 将数据流中的整数 <code>num</code> 添加到数据结构中。</p> </li>
 * <li> <p><code>double findMedian()</code> 返回到目前为止所有元素的中位数。与实际答案相差&nbsp;<code>10<sup>-5</sup></code>&nbsp;
 * 以内的答案将被接受。</p> </li>
 * </ul>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入</strong>
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * <strong>输出</strong>
 * [null, null, null, 1.5, null, 2.0]
 *
 * <strong>解释</strong>
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0</pre>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>-10<sup>5</sup>&nbsp;&lt;= num &lt;= 10<sup>5</sup></code></li>
 * <li>在调用 <code>findMedian</code>&nbsp;之前，数据结构中至少有一个元素</li>
 * <li>最多&nbsp;<code>5 * 10<sup>4</sup></code>&nbsp;次调用&nbsp;<code>addNum</code>&nbsp;和&nbsp;
 * <code>findMedian</code></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>设计</li><li>双指针</li><li>数据流</li><li>排序</li><li>堆（优先队列）</li></div></div><br
 * ><div><li>👍 784</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295
 * 数据流的中位数
 *
 * @author wangweizhou
 * @date 2022-12-23 23:18:17
 */

public class FindMedianFromDataStream {

    public static void main(String[] args) {

        //测试代码
        //Solution solution = new FindMedianFromDataStream().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MedianFinder {


        // 如果数据在容器中已经排序，那么中位数可以由P1和P2指向的数得到。
        // 如果容器中数据的数目是奇数，那么P1和P2指向同一个数据。如果容器中数据的数目是偶数，那么中位数就是P1和P2的平均数。
        // 我们注意到整个数据容器被分隔成两部分。位于容器左边部分的所有数据比右边的所有数据小。
        // 另外，P1指向的数据是左边部分最大的数，P2指向的数据是右边部分最小的数。
        // 如果能够保证数据容器左边的数据都小于右边的数据，那么即使左、右两边内部的数据没有排序，也可以根据左边最大的数及右边最小的数得到中位数。


        // 如何快速从一个数据容器中找出最大数？用最大堆实现这个数据容器，因为位于堆顶的就是最大的数据。同样，也可以快速从最小堆中找出最小数。
        // 因此，可以用如下思路来解决这个问题：用一个最大堆实现左边的数据容器，用一个最小堆实现右边的数据容器。往堆中插入一个数据的时间效率是O(logN)。
        // 由于只需要O(1)时间就可以得到位于堆顶的数据，因此得到中位数的时间复杂度是O(1)。


        // 接下来考虑用最大堆和最小堆实现的一些细节。
        // 容器左边的数据使用最大堆来保存，容器右边的数据使用最小堆来保存。
        // 0.首先要保证数据平均分配到两个堆中，因此两个堆中数据的数目之差不能超过1且左边最大堆数据个数>=右边最小堆数据个数。
        // 同时还要保证最大堆中的所有数据都要小于最小堆中的数据。数据容器被分为两个部分：位于容器左边的数据比容器右边的数据小。

        // 1.为了实现平均分配，当已加入容器中的数据数目是奇数时【也就是包含这个数据的情况下，数据总数目是偶数。这样最大堆和最小堆平均分配】，那么根据两堆的平均分配，需要将一个数据添加到最小堆中。这样两边刚好数量相同。
        // 为了保证最大堆中的所有数据都要小于最小堆中的数据，那么就需要比较待插入数据和最大堆堆顶的元素大小。
        // 1.1若最大堆栈顶元素大于待加入元素【左侧最大大于待加入元素】，那么这时就将最大堆【左侧】堆顶元素出堆然后并将该元素添加到最小堆【右侧】，然后将待加入元素加入到最大堆【左侧】中。
        // 1.2若最大堆栈顶元素小于待加入元素，那么将该待加入元素加入到最小堆【右侧】中。
        // 2.当已加入容器中的数据数目是偶数时【也就是包含这个数据的情况下，数据总数目是奇数。】，那么根据两堆的平均分配，需要将一个数据添加到最大堆【左侧】中。这样两边刚好数量相同。
        // 为了保证最大堆中的所有数据都要小于最小堆中的数据，那么就需要比较待插入数据和最大堆堆顶的元素大小。
        // 2.1若最小堆的堆顶元素小于待加入元素【右侧最小小于待加入元素】，那么这时就将最小堆【右侧】堆顶元素出堆然后并将该元素添加到最大堆中【左侧】，然后将待加入元素加入到最小堆【右侧】中。
        // 2.2若最小堆的堆顶元素大于待加入元素，那么将该待加入元素加入到最大堆【左侧】中


        //// 解法1：最大堆和最小堆  这种就是利用现有数据结构创造具有新特性的数据容器
        private PriorityQueue<Integer> rightMinHeap;// 最小堆，用来保存容器右边的数据，堆顶元素是堆中元素的最小值
        private PriorityQueue<Integer> leftMaxHeap;// 最大堆，用来保存容器左边的数据，堆顶元素是堆中元素的最大值

        public MedianFinder() {
            rightMinHeap = new PriorityQueue<>();// 优先队列默认堆顶是最小值
            leftMaxHeap = new PriorityQueue<>((a, b) -> (b - a));// lambda的写法，构造大顶堆！
            //    leftMax=new PriorityQueue<>(new Comparator<Integer>() {// 比较器实现
            //        @Override
            //        public int compare(Integer o1, Integer o2) {
            //            return o2-o1;
            //        }
            //    });
        }

        // 两个堆中数据的数目之差不能超过1且左边最大堆数据个数>=右边最小堆数据个数。和保证最大堆中的所有数据都要小于最小堆中的数据。
        public void addNum(int num) {
            if (rightMinHeap.size() == leftMaxHeap.size()) {// 当前已保存的数据个数是偶数个,
                if (rightMinHeap.size() > 0 && rightMinHeap.peek() < num) {
                    // 当右边最小堆非空，最小堆的堆顶元素小于当前待插入元素，将最小堆堆顶元素弹出然后加入最大堆，然后将待加入元素加入最小堆
                    leftMaxHeap.offer(rightMinHeap.poll());
                    rightMinHeap.offer(num);
                } else {// 当最小堆的堆顶元素大于等于当前待插入元素，将待插入元素直接插入最大堆
                    leftMaxHeap.offer(num);
                }
            } else {// 当前已保存的数据个数是奇数个
                if (leftMaxHeap.size() > 0 && leftMaxHeap.peek() > num) {
                    // 当最大堆栈顶元素大于当前待插入元素时，将最大堆的堆顶元素弹出然后加入最小堆，然后将待加入元素加入最大堆
                    rightMinHeap.offer(leftMaxHeap.poll());
                    leftMaxHeap.offer(num);
                } else {// 当最大堆栈顶元素小于等于当前待插入元素时，将待插入元素直接插入最小堆
                    rightMinHeap.offer(num);
                }
            }
        }


        public double findMedian() {
            if (rightMinHeap.size() == leftMaxHeap.size()) {// 当所有数据个数是偶数个时，中位数是最大堆和最小堆堆顶元素的平均值
                return (rightMinHeap.peek() + leftMaxHeap.peek()) / 2.0;
            } else {// 当所有数据个数是奇数个时，中间值是左边最大堆的堆顶元素
                return leftMaxHeap.peek();
            }
        }


        //// 解法2：最大堆和最小堆  这种就是利用现有数据结构创造具有新特性的数据容器
        ////// 用一个最大堆实现左边的数据容器，用一个最小堆实现右边的数据容器。
        //// 因为最大堆和最小堆加入或者弹出元素都会自己调整堆中的排序，其实下面这个写法理解起来比上面的更好理解。
        //// 这一个少了判断待插入元素和最小堆或或者最大堆堆顶元素的大小。
        //
        //PriorityQueue<Integer> rightMinHeap;// 最小堆，用来保存容器右边的数据，堆顶元素是堆中元素的最小值
        //PriorityQueue<Integer> leftMaxHeap;// 最大堆，用来保存容器左边的数据，堆顶元素是堆中元素的最大值
        //
        //public MedianFinder() {
        //    rightMinHeap =new PriorityQueue<>();// 优先队列默认堆顶是最小值
        //    leftMaxHeap =new PriorityQueue<>((a, b)->(b-a));// lambda的写法，构造大顶堆！
        //    //    leftMax=new PriorityQueue<>(new Comparator<Integer>() {// 比较器实现
        //    //        @Override
        //    //        public int compare(Integer o1, Integer o2) {
        //    //            return o2-o1;
        //    //        }
        //    //    });
        //}
        //
        ////// 两个堆中数据的数目之差不能超过1且左边最大堆数据个数>=右边最小堆数据个数。和保证最大堆中的所有数据都要小于最小堆中的数据。
        //public void addNum(int num) {
        //    // 当前已保存的数据个数是偶数个,:先加入小顶堆，小顶堆自动调整之后再将小顶堆的堆顶加入到大顶堆——
        //    // 这样调整后大顶堆的元素个数最多比小顶堆多一个，最终输出中位数的时候输出大顶堆的堆顶即可
        //    if(rightMinHeap.size() == leftMaxHeap.size()){
        //        rightMinHeap.offer(num);
        //        leftMaxHeap.offer(rightMinHeap.poll());
        //    }else{// 当前已保存的数据个数是是奇数个：先加入大顶堆，大顶堆自动调整后再将大顶堆的堆顶加入小顶堆
        //        // 这样保证调整后大顶堆的元素个数和小顶堆的元素个数相同
        //        leftMaxHeap.offer(num);
        //        rightMinHeap.offer(leftMaxHeap.poll());
        //    }
        //}
        //
        //public double findMedian() {
        //    if(leftMaxHeap.size() == rightMinHeap.size())
        //        return (leftMaxHeap.peek() + rightMinHeap.peek())/2.0;
        //    else
        //        return leftMaxHeap.peek();
        //}


    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

//leetcode submit region end(Prohibit modification and deletion)

}
