/**
 * <p>给定两个以 <strong>升序排列</strong> 的整数数组 <code>nums1</code> 和<strong> </strong><code>nums2</code><strong>&nbsp;
 * </strong>,&nbsp;以及一个整数 <code>k</code><strong>&nbsp;</strong>。</p>
 *
 * <p>定义一对值&nbsp;<code>(u,v)</code>，其中第一个元素来自&nbsp;<code>nums1</code>，第二个元素来自 <code>nums2</code><strong>&nbsp;
 * </strong>。</p>
 *
 * <p>请找到和最小的 <code>k</code>&nbsp;个数对&nbsp;<code>(u<sub>1</sub>,v<sub>1</sub>)</code>, <code>&nbsp;(u<sub>2</sub>,
 * v<sub>2</sub>)</code> &nbsp;... &nbsp;<code>(u<sub>k</sub>,v<sub>k</sub>)</code>&nbsp;。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * <strong>输出:</strong> [1,2],[1,4],[1,6]
 * <strong>解释: </strong>返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * <strong>输出: </strong>[1,1],[1,1]
 * <strong>解释: </strong>返回序列中的前 2 对数：
 * &nbsp;    [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>nums1 = [1,2], nums2 = [3], k = 3
 * <strong>输出:</strong> [1,3],[2,3]
 * <strong>解释: </strong>也可能序列中所有的数对都被返回:[1,3],[2,3]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>9</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup></code></li>
 * <li><code>nums1</code> 和 <code>nums2</code> 均为升序排列</li>
 * <li><code>1 &lt;= k &lt;= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>堆（优先队列）</li></div></div><br><div><li>👍 447</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 373
 * 查找和最小的 K 对数字
 *
 * @author wangweizhou
 * @date 2022-11-19 22:51:02
 */

public class FindKPairsWithSmallestSums {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new FindKPairsWithSmallestSums().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 可以用最大堆来存储这k个和最小的数对。逐一将m×n个数对添加到最大堆中。当堆中的数对的数目小于k时，直接将数对添加到堆中。
        //// 如果堆中已经有k个数对，那么先要比较待添加的数对之和及堆顶的数对之和（也是堆中最大的数对之和）。
        //// 如果待添加的数对之和大于或等于堆顶的数对之和，那么堆中的k个数对之和都小于或等于待添加的数对之和，因此待添加的数对可以忽略。
        //// 如果待添加的数对之和小于堆顶的数对之和，那么删除堆顶的数对，并将待添加的数对添加到堆中，这样可以确保堆中存储的是和最小的k个数对。
        //// 每次都是将待添加的数对与堆中和最大的数对进行比较，而这也是用最大堆的原因。
        //
        //// 接下来考虑如何优化。题目给出的条件是输入的两个数组都是递增排序的，这个特性我们还没有用到。
        //// 如果从第1个数组中选出第k+1个数字和第2个数组中的某个数字组成数对p，那么该数对之和一定不是和最小的k个数对中的一个，
        //// 这是因为第1个数组中的前k个数字和第2个数组中的同一个数字组成的k个数对之和都要小于数对p之和。
        //// 因此，不管输入的数组nums1有多长，最多只需要考虑前k个数字。同理，不管输入的数组nums2有多长，最多也只需要考虑前k个数字。
        //
        //// maxHeap是一个最大堆，它的每个元素都是一个长度为2的数组，表示一个数对。
        //// 每个数对的第1个数字来自数组nums1，第2个数字来自数组nums2。
        //// 由于希望和最大的数对位于堆的顶部，因此在PriorityQueue的构造函数中传入的比较规则比较的是两个数对之和。
        //// 可以用一个lambda表达式定义比较规则，它的参数是两个数对p1和p2。
        //// 由于需要一个最大堆，和默认的最小堆的比较规则相反，因此lambda表达式的返回值是用数对p2之和减去数对p1之和。


        // 解法1：最大堆   maxHeap是一个最大堆，它的每个元素都是一个长度为2的数组，表示一个数对。每个数对的第1个数字来自数组nums1，第2个数字来自数组nums2。
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            List<List<Integer>> lists = new ArrayList<>();
            if (nums1 == null || nums2 == null|| nums1.length==0|| nums2.length==0) {// 判空
                return lists;
            }

            // // 最大堆中保存两个元素，这里使用数组传递元素。 最大堆：后减前就是最大堆
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                    (p1, p2) -> p2[0] + p2[1] - p1[0] - p1[1]
            );
            // 因为数组nums1和nums2有序，所以只要在前k个里面找就可以
            for (int i=0; i<Math.min(k, nums1.length);i++){
                for (int j = 0; j < Math.min(k, nums2.length); j++) {
                    if(maxHeap.size()<k){// 当堆中元素数目小于k个，将当前数对直接加入堆中
                        maxHeap.offer(new int[]{nums1[i],nums2[j]});
                    }else{// 当堆中元素已经有K个
                        int[] peek=maxHeap.peek();// 获取栈顶数对
                        if(peek[0]+peek[1]>nums1[i]+nums2[j]){// 如果当前数对的值小于堆顶元素数对的值
                            maxHeap.poll();// 移出堆顶元素
                            maxHeap.offer(new int[]{nums1[i],nums2[j]});// 将当前数对添加到最大堆中
                        }
                    }
                }
            }


            // 遍历堆中元素将堆中元素保存在结果集合中
            while(!maxHeap.isEmpty()){
                int[] values=maxHeap.poll();
                lists.add(Arrays.asList(values[0],values[1]));
            }
            return lists;
        }




        ////// 解法2：最小堆
        //// minHeap是一个最小堆，它的每个元素都是一个长度为2的数组，数组的第1个数字表示数对的第1个数字在数组nums1中的下标，
        //// 第2个数字表示数对的第2个数字在数组nums2中的下标。
        //
        //// 由于每次都是从k个候选的数对中选取和最小的数对，因此可以用一个最小堆来存储候选的数对。
        //// 如果和最小的数对的两个数字在两个数组中的下标分别为i1和i2，将该数对添加到结果中并将其从最小堆中删除，
        //// 再将在两个数组中下标分别为i1和i2+1的两个数字作为新的候选数对添加到最小堆中。
        //
        //// minHeap是一个最小堆，它的每个元素都是一个长度为2的数组，数组的第1个数字表示数对的第1个数字在数组nums1中的下标，
        //// 第2个数字表示数对的第2个数字在数组nums2中的下标。
        //// 由于使用最小堆的目的是找出和最小的数对，
        //// 因此在创建minHeap时在构造函数传入的lambda表达式中分别根据数对的两个数字在两个数组nums1和nums2的下标读取对应的数字并比较数对之和。
        //
        //public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //    List<List<Integer>> lists = new ArrayList<>();
        //    if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {// 判空
        //        return lists;
        //    }
        //    // minHeap是一个最小堆，它的每个元素都是一个长度为2的数组，表示一个数对。每个数对的第1个数字来自数组nums1，第2个数字来自数组nums2。
        //    Queue<int[]> minHeap = new PriorityQueue<>(
        //            (p1, p2) -> nums1[p1[0]] + nums2[p1[1]] - nums1[p2[0]] - nums2[p2[1]]
        //    );
        //
        //    // 将数组nums1的前k个元素和数组num2的第1个元素组成的数对添加到最小堆中
        //    for (int i = 0; i < Math.min(k, nums1.length); i++) {//
        //        minHeap.offer(new int[]{i, 0});
        //    }
        //    List<List<Integer>> result = new ArrayList<>();// 结果集合
        //
        //    // 最小堆中有候选数对
        //    while (k > 0 && !minHeap.isEmpty()) {
        //        // 如果和最小的数对的两个数字在两个数组中的下标分别为i1和i2，将该数对添加到结果中并将其从最小堆中删除，
        //        // 再将在两个数组中下标分别为i1和i2+1的两个数字作为新的候选数对添加到最小堆中。
        //        int[] min = minHeap.poll();// 最小堆的堆顶元素就是目前最小的数对
        //        result.add(Arrays.asList(nums1[min[0]], nums2[min[1]]));// 将最小数对添加到结果集合中
        //        k--;// 数对的个数减1
        //        if (min[1] < nums2.length - 1) {
        //            minHeap.offer(new int[]{min[0], min[1] + 1});
        //        }
        //    }
        //    return result;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
