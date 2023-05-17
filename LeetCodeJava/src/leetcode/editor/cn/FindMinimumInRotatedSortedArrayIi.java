/**
 * 已知一个长度为 <code>n</code> 的数组，预先按照升序排列，经由 <code>1</code> 到 <code>n</code> 次 <strong>旋转</strong> 后，得到输入数组。例如，原数组
 * <code>nums = [0,1,4,4,5,6,7]</code> 在变化后可能得到：
 * <ul>
 * <li>若旋转 <code>4</code> 次，则可以得到 <code>[4,5,6,7,0,1,4]</code></li>
 * <li>若旋转 <code>7</code> 次，则可以得到 <code>[0,1,4,4,5,6,7]</code></li>
 * </ul>
 *
 * <p>注意，数组 <code>[a[0], a[1], a[2], ..., a[n-1]]</code> <strong>旋转一次</strong> 的结果为数组 <code>[a[n-1], a[0], a[1],
 * a[2], ..., a[n-2]]</code> 。</p>
 *
 * <p>给你一个可能存在 <strong>重复</strong> 元素值的数组 <code>nums</code> ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的
 * <strong>最小元素</strong> 。</p>
 *
 * <p>你必须尽可能减少整个过程的操作步骤。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,3,5]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,2,2,0,1]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == nums.length</code></li>
 * <li><code>1 &lt;= n &lt;= 5000</code></li>
 * <li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li>
 * <li><code>nums</code> 原来是一个升序排序的数组，并进行了 <code>1</code> 至 <code>n</code> 次旋转</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>这道题与
 * <a href="https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/description/">寻找旋转排序数组中的最小值</a>
 * 类似，但 <code>nums</code> 可能包含重复元素。允许重复会影响算法的时间复杂度吗？会如何影响，为什么？</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 518</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 154
 * 寻找旋转排序数组中的最小值 II
 *
 * @author wangweizhou
 * @date 2022-07-02 02:06:05
 */

public class FindMinimumInRotatedSortedArrayIi {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new FindMinimumInRotatedSortedArrayIi().new Solution();
        //int[] nums = {1,1,1, 1, 2};
        int[] nums = {4, 4, 4, 4, 4, 0, 4};
        int ans = solution.findMin(nums);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：二分查找  原数组是单调不减数组，核心是剪枝。
        // 原数组是单调不减数组 ，旋转后结果：旋转点左边是单调不减，旋转点右边是单调不减。
        // 情况1：旋转次数=数组长度，就是原数组，单调不减【最小值是第一个】；
        // 情况2：旋转次数<数组长度，单调不减【这边的每个值不小于后面的每个值】-下降【这个值是最小值】-单调不减【旋转次数<数组长度】

        // 1.若nums[mid]<nums[right],两种情况都可能，，mid指向的可能是最小值，最小值一定不在【mid+1,right】,则最小值一定在[left,mid]段
        // 2.若nums[mid]>nums[right]，只能是情况2，mid指向的不是最小值，则最小值一定在[mid+1,right].
        // 3.若nums[mid]=nums[right]，不清楚最小值在那个区间，应该逐个缩减右界；因为数组是升序的，所以最小值一定靠近左侧，而不是右侧


        public int findMin(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int length = nums.length;
            int left = 0;
            int right = length - 1;
            while (left < right) {// left < right：表明最后区间至少有两个元素
                int mid = (left + right) / 2;
                if (nums[mid] > nums[right]) {// 左长右短  mid指向的不是最小值，最小值一定不在【left,mid】则最小值一定在[mid+1,right].
                    left = mid + 1;
                } else if (nums[mid] < nums[right]) {// 左短右长， mid指向的可能是最小值，最小值一定不在【mid+1,right】,则最小值一定在[left,mid]段
                    right = mid;
                } else {// 不清楚最小值在那个区间，应该逐个缩减右界，因为数组是升序的，所以最小值一定靠近左侧，而不是右侧
                    // nums[mid]==nums[right]
                    right--;
                }
            }
            return nums[left];
        }



        //// 解法2：比较左侧
        //public int findMin(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int l = 0;
        //    int r = nums.length - 1;
        //    int mid = (l + r) / 2;
        //    //if (nums[r] > nums[l]) {// 旋转了等于数组长度的次数，则数组就是有序数组
        //    //    return nums[l];
        //    //}
        //
        //    while (l <= r) {
        //        mid = (l + r) / 2;
        //        //如果二分后的数组是有序数组，则返回最左元素，即为最小
        //        if (nums[r] > nums[l]) {
        //            return nums[l];
        //        }
        //        if (nums[l] < nums[mid]) {
        //            //若最左小于mid元素，则最左到mid是严格递增的，那么最小元素必定在mid之后
        //            l = mid + 1;
        //        } else if (nums[l] > nums[mid]) {
        //            //若最左大于mid元素，则最小元素必定在最左到mid之间(不包括最左，因为最左已经大于mid)
        //            r = mid;
        //            l++;
        //        } else {
        //            //若二者相等，则最小元素必定在l+1到r之间，因为l和mid相等，故可以去除
        //            l++;
        //        }
        //    }
        //    return nums[mid];
        //}





    //  解法2：暴力解法，遍历 写法1
	/*
	public int findMin(int[] nums) {
		if(nums==null||length==0){
			return Integer.MAX_VALUE;
		}
		int length=nums.length;
		for (int i = 0; i < length-1; i++) {
			if(nums[i]>nums[i+1]){
				return nums[i+1];
			}
		}
		return nums[0];
	}
	*/



	/*
	//  解法2：暴力解法，遍历 写法2
	public int findMin(int[] nums) {
		int length=nums.length;
		if(nums==null||length==0){
			return Integer.MAX_VALUE;
		}
		//数组长度不为1时，最小值是下坡的第一个
		//这里i结束的终止条件是length-1，这个是为了应对，原数组一直是一个升序排列，那么最小值就是数组第一个，通过取余来循环到第一个
		//当i=length-1时，(i+1)%length=0，那么就是数组最后一个元素和第一个元素比较
		int ans = nums[0];//数组非空，假定最小值是数组第一个元素
		for (int i = 0; i < length; i++) {
			if (nums[i] > nums[(i + 1) % length]) {
				ans = nums[(i + 1) % length];
			}
		}
		return ans;
	}
	*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
