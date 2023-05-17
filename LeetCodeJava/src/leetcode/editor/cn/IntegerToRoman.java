/**
 * <p>罗马数字包含以下七种字符： <code>I</code>， <code>V</code>， <code>X</code>， <code>L</code>，<code>C</code>，<code>D</code> 和 <code>M</code>。</p>
 *
 * <pre>
 * <strong>字符</strong>          <strong>数值</strong>
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000</pre>
 *
 * <p>例如， 罗马数字 2 写做 <code>II</code> ，即为两个并列的 1。12 写做 <code>XII</code> ，即为 <code>X</code> + <code>II</code> 。 27 写做  <code>XXVII</code>, 即为 <code>XX</code> + <code>V</code> + <code>II</code> 。</p>
 *
 * <p>通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 <code>IIII</code>，而是 <code>IV</code>。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 <code>IX</code>。这个特殊的规则只适用于以下六种情况：</p>
 *
 * <ul>
 * <li><code>I</code> 可以放在 <code>V</code> (5) 和 <code>X</code> (10) 的左边，来表示 4 和 9。</li>
 * <li><code>X</code> 可以放在 <code>L</code> (50) 和 <code>C</code> (100) 的左边，来表示 40 和 90。 </li>
 * <li><code>C</code> 可以放在 <code>D</code> (500) 和 <code>M</code> (1000) 的左边，来表示 400 和 900。</li>
 * </ul>
 *
 * <p>给你一个整数，将其转为罗马数字。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> num = 3
 * <strong>输出:</strong> "III"</pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> num = 4
 * <strong>输出:</strong> "IV"</pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> num = 9
 * <strong>输出:</strong> "IX"</pre>
 *
 * <p><strong>示例 4:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> num = 58
 * <strong>输出:</strong> "LVIII"
 * <strong>解释:</strong> L = 50, V = 5, III = 3.
 * </pre>
 *
 * <p><strong>示例 5:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> num = 1994
 * <strong>输出:</strong> "MCMXCIV"
 * <strong>解释:</strong> M = 1000, CM = 900, XC = 90, IV = 4.</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= num <= 3999</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>数学</li><li>字符串</li></div></div><br><div><li>👍 895</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 整数转罗马数字
 */
public class IntegerToRoman {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new IntegerToRoman().new Solution();
        System.out.println(solution.intToRoman(6789));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 确定罗马数字的规则是：对于罗马数字从左到右的每一位，选择尽可能大的符号值。
        // 根据罗马数字的唯一表示法，为了表示一个给定的整数num，我们寻找不超过num 的最大符号值，将num 减去该符号值，
        // 然后继续寻找不超过num 的最大符号值，将该符号拼接在上一个找到的符号之后，
        // 循环直至num 为 0。最后得到的字符串即为num 的罗马数字表示。
        //

   /* public String intToRoman(int num) {
		int [] values={1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int value=0;
		String symbol=null;
		StringBuffer roman=new StringBuffer();
		for(int i=0;i<values.length;i++){
			value=values[i];
			symbol=symbols[i];
			while(num>=value){// 一个给定的整数num，我们寻找不超过num 的最大符号值，将num 减去该符号值，
				num-=value;
				roman.append(symbol);//将该符号拼接在上一个找到的符号之后
			}
			if(num==0){
				break;
			}
		}
		return roman.toString();
    }*/

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        public String intToRoman(int num) {
            StringBuffer roman = new StringBuffer();
            //roman.append(thousands[num / 1000]);
            int count=num/1000;//含有多少个1000,这样这个题目就不受位数限制了
            for (int i = 0; i < count; i++) {
                roman.append("M");
            }
            roman.append(hundreds[num % 1000 / 100]);// num % 1000得到个十百位，num % 1000 / 100是几百
            roman.append(tens[num % 100 / 10]);
            roman.append(ones[num % 10]);
            return roman.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
