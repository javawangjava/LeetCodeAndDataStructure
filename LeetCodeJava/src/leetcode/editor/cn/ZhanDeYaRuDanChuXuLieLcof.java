/**
<p>输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
<strong>输出：</strong>true
<strong>解释：</strong>我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -&gt; 4,
push(5), pop() -&gt; 5, pop() -&gt; 3, pop() -&gt; 2, pop() -&gt; 1
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
<strong>输出：</strong>false
<strong>解释：</strong>1 不能在 2 之前弹出。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= pushed.length == popped.length &lt;= 1000</code></li>
	<li><code>0 &lt;= pushed[i], popped[i] &lt; 1000</code></li>
	<li><code>pushed</code>&nbsp;是&nbsp;<code>popped</code>&nbsp;的排列。</li>
</ol>

<p>注意：本题与主站 946 题相同：<a href="https://leetcode-cn.com/problems/validate-stack-sequences/">https://leetcode-cn.com/problems/validate-stack-sequences/</a></p>
<div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>模拟</li></div></div><br><div><li>👍 379</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 31
 * 栈的压入、弹出序列
 * @author wangweizhou
 * @date 2022-09-14 16:15:56
 */
public class ZhanDeYaRuDanChuXuLieLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new ZhanDeYaRuDanChuXuLieLcof().new Solution();
		 int[] pushed= {};
		 int[] popped= {};
		 //int[] pushed={1,2,3,4,5};
		 //int[] popped={4,5,3,2,1};
		 boolean bool=solution.validateStackSequences(pushed,popped);
		 System.out.println(bool);

	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

	//	解法1：单个栈模拟  双指针遍历入栈和出栈数组

	//  遍历数组 pushed，将 pushed 的每个元素依次入栈；
	//  每次将 pushed 的元素入栈之后，如果栈不为空且栈顶元素与 popped 的当前元素相同，则将栈顶元素出栈，同时遍历数组popped，直到栈为空或栈顶元素与 popped 的当前元素不同。

    public boolean validateStackSequences(int[] pushed, int[] popped) {
		// 压栈和出栈数组至少有一个为空，或者两个的长度不一样
		if(pushed==null||popped==null||pushed.length!=popped.length){
			return false;
		}
		// 到这里两个数组的长度相等
		int len=pushed.length;
		if(len==0){// 两个空数组
			return true;
		}

		Deque<Integer> stack=new LinkedList<>();
		int index1=0;// 入栈数组的遍历变量
		int index2=0;// 出栈数组的遍历变量
		while(index1<len){
			stack.push(pushed[index1]);// 将入栈数组的当前元素入栈
			index1++;// 入栈数组指针后移
			// 当栈不空并且出栈数组的当前元素等于栈顶元素，出栈
			while(!stack.isEmpty()&&stack.peek()== popped[index2]){
				stack.pop();
				index2++;
			}
		}
		// 如果入栈和出栈能对应起来，那么栈为空
		return stack.isEmpty();
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
