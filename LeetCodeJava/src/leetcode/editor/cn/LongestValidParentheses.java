/**
 * <p>给你一个只包含 <code>'('</code> 和 <code>')'</code> 的字符串，找出最长有效（格式正确且连续）括号子串的长度。</p>
 *
 * <p> </p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "(()"
 * <strong>输出：</strong>2
 * <strong>解释：</strong>最长有效括号子串是 "()"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = ")()())"
 * <strong>输出：</strong>4
 * <strong>解释：</strong>最长有效括号子串是 "()()"
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = ""
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= s.length <= 3 * 10<sup>4</sup></code></li>
 * <li><code>s[i]</code> 为 <code>'('</code> 或 <code>')'</code></li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>栈</li><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 1969</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 32
 * 最长有效括号
 *
 * @author wangweizhou
 * @date 2022-08-22 15:59:47
 */

public class LongestValidParentheses {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new LongestValidParentheses().new Solution();
        String s = "(()";
        int ans = solution.longestValidParentheses(s);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 序列长度问题对于区间左右开闭区间的处理。就是左右括号开闭时是否加1的区别。
        // 有效括号长度=当前索引-当前有效括号开始位置的前一个位置=当前索引 - 出栈后新的栈顶索引。
        // 有效括号长度=当前索引-当前有效括号开始位置+1=当前索引-（当前有效括号开始位置-1）。
        // 所以栈底要保存当前有效括号开始位置的前一个位置，其实也就是栈空时，栈中第一个右括号的位置。



        // 解法1：栈  注意这里是后进入的先配对弹出，符合后进先出的逻辑，所以使用栈来实现。
        // 题目是求长度，保存左括号的索引即可，没必要存符号本身。

        // 一段连续的括号子串开始位置肯定是从左括号位置开始的。
        // 情况1：一段连续括号子串的前一个位置是左括号，且该左括号没有与之匹配的右括号，那么这时候这一个左括号不会弹出栈；只有当出现与该左括号匹配的右括号时，该左括号才会出栈。
        // 情况2：一段连续括号子串的前一个位置是右括号，那么该右括号不会有匹配的左括号，这时候只有在一个连续括号序列结束之后又遇到一个有括号才会出栈。


        // 当前有效括号开始位置的前一个位置说明： 本质上这个（-1）就是假设一段连续括号子串的前一个位置是右括号。这样理解起来就轻松了。
        // 情况1：因为字符串下标0之前没有字符，则栈中提前压入（-1）作为参照物。【重要】。
        // 下标（-1）这一个在第一个从字符串下标0开始的连续括号时使用，当第一个连续括号结束时，下一个连续括号序列的开始位置肯定不是下标（-1），这时可以采用保存上一个连续括号序列结束时的位置。
        // 情况2：后续遍历过程中，若栈空并且遇到右括号，则将（-1）出栈，右括号的下标入栈，更新「最后一个没有被匹配的右括号的下标」


        // 步骤：
        // 1.扫描到左括号，就将当前位置⼊栈。
        // 2.扫描到右括号，就将栈顶元素出栈。这时候分为两种情况。栈顶出栈之后，那么栈可能为空或者不空。
        // 2.1：若栈顶的元素出栈后，栈不空，那么说明栈的栈顶元素是连续括号序列开始位置的前一个位置这时候就可以使用（当前位置-栈顶元素的位置）获得连续括号序列的长度。
        // 2.2：若栈顶的元素出栈后，栈空，那么说明栈的栈顶元素本来就是一个右括号，那么说明已经有一个连续括号序列在前一个位置结束。
        // 则新遍历到的右括号可能是新的连续括号序列开始位置的前一个位置，这时候需要将当前遍历到的右括号的位置入栈。

        public int longestValidParentheses(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int maxLen = 0;
            Deque<Integer> stack = new LinkedList<>();
            // 本质上这个（-1）就是假设一段连续括号子串的前一个位置是右括号。这样理解起来就轻松了。看前面的思路说明就可以。
            stack.push(-1);// 因为字符串下标0之前没有字符，则栈中提前压入（-1）作为参照物。来表示最后一个没有被匹配的右括号的下标。
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {// 左括号的索引，入栈
                    stack.push(i);
                } else {// 遍历到右括号
                    stack.pop();// 栈顶的元素出栈，这时的栈顶元素可能是与遍历到的右括号匹配的左括号，或者同样是一个右括号
                    if (stack.isEmpty()) {// 栈空，入栈当前位置。这时因为当前位置可能是下一个连续括号序列开始的前一个位置
                        stack.push(i);
                    } else {// 栈不空，说明弹出的肯定是一个左括号，那么就出现了左右括号成对出现的情况。这时候要更新括号子串的最大长度
                        maxLen = Math.max(maxLen, i - stack.peek()); // 挑战最大值
                    }
                }
            }
            return maxLen;
        }




        //// 解法2：暴力法
        //// 判断从字符串的每个位置开始的最⻓合法⼦串是多⻓即可。
        //// ⽽判断是否是合法⼦串，不⽤栈，⽽是⽤⼀个变量记录当前的括号情况，遇到左括号加 1，遇到右括号减 1，如果变成 0 ，那么就说明左右括号是成对出现的，我们就更新下最⻓合法⼦串。
        //public int longestValidParentheses(String s) {
        //    if(s==null||s.length()==0){// 判空
        //        return 0;
        //    }
        //    int len=s.length();
        //    char[] charArr=s.toCharArray();
        //    int maxLen=0;
        //    // 双层循环，外层循环遍历字符串的每一个字符，内层循环遍历外层循环之后的所有字符串，内层循环遍历时
        //    for (int i = 0; i < len; i++) {
        //        int count=0;// 子序列每次开始新的位置遍历，计数器重置
        //        for (int j = i; j < len; j++) {// 遍历[i，len-1]，每次遇到左括号计数器加1；每次遇到右括号计数器减1。
        //            if(charArr[j]=='('){// 遇到左括号加 1
        //                count++;
        //            }else if(charArr[j]==')'){// 遇到右括号减 1
        //                count--;
        //            }
        //
        //            if(count==0){// 计数器变成 0 ，当前是合法序列，更新最⻓⻓度
        //                maxLen=Math.max(maxLen,j-i+1);// 双闭区间
        //            }else if(count<0){// count < 0 说明右括号多了，此时⽆论后边是什么，⼀定是⾮法字符串了，所以可以提前结束循环
        //                break;
        //            }
        //        }
        //    }
        //    return maxLen;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
