/**
 * <p>给你一个整数数组 <code>cost</code> ，其中 <code>cost[i]</code> 是从楼梯第 <code>i</code>
 * 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。</p>
 *
 * <p>你可以选择从下标为 <code>0</code> 或下标为 <code>1</code> 的台阶开始爬楼梯。</p>
 *
 * <p>请你计算并返回达到楼梯顶部的最低花费。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>cost = [10,<em><strong>15</strong></em>,20]
 * <strong>输出：</strong>15
 * <strong>解释：</strong>你将从下标为 1 的台阶开始。
 * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
 * 总花费为 15 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>cost = [<em><strong>1</strong></em>,100,<em><strong>1</strong></em>,1,<em><strong>1</strong></em>,100,<em><strong>1</strong></em>,<em><strong>1</strong></em>,100,<em><strong>1</strong></em>]
 * <strong>输出：</strong>6
 * <strong>解释：</strong>你将从下标为 0 的台阶开始。
 * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
 * 总花费为 6 。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= cost.length &lt;= 1000</code></li>
 * <li><code>0 &lt;= cost[i] &lt;= 999</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 974</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 746
 * 使用最小花费爬楼梯
 *
 * @author wangweizhou
 * @date 2022-08-05 01:34:23
 */

public class MinCostClimbingStairs {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new MinCostClimbingStairs().new Solution();
        int[] cost={1,100,1,1,100,1};
        int min=solution.minCostClimbingStairs(cost);
        System.out.println(min);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法2： 动态规划2：和另一个动态规划1的状态定义和状态转移方程不一样
        // 状态定义：用函数f（i）表示从楼梯的第i级台阶再往上爬的最少成本。
        // 如果一个楼梯有n级台阶（台阶从0开始计数，从第0级一直到第n-1级），由于一次可以爬1级或2级台阶，
        // 因此最终可以从第n-2级台阶或第n-1级台阶爬到楼梯的顶部，即f（n-1）和f（n-2）的最小值就是这个问题的最优解。


        // 应用动态规划的第1步是找出状态转移方程，即用一个等式表示其中某一步的最优解和前面若干步的最优解的关系。
        // 根据题目的要求，可以一次爬1级或2级台阶，既可以从第i-1级台阶爬上第i级台阶，也可以从第i-2级台阶爬上第i级台阶，
        // 因此，从第i级台阶往上爬的最少成本应该是从第i-1级台阶往上爬的最少成本和从第i-2级台阶往上爬的最少成本的较小值再加上爬第i级台阶的成本。
        // 这个关系可以用状态转移方程表示为f（i）=min（f（i-1），f（i-2））+cost[i]。
        // 上述状态转移方程有一个隐含的条件，即i大于或等于2。如果i小于2怎么办？如果i等于0，则可以直接从第0级台阶往上爬，f（0）等于cost[0]。
        // 如果i等于1，题目中提到可以从第1级台阶出发往上爬，因此f（1）等于cost[1]。


        // 状态转移方程：f（i）=min（f（i-1），f（i-2））+cost[i]。

        // 解法2：写法1 动态规划：状态定义：用函数f（i）表示从楼梯的第i级台阶再往上爬的最少成本。
        public int minCostClimbingStairs(int[] cost) {
            if (cost == null || cost.length == 0) {// 判空
                return 0;
            }
            int length=cost.length;
            int[] dp=new int[length];// 数组dp用来保存求解每个问题结果的缓存。“dp[i]”表示从楼梯的第i级台阶再往上爬的最少成本。
            dp[0]=cost[0];
            dp[1]=cost[1];
            for(int i=2;i<length;i++){
                dp[i]=Math.min(dp[i-1],dp[i-2])+cost[i];
            }
            return Math.min(dp[length-2],dp[length-1]);
        }





        // 解法2：动态规划2 写法2 动态规划+空间优化 状态定义：用函数f（i）表示从楼梯的第i级台阶再往上爬的最少成本。
        // 前面用一个长度为n的数组将所有f（i）的结果都保存下来。求解f（i）时只需要f（i-1）和f（i-2）的结果，
        // 从f（0）到f（i-3）的结果其实对求解f（i）并没有任何作用。
        // 也就是说，在求每个f（i）的时候，需要保存之前的f（i-1）和f（i-2）的结果，因此只要一个长度为2的数组即可。

        //public int minCostClimbingStairs(int[] cost) {
        //    if (cost == null || cost.length == 0) {// 判空
        //        return 0;
        //    }
        //    int length=cost.length;
        //    int first =cost[0];// 从当前台阶的前面倒数第一个台阶向上爬的最小花费
        //    int second =cost[1];// 从当前台阶的前面倒数第二个台阶向上爬的最小花费
        //    for(int i=2;i<length;i++){
        //        int curr =Math.min(first, second)+cost[i];// 从下一个台阶
        //        first = second;
        //        second = curr;
        //    }
        //    return Math.min(first, second);
        //}




        // 数组dp的长度是2，求解的f（i）的结果保存在数组下标为“i%2”的位置
        // 根据f（i-1）和f（i-2）的结果计算出f（i）的结果，并将f（i）的结果写入之前保存f（i-2）的位置。
        // 用f（i）的结果覆盖f（i-2）的结果并不会带来任何问题，这是因为接下来求解f（i+1）只需要f（i）的结果和f（i-1）的结果，不需要f（i-2）的结果

        //// 解法2：动态规划2 写法3 动态规划： 状态定义：用函数f（i）表示从楼梯的第i级台阶再往上爬的最少成本。
        //// 动态规划+滚动数组dp
        //public int minCostClimbingStairs(int[] cost) {
        //    if (cost == null || cost.length == 0) {// 判空
        //        return 0;
        //    }
        //    int length=cost.length;
        //    int[] dp=new int[]{cost[0],cost[1]};
        //    for(int i=2;i<length;i++){
        //        dp[i%2]=Math.min(dp[0], dp[1])+cost[i];// 从下一个台阶
        //    }
        //    return Math.min(dp[0], dp[1]);
        //}





        // 解法1：动态规划1  写法1 动态规划1的状态方程和动态规划2的转移方程定义不一样
        // 假设数组 cost 的长度为 n，则 n 个阶梯分别对应下标 0 到 n−1，楼层顶部对应下标 n，问题等价于计算达到下标 n 的最小花费。
        // 定义状态：dp[i] 表示达到下标 i 的最小花费
        // 状态转移方程：dp[i]=min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2])，每次支付之后可以跨一个台阶或者两个台阶。
        // 初始状态：题干说可以从下标为0或者1的台阶开始爬，那么达到下标为0或者1的的花费都是0。  dp[0]=0;dp[1]=0;

        //public int minCostClimbingStairs(int[] cost) {
        //    if (cost == null || cost.length == 0) {// 判空
        //        return 0;
        //    }
        //    // int数组默认初始值是0。
        //    //（初始状态）因为可以直接从第0级或是第1级台阶开始，因此这两级的花费都直接为0.所以java这里不需要额外初始化
        //    int[] dp = new int[cost.length + 1];//用来保存最小花费的数组
        //    // 初始状态：题干说可以从下标为0或者1的台阶开始爬，那么达到下标为0或者1的的花费都是0.
        //    dp[0]=0;
        //    dp[1]=0;
        //    for (int i = 2; i < cost.length + 1; i++) {
        //        dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);// 更新最小值即可
        //    }
        //    return dp[cost.length];
        //}




     /*
        // 解法1：动态规划1 写法2 动态规划+动态数组
        // 定义状态：dp[i] 表示达到下标 i 的最小花费
        // 初始状态：题干说可以从下标为0或者1的台阶开始爬，那么达到下标为0或者1的的花费都是0。  dp[0]=0;dp[1]=0;
        // 状态转移方程：dp[i]=min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2])，每次支付之后可以跨一个台阶或者两个台阶
        // 求解dp[i]只依赖与dp[i-1]和dp[i-2]，所以可以使用动态数组简化空间效率。

        public int minCostClimbingStairs(int[] cost) {
            if (cost == null || cost.length == 0) {// 判空
                return 0;
            }
            int prev = 0;
            int curr = 0;
            for (int i = 2; i < cost.length + 1; i++) {
                int next = Math.min(prev + cost[i - 2], curr + cost[i - 1]);
                prev = curr;
                curr = next;
            }
            return curr;
        }
*/




        // 解法2：写法1  递归有重复计算，超时
        //public int minCostClimbingStairs(int[] cost) {
        //    if (cost == null || cost.length == 0) {// 判空
        //        return 0;
        //    }
        //    int length=cost.length;
        //    return Math.min(minCostFunc(cost,length-2),minCostFunc(cost,length-1));
        //}
        //
        //private int minCostFunc(int[] costs,int i){
        //    if(i<2){
        //        return costs[i];
        //    }
        //    return Math.min(minCostFunc(costs,i-2),minCostFunc(costs,i-1))+costs[i];
        //}





        // 解法2：写法2   递归：自上而下解决问题。使用缓存的递归代码
        // 为了避免重复计算带来的问题，一个常用的解决办法是将已经求解过的问题的结果保存下来。在每次求解一个问题之前，应先检查该问题的求解结果是否已经存在。

        //public int minCostClimbingStairs(int[] cost) {
        //    if (cost == null || cost.length == 0) {// 判空
        //        return 0;
        //    }
        //    int length=cost.length;
        //    int[] dp=new int[length];// 数组dp用来保存求解每个问题结果的缓存。“dp[i]”表示从楼梯的第i级台阶再往上爬的最少成本。
        //    minCostFunc(cost,length-1,dp);//
        //    return Math.min(dp[length-2],dp[length-1]);
        //}
        //
        //
        //// 递归计算dp[i]。
        //// 该数组的每个元素都初始化为0。由于题目中从每级台阶往上爬的成本都是正数，因此如果某个问题f（i）之前已经求解过，
        //// 那么“dp[i]”的缓存的结果将是一个大于0的数值。只有当“dp[i]”等于0时，它对应的f（i）之前还没有求解过。

        //private void minCostFunc(int[] costs,int i,int[] dp){
        //    if(i<2){
        //        dp[i]= costs[i];
        //    }else if(dp[i]==0){
        //        minCostFunc(costs,i-2,dp);
        //        minCostFunc(costs,i-1,dp);
        //        dp[i]=Math.min(dp[i-2],dp[i-1])+costs[i];
        //    }
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
