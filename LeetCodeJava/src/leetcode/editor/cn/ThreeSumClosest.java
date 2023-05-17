/**
 * <p>给你一个长度为 <code>n</code> 的整数数组&nbsp;<code>nums</code><em>&nbsp;</em>和 一个目标值&nbsp;<code>target</code>。请你从
 * <code>nums</code><em> </em>中选出三个整数，使它们的和与&nbsp;<code>target</code>&nbsp;最接近。</p>
 *
 * <p>返回这三个数的和。</p>
 *
 * <p>假定每组输入只存在恰好一个解。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [-1,2,1,-4], target = 1
 * <strong>输出：</strong>2
 * <strong>解释：</strong>与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,0,0], target = 1
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>3 &lt;= nums.length &lt;= 1000</code></li>
 * <li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
 * <li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 1224</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 16
 * 最接近的三数之和
 *
 * @author wangweizhou
 * @date 2022-08-29 11:18:34
 */

public class ThreeSumClosest {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ThreeSumClosest().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1 ：双指针+排序+去重    唯一解
        // 注意16和15的不同，16是唯一解，15是所有不重复解
        // 因为这个题目是求三数之和与目标值target最接近的情况，那么每次有一个新的三数之和组合，需要判断与目标值target的接近程度。
        public int threeSumClosest(int[] nums, int target) {
            if (nums == null || nums.length < 3) {// 数组为空或者数组长度小于3
                return Integer.MAX_VALUE;
            }
            Arrays.sort(nums);
            int result = nums[0] + nums[1] + nums[2];
            for (int i = 0; i < nums.length - 2; i++) {
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    // 对于每一个i首先处理三数之和的上下限，这样剪枝快
                    // 对于每个i在区间[left,len-1]中找出（nums[i] + nums[left] + nums[left + 1]）的组合是最小的，之后的组合和肯定是越来越大，
                    // 那么该组合就是离target最近的组合。
                    int min = nums[i] + nums[left] + nums[left + 1];
                    if (target < min) {// 对应特定i，最小值大于target，则后面的组合都越来越大,剪枝
                        // 检查对于特定的i三数之和最小值和上一个三数之和哪一个更接近目标值target
                        if (Math.abs(result - target) > Math.abs(min - target)) {
                            result = min;
                        }
                        break;
                    }
                    // 对于每个i判断三数之和最大值
                    // 对于每个i在区间[left,len-1]中找出（nums[i] + nums[right - 1] + nums[right]）的组合是最大的，之后的组合和肯定是越来越小，
                    // 那么该组合就是离target最近的组合。
                    int max = nums[i] + nums[right - 1] + nums[right];
                    if (target > max) {// 对应特定i，最大值小于target，则后面的组合都越来越小，剪枝
                        // 检查对于特定的i三数之和最大值和上一个三数之和哪一个更接近目标值target
                        if (Math.abs(result - target) > Math.abs(max - target)) {
                            result = max;
                        }
                        break;
                    }

                    // 一般情况
                    int sum = nums[i] + nums[left] + nums[right];
                    // 判断三数之和是否等于target
                    if (sum == target) {// 三数之和和目标值相等，肯定是最近的，所以直接返回。
                        return sum;
                    }
                    if (Math.abs(sum - target) < Math.abs(result - target)) {// 当sum与target越来越近时更新sum
                        result = sum;
                    }
                    if (sum > target) {// 和要变小
                        // 解决nums[right]重复
                        right--;
                        while (left != right && nums[right] == nums[right + 1]) {// 快速剪枝，把与right指向相同的全部去掉
                            right--;
                        }
                    } else {// 和要变大
                        // 解决nums[left]重复
                        left++;
                        while (left != right && nums[left] == nums[left - 1]) {// 快速剪枝，把与left指向相同的全部去掉
                            left++;
                        }
                    }
                }
                while (i < nums.length - 2 && nums[i] == nums[i + 1]) {// 这里是当前数字对应的三数之和已经计算，所以可以和后一个数进行比较剪枝，跳过和当前数字相同的。
                    i++;
                }
            }
            return result;
        }



        //
        //public int threeSumClosest(int[] nums, int target) {
        //    if (nums == null || nums.length < 3) {// 数组为空或者数组长度小于3
        //        return Integer.MAX_VALUE;
        //    }
        //    Arrays.sort(nums);
        //    int len=nums.length;
        //    int res=nums[0]+nums[1]+nums[2];
        //    for (int i = 0; i < len-2; i++) {
        //        if(i>0&&nums[i]==nums[i-1]){ //注意这里是（i-1）因为先要算一次，所以要保证下标大于0，然后看后面的左右指针能否使用nums[i]
        //            continue;
        //        }
        //        int left=i+1;
        //        int right=len-1;
        //        while (left<right){
        //            int min =nums[i]+nums[left]+nums[left+1];
        //            if(min >target){
        //                if(Math.abs(min -target)<Math.abs(res-target)){
        //                    res= min;
        //                }
        //                break;
        //            }
        //            int max=nums[i]+nums[right-1]+nums[right];
        //            if(max<target){
        //                if(Math.abs(max-target)<Math.abs(res-target)){
        //                    res=max;
        //                }
        //                break;
        //            }
        //            int  sum=nums[i]+nums[left]+nums[right];
        //            if(sum==target){
        //                return sum;
        //            }
        //            if(Math.abs(sum-target)<Math.abs(res-target)){
        //                res=sum;
        //            }
        //            if(sum>target){
        //                right--;
        //                while (left<right&&nums[right]==nums[right+1]){
        //                    right--;
        //                }
        //            }else if(sum<target){
        //                left++;
        //                while (left<right&&nums[left-1]==nums[left]){
        //                    left++;
        //                }
        //            }
        //        }
        //    }
        //    return res;
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
