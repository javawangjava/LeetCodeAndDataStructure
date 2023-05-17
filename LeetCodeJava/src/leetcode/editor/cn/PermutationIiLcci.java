/**
 * <p>有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。</p>
 *
 * <p><strong>示例1:</strong></p>
 *
 * <pre><strong> 输入</strong>：S = &quot;qqe&quot;
 * <strong> 输出</strong>：[&quot;eqq&quot;,&quot;qeq&quot;,&quot;qqe&quot;]
 * </pre>
 *
 * <p><strong>示例2:</strong></p>
 *
 * <pre><strong> 输入</strong>：S = &quot;ab&quot;
 * <strong> 输出</strong>：[&quot;ab&quot;, &quot;ba&quot;]
 * </pre>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ol>
 * <li>字符都是英文字母。</li>
 * <li>字符串长度在[1, 9]之间。</li>
 * </ol>
 * <div><div>Related Topics</div><div><li>字符串</li><li>回溯</li></div></div><br><div><li>👍 70</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 面试题 08.08
 * 有重复字符串的排列组合
 *
 * @author wangweizhou
 * @date 2022-08-02 10:39:53
 */
public class PermutationIiLcci {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new PermutationIiLcci().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：回溯法+标记数组+排序
        public String[] permutation(String S) {
            if (S == null || S.length() == 0) {//判空
                return null;
            }
            int length = S.length();
            char[] charS = S.toCharArray();//转字符数组
            Arrays.sort(charS);//先按字典序排序，使重复字符串相邻

            List<String> list = new ArrayList<>();//可变数组用来保存字符串数组所有的全排列
            StringBuffer strBu = new StringBuffer();//用来保存字符串数组的一个全排列
            boolean[] isVisited = new boolean[length];//标记每个位置的字符是否被使⽤过

            backtrace(list, charS, strBu, isVisited);// 递归获取
            // 创建返回数组，并将list中元素保存到返回数组中
            String[] ans = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }


        private void backtrace(List<String> list, char[] charS, StringBuffer strBu, boolean[] isVisited) {
            //当可变字符串strBu长度等于字符串长度，即字符串的所有字符已经加入到可变字符串中
            if (strBu.length() == charS.length) { //临时字符串满了加⼊输出list
                list.add(new String(strBu));
                return;
            }

            for (int i = 0; i < charS.length; i++) {//遍历所有元素选取⼀个加⼊
                //如果该元素已经被加⼊了则不需要再加⼊了
                //当前的元素str[i]与同⼀层的前⼀个元素str[i-1]相同且str[i-1]已经⽤过了
                if (isVisited[i] || (i > 0 && charS[i - 1] == charS[i] && !isVisited[i - 1])) {
                    continue;
                }
                isVisited[i] = true;//标记为使⽤过
                strBu.append(charS[i]);//加⼊临时字符串
                backtrace(list, charS, strBu, isVisited);
                //回溯
                isVisited[i] = false;//标记为未使⽤过
                strBu.deleteCharAt(strBu.length() - 1);//删除临时字符串的最后一个字母
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
