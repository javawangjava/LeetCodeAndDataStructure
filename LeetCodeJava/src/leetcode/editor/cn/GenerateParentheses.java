/**
 * <p>数字 <code>n</code>&nbsp;代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 <strong>有效的 </strong>括号组合。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 3
 * <strong>输出：</strong>["((()))","(()())","(())()","()(())","()()()"]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1
 * <strong>输出：</strong>["()"]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 8</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li><li>回溯</li></div></div><br><div><li>👍 2789</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 22
 * 括号生成
 *
 * @author wangweizhou
 * @date 2022-08-02 21:39:17
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new GenerateParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 如果输入n，那么生成的括号组合包含n个左括号和n个右括号。
        //// 因此生成这样的组合需要2n步，每一步生成一个括号。每一步都面临两个选项，既可能生成左括号也可能生成右括号。
        ////	在生成括号组合时需要注意每一步都要满足限制条件。第1个限制条件是左括号或右括号的数目不能超过n个。
        ////	第2个限制条件是括号的匹配原则，即在任意步骤中已经生成的右括号的数目不能超过左括号的数目。
        //
        //// 解法1：回溯法  写法2 下面程序采用的减数的操作
        //// 注意下面使用的是可变字符串，可变字符串是在原字符串的基础上进行操作的，所以回溯的时候要清除最后一步的修改
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();//记录结果
            if (n < 0) {
                return result;
            }
            StringBuilder str = new StringBuilder();//可变字符串
            generateParenthesisFunc(n, n, str, result);//
            return result;
        }


        // 参数left表示还需要生成左括号的数目，参数right表示还需要生成右括号的数目。
        // 每生成一个左括号，参数left减1；每生成一个右括号，参数right减1。当参数left和right都等于0时，一个完整的括号组合已经生成。
        // 在生成一个括号时，只要已经生成的左括号的数目少于n个（即参数left大于0）就可能生成一个左括号，
        // 只要已经生成的右括号的数目少于已经生成的左括号的数目（即参数left小于right）就可能生成一个右括号。
        private void generateParenthesisFunc(int left, int right, StringBuilder combination, List<String> result) {
            if (left == 0 && right == 0) {
                result.add(combination.toString());
                return;
            }
            if (left > 0) {//使⽤⼀次左括号,剩余的左括号数大于0
                generateParenthesisFunc(left - 1, right, combination.append("("), result);// 在括号组合中添加一个新的左括号
                combination.deleteCharAt(combination.length() - 1);//回溯，删除最后一次修改的状态
            }
            if (left < right) {//使⽤右括号个数必须少于左括号，剩余的右括号数大于左括号数
                generateParenthesisFunc(left, right - 1, combination.append(")"), result);
                combination.deleteCharAt(combination.length() - 1);
            }
        }




        //// 解法1：回溯法  写法3 下面程序采用的减数的操作
        //// 注意下面用的时字符串拼接，java中字符串拼接会产生新的字符串，所以不需要回溯。
        //public List<String> generateParenthesis(int n) {
        //    List<String> result = new ArrayList<>();//记录结果
        //    generateParenthesisFunc(n, n, "", result);//
        //    return result;
        //}
        //
        //
        //// 参数left表示还需要生成左括号的数目，参数right表示还需要生成右括号的数目。
        //// 每生成一个左括号，参数left减1；每生成一个右括号，参数right减1。当参数left和right都等于0时，一个完整的括号组合已经生成。
        //// 在生成一个左括号时，只要已经生成的左括号的数目少于n个（即参数left大于0）就可能生成一个左括号，
        //// 只要已经生成的右括号的数目少于已经生成的左括号的数目（即参数left小于right）就可能生成一个右括号。
        //private void generateParenthesisFunc(int left, int right, String combination, List<String> result) {
        //    if (left == 0 && right == 0) {
        //        result.add(combination);
        //        return;
        //    }
        //    if (left > 0) {
        //        generateParenthesisFunc(left - 1, right, combination + "(", result);
        //    }
        //    if (left < right) {
        //        generateParenthesisFunc(left, right - 1, combination + ")", result);
        //    }
        //}




        //// 解法1：回溯法 可变字符串需要回溯，清除上一步的修改  下面采用累加计数
        //public List<String> generateParenthesis(int n) {
        //    List<String> lists = new ArrayList<>();//记录结果
        //    if (n < 0) {
        //        return lists;
        //    }
        //    StringBuilder sb = new StringBuilder();//可变字符串
        //    generateParenthesisFunc(0, 0, n, sb, lists);
        //    return lists;
        //}
        //
        //
        //private void generateParenthesisFunc(int left, int right, int count, StringBuilder sb,
        //                                     List<String> lists) {
        //    if (left == count && right == count) {// 左右括号都是count个时，就加⼊结果
        //        lists.add(sb.toString());
        //        return;
        //    }
        //    if (left < count) {// 当左括号数小于count,使⽤⼀次左括号
        //        generateParenthesisFunc(left + 1, right, count, sb.append("("), lists);
        //        sb.deleteCharAt(sb.length() - 1);
        //    }
        //    if (right < count && right < left) {// 当右括号数小于count，且已经选择的左括号数大于右括号数
        //        generateParenthesisFunc(left, right + 1, count, sb.append(")"), lists);
        //        sb.deleteCharAt(sb.length() - 1);
        //    }
        //}




        //// 解法2：递归
        //// 字符串直接相加会产生很多的临时变量。"qwe"+"2"
        //
        //public List<String> generateParenthesis(int n) {
        //    List<String> ans = new ArrayList<>();//记录结果
        //    recursion(0, 0, n, "", ans);//递归
        //    return ans;
        //}
        //
        //
        //// left表示左括号个数，right表示右括号个数，count表示括号对数
        //private void recursion(int left, int right, int count,String str,List<String> ans) {
        //
        //    if (left == count && right == count) {//左右括号都⽤完了，就加⼊结果
        //        ans.add(str);
        //        return;
        //    }
        //
        //	//先使用左括号，左括号数目小于count
        //    if (left < count) {//使⽤⼀次左括号
        //        recursion(left + 1, right, count, str + "(", ans);
        //    }
        //	//后使用右括号，并且左括号数目大于右括号数目
        //    if (right < count && left > right) { //使⽤右括号个数必须少于左括号
        //        recursion(left, right + 1, count, str + ")", ans);
        //    }
        //
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
