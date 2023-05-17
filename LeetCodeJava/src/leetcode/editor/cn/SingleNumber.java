/**
 * <p>给定一个<strong>非空</strong>整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。</p>
 *
 * <p><strong>说明：</strong></p>
 *
 * <p>你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre><strong>输入:</strong> [2,2,1]
 * <strong>输出:</strong> 1
 * </pre>
 *
 * <p><strong>示例&nbsp;2:</strong></p>
 *
 * <pre><strong>输入:</strong> [4,1,2,1,2]
 * <strong>输出:</strong> 4</pre>
 * <div><div>Related Topics</div><div><li>位运算</li><li>数组</li></div></div><br><div><li>👍 2461</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 只出现一次的数字
 *
 * @author wangweizhou
 * @date 2022-06-24 21:42:45
 */

public class SingleNumber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new SingleNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //  方法一：位运算
        // 异或运算有以下三个性质。
        // 任何数和 0 做异或运算，结果仍然是原来的数，即 a⊕0=a。
        // 任何数和其自身做异或运算，结果是 0，即a⊕a=0。
        // 异或运算满足交换律和结合律，即a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。

/*
    public int singleNumber(int[] nums) {
		int single=0;
		for (int num:nums){
			single^=num;
		}
		return single;
    }*/




        // 解法2：哈希表和遍历器
        public int singleNumber(int[] nums) {
            Set<Integer> set = new HashSet<>();
            int ans=0;
            for (int i = 0; i < nums.length; i++) {
                if(!set.add(nums[i])){
                    set.remove(nums[i]);
                }
                //if (set.contains(nums[i])) {
                //    set.remove(nums[i]);
                //} else {
                //    set.add(nums[i]);
                //}
            }

            // foreach 循环
            for(Integer num:set){
                ans=num;

            }

            // 迭代器循环
            //Iterator iter = set.iterator();
            //int i = 0;
            //while (iter.hasNext()) {
            //    ans= (Integer) iter.next();
            //}

            return ans;
        }




       /*
        //	解法2： HashMap 哈希表实现
        public int singleNumber(int[] nums) {
            Map<Integer,Integer> map=new HashMap<>();

            // 哈希表中存储所有数字
            for(int num:nums){
                map.put(num,map.getOrDefault(num,0)+1);
            }

           *//* for(int i=0;i<nums.length;i++){
                if(map.get(nums[i])==1){
                    return nums[i];
                }
            }*//*

            for(int num:nums){
                if(map.get(num)==1){
                    return num;
                }
            }
            return -1;//没找到
        }*/



  /*
        //	解法2： HashMap 哈希表实现
        public int singleNumber(int[] nums) {
            Map<Integer,Integer> map=new HashMap<>();
            // 哈希表中存储所有数字
            for(int num:nums){
                if(map.containsKey(num)){
                    map.remove(num);
                }else{
                    map.put(num,1);
                }
            }

           *//* for(int i=0;i<nums.length;i++){
                if(map.get(nums[i])==1){
                    return nums[i];
                }
            }*//*
            Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
            Map.Entry<Integer,Integer> entry=entries.next();
            return entry.getKey();
        }

        */
    }
//leetcode submit region end(Prohibit modification and deletion)

}
