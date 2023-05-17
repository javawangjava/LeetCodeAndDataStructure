/**
 * <p>给你一根长度为 <code>n</code> 的绳子，请把绳子剪成整数长度的 <code>m</code> 段（m、n都是整数，n&gt;1并且m&gt;1），每段绳子的长度记为 <code>k[0],k[1]..
 * .k[m-1]</code> 。请问 <code>k[0]*k[1]*...*k[m-1]</code> 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入: </strong>2
 * <strong>输出: </strong>1
 * <strong>解释: </strong>2 = 1 + 1, 1 &times; 1 = 1</pre>
 *
 * <p><strong>示例&nbsp;2:</strong></p>
 *
 * <pre><strong>输入: </strong>10
 * <strong>输出: </strong>36
 * <strong>解释: </strong>10 = 3 + 3 + 4, 3 &times;&nbsp;3 &times;&nbsp;4 = 36</pre>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= n &lt;= 58</code></li>
 * </ul>
 *
 * <p>注意：本题与主站 343 题相同：
 * <a href="https://leetcode-cn.com/problems/integer-break/">https://leetcode-cn.com/problems/integer-break/</a></p>
 * <div><div>Related Topics</div><div><li>数学</li><li>动态规划</li></div></div><br><div><li>👍 507</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 14- I
 * 剪绳子
 * @author wangweizhou
 * @date 2022-09-21 14:58:16
 */
public class JianShengZiLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new JianShengZiLcof().new Solution();
        int ans = solution.cuttingRope(0);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法2:根据题意至少要分成两段
        // 动态规划:
        // 状态定义：dp[i] 表示将正整数 i 拆分成若干个正整数的和之后，这些正整数的最大乘积。
        // 状态转移方程：dp[i] = max(dp[j]* dp[i-j]);
        public int cuttingRope(int n) {
            // 注意这里长度小于3的绳子单独处理了
            if(n<2){// 不能剪短，认定为0
                return 0;
            }
            if(n==2){// 长度为2，只能剪成长度为1的两段
                return 1;
            }
            if(n==3){// 长度为3，剪成长度为1和2的两段乘积大
                return 2;
            }

            // dp[i] 表示将正整数 i 拆分成若干个正整数的和之后，这些正整数的最大乘积。
            int[] dp = new int[n + 1];
            // 注意这里初始状态的定义，前面已经单独处理了长度小于等于3的绳子。
            // 那么后面整数大于等于4，注意长度小于3的绳子没有必要剪开。也就是绳子长度剪到出现1，2，3的时候就不能再继续向下剪了，再往下剪乘积会变小。
            // 所以这里的dp[1]，dp[2]，dp[3]表示从长度大于等于4的整数中分出长度为1，2，3的分段后就没必要再分段了。
            dp[1]=1;
            dp[2]=2;
            dp[3]=3;
            for (int i = 4; i <= n; i++) {
                //for (int j = 1; j <= i; j++) { // 剪得第一段长度超过i/2时，就是对称的了，没必要在遍历。;例如将10剪成（4，6）和（6，4）结果应该是一样的。
                for (int j = 1; j <= i/2; j++) {
                    // 当i固定时，剪下的第一段长度j（1，2，3，...,）变化时，那么就有一个对应的dp[i]，则需要在i固定时，则需要在所有的dp[i]中选取最大的dp[i]。
                    dp[i] = Math.max(dp[i], dp[j]* dp[i-j]);
                }
            }
            return dp[n];
        }


        /*

        // 解法1：动态规划：
        // dp[i] 表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积。
        // 0 不是正整数，1 是最小的正整数，0 和 1 都不能拆分，因此 dp[0]=dp[1]=0。

        // 当i>=2时，假设对正整数i拆分出来的第一个正整数是j(1<=j<i),则有以下两种方案：
        // 如果只剪掉长度为1，对最后的乘积无任何增益，所以从长度为2开始剪。剪了第一段后，剩下(i - j)长度可以剪也可以不剪。
        // 1) 将i拆分成j和i-j的和，且i-j不再拆分成多个正整数，此时的乘积是j*(i-j);
        // 2) 将i拆分成j和i-j的和，且i-j继续分成多个正整数，此时的乘积是j*dp[i-j]。
        // 当j固定时，有dp[i]=max(j * (i - j), j * dp[i- j])；
        // 由于j的范围是1到(i-1),所以需要遍历所有的j得到dp[i]的最大值，
        // 因此可以得到状态转移方程：dp[i] = max(max(j * (i - j), j * dp[i - j]))
        // 程序中这样写：dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j]))

        public int cuttingRope(int n) {
            int[] dp = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                //for (int j = 1; j <= i; j++) { // 剪得第一段长度超过i/2时，就是对称的了，没必要在遍历。;例如将10剪成（4，6）和（6，4）结果应该是一样的。
                for (int j = 1; j <= i/2; j++) {
                    // 剪了第一段后，剩下(i - j)长度可以剪也可以不剪。如果不剪的话长度乘积即为j * (i - j)；如果剪的话长度乘积即为j * dp[i - j]。取两者最大值
                    // Math.max(j * (i - j), j * dp[i - j])表示长度为i的绳子，将j固定时减掉长度为j的一段后，剩下（i-j）的长度拆分或者不拆分的最大值
                    // 当i固定时，剪下的第一段长度j（1，2，3，...,）变化时，那么就有一个对应的dp[i]，则需要在i固定时，则需要在所有的dp[i]中选取最大的dp[i]。
                    int curr= Math.max(j * (i - j), j * dp[i - j]);
                    dp[i] = Math.max(dp[i],curr);
                    //dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
                }
            }
            return dp[n];
        }

        */






/*
	// 数学推导/贪心算法
    public int cuttingRope(int n) {
		if(n<2){
			return 0;
		}
		if(n==2){// 长难为2，只能剪成长度为1的两段
			return 1;
		}
		if(n==3){// 长度为3，剪成长度为1和2的两段乘积大
			return 2;
		}

        // 剩余长度为0，1，2时，则不需要再剪短了。
		int quotient = n / 3;// 尽可能多的剪成长度为3的绳子段数
		int remainder = n % 3;// 剪完长度为3的绳子之后剩余的绳子段数
		if (remainder == 0) {// 将 n 拆分成 quotient 个 33；
			return (int) Math.pow(3, quotient);
		} else if (remainder == 1) {// 一段长度为4的，那么长度为3的段数少1。若最后一段绳子长度为 1 ；则应把一份 3+1 替换为 2+2，因为2×2>3×1。
			return (int) Math.pow(3, quotient - 1) * 4;
		} else {// 余数为2，则保留，不再拆为 1+1 。
			return (int) Math.pow(3, quotient) * 2;
		}
    }

    */

    }
//leetcode submit region end(Prohibit modification and deletion)

}
