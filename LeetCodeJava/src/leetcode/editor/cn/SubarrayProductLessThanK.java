/**
 * 给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，请你返回子数组内所有元素的乘积严格小于<em> </em><code>k</code> 的连续子数组的数目。
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [10,5,2,6], k = 100
 * <strong>输出：</strong>8
 * <strong>解释：</strong>8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3], k = 0
 * <strong>输出：</strong>0</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:&nbsp;</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
 * <li><code>0 &lt;= k &lt;= 10<sup>6</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>滑动窗口</li></div></div><br><div><li>👍 563</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 713
 * 乘积小于 K 的子数组
 *
 * @author wangweizhou
 * @date 2022-06-22 14:35:48
 */


public class SubarrayProductLessThanK {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new SubarrayProductLessThanK().new Solution();
        int[] nums = {10, 5, 2, 6};
        //int[] nums = {2,3,1,2,4,3};
        int a = solution.numSubarrayProductLessThanK(nums, 100);
        System.out.println(a);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：双指针组成长度可变的滑动窗口，大循环是右指针的移动，内部小循环是左指针的移动。

        // 用指针P1和P2指向数组中的两个数字，两个指针之间的数字组成一个子数组。指针P1永远不会走到指针P2的右边。
        // 两个指针初始化都指向数组的第1个数字（下标为0的数字）。
        // 如果两个指针之间的子数组中数字的乘积小于k，则向右移动指针P2。向右移动指针P2相当于在子数组中添加一个新的数字，由于数组中的数字都是正整数，因此子数组中数字的乘积就会变大。
        // 如果两个指针之间的子数组中数字的乘积大于或等于k，则向右移动指针P1。向右移动指针P1相当于从子数组中删除最左边的数字，由于数组中的数字都是正整数，因此子数组中数字的乘积就会变小。
        // 由于我们的目标是求出所有数字乘积小于k的子数组的个数，一旦向右移动指针P1到某个位置时子数组的乘积小于k，就不需要再向右移动指针P1。
        // 这是因为只要保持指针P2不动，向右移动指针P1形成的所有子数组的数字乘积就一定小于k。此时两个指针之间有多少个数字，就找到了多少个数字乘积小于k的子数组。

        // 类似动态规划的引入算法。滑动窗口引入一个新元素，则对应引入的新子串数目：共有 right - left + 1 种。新引入的子串必须包含right这个元素。
        //  nums[right]
        //  nums[right-1], nums[right]
        //  nums[right-2], nums[right-1], nums[right]
        //  nums[left], ......, nums[right-2], nums[right-1], nums[right]


        //// 计算子数组的个数
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k <= 1) {//判空
                return 0;
            }
            int len = nums.length;
            int left = 0;//开始时left和right都指向数组的第一个元素
            int right = 0;// 注意这里right=0,表示将right指向的元素加入到了滑动窗口中
            long product = 1;//记录子数组乘积
            int count = 0;//记录满足条件的子数组个数

            // [left,right]组成动态滑动窗口，进窗口乘进队元素，出窗口除出队元素。始终保持滑动窗口的子数组乘积小于k
            // 根据滑动窗口中元素乘积也就是子数组乘积的算法，left<=right.若left>right那么滑动窗口中没有元素了,子数组成绩一定小于k了。

            while (right < len) {//用右指针遍历整个数组，每次循环右指针右移一次
                product *= nums[right];//每加入一个新的元素，就更新子数组乘积
                // 当乘积大于等于k，左指针右移并把左指针指向的数除掉.根据子数组乘积的特点，左指针left一定不会超过右指针right
                //while(product>=k&&left<=right){//当left>right，滑动窗口中已经没有了元素，乘积一定小于k
                while (product >= k) {//注意这里是通过循环移动，目的是必须保证子数组乘积小于k
                    product /= nums[left];
                    left++;
                }
                // 内层循环结束之后，则子数组的成绩就小于k了
                // 每次右指针位移到一个新位置，只需要加上包含最右边的数字的子串数量。也就是计数时加上包含最新引入元素的子串数目
                count += right - left + 1;
                right++;   //右指针右移，新元素加入滑动窗口
            }
            return count;
        }



        //public int numSubarrayProductLessThanK(int[] nums, int k) {
        //    if (nums == null || nums.length == 0 || k <= 1) {
        //        return 0;
        //    }
        //    int len = nums.length;
        //    int left = -1;
        //    int right = 0;
        //    int product = 1;
        //    int count = 0;
        //    while (right < len) {
        //        product *= nums[right];
        //        while (product >= k && left < right) {
        //            left++;
        //            product /= nums[left];
        //        }
        //        count += right - left;// 这个写法是左开右闭且left指向满足条件的子数组的前一个位置
        //        right++;
        //    }
        //    return count;
        //}




      /*
        // 解法2：暴力循环
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (nums == null || nums.length == 0) {//判空
                return 0;
            }
            int len = nums.length;
            int count=0;
            for (int i = 0; i < len; i++) {
                int product=1;
                for (int j = i; j <len ; j++) {
                    product*=nums[j];
                    if(product<k){
                        count++;
                    }else{
                        break;
                    }
                }
            }
            return count;
        }
*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
