/**
 * /**
 * <p>请你判断一个&nbsp;<code>9 x 9</code> 的数独是否有效。只需要<strong> 根据以下规则</strong> ，验证已经填入的数字是否有效即可。</p>
 *
 * <ol>
 * <li>数字&nbsp;<code>1-9</code>&nbsp;在每一行只能出现一次。</li>
 * <li>数字&nbsp;<code>1-9</code>&nbsp;在每一列只能出现一次。</li>
 * <li>数字&nbsp;<code>1-9</code>&nbsp;在每一个以粗实线分隔的&nbsp;<code>3x3</code>&nbsp;宫内只能出现一次。（请参考示例图）</li>
 * </ol>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>注意：</strong></p>
 *
 * <ul>
 * <li>一个有效的数独（部分已被填充）不一定是可解的。</li>
 * <li>只需要根据以上规则，验证已经填入的数字是否有效即可。</li>
 * <li>空白格用&nbsp;<code>'.'</code>&nbsp;表示。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/04/12/250px-sudoku-by-l2g-20050714svg.png"
 * style="height:250px; width:250px" />
 * <pre>
 * <strong>输入：</strong>board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * <strong>输出：</strong>false
 * <strong>解释：</strong>除了第一行的第一个数字从<strong> 5</strong> 改为 <strong>8 </strong>以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>board.length == 9</code></li>
 * <li><code>board[i].length == 9</code></li>
 * <li><code>board[i][j]</code> 是一位数字（<code>1-9</code>）或者 <code>'.'</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>矩阵</li></div></div><br><div><li>👍 895</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;


/**
 * 36
 * 有效的数独
 */


public class ValidSudoku {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ValidSudoku().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {


        // 解法1： 标记数组+双循环判断
        // 只要我们判断是否为有效的数独，所以只需要判断已经填完的是否违反数独的规则
        // 则需要判断每一行，每一列，每个小方块中数字是否重复，这时候用标记数组或者哈希表实现就可以

        // num 对应 board[row][col]所指向的元素
        // rows[row][curNum] 第一个下标表示board[row][col]所指向的元素的所在行row，
        // cols[col][curNum] 第一个下标表示board[row][col]所指向的元素的所在列col。
        // boxs[row/3*3+col/3][curNum] 第一个下标表示数字board[row][col]所在的第几个小方块
        // 第二个下标表示board[row][col]所指向的元素的值curNum。
        // 若board[row][col]所指向的元素不在对应行，对应列，对应小方块中，则对应数组值为false即该行中没有数curNum；对应数组值为true表示该行中有数curNum。


        public boolean isValidSudoku(char[][] board) {
            if (board == null || board.length != 9 || board[0].length != 9) {//判空和二维数组规模
                return false;
            }
            int rowLength = board.length;// 二维数组行数
            int colLength = board[0].length;// 二维数组列数

            // 整个board有9行，第二维的维数10是为了让下标有9，和数独中的数字9对应。也可以第二维就是9，然后把把数字-1，这样就和下标对应了。
            boolean[][] rows = new boolean[9][10];// 哈希表存储每一行的每个数是否出现过，默认初始情况下，每一行每一个数都没有出现过
            boolean[][] cols = new boolean[9][10];// 存储每一列的每个数是否出现过，默认初始情况下，每一列的每一个数都没有出现过
            boolean[][] boxs = new boolean[9][10];// 存储每一个box的每个数是否出现过，默认初始情况下，在每个box中，每个数都没有出现过。整个board有9个box。
            for (int row = 0; row < rowLength; row++) {//遍历board的行
                for (int col = 0; col < colLength; col++) {//遍历board的列
                    if (board[row][col] == '.') {//没有数字，进入下一循环
                        continue;
                    }
                    //当遍历到一个数字时就先判断哈希表中有没有当前数字，已经有该数字就不合规则，没有该数字那么对应标志设置为true。
                    int curNum = board[row][col] - '0';//获取当前位置的数字
                    if (rows[row][curNum] || cols[col][curNum] || boxs[row / 3 * 3 + col / 3][curNum]) {//该数字是否在该行、该列、对应的小田字格中出现
                        return false;
                    }
                    rows[row][curNum] = true;
                    cols[col][curNum] = true;
					boxs[row / 3 * 3 + col / 3][curNum] = true;
                }
            }
			return true;
        }



 /*

        // 解法二：哈希表， 效率太低
        // map中key值是所在行，列，或者小方块。对应的值是所在行，列或者小方块对应的set集合
        public boolean isValidSudoku(char[][] board) {
            Map<Integer, Set<Integer>> row  = new HashMap<>();
            Map<Integer, Set<Integer>>  col = new HashMap<>();
            Map<Integer, Set<Integer>>  area = new HashMap<>();
            for (int i = 0; i < 9; i++) {
                row.put(i, new HashSet<>());
                col.put(i, new HashSet<>());
                area.put(i, new HashSet<>());
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    char c = board[i][j];
                    if (c == '.') continue;
                    int u = c - '0';
                    int idx = i / 3 * 3 + j / 3;
                    if (row.get(i).contains(u) || col.get(j).contains(u) || area.get(idx).contains(u)) return false;
                    row.get(i).add(u);
                    col.get(j).add(u);
                    area.get(idx).add(u);
                }
            }
            return true;
        }

        */

    }

//leetcode submit region end(Prohibit modification and deletion)

}
