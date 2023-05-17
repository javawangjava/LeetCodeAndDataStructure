/**
 * <p>给定一个包含非负整数的 <code><em>m</em> x <em>n</em></code> 网格 <code>grid</code> ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。</p>
 *
 * <p><strong>说明：</strong>每次只能向下或者向右移动一步。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/05/minpath.jpg" style="width: 242px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>grid = [[1,3,1],[1,5,1],[4,2,1]]
 * <strong>输出：</strong>7
 * <strong>解释：</strong>因为路径 1→3→1→1→1 的总和最小。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>grid = [[1,2,3],[4,5,6]]
 * <strong>输出：</strong>12
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == grid.length</code></li>
 * <li><code>n == grid[i].length</code></li>
 * <li><code>1 <= m, n <= 200</code></li>
 * <li><code>0 <= grid[i][j] <= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>矩阵</li></div></div><br><div><li>👍 1314</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 64
 * 最小路径和
 *
 * @author wangweizhou
 * @date 2022-08-05 16:57:33
 */

public class MinimumPathSum {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new MinimumPathSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 用函数f（i，j）表示从格子的左上角坐标为（0，0）的位置（用grid[0][0]表示）出发到达坐标为（i，j）的位置（用grid[i][j]表示）的路径的数字之和的最小值。
        // 如果格子的大小为m×n，那么f（m-1，n-1）就是问题的解。
        // 当i等于0时，机器人位于格子最上面的一行，机器人不可能从某个位置向下走一步到达一个行号i等于0的位置。
        // 此时只有一条从左向右的路径，因此f（0，j）等于，即最上面一行从grid[0][0]开始到grid[0][j]为止所有格子的值之和。
        // 当j等于0时，机器人位于格子最左边的一列，机器人不可能从某个位置向右走一步到达一个列号j等于0的位置。
        // 此时只有一条从上到下的路径，因此f（i，0）等于，即最左边一列从grid[0][0]开始到grid[i][0]为止所有格子的值之和。
        // 当行号i、列号j都大于0时，机器人有两种方法可以到达坐标为（i，j）的位置。
        // 它既可以从坐标为（i-1，j）的位置向下走一步，也可以从坐标为（i，j-1）的位置向右走一步，因此，f（i，j）等于f（i-1，j）与f（i，j-1）的最小值加上grid[i][j]。


        //// 解法1：动态规划
        //// 状态定义：dp[i][j]表示从[0,0]到[i,j]位置的路径上数字和最小
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {// 判空
                return 0;
            }
            int rowLen = grid.length;// 矩阵行数
            int colLen = grid[0].length;// 矩阵列数

            //状态定义：dp[i][j]表示从[0,0]到[i,j]位置为终点的路径中途径的元素和数组，其实就是类似前缀和数组。
            int[][] dp = new int[rowLen][colLen];//可以构造一个与矩阵同样大小的二维辅助数组
            // 初始状态：
            dp[0][0] = grid[0][0];
            for (int i = 1; i < rowLen; i++) {//处理第⼀列
                // 当j等于0时，机器人位于格子最左边的一列，机器人不可能从某个位置向右走一步到达一个列号j等于0的位置。
                // 此时只有一条从上到下的路径，因此f（i，0）等于 ，即最左边一列从grid[0][0]开始到grid[i][0]为止所有格子的值之和。
                dp[i][0] = grid[i][0] + dp[i - 1][0];
            }
            for (int j = 1; j < colLen; j++) {//处理第⼀⾏
                // 当i等于0时，机器人位于格子最上面的一行，机器人不可能从某个位置向下走一步到达一个行号i等于0的位置。
                // 此时只有一条从左向右的路径，因此f（0，j）等于，即最上面一行从grid[0][0]开始到grid[0][j]为止所有格子的值之和。
                dp[0][j] = grid[0][j] + dp[0][j - 1];
            }
            // 动态转移方程：
            for (int i = 1; i < rowLen; i++) { //其他按照公式来
                for (int j = 1; j < colLen; j++) {
                    // 当行号i、列号j都大于0时，机器人有两种方法可以到达坐标为（i，j）的位置。
                    // 它既可以从坐标为（i-1，j）的位置向下走一步，也可以从坐标为（i，j-1）的位置向右走一步，因此，f（i，j）等于f（i-1，j）与f（i，j-1）的最小值加上grid[i][j]。
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            return dp[rowLen - 1][colLen - 1];
        }





        // 解法1：动态规划+动态数组
        // 状态定义：dp[i][j]表示从[0,0]到[i,j]位置的路径上数字和最小
        // 由于计算f（i，j）时只需要用到它上面一行的f（i-1，j），因此实际上只需要保留两行就可以。也就是说，创建一个只有两行的数组dp，将f（i，j）保存到“dp[i%2][j]”中即可。

        //public int minPathSum(int[][] grid) {
        //    if (grid == null || grid.length == 0) {//判空
        //        return 0;
        //    }
        //    int rowLen = grid.length;//矩阵行数
        //    int colLen = grid[0].length;//矩阵列数
        //
        //    //状态定义：dp[i][j]表示从[0,0]到[i,j]位置为终点的最短路径⻓度
        //    int[][] dp = new int[2][colLen];//可以构造一个与矩阵同样大小的二维辅助数组
        //    dp[0][0] = grid[0][0];
        //    for (int j = 1; j < colLen; j++) {//处理第⼀⾏
        //        dp[0][j] = grid[0][j] + dp[0][j - 1];
        //    }
        //    // 动态转移方程：
        //    for (int i = 1; i < rowLen; i++) { //其他按照公式来
        //        dp[i%2][0] = grid[i][0] + dp[(i - 1)%2][0];//处理第⼀列
        //        for (int j = 1; j < colLen; j++) {
        //            dp[i%2][j] = grid[i][j] + Math.min(dp[(i - 1)%2][j], dp[i%2][j - 1]);
        //        }
        //    }
        //    return dp[(rowLen - 1)%2][colLen - 1];
        //}





        // 解法3：动态规划+动态数组   用一行的动态数组来滚动，同一行的某一个位置在计算前后保存两行的数据
        // 可以进一步优化空间效率，即只需要一个一维数组dp。在计算f（i，j）时需要f（i-1，j）的值。
        // 值得注意的是，f（i-1，j）在完成f（i，j）的计算之后再也用不到了，因此将f（i-1，j）和f（i，j）保存到同一个数组dp的同一个位置“dp[j]”中。
        // 在计算f（i，j）之前，“dp[j]”保存的是f（i-1，j）的值，用f（i-1，j）的值计算出f（i，j）的值之后，将f（i，j）的值保存到“dp[j]”中。
        // 虽然之前保存在“dp[j]”中的f（i-1，j）的值被覆盖了，但这个值也不再需要，它被覆盖不会带来任何问题。
        // 优化空间效率之后的代码用一维数组dp保存f（i，j）的值。
        // 在二重循环的“dp[j]=grid[i][j]+Math.min（dp[j]，dp[j-1]）”中根据状态转移方程计算f（i，j）。
        // 赋值运算符右边的“dp[j]”保存的是f（i-1，j）的值，“dp[j-1]”中保存的是f（i，j-1）的值。
        // 在计算f（i，j）之前已经完成了f（i，j-1）的计算，并且将f（i，j-1）的值保存到“dp[j-1]”中。
        // 用f（i-1，j）和f（i，j-1）的最小值加上“grid[i][j]”就可以得到f（i，j）的值，再将f（i，j）的值保存到“dp[j]”中。


        //public int minPathSum(int[][] grid) {
        //    if (grid == null || grid.length == 0 || grid[0].length == 0) {// 判空
        //        return 0;
        //    }
        //    int rowLen = grid.length;//矩阵行数
        //    int colLen = grid[0].length;//矩阵列数
        //
        //    //状态定义：dp[i][j]表示从[0,0]到[i,j]位置为终点的最短路径⻓度
        //    int[] dp = new int[colLen];// 注意这里压缩空间，创建一个长度是参数列数的dp数组
        //    // 一维数组初始化只需要处理第一行
        //    dp[0]= grid[0][0];
        //    for (int j = 1; j < colLen; j++) {//处理第⼀⾏
        //        dp[j] = grid[0][j] + dp[j - 1];
        //    }
        //    // 动态转移方程：
        //    for (int i = 1; i < rowLen; i++) { //其他按照公式来
        //        // 每次在更新行时，就处理每一行的第一个元素
        //        dp[0] = grid[i][0] + dp[0];//处理第⼀列，也就是每一行的第一个元素
        //        for (int j = 1; j < colLen; j++) {
        //            dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
        //        }
        //    }
        //    return dp[colLen - 1];
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
