/**
 * <p>给定两个整数，被除数&nbsp;<code>dividend</code>&nbsp;和除数&nbsp;<code>divisor</code>。将两数相除，要求不使用乘法、除法和 mod 运算符。</p>
 *
 * <p>返回被除数&nbsp;<code>dividend</code>&nbsp;除以除数&nbsp;<code>divisor</code>&nbsp;得到的商。</p>
 *
 * <p>整数除法的结果应当截去（<code>truncate</code>）其小数部分，例如：<code>truncate(8.345) = 8</code> 以及 <code>truncate(-2.7335) =
 * -2</code></p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1:</strong></p>
 *
 * <pre><strong>输入:</strong> dividend = 10, divisor = 3
 * <strong>输出:</strong> 3
 * <strong>解释: </strong>10/3 = truncate(3.33333..) = truncate(3) = 3</pre>
 *
 * <p><strong>示例&nbsp;2:</strong></p>
 *
 * <pre><strong>输入:</strong> dividend = 7, divisor = -3
 * <strong>输出:</strong> -2
 * <strong>解释:</strong> 7/-3 = truncate(-2.33333..) = -2</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>被除数和除数均为 32 位有符号整数。</li>
 * <li>除数不为&nbsp;0。</li>
 * <li>假设我们的环境只能存储 32 位有符号整数，其数值范围是 [&minus;2<sup>31</sup>,&nbsp; 2<sup>31&nbsp;</sup>&minus; 1]。本题中，如果除法结果溢出，则返回
 * 2<sup>31&nbsp;</sup>&minus; 1。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>位运算</li><li>数学</li></div></div><br><div><li>👍 957</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 29
 * 两数相除
 * @author wangweizhou
 * @date 2022-08-13 17:54:42
 */

public class DivideTwoIntegers {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new DivideTwoIntegers().new Solution();
    }


//leetcode submit region begin(Prohibit modification and deletion)

    class Solution {

        //
        public int divide(int dividend, int divisor) {// dividend 被除数，divisor 除数
            // 当除数为1，直接返回被除数
            if (divisor == 1) {
                return dividend;
            }

            // 当除数为-1分类讨论
            if (divisor == -1) {
                if (dividend == Integer.MIN_VALUE) {//当除数为-1且被除数为Integer.MIN_VALUE时，将会溢出，按规定返回Integer.MAX_VALUE
                    return Integer.MAX_VALUE;
                } else {
                    return -dividend;//当除数为-1且被除数为不是Integer.MIN_VALUE时，正常输出
                }
            }

            if (dividend == 0 && divisor != 0) {//0除以非0除数结果是0
                return 0;
            }

            boolean flag=true;
            if ((dividend > 0 & divisor < 0) || (dividend < 0 & divisor > 0)) {//两数异号，结果是负数。两数同号，结果正数
                flag = false;
            }

			// 除数和被除数变为0
            dividend = dividend > 0 ? -dividend : dividend;
            divisor = divisor > 0 ? -divisor : divisor;

            int ans = divideFunc(dividend, divisor);
            return flag?ans:-ans;
        }


		// 除数和被除数都是负数
        private int divideFunc(int dividend, int divisor) {
            if (dividend > divisor) {//除数和被除数都是负数,当被除数大于除数
                return 0;
            }

            int count = 1;
            int tempDivisor = divisor;
            while ((dividend - tempDivisor) <= tempDivisor) {
                count = count + count;
                tempDivisor = tempDivisor + tempDivisor;
            }
            return count + divideFunc(dividend - tempDivisor, divisor);

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
