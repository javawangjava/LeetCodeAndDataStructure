/**
 * <p>给你一个 <code>m</code> 行 <code>n</code> 列的矩阵 <code>matrix</code> ，请按照 <strong>顺时针螺旋顺序</strong> ，返回矩阵中的所有元素。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral1.jpg" style="width: 242px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * <strong>输出：</strong>[1,2,3,6,9,8,7,4,5]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral.jpg" style="width: 322px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * <strong>输出：</strong>[1,2,3,4,8,12,11,10,9,5,6,7]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == matrix.length</code></li>
 * <li><code>n == matrix[i].length</code></li>
 * <li><code>1 <= m, n <= 10</code></li>
 * <li><code>-100 <= matrix[i][j] <= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>矩阵</li><li>模拟</li></div></div><br><div><li>👍 1181</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 54
 * 螺旋矩阵
 *
 * @author wangweizhou
 * @date 2022-08-20 05:47:26
 */

public class SpiralMatrix {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new SpiralMatrix().new Solution();
        //int[][] matrix={{1},{},{}};
        //int[][] matrix={{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{1, 2, 3}};
        List<Integer> ans = solution.spiralOrder(matrix);
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：模拟
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> order = new ArrayList<>();// 结果序列
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {// 判空
                return order;
            }
            int rows = matrix.length, columns = matrix[0].length;
            boolean[][] visited = new boolean[rows][columns];//标记数组表示该位置是否被访问过
            int total = rows * columns;//矩阵元素总数，
            int row = 0, column = 0;
            // 解释方向处理，二维矩阵的第一维表示的是4个角；第二维的第一个表示的行数处理，第二维的第二个表示的列数处理。
            // 因为就题干来说这四个的先后处理顺序是一定的
            // {0, 1}表示左上角开始，行数不变，列数加1:向右；{1, 0}表示右上角开始行数加1，列数不变：向下；
            // {0,-1}表示右下角开始，行数不变，列数减1：向左；{-1,0}表示左下角开始行数减1，列数不变：向上。
            int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};// 这里和顺时针的顺序一样
            int dirIndex = 0;// 来确定是哪一个角 。0：左上；1：右上；2：右下；3：左下。
            for (int i = 0; i < total; i++) {// 通过矩阵总的元素数来实现矩阵元素的遍历
                order.add(matrix[row][column]);// 遇到新元素就加入到队列中
                visited[row][column] = true;// 更新标记数组

                // 利用nextRow和nextColumn来判断是否要转向
                int nextRow = row + dirs[dirIndex][0];
                int nextColumn = column + dirs[dirIndex][1];
                // 数组越界或者元素已经访问过了，转角
                if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                    dirIndex = (dirIndex + 1) % 4;// 通过取余实现四个角的循环
                }
                row += dirs[dirIndex][0];
                column += dirs[dirIndex][1];
            }
            return order;
        }




        //// 解法2：按层模拟
        //// 定义矩阵的第 k 层是到最近边界距离为 k 的所有顶点。
        //// 首先输出最外层，然后次外层，最后最内层
        //// 对于每层，从左上方开始以顺时针的顺序遍历所有元素。假设当前层的左上角位于 (top,left)，右下角位于 (bottom,right)，按照如下顺序遍历当前层的元素。
        //
        ////1.从左到右遍历上侧元素，依次为 (top,left) 到 (top,right)。
        ////2.从上到下遍历右侧元素，依次为 (top+1,right) 到 (bottom,right)。
        ////3.如果 left<right 且 top<bottom，则从右到左遍历下侧元素，依次为(bottom,right−1) 到 (bottom,left+1)，
        ////4.以及从下到上遍历左侧元素，依次为 (bottom,left) 到 (top+1,left)。
        ////5.遍历完当前层的元素之后，将 left 和 top 分别增加 1，将 right 和 bottom分别减少 1，进入下一层继续遍历，直到遍历完所有元素为止。
        //
        //
        //public List<Integer> spiralOrder(int[][] matrix) {
        //    List<Integer> order = new ArrayList<>();
        //    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        //        return order;
        //    }
        //    int rows = matrix.length, columns = matrix[0].length;
        //    int left = 0, right = columns - 1, top = 0, bottom = rows - 1;// 定义每层的四个角
        //    while (left <= right && top <= bottom) {//定义每层的范围
        //        for (int column = left; column <= right; column++) {//从左到右遍历每层的上侧元素
        //            order.add(matrix[top][column]);
        //        }
        //        for (int row = top + 1; row <= bottom; row++) {//从上到下遍历每层的右侧元素
        //            order.add(matrix[row][right]);
        //        }
        //        if (left < right && top < bottom) {//到了每层的右下角
        //            for (int column = right - 1; column > left; column--) {//从右到左遍历每层的下侧元素
        //                order.add(matrix[bottom][column]);
        //            }
        //            for (int row = bottom; row > top; row--) {//从下到上遍历每层的右侧元素
        //                order.add(matrix[row][left]);
        //            }
        //        }
        //        //遍历完一层，向内层转换，
        //        left++;
        //        right--;
        //        top++;
        //        bottom--;
        //    }
        //    return order;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
