/**
 * <p>给你一个由 <code>'1'</code>（陆地）和 <code>'0'</code>（水）组成的的二维网格，请你计算网格中岛屿的数量。</p>
 *
 * <p>岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。</p>
 *
 * <p>此外，你可以假设该网格的四条边均被水包围。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == grid.length</code></li>
 * <li><code>n == grid[i].length</code></li>
 * <li><code>1 <= m, n <= 300</code></li>
 * <li><code>grid[i][j]</code> 的值为 <code>'0'</code> 或 <code>'1'</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>数组</li><li>矩阵</li></div></div><br
 * ><div><li>👍 1829</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 200
 * 岛屿数量
 *
 * @author wangweizhou
 * @date 2022-08-01 23:20:43
 */

public class NumberOfIslands {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new NumberOfIslands().new Solution();
        char[][] grid = new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        int ans = solution.numIslands(grid);
        System.out.println(ans);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 广度优先遍历+使用标记数组
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int rowLen = grid.length;
            int colLen = grid[0].length;
            int count = 0;
            boolean[][] isVisited = new boolean[rowLen][colLen];
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < colLen; j++) {
                    // 遍历矩阵的每一个位置，当该位置字符为‘1’，且没有访问过时，以该位置为起点进行广度遍历
                    if (grid[i][j] == '1' && !isVisited[i][j]) {
                        bfs(grid, isVisited, i, j);
                        count++;
                    }
                }
            }
            return count;
        }


        // 在图grid中以(i,j)为起点进行遍历。
        private void bfs(char[][] grid, boolean[][] isVisited, int i, int j) {
            Deque<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{i, j});
            isVisited[i][j] = true;
            int rowLen = grid.length;
            int colLen = grid[0].length;
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            while (queue.size() > 0) {
                int[] pos = queue.poll();
                for (int[] dir : dirs) {
                    int row = pos[0] + dir[0];
                    int col = pos[1] + dir[1];
                    if (row >= 0 && row < rowLen && col >= 0 && col < colLen && grid[row][col] == '1' && !isVisited[row][col]) {
                        queue.offer(new int[]{row, col});
                        isVisited[row][col] = true;
                    }
                }
            }
        }




        //// 方法二：广度优先搜索
        //// step1:优先判断空矩阵等情况。
        //// stp2:从上到下从左到右遍历矩阵每一个位置的元素，如果该元素值为1，统计岛屿数量。
        //// stp3:使用bfs将遍历矩阵遇到的1以及相邻的1全部置为0：利用两个队列辅助每次队列进入第一个进入的1，然后遍历队列，
        //// 依次探讨队首的四个方向，是否符合，如果符合则置为0，且位置坐标加入队列，继续遍历，直到队列为空。

        //public int numIslands(char[][] grid) {
        //    if (grid == null || grid.length == 0 || grid[0].length == 0) {// 空矩阵
        //        return 0;
        //    }
        //    int rowLen = grid.length;// 二维数组的行数
        //    int colLen = grid[0].length;// 二维数组的列数
        //    int count = 0;// 记录岛屿数
        //    for (int i = 0; i < rowLen; i++) {
        //        for (int j = 0; j < colLen; j++) {
        //            if (grid[i][j] == '1') {// 遍历矩阵，每次从数值等于1的节点开始深度遍历，
        //                count++;// 每次广度遍历一个子图的时候，子图个数+1，也就是岛屿数目加1
        //                bfs(grid, i, j);//将与这个1相邻的所有1置为0
        //            }
        //        }
        //    }
        //    return count;
        //}
        //
        //
        //// 在遍历的过程中将遍历到的‘1’置为‘0’。
        //private void bfs(char[][] grid, int row, int col) {
        //    if (grid == null || grid.length == 0 || grid[0].length == 0) {
        //        return;
        //    }
        //    int rowLen = grid.length;
        //    int colLen = grid[0].length;
        //    Queue<int[]> queue = new LinkedList<>();
        //    queue.offer(new int[]{row, col});
        //    grid[row][col] = '0';// 每次在将当前位置的元素加入队列时，将当前位置置‘0’。
        //    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //    while (!queue.isEmpty()) {
        //        int[] pos = queue.poll();
        //        for (int[] dir : dirs) {
        //            int newRow = pos[0] + dir[0];
        //            int newCol = pos[1] + dir[1];
        //            if (newRow >= 0 && newRow < rowLen && newCol >= 0 && newCol < colLen && grid[newRow][newCol] ==
        //            '1') {
        //                queue.offer(new int[]{newRow, newCol});
        //                grid[newRow][newCol] = '0';
        //            }
        //        }
        //    }
        //    return;
        //}





        //// ⽅法⼀： dfs（推荐使⽤）
        //// step1:优先判断空矩阵等情况。
        //// step2:从上到下从左到右遍历矩阵每一个位置的元素，如果该元素值为1，统计岛屿数量。
        //// step3：使用dfs将遍历矩阵遇到的1以及相邻的1全部置为0。这样后续再遍历时就不会重复遍历

        //public int numIslands(char[][] grid) {
        //    if (grid == null || grid.length == 0 || grid[0].length == 0) {// 空矩阵的情况
        //        return 0;
        //    }
        //    int rowLength = grid.length;// 二维数组的行数
        //    int colLength = grid[0].length;// 二维数组的列数
        //    int count = 0;// 记录岛屿数
        //    for (int i = 0; i < rowLength; i++) {
        //        for (int j = 0; j < colLength; j++) {
        //            if (grid[i][j] == '1') {//// 遍历矩阵，每次从数值等于1的节点开始深度遍历，
        //                count++;// 每次深度遍历一个子图的时候，子图个数+1，也就是岛屿数目加1
        //                // 使用dfs将遍历矩阵遇到的1以及相邻的1全部置为0。这样后续再遍历时就不会重复遍历
        //                dfs(grid, i, j);//将与这个1相邻的所有1置为0
        //            }
        //        }
        //    }
        //    return count;
        //}
        //
        //


        // dfs递归遍历写法1
        //private void dfs(char[][] grid, int i, int j){
        //    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //    int rowLen = grid.length;
        //    int colLen = grid[0].length;
        //    grid[i][j]='0';
        //    for(int[] dir:dirs){
        //        int row=i+dir[0];
        //        int col=j+dir[1];
        //        if (row >= 0 && row < rowLen && col >= 0 && col < colLen && grid[row][col] == '1' ) {
        //            dfs(grid,row,col);
        //        }
        //    }
        //}



        // //dfs +递归遍历写法3   这个只是将四个方向分开写的
        //private void dfs(char[][] grid, int row, int col) {//深度优先遍历与grid[row][col]相邻的所有1
        //    int rowLength = grid.length;
        //    int colLength = grid[0].length;
        //    grid[row][col] = '0';// // 处理当前节点，将当前位置置0。
        //    //后续四个⽅向遍历，数组要验证数组下标是否越界，新位置的字符等于‘1’
        //    if (row - 1 >= 0 && grid[row - 1][col] == '1') {
        //        dfs(grid, row - 1, col);
        //    }
        //    if (row + 1 < rowLength && grid[row + 1][col] == '1') {
        //        dfs(grid, row + 1, col);
        //    }
        //    if (col - 1 >= 0 && grid[row][col - 1] == '1') {
        //        dfs(grid, row, col - 1);
        //    }
        //    if (col + 1 < colLength && grid[row][col + 1] == '1') {
        //        dfs(grid, row, col + 1);
        //    }
        //}




        //// dfs递归遍历写法2
        //private void dfs(char[][] grid, int row, int col) {// 深度优先遍历与grid[row][col]相邻的所有1
        //    if (grid == null || grid.length == 0 || grid[0].length == 0) {// 空矩阵的情况
        //        return;
        //    }
        //    int rowLength = grid.length;
        //    int colLength = grid[0].length;
        //    // 结束条件：四周越界row<0||col<0||row>=rowLength||col>=colLength
        //    // grid[row][col]=='0'  遇到‘0’
        //    // 在递归结束条件，当前节点没有越界，当前节点可以访问
        //    if (row < 0 || col < 0 || row >= rowLength || col >= colLength || grid[row][col] == '0') {
        //        return;
        //    }
        //    // 当前位置置‘0’，向四周遍历
        //    grid[row][col] = '0';// 处理当前节点，将当前位置置0。
        //    // 遍历四个方向
        //    dfs(grid, row - 1, col);
        //    dfs(grid, row + 1, col);
        //    dfs(grid, row, col - 1);
        //    dfs(grid, row, col + 1);
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
