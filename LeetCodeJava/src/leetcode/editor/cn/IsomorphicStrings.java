/**
 * <p>给定两个字符串&nbsp;<code>s</code>&nbsp;和&nbsp;<code>t</code>&nbsp;，判断它们是否是同构的。</p>
 *
 * <p>如果&nbsp;<code>s</code>&nbsp;中的字符可以按某种映射关系替换得到&nbsp;<code>t</code>&nbsp;，那么这两个字符串是同构的。</p>
 *
 * <p>每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = <code>"egg", </code>t = <code>"add"</code>
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = <code>"foo", </code>t = <code>"bar"</code>
 * <strong>输出：</strong>false</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = <code>"paper", </code>t = <code>"title"</code>
 * <strong>输出：</strong>true</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <p><meta charset="UTF-8" /></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
 * <li><code>t.length == s.length</code></li>
 * <li><code>s</code>&nbsp;和&nbsp;<code>t</code>&nbsp;由任意有效的 ASCII 字符组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 482</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 205
 * 同构字符串
 *
 * @author wangweizhou
 * @date 2022-07-07 00:55:37
 */
public class IsomorphicStrings {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new IsomorphicStrings().new Solution();
        String s = "foo";
        String t = "bar";
        solution.isIsomorphic(s, t);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 需要我们判断 s 和 t 每个位置上的字符是否都一一对应，即 s 的任意一个字符被 t 中唯一的字符对应，同时 t 的任意一个字符被 s 中唯一的字符对应。
        // 两个字符串同构的含义就是字符串 s 可以唯一的映射到 t ，同时 t 也可以唯一的映射到 s 。

        // 维护两张哈希表，第一张哈希表 s2t 以 s 中字符为键，映射至 t 的字符为值，第二张哈希表 t2s 以 t 中字符为键，映射至 s 的字符为值。
        //




        //方法一：哈希表
        public boolean isIsomorphic(String s, String t) {
            Map<Character, Character> s2t = new HashMap<>();
            Map<Character, Character> t2s = new HashMap<>();
            int len = s.length();
            for (int i = 0; i < len; ++i) {
                char x = s.charAt(i), y = t.charAt(i);
                //一一映射且字符先后顺序不能改变
                // 即当前下标 index 对应的字符 s[index] 已经存在映射且不为 t[index]
                // 或当前下标 index 对应的字符 t[index] 已经存在映射且不为s[index]）时说明两个字符串无法构成同构，返回 false
                if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                    return false;
                }
                s2t.put(x, y);
                t2s.put(y, x);
            }
            return true;
        }


        /*
        // 没看懂这个解法
        public boolean isIsomorphic(String s, String t) {
            char[] chars = s.toCharArray();
            char[] chart = t.toCharArray();
            int[] preIndexOfs = new int[256];
            int[] preIndexOft = new int[256];
            for (int i = 0; i < chars.length; i++) {
                if (preIndexOfs[chars[i]] != preIndexOft[chart[i]]) {
                    return false;
                }
                preIndexOfs[chars[i]] = i + 1;
                preIndexOft[chart[i]] = i + 1;
            }
            return true;
        }

        */




    }
//leetcode submit region end(Prohibit modification and deletion)

}
