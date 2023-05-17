/**
 * <p>根据<a href="https://baike.baidu.com/item/%E9%80%86%E6%B3%A2%E5%85%B0%E5%BC%8F/128437" target="_blank"> 逆波兰表示法</a>
 * ，求表达式的值。</p>
 *
 * <p>有效的算符包括&nbsp;<code>+</code>、<code>-</code>、<code>*</code>、<code>/</code>&nbsp;。每个运算对象可以是整数，也可以是另一个逆波兰表达式。</p>
 *
 * <p><b>注意&nbsp;</b>两个整数之间的除法只保留整数部分。</p>
 *
 * <p>可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>tokens = ["2","1","+","3","*"]
 * <strong>输出：</strong>9
 * <strong>解释：</strong>该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * </pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>tokens = ["4","13","5","/","+"]
 * <strong>输出：</strong>6
 * <strong>解释：</strong>该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * </pre>
 *
 * <p><strong>示例&nbsp;3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * <strong>输出：</strong>22
 * <strong>解释：</strong>该算式转化为常见的中缀算术表达式为：
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= tokens.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>tokens[i]</code>&nbsp;是一个算符（<code>"+"</code>、<code>"-"</code>、<code>"*"</code> 或 <code>"/"</code>），或是在范围
 * <code>[-200, 200]</code> 内的一个整数</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>逆波兰表达式：</strong></p>
 *
 * <p>逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。</p>
 *
 * <ul>
 * <li>平常使用的算式则是一种中缀表达式，如 <code>( 1 + 2 ) * ( 3 + 4 )</code> 。</li>
 * <li>该算式的逆波兰表达式写法为 <code>( ( 1 2 + ) ( 3 4 + ) * )</code> 。</li>
 * </ul>
 *
 * <p>逆波兰表达式主要有以下两个优点：</p>
 *
 * <ul>
 * <li>去掉括号后表达式无歧义，上式即便写成 <code>1 2 + 3 4 + * </code>也可以依据次序计算出正确结果。</li>
 * <li>适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>数学</li></div></div><br><div><li>👍 593</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 150
 * 逆波兰表达式求值
 *
 * @author wangweizhou
 * @date 2022-08-29 17:28:16
 */

public class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new EvaluateReversePolishNotation().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 后缀表达式又叫逆波兰式（Reverse Polish Notation，RPN），是一种将操作符放在操作数后面的算术表达式。
        // 通常用的是中缀表达式，即操作符位于两个操作数的中间，如“2+1*3”。使用后缀表达式的好处是不需要使用括号。
        // 由于栈中只保存操作数，操作符不需要保存到栈中，因此上述代码创建的是一个整数型栈。
        // 上述代码逐一扫描后缀表达式数组中的每个字符串。如果遇到的是一个操作数，则将其入栈；
        // 如果遇到的是一个操作符，则操作符前面的两个操作数出栈并执行相应的运算，然后计算结果入栈。


        //// 后缀表达式：栈模拟
        public int evalRPN(String[] tokens) {
            if (tokens == null || tokens.length == 0) {// 判空处理这里是自己约定的
                return Integer.MIN_VALUE;
            }
            Deque<Integer> stack = new LinkedList<>();
            for (String token : tokens) {// 遍历字符串的字符
                switch (token) {
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        // 遇到运算符则弹出两个运算数，并进行相应的计算
                        int num1 = stack.pop();
                        int num2 = stack.pop();
                        stack.push(calculate(num2, num1, token));
                        break;
                    default:// 遇到运算数，则入栈
                        stack.push(Integer.parseInt(token));
                }
            }
            return stack.pop();
        }



        private int calculate(int num1, int num2, String operator) {
            switch (operator) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    return num1 / num2;
                default:
                    return 0;
            }
        }



        // 和上面一样。只是遍历方式有一点不同
        //public int evalRPN(String[] tokens) {
        //    if (tokens == null || tokens.length == 0) {// 判空处理这里是自己约定的
        //        return Integer.MAX_VALUE;
        //    }
        //    Deque<Integer> stack = new LinkedList<>();
        //    for (int i = 0; i < tokens.length; i++) {// 遍历字符串的字符
        //        switch (tokens[i]) {
        //            case "+":
        //            case "-":
        //            case "*":
        //            case "/": {
        //                // 遇到运算符则弹出两个运算数，并进行相应的计算
        //                int num1 = stack.pop();
        //                int num2 = stack.pop();
        //                stack.push(calculate(num2, num1, tokens[i]));
        //                break;// 防止穿透
        //            }
        //            default:{
        //                // 遇到运算数，则入栈
        //                stack.push(Integer.valueOf(tokens[i]));
        //            }
        //        }
        //    }
        //    return stack.pop();
        //}
        //
        //
        //private int calculate(int num1, int num2, String cal) {
        //    switch (cal) {
        //        case "+":
        //            return num1 + num2;
        //        case "-":
        //            return num1 - num2;
        //        case "*":
        //            return num1 * num2;
        //        case "/":
        //            return num1 / num2;
        //        default:
        //            return 0;
        //    }
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
