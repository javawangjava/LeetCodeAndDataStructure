/**
<p>实现&nbsp;<a href="https://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(<em>x</em>, <em>n</em>)</a>&nbsp;，即计算 <code>x</code> 的整数&nbsp;<code>n</code> 次幂函数（即，<code>x<sup>n</sup></code><sup><span style="font-size:10.8333px"> </span></sup>）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = 10
<strong>输出：</strong>1024.00000
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 2.10000, n = 3
<strong>输出：</strong>9.26100
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = -2
<strong>输出：</strong>0.25000
<strong>解释：</strong>2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-100.0 &lt; x &lt; 100.0</code></li>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup>-1</code></li>
	<li><code>-10<sup>4</sup> &lt;= x<sup>n</sup> &lt;= 10<sup>4</sup></code></li>
</ul>
<div><div>Related Topics</div><div><li>递归</li><li>数学</li></div></div><br><div><li>👍 1042</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 50
 * Pow(x, n)
 * @author wangweizhou
 * @date 2022-09-15 16:22:13
 */
public class PowxN{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new PowxN().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


	//	解法1：快速幂+迭代
	public double myPow(double x, int n) {
		if (x == 0) {
			return 0;// 0的任何非0次方都是0。0的0次方在数学上没有意义，这里处理为0。
		}
		long exponent =n;// 指数
		if(exponent >=0){
			return powerWithUnsignedExponent(x, exponent);
		}else{
			return 1.0/powerWithUnsignedExponent(x,-exponent);
		}
	}


	// 计算任何数的非负指数幂
	// 快速幂  类似二分法   从高次幂到低次幂 结束条件是幂次为1
	// 其实可以看作是运用指数运算法则进行运算 a^(2n)=(a^2)^n。
	private double powerWithUnsignedExponent(double base, long exponent){
		if(base==0&&exponent<=0){// 0的负值数幂没有定义，会报错，报错如何处理自己约定
			//同样这里也包含了0的0次方。0的0次方在数学上没有意义，这里约定返回0.
			return 0.0;// 这里约定为0.0，具体的可以自行约定。
		}
		if(exponent==0){// 非0数的任何次幂等于1，
			return 1;
		}
		if(exponent==1){// 任何数的1次幂等于本身
			return base;
		}
		double result=1.0;// result表示最终的结果
		while(exponent>0){
			// 如果开始时指数是奇数，那么result会额外乘上一个底数，result等于最开始时的底数。
			// 如果开始时指数是偶数，那么result就是1。
			if((exponent&1)==1){// 指数的二进制最右侧的数是1，也就是指数是奇数,要多乘一次底数
				result=result*base;
				base=base*base;// 底数平方
				exponent=exponent>>1;// 舍弃指数的二进制最右侧的一位
			}else{// 指数是偶数
				base=base*base;// 底数平方
				exponent=exponent>>1;// 舍弃指数的二进制最右侧的一位
			}

		}
		return result;
	}







	////	解法2：快速幂+递归
	//public double myPow(double x, int n) {
	//	if (x == 0) {
	//		return 0;// 0的任何非0次方都是0。0的0次方在数学上没有意义，这里处理为0。
	//	}
	//	long exponent =n;// 指数
	//	if(exponent >=0){
	//		return powerWithUnsignedExponent(x, exponent);
	//	}else{
	//		return 1.0/powerWithUnsignedExponent(x,-exponent);
	//	}
	//}
	//
	//
	//// 计算任何数的非负指数幂
	//// 快速幂    递归 是由低次幂到高次幂
	//private double powerWithUnsignedExponent(double base, long exponent){
	//	if(exponent==0){// 非0数的任何次幂等于1
	//		return 1;
	//	}
	//	if(exponent==1){// 任何数的1次幂等于本身
	//		return base;
	//	}
	//	// 每次递归都使得指数减少一半
	//	double result=powerWithUnsignedExponent(base,exponent>>1);//递归
	//
	//	if((exponent&1)==1){// 当指数为奇数时，需要再额外乘以底数
	//		result=result*result;// 将上次的结果进行平方
	//		result=result*base;
	//	}else{// 当指数为偶数时
	//		result=result*result;// 将上次的结果进行平方
	//	}
	//
	//	// 将上面简化之后就是下面的写法
	//	//result=result*result;// 将上次的结果进行平方
	//	//if((exponent&1)==1){// 当指数为奇数时，需要再额外乘以底数
	//	//	result=result*base;
	//	//}
	//	return result;
	//}


}
//leetcode submit region end(Prohibit modification and deletion)

}
