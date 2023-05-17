/**
 * <p>单词的 <strong>缩写</strong> 需要遵循 <起始字母><中间字母数><结尾字母> 这样的格式。如果单词只有两个字符，那么它就是它自身的 <strong>缩写</strong> 。</p>
 *
 * <p>以下是一些单词缩写的范例：</p>
 *
 * <ul>
 * <li><code>dog --> d1g</code> 因为第一个字母 <code>'d'</code> 和最后一个字母 <code>'g'</code> 之间有 <code>1</code> 个字母</li>
 * <li><code>internationalization --> i18n</code> 因为第一个字母 <code>'i'</code> 和最后一个字母 <code>'n'</code> 之间有
 * <code>18</code> 个字母</li>
 * <li><code>it --> it</code> 单词只有两个字符，它就是它自身的 <strong>缩写</strong></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p>实现 <code>ValidWordAbbr</code> 类：</p>
 *
 * <ul>
 * <li><code>ValidWordAbbr(String[] dictionary)</code> 使用单词字典 <code>dictionary</code> 初始化对象</li>
 * <li><code>boolean isUnique(string word)</code> 如果满足下述任意一个条件，返回 <code>true</code> ；否则，返回 <code>false</code> ：
 * <ul>
 * <li>字典 <code>dictionary</code> 中没有任何其他单词的 <strong>缩写</strong> 与该单词 <code>word</code> 的 <strong>缩写</strong> 相同。</li>
 * <li>字典 <code>dictionary</code> 中的所有 <strong>缩写</strong> 与该单词 <code>word</code> 的 <strong>缩写</strong> 相同的单词都与
 * <code>word</code> <strong>相同</strong> 。</li>
 * </ul>
 * </li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入</strong>
 * ["ValidWordAbbr", "isUnique", "isUnique", "isUnique", "isUnique", "isUnique"]
 * [[["deer", "door", "cake", "card"]], ["dear"], ["cart"], ["cane"], ["make"], ["cake"]]
 * <strong>输出
 * </strong>[null, false, true, false, true, true]
 *
 * <strong>解释</strong>
 * ValidWordAbbr validWordAbbr = new ValidWordAbbr(["deer", "door", "cake", "card"]);
 * validWordAbbr.isUnique("dear"); // 返回 false，字典中的 "deer" 与输入 "dear" 的缩写都是 "d2r"，但这两个单词不相同
 * validWordAbbr.isUnique("cart"); // 返回 true，字典中不存在缩写为 "c2t" 的单词
 * validWordAbbr.isUnique("cane"); // 返回 false，字典中的 "cake" 与输入 "cane" 的缩写都是 "c2e"，但这两个单词不相同
 * validWordAbbr.isUnique("make"); // 返回 true，字典中不存在缩写为 "m2e" 的单词
 * validWordAbbr.isUnique("cake"); // 返回 true，因为 "cake" 已经存在于字典中，并且字典中没有其他缩写为 "c2e" 的单词
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= dictionary.length <= 3 * 10<sup>4</sup></code></li>
 * <li><code>1 <= dictionary[i].length <= 20</code></li>
 * <li><code>dictionary[i]</code> 由小写英文字母组成</li>
 * <li><code>1 <= word <= 20</code></li>
 * <li><code>word</code> 由小写英文字母组成</li>
 * <li>最多调用 <code>5000</code> 次 <code>isUnique</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍
 * 17</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 288
 * 单词的唯一缩写
 * @author wangweizhou
 * @date 2022-07-06 19:49:04
 */
public class UniqueWordAbbreviation {
    public static void main(String[] args) {
        //测试代码
        //ValidWordAbbr solution = new UniqueWordAbbreviation().new ValidWordAbbr();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class ValidWordAbbr {

        // 为了判断一个单词的缩写在字典里是否是唯一的，我们判断它跟字典里所有其他单词是否满足如下所有条件：
        // 1.它们不是同一个单词。一个单词的缩写是唯一的当且仅当没有其他的字典中的单词与它有相同的缩写。
        // 2.它们有相同的长度
        // 3.它们开始字母和结束字母相同

        //isUnique(word) 的逻辑：
        // 单词的缩写是否在字典中出现过？如果没有，它就是唯一的。
        // 如果上述答案是出现过，它是唯一的条件是这个组里除了它本身 word 以外没有其他任何单词。

        String[] dictionary;

        public ValidWordAbbr(String[] dictionary) {
            this.dictionary=dictionary;
        }

        public boolean isUnique(String word) {
            int n = word.length();
            for (String str : dictionary) {
                if (word.equals(str)) {//dictionary中已有同一个单词
                    continue;
                }
                int m = str.length();
                //dictionary中有与word长度相同，首尾字母相同的单词，但不是同一个单词
                if (m == n
                        && str.charAt(0) == word.charAt(0)
                        && str.charAt(m - 1) == word.charAt(n - 1)) {
                    return false;
                }
            }
            return true;

        }
    }

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
