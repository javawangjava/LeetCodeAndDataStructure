/**
 * <p>整数数组 <code>nums</code> 按升序排列，数组中的值 <strong>互不相同</strong> 。</p>
 *
 * <p>在传递给函数之前，<code>nums</code> 在预先未知的某个下标 <code>k</code>（<code>0 &lt;= k &lt; nums.length</code>）上进行了
 * <strong>旋转</strong>，使数组变为 <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code>（下标
 * <strong>从 0 开始</strong> 计数）。例如， <code>[0,1,2,4,5,6,7]</code> 在下标 <code>3</code> 处经旋转后可能变为&nbsp;<code>[4,5,6,7,0,1,
 * 2]</code> 。</p>
 *
 * <p>给你 <strong>旋转后</strong> 的数组 <code>nums</code> 和一个整数 <code>target</code> ，如果 <code>nums</code> 中存在这个目标值
 * <code>target</code> ，则返回它的下标，否则返回&nbsp;<code>-1</code>&nbsp;。</p>
 *
 * <p>你必须设计一个时间复杂度为 <code>O(log n)</code> 的算法解决此问题。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [<code>4,5,6,7,0,1,2]</code>, target = 0
 * <strong>输出：</strong>4
 * </pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [<code>4,5,6,7,0,1,2]</code>, target = 3
 * <strong>输出：</strong>-1</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1], target = 0
 * <strong>输出：</strong>-1
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 5000</code></li>
 * <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * <li><code>nums</code> 中的每个值都 <strong>独一无二</strong></li>
 * <li>题目数据保证 <code>nums</code> 在预先未知的某个下标上进行了旋转</li>
 * <li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 2143</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 33
 * 搜索旋转排序数组
 */

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new SearchInRotatedSortedArray().new Solution();
        //int nums[] = {4,5,-1,0,2};
        //int nums[] = {4, 5, 6, 0, 1, 2, 3};
        int nums[] = {4, 0,};
        int a = solution.search(nums, 0);
        System.out.println(a);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 1.从旋转点若左右等长，从中点两侧都有序;
        // 2.从旋转点若左长右短，中点在左长的一段，从中点分开，左侧有序，右侧无序;
        // 3.从旋转点若左短右长，中点右长的一段从中点分开，左侧无序，右侧有序;
        // 原单调递增数组，经过旋转，形成单调递增-下降【旋转点下标】-单调递增数组。最小值在无序的那一段，中点在长的一段，且左半段的每个数大于右半段的每个数


        //// 解法1：二分查找  核心是判断出来要找的在那半段或者不在那半段，减半查找范围
        //// 解题思路：1.先判断中点在旋转点下标的；2.在判断target在中点的左侧还是右侧
        //public int search(int[] nums, int target) {
        //    if (nums == null || nums.length == 0) {
        //        return -1;
        //    }
        //    int length = nums.length;
        //    int left = 0;
        //    int right = length - 1;
        //    while (left <= right) {
        //        int mid = (left + right) / 2;
        //        // 原单调递增数组，经过旋转，形成单调递增-下降【旋转点下标】-单调递增数组 。中点在长的一段，且左半段的每个数大于右半段的每个数
        //        // 1.从旋转点若左右等长，从中点两侧都有序
        //        // 2.从旋转点若左长右短，中点在左长的一段，从中点分开，左侧有序，右侧无序
        //        // 3.从旋转点若左短右长，中点右长的一段,从中点分开，左侧无序，右侧有序
        //        // 先讨论中点在旋转点的那一侧，然后再讨论目标值在中点的那一侧
        //
        //        // 注意下面为了不形成死循环，对于左右边界的处理，使得每一次新区间的左右边界都要在原有的基础上移动
        //        // 前面已经判断了mid不是目标位置,所以左右边界可以在mid的基础上左右变动
        //        if (nums[mid] == target) { // 注意这里已经单独判断中间字符是否是目标字符了
        //            return mid;
        //        }
        //        // 因为java中除法是向下取整，所以left可能和mid重合。当区间只有两个元素时，left会和mid重合。
        //        if (nums[left] <= nums[mid]) {// 注意这里有等号，从旋转点左长右短，中点在左长段。  从中点分开，左侧有序，右侧无序
        //            if (nums[left] <= target && target < nums[mid]) { //target在[left,mid-1]有序的子数组中。
        //                right = mid - 1;
        //            } else { //target在右侧[mid+1,right]无序的子数组中
        //                left = mid + 1;
        //            }
        //        } else {// 从旋转点若左短右长，中点右长的一段从中点分开，左侧无序，右侧有序
        //            if (nums[mid] < target && target <= nums[right]) { //target在右侧[mid+1,right]有序的子数组中
        //                left = mid + 1;
        //            } else { //target在左侧[left,mid-1]无序的子数组中
        //                right = mid - 1;
        //            }
        //        }
        //    }
        //    return -1;
        //}




        //public int search(int[] nums, int target) {
        //    if (nums == null || nums.length == 0) {
        //        return Integer.MAX_VALUE;
        //    }
        //    int left=0;
        //    int right=nums.length-1;
        //    while (left<=right){
        //        int mid=(left+right)/2;
        //        if(nums[mid]==target){
        //            return mid;
        //        }
        //        // 当最终区间只有两个元素时，由于除法向下取整，那么left和mid可能会重合。
        //        if(nums[left]==nums[mid]){
        //            if(nums[right]==target){
        //                return right;
        //            }
        //        }
        //        if(nums[left]<nums[mid]){
        //            if(nums[left]<=target&&target<nums[mid]){
        //                right=mid-1;
        //            }else {
        //                left=mid+1;
        //            }
        //        }else {
        //            if(nums[mid]<target&&target<=nums[right]){
        //                left=mid+1;
        //            }else {
        //                right=mid-1;
        //            }
        //        }
        //    }
        //    return -1;
        //}




        // 下面这个是从比较右半段来确定右半段是否有序。
        public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return Integer.MAX_VALUE;
            }
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                // 因为除法是向下取整，所以这里只有当left=right时，mid才会和right重合，重合之后只要所指向的元素不是目标值，后面左右边界更新之后会越界结束循环。
                //if (nums[mid] <= nums[right]) {
                if (nums[mid] < nums[right]) {// 注意这里是利用right和mid来比较，
                    if (nums[mid] < target && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    if (nums[left] <= target && target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            return -1;
        }




        //// 解法1： 二分法  没有解法2好理解
        //// 原单调递增数组，经过旋转，形成单调递增-下降【旋转点下标】-单调递增数组 。中点在长的一段，且左半段的每个数大于右半段的每个数
        //// 1.先判断target在旋转点的左侧还是右侧
        //// 2.再判断中点在旋转点的左侧还是右侧
        //public int search(int[] nums, int target) {
        //    if(nums==null||nums.length==0){
        //        return -1;
        //    }
        //    int length = nums.length;
        //    int left = 0;
        //    int right = length - 1;
        //    while (left <= right) {
        //        int mid = (left + right) / 2;
        //        if (nums[mid] == target) { //单独判断中间字符
        //            return mid;
        //        }
        //        // 原单调递增数组，经过旋转，形成单调递增-下降【旋转点下标】-单调递增数组 。中点在长的一段，且左半段的每个数大于右半段的每个数
        //        if (nums[0] <= target) {// target在旋转点的左侧
        //            if(nums[mid]<nums[0]){// 从旋转点看，左短右长。mid在旋转点右侧
        //                nums[mid]=Integer.MAX_VALUE;// target不在[mid，right]里面
        //            }
        //        } else {// target在旋转点的右侧
        //           if(nums[mid]>=nums[0]){// 从旋转点看，左长右短。mid在旋转点左侧
        //               nums[mid]=Integer.MIN_VALUE;// target不在[left，mid]里面
        //           }
        //        }
        //
        //        if(nums[mid]<target){
        //            left=mid+1;
        //        }else{
        //            right=mid-1;
        //        }
        //    }
        //    return -1;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
