/**
<p><big><small>给定一个二维矩阵 <code>matrix</code>，</small></big>以下类型的多个请求：</p>

<ul>
	<li><big><small>计算其子矩形范围内元素的总和，该子矩阵的左上角为 <code>(row1,&nbsp;col1)</code> ，右下角为 <code>(row2,&nbsp;col2)</code> 。</small></big></li>
</ul>

<p>实现 <code>NumMatrix</code> 类：</p>

<ul>
	<li><code>NumMatrix(int[][] matrix)</code>&nbsp;给定整数矩阵 <code>matrix</code> 进行初始化</li>
	<li><code>int sumRegion(int row1, int col1, int row2, int col2)</code>&nbsp;返回<big><small>左上角</small></big><big><small> <code>(row1,&nbsp;col1)</code>&nbsp;、右下角&nbsp;<code>(row2,&nbsp;col2)</code></small></big>&nbsp;的子矩阵的元素总和。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://pic.leetcode-cn.com/1626332422-wUpUHT-image.png" style="width: 200px;" /></p>

<pre>
<strong>输入:</strong> 
[&quot;NumMatrix&quot;,&quot;sumRegion&quot;,&quot;sumRegion&quot;,&quot;sumRegion&quot;]
[[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
<strong>输出:</strong> 
[null, 8, 11, 12]

<strong>解释:</strong>
NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m,&nbsp;n &lt;=&nbsp;200</code><meta charset="UTF-8" /></li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= matrix[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= row1 &lt;= row2 &lt; m</code></li>
	<li><code>0 &lt;= col1 &lt;= col2 &lt; n</code></li>
	<li><meta charset="UTF-8" />最多调用 <code>10<sup>4</sup></code> 次&nbsp;<code>sumRegion</code> 方法</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 304&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/range-sum-query-2d-immutable/">https://leetcode-cn.com/problems/range-sum-query-2d-immutable/</a></p>
<div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>矩阵</li><li>前缀和</li></div></div><br><div><li>👍 44</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 二维子矩阵的和
 * @author wangweizhou
 * @date 2022-06-22 23:48:47
 */
public class O4NDxx{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 //Solution solution = new O4NDxx().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class NumMatrix {

	// 解法2：一维前缀和

	private int[][] sums;
	public NumMatrix(int[][] matrix) {//构造器
		int row = matrix.length;//矩阵行数
		int column = matrix[0].length;//矩阵列数
		if (row == 0 || column == 0) {//其实这里只要row==0就可以，java中矩阵本质是一维数组
			return;
		}
		sums = new int[row + 1][column + 1];
		for (int i = 0; i < row; i++) {
			int rowSum = 0;
			for (int j = 0; j < column; j++) {
				// rowSum表示第i行的和。rowSum=matrix[i][0]+matrix[i][1]+...+matrix[i][j]
				rowSum += matrix[i][j];//
				sums[i + 1][j + 1] = sums[i][j + 1] + rowSum;// rowSum
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return sums[row2 + 1][col2 + 1]- sums[row1][col2 + 1] - sums[row2 + 1][col1] + sums[row1][col1];
	}


	//	 解法1：二维前缀和
	// 1. 定义二维前缀和 preNums[i, j]: 第i行第j列格子左上部分所有元素之和(不包含第i行第j列格子元素)【包括不包括前后要处理一致】
	// 2. 前缀和计算公式 preNums[i][j] = preNums[i - 1][j] + preNums[i][j - 1] - preNums[i - 1][j - 1] + preNums[i][j]

       /* private int[][] sums;
        public NumMatrix(int[][] matrix) {//构造器
            if (matrix.length == 0 || matrix[0].length == 0) {// 处理输入数组为空集
                return;
            }

            // sums[i+1][j+1]表示从左上角[0][0]到右下角[i][j]的子矩阵数字之和
            sums = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 0; i < matrix.length; i++) {//i表示行，j表示列
                for (int j = 0; j < matrix[0].length; j++) {
                    sums[i + 1][j + 1] = sums[i][j + 1] + sums[i + 1][j] - sums[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sums[row2 + 1][col2 + 1]- sums[row1][col2 + 1] - sums[row2 + 1][col1] + sums[row1][col1];
        }*/

}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
