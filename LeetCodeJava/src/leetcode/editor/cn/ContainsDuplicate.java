/**
 * 给你一个整数数组 <code>nums</code> 。如果任一值在数组中出现 <strong>至少两次</strong> ，返回 <code>true</code> ；如果数组中每个元素互不相同，返回
 * <code>false</code> 。
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3,1]
 * <strong>输出：</strong>true</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3,4]
 * <strong>输出：</strong>false</pre>
 *
 * <p><strong>示例&nbsp;3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,1,1,3,3,4,3,2,4,2]
 * <strong>输出：</strong>true</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>排序</li></div></div><br><div><li>👍 760</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 存在重复元素
 *
 * @author wangweizhou
 * @date 2022-06-24 17:56:31
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ContainsDuplicate().new Solution();
        int[] nums={1,2,3,1};
        solution.containsDuplicate(nums);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法1：排序
        // 在对数字从小到大排序之后，数组的重复元素一定出现在相邻位置中。
        // 因此，我们可以扫描已排序的数组，每次判断相邻的两个元素是否相等，如果相等则说明存在重复的元素。
/*
        public boolean containsDuplicate(int[] nums) {
            Arrays.sort(nums);
            int length = nums.length;
            for (int i = 0; i < length; i++) {
                if(nums[i]==nums[i+1]){
                    return true;
                }
            }
            return false;
        }
        */

        //    解法2：哈希表  HashSet实现   需要先向其中添加
        //    对于数组中每个元素，我们将它插入到哈希表中。如果插入一个元素时发现该元素已经存在于哈希表中，则说明存在重复的元素。
        /*

        public boolean containsDuplicate(int[] nums) {
            Set<Integer> set=new HashSet<>();
            for (int num:nums ) {
                if(!set.add(num)){
                    return true;
                }
            }
            return false;
        }
        */


        //  解法3：哈希表 HashMap实现   这个需要先放进去map然后再取value判断是否大于2

        //  HashMap中键key表示数组元素，值value表示对应数组元素的出现次数
        // V get(Object key) 返回指定键映射到的值，如果此映射不包含键的映射，则返回 null 。
        // default V getOrDefault(Object key, V defaultValue) 返回指定键映射到的值，如果此映射不包含键的映射，则返回 defaultValue 。
        public boolean containsDuplicate(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
               /*
                //需要先放进去map然后再取value判断是否大于2
                map.put(num, map.getOrDefault(num,0) + 1);
                if (map.get(num) >= 2) {
                    return true;
                }*/

                if(map.containsKey(num)){
                    return true;
                }
                map.put(num,0);
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
