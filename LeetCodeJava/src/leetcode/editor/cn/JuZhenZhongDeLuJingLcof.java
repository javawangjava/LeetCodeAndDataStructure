/**
<p>给定一个&nbsp;<code>m x n</code> 二维字符网格&nbsp;<code>board</code> 和一个字符串单词&nbsp;<code>word</code> 。如果&nbsp;<code>word</code> 存在于网格中，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。</p>

<p>&nbsp;</p>

<p>例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。</p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word2.jpg" style="width: 322px; height: 242px;" /></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>board = [["a","b"],["c","d"]], word = "abcd"
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n = board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 6</code></li>
	<li><code>1 &lt;= word.length &lt;= 15</code></li>
	<li><code>board </code>和<code> word </code>仅由大小写英文字母组成</li>
</ul>

<p><strong>注意：</strong>本题与主站 79 题相同：<a href="https://leetcode-cn.com/problems/word-search/">https://leetcode-cn.com/problems/word-search/</a></p>
<div><div>Related Topics</div><div><li>数组</li><li>回溯</li><li>矩阵</li></div></div><br><div><li>👍 682</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 12
 * 矩阵中的路径
 * @author wangweizhou
 * @date 2022-09-20 23:00:18
 */

public class JuZhenZhongDeLuJingLcof{

	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new JuZhenZhongDeLuJingLcof().new Solution();
		 char[][] board={{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		 boolean bool=solution.exist(board,"ABCCED");
		 System.out.println(bool);

	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


	//	解法1： 写法2  深度遍历+回溯
	//	 board 和 word 仅由大小写英文字母组成
	public boolean exist(char[][] board, String word) {
		if(board==null||board.length==0||board[0].length==0||word==null){// 判空
			return false;
		}
		char[] words = word.toCharArray();// 字符串转换成数组，字符串的底层charAt()其实就是将字符串转换成字符数组
		// 遍历图  遍历二维数组的起始位置
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				// 如果每次从二维数组的新的一个起点开始遍历，则从目标数组的第一个字符开始遍历
				if(dfs(board,  i, j, words,0)) {
					return true;
				}
			}
		}
		//所有开始位置都遍历完了，没找到，返回false
		return false;
	}


	// k是传入字符串当前索引，如果当前字符串索引和图当前索引对应的值不相等，表示第一个数就不相等,所以继续找第一个相等的数。
	// 如果board[i][j] == word[k]，则表明当前找到了对应的数，就继续执行（标记找过，继续dfs 下上右左）

	//深度优先搜索    i，j是当前所在格子，idx是当前遍历到word的第几个字符了


	// board[row][col]是二维数组当前遍历的位置， word[k]是目标字符
	private boolean dfs(char[][] board, int row, int col, char[] word, int k) {
		// 行或列索引越界，当前矩阵元素与目标字符不同
		if(row >= board.length || row < 0 || col >= board[0].length || col < 0 || board[row][col] != word[k]) {
			return false;
		}
		if(k == word.length - 1){// 目标字符数组的每个字符都找到了
			return true;
		}

		// //标记已访问 :访问过的位置设置为空字符，因为目标字符串只由大小写字母组成，所以肯定和空字符不一样
		board[row][col] = '\0';// 做标记是为了防止标记字符与矩阵原有字符重复，空字符肯定和目标字符串中的字符不相等

		// 遍历当前位置的上下左右来寻找下一个目标字符   顺序是上下左右
		boolean res = dfs(board,row - 1, col,  word, k + 1)
				|| dfs(board,  row + 1, col,  word, k + 1)
				|| dfs(board,  row, col - 1,  word, k + 1)
				|| dfs(board, row, col + 1,  word, k + 1);

		// 前面是递归实现的，如果前面找到了，那么就正常结束了，执行到这里只会是前面没找到回溯到上一个节点，那么就要将上一个节点还原
		board[row][col] = word[k];// //回溯，修改回来，表示未访问过
		return res;
	}



	/*

	// 解法1： dfs+回溯
	public boolean exist(char[][] board, String word) {
		if(board==null||board.length==0||board[0].length==0||word==null){// 判空
			return false;
		}
		int rowLen=board.length;// 行数
		int colLen=board[0].length;
		char[] words=word.toCharArray();
		boolean[][] isVisted=new boolean[rowLen][colLen];// 标记数组标记是否访问过二维矩阵对应的元素
		for (int row = 0; row < rowLen; row++) {
			for (int col = 0; col < colLen; col++) {
				// 如果每次从二维数组的新的一个起点开始遍历，则从目标数组的第一个字符开始遍历
				if(dfs(board,isVisted,row,col,words,0)){
					return true;
				}
			}
		}
		return false;
	}


	// 深度遍历二维数组
	// 递归结束条件： 返回 false ： (1) 行或列索引越界 或 (2) 当前矩阵元素与目标字符不同 或 (3) 当前矩阵元素已访问过 （ (3) 可合并至 (2) ） 。
	// 返回 true ： k = len(word) - 1 ，即字符串 word 已全部匹配。

	private boolean dfs(char[][] board,boolean[][] isVisted,int row,int col,char[] words, int k ){
		if(board[row][col]!=words[k]){//
			// 当前矩阵元素和当前的目标字符不符
			return false;
		}else if(k==words.length-1){// 已经遍历完了整个目标字符串
			return true;
		}
		boolean result=false;
		isVisted[row][col]=true;// 标记当前元素已经访问过了
		int[][] directions={{-1,0},{1,0},{0,-1},{0,1}};// 四个方向 二维数组的每个元素表示一个方向。上下左右
		// 遍历当前位置的四个方向
		for(int[] dir:directions){
			int newRow=row+dir[0],newCol=col+dir[1];// 可能的下一个方向
			// 首先要判段可能的下一个新位置是否越界，新位置的元素是否访问过了
			if(newRow>=0&&newRow<board.length&&newCol>=0&&newCol<board[0].length&&!isVisted[newRow][newCol]){// 行列没有越界
				if(dfs(board,isVisted,newRow,newCol,words,k+1)){
					result=true;
					break;
				}
			}
		}
		isVisted[row][col]=false;// 当前路径没有找到所包含的单词，回溯返回上一个节点，将当前节点设置为未访问
		return result;
	}

	*/



	/*

	// 解法1： dfs+回溯
	public boolean exist(char[][] board, String word) {
		if(board==null||board.length==0||board[0].length==0||word==null){// 判空
			return false;
		}
		int rowLen=board.length;// 行数
		int colLen=board[0].length;
		boolean[][] isVisted=new boolean[rowLen][colLen];// 标记数组标记是否访问过二维矩阵对应的元素
		for (int row = 0; row < rowLen; row++) {
			for (int col = 0; col < colLen; col++) {
				if(dfs(board,isVisted,row,col,word,0)){
					return true;
				}
			}
		}
		return false;
	}


	// 深度遍历二维数组
	// 递归结束条件： 返回 false ： (1) 行或列索引越界 或 (2) 当前矩阵元素与目标字符不同 或 (3) 当前矩阵元素已访问过 （ (3) 可合并至 (2) ） 。
	// 返回 true ： k = len(word) - 1 ，即字符串 word 已全部匹配。

	private boolean dfs(char[][] board,boolean[][] isVisted,int row,int col,String str, int k ){
		if(board[row][col]!=str.charAt(k)){// 当前矩阵元素和当前的目标字符不符
			return false;
		}else if(k==str.length()-1){// 已经遍历完了整个目标字符串
			return true;
		}
		isVisted[row][col]=true;// 标记当前元素已经访问过了
		int[][] directions={{-1,0},{1,0},{0,-1},{0,1}};// 四个方向 二维数组的每个元素表示一个方向。上下左右
		boolean result=false;
		// 遍历当前位置的四个方向
		for(int[] dir:directions){
			int newRow=row+dir[0],newCol=col+dir[1];
			if(newRow>=0&&newRow<board.length&&newCol>=0&&newCol<board[0].length){// 行列没有越界
				if(!isVisted[newRow][newCol]){
					if(dfs(board,isVisted,newRow,newCol,str,k+1)){
						result=true;
						break;
					}
				}
			}
		}
		isVisted[row][col]=false;// 当前路径没有找到所包含的单词，回溯返回上一个节点，将当前节点设置为未访问
		return result;
	}

	*/


}
//leetcode submit region end(Prohibit modification and deletion)

}
