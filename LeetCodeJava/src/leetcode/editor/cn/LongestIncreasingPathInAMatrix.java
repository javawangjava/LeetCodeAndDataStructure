/**
 * <p>给定一个 <code>m x n</code> 整数矩阵 <code>matrix</code> ，找出其中 <strong>最长递增路径</strong> 的长度。</p>
 *
 * <p>对于每个单元格，你可以往上，下，左，右四个方向移动。 你 <strong>不能</strong> 在 <strong>对角线</strong> 方向上移动或移动到
 * <strong>边界外</strong>（即不允许环绕）。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/01/05/grid1.jpg" style="width: 242px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>最长递增路径为 <code>[1, 2, 6, 9]</code>。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/01/27/tmp-grid.jpg" style="width: 253px; height: 253px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>最长递增路径是 <code>[3, 4, 5, 6]</code>。注意不允许在对角线方向上移动。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>matrix = [[1]]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == matrix.length</code></li>
 * <li><code>n == matrix[i].length</code></li>
 * <li><code>1 <= m, n <= 200</code></li>
 * <li><code>0 <= matrix[i][j] <= 2<sup>31</sup> - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>图</li><li>拓扑排序</li><li>记忆化搜索</li><li>数组</li
 * ><li>动态规划</li><li>矩阵</li></div></div><br><div><li>👍 680</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 329
 * 矩阵中的最长递增路径
 *
 * @author wangweizhou
 * @date 2022-08-03 04:28:55
 */

public class LongestIncreasingPathInAMatrix {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new LongestIncreasingPathInAMatrix().new Solution();
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        int ans = solution.longestIncreasingPath(matrix);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {



        // 由于这个问题是关于递增路径的，因此只关心从较小的数字指向较大的数字的边，两个不同数字在图中对应的节点之间的边是有向边，针对这个问题构建出来的图是一个有向图。
        // 同时，由于图中所有边都是从较小的数字指向较大的数字，这样的边不可能形成环，因此构建出来的图一定是有向无环图。
        // 由于需要搜索图中的所有节点才能确定最长递增路径的长度，因此这也是一个关于图搜索的问题。解决图搜索通常用广度优先搜索和深度优先搜索这两种不同的方法。

        // 这个问题中的路径是非常关键的信息，而深度优先搜索能够很方便地记录搜索的路径，因此深度优先搜索更适合这个问题。
        // 因为不知道从哪个节点开始的递增路径是最长的，所以试着找出从矩阵的每个数字出发的最长递增路径的长度，通过比较可以得出整个矩阵中的最长递增路径的长度。
        // 注意这个找最长路径，那么同一个节点可能是不同路径中的节点，则在不同的图中同一个节点可以重复访问，那么这里也就不能标记为已经访问过了。


        // 创建了一个与输入矩阵matrix大小相同的矩阵lengths，“lengths[i][j]”保存的是从矩阵中坐标为（i，j）的数字出发的最长递增路径的长度，然后通过比较得出矩阵中最长的递增路径的长度longest。
        // 假设要计算从坐标（r1，c1）的数字开始的最长递增路径的长度，则可以在它上、下、左、右这4个方向尝试找到比它更大的相邻的数字。
        // 如果某个坐标为（r2，c2）的相邻的数字比坐标为（r1，c1）的数字大，那么从坐标（r1，c1）前往坐标（r2，c2）就是一条递增的路径，
        // 并且此时从坐标（r1，c1）开始的路径的长度比从坐标（r2，c2）开始的路径长1。
        // 与坐标（r1，c1）相邻并且数字更大的可能不止一个（r2，c2）。
        // 如果以与坐标（r1，c1）相邻并且更大的数字为起点的所有递增路径的最长长度为d，那么以坐标（r1，c1）为起点的最长递增路径的长度为d+1。
        // 求以某个与坐标（r1，c1）相邻的数字为起点的最长递增路径的长度与求以坐标（r1，c1）的数字为起点的最长递增路径的长度是同一个问题，可以调用递归函数求得。

        // 矩阵lengths的所有值都初始化为0（因为在Java中0是整数类型的默认值，所以省略了将矩阵lengths中数字初始化为0的代码）。
        // 以矩阵中某个坐标为起点的最长递增路径的长度至少是1。如果“lengths[i][j]”的值大于0，就说明之前已经计算过以坐标（i，j）为起点的最长递增路径的长度，

        // 如果在计算以其他坐标为起点的最长递增路径的长度时需要以坐标（i，j）为起点的最长递增路径的长度，就没有必要再次计算，只需要直接返回就可以。【记忆化缓存】
        // 矩阵lengths在这里还起到了缓存的作用，能够确保以任意坐标为起点的最长递增路径的长度只需要计算一次。
        // 由于总是沿着数字递增的方向（“matrix[r][c]>matrix[i][j]”为true时）在矩阵对应的图中搜索，
        // 这相当于是在一个有向无环图中搜索，因此不会出现重复访问一个节点的情况，也无须判断一个节点之前是否访问过。


        //// 解法1：记忆化深度优先搜索
        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {// 判空
                return 0;
            }
            int rowLen = matrix.length;// 二维数组行数
            int colLen = matrix[0].length;// 二维数组列数
            int maxLength = 0;// 矩阵的最长递增路径
            // 创建了一个与输入矩阵matrix大小相同的矩阵lengths，“lengths[i][j]”保存的是从矩阵中坐标为（i，j）的数字出发的最长递增路径的长度，
            // 矩阵lengths的所有值都初始化为0（因为在Java中0是整数类型的默认值，所以省略了将矩阵lengths中数字初始化为0的代码）。
            int[][] lengths = new int[rowLen][colLen];
            // 因为不知道从哪个节点开始的递增路径是最长的，所以试着找出从矩阵的每个数字出发的最长递增路径的长度，
            // 通过比较可以得出整个矩阵中的最长递增路径的长度。
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < colLen; j++) {
                    int length = getPath(matrix, lengths, i, j);// 获得从矩阵位置（i,j）开始的最长递增路径路径长度
                    maxLength = Math.max(maxLength, length);//然后通过比较得出矩阵中最长的递增路径的长度longest。
                }
            }
            return maxLength;
        }



        // 注释简单  这个是直接使用lengths[row][col]。  写法1
        private int getPath(int[][] matrix, int[][] lengths, int row, int col) {
            int rowLen = matrix.length;
            int colLen = matrix[0].length;
            // 如果在计算以其他坐标为起点的最长递增路径的长度时需要以坐标（i，j）为起点的最长递增路径的长度，就没有必要再次计算，只需要直接返回就可以。【记忆化缓存】
            if (lengths[row][col] != 0) {// 剪枝
                return lengths[row][col];
            }
            // 处理当前节点，以当前节点为起点的路径长度的最小值为1【也就是只有当前节点】
            int len = 1;// 其实这是一个临时变量，这里使用直接使用lengths[row][col]也是没问题的。
            // 遍历当前节点的四个反向
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : dirs) {
                int newR = dir[0] + row;
                int newC = dir[1] + col;
                if (newR >= 0 && newR < rowLen && newC >= 0 && newC < colLen) {// 没越界
                    // 沿着增长的路径深入，路径长度加1，更新以[row][col]为起点的最大长度。
                    if (matrix[newR][newC] > matrix[row][col]) {// 路径沿着数值增大的方向前进
                        int path = getPath(matrix, lengths, newR, newC);// 获取从下一个节点出发的最大路径长度
                        len = Math.max(len, path + 1);// 更新从当前节点(row,col)出发的最大长度。// 深度加1，父节点的深度是子节点深度加1。
                    }
                }
            }
            lengths[row][col] = len;// 更新从记忆数组lengths的从（row,col）出发的长度lengths[row][col]。
            return len;
        }





        //// 注释简单  这个是直接使用lengths[row][col]。  写法2
        //private int getPath(int[][] matrix, int[][] lengths, int row, int col) {
        //    int rowLen = matrix.length;
        //    int colLen = matrix[0].length;
        //    if (lengths[row][col] != 0) {// 剪枝
        //        return lengths[row][col];
        //    }
        //    // 处理当前节点，以当前节点为起点的路径长度的最小值为1【也就是只有当前节点】
        //    lengths[row][col] = 1;// 其实这是一个临时变量，这里使用直接使用lengths[row][col]也是没问题的。
        //    // 遍历当前节点的四个反向
        //    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //    for (int[] dir : dirs) {
        //        int newR = dir[0] + row;
        //        int newC = dir[1] + col;
        //        if (newR >= 0 && newR < rowLen && newC >= 0 && newC < colLen) {// 没越界
        //            // 沿着增长的路径深入，路径长度加1，更新以[row][col]为起点的最大长度。
        //            if (matrix[newR][newC] > matrix[row][col]) {// 路径沿着数值增大的方向前进
        //                int path = getPath(matrix, lengths, newR, newC);// 获取从下一个节点出发的最大路径长度
        //                lengths[row][col] = Math.max(lengths[row][col], path + 1);// 更新从当前节点(row,col)出发的最大长度
        //            }
        //        }
        //    }
        //    //lengths[row][col] = len;// 更新从记忆数组lengths的从（row,col）出发的长度lengths[row][col]。
        //    return lengths[row][col];
        //}


        //// 计算从坐标（row，col）的数字开始的最长递增路径的长度，则可以在它上、下、左、右这4个方向尝试找到比它更大的相邻的数字。
        //// 递归的深度遍历
        //private int getPath(int[][] matrix, int[][] lengths, int row, int col) {
        //    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {//
        //        return 0;
        //    }
        //    int rowLen = matrix.length;//二维数组行数
        //    int colLen = matrix[0].length;//二维数组列数
        //    // 以矩阵中某个坐标为起点的最长递增路径的长度至少是1。
        //    // 矩阵lengths在这里还起到了缓存的作用，能够确保以任意坐标为起点的最长递增路径的长度只需要计算一次。
        //    // 如果“lengths[i][j]”的值大于0，就说明之前已经计算过以坐标（i，j）为起点的最长递增路径的长度。
        //    // 如果在计算以其他坐标为起点的最长递增路径的长度时需要以坐标（i，j）为起点的最长递增路径的长度，就没有必要再次计算，只需要直接返回就可以。
        //    if (lengths[row][col] != 0) {// 记忆化剪枝
        //        return lengths[row][col];
        //    }
        //    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};// 四个方向
        //    // 处理当前节点
        //    // 以矩阵中某个坐标为起点的最长递增路径的长度至少是1。
        //    int length = 1;// 只包含初始位置时，最长递增路径长度为1。
        //    // 当前位置的相邻位置有
        //    for (int[] dir : dirs) {// 循环遍历当前位置的上下左右4个位置，四个方向是合并写在一起的
        //        int newr = row + dir[0];// 新的一行
        //        int newc = col + dir[1];// 新的一列
        //        if (newr >= 0 && newr < rowLen && newc >= 0 && newc < colLen) {// 因为要访问数组，所以新位置要判断是否越界，
        //            // 如果某个坐标为（r2，c2）的相邻的数字比坐标为（r1，c1）的数字大，那么从坐标（r1，c1）前往坐标（r2，c2）就是一条递增的路径，
        //            if (matrix[newr][newc] > matrix[row][col]) {// 当相邻位置的值大于当前位置的值，则表明是递增路径
        //                // 并且此时从坐标（r1，c1）开始的路径的长度比从坐标（r2，c2）开始的路径长1。
        //                // 与坐标（r1，c1）相邻并且数字更大的可能不止一个（r2，c2）。
        //                // 如果以与坐标（r1，c1）相邻并且更大的数字为起点的所有递增路径的最长长度为d，那么以坐标（r1，c1）为起点的最长递增路径的长度为d+1。
        //                int path = getPath(matrix, lengths, newr, newc);// 获取以相邻位置为起点的递增路径长度
        //                length = Math.max(path + 1, length);// 找出以当前位置为起点的最长的递增路径
        //            }
        //        }
        //    }
        //    lengths[row][col] = length;// 记录以当前位置（row,col）为起点的最长递增路径
        //    return length;
        //}
        //




        //// 解法2：和解法1细节有点不一样。记忆化深度优先搜索
        //private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//记录四个方向
        //
        //public int longestIncreasingPath(int[][] matrix) {
        //    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {//判空
        //        return 0;
        //    }
        //    int rowLength = matrix.length;//二维数组行数
        //    int colLength = matrix[0].length;//二维数组列数
        //    int maxLength = 0;
        //    int[][] dp = new int[rowLength][colLength];//dp[i][j]用于记录原数组[i,j]单元格拥有的最长递增路径
        //    for (int i = 0; i < rowLength; i++) {
        //        for (int j = 0; j < colLength; j++) {
        //            maxLength = Math.max(maxLength, dfs(matrix,dp,i,j));
        //        }
        //    }
        //    return maxLength;
        //}
        //
        //
        //
        ////深度优先搜索，返回最⼤单元格数
        //private int dfs(int[][] matrix, int[][] dp, int row, int col) {
        //    int rowLength = matrix.length;//二维矩阵行数
        //    int colLength = matrix[0].length;//二维矩阵列数
        //    if (dp[row][col] != 0) {
        //        return dp[row][col];
        //    }
        //    dp[row][col]++;
        //    for (int i = 0; i < 4; i++) {
        //        int nextrow = row + dirs[i][0];
        //        int nextcol = col + dirs[i][1];
        //        //判断条件
        //        //nextrow和nextcol在
        //        if (nextrow >= 0 && nextrow < rowLength && nextcol >= 0 && nextcol < colLength &&
        //        matrix[nextrow][nextcol] > matrix[row][col]) {
        //            dp[row][col] = Math.max(dp[row][col], dfs(matrix, dp, nextrow, nextcol)+ 1) ;
        //        }
        //    }
        //    return dp[row][col];
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
