/**
 * <p>给定一个整数数组 <code>asteroids</code>，表示在同一行的小行星。</p>
 *
 * <p>对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。</p>
 *
 * <p>找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。</p>
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
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>asteroids = [-2,-1,1,2]
 * <strong>输出：</strong>[-2,-1,1,2]
 * <b>解释</b><strong>：</strong>-2 和 -1 向左移动，而 1 和 2 向右移动。 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。 </pre>
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
 *
 * <p>&nbsp;</p>
 *
 * <p><meta charset="UTF-8" />注意：本题与主站 735&nbsp;题相同：&nbsp;
 * <a href="https://leetcode-cn.com/problems/asteroid-collision/">https://leetcode-cn.com/problems/asteroid-collision/</a></p>
 * <div><div>Related Topics</div><div><li>栈</li><li>数组</li></div></div><br><div><li>👍 49</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer II 037
 * 小行星碰撞
 * @author wangweizhou
 * @date 2022-11-15 10:00:18
 */
public class XagZNi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new XagZNi().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 栈模拟  写法2
        public int[] asteroidCollision(int[] asteroids) {
            if (asteroids == null || asteroids.length == 0) {
                return new int[1];
            }
            Deque<Integer> stack = new ArrayDeque<>();

            for (int as:asteroids){
            	while(!stack.isEmpty()&&stack.peek()>0&&stack.peek()<-as){// 栈不空，当小行星相左飞行，且栈顶小行星较小，那么栈顶小行星弹出消失
            		stack.pop();
            	}
            	if(!stack.isEmpty()&&as<0&&stack.peek()==-as){// 栈不空，小行星向左飞行，且和栈顶一样大，那么栈顶小行星弹出消失，小行星也消失
            		stack.pop();
            	}else if(as>0||stack.isEmpty()||stack.peek()<0){// 当小行星相右飞行，栈空，栈顶小行星向左飞行
            		stack.push(as);
            	}
            }

            int size = stack.size();// 栈中元素个数
            int[] ans = new int[size];
            for (int i = size - 1; i >= 0; i--) {//遍历将栈中元素弹出，栈先进后出
                ans[i] = stack.pop();
            }
            return ans;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
