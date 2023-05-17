/**
<p>编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
<strong>输出：</strong>
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
<strong>输出：</strong>
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
</pre>
<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>矩阵</li></div></div><br><div><li>👍 70</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 零矩阵
 * @author wangweizhou
 * @date 2022-06-29 15:25:42
 */
public class ZeroMatrixLcci{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new ZeroMatrixLcci().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	public void setZeroes(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		boolean row0_flag = false;
		boolean col0_flag = false;
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
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		if (row0_flag) {
			for (int j = 0; j < col; j++) {
				matrix[0][j] = 0;
			}
		}
		if (col0_flag) {
			for (int i = 0; i < row; i++) {
				matrix[i][0] = 0;
			}
		}
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}
