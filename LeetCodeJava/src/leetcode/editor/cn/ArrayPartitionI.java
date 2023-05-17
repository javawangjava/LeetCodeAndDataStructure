/**
 * <p>给定长度为 <code>2n</code><strong> </strong>的整数数组 <code>nums</code> ，你的任务是将这些数分成 <code>n</code><strong> </strong>对,
 * 例如 <code>(a<sub>1</sub>, b<sub>1</sub>), (a<sub>2</sub>, b<sub>2</sub>), ..., (a<sub>n</sub>, b<sub>n</sub>)
 * </code> ，使得从 <code>1</code> 到 <code>n</code> 的 <code>min(a<sub>i</sub>, b<sub>i</sub>)</code> 总和最大。</p>
 *
 * <p>返回该 <strong>最大总和</strong> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,4,3,2]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>所有可能的分法（忽略元素顺序）为：
 * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
 * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
 * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
 * 所以最大总和为 4</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [6,2,6,5,1,2]
 * <strong>输出：</strong>9
 * <strong>解释：</strong>最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= n <= 10<sup>4</sup></code></li>
 * <li><code>nums.length == 2 * n</code></li>
 * <li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>计数排序</li><li>排序</li></div></div><br><div><li>👍
 * 303</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 561
 * 数组拆分 I
 * @author wangweizhou
 * @date 2022-07-04 20:03:31
 */
public class ArrayPartitionI {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ArrayPartitionI().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 题目要求每一对中较小值的和最大，那么就要使得每一对中的较小值尽可能大。则每次在剩余元素中选最小的两个组成一队。


        /*
        // 解法1：分成n对，每一对的最小值的总和最大，那么就排序。
        // 排序之后，奇数和随后的偶数组成一对。然后把所有的奇数相加，就是所求

        public int arrayPairSum(int[] nums) {
            if (nums == null || nums.length == 0) {
                return Integer.MIN_VALUE;// 题干没有说如何处理
            }
            Arrays.sort(nums);
            int sum = 0;
            for (int i = 0; i < nums.length; i = i + 2) {
                sum += nums[i];
            }
            return sum;
        }
        */


       /*
        public int arrayPairSum(int[] nums) {




        }
*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}
