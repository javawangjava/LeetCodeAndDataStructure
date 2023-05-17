/**
 * <p>给定三个字符串&nbsp;<code>s1</code>、<code>s2</code>、<code>s3</code>，请你帮忙验证&nbsp;<code>s3</code>&nbsp;是否是由&nbsp;
 * <code>s1</code>&nbsp;和&nbsp;<code>s2</code><em> </em><strong>交错 </strong>组成的。</p>
 *
 * <p>两个字符串 <code>s</code> 和 <code>t</code> <strong>交错</strong> 的定义与过程如下，其中每个字符串都会被分割成若干 <strong>非空</strong> 子字符串：</p>
 *
 * <ul>
 * <li><code>s = s<sub>1</sub> + s<sub>2</sub> + ... + s<sub>n</sub></code></li>
 * <li><code>t = t<sub>1</sub> + t<sub>2</sub> + ... + t<sub>m</sub></code></li>
 * <li><code>|n - m| &lt;= 1</code></li>
 * <li><strong>交错</strong> 是 <code>s<sub>1</sub> + t<sub>1</sub> + s<sub>2</sub> + t<sub>2</sub> + s<sub>3</sub> +
 * t<sub>3</sub> + ...</code> 或者 <code>t<sub>1</sub> + s<sub>1</sub> + t<sub>2</sub> + s<sub>2</sub> + t<sub>3</sub>
 * + s<sub>3</sub> + ...</code></li>
 * </ul>
 *
 * <p><strong>注意：</strong><code>a + b</code> 意味着字符串 <code>a</code> 和 <code>b</code> 连接。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/02/interleave.jpg" />
 * <pre>
 * <strong>输入：</strong>s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s1 = "", s2 = "", s3 = ""
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= s1.length, s2.length &lt;= 100</code></li>
 * <li><code>0 &lt;= s3.length &lt;= 200</code></li>
 * <li><code>s1</code>、<code>s2</code>、和 <code>s3</code> 都由小写英文字母组成</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>您能否仅使用 <code>O(s2.length)</code> 额外的内存空间来解决它?</p>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 799</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


/**
 * 97
 * 交错字符串
 *
 * @author wangweizhou
 * @date 2022-12-01 18:15:03
 */

public class InterleavingString {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new InterleavingString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 如果字符串s1的长度为m，字符串s2的长度为n，那么它们交织得到的字符串s3的长度一定是m+n。
        // 状态定义：可以用函数f（i，j）表示字符串s1的下标从0到i的子字符串（记为s1[0..i]，长度为i+1）
        // 和字符串s2的下标从0到j的子字符串（记为s2[0..j]，长度为j+1）能否交织得到字符串s3的下标从0到i+j+1（记为s3[0..i+j+1]，长度为i+j+2）的子字符串。
        // f（m-1，n-1）就是整个问题的解。


        // 状态转移方程：
        // 按照字符串的交织规则，字符串s3的下标为i+j+1的字符（s3[i+j+1]）既可能是来自字符串s1的下标为i的字符（s1[i]），也可能是来自字符串s2的下标为j的字符（s2[j]）。
        // 如果s3[i+j+1]和s1[i]相同，只要s1[0..i-1]和s2[0..j]能交织得到子字符串s3[i+j]，那么s1[0..i]一定能和s2[0..j]交织得到s3[0..i+j+1]。
        // 也就是说，当s3[i+j+1]和s1[i]相同时，f（i，j）的值等于f（i-1，j）的值。类似地，当s3[i+j+1]和s2[j]相同时，f（i，j）的值等于f（i，j-1）的值。
        // 如果s1[i]和s2[j]都和s3[i+j+1]相同，此时只要f（i-1，j）和f（i，j-1）有一个值为true，那么f（i，j）的值为true。
        // 由此可知，f（i，j）的值依赖于f（i-1，j）和f（i，j-1）的值。如果i等于0，那么f（0，j）的值依赖于f（-1，j）和f（0，j-1）的值。


        // 状态转移方程中的i是指字符串s1中当前处理的子字符串的最后一个字符的下标。当i等于0时，当前处理的字符串s1的子字符串中只有一个下标为0的字符。
        // 那么当i等于-1时，当前处理的字符串s1的子字符串中一个字符也没有，是空的。
        // f（-1，j）的含义是当字符串s1的子字符串是空字符串的时候，它和字符串s2从下标从0到j的子字符串（即s2[0..j]）能否交织出字符串s3中下标从0到j的子字符串（即s3[0..j]）。
        // 由于空字符和s2[0..j]交织的结果一定还是s2[0..j]，因此f（-1，j）的值其实取决于子字符串s2[0..j]和s3[0..j]是否相同。
        // 如果s2[j]和s3[j]不同，那么f（-1，j）的值为false；如果s2[j]和s3[j]相同，那么f（-1，j）的值等于f（-1，j-1）的值。
        // 类似地，f（i，-1）的含义是当字符串s2的子字符串是空字符串时，它和s1[0..i]能否交织得到s3[0..i]，因此f（i，-1）的值取决于子字符串s1[0..i]和s3[0..i]是否相同。
        // 如果s1[i]和s3[i]不同，那么f（i，-1）的值为false；如果s1[i]和s3[i]相同，那么f（i，-1）的值等于f（i-1，-1）的值。
        // 当i和j都等于-1时，f（-1，-1）的值的含义是两个空字符串能否交织得到一个空字符串。这显然是可以的，因此f（-1，-1）的值为true。

        // 注意下面程序的状态转移方程是将上面f（i，j）统一向右平移了一个单位。


        ////// 解法1：动态规划  写法1
        ////// 状态转移方程：dp[i+1][j+1]表示字符串s1[0..i]和字符串s2[0..j]能否交织得到字符串s3[0..i+j+1]
        //public boolean isInterleave(String s1, String s2, String s3) {
        //    if (s1.length() + s2.length() != s3.length()) {
        //        // 如果字符串s1的长度为m ，字符串s2的长度为n，那么它们交织得到的字符串s3的长度一定是m+n。
        //        return false;
        //    }
        //    // 状态转移方程数组下标有-1，会越界。所以数组统一向平移一个单位。
        //    boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        //    // 初始化
        //    dp[0][0] = true;// 两个空字符串能否交织得到一个空字符串。这显然是可以的，因此f（-1，-1）的值为true。
        //
        //    // dp[i][0]的含义是当字符串s2的子字符串是空字符串时，它和s1[0..i]能否交织得到s3[0..i]，
        //    // 因此dp[i][0]的值取决于子字符串s1[0..i]和s3[0..i]是否相同。
        //    for (int i = 0; i < s1.length(); i++) {
        //        if (s1.charAt(i) == s3.charAt(i)) {
        //            dp[i + 1][0] = dp[i][0];
        //        } else {
        //            dp[i + 1][0] = false;
        //        }
        //    }
        //
        //    // dp[0][j]的含义是当字符串s1的子字符串是空字符串的时候，它和字符串s2从下标从0到j的子字符串（即s2[0..j]）能否交织出字符串s3中下标从0到j的子字符串（即s3[0..j]）。
        //    for (int j = 0; j < s2.length(); j++) {
        //        if (s2.charAt(j) == s3.charAt(j)) {
        //            dp[0][j + 1] = dp[0][j];
        //        } else {
        //            dp[0][j + 1] = false;
        //        }
        //    }
        //
        //    // 按照字符串的交织规则，字符串s3的下标为i+j+1的字符（s3[i+j+1]）既可能是来自字符串s1的下标为i的字符（s1[i]），也可能是来自字符串s2的下标为j的字符（s2[j]）。
        //    for (int i = 0; i < s1.length(); i++) {
        //        for (int j = 0; j < s2.length(); j++) {
        //            char ch1 = s1.charAt(i);
        //            char ch2 = s2.charAt(j);
        //            char ch3 = s3.charAt(i + j + 1);
        //            // 当s3[i+j+1]和s1[i]相同时，f（i，j）的值等于f（i-1，j）的值。类似地，当s3[i+j+1]和s2[j]相同时，f（i，j）的值等于f（i，j-1）的值。
        //            if (ch1 == ch3 && ch2 == ch3) {
        //                // 如果s1[i]和s2[j]都和s3[i+j+1]相同，此时只要f（i-1，j）和f（i，j-1）有一个值为true，那么f（i，j）的值为true。
        //                dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j];
        //            } else if (ch1 == ch3) {
        //                // 如果s3[i+j+1]和s1[i]相同，只要s1[0..i-1]和s2[0..j]能交织得到子字符串s3[i+j]，那么s1[0..i]一定能和s2[0..j]交织得到s3[0.
        //                // .i+j+1]。
        //                // 也就是说，当s3[i+j+1]和s1[i]相同时，f（i，j）的值等于f（i-1，j）的值。
        //                dp[i + 1][j + 1] = dp[i][j + 1];
        //            } else if (ch2 == ch3) {
        //                // 当s3[i+j+1]和s2[j]相同时，f（i，j）的值等于f（i，j-1）的值。
        //                dp[i + 1][j + 1] = dp[i + 1][j];
        //            } else {// 当s3[i+j+1]和s1[i]或者s2[j]都不相同时
        //                dp[i + 1][j + 1] = false;
        //            }
        //        }
        //    }
        //    return dp[s1.length()][s2.length()];
        //}



        //// 解法1：动态规划  写法2
        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }
            int len1 = s1.length();
            int len2 = s2.length();
            boolean[][] dp = new boolean[len1 + 1][len2 + 1];
            dp[0][0] = true;
            for (int i = 1; i <= len1; i++) {
                if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                    dp[i][0] = dp[i - 1][0];
                } else {
                    dp[i][0] = false;
                }
            }

            for (int j = 1; j <= len2; j++) {
                if (s2.charAt(j - 1) == s3.charAt(j - 1)) {
                    dp[0][j] = dp[0][j - 1];
                } else {
                    dp[0][j] = false;
                }
            }

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    char ch1 = s1.charAt(i - 1);
                    char ch2 = s2.charAt(j - 1);
                    char ch3 = s3.charAt(i + j - 1);
                    if (ch1 == ch3 && ch2 == ch3) {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    } else if (ch1 == ch3) {
                        dp[i][j] = dp[i - 1][j];
                    } else if (ch2 == ch3) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
            return dp[len1][len2];
        }


        // // 解法1：动态规划  写法3
        //public boolean isInterleave(String s1, String s2, String s3) {
        //	if(s1.length()+s2.length()!=s3.length()){// 如果字符串s1的长度为m，字符串s2的长度为n，那么它们交织得到的字符串s3的长度一定是m+n。
        //		return false;
        //	}
        //	// 状态转移方程数组下标有-1，会越界。所以数组统一向平移一个单位。
        //	boolean[][] dp=new boolean[s1.length()+1][s2.length()+1];
        //	dp[0][0]=true;// 两个空字符串能否交织得到一个空字符串。这显然是可以的，因此f（-1，-1）的值为true。
        //	// dp[i][0]表示没有选取字符串s2中的子字符串，
        //
        //	// f（i，-1）的含义是当字符串s2的子字符串是空字符串时，它和s1[0..i]能否交织得到s3[0..i]，因此f（i，-1）的值取决于子字符串s1[0..i]和s3[0..i]是否相同。
        //	for (int i = 0; i < s1.length(); i++) {
        //		dp[i+1][0]=(s1.charAt(i)==s3.charAt(i)&&dp[i][0]);
        //	}
        //
        //	// f（-1，j）的含义是当字符串s1的子字符串是空字符串的时候，它和字符串s2从下标从0到j的子字符串（即s2[0..j]）能否交织出字符串s3中下标从0到j的子字符串（即s3[0..j]）。
        //	for (int j = 0; j < s2.length(); j++) {
        //		dp[0][j+1]=(s2.charAt(j)==s3.charAt(j)&&dp[0][j]);
        //	}
        //
        //	for (int i = 0; i <s1.length(); i++) {
        //		for (int j = 0; j < s2.length(); j++) {
        //			char ch1=s1.charAt(i);
        //			char ch2=s2.charAt(j);
        //			char ch3=s3.charAt(i+j+1);
        //			// 当s3[i+j+1]和s1[i]相同时，f（i，j）的值等于f（i-1，j）的值。类似地，当s3[i+j+1]和s2[j]相同时，f（i，j）的值等于f（i，j-1）的值。
        //			dp[i+1][j+1]=(ch1==ch3&&dp[i][j+1])||(ch2==ch3&&dp[i+1][j]);
        //		}
        //	}
        //	return dp[s1.length()][s2.length()];
        //}


        //// 解法1：动态规划+动态数组
        //// 只需要保存两行就可以
        //public boolean isInterleave(String s1, String s2, String s3) {
        //    if (s1.length() + s2.length() != s3.length()) {// 如果字符串s1的长度为m，字符串s2的长度为n，那么它们交织得到的字符串s3的长度一定是m+n。
        //        return false;
        //    }
        //    // 状态转移方程数组下标有-1，会越界。所以数组统一向平移一个单位。
        //    boolean[][] dp = new boolean[2][s2.length() + 1];
        //    dp[0][0] = true;// 两个空字符串能否交织得到一个空字符串。这显然是可以的，因此f（-1，-1）的值为true。
        //    // dp[i][0]表示没有选取字符串s2中的子字符串，
        //
        //    // f（-1，j）的含义是当字符串s1的子字符串是空字符串的时候，它和字符串s2从下标从0到j的子字符串（即s2[0..j]）能否交织出字符串s3中下标从0到j的子字符串（即s3[0..j]）。
        //    for (int j = 0; j < s2.length(); j++) {
        //        dp[0][j + 1] = (s2.charAt(j) == s3.charAt(j) && dp[0][j]);
        //    }
        //    for (int i = 0; i < s1.length(); i++) {
        //        // f（i，-1）的含义是当字符串s2的子字符串是空字符串时，它和s1[0..i]能否交织得到s3[0..i]，因此f（i，-1）的值取决于子字符串s1[0..i]和s3[0..i]是否相同。
        //        dp[(i + 1) % 2][0] = (s1.charAt(i) == s3.charAt(i) && dp[i % 2][0]);
        //        for (int j = 0; j < s2.length(); j++) {
        //            char ch1 = s1.charAt(i);
        //            char ch2 = s2.charAt(j);
        //            char ch3 = s3.charAt(i + j + 1);
        //            // 当s3[i+j+1]和s1[i]相同时，f（i，j）的值等于f（i-1，j）的值。类似地，当s3[i+j+1]和s2[j]相同时，f（i，j）的值等于f（i，j-1）的值。
        //            dp[(i + 1) % 2][j + 1] = (ch1 == ch3 && dp[i % 2][j + 1]) || (ch2 == ch3 && dp[(i + 1) % 2][j]);
        //        }
        //    }
        //    return dp[s1.length() % 2][s2.length()];
        //}


        //	解法2：动态规划+优化空间效率  这个后面再看
        // 只需要保留二维数组中的一行就可以。f（i，j）的值依赖于位于它上方的f（i-1，j）和它左方的f（i，j-1），因此在计算f（i，j+1）时只依赖于f（i-1，j+1）和f（i，j）的值。
        // f（i-1，j）的值在计算出f（i，j）之后就不再需要，因此可以用同一个位置保存f（i-1，j）和f（i，j）的值。
        // 该位置在f（i，j）计算之前保存的是f（i-1，j）的值，一旦计算出f（i，j）的值之后就替换f（i-1，j）。
        // 这时会丢失f（i-1，j）的值，但不会导致任何问题，因为以后的计算不再需要f（i-1，j）的值
        //public boolean isInterleave(String s1, String s2, String s3) {
        //	if(s1.length()+s2.length()!=s3.length()){// 如果字符串s1的长度为m，字符串s2的长度为n，那么它们交织得到的字符串s3的长度一定是m+n。
        //		return false;
        //	}
        //	// 函数的第二个参数s2长度选小的，这样节省存储空间
        //    if(s1.length()<s2.length()){
        //        return isInterleave(s2,s1,s3);
        //    }
        //
        //	boolean[] dp=new boolean[s2.length()+1];
        //	dp[0]=true;
        //
        //	for (int j = 0; j < s2.length(); j++) {
        //		dp[j+1]=(s2.charAt(j)==s3.charAt(j)&&dp[j]);
        //	}
        //
        //	for (int i = 0; i <s1.length(); i++) {
        //        dp[0]=dp[0]&&s1.charAt(i)==s3.charAt(i);
        //		for (int j = 0; j < s2.length(); j++) {
        //			char ch1=s1.charAt(i);
        //			char ch2=s2.charAt(j);
        //			char ch3=s3.charAt(i+j+1);
        //			// 当s3[i+j+1]和s1[i]相同时，f（i，j）的值等于f（i-1，j）的值。类似地，当s3[i+j+1]和s2[j]相同时，f（i，j）的值等于f（i，j-1）的值。
        //			dp[j+1]=(ch1==ch3&&dp[j+1])||(ch2==ch3&&dp[j]);
        //		}
        //	}
        //	return dp[s2.length()];
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
