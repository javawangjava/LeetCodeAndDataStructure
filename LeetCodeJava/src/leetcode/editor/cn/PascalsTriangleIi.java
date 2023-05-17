/**
<p>给定一个非负索引 <code>rowIndex</code>，返回「杨辉三角」的第 <code>rowIndex</code><em> </em>行。</p>

<p><small>在「杨辉三角」中，每个数是它左上方和右上方的数的和。</small></p>

<p><img alt="" src="https://pic.leetcode-cn.com/1626927345-DZmfxB-PascalTriangleAnimated2.gif" /></p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> rowIndex = 3
<strong>输出:</strong> [1,3,3,1]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> rowIndex = 0
<strong>输出:</strong> [1]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> rowIndex = 1
<strong>输出:</strong> [1,1]
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>0 <= rowIndex <= 33</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong></p>

<p>你可以优化你的算法到 <code><em>O</em>(<i>rowIndex</i>)</code> 空间复杂度吗？</p>
<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 407</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 119
 * 杨辉三角 II
 * @author wangweizhou
 * @date 2022-07-04 16:04:42
 */
public class PascalsTriangleIi{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new PascalsTriangleIi().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> getRow(int rowIndex) {

			List<List<Integer>> ret = new ArrayList<List<Integer>>();
			for (int i = 0; i < rowIndex+1; ++i) {// i表示行数
				List<Integer> row = new ArrayList<Integer>();
				for (int j = 0; j <= i; ++j) {//j表示列数
					if (j == 0 || j == i) {//j表示列数，每一行的第一个或者最后一个元素
						row.add(1);
					} else {
						//E get(int index) 返回此列表中指定位置的元素。
						row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
					}
				}
				ret.add(row);
			}
			return ret.get(rowIndex);
    }

	//注意到对第 i+1 行的计算仅用到了第 i 行的数据，因此可以使用滚动数组的思想优化空间复杂度。
	/*

	public List<Integer> getRow(int rowIndex) {
		List<Integer> pre = new ArrayList<Integer>();
		for (int i = 0; i <= rowIndex; ++i) {
			List<Integer> cur = new ArrayList<Integer>();
			for (int j = 0; j <= i; ++j) {
				if (j == 0 || j == i) {
					cur.add(1);
				} else {
					cur.add(pre.get(j - 1) + pre.get(j));
				}
			}
			pre = cur;
		}
		return pre;
	}
*/


	/*

	//

	public List<Integer> getRow(int rowIndex) {
		List<Integer> row = new ArrayList<Integer>();
		row.add(1);
		for (int i = 1; i <= rowIndex; ++i) {
			row.add(0);
			for (int j = i; j > 0; --j) {
				row.set(j, row.get(j) + row.get(j - 1));
			}
		}
		return row;
	}
	*/

}
//leetcode submit region end(Prohibit modification and deletion)

}
