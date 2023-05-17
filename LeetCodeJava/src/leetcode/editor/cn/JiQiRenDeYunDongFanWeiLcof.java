/**
 * <p>地上有一个m行n列的方格，从坐标 <code>[0,0]</code> 到坐标 <code>[m-1,n-1]</code> 。一个机器人从坐标 <code>[0, 0]
 * </code>的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37]
 * ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>m = 2, n = 3, k = 1
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>m = 3, n = 1, k = 0
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n,m &lt;= 100</code></li>
 * <li><code>0 &lt;= k&nbsp;&lt;= 20</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>动态规划</li></div></div><br><div><li>👍
 * 570</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 面试题13
 * 机器人的运动范围
 *
 * @author wangweizhou
 * @date 2022-09-26 18:22:44
 */

public class JiQiRenDeYunDongFanWeiLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new JiQiRenDeYunDongFanWeiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 图的深度遍历：
        public int movingCount(int m, int n, int k) {
            if (m <= 0 || n <= 0 || k < 0) {
                return 0;
            }
            boolean[][] isVisited = new boolean[m][n];
            return graphDfs(isVisited, 0, 0, k);
        }


        // 递归实现图的遍历
        private int graphDfs(boolean[][] isVisited, int row, int col, int k) {
            int count = 1;// 当前位置可访问，将计数器设置为1.
            isVisited[row][col] = true;// 将当前访问的位置设置为已访问
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : dirs) {
                int newR = row + dir[0];
                int newC = col + dir[1];
                if (newR >= 0 && newR < isVisited.length && newC >= 0 && newC < isVisited[0].length &&
                !isVisited[newR][newC]) {
                    if (getDigits(newR) + getDigits(newC) <= k) {
                        count += graphDfs(isVisited, newR, newC, k);
                    }
                }
            }
            return count;
        }

        //// 迭代实现图的深度遍历
        //private int graphDfs(boolean[][] isVisited, int i, int j, int k) {
        //    int rowLen = isVisited.length;
        //    int colLen = isVisited[0].length;
        //    Deque<int[]> stack = new LinkedList<>();
        //    // 当将某一个位置添加到栈中，随即将进栈的位置标记为true,这样能防止同一个位置重复访问
        //    stack.push(new int[]{i, j});
        //    isVisited[i][j] = true;
        //    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //    int count = 0;
        //    while (stack.size() > 0) {
        //        int[] pos = stack.poll();
        //        count++;
        //        for (int[] dir : dirs) {
        //            int row = pos[0] + dir[0];
        //            int col = pos[1] + dir[1];
        //            if (row >= 0 && row < rowLen && col >= 0 && col < colLen && !isVisited[row][col]) {
        //                if (getDigits(row) + getDigits(col) <= k) {
        //                    stack.push(new int[]{row, col});
        //                    isVisited[row][col] = true;
        //                }
        //            }
        //        }
        //    }
        //    return count;
        //}

        // 计算当前位置的数位和
        private int getDigits(int num) {
            int digitSum = 0;
            while (num > 0) {
                digitSum += num % 10;
                num = num / 10;
            }
            return digitSum;
        }





        ////	解法1：DFS  写法2    图的遍历
        //public int movingCount(int m, int n, int k) {
        //    if (m <= 0 || n <= 0 || k < 0) {// 判空
        //        return 0;
        //    }
        //    boolean[][] isVisited = new boolean[m][n];// 标记数组标记当前位置（row,col）是否已经访问过。
        //    return moveCountFunc(isVisited, 0, 0, k);// 只能从（0，0）位置开始
        //}
        //
        //
        //// moveCountFunc返回值是从（row,col）出发可以经过的多少个格子。
        //private int moveCountFunc(boolean[][] isVisited, int row, int col, int k) {
        //    int count = 0;
        //    if (check(isVisited, row, col, k)) {// 要从(row,col)进入遍历，那么首先要检查当前位置（row,col）是否可以访问
        //        isVisited[row][col] = true;// 将当前位置标记为已经访问
        //        // 其实下面这个类比二叉树的子节点可以向父节点贡献的最大数值，二叉树是左右子树相加。
        //        // 访问了当前位置，那么访问的位置数+1。加上从当前位置开始可以上下左右遍历的所有位置数量。这里是四个方向，所以是四个方向相加。
        //        count = 1 + moveCountFunc(isVisited, row - 1, col, k) + moveCountFunc(isVisited, row + 1, col, k) +
        //                moveCountFunc(isVisited, row, col - 1, k) + moveCountFunc(isVisited, row, col + 1, k);
        //    }
        //    return count;
        //}
        //
        //
        //// 检查当前位置（row,col）是否可以访问
        //// 下标出界，当前位置已经访问或者当前位置的数位和>k,则当前位置不可以访问
        //private boolean check(boolean[][] isVisited, int row, int col, int k) {
        //    if (row < 0 || row >= isVisited.length || col < 0 || col >= isVisited[0].length
        //            || isVisited[row][col] || getDigitSum(row) + getDigitSum(col) > k) {
        //        return false;
        //    }
        //    return true;
        //    ////当前位置没有越界，当前位置没有访问过，单亲未知的数位和满足<=k
        //    //if (row >= 0 && row < isVisited.length && col >= 0 && col < isVisited[0].length &&
        //    !isVisited[row][col] && getDigitSum(row)
        //    //        + getDigitSum(col) <= k) {
        //    //    return true;
        //    //}
        //    //return false;
        //}
        //
        //
        //// 计算数字的数位和，其实就是将数字的每一位相加得到的和。
        //private int getDigitSum(int num) {
        //    int sum = 0;
        //    while (num > 0) {
        //        sum += num % 10;
        //        num /= 10;
        //    }
        //    return sum;
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
