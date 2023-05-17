/**
<p>编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为<a href="https://baike.baidu.com/item/%E6%B1%89%E6%98%8E%E9%87%8D%E9%87%8F" target="_blank">汉明重量</a>）。</p>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。</li>
	<li>在 Java 中，编译器使用<a href="https://baike.baidu.com/item/二进制补码/5295284" target="_blank">二进制补码</a>记法来表示有符号整数。因此，在上面的 <strong>示例 3</strong> 中，输入表示有符号整数 <code>-3</code>。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>00000000000000000000000000001011
<strong>输出：</strong>3
<strong>解释：</strong>输入的二进制串 <code><strong>00000000000000000000000000001011</strong> 中，共有三位为 '1'。</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>00000000000000000000000010000000
<strong>输出：</strong>1
<strong>解释：</strong>输入的二进制串 <strong>00000000000000000000000010000000</strong> 中，共有一位为 '1'。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>11111111111111111111111111111101
<strong>输出：</strong>31
<strong>解释：</strong>输入的二进制串 <strong>11111111111111111111111111111101</strong> 中，共有 31 位为 '1'。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>输入必须是长度为 <code>32</code> 的 <strong>二进制串</strong> 。</li>
</ul>

<ul>
</ul>

<p> </p>

<p><strong>进阶</strong>：</p>

<ul>
	<li>如果多次调用这个函数，你将如何优化你的算法？</li>
</ul>
<div><div>Related Topics</div><div><li>位运算</li><li>分治</li></div></div><br><div><li>👍 516</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 191
 * 位1的个数
 * @author wangweizhou
 * @date 2022-09-15 16:07:34
 */
public class NumberOf1Bits{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new NumberOf1Bits().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {





	//// 解法2：
	//// 把一个整数减去1，都是把最右边的1变成0。把最右边的1的右边的所有的0都变成1，而最右边的1的左边的所有位都保持不变。
	//// 把一个整数减去1，就相当于把最右边的1和最右边的1右边的所有位做了非运算。

	//// 把一个整数和该整数减去1的结果做位与运算，相当于把该整数最右边的1变成0，其余不变。【最右边的1左边两个数一样，最右边的1两个数异号，最右边的1的右侧两个数异号】
	//
	public int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			n &= n - 1;
			count++;
		}
		return count;
	}



	//// 解法2：将该数n分别与1，2，4，8等做位与运算
	// 判断整数n的二进制位的每一位是否为1，将1左移1位得到2，左移2位得到4，依次类推
	// 位与运算，同1为1，有0为0。

	//public int hammingWeight(int n) {
	//	int count = 0;
	//	for (int i = 0; i < 32; i++) {
	//		if ((n & (1 << i)) == 1) {// 判断整数n的二进制位的每一位是否为1
	//			count++;
	//		}
	//	}
	//	return count;
	//}




	////	解法1：将数字n右移 32 次
	//public int hammingWeight(int n) {
	//	int count = 0;
	//	while (n != 0) {
	//		count += n & 1;// 使用 n & 1 得到二进制末尾是否为 1。 &按位与全1为1，有0为0.
	//		n >>>= 1;// 把第一个操作数的二进制补码整体右移指定位数（第二个操作数）后，左边空出来的位总是以0填充。
	//	}
	//	return count;
	//}


}
//leetcode submit region end(Prohibit modification and deletion)

}
