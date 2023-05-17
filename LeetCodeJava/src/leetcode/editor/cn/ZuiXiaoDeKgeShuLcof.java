/**
 * <p>输入整数数组 <code>arr</code> ，找出其中最小的 <code>k</code> 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>arr = [3,2,1], k = 2
 * <strong>输出：</strong>[1,2] 或者 [2,1]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>arr = [0,1,2,1], k = 1
 * <strong>输出：</strong>[0]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= k &lt;= arr.length &lt;= 10000</code></li>
 * <li><code>0 &lt;= arr[i]&nbsp;&lt;= 10000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>快速选择</li><li>排序</li><li>堆（优先队列）</li></div></div><br
 * ><div><li>👍 483</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 剑指 Offer 40
 * 最小的k个数
 *
 * @author wangweizhou
 * @date 2022-09-24 17:57:24
 */
public class ZuiXiaoDeKgeShuLcof {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ZuiXiaoDeKgeShuLcof().new Solution();
        int[] arr = {6, 7, 8, 9, 1, 2, 3, 4, 5};
        int[] nums = solution.getLeastNumbers(arr, 3);
        if (nums.length == 0) {
            System.out.println("数组长度为0");
        } else {
            for (int num : nums) {
                System.out.print(num);
            }
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 我们可以先创建一个大小为k的数据容器来存储最小的k个数字，接下来每次从输入的n个整数中读入一个数。
        // 如果容器中已有的数字少于k个，则直接把这次读入的整数放入容器之中；
        // 如果容器中已有k个数字了，也就是容器已满，此时我们不能再插入新的数字而只能替换已有的数字。
        // 找出这已有的k个数中的最大值，然后拿这次待插入的整数和最大值进行比较。
        // 如果待插入的值比当前已有的最大值小，则用这个数替换当前已有的最大值；
        // 如果待插入的值比当前已有的最大值还要大，那么这个数不可能是最小的k个整数之一，于是我们可以抛弃这个整数。
        //
        // 因此，当容器满了之后，我们要做3件事情：一是在k个整数中找到最大数；二是有可能在这个容器中删除最大数；三是有可能要插入一个新的数字。
        //
        // 我们可以选择用不同的二叉树来实现这个数据容器。由于每次都需要找到k个整数中的最大数字，我们很容易想到用最大堆。
        // 在最大堆中，根节点的值总是大于它的子树中任意节点的值。于是我们每次可以在O)时间内得到已有的k个数字中的最大值，但需要O(logk)时间完成删除及插入操作。


        //// 解法1：大顶堆
        // Java中默认的是小顶堆
        public int[] getLeastNumbers(int[] arr, int k) {
            if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
                return new int[0];
            }
            int[] ans = new int[k];//
            // 创建大小为k的最大堆，将自带的小根堆变化成最大堆，设定容量为K
            //PriorityQueue<Integer> maxHeap=new PriorityQueue<>((o1,o2)->(o2-o1));
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });

			// 如果容器中已有的数字少于k个，则直接把这次读入的整数放入容器之中；
            for (int i = 0; i < k; i++) {
                maxHeap.offer(arr[i]);
            }

			// 如果容器中已有k个数字了，也就是容器已满，此时我们不能再插入新的数字而只能替换已有的数字。
            // 当容器已满，根据元素的大小判断是否需要更换容器中已有的元素
            for (int i = k; i < arr.length; i++) {
                if (maxHeap.peek() > arr[i]) {// 当待插入的元素小于小根堆的堆顶元素，那么就弹出堆定元素，插入新元素
                    maxHeap.poll();
                    maxHeap.offer(arr[i]);
                }
            }

            // 将容器中的元素放入结果数组
            for (int i = 0; i < k; i++) {
                ans[i] = maxHeap.poll();
            }
            return ans;
        }






        //// 解法2：可以基于Partition函数来解决这个问题。
        //// 如果基于数组的第k个数字来调整，则使得比第k个数字小的所有数字都位于数组的左边，比第个数字大的所有数字都位于数组的右边。
        //// 这样调整之后，位于数组中左边的k个数字就是最小的k个数字（这个数字不一定是排序的）。
        //
        //public int[] getLeastNumbers(int[] arr, int k) {
        //    if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
        //        return new int[0];
        //    }
        //    int index = partition(arr, 0, arr.length - 1);
        //    int left = 0;
        //    int right = arr.length - 1;
        //    while (index != k-1) {
        //        if (index > k - 1) {
        //            right = index - 1;
        //            index = partition(arr, left, right);
        //        } else {
        //            left = index + 1;
        //            index = partition(arr, left, right);
        //        }
        //    }
        //    int[] ans = new int[k];
        //    for (int i = 0; i < k; i++) {
        //        ans[i] = arr[i];
        //    }
        //    return ans;
        //}
        //
        //
        //private int partition(int[] nums, int left, int right) {
        //    if (nums == null || nums.length == 0 || left > right || left < 0 || left >= nums.length || right < 0 ||
		//    right >= nums.length) {
        //        return Integer.MIN_VALUE;
        //    }
        //    int pivot = new Random().nextInt(right - left + 1) + left;
        //    swap(nums, pivot, right);
        //    int prevSmall = left - 1;
        //    for (int i = left; i < right; i++) {
        //        if (nums[i] < nums[right]) {
        //            prevSmall++;
        //            swap(nums, i, prevSmall);
        //        }
        //    }
        //    prevSmall++;
        //    swap(nums, prevSmall, right);
        //    return prevSmall;
        //}
        //
        //
        //private void swap(int[] nums, int a, int b) {
        //    if (a != b) {
        //        int temp = nums[a];
        //        nums[a] = nums[b];
        //        nums[b] = temp;
        //    }
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
