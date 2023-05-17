/**
 * 符合下列属性的数组 <code>arr</code> 称为 <strong>山脉数组</strong> ：
 * <ul>
 * <li><code>arr.length >= 3</code></li>
 * <li>存在 <code>i</code>（<code>0 < i < arr.length - 1</code>）使得：
 * <ul>
 * <li><code>arr[0] < arr[1] < ... arr[i-1] < arr[i] </code></li>
 * <li><code>arr[i] > arr[i+1] > ... > arr[arr.length - 1]</code></li>
 * </ul>
 * </li>
 * </ul>
 *
 * <p>给你由整数组成的山脉数组 <code>arr</code> ，返回任何满足 <code>arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... >
 *     arr[arr.length - 1]</code> 的下标 <code>i</code> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>arr = [0,1,0]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>arr = [0,2,1,0]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>arr = [0,10,5,2]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>arr = [3,4,5,1]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>arr = [24,69,100,99,79,78,67,36,26,19]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>3 <= arr.length <= 10<sup>4</sup></code></li>
 * <li><code>0 <= arr[i] <= 10<sup>6</sup></code></li>
 * <li>题目数据保证 <code>arr</code> 是一个山脉数组</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>很容易想到时间复杂度 <code>O(n)</code> 的解决方案，你可以设计一个 <code>O(log(n))</code> 的解决方案吗？</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 295</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 852
 * 山脉数组的峰顶索引
 *
 * @author wangweizhou
 * @date 2022-08-30 14:46:27
 */

public class PeakIndexInAMountainArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new PeakIndexInAMountainArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 根据题意，数组所有元素不相等。其实就是A字型找最大值

        // 山峰数组中的最大值是数组中唯一一个比它左右两边数字都大的数字。
        // 位于最大值前面的数字（除第1个数字之外）总是比它前一个数字大但比它后一个数字小，位于最大值后面的数字（除最后一个数字之外）总是比它后一个数字大但比它前一个数字小。
        // 可以根据山峰数组的这个特点应用二分查找算法。先取出位于数组中间的数字。
        // 如果这个数字比它前后两个数字都大，那么就找到了数组的最大值。
        // 如果这个数字比它前一个数字大但比后一个数字小，那么这个数字位于数组递增的部分，数组的最大值一定在它的后面，接下来只需要在数组的后半部分查找就可以。
        // 如果这个数字比它前一个数字小但比后一个数字大，那么这个数字位于数组递减的部分，数组的最大值一定在它的前面，接下来只需要在数组的前半部分查找就可以。

        // 在一个长度为n的山峰数组中，由于第1个数字和最后一个数字都不可能是最大值，因此初始查找范围为数组下标从1到n-2的部分。
        // 取出位于当前查找范围中间的数字，即下标为mid的数字，如果这个数字大于它前后两个数字，那么它就是最大值；
        // 如果它大于它前面的数字，那么它位于数组递增的部分，接下来查找它的后半部分；否则它位于数组递减的部分，接下来查找它的前半部分。
        // 如果输入的数组是一个有效的山峰数组，那么在while循环中一定能找到山峰数组的最大值。
        // 只是Java的语法要求函数的每个分支必须有返回值，所以在函数体的最后添加一行返回-1的代码。实际上，这一行代码不会被执行。


        // 解法1：写法2二分法  一定有尖点
        public int peakIndexInMountainArray(int[] arr) {
            if (arr == null || arr.length < 3) {
                return Integer.MIN_VALUE;
            }
            // 题意已经默认了尖点在中间不在两端，所以边界直接就不包含两个边界
            int left = 1;
            int right = arr.length - 2;
            while (left <= right) {
                int mid = (left + right) / 2;
                // 因为前面初始值的原因，所以这里不用检查数组下标越界
                if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {// 找到尖。
                    return mid;
                }
                // 下面mid不是尖，也就是不包含尖的两侧
                if (arr[mid] < arr[mid + 1]) {// 尖点左侧
                    left = mid + 1;
                } else {
                    right = mid - 1;// 尖点右侧
                }
            }
            return Integer.MIN_VALUE;
        }




        //// 解法1：二分法  由中点值和中点值的下一个来缩短遍历区间
        //// 中间值和中间值的下一个是递增段，更新左边界
        //// 中间值和中间值的下一个是递减段，更新右边界
        //// 峰顶元素左侧满足 arr[i−1]<arr[i] 性质，右侧不满足
        //// 峰顶元素右侧满足 arr[i]>arr[i+1] 性质，左侧不满足
        //
        //public int peakIndexInMountainArray(int[] arr) {
        //    if (arr == null || arr.length < 3) {
        //        return Integer.MIN_VALUE;
        //    }
        //    int left = 0;
        //    int right = arr.length - 1;
        //    while (left < right) {
        //        int mid = (left + right) / 2;
        //        if (arr[mid] < arr[mid + 1]) {// 上升段，也就是最大值的左边
        //            left = mid + 1;      // 最大值在[mid+1,right]区间
        //        } else {// 下降段，也就是最大值的右边
        //            right = mid;// 最大值在[left,mid]区间
        //        }
        //    }
        //    // l == r 停止循环，因为定义的循环不变量，所以 l/r 都是最大值索引。
        //    return left;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
