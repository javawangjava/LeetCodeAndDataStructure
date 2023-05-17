/**
 * <p>给定一个只包括 <code>'('</code>，<code>')'</code>，<code>'{'</code>，<code>'}'</code>，<code>'['</code>，<code>']'</code>
 * 的字符串 <code>s</code> ，判断字符串是否有效。</p>
 *
 * <p>有效字符串需满足：</p>
 *
 * <ol>
 * <li>左括号必须用相同类型的右括号闭合。</li>
 * <li>左括号必须以正确的顺序闭合。</li>
 * </ol>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "()"
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "()[]{}"
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "(]"
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "([)]"
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "{[]}"
 * <strong>输出：</strong>true</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 10<sup>4</sup></code></li>
 * <li><code>s</code> 仅由括号 <code>'()[]{}'</code> 组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>字符串</li></div></div><br><div><li>👍 3328</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 有效的括号
 */

public class ValidParentheses {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ValidParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public boolean isValid(String s) {
            if (s == null || s.length() == 0) {
                return true;
            }
            int len = s.length();
            if (len % 2 == 1) {
                return false;
            }
            Map<Character, Character> map = new HashMap<>();
            map.put(')', '(');
            map.put(']', '[');
            map.put('}', '{');
            Deque<Character> stack = new LinkedList<>();

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (map.containsKey(ch)) {
                    if (stack.isEmpty() || stack.peek() != map.get(ch)) {
                        return false;
                    }
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
            return stack.isEmpty();
        }




        // 思路：遍历字符串s,将遍历到的字符串s中的左括号压入栈中，当遍历到字符串中的右括号时，检查栈顶元素是否是和遍历到的右括号相匹配。
        //public boolean isValid(String s) {
        //    if (s == null || s.length() == 0) {
        //        return true;
        //    }
        //    int length = s.length();
        //    if (length % 2 == 1) {// 字符串是奇数个，肯定不配对
        //        return false;
        //    }
        //
        //    // 因为有3对括号，同一个字符可能有6种选择，用if-else语句选择太多。
        //    // 哈希表可以在O(1)时间检查当前遍历到的元素是否是右括号。哈希表也可以在O(1)找到右括号对应的左括号。
        //    // 哈希表中的键 key 是一对括号中的右括号，哈希表中的值value是与该右括号对应的左括号。
        //    Map<Character, Character> map = new HashMap<>();
        //    map.put(')', '(');
        //    map.put(']', '[');
        //    map.put('}', '{');
        //
        //
        //    Deque<Character> stack = new LinkedList<>();// 用栈来保存遍历到的左括号
        //    // 遍历字符串s,将遍历到的字符串s中的左括号压入栈中，当遍历到字符串中的右括号时，检查栈顶元素是否是和遍历到的右括号相匹配
        //    for (int i = 0; i < length; i++) {
        //        char ch = s.charAt(i);// 当前遍历到的字符串
        //        if (map.containsKey(ch)) {// 遇到右括号，检查栈顶元素是否是和该右括号对应的左括号，能配对就弹出栈配对。配不了对就返回false
        //            if (stack.isEmpty() || stack.peek() != map.get(ch)) {// 栈为空，或者栈顶元素和遍历到的右括号不能配对
        //                return false;
        //            }
        //            // 执行到这里栈不空，并且栈顶元素是与当前遍历到的右括号对应的左括号
        //            stack.poll();// 当栈顶元素和遍历到的右括号匹配时，将栈顶的左括号弹出栈
        //        } else {// 遇到左括号，压入栈
        //            stack.push(ch);
        //        }
        //    }
        //    return stack.isEmpty();// 遍历完字符串，如果栈为空就说明配对成功。如果栈不空则说明配对不成功
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
