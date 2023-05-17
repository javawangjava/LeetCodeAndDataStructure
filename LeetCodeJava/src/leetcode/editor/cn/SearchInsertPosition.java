/**
 * <p>给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。</p>
 *
 * <p>请必须使用时间复杂度为 <code>O(log n)</code> 的算法。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [1,3,5,6], target = 5
 * <strong>输出:</strong> 2
 * </pre>
 *
 * <p><strong>示例&nbsp;2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [1,3,5,6], target = 2
 * <strong>输出:</strong> 1
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [1,3,5,6], target = 7
 * <strong>输出:</strong> 4
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * <li><code>nums</code> 为&nbsp;<strong>无重复元素&nbsp;</strong>的&nbsp;<strong>升序&nbsp;</strong>排列数组</li>
 * <li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 1595</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 35
 * 搜索插入位置
 */

public class SearchInsertPosition {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new SearchInsertPosition().new Solution();
        int[] nums = {1, 3, 5, 6, 7};
        int a = solution.searchInsert(nums, 8);
        System.out.println(a);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int searchInsert(int[] nums, int target) {
            if(nums==null||nums.length==0){
                return -1;
            }
            int left=0;
            int right=nums.length-1;
            while (left<=right){
                int mid=(left+right)/2;
                if(nums[mid]>=target){
                    if(mid==0||nums[mid-1]<target){
                        return mid;
                    }else {
                        right=mid-1;
                    }
                }else {
                    left=mid+1;
                }
            }
            return nums.length;
        }


        // 情况1：首先考虑如果目标值t不在数组中时它应该被插入哪个位置。由于数组是排序的，因此它应该排在所有比它小的数字的后面。
        // 也就是说，它的插入位置满足两个条件：一是该位置上的数字大于t，二是该位置的前一个数字小于t。
        // 情况2：当数组中包含目标值时，返回它在数组中的位置。由于数组中没有相同的数字，因此它前一个数字一定小于目标值。
        // 于是可以将目标值t是否在数组中出现的两种情况统一起来，即查找满足两个条件的位置：一是该位置上的数字大于或等于t，二是该位置的前一个数字小于t。

        // 注意处理待插入位置是数组第一个和最后一个。
        // 有两种情况需要特别注意。第1种情况是当mid等于0时如果nums[mid]依然大于目标值t，则意味着数组中的所有数字都比目标值大，应该返回0。
        // 第2种情况是当数组中不存在大于或等于目标值t的数字时，那么t应该添加到数组所有值的后面，即返回数组的长度。


        // 解法1：写法1：查找第一个大于等于target的位置，并且前一个位置的数字小于target。这个好理解。

        // 二分查找是在数组nums的某个范围内进行的，初始范围包括整个数组。
        // 每次二分查找都选取位于当前查找范围中间的下标为mid的值，然后比较nums[mid]和目标值t。
        // 如果nums[mid]大于或等于t，那么接着比较它的前一个数字nums[mid-1]和t。
        // 如果同时满足nums[mid]≥t并且nums[mid-1]＜t，那么mid就是符合条件的位置，返回mid即可。
        // 如果nums[mid]≥t并且nums[mid-1]≥t，那么符合条件的位置一定位于mid的前面，接下来在当前范围的前半部分查找。
        // 如果nums[mid]小于t，则意味着符合条件的位置一定位于mid的后面，接下来在当前范围的后半部分查找。



        ////// 查找第一个大于等于target的位置
        //public int searchInsert(int[] nums, int target) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int length = nums.length;
        //    int left = 0;
        //    int right = length - 1;
        //    while (left <= right) {
        //        int mid = (left + right) / 2;
        //        if (nums[mid] >= target) {
        //            // 当mid等于0时如果nums[mid]依然大于目标值t，则意味着数组中的所有数字都比目标值大，应该返回0。
        //            // 如果同时满足nums[mid]≥t并且nums[mid-1]＜t，那么mid就是符合条件的位置，返回mid即可。
        //            if (mid == 0 || nums[mid - 1] < target) {
        //                return mid;
        //            }
        //            right = mid - 1;// 没有结束方法，这时候在左半边找
        //        } else {
        //            left = mid + 1;
        //        }
        //    }
        //    // 当数组中不存在大于或等于目标值t的数字时，那么t应该添加到数组所有值的后面，即返回数组的长度。
        //    return nums.length;
        //}




        //// 写法6：
        //public int searchInsert(int[] nums, int target) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int left = 0;
        //    int right = nums.length - 1;
        //    while (left <= right) {
        //        int mid = (left + right) / 2;
        //        if (nums[mid] == target) {
        //            return mid;
        //        } else if (nums[mid] > target) {
        //            if (mid == 0 || nums[mid - 1] < target) {
        //                return mid;
        //            }
        //            right = mid - 1;
        //        } else {
        //            left = mid + 1;
        //        }
        //    }
        //    return nums.length;
        //}




        // 写法2
        // while循环的结束条件，情况1：在数组中找到目标值，return结束；java的除法是舍弃小数部分，向下取整，所以left<=mid<=right。
        // 情况2：当原数组不包含target时，考虑while循环最后一次执行的总是 left=right=mid或者left=mid=right-1,
        // 此时nums[mid] 左边的数全部小于target，nums[mid]右边的数全部大于target,则此时我们要返回的插入位置分为两种情况：
        // ①就是这个位置，即nums[mid]>target【nums[left]>target】时，此时执行了right=mid-1，返回left正确
        // ②是该位置的右边一个，即nums[mid]<target时，此时执行了left=mid+1,返回left也正确

        //public int searchInsert(int[] nums, int target) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int length = nums.length;
        //    int left = 0;
        //    int right = length - 1;
        //    while (left <= right) {
        //        int mid = left + (right - left) / 2;// java的除法是舍弃小数部分，向下取整，所以left<=mid<=right。
        //        if (nums[mid] == target) {
        //            return mid;
        //        } else if (nums[mid] < target) {//不包含mid,包含
        //            left = mid + 1;
        //        } else {// nums[mid]>target  不包含mid,包含mid-1
        //            right = mid - 1;
        //        }
        //    }
        //    // 当数组中没有与target相等的元素，while循环结束时，left指向大于mid指向的数字的第一个位置
        //    return left;//查找结束如果没有相等值则返回 left，该值为插入位置
        //}


        //// 二分查找：写法4
        //public int searchInsert(int[] nums, int target) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int length = nums.length;
        //    int left = 0;
        //    int right = length - 1;
        //    int ans = -1;
        //    while (left <= right) {
        //        int mid = (left + right) / 2;
        //        if (nums[mid] >= target) {
        //            ans = mid;
        //            right = mid - 1;
        //        } else {
        //            left = mid + 1;
        //        }
        //    }
        //    return ans == -1 ? nums.length : ans;
        //}


        //	写法3：找第一个大于等于target的位置
        //public int searchInsert(int[] nums, int target) {
        //     if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int length = nums.length;
        //    int left = 0;
        //    int right = length - 1;
        //    while (left <= right) {
        //        int mid = left + (right - left) / 2;
        //        if (nums[mid] >= target) {
        //            right = mid - 1;
        //        }else{
        //            left=mid+1;
        //        }
        //    }
        //    return left;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
