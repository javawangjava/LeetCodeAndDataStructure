/**
 * <p>给定一个含有 <code>n</code><strong> </strong>个正整数的数组和一个正整数 <code>target</code><strong> 。</strong></p>
 *
 * <p>找出该数组中满足其和<strong> </strong><code>≥ target</code><strong> </strong>的长度最小的 <strong>连续子数组</strong>
 * <code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code>
 * ，并返回其长度<strong>。</strong>如果不存在符合条件的子数组，返回 <code>0</code> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>target = 7, nums = [2,3,1,2,4,3]
 * <strong>输出：</strong>2
 * <strong>解释：</strong>子数组 <code>[4,3]</code> 是该条件下的长度最小的子数组。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>target = 4, nums = [1,4,4]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>target = 11, nums = [1,1,1,1,1,1,1,1]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= target <= 10<sup>9</sup></code></li>
 * <li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
 * <li><code>1 <= nums[i] <= 10<sup>5</sup></code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>如果你已经实现<em> </em><code>O(n)</code> 时间复杂度的解法, 请尝试设计一个 <code>O(n log(n))</code> 时间复杂度的解法。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>前缀和</li><li>滑动窗口</li></div></div><br><div><li>👍
 * 1219</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


/**
 * 209
 * 长度最小的子数组
 */

public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MinimumSizeSubarraySum().new Solution();
        //int[] nums = {1, 4, 4};
        int[] nums = {2,3,1,2,4,3};
        int a = solution.minSubArrayLen(7, nums);
        System.out.println(a);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法1：同向双指针   长度可变的滑动窗口
        // 子数组由数组中一个或连续的多个数字组成。一个子数组可以用两个指针表示。
        // 如果第1个指针left指向子数组的第1个数字，第2个指针right指向子数组的最后一个数字，那么子数组就是由这两个指针之间的所有数字组成的。
        // 指针left和right初始化的时候都指向数组的第1个元素。
        // 如果两个指针之间的子数组中所有数字之和大于或等于k【那么这时候就可以更新满足条件的最小长度】，那么把指针left向右移动。
        // 每向右移动指针left一步，相当于从子数组的最左边删除一个数字，子数组的长度也减1。由于数组中的数字都是正整数，从子数组中删除一些数字就能减小子数组之和。
        // 由于目标是找出和大于或等于k的最短子数组，因此一直向右移动指针left，直到子数组的和小于k为止。
        // 如果两个指针之间的子数组中所有数字之和小于k，那么把指针right向右移动。
        // 指针right每向右移动一步就相当于在子数组的最右边添加一个新的数字，子数组的长度加1。由于数组中的所有数字都是正整数，因此在子数组中添加新的数字能得到更大的子数组之和。


        // 注意这个滑动窗口是使用数组下标模拟形成的

        //public int minSubArrayLen(int target, int[] nums) {
        //    if (nums == null || nums.length == 0) {//判空
        //        return 0;
        //    }
        //    int length = nums.length;
        //    int minLength = Integer.MAX_VALUE;
        //    //开始时left和right都指向数组的第一个元素
        //    int left = 0;
        //    int right = 0;
        //    int sum = 0;
        //    // [left,right]组成动态窗口，进窗口加进队元素，出窗口减出队元素
        //    // 根据子数组的和的特点，左指针left一定不会超过右指针right,写着也没问题。就这道题而言不写也可以。
        //
        //    // 用right来遍历整个数组
        //    while (right < length&&left<=right) {//用右指针遍历整个数组，每次循环右指针右移一次
        //        sum += nums[right];//数组元素进入队列就要更新子数组和
        //        //子数组和大于target，这时更新最小长度，并尝试移出最左侧元素不断更新,注意这里（left<=right）【因为可以把窗口移空】
        //        while (sum >= target&&left<=right) {
        //            minLength = Math.min(minLength, right - left + 1);
        //            sum -= nums[left];//先在子数组中移除该元素再left指针右移
        //            left++;
        //        }
        //        right++;   //右指针右移
        //    }
        //    return minLength == Integer.MAX_VALUE ? 0 : minLength;
        //}


        public int minSubArrayLen(int target, int[] nums) {
            if(nums==null||nums.length==0){
                return 0;
            }
            int len=nums.length;
            int left=0;
            int right=0;
            int sum=0;
            int minLen=Integer.MAX_VALUE;

            while (left<=right&&right<len){
                sum+=nums[right];
                while (sum>=target){
                    minLen=Math.min(minLen,right-left+1);
                    sum-=nums[left];
                    left++;
                }
                right++;
            }
            return minLen==Integer.MAX_VALUE?0:minLen;
        }



       /*

        //解法1：暴力循环  超时
        //初始化子数组的最小长度为无穷大，枚举数组nums 中的每个下标作为子数组的开始下标，
        //对于每个开始下标 i，需要找到大于或等于 i 的最小下标 j，使得从 nums[i] 到 nums[j] 的元素和大于或等于 s，并更新子数组的最小长度（此时子数组的长度是 j-i+1）。

        public int minSubArrayLen(int target, int[] nums) {
            if (nums == null||nums.length==0) {
                return 0;
            }
            int length = nums.length;
            int minLength = Integer.MAX_VALUE;

            // 暴力循环每一个子数组
            for (int i = 0; i < length; i++) {
                int sum = 0;
                for (int j = i; j < length; j++) {
                    sum += nums[j];
                    if (sum >= target) { //找出数组中从i开始的满足其和 ≥ target 的长度最小的连续子数组
                        minLength = (minLength <= j-i+1 ? minLength : j-i+1);
                        //minLength = Math.min(minLength, j - i + 1);
                        break;
                    }
                }
            }
            return minLength!=Integer.MAX_VALUE?minLength:0;
             //return minLength == Integer.MAX_VALUE ? 0 : minLength;

        }*/





        //    解法3：前缀和+二分查找

        //我们申请一个临时数组 sums，其中 sums[i] 表示的是原数组 nums 前 i 个元素的和，
        // 题中说了 “给定一个含有 n 个 正整数 的数组”，既然是正整数，那么相加的和会越来越大，也就是sums数组中的元素是递增的。
        //我们只需要找到 sums[k]-sums[j]>=s，那么 k-j 就是满足的连续子数组，但不一定是最小的，所以我们要继续找，直到找到最小的为止所以我们可以换种思路，
        // 求 sums[k]-sums[j]>=s 我们可以求 sums[j]+s<=sums[k]，那这样就好办了，因为数组sums中的元素是递增的，
        // 也就是排序的，我们只需要求出 sum[j]+s的值，然后使用二分法查找即可找到这个 k。


/*
        private static int binarySearch(int[] arr, int target) {
            int left = 0;
            int right = arr.length - 1;
            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (arr[mid] < target) {
                    left = mid + 1;
                } else if (arr[mid] > target) {
                    right = mid - 1;
                } else {
                    return mid;// target找到
                }
            }
            //没找到时返回的是 (-(insertion point) - 1),insertion point指比第一个大于key的位置
            // left就是待插入位置
            //return -(left+1);
            return -left - 1;// target not found
        }

        public int minSubArrayLen(int target, int[] nums) {
            int length = nums.length;
            if (length == 0) {
                return 0;
            }
            int minLength = Integer.MAX_VALUE;
            // 为了方便计算，令 size = n + 1
            // sums[0] = 0 意味着前 0 个元素的前缀和为 0
            // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
            // 以此类推
            // sums[i] 表示的是原数组 nums 前 i 个元素的和，
            int[] sums = new int[length + 1];
            for (int i = 1; i <= length; i++) {
                sums[i] = nums[i - 1] + sums[i - 1];
            }

            for (int i = 1; i <= length; i++) {
                int temp = target + sums[i - 1];
                int bound = Solution.binarySearch(sums, temp);
                // API中提供的没找到返回待插入位置，但是返回的是负数(-(insertion point +1)),insertion point指比第一个大于key的位置
                if (bound < 0) {
                    //(-bound-1)就是（-（-(insertion point +1)）-1）=(insertion point +1)-1=insertion point
                    bound = -bound - 1;
                }
                if (bound <= length) {
                    minLength = Math.min(minLength, bound - i + 1);
                }
            }
            return minLength == Integer.MAX_VALUE ? 0 : minLength;
        }

        */

    }
//leetcode submit region end(Prohibit modification and deletion)

}
