/**
 * <p>给你一个整数数组 <code>nums</code> ，你需要找出一个 <strong>连续子数组</strong> ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。</p>
 *
 * <p>请你找出符合题意的 <strong>最短</strong> 子数组，并输出它的长度。</p>
 *
 * <p> </p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,6,4,8,10,9,15]
 * <strong>输出：</strong>5
 * <strong>解释：</strong>你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3,4]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 10<sup>4</sup></code></li>
 * <li><code>-10<sup>5</sup> <= nums[i] <= 10<sup>5</sup></code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>你可以设计一个时间复杂度为 <code>O(n)</code> 的解决方案吗？</p>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>栈</li><li>贪心</li><li>数组</li><li>双指针</li><li>排序</li><li>单调栈</li></div></div
 * ><br><div><li>👍 904</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 581
 * 最短无序连续子数组
 *
 * @author wangweizhou
 * @date 2022-08-18 09:51:29
 */

public class ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ShortestUnsortedContinuousSubarray().new Solution();
        //int[] nums={1,2,3,4};
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        //int[] nums = {2, 1};
        int ans = solution.findUnsortedSubarray(nums);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public int findUnsortedSubarray(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return 0;
            }
            int len = nums.length;
            int left = 0;
            int right = len - 1;
            while (left < len - 1 && nums[left] <= nums[left + 1]) {
                left++;
            }

            while (right > 0 && nums[right] >= nums[right - 1]) {
                right--;
            }

            int start = left;
            int end = right;
            for (int i = start; i <= end; i++) {
                while (left >= 0 && nums[i] < nums[left]) {
                    left--;
                }
                while (right < len && nums[i] > nums[right]) {
                    right++;
                }
            }

            return right > left ? (right-1) - (left+1) + 1 : 0;
        }


        // 解法2： 双指针+线性扫描
        // 从左向右找到单调递增的子序列[0,left]。从右向左找到单调递增的子序列[right,len-1]。
        // 对区间[left,right]（包含left和right两端点）进行遍历；
        // 当[left,right]中间出现小于左侧单调递增区间的[0,left]的上限nums[left]的元素时，不断将left左移；
        // 当[left,right]中间出现大于右侧单调递增[right,len-1]的下限nums[right]的元素时，不断将right右移。

        //public int findUnsortedSubarray(int[] nums) {
        //    if (nums == null || nums.length <= 1) {
        //        return 0;
        //    }
        //    int len = nums.length;
        //    int left = 0, right = len - 1;
        //    //找到左侧单调递增的分割点,也就是左侧单调递增的截止点
        //    while (left < right && nums[left] <= nums[left + 1]) {//单调递增【0，left】,left是单调递增的截止点
        //        left++;
        //    }
        //    //找到右侧单调递增的分割点,也就是左侧单调递增的截止点
        //    while (left < right && nums[right] >= nums[right - 1]) {//单调递增【right,len】，right是单调递增的开始点
        //        right--;
        //    }
        //    // 对区间[left,right]（包含left和right两端点）进行遍历
        //    // 当[left,right]中间出现小于左侧单调递增区间的[0,left]的上限nums[left]的元素时，不断将left左移
        //    // 当[left,right]中间出现大于右侧单调递增【right,len】的下限nums[right]的元素时，不断将right右移
        //    int start = left, end = right;//因为后续左右边界会变化，所以利用临时变量
        //    // 遍历[start,end]
        //    // left是单调递增的截止点，right是单调递增的开始点。
        //    for (int i = start; i <= end; i++) {
        //        // 若nums[i] < nums[left]，则表明i在【0，left】内部，这里需要循环找到在【0，left】中单调递增的上限小于等于nums[i]的位置。
        //        while (left >= 0 && nums[i] < nums[left]) {//当[left，right]中出现小于左区间有边界的元素时，左边界会不断左移，
        //            // 然后【start,end】中没有遍历的的元素不断与最新的左边界比较。
        //            left--;
        //        }
        //        while (right <= len - 1 && nums[i] > nums[right]) {
        //            right++;
        //        }
        //    }
        //    return right == left ? 0 : (right - 1) - (left + 1) + 1;
        //}


        //// 解法2： 双指针+线性扫描
        //public int findUnsortedSubarray(int[] nums) {
        //    if (nums == null || nums.length <= 1) {
        //        return 0;
        //    }
        //    int len = nums.length;
        //    int left = 0, right = len - 1;
        //    //找到左侧单调递增的分割点
        //    while (left < right && nums[left] <= nums[left + 1]) {//单调递增【0，left】,left之后就可以递减
        //        left++;
        //    }
        //    //找到右侧单调递增的分割点
        //    while (left < right && nums[right] >= nums[right - 1]) {//单调递增【right,len】，right之前就递减
        //        right--;
        //    }
        //
        //    // 对区间[left,right]（包含left和right两端点）进行遍历
        //    // 当[left,right]中间出现小于左侧单调递增区间的[0,left]的上限nums[left]的元素时，不断将left左移
        //    // 当[left,right]中间出现大于右侧单调递增【right,len】的下限nums[right]的元素时，不断将right右移
        //
        //    int start = left, end = right;//因为后续左右边界会变化，所以利用临时变量
        //    int min = nums[left], max = nums[right];
        //    for (int i = start; i <= end; i++) {
        //        if (nums[i] < min) {//当[left，right]中出现小于左区间右边界的元素时，
        //            while (left >= 0 && nums[left] > nums[i]) {//当左区间右边界小于当前元素时，不断将左区间右边界向左移动
        //                left--;
        //            }
        //            min = left >= 0 ? nums[left] : Integer.MIN_VALUE;//更新左区间的最小值。
        //        }
        //        if (nums[i] > max) {
        //            while (right < len && nums[right] < nums[i]) {
        //                right++;
        //            }
        //            max = right < len ? nums[right] : Integer.MAX_VALUE;
        //        }
        //    }
        //    return right == left ? 0 : (right - 1) - (left + 1) + 1;
        //}


        //// 解法1：双指针+排序   复制之后排序对比排序后数组和原数组不同元素的个数
        //public int findUnsortedSubarray(int[] nums) {
        //    if (nums == null || nums.length <= 1) {
        //        return 0;
        //    }
        //    int[] copy = new int[nums.length];
        //    for (int i = 0; i < nums.length; i++) {
        //        copy[i] = nums[i];
        //    }
        //    Arrays.sort(copy);
        //    int left = 0;
        //    int right = nums.length - 1;
        //    while (left <= right && nums[left] == copy[left]) {
        //        left++;
        //    }
        //    while (left <= right && nums[right] == copy[right]) {
        //        right--;
        //    }
        //    return right >= left ? right - left + 1 : 0;
        //}


        //// 解法1：双指针+排序 写法2    复制之后排序对比排序后数组和原数组不同元素的个数
        //public int findUnsortedSubarray(int[] nums) {
        //    if (nums == null || nums.length <= 1) {
        //        return 0;
        //    }
        //    int[] copy = new int[nums.length];
        //    for (int i = 0; i < nums.length; i++) {
        //        copy[i] = nums[i];
        //    }
        //    Arrays.sort(copy);
        //    int left = 0;
        //    int right = copy.length - 1;
        //    while (nums[left] == copy[left] && left < nums.length - 1) {
        //        left++;
        //    }
        //    while (nums[right] == copy[right] && right > 0) {
        //        right--;
        //        if (right < left) {
        //            return 0;
        //        }
        //    }
        //    return right >= left ? right - left + 1 : 0;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
