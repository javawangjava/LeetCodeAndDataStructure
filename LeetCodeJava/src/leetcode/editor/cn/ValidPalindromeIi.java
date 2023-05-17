/**
 * <p>给定一个非空字符串 <code>s</code>，<strong>最多</strong>删除一个字符。判断是否能成为回文字符串。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> s = "aba"
 * <strong>输出:</strong> true
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> s = "abca"
 * <strong>输出:</strong> true
 * <strong>解释:</strong> 你可以删除c字符。
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> s = "abc"
 * <strong>输出:</strong> false</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 10<sup>5</sup></code></li>
 * <li><code>s</code> 由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 519</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 680
 * 验证回文字符串 Ⅱ
 *
 * @author wangweizhou
 * @date 2022-07-21 18:05:38
 */

public class ValidPalindromeIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ValidPalindromeIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 结合5,125,647和680一起看

        // 本题还是从字符串的两端开始向里逐步比较两个字符是不是相同。
        // 如果相同，则继续向里移动指针比较里面的两个字符。如果不相同，按照题目的要求，在删除一个字符之后再比较其他的字符就能够形成一个回文。
        // 由于事先不知道应该删除两个不同字符中的哪一个，因此两个字符都可以进行尝试。

        // 在函数validPalindrome的最后的return语句中，如果变量start等于输入字符串s的长度的一半，那么字符串s本身就是一个回文。
        // 如果变量start小于字符串s的长度的一半，那么下标为start和end的两个字符不相同，分别跳过下标start和end（相当于删除字符串中下标
        // 为start或end的字符），调用函数isPalindrome可以判断剩下的字符串是不是一个回文。


        // 解法1：双指针
        // 在允许最多删除一个字符的情况下，同样可以使用双指针，通过贪心实现。
        // 初始化两个指针 low 和 high分别指向字符串的第一个字符和最后一个字符。
        // 每次判断两个指针指向的字符是否相同，如果相同，则更新指针，将 low 加 1，high 减1，然后判断更新后的指针范围内的子串是否是回文字符串。
        // 如果两个指针指向的字符不同，则两个字符中必须有一个被删除，此时我们就分成两种情况：
        // 即删除左指针对应的字符，留下子串 s[low+1:high]，或者删除右指针对应的字符，留下子串 s[low:high−1]。
        // 当这两个子串中至少有一个是回文串时，就说明原始字符串删除一个字符之后就以成为回文串。


        public boolean validPalindrome(String s) {
            if (s == null || s.length() <= 1) {
                return true;
            }
            int left = 0;// 初始化指针指向字符串的第一个字符
            int right = s.length() - 1;// 初始化指针指向字符串的最后一个字符
            while (left < right) {
                if (s.charAt(left) == s.charAt(right)) {// 每次判断两个指针指向的字符是否相同，如果相同，则更新指针，将 low 加 1，high 减1
                    left++;
                    right--;
                } else {
                    // 如果两个指针指向的字符不同，则两个字符中必须有一个被删除，此时我们就分成两种情况：
                    // 即删除左指针对应的字符，留下子串 s[low+1:high]，或者删除右指针对应的字符，留下子串 s[low:high−1]。
                    // 当这两个子串中至少有一个是回文串时，就说明原始字符串删除一个字符之后就以成为回文串。
                    return validPalindromeFunc(s, left + 1, right) || validPalindromeFunc(s, left, right - 1);
                }
            }
            return true;
        }


        // 判断字符串s的子串[left,right]是否是回文串
        private boolean validPalindromeFunc(String str,int left,int right){
            while (left<right){
                if(str.charAt(left)!=str.charAt(right)){
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }






        //// 解法2：双指针 写法2
        //public boolean validPalindrome(String s) {
        //    if (s == null || s.length() <= 1) {
        //        return true;
        //    }
        //    int len = s.length();
        //    int left = 0;
        //    int right = len - 1;
        //    while (left < len / 2) {// 因为最多删除一个字符，所以这里可以使用长度的一般
        //        if (s.charAt(left) != s.charAt(right)) {
        //            break;
        //        }
        //        left++;
        //        right--;
        //    }
        //    return left == len / 2 || isPalindrome(s, left, right - 1) || isPalindrome(s, left + 1, right);
        //}
        //
        //
        //
        //private boolean isPalindrome(String s, int left, int right) {
        //    while (left < right) {
        //        if (s.charAt(left) != s.charAt(right)) {
        //            break;
        //        }
        //        left++;
        //        right--;
        //    }
        //    return left >= right;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
