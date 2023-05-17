/**
 * <p>写一个函数，求两个整数之和，要求在函数体内不得使用 &ldquo;+&rdquo;、&ldquo;-&rdquo;、&ldquo;*&rdquo;、&ldquo;/&rdquo; 四则运算符号。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例:</strong></p>
 *
 * <pre><strong>输入:</strong> a = 1, b = 1
 * <strong>输出:</strong> 2</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>a</code>,&nbsp;<code>b</code>&nbsp;均可能是负数或 0</li>
 * <li>结果不会溢出 32 位整数</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>位运算</li><li>数学</li></div></div><br><div><li>👍 349</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 65
 * 不用加减乘除做加法
 * @author wangweizhou
 * @date 2022-09-25 00:40:36
 */

public class BuYongJiaJianChengChuZuoJiaFaLcof {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new BuYongJiaJianChengChuZuoJiaFaLcof().new Solution();
        int ans = solution.add(0, -2);
        System.out.println(ans);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //	解法1：位运算  模拟加法
        // 异或运算：相同为1，不同为0。 与运算：相同为1，不同为0；
        // 第一步：不考虑进位，两个数的二进制对应位相加，0+0=0，0+1=1，1+1=10，不考虑进位其结果和异或结果一样
        // 第二步：考虑进位，只有1+1=10，相当于作与运算，然后再左移一位
        // 第三步：将不考虑进位的结果和进位的结果相加，重复前两步
        public int add(int a, int b) {
            if (a == 0) {
                return b;
            } else if (b == 0) {
                return a;
            }
            int ans = 0;
            while (a != 0&&b!=0) {
                int temp = a ^ b;// 不考虑进位，两个数的二进制对应位相加，0+0=0，0+1=1，1+1=10，不考虑进位其结果和异或结果一样
                int carry = (a & b) << 1;// 考虑进位，只有1+1=10，相当于作与运算，然后再左移一位
                a = temp;
                b = carry;
            }
            return a==0?b:a;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
