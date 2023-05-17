/**
 * 已知一个长度为 <code>n</code> 的数组，预先按照升序排列，经由 <code>1</code> 到 <code>n</code> 次 <strong>旋转</strong> 后，得到输入数组。例如，原数组
 * <code>nums = [0,1,2,4,5,6,7]</code> 在变化后可能得到：
 * <ul>
 * <li>若旋转 <code>4</code> 次，则可以得到 <code>[4,5,6,7,0,1,2]</code></li>
 * <li>若旋转 <code>7</code> 次，则可以得到 <code>[0,1,2,4,5,6,7]</code></li>
 * </ul>
 *
 * <p>注意，数组 <code>[a[0], a[1], a[2], ..., a[n-1]]</code> <strong>旋转一次</strong> 的结果为数组 <code>[a[n-1], a[0], a[1],
 * a[2], ..., a[n-2]]</code> 。</p>
 *
 * <p>给你一个元素值 <strong>互不相同</strong> 的数组 <code>nums</code> ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的
 * <strong>最小元素</strong> 。</p>
 *
 * <p>你必须设计一个时间复杂度为&nbsp;<code>O(log n)</code> 的算法解决此问题。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,4,5,1,2]
 * <strong>输出：</strong>1
 * <strong>解释：</strong>原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [4,5,6,7,0,1,2]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [11,13,15,17]
 * <strong>输出：</strong>11
 * <strong>解释：</strong>原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
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
 * <li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
 * <li><code>nums</code> 原来是一个升序排序的数组，并进行了 <code>1</code> 至 <code>n</code> 次旋转</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 776</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 153
 * 寻找旋转排序数组中的最小值
 *
 * @author wangweizhou
 * @date 2022-07-01 16:16:45
 */

public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new FindMinimumInRotatedSortedArray().new Solution();
        //int[] a = {1, 2, 3, 4, 5};
        //int[] a = {1, 2, 3, 4, 5};
        int[] a = {3, 4, 5, 6, 0, 1, 2};
        int ans = solution.findMin(a);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //// 解法1：二分查找
        //// 原数组是单调不减数组 ，旋转后结果：旋转点左边是单调不减，旋转点右边是单调不减。
        //// 情况1：旋转次数=数组长度，就是原数组，单调不减【最小值是第一个】；
        //// 情况2：旋转次数<数组长度，单调不减【这边的每个值不小于后面的每个值】-下降【这个值是最小值】-单调不减【旋转次数<数组长度】
        //// 1.若nums[mid]<nums[right],两种情况都可能，，mid指向的可能是最小值，最小值一定不在【mid+1,right】,则最小值一定在[left,mid]段;
        //// 2.若nums[mid]>nums[right]，只能是情况2，mid指向的不是最小值，最小值一定不在[left,mid]段,则最小值一定在[mid+1,right].
        //// 3.若nums[mid]=nums[right]，不清楚最小值在那个区间，应该逐个缩减右界；因为数组是升序的，所以最小值一定靠近左侧，而不是右侧。

        public int findMin(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int length = nums.length;
            int left = 0;
            int right = length - 1;
            while (left < right) {// left < right：表明最后区间至少有两个元素
                int mid = (left + right) / 2;
                if (nums[mid] > nums[right]) {// 从旋转点左长右短，中点在左长段。
                    // 只能是情况2，mid指向的不是最小值，最小值一定不在[left,mid]段,则最小值一定在[mid+1,right].
                    left = mid + 1;
                } else {
                    // 这里的条件是nums[mid] <= nums[right],但是原数组是升序排列，所以这里条件其实是nums[mid] < nums[right]。
                    // 两种情况都可能，，mid指向的可能是最小值，最小值一定不在【mid+1,right】,则最小值一定在[left,mid]段
                    right = mid;
                }
            }
            return nums[left];// 最后区间只有一个元素，两个指针指向相同的元素。
        }



        //public int findMin(int[] nums) {
        //    if(nums==null||nums.length==0){
        //        return Integer.MAX_VALUE;
        //    }
        //    int len=nums.length;
        //    int left=0;
        //    int right=len-1;
        //    if(nums[0]<nums[len-1]){
        //        return nums[0];
        //    }
        //    while (left<=right) {
        //        int mid = (left + right) >> 1;
        //        if (nums[mid] < nums[right]) {
        //            if (mid >= 1 && nums[mid - 1] < nums[mid]) {
        //                right = mid - 1;
        //            } else {
        //                return nums[mid];
        //            }
        //        } else {
        //            left = mid + 1;
        //        }
        //    }
        //    return nums[right];
        //}



        //// 解法2：二分查找写法2
        //// 原数组是单调递增数组 ，旋转后结果
        //// 情况1：旋转次数=数组长度，就是原数组，单调不减【最小值是第一个】；
        //// 情况2：旋转次数<数组长度，单调不减【这边的每个值不小于后面的每个值】-下降【这个值是最小值】-单调不减【旋转次数<数组长度】
        //
        //public int findMin(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int length = nums.length;
        //    int left = 0;
        //    int right = length - 1;
        //    while(left<=right){
        //        int mid=(left+right)/2;
        //        if(nums[left]<=nums[mid]){//情况1和情况2的左长右短都可能
        //            if(nums[mid]<=nums[right]){// nums[left]<=nums[mid],nums[mid]<=nums[right]。
        //                // 只能是情况1，[left,right]是单调递增的
        //                return nums[left];
        //            }else{// nums[left]<=nums[mid],nums[mid]>nums[right]
        //                // 只能是情况2的左长右短，mid指向的不是最小值，最小值一定不在[left,mid]段,则最小值一定在[mid+1,right].
        //                left=mid+1;
        //            }
        //        }else{// nums[left]>nums[mid] 只能是情况2的左短右长。mid指向的可能是最小值，最小值一定不在【mid+1,right】,则最小值一定在[left,mid]段
        //            right=mid;
        //        }
        //    }
        //    return -1;
        //}






        //// 解法2：二分查找写法3  画图模拟  上面的合并
        //public int findMin(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int length = nums.length;
        //    int left = 0;
        //    int right = length - 1;
        //
        //    while(left<=right){
        //        int mid=(left+right)/2;
        //        if(nums[left]<=nums[mid]&&nums[mid]<=nums[right]){// 旋转次数=数组长度，就是原数组，单调不减【最小值是第一个】；
        //            return nums[left];
        //        }else if(nums[left]<=nums[mid]&&nums[mid]>nums[right]){// 从旋转点左长右短，中点在左长段。
        //            left=mid+1;
        //        //}else if(nums[mid]<nums[left]&&nums[mid]<=nums[right]){// 从旋转点左短右长，中点在右长段。
        //        }else {// 从旋转点左短右长，中点在右长段。上面else-if里面的条件可以不用写
        //            right=mid;
        //        }
        //    }
        //    return -1;
        //}





        //// 暴力法写法1：
        //public int findMin(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return Integer.MAX_VALUE;
        //    }
        //    int len = nums.length;
        //    if (nums[0] < nums[len - 1]) {
        //        return nums[0];
        //    }
        //    for (int i = 0; i < len-2; i++) {
        //        if(nums[i]>nums[i+1]){
        //            return nums[i+1];
        //        }
        //    }
        //    return nums[len-1];
        //}




        /*
        // 暴力法写法2：
        public int findMin(int[] nums) {
            if (nums == null || nums.length == 0) {
                return Integer.MAX_VALUE;
            }
            int length = nums.length;
            int ans = nums[0];//数组非空，假定最小值是数组第一个元素
            // 数组长度不为1时，最小值是下坡的第一个
            // 这里i结束的终止条件是length-1。
            // 取余是为了应对原数组一直是一个升序排列，那么最小值就是数组第一个，通过取余来循环到第一个。
            // 当i=length-1时，(i+1)%length=0，那么就是数组最后一个元素和第一个元素比较。
            for (int i = 1; i < length + 1; i++) {//注意这个开始和结束条件
                if (nums[i % length] < nums[(i - 1) % length]) {
                    ans = nums[i % length];
                }
            }
            return ans;
        }
        */




       /*
       //暴力法写法3：
        public int findMin(int[] nums) {
            if(nums==null||nums.length==0){
                return Integer.MAX_VALUE;//题目没有给处理信息，这里随意写了一个处理办法
            }
            int length=nums.length;
            int ans = nums[0];//数组非空，假定最小值是数组第一个元素
            //数组长度不为1时，最小值是下坡的第一个
            //这里i结束的终止条件是length-1，取余是为了应对原数组一直是一个升序排列，那么最小值就是数组第一个，通过取余来循环到第一个
            //当i=length-1时，(i+1)%length=0，那么就是数组最后一个元素和第一个元素比较
            for (int i = 0; i < length; i++) {//注意这个开始和结束条件
                if(nums[i]>=nums[(i+1)%length]){// 当最小值不是第一个元素时，更新最小值。注意这里用的（>=）因为原数组是升序。
                    return nums[(i+1)%length];//没必要遍历完数组找到时就可以结束方法
                }
            }
            return ans;
        }
        */



    }
//leetcode submit region end(Prohibit modification and deletion)

}
