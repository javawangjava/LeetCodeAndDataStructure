/**
 * <p>给你一个大小为 <code>m x n</code> 的二进制矩阵 <code>grid</code> 。</p>
 *
 * <p><strong>岛屿</strong>&nbsp;是由一些相邻的&nbsp;<code>1</code>&nbsp;(代表土地) 构成的组合，这里的「相邻」要求两个 <code>1</code> 必须在
 * <strong>水平或者竖直的四个方向上 </strong>相邻。你可以假设&nbsp;<code>grid</code> 的四个边缘都被 <code>0</code>（代表水）包围着。</p>
 *
 * <p>岛屿的面积是岛上值为 <code>1</code> 的单元格的数目。</p>
 *
 * <p>计算并返回 <code>grid</code> 中最大的岛屿面积。如果没有岛屿，则返回面积为 <code>0</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/05/01/maxarea1-grid.jpg" style="width: 500px; height:
 * 310px;" />
 * <pre>
 * <strong>输入：</strong>grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * <strong>输出：</strong>6
 * <strong>解释：</strong>答案不应该是 <span><code>11</code></span> ，因为岛屿只能包含水平或垂直这四个方向上的 <span><code>1</code></span> 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>grid = [[0,0,0,0,0,0,0,0]]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == grid.length</code></li>
 * <li><code>n == grid[i].length</code></li>
 * <li><code>1 &lt;= m, n &lt;= 50</code></li>
 * <li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>数组</li><li>矩阵</li></div></div><br
 * ><div><li>👍 893</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 695
 * 岛屿的最大面积
 *
 * @author wangweizhou
 * @date 2022-12-11 19:54:45
 */

public class MaxAreaOfIsland {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new MaxAreaOfIsland().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 其实图是节点和边的集合，因此需要找出图的节点和边。这个题目关注的是地图中的岛屿，也就是矩阵中的1。
        //// 矩阵中的每个值为1的格子都是图中的一个节点。矩阵中的一个格子可能与位于它上、下、左、右的4个格子相邻，两个相邻的值为1的格子之间有一条边相连。
        //// 一个图可能包含若干互不连通的子图，但子图内的所有节点相互连通。
        //// 将岛屿转换成图之后，岛屿的面积就变成子图中节点的数目。如果能计算出每个连通子图中节点的数目，就能知道最大的岛屿的面积。
        //// 可以逐一扫描矩阵中的每个格子，如果遇到一个值为1的格子并且它不在之前已知的岛屿上，那么就到达了一个新的岛屿，于是搜索这个岛屿并计算它的面积。
        //// 在比较所有岛屿的面积之后就可以知道最大的岛屿的面积。
        //
        //
        //// 创建了一个和输入矩阵相同大小的矩阵visited，它的作用是用一个布尔值标识矩阵中的每个值为1的格子是否已经到达过，用来确保每个格子只搜索一次。
        //// 接下来从一个值为1且没有访问过的格子出发找出它所在岛屿的面积，即对应的节点所在连通子图的节点数目。
        //// 由于需要搜索整个连通子图可以得到节点的数目，因此这就是一个典型的图的搜索问题。两种不同的图搜索算法分别为广度优先搜索和深度优先搜索。

        public int maxAreaOfIsland(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int rowLen = grid.length;// 行数
            int colLen = grid[0].length;// 列数
            // 创建了一个和输入矩阵相同大小的矩阵visited，它的作用是用一个布尔值标识矩阵中的每个值为1的格子是否已经到达过，用来确保每个格子只搜索一次。
            boolean[][] isVisited = new boolean[rowLen][colLen];
            int maxArea = 0;

            // 遍历图，从一个值为1且没有访问过的格子出发找出它所在岛屿的面积，即对应的节点所在连通子图的节点数目。
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < colLen; j++) {
                    // 从一个值为1且没有访问过的格子出发找出它所在岛屿的面积，即对应的节点所在连通子图的节点数目。
                    // 在比较所有岛屿的面积之后就可以知道最大的岛屿的面积。
                    if (grid[i][j] == 1 && !isVisited[i][j]) {
                        int area = getArea(grid, isVisited, i, j);
                        maxArea = Math.max(maxArea, area);
                    }
                }
            }
            return maxArea;
        }



        //// 广度优先搜索通常需要一个队列。队列中保存节点值为1且没有访问过的节点
        //// 先将起始节点添加到队列中。接下来每步从队列中取出一个节点进行访问。对于这个题目而言，每访问一个节点，岛屿的面积增加1。
        //// 接下来从上、下、左、右这4个方向判断相邻的节点是不是还有没有到达过的值为1的节点，如果有，就将其添加到队列中。
        //// 重复这个过程，直到队列的长度为0，此时初始节点所在的子图搜索完毕。
        //
        //// 代码中队列的元素为矩阵中的坐标，每个坐标都包含行号和列号这两个值，用一个长度为2的数组表示。
        //// 二维数组dirs表示在矩阵中向上、下、左、右这4个方向前进一步时坐标的变化。在矩阵中向上移动一步时行号减1而列号不变，所以坐标的改变值为（-1，0），其他方向的改变值类似。
        //// 用当前坐标pos加上坐标的改变值就得到向不同方向前进一步之后的坐标。这样写代码的好处是容易用一个简洁的循环实现向4个不同方向前进。

        // 解法1：广度优先搜索，用队列实现     获得图的节点个数
        private int getArea(int[][] grid, boolean[][] isVisited, int i, int j) {
            Queue<int[]> queue = new LinkedList<>();
            // 代码中队列的元素为矩阵中的坐标，每个坐标都包含行号和列号这两个值，用一个长度为2的数组表示。
            queue.add(new int[]{i, j});// 将当前坐标加入到队列中，二维数组第一维是行，第二维是列。
            isVisited[i][j] = true;// 将当前节点标识为已访问
            // 二维数组dirs表示在矩阵中向上、下、左、右这4个方向前进一步时坐标的变化。在矩阵中向上移动一步时行号减1而列号不变，所以坐标的改变值为（-1，0），其他方向的改变值类似。
            // 用当前坐标pos加上坐标的改变值就得到向不同方向前进一步之后的坐标。
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int area = 0;
            while (!queue.isEmpty()) {
                int[] pos = queue.remove();// 队首元素出队，队首元素一直是某一个子图的根节点
                area++;// 每次从队列中弹出一个节点，则图的面积加1。处理当前节点
                for (int[] dir : dirs) {// 循环遍历当前位置的上下左右四个方向
                    int row = pos[0] + dir[0];
                    int col = pos[1] + dir[1];
                    // 检查新坐标没有越界，并且下一个节点值为1且没有访问过，类比二叉树访问的左右子节点
                    if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1 &&
                    !isVisited[row][col]) {
                        queue.add(new int[]{row, col});// 将当前坐标的相邻节点【新节点】加入到队列中
                        isVisited[row][col] = true;// 将当前节点相邻节点【新节点】标识为已访问
                    }
                }
                // 类比二叉树的遍历，将四个方向分开，上面循环只是将四个方向的代码合并在一起写了。二叉树的左右子节点名字不一样，不能合并在一起写
                //int row=pos[0]+dirs[0][0];
                //int col=pos[1]+dirs[0][1];
                //if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1 &&
                //!isVisited[row][col]) {
                //    queue.add(new int[]{row, col});
                //    isVisited[row][col] = true;
                //}
                //
                //
                //row=pos[0]+dirs[1][0];
                //col=pos[1]+dirs[1][1];
                //if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1 &&
                //!isVisited[row][col]) {
                //    queue.add(new int[]{row, col});
                //    isVisited[row][col] = true;
                //}
                //
                //
                //row=pos[0]+dirs[2][0];
                //col=pos[1]+dirs[2][1];
                //if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1 &&
                //!isVisited[row][col]) {
                //    queue.add(new int[]{row, col});
                //    isVisited[row][col] = true;
                //}
                //
                //row=pos[0]+dirs[3][0];
                //col=pos[1]+dirs[3][1];
                //if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1 &&
                //!isVisited[row][col]) {
                //    queue.add(new int[]{row, col});
                //    isVisited[row][col] = true;
                //}
            }
            return area;
        }






        // 解法2：深度优先搜索
        // 如果将前面代码中的队列替换成栈，由于栈按照“后进先出”的顺序进行压栈、出栈操作，因此图搜索的顺序相应地变成深度优先搜索。
        // 基于栈的深度优先搜索的代码和基于队列的广度优先搜索的代码非常类似.

        //private int getArea(int[][] grid,boolean[][] isVisited,int i,int j){
        //	Deque<int[]> stack=new LinkedList<>();
        //	stack.push(new int[]{i,j});
        //	isVisited[i][j]=true;
        //	int[][] dirs={{-1,0},{1,0},{0,-1},{0,1}};
        //	int area=0;
        //	while(!stack.isEmpty()){
        //		int[] pos=stack.remove();
        //		area++;
        //		for (int[] dir:dirs) {
        //			int row=pos[0]+dir[0];
        //			int col=pos[1]+dir[1];
        //			if(row>=0&&row<grid.length&&col>=0&&col<grid[0].length&&grid[row][col]==1&&!isVisited[row][col]){
        //				stack.add(new int[]{row,col});
        //				isVisited[row][col]=true;
        //			}
        //		}
        //	}
        //	return area;
        //}


        //// 基于递归实现深度优先搜索
        //// 从起始节点出发的岛屿的面积等于起始节点的面积（一个节点的面积为1）加上与之相邻并且没有访问过的节点能到达的岛屿的面积。
        //// 求相邻节点能到达的岛屿的面积和初始问题完全一样，可以用递归函数求得。
        //private int getArea(int[][] grid,boolean[][] isVisited,int i,int j){
        //	int area=1;
        //	isVisited[i][j]=true;
        //	int[][] dirs={{-1,0},{1,0},{0,-1},{0,1}};
        //	for (int[] dir:dirs) {
        //		int row=i+dir[0];
        //		int col=j+dir[1];
        //		if(row>=0&&row<grid.length&&col>=0&&col<grid[0].length&&grid[row][col]==1&&!isVisited[row][col]){
        //			area+=getArea(grid,isVisited,row,col);
        //		}
        //	}
        //	return area;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
