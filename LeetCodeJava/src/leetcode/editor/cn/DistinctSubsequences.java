/**
 * <p>给定一个字符串 <code>s</code><strong> </strong>和一个字符串 <code>t</code> ，计算在 <code>s</code> 的子序列中 <code>t</code> 出现的个数。</p>
 *
 * <p>字符串的一个 <strong>子序列</strong> 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，<code>"ACE"</code> 是
 * <code>"ABCDE"</code> 的一个子序列，而 <code>"AEC"</code> 不是）</p>
 *
 * <p>题目数据保证答案符合 32 位带符号整数范围。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "rabbbit", t = "rabbit"<code>
 * <strong>输出</strong></code><strong>：</strong><code>3
 * </code><strong>解释：</strong>
 * 如下图所示, 有 3 种可以从 s 中得到 <code>"rabbit" 的方案</code>。
 * <code><strong><u>rabb</u></strong>b<strong><u>it</u></strong></code>
 * <code><strong><u>ra</u></strong>b<strong><u>bbit</u></strong></code>
 * <code><strong><u>rab</u></strong>b<strong><u>bit</u></strong></code></pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "babgbag", t = "bag"
 * <code><strong>输出</strong></code><strong>：</strong><code>5
 * </code><strong>解释：</strong>
 * 如下图所示, 有 5 种可以从 s 中得到 <code>"bag" 的方案</code>。
 * <code><strong><u>ba</u></strong>b<u><strong>g</strong></u>bag</code>
 * <code><strong><u>ba</u></strong>bgba<strong><u>g</u></strong></code>
 * <code><u><strong>b</strong></u>abgb<strong><u>ag</u></strong></code>
 * <code>ba<u><strong>b</strong></u>gb<u><strong>ag</strong></u></code>
 * <code>babg<strong><u>bag</u></strong></code>
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= s.length, t.length <= 1000</code></li>
 * <li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 862</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 115
 * 不同的子序列
 * @author wangweizhou
 * @date 2022-09-09 23:33:13
 */

public class DistinctSubsequences {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new DistinctSubsequences().new Solution();
        int ans = solution.numDistinct("appplep", "apple");
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 应用动态规划解决问题的关键在于找出状态转移方程。
        // 由于这个问题的输入有两个字符串，因此状态转移方程有两个参数。
        // 用f（i，j）表示字符串S下标从0到i的子字符串（记为S[0..i]）中等于字符串T下标从0到j的子字符串（记为T[0..j]）的子序列的数目。
        // 如果字符串S的长度是m，字符串T的长度是n，那么f（m-1，n-1）就是字符串S中等于字符串T的子序列的数目。
        // 当字符串S的长度小于字符串T的长度时，字符串S中不可能存在等于字符串T的子序列，所以当i小于j时f（i，j）的值都等于0。


        // 每次新添加一个元素时，如果S[i]和T[j]不相同，则只能舍去S[i]，那么S[0..i]中的子序列等于T[0..j]的数目等于S[0..i-1]中的子序列等于T[0..j]的数目，此时f（i，j）等于f（i-1，j）。
        // 如果字符串S中下标为i的字符（记为S[i]）等于字符串T中下标为j的字符（记为T[j]），那么对S[i]有两个可能做法：
        // 一个是用S[i]去匹配T[j]，那么S[0..i]中的子序列等于T[0..j]的数目等于S[0..i-1]中的子序列等于T[0..j-1]的数目；
        // 另一个是舍去S[i]，那么S[0..i]中的子序列等于T[0..j]的数目等于S[0..i-1]中的子序列等于T[0..j]的数目。
        // 因此，当S[i]等于T[j]时，f（i，j）等于f（i-1，j-1）+ f（i-1，j）。


        // 前面i>=1，j>=1。
        // 接着考虑字符串S和T为空的情形。由于f（0，j）表示S[0..0]的子序列（子字符串的长度为1）中等于T[0..j]的数目，因此f（-1，j）表示字符串S为空。同理，f（i，-1）表示字符串T为空。
        // 当字符串S、T都为空时，两个字符串匹配，因此f（-1，-1）等于1。
        // 如果字符串S为空而字符串T不为空，那么字符串S中不可能存在等于字符串T的子序列，即当j大于或等于0时f（-1，j）等于0。
        // 如果字符串S不为空而字符串T为空，那么字符串S的空子序列（舍去字符串S的所有字符）等于字符串T，即当i大于或等于0时f（i，-1）等于1。
        // 如果字符串S的长度为m，字符串T的长度为n，则上述代码中二维数组dp的行数为m+1，列数为n+1。
        // f（i，j）的值保存在“dp[i+1][j+1]”中。代码中的二重循环根据S[i]和T[j]是否相同按照状态转移方程计算f（i，j）。


        // 解法2：动态规划  在s中找t。下面程序是前s后t。  dp数组全部向右平移一个单位。
        // f（i，j）的值保存在“dp[i+1][j+1]”中。
        public int numDistinct(String s, String t) {
            if (s==null||t==null||s.length()<t.length()) {// s长度小于t时，s的子序列中不会出现t
                return 0;
            }
            int sLen = s.length();
            int tLen = t.length();
            int[][] dp = new int[sLen + 1][tLen + 1];
            // 如果字符串S为空而字符串T不为空，那么字符串S中不可能存在等于字符串T的子序列，即当j大于或等于0时f（-1，j）等于0。因为Java的int类型的默认值是0，所以不需要额外初始化。
            // dp[0][j]=0（j>0）；
            for (int j = 1; j < tLen; j++) {
                dp[0][j]=0;
            }
            // 当字符串S、T都为空时，两个字符串匹配，因此f（-1，-1）等于1。
            // 如果字符串S不为空而字符串T为空，那么字符串S的空子序列（舍去字符串S的所有字符）等于字符串T，即当i大于或等于0时f（i，-1）等于1。
            for (int i = 0; i <= sLen; i++) {// 在任意字符串中都可以找到空字符串
                dp[i][0] = 1;
            }

            for (int i = 0; i < sLen; i++) {
                // 注意在s[0,i]中找t[0.j],所以s的子串长度一定要大于等于t的子串长度。
                for (int j = 0; j <= i && j < tLen; j++) {
                    if (s.charAt(i) == t.charAt(j)) {
                        // 如果字符串S中下标为i的字符（记为S[i]）等于字符串T中下标为j的字符（记为T[j]），那么对S[i]有两个可能做法：
                        // 一个是用S[i]去匹配T[j]，那么S[0..i]中的子序列等于T[0..j]的数目等于S[0..i-1]中的子序列等于T[0..j-1]的数目；
                        // 另一个是舍去S[i]，那么S[0..i]中的子序列等于T[0..j]的数目等于S[0..i-1]中的子序列等于T[0..j]的数目。
                        // 因此，当S[i]等于T[j]时，f（i，j）等于f（i-1，j-1）+f（i-1，j）。
                        dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1];
                    } else {
                        //如果S[i]和T[j]不相同，则只能舍去S[i]，那么S[0..i]中的子序列等于T[0..j]的数目等于S[0..i-1]中的子序列等于T[0..j]的数目，此时f（i，j）等于f（i-1，j）。
                        dp[i + 1][j + 1] = dp[i][j + 1];
                    }
                }
            }
            return dp[sLen][tLen];
        }





        //// 解法2：动态规划+动态数组  在s中找t。下面程序是前s后t。
        //// 在计算f（i，j）的值时，最多只需要用到它上一行f（i-1，j-1）和f（i-1，j）的值，因此可以只保存表格中的两行。
        //// 可以创建一个只有两行的二维数组dp，列数仍然是n+1，将f（i，j）保存在“dp[（i+1）%2][j+1]”中。
        //public int numDistinct(String s, String t) {
        //	if(s.length() < t.length()) {// s长度小于t时，s中不会出现t
        //		return 0;
        //	}
        //	int sLen=s.length();
        //	int tLen=t.length();
        //	int[][] dp=new int[2][tLen+1];
        //
        //	// 如果字符串S为空而字符串T不为空，那么字符串S中不可能存在等于字符串T的子序列，即当j大于或等于0时f（-1，j）等于0。因为Java的int类型的默认值是0，所以不需要额外初始化。
        //	// 当字符串S、T都为空时，两个字符串匹配，因此f（-1，-1）等于1。
        //	// 如果字符串S不为空而字符串T为空，那么字符串S的空子序列（舍去字符串S的所有字符）等于字符串T，即当i大于或等于0时f（i，-1）等于1。
        //
        //	for (int i = 0; i <sLen ; i++) {
        //		dp[i%2][0]=1;// 在任意字符串中都可以找到空字符串
        //		// 注意在s[0,i]中找t[0.j]所以，s的子串长度一定要大于等于t的子串长度。
        //		for (int j = 0; j <=i&&j <tLen ; j++) {
        //			if(s.charAt(i)==t.charAt(j)){// 如果字符串S中下标为i的字符（记为S[i]）等于字符串T中下标为j的字符（记为T[j]）
        //				// dp[i][j]： 一个是用S[i]去匹配T[j]，那么S[0..i]中等于T[0..j]的子序列的数目等于S[0..i-1]中等于T[0..j-1]的子序列的数目；
        //				// dp[i][j+1]：另一个是舍去S[i]，那么S[0..i]中等于T[0..j]的子序列的数目等于S[0..i-1]中等于T[0..j]的子序列的数目。
        //				dp[(i+1)%2][j+1]=dp[i%2][j]+dp[i%2][j+1];
        //			}else{
        //				// 如果S[i]和T[j]不相同，则只能舍去S[i]，此时f（i，j）等于f（i-1，j）。
        //				dp[(i+1)%2][j+1]=dp[i%2][j+1];
        //			}
        //		}
        //	}
        //	return dp[sLen%2][tLen];
        //}




        // 解法1：动态规划  写法3 在s中找t。下面状态定义是前t后s。和上面细节上有一点不同
        // 状态定义：dp[i][j] 表示 T[0:i-1] 可以由 S[0:j-1] 字符串组成的最多个数，T中前i个字母。前t后s。
        // 那么转移方程就要把T中前i个字母一定要能找到，也就是要保证T中前i个字母。

        // 状态转移方程：遍历参数有点不一样
        // 因为当T[i]=S[j]，那么就在s的前（j-1）找到t的前（i-1)或者在s的前（j-1）中找到t的前（i)个。
        // 当 S[j] != T[i]，那么就只能在s的前（j-1）找到t的前（i)个。

        // 当 T[i]=S[j],那么就包含两种情况，dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
        // 当 T[i]!=S[j] , dp[i][j] = dp[i][j-1]。


        //public int numDistinct(String s, String t) {
        //	if(s.length() < t.length()) {
        //		return 0; // s长度小于t时，s中不会出现t
        //	}
        //	int sLen=s.length();
        //	int tLen=t.length();
        //
        //	int[][] dp=new int[tLen+1][sLen+1];
        //	for (int j = 0; j < sLen+1; j++) {
        //		dp[0][j]=1;
        //	}
        //	for (int i = 1; i <tLen+1 ; i++) {
        //		for (int j = 1; j <sLen+1 ; j++) {
        //
        //			if(t.charAt(i-1)==s.charAt(j-1)){
        //				dp[i][j]=dp[i-1][j-1]+dp[i][j-1];
        //			}else{
        //				dp[i][j]=dp[i][j-1];
        //			}
        //		}
        //	}
        //	return dp[tLen][sLen];
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
