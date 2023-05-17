/**
 * <p>给定一个仅包含数字&nbsp;<code>2-9</code>&nbsp;的字符串，返回所有它能表示的字母组合。答案可以按 <strong>任意顺序</strong> 返回。</p>
 *
 * <p>给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。</p>
 *
 * <p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/11/09/200px-telephone-keypad2svg.png"
 * style="width: 200px;" /></p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>digits = "23"
 * <strong>输出：</strong>["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>digits = ""
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>digits = "2"
 * <strong>输出：</strong>["a","b","c"]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= digits.length &lt;= 4</code></li>
 * <li><code>digits[i]</code> 是范围 <code>['2', '9']</code> 的一个数字。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>回溯</li></div></div><br><div><li>👍 1958</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17
 * 电话号码的字母组合
 */

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    class Solution {

        //// 解法1：回溯法
        public List<String> letterCombinations(String digits) {
            List<String> combinations = new ArrayList<>();// 最终输出结果的combinations
            if (digits == null || digits.length() == 0) {
                return combinations;
            }
            // 数组模拟哈希表。数组的下标是哈希表的键，数组的值是哈希表的值
            // 一个映射表，第二个位置是"abc“,第三个位置是"def"。。。这里也可以用map，用数组可以更节省点内存
            String[] phoneKeyMap = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            backtrack(digits, 0, phoneKeyMap, new StringBuilder(), combinations);
            return combinations;
        }


        //// 回溯算法
        //// StringBuffer combination：一种可能的字母映射组合。List<String> combinations：所有字母映射的组合；
        //// String digits：数字字符串；int index：数字字符串digits的第index个字符的指针；Map<Character, String> phoneMap：数字与字母的映射表;
        private void backtrack(String digits, int index, String[] phoneKeyMap, StringBuilder combination,
                               List<String> combinations) {
            if (index == digits.length()) {// 遍历完了数字字符串的所有字符,那么就获得了一种可能的字母映射组合,和下面的作用一样
                // 可变字符串combination长度等于digits长度，即遍历完了整个字符串digits,将一种字母组合加入到combinations中
                combinations.add(combination.toString());
            } else {
                //（digits.charAt(index)-'0'）获取字符串digits中index指向的数字，char可以自动转换为int进行运算
                // phoneKeyMap[digit - '0']：获取字符串digits中index位置的字符在phoneMapp中对应的映射letters
                char digit = digits.charAt(index);
                String letters = phoneKeyMap[digit - '0'];//获取字符串digits中index指向的数字在字符串数组phoneMap中的对应字符串letters
                int len = letters.length();
                for (int i = 0; i < len; i++) {//遍历数字在对应的映射中的字符串phoneMap
                    combination.append(letters.charAt(i));//将对应映射的字符串中的元素每次选出一个后缀在动态数组中
                    backtrack(digits, index + 1, phoneKeyMap, combination, combinations);// 递归遍历数字字符串的下一个位置的元素
                    combination.deleteCharAt(index);// 回溯，清除上一步的修改
                }
            }
        }



        // 解法2:
        // 首先使用哈希表存储每个数字对应的所有可能的字母，然后进行回溯操作。
        // 回溯过程中维护一个字符串，表示已有的字母排列（如果未遍历完电话号码的所有数字，则已有的字母排列是不完整的）。
        // 该字符串初始为空。每次取电话号码的一位数字，从哈希表中获得该数字对应的所有可能的字母，并将其中的一个字母插入到已有的字母排列后面，
        // 然后继续处理电话号码的后一位数字，直到处理完电话号码中的所有数字，即得到一个完整的字母排列。
        // 然后进行回退操作，遍历其余的字母排列。
        // 回溯算法用于寻找所有的可行解，如果发现一个解不可行，则会舍弃不可行的解。
        // 在这道题中，由于每个数字对应的每个字母都可能进入字母组合，因此不存在不可行的解，直接穷举所有的解即可。


        //public List<String> letterCombinations(String digits) {
        //    List<String> combinations = new ArrayList<>();// 所有组合的结果
        //    if (digits == null || digits.length() == 0) {
        //        return combinations;
        //    }
        //    // 初始化哈希表，使用哈希表存储每个数字和该数字对应的字母组合。
        //    // 哈希表的键key是数字的字符类型，哈希表的值value是该键对应字母组成的字符串。
        //    Map<Character, String> phoneMap = new HashMap<>() {
        //        {
        //            put('2', "abc");
        //            put('3', "def");
        //            put('4', "ghi");
        //            put('5', "jkl");
        //            put('6', "mno");
        //            put('7', "pqrs");
        //            put('8', "tuv");
        //            put('9', "wxyz");
        //        }
        //    };
        //    backtrack(digits, 0, phoneMap, new StringBuffer(), combinations);
        //    return combinations;
        //}
        //
        //
        //// StringBuffer combination：一种可能的字母映射组合。List<String> combinations：所有字母映射的组合；
        //// String digits：数字字符串；int index：数字字符串digits的第index个字符的指针；Map<Character, String> phoneMap：数字与字母的映射表;
        //private void backtrack(String digits, int index, Map<Character, String> phoneMap,
        //                       StringBuffer combination, List<String> combinations) {
        //    if (index == digits.length()) {// 遍历完了数字字符串的所有字符,那么就获得了一种可能的字母映射组合
        //        combinations.add(combination.toString());
        //    } else {
        //        char digit = digits.charAt(index);// 获取数字字符串的第index个字符，即电话中的某一个数字按键。
        //        String letters = phoneMap.get(digit);// 获取数字digit对应的字母映射的字符串。即电话中的某一个数字按键映射的字符串。
        //        int lettersLen = letters.length();// 数字digit对应的字母映射的字符串长度。即电话中的某一个数字按键映射的字符串的长度。
        //        for (int i = 0; i < lettersLen; i++) {// 遍历数字映射的字符串的每一个字母
        //            combination.append(letters.charAt(i));//
        //            backtrack(digits, index + 1, phoneMap, combination, combinations);
        //            combination.deleteCharAt(index);// 回溯，清除上一步的修改
        //        }
        //    }
        //
    }


//leetcode submit region end(Prohibit modification and deletion)

}
