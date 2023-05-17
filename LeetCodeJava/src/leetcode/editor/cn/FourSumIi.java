/**
 * <p>给你四个整数数组 <code>nums1</code>、<code>nums2</code>、<code>nums3</code> 和 <code>nums4</code> ，数组长度都是 <code>n</code>
 * ，请你计算有多少个元组 <code>(i, j, k, l)</code> 能满足：</p>
 *
 * <ul>
 * <li><code>0 &lt;= i, j, k, l &lt; n</code></li>
 * <li><code>nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * <strong>输出：</strong>2
 * <strong>解释：</strong>
 * 两个元组如下：
 * 1. (0, 0, 0, 1) -&gt; nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -&gt; nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p>&nbsp; <strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == nums1.length</code></li>
 * <li><code>n == nums2.length</code></li>
 * <li><code>n == nums3.length</code></li>
 * <li><code>n == nums4.length</code></li>
 * <li><code>1 &lt;= n &lt;= 200</code></li>
 * <li><code>-2<sup>28</sup> &lt;= nums1[i], nums2[i], nums3[i], nums4[i] &lt;= 2<sup>28</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 595</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;

/**
 * 454
 * 四数相加 II
 * @author wangweizhou
 * @date 2022-07-06 17:23:32
 */
public class FourSumIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new FourSumIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 方法一：分组 + 哈希表
        // 我们以存 AB 两数组之和为例。对于 A 和 B，我们使用二重循环对它们进行遍历,首先求出 A 和 B 任意两数之和 sumAB，以 sumAB 为 key，sumAB 出现的次数为 value，存入
        // hashmap 中。
        // 对于 C 和 D，我们同样使用二重循环对它们进行遍历。然后计算 C 和 D 中任意两数之和的相反数 -sumCD，在 hashmap 中查找是否存在 key 为 -sumCD。
        // 如果 -sumCD 出现在哈希映射中，那么将 -sumCD 对应的值累加进答案中。
        // 算法时间复杂度为 O(n2)。

        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            //if (nums1.length == 0) {//这个其实不需要，因为题干已经告诉长度大于等于1
            //    return 0;
            //}

            int length = nums1.length;//四个数组长度都一样

            HashMap<Integer, Integer> numsAB = new HashMap<>();//一个哈希表就可以了
            for (int numA : nums1) {
                for (int numB : nums2) {
                    int sumAB = numA + numB;
                    numsAB.put(sumAB, numsAB.getOrDefault(sumAB, 0) + 1);

                    // 下面if-else 语句和上面numsAB.put(sumAB,numsAB.getOrDefault(sumAB,0)+1);效果一样
                    //if(numsAB.containsKey(sumAB)){
                    //	numsAB.put(sumAB,numsAB.get(sumAB)+1);
                    //}else{
                    //	numsAB.put(sumAB,1);
                    //}
                }
            }

            int count = 0;
            for (int numC : nums3) {
                for (int numD : nums4) {
                    int target = -numC - numD;
                    if (numsAB.containsKey(target)) {
                        count+=numsAB.get(target);
                    }
                }
            }
            return count;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
