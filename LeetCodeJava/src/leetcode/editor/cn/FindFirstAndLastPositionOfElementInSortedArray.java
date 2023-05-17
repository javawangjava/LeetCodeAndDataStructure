/**
 * <p>给你一个按照非递减顺序排列的整数数组 <code>nums</code>，和一个目标值 <code>target</code>。请你找出给定目标值在数组中的开始位置和结束位置。</p>
 *
 * <p>如果数组中不存在目标值 <code>target</code>，返回&nbsp;<code>[-1, -1]</code>。</p>
 *
 * <p>你必须设计并实现时间复杂度为&nbsp;<code>O(log n)</code>&nbsp;的算法解决此问题。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [<code>5,7,7,8,8,10]</code>, target = 8
 * <strong>输出：</strong>[3,4]</pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [<code>5,7,7,8,8,10]</code>, target = 6
 * <strong>输出：</strong>[-1,-1]</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [], target = 0
 * <strong>输出：</strong>[-1,-1]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>9</sup>&nbsp;&lt;= nums[i]&nbsp;&lt;= 10<sup>9</sup></code></li>
 * <li><code>nums</code>&nbsp;是一个非递减数组</li>
 * <li><code>-10<sup>9</sup>&nbsp;&lt;= target&nbsp;&lt;= 10<sup>9</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 1753</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 34
 * 在排序数组中查找元素的第一个和最后一个位置
 */

public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        ////测试代码
        Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        int[] nums = {7, 7};
        ////int[] nums = {7,7};
        int[] ans = solution.searchRange(nums, 8);
        System.out.println(ans[0]);
        System.out.println(ans[1]);

        //int a = solution.binarySearchBigger(nums, 6);
        //System.out.println(a);

    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法3： 直接查找排序数组中第一个和最后一个目标数字
        // 我们先分析如何用二分查找算法在数组中找到第一个k。二分查找算法总是先拿数组中间的数字和k作比较。
        // 如果中间的数字比k大，那么k只有可能出现在数组的前半段，下一轮我们只在数组的前半段查找就可以了。
        // 如果中间的数字比k小，那么k只有可能出现在数组的后半段，下一轮我们只在数组的后半段查找就可以了。
        // 如果中间的数字和k相等呢？我们先判断这个数字是不是第一个k。
        // 如果中间数字的前面一个数字不是k,那么此时中间的数字刚好就是第一个k;如果中间数字的前面一个数字也是k,那么第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。
        // 在函数GetFirstK中， 1 如果数组中不包含数字k， 那么返回-1； 如果数组中包含至少一个k， 那么返回第一个k在数组中的下标。

        // 我们可以用同样的思路在排序数组中找到最后一个k。
        // 如果中间数字比大，那么只能出现在数组的前半段。如果中间数字比小，那么k只能出现在数组的后半段。
        // 如果中间数字等于k呢？我们需要判断这个k是不是最后一个k，也就是中间数字的下一个数字是不是也等于k。
        // 如果下一个数字不是k，则中间数字就是最后一个k：否则下一轮我们还是要在数组的后半段中去查找。

        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) {
                return new int[]{-1, -1};
            }
            int[] ans = new int[]{-1, -1};
            ans[0] = getFirstTarget(nums, target);
            ans[1] = getLastTarget(nums, target);
            return ans;
        }


        // 找到第一个等于target的数，使用二分查找找到一个等于target的元素之后，从该元素的位置向前找
        private int getFirstTarget(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {// 如果中间的数字和k相等呢？我们先判断这个数字是不是第一个k。
                    // 如果中间数字的前面一个数字不是k,那么此时中间的数字刚好就是第一个k;
                    // 如果中间数字的前面一个数字也是k,那么第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。
                    if (mid == 0 || nums[mid - 1] != target) {
                        return mid;
                    } else {
                        right = mid - 1;
                    }
                } else if (nums[mid] > target) {// 如果中间的数字比k大，那么k只有可能出现在数组的前半段，下一轮我们只在数组的前半段查找就可以了。
                    right = mid - 1;
                } else {// 如果中间的数字比k小，那么k只有可能出现在数组的后半段，下一轮我们只在数组的后半段查找就可以了。
                    left = mid + 1;
                }
            }
            return -1;
        }


        // 找到最后一个等于target的数，使用二分查找找到一个等于target的元素之后，从该元素的位置向后找
        private int getLastTarget(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {// 如果中间的数字和k相等呢？我们先判断这个数字是不是第一个k。
                    // 如果中间数字的前面一个数字不是k,那么此时中间的数字刚好就是第一个k;如果中间数字的前面一个数字也是k,那么第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。
                    if (mid == nums.length - 1 || nums[mid + 1] != target) {
                        return mid;
                    } else {
                        left = mid + 1;
                    }
                } else if (nums[mid] > target) {// 如果中间的数字比k大，那么k只有可能出现在数组的前半段，下一轮我们只在数组的前半段查找就可以了。
                    right = mid - 1;
                } else {// 如果中间的数字比k小，那么k只有可能出现在数组的后半段，下一轮我们只在数组的后半段查找就可以了。
                    left = mid + 1;
                }
            }
            return -1;
        }




        //// 解法3： 直接查找排序数组中第一个和最后一个目标数字     递归实现
        //public int[] searchRange(int[] nums, int target) {
        //    if (nums == null || nums.length == 0) {
        //        return new int[]{-1, -1};
        //    }
        //    int[] ans = new int[]{-1, -1};
        //    ans[0] = getFirstTarget(nums, 0, nums.length - 1, target);
        //    ans[1] = getLastTarget(nums, 0, nums.length - 1, target);
        //    return ans;
        //}
        //
        //
        //// 递归实现
        //// 我们先分析如何用二分查找算法在数组中找到第一个k。二分查找算法总是先拿数组中间的数字和k作比较。
        //// 如果中间的数字比k大，那么k只有可能出现在数组的前半段，下一轮我们只在数组的前半段查找就可以了。
        //// 如果中间的数字比k小，那么k只有可能出现在数组的后半段，下一轮我们只在数组的后半段查找就可以了。
        //// 如果中间的数字和k相等呢？我们先判断这个数字是不是第一个k。
        //// 如果中间数字的前面一个数字不是k,那么此时中间的数字刚好就是第一个k;如果中间数字的前面一个数字也是k,那么第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。
        //// 在函数GetFirstK中， 1 如果数组中不包含数字k， 那么返回-1； 如果数组中包含至少一个k， 那么返回第一个k在数组中的下标。
        //
        //private int getFirstTarget(int[] nums, int left, int right, int target) {
        //    if (nums == null || nums.length == 0 || left > right) {
        //        return -1;
        //    }
        //    int mid = (left + right) >> 1;
        //    if (nums[mid] == target) {// 如果中间的数字和k相等呢？我们先判断这个数字是不是第一个k。
        //        // 如果中间数字的前面一个数字不是k,那么此时中间的数字刚好就是第一个k:如果中间数字的前面一个数字也是k,那么第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。
        //        if ((mid > 0 && nums[mid - 1] != target) || mid == 0) {
        //            return mid;
        //        } else {
        //            right = mid - 1;
        //        }
        //    } else if (nums[mid] > target) {// 如果中间的数字比k大，那么k只有可能出现在数组的前半段，下一轮我们只在数组的前半段查找就可以了。
        //        right = mid - 1;
        //    } else {// 如果中间的数字比k小，那么k只有可能出现在数组的后半段，下一轮我们只在数组的后半段查找就可以了。
        //        left = mid + 1;
        //    }
        //    return getFirstTarget(nums, left, right, target);
        //}
        //
        //
        //// 我们可以用同样的思路在排序数组中找到最后一个k。
        //// 如果中间数字比大，那么只能出现在数组的前半段。如果中间数字比小，那么k只能出现在数组的后半段。
        //// 如果中间数字等于k呢？我们需要判断这个k是不是最后一个k，也就是中间数字的下一个数字是不是也等于k。
        //// 如果下一个数字不是k，则中间数字就是最后一个k：否则下一轮我们还是要在数组的后半段中去查找。
        //
        //private int getLastTarget(int[] nums, int left, int right, int target) {
        //    if (nums == null || nums.length == 0 || left > right) {
        //        return -1;
        //    }
        //    int mid = (left + right) >> 1;
        //    if (nums[mid] == target) {// 如果中间的数字和k相等呢？我们先判断这个数字是不是第一个k。
        //        // 如果中间数字的前面一个数字不是k,那么此时中间的数字刚好就是第一个k:如果中间数字的前面一个数字也是k,那么第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。
        //        if ((mid < nums.length - 1 && nums[mid + 1] != target) || mid == nums
        //                .length - 1) {
        //            return mid;
        //        } else {
        //            left = mid + 1;
        //        }
        //    } else if (nums[mid] > target) {// 如果中间的数字比k大，那么k只有可能出现在数组的前半段，下一轮我们只在数组的前半段查找就可以了。
        //        right = mid - 1;
        //    } else {// 如果中间的数字比k小，那么k只有可能出现在数组的后半段，下一轮我们只在数组的后半段查找就可以了。
        //        left = mid + 1;
        //    }
        //    return getLastTarget(nums, left, right, target);
        //}





        //  解法2：
        //public int[] searchRange(int[] nums, int target) {
        //    if (nums == null || nums.length == 0) {
        //        return new int[]{-1, -1};
        //    }
        //    int len = nums.length;
        //    int first = binarySearchBigger(nums, target - 1);// 查找(第一个大于target-1)的值的位置就是target的第一个位置
        //    int second = binarySearchBigger(nums, target) - 1;// 查找(第一个大于target的位置-1)就是target最后一个位置
        //    // 注意first和second可能越界，所以要判断是否越界
        //    if (first < len && second < len && nums[first] == target && nums[second] == target) {
        //        return new int[]{first, second};
        //    }
        //    return new int[]{-1, -1};
        //}
        //
        //
        //// 二分法 查找第一个大于 target 的值
        //// 注意这个一定返回的是比目标值大的下一位的索引，可能会越界，那么在后面使用时需要对 left 进行判断，不能直接返回。
        //// 注意：结束时：left > right，如果 target 即为最大值，则会产生数组越界，需要对 left 进行判断，不能直接返回。
        //private int binarySearchBigger(int[] nums, int target) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int left = 0;
        //    int right = nums.length - 1;
        //    // 循环结束条件：left > right。
        //    while (left <= right) {
        //        int mid = (left + right) / 2;
        //        if (nums[mid] <= target) {
        //            left = mid + 1;
        //        } else {// nums[mid] > target:
        //            right = mid - 1;
        //        }
        //    }
        //    return left;
        //}







       /*
       // 解法1：  暴力法两次遍历。从左向右去遍历，从右向左遍历
        public int[] searchRange(int[] nums, int target) {
            int length = nums.length;
            int ans[] = {-1, -1};
            if (length == 0) {//nums为空
                return ans;
            }
            if (nums[0] > target || nums[length - 1] < target) {//nums非空，但是target不在数组范围内
                return ans;
            }
            // 从左向右遍历，第一次出现位置
            for (int i = 0; i < length; i++) {
                if(nums[i]==target){
                    ans[0]=i;
                    break;
                }
            }
            // 从右向左遍历，第一次出现的位置就是从左向右遍历最后一次出现的位置
            for (int i = length-1; i >=0 ; i--) {
                if(nums[i]==target){
                    ans[1]=i;
                    break;
                }
            }
            return ans;
        }
        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
