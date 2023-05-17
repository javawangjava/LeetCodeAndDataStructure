/**
 * <p>给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。</p>
 *
 * <p>请你找出并返回只出现一次的那个数。</p>
 *
 * <p>你设计的解决方案必须满足 <code>O(log n)</code> 时间复杂度和 <code>O(1)</code> 空间复杂度。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [1,1,2,3,3,4,4,8,8]
 * <strong>输出:</strong> 2
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums =  [3,3,7,7,10,11,11]
 * <strong>输出:</strong> 10
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><meta charset="UTF-8" /></p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>0 &lt;= nums[i]&nbsp;&lt;= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 541</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 540
 * 有序数组中的单一元素
 *
 * @author wangweizhou
 * @date 2022-08-28 21:48:00
 */

public class SingleElementInASortedArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new SingleElementInASortedArray().new Solution();
        int[] nums = {2, 2, 3, 3, 4};
        int ans = solution.singleNonDuplicate(nums);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 输入的数组没有经过排序，其他条件不变，那么这就是另一类很经典的面试题。
        //// 由于两个相同的数字异或的结果是0，因此如果将数组中所有数字异或，最终的结果就是那个唯一只出现一次的数字。
        //// 解法5：异或
        //public int singleNonDuplicate(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int len=nums.length;
        //    int ans=nums[0];
        //    for (int i = 1; i < len; i++) {
        //        ans=ans^nums[i];
        //    }
        //    return ans;
        //}



        // 在一个排序数组中，如果所有数字都出现了两次，那么将数组中的数字每两个分成一组，每组的两个数字都是相等的。
        // 但如果在数组中添加一个只出现一次的数字，那么这个规律就会被打破。例如，在数组[1，1，2，2，3，4，4，5，5]中，
        // 如果将两个数字分成一组，可以分成（1，1）、（2，2）、（3，4）和（4，5），以及最后还剩下的数字5。
        // 在这几组数字中，前两组的数字分别相同，但后面两组的数字就不相同。
        // 数组中的数字每两个分成一组，最初的若干组的两个数字都是相同的。但遇到了只出现一次的数字之后，情况发生变化。
        // 这个只出现一次的数字和后面的数字结合成一组，导致后面所有出现两次的数字都被分到两个不同的组，即后面所有组的两个数字都不相同。
        // 只出现一次的数组只可能是每一组的第一个数字。
        // 由此可见，只出现一次的数字正好是第1个两个数字不相等的分组的第1个数字。


        //// 解法2：二分法
        //// 将数组元素两两分成一组，数组下标从0开始，对于数组而言，成对元素中的第一个所对应的下标必然是偶数，成对元素中的第二个所对应的下标必然是奇数。
        // 初始时，二分查找的左边界是 0，右边界是数组的最大下标。每次取左右边界的平均值 mid 作为待判断的下标，根据 mid 的奇偶性决定和左边或右边的相邻元素比较：
        // 1.如果 mid 是偶数，则比较 nums[mid] 和 nums[mid+1] 是否相等；
        // 1.1若对应数组值和下一个数组值相等的话，说明单一元素在不包含mid的右侧，包含mid的左侧没有;
        // 1.2若对应数组值和下一个数组值不相等的话，说明单一元素出现在包含mid的左侧。
        // 2.如果 mid 是奇数，则比较 nums[mid−1] 和nums[mid] 是否相等。
        // 如果上述比较相邻元素的结果是相等，则 mid<x，调整左边界，否则 mid≥x，调整右边界。调整边界之后继续二分查找，直到确定下标 x 的值。
        // 得到下标 x 的值之后，nums[x] 即为只出现一次的元素。


        //// 根据规则，只出现一次的元素一定是每一对元素的第一个元素。
        //public int singleNonDuplicate(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int left = 0;
        //    int right = nums.length - 1;
        //    while (left < right) {// 注意这里没有等号，[left,right]至少有两个元素，mid向下取整，mid+1不会越界
        //        int mid = (left + right) >> 1;
        //        if (mid % 2 == 0) {// mid 为偶数下标,数组下标从0开始,实际是数组中第奇数个元素
        //            if (nums[mid] == nums[mid + 1]) {// 若偶数下标的值与下一个值相同
        //                // 若偶数下标的值会与下一个值相同，则表明在[left,mid+1]范围内不包含只出现一次的数字
        //                //left = mid + 1;// 这里（+1）或者（+2）都可以，
        //                left = mid + 2;
        //            } else {// 偶数下标的值会与下一个值不同，则说明 [left,mid]一定包含单一元素，这里mid为每一组元素的第一个元素，则可能是第一个不同的数字，所以这里是闭区间
        //                right = mid;
        //            }
        //        } else {// mid 为奇数下标,数组下标从0开始,实际是数组中第偶数个元素
        //            if (nums[mid - 1] == nums[mid]) {// 若奇数下标的值和前一个值相同，可以确保 [left,mid]不包含单一元素
        //                left = mid + 1;
        //            } else {
        //                // 若奇数下标的值和前一个值不同，那么说明[left,mid-1]中一定包含单一元素，这里写成[left,mid-1]和[left,mid]都可以
        //                //right = mid;
        //                right = mid-1;
        //            }
        //        }
        //    }
        //    // 循环结束的条件是left==right
        //    return nums[right];
        //}





        //  解法2：二分法  写法2
        public int singleNonDuplicate(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {// [left,right]至少一个元素，左右边界要能变化，或者能结束循环，避免形成死循环
                int mid = (left + right) >> 1;
                if (mid % 2 == 0) {
                    if (mid < nums.length - 1 && nums[mid] == nums[mid + 1]) {// 这里要和后一个元素比较，要检查是否出界
                        left = mid + 2;
                    } else {
                        if (mid == 0 || nums[mid] != nums[mid - 1]) {// 如果mid是0，那么左边就没有元素了，下标不需要-1了
                            return nums[mid];
                        } else {
                            right = mid - 2;
                        }
                    }
                } else {
                    if (nums[mid] == nums[mid - 1]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
            return nums[nums.length - 1];
        }




        ////	解法1：哈希表  写法2 HashSet
        //public int singleNonDuplicate(int[] nums) {
        //	if(nums==null){
        //		return -1;
        //	}
        //	int len=nums.length;
        //	Set<Integer> set=new HashSet<>();
        //	for (int i = 0; i < len; i++) {
        //		if(set.contains(nums[i])){
        //			set.remove(nums[i]);
        //		}else{
        //			set.add(nums[i]);
        //		}
        //	}
        //	// 遍历器和后面foreach一样都是遍历set集合
        //	//Iterator iter=set.iterator();
        //	//return (Integer)iter.next();
        //	for(Integer ans:set){
        //		return ans;
        //	}
        //	return -1;
        //}
        //




        //// 解法1：哈希表 写法1 HashMap
        //public int singleNonDuplicate(int[] nums) {
        //	if(nums==null){
        //		return -1;
        //	}
        //	int len=nums.length;
        //	Map<Integer,Integer> map=new HashMap<>();
        //	for (int i = 0; i < len; i++) {
        //		map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        //	}
        //	for (int i = 0; i < len; i++) {
        //		if(map.get(nums[i])==1){
        //			return nums[i];
        //		}
        //	}
        //	return -1;
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
