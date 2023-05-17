/**
 * <p>把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p>你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre><strong>输入:</strong> 1
 * <strong>输出:</strong> [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * </pre>
 *
 * <p><strong>示例&nbsp;2:</strong></p>
 *
 * <pre><strong>输入:</strong> 2
 * <strong>输出:</strong> [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <p><code>1 &lt;= n &lt;= 11</code></p>
 *
 * <div><div>Related Topics</div><div><li>数学</li><li>动态规划</li><li>概率与统计</li></div></div><br><div><li>👍
 * 503</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 60
 * n个骰子的点数
 *
 * @author wangweizhou
 * @date 2022-12-27 23:58:51
 */
public class NgeTouZiDeDianShuLcof {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new NgeTouZiDeDianShuLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：动态规划
        // 首先用数组的第一维来表示阶段，也就是投掷完了几枚骰子。然后用第二维来表示投掷完这些骰子后，可能出现的点数。数组的值就表示，该阶段各个点数出现的次数。
        // 状态定义：dp[i][j]：表示投掷完 i 枚骰子后，点数 j 的出现次数。

        public double[] dicesProbability(int n) {
            if (n < 1) {
                return new double[0];
            }
            // n个骰子最大点数为6n。
            int[][] dp = new int[n + 1][6 * n + 1];// 数组下标是从0开始的，java的基本数据类型的默认值是0。
            for (int j = 1; j <= 6; j++) {// 初始状态，第一个骰子的点数
                dp[1][j] = 1;
            }

            for (int i = 2; i <= n; i++) {// i 表示已经掷完的骰子个数
                for (int j = i; j <= 6 * i; j++) {// j 表示掷完 i 枚骰子后的总点数。当前一共 i 个骰子，那么最大的点数和是 6 * i。
                    // 单单看第 i 枚骰子，它的点数可能为 1,2,3,...,6 ，因此投掷完 i 枚骰子后点数 j 出现的次数，
                    // 可以由投掷完 n−1 枚骰子后，对应点数 j−1,j−2,j−3,...,j−6 出现的次数之和转化过来。
                    // 因为目前已经有了前（i-1）枚骰子，则前（i-1）个骰子总点数最少是（i-1）；
                    // 当掷完第i个骰子后，点数要到达j，若curr为最大值，则只能是前（i-1）个骰子点数为1，curr<=j-(i-1)。
                    for (int curr = 1; curr <= 6 && curr <= j+1-i ; curr++) {
                        dp[i][j] += dp[i - 1][j - curr];
                    }
                }
            }

            // n个骰子都是1时，则点数和有最小值n; n个骰子都是6时，则点数和最大值为6n。
            double[] ans = new double[6 * n - n + 1];//
            for (int i = n; i <= 6 * n; i++) {
                ans[i - n] = dp[n][i] / Math.pow(6, n);// 注意java中浮点型是取近似值。
            }
            return ans;
        }




        //// 解法2：动态规划+动态数组优化空间
        //public double[] dicesProbability(int n) {
        //	//因为最后的结果只与前一个动态转移数组有关，所以这里只需要设置一个一维的动态转移数组
        //	//原本dp[i][j]表示的是前i个骰子的点数之和为j的概率，现在只需要最后的状态的数组，所以就只用一个一维数组dp[j]表示n个骰子下每个结果的概率。
        //	//初始是1个骰子情况下的点数之和情况，就只有6个结果，所以用dp的初始化的size是6个
        //	double[] dp = new double[6];
        //	//只有一个数组
        //	Arrays.fill(dp,1.0/6.0);
        //	//从第2个骰子开始，这里n表示n个骰子，先从第二个的情况算起，然后再逐步求3个、4个···n个的情况
        //	//i表示当总共i个骰子时的结果
        //	for(int i=2;i<=n;i++){
        //		//每次的点数之和范围会有点变化，点数之和的值最大是i*6，最小是i*1，i之前的结果值是不会出现的；
        //		//比如i=3个骰子时，最小就是3了，不可能是2和1，所以点数之和的值的个数是6*i-(i-1)，化简：5*i+1
        //		//当有i个骰子时的点数之和的值数组先假定是temp
        //		double[] temp = new double[5*i+1];
        //		//从i-1个骰子的点数之和的值数组入手，计算i个骰子的点数之和数组的值
        //		//先拿i-1个骰子的点数之和数组的第j个值，它所影响的是i个骰子时的temp[j+k]的值
        //		for(int j=0;j<dp.length;j++){
        //			//比如只有1个骰子时，dp[1]是代表当骰子点数之和为2时的概率，它会对当有2个骰子时的点数之和为3、4、5、6、7、8产生影响，因为当有一个骰子的值为2时，另一个骰子的值可以为1~6
        //			，产生的点数之和相应的就是3~8；比如dp[2]代表点数之和为3，它会对有2个骰子时的点数之和为4、5、6、7、8、9产生影响；所以k在这里就是对应着第i
        //			个骰子出现时可能出现六种情况，这里可能画一个K神那样的动态规划逆推的图就好理解很多
        //			for(int k=0;k<6;k++){
        //				//这里记得是加上dp数组值与1/6的乘积，1/6是第i个骰子投出某个值的概率
        //				temp[j+k]+=dp[j]*(1.0/6.0);
        //			}
        //		}
        //		//i个骰子的点数之和全都算出来后，要将temp数组移交给dp数组，dp数组就会代表i个骰子时的可能出现的点数之和的概率；用于计算i+1个骰子时的点数之和的概率
        //		dp = temp;
        //	}
        //	return dp;
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
