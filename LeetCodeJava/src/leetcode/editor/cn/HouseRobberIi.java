/**
 * <p>你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 <strong>围成一圈</strong>
 * ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，<strong>如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警</strong> 。</p>
 *
 * <p>给定一个代表每个房屋存放金额的非负整数数组，计算你 <strong>在不触动警报装置的情况下</strong> ，今晚能够偷窃到的最高金额。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,3,2]
 * <strong>输出：</strong>3
 * <strong>解释：</strong>你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3,1]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * &nbsp;    偷窃到的最高金额 = 1 + 3 = 4 。</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 100</code></li>
 * <li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1120</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 213
 * 打家劫舍 II
 *
 * @author wangweizhou
 * @date 2022-08-07 18:30:47
 */

public class HouseRobberIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new HouseRobberIi().new Solution();
        int[] nums = {1, 2, 3};
        int ans = solution.rob(nums);
        System.out.println(ans);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 区别在于小偷不能同时到标号为0和n-1的两幢房屋内偷东西。
        // 如果他考虑去标号为0的房屋，那么他一定不能去标号为n-1的房屋；如果他考虑去标号为n-1的房屋，那么他一定不能去标号为0的房屋。
        // 因此，可以将这个问题分解成两个子问题：一个问题是求小偷从标号为0开始到标号为n-2结束的房屋内能偷得的最多财物数量，
        // 另一个问题是求小偷从标号为1开始到标号为n-1结束的房屋内能偷得的最多财物数量。
        // 小偷从标号为0开始到标号为n-1结束的房屋内能偷得的最多财物数量是这两个子问题的解的最大值。


        // 解法1：动态规划
        // 状态定义：可以用f（i）表示小偷从标号为0的房屋开始到标号为i【包括下标i】的房屋为止最多能偷取到的财物的最大值。
        // 最终的状态转移方程： dp[i]=max(dp[i-1],dp[i−2]+nums[i])  这个就是和递归类似，只考虑功能上的。
        //	 环状排列意味着第一个房子和最后一个房子中只能选择一个偷窃，因此可以把此环状排列房间问题约化为两个单排排列房间子问题：
        //   1.在不偷窃第一个房子的情况下（即 nums[1:n]），最大金额是 p_1；
        //   2.在不偷窃最后一个房子的情况下（即 nums[0:n-1]），最大金额是 p_2。
        //   综合偷窃最大金额： 为以上两种情况的较大值，即 max(p1,p2) 。



        // 解法1:写法1：动态规划 dp[]数组的长度和nums数组长度一样
        // 这里定义区间为双闭区间[start,end]，注意代码前后对应
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {// 判空
                return 0;
            }
            int length = nums.length;
            if (length == 1) {// 只有一个房子，贼不走空
                return nums[0];
            }
            return Math.max(robFun(nums, 0, length - 2), robFun(nums, 1, length - 1));
        }


        //  抢劫区间房屋 [start,end]
        private int robFun(int[] nums, int start, int end) {
            if (nums == null || nums.length == 0 || start > end) {//判空
                return 0;
            }
            int[] dp = new int[nums.length];// 创建长度为（nums.length）的动态数组来存储。
            // 初始状态
            dp[start] = nums[start];// 区间开始的第1间，只有这个是最大的
            if (start < end) {// 区间内不止一个元素
                dp[start + 1] = Math.max(nums[start], nums[start + 1]);//前2间，偷最大的
            }

            // 状态转移方程
            for (int i = start + 2; i <= end; i++) {//从开始位置的后两个算起
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[end];
        }



        //// 解法1:写法1优化：动态规划 优化空间。dp数组长度和[start，end]数组一样长，长度也就减小了1，没必要。
        //public int rob(int[] nums) {
        //    if (nums == null || nums.length == 0) {//判空
        //        return 0;
        //    }
        //    int length = nums.length;
        //    if (length == 1) {//只有一个房子，贼不走空
        //        return nums[0];
        //    }
        //    return Math.max(robFun(nums, 0, length - 2), robFun(nums, 1, length - 1));
        //}
        //
        //
        //
        // // 这里定义区间为双闭区间[start,end]，注意代码前后对应
        //private int robFun(int[] nums, int start, int end) {
        //    if (nums == null || nums.length == 0 || start > end) {//判空
        //        return 0;
        //    }
        //    int[] dp = new int[end - start + 1];// 创建长度为（end - start + 1）的动态数组来存储。注意数组下标从0开始，所以这里要偏移。
        //    dp[start-start] = nums[start];//开始的第1间，只有这个是最大的
        //    if(start<end){
        //        dp[start + 1-start] = Math.max(nums[start], nums[start + 1]);//前2间，偷最大的
        //    }
        //
        //    for (int i = start + 2; i <= end; i++) {//从开始位置的后两个算起
        //        dp[i-start] = Math.max(dp[i - 1-start], dp[i - 2-start] + nums[i]);
        //    }
        //    //// 从dp[2]开始，注意下标的匹配
        //    //for (int i = 2; i <end-start+1 ; i++) {
        //    //    dp[i]=Math.max(dp[i-1],dp[i-2]+nums[start+i]);
        //    //}
        //    return dp[end-start];
        //}




        //// 解法1：写法3：动态规划 + 动态数组
        //public int rob(int[] nums) {
        //    if (nums == null || nums.length == 0) {// 判空
        //        return 0;
        //    }
        //    int length = nums.length;
        //    if (length == 1) {// 只有一个房子，贼不走空
        //        return nums[0];
        //    }
        //    return Math.max(robFun(nums, 0, length - 2), robFun(nums, 1, length - 1));
        //}
        //
        //
        //private int robFun(int[] nums, int start, int end) {
        //    if (nums == null || nums.length == 0 || start > end) {//判空
        //        return 0;
        //    }
        //    // dp数组只有两个元素，dp数组下标从0开始，nums数组从start开始。那就是说dp数组下标0对应的nums数组的下标start。
        //    int[] dp = new int[2];// 创建长度为2的动态数组来存储。注意数组下标从0开始，所以dp的下标要偏移。
        //    dp[0] = nums[start];//开始的第1间，只有这个是最大的
        //    if (start < end) {// [start,end]至少有两个元素
        //        dp[1] = Math.max(nums[start], nums[start + 1]);//前2间，偷最大的
        //    }
        //
        //    for (int i = start + 2; i <= end; i++) {//从开始位置的后两个算起
        //        dp[(i - start) % 2] = Math.max(dp[(i - 1 - start) % 2], dp[(i - 2 - start) % 2] + nums[i]);
        //    }
        //    return dp[(end - start) % 2];
        //}




/*
        // 解法1：动态规划  写法1
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {//判空
                return 0;
            }
            int length = nums.length;
            if (length == 1) {//只有一个房子，贼不走空
                return nums[0];
            }
            if (length == 2) {//只有两个房子，偷多的
                return Math.max(nums[0], nums[1]);
            }

            //情况1：不偷最后一家
            int[] dp1 = new int[length];
            dp1[0] = nums[0];
            dp1[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < length - 1; i++) {
                dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
            }


            //情况2：不偷第一家
            int[] dp2 = new int[length];
            dp2[1] = nums[1];
            dp2[2] = Math.max(nums[1], nums[2]);
            for (int i = 3; i < length; i++) {
                dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i]);
            }
			return Math.max(dp1[length - 2], dp2[length - 1]);
        }

        */


    }

//leetcode submit region end(Prohibit modification and deletion)

}
