/**
 * <p>给定两个字符串 <code>s</code> 和&nbsp;<code>t</code> 。返回 <code>s</code> 中包含&nbsp;<code>t</code>&nbsp;的所有字符的最短子字符串。如果
 * <code>s</code> 中不存在符合条件的子字符串，则返回空字符串 <code>""</code> 。</p>
 *
 * <p>如果 <code>s</code> 中存在多个符合条件的子字符串，返回任意一个。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>注意： </strong>对于 <code>t</code> 中重复字符，我们寻找的子字符串中该字符数量必须不少于 <code>t</code> 中该字符数量。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "ADOBECODEBANC", t = "ABC"
 * <strong>输出：</strong>"BANC"
 * <strong>解释：</strong>最短子字符串 "BANC" 包含了字符串 t 的所有字符 'A'、'B'、'C'</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "a", t = "a"
 * <strong>输出：</strong>"a"
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "a", t = "aa"
 * <strong>输出：</strong>""
 * <strong>解释：</strong>t 中两个字符 'a' 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length, t.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你能设计一个在 <code>o(n)</code> 时间内解决此问题的算法吗？</p>
 *
 * <p>&nbsp;</p>
 *
 * <p>
 * <meta charset="UTF-8" />注意：本题与主站 76&nbsp;题相似（本题答案不唯一）：
 * <a href="https://leetcode-cn.com/problems/minimum-window-substring/">https://leetcode-cn.com/problems/minimum-window-substring/</a></p>
 *
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 72</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 017
 * 含有所有字符的最短字符串
 *
 * @author wangweizhou
 * @date 2022-12-31 19:54:12
 */
public class M1oyTv {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new M1oyTv().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 如果一个字符串s中包含另一个字符串t的所有字符，那么字符串t的所有字符在字符串s中都出现，并且同一个字符在字符串s中出现的次数不少于在字符串t中出现的次数。
        // 用一个哈希表来统计一个字符串中每个字符出现的次数。
        // 首先扫描字符串t，每扫描到一个字符，就把该字符在哈希表中对应的值加1。
        // 然后扫描字符串s，每扫描一个字符，就检查哈希表中是否包含该字符。
        // 如果哈希表中没有该字符，则说明该字符不是字符串t中的字符，可以忽略不计。
        // 如果哈希表中存在该字符，则把该字符在哈希表中的对应值减1。
        // 如果字符串s中包含字符串t的所有字符，那么哈希表中最终所有的值都应该小于或等于0。
        // 仍然可以用两个指针定位字符串s的子字符串，其中第1个指针指向子字符串的第1个字符，第2个指针指向子字符串的最后一个字符。
        // 如果某一时刻两个指针之间的子字符串还没有包含字符串t的所有字符，则在子字符串中添加新的字符，于是向右移动第2个指针。
        // 如果仍然没有包含字符串t的所有字符，则继续向右移动第2个指针。
        // 如果某一时刻两个指针之间的子字符串已经包含字符串t的所有字符，由于目标是找出最短的符合条件的子字符串，因此向右移动第1个指针，以判断删除子字符串最左边的字符之后是否仍然包含字符串t的所有字符。


        // 用来判断s中是否包含t中所有字符
        // 哈希表map中键key是字符串t中的不同的字符，值value是对应字符出现的次数。下面操作的是次数并没有移出已经加入哈希表中的元素。
        // 字符串s中的字符加入哈希表中的时候对应的次数加1；字符串t中的字符加入哈希表的时候对应的次数减1。
        // 如果一个字符串s中包含另一个字符串t的所有字符，那么字符串t的所有字符在字符串s中都出现，并且同一个字符在字符串s中出现的次数不少于在字符串t中出现的次数。


        public String minWindow(String s, String t) {
            if (s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() < t.length()) {
                return null;
            }
            int sLen = s.length();
            int tLen = t.length();

            // 哈希表的键key是字符串t中的字符，值value是对应字符的出现次数
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < tLen; i++) {// 扫描字符串t，每扫描到一个字符，就把该字符在哈希表中对应的值加1。
                char ch = t.charAt(i);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }

            // 当变量count等于0时，两个指针之间的子字符串就包含字符串t中的所有字符。
            int count = map.size();// 变量count是出现在字符串t中但还没有出现在字符串s中的子字符串中的字符的个数。
            // [start,end]是滑动窗口  [minStart,minEnd]是包含字符串t的所有字符的最短滑动窗口。
            int start = 0;// 变量start相当于第1个指针，指向字符串s的子字符串中的第1个字符，
            int end = 0;// 变量end相当于第2个指针，指向字符串s的子字符串中的最后一个字符。

            int minStart = 0;// 最短字符串的左边界
            int minEnd = 0;// 最短字符串的右边界
            int minLen = Integer.MAX_VALUE;// 最短字符串的长度


            // 滑动窗口右边界没有越界。或者当前滑动窗口中已经包含字符串t的所有字符并且滑动窗口右边界越界。
            while (end < sLen || (count == 0 && end == sLen)) {
                // 变量count是出现在字符串t中但还没有出现在字符串s中的子字符串中的字符的个数。
                if (count > 0) {// 如果某一时刻两个指针之间的子字符串还没有包含字符串t的所有字符，则在子字符串中添加新的字符，于是向右移动第2个指针。
                    char endCh = s.charAt(end);// 新遍历到的元素
                    // 扫描字符串s，每扫描一个字符，就检查哈希表中是否包含该字符。
                    // 如果哈希表中没有该字符，则说明该字符不是字符串t中的字符，可以忽略不计。
                    if (map.containsKey(endCh)) {
                        map.put(endCh, map.get(endCh) - 1);// 如果哈希表中存在该字符，则把该字符在哈希表中的对应值减1。
                        if (map.get(endCh) == 0) {// 哈希表中不含有该字符，count减1。说明当前滑动窗口中已经包含字符endCh
                            count--;
                        }
                    }
                    end++;// 滑动窗口右边界右移
                } else { // 如果某一时刻两个指针之间的子字符串已经包含字符串t的所有字符，
                    // 如果字符串s中包含字符串t的所有字符，那么哈希表中最终所有的值都应该小于或等于0。
                    if (end - start < minLen) { // 字符串s的子串含有字符串t的所有字符，那么这时候就需要找出符合要求的最短子字符串。
                        // 更新最小长度和，最小滑动窗口的左右边界。
                        minLen = end - start;
                        minStart = start;
                        minEnd = end;
                    }

                    // 尝试缩小当前的滑动窗口，将滑动窗口左边界元素移出滑动窗口和哈希表。
                    char startCh = s.charAt(start);// 滑动窗口的左边界
                    if (map.containsKey(startCh)) {// 当哈希表中含有滑动窗口左边界的元素
                        map.put(startCh, map.get(startCh) + 1);// 将哈希表中该元素的出现次数加1
                        if (map.get(startCh) == 1) {// 如果哈希表中该元素的次数为1，也就表明当前两个指针之间的子字符串还没有包含字符串t的所有字符
                            count++;
                        }
                    }
                    start++;// 滑动窗口左边界右移
                }
            }
            return minLen < Integer.MAX_VALUE ? s.substring(minStart, minEnd) : "";
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
