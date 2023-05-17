/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * <p>
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= s.length <= 30
 * <p>
 * s 由小写英文字母、数字和方括号
 * '[]' 组成
 * s 保证是一个 有效 的输入。
 * s 中所有整数的取值范围为
 * [1, 300]
 * <p>
 * <p>
 * Related Topics 栈 递归 字符串 👍 1421 👎 0
 */

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 394
 * 字符串解码
 *
 * @author wangweizhou
 * @date 2023-03-01 00:29:06
 */

public class DecodeString {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new DecodeString().new Solution();
        //String str="3[a]2[bc]";
        String str = "3";
        String ans = solution.decodeString(str);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        ////
        //public String decodeString(String s) {
        //    if (s == null || s.length() == 0) {
        //        return s;
        //    }
        //    int len = s.length();
        //    char[] cs = s.toCharArray();
        //    Deque<Integer> numStack = new ArrayDeque<>();
        //    Deque<String> strStack = new ArrayDeque<>();
        //    for (int i = 0; i < len; i++) {
        //        if (cs[i] - '0' >= 0 && cs[i] - '0' <= 9) {
        //            int num = 0;
        //            while (i < len && cs[i] - '0' >= 0 && cs[i] - '0' <= 9) {
        //                num = num * 10 + (cs[i] - '0');
        //                i++;
        //            }
        //            numStack.push(num);
        //        }
        //        if (i >= len) {
        //            break;
        //        }
        //        if (cs[i] == '[') {
        //            strStack.push(String.valueOf(cs[i]));
        //        } else if (cs[i] == ']') {
        //            StringBuilder temp = new StringBuilder();
        //            while (!strStack.peek().equals("[")) {
        //                temp.insert(0, strStack.pop());
        //            }
        //            strStack.pop();
        //            int k = numStack.pop();
        //            StringBuilder sb = new StringBuilder();
        //            for (int j = 0; j < k; j++) {
        //                sb.append(temp);
        //            }
        //            strStack.push(sb.toString());
        //        } else {
        //            strStack.push(String.valueOf(cs[i]));
        //        }
        //    }
        //
        //    StringBuilder res = new StringBuilder();
        //    while (!strStack.isEmpty()) {
        //        res.insert(0, strStack.pop());
        //    }
        //    return res.toString();
        //}


        // 做法：双栈，逆波兰表达式做法 + 快速幂倍增思想
        // 与逆波兰表达式求值类似。本题需要两个栈，数字栈 numStack 用于存储重复次数， 字符传栈 strStack 用于存储字符以及拼接过程中的中间字符串。
        // 具体做法是遍历 s 根据遇到的不同字符执行如下操作：
        // 1.遇到数字，推入 numStack ，注意数字可能多于一个字符。
        // 2.遇到字母，推入 strStack 。
        // 3.遇到 "[" ，推入 strStack 。
        // 4.遇到 "]"，连续从 strStack 中推出栈顶字符串并拼接，直到遇到 "[" ，接着推出当前 numStack 栈顶数字 k ，将拼接好的字符串重复 k 次后推入 strStack 中。
        // 5.遍历结束时，strStack 就存放了所以重复好了的 "[]" 内的字符串，将他们拼接后返回即可。
        // 重复字符串时，我们可以利用类似快速幂的倍增做法加快重复过程。

        public String decodeString(String s) {
            if (s == null || s.length() == 0) {// 判空
                return s;
            }
            Deque<Integer> numStack = new ArrayDeque<>();// 数字栈
            Deque<String> strStack = new ArrayDeque<>();// 符号栈
            char[] chs = s.toCharArray();
            int len = chs.length;
            for (int i = 0; i < len; i++) {
                // chs[i] - '0'：将char类型字符转换为int型数字
                if (chs[i] - '0' >= 0 && chs[i] - '0' <= 9) { // 遍历遇到数字
                    // 获取以当前位置开始的连续数字
                    int num = 0;
                    while (i < len && chs[i] - '0' >= 0 && chs[i] - '0' <= 9) { // 截取数字
                        num = num * 10 + chs[i] - '0';
                        i++;
                    }
                    numStack.push(num);// 将获取的数字压入数字栈
                }
                // 上面获取数字之后，遍历指针后移了一位，所以在后续处理之前要先判断遍历指针i是否越界
                if (i >= len) {
                    break;
                }
                if (chs[i] == '[') {
                    strStack.push(String.valueOf(chs[i])); // 遇到 '[' 推入字符串栈
                } else if (chs[i] == ']') { // 遇到 ']'
                    // 遇到右括号时，将栈中的'['之前的字符弹出并拼接起来，
                    StringBuilder tempStr = new StringBuilder();// 变量curSb用来获取相邻的两个"[]"之间的字符串
                    // 栈顶不是左括号，则将每次栈顶的字符拼接获取相邻的左右括号之间的字符串。
                    while (!strStack.peek().equals("[")) { // 拼接 '[' 之前的字符串
                        tempStr.insert(0, strStack.pop());// 注意栈是先进后出，所以这里将后出的插入到字符串最前面
                    }
                    // 上面内层循环while结束就获取了左右括号之间的字符串部分
                    strStack.pop(); // 推出 '['。这时候推出字符栈中栈顶的左括号。要在新的字符串压入字符栈之前先弹出左括号'['。
                    int k = numStack.pop(); // 推出数字栈的栈顶数字，即一对中括号前面的数字，
                    String repeatedStr = repeate(tempStr.toString(), k); // 重复 k 次
                    strStack.push(repeatedStr); // 将中括号内部的字符串重复k次然后再推入字符串栈。将k[encoded_string]翻译之后重新加入字符栈，中括号可能会嵌套。
                } else {// 这里是遇到中括号中的字符串。String.valueOf(chs[i])：将字符转换为字符串并压入符号栈。
                    strStack.push(String.valueOf(chs[i]));
                }
            }
            StringBuilder res = new StringBuilder();
            while (!strStack.isEmpty()) {
                res.insert(0, strStack.pop());// 注意栈是先进后出，所以这里将后出的插入到字符串最前面
            }
            return res.toString();
        }


        // 快速幂思路，实在不会也可以循环实现。
        // 将字符串s重复k次并拼接在一起。
        private String repeate(String s, int k) { // 倍增拼接 (快速幂思想)
            StringBuilder res = new StringBuilder();
            StringBuilder sb = new StringBuilder(s);
            while (k > 0) {
                if (k % 2 == 1) {
                    res = res.append(sb);
                }
                sb = sb.append(sb);
                k /= 2;
            }
            return res.toString();
        }


        ////  写法2:
        //public String decodeString(String s) {
        //    StringBuilder res = new StringBuilder();
        //    int multi = 0;
        //    LinkedList<Integer> stack_multi = new LinkedList<>();
        //    LinkedList<String> stack_res = new LinkedList<>();
        //    for(Character c : s.toCharArray()) {
        //        if(c == '[') {
        //            stack_multi.addLast(multi);
        //            stack_res.addLast(res.toString());
        //            multi = 0;
        //            res = new StringBuilder();
        //        } else if(c == ']') {
        //            StringBuilder tmp = new StringBuilder();
        //            int cur_multi = stack_multi.removeLast();
        //            for(int i = 0; i < cur_multi; i++) {
        //                tmp.append(res);
        //            }
        //            res = new StringBuilder(stack_res.removeLast() + tmp);
        //        } else if(c >= '0' && c <= '9') {
        //            multi = multi * 10 + Integer.parseInt(c + "");
        //        } else {
        //            res.append(c);
        //        }
        //    }
        //    return res.toString();
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
