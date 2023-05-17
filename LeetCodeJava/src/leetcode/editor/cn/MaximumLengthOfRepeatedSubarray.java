/**
 * <p>给两个整数数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;，返回 <em>两个数组中 <strong>公共的</strong>
 * 、长度最长的子数组的长度&nbsp;</em>。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * <strong>输出：</strong>3
 * <strong>解释：</strong>长度最长的公共子数组是 [3,2,1] 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * <strong>输出：</strong>5
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
 * <li><code>0 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>动态规划</li><li>滑动窗口</li><li>哈希函数</li><li>滚动哈希</li
 * ></div></div><br><div><li>👍 770</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 718
 * 最长重复子数组
 *
 * @author wangweizhou
 * @date 2022-08-09 02:47:40
 */


public class MaximumLengthOfRepeatedSubarray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MaximumLengthOfRepeatedSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // ⽅法1：动态规划
        public int findLength(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
                return 0;
            }
            int len1 = nums1.length;
            int len2 = nums2.length;
            int maxLen = 0;
            int[][] dp = new int[len1 + 1][len2 + 1];
            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 0;
                    }
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
            return maxLen;
        }




        ////	方法二：滑动窗口
        //public int findLength(int[] nums1, int[] nums2) {
        //    if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
        //        return 0;
        //    }
        //    int len1 = nums1.length;
        //    int len2 = nums2.length;
        //    int result = 0;
        //    for (int i = 0; i < len1; i++) {
        //        int len = Math.min(len2, len1 - i);
        //        int maxLen = maxLengthFunc(nums1, i, nums2, 0, len);
        //        result = Math.max(result, maxLen);
        //    }
        //    for (int i = 0; i < len2; i++) {
        //        int len = Math.min(len1, len2 - i);
        //        int maxLen = maxLengthFunc(nums1, 0, nums2, i, len);
        //        result = Math.max(result, maxLen);
        //    }
        //    return result;
        //}
        //
        //
        //private int maxLengthFunc(int[] nums1, int index1, int[] nums2, int index2, int len) {
        //    int maxLength = 0;
        //    int count = 0;
        //    for (int i = 0; i < len; i++) {
        //        if (nums1[index1 + i] == nums2[index2 + i]) {
        //            count++;
        //        } else {
        //            count = 0;
        //        }
        //        maxLength = Math.max(maxLength, count);
        //    }
        //    return maxLength;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
