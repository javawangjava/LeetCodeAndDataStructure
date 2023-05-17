/**
 * <p>实现&nbsp;<a href="https://baike.baidu.com/item/strstr/811469" target="_blank">strStr()</a>&nbsp;函数。</p>
 *
 * <p>给你两个字符串&nbsp;<code>haystack</code> 和 <code>needle</code> ，请你在 <code>haystack</code> 字符串中找出 <code>needle</code> 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回&nbsp; <code>-1</code><strong> </strong>。</p>
 *
 * <p><strong>说明：</strong></p>
 *
 * <p>当&nbsp;<code>needle</code>&nbsp;是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。</p>
 *
 * <p>对于本题而言，当&nbsp;<code>needle</code>&nbsp;是空字符串时我们应当返回 0 。这与 C 语言的&nbsp;<a href="https://baike.baidu.com/item/strstr/811469" target="_blank">strstr()</a>&nbsp;以及 Java 的&nbsp;<a href="https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#indexOf(java.lang.String)" target="_blank">indexOf()</a>&nbsp;定义相符。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>haystack = "hello", needle = "ll"
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>haystack = "aaaaa", needle = "bba"
 * <strong>输出：</strong>-1
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= haystack.length, needle.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>haystack</code> 和 <code>needle</code> 仅由小写英文字符组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li><li>字符串匹配</li></div></div><br><div><li>👍 1461</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 实现 strStr()
 */
public class ImplementStrstr {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ImplementStrstr().new Solution();
        int a = solution.strStr("aaaao", "laa");
        System.out.println(a);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //方法1： 暴力匹配
        public int strStr(String haystack, String needle) {
            if (needle == null) {
                return 0;
            }
            int length1 = haystack.length();
            int length2 = needle.length();
            if (length1 < length2) {//haystack中找不到needle，则返回-1
                return -1;
            }

            int index = 0;// index枚举原串的「发起点」
            while (index + length2 <= length1) {
                // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
                int j = index;
                int i = 0;

                while (i < length2 && haystack.charAt(j) == needle.charAt(i)) {
                    j++;//后移
                    i++;
                    if (i == length2) { // 如果能够完全匹配，返回原串的「发起点」下标
                        return index;
                    }
                }
                index++;
            }
            return -1;
        }

        //    方法2：
  /*  public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }*/

        //方法3#：
        // KMP 算法  没看明白
        // ss: 原串(string)  pp: 匹配串(pattern)
        /*public int strStr(String ss, String pp) {
            if (pp.isEmpty()) return 0;

            // 分别读取原串和匹配串的长度
            int n = ss.length(), m = pp.length();
            // 原串和匹配串前面都加空格，使其下标从 1 开始
            ss = " " + ss;
            pp = " " + pp;

            char[] s = ss.toCharArray();
            char[] p = pp.toCharArray();

            // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
            int[] next = new int[m + 1];
            // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
            for (int i = 2, j = 0; i <= m; i++) {
                // 匹配不成功的话，j = next(j)
                while (j > 0 && p[i] != p[j + 1]) j = next[j];
                // 匹配成功的话，先让 j++
                if (p[i] == p[j + 1]) j++;
                // 更新 next[i]，结束本次循环，i++
                next[i] = j;
            }

            // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
            for (int i = 1, j = 0; i <= n; i++) {
                // 匹配不成功 j = next(j)
                while (j > 0 && s[i] != p[j + 1]) j = next[j];
                // 匹配成功的话，先让 j++，结束本次循环后 i++
                if (s[i] == p[j + 1]) j++;
                // 整一段匹配成功，直接返回下标
                if (j == m) return i - m;
            }

            return -1;
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}
