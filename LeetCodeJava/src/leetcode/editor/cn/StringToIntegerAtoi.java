/**
 * <p>请你来实现一个&nbsp;<code>myAtoi(string s)</code>&nbsp;函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 <code>atoi</code> 函数）。</p>
 *
 * <p>函数&nbsp;<code>myAtoi(string s)</code> 的算法如下：</p>
 *
 * <ol>
 * <li>读入字符串并丢弃无用的前导空格</li>
 * <li>检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。</li>
 * <li>读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。</li>
 * <li>将前面步骤读入的这些数字转换为整数（即，"123" -&gt; 123， "0032" -&gt; 32）。如果没有读入数字，则整数为 <code>0</code> 。必要时更改符号（从步骤 2 开始）。</li>
 * <li>如果整数数超过 32 位有符号整数范围 <code>[−2<sup>31</sup>,&nbsp; 2<sup>31&nbsp;</sup>− 1]</code>
 * ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 <code>−2<sup>31</sup></code> 的整数应该被固定为 <code>−2<sup>31</sup></code> ，大于
 * <code>2<sup>31&nbsp;</sup>− 1</code> 的整数应该被固定为 <code>2<sup>31&nbsp;</sup>− 1</code> 。</li>
 * <li>返回整数作为最终结果。</li>
 * </ol>
 *
 * <p><strong>注意：</strong></p>
 *
 * <ul>
 * <li>本题中的空白字符只包括空格字符 <code>' '</code> 。</li>
 * <li>除前导空格或数字后的其余字符串外，<strong>请勿忽略</strong> 任何其他字符。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "42"
 * <strong>输出：</strong>42
 * <strong>解释：</strong>加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
 * 第 1 步："42"（当前没有读入字符，因为没有前导空格）
 * ^
 * 第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * ^
 * 第 3 步："<u>42</u>"（读入 "42"）
 * ^
 * 解析得到整数 42 。
 * 由于 "42" 在范围 [-2<sup>31</sup>, 2<sup>31</sup> - 1] 内，最终结果为 42 。</pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "   -42"
 * <strong>输出：</strong>-42
 * <strong>解释：</strong>
 * 第 1 步："<u><strong>   </strong></u>-42"（读入前导空格，但忽视掉）
 * ^
 * 第 2 步："   <u><strong>-</strong></u>42"（读入 '-' 字符，所以结果应该是负数）
 * ^
 * 第 3 步："   <u><strong>-42</strong></u>"（读入 "42"）
 * ^
 * 解析得到整数 -42 。
 * 由于 "-42" 在范围 [-2<sup>31</sup>, 2<sup>31</sup> - 1] 内，最终结果为 -42 。
 * </pre>
 *
 * <p><strong>示例&nbsp;3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "4193 with words"
 * <strong>输出：</strong>4193
 * <strong>解释：</strong>
 * 第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
 * ^
 * 第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * ^
 * 第 3 步："<u>4193</u> with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
 * ^
 * 解析得到整数 4193 。
 * 由于 "4193" 在范围 [-2<sup>31</sup>, 2<sup>31</sup> - 1] 内，最终结果为 4193 。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= s.length &lt;= 200</code></li>
 * <li><code>s</code> 由英文字母（大写和小写）、数字（<code>0-9</code>）、<code>' '</code>、<code>'+'</code>、<code>'-'</code> 和 <code>'
 * .'</code> 组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li></div></div><br><div><li>👍 1448</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 8
 * 字符串转换整数 (atoi)
 *
 * @author wangweizhou
 * @date 2022-06-16 11:33:31
 */

public class StringToIntegerAtoi {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new StringToIntegerAtoi().new Solution();

        Solution solution = new StringToIntegerAtoi().new Solution();
        String str = "         f123kp123";
        int res = solution.myAtoi(str);
        System.out.println("res=" + res);

        //System.out.println(Integer.MAX_VALUE);
        //System.out.println(Integer.MIN_VALUE);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public int myAtoi(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int len = s.length();
            int sign = 1;
            long ans = 0;
            int index = 0;
            char[] cs = s.toCharArray();
            while (index < len && cs[index] == ' ') {
                index++;
            }
            if (index < len && (cs[index] == '-' || cs[index] == '+')) {
                sign = cs[index] == '-' ? -1 : 1;
                index++;
            }
            while (index < len && cs[index] <= '9' && cs[index] >= '0') {
                int digit = cs[index] - '0';
                index++;
                if (10 * ans + digit > Integer.MAX_VALUE) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                ans = ans * 10 + digit;
            }
            return (int) ans * sign;
        }



        // 解法1：遍历字符串 写法1
        //public int myAtoi(String s) {
        //    if (s == null || s.length() == 0) {
        //        return 0;
        //    }
        //
        //    int sign = 1;//字符串的符号部分,1表示正数，-1表示负数。
        //    int ans = 0;//字符串的除符号位之外的数字部分
        //    int index = 0;//字符串转换成数组之后的数组下标
        //    char[] array = s.toCharArray();//字符串转换成字符数组
        //
        //    // 循环遍历去除数组的前导空格，也就是从真实数据部分开始
        //    while (index < array.length && array[index] == ' ') {
        //        index++;
        //    }
        //
        //    // 上述循环结束之后，就去除完了前导空格，然后判断有无符号。
        //	// 符号包括正负号两个，这一位只要有显性的符号位，就一定要确定符号位的正负。
        //    if (index < array.length && (array[index] == '-' || array[index] == '+')) {
        //        sign = array[index] == '-' ? -1 : 1;//正号可能省略了，所以判断负号
        //        index++;
        //    }
        //
        //    // 判断字符中的数字是否合法，只考虑合法，取出连续的合法的数字部分
        //    while (index < array.length && array[index] <= '9' && array[index] >= '0') {
        //		// 数组中当前字符与‘0’的差值，因为前面已经判断了数组元素必须在0~9之间，其实就是当前数组的array[index]的整数型数值
        //        int digit = array[index] - '0';
        //        index++;
        //		// 这里使用的整数型数字，所以在少一位的时候就判断是否越界
        //		// 这里只是考虑数字的整数部分。（ans > (Integer.MAX_VALUE - digit) / 10） 可以看作 (10*ans+digit>Integer.MAX_VALUE)
        //		// 但是int型越界之后会截断，所以需要将(10*ans+digit>Integer.MAX_VALUE)变形，转换成不越界的情况（ans > (Integer.MAX_VALUE - digit) /
        //		10）
        //        if (ans > (Integer.MAX_VALUE - digit) / 10) {//位数少一位的时候判断是否越界，那么多最后一位时一定越界
        //            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        //        }
        //        ans = ans * 10 + digit;
        //    }
        //    return ans * sign;
        //}


        // 解法1：遍历字符串 写法2
	/*public int myAtoi(String str) {
		int len = str.length();
		// str.charAt(i) 方法回去检查下标的合法性，一般先转换成字符数组
		char[] charArray = str.toCharArray();

		// 1、去除前导空格
		int index = 0;
		while (index < len && charArray[index] == ' ') {
			index++;
		}

		// 2、如果已经遍历完成（针对极端用例 "      "）
		if (index == len) {
			return 0;
		}

		// 3、如果出现符号字符，仅第 1 个有效，并记录正负
		int sign = 1;
		char firstChar = charArray[index];
		if (firstChar == '+') {
			index++;
		} else if (firstChar == '-') {
			index++;
			sign = -1;
		}

		// 4、将后续出现的数字字符进行转换
		// 不能使用 long 类型，这是题目说的
		int res = 0;
		while (index < len&& charArray[index] <= '9' && charArray[index] >= '0') {
			char currChar = charArray[index];

			// 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判：断乘以 10 以后是否越界
			// res > Integer.MAX_VALUE / 10 正整数res比最大正整数从十位开始做个位的数大，正整数整数越界
			// res == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE % 10  正整数除个位外都相同，res的个位大于最大正整数个位
			if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE
			 % 10)) {
				return Integer.MAX_VALUE;
			}
			if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))) {
				return Integer.MIN_VALUE;
			}

			// 4.2 合法的情况下，才考虑转换，每一步都把符号位乘进去
			res = res * 10 + sign * (currChar - '0');
			index++;
		}
		return res;
	}*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
