/**
 * <p>给定一个整数数组 <code>nums</code>&nbsp;和一个整数目标值 <code>target</code>，请你在该数组中找出 <strong>和为目标值
 * </strong><em><code>target</code></em>&nbsp; 的那&nbsp;<strong>两个</strong>&nbsp;整数，并返回它们的数组下标。</p>
 *
 * <p>你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。</p>
 *
 * <p>你可以按任意顺序返回答案。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,7,11,15], target = 9
 * <strong>输出：</strong>[0,1]
 * <strong>解释：</strong>因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,2,4], target = 6
 * <strong>输出：</strong>[1,2]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,3], target = 6
 * <strong>输出：</strong>[0,1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
 * <li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
 * <li><strong>只会存在一个有效答案</strong></li>
 * </ul>
 *
 * <p><strong>进阶：</strong>你可以想出一个时间复杂度小于 <code>O(n<sup>2</sup>)</code> 的算法吗？</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 14546</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 两数之和
 * @author wangweizhou
 * @date 2022-06-06 18:39:42
 */
public class TwoSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new TwoSum().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    class Solution {


        // 解法1：
	/* public int[] twoSum(int[] nums,int target){
        int[] ans=new int[2];//定义数组存储结果
        //使用双层循环来遍历
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]+nums[j]==target){//两个不同数相加等于target
                    //ans[0]=i;
                    //ans[1]=j;
                    //return ans;
                    return new int[]{i,j};
                }
            }
        }
        //return ans;
        throw new IllegalArgumentException("No two sum solution");
    }
    */





 /*
    // 解法2：
    // 第⼆层 for 循环⽆⾮是遍历所有的元素，看哪个元素等于 sub
    // 把数组的每个元素保存为 hash 的 key，数组下标保存为 hash 的 value，这样可以直接由键找对应的value是否存在，
    //hashMap中没有得到键的方法，基本全是得到值的方法

    public int[] twoSum(int[] nums,int target){
        Map<Integer,Integer> map=new HashMap<>();
        // 把所有的键值对先放进入hashmap中
        for (int i = 0; i < nums.length; i++) {
            // put(K key, V value) 将指定的值与此映射中的指定键相关联。
            map.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            int sub=target-nums[i];
            // containsKey(Object key) 如果此映射包含指定键的映射，则返回 true 。
            // get(Object key) 返回指定键映射到的值，如果此映射不包含键的映射，则返回 null 。
            if(map.containsKey(sub)&&map.get(sub)!=i){//有且不重复
                return new int[]{i,map.get(sub)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    */


        //解法3：
        public int[] twoSum(int[] nums, int target) {
            if (nums == null) {
                return null;
            }
            Map<Integer, Integer> map = new HashMap<>();//创建哈希表,两元组分别表示值、下标
            for (int i = 0; i < nums.length; i++) {
                //在哈希表中查找target-numbers[i]
                int sub = target - nums[i];
                if (map.containsKey(sub)) { //变化仅仅是不需要判断是不是当前元素了，因为当前元素还没有添加进 hash ⾥。
                    return new int[]{i, map.get(sub)};
                }
                map.put(nums[i], i);//当前元素还没有添加进 hash ⾥
            }
            return null;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
