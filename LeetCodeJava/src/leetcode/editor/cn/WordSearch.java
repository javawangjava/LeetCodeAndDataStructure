/**
 * <p>给定一个 <code>m x n</code> 二维字符网格 <code>board</code> 和一个字符串单词 <code>word</code> 。如果 <code>word</code> 存在于网格中，返回
 * <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p>单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word2.jpg" style="width: 322px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word-1.jpg" style="width: 322px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/15/word3.jpg" style="width: 322px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == board.length</code></li>
 * <li><code>n = board[i].length</code></li>
 * <li><code>1 <= m, n <= 6</code></li>
 * <li><code>1 <= word.length <= 15</code></li>
 * <li><code>board</code> 和 <code>word</code> 仅由大小写英文字母组成</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>你可以使用搜索剪枝的技术来优化解决方案，使其在 <code>board</code> 更大的情况下可以更快解决问题？</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li><li>矩阵</li></div></div><br><div><li>👍 1437</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 79
 * 单词搜索
 *
 * @author wangweizhou
 * @date 2022-09-15 00:24:54
 */


public class WordSearch {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new WordSearch().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // java 中string.charAt()底层就是将字符串转换为字符数组然后在使用数组下标定位。所以可以先将字符串转换为字符数组然后再用数组下标定位。

        // 这是一个可以用回测法解决的典型题。
        // 首先，在矩阵中任选一个格子作为路径的起点。
        // 当矩阵中坐标为(row,col)的格子board[row][col]和路径字符串中下标为k的字符str.charAt(k)不一样时，那么这个格子不可能处在路径上的第i个位置。
        // 当矩阵中坐标为(row,col)的格子board[row][col]和路径字符串中下标为k的字符str.charAt(k)一样时，从4个相邻的格子(row,col-1)、(row-1,col)、(row,
        // col+1)和(row+1,col)中去定位路径字符串中下标为k+1的字符。

        // 如果4个相邻的格子都没有匹配字符串中下标为k+1的字符，则表明当前路径字符串中下标为k的字符在矩阵中的定位不正确，我们需要回到前一个字符(k-1),然后重新定位。
        // 一直重复这个过程，直到路径字符串上的所有字符都在矩阵中找到合适的位置(此时k=word.length)。
        // 由于路径不能重复进入矩阵的格子，所以还需要定义和字符矩阵大小一样的布尔值矩阵，用来标识路径是否已经进入了每个格子。



        // 解法1： dfs+回溯  写法1：利用原二维数组特点左标记数组
        public boolean exist(char[][] board, String word) {
            if (board == null || board.length == 0 || board[0].length == 0 || word == null) {// 判空
                return false;
            }
            char[] words = word.toCharArray();// 字符串转换成数组，字符串的底层charAt()其实就是将字符串转换成字符数组
            // 遍历图  遍历二维数组的起始位置
            int rowLen = board.length;// 二维数组行数
            int colLen = board[0].length;// 二维数组列数
            // 因为只需要找出矩阵中的一个路径和字符串的字符顺序对应，所以可以遍历矩阵的每一个位置作为开始节点来对应目标字符串的第一个字符
            for (int row = 0; row < rowLen; row++) {
                for (int col = 0; col < colLen; col++) {
                    if (dfs(board, row, col, words, 0)) {// 注意这里只能是字符串的第一个位置开始，即k=0；
                        return true;
                    }
                }
            }
            //所有开始位置都遍历完了，没找到，返回false
            return false;
        }


        // 递归实现深度优先搜索
        // 判断从矩阵board[row][col]位置开始的路径是否和str.charAt(k)位置开始的子字符串是否一一对应
        // board是原二维数组，isVisited是与原二维数组对应的标记数组，row是二维矩阵的行，col是二维矩阵的列。
        // word是字符数组，k是字符数组的第k的元素下标。
        // board[row][col]是二维数组当前遍历的位置， word[k]是目标字符

        private boolean dfs(char[][] board, int row, int col, char[] word, int k) {
            if (k == word.length) {// 数组下标k已经越界，表明已经遍历完了目标字符串word,也就是二维数组中有目标字符串word对应的路径
                return true;
            }
            boolean hasPath = false;// 表示截至二维数组的当前位置board[row][col]是否有字符数组[0,k]对应的路径
            // 当前位置board[row][col]的下标没有越界，当前位置没有访问过【不能重复】，并且board[row][col] == word[k]
            if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
                if (board[row][col] == word[k]) {
                    //标记已访问 :访问过的位置设置为空字符，因为目标字符串只由大小写字母组成，所以肯定和空字符不一样
                    board[row][col] = '\0';
                    // 递归遍历当前位置的前后左右四个方向
                    hasPath = dfs(board, row - 1, col, word, k + 1)
                            || dfs(board, row + 1, col, word, k + 1)
                            || dfs(board, row, col - 1, word, k + 1)
                            || dfs(board, row, col + 1, word, k + 1);

                    // 前面是递归实现的，如果前面找到了，那么方法就正常结束了，执行到这里只会是前面没找到回溯到上一个节点，那么就要将上一个节点还原
                    if (!hasPath) {// 如果当前位置的四个方向都没有路径，则回溯，清除这一层的改变。
                        // 因为这一层没有对word的下标k做任何改变，所以k不需要改变。标记数组恢复原状态
                        //k--;// 注意Java的基本类型数据的传递只是将保存的内容传递一次
                        board[row][col] = word[k];
                    }
                }
            }
            return hasPath;
        }



        //// 解法1： dfs+回溯  写法2：利用原二维数组特点左标记数组。细节和写法1有一点不同。
        //public boolean exist(char[][] board, String word) {
        //    if (board == null || board.length == 0 || board[0].length == 0 || word == null) {// 判空
        //        return false;
        //    }
        //    char[] words = word.toCharArray();// 字符串转换成数组，字符串的底层charAt()其实就是将字符串转换成字符数组
        //    // 遍历图  遍历二维数组的起始位置
        //    int rowLen = board.length;// 二维数组行数
        //    int colLen = board[0].length;// 二维数组列数
        //    // 因为只需要找出矩阵中的一个路径和字符串的字符顺序对应，所以可以遍历矩阵的每一个位置作为开始节点来对应目标字符串的第一个字符
        //    for (int row = 0; row < rowLen; row++) {
        //        for (int col = 0; col < colLen; col++) {
        //            if (dfs(board, row, col, words, 0)) {// 注意这里只能是字符串的第一个位置开始，即k=0；
        //                return true;
        //            }
        //        }
        //    }
        //    //所有开始位置都遍历完了，没找到，返回false
        //    return false;
        //}
        //
        //
        //// 递归实现深度优先搜索
        //// 判断从矩阵board[row][col]位置开始的路径是否和str.charAt(k)位置开始的子字符串是否一一对应
        //// board是原二维数组，isVisited是与原二维数组对应的标记数组，row是二维矩阵的行，col是二维矩阵的列。
        //// word是字符数组，k是字符数组的第k的元素下标。
        //// board[row][col]是二维数组当前遍历的位置， word[k]是目标字符
        //
        //private boolean dfs(char[][] board, int row, int col, char[] word, int k) {
        //    // 剪枝：行或列索引越界，当前矩阵元素与目标字符不同
        //    if (row >= board.length || row < 0 || col >= board[0].length || col < 0 || board[row][col] != word[k]) {
        //        return false;
        //    }
        //    // 执行完上面没有结束方法，就表明二维数组行列没有越界，当前矩阵元素与目标字符相同
        //    // 因为当前矩阵元素与目标字符相同，那么字符数组的下标也就不能越界。遍历字符数组的下标在字符数组的最后一个下标处截至
        //    if (k == word.length - 1) {// 这一行表明字符数组的最后一个字符和二维数组中的某一个字符相等
        //        return true;
        //    }
        //    //标记已访问 :访问过的位置设置为空字符，因为目标字符串只由大小写字母组成，所以肯定和空字符不一样
        //    board[row][col] = '\0';
        //    // 递归遍历当前位置的前后左右四个方向
        //    boolean hasPath = dfs(board, row - 1, col, word, k + 1)
        //            || dfs(board, row + 1, col, word, k + 1)
        //            || dfs(board, row, col - 1, word, k + 1)
        //            || dfs(board, row, col + 1, word, k + 1);
        //
        //    // 前面是递归实现的，如果前面找到了路径，那么方法就一直返回true正常结束了，不需要回溯。
        //    // 如果没找到，那么hasPath就是false,需要回溯到上一个节点，那么就要将上一个节点还原
        //    if (!hasPath) {// 如果当前位置的四个方向都没有路径，则回溯，清除这一层的改变。
        //        // 因为这一层没有对word的下标k做任何改变，所以k不需要改变。标记数组恢复原状态
        //        //k--;// 注意Java的基本类型数据的传递只是将保存的内容传递一次
        //        board[row][col] = word[k];
        //    }
        //    return hasPath;
        //}







        //// 解法2：dfs+回溯  写法1：利用和元素组一样大小的标记数组来标记已经访问过的位置
        //public boolean exist(char[][] board, String word) {
        //    if (board == null || board.length == 0 || board[0].length == 0 || word == null) {// 判空
        //        return false;
        //    }
        //    char[] words = word.toCharArray();// 字符串转换成数组，字符串的底层charAt()其实就是将字符串转换成字符数组
        //    // 遍历图  遍历二维数组的起始位置
        //    int rowLen = board.length;// 二维数组行数
        //    int colLen = board[0].length;// 二维数组列数
        //    boolean[][] isVisited = new boolean[rowLen][colLen];// 创建一个和原数组大小一样的标记数组，标记数组用来标记是否访问过二维矩阵对应位置的元素
        //    // 因为只需要找出矩阵中的一个路径和字符串的字符顺序对应，所以可以遍历矩阵的每一个位置作为开始节点来对应目标字符串的第一个字符
        //    for (int row = 0; row < rowLen; row++) {
        //        for (int col = 0; col < colLen; col++) {
        //            if (dfs(board, isVisited, row, col, words, 0)) {// 注意这里只能是字符串的第一个位置开始，即k=0；
        //                return true;
        //            }
        //        }
        //    }
        //    //所有开始位置都遍历完了，没找到，返回false
        //    return false;
        //}
        //
        //
        //// 递归实现深度优先搜索
        //// 判断从矩阵board[row][col]位置开始的路径是否和str.charAt(k)位置开始的子字符串是否一一对应
        //// board是原二维数组，isVisited是与原二维数组对应的标记数组，row是二维矩阵的行，col是二维矩阵的列。
        //// word是字符数组，k是字符数组的第k的元素下标。
        //// board[row][col]是二维数组当前遍历的位置， word[k]是目标字符
        //
        //private boolean dfs(char[][] board, boolean[][] isVisited, int row, int col, char[] word, int k) {
        //    if (k == word.length) {// 数组下标k已经越界，表明已经遍历完了目标字符串word,也就是二维数组中有目标字符串word对应的路径
        //        return true;
        //    }
        //    boolean hasPath = false;// 表示截至二维数组的当前位置board[row][col]是否有字符数组[0,k]对应的路径
        //    // 当前位置board[row][col]的下标没有越界，当前位置没有访问过【不能重复】，并且board[row][col] == word[k]
        //    if (row >= 0 && row < board.length && col >= 0 && col < board[0].length ) {// 要访问二维数组的元素首先要检查二维数组的下标没有越界
        //        if(board[row][col] == word[k] && !isVisited[row][col]){// 二维数组的当前位置的元素等于字符数组对应的元素，且二维数组的当前位置元素没有访问过
        //            isVisited[row][col] = true;// 二维数组的当前位置已经访问
        //            // 递归遍历当前位置的前后左右四个方向
        //            hasPath = dfs(board, isVisited, row - 1, col, word, k + 1)
        //                    || dfs(board, isVisited, row + 1, col, word, k + 1)
        //                    || dfs(board, isVisited, row, col - 1, word, k + 1)
        //                    || dfs(board, isVisited, row, col + 1, word, k + 1);
        //            if (!hasPath) {// 如果当前位置的四个方向都没有路径，则回溯，清除这一层的改变。
        //                // 因为这一层没有对word的下标k做任何改变，所以k不需要改变。标记数组恢复原状态
        //                //k--;// 注意Java的基本类型数据的传递只是将保存的内容传递一次
        //                isVisited[row][col] = false;
        //            }
        //        }
        //    }
        //    return hasPath;
        //}





        // 注意解法4的三种写法的深度递归方法 方法定义相同

        //// 解法4： dfs+回溯+利用循环遍历四个方向  写法1：利用和元素组一样大小的标记数组来标记已经访问过的位置,
        //public boolean exist(char[][] board, String word) {
        //    if (board == null || board.length == 0 || board[0].length == 0 || word == null) {// 判空
        //        return false;
        //    }
        //    int rowLen = board.length;// 行数
        //    int colLen = board[0].length;
        //    boolean[][] isVisted = new boolean[rowLen][colLen];// 创建一个和原数组大小一样的标记数组，标记数组用来标记是否访问过二维矩阵对应位置的元素
        //    // 因为只需要找出矩阵中的一个路径和字符串的字符顺序对应，所以可以遍历矩阵的每一个位置作为开始节点来对应目标字符串的第一个字符
        //    for (int row = 0; row < rowLen; row++) {
        //        for (int col = 0; col < colLen; col++) {
        //            if (dfs(board, isVisted, row, col, word, 0)) {
        //                return true;
        //            }
        //        }
        //    }
        //    return false;
        //}

        //// 深度遍历二维数组
        //// 这个还会判断第一次是否越界
        //// 判断从矩阵board[row][col]位置开始的路径是否和str.charAt(k)位置开始的子字符串是否一一对应
        //private boolean dfs(char[][] board, boolean[][] isVisited, int row, int col, String str, int k) {
        //    if (k == str.length()) {// 已经遍历完了整个目标字符串,目标字符数组的每个字符都找到了
        //        return true;
        //    }
        //    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};// 四个方向 二维数组的每个元素表示一个方向。上下左右
        //    boolean hasPath = false;// 表示截至二维数组的当前位置board[row][col]是否有字符数组[0,k]对应的路径
        // // 当前位置board[row][col]的下标没有越界，当前位置没有访问过【不能重复】，并且board[row][col] == word[k]
        //    if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {// 要访问二维数组的元素首先要检查二维数组的下标没有越界
        //        if (board[row][col] == str.charAt(k) && !isVisited[row][col])
        //        {//二维数组的当前位置的元素等于字符数组对应的元素，且二维数组的当前位置元素没有访问过
        //            //标记已访问 :访问过的位置设置为空字符，因为目标字符串只由大小写字母组成，所以肯定和空字符不一样
        //            isVisited[row][col] = true;// 标记当前元素已经访问过了
        //            // 递归遍历当前位置的前后左右四个方向
        //            // 注意将这个循环遍历四个方向和前面或连接的四个方向对比，
        //            // “或”连接的四个方向若其中一个方向符合要求，则就进入下一层进行递归遍历，所以循环遍历的时候当某个方向符合要求那么就停止遍历其他方向，并且要沿着符合要求的方向继续深入
        //            for (int[] dir : directions) {
        //                int newRow = row + dir[0];// 新位置的行
        //                int newCol = col + dir[1];// 新位置的列
        //                // 循环遍历的时候当某个方向符合要求那么就停止遍历其他方向，并且要沿着符合要求的方向继续深入
        //                if (dfs(board, isVisited, newRow, newCol, str, k + 1)) {
        //                    hasPath = true;
        //                    break;
        //                }
        //            }
        //            // 前面是递归实现的，如果前面找到了，那么方法就正常结束了，执行到这里只会是前面没找到回溯到上一个节点，那么就要将上一个节点还原
        //            if (!hasPath) {// 如果当前位置的四个方向都没有路径，则回溯，清除这一层的改变。
        //                // 因为这一层没有对word的下标k做任何改变，所以k不需要改变。标记数组恢复原状态
        //                isVisited[row][col] = false;// 当前路径没有找到所包含的单词，回溯返回上一个节点，将当前节点设置为未访问
        //            }
        //        }
        //    }
        //    return hasPath;
        //}




        ////// 解法4： dfs+回溯+利用循环遍历四个方向  写法2：利用和元素组一样大小的标记数组来标记已经访问过的位置,
        //// 写法1和2的区别就是剪枝【：行或列索引越界，当前矩阵元素与目标字符不同。】处理的位置不同。
        //// 深度遍历二维数组
        //// 判断从矩阵board[row][col]位置开始的路径是否和str.charAt(k)位置开始的子字符串是否一一对应
        //private boolean dfs(char[][] board, boolean[][] isVisted, int row, int col, String str, int k) {
        //
        //    // 剪枝：行或列索引越界，当前矩阵元素与目标字符不同
        //    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != str.charAt(k)
        //            || isVisted[row][col]) {
        //        return false;
        //    }
        //    // 执行完上面没有结束方法，就表明二维数组行列没有越界，当前位置没有访问过，当前矩阵元素与目标字符相同
        //    // 因为当前矩阵元素与目标字符相同，那么字符数组的下标也就不能越界。遍历字符数组的下标在字符数组的最后一个下标处截至
        //    if (k == str.length() - 1) {// 这一行表明字符数组的最后一个字符和二维数组中的某一个字符相等
        //        return true;
        //    }
        //
        //    isVisted[row][col] = true;// 标记当前元素已经访问过了
        //    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};// 四个方向 二维数组的每个元素表示一个方向。上下左右
        //    boolean hasPath = false;
        //    // 遍历当前位置的四个方向
        //    for (int[] dir : directions) {
        //        int newRow = row + dir[0];// 新位置的行
        //        int newCol = col + dir[1];// 新位置的列
        //        if (dfs(board, isVisted, newRow, newCol, str, k + 1)) {
        //            hasPath = true;
        //            break;
        //        }
        //    }
        //    isVisted[row][col] = false;// 当前路径没有找到所包含的单词，回溯返回上一个节点，将当前节点设置为未访问
        //    return hasPath;
        //}





        //// 解法4： dfs+回溯+利用循环遍历四个方向  写法3：利用和元素组一样大小的标记数组来标记已经访问过的位置,
        //// 在第一次调用该方法的时候，肯定没有越界所以这里第一次进入该方法的时候不需要判断越界，在后面进入下一层的时候再判断。
        //// 深度遍历二维数组
        //// 判断从矩阵board[row][col]位置开始的路径是否和str.charAt(k)位置开始的子字符串是否一一对应
        //private boolean dfs(char[][] board,boolean[][] isVisted,int row,int col,String str, int k ){
        //    // 首先判断矩阵当前位置board[row][col]和字符串的对应位置board[row][col]是否相同，
        //    if(board[row][col]!=str.charAt(k)){
        //        // 当前矩阵元素和当前的目标字符不符
        //        return false;
        //    }else if(k==str.length()-1){// 已经遍历完了整个目标字符串,目标字符数组的每个字符都找到了
        //        return true;
        //    }
        //    isVisted[row][col]=true;// 标记当前元素已经访问过了
        //    int[][] directions={{-1,0},{1,0},{0,-1},{0,1}};// 四个方向 二维数组的每个元素表示一个方向。上下左右
        //    boolean hasPath =false;
        //    // 遍历当前位置的四个方向
        //    for(int[] dir:directions){
        //        int newRow=row+dir[0];// 新位置的行
        //        int newCol=col+dir[1];// 新位置的列
        //        if(newRow>=0&&newRow<board.length&&newCol>=0&&newCol<board[0].length){// 行列没有越界
        //            if(!isVisted[newRow][newCol]&&board[newRow][newCol]==str.charAt(k+1)){
        //                // 循环遍历的时候当某个方向符合要求那么就停止遍历其他方向，并且要沿着符合要求的方向继续深入
        //                if(dfs(board,isVisted,newRow,newCol,str,k+1)){
        //                    hasPath =true;
        //                    break;
        //                }
        //            }
        //        }
        //    }
        //
        //    // 如果前面遍历完了目标字符串word找到了路径，那么一直就是递归返回的true。不会回溯。
        //    // 如果前面遍历完某个节点[row][col]的四个相邻的格子，四个相邻的格子都没有匹配字符串中下标为(k+1)的字符，
        //    // 则表明当前路径中[row][col]坐标不应该在路径中，需要回溯，将位置[row][col]设置为未访问
        //    isVisted[row][col]=false;// 当前路径没有找到所包含的单词，回溯返回上一个节点，将当前节点设置为未访问
        //    return hasPath;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
