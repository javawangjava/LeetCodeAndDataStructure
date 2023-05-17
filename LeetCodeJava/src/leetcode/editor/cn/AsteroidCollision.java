/**
 * <p>给定一个整数数组 <code>asteroids</code>，表示在同一行的行星。</p>
 *
 * <p>对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。</p>
 *
 * <p>找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>asteroids = [5,10,-5]
 * <strong>输出：</strong>[5,10]
 * <b>解释：</b>10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>asteroids = [8,-8]
 * <strong>输出：</strong>[]
 * <b>解释：</b>8 和 -8 碰撞后，两者都发生爆炸。</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>asteroids = [10,2,-5]
 * <strong>输出：</strong>[10]
 * <b>解释：</b>2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= asteroids.length&nbsp;&lt;= 10<sup>4</sup></code></li>
 * <li><code>-1000 &lt;= asteroids[i] &lt;= 1000</code></li>
 * <li><code>asteroids[i] != 0</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>数组</li></div></div><br><div><li>👍 348</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 735
 * 行星碰撞
 *
 * @author wangweizhou
 * @date 2022-08-29 19:27:45
 */

public class AsteroidCollision {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new AsteroidCollision().new Solution();
        int[] nums = {-2, -2, 1, -2};
        int[] ans = solution.asteroidCollision(nums);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 整个数组的元素是后进入的可能先发生碰撞，所以符合后进先出的规则。那么这适合用栈，所以使用栈来模拟。
        //// 负数向左，正数向右，同向不会相撞。只有相向而行的行星才会碰撞。
        //// 方案1：如果当栈顶是负数，遍历到的元素是正数，这时候不可能相撞，但是遍历到的栈顶元素不知道后续会不会碰撞，也没有容器来保存。所以不行
        //// 方案2：如果当栈顶是正数，遍历到的元素是负数，这时候会发生碰撞。
        //// 若栈顶元素值小，那么栈顶元素将爆炸【出栈】，继续检查后续的栈顶元素。若栈顶元素值大，那么遍历到的元素将爆炸，原栈顶元素保留。那么方案2行的通。


        //// 解法1：栈模拟    栈中存放存在的行星   先弹出的弹出然后再该压入的压入。
        //// 小行星相撞的规律：1、如果一颗小行星向右飞行，那么可以将它入栈。
        //// 2、如果一颗小行星是向左飞行的，而位于栈顶的小行星向右飞行，那么它将与位于栈顶的小行星相撞。
        //// 2.1、如果位于栈顶的小行星较小，那么栈顶的小行星将爆炸消失，也就是说栈顶的小行星将出栈。然后判断向左飞行的小行星是否将与下一颗位于栈顶的小行星相撞。
        ////  若向左飞行的小行星与栈中所有向右飞行的小行星相撞后没有爆炸消失，则向左飞行的小行星入栈【发生碰撞后剩下的行星，这个行星不会和后续行星发生碰撞】。
        //// 2.2、如果栈顶小行星较大，则向左飞行的小行星消失。栈顶小行星保持不变。


        //public int[] asteroidCollision(int[] ats) {
        //    if (ats == null || ats.length == 0) {
        //        return new int[0];
        //    }
        //    Deque<Integer> stack = new ArrayDeque<>();
        //    for (int aster : ats) {// 遍历数组
        //        // 这时使用了一个状态词来表示
        //        boolean alive = true;// 对于遍历到的每一个小行星，最初假设状态是存活状态
        //        // 内层循环大条件只要是相向而行就可以。
        //        // 当当前行星 aster 存活且 aster<0，栈不空且栈顶元素大于 0 时，说明两个行星相互向对方移动，相向而行肯定会发生碰撞。
        //        while (alive && aster < 0 && !stack.isEmpty() && stack.peek() > 0) {
        //            // 相向而行肯定会发生碰撞。当前小行星aster较小时，当前小行星aster爆炸死亡。相向而行且当前小行星较大时，当前小行星存活。
        //            alive = stack.peek() < -aster;
        //            // 相向而行发生碰撞时，栈顶小行星的状态。
        //            if (stack.peek() <= -aster) {  // 如果栈顶元素小于等于 −aster，则栈顶元素表示的行星发生爆炸【出栈】，执行出栈操作
        //                stack.pop();
        //            }
        //        }
        //        if (alive) {//如果最后 alive 为真，说明行星aster 不会爆炸，则将aster 入栈。
        //            stack.push(aster);
        //        }
        //    }
        //    int size = stack.size();// 栈中元素个数
        //    int[] ans = new int[size];
        //    for (int i = size - 1; i >= 0; i--) {//遍历将栈中元素弹出，栈先进后出
        //        ans[i] = stack.pop();
        //    }
        //    return ans;
        //}



        public int[] asteroidCollision(int[] ats) {
            if (ats == null || ats.length == 0) {
                return new int[0];
            }
            Deque<Integer> stack = new LinkedList<>();
            int len = ats.length;
            for (int i = 0; i < len; i++) {
                boolean alive = true;
                while (alive && !stack.isEmpty() && stack.peek() > 0 && ats[i] < 0) {
                    alive = stack.peek() < -ats[i];
                    if (stack.peek() <= -ats[i]) {
                        stack.pop();
                    }
                }
                if (alive) {
                    stack.push(ats[i]);
                }
            }
            int size = stack.size();// 栈中元素个数
            int[] ans = new int[size];
            for (int i = size - 1; i >= 0; i--) {//遍历将栈中元素弹出，栈先进后出
                ans[i] = stack.pop();
            }
            return ans;
        }




        //public int[] asteroidCollision(int[] ats) {
        //    if (ats == null || ats.length == 0) {
        //        return new int[0];
        //    }
        //    Deque<Integer> stack = new LinkedList<>();
        //    int len = ats.length;
        //    for (int i = 0; i < len; i++) {
        //      while (!stack.isEmpty()&&stack.peek()>0&&stack.peek()<-ats[i]){
        //          stack.pop();
        //      }
        //      if(!stack.isEmpty()&&stack.peek()>0&&stack.peek()==-ats[i]){
        //          stack.pop();
        //      }else if(stack.isEmpty()||stack.peek()<0||ats[i]>0){
        //        stack.push(ats[i]);
        //      }
        //    }
        //
        //    int[] ans = new int[stack.size()];
        //    for (int i = ans.length - 1; i >= 0; i--) {
        //        ans[i] = stack.pop();
        //    }
        //    return ans;
        //}


        //// 栈模拟  写法2
        //public int[] asteroidCollision(int[] asteroids) {
        //    if (asteroids == null || asteroids.length == 0) {
        //        return new int[0];
        //    }
        //    Deque<Integer> stack = new ArrayDeque<>();
        //    for (int as : asteroids) {
        //        // 如果位于栈顶的小行星较小，那么栈顶的小行星将爆炸消失，也就是说栈顶的小行星将出栈。
        //        // 然后判断向左飞行的小行星是否将与下一颗位于栈顶的小行星相撞。所以这里是一个循环实现的。
        //        // 而且发生碰撞之后，当前小行星可能还需要入栈，所以把循环放在前面执行。
        //        while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -as) {// 栈不空并且栈顶向右并且当前小行星向左飞行
        //            stack.pop();
        //        }
        //        // 上面循环结束的时候:1.栈为空;2.栈顶元素<0;3.stack.peek()>=-as。
        //        if (!stack.isEmpty() && stack.peek()>0 && stack.peek() == -as) {//
        //            // 栈不空，小行星向左飞行，且和栈顶一样大，那么栈顶小行星弹出消失，小行星也消失
        //            stack.pop();
        //        } else if (as > 0 || stack.isEmpty() || stack.peek() < 0) {// 当小行星相右飞行或者栈空或者栈顶小行星向左飞行，当前的行星都应该入栈。
        //            stack.push(as);
        //        }
        //    }
        //
        //    int size = stack.size();// 栈中元素个数
        //    int[] ans = new int[size];
        //    for (int i = size - 1; i >= 0; i--) {//遍历将栈中元素弹出，栈先进后出
        //        ans[i] = stack.pop();
        //    }
        //    return ans;
        //    //return stack.stream().mapToInt(i->i).toArray();
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
