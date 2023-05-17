/**
 * <p>给你一个字符串 <code>s</code>，请你将<em> </em><code>s</code><em> </em>分割成一些子串，使每个子串都是 <strong>回文串</strong> 。返回
 * <code>s</code> 所有可能的分割方案。</p>
 *
 * <p><strong>回文串</strong> 是正着读和反着读都一样的字符串。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "aab"
 * <strong>输出：</strong>[["a","a","b"],["aa","b"]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "a"
 * <strong>输出：</strong>[["a"]]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 16</code></li>
 * <li><code>s</code> 仅由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li><li>回溯</li></div></div><br><div><li>👍 1324</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 131
 * 分割回文串
 *
 * @author wangweizhou
 * @date 2022-12-04 23:45:55
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new PalindromePartitioning().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：回溯法
        // 当处理到字符串中的某个字符时，如果包括该字符在内后面还有n个字符，那么此时面临n个选项，即分割出长度为1的子字符串（只包含该字符）、
        // 分割出长度为2子字符串（即包含该字符及它后面的一个字符），以此类推，分割出长度为n的子字符串（即包含该字符在内的后面的所有字符）。
        // 由于题目要求分割出来的每个子字符串都是回文，因此需要逐一判断这n个子字符串是不是回文，只有回文子字符串才是符合条件的分割。
        // 分割出一段回文子字符串之后，接着分割后面的字符串。

        // 在递归函数helper中，参数substrings是一组所有子字符串都是回文的分割。
        // 当处理到下标为start的字符串时，代码用一个for循环逐一判断从下标start开始到i结束（i从下标start开始，到字符串s的最后一个字符结束）的每个子字符串是否为回文。
        // 如果是回文，就分割出一个符合条件的子字符串，添加到substrings中，接着处理下标从i+1开始的剩余的字符串。
        // 当start等于字符串s的长度时，整个字符串s已经被分割成若干回文子字符串。

        public List<List<String>> partition(String s) {
            List<List<String>> lists = new LinkedList<>();
            if (s == null || s.length() ==0) {
                return lists;
            }
            partitionFunc(s, 0, new LinkedList<>(), lists);
            return lists;
        }


        private void partitionFunc(String str, int start, LinkedList<String> substrings, List<List<String>> lists) {
            if (start == str.length()) {
                lists.add(new LinkedList<>(substrings));
                return;
            }
            for (int i = start; i < str.length(); i++) {
                // 因为截取字符串是消耗性能的，因此，采用传子串下标的方式判断一个子串是否是回文子串
                if (isPalindrome(str, start, i)) {// 当[start,i]是回文，将子串[start,i]添加到集合substrings中
                    substrings.add(str.substring(start, i + 1));
                    partitionFunc(str, i + 1, substrings, lists);// 因为[start,i]是回文，切割出[start,i]之后，所以要从（i+1）开始看是否能再分割
                    substrings.removeLast();// 回溯
                }
            }
        }


        // 判断[start,end]是回文
        private boolean isPalindrome(String str, int start, int end) {
            while (start < end) {
                if (str.charAt(start++) != str.charAt(end--)) {
                    return false;
                }
            }
            return true;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
