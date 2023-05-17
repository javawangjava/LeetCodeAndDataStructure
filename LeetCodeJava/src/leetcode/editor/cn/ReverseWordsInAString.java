/**
 * <p>给你一个字符串 <code>s</code> ，颠倒字符串中 <strong>单词</strong> 的顺序。</p>
 *
 * <p><strong>单词</strong> 是由非空格字符组成的字符串。<code>s</code> 中使用至少一个空格将字符串中的 <strong>单词</strong> 分隔开。</p>
 *
 * <p>返回 <strong>单词</strong> 顺序颠倒且 <strong>单词</strong> 之间用单个空格连接的结果字符串。</p>
 *
 * <p><strong>注意：</strong>输入字符串 <code>s</code>中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "<code>the sky is blue</code>"
 * <strong>输出：</strong>"<code>blue is sky the</code>"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = " &nbsp;hello world &nbsp;"
 * <strong>输出：</strong>"world hello"
 * <strong>解释：</strong>颠倒后的字符串中不能存在前导空格和尾随空格。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "a good &nbsp; example"
 * <strong>输出：</strong>"example good a"
 * <strong>解释：</strong>如果两个单词间有多余的空格，颠倒后的字符串需要将单词间的空格减少到仅有一个。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>s</code> 包含英文大小写字母、数字和空格 <code>' '</code></li>
 * <li><code>s</code> 中 <strong>至少存在一个</strong> 单词</li>
 * </ul>
 *
 * <ul>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用&nbsp;<code>O(1)</code> 额外空间复杂度的 <strong>原地</strong> 解法。</p>
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 581</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 151
 * 颠倒字符串中的单词
 *
 * @author wangweizhou
 * @date 2022-06-29 16:34:40
 */

public class ReverseWordsInAString {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ReverseWordsInAString().new Solution();
        String s = "   the    sky is blue   ";
        String str = solution.reverseWords(s);
        System.out.println(str);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 解法1：使用快慢指针从字符串右端开始遍历
        //// 从右向左的滑动窗口，滑动窗口里装每个单词。 left指向单词左边的空格，所以可以在字符串的左边提前添加一个空格“ ”，做预处理，统一处理方式。
        //// （left,right]也就是[left+1,right]里面存储的是单词。

        //public String reverseWords(String s) {
        //    if (s == null || s.length() == 0) {
        //        return s;
        //    }
        //    s = " " + s;// left最终指向单词前面的那个空格，所以这里进行预处理，不管原来单词左边有无空格，在字符串的最左侧添加一个空格，预处理
        //    int len = s.length();
        //    StringBuilder ans = new StringBuilder();
        //    // 使用left遍历整个字符串，left表示单词第一个字母的前一位，right表示单词的最后一个字母【这里注意使用API时right要指向单词最后一个字母的后一个位置】。
        //    // 因为StringBuilder append(CharSequence s, int start, int end) 将指定的 CharSequence序列追加到此序列。包左不包右。
        //    // 所以滑动窗口的左边界从字符串的最后一个元素开始，right应该从字符串的最右指针的下一个开始或者单词后面的空格开始。
        //    // 所以开始遍历是right是从len开始的。
        //
        //    for (int left = len - 1, right = len; left >= 0; left--) {
        //        // left指向的是空格
        //        if (s.charAt(left) == ' ') {// 当left指向单词前面的那个空格
        //            if (left + 1 < right) {// left和right不是紧挨着的，表明有单词存在
        //                // StringBuilder append(CharSequence s, int start, int end) 将指定的 CharSequence序列追加到此序列。包左不包右
        //                // 从索引start开始的参数s字符按顺序附加到该序列的内容，直到索引end 。 该序列的长度增加了值end - start 。
        //                ans.append(s, left + 1, right).append(' ');//添加单词和空格
        //            }
        //            // 只要left指向的是空格，right就指向left。
        //            right = left;// right始终指向每个单词的最后一个字母后面的空格
        //        }
        //    }
        //    return ans.substring(0, ans.length() - 1);
        //}




        //	解法2：调用API
        //public String reverseWords(String s) {
        //    if (s == null || s.length() ==0) {
        //        return s;
        //    }
        //    //String trim() 返回一个字符串，其值为此字符串，删除了所有前导和尾随空格，
        //    // 除去开头和末尾的空白字符
        //    s = s.trim();
        //    // 正则匹配连续的空白字符作为分隔符分割
        //    List<String> wordList = Arrays.asList(s.split("\\s+"));
        //    Collections.reverse(wordList);
        //    return String.join(" ", wordList);
        //}




        //  解法3:自行编写对应的函数
        public String reverseWords(String s) {
            if (s == null || s.length() == 0) {
                return null;
            }
            StringBuilder sb = trimSpaces(s);// 去除字符串中多余的空格
            reverse(sb, 0, sb.length() - 1);// 翻转字符串
            reverseEachWord(sb);// 翻转每个单词
            return sb.toString();
        }


        // 去除多余空格写法1：，用空格前的一个位置为字母来实现
        public StringBuilder trimSpaces(String s) {
            if (s == null || s.length() == 0) {
                return null;
            }
            int left = 0, right = s.length() - 1;
            // 去掉字符串开头的空白字符
            while (left <= right && s.charAt(left) == ' ') {
                ++left;
            }
            // 去掉字符串末尾的空白字符
            while (left <= right && s.charAt(right) == ' ') {
                --right;
            }
            // 将字符串中间多余的空白字符去除
            StringBuilder sb = new StringBuilder();
            while (left <= right) {
                char c = s.charAt(left);// 当前指针指向的元素，可能是字母可能是空格
                if (c != ' ') {// 当前指针指向的元素不是空格，是字符。
                    sb.append(c);
                } else if (sb.charAt(sb.length() - 1) != ' ') {
                    // 若当前指针指向的元素是空格，那么若可变字符串的最后一个元素不是空格，则将空格连接到可变字符串后面
                    // 这一个保证每一个单词后面只有一个空格
                    sb.append(c);
                }
                ++left;// 指针后移
            }
            return sb;
        }


        //// 去除多余空格写法2：，用空格前的一个位置为字母来实现
        //public StringBuilder trimSpaces(String s) {
        //    if (s == null || s.length() == 0) {
        //        return null;
        //    }
        //    int left = 0, right = s.length() - 1;
        //    // 去掉字符串开头的空白字符
        //    while (left <= right && s.charAt(left) == ' ') {
        //        ++left;
        //    }
        //    // 去掉字符串末尾的空白字符
        //    while (left <= right && s.charAt(right) == ' ') {
        //        --right;
        //    }
        //    // 将字符串中间多余的空白字符去除
        //    StringBuilder sb = new StringBuilder();
        //    int count = 0;// 用计数器来处理空格个数
        //    while (left <= right) {
        //        char ch = s.charAt(left);// 当前指针指向的元素，可能是字母可能是空格
        //        if (ch != ' ') {// 当前指针指向的元素不是空格，是字符。
        //            count = 0;
        //            sb.append(ch);
        //        } else {// 当前指针指向的元素是空格
        //            count++;// 空格个数加1
        //            if (count == 1) {// 当空格个数为1时，将空格添加在单词后面
        //                sb.append(ch);
        //            }
        //        }
        //        left++;// 指针后移
        //    }
        //    return sb;
        //}


        // 反转整个字符串[left,right]   可变字符串设置待交换位置的字符
        public void reverse(StringBuilder sb, int left, int right) {
            if (sb == null || sb.length() == 0 || left > right) {
                return;
            }
            // 交换left和right位置的字符
            while (left < right) {
                char tmp = sb.charAt(left);
                sb.setCharAt(left, sb.charAt(right));
                sb.setCharAt(right, tmp);
                left++;
                right--;
            }
        }


        // 反转单词,单词之间只有一个空格
        public void reverseEachWord(StringBuilder sb) {
            if (sb == null || sb.length() == 0) {// 判空
                return;
            }
            int len = sb.length();
            int start = 0, end = 0;
            while (start < len) {
                // 循环至单词的末尾，end最终是单词后的空格，
                while (end < len && sb.charAt(end) != ' ') {
                    ++end;
                }
                // 翻转单词[start,end-1]
                reverse(sb, start, end - 1);
                // 更新start，end，去找下一个单词
                start = end + 1;
                ++end;
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
