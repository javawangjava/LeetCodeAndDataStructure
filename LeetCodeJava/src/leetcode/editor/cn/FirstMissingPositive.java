/**
 * <p>给你一个未排序的整数数组 <code>nums</code> ，请你找出其中没有出现的最小的正整数。</p>
 * 请你实现时间复杂度为 <code>O(n)</code> 并且只使用常数级别额外空间的解决方案。
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,0]
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,4,-1,1]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [7,8,9,11,12]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 5 * 10<sup>5</sup></code></li>
 * <li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 1532</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 41
 * 缺失的第一个正数
 *
 * @author wangweizhou
 * @date 2022-07-21 19:23:20
 */

public class FirstMissingPositive {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new FirstMissingPositive().new Solution();
        //int[] nums={3,4,-1,1};
        int[] nums = {2, 2};
        int ans = solution.firstMissingPositive(nums);
        System.out.println(ans);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //方法三： 数组模拟哈希表   // 将数组视为哈希表 这个比方法二好理解

        // 找出其中没有出现的最小的正整数。
        // 长度为n数组，若元素没有重复，则如果数组填满了1~n,那么缺失n+1,如果数组填不满1~n,那么缺失的就是1~n中的数字。
        // 那么就需要在1~n中找到缺失的数，如果没找到就应该是n+1。
        // 通过遍历把[1, n] 中的正数放置在对应的位置上，数值为 i 的数映射到下标为 i - 1 的位置。


        public int firstMissingPositive(int[] nums) {
            if (nums == null) {
                return 1;
            }

            int len = nums.length;
            for (int i = 0; i < len; i++) {
                // nums[i] > 0 && nums[i] <= len 说明遇到区间[1,len]中的正整数，
                // [1,len]中的正整数的正确位置：数值为 i 的数在下标为（i - 1） 的位置；即nums[i-1]=i。即下标比表示的正数小1。
                // 在正确位置时， nums[i]表示的数值应该是（i+1）,则（nums[i] - 1）表示的数值为i。

                // 那么正整数nums[i]放置在正确位置应该是nums[nums[i] - 1] == nums[i]。
                // 上面就是检验数组值为(i+1【即nums[i]】）是否放置在正确的位置上(nums[i]【即nums[nums[i] - 1]】）。
                // 若nums[nums[i] - 1] != nums[i]。这说明nums[i]没有在正确的位置上。验证（nums[i] - 1==i）不行，碰到有重复元素的可能会错。

                while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                    swap(nums, nums[i] - 1, i);// 位置不对时，交换位置，元素（nums[i] - 1==i）应该交换到i的位置
                }
            }


            // 数组修改成下标为（i-1）的位置存放数值i.
            // 重新遍历数组，找出第一个数组值不等不下标的那个数，
            for (int i = 0; i < len; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }
            // 都正确则返回数组长度 + 1
            return len + 1;
        }


        //交换数组中的两个元素
        private void swap(int[] nums, int index1, int index2) {
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }




    /*

	// 解法1：哈希表
	// n个长度的数组，没有重复，则如果数组填满了1~n,那么缺失n+1,如果数组填不满1~n,那么缺失的就是1~n中的数字。

    public int firstMissingPositive(int[] nums) {
		int length=nums.length;
        // 哈希表的key存储 出现的数字，value表示出现次数，全部设置为1
		Map<Integer,Integer> map=new HashMap<>();
		for (int i = 0; i < length; i++) {//哈希表记录数组中出现的每个数字
			map.put(nums[i],1);
		}
        // 从1开始遍历，查询哈希表中是否有这个数字，如果没有，说明它就是数组缺失的第一个正整数，即找到
		int num=1;
		while(map.containsKey(num)){//从1开始找到哈希表中第⼀个没有出现的正整数
			num++;
		}
		return num;
    }

    */





        /*

        //⽅法⼆：原地哈希（扩展思路）  没看懂
        //前面提到了数组要么缺失1~n中的某个数字，要么缺失n+1.
        //step1:我们可以先遍历数组将所有的负数都修改成n+1.
        //step2：然后再遍历数组，每当遇到一个元素绝对值不超过n时，则表示这个元素是1~n中出现的元素，
        //我们可以将这个数值对应的下标里的元素改成负数，相当于每个出现过的正整数的下标都指向一个负数，这就是类似哈希表的实现原理的操作。
        //step3:最后遍历数组的时候碰到的第一个非负数的下标就是没有出现的第一个正整数，因为它在之前的过程中没有被修改，说明它这个下标的正整数没有出现过。

        public int firstMissingPositive(int[] nums) {
            int length = nums.length;
            for (int i = 0; i < length; i++) {//遍历数组
                if (nums[i] <= 0) {//负数全部记为n+1
                    nums[i] = length + 1;
                }
            }
            for (int j = 0; j < length; j++) {
                int num = Math.abs(nums[j]);
                if (num <= length) {//对于1-n中的数字,这个数字的下标标记为负数
                    nums[num - 1] = -1 * Math.abs(nums[num - 1]);
                }
            }
            for (int i = 0; i < length; i++) {
                if (nums[i] > 0) {
                    return i + 1;
                }
            }
            return length + 1;
        }
        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
