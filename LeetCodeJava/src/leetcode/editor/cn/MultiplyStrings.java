/**
 * <p>给定两个以字符串形式表示的非负整数&nbsp;<code>num1</code>&nbsp;和&nbsp;<code>num2</code>，返回&nbsp;<code>num1</code>&nbsp;和&nbsp;
 * <code>num2</code>&nbsp;的乘积，它们的乘积也表示为字符串形式。</p>
 *
 * <p><strong>注意：</strong>不能使用任何内置的 BigInteger 库或直接将输入转换为整数。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> num1 = "2", num2 = "3"
 * <strong>输出:</strong> "6"</pre>
 *
 * <p><strong>示例&nbsp;2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> num1 = "123", num2 = "456"
 * <strong>输出:</strong> "56088"</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= num1.length, num2.length &lt;= 200</code></li>
 * <li><code>num1</code>&nbsp;和 <code>num2</code>&nbsp;只能由数字组成。</li>
 * <li><code>num1</code>&nbsp;和 <code>num2</code>&nbsp;都不包含任何前导零，除了数字0本身。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>字符串</li><li>模拟</li></div></div><br><div><li>👍 1019</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;


/**
 * 43
 * 字符串相乘
 *
 * @author wangweizhou
 * @date 2022-08-14 18:41:21
 */

public class MultiplyStrings {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new MultiplyStrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：写一个竖式乘法就看明白了
        public String multiply(String num1, String num2) {
        	if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {// 判空
        		return "";
        	}
        	if (num1.equals("0") || num2.equals("0")) {// num1和num2之一是0，则直接将 0 作为结果返回即可
        		return "0";
        	}
            int len1 = num1.length();
            int len2 = num2.length();
            // n位数*m位数，那么最大的数位是（n+m）。必须99*9，那么最大不会100*10=1000。即最大也就是3位数（2+1）。
            int[] ans = new int[len1 + len2];
            for (int i = len1 - 1; i >= 0; i--) {
                // ans[i + j + 1]是第（i + j + 1）数位上的数字
                // ans[i + j] 表示计算完第（i + j + 1）数位上的结果的进位，因为乘法的进位大于1，而且根据竖乘进位要累加前面的进位
                int val1 = num1.charAt(i) - '0';// 获取第 i 位的数字
                for (int j = len2 - 1; j >= 0; j--) {
                    int val2 = num2.charAt(j) - '0';// 获取第 j 位的数字
                    // 相乘的结果  ans[i + j + 1]为原来第（i+j+1）位的数，val1*val2是第i位和第j位相乘之后的结果
                    int sum = ans[i + j + 1] + val1 * val2;
                    ans[i + j + 1] = sum % 10;// 这个相当于求当前位剩余的。ans[i + j + 1]表示第（i+j+1）位新的数值
                    ans[i + j] += sum / 10;// 这个相当于进位，进位上的数
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ans.length; i++) {
                if (i == 0 && ans[i] == 0) {// 若乘积结果的最高位为0，则跳过最高位
                    continue;//   i++;这里i++和continue 效果一样，都是为了最高位为0的情况
                }
                sb.append(ans[i]);
            }
            return sb.toString();
        }






        //// 解法2：写法2
        //public String multiply(String num1, String num2) {
        //	if(num1==null||num2==null||num1.length()==0||num2.length()==0){//判空
        //		return "";
        //	}
        //	if (num1.equals("0") || num2.equals("0")) {// num1和num2之一是0，则直接将 00 作为结果返回即可
        //		return "0";
        //	}
        //	String ans = "0";
        //	int len1 = num1.length(), len2 = num2.length();
        //
        //	for (int i = len2 - 1; i >= 0; i--) {//从字符串num2的最后一位起
        //		StringBuffer curr = new StringBuffer();
        //		int carry = 0;
        //		for (int j = len2 - 1; j > i; j--) {
        //			curr.append(0);
        //		}
        //		int cur2 = num2.charAt(i) - '0';
        //		for (int j = len1 - 1; j >= 0; j--) {
        //			int cur1 = num1.charAt(j) - '0';
        //			int product = cur1 * cur2 + carry;
        //			curr.append(product % 10);
        //			carry = product / 10;
        //		}
        //		if (carry != 0) {
        //			curr.append(carry % 10);
        //		}
        //		ans = addStrings(ans, curr.reverse().toString());
        //	}
        //	return ans;
        //}





        //// 下面是链表相加进行处理
        //public String addStrings(String num1, String num2) {
        //	if(num1==null||num2==null||num1.length()==0||num2.length()==0){//判空
        //		return "";
        //	}
        //	if(num1.equals("0")){
        //		return num2;
        //	}
        //	if(num2.equals("0")){
        //		return num1;
        //	}
        //	StringBuilder  sum=new StringBuilder();
        //	int carry=0;//进位
        //	int index1=num1.length()-1;//字符串num1的索引指向num1最后一位
        //	int index2=num2.length()-1;
        //	while(index1>=0||index2>=0||carry!=0){//两个字符串没有遍历完或者有进位
        //		int cur1=index1>=0?num1.charAt(index1)-'0':0;//当指针index1没有遍历完时指向对应位，当遍历完数组时，则指向0；
        //		int cur2=index2>=0?num2.charAt(index2)-'0':0;
        //		int temp=cur1+cur2+carry;// 当前位相加
        //		sum.append(temp%10);//和倒序加入，最后相加完之后再反转顺序
        //		carry=temp/10;//获取进位
        //		index1--;//指针前移
        //		index2--;
        //	}
        //	sum.reverse();//反转结果
        //	return sum.toString();
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
