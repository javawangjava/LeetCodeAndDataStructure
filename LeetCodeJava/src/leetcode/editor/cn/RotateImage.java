/**
 * <p>给定一个 <em>n&nbsp;</em>×&nbsp;<em>n</em> 的二维矩阵&nbsp;<code>matrix</code> 表示一个图像。请你将图像顺时针旋转 90 度。</p>
 *
 * <p>你必须在<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"> 原地</a>
 * </strong> 旋转图像，这意味着你需要直接修改输入的二维矩阵。<strong>请不要 </strong>使用另一个矩阵来旋转图像。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg" style="height: 188px; width: 500px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * <strong>输出：</strong>[[7,4,1],[8,5,2],[9,6,3]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg" style="height: 201px; width: 500px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * <strong>输出：</strong>[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == matrix.length == matrix[i].length</code></li>
 * <li><code>1 &lt;= n &lt;= 20</code></li>
 * <li><code>-1000 &lt;= matrix[i][j] &lt;= 1000</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>数学</li><li>矩阵</li></div></div><br><div><li>👍 1336</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 48
 * 旋转图像
 */

public class RotateImage {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new RotateImage().new Solution();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        solution.rotate(matrix);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //  解法2写法2：翻转代替旋转
        //  先将其通过水平轴翻转得到，再根据主对角线翻转得到。先后顺序也可以颠倒。
        //  matrix[row][col] 水平轴翻转 matrix[n−1-row][col]
        //  matrix[row][col] 主对角线翻转 matrix[col][row]
        //  matrix[row][col] 水平轴翻转 matrix[n−1-row][col]主对角线翻转 matrix[col][n−1-row]

        public void rotate(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            int length = matrix.length;
            //	上下翻转,行数只要到一半就可以，列元素必须全部反转
            for (int row = 0; row < length / 2; row++) {// 如果是偶数行就刚好反转一半。如果是奇数行那么除2向下取整，中间的那一行不反转。
                for (int col = 0; col < length; col++) {
                    int temp = matrix[row][col];
                    matrix[row][col] = matrix[length - 1 - row][col];
                    matrix[length - 1 - row][col] = temp;
                }
            }

            // 矩阵转置，对角线不用交换
            // 矩阵关于主对角线转置。主对角线反转。这里是行数是全部，列数是行数之后。
            for (int row = 0; row < length; row++) {//
                for (int col = row + 1; col < length; col++) {// 沿对角线反转，只需要一半。
                    int temp = matrix[row][col];
                    matrix[row][col] = matrix[col][row];
                    matrix[col][row] = temp;
                }
            }
        }




      /*
        //	解法2：翻转代替旋转
        //  先将其通过水平轴翻转得到，再根据主对角线翻转得到。先后顺序也可以颠倒。
        //  matrix[row][col] 水平轴翻转 matrix[n−1-row][col]
        //  matrix[row][col] 主对角线翻转 matrix[col][row]
        //  matrix[row][col] 水平轴翻转 matrix[n−1-row][col]主对角线翻转 matrix[col][n−1-row]

        //swap(int[][] matrix, int i, int j,int m,int n)表示matrix[i][j]与matrix[m][n]交换


        public static void swap(int[][] matrix, int i, int j, int m, int n) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            int length = matrix.length;
            if (i < length && j < length && m < length && n < length) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[m][n];
                matrix[m][n] = temp;
            }
        }


        public void rotate(int[][] matrix) {
            int length = matrix.length;
            //	上下翻转
            for (int i = 0; i < length / 2; i++) {//上下翻转，只需要反转到行数的一半
                for (int j = 0; j < length; j++) {
                    swap(matrix, i, j, length - 1 - i, j);
                }
            }
            //   主对角线反转
            for (int i = 0; i < length; i++) {//上下翻转，只需要反转到行数的一半
                for (int j = 0; j < i; j++) {
                    swap(matrix, i, j, j, i);
                }
            }
        }*/




        // 方法一：使用辅助数组
        // 对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在倒数第 i 列的第 j 个位置
        // 由于矩阵中的行列从 0 开始计数，因此对于矩阵中的元素 matrix[row][col]，在旋转后，它的新位置为matrixnew[col][n−row−1]。
        // 我们使用一个与 matrix 大小相同的辅助数组matrixnew，临时存储旋转后的结果。
        // 我们遍历matrix 中的每一个元素，根据上述规则将该元素存放到 matrixnew中对应的位置。在遍历完成之后，再将 matrixnew中的结果复制到原数组中即可。

        /*
        public void rotate(int[][] matrix) {
            int length=matrix.length;
            int[][] newMatrix=new int[length][length];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    newMatrix[j][length-i-1]=matrix[i][j];
                }
            }

            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    matrix[i][j]=newMatrix[i][j];
                }
            }
        }*/




        //  方法二：原地旋转
        //  temp=matrix[row][col]
        //  matrix[row][col]=matrix[n−col−1][row]
        //  matrix[n−col−1][row]=matrix[n−row−1][n−col−1]
        //  matrix[n−row−1][n−col−1]=matrix[col][n−row−1]
        //  matrix[col][n−row−1]=temp

        //  当 n 为偶数时，我们需要枚举 n^2 / 4 = (n/2)*(n/2)个位置，
        //  当 n 为奇数时，由于中心的位置经过旋转后位置不变，我们需要枚举 (n^2-1) / 4 = ((n−1)/2)×((n+1)/2) 个位置

       /*
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            for (int i = 0; i < n / 2; ++i) {
                for (int j = 0; j < (n + 1) / 2; ++j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[n - j - 1][i];
                    matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                    matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                    matrix[j][n - i - 1] = temp;
                }
            }
        }
        */

    }
//leetcode submit region end(Prohibit modification and deletion)

}
