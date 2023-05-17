/**
 * <p>给你一个字符串 <code>s</code>，请你将 <code>s</code> 分割成一些子串，使每个子串都是回文。</p>
 *
 * <p>返回符合要求的 <strong>最少分割次数</strong> 。</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "aab"
 * <strong>输出：</strong>1
 * <strong>解释：</strong>只需一次分割就可将 <em>s </em>分割成 ["aa","b"] 这样两个回文子串。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "a"
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "ab"
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 2000</code></li>
 * <li><code>s</code> 仅由小写英文字母组成</li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 617</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 132
 * 分割回文串 II
 *
 * @author wangweizhou
 * @date 2022-09-09 21:52:56
 */

public class PalindromePartitioningIi {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new PalindromePartitioningIi().new Solution();

        int ans = solution.minCut("aaba");
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 可以将一个字符串切若干刀使每个子字符串都是回文，也就是说，完成一个分割需要多个步骤，而且每个步骤的分割也可能面临多个选择。
        // 例如，在考虑分割字符串"aaba"以最后一个字符'a'为结尾字符的回文子字符串时，
        // 就有两个选择：一个选择是分割出来的回文子字符串只包含一个字符，即"a"（此时整个字符串"aaba"可以分割出3个回文子字符串"aa"、"b"和"a"）；
        // 另一个选择是分割出来的子字符串包含3个字符，即"aba"（此时整个字符串"aaba"可以分割出两个回文子字符串，即"a"和"aba"）。

        // 应用动态规划解决问题的关键在于找出状态转移方程。只与一个变量有关，状态转移方程只有一个变量。
        // 假设字符串为S，下标为i的字符为S[i]，下标从j到i的子字符串为S[j..i]。
        // 用f（i）表示从下标为0到i的子字符串S[0..i]的符合条件的最少分割次数。如果字符串的长度是n，那么f（n-1）就是问题的解。
        // 如果子字符串S[0..i]本身就是一个回文，那么不需要分割就符合要求，此时f（i）等于0。
        // 如果子字符串S[0..i]不是一个回文，那么对每个下标j（1≤j≤i）逐一判断子字符串S[j..i]是不是回文。
        // 如果是回文，那么这就是一个有效的分割方法，此时的分割次数相当于子字符串S[0..j-1]的分割次数再加1。
        // 因为这是将子字符串S[0..j-1]按照要求分割之后，再在S[j-1]和S[j]这两个字符中间再分割一次。
        // 因此，f（i）就是所有符合条件的j对应的f（j-1）的最小值加1。


        // 为了优化时间复杂度，上述代码需要预处理，先判断所有子字符串S[j..i]是不是回文，并将子字符串是否为回文的结果保存在“isPal[j][i]”中。
        // 判断子字符串S[j..i]是否为回文的标准是字符S[j]和S[i]相同，并且子字符串S[j+1..i-1]也是回文。
        // 优化之后只需要O（1）的时间就能判断子字符串S[j..i]是不是回文。



        // 解法1：动态规划
        public int minCut(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int len = s.length();
            char[] cs = s.toCharArray();

            // isPal[l][r]用来表示[l,r]这一段是否为回文串，[l,l]表示是一个字母，肯定是回文串
            // 判断是回文串的标准：
            // 1、当S[l]==S[r]且l==r，即只有一个字符，则[l,r]属于回文。
            // 2.1、当S[l]==S[r]且l+1==r，则[l,r]是回文串，即字符串有两个字符且两个字符相等，则该字符串是回文串。
            // 2.2、当S[l]==S[r]且l+1<r，若[l+1,r-1]是回文串，则[l,r]是回文串。若[l+1,r-1]不是回文串，则[l,r]不是回文串。

            // 使用递归来标记所有子字符串是否是回文
            // 第1个二重循环做预处理是为了判断每个子字符串是不是回文。
            boolean[][] isPal = new boolean[len][len];
            for (int r = 0; r < len; r++) {// 右边界
                for (int l = 0; l <= r; l++) {// 左边界,左边界<=右边界
                    // 当right <= left +1时，表示最多两个字符。right < left +1，即left=right，即只有一个字符；left+1=right，即只有两个字符。
                    // 当right > left +1，即至少3个字符。isPal[left +1][right -1]表示向内各走一步为回文串。
                    if (cs[r] == cs[l] ) {
                        if(r <= l + 1){
                            isPal[l][r] = true;
                        }else if(isPal[l + 1][r - 1]){
                            isPal[l][r] = true;
                        }
                    }
                }
            }


            // 第2个二重循环是为了计算状态转移方程
            // 用dp[i]表示从下标为0到i的子字符串S[0..i]的符合条件的最少分割次数。
            // 最少回文分割，如果[left,right]是回文，那么就不需要再分割了。
            int[] dp = new int[len];
            for (int r = 0; r < len; r++) {
                if (isPal[0][r]) {// 如果 [0,r] 满足回文，不需要分割
                    // 如果子字符串S[0..i]本身就是一个回文，那么不需要分割就符合要求，此时f（i）等于0
                    dp[r] = 0;
                } else {// 如果 [0,r] 不满足回文，那么就要分割
                    dp[r] = r;// 先设定一个最大分割次数（i+1 个字符最多消耗 i次分割），即将字符串分割成单个字符。

                    // 如果子字符串S[0..i]不是一个回文，那么对每个下标j（1≤j≤i）逐一判断子字符串S[j..i]是不是回文。
                    // 如果是回文，那么这就是一个有效的分割方法，此时的分割次数相当于子字符串S[0..j-1]的分割次数再加1，
                    // 因为这是将子字符串S[0..j-1]按照要求分割之后再在S[j-1]和S[j]这两个字符中间再分割一次。
                    for (int l = 0; l <= r; l++) {
                        // 在所有符合 [l,r] 回文的方案中取最小值
                        if (isPal[l][r]) {
                            dp[r] = Math.min(dp[r], dp[l - 1] + 1);
                        }
                    }
                }
            }
            return dp[len - 1];
        }





    }
//leetcode submit region end(Prohibit modification and deletion)

}
