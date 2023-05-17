/**
 * <p>给定整数 <code>n</code> ，返回 <em>所有小于非负整数&nbsp;<code>n</code>&nbsp;的质数的数量</em> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 10
 * <strong>输出：</strong>4
 * <strong>解释：</strong>小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 0
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1
 * <strong>输出</strong>：0
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= n &lt;= 5 * 10<sup>6</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>数学</li><li>枚举</li><li>数论</li></div></div><br><div><li>👍
 * 903</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 计数质数
 *
 * @author wangweizhou
 * @date 2022-06-28 16:54:46
 */
public class CountPrimes {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new CountPrimes().new Solution();
        int a = solution.countPrimes(10);
        System.out.println(a);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 方法二：暴力法优化
        // 质数（Prime number），又称素数，指在大于 1 的自然数中，除了 1 和该数自身外，无法被其他自然数整除的数。

       /*
        public int countPrimes(int n) {
            if (n == 0 || n == 1) {
                return 0;
            }
            int num = 0;
            for (int i = 2; i < n; i++) {
                num += isPrimre(i) ? 1 : 0;
            }
            return num;
        }

        // 判断是否为质数
        public boolean isPrimre(int i) {
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {//有其他因子则是合数
                    return false;
                }
            }
            //循环结束没有其他因子就是质数
            return true;
        }*/


        //  方法二：埃氏筛  要
        //  初始化长度 O(n) 的标记数组，表示这个数组是否为质数。数组初始化所有的数都是质数.
        //  从 2 开始将当前数字的倍数全都标记为合数。标记到 \sqrt{n}n  时停止即可。
        // 注意每次找当前素数 x 的倍数时，是从 x^2开始的。因为如果 x>2，那么 2∗x 肯定被素数 2 给过滤了，最小未被过滤的肯定是 x^2。



        public int countPrimes(int n) {
            boolean[] isPrim = new boolean[n];
            Arrays.fill(isPrim, true);
            // 从 2 开始枚举到 sqrt(n)。
            for (int i = 2; i * i < n; i++) {
                // 如果当前是素数
                if (isPrim[i]) {
                    // 就把从 i*i 开始，i 的所有倍数都设置为 false。
                    for (int j = i * i; j < n; j += i) {
                        isPrim[j] = false;
                    }
                }
            }

            // 计数
            int cnt = 0;
            for (int i = 2; i < n; i++) {
                if (isPrim[i]) {
                    cnt++;
                }
            }
            return cnt;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
