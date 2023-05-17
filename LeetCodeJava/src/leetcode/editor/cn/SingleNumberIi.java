/**
 * <p>给你一个整数数组 <code>nums</code> ，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次 。</strong>请你找出并返回那个只出现了一次的元素。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,2,3,2]
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,1,0,1,0,1,99]
 * <strong>输出：</strong>99
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 3 * 10<sup>4</sup></code></li>
 * <li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
 * <li><code>nums</code> 中，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次</strong></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？</p>
 * <div><div>Related Topics</div><div><li>位运算</li><li>数组</li></div></div><br><div><li>👍 889</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 137
 * 只出现一次的数字 II
 *
 * @author wangweizhou
 * @date 2022-07-20 22:50:48
 */
public class SingleNumberIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new SingleNumberIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：哈希表   键表示一个元素，值表示其出现的次数。
        //public int singleNumber(int[] nums) {
        //    Map<Integer, Integer> map = new HashMap<>();
        //    int length = nums.length;
        //    int ans = 0;
        //    for (int i = 0; i < length; i++) {
        //        map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        //    }
        //
        //    // foreach遍历map
        //    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        //        if(entry.getValue()==1){
        //            ans= entry.getKey();
        //            break;
        //        }
        //    }
        ///*
        //// 通过遍历数组实现
        //for (int i = 0; i < length ; i++) {
        //	if(map.get(nums[i])==1){
        //		ans =nums[i];
        //	}
        //}
        //*/
        //
        //    return ans;
        //}



        //
        public int singleNumber(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num,map.getOrDefault(num,0)+1);
            }
            int ans=0;
            for(Integer key:map.keySet()){
                if(map.get(key)==1){
                    ans=key;
                    break;
                }
            }
            return ans;
        }



        //// 没看明白
        //public int singleNumber(int[] nums) {
        //    int ans = 0;
        //    for (int i = 0; i < 32; ++i) {
        //        int total = 0;
        //        for (int num: nums) {
        //            total += ((num >> i) & 1);
        //        }
        //        if (total % 3 != 0) {
        //            ans |= (1 << i);
        //        }
        //    }
        //    return ans;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
