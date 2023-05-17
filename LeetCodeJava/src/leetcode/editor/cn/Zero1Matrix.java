/**
 * <p>给定一个由 <code>0</code> 和 <code>1</code> 组成的矩阵 <code>mat</code>&nbsp;，请输出一个大小相同的矩阵，其中每一个格子是 <code>mat</code>
 * 中对应位置元素到最近的 <code>0</code> 的距离。</p>
 *
 * <p>两个相邻元素间的距离为 <code>1</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><b>示例 1：</b></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626667201-NCWmuP-image.png" style="width: 150px; " /></p>
 *
 * <pre>
 * <strong>输入：</strong>mat =<strong> </strong>[[0,0,0],[0,1,0],[0,0,0]]
 * <strong>输出：</strong>[[0,0,0],[0,1,0],[0,0,0]]
 * </pre>
 *
 * <p><b>示例 2：</b></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626667205-xFxIeK-image.png" style="width: 150px; " /></p>
 *
 * <pre>
 * <b>输入：</b>mat =<b> </b>[[0,0,0],[0,1,0],[1,1,1]]
 * <strong>输出：</strong>[[0,0,0],[0,1,0],[1,2,1]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == mat.length</code></li>
 * <li><code>n == mat[i].length</code></li>
 * <li><code>1 &lt;= m, n &lt;= 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= m * n &lt;= 10<sup>4</sup></code></li>
 * <li><code>mat[i][j] is either 0 or 1.</code></li>
 * <li><code>mat</code> 中至少有一个 <code>0&nbsp;</code></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>动态规划</li><li>矩阵</li></div></div><br><div><li>👍
 * 787</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 542
 * 01 矩阵
 *
 * @author wangweizhou
 * @date 2022-12-12 15:40:25
 */

public class Zero1Matrix {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new Zero1Matrix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 矩阵中的每个格子可以看成图中的一个节点，矩阵中上、下、左、右相邻的格子对应的节点之间有一条边相连。
        // 这个题目要求计算每个格子离最近的0的距离。根据题目的要求，上、下、左、右相邻的两个格子的距离为1。
        // 可以将图看成一个无权图，图中两个节点的距离是连通它们的路径经过的边的数目。
        // 由于这个问题与无权图的最近距离相关，因此可以考虑应用广度优先搜索解决。
        // 广度优先搜索需要一个队列。图中的哪些节点可以当作初始节点添加到队列中？
        // 这个问题是求每个格子离最近的0的距离，因此可以将所有的0当作初始节点添加到队列中，然后以值为0的节点作为起点做广度优先搜索。
        // 如果经过d步到达某个格子，那么该格子离最近的0的距离就是d。
        // 反向思维：从某个格子到最近的0的距离转换为从最近的0到某个格子。


        // 代码创建了一个大小与输入矩阵matrix相同的二维数组dists，用来记录每个格子离最近的0的距离。
        // 如果matrix[i][j]为0，那么这个格子离最近的0的距离自然是0，因此dists[i][j]设为0。
        // 如果matrix[i][j]的值为1，则先用最大的整数值初始化dists[i][j]，接下来搜索到对应的节点时再更新它的值。

        // 队列中的元素是矩阵中格子的坐标，是一个长度为2的数组。
        // 一个格子的坐标被添加到队列中之前，它离最近的0的距离已经计算好并且保存在数组dists中。
        // 每次从队列中取出一个坐标为pos的格子，该格子离最近的0的距离用变量dist表示。从该格子出发沿着上、下、左、右到达坐标为（r，c）的格子。
        // 如果该格子之前没有到达过，此时“dists[r][c]”的值仍然为最大的整数值，那么“dists[r][c]>dist+1”的值为true。
        // 由于是从离最近的0的距离为dist的格子多走一步到达该格子的，因此该格子离最近的0的距离是dist+1。
        // 此外，还需要将该格子添加到队列中，以便接下来搜索与该格子相连的其他节点。
        // 这是因为用的是广度优先搜索，而广度优先搜索能够保证从起始节点到达任意节点一定是沿着最短路径的。
        // 当第1次到达坐标为（r，c）的格子时记录到“dists[r][c]”的值一定是从值为0的格子到该格子的最短距离。
        // 因此，当再次到达坐标为（r，c）的格子时，“dists[r][c]>dist+1”的值为false。通过比较距离可以避免重复访问某个格子。
        // 如果之前已经到达过坐标为（r，c）的格子，那么dists[r][c]的值一定不可能大于dist+1。


        // 注意题目意思：如果matrix[i][j]为0，那么这个格子离最近的0的距离自然是0，因此dists[i][j]设为0。
        // 这个问题是求每个格子离最近的0的距离，因此可以将所有的0当作初始节点添加到队列中，然后以值为0的节点作为起点做广度优先搜索。
        // 以每一个节点值为0的节点作为其实节点开始遍历。


        public int[][] updateMatrix(int[][] mat) {
            if (mat == null || mat.length == 0 || mat[0].length == 0) {// 判空
                return mat;
            }
            int rowLen = mat.length;
            int colLen = mat[0].length;
            int[][] distance = new int[rowLen][colLen];// 创建了一个大小与输入矩阵matrix相同的二维数组dists，用来记录每个格子离最近的0的距离。
            Queue<int[]> queue = new LinkedList<>();// 队列中的元素是矩阵中格子的坐标，是一个长度为2的数组。
            // 这个问题是求每个格子离最近的0的距离，因此可以将所有的0当作初始节点添加到队列中，然后以值为0的节点作为起点做广度优先搜索。
            // 一个格子的坐标被添加到队列中之前，它离最近的0的距离已经计算好并且保存在数组dists中。
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < colLen; j++) {
                    // 遍历矩阵mat的每一个元素，将所有0元素加入队列
                    if (mat[i][j] == 0) {// 如果matrix[i][j]为0，那么这个格子离最近的0的距离自然是0，因此dists[i][j]设为0。
                        queue.add(new int[]{i, j});
                        distance[i][j] = 0;// 节点值为0的节点距离最近的0的距离为0。
                    } else { // 如果matrix[i][j]的值为1，则先用最大的整数值初始化dists[i][j]，接下来搜索到对应的节点时再更新它的值。
                        // 矩阵中的非0元素位置标记为无穷大
                        distance[i][j] = Integer.MAX_VALUE;
                    }
                }
            }


            // 以值为0的节点作为起点做广度优先搜索。
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            while (!queue.isEmpty()) {
                int[] pos = queue.remove();
                // 每次从队列中取出一个坐标为pos的格子，该格子离最近的0的距离用变量dist表示。
                int dist = distance[pos[0]][pos[1]];// 坐标为[pos[0],pos[1]]的节点距离最近的0的距离
                // 从坐标为pos的格子出发沿着上、下、左、右到达坐标为（r，c）的格子。
                for (int[] dir : dirs) {
                    int row = pos[0] + dir[0];
                    int col = pos[1] + dir[1];
                    if (row >= 0 && row < rowLen && col >= 0 && col < colLen) { // 坐标没有越界
                        // 如果该格子之前没有到达过，此时“dists[r][c]”的值仍然为最大的整数值，那么“dists[r][c]>dist+1”的值为true。
                        // 由于是从离最近的0的距离为dist的格子多走一步到达该格子的，因此该格子离最近的0的距离是dist+1。
                        // 此外，还需要将该格子添加到队列中，以便接下来搜索与该格子相连的其他节点。
                        // 如果之前已经到达过坐标为（r，c）的格子，那么dists[r][c]的值一定不可能大于dist+1。
                        // 这是因为用的是广度优先搜索，而广度优先搜索能够保证从起始节点到达任意节点一定是沿着最短路径的。
                        // 当第1次到达坐标为（r，c）的格子时记录到“dists[r][c]”的值一定是从值为0的格子到该格子的最短距离。
                        // 因此，当再次到达坐标为（r，c）的格子时，“dists[r][c]>dist+1”的值为false。通过比较距离可以避免重复访问某个格子。
                        // 坐标为[pos[0],pos[1]]的节点距离最近的0的距离
                        // 比较从最近的0到位置为[row,col]的距离和从位置pos到[row,col]的距离，【相邻的节点距离为1】

                        // 新的位置是当前位置的相邻的节点，从当前节点到当前节点的相邻节点只要多走一步
                        if (distance[row][col] > dist + 1) {
                            // 此外，还需要将该格子添加到队列中，以便接下来搜索与该格子相连的其他节点。
                            queue.add(new int[]{row, col});
                            distance[row][col] = dist + 1;
                        }
                    }
                }
            }
            return distance;
        }



        //// 方法二：动态规划  看的不太明白
        //// 对于矩阵中的任意一个 1 以及一个 0，我们如何从这个 1 到达 0 并且距离最短呢？
        //// 根据上面的做法，我们可以从 1 开始，先在水平方向移动，直到与 0 在同一列，随后再在竖直方向上移动，直到到达 0 的位置。
        //// 这样一来，从一个固定的 1 走到任意一个 0，在距离最短的前提下可能有四种方法：
        //
        //// 只有 水平向左移动 和 竖直向上移动；
        //// 只有 水平向左移动 和 竖直向下移动；
        //// 只有 水平向右移动 和 竖直向上移动；
        //// 只有 水平向右移动 和 竖直向下移动。
        //
        //public int[][] updateMatrix(int[][] matrix) {
        //    if (matrix == null || matrix.length == 0) {
        //        return matrix;
        //    }
        //    int rowLen = matrix.length, colLen = matrix[0].length;
        //    // 初始化动态规划的数组，所有的距离值都设置为一个很大的数
        //    int[][] dist = new int[rowLen][colLen];
        //    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //    for (int i = 0; i < rowLen; ++i) {
        //        Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
        //    }
        //    // 如果 (i, j) 的元素为 0，那么距离为 0
        //    for (int i = 0; i < rowLen; ++i) {
        //        for (int j = 0; j < colLen; ++j) {
        //            if (matrix[i][j] == 0) {
        //                dist[i][j] = 0;
        //            }
        //        }
        //    }
        //
        //    // 只有 水平向左移动 和 竖直向上移动，注意动态规划的计算顺序
        //    for (int i = 0; i < rowLen; ++i) {
        //        for (int j = 0; j < colLen; ++j) {
        //            if (i - 1 >= 0) {// 竖直向上移动
        //                dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
        //            }
        //            if (j - 1 >= 0) {// 水平向左移动
        //                dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
        //            }
        //        }
        //    }
        //    // 只有 水平向左移动 和 竖直向下移动，注意动态规划的计算顺序
        //    for (int i = rowLen - 1; i >= 0; --i) {
        //        for (int j = 0; j < colLen; ++j) {
        //            if (i + 1 < rowLen) {
        //                dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
        //            }
        //            if (j - 1 >= 0) {
        //                dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
        //            }
        //        }
        //    }
        //    // 只有 水平向右移动 和 竖直向上移动，注意动态规划的计算顺序
        //    for (int i = 0; i < rowLen; ++i) {
        //        for (int j = colLen - 1; j >= 0; --j) {
        //            if (i - 1 >= 0) {
        //                dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
        //            }
        //            if (j + 1 < colLen) {
        //                dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
        //            }
        //        }
        //    }
        //    // 只有 水平向右移动 和 竖直向下移动，注意动态规划的计算顺序
        //    for (int i = rowLen - 1; i >= 0; --i) {
        //        for (int j = colLen - 1; j >= 0; --j) {
        //            if (i + 1 < rowLen) {
        //                dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
        //            }
        //            if (j + 1 < colLen) {
        //                dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
        //            }
        //        }
        //    }
        //    return dist;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
