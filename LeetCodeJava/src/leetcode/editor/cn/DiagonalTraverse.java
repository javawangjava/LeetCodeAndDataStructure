/**
 * <p>给你一个大小为 <code>m x n</code> 的矩阵 <code>mat</code> ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/04/10/diag1-grid.jpg" style="width: 334px; height:
 * 334px;" />
 * <pre>
 * <strong>输入：</strong>mat = [[1,2,3],[4,5,6],[7,8,9]]
 * <strong>输出：</strong>[1,2,4,7,5,3,6,8,9]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>mat = [[1,2],[3,4]]
 * <strong>输出：</strong>[1,2,3,4]
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
 * <li><code>-10<sup>5</sup> &lt;= mat[i][j] &lt;= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>矩阵</li><li>模拟</li></div></div><br><div><li>👍 374</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 对角线遍历
 * @author wangweizhou
 * @date 2022-06-29 16:00:55
 */
public class DiagonalTraverse {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new DiagonalTraverse().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法1：
        // 每一趟对角线中元素的坐标（x, y）相加的和是递增的。
        // 每一趟都是 x 或 y 其中一个从大到小（每次-1），另一个从小到大（每次+1）。
        // 确定初始值。例如这一趟是 x 从大到小， x 尽量取最大，当初始值超过 x 的上限时，不足的部分加到 y 上面。
        // 确定结束值。例如这一趟是 x 从大到小，这一趟结束的判断是， x 减到 0 或者 y 加到上限。
        // 这一趟是 x 从大到小，那么下一趟是 y 从大到小，循环进行。 并且方向相反时，逻辑处理是一样的，除了x，y和他们各自的上限值是相反的。

        //public int[] findDiagonalOrder(int[][] mat) {
        //
        //    //column 列  row 行
        //    //java 中不存在二维数组
        //    int m = mat.length;// 二维数组的列
        //    int n = mat[0].length;// 二维数组的行
        //    if (mat == null || m == 0 || n == 0) {
        //        return new int[0];
        //    }
		//	boolean directionFlag=true;
        //
		//	for (int i = 0; i < m+n; i++) {
		//		int
        //
		//	}
        //
        //
        //}
    }
//leetcode submit region end(Prohibit modification and deletion)

}
