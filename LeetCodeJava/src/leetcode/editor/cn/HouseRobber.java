/**
 * <p>你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，<strong>如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警</strong>。</p>
 *
 * <p>给定一个代表每个房屋存放金额的非负整数数组，计算你<strong> 不触动警报装置的情况下 </strong>，一夜之内能够偷窃到的最高金额。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>[1,2,3,1]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>[2,7,9,3,1]
 * <strong>输出：</strong>12
 * <strong>解释：</strong>偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 100</code></li>
 * <li><code>0 <= nums[i] <= 400</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 2176</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 198
 * 打家劫舍
 *
 * @author wangweizhou
 * @date 2022-06-28 10:25:47
 */

public class HouseRobber {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new HouseRobber().new Solution();
        //int[] nums = {1, 2, 3, 1};
        int[] nums = {2, 7, 9, 3, 1};
        int ans = solution.rob(nums);
        System.out.println(ans);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 小偷一次只能进入一幢房屋内盗窃，因此到街道上所有房屋中盗窃需要多个步骤，每一步到一幢房屋内盗窃。
        // 由于这条街道有报警系统，因此他每到一幢房屋前都面临一个选择，考虑是不是能进去偷东西。
        // 完成一件事情需要多个步骤，并且每一步都面临多个选择，求问题的最优解，因此这个问题适合运用动态规划。
        // 应用动态规划解决问题的关键在于找出状态转移方程。
        // 用动态规划解决单序列问题的关键在于找到序列中一个元素对应的解和前面若干元素对应的解的关系，并用状态转移方程表示。


        // 动态规划 解题思路2：将在第i步时的多个选择表示成多个状态。注意学习这种思路
        // 由于小偷到达标号为i的房屋时有两个选择，他可以选择进去偷东西或不进去偷东西，因此可以定义两个表达式g（i）和f（i），
        // 其中f（i）表示小偷选择不进入标号为i的房屋偷东西时能偷得的最多财物数量，而g（i）表示小偷选择进入标号为i的房屋偷东西时能偷得的最多财物数量。
        // f（n-1）和g（n-1）的最大值就是小偷能从n幢房屋内偷得的财物的最大值。

        // 当小偷选择不进入标号为i的房屋偷东西时，那么他不管是不是进入标号为i-1的房屋偷东西都不会触发报警系统，
        // 此时他能偷得的财物数量取决于他从标号为0的房屋开始到标号为i-1的房屋为止能偷得的财物数量，因此f（i）=max（f（i-1），g（i-1））。
        // 当小偷选择进入标号为i的房屋偷取价值为nums[i]的财物时，那么他一定不能进入标号为i-1的房屋偷东西，否则就会触发报警系统，因此g（i）=f（i-1）+nums[i]。

        // 两个状态转移方程有一个隐含条件，要求i大于0，否则i-1没有意义。
        // 当i等于0时，f（0）表示街道上只有标号为0的房屋并且小偷选择不进去偷东西，那么他什么也没有偷到，因此f（0）=0。
        // g（0）表示当只有标号为0的房屋并且小偷选择进去偷东西，那么房屋内财物的价值就是小偷能偷取的东西的价值，即g（0）=nums[0]。


        // 解法2：动态规划  定义两个状态 ，下面的两个一维数组可以使用二维数组的第二维来表示。也可以使用动态数组来优化空间
        //public int rob(int[] nums) {
        //    if (nums == null || nums.length == 0) {//判空
        //        return 0;
        //    }
        //    int length = nums.length;
        //    int[] dpIn=new int[length];
        //    int[] dpNotIn=new int[length];
        //    dpIn[0]=nums[0];
        //    dpNotIn[0]=0;
        //
        //    for (int i = 1; i < length; i++) {
        //        dpIn[i]=dpNotIn[i-1]+nums[i];// 当小偷选择进入标号为i的房屋偷取价值为nums[i]的财物时，那么他一定不能进入标号为i-1的房屋偷东西
        //        dpNotIn[i]=Math.max(dpNotIn[i-1],dpIn[i-1]);// 当小偷选择不进入标号为i的房屋偷东西时，此时他能偷得的财物数量取决于他从标号为0的房屋开始到标号为i-1的房屋为止能偷得的财物数量
        //    }
        //    return Math.max(dpNotIn[length-1],dpIn[length-1] );
        //}




        //// 方法一：动态规划  从下往上
        //// 假设街道上有n幢房屋（分别用0～n-1标号），小偷从标号为0的房屋开始偷东西。
        //// 可以用f（i）表示小偷从标号为0的房屋开始到标号为i【包括下标i】的房屋为止最多能偷取到的财物的最大值。f（n-1）的值是小偷从n幢房屋中能偷取的最多财物的数量。

        //// 小偷在标号为i的房屋前有两个选择。
        //// 一个选择是他进去偷东西。由于街道上有报警系统，因此他不能进入相邻的标号为i-1的房屋内偷东西，之前他最多能偷取的财物的最大值是f（i-2）。
        //// 因此，小偷如果进入标号为i的房屋并盗窃，他最多能偷得f（i-2）+nums[i]（nums是表示房屋内财物数量的数组）。
        //// 另一个选择是小偷不进入标号为i的房屋，那么他可以进入标号为i-1的房屋内偷东西，因此此时他最多能偷取的财物的数量为f（i-1）。
        //// 那么小偷在到达标号为i的房屋时他能偷取的财物的最大值就是两个选项的最大值，即f（i）=max（f（i-2）+nums[i]，f（i-1）），这就是解决这个问题的状态转移方程。

        //// 假设i大于或等于2。当i等于0时，f（0）是街道上只有标号为0的一幢房屋时小偷最多能偷得的财物的数量，此时他无所顾忌，直接进入标号为0的房屋偷东西，因此f（0）=nums[0]；
        //// 当i等于1时，f（1）是街道上只有标号为0和1的两幢房屋时小偷最多能偷得的财物的数量，
        //// 因为街道上有报警系统，他只能到两幢房屋的其中一幢去偷东西，所以他应该选择到财物数量更多的房屋去偷东西，即f（1）=max（nums[0]，nums[1]）。

        //// 状态转移方程：f（i）=max（f（i-2）+nums[i]，f（i-1））
        //// 初始状态：f（0）=nums[0]；f（1）=max（nums[0]，nums[1]）。


         // 方法一： 写法1：动态规划  和写法2的不同之处只是对于数组长度为1的处理不同
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {// 判空
                return 0;
            }
            int length = nums.length;
            int[] dp = new int[length];//f（i）表示小偷从标号为0的房屋开始到标号为i【包括下标i】的房屋为止最多能偷取到的财物的最大值
            dp[0] = nums[0]; // f（0）是街道上只有标号为0的一幢房屋时小偷最多能偷得的财物的数量，此时他无所顾忌，直接进入标号为0的房屋偷东西，因此f（0）=nums[0]；
            // 这里要单独处理下标为1的元素，防止数组下标越界
            if(length>1){
                dp[1] = Math.max(nums[0], nums[1]);//f（1）是街道上只有标号为0和1的两幢房屋时小偷最多能偷得的财物的数量，他应该选择到财物数量更多的房屋去偷东西，即f（1）=max（nums[0]，nums[1]）
            }
            for (int i = 2; i < length; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[length - 1];
        }




        //// 方法一： 写法2：动态规划
        //public int rob(int[] nums) {
        //    if (nums == null || nums.length == 0) {//判空
        //        return 0;
        //    }
        //    if (nums.length == 1) {// 这里要单独处理下标为1的元素，防止数组下标越界
        //        return nums[0];
        //    }
        //    int length = nums.length;
        //    int[] dp = new int[length];//f（i）表示小偷从标号为0的房屋开始到标号为i【包括下标i】的房屋为止最多能偷取到的财物的最大值
        //    dp[0] = nums[0]; // f（0）是街道上只有标号为0的一幢房屋时小偷最多能偷得的财物的数量，此时他无所顾忌，直接进入标号为0的房屋偷东西，因此f（0）=nums[0]；
        //    dp[1] = Math.max(nums[0], nums[1]);
        // f（1）是街道上只有标号为0和1的两幢房屋时小偷最多能偷得的财物的数量，他应该选择到财物数量更多的房屋去偷东西，即f（1）=max（nums[0]，nums[1]）
        //    for (int i = 2; i < length; i++) {
        //        dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        //    }
        //    return dp[length - 1];
        //}






        // 状态转移方程： dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]).其实只需要存储dp[i - 1]和dp[i - 2]两个量就可以。
        // 考虑到每间房屋的最高总金额只和该房屋的前两间房屋的最高总金额相关，因此可以使用滚动数组，在每个时刻只需要存储前两间房屋的最高总金额。
        // 没优化前是用一个数组存储dp[i],实际在使用过程中只需要存储dp[i]的前两位dp[i-1]和dp[i-2]


        //// 方法一优化：写法2：动态规划+滚动数组
        //public int rob(int[] nums) {
        //    if (nums == null || nums.length == 0) {//判空
        //        return 0;
        //    }
        //    if (nums.length == 1) {// 这里要单独处理下标为1的元素，防止数组下标越界
        //        return nums[0];
        //    }
        //    int length = nums.length;
        //    int[] dp=new int[2];
        //    dp[0]=nums[0];
        //    dp[1]=Math.min(nums[0],nums[1]);
        //    for (int i = 2; i < length; i++) {
        //       dp[i%2]=Math.max(dp[(i-1)%2],dp[(i-2)%2]+nums[i]);
        //    }
        //    return  dp[(length-1)%2];
        //}




        // 方法一优化：写法1：动态规划+滚动数组
        //public int rob(int[] nums) {
        //    if (nums == null || nums.length == 0) {//判空
        //        return 0;
        //    }
        //    if (nums.length == 1) {// 这里要单独处理下标为1的元素，防止数组下标越界
        //        return nums[0];
        //    }
        //    int length = nums.length;
        //    int prev = nums[0]; // f（0）是街道上只有标号为0的一幢房屋时小偷最多能偷得的财物的数量，此时他无所顾忌，直接进入标号为0的房屋偷东西，因此f（0）=nums[0]；
        //    // 这里要单独处理下标为1的元素，防止数组下标越界
        //    int curr = Math.max(nums[0], nums[1]);//当前偷盗的最大金额//f（1）是街道上只有标号为0和1
        //    // 的两幢房屋时小偷最多能偷得的财物的数量，他应该选择到财物数量更多的房屋去偷东西，即f（1）=max（nums[0]，nums[1]）
        //    for (int i = 2; i < length; i++) {
        //        int next = Math.max(prev + nums[i], curr);
        //        prev = curr;
        //        curr = next;
        //    }
        //    return curr;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
