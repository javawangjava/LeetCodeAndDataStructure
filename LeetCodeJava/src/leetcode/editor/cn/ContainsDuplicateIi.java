/**
 * <p>给你一个整数数组&nbsp;<code>nums</code> 和一个整数&nbsp;<code>k</code> ，判断数组中是否存在两个 <strong>不同的索引</strong><em>&nbsp;
 * </em><code>i</code>&nbsp;和<em>&nbsp;</em><code>j</code> ，满足 <code>nums[i] == nums[j]</code> 且 <code>abs(i - j)
 * &lt;= k</code> 。如果存在，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3,1], k<em> </em>= 3
 * <strong>输出：</strong>true</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,0,1,1], k<em> </em>=<em> </em>1
 * <strong>输出：</strong>true</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3,1,2,3], k<em> </em>=<em> </em>2
 * <strong>输出：</strong>false</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
 * <li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>滑动窗口</li></div></div><br><div><li>👍 486</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 219
 * 存在重复元素 II
 *
 * @author wangweizhou
 * @date 2022-06-30 22:39:54
 */
public class ContainsDuplicateIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ContainsDuplicateIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //	 解法1：双指针  耗时太长

	/*
    public boolean containsNearbyDuplicate(int[] nums, int k) {
		int length=nums.length;
		if(nums==null||length==0){
			return false;
		}

		for (int i = 0; i < length-1; i++) {
			for (int j = i+1; j <= i+k&&j < length; j++) {
				if(nums[i]==nums[j]){
					return true;
				}
			}
		}
		return false;
    }*/


        // 解法2：哈希表+滑动窗口
        // 维护一个哈希表，里面始终最多包含 k 个元素，当出现重复值时则说明在 k 距离内存在重复元素
        // 每次遍历一个元素则将其加入哈希表中，如果哈希表的大小大于 k，则移除最前面的数字

      /*
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                // 先判断再添加，避免重复
                //if (set.contains(nums[i])) {// 判断哈希表中是否有满足条件的元素
                //    return true;
                //}
                //set.add(nums[i]);

                if (!set.add(nums[i])) {// 判断哈希表中是否有满足条件的元素
                    return true;
                }

                if (set.size() > k) {// 当哈希表中元素个数大于k时，移除最前面的元素
                    set.remove(nums[i - k]);
                }
            }
            return false;
        }*/

    //   解法3：哈希表
    //    HashMap中 键key表示数组值，值value表示数组下标

        public boolean containsNearbyDuplicate(int[] nums, int k) {
            Map<Integer,Integer> map=new HashMap<>();
            int length=nums.length;
            for (int i = 0; i < length; i++) {
                // 先判断再添加，避免重复
                if(map.containsKey(nums[i])&&i-map.get(nums[i])<=k){
                    return true;
                }
                map.put(nums[i],i);
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
