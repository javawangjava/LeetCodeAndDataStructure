/**
 * <p>假设你正在爬楼梯。需要 <code>n</code>&nbsp;阶你才能到达楼顶。</p>
 *
 * <p>每次你可以爬 <code>1</code> 或 <code>2</code> 个台阶。你有多少种不同的方法可以爬到楼顶呢？</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 2
 * <strong>输出：</strong>2
 * <strong>解释：</strong>有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 3
 * <strong>输出：</strong>3
 * <strong>解释：</strong>有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 45</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>记忆化搜索</li><li>数学</li><li>动态规划</li></div></div><br><div><li>👍
 * 2567</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 70
 * 爬楼梯
 *
 * @author wangweizhou
 * @date 2022-08-05 00:36:39
 */

public class ClimbingStairs {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ClimbingStairs().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // ⽅法2：动态规划   具体思路见斐波拉契数列
        // 认为是从第0阶爬到第n阶
        // 定义状态：num[i]表示到达第i阶的方法数
        // 状态转移方程：num[i]=num[i-1]+num[i-2];
        // 初始状态：num[0]=1;从第 0 级开始爬的，所以从第 0 级爬到第 0 级我们可以看作只有一种方案，即 f(0) = 1；这样处理只是为了方便后续处理。可以没有语义

        // 其实常规解法可以分成多个子问题，爬第n阶楼梯的方法数量，等于 2 部分之和
        // 1.爬上 n-1阶楼梯的方法数量。因为再爬1阶就能到第n阶;
        // 2.爬上 n-2阶楼梯的方法数量，因为再爬2阶就能到第n阶;
        // 所以我们得到公式 dp[n] = dp[n-1] + dp[n-2]

        // ⽅法2：动态规划  写法1
        // 不建议为f[0]设置值的，循环里面应该从3开始
        // public int climbStairs(int n) {
        //    if(n<0){
        //        return Integer.MIN_VALUE;
        //    }
        //
        //    if (n <= 2) {//从0开始，第0项是1，第⼀项是1
        //        return n;
        //    }
        //    // 这里n>=1所以
        //    int[] nums = new int[n + 1];
        //    nums[1] = 1;//从第 0 级开始爬的，所以从第 0 级爬到第 1 级我们可以看作只有一种方案，即 nums[1] = 1；
        //    nums[2] = 2;// 从第 0 级到第 2 级2种方案，即爬一级，nums[2] = 2
        //    for (int i = 3; i < n + 1; i++) {//
        //        nums[i] = nums[i - 1] + nums[i - 2];
        //    }
        //    return nums[n];
        //}


        // ⽅法2：动态规划  写法2
        public int climbStairs(int n) {
            if(n<0){
                return Integer.MIN_VALUE;
            }

            if (n <= 1) {//从0开始，第0项是1，第⼀项是1
                return n;
            }
            // 这里n>=1所以
            int[] nums = new int[n + 1];
            nums[0] = 1;// 从第 0 级到第 0 级可以认为有一种爬法，没有动。这个是由题目举得例子而来
            nums[1] = 1;//从第 0 级开始爬的，所以从第 0 级爬到第 1 级我们可以看作只有一种方案【爬1个台阶】，即 nums[1] = 1；

            for (int i = 2; i < n + 1; i++) {//
                nums[i] = nums[i - 1] + nums[i - 2];
            }
            return nums[n];
        }




		/*

        //⽅法⼀：动态规划+动态数组
        //一只青蛙一次可以跳1阶或2阶，直到跳到第n阶，也可以看成这只青蛙从阶往下跳，到0阶，按照原路返回的话，两种方法事实上可以的跳法是一样的一一即怎么来的，怎么回去！
        //当青蛙在第n阶往下跳，它可以选择跳1阶到n一1，也可以选择跳2阶到n一2，即它后续的跳法变成了f(n一1)+f(n一2)，这就变成了斐波那契数列。因此可以按照斐波那契数列的做法来做：即输入n,
        // 输出第n个斐波那契数列的值，初始化0阶有1种，1阶有1种。

        public int climbStairs(int n) {
            if (n <= 1) {
                return n;
            }
            int prev = 1;
            int curr = 1;
            int next = 0;
            for (int i = 2; i <= n; i++) {
                next = prev + curr;
                prev = curr;
                curr = next;
            }
            return next;
        }

		*/

    }
//leetcode submit region end(Prohibit modification and deletion)

}
