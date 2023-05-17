/**
 * <p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> ，其中 <code>nums</code> 的所有整数都在范围 <code>[1, n]</code> 内，且每个整数出现
 * <strong>一次</strong> 或 <strong>两次</strong> 。请你找出所有出现 <strong>两次</strong> 的整数，并以数组形式返回。</p>
 *
 * <p>你必须设计并实现一个时间复杂度为 <code>O(n)</code> 且仅使用常量额外空间的算法解决此问题。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [4,3,2,7,8,2,3,1]
 * <strong>输出：</strong>[2,3]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,1,2]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1]
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == nums.length</code></li>
 * <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
 * <li><code>1 &lt;= nums[i] &lt;= n</code></li>
 * <li><code>nums</code> 中的每个元素出现 <strong>一次</strong> 或 <strong>两次</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 661</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 442
 * 数组中重复的数据
 * @author wangweizhou
 * @date 2022-09-19 19:17:10
 */
/**
 * 剑指 Offer 03 和上面的对应起来学
 * 数组中重复的数字
 *
 * @author wangweizhou
 * @date 2022-09-14 19:28:57
 */

public class FindAllDuplicatesInAnArray {

    public static void main(String[] args) {

        //测试代码
        Solution solution = new FindAllDuplicatesInAnArray().new Solution();
        //int[] nums={1,1,2};
        //int[] nums={1,1,2};
        //int[] nums=null;
        int[] nums={2,3,4,3};
        List<Integer> lists=solution.findDuplicates(nums);
        if(lists.size()==0){
            System.out.println("无");
        }
        for (int i = 0; i <lists.size();  i++) {
            System.out.println(lists.get(i));
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 和剑指offer 的3相似
        // 数组中的数字都在1~n的范围内。如果这个数组中没有重复的数字，那么当数组排序之后数值为i的数将出现在下标为(i-1)的位置。
        // 如果 i 恰好出现了一次，那么将 i 放在数组中下标为 i−1 的位置即可；
        // 如果 i 出现了两次，那么我们希望其中的一个 i 放在数组下标中为i−1 的位置，另一个 i 放置在任意「不冲突」的位置 j。也就是说，数 j+1 没有在数组中出现过

        // 核心也就是函数自变量比函数值小1,也就是因变量比自变量大1。f(x)=x+1;自变量比因变量小1。

        // 解法1：将元素交换到对应的位置
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> lists = new ArrayList<>();
            if (nums == null || nums.length == 0) {// 数组判空
                return lists;
            }
            int len = nums.length;
            for (int i = 0; i < len; i++) {// 数组元素判出界
                if (nums[i] < 1 || nums[i] > len) {
                    return lists;
                }
            }

            // 核心也就是函数自变量比函数值小1,也就是因变量比自变量大1。f(x)=x+1;自变量比因变量小1。
            // 将数组元素重新排序，记m=nums[i]，将元素放置在对应的位置上，即函数自变量比函数值小1。
            for (int i = 0; i < len; i++) {
                // 记m=nums[i]，当nums[nums[i] - 1] != nums[i];即nums[m - 1] != m;则交换 nums[nums[i] - 1]和nums[i]
                while (nums[nums[i]-1]!=nums[i]) {
                    swap(nums,i,nums[i]-1);// 将数字m放置到下标m-1的位置，也就是将nums[i]放置到下标（nums[i]-1）的位置
                }
            }


            for (int i = 0; i < len; i++) {
                // 核心也就是函数自变量比因变量小1。nums[i] != i + 1 即nums[i] -1!= i。
                //if (nums[i] != i + 1) {
                if (nums[i]-1 != i) {
                    lists.add(nums[i]);
                }
            }
            return lists;
        }


        // 交换数组中两个元素,交换数组元素用下标比较方便，当然也可以用常见的两个数的交换方式
        private void swap(int[] nums, int index1, int index2) {
            if(nums==null||nums.length==0){
                return;
            }
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }




/*
        // 和剑指offer 的3差不多
        // 数组中的数字都在1~n的范围内。如果这个数组中没有重复的数字，那么当数组排序之后数值为i的数将出现在下标为(i-1)的位置。
        // 核心也就是函数自变量比函数值小1,也就是因变量比自变量大1。f(x)=x+1;自变量比因变量小1。
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> lists = new ArrayList<>();
            if (nums == null || nums.length == 0) {// 数组判空
                return lists;
            }
            int len = nums.length;
            for (int i = 0; i < len; i++) {// 数组元素判出界
                if (nums[i] < 1 || nums[i] > len) {
                    return lists;
                }
            }

            // 核心也就是函数自变量比函数值小1,也就是因变量比自变量大1。f(x)=x+1;自变量比因变量小1。
            // 将数组元素重新排序，记m=nums[i]，将元素放置在对应的位置上，即函数自变量比函数值小1。
            for (int i = 0; i < len; i++) {
                // m=nums[i]；nums[nums[i] - 1] != nums[i];即nums[m - 1] != m;
                while (nums[nums[i] - 1] != nums[i]) {
                    swap(nums,i,nums[i]-1);
                }
            }

            for (int i = 0; i < len; i++) {
                // 核心也就是函数自变量比函数值小1。nums[i] != i + 1 即nums[i] -1!= i。
                //if (nums[i] != i + 1) {
                if (nums[i]-1 != i ) {
                    lists.add(nums[i]);
                }
            }
            return lists;
        }

        // 交换数组中两个元素,交换数组元素用下标比较方便，当然也可以用常见的两个数的交换方式
        private void swap(int[] nums, int index1, int index2) {
            if(nums==null||nums.length==0){
                return;
            }
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }

        */
    }


//leetcode submit region end(Prohibit modification and deletion)

}
