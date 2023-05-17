/**
 * <p>给你两个单词&nbsp;<code>word1</code> 和&nbsp;<code>word2</code>， <em>请返回将&nbsp;<code>word1</code>&nbsp;转换成&nbsp;
 * <code>word2</code> 所使用的最少操作数</em> &nbsp;。</p>
 *
 * <p>你可以对一个单词进行如下三种操作：</p>
 *
 * <ul>
 * <li>插入一个字符</li>
 * <li>删除一个字符</li>
 * <li>替换一个字符</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>word1 = "horse", word2 = "ros"
 * <strong>输出：</strong>3
 * <strong>解释：</strong>
 * horse -&gt; rorse (将 'h' 替换为 'r')
 * rorse -&gt; rose (删除 'r')
 * rose -&gt; ros (删除 'e')
 * </pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>word1 = "intention", word2 = "execution"
 * <strong>输出：</strong>5
 * <strong>解释：</strong>
 * intention -&gt; inention (删除 't')
 * inention -&gt; enention (将 'i' 替换为 'e')
 * enention -&gt; exention (将 'n' 替换为 'x')
 * exention -&gt; exection (将 'n' 替换为 'c')
 * exection -&gt; execution (插入 'u')
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= word1.length, word2.length &lt;= 500</code></li>
 * <li><code>word1</code> 和 <code>word2</code> 由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 2524</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 72
 * 编辑距离
 * @author wangweizhou
 * @date 2022-08-09 06:00:11
 */


public class EditDistance {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new EditDistance().new Solution();
        String word1="horse";
        String word2="ros";
        int ans=solution.minDistance(word1,word2);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 方法一：动态规划  没看明白思路  插入删除说的糊里糊涂
        // 可以对任意一个单词进行三种操作：插入一个字符；删除一个字符；替换一个字符。
        // 状态定义：dp[i][j] 表示 A 的前 i 个字母和 B 的前 j 个字母之间的编辑距离。

        // D[i][j-1] 为 A 的前 i 个字符和 B 的前 j - 1 个字符编辑距离的子问题。
        // 也就是需要再得到B的第j个字符，即对于 B 的第 j 个字符，我们在 A 的末尾添加了一个相同的字符，那么 D[i][j] 最小可以为D[i][j-1] + 1；

        // D[i-1][j] 为 A 的前 i - 1 个字符和 B 的前 j 个字符编辑距离的子问题。
        // 也就是A中多了第i个字符，即对于 A 的第 i 个字符，我们在 B 的末尾添加了一个相同的字符，那么 D[i][j] 最小可以为D[i-1][j] + 1；

        // D[i-1][j-1] 为 A 前 i - 1 个字符和 B 的前 j - 1 个字符编辑距离的子问题。
        // 即对于 B 的第 j 个字符，我们修改 A 的第 i 个字符使它们相同，那么 D[i][j] 最小可以为D[i-1][j-1] + 1。
        // 特别地，如果 A 的第 i 个字符和 B 的第 j 个字符原本就相同，那么我们实际上不需要进行修改操作。在这种情况下，D[i][j] 最小可以为 D[i-1][j-1]。


        // 状态转移方程：
        // 若 A 和 B 的最后一个字母相同：
        // 若 A 和 B 的最后一个字母不同：



        public int minDistance(String word1, String word2) {
            if (word1 == null || word2 == null) {
                return 0;
            }
            int len1 = word1.length();
            int len2 = word2.length();

            // 有一个字符串为空串
            if (len1 * len2 == 0) {
                return len1 + len2;
            }

            // DP 数组
            int[][] dp = new int[len1 + 1][len2 + 1];

            // 边界状态初始化
            for (int i = 0; i < len1 + 1; i++) {
                dp[i][0] = i;
            }
            for (int j = 0; j < len2 + 1; j++) {
                dp[0][j] = j;
            }


            for (int i = 1; i < len1 + 1; i++) {
                for (int j = 1; j < len2 + 1; j++) {
                    int left = dp[i - 1][j] + 1;
                    int up = dp[i][j - 1] + 1;
                    int upLeft = dp[i - 1][j - 1];
                    if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                        upLeft += 1;
                    }
                    dp[i][j] = Math.min(left, Math.min(up, upLeft));
                }
            }
            return dp[len1][len2];
        }


    }

//leetcode submit region end(Prohibit modification and deletion)

}
