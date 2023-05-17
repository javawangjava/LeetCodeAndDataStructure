/**
 * /**
 * <p>给定一个二进制数组 <code>nums</code> ， 计算其中最大连续 <code>1</code> 的个数。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,1,0,1,1,1]
 * <strong>输出：</strong>3
 * <strong>解释：</strong>开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <b>输入：</b>nums = [1,0,1,1,0,1]
 * <b>输出：</b>2
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>nums[i]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code>.</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li></div></div><br><div><li>👍 326</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 485
 * 最大连续 1 的个数
 *
 * @author wangweizhou
 * @date 2022-06-29 20:06:26
 */
public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MaxConsecutiveOnes().new Solution();
        int[] a = {1, 1, 0, 0, 0, 1};
        int ans = solution.findMaxConsecutiveOnes(a);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


       /*

        // 解法2：双指针-快慢指针
        public int findMaxConsecutiveOnes(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            int left = 0;
            int right = 0;
            int ans = 0;
            while (right < length) { // 使用right遍历整个数组
                if (nums[right] == 0) {// 当right遍历到数组的元素为0时，更新最大长度，更新left指向的位置
                    ans = Math.max(ans, right - left);
                    left = right + 1;// 当前right指向的元素是0，更新left指向当前位置的下一位，下一位可能是1。
                }
                // 单独处理数组最后一个元素是1的情形
                if (right == length - 1 && nums[right] == 1) {// 当数组的最后一个元素就是1时
                    ans = Math.max(ans, right - left+1);
                }
                right++;
            }
            return ans;
        }
        */




        // 解法1：一次遍历：存储最大的长度

        // 解法1：为了得到数组中最大连续 1的个数，需要遍历数组，并记录最大的连续 1 的个数和当前的连续 1 的个数。
        // 如果当前元素是 1，则将当前的连续 1 的个数加 1，否则，使用之前的连续 1的个数更新最大的连续 1 的个数，并将当前的连续 1 的个数清零。
        // 遍历数组结束之后，需要再次使用当前的连续 1 的个数更新最大的连续 1 的个数，因为数组的最后一个元素可能是 1，且最长连续 1 的子数组可能出现在数组的末尾，
        // 如果遍历数组结束之后不更新最大的连续1的个数，则会导致结果错误。


        public int findMaxConsecutiveOnes(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int max = 0;
            int num = 0;
            int length = nums.length;

            for (int i = 0; i < length; i++) {
                // 若二进制末尾是1的话，（num++）更新了，但是并没有更新最大长度。
                if (nums[i] == 1) {
                    num ++;
                } else {
                    max = Math.max(max, num);
                    num = 0;
                }
            }
            // 单独处理数组最后一个元素是1的情形
            if (nums[length - 1] == 1) {
                max = Math.max(max, num);
            }
            return max;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
