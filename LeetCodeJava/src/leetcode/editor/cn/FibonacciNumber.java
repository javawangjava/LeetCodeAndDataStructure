/**
 * <p><strong>斐波那契数</strong>&nbsp;（通常用&nbsp;<code>F(n)</code> 表示）形成的序列称为 <strong>斐波那契数列</strong> 。该数列由&nbsp;
 * <code>0</code> 和 <code>1</code> 开始，后面的每一项数字都是前面两项数字的和。也就是：</p>
 *
 * <pre>
 * F(0) = 0，F(1)&nbsp;= 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n &gt; 1
 * </pre>
 *
 * <p>给定&nbsp;<code>n</code> ，请计算 <code>F(n)</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 2
 * <strong>输出：</strong>1
 * <strong>解释：</strong>F(2) = F(1) + F(0) = 1 + 0 = 1
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 3
 * <strong>输出：</strong>2
 * <strong>解释：</strong>F(3) = F(2) + F(1) = 1 + 1 = 2
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 4
 * <strong>输出：</strong>3
 * <strong>解释：</strong>F(4) = F(3) + F(2) = 2 + 1 = 3
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= n &lt;= 30</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>记忆化搜索</li><li>数学</li><li>动态规划</li></div></div><br><div><li>👍
 * 510</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 509
 * 斐波那契数
 *
 * @author wangweizhou
 * @date 2022-08-05 00:01:53
 */
public class FibonacciNumber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new FibonacciNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //⽅法三：动态规划(扩展思路)
        //step1:创建一个长度为n+1的数组，因为只有n+1才能有下标第n项，我们用它记录前n项斐波那契数列。
        //step2:根据公式，初始化第0项和第1项（题目中是第1项和第2项，本质上一样的）
        //step3:遍历数组，依照公式某一项等于前两项之和，将数组后续元素补齐，即可得到fib[n]。

        public int fib(int n) {
            if(n<=1){//从0开始，第0项是0，第⼀项是1
                return n;
            }
            int[] fib=new int[n+1];
            fib[0]=0;
            fib[1]=1;
            for (int i = 2; i < n+1; i++) {//依次相加
                fib[i]=fib[i-1]+fib[i-2];
            }
            return fib[n];
        }





     /*
        // 解法1：迭代相加
        //step1:低于2项的数列，直接返回n.
        //step2:初始化第0项，与第1项分别为0,1.
        //step3:从第2项开始，逐渐按照公式累加，并更新相加数始终为下一项的前两项。


        public int fib(int n) {
            if (n <= 1) {//从0开始，第0项是0，第⼀项是1
                return n;
            }
            int res = 0;
            int a = 0;
            int b = 1;
            //第三项开始是前两项的和,然后保留最新的两项，更新数据相加
            for (int i = 2; i <= n; i++) {
                res = a + b;
                a = b;
                b = res;
            }
            return res;
        }
        */





      /*
        // 解法2：递归
        // step1:低于2项的数列，直接返回n.
        //step2:对于当前n,递归调用函数计算两个子问题相加。

        public int fib(int n) {
            if (n <= 1) {//从0开始，第0项是0，第⼀项是1
                return n;
            } else {
                return fib(n - 1) + fib(n - 2);//根据公式递归调⽤函数
            }
        }
        */

    }
//leetcode submit region end(Prohibit modification and deletion)

}
