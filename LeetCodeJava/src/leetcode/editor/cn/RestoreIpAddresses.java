/**
 * <p><strong>有效 IP 地址</strong> 正好由四个整数（每个整数位于 <code>0</code> 到 <code>255</code> 之间组成，且不能含有前导 <code>0</code>），整数之间用
 * <code>'.'</code> 分隔。</p>
 *
 * <ul>
 * <li>例如：<code>"0.1.2.201"</code> 和<code> "192.168.1.1"</code> 是 <strong>有效</strong> IP 地址，但是 <code>"0.011.255
 * .245"</code>、<code>"192.168.1.312"</code> 和 <code>"192.168@1.1"</code> 是 <strong>无效</strong> IP 地址。</li>
 * </ul>
 *
 * <p>给定一个只包含数字的字符串 <code>s</code> ，用以表示一个 IP 地址，返回所有可能的<strong>有效 IP 地址</strong>，这些地址可以通过在 <code>s</code> 中插入&nbsp;
 * <code>'.'</code> 来形成。你 <strong>不能</strong>&nbsp;重新排序或删除 <code>s</code> 中的任何数字。你可以按 <strong>任何</strong> 顺序返回答案。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "25525511135"
 * <strong>输出：</strong>["255.255.11.135","255.255.111.35"]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "0000"
 * <strong>输出：</strong>["0.0.0.0"]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "101023"
 * <strong>输出：</strong>["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 20</code></li>
 * <li><code>s</code> 仅由数字组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>回溯</li></div></div><br><div><li>👍 1096</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 93
 * 复原 IP 地址
 *
 * @author wangweizhou
 * @date 2022-12-11 14:53:47
 */

public class RestoreIpAddresses {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new RestoreIpAddresses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 首先总结IP地址的特点。一个IP地址被3个'.'字符分隔成4段，每段是从0到255之间的一个数字。
        // 另外，除"0"本身外，其他数字不能以'0'开头。
        // 下面逐个扫描输入字符串中的字符以恢复IP地址。针对字符串中的每个数字，通常面临两个选项。
        // 第1个选项是将当前字符拼接到当前分段数字的末尾，拼接之后的数字应该在0到255之间。
        // 第2个选项是当前字符作为一个新的分段数字的开始。
        // 需要注意的是，一个IP地址最多只有4个分段数字，并且当开始一个新的分段数字时前一个分段数字不能是空的。

        // 例如，当输入字符串"10203040"并处理其中的字符'2'时，已经得到一个分段数字"10"。
        // 此时既可以将字符'2'拼接到当前分段数字的末尾，得到"102"，也可以将字符'2'作为一个新的分段数字的开始，得到"10.2"。
        // 如果输入的字符串的长度为n，由于逐一处理字符串中的每个字符，因此需要n步，并且每一步都面临两个可能的选项。由此可见，这个题目也适合采用回溯法解决。


        //// 解法1：回溯法
        //public List<String> restoreIpAddresses(String s) {
        //    List<String> lists = new LinkedList<>();
        //    if (s == null || s.length() > 12 || s.length() < 4) {// 判空
        //        return lists;
        //    }
        //    func(s, 0, 0, "", "", lists);
        //    return lists;
        //}
        //
        //
        //// 递归函数func的参数index是字符串str中当前被处理的字符的下标。
        //// 参数segI是当前分段数字的下标，由于IP地址有4个分段数字，因此参数segI的取值范围是从0到4。
        //// 参数seg是当前已经恢复的一个分段数字，而参数ip是当前已经恢复的IP地址，已经恢复的ip地址除了完全恢复的，每一个小段的后面都是分隔符"."。
        //// 在递归函数func中，如果将当前字符（变量ch）拼接到当前分段数字seg之后得到一个有效的分段数字，那么首先选择将字符ch拼接到seg的末尾。
        //// 如果当前的分段数字seg不为空并且已经恢复的分段数字的数目少于4个，那么还可以选择将ch作为一个新的分段数字的开始。
        //// 当字符串s中所有字符都已经处理完时，刚好恢复了4个分段数字并且当前得到一个有效的分段数字，那么恢复了一个完整的IP地址，可以添加到返回值中。
        //private void func(String str, int index, int segI, String seg, String ip, List<String> lists) {
        //    if(str.length()-index>(4-segI)*3){
        //        return;
        //    }
        //    // 根据后面的代码逻辑，是在前面3段每一段的后面都加一个"."号来分割IP地址
        //    if (index == str.length() && segI == 3 && isValidSeg(seg)) {
        //        // index == str.length()：遍历完了字符串；segI == 3：当前分段数是3，即字符串已经分好前3段了，并且第三段后面已经有分隔符"."；
        //        // isValidSeg(seg)：最后一个分段【也就是第4个分段】是有效分段
        //        lists.add(ip + seg);// 将第4个分段和前面已经恢复的分段连接起来，添加到结果集合中
        //    } else if (index < str.length() && segI <= 3) {// 当前处理的字符位置没有越界，当前已经获得的合法的ip分段数小于等于3个
        //
        //        // 没有遍历完字符串并且分段数<=3。
        //        char ch = str.charAt(index);
        //
        //        // 遍历到字符串中的每个数字，第一种选择是将该字符拼接到当前分段数字的末尾。当然拼接前要检查拼接后的分段是否有效
        //        if (isValidSeg(seg + ch)) {  // 如果将当前字符（变量ch）拼接到当前分段数字seg之后得到一个有效的分段数字，那么首先选择将字符ch拼接到seg的末尾。
        //            // 从下一个字符开始遍历，当前的分段数不变，当前分段拼接当前的字符
        //            func(str, index + 1, segI, seg + ch, ip, lists);
        //        }
        //        // 第二种选择是将当前字符作为一个新的分段数字的开始。
        //        // 如果当前的分段数字seg不为空并且已经恢复的分段数字的数目少于3个
        //        if (seg.length() > 0 && segI < 3) {
        //            func(str, index + 1, segI + 1, "" + ch, ip + seg + ".", lists);
        //        }
        //    }
        //}
        //
        //
        //
        //// 判断当前分段是不是有效的分段
        //// 一个IP地址被3个'.'字符分隔成4段，每段是从0到255之间的一个数字。另外，除"0"本身外，其他数字不能以'0'开头。
        //private boolean isValidSeg(String seg) {
        //    // 除"0"本身外，其他数字不能以'0'开头：seg.equals("0") || seg.charAt(0) != '0')
        //    if (Integer.valueOf(seg) <= 255 && (seg.equals("0") || seg.charAt(0) != '0')) {
        //        return true;
        //    }
        //    return false;
        //}




        // 解法2：回溯法
        // 画图理解
        public List<String> restoreIpAddresses(String s) {
            List<String> lists = new ArrayList<>();// 定义一个返回结果的集合
            if (s == null || s.length() > 12 || s.length() < 4) {// 如果当前字符长度大于12或者小于4都不满足
                return lists;
            }
            Deque<String> path = new ArrayDeque<>(); // 定义一个保存路径上的变量,
            int len = s.length(); //定义表示一个字符长度的变量
            //深度优先搜索
            dfs(s, len, 0, 4, path, lists);
            //返回结果
            return lists;
        }



        // 第2个参数len是字符串s的长度；第3个参数begin是字符串s中当前被处理的字符的下标；
        // 第4个参数residue是剩余的分段数；第5个参数path是当前已经恢复的一个分段数字
        public void dfs(String s, int len, int begin, int residue, Deque<String> path, List<String> lists) {
            //如果字符串已经遍历到最后了，并且已经切分为4段了，就把当前路径上的元素加入到返回的结果集中
            if (begin == len) {
                if (residue == 0) {
                    // static String join(CharSequence delimiter, CharSequence... elements)
                    // 返回由 CharSequence elements的副本组成的新String，该副本与指定的 delimiter的副本连接在一起。
                    lists.add(String.join(".", path));
                }
                return;
            }

            // begin表示遍历字符串从哪里开始，ip的每一个分段长度不超过3个字符
            for (int i = begin; i < begin + 3; i++) {
                // begin，每次选择都是从之前选择的元素的下一个元素开始，
                if (i >= len) {//如果超出字符串的长度，就直接退出
                    break;
                }
                if (len - i > residue * 3) {// 如果剩余元素大于ip最多能容纳的个数，就剪枝。
                    continue;
                }
                //判断当前截取字符获得的子字符串[begin,i]是否是小于0或者大于255
                if (judgeIpSegment(s, begin, i)) {
                    String currentIpSegment = s.substring(begin, i + 1);  //保留当前截取字符获得子字符串[begin,i]
                    path.addLast(currentIpSegment);//将当前路径上的元素加入到路径队列中
                    //递归下一层，从分割的字符串的
                    dfs(s, len, i + 1, residue - 1, path, lists);
                    //剪枝
                    path.removeLast();
                }
            }
        }



        // 判断[left,right]是否是合法的ip分段
        private boolean judgeIpSegment(String s, int left, int right) {
            int len = right - left + 1;// 定义一个变量len表示整个子字符串[left,right]的长度
            if (len > 1 && s.charAt(left) == '0') {// 如果子字符串的长度大于1，且子字符串的第一个字符是'0'，就直接false
                return false;
            }
            int res = 0;// res表示子字符串[left,right]的数字形式
            while (left <= right) {// 将子串[left,right]转换成数字形式
                res = res * 10 + s.charAt(left) - '0';
                left++;
            }
            return res >= 0 && res <= 255;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
