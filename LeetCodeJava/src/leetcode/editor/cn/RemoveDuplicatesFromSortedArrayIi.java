/**
 * <p>给你一个有序数组 <code>nums</code> ，请你<strong>
 * <a href="http://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"> 原地</a>
 * </strong> 删除重复出现的元素，使得出现次数超过两次的元素<strong>只出现两次</strong> ，返回删除后数组的新长度。</p>
 *
 * <p>不要使用额外的数组空间，你必须在 <strong>
 * <a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地 </a>修改输入数组
 * </strong>并在使用 O(1) 额外空间的条件下完成。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>说明：</strong></p>
 *
 * <p>为什么返回数值是整数，但输出的答案是数组呢？</p>
 *
 * <p>请注意，输入数组是以<strong>「引用」</strong>方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。</p>
 *
 * <p>你可以想象内部操作如下:</p>
 *
 * <pre>
 * // <strong>nums</strong> 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中<strong> 该长度范围内</strong> 的所有元素。
 * for (int i = 0; i &lt; len; i++) {
 * &nbsp; &nbsp; print(nums[i]);
 * }
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,1,1,2,2,3]
 * <strong>输出：</strong>5, nums = [1,1,2,2,3]
 * <strong>解释：</strong>函数应返回新长度 length = <strong><code>5</code></strong>, 并且原数组的前五个元素被修改为 <strong><code>1, 1, 2, 2,</code></strong> <strong>3 </strong>。 不需要考虑数组中超出新长度后面的元素。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,0,1,1,1,1,2,3,3]
 * <strong>输出：</strong>7, nums = [0,0,1,1,2,3,3]
 * <strong>解释：</strong>函数应返回新长度 length = <strong><code>7</code></strong>, 并且原数组的前五个元素被修改为&nbsp;<strong><code>0</code></strong>, <strong>0</strong>, <strong>1</strong>, <strong>1</strong>, <strong>2</strong>, <strong>3</strong>, <strong>3 。</strong> 不需要考虑数组中超出新长度后面的元素。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
 * <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * <li><code>nums</code> 已按升序排列</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li></div></div><br><div><li>👍 707</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 删除有序数组中的重复项 II
 *
 * @author wangweizhou
 * @date 2022-06-28 22:03:52
 */
public class RemoveDuplicatesFromSortedArrayIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new RemoveDuplicatesFromSortedArrayIi().new Solution();

        int[] nums = {1, 2, 2, 3, 3, 4, 4};
        int ans = solution.removeDuplicates(nums);

        for (int i = 0; i < ans; i++) {
            System.out.println(nums[i]);
        }
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //	「保留 k 位」通用解法

        //由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留
        //对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留

       /*
        public int removeDuplicates(int[] nums,int k) {

            if (nums == null) {
                return 0;
            }
            int length = nums.length;
            if (length <= k) {
                return length;
            }

            int left = k;
            int right = k;

            // right用来遍历整个数组，快指针表示已经检查过的数组的长度，即 nums[fast] 表示待检查的第一个元素
            // left左侧表示已经排序好的子数组，也就是慢指针left表示处理出的数组的长度。nums[slow−1] 为上一个应该被保留的元素所移动到的指定位置。
            // 本题要求相同元素最多出现两次而非一次，所以我们需要检查上上个应该被保留的元素 nums[slow−k] 是否和当前待检查元素nums[fast] 相同。
            // 当且仅当 nums[slow−k]=nums[fast] 时，当前待检查元素nums[fast] 不应该被保留（因为此时必然有 nums[slow−k]=nums[slow−1]=nums[fast]）。
            while (right < length) {
                if (nums[left - k] != nums[right]) {
                    nums[left] = nums[right];
                    left++;
                }
                right++;
            }
            return left;
        }*/

        // right用来遍历整个数组，快指针表示已经检查过的数组的长度，即 nums[fast] 表示待检查的第一个元素
        // left左侧表示已经排序好的子数组，也就是慢指针left表示处理出的数组的长度。nums[slow−1] 为上一个应该被保留的元素所移动到的指定位置。
        // 本题要求相同元素最多出现两次而非一次，所以我们需要检查上上个应该被保留的元素 nums[slow−2] 是否和当前待检查元素nums[fast] 相同。
        // 当且仅当 nums[slow−2]=nums[fast] 时，当前待检查元素nums[fast] 不应该被保留（因为此时必然有 nums[slow−2]=nums[slow−1]=nums[fast]）。
        // 最后，slow 即为处理好的数组的长度。


        public int removeDuplicates(int[] nums) {
            int length = nums.length;
            if (nums == null) {
                return 0;
            }
            if (length <= 2) {
                return length;
            }

            int left = 2;
            int right = 2;

            // right用来遍历整个数组，快指针表示已经检查过的数组的长度，即 nums[fast] 表示待检查的第一个元素
            // left左侧表示已经排序好的子数组，也就是慢指针left表示处理出的数组的长度。nums[slow−1] 为上一个应该被保留的元素所移动到的指定位置。
            // 本题要求相同元素最多出现两次而非一次，所以我们需要检查上上个应该被保留的元素 nums[slow−2] 是否和当前待检查元素nums[fast] 相同。
            // 当且仅当 nums[slow−2]=nums[fast] 时，当前待检查元素nums[fast] 不应该被保留（因为此时必然有 nums[slow−2]=nums[slow−1]=nums[fast]）。
            while (right < length) {
                if (nums[left - 2] != nums[right]) {
                    nums[left] = nums[right];
                    left++;
                }
                right++;
            }
            return left;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
