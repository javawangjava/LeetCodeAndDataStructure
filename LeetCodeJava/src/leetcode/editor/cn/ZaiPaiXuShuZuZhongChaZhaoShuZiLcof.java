/**
 * <p>统计一个数字在排序数组中出现的次数。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [<code>5,7,7,8,8,10]</code>, target = 8
 * <strong>输出:</strong> 2</pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [<code>5,7,7,8,8,10]</code>, target = 6
 * <strong>输出:</strong> 0</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= nums.length <= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code></li>
 * <li><code>nums</code> 是一个非递减数组</li>
 * <li><code>-10<sup>9</sup> <= target <= 10<sup>9</sup></code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>注意：</strong>本题与主站 34 题相同（仅返回值不同）：
 * <a href="https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/">https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/</a></p>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 357</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 53 - I
 * 在排序数组中查找数字 I
 *
 * @author wangweizhou
 * @date 2022-09-14 00:06:28
 */

public class ZaiPaiXuShuZuZhongChaZhaoShuZiLcof {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ZaiPaiXuShuZuZhongChaZhaoShuZiLcof().new Solution();
        int[] nums = {5,6,7};
        //int ans = solution.search(nums, 6);
        //System.out.println(ans);


        System.out.println(solution.getFirstTarget(nums,6));
        System.out.println(solution.getLastTarget(nums,6));


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int first = getFirstTarget(nums, target);
            int last = getLastTarget(nums, target);
            if (first != -1 && last != -1) {
                return last - first + 1;
            } else {
                return 0;
            }
        }



        private int getFirstTarget(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    if (mid == 0 || nums[mid - 1] != target) {
                        return mid;
                    } else {
                        right = mid - 1;
                    }
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }



        private int getLastTarget(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    if (mid == nums.length - 1 || nums[mid + 1] != target) {
                        return mid;
                    } else {
                        left = mid + 1;
                    }
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }



        // 解法1：
        //public int search(int[] nums, int target) {
        //    if (nums == null || nums.length == 0) {
        //        return 0;
        //    }
        //    int low = binarySearchBigger(nums, target - 1);
        //    int high = binarySearchBigger(nums, target);
        //    return high - low;
        //}
        //
        //
        //
        //private int binarySearchBigger(int[] nums, int num) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int left = 0;
        //    int right = nums.length - 1;// 数组的最后一个元素
        //    while (left <= right) {
        //        int mid = (left + right) / 2;
        //        if (nums[mid] <= num) {
        //            left = mid + 1;
        //        } else {
        //            right = mid - 1;
        //        }
        //    }
        //    return left;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
