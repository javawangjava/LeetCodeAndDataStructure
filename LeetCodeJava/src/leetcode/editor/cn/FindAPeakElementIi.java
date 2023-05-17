/**
<p>一个 2D 网格中的 <strong>峰值</strong><strong> </strong>是指那些 <strong>严格大于 </strong>其相邻格子(上、下、左、右)的元素。</p>

<p>给你一个<strong> 从 0 开始编号 </strong>的 <code>m x n</code> 矩阵 <code>mat</code> ，其中任意两个相邻格子的值都<strong> 不相同</strong> 。找出 <strong>任意一个 峰值</strong> <code>mat[i][j]</code> 并 <strong>返回其位置 </strong><code>[i,j]</code> 。</p>

<p>你可以假设整个矩阵周边环绕着一圈值为 <code>-1</code> 的格子。</p>

<p>要求必须写出时间复杂度为 <code>O(m log(n))</code> 或 <code>O(n log(m))</code> 的算法</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/06/08/1.png" style="width: 206px; height: 209px;" /></p>

<pre>
<strong>输入:</strong> mat = [[1,4],[3,2]]
<strong>输出:</strong> [0,1]
<strong>解释:</strong>&nbsp;3 和 4 都是峰值，所以[1,0]和[0,1]都是可接受的答案。
</pre>

<p><strong>示例 2:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2021/06/07/3.png" style="width: 254px; height: 257px;" /></strong></p>

<pre>
<strong>输入:</strong> mat = [[10,20,15],[21,30,14],[7,16,32]]
<strong>输出:</strong> [1,1]
<strong>解释:</strong>&nbsp;30 和 32 都是峰值，所以[1,1]和[2,2]都是可接受的答案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 10<sup>5</sup></code></li>
	<li>任意两个相邻元素均不相等.</li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>矩阵</li></div></div><br><div><li>👍 49</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 1901
 * 寻找峰值 II
 * @author wangweizhou
 * @date 2022-08-30 23:37:16
 */

//public class FindAPeakElementIi{
//	 public static void main(String[] args) {
//	 	 //测试代码
//	 	 Solution solution = new FindAPeakElementIi().new Solution();
//	 }
//
////leetcode submit region begin(Prohibit modification and deletion)
//class Solution {
//    public int[] findPeakGrid(int[][] mat) {
//
//    }
//}
////leetcode submit region end(Prohibit modification and deletion)
//
//}
