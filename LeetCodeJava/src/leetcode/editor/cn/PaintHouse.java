/**
 * <p>假如有一排房子，共 <code>n</code> 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。</p>
 *
 * <p>当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 <code>n x 3</code><em> </em>的正整数矩阵
 * <code>costs</code> 来表示的。</p>
 *
 * <p>例如，<code>costs[0][0]</code> 表示第 0 号房子粉刷成红色的成本花费；<code>costs[1][2]</code> 表示第 1 号房子粉刷成绿色的花费，以此类推。</p>
 *
 * <p>请计算出粉刷完所有房子最少的花费成本。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>costs = [[17,2,17],[16,16,5],[14,3,19]]
 * <strong>输出: </strong>10
 * <strong>解释: </strong>将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色<strong>。</strong>
 * 最少花费: 2 + 5 + 3 = 10。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>costs = [[7,6,2]]
 * <strong>输出: 2</strong>
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>costs.length == n</code></li>
 * <li><code>costs[i].length == 3</code></li>
 * <li><code>1 <= n <= 100</code></li>
 * <li><code>1 <= costs[i][j] <= 20</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 188</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 256
 * 粉刷房子
 *
 * @author wangweizhou
 * @date 2022-08-16 05:43:59
 */

public class PaintHouse {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new PaintHouse().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 每步粉刷1幢房子，粉刷n幢房子需要n步。由于每幢房子都能被粉刷成红色、绿色和蓝色这3种颜色中的一种，因此每步都面临3种选择。
        // 求最优解，因此这个问题适合用动态规划解决。
        // 输入的n幢房子可以看成一个序列。每步多考虑1幢房子，在标号从0开始到i-1结束的房子的最少粉刷成本的基础上计算标号从0开始到i结束的房子的最少粉刷成本。


        // 动态规划 解题思路2：将在第i步时的多个选择表示成多个状态。注意学习这种思路。
        // 根据粉刷的规则，相邻的两幢房子不能被粉刷成相同的颜色，要计算粉刷到标号为i的房子时的成本，还需要考虑标号为i-1的房子的颜色。
        // 在粉刷第i个房子时有三种不同的选择，因此，需要3个表达式，即r（i）、g（i）、b（i），分别表示将标号为i的房子粉刷成红色、绿色和蓝色时粉刷标号从0到i的i+1幢房子的最少成本。
        // 假设粉刷每幢房子的成本用一个二维数组costs表示，那么costs[i]中包含的3个数字分别是将标号为i的房子粉刷成红色、绿色和蓝色的成本。
        // 当标号为i的房子被粉刷成红色时，标号为i-1的房子可以被粉刷成绿色或蓝色，因此r（i）=min（g（i-1），b（i-1））+costs[i][0]。
        // 类似地，当标号为i的房子被粉刷成绿色时，标号为i-1的房子可以被粉刷成红色或蓝色，因此g（i）=min（r（i-1），b（i-1））+costs[i][1]；
        // 当标号为i的房子被粉刷成蓝色时，标号为i-1的房子可以被粉刷成红色或绿色，因此b（i）=min（r（i-1），g（i-1））+costs[i][2]。

        // 这3个状态转移方程有一个隐含条件，要求i大于0，否则i-1没有意义。
        // 当i等于0时，r（0）就是将标号为0的房子粉刷成红色的成本costs[0][0]，g（0）就是将标号为0的房子粉刷成绿色的成本costs[0][1]，
        // 而b（0）就是将标号为0的房子粉刷成蓝色的成本costs[0][2]。


        //	解法1：动态规划
        //	costs数组第一维表示第几号房子，第二维表示刷成对应颜色的成本。
        // 定义状态：用 dp[i][j] 表示截止到第 i 号房子【包括】且第 i 号房子被粉刷成第 j 种颜色时的最小花费成本。由于一共有 n 个房子和 3 种颜色，因此 0≤i<n，0≤j<3。
        // 状态转移方程：对于 1≤i<n，第 i 号房子和第 i−1 号房子的颜色必须不同，因此当第 i 号房子被粉刷成某一种颜色时，第 i−1号房子只能被粉刷成另外两种颜色之一。
        //      dp[i][0]=min(dp[i−1][1],dp[i−1][2])+costs[i][0]
        //      dp[i][1]=min(dp[i−1][0],dp[i−1][2])+costs[i][1]
        //      dp[i][2]=min(dp[i−1][0],dp[i−1][1])+costs[i][2]
        // 初始状态：上面状态转移方程，i>=1。对于任意 0≤j<3，dp[0][j]=costs[0][j]，表示第0号房子被刷成第3种颜色的成本。


        public int minCost(int[][] costs) {
            if (costs == null || costs.length == 0 || costs[0].length == 0) {// 判空
                return -1;
            }

            int rowLen = costs.length;// 二维数组行数就是房间数
            int colLen = costs[0].length;// 二维数组列数就是颜色数
            // dp[i][j] 表示截止到第 i 号房子且第 i 号房子被粉刷成第 j 种颜色时的最小花费成本
            int[][] dp = new int[rowLen][colLen];
            // 初始状态：第一个房子装修成什么颜色
            // 二维数组的第二维：0 - 红色，1 - 蓝色，2 - 绿色

            dp[0][0] = costs[0][0];
            dp[0][1] = costs[0][1];
            dp[0][2] = costs[0][2];
            for (int i = 1; i < rowLen; i++) {
                // 因为颜色只有三种，这里直接列举出来就行，没有使用循环
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
            }
            return Math.min(dp[rowLen - 1][0], Math.min(dp[rowLen - 1][1], dp[rowLen - 1][2]));// 找出最后一个房间涂完色后的最小值
        }




        // 下面把3个列举写成了循环
        //public int minCost(int[][] costs) {
        //    if (costs == null||costs.length==0) {// 判空
        //        return Integer.MIN_VALUE;
        //    }
        //
        //    int rowLen = costs.length;//二维数组行数就是房间数
        //    int colLen = costs[0].length;//二维数组列数就是颜色数
        //    // dp[i][j] 表示截止到第 i 号房子且第 i 号房子被粉刷成第 j 种颜色时的最小花费成本
        //    int[][] dp = new int[rowLen][colLen];
        //    // 初始状态：第一个房子装修成什么颜色
        //    // 二维数组的第二维：0 - 红色，1 - 蓝色，2 - 绿色
        //
        //    dp[0][0] = costs[0][0];
        //    dp[0][1] = costs[0][1];
        //    dp[0][2] = costs[0][2];
        //    for (int i = 1; i < rowLen; i++) {
        //        for(int j=0;j<colLen;j++){
        //            dp[i][j%3] = Math.min(dp[i - 1][(j+1)%3], dp[i - 1][(j+2)%3]) + costs[i][j];
        //        }
        //    }
        //    return Math.min(dp[rowLen - 1][0], Math.min(dp[rowLen - 1][1], dp[rowLen - 1][2]));// 找出最后一个房间涂完色后的最小值
        //}






	/*

	// 解法2：动态规划+动态数组
	public int minCost(int[][] costs) {
		if (costs == null||costs.length==0) {
			return Integer.MIN_VALUE;
		}
		int redCost=costs[0][0];
		int blueCost=costs[0][1];
		int yellowCost=costs[0][2];

		for (int i = 1; i < costs.length; i++) {
			int newRedCost=Math.min(blueCost,yellowCost)+costs[i][0];
			int newBlueCost=Math.min(redCost,yellowCost)+costs[i][1];
			int newYellowCost=Math.min(redCost,blueCost)+costs[i][2];
			redCost=newRedCost;
			blueCost=newBlueCost;
			yellowCost=newYellowCost;
		}
		return Math.min(redCost,Math.min(blueCost,yellowCost));
	}

	*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
