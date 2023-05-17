/**
 * <p>给定&nbsp;<code>pushed</code>&nbsp;和&nbsp;<code>popped</code>&nbsp;两个序列，每个序列中的
 * <strong>值都不重复</strong>，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 <code>true</code>；否则，返回
 * <code>false</code>&nbsp;。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * <strong>输出：</strong>true
 * <strong>解释：</strong>我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -&gt; 4,
 * push(5), pop() -&gt; 5, pop() -&gt; 3, pop() -&gt; 2, pop() -&gt; 1
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * <strong>输出：</strong>false
 * <strong>解释：</strong>1 不能在 2 之前弹出。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= pushed.length &lt;= 1000</code></li>
 * <li><code>0 &lt;= pushed[i] &lt;= 1000</code></li>
 * <li><code>pushed</code> 的所有元素 <strong>互不相同</strong></li>
 * <li><code>popped.length == pushed.length</code></li>
 * <li><code>popped</code> 是 <code>pushed</code> 的一个排列</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>模拟</li></div></div><br><div><li>👍 347</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 946
 * 验证栈序列
 *
 * @author wangweizhou
 * @date 2022-09-14 16:27:34
 */

public class ValidateStackSequences {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ValidateStackSequences().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 总结上述入栈、出栈的过程。
        // 我们可以找到判断一个序列是不是栈的弹出序列的规律：如果出栈序列中下一个弹出的数字刚好是入栈序列的栈顶数字，那么直接弹出；
        // 如果下一个弹出的数字不在压栈序列的栈顶，则把压栈序列中还没有入栈的数字压入辅助栈，直到把下一个需要弹出的数字压入栈顶为止；
        // 如果所有数字都压入栈后仍然没有找到下一个弹出的数字，那么该序列不可能是一个弹出序列。


        //// 解法1：模拟
        //// 模拟出栈和入栈的流程  根据出栈序列和入栈序列的顺序模拟，该入栈的时候入栈，该出栈的时候出栈。
        //// 以入栈为主，对应出栈的顺序判断是否可以出栈，最后判断栈是否为空。

        public boolean validateStackSequences(int[] pushed, int[] popped) {
            if (pushed == null || popped == null ||
                    pushed.length == 0 || popped.length == 0 || pushed.length != popped.length) {// 判空
                return false;
            }
            Deque<Integer> stack=new LinkedList<>();// 使用真正的栈来对出栈和入栈序列进行模拟
            int len=popped.length;
            int in=0;// 指针in是入栈序列的遍历指针
            int out=0;// 指针out是出栈序列的遍历指针

            // 循环遍历出栈和入栈的数组。数据只有先入栈然后才能出栈。
            while (in<len){// 先遍历入栈序列
                stack.push(pushed[in]);// 将入栈序列的元素按要求压入数据栈
                in++;// 入栈序列的遍历指针后移
                while (stack.size()>0&&stack.peek()==popped[out]){// 当栈不空且栈顶元素等于出栈序列的出栈元素时，模拟出栈
                    stack.pop();// 数据栈的栈顶元素出栈
                    out++;// 出栈序列的遍历指针后移
                }
            }
            //return  stack.isEmpty();
            return stack.size()==0;// 如果数据栈是空，则两个序列匹配。即所有数据进栈之后所有数据也出栈了
        }




        //// 解法2：双指针模拟出栈入栈+数组模拟数据栈
        //public boolean validateStackSequences(int[] pushed, int[] popped) {
        //    if (pushed == null || pushed.length == 0 || popped == null || popped.length == 0 || pushed.length != popped.length) {
        //        return false;
        //    }
        //    int len = pushed.length;
        //    int[] nums = new int[len];// 创建数组来模拟数据栈
        //    Arrays.fill(nums, -1);// 因为题干测试案例的所有数据非负数，所有这里将数组的默认值填为负数
        //    int index = 0;// // 数组模拟的栈的遍历指针
        //    int in = 0;// 指针in是入栈序列的遍历指针
        //    int out = 0;// 指针out是出栈序列的遍历指针
        //    // 循环遍历出栈和入栈的数组
        //    while (in < len) {
        //        nums[index] = pushed[in];// 将入栈序列的元素按要求压入数组模拟的栈
        //        in++;// 入栈序列的遍历指针后移
        //        // 当数组模拟的栈不空并且该模拟栈的栈顶就是出栈数组的栈顶元素，那么表明模拟栈的栈顶元素要出栈
        //        while (index >= 0 && nums[index] == popped[out]) {
        //            nums[index] = -1;// 模拟栈出栈元素重置为初始值
        //            index--;// 数组模拟栈的栈顶元素出栈了，遍历指针后移
        //            out++;// 出栈序列的遍历指针后移
        //        }
        //        index++;// 模拟栈的指针后移，模拟栈的遍历指针为下一个待加入元素的位置
        //    }
        //    return index == 0;// 如果数组模拟的数据栈是空，则两个序列匹配。即所有数据进栈之后所有数据也出栈了
        //}






        //// 解法2：双指针模拟
        //// 数组模拟  下面这个连模拟数组都省了，看着会有一点迷糊
        //public boolean validateStackSequences(int[] pushed, int[] popped) {
        //    if (pushed == null || popped == null ||
        //            pushed.length == 0 || popped.length == 0 || pushed.length != popped.length) {// 判空
        //        return false;
        //    }
        //    int index = 0;// 数组模拟的栈的下标
        //    int in = 0;// 指针in指向入栈数组的对应元素
        //    int out = 0;// 指针out指向出栈数组的对应元素
        //    // 循环遍历出栈和入栈的数组
        //    while (in < pushed.length) {
        //        pushed[index] = pushed[in];// 将入栈数组的元素按要求压入数组模拟的栈
        //        in++;// 入栈序列的遍历指针后移
        //        // 当数组模拟的栈不空并且该模拟栈的栈顶就是出栈数组的栈顶元素，那么表明模拟栈的栈顶元素要出栈
        //        while (index >= 0 && pushed[index] == popped[out]) {// 元素出栈更新指针
        //            index--;// 数组模拟栈的栈顶元素出栈了
        //            out++;// 出栈序列的遍历指针后移
        //        }
        //        index++;// 模拟栈的指针后移，模拟栈的遍历指针为下一个待加入元素的位置
        //    }
        //    return index == 0;// 如果数组模拟的数据栈是空，则两个序列匹配。即所有数据进栈之后所有数据也出栈了
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
