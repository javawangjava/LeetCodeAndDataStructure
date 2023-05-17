/**
<p>给你一幅由 <code>N × N</code> 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。</p>

<p>不占用额外内存空间能否做到？</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
给定 <strong>matrix</strong> = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

<strong>原地</strong>旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
给定 <strong>matrix</strong> =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

<strong>原地</strong>旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
</pre>

<p><strong>注意</strong>：本题与主站 48 题相同：<a href="https://leetcode-cn.com/problems/rotate-image/">https://leetcode-cn.com/problems/rotate-image/</a></p>
<div><div>Related Topics</div><div><li>数组</li><li>数学</li><li>矩阵</li></div></div><br><div><li>👍 235</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 旋转矩阵
 * @author wangweizhou
 * @date 2022-06-29 14:59:05
 */
public class RotateMatrixLcci{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new RotateMatrixLcci().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


	public  void swap(int[][] matrix, int i, int j, int m, int n) {
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
		int[][] newMatrix = new int[length][length];
		//	上下翻转
		for (int i = 0; i < length / 2; i++) {//上下翻转，只需要反转到行数的一半
			for (int j = 0; j < length; j++) {
				swap(matrix, i, j, length - 1 - i, j);
			}
		}
		//    主对角线反转
		for (int i = 0; i < length; i++) {//上下翻转，只需要反转到行数的一半
			for (int j = 0; j < i; j++) {
				swap(matrix, i, j, j, i);
			}
		}
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}
