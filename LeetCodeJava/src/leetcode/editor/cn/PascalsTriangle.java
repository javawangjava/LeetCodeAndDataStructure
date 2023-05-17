/**
 * <p>给定一个非负整数 <em><code>numRows</code>，</em>生成「杨辉三角」的前 <em><code>numRows</code> </em>行。</p>
 *
 * <p><small>在「杨辉三角」中，每个数是它左上方和右上方的数的和。</small></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626927345-DZmfxB-PascalTriangleAnimated2.gif" /></p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> numRows = 5
 * <strong>输出:</strong> [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> numRows = 1
 * <strong>输出:</strong> [[1]]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 <= numRows <= 30</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 782</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 118
 * 杨辉三角
 * @author wangweizhou
 * @date 2022-07-04 15:45:12
 */
public class PascalsTriangle {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new PascalsTriangle().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> ret = new ArrayList<List<Integer>>();
            for (int i = 0; i < numRows; ++i) {// i表示行数
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
            return ret;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
