/**
<p>给你一个整数 <code>n</code> ，找出从 <code>1</code> 到 <code>n</code> 各个整数的 Fizz Buzz 表示，并用字符串数组 <code>answer</code>（<strong>下标从 1 开始</strong>）返回结果，其中：</p>

<ul>
	<li><code>answer[i] == "FizzBuzz"</code> 如果 <code>i</code> 同时是 <code>3</code> 和 <code>5</code> 的倍数。</li>
	<li><code>answer[i] == "Fizz"</code> 如果 <code>i</code> 是 <code>3</code> 的倍数。</li>
	<li><code>answer[i] == "Buzz"</code> 如果 <code>i</code> 是 <code>5</code> 的倍数。</li>
	<li><code>answer[i] == i</code> （以字符串形式）如果上述条件全不满足。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>["1","2","Fizz"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>["1","2","Fizz","4","Buzz"]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 15
<strong>输出：</strong>["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>
<div><div>Related Topics</div><div><li>数学</li><li>字符串</li><li>模拟</li></div></div><br><div><li>👍 193</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * Fizz Buzz
 * @author wangweizhou
 * @date 2022-06-28 16:28:43
 */
public class FizzBuzz{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new FizzBuzz().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> fizzBuzz(int n) {
		if(n<=0){
			return null;
		}
		List<String> list=new ArrayList<>();
		//String[] str=new String[n];
		for (int i = 1; i <= n; i++) {
			if(i%3==0&&i%5==0){
				list.add(i-1,"FizzBuzz");
				//str[i-1]="FizzBuzz";
			}else if(i%3==0){
				list.add(i-1,"Fizz");
				//str[i-1]="Fizz";
			}else if(i%5==0){
				list.add(i-1,"Buzz");
				//str[i-1]="Buzz";
			}else{
				list.add(i-1, String.valueOf(i));
				//str[i-1]="i";
			}
		}
		return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
