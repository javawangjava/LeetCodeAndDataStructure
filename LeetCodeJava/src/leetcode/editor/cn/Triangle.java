/**
 * <p>给定一个三角形 <code>triangle</code> ，找出自顶向下的最小路径和。</p>
 *
 * <p>每一步只能移动到下一行中相邻的结点上。<strong>相邻的结点 </strong>在这里指的是 <strong>下标</strong> 与 <strong>上一层结点下标</strong> 相同或者等于
 * <strong>上一层结点下标 + 1</strong> 的两个结点。也就是说，如果正位于当前行的下标 <code>i</code> ，那么下一步可以移动到下一行的下标 <code>i</code> 或 <code>i +
 * 1</code> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * <strong>输出：</strong>11
 * <strong>解释：</strong>如下面简图所示：
 * <strong>2</strong>
 * <strong>3</strong> 4
 * 6 <strong>5</strong> 7
 * 4 <strong>1</strong> 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>triangle = [[-10]]
 * <strong>输出：</strong>-10
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= triangle.length <= 200</code></li>
 * <li><code>triangle[0].length == 1</code></li>
 * <li><code>triangle[i].length == triangle[i - 1].length + 1</code></li>
 * <li><code>-10<sup>4</sup> <= triangle[i][j] <= 10<sup>4</sup></code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>你可以只使用 <code>O(n)</code> 的额外空间（<code>n</code> 为三角形的总行数）来解决这个问题吗？</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1101</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 120
 * 三角形最小路径和
 *
 * @author wangweizhou
 * @date 2022-09-10 18:13:58
 */

public class Triangle {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new Triangle().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    class Solution {


        // 可能需要用矩阵坐标来定位三角形中的数字。图中的相邻两行数字的位置相互交错，这样很难用矩阵坐标来表示数字的位置。
        // 可以移动三角形每行的位置使它们左端对齐，对齐之后就能很方便地用矩阵的行坐标和列坐标来定位每个数字。
        // 如果三角形有n行数字，将这些行左端对齐之后就成了一个n×n的矩阵的左下半部分。如果三角形中某个数字在矩阵中的行号和列号分别是i和j，那么i≥j。
        // 在左端对齐的三角形中，从一个数字出发，下一步要么前往下一行正下方的数字，要么前往右下方的数字。


        // 可以用函数f（i，j）表示从三角形的左上角坐标为（0，0）的位置出发到达坐标为（i，j）（i≥j）的位置时路径数字之和的最小值，同时用grid[i][j]表示三角形行号和列号分别为i和j的数字。
        // 如果三角形中包含n行数字，那么f（n-1，j）的最小值就是整个问题的最优解。
        // 当j等于0时，也就是当前到达某行的第1个数字。机器人位于格子最左边的一列，机器人不可能从某个位置向右走一步到达一个列号j等于0的位置。
        // 此时只有一条从上到下的路径，因此f（i，0）等于f（i-1，0）和grid[i][0]之和，即最左边一列从grid[0][0]开始到grid[i][0]为止所有格子的值之和。

        // 如果i等于j，也就是当前到达某行的最后一个数字，此时它的正上方没有数字，前一步只能是来自它左上方的数字，因此f（i，i）等于f（i-1，i-1）与grid[i][i]之和。
        // 如果当前行号和列号分别为i和j的位置位于某行的中间，那么前一步既可能是来自它正上方的数字（行号和列号分别为i-1和j），也可能是来自它左上方的数字（行号和列号分别为i-1和j-1），
        // 所以f（i，j）等于f（i-1，j）与f（i-1，j-1）的最小值再加上T[i][j]。


        //// 解法1：写法2 动态规划
        //// 三角形中元素按行存储，外层List的每一个元素就是三角形的每一行。内层List的每一个元素就是对应行的第几个元素
        public int minimumTotal(List<List<Integer>> triangle) {
            if (triangle == null || triangle.size() == 0) {
                return 0;
            }
            int size = triangle.size();// 三角形行数
            int[][] dp = new int[size][size];
            // 初始状态：
            dp[0][0] = triangle.get(0).get(0);// 初始化为左上角的数值
            for (int i = 1; i < size; i++) {// 初始化矩阵的第一列和矩阵的对角线
                dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);// 矩阵的第一列，即三角形每一行的第一个数字，只能从上到下
                dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);// 矩阵的对角线，即三角形每一行的最后一个数字，只能是从左上角下来
            }

            // dp[i][j]是前缀和数组，最后从前缀和数组中值最小的
            for (int i = 1; i < size; i++) {
                for (int j = 1; j < i; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }

            // 找出从左上角到达最后一行的路径和的最小值
            int min = Integer.MAX_VALUE;
            for (int num : dp[size - 1]) {// 遍历二维数组的最后一行，找出最小值
                min = Math.min(min, num);
            }
            return min;
        }





        //// 解法1：写法1 动态规划
        //// 三角形中元素按行存储，外层List的每一个元素就是三角形的每一行。内层List的每一个元素就是对应行的第几个元素
        //public int minimumTotal(List<List<Integer>> triangle) {
        //    if (triangle == null||triangle.size()==0) {
        //        return 0;
        //    }
        //    int size = triangle.size();// 三角形行数
        //    int[][] dp = new int[size][size];
        //    dp[0][0] = triangle.get(0).get(0);// 初始化为左上角的数值
        //
        //    // dp[i][j]是前缀和数组，最后从前缀和数组中值最小的
        //    // 遍历左下角
        //    for (int i = 0; i < size; i++) {
        //        for (int j = 0; j <= i; j++) {
        //            int num = triangle.get(i).get(j);//获取当前位置的数值
        //            if (i > 0 && j == 0) {// 矩阵的第一列，即三角形每一行的第一个数字，只能从上到下
        //                dp[i][j] = dp[i - 1][j] + num;
        //            } else if (i > 0 && i == j) {// 矩阵的对角线，即三角形每一行的最后一个数字，只能是从左上角下来
        //                dp[i][j] = dp[i - 1][j - 1] + num;
        //            } else if (i > 0) {
        //                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + num;
        //            }
        //        }
        //    }
        //    int min = Integer.MAX_VALUE;
        //    for (int num : dp[size - 1]) {// 遍历二维数组的最后一行，找出最小值
        //        min = Math.min(min, num);
        //    }
        //    return min;
        //}





        // 由于计算f（i，j）时只需要用到它上面一行的f（i-1，j）和f（i-1，j-1），因此实际上只需要保留两行就可以。
        // 也就是说，创建一个只有两行的数组dp，将f（i，j）保存到“dp[i%2][j]”中即可。
        // 还可以考虑进一步优化空间效率，即能否只需要一个一维数组dp。如果能够将f（i，j）和f（i-1，j）都保存到“dp[j]”中，那么一个一维数组就可以保存所需的数据。

        // 假设在计算f（i，j）之前“dp[j]”中保存的是f（i-1，j）的值。在计算f（i，j）时需要f（i-1，j-1）和f（i-1，j）。
        // 在计算完f（i，j）之后能否用f（i，j）的值覆盖保存在“dp[j]”中的f（i-1，j）取决于是否还需要f（i-1，j）的值。
        // 如果每行按照从左到右的顺序，那么在计算完f（i，j）之后将计算f（i，j+1），而计算f（i，j+1）可能需要f（i-1，j）和f（i-1，j+1）的值，
        // 也就是f（i-1，j）的值在计算f（i，j+1）时可能会被用到，因此在计算完f（i，j）之后不能将f（i-1，j）的值丢掉。
        // 但计算f（i，j）时并不依赖同一行左侧的f（i，j-1），因此并不一定要按照从左到右的顺序计算每行，按照从右到左的顺序计算也可以。

        // 如果按照从右到左的顺序，则先计算f（i，j），需要用到f（i-1，j-1）和f（i-1，j）。接下来计算f（i，j-1），需要用到f（i-1，j-1）和f（i-1，j-2）。
        // 计算f（i-1，j-1）并不需要用到f（i-1，j）。
        // 因此，按照从右到左的顺序在计算完f（i，j）之后，将f（i，j）的值保存到“dp[j]”中并替换f（i-1，j）的值，并且不会带来任何问题，因此f（i-1，j）的值以后就不再需要。


        //// 解法1：写法4 动态规划 +优化空间  一维数组就是将两行的数组合并使用覆盖合并为一个
        //public int minimumTotal(List<List<Integer>> triangle) {
        //    if (triangle == null || triangle.size() == 0) {
        //        return 0;
        //    }
        //    int size = triangle.size();// 三角形行数
        //    int[] dp = new int[size];
        //
        //    for (int i = 0; i < size; i++) {// 遍历三角形的每一行
        //        // 每一行从右向左遍历，这样才能用一维数组来覆盖
        //        for (int j = i; j >=0 ; j--) {
        //            if ( j == 0) {
        //                dp[j] =dp[j] + triangle.get(i).get(j);// 矩阵的第一列，即三角形每一行的第一个数字，只能从上到下
        //            } else if (j==i) {
        //                dp[j] = dp[j - 1]+ triangle.get(i).get(j);// 矩阵的对角线，即三角形每一行的最后一个数字，只能是从左上角下来
        //            } else {
        //                dp[j] = Math.min(dp[j], dp[j - 1])+ triangle.get(i).get(j);
        //            }
        //        }
        //    }
        //
        //    int min = Integer.MAX_VALUE;
        //    for (int num : dp) {// 遍历二维数组的最后一行，找出最小值
        //        min = Math.min(min, num);
        //    }
        //    return min;
        //}





        //// 解法3：动态规划
        //// 和上面的思路相反，从底边到[0,0]位置的最小和
        //// 只能从正下方或者右下方到达[i,j]，即到达[i,j]位置只能从[i+1,j+1]和[i+1,j]走向[i,j]。
        //public int minimumTotal(List<List<Integer>> triangle) {
        //    if (triangle == null || triangle.size() == 0) {
        //        return 0;
        //    }
        //    int n = triangle.size();
        //    // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
        //    int[][] dp = new int[n + 1][n + 1];
        //    // 从三角形的最后一行开始递推。
        //    for (int i = n - 1; i >= 0; i--) {// 从最后一行向第一行遍历
        //        for (int j = 0; j <= i; j++) {// 每一行是从左向右遍历
        //            dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
        //        }
        //    }
        //    return dp[0][0];
        //}




        //// 解法2：一维动态规划
        //public int minimumTotal(List<List<Integer>> triangle) {
        //    int n = triangle.size();
        //    int[] dp = new int[n + 1];
        //    for (int i = n - 1; i >= 0; i--) {
        //        for (int j = 0; j <= i; j++) {
        //            dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
        //        }
        //    }
        //    return dp[0];
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
