/**
 * <p>给你一个整数 <code>x</code> ，如果 <code>x</code> 是一个回文整数，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p>回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。</p>
 *
 * <ul>
 * <li>例如，<code>121</code> 是回文，而 <code>123</code> 不是。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 121
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = -121
 * <strong>输出：</strong>false
 * <strong>解释：</strong>从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 10
 * <strong>输出：</strong>false
 * <strong>解释：</strong>从右向左读, 为 01 。因此它不是一个回文数。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>-2<sup>31</sup>&nbsp;&lt;= x &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你能不将整数转为字符串来解决这个问题吗？</p>
 * <div><div>Related Topics</div><div><li>数学</li></div></div><br><div><li>👍 2049</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


/**
 * 回文数
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new PalindromeNumber().new Solution();

        //int num=10;
        //System.out.println(solution.isPalindrome(num));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
	/*//	 解法1：调用api
    public boolean isPalindrome(int x) {

		String reverseStr=(new StringBuilder(x+"")).reverse().toString();
		return (x+"").equals(reverseStr);
    }*/

        // 解法2：
        public boolean isPalindrome(int x) {
            //1.所有负数都不可能是回文.除了 0 以外，所有个位是 0 的数字不可能是回文
            if (x < 0 || (x != 0 && x % 10 == 0)) {
                return false;
            }
            //	2.既然是回文数，反转后一半和前一半进行比较
            int reverseNum = 0;
            int temp;//
            //反转后半部分
            while (x > reverseNum) {
                temp = x % 10;//temp表示数x的最后一位
                reverseNum = reverseNum * 10 + temp;
                x /= 10;//原数舍去最后一位
            }
            //	3.当数字长度为奇数时，除去中间的数，前半部分就等于后半部分。我们可以通过 revertedNumber/10 去除处于中位的数字。
            //	当数字长度为偶数时，前半部分就等于后半部分
            return x == reverseNum || x == reverseNum / 10;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
