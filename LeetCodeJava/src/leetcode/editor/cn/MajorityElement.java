/**
 * <p>给定一个大小为 <code>n</code><em> </em>的数组&nbsp;<code>nums</code> ，返回其中的多数元素。多数元素是指在数组中出现次数 <strong>大于</strong>&nbsp;
 * <code>⌊ n/2 ⌋</code>&nbsp;的元素。</p>
 *
 * <p>你可以假设数组是非空的，并且给定的数组总是存在多数元素。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,2,3]
 * <strong>输出：</strong>3</pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,2,1,1,1,2,2]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p>&nbsp;</p>
 * <strong>提示：</strong>
 *
 * <ul>
 * <li><code>n == nums.length</code></li>
 * <li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
 * <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>分治</li><li>计数</li><li>排序</li></div></div><br><div
 * ><li>👍 1501</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


import java.util.Random;

/**
 * 169
 * 多数元素
 *
 * @author wangweizhou
 * @date 2022-07-20 19:51:39
 */

public class MajorityElement {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new MajorityElement().new Solution();
        int[] nums = {3, 2, 3, 3, 2};
        int ans = solution.majorityElement(nums);
        System.out.println(ans);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 数组中有一个数字出现的次数超过数组长度的一半，也就是说它出现的次数比其他所有数字出现次数的和还要多。
        //因此，我们可以考虑在遍历数组的时候保存两个值：一个是数组中的一个数字：另一个是次数。
        //当我们遍历到下一个数字的时候，如果下一个数字和我们之前保存的数字相同，则次数加1；
        //如果下一个数字和我们之前保存的数字不同，则次数减1。
        //如果次数为零，那么我们需要保存下一个数字，并把次数设为1。
        //由于我们要找的数字出现的次数比其他所有数字出现的次数之和还要多，那么要找的数字肯定是最后一次把次数设为1时对应的数字。

        // 因为候选人的得票总数大于一半，所以肯定会最终站在舞台上
        // 群众投票选举候选人上台，只有当候选人票数大于1时可以继续留在舞台上，候选人的票数等于0时，更换新的候选人。
        // 假定每个候选人的票数都是1。也就是当选取一个新的候选人时，设定票数为1.
        // 开始时随机选取一个候选人，设置票数为1


        // 解法2：投票法 写法2  写法2和写法1的初始候选人和初始候选人票数不一样
        public int majorityElement(int[] nums) {
            if (nums == null || nums.length == 0) {
                return Integer.MIN_VALUE;
            }
            int candidate = 0;//初始化候选人
            int count = 0;// 初始化候选人票数
            // 每个候选人的默认票数都是0，当候选人票数为0时，更换候选人。每一个群众都要投票，无非是支持当前候选人和不支持当前的候选人
            for (int i = 0; i <nums.length ; i++) {//
                if(count==0){// 当候选人的票数为0时，更换候选人
                    candidate=nums[i];
                }
                // 上面换人之后下面依旧会执行
                if(candidate==nums[i]){// 若当前元素和候选人相等时，候选人票数+1
                    count++;
                }else{// 若当前元素和候选人不相等时，候选人票数-1
                    count--;
                }
            }
            return candidate;
        }




        //// 解法2：投票法   写法1：这个只能处理一定有多数元素的，
        //public int majorityElement(int[] nums) {
        //    if (nums == null || nums.length == 0) {// 这里约定空数组或者空引用时返回min
        //        return Integer.MIN_VALUE;
        //    }
        //    int len = nums.length;
        //    int candidate = nums[0];// 群众第一次投票选举的候选人candiate上台
        //    int count = 1;// 初始化候选人票数
        //    // 注意这里是从位置1开始的，循环中是三个选择,所以每次换人之后都是新换的人票数为1。
        //    for (int i = 1; i < len; i++) {
        //        if (count == 0) {// 当候选人的票数为0，那么就下台换当前群众选的新一个候选人上台
        //            // 换人之后，那么新的候选人的票数为1
        //            candidate = nums[i];
        //            count = 1;
        //        } else if (nums[i] == candidate) {// 当前群众投票选的人就是当前候选人时，则候选人票数+1
        //            count++;
        //        } else if (nums[i] != candidate) {// 当前群众投票选的人不是当前候选人时，则候选人票数-1
        //            count--;
        //        }
        //    }
        //    return candidate;
        //
        //    //if(checkValid(nums, candidate)){
        //    //    return candidate;
        //    //}else{
        //    //    return Integer.MIN_VALUE;
        //    //}
        //}

        // 其实不需要checkValid，不然直接遍历哈希表就可以
        //// 检查target是否是数组的众数
        //private boolean checkValid(int[] nums,int target){
        //    if(nums==null||nums.length==0){
        //        return false;
        //    }
        //    int count=0;
        //    for(int num:nums){
        //        if(num==target){
        //            count++;
        //        }
        //    }
        //    if(count*2>nums.length){
        //        return true;
        //    }
        //    return false;
        //}





        //// 解法一：基于Partition函数的时间复杂度为O(n)的算法。
        // 如果我们回到题目本身仔细分析，就会发现前面的思路并没有考虑到数组的特性：数组中有一个数字出现的次数超过了数组长度的一半。
        //// 如果把这个数组排序，那么排序之后位于数组中间的数字一定就是那个出现次数超过数组长度一半的数字。
        //// 也就是说，这个数字就是统计学上的中位数，即长度为n的数组中第n/2大的数字。我们有成熟的时间复杂度为O（n）的算法得到数组中任意第k大的数字。
        //
        //// 这种算法受快速排序算法的启发。
        // 在随机快速排序算法中，我们先在数组中随机选择一个数字，然后调整数组中数字的顺序，使得比选中的数字小的数字都排在它的左边，比选中的数字大的数字都排在它的右边。
        //// 如果这个选中的数字的下标刚好是n/2，那么这个数字就是数组的中位数;
        //// 如果它的下标大于n/2，那么中位数应该位于它的左边，我们可以接着在它的左边部分的数组中查找;
        //// 如果它的下标小于n/2，那么中位数应该位于它的右边，我们可以接着在它的右边部分的数组中查找。
        //
        //public int majorityElement(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return Integer.MIN_VALUE;
        //    }
        //
        //    int len = nums.length;
        //    int middle = len >> 1;
        //    int start = 0;
        //    int end = len - 1;
        //    int index = partition(nums, start, end);
        //    while (index != middle) {
        //        // 如果它的下标大于n/2，那么中位数应该位于它的左边，我们可以接着在它的左边部分的数组中查找;
        //        if (index > middle) {
        //            end = index - 1;
        //            index = partition(nums, start, end);
        //        } else {
        //        // 如果它的下标小于n/2，那么中位数应该位于它的右边，我们可以接着在它的右边部分的数组中查找。
        //            start = index + 1;
        //            index = partition(nums, start, end);
        //        }
        //    }
        //    // 如果这个选中的数字的下标刚好是n/2，那么这个数字就是数组的中位数;
        //    int result = nums[middle];
        //    return result;
        //}
        //
        //
        //
        //private int partition(int[] nums,int left,int right){
        //    if (nums == null || nums.length == 0 || left > right || left < 0 || left >= nums.length || right < 0 ||
        //    right >=nums.length) {
        //        return Integer.MIN_VALUE;
        //    }
        //    int pivot = new Random().nextInt(right - left + 1) + left;// 随机选取[left,right]中的 "基准"（pivot）
        //    swap(nums, pivot, right);// 先将一个随机选中的"基准"（pivot）交换至数组的尾部
        //    int prevSmall = left - 1;// prevSmall指向上一个比"基准"（pivot）小的元素，取区间[left,right]的left左侧第一个
        //
        //    for (int i = left; i < right; i++) {// 指针i遍历[left,right-1]将遍历到的小于"基准"（pivot）的元素放置在前面
        //        // 当前元素小于基准，将该元素放置在区间[left,right]的前半部分
        //        if (nums[i] < nums[right]) {
        //            prevSmall++;// 因为prevSmall指向上一个比"基准"（pivot）小的元素，所以先要后移至新的位置然后再交换
        //            swap(nums, i, prevSmall);
        //        }
        //    }
        //
        //    // 前面结束之后，prevSmall指向最后一个比"基准"（pivot）小的位置，将基准交换至合适的位置。即左边比基准小，右边比基准大
        //    prevSmall++;
        //    swap(nums, prevSmall, right);
        //    return prevSmall;// 基准所在位置
        //}
        //
        //
        //
        //private void swap(int[] nums, int index1, int index2) {
        //    if (index1 != index2) {
        //        int temp = nums[index1];
        //        nums[index1] = nums[index2];
        //        nums[index2] = temp;
        //    }
        //}







        ////  解法2：排序
        ////  如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，那么下标为n/2的元素（下标从 0 开始）一定是众数。
        //
        //public int majorityElement(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return Integer.MIN_VALUE;
        //    }
        //    Arrays.sort(nums);
        //    return nums[nums.length / 2];
        //}




        /*
        //	 解法1：哈希表
        public int majorityElement(int[] nums) {
            if (nums == null || nums.length == 0) {
                return Integer.MIN_VALUE;
            }
            //哈希表统计每个数字出现的次数,即key值为数组元素，value值为其出现次数
            Map<Integer, Integer> map = new HashMap<>();
            int length = nums.length;
            int ans = 0;
            for (int i = 0; i < length; i++) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
                if (map.get(nums[i]) > length / 2) {
                    ans = nums[i];
                    break;
                }
            }
            return ans;
        }
        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
