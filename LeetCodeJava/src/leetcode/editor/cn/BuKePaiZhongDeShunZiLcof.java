/**
 * <p>从<strong>若干副扑克牌</strong>中随机抽 <code>5</code> 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0
 * ，可以看成任意数字。A 不能视为 14。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> [1,2,3,4,5]
 * <strong>输出:</strong> True</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> [0,0,1,2,5]
 * <strong>输出:</strong> True</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <p>数组长度为 5&nbsp;</p>
 *
 * <p>数组的数取值为 [0, 13] .</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>排序</li></div></div><br><div><li>👍 272</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 剑指 Offer 61
 * 扑克牌中的顺子
 *
 * @author wangweizhou
 * @date 2022-09-14 11:25:36
 */

public class BuKePaiZhongDeShunZiLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new BuKePaiZhongDeShunZiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //// 写法1：
        //public boolean isStraight(int[] nums) {
        //    if (nums == null || nums.length != 5) {
        //        return false;
        //    }
        //    int len = nums.length;
        //    int jkcnt = 0;// 大小王个数
        //    Arrays.sort(nums);
        //    for (int i = 0; i < len; i++) {
        //        // 统计0的个数,也就是大小王的个数
        //        if (nums[i] == 0) {// 大小王可以作为任意牌，即可以作为牌间空隙插入。且数量不限
        //            jkcnt++;
        //        }else if(i != len - 1 && nums[i] == nums[i + 1]){// 同时这个也暗含该牌不是大小王
        //            return false;// 有相同的牌，则不是顺子
        //        }
        //    }
        //    // jkcnt就是排序后数组最小值的位置，nums[jkcnt]就是排序后数组的最小值，nums[len - 1]为排序后数组的最大值
        //    // 最大牌 - 最小牌 < 5 则可构成顺子
        //    return nums[len - 1] - nums[jkcnt] < 5;// 数组除0外的数最大值最小值差值必须在4以及4以内。
        //}




        // 写法2：
        public boolean isStraight(int[] nums) {
            if (nums == null || nums.length != 5) {
                return false;
            }
            int len = nums.length;
            int jkcnt = 0;// 大小王个数
            Arrays.sort(nums);
            for (int i = 0; i < len; i++) {
                // 一个顺子中除了任意牌之外，不能再有相同的牌。
                // nums[i] != 0不是任意牌 。i < len - 1 && nums[i] == nums[i + 1]：有相同的牌。因为要和后一位相比较，那么要判断不能越界
                if (nums[i] != 0 && i < len - 1 && nums[i] == nums[i + 1]) {// 牌间不可以有相同的牌，（大小王除外）
                    return false;
                }
                // 统计0的个数
                if (nums[i] == 0) {// 大小王可以作为任意牌，即可以作为牌间空隙插入。且数量不限
                    jkcnt++;
                }
            }
            // jkcnt就是排序后数组最小值的位置，nums[jkcnt]就是排序后数组的最小值，nums[len - 1]为排序后数组的最大值
            // 开区间（jkcnt，len-1]的长度。除任意牌之外，最大牌和最小牌的差值必须小于等于4
            return nums[len - 1] - nums[jkcnt] < 5;// 数组除0外的数最大值最小值差值必须在4以及4以内。
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
