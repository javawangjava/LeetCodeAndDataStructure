/**
 * <p>给定一个&nbsp;<code><em>m</em> x <em>n</em></code> 的矩阵，如果一个元素为 <strong>0 </strong>，则将其所在行和列的所有元素都设为
 * <strong>0</strong> 。请使用 <strong>
 *     <a href="http://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地</a>
 * </strong> 算法<strong>。</strong></p>
 *
 * <ul>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/08/17/mat1.jpg" style="width: 450px; height: 169px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * <strong>输出：</strong>[[1,0,1],[0,0,0],[1,0,1]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/08/17/mat2.jpg" style="width: 450px; height: 137px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * <strong>输出：</strong>[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == matrix.length</code></li>
 * <li><code>n == matrix[0].length</code></li>
 * <li><code>1 &lt;= m, n &lt;= 200</code></li>
 * <li><code>-2<sup>31</sup> &lt;= matrix[i][j] &lt;= 2<sup>31</sup> - 1</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>一个直观的解决方案是使用 &nbsp;<code>O(<em>m</em><em>n</em>)</code>&nbsp;的额外空间，但这并不是一个好的解决方案。</li>
 * <li>一个简单的改进方案是使用 <code>O(<em>m</em>&nbsp;+&nbsp;<em>n</em>)</code> 的额外空间，但这仍然不是最好的解决方案。</li>
 * <li>你能想出一个仅使用常量空间的解决方案吗？</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>矩阵</li></div></div><br><div><li>👍 736</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 矩阵置零
 * @author wangweizhou
 * @date 2022-06-29 15:00:02
 */
public class SetMatrixZeroes {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new SetMatrixZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 方法一：使用标记数组

        // 用两个标记数组分别记录每一行和每一列是否有零出现。
        // 具体地，我们首先遍历该数组一次，如果某个元素为 0，那么就将该元素所在的行和列所对应标记数组的位置置为 true。
        // 最后我们再次遍历该数组，用标记数组更新原数组即可。

/*
    public void setZeroes(int[][] matrix) {
		int m=matrix.length;
		int n=matrix[0].length;
		boolean[] row=new boolean[m];
		boolean[] col=new boolean[n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(matrix[i][j]==0){ //如果矩阵某个元素为0，则把对应行和列的标记数组置true
					row[i]=col[j]=true;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(row[i]||col[j]){//如果对应行和列的标记数组为true,则把该行或者该列置为0.
					matrix[i][j]=0;
				}
			}
		}
    }*/


//	解法2：  看的不太明白 后面再看
//关键思想: 用matrix第一行和第一列记录该行该列是否有0,作为标志位。
//但是对于第一行,和第一列要设置一个标志位,为了防止自己这一行(一列)也有0的情况。


        public void setZeroes(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;
            boolean row0_flag = false;
            boolean col0_flag = false;
            // 单独处理第一行和第一列有零的情况
            // 第一行是否有零
            for (int j = 0; j < col; j++) {
                if (matrix[0][j] == 0) {
                    row0_flag = true;
                    break;
                }
            }

            // 第一列是否有零
            for (int i = 0; i < row; i++) {
                if (matrix[i][0] == 0) {
                    col0_flag = true;
                    break;
                }
            }


            // 把第一行第一列作为标志位
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = matrix[0][j] = 0;
                    }
                }
            }

            // 置0
            // 根据第一行第一列的标志位置0
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
            // 根据第一行的标志位置0
            if (row0_flag) {
                for (int j = 0; j < col; j++) {
                    matrix[0][j] = 0;
                }
            }
            // 根据第一列的标志位置0
            if (col0_flag) {
                for (int i = 0; i < row; i++) {
                    matrix[i][0] = 0;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
