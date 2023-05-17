package template;

public class DP {

    // 区分两个概念：子序列可以是不连续的；子数组（子字符串）需要是连续的；
    //
    // 类似哨兵节点的处理：
    // 动态规划也是有套路的：单个数组或者字符串要用动态规划时，可以把动态规划 dp[i] 定义为 nums[0:i] 中想要求的结果；或者dp[i] 定义为 nums[0:i-1]中想要求的结果
    // 当两个数组或者字符串要用动态规划时，可以把动态规划定义成两维的 dp[i][j] ，其含义是在 A[0:i] 与 B[0:j] 之间匹配得到的想要的结果。或者其含义是在 A[0:i-1] 与 B[0:j-1]
    // 之间匹配得到的想要的结果。
    //

    // 假设数组 cost 的长度为 n，则 n 个阶梯分别对应下标 0 到 n−1，楼层顶部对应下标 n，问题等价于计算达到下标 n 的最小花费。

    // 解法1：动态规划  时间复杂度和空间复杂度都是 O(n)  注意题干是爬到楼顶，也就是n层楼要爬到（n+1）层
    // 定义状态：dp[i] 表示达到下标 i 的最小花费
    // 状态转移方程：dp[i]=min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2])，每次支付之后可以跨一个台阶或者两个台阶
    // 初始状态：题干说可以从下标为0或者1的台阶开始爬，那么达到下标为0或者1的的花费都是0.   dp[0]=0;dp[1]=0;

    // step1：可以用一个数组记录每次爬到第i阶楼梯的最小花费，然后每增加一级台阶就转移一次状态，最终得到结果。
    // step2:（初始状态）因为可以直接从第0级或是第1级台阶开始，因此这两级的花费都直接为0.
    // step3:（状态转移）每次到一个台阶，只有两种情况，要么是它前一级台阶向上一步，要么是它前两级的台阶向上两步，
    // 因为在前面的台阶花费我们都得到了，因此每次更新最小值即可，转移方程为：dp[i]=min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2])


    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {// 判空
            return 0;
        }
        // int数组默认初始值是0。
        //（初始状态）因为可以直接从第0级或是第1级台阶开始，因此这两级的花费都直接为0.所以java这里不需要额外初始化
        int[] dp = new int[cost.length + 1];//用来保存最小花费的数组
        // 初始状态：题干说可以从下标为0或者1的台阶开始爬，那么达到下标为0或者1的的花费都是0.
        dp[0]=0;
        dp[1]=0;
        for (int i = 2; i < cost.length + 1; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);// 更新最小值即可
        }
        return dp[cost.length];
    }




     /*
        // 解法2：动态规划+动态数组
        // 状态转移方程：dp[i]=min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2])，每次支付之后可以跨一个台阶或者两个台阶
        // 可以使用滚动数组

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




    // 二维动态规划

    // 状态定义： dp[i][j] 表示 text1[0:i-1] 和 text2[0:j-1] 的最长公共子序列。text1[0:i-1] 表示的是 text1 的 第 0 个元素到第 i - 1 个元素，两端都包含。
    // i=0,j=0，dp[0][0] 表示text1[0:-1] 和 text2[0:-1] 的最长公共子序列，这个是不存在的，所以为0。
    // i=1，j=1， dp[1][1] 表示text1[0:0] 和 text2[0:0] 的最长公共子序列，其实也就是两个数组的第一个元素。

    // 状态转移方程： 动态转移方程有（i - 1），所以循环时要从i=1开始。
    // dp[i][j]=dp[i−1][j−1]+1, 当 text1[i - 1] == text2[j - 1];
    // dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]), 当 text1[i−1]!=text2[j−1]

    // 状态的初始化：初始化就是要看当 i = 0 与 j = 0 时， dp[i][j] 应该取值为多少。
    // 当 i = 0 时，dp[0][j] 表示的是 text1 中取空字符串 跟 text2 的最长公共子序列，结果肯定为 0。
    // 当 j = 0 时，dp[i][0] 表示的是 text2 中取空字符串 跟 text1 的最长公共子序列，结果肯定为 0.
    // 当 i = 0 或者 j = 0 时，dp[i][j] 初始化为 0.


    public int longestCommonSubsequence(String text1, String text2) {
        if(text1==null||text1.length()==0||text2==null||text2.length()==0){
            return 0;
        }
        int len1=text1.length();
        int len2=text2.length();

        // dp[i][j] 表示 text1[0:i-1] 和 text2[0:j-1] 的最长公共子序列。
        // 当 i = 0 时，dp[0][j] 表示的是 text1 中取空字符串 跟 text2 的最长公共子序列，结果肯定为 0。
        // 当 j = 0 时，dp[i][0] 表示的是 text2 中取空字符串 跟 text1 的最长公共子序列，结果肯定为 0。
        // 当 i = 0 或者 j = 0 时，dp[i][j] 初始化为 0.因为 int 型数组的默认值就是0，所有初始化不用额外手动初始化了。

        int[][] dp=new int[len1+1][len2+1];
        for (int i = 1; i <=len1 ; i++) {
            for (int j = 1; j <=len2 ; j++) {
                if(text1.charAt(i-1)==text2.charAt(j-1)){//若字符相等
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{//若不想等
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
