/**
 * <p>如果一个二进制字符串，是以一些 <code>0</code>（可能没有 <code>0</code>）后面跟着一些 <code>1</code>（也可能没有 <code>1</code>）的形式组成的，那么该字符串是
 * <strong>单调递增 </strong>的。</p>
 *
 * <p>给你一个二进制字符串 <code>s</code>，你可以将任何 <code>0</code> 翻转为 <code>1</code> 或者将 <code>1</code> 翻转为 <code>0</code> 。</p>
 *
 * <p>返回使 <code>s</code> 单调递增的最小翻转次数。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "00110"
 * <strong>输出：</strong>1
 * <strong>解释：</strong>翻转最后一位得到 00111.
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "010110"
 * <strong>输出：</strong>2
 * <strong>解释：</strong>翻转得到 011111，或者是 000111。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "00011000"
 * <strong>输出：</strong>2
 * <strong>解释：</strong>翻转得到 00000000。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>s[i]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 279</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 926
 * 将字符串翻转到单调递增
 * @author wangweizhou
 * @date 2022-08-16 07:11:43
 */

public class FlipStringToMonotoneIncreasing {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new FlipStringToMonotoneIncreasing().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //	一次翻转字符串中的一个字符，翻转字符串需要多个步骤。针对每个字符都有两个选择，即选择翻转该字符或不翻转该字符。
        //	完成一件事情需要多个步骤并且每个步骤都有多个选择，计算符合要求的最少翻转次数，也就是求最优解，因此动态规划更适合解决这个问题。

        //  在某一步有多个选择，可以使用多个状态方程来表示不同的状态。
        //	应用动态规划解决问题总是从分析状态转移方程开始的。如果一个只包含'0'和'1'的字符串S的长度为i+1，它的字符的下标范围为0～i。
        //	状态假设：在翻转下标为i的字符时假设它的前i个字符都已经按照规则翻转完毕，所有的字符'0'都位于'1'的前面。

        // 如果前i【0，i-1】个字符在翻转某些'0'和'1'之后得到的符合要求的字符串的最后一个字符是'0'，那么无论下标为i的字符是'0'还是'1'，这i+1个字符组成的字符串都是符合要求的。
        // 如果前i【0，i-1】个字符在翻转某些'0'和'1'之后得到的符合要求的字符串的最后一个字符是'1'，那么必须保证下标为i的字符是'1'，这样才能确保这i+1个字符组成的字符串是符合要求的。

        // 由于翻转下标为i的字符依赖于前i【0，i-1】个字符翻转之后最后一个字符是'0'还是'1'，因此要分为两种情况讨论。
        // 假设函数f（i）表示把字符串中从下标为0的字符到下标为i的字符（记为S[0..i]，字符串中前i+1个字符组成的子字符串）变成符合要求的字符串并且最后一个字符【下标是i】是'0'所需要的最少翻转次数。
        // 假设函数g（i）表示把字符串中S[0..i]变成符合要求的字符串并且最后一个字符是'1'所需要的最少翻转次数。
        // 如果字符串的长度是n，那么f（n-1）和g（n-1）就是翻转整个字符串使字符串符合要求并且最后一个字符分别变成'0'和'1'的最少翻转次数，它们的最小值就是整个问题的解。


        // 当输入字符串中下标为i的字符（即S[i]）是'0'时，f（i）=f（i-1），因为这一步不需要翻转；g（i）=min[f（i-1），g（i-1）]+1，因为要把S[i]从'0'翻转成'1'；
        // 当输入字符串中下标为i的字符是'1'时，f（i）=f（i-1）+1，因为要把S[i]翻转成'0';当S[i]是'1'时，此时不需要翻转字符，因此g（i）=min[f（i-1），g（i-1）]。

        // 当i等于0时，f（0）和g（0）的值取决于下标为0的字符S[0]。
        // 如果S[0]为'0'，那么f（0）的值为0；g（0）的值为1；
        // 如果S[0]为'1'，那么f（0）的值为1;g（0）的值为0。



        // 解法1：动态规划 写法1    每当已有字符串中新加入一个字符时，要根据要求看是否需要反转该字符
        public int minFlipsMonoIncr(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int len = s.length();
            int[] dp0 = new int[len];    // 假设函数f（i）表示把字符串S[0..i]变成符合要求的字符串并且使得最后一个字符S[i]是'0'所需要的最少翻转次数。
            int[] dp1 = new int[len];    // 假设函数g（i）表示把字符串S[0..i]变成符合要求的字符串并且使得最后一个字符S[i]是'1'所需要的最少翻转次数。
            char[] charArr = s.toCharArray();
            // 初始状态
            if (charArr[0] == '0') {
                dp0[0] = 0;
                dp1[0] = 1;
            } else if (charArr[0] == '1') {
                dp0[0] = 1;
                dp1[0] = 0;
            }
            for (int i = 1; i < len; i++) {
                if (charArr[i] == '0') {
                    dp0[i] = dp0[i - 1];// 当输入字符串中下标为i的字符（即S[i]）是'0'时，这一步只需要在f（i-1）后面再添加一位S[i]就可以，不需要翻转。
                    // f（i）=f（i-1）；无法在g（i-1）的基础上进行操作。
                    dp1[i] = Math.min(dp0[i - 1], dp1[i - 1]) + 1;// g（i）=min[f（i-1），g（i-1）]+1，因为要把S[i]从'0'翻转成'1',反转一次；
                } else if (charArr[i] == '1') {
                    dp0[i] = dp0[i - 1] + 1;    // 当输入字符串中下标为i的字符（即S[i]）是'1'时，f（i）=f（i-1）+1，因为要把S[i]翻转成'0',反转一次;
                    dp1[i] = Math.min(dp0[i - 1], dp1[i - 1]);// 当S[i]是'1'时，此时不需要翻转字符，因此g（i）=min[f（i-1），g（i-1）]。
                }
            }
            return Math.min(dp0[len - 1], dp1[len - 1]);
        }




        //// 解法1：动态规划+动态数组 写法2
        //public int minFlipsMonoIncr(String s) {
        //	if(s==null||s.length()==0){
        //		return  0;
        //	}
        //	int len=s.length();
        //	int[] dp0=new int[2];
        //	int[] dp1=new int[2];
        //	char[] charArr=s.toCharArray();
        //	if(charArr[0]=='0'){
        //		dp0[0]=0;
        //		dp1[0]=1;
        //	}else if(charArr[0]=='1'){
        //		dp0[0]=1;
        //		dp1[0]=0;
        //	}
        //
        //	for (int i = 1; i < len; i++) {
        //		if(charArr[i]=='0'){
        //			dp0[i%2]=dp0[(i-1)%2];
        //			dp1[i%2]=Math.min(dp0[(i-1)%2],dp1[(i-1)%2])+1;
        //		}else if(charArr[i]=='1'){
        //			dp0[i%2]=dp0[(i-1)%2]+1;
        //			dp1[i%2]=Math.min(dp0[(i-1)%2],dp1[(i-1)%2]);
        //		}
        //	}
        //	return Math.min(dp0[(len-1)%2],dp1[(len-1)%2]);
        //}




        //// 解法1：动态规划 写法1   将前面的两个一维数组合并成二维数组
        //public int minFlipsMonoIncr(String s) {
        //	if (s == null || s.length() == 0) {
        //		return 0;
        //	}
        //	int len = s.length();
        //	// dp数组第一维表示符合要求的字符串的最后一个字符是'0'还是'1',
        //	// 第二维表示把字符串中S[0..i]变成符合要求的字符串所需要的最少翻转次数
        //	// dp[0][i]表示把字符串S[0..i]变成符合要求的字符串并且使得最后一个字符S[i]是'0'所需要的最少翻转次数。
        //	// dp[1][i]表示把字符串S[0..i]变成符合要求的字符串并且使得最后一个字符S[i]是'1'所需要的最少翻转次数。
        //	int[][] dp=new int[2][len];
        //	char[] charArr=s.toCharArray();
        //	if(charArr[0]=='0'){// 当新加入的字符原来是‘0’时，变成符合要求的字符串可能需要反转该字符
        //		dp[0][0]=0;
        //		dp[1][0]=1;
        //	}else if(charArr[0]=='1'){// 当新加入的字符原来是‘1’时，变成符合要求的字符串可能需要反转该字符
        //		dp[0][0]=1;
        //		dp[1][0]=0;
        //	}
        //	for (int i = 1; i <len ; i++) {
        //		if(charArr[i]=='0'){
        //			dp[0][i]=dp[0][i-1];
        //			dp[1][i]=Math.min(dp[1][i-1],dp[0][i-1])+1;
        //		}else if(charArr[i]=='1'){
        //			dp[0][i]=dp[0][i-1]+1;
        //			dp[1][i]=Math.min(dp[1][i-1],dp[0][i-1]);
        //		}
        //	}
        //	return Math.min(dp[0][len-1],dp[1][len-1]);
        //}





        //// 解法2：动态规划+动态数组 写法2   将前面的两个一维数组合并成二维数组
        //public int minFlipsMonoIncr(String s) {
        //	if(s==null||s.length()==0){
        //		return  0;
        //	}
        //	int len=s.length();
        //	// 将前面解法的两个数组合并成一个二维数组，dp[0][i]就是前面的dp0[i]。dp[1][i]就是前面的dp1[i]。
        //	int[][] dp=new int[2][2];
        //	char[] charArr=s.toCharArray();
        //	dp[0][0]=charArr[0]=='0'?0:1;
        //	dp[1][0]=charArr[0]=='1'?0:1;
        //
        //	//if(charArr[0]=='0'){// 上面的三目运算符是这里if语句的简化
        //	//	dp[0][0]=0;
        //	//	dp[1][0]=1;
        //	//}else if(charArr[0]=='1'){
        //	//	dp[0][0]=1;
        //	//	dp[1][0]=0;
        //	//}
        //
        //	for (int i = 1; i < len; i++) {
        //
        //		if(charArr[i]=='0'){
        //			dp[0][i%2]=dp[0][(i-1)%2];
        //			dp[1][i%2]=Math.min(dp[1][(i-1)%2],dp[0][(i-1)%2])+1;
        //		}else if(charArr[i]=='1'){
        //			dp[0][i%2]=dp[0][(i-1)%2]+1;
        //			dp[1][i%2]=Math.min(dp[0][(i-1)%2],dp[1][(i-1)%2]);
        //		}
        //	}
        //	return Math.min(dp[0][(len-1)%2],dp[1][(len-1)%2]);
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
