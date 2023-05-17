/**
 * <p>在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于
 * 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre><strong>输入:</strong>
 * <code>[
 * &nbsp; [1,3,1],
 * &nbsp; [1,5,1],
 * &nbsp; [4,2,1]
 * ]</code>
 * <strong>输出:</strong> <code>12
 * </code><strong>解释:</strong> 路径 1&rarr;3&rarr;5&rarr;2&rarr;1 可以拿到最多价值的礼物</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p>提示：</p>
 *
 * <ul>
 * <li><code>0 &lt; grid.length &lt;= 200</code></li>
 * <li><code>0 &lt; grid[0].length &lt;= 200</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>矩阵</li></div></div><br><div><li>👍 328</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 47
 * 礼物的最大价值
 * @author wangweizhou
 * @date 2022-09-15 14:52:51
 */
public class LiWuDeZuiDaJieZhiLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LiWuDeZuiDaJieZhiLcof().new Solution();
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int ans = solution.maxValue(grid);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 动态规划：
        // 状态定义：状态转移二维数组为 dp，dp[i][j] 表示从 grid[0][0] 到 grid[i][j] 得到礼物的最大价值
        // 状态转移方程：
        //当 i=0 && j=0 时，dp[0][0] = grid[0][0]
        //当 i=0 && j!=0 时，dp[i][j] = grid[i][j] + dp[i][j-1]
        //当 i!=0 && j=0 时，dp[i][j] = grid[i][j] + dp[i-1][j]
        //当 i!=0 && j!=0 时，dp[i][j] = grid[i][j] + max(dp[i-1][j], dp[i][j-1])

        public int maxValue(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int rowLen = grid.length;
            int colLen = grid[0].length;
            int[][] dp = new int[rowLen][colLen];
            // 初始状态
            dp[0][0]=grid[0][0];
            for (int i = 1; i < rowLen; i++) {// 第一列
                dp[i][0] = dp[i-1][0]+grid[i][0];
            }

            for (int j = 1; j < colLen; j++) {// 第一行
                dp[0][j] = dp[0][j-1]+grid[0][j];
            }

            for (int i = 1; i < rowLen; i++) {// 中间
                for (int j = 1; j < colLen; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
            return dp[rowLen - 1][colLen - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
