/**
 * <p>给定两个字符串 <code>text1</code> 和 <code>text2</code>，返回这两个字符串的最长 <strong>公共子序列</strong> 的长度。如果不存在
 * <strong>公共子序列</strong> ，返回 <code>0</code> 。</p>
 *
 * <p>一个字符串的 <strong>子序列</strong><em> </em>是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。</p>
 *
 * <ul>
 * <li>例如，<code>"ace"</code> 是 <code>"abcde"</code> 的子序列，但 <code>"aec"</code> 不是 <code>"abcde"</code> 的子序列。</li>
 * </ul>
 *
 * <p>两个字符串的 <strong>公共子序列</strong> 是这两个字符串所共同拥有的子序列。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>text1 = "abcde", text2 = "ace"
 * <strong>输出：</strong>3
 * <strong>解释：</strong>最长公共子序列是 "ace" ，它的长度为 3 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>text1 = "abc", text2 = "abc"
 * <strong>输出：</strong>3
 * <strong>解释：</strong>最长公共子序列是 "abc" ，它的长度为 3 。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>text1 = "abc", text2 = "def"
 * <strong>输出：</strong>0
 * <strong>解释：</strong>两个字符串没有公共子序列，返回 0 。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= text1.length, text2.length <= 1000</code></li>
 * <li><code>text1</code> 和 <code>text2</code> 仅由小写英文字符组成。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 1076</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 1143
 * 最长公共子序列
 * @author wangweizhou
 * @date 2022-08-05 02:07:06
 */

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LongestCommonSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 应用动态规划解决问题的关键在于确定状态转移方程。由于输入有两个字符串，因此状态转移方程有两个参数。
        // 状态定义：用函数f（i，j）表示第1个字符串中下标从0到i的子字符串（记为s1[0..i]）和第2个字符串中下标从0到j的子字符串（记为s2[0..j]）的最长公共子序列的长度。
        // 如果第1个字符串的长度是m，第2个字符串的长度是n，那么f（m-1，n-1）就是整个问题的解。


        // 状态转移方程：
        // 如果第1个字符串中下标为i的字符（记为s1[i]）与第2个字符串中下标为j（记为s2[j]）的字符相同，
        // 那么f（i，j）相当于在s1[0..i-1]和s2[0..j-1]的最长公共子序列的后面添加一个公共字符，也就是f（i，j）=f（i-1，j-1）+1。
        // 如果字符s1[i]与字符s2[j]不相同，则这两个字符不可能同时出现在s1[0..i]和s2[0..j]的公共子序列中。
        // 此时s1[0..i]和s2[0..j]的最长公共子序列要么是s1[0..i-1]和s2[0..j]的最长公共子序列，要么是s1[0..i]和s2[0..j-1]的最长公共子序列。
        // 也就是说，此时f（i，j）是f（i-1，j）和f（i，j-1）的最大值。

        // 状态转移方程：
        // 当 text1[i] == text2[j],dp[i][j]=dp[i−1][j−1]+1;
        // 当 text1[i] != text2[j],dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])。

        // 根据状态转移方程，i>=1,并且j>=1。
        // 当上述状态转移方程的i或j等于0时，即求f（0，j）或f（i，0）时可能需要f（-1，j）或f（i，-1）的值。
        // f（0，j）的含义是s1[0..0]和s2[0..j]这两个子字符串的最长公共子序列的长度，即第1个子字符串只包含一个下标为0的字符，
        // 那么f（-1，j）对应的第1个子字符串再减少一个字符，所以第1个子字符串是空字符串。
        // 任意空字符串和另一个字符串的公共子序列的长度都是0，所f（-1，j）的值等于0。同理，f（i，-1）的值也等于0。
        // f（0，j）和f（i，0）都不好初始化。
        // 但是f（-1，j）=0，f（i，-1）=0这两个初始状态好初始化，并且f（-1，j）和f（i，-1）数组下标越界，所以f（i，j）统一向右偏移一个位置。
        // 修改状态定义：用函数f（i+1，j+1）表示第1个字符串中下标从0到i的子字符串（记为s1[0..i]）和第2个字符串中下标从0到j的子字符串（记为s2[0..j]）的最长公共子序列的长度。
        // 状态定义： dp[i][j] 表示 text1[0:i-1] 和 text2[0:j-1] 的最长公共子序列。
        // text1[0:i-1] 表示的是 text1 的 第 0 个元素到第 i - 1个元素，两端都包含。或者理解为text1的前i个字符。
        // 也就是动态规划数组的下标比参数字符串的最后一个下标大1。那么结果就是dp[len1][len2]。



        // 解法2： 写法1 动态规划
        public int longestCommonSubsequence(String text1, String text2) {
            if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
                return 0;
            }
            int len1 = text1.length();
            int len2 = text2.length();

            // 修改状态定义：用函数f（i+1，j+1）表示第1个字符串中下标从0到i的子字符串（记为s1[0..i]）和第2个字符串中下标从0到j的子字符串（记为s2[0..j]）的最长公共子序列的长度。
            // dp[i+1][j+1] 表示 text1[0:i] 和 text2[0:j] 的最长公共子序列。
            int[][] dp = new int[len1 + 1][len2 + 1];// java int数组的默认值是0，所以这里不需要额外初始化。
            // dp[0][j]=0和dp[i][0]=0;表示空字符串和其他字符串没有公共子序列
            for (int i = 0; i < len1; i++) {
                for (int j = 0; j < len2; j++) {
                    if (text1.charAt(i) == text2.charAt(j)) {// 若新位置的两个字符相等
						// 如果第1个字符串中下标为i的字符（记为s1[i]）与第2个字符串中下标为j（记为s2[j]）的字符相同，
						// 那么f（i，j）相当于在s1[0..i-1]和s2[0..j-1]的最长公共子序列的后面添加一个公共字符，也就是f（i，j）=f（i-1，j-1）+1。
                        dp[i + 1][j + 1] = dp[i][j] + 1;
                    } else {// 若新位置的两个字符不相等
						// 如果字符s1[i]与字符s2[j]不相同，则这两个字符不可能同时出现在s1[0..i]和s2[0..j]的公共子序列中。
						// 此时s1[0..i]和s2[0..j]的最长公共子序列要么是s1[0..i-1]和s2[0..j]的最长公共子序列，
						// 要么是s1[0..i]和s2[0..j-1]的最长公共子序列。也就是说，此时f（i，j）是f（i-1，j）和f（i，j-1）的最大值。
                        dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                    }
                }
            }
            return dp[len1][len2];
        }






        // 解法2： 写法2 动态规划+动态数组优化空间效率
        // 需要注意的是，f（i，j）的值依赖于表格中左上角f（i-1，j-1）的值、正上方f（i-1，j）的值和同一行左边f（i，j-1）的值。
        // 由于计算f（i，j）的值时只需要使用上方一行的值和同一行左边的值，因此实际上只需要保存表格中的两行就可以。

        //public int longestCommonSubsequence(String text1, String text2) {
        //	if(text1==null||text1.length()==0||text2==null||text2.length()==0){
        //		return 0;
        //	}
        //	int len1=text1.length();
        //	int len2=text2.length();
        //
        //	if(len1<len2){// 这里为了优化空间，第二个参数取较短的字符串
        //		return longestCommonSubsequence(text2,text1);
        //	}
        //
        //	// 修改状态定义：用函数f（i+1，j+1）表示第1个字符串中下标从0到i的子字符串（记为s1[0..i]）和第2个字符串中下标从0到j的子字符串（记为s2[0..j]）的最长公共子序列的长度。
        //	// dp[i+1][j+1] 表示 text1[0:i] 和 text2[0:j] 的最长公共子序列。
        //	int[][] dp=new int[2][len2+1];
        //	for (int i = 0; i <len1 ; i++) {
        //		for (int j = 0; j <len2 ; j++) {
        //			if(text1.charAt(i)==text2.charAt(j)){//若字符相等
        //				dp[(i+1)%2][j+1]=dp[i%2][j]+1;
        //			}else{//若不想等
        //				dp[(i+1)%2][j+1]=Math.max(dp[(i+1)%2][j],dp[i%2][j+1]);
        //			}
        //		}
        //	}
        //	return dp[len1%2][len2];
        //}





        //// 解法2： 写法3 动态规划+动态数组优化空间效率。将动态数组中变量给新的名字，并循环代替。 这个看的不太懂
        //// 这个一维数组的长度是表格的列数（即输入字符串s2的长度加1）。
        //// 为了让一个一维数组保存表格中的两行信息，一维数组的每个位置需要保存原来表格中上下两格的信息，即f（i，j）和f（i-1，j）都保存在数组dp下标j+1的位置。
        //// 在计算f（i，j）之前，“dp[j+1]”中保存的是f（i-1，j）的值；在完成f（i，j）的计算之后，“dp[j+1]”被f（i，j）的值替换。
        //// 需要注意的是，在计算f（i，j+1）时，可能还需要f（i-1，j）的值，因此在计算f（i，j）之后不能直接用f（i，j）的值替换“dp[j+1]”中f（i-1，j）的值。
        //// 可以在用f（i，j）的值替换“dp[j+1]”中f（i-1，j）的值之前先将f（i-1，j）的值临时保存起来，这样下一步在计算f（i，j+1）时还能得到f（i-1，j）的值。
        //
        //
        //// 在上述代码中，变量prev用来保存数组中被替换的值。在计算f（i，j）之前，变量prev保存的是f（i-1，j-1）的值。
        //// 在计算f（i，j）（代码中的变量cur）之后将它保存到“dp[j+1]”中。在保存f（i，j）之前，将保存在“dp[j+1]”中的值（即f（i-1，j））临时保存到变量prev中。
        //// 下一步计算f（i，j+1）时可以从变量prev中得到f（i-1，j）。
        //// 在代码“cur=Math.max（dp[j]，dp[j+1]）”中，“dp[j]”对应的是f（i，j-1），而“dp[j+1]”对应的是f（i-1，j）。
        //// 由于是按照从上到下、从左到右的顺序填充表格的，因此在计算f（i，j）之前，f（i，j-1）的值已经计算出来并保存到dp[j]的位置。
        //// 此时f（i，j）的值还没有计算出来，因此保存在“dp[j+1]”中的还是f（i-1，j）的值。
        //
        //
        //public int longestCommonSubsequence(String text1, String text2) {
        //	if(text1==null||text1.length()==0||text2==null||text2.length()==0){
        //		return 0;
        //	}
        //	int len1=text1.length();
        //	int len2=text2.length();
        //
        //	if(len1<len2){// 这里为了优化空间，第二个参数取较短的字符串
        //		return longestCommonSubsequence(text2,text1);
        //	}
        //	// prev 表示f（i-1，j-1）的值
        //	// curr 表示f（i，j）的值
        //	// 在计算f（i，j）（代码中的变量cur）之后将它保存到“dp[j+1]”中。在保存f（i，j）之前，将保存在“dp[j+1]”中的值（即f（i-1，j））临时保存到变量prev中。
        //
        //	int[] dp=new int[len2+1];
        //	for (int i = 0; i <len1 ; i++) {
        //		int prev=dp[0];
        //		for (int j = 0; j <len2 ; j++) {
        //			int curr;
        //			if(text1.charAt(i)==text2.charAt(j)){//若字符相等
        //				curr=prev+1;
        //			}else{//若不想等
        //				// 在代码“cur=Math.max（dp[j]，dp[j+1]）”中，“dp[j]”对应的是f（i，j-1），而“dp[j+1]”对应的是f（i-1，j）。
        //				curr=Math.max(dp[j],dp[j+1]);
        //			}
        //			prev=dp[j+1];
        //			dp[j+1]=curr;
        //		}
        //	}
        //	return dp[len2];
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
