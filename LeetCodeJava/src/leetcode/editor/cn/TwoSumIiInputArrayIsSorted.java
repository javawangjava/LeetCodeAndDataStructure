/**
 * <p>给你一个下标从 <strong>1</strong> 开始的整数数组&nbsp;<code>numbers</code> ，该数组已按<strong><em> </em>非递减顺序排列&nbsp;
 * </strong>，请你从数组中找出满足相加之和等于目标数&nbsp;<code>target</code> 的两个数。如果设这两个数分别是 <code>numbers[index<sub>1</sub>]</code> 和
 * <code>numbers[index<sub>2</sub>]</code> ，则 <code>1 &lt;= index<sub>1</sub> &lt; index<sub>2</sub> &lt;= numbers
 * .length</code> 。</p>
 *
 * <p>以长度为 2 的整数数组 <code>[index<sub>1</sub>, index<sub>2</sub>]</code> 的形式返回这两个整数的下标
 * <code>index<sub>1</sub></code><em> </em>和<em> </em><code>index<sub>2</sub></code>。</p>
 *
 * <p>你可以假设每个输入 <strong>只对应唯一的答案</strong> ，而且你 <strong>不可以</strong> 重复使用相同的元素。</p>
 *
 * <p>你所设计的解决方案必须只使用常量级的额外空间。</p>
 * &nbsp;
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>numbers = [<strong><em>2</em></strong>,<strong><em>7</em></strong>,11,15], target = 9
 * <strong>输出：</strong>[1,2]
 * <strong>解释：</strong>2 与 7 之和等于目标数 9 。因此 index<sub>1</sub> = 1, index<sub>2</sub> = 2 。返回 [1, 2] 。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>numbers = [<strong><em>2</em></strong>,3,<strong><em>4</em></strong>], target = 6
 * <strong>输出：</strong>[1,3]
 * <strong>解释：</strong>2 与 4 之和等于目标数 6 。因此 index<sub>1</sub> = 1, index<sub>2</sub> = 3 。返回 [1, 3] 。</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>numbers = [<strong><em>-1</em></strong>,<strong><em>0</em></strong>], target = -1
 * <strong>输出：</strong>[1,2]
 * <strong>解释：</strong>-1 与 0 之和等于目标数 -1 。因此 index<sub>1</sub> = 1, index<sub>2</sub> = 2 。返回 [1, 2] 。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= numbers.length &lt;= 3 * 10<sup>4</sup></code></li>
 * <li><code>-1000 &lt;= numbers[i] &lt;= 1000</code></li>
 * <li><code>numbers</code> 按 <strong>非递减顺序</strong> 排列</li>
 * <li><code>-1000 &lt;= target &lt;= 1000</code></li>
 * <li><strong>仅存在一个有效答案</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>二分查找</li></div></div><br><div><li>👍 827</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 167
 * 两数之和 II - 输入有序数组
 */

public class TwoSumIiInputArrayIsSorted {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new TwoSumIiInputArrayIsSorted().new Solution();
        int[] nums = new int[]{2, 7, 11, 15};
        int[] ans = solution.twoSum(nums, 9);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法3：双指针  时间复杂度是O（n）
        // 我们用两个指针P1和P2分别指向数组中的两个数字。指针P1初始化指向数组的第1个（下标为0）数字，指针P2初始化指向数组的最后一个数字。
        // 如果指针P1和P2指向的两个数字之和等于输入的k，那么就找到了符合条件的两个数字。
        // 如果指针P1和P2指向的两个数字之和小于k，那么我们希望两个数字的和再大一点。
        // 由于数组已经排好序，因此可以考虑把指针P1向右移动。因为在排序数组中右边的数字要大一些，所以两个数字的和也要大一些，这样就有可能等于输入的数字k。
        // 同样，当两个数字的和大于输入的数字k时，可以把指针P2向左移动，因为在排序数组中左边的数字要小一些。

        public int[] twoSum(int[] numbers, int target) {
            int[] ans = {-1, -1};// 找不到时返回的数组
            // 特殊情况处理，题目没有额外要求可以自己约定规则
            if (numbers == null || numbers.length <= 1) {//判空，数组至少要两个数
                return ans;
            }
            int length = numbers.length;
            int left = 0;
            int right = length - 1;
            while (left < right) {// 因为双指针left和right指向两个不同的数，所以不用等号
                int sum = numbers[left] + numbers[right];
                if (sum == target) {
                    //return new int[]{left+1,right+1};
                    ans[0] = left + 1;
                    ans[1] = right + 1;
                    return ans;
                } else if (sum < target) {
                    left++;//首部尾部相加小于目标值，首部后移变大
                } else {
                    right--;  //首部尾部相加大于目标值，尾部前移变小
                }
            }
            return ans;
        }


   /*

   //   解法4：循环+哈希表 优化，可以边判断便加入，只判断该位前面有没有和该位满足条件
        public int[] twoSum(int[] numbers, int target) {
            int[] ans={-1,-1};// 找不到时返回的数组
            // 特殊情况处理，题目没有额外要求可以自己约定规则
            if(numbers==null||numbers.length<=1){//判空，数组至少要两个数
                return ans;
            }
            int len = numbers.length;
            Map<Integer,Integer> map=new HashMap<>();
            for (int i = 0; i < len; i++) {
                if(map.containsKey(target-numbers[i])){
                    ans[0]=map.get(target-numbers[i])+1;//因为目前哈希表中只有填了该位前面的哈希值，所以比当前位要小
                    ans[1]=i+1;
                    break;
                }
                map.put(numbers[i],i); //和下面else 一样，因为结果唯一，找到时就结束了，那么找到前的每一个都会加入
                //else{
                //    map.put(numbers[i],i);
                //}
            }
            return ans;
        }
   */




/*
         //解法4：循环+哈希表 时间复杂度是O（n）  不要求数组有序
         //哈希表中key 表示numbers[i]，value表示i
        public int[] twoSum(int[] numbers, int target) {
            int[] ans={-1,-1};// 找不到时返回的数组
            // 特殊情况处理，题目没有额外要求可以自己约定规则
            if(numbers==null||numbers.length<=1){//判空，数组至少要两个数
                return ans;
            }
            int len = numbers.length;
            Map<Integer,Integer> map=new HashMap<>();
            for (int i = 0; i < len; i++) {
                map.put(numbers[i],i);
            }
            for (int i = 0; i < len; i++) {
                if(map.containsKey(target-numbers[i])){
                    ans[0]=i+1;
                    ans[1]=map.get(target-numbers[i])+1;
                    break;
                }
            }
            return ans;
        }

        */



   /*

    //	解法1：双循环,  时间复杂度是O（n^2）
   public int[] twoSum(int[] numbers, int target) {
		int[] ans={-1,-1};
		if(numbers==null||numbers.length<=1){//判空，数组至少要两个数
			return ans;
		}
	   int length=numbers.length;
		for (int i = 0; i < length-1; i++) {
			for (int j = i+1; j < length; j++) {
				if(numbers[i]+numbers[j]==target){
					ans[0]=i+1;
					ans[1]=j+1;
					break;
					//return new int[]{i+1,j+1};//直接返回需要的
				}
			}
		}
		return ans;
    }

   */




        /*

        //解法2： 循环+二分查找   有序数组可以用二分查找找第二个数,时间复杂度是O（nlogn）

        // 注意前后细节应该对应在一起
        // 在数组nums中从下标start之后的数组中利用二分查找查找target
        private static int binarySearch(int[] arr, int start, int target) {
            if (arr == null) {
                return -1;
            }
            int left = start;
            int right = arr.length - 1;

            //因为数组是递增的所以在这里判断是否在数组值区间内
            if (target < arr[left] || target > arr[right] || left > right) {
                return -1;
            }
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] == target) {
                    return mid;
                } else if (arr[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;//没找到
        }


        public int[] twoSum(int[] numbers, int target) {
            int[] ans = {-1, -1};// 找不到时返回的数组
            // 特殊情况处理，题目没有额外要求可以自己约定规则
            if (numbers == null || numbers.length <= 1) {//判空，数组至少要两个数
                return ans;
            }
            int length = numbers.length;
            for (int i = 0; i < length; i++) {//因为是两数之和，第二个数在第一个数之后，所以这里还可以写成（i < length-1）
                // 利用二分法查找 target-numbers[i]，但要注意返回值的合法性
                // 因为原数组是非递减顺序排列，也就是说数列中可能有相等的元素。那么二分查找时要注意从当前元素的后面查找起
                int second = binarySearch(numbers, i + 1, target - numbers[i]);

                //这里要注意temp可能返回的是-1，所以首先要保证数据有效性用的短路与，temp!=-1必须先执行
                if (second != -1) {//只要返回值不是-1，就说明二分查找找到了结果，下面这两种注释的if语句也可以用来检验数据的合法性
                    //if(second !=-1&&target-numbers[i]==numbers[second]){//只要返回值不是-1，就说明二分查找找到了结果
                    //if (second >= 0 && second < length) {
                    ans[0] = i + 1;
                    ans[1] = second + 1;
                    return ans;
                    // return new int[]{i + 1, second + 1};//这个和上面的if语句块的作用一样
                }
            }
            return new int[]{-1, -1};
        }
        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
