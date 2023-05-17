/**
 * <p>给定一个整数，写一个函数来判断它是否是 3&nbsp;的幂次方。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p>整数 <code>n</code> 是 3 的幂次方需满足：存在整数 <code>x</code> 使得 <code>n == 3<sup>x</sup></code></p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 27
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 0
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 9
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 45
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你能不使用循环或者递归来完成本题吗？</p>
 * <div><div>Related Topics</div><div><li>递归</li><li>数学</li></div></div><br><div><li>👍 256</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 3 的幂
 *
 * @author wangweizhou
 * @date 2022-06-28 21:20:59
 */
public class PowerOfThree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new PowerOfThree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 方法一：试除法
        //思路与算法
        //我们不断地将 n 除以 3，直到 n=1。如果此过程中 n 无法被 3 整除，就说明 n 不是 3 的幂。
        //本题中的 n 可以为负数或 0，可以直接提前判断该情况并返回False，也可以进行试除，因为负数或 0 也无法通过多次除以 3 得到 1。
/*
        public boolean isPowerOfThree(int n) {
            if (n <= 0) {
                return false;
            }
            while (n != 0 && n % 3 == 0) {
                n /= 3;
            }
            return n == 1;
        }*/

        // 方法二：判断是否为最大 3 的幂的约数
        // 传参 n 的数据类型为 int，这引导我们首先分析出 int 范围内的最大 3次幂是多少，约为 3^19 = 1162261467
        // 如果 n 为 3 的幂的话，那么必然满足 n * 3^k = 1162261467.即 n 与 1162261467 存在倍数关系。
        // 因此，我们只需要判断 n 是否为 1162261467 的约数即可。

        public boolean isPowerOfThree(int n) {
            return n > 0 && 1162261467 % n == 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
