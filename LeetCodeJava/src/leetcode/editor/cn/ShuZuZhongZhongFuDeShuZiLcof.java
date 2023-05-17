/**
 * <p>找出数组中重复的数字。</p>
 *
 * <p><br>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>
 * [2, 3, 1, 0, 2, 5, 3]
 * <strong>输出：</strong>2 或 3
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <p><code>2 &lt;= n &lt;= 100000</code></p>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>排序</li></div></div><br><div><li>👍 949</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 03
 * 数组中重复的数字
 *
 * @author wangweizhou
 * @date 2022-09-14 19:28:57
 */

/**
 * 442  和上面的对应起来学
 * 数组中重复的数据
 *
 * @author wangweizhou
 * @date 2022-09-19 19:17:10
 */

public class ShuZuZhongZhongFuDeShuZiLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ShuZuZhongZhongFuDeShuZiLcof().new Solution();
        //int[] nums = {1,2,4,2,5,3};
        //int[] nums = {1,2,4,2,1,5,3};
        //int[] nums = {1,2,4,5,3};
        int[] nums = {4, 2, 0, 4, 3};
        int ans = solution.findRepeatNumber(nums);
        System.out.println(ans);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解题思路：
        // 数组中的数字都在0~n-1的范围内。如果这个数组中没有重复的数字，那么当数组排序之后数字i将出现在下标为i的位置。
        // 也就是函数自变量和函数值是一样的。函数自变量是数组下标，函数因变量是数组中数组元素的值。
        // 如果数组中有重复的数字，那么有些位置可能存在多个数字，同时有些位置可能没有数字。也就是多个位置对应着同一个元素的映射。


        // 核心就是在遍历数组的过程中将数组的第i个元素nums[i]映射到第i个位置（下标i）。

        // 1.当扫描到下标为i的数字nums[i]时，记第i个元素是m（nums[i]=m）。
        // 2首先比较这个数字(用m表示)是不是等于i。nums[i] == i？检查数组中第i个元素nums[i]是否出现在第i个位置（下标i）。
        // 2.1如果是，数组中第i个元素nums[i]出现在第i个位置（下标i），说明此数字已在对应索引位置，无需交换，则接着扫描下一个数字;
        // 2.2如果不是，则再拿数字m和第m个数字进行比较。nums[nums[i]]==nums[i] ？
        // 3.1如果它和第m个数字相等，就找到了一个重复的数字（该数字m在下标为i和m的位置都出现了）;
        // 3.2如果它和第m个数字不相等，就把第i个数字和第m个数字交换，把m放到下标为m的位置【交换索引为 i 和 nums[i] 的元素值，将此数字交换至对应索引位置。】。
        // 接下来再重复这个比较、交换的过程，直到我们发现一个重复的数字。

        // 解法2：哈希表  数组模拟哈希表 写法2
        public int findRepeatNumber(int[] nums) {
            if (nums == null || nums.length == 0) {// 判空
                return -1;
            }

            int len = nums.length;
            for (int i = 0; i < len; i++) {
                if (nums[i] < 0 || nums[i] > len - 1) {  // 判断数组元素是否越界,力扣的测试用例不包含越界的测试用例
                    return -1;
                }
                //若 nums[i]=i ： 说明此数字已在对应索引位置，无需交换，因此跳过,进入下一个位置；
                if (nums[i] != i) {// nums[i] != i；当nums[i]没有放在对应的位置i时，也就是数字m出现在了下标为i的地方
                    if (nums[nums[i]] == nums[i]) {
                        // nums[nums[i]]==nums[i] ，即 nums[m]=nums[i]=m，（数字m也就是元素值为m的元素在下标为i和m的位置都出现了）。
                        // 数字m出现在下标为m的地方（nums[m]=m），数字m出现在了下标为i的地方（nums[i]=m）
                        return nums[i];
                    } else {
                        // 执行到这里 nums[i] != i，nums[i] != nums[nums[i]]，即nums[m]!=m;m=nums[i]
                        // 也就是数字m出现在下标为i的位置，数字m没有出现在下标为m的位置。那么应该将数字m放置到第m个位置。
                        // 交换 nums[i] 和 nums[nums[i]]，这个只是将数字m放置在了第m个位置。
                        // 第i个位置不一样是应该放置的数字nums[i]。即nums[i] 和i不一定等于，还需要再次判断
                        //swap(nums,i,nums[i]);// 两个都行
                        swap(nums, nums[i], i);
                        i--;// 注意这里要自减1，前面
                    }
                }
                //上面是下面的简化
                //if (nums[i] == i) {//若 nums[i]=i ： 说明此数字已在对应索引位置，无需交换，因此跳过；
                //    continue;
                //} else {// 执行到这里则nums[i] != i
                //    if (nums[nums[i]] == nums[i]) {
                //        // nums[nums[i]]==nums[i] ，即 nums[m]=nums[i]=m，数字m出现在下标为m的地方（nums[m]=m），数字m出现在了下标为i的地方（nums[i
                //        // ]=m）
                //        return nums[i];
                //    } else {
                //        if (nums[nums[i]] == nums[i]) {
                //            // nums[nums[i]]==nums[i] ，即 nums[m]=nums[i]=m，数字m出现在下标为m的地方（nums[m]=m），数字m出现在了下标为i
                //            // 的地方（nums[i]=m）
                //            return nums[i];
                //        } else {
                //            //swap(nums,i,nums[i]);
                //            swap(nums, nums[i], i);
                //            i--;
                //        }
                //    }
                //}
            }
            return -1;
        }


        private void swap(int[] nums, int i, int j) {
            if(nums[i]!=nums[j]){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }


        //// 解法2：哈希表  数组模拟哈希表 写法1
        //public int findRepeatNumber(int[] nums) {
        //    if (nums == null || nums.length == 0) {// 判空
        //        return -1;
        //    }
        //    int len = nums.length;
        //
        //    // 检查数组中第i个元素nums[i]是否出现在第i个位置（下标i）
        //    for (int i = 0; i < len; i++) {
        //        if (nums[i] < 0 || nums[i] > len - 1) { // 判断数组元素是否越界,力扣的测试用例不包含越界的测试用例
        //            return -1;
        //        }
        //        // 数组中第i个元素是nums[i]，记第i个元素是m（nums[i]=m）
        //        while (nums[i] != i) { // 当nums[i]没有放在对应的位置i时，也就是数字m出现在了下标为i的地方
        //            // nums[nums[i]]==nums[i] ，即 nums[m]=nums[i]=m，（数字m也就是元素值为m的元素在下标为i和m的位置都出现了）。
        //            // 数字m出现在下标为m的地方（nums[m]=m），数字m出现在了下标为i的地方（nums[i]=m）
        //            if (nums[nums[i]] == nums[i]) {//当nums[i]没有放在对应的位置i时，也就是数字m出现在了下标为i的地方, 再拿数字m和第m个数字进行比较。
        //                return nums[i];
        //            } else {
        //                // 执行到这里 nums[i] != i，nums[i] != nums[nums[i]]，即nums[m]!=m;m=nums[i]
        //                // 也就是数字m出现在下标为i的位置，数字m没有出现在下标为m的位置。那么应该将数字m放置到第m个位置。
        //                // 交换 nums[i] 和 nums[nums[i]]
        //                swap(nums, i, nums[i]);
        //            }
        //        }
        //    }
        //    return -1;
        //}


        ////	解法1：哈希表 API
        ////  时间复杂度 O(N) ： 遍历数组使用 O(N) ，HashSet 添加与查找元素皆为 O(1) 。
        ////  空间复杂度 O(N) ： HashSet 占用 O(N) 大小的额外空间。
        //
        //public int findRepeatNumber(int[] nums) {
        //	if(nums==null||nums.length==0){
        //		return -1;
        //	}
        //    int len = nums.length;
        //
        //	Set<Integer> set=new HashSet<>();
        //	for(int num:nums){
        //        if(num<0||num>len-1){// 判断数组元素是否越界,力扣的测试用例不包含越界的测试用例
        //            return -1;
        //        }
        //        // Adds the specified element to this set if it is not already present (optional operation).
        //        // If this set already contains the element, the call leaves the set unchanged and returns false.
        //		if(!set.add(num)){
        //			return num;
        //		}
        //        //// 上面if是下面if的简化版本
        //        //if(set.contains(num)){
        //        //    return num;
        //        //}else {
        //        //    set.add(num);
        //        //}
        //	}
        //	return -1;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
