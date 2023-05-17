/**
<p>编写一个算法来判断一个数 <code>n</code> 是不是快乐数。</p>

<p><strong>「快乐数」</strong>&nbsp;定义为：</p>

<ul>
	<li>对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。</li>
	<li>然后重复这个过程直到这个数变为 1，也可能是 <strong>无限循环</strong> 但始终变不到 1。</li>
	<li>如果这个过程 <strong>结果为</strong>&nbsp;1，那么这个数就是快乐数。</li>
</ul>

<p>如果 <code>n</code> 是 <em>快乐数</em> 就返回 <code>true</code> ；不是，则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 19
<strong>输出：</strong>true
<strong>解释：
</strong>1<sup>2</sup> + 9<sup>2</sup> = 82
8<sup>2</sup> + 2<sup>2</sup> = 68
6<sup>2</sup> + 8<sup>2</sup> = 100
1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>
<div><div>Related Topics</div><div><li>哈希表</li><li>数学</li><li>双指针</li></div></div><br><div><li>👍 971</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * 202
 * 快乐数
 * @author wangweizhou
 * @date 2022-06-30 21:57:48
 */
public class HappyNumber{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new HappyNumber().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

	//	具体原理看leetcode解析
	//  通过算法分析：最终会得到 1或者最终会进入循环。
	//  判定循环可以用哈希表也可以用快慢指针来做

	/*

	//	 方法一：用哈希集合检测循环
    public boolean isHappy(int n) {
		Set<Integer> set=new HashSet<>();
		while(n!=1&&!set.contains(n)){// 等于1或者运算进入循环就结束循环
			set.add(n);
			n=getNext(n);
		}
		return n==1;
    }
	*/




	//  方法二：快慢指针检测循环
	//  慢速在链表中前进 1 个节点，快跑者前进 2 个节点（对 getNext(n) 函数的嵌套调用）。
	//	如果 n 是一个快乐数，即没有循环，那么快跑者最终会比慢跑者先到达数字 1。
	//	如果 n 不是一个快乐的数字，那么最终快跑者和慢跑者将在同一个数字上相遇。
	public boolean isHappy(int n) {
		int slow = n;
		int fast = getNext(n);
		while (fast != 1 && slow != fast) {// 快指针等于1或者运算进入循环就结束循环
			slow = getNext(slow);
			fast = getNext(getNext(fast));
		}
		return fast == 1;
	}


	private int getNext(int n){
		int totalSum=0;
		while(n>0){
			int d=n%10;
			n/=10;
			totalSum+=d*d;
		}
		return totalSum;
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}
