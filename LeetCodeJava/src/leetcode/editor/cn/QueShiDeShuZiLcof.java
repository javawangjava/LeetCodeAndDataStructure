/**
 * <p>一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre><strong>输入:</strong> [0,1,3]
 * <strong>输出:</strong> 2
 * </pre>
 *
 * <p><strong>示例&nbsp;2:</strong></p>
 *
 * <pre><strong>输入:</strong> [0,1,2,3,4,5,6,7,9]
 * <strong>输出:</strong> 8</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <p><code>1 &lt;= 数组长度 &lt;= 10000</code></p>
 * <div><div>Related Topics</div><div><li>位运算</li><li>数组</li><li>哈希表</li><li>数学</li><li>二分查找</li></div></div><br><div
 * ><li>👍 312</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 53 - II
 * 0～n-1中缺失的数字
 *
 * @author wangweizhou
 * @date 2022-09-14 00:18:18
 */

public class QueShiDeShuZiLcof {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new QueShiDeShuZiLcof().new Solution();
        int[] nums = {0, 1, 3, 4};
        int ans = solution.missingNumber(nums);
        System.out.println(ans);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法2：二分法
        // 找出数组中第一个nums[i]>i【或者说nums[i]!=i】的数组下标
        public int missingNumber(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == mid) {// 当前位置的元素和下标相等，那么缺失的在右半边
                    left = mid + 1;
                } else if (nums[mid] > mid) {// 当前位置的元素大于下标，那么缺失的在包含中间位置的左半边
                    if (mid == 0 || nums[mid - 1] == mid - 1) {// 当中间位置是第一个时或者中间位置的前一个位置元素和对应下标相同，那么就找到了缺失的元素
                        return mid;
                    } else {// 中间位置不是第一个位置且前一个位置的元素和下标不相同，那么缺失的在左半边
                        right = mid - 1;
                    }
                }
            }
            return nums.length;// 当前面没找到时那就表明这时缺少最后一个元素
        }




        //// 解法1：
        //public int missingNumber(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int left = 0;
        //    int right = nums.length;
        //    while (left < right) {
        //        int mid = (left + right) / 2;
        //        if (nums[mid] == mid) {
        //            left = mid + 1;
        //        } else {
        //            right = mid;
        //        }
        //    }
        //    return left;
        //}

    }
//leetcode submit region end(Prohibit modification and deletion)

}
