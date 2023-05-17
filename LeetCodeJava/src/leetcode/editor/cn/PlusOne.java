/**
 * <p>给定一个由 <strong>整数 </strong>组成的<strong> 非空</strong> 数组所表示的非负整数，在该数的基础上加一。</p>
 *
 * <p>最高位数字存放在数组的首位， 数组中每个元素只存储<strong>单个</strong>数字。</p>
 *
 * <p>你可以假设除了整数 0 之外，这个整数不会以零开头。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>digits = [1,2,3]
 * <strong>输出：</strong>[1,2,4]
 * <strong>解释：</strong>输入数组表示数字 123。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>digits = [4,3,2,1]
 * <strong>输出：</strong>[4,3,2,2]
 * <strong>解释：</strong>输入数组表示数字 4321。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>digits = [0]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= digits.length <= 100</code></li>
 * <li><code>0 <= digits[i] <= 9</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>数学</li></div></div><br><div><li>👍 1026</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 加一
 * @author wangweizhou
 * @date 2022-06-24 23:44:35
 */
public class PlusOne {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new PlusOne().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法1： 通用方法
        // 因为是末尾+1，所有每次进位最大可能是1。
        // 加一得十进一位.个位数为 0 加法运算如不出现进位就运算结束了,若进位只会是一。
        // 那么判断进位思路1： carry=(digits[i]+carry)/10;

        public int[] plusOne(int[] digits) {
            int carry = 1;
            int length = digits.length;
            int[] ans = new int[length + 1];

            for (int i = length - 1; i >= 0; i--) {
                ans[i + 1] = (digits[i] + carry) % 10;
                carry = (digits[i] + carry) / 10;
            }
            if (carry == 1) {
                ans[0] = carry;
                return ans;
            } else {
                return Arrays.copyOfRange(ans, 1, length + 1);
            }
        }


        //	解法2：特殊情况特殊处理
/*
        public int[] plusOne(int[] digits) {
            for (int i = digits.length - 1; i >= 0; i--) {
                digits[i]++;//最后一位+1
                digits[i] = digits[i] % 10;//+1之后求余运算存储在当前位置
                // +1之后求余运算不等于0也就意味着当前位运算后没有进位，那么其他位的数就不会变化，返回
                if (digits[i] != 0) return digits;
            //  +1之后求余运算等于0也就意味着当前位运算后有进位，那么前移一位，继续循环
            }
            // 若循环结束之后，并且没有结束方法，那就意味着所有位都是0，新建一个长度为(length+1)的数组，第一位设置为1，其余位为0.
            // 也就是所有位都进位，则长度加1
            digits = new int[digits.length + 1];
            digits[0] = 1;
            return digits;
        }*/

        //	解法3：特殊情况特殊处理
/*
        public int[] plusOne(int[] digits) {
            for (int i = digits.length - 1; i >= 0; i--) {
              if(digits[i]==9){
                  digits[i]=0;
              }else{
                  digits[i]+=1;
                  return digits;
              }
            }
            // 若循环结束之后，并且没有结束方法，那就意味着所有位都是0，新建一个长度为(length+1)的数组，第一位设置为1，其余位为0.
            // 也就是所有位都进位，则长度加1
            digits = new int[digits.length + 1];
            digits[0] = 1;
            return digits;
        }*/

    }
//leetcode submit region end(Prohibit modification and deletion)

}
