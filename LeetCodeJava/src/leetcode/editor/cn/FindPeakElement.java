/**
 * <p>峰值元素是指其值严格大于左右相邻值的元素。</p>
 *
 * <p>给你一个整数数组&nbsp;<code>nums</code>，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 <strong>任何一个峰值</strong> 所在位置即可。</p>
 *
 * <p>你可以假设&nbsp;<code>nums[-1] = nums[n] = -∞</code> 。</p>
 *
 * <p>你必须实现时间复杂度为 <code>O(log n)</code><em> </em>的算法来解决此问题。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = <code>[1,2,3,1]</code>
 * <strong>输出：</strong>2
 * <strong>解释：</strong>3 是峰值元素，你的函数应该返回其索引 2。</pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = <code>[</code>1,2,1,3,5,6,4]
 * <strong>输出：</strong>1 或 5
 * <strong>解释：</strong>你的函数可以返回索引 1，其峰值元素为 2；
 * &nbsp;    或者返回索引 5， 其峰值元素为 6。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 1000</code></li>
 * <li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
 * <li>对于所有有效的 <code>i</code> 都有 <code>nums[i] != nums[i + 1]</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 838</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 162
 * 寻找峰值
 *
 * @author wangweizhou
 * @date 2022-07-01 15:20:45
 */

public class FindPeakElement {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new FindPeakElement().new Solution();
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        int ans = solution.findPeakElement(nums);
        System.out.println(ans);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int findPeakElement(int[] nums) {
            if(nums==null||nums.length==0){
                return Integer.MIN_VALUE;
            }
            int left=0;
            int right=nums.length-1;
            while (left<right){
                int mid=(left+right)>>1;
                if(nums[mid]>nums[mid+1]){
                    if(mid==0||nums[mid]>nums[mid-1]){
                        return mid;
                    }else {
                        right=mid-1;
                    }
                }else {
                    if(mid==0||nums[mid-1]<nums[mid]){
                        left=mid+1;
                    }else {
                        // 下面这两个都可以
                        left=mid+1;
                        //right=mid-1;
                    }
                }
            }
            return left;

        }




        // 就是M型找极大值

        // 方法二：题目要求只要找到一个峰值就可以，二分查找，题目两端是负无穷，所以中间一定有极大值
        // 从中间位置找爬坡段

        //public int findPeakElement(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int length = nums.length;
        //    int left = 0;
        //    int right = length - 1;
        //    while (left < right) {
        //        int mid = left + (right - left)/2;
        //        if (nums[mid] <= nums[mid + 1]) {//上坡段
        //            left = mid+1;
        //        } else {
        //            right = mid;
        //        }
        //    }
        //    return left;
        //}




        //// 解法3：画图四种类型，单调增，单调减，A型，V型。
        //public int findPeakElement(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return Integer.MIN_VALUE;
        //    }
        //    if(nums.length==1){
        //        return 0;
        //    }
        //    int left = 0;
        //    int right = nums.length - 1;
        //    while (left <= right) {
        //        int mid = (left + right) >> 1;
        //        if (mid+1<nums.length&&nums[mid] <= nums[mid + 1]) {// 这个和后一个进行比较，那么要验证数组下标不能越界
        //            if (mid == 0 || nums[mid - 1] <= nums[mid]) {// 要和前一个对比，所以数组下标不能越界
        //                left = mid + 1;
        //            } else {
        //                right = mid - 1;
        //            }
        //
        //        } else {
        //            if (mid == 0 || nums[mid - 1] <= nums[mid]) {// 要和前一个对比，所以数组下标不能越界
        //                return mid;
        //            } else {
        //                right = mid - 1;
        //            }
        //        }
        //    }
        //    return left;
        //}




        ////
        //public int findPeakElement(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return Integer.MIN_VALUE;
        //    }
        //    int left = 0;
        //    int right = nums.length - 1;
        //    while (left < right) {// 注意这里没有等号，那也就表明[left,right]至少两个元素，mid向下取整，所以mid+1不会越界。
        //        int mid = (left + right) >> 1;
        //        if (nums[mid] <= nums[mid + 1]) {
        //            if (mid == 0 || nums[mid - 1] <= nums[mid]) {// 要和前一个对比，所以数组下标不能越界
        //                left = mid + 1;
        //            } else {
        //                right = mid - 1;
        //            }
        //        } else {
        //            if (mid == 0 || nums[mid - 1] <= nums[mid]) {// 要和前一个对比，所以数组下标不能越界
        //                return mid;
        //            } else {
        //                right = mid - 1;
        //            }
        //        }
        //    }
        //    return left;
        //}





        ////	 方法一：寻找极大值,一直爬坡，遇到第一个下坡的时候停止
        //public int findPeakElement(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int ans = 0;// ans保存当前位置的极大值
        //    for (int i = 0; i < nums.length; i++) {
        //        if (nums[i] > nums[ans]) {
        //            ans = i;
        //        }
        //    }
        //    return ans;
        //}




        //// 方法1：写法2,迭代爬坡,只要找一个，题目两端是负无穷，所以中间一定有极大值
        //// 逐个遍历，遇坡就爬，遇到第一个下坡的时候停止。上坡上到顶端就是极大值
        //
        //public int findPeakElement(int[] nums) {
        //    if (nums == null) {
        //        return -1;
        //    }
        //    int length = nums.length;
        //    for (int i = 0; i < length; i++) {
        //        if (i < length - 1 && nums[i] > nums[i + 1]) {
        //            return i;
        //        }
        //        if (i == length - 1) {
        //            return length - 1;
        //        }
        //    }
        //    return -1;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
