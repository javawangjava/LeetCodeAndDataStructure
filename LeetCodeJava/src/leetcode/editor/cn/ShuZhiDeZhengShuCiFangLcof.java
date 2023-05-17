/**
<p>实现 <a href="https://www.cplusplus.com/reference/valarray/pow/">pow(<em>x</em>, <em>n</em>)</a> ，即计算 x 的 n 次幂函数（即，x<sup>n</sup>）。不得使用库函数，同时不需要考虑大数问题。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = 10
<strong>输出：</strong>1024.00000
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 2.10000, n = 3
<strong>输出：</strong>9.26100</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = -2
<strong>输出：</strong>0.25000
<strong>解释：</strong>2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-100.0 < x < 100.0</code></li>
	<li><code>-2<sup>31</sup> <= n <= 2<sup>31</sup>-1</code></li>
	<li><code>-10<sup>4</sup> <= x<sup>n</sup> <= 10<sup>4</sup></code></li>
</ul>

<p> </p>

<p>注意：本题与主站 50 题相同：<a href="https://leetcode-cn.com/problems/powx-n/">https://leetcode-cn.com/problems/powx-n/</a></p>
<div><div>Related Topics</div><div><li>递归</li><li>数学</li></div></div><br><div><li>👍 353</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 剑指 Offer 16
 * 数值的整数次方
 * @author wangweizhou
 * @date 2022-09-22 08:44:29
 */

public class ShuZhiDeZhengShuCiFangLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new ShuZhiDeZhengShuCiFangLcof().new Solution();


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
	private double powerWithUnsignedExponent(double base, long exponent){
		if(exponent==0){// 非0数的任何次幂等于1
			return 1;
		}
		if(exponent==1){// 任何数的1次幂等于本身
			return base;
		}
		double result=1.0;
		while(exponent>0){
			if((exponent&1)==1){// 指数的二进制最右侧的数是1，也就是exponent是奇数,要多乘一次底数
				result=result*base;
			}
			// 以下两步是搭配的：把底数平方之后作为下一次的底数，那么对应的下一次的幂指数是原幂指数的一半
			base=base*base;// 底数平方，把底数平方之后作为下一次的底数，相当于将不断的平方之后再平方
			exponent=exponent>>1;// 舍弃指数的二进制最右侧的一位，那么对应的下一次的幂指数是原幂指数的一半

			// 上面是下面的简化
			//if((exponent&1)==1){// 指数的二进制最右侧的数是1，也就是指数是奇数,要多乘一次底数
			//	result=result*base;
			//	base=base*base;// 底数平方，把底数平方之后作为下一次的底数，相当于将不断的平方之后再平方
			//	exponent=exponent>>1;// 舍弃指数的二进制最右侧的一位，那么对应的下一次的幂指数是原幂指数的一半
			//}else{// 指数是偶数
			//	base=base*base;// 底数平方，把底数平方之后作为下一次的底数，相当于将不断的平方之后再平方
			//	exponent=exponent>>1;// 舍弃指数的二进制最右侧的一位，那么对应的下一次的幂指数是原幂指数的一半
			//}

		}
		return result;
	}






	/*

	//	解法2：快速幂+递归
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
	// 快速幂    递归 是由低次幂到高次幂
	private double powerWithUnsignedExponent(double base, long exponent){
		if(exponent==0){// 非0数的任何次幂等于1
			return 1;
		}
		if(exponent==1){// 任何数的1次幂等于本身
			return base;
		}
		// 每次递归都使得指数减少一半
		double result=powerWithUnsignedExponent(base,exponent>>1);//递归
		result=result*result;// 将上次的结果进行平方
		if((exponent&1)==1){// 当指数为奇数时，需要再额外乘以底数
			result=result*base;
		}
		// 简化之后就是上面的写法
		//if((exponent&1)==1){// 当指数为奇数时，需要再额外乘以底数
		//	result=result*result;// 将上次的结果进行平方
		//	result=result*base;
		//}else{// 当指数为偶数时
		//	result=result*result;// 将上次的结果进行平方
		//}
		return result;
	}

	*/


}
//leetcode submit region end(Prohibit modification and deletion)

}
