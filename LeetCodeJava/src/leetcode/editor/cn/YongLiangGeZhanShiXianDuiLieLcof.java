/**
<p>用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 <code>appendTail</code> 和 <code>deleteHead</code> ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，<code>deleteHead</code>&nbsp;操作返回 -1 )</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
[&quot;CQueue&quot;,&quot;appendTail&quot;,&quot;deleteHead&quot;,&quot;deleteHead&quot;]
[[],[3],[],[]]
<strong>输出：</strong>[null,null,3,-1]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>
[&quot;CQueue&quot;,&quot;deleteHead&quot;,&quot;appendTail&quot;,&quot;appendTail&quot;,&quot;deleteHead&quot;,&quot;deleteHead&quot;]
[[],[],[5],[2],[],[]]
<strong>输出：</strong>[null,-1,null,null,5,2]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= values &lt;= 10000</code></li>
	<li><code>最多会对&nbsp;appendTail、deleteHead 进行&nbsp;10000&nbsp;次调用</code></li>
</ul>
<div><div>Related Topics</div><div><li>栈</li><li>设计</li><li>队列</li></div></div><br><div><li>👍 604</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 09
 * 用两个栈实现队列
 * @author wangweizhou
 * @date 2022-09-14 15:33:02
 */

public class YongLiangGeZhanShiXianDuiLieLcof{

	 public static void main(String[] args) {
	 	 //测试代码
         CQueue solution = new YongLiangGeZhanShiXianDuiLieLcof().new CQueue();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class CQueue {


    //  用两个栈实现队列这个数据结构。
    //  栈实现队列的本质就是负负得正，两次先进后出的结果就是先进先出了。

    //  每次进入队列的元素全部进入栈1，栈2中的元素是每次栈2为空时，将栈1中元素弹出并压入栈2形成的。所以栈2顶部一定是截至目前最早进入栈1的元素。
    //  在构造函数中完成两个栈的初始化工作，在 appendTail 函数中向其中一个栈 stack1 结尾插入整数，
    //  在 deleteHead 函数中如果stack2 为空，则将 stack1 的值全部弹出放到 stack2 中，再从 stack2 中取值，这样达到了负负为正的队列效果。


    //  1.每次进入队列的元素都全部加入栈1；
    //  2.当需要出栈时，首先检查栈2是否为空，若栈2为空，则将栈2栈顶元素出栈。若栈2不空，则将栈1中元素弹出并压入栈2。



    private Deque<Integer> stack1;
    private Deque<Integer> stack2;
    public CQueue() {
        stack1=new LinkedList<>();
        stack2=new LinkedList<>();
    }


    public void appendTail(int value) {
        stack1.push(value);
    }


    public int deleteHead() {
        if(stack2.size()>0){
            return  stack2.pop();
        }else {
            if(stack1.size()>0){
                while (stack1.size()>0){
                    stack2.push(stack1.pop());
                }
                return  stack2.pop();
            }else {
                return -1;
            }
        }
    }





    //// 进入队列元素全部进入栈1
    //// 栈2中存储早进入队列的元素，从栈顶到栈底表示进入队列越早
    //private Deque<Integer> stack1;// 定义全局变量
    //private Deque<Integer> stack2;
    //
    //public CQueue() {// CQueue 构造函数，初始化 stack1 和 stack2
    //    stack1=new LinkedList<>();
    //    stack2=new LinkedList<>();
    //}
    //
    //
    //// appendTail(int value) 作用是将数据加入到队列尾部
    //// 进入队列的元素全部进入栈1，所以这里只要将数据压入栈1即可
    //public void appendTail(int value) {// appendTail 函数，将 value 加到 stack1 里面，先进后出
    //    stack1.push(value);
    //}
    //
    //
    //// deleteHead() 作用是删除队首元素
    //// 删除队首元素就是删除最早进入栈1的元素。
    //// 1.当栈2不空时，栈2的栈顶元素就是最早进入队列的元素，也就是待删除元素。
    //// 2.当栈2为空时，将栈1中元素弹出并压入栈2，这时候栈2不空，栈2顶部元素就是最早进入队列的元素。
    //// 3.当栈1和栈2都为空时，队列则为空，没有数据可以用来删除。
    //public int deleteHead() {
    //    if(!stack2.isEmpty()){// 当栈2不空时，栈2的栈顶元素就是最早进入队列的元素，也就是待删除元素。
    //        return stack2.pop();
    //    }else{// 当栈2为空时，要判断队列中是否还有其他元素，即判断栈1中是否还有其他元素
    //        if(!stack1.isEmpty()){// 当栈2为空时且栈1不空，
    //            while(!stack1.isEmpty()){// 将栈1中所有元素压入栈2，那么待删除元素就是栈2的栈顶元素。
    //                stack2.push(stack1.pop());
    //            }
    //            return stack2.pop();
    //        }else {// 当栈2为空，栈1也为空，则队列中没有元素
    //            return -1;
    //        }
    //    }
    //}
    //


}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */

//leetcode submit region end(Prohibit modification and deletion)

}
