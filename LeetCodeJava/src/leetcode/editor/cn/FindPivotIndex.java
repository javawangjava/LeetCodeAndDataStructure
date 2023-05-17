/**
 * <p>给你一个整数数组&nbsp;<code>nums</code> ，请计算数组的 <strong>中心下标 </strong>。</p>
 *
 * <p>数组<strong> 中心下标</strong><strong> </strong>是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。</p>
 *
 * <p>如果中心下标位于数组最左端，那么左侧数之和视为 <code>0</code> ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。</p>
 *
 * <p>如果数组有多个中心下标，应该返回 <strong>最靠近左边</strong> 的那一个。如果数组不存在中心下标，返回 <code>-1</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1, 7, 3, 6, 5, 6]
 * <strong>输出：</strong>3
 * <strong>解释：</strong>
 * 中心下标是 3 。
 * 左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
 * 右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1, 2, 3]
 * <strong>输出：</strong>-1
 * <strong>解释：</strong>
 * 数组中不存在满足此条件的中心下标。</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2, 1, -1]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>
 * 中心下标是 0 。
 * 左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
 * 右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>注意：</strong>本题与主站 1991 题相同：
 * <a href="https://leetcode-cn.com/problems/find-the-middle-index-in-array/" target="_blank">https://leetcode-cn.com/problems/find-the-middle-index-in-array/</a></p>
 * <div><div>Related Topics</div><div><li>数组</li><li>前缀和</li></div></div><br><div><li>👍 413</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 724
 * 寻找数组的中心下标
 *
 * @author wangweizhou
 * @date 2022-06-22 22:46:10
 */

public class FindPivotIndex {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new FindPivotIndex().new Solution();
        //int[] nums={1,7,3,6,5,6};
        int[] nums = {1, -1, 1};
        int ans = solution.pivotIndex(nums);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法1：前缀和数组
        public int pivotIndex(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int len = nums.length;
            int[] sums = new int[len + 1];

            // 注意这里的前缀和是相对于原元素数组后移一位的
            //  sums[i+1]表示nums[0]到nums[i]的前缀和
            for (int i = 0; i < len; i++) {
                sums[i+1] = sums[i] + nums[i];
            }

            for (int i = 0; i < len; i++) {
                if (sums[i+1] - nums[i] ==sums[len] - sums[i+1]) {
                    return i;
                }
            }
            return -1;
        }




        // 假设从头到尾扫描数组中的每个数字。当扫描到第i个数字时，它左边的子数组的数字之和就是从第1个数字开始累加到第i-1个数字的和。
        // 此时它右边的子数组的数字之和就是从第i+1个数字开始累加到最后一个数字的和，这个和等于数组中所有数字之和减去从第1个数字累加到第i个数字的和。
        // 如果从数组的第1个数字开始扫描并逐一累加扫描到的数字，当扫描到第i个数字的时候，就可以知道累加到第i个数字的和，这个和减去第i个数字就是累加到第i-1个数字的和。
        // 同时，要知道数组中的所有数字之和，只需要从头到尾扫描一次数组就可以。

        // 解法1：前缀和改进+动态数组
        // 并不需要创建数组保存前i项的和，只需要知道数组总和和左侧元素之和，就可以计算出右侧所有元素之和
        //public int pivotIndex(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int length = nums.length;
        //    int toatlSum = 0;//数组元素总和
        //
        //    //遍历求数组的总和
        //    for (int num : nums) {
        //        toatlSum += num;
        //    }
        //
        //    int Sum = 0;//表示子数组[0,i]的前i项和,表示截止nums[i]的前项和
        //    for (int i = 0; i < length; i++) {
        //        Sum += nums[i];
        //        //数组中心下标是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
        //        //if (Sum - nums[i] == toatlSum - Sum) {
        //        if (2* Sum == toatlSum+nums[i]) {
        //            return i;
        //        }
        //    }
        //    return -1;
        //}



    /*
        //	 解法2：前缀和 写法1
        // 单独创建一个数组来存储数组nums的前i项和。sums[i]表示截止元素nums[i]【包含nums[i]】为止的前项和。
        // 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标0的左侧不存在元素。

        public int pivotIndex(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int length = nums.length;
            //sums[i]表示截止元素nums[i]【包含nums[i]】为止的前项和
            int[] sums = new int[length];
            sums[0] = nums[0];
            for (int i = 1; i < length; i++) {
                sums[i] = sums[i - 1] + nums[i];//前i项和等于前（i-1）项和+第i项
            }
            // 当前下标i,假定当前下标是中心下标
            // sums[i]表示截止元素nums[i]【包含nums[i]】为止的前项和
            for (int i = 0; i < length; i++) {
                if (sums[i] - nums[i] == sums[length - 1] - sums[i]) {
                    return i;
                }
            }
            return -1;
        }
*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
