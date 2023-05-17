/**
 * <p>一个机器人位于一个 <code>m x n</code><em>&nbsp;</em>网格的左上角 （起始点在下图中标记为 &ldquo;Start&rdquo; ）。</p>
 *
 * <p>机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 &ldquo;Finish&rdquo; ）。</p>
 *
 * <p>问总共有多少条不同的路径？</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png" /></p>
 *
 * <pre>
 * <strong>输入：</strong>m = 3, n = 7
 * <strong>输出：</strong>28</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>m = 3, n = 2
 * <strong>输出：</strong>3
 * <strong>解释：</strong>
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -&gt; 向下 -&gt; 向下
 * 2. 向下 -&gt; 向下 -&gt; 向右
 * 3. 向下 -&gt; 向右 -&gt; 向下
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>m = 7, n = 3
 * <strong>输出：</strong>28
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>m = 3, n = 3
 * <strong>输出：</strong>6</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= m, n &lt;= 100</code></li>
 * <li>题目数据保证答案小于等于 <code>2 * 10<sup>9</sup></code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><meta charset="UTF-8" />注意：本题与主站 62&nbsp;题相同：&nbsp;
 * <a href="https://leetcode-cn.com/problems/unique-paths/">https://leetcode-cn.com/problems/unique-paths/</a></p>
 * <div><div>Related Topics</div><div><li>数学</li><li>动态规划</li><li>组合数学</li></div></div><br><div><li>👍 23</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;


/**
 * 剑指 Offer II 098
 * 路径的数目
 * @author wangweizhou
 * @date 2022-08-05 15:54:47
 */
public class TwoAoeFn {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new TwoAoeFn().new Solution();

       //int ans= solution.uniquePaths(3,2);
       // System.out.println(ans);



    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


	/*

	//⽅法⼀：递归
    public int uniquePaths(int m, int n) {

		if(m==1||n==1){//矩阵只要有⼀条边为1，路径数就只有⼀种了
			return 1;
		}
		return uniquePaths(m-1,n)+uniquePaths(m,n-1);//两个分支
    }

	*/



        //	⽅法⼆：动态规划
        public int uniquePaths(int m, int n) {

            int[][] dp=new int[m+1][n+1];//dp[i][j]表示⼤⼩为i*j的矩阵的路径数量
            for (int i = 1; i <m+1 ; i++) {
                for (int j = 1; j <n+1 ; j++) {
                    if(i==1){//只有1⾏的时候，只有⼀种路径
                        dp[i][j]=1;
                        continue;
                    }
                    if(j==1){//只有1列的时候，只有⼀种路径
                        dp[i][j]=1;
                        continue;
                    }
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];//路径数等于左⽅格⼦的路径数加上上⽅格⼦的路径数
                }
            }
            return dp[m][n];
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
