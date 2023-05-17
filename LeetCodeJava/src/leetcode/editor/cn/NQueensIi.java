/**
 * <p><strong>n&nbsp;皇后问题</strong> 研究的是如何将 <code>n</code>&nbsp;个皇后放置在 <code>n × n</code> 的棋盘上，并且使皇后彼此之间不能相互攻击。</p>
 *
 * <p>给你一个整数 <code>n</code> ，返回 <strong>n 皇后问题</strong> 不同的解决方案的数量。</p>
 *
 * <p>&nbsp;</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/queens.jpg" style="width: 600px; height: 268px;" />
 * <pre>
 * <strong>输入：</strong>n = 4
 * <strong>输出：</strong>2
 * <strong>解释：</strong>如上图所示，4 皇后问题存在两个不同的解法。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 9</code></li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>回溯</li></div></div><br><div><li>👍 379</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


/**
 * 52
 * N皇后 II
 *
 * @author wangweizhou
 * @date 2022-08-02 17:14:24
 */

public class NQueensIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new NQueensIi().new Solution();




    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // ⽅法：递归
        //step1:对于第一行，皇后可能出现在该行的任意一列，我们用一个数组pos记录皇后出现的位置，下标为行号，元素值为列号。
        //step2:如果皇后出现在第一列，那么第一行的皇后位置就确定了，相当于在剩余的n-1行中找n-1个皇后的位置，这就是一个子问题，因此使用递归。
        //终止条件：当最后一行都被选择了位置，说明个皇后位置齐了，增加一种方案数返回。
        //返回值：每一级要将选中的位置及方案数返回。
        //本级任务：每一级其实就是在该行选择一列作为该行皇后的位置，遍历所有的列选择一个符合条件的位置加入数组，然后进入下一级。
        //step3:每个子问题检查是否符合条件，我们可以对比所有已经记录的行，对其记录的列号查看与当前行列号的关系：即是否同行、同列或是同一对角线。

        private int count;

        public int totalNQueens(int n) {
            if (n == 0) {
                return 0;
            }
            count = 0;
            int[] pos = new int[n];//下标为⾏号，数组值为列号，记录皇后位置
            //Arrays.fill(pos,0);//数组用0填充
            recursion(n, 0, pos);//递归
            return count;
        }


        // n是皇后个数，row是棋盘行数，数组pos记录皇后出现的位置，下标为行号，数组值为列号。
        // 因为递归是以行号为迭代参数，循环以列数为循环参数，实现对矩阵的遍历
        private void recursion(int n, int row, int[] pos) {//递归查找皇后种类
            if (row == n) {//完成全部⾏都选择了位置
                count++;
                return;
            }

            for (int col = 0; col < n; col++) {//遍历所有列
                if (isValid(pos, row, col)) {//检查该位置是否符合条件
                    pos[row] = col;//位置合法则加⼊位置
                    recursion(n, row + 1, pos);//递归继续查找
                }
            }
        }


        //判断皇后位置是否符合条件，以行为遍历指标
        //数组pos记录皇后出现的位置，下标为行号，数组值为列号。
        private boolean isValid(int[] pos, int row, int col) {
            //检查待插入位置和已经插入位置是否冲突，即同⾏同列同⼀斜线
            for (int i = 0; i < row; i++) {//遍历所有已经记录的⾏
                //row==i 同行，col==pos[i] 同列，Math.abs(row-i)==Math.abs(col-pos[i]) 同斜线
                //正方形同斜线那么斜率是±1。
                if (row == i || col == pos[i] || Math.abs(row - i) == Math.abs(col - pos[i])) {
                    return false;
                }
            }
            return true;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
