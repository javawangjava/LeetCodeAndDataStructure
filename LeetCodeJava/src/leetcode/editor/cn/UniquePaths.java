/**
 * <p>一个机器人位于一个 <code>m x n</code><em> </em>网格的左上角 （起始点在下图中标记为 “Start” ）。</p>
 *
 * <p>机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。</p>
 *
 * <p>问总共有多少条不同的路径？</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img src="https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png" />
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
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
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
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= m, n <= 100</code></li>
 * <li>题目数据保证答案小于等于 <code>2 * 10<sup>9</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>动态规划</li><li>组合数学</li></div></div><br><div><li>👍
 * 1541</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 62
 * 不同路径
 * @author wangweizhou
 * @date 2022-09-10 16:01:53
 */

public class UniquePaths {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new UniquePaths().new Solution();
        int ans = solution.uniquePaths(3, 2);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //	应用动态规划解决问题的关键在于找出状态转移方程。
        //	可以用函数f（i，j）表示从格子的左上角坐标为（0，0）的位置出发到达坐标为（i，j）的位置的路径的数目。如果格子的大小为m×n，那么f（m-1，n-1）就是问题的解。
        //	当i等于0时，机器人位于格子最上面的一行，机器人不可能从某个位置向下走一步到达一个行号i等于0的位置。
        //	因此，f（0，j）等于1，即机器人只有一种方法可以到达坐标为（0，j）的位置，即从（0，j-1）的位置向右走一步。
        //	当j等于0时，机器人位于格子最左边的一列，机器人不可能从某个位置向右走一步到达一个列号j为0的位置。
        //	因此，f（i，0）等于1，即机器人只有一种方法可以到达坐标为（i，0）的位置，即从（i-1，0）的位置向下走一步。
        //	当行号i、列号j都大于0时，机器人有两种方法可以到达坐标为（i，j）的位置。
        //	它既可以从坐标为（i-1，j）的位置向下走一步，也可以从坐标为（i，j-1）的位置向右走一步，因此，f（i，j）等于f（i-1，j）与f（i，j-1）之和。


        //	解法2：动态规划
        //  dp[i][j] 表示从[0,0]到[i-1,j-1]的不同路径，不同路径拐一次弯就算不同路径。
        public int uniquePaths(int m, int n) {
            if (m <= 0 || n <= 0) {
                return -1;
            }
            int[][] dp = new int[m][n];
            for (int i = 0; i < m; i++) {
                //当j等于0时，机器人位于格子最左边的一列，f（i，0）等于1，即机器人只有一种方法可以到达坐标为（i，0）的位置，即从（i-1，0）的位置向下走一步。
                dp[i][0] = 1;// 第一列一直向下。只有一直向右一条路径
            }
            for (int j = 0; j < n; j++) {
                //当i等于0时，机器人位于格子最上面的一行，f（0，j）等于1，即机器人只有一种方法可以到达坐标为（0，j）的位置，即从（0，j-1）的位置向右走一步。
                dp[0][j] = 1;//第一行一直向右。只有一直向下一条路径
            }

            //	当行号i、列号j都大于0时，机器人有两种方法可以到达坐标为（i，j）的位置。它既可以从坐标为（i-1，j）的位置向下走一步，也可以从坐标为（i，j-1）的位置向右走一步，因此，f（i，j）等于f（i-1
			//	，j）与f（i，j-1）之和。
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];// 状态转移方程
                }
            }
            return dp[m - 1][n - 1];
        }





        ////	解法2：动态规划+动态数组
        ////  dp[i][j] 表示从[0,0]到[i-1,j-1]的不同路径，不同路径拐一次弯就算不同路径。
        //// 在计算f（i，j）时只需要用到 f（i-1，j）和f（i，j-1）的值，因此只需要保存标号分别为i-1和i 的两行就可以。
        //// 如果创建一个只有两行的二维数组dp，将f（i，j）保存在“dp[i%2][j]”中，那么就将空间复杂度优化到O（n）。
        //
        //public int uniquePaths(int m, int n) {
        //	if (m <= 0 || n <= 0) {
        //		return -1;
        //	}
        //	int[][] dp=new int[2][n];
        //	dp[0][0]=1;
        //
        //	for (int j = 0; j < n; j++) {
        //		//当i等于0时，机器人位于格子最上面的一行，f（0，j）等于1，即机器人只有一种方法可以到达坐标为（0，j）的位置，即从（0，j-1）的位置向右走一步。
        //		dp[0][j]= 1;//第一行一直向右
        //	}
        //
        //	//	当行号i、列号j都大于0时，机器人有两种方法可以到达坐标为（i，j）的位置。它既可以从坐标为（i-1，j）的位置向下走一步，也可以从坐标为（i，j-1）的位置向右走一步，因此，f（i，j）等于f（i-1
		//	，j）与f（i，j-1）之和。
        //	for (int i = 1; i < m; i++) {
        //		dp[i%2][0]=1;// 当j等于0时，机器人位于格子最左边的一列，f（i，0）等于1，即机器人只有一种方法可以到达坐标为（i，0）的位置，即从（i-1，0）的位置向下走一步。
        //		for (int j =1; j < n; j++) {
        //			dp[i%2][j]=dp[(i-1)%2][j]+dp[i%2][j-1];// 状态转移方程
        //		}
        //	}
        //	return dp[(m-1)%2][n-1];
        //}



        //// 解法2：动态规划+优化空间
        //// 只需要创建一个一维数组dp就可以。在计算f（i，j）时需要用到f（i-1，j）和f（i，j-1）的值。
        //// 接下来在计算f（i，j+1）时需要用到f（i-1，j+1）和f（i，j）的值。
        //// 在计算完f（i，j）之后，就不再需要f（i-1，j）的值。
        //// 在二维表格中，f（i，j）和f（i-1，j）是上下相邻的两个位置。
        //// 由于在用f（i-1，j）计算出f（i，j）之后就不再需要f（i-1，j），因此可以只用一个位置来保存f（i-1，j）和f（i，j）的值。
        //// 这个位置在计算f（i，j）之前保存的是f（i-1，j）的值，计算f（i，j）之后保存的是f（i，j）的值。
        //// 由于每个位置能够用来保存两个值，因此只需要一个一维数组就能保存表格中的两行。
		//
        //// 代码中的dp是一个一维数组，f（i-1，j）和f（i，j）都保存在“dp[j]”中。
        //// 仍然用一个二重循环按照状态转移方程计算，循环体内的“dp[j]+=dp[j-1]”可以看成“dp[j]=dp[j]+dp[j-1]”。
        //// 在赋值运算符的右边，“dp[j]”中保存的是f（i-1，j），“dp[j-1]”中保存的是f（i，j-1）。
        //// 在计算f（i，j）之前，按照从左到右的顺序f（i，j-1）的值已经计算出来并保存在“dp[j-1]”中。
        //// 用f（i-1，j）和f（i，j-1）的值计算出f（i，j）之后将结果保存到“dp[j]”中。
        //// 虽然之前保存在“dp[j]”中的f（i-1，j）的值被覆盖了，但由于这个值不再需要，因此覆盖这个值并不会出现任何问题。
		//
		//
        //public int uniquePaths(int m, int n) {
        //    if (m <= 0 || n <= 0) {
        //        return -1;
        //    }
        //    int[] dp = new int[n];
        //    Arrays.fill(dp, 1);
		//
		//	// 代码中的dp是一个一维数组，f（i-1，j）和f（i，j）都保存在“dp[j]”中。
		//	// 仍然用一个二重循环按照状态转移方程计算，循环体内的“dp[j]+=dp[j-1]”可以看成“dp[j]=dp[j]+dp[j-1]”。
		//	// 在赋值运算符的右边，“dp[j]”中保存的是f（i-1，j），“dp[j-1]”中保存的是f（i，j-1）。
		//	// 在计算f（i，j）之前，按照从左到右的顺序f（i，j-1）的值已经计算出来并保存在“dp[j-1]”中。
		//	// 用f（i-1，j）和f（i，j-1）的值计算出f（i，j）之后将结果保存到“dp[j]”中。
		//
        //    for (int i = 1; i < m; i++) {
        //        for (int j = 1; j < n; j++) {
        //            //dp[j]+=dp[j-1];// 状态转移方程  f（i-1，j）和f（i，j）都保存在“dp[j]”中。
        //            dp[j] = dp[j] + dp[j - 1];// 状态转移方程
        //        }
        //    }
        //    return dp[n - 1];
        //}



	/*

	public int uniquePaths(int m, int n) {
		int[][] dp=new int[m][n];
		//dp[i][j]表示从(0,0)走到(i,j)的总路线数(只允许向右或向下走)
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(i*j==0)dp[i][j]=1;
				else dp[i][j]=dp[i-1][j]+dp[i][j-1];
			}
		}
		return dp[m-1][n-1];
	}

	*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
