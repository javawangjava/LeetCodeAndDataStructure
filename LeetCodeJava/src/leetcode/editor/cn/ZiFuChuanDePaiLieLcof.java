/**
 * <p>输入一个字符串，打印出该字符串中字符的所有排列。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p>你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例:</strong></p>
 *
 * <pre><strong>输入：</strong>s = &quot;abc&quot;
 * <strong>输出：[</strong>&quot;abc&quot;,&quot;acb&quot;,&quot;bac&quot;,&quot;bca&quot;,&quot;cab&quot;,&quot;cba&quot;<strong>]</strong>
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <p><code>1 &lt;= s 的长度 &lt;= 8</code></p>
 * <div><div>Related Topics</div><div><li>字符串</li><li>回溯</li></div></div><br><div><li>👍 611</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 38
 * 字符串的排列
 *
 * @author wangweizhou
 * @date 2022-09-29 11:08:10
 */

public class ZiFuChuanDePaiLieLcof {

    public static void main(String[] args) {

        // 测试代码
        Solution solution = new ZiFuChuanDePaiLieLcof().new Solution();
        String str = "abc";
        String[] ans = solution.permutation(str);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：回溯法 写法1
        public String[] permutation(String s) {
            if (s == null) {
                return new String[0];
            }
            Set<String> res = new HashSet();// 用HashSet表示结果集合，HashSet不能保存重复元素
            backtrack(s.toCharArray(), new StringBuilder(), new boolean[s.length()], res);
            return res.toArray(new String[0]);// 字符串转数组
        }


        // 回溯函数
        // 第一个参数：char[] ch 参数字符数组或者字符串；第二个参数：StringBuilder sb 是一种排列；
        // 第三个参数：boolean[] visitid 标记数组标记当前字符是否已经访问过；第四个参数：Set<String> res 表示所有排列
        public void backtrack(char[] ch, StringBuilder sb, boolean[] visitid, Set<String> res) {
            if (sb.length() == ch.length) {// 当新排列sb的长度等于参数数组ch的长度时，将新排列加入结果集合中
                res.add(sb.toString());
                return;
            }

            // 函数是递归实现的，每次递归进入一层，就处理一个新的位置
            // 排列每一个位置的字符从原参数数组中选取，
            for (int i = 0; i < ch.length; i++) {
                // 通过标记数组来跳过已经访问的字符，每一个新位置的字符只能从剩余没有使用过的字符中选取
                if (visitid[i]) {// 剪枝，如果参数字符串当前位置i的元素已经使用过，则跳过进入下一个位置
                    continue;
                }
                // 执行到这里，那么位置i的字符没有使用过
                sb.append(ch[i]);// 将当前位置i的字符添加到可变字符串末尾
                visitid[i] = true;// 标记当前位置i的字符已经访问过
                backtrack(ch, sb, visitid, res);// 进入下层递归
                // 回溯，撤销上一步的修改
                sb.deleteCharAt(sb.length() - 1);// 撤销选择
                visitid[i] = false;// 标记当前位置i的字符没有访问过
            }
        }




        // 解法1：回溯法 写法2 与写法1的区别是将哈希表设置为全局参数
        //Set<String> res = new HashSet();
        //public String[] permutation(String s) {
        //	if(s==null){
        //		return new String[0];
        //	}
        //	backtrack(s.toCharArray(),new StringBuilder(), new boolean[s.length()]);
        //	return res.toArray(new String[0]);
        //}
        //
        //
        //// 回溯函数
        //public void backtrack(char[] ch,StringBuilder sb, boolean[] visitid){
        //	// 终止条件
        //	if(sb.length() == ch.length){
        //		res.add(sb.toString());
        //		return;
        //	}
        //
        //	// 选择列表
        //	for(int i = 0; i < ch.length; i++){
        //		if(visitid[i]) {// 剪枝，如果当前位置的元素已经使用过，则跳过进入下一个位置
        //			continue;
        //		}
        //		sb.append(ch[i]);// 做出选择
        //		visitid[i] = true;// 更新标记
        //		backtrack(ch,sb,visitid);// 进入下层回溯
        //		// 回溯
        //		sb.deleteCharAt(sb.length()-1);// 撤销选择
        //		visitid[i] = false;
        //	}
        //}






        // 看的不太懂
        // 把一个字符串看成由两部分组成：第一部分是它的第一个字符；第二部分是后面的所有字符。
        // 我们求整个字符串的排列，可以看成两步。第一步求所有可能出现在第一个位置的字符，即把第一个字符和后面所有的字符交换。
        // 第二步固定第一个字符，求后面所有字符的排列。这时候我们仍把后面的所有字符分成两部分：后面字符的第一个字符，以及这个字符之后的所有字符。然后把第一个字符逐一和它后面的字符交换。


        //// 解法2：交换
        //public String[] permutation(String s) {
        //    if (s == null) {
        //        return new String[0];
        //    }
        //    List<String> res = new LinkedList<>();// 结果集合
        //    char[] charArr = s.toCharArray();// 将字符串参数转换为字符数组
        //    dfs(charArr, 0, res);// 从第一个位置开始递归
        //    return res.toArray(new String[res.size()]);// 将最终结果转换成String数组类型
        //}
        //
        //
        //// charArr：字符数组参数或者字符串参数；index:表示字符数组参数的遍历指针；res:结果集合
        //private void dfs(char[] charArr, int index, List<String> res) {
        //    if (index == charArr.length - 1) {// 遍历到字符数组的最后一个元素
        //        res.add(String.valueOf(charArr));// 添加排列方案
        //        return;
        //    }
        //
        //    HashSet<Character> set = new HashSet<>();// 哈希表记录防止同一层的递归出现重复元素
        //    // 遍历[index,charArr.length-1]数组
        //    for (int i = index; i < charArr.length; i++) {
        //        if (set.contains(charArr[i])) {// 哈希表中已有该元素
        //            continue;
        //        }
        //        // 执行到这里哈希表中没有该位置对应的字符
        //        set.add(charArr[i]);// 将位置i处的字符加入到哈希表中
        //        swap(charArr, i, index); // 将index位置和i位置的元素进行交换
        //        dfs(charArr, index + 1, res); // 递归遍历下一个位置的字符
        //        swap(charArr, i, index); // 回溯，清除上一步的改变
        //    }
        //}
        //
        //
        //// 交换数组中下标为a和b的元素
        //private void swap(char[] charArr, int a, int b) {
        //    char tmp = charArr[a];
        //    charArr[a] = charArr[b];
        //    charArr[b] = tmp;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
