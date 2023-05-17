/**
 * <p>给定一个包含大写字母和小写字母的字符串<meta charset="UTF-8" />&nbsp;<code>s</code>&nbsp;，返回&nbsp;<em>通过这些字母构造成的
 * <strong>最长的回文串</strong></em>&nbsp;。</p>
 *
 * <p>在构造过程中，请注意 <strong>区分大小写</strong> 。比如&nbsp;<code>"Aa"</code>&nbsp;不能当做一个回文字符串。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1: </strong></p>
 *
 * <pre>
 * <strong>输入:</strong>s = "abccccdd"
 * <strong>输出:</strong>7
 * <strong>解释:</strong>
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong>s = "a"
 * <strong>输入:</strong>1
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong>s = "bb"
 * <strong>输入:</strong> 2
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 2000</code></li>
 * <li><code>s</code>&nbsp;只能由小写和/或大写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 426</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * 409
 * 最长回文串
 * @author wangweizhou
 * @date 2022-07-05 00:39:49
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LongestPalindrome().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestPalindrome(String s) {

            if (s == null || s.length() == 0) {
                return 0;
            }
            int length = s.length();
            Set<Character> set = new HashSet<>();
            int curr = 0;
            while (curr < length) {
                char ch = s.charAt(curr);
                if (set.contains(ch)) {
                    set.remove(ch);
                } else {
                    set.add(ch);
                }
				curr++;
            }
			int setSize=set.size();
            return setSize==0? length :length - set.size()+1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
