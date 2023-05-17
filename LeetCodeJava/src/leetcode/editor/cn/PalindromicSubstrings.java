/**
 * <p>给你一个字符串 <code>s</code> ，请你统计并返回这个字符串中 <strong>回文子串</strong> 的数目。</p>
 *
 * <p><strong>回文字符串</strong> 是正着读和倒过来读一样的字符串。</p>
 *
 * <p><strong>子字符串</strong> 是字符串中的由连续字符组成的一个序列。</p>
 *
 * <p>具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "abc"
 * <strong>输出：</strong>3
 * <strong>解释：</strong>三个回文子串: "a", "b", "c"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "aaa"
 * <strong>输出：</strong>6
 * <strong>解释：</strong>6个回文子串: "a", "a", "a", "aa", "aa", "aaa"</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 1000</code></li>
 * <li><code>s</code> 由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 951</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 647
 * 回文子串
 *
 * @author wangweizhou
 * @date 2022-08-18 09:02:51
 */


public class PalindromicSubstrings {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new PalindromicSubstrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 结合5,125,647和680一起看
        // 前面都是从字符串的两端开始向里移动指针来判断字符串是否是一个回文，其实也可以换一个方向从字符串的中心开始向两端延伸。
        // 如果存在一个长度为m的回文子字符串，则分别再向该回文的两端延伸一个字符，并判断回文前后的字符是否相同。
        // 如果相同，就找到了一个长度为m+2的回文子字符串。
        // 回文的长度既可以是奇数，也可以是偶数。长度为奇数的回文的对称中心只有一个字符，而长度为偶数的回文的对称中心有两个字符。
        // 字符串的下标为i。第i个字符本身可以成为长度为奇数的回文子字符串的对称中心，同时第i个字符和第i+1个字符可以一起成为长度为偶数的回文子字符串的对称中心。


        // 解法3：中心扩散法
        public int countSubstrings(String s) {
            if (s == null || s.length() == 0) {// 判空
                return 0;
            }
            int count = 0;
            int len = s.length();
            for (int i = 0; i < len; i++) {// 遍历字符串，分别获取以第i个字符为中心，和以第i个和第（i+1）个字符为中心的回文字符串个数
                count += countPalindrome(s, i, i);// 长度为奇数的回文的对称中心只有一个字符
                count += countPalindrome(s, i, i + 1);// 长度为偶数的回文的对称中心有两个字符。
            }
            return count;
        }



        // 计算以[left,right]为中心的回文子串个数
        private int countPalindrome(String str, int left, int right) {
            if(left>right){
                return 0;
            }
            int count = 0;
            int len = str.length();
            // 字符串下标没有越界，字符串从中心位置开始，向两边扩散，每扩散一个新位置，则回文子串的个数加1。
            while (left >= 0 && right < len && str.charAt(left) == str.charAt(right)) {
                count++;
                left--;
                right++;
            }
            return count;
        }





        //// 解法2：中心拓展法
        //// 中心点即 left 指针和 right 指针初始化指向的地方，可能是一个也可能是两个
        //// 为什么有 2 * len - 1 个中心点？
        //// aba 有5个中心点，分别是 a、b、c、ab、ba
        //// abba 有7个中心点，分别是 a、b、b、a、ab、bb、ba
        //
        //// 数学归纳：字符串中心点的个数（2 * s.length() - 1）
        //public int countSubstrings(String s) {
        //    int count = 0;
        //    // 数学归纳找到的规律
        //    // 因为字符串也是从下标为0开始的，所以center也从下标为0开始
        //    // center从下标0开始，所以目前截止到center【包含center】的元素个数是（center+1）个
        //    for (int center = 0; center < 2 * s.length() - 1; center++) {
        //        // left和right指针和中心点的关系是？
        //        // 首先是left，有一个很明显的2倍关系的存在，其次是right，可能和left指向同一个（偶数时），也可能往后移动一个（奇数）
        //        // 大致的关系出来了，可以选择带两个特殊例子进去看看是否满足。
        //        int left = center / 2;
        //        int right = left + center % 2;//通过除2取余看是否和left指向同一个元素
        //
        //        // 判断中心位置【left,right=left+1】或者【left，left】开始进行扩展
        //        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
        //            count++;
        //            left--;
        //            right++;
        //        }
        //    }
        //    return count;
        //}





		/*

	// 解法1：动态规划法
	// 状态：boolean dp[i][j] 表示字符串s在[i,j]区间的子串是否是一个回文串。
	// 状态转移方程：当 s[i] == s[j] && (j - i < 2 || dp[i + 1][j - 1]) 时，dp[i][j]=true，否则为false

	// 状态转移方程解释：
	// 1.当只有一个字符时，比如 a 自然是一个回文串。
	// 2.当有两个字符时，如果是相等的，比如 aa，也是一个回文串。
	// 3.当有三个及以上字符时，比如 ababa 这个字符记作串 1，把两边的 a 去掉，也就是 bab 记作串 2，可以看出只要串2是一个回文串，那么左右各多了一个 a 的串 1 必定也是回文串。
	// 所以当 s[i]==s[j]时，自然要看 dp[i+1][j-1] 是不是一个回文串。

	public int countSubstrings(String s) {
		// 动态规划法
		boolean[][] dp = new boolean[s.length()][s.length()];
		int count = 0;
		for (int right = 0; right < s.length(); right++) {
			for (int left = 0; left <= right; left++) {
				if (s.charAt(left) == s.charAt(right) && (right - left < 2 || dp[left + 1][right - 1])) {
					dp[left][right] = true;
					count++;
				}
			}
		}
		return count;
	}
	*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
