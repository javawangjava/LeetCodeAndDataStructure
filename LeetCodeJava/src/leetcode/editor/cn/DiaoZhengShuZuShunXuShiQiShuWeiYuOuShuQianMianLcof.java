/**
 * <p>输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums =&nbsp;[1,2,3,4]
 * <strong>输出：</strong>[1,3,2,4]
 * <strong>注：</strong>[3,1,2,4] 也是正确的答案之一。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ol>
 * <li><code>0 &lt;= nums.length &lt;= 50000</code></li>
 * <li><code>0 &lt;= nums[i] &lt;= 10000</code></li>
 * </ol>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 259</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 21
 * 调整数组顺序使奇数位于偶数前面
 *
 * @author wangweizhou
 * @date 2022-09-13 20:04:59
 */

public class DiaoZhengShuZuShunXuShiQiShuWeiYuOuShuQianMianLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new DiaoZhengShuZuShunXuShiQiShuWeiYuOuShuQianMianLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public int[] exchange(int[] nums) {
            if (nums == null || nums.length == 0) {
                return new int[0];
            }
            int len=nums.length;
            int left=0;
            int right=len-1;
            while (left<right){
                while (left<right&&nums[left]%2==1){
                    left++;
                }
                while (left<right&&nums[right]%2==0){
                    right--;
                }
                swap(nums,left,right);
            }
            return nums;
        }

        private void swap(int[] nums,int i, int j){
            if(nums[i]!=nums[j]){
                int temp=nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
            }
        }




        // 所有的奇数应该位于偶数的前面
        // 原数组奇数和偶数各占一半，在左半边找偶数，右半边找奇数，找到之后交换

        // 解法1：相向双指针 原地交换
        // 左指针指向数组的第一个数字，右指针指向数组的最后一个数字。
        // 当左指针向右遍历遇到偶数，暂停遍历，当右指针向左遍历遇到奇数，暂停遍历，交换奇数和偶数。继续遍历
        //
        //public int[] exchange(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return nums;
        //    }
        //    int len = nums.length;
        //    int left = 0;
        //    int right = len - 1;
        //    while (left < right) {
        //        //向后移动left,直到它指向偶数
        //        //while(left<right&&nums[left]%2==1){//  nums[left]%2==1则nums[left]为奇数
        //        while (left < right && (nums[left] & 1) == 1) {//  nums[left]&1)==1表示nums[left]是奇数
        //            left++;
        //        }
        //        //向前移动right，直到它指向奇数
        //        //while(left<right&&nums[right]%2==0){
        //        while (left < right && (nums[right] & 1) == 0) {// nums[right]&1)==0表示nums[right]是偶数
        //            right--;
        //        }
        //        // 交换奇数和偶数
        //        swap(nums, left, right);
        //    }
        //    return nums;
        //}
        //
        //
        //private void swap(int[] nums,int i, int j){
        //    if(nums[i]!=nums[j]){
        //        int temp=nums[i];
        //        nums[i]=nums[j];
        //        nums[j]=temp;
        //    }
        //}





        //// 解法1：相向双指针 原地交换  写法2
        //// 左指针指向数组的第一个数字，右指针指向数组的最后一个数字。
        //// 当左指针向右遍历遇到偶数，暂停遍历，当右指针向左遍历遇到奇数，暂停遍历，交换奇数和偶数。继续遍历
        //public int[] exchange(int[] nums) {
        //	if (nums == null || nums.length == 0) {
        //		return nums;
        //	}
        //	int len = nums.length;
        //	int left = 0;
        //	int right = len - 1;
        //	while (left < right) {
        //		//向后移动left,直到它指向偶数
        //		//while(left<right&&nums[left]%2==1){//  nums[left]%2==1则nums[left]为奇数
        //		if ((nums[left] & 1) == 1) {//  nums[left]&1)==1表示nums[left]是奇数
        //			left++;
        //			continue;
        //		}
        //		//向前移动right，直到它指向奇数
        //		//while(left<right&&nums[right]%2==0){
        //		if ((nums[right] & 1) == 0) {// nums[right]&1)==0表示nums[right]是偶数
        //			right--;
        //			continue;
        //		}
        //		// 交换奇数和偶数
        //		swap(nums, left, right);
        //	}
        //	return nums;
        //}
        //
        //private void swap(int[] nums, int i, int j) {
        //	int temp = nums[i];
        //	nums[i] = nums[j];
        //	nums[j] = temp;
        //}






        //// 解法1：写法2 这里把判断条件分离出来了
        //public int[] exchange(int[] nums) {
        //	if (nums == null || nums.length == 0) {
        //		return nums;
        //	}
        //	int len = nums.length;
        //	int left = 0;
        //	int right = len - 1;
        //	while (left < right) {
        //		//向后移动left,直到它指向偶数
        //		while (left < right && !isEven(nums[left])) {// nums[left]为奇数,直到遇到偶数
        //			left++;
        //		}
        //		//向前移动right，直到它指向奇数
        //		while (left < right && isEven(nums[right])) {
        //			right--;
        //		}
        //		// 交换奇数和偶数
        //		swap(nums, left, right);
        //	}
        //	return nums;
        //}
        //
        //private void swap(int[] nums, int i, int j) {
        //	int temp = nums[i];
        //	nums[i] = nums[j];
        //	nums[j] = temp;
        //}
        //
        //
        //// 任何整数 & 1，结果为 1 ，则为奇数；结果为 0 ，则为偶数
        //// 判断元素时偶数或者奇数
        //private boolean isEven(int num) {
        //	return (num & 1) == 0;
        //}







        /*
        //	  解法2：新建一个数组 双指针+一次遍历
        public int[] exchange(int[] nums) {
            if(nums==null||nums.length==0){
                return nums;
            }
            int len=nums.length;
            int[] newnums=new int[len];
            int left=0;
            int right=len-1;
            for (int i = 0; i < len; i++) {
                if(nums[i]%2==0){
                    newnums[right]=nums[i];
                    right--;
                }else{
                    newnums[left]=nums[i];
                    left++;
                }
            }
            return newnums;
        }
        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
