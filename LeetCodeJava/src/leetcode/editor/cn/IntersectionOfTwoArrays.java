/**
 * <p>给定两个数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code> ，返回 <em>它们的交集</em>&nbsp;。输出结果中的每个元素一定是
 * <strong>唯一</strong> 的。我们可以 <strong>不考虑输出结果的顺序</strong> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums1 = [1,2,2,1], nums2 = [2,2]
 * <strong>输出：</strong>[2]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * <strong>输出：</strong>[9,4]
 * <strong>解释：</strong>[4,9] 也是可通过的
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
 * <li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>双指针</li><li>二分查找</li><li>排序</li></div></div><br><div
 * ><li>👍 564</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 349
 * 两个数组的交集
 *
 * @author wangweizhou
 * @date 2022-06-30 16:26:20
 */

public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new IntersectionOfTwoArrays().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法1：计算两个数组的交集，直观的方法是遍历数组 nums1，对于其中的每个元素，遍历数组 nums2 判断该元素是否在数组 nums2 中，如果存在，则将该元素添加到返回值。
        // 如果使用哈希集合存储元素，则可以在 O(1)O(1) 的时间内判断一个元素是否在集合中，从而降低时间复杂度。

        /*
        public int[] intersection(int[] nums1, int[] nums2) {
            int length1 = nums1.length;
            int length2 = nums2.length;
            if (nums1 == null || nums2 == null || length1 == 0 || length2 == 0) {
                return null;
            }
            Set<Integer> set = new HashSet<>();
            Set<Integer> intersection = new HashSet<>();
            for (int i = 0; i < length1; i++) {
                set.add(nums1[i]);
            }
            for (int i = 0; i < length2; i++) {
                if (set.contains(nums2[i])) {
                    intersection.add(nums2[i]);
                }
            }
            int length = intersection.size();
            int[] ans = new int[length];
            int index = 0;
            // 遍历集合的方式
            for (int num : intersection) {
                ans[index++] =num;
            }
            return ans;
        }*/


    //    解法2：排序+双指针

    public int[] intersection(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        if (nums1 == null || nums2 == null || length1 == 0 || length2 == 0) {
            return null;
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int curr1=0;
        int curr2=0;
        int[] intersection=new int [Math.max(length1,length2)];
        int index=0;
        while(curr1<length1&&curr2<length2){
            if(nums1[curr1]==nums2[curr2]){
                if(index==0||intersection[index]!=intersection[index-1]){//后续放入的不能和前一个相同
                    intersection[index++]=nums1[curr1];
                }
                curr1++;
                curr2++;
            }else if(nums1[curr1]<nums2[curr2]){
                curr1++;
            }else{
                curr2++;
            }
        }
        return Arrays.copyOfRange(intersection,0,index);
    }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
