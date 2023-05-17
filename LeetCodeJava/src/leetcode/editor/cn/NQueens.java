/**
 * <p>按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。</p>
 *
 * <p><strong>n&nbsp;皇后问题</strong> 研究的是如何将 <code>n</code>&nbsp;个皇后放置在 <code>n×n</code> 的棋盘上，并且使皇后彼此之间不能相互攻击。</p>
 *
 * <p>给你一个整数 <code>n</code> ，返回所有不同的&nbsp;<strong>n<em>&nbsp;</em>皇后问题</strong> 的解决方案。</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p>每一种解法包含一个不同的&nbsp;<strong>n 皇后问题</strong> 的棋子放置方案，该方案中 <code>'Q'</code> 和 <code>'.'</code> 分别代表了皇后和空位。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/queens.jpg" style="width: 600px; height: 268px;" />
 * <pre>
 * <strong>输入：</strong>n = 4
 * <strong>输出：</strong>[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * <strong>解释：</strong>如上图所示，4 皇后问题存在两个不同的解法。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1
 * <strong>输出：</strong>[["Q"]]
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
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 1437</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 51
 * N 皇后
 *
 * @author wangweizhou
 * @date 2022-08-02 16:22:27
 */

public class NQueens {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new NQueens().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法⼀: 回溯法  没看懂，只看代码，注释有的有问题
        public List<List<String>> solveNQueens(int n) {

            List<List<String>> lists = new ArrayList<>(); // 使用一个动态数组lists保存不同的棋子放置方案
            if(n==0){//判空
                return lists;
            }
            List<Integer> currentQueue = new ArrayList<>();// 动态数组currentQueue用来存储皇后的放置方案
            backtrack(lists, currentQueue, n);
            return lists;
        }



        //回溯法
        private void backtrack(List<List<String>> lists, List<Integer> currentQueue, int n) {
            // 当前皇后的个数是否等于 n 了，等于的话就加到结果中
            if (currentQueue.size() == n) {
                List<String> temp = new ArrayList<>();
                //遍历棋盘的每一行或者每一列，
                for (int i = 0; i < n; i++) {
                    char[] rowBoard = new char[n];//棋盘的每一行或者每一列
                    Arrays.fill(rowBoard, '.');//棋盘的每一行或者每一列全部填上‘.’
                    rowBoard[currentQueue.get(i)] = 'Q';
                    temp.add(new String(rowBoard));
                }
                lists.add(temp);
                return;
            }


            //   尝试每一列
            for (int col = 0; col < n; col++) {
                if (!currentQueue.contains(col)) {//当前列是否冲突
                    if (isDiagalAttack(currentQueue, col)) {//判断对⻆线是否冲突
                        continue;
                    }
                    currentQueue.add(col);//将当前列的皇后加⼊
                    //去考虑下⼀⾏的情况
                    backtrack(lists, currentQueue, n);
                    //将当前列的皇后移除，去判断下⼀列
                    //进⼊这⼀步就是两种情况，下边的⾏⾛不通了回到这⾥或者就是已经拿到了⼀个解回到这⾥
                    currentQueue.remove(currentQueue.size() - 1);
                }
            }
        }



        private boolean isDiagalAttack(List<Integer> currentQuQueen, int col) {
            int currentRow = currentQuQueen.size();
            int currentColumn = col;
            //判断每⼀⾏的皇后的情况
            for (int row = 0; row < currentQuQueen.size(); row++) {
                //左上⻆的对⻆线和右上⻆的对⻆线，差要么相等，要么互为相反数，直接写成了绝对值
                if (Math.abs(currentRow - row) == Math.abs(currentColumn - currentQuQueen.get(row))) {
                    return true;
                }
            }
            return false;
        }




      /*
        //
        //  没看懂
        public List<List<String>> solveNQueens(int n) {
            //定义一个返回结果的集合
            List<List<String>> res = new ArrayList<>();
            //定义一个存储皇后的集合
            int[] queens = new int[n];
            //填充数组queens[]中的每个元素都是-1
            //queens={-1,-1,-1...-1}
            Arrays.fill(queens, -1);
            //定义一个变量，来记录当前元素所在的列，并将他所在的列标记为不可放元素
            Set<Integer> columns = new HashSet<>();
            //定义一个变量，来记录当前元素所在的左对角线，并将他所在的左对角线标记为不可放元素
            Set<Integer> diagonals1= new HashSet<>();
            //定义一个变量，来纪律当前元素所在的右对角线，并将他所在的右对角线标记为不可放元素
            Set<Integer> diagonals2 = new HashSet<>();
            //深度优先搜索方法
            dfs(res, queens, n, 0, columns,diagonals1,diagonals2);
            return res;
        }

        public void dfs(List<List<String>> res, int[] queens,int n,int row,  Set<Integer> columns,Set<Integer>
        diagonals1,Set<Integer> diagonals2){

            //如果当前遍历到最后一行，就说明存在一个解法
            //所以将皇后的位置，存放入结果中
            if(row == n){
                //用来将当前的N行N列中的元素所在的位置结果，转换格式
                List<String> board = generateBoard(queens, n);
                //将符合条件的结果添加进返回结果集中
                res.add(board);

            }else{
                //遍历所有行
                for(int i = 0; i < n; i++){
                    //用来标记，当前行元素所在的列，都不可放元素
                    if(columns.contains(i)){
                        continue;
                    }
                    //去除左对角线上的所有元素
                    //row 表示行，i表示列
                    int diagonal1 = row-i;
                    if(diagonals1.contains(diagonal1)){
                        continue;
                    }
                    //去除右对角线上的元素
                    int diagonal2 = row + i;
                    if(diagonals2.contains(diagonal2)){
                        continue;
                    }
                    //经过上面的三次排除，就可以找到元素在当前行的哪一列的位置。
                    //选第一行的第几列，也可以叫单元格所在的位置
                    queens[row] = i;
                    //把选中的单元格加入到，去除列的集合中
                    //用来给下一行的元素所在的列作为排除条件判断
                    columns.add(i);
                    //把选中的单元格加入到，去除左对角线的集合中
                    diagonals1.add(diagonal1);
                    //把选中的单元格加入到，去除右对角线的集合中
                    diagonals2.add(diagonal2);
                    //递归遍历下一行，
                    dfs(res,queens,n,row+1,columns,diagonals1,diagonals2);
                    //剪枝操作
                    queens[row] = -1;
                    //将当前列和左对角线和右对角线的元素都删除，避免重复遍历
                    columns.remove(i);
                    diagonals1.remove(diagonal1);
                    diagonals2.remove(diagonal2);
                }
            }
        }


        //转换格式
        public List<String> generateBoard(int[] queens,int n){
            //定义一个结果集，用于返回结果
            List<String> board = new ArrayList<>();
            //遍历所有行
            for(int i = 0; i < n; i++){
                char[] row = new char[n];
                Arrays.fill(row, '.');
                //将当前行所在的列的，位置置为Q
                row[queens[i]] = 'Q';
                //将当前结果添加进结果集中
                board.add(new String(row));
            }
            return board;
        }
*/



          /*

        // 回溯法+二维数组
        public List<List<String>> solveNQueens(int n) {
            // 首先创建存放结果的List
            // 棋盘的每一行都是一个List
            List<List<String>> results = new LinkedList<>();

            // 初始化棋盘
            //static void fill(char[] a, char val) 将指定的char值分配给指定的chars数组的每个元素。
            char[][] map = new char[n][n];
            for (char[] chars : map) {
                Arrays.fill(chars, '.');
            }

            // 以行开始进行判断，当然以列也可以
            int row = 0;
            // 深度优先搜索
            dfs(map, row, results);

            results.forEach(System.out :: println);
            return results;
        }


        public void dfs(char[][] map, int row, List<List<String>> results){
            int length = map.length;

            // 终止条件,如果到达最后一行，判断结束
            if(row == length){
                List<String> path = new LinkedList<>();
                for (char[] chars : map) {
                    path.add(String.valueOf(chars));
                }
                results.add(path);
                return;
            }

            // 选择过程
            for (int col = 0; col < length; col++) {
                // 判断棋盘上当前位置是否合法
                if(!isValid(map, row, col)){
                    continue;
                }
                // 如果合法，做出选择，判断下一行可能的摆放情况
                map[row][col] = 'Q';
                // 回溯
                dfs(map, row + 1, results);
                // 撤销选择
                map[row][col] = '.';
            }
        }


        private boolean isValid(char[][] chessMap, int row, int col) {
            int length = chessMap.length;
            // 判断当前行、列的位置是否合法
            for (char[] rowList : chessMap) {
                // 如果其他行的该列上已经放了皇后，则不能再放，说明当前位置不合法
                if (rowList[col] == 'Q') {
                    return false;
                }
            }

            // 判断右上对角线情况，行减列加
            for (int i = row - 1, j = col + 1; i >= 0 && j < length; i--, j++) {
                // 如果对角线上已经放了，该位置不合法
                if (chessMap[i][j] == 'Q'){
                    return false;
                }
            }
            // 检查左上对角线，行减列减
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                // 如果对角线上已经放了，该位置不合法
                if (chessMap[i][j] == 'Q'){
                    return false;
                }
            }
            // 如果该列的所有行和所有对角线上都没有放，则说明为合法位置
            return true;

        }
*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
