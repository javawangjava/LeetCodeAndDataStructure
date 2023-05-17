/**
<p>输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>[1,2,3,6,9,8,7,4,5]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>matrix =&nbsp;[[1,2,3,4],[5,6,7,8],[9,10,11,12]]
<strong>输出：</strong>[1,2,3,4,8,12,11,10,9,5,6,7]
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>0 &lt;= matrix.length &lt;= 100</code></li>
	<li><code>0 &lt;= matrix[i].length&nbsp;&lt;= 100</code></li>
</ul>

<p>注意：本题与主站 54 题相同：<a href="https://leetcode-cn.com/problems/spiral-matrix/">https://leetcode-cn.com/problems/spiral-matrix/</a></p>
<div><div>Related Topics</div><div><li>数组</li><li>矩阵</li><li>模拟</li></div></div><br><div><li>👍 456</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 29
 * 顺时针打印矩阵
 * @author wangweizhou
 * @date 2022-09-22 19:49:27
 */
public class ShunShiZhenDaYinJuZhenLcof{
	 public static void main(String[] args) {

	 	 //测试代码
	 	 Solution solution = new ShunShiZhenDaYinJuZhenLcof().new Solution();
		 int[][] matrix={{1,2,3},{4,5,6},{7,8,9}};
		 //int[][] matrix={{1},{2},{3}};
		 int[] nums=solution.spiralOrder(matrix);
		 for(int num:nums){
			 System.out.print(num+",");
		 }

	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

	//	 解法2：按层打印
	// 画图之后就明白了
	public int[] spiralOrder(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {// 判空
			return new int[0];
		}
		int rows = matrix.length, columns = matrix[0].length;
		int[] order = new int[rows * columns];
		int index = 0;
		int left = 0, right = columns - 1, top = 0, bottom = rows - 1;//最外层的边界
		while (left <= right && top <= bottom) {
			// 从左到右遍历一行，即顺时针的上面一行  行不变，列变大
			for (int column = left; column <= right; column++) {
				order[index++] = matrix[top][column];
			}
			// 从上到下遍历一列，即顺时针的右边一列  列不变，行变大
			for (int row = top + 1; row <= bottom; row++) {
				order[index++] = matrix[row][right];
			}
			// left < right && top < bottom 至少两行两列
			if (left < right && top < bottom) {
				// 从右到左遍历一行，即顺时针的下面一行  行不变，列变小
				for (int column = right - 1; column >= left; column--) {
					order[index++] = matrix[bottom][column];
				}
				// 从下到上遍历一列，即顺时针的左边一列  列不变，行变小
				for (int row = bottom-1; row > top; row--) {
					order[index++] = matrix[row][left];
				}
			}
			// 下一圈的左右边界
			left++;
			right--;
			top++;
			bottom--;
		}
		return order;
	}






	/*

	//	 解法1：模拟
	public int[] spiralOrder(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {// 判空
			return new int[0];
		}
		int rows = matrix.length;// 行数
		int columns = matrix[0].length;// 列数
		boolean[][] visited = new boolean[rows][columns];// 同型的辅助数组来标记每个元素所在的位置是否被访问过
		int total = rows * columns;// 路径的长度即为矩阵中的元素数量
		int[] order = new int[total];// 矩阵顺时针访问的结果数组
		int row = 0, column = 0;
		// 顺时针方向：四个方向依次是：右下左上。一维数组的前一个是行，后一个是列，
		int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		int directionIndex = 0;
		for (int i = 0; i < total; i++) {
			order[i] = matrix[row][column];// 将当前元素添加到结果数组中
			visited[row][column] = true;// 标记当前位置已经访问过

			// 依次假定下一个位置，后面紧接着判断下一个位置是否合规，是否需要进行转向
			int nextRow = row + directions[directionIndex][0];
			int nextColumn = column + directions[directionIndex][1];
			// 当路径超出界限或者进入之前访问过的位置时，顺时针旋转，进入下一个方向。
			if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
				directionIndex = (directionIndex + 1) % 4;// 右下左上形成一个顺时针循环。进行转向判断
			}
			// 实际获取下一个位置
			row += directions[directionIndex][0];
			column += directions[directionIndex][1];
		}
		return order;
	}

	*/


}
//leetcode submit region end(Prohibit modification and deletion)

}
