/**
给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。 

 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。 

 

 示例 1： 

 
输入：num1 = "11", num2 = "123"
输出："134"
 

 示例 2： 

 
输入：num1 = "456", num2 = "77"
输出："533"
 

 示例 3： 

 
输入：num1 = "0", num2 = "0"
输出："0"
 

 

 

 提示： 

 
 1 <= num1.length, num2.length <= 10⁴ 
 num1 和num2 都只包含数字 0-9 
 num1 和num2 都不包含任何前导零 
 

 Related Topics 数学 字符串 模拟 👍 680 👎 0

*/

package leetcode.editor.cn;

/**
 * 415
 * 字符串相加
 * @author wangweizhou
 * @date 2023-03-01 00:45:16
 */
public class AddStrings{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new AddStrings().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public String addStrings(String num1, String num2) {
		if(num1==null||num1.length()==0){
			return num2;
		}
		if(num2==null||num2.length()==0){
			return num1;
		}
		StringBuilder sb=new StringBuilder();
		int len1=num1.length();
		int len2=num2.length();
		int carry=0;
		int index1=len1-1;
		int index2=len2-1;
		while (index1>=0||index2>=0||carry>0){
			int curr1=index1>=0?num1.charAt(index1)-'0':0;
			int curr2=index2>=0?num2.charAt(index2)-'0':0;
			int currSum=curr1+curr2+carry;
			int curr=currSum%10;
			carry=currSum/10;
			index1--;
			index2--;
			sb.append(curr);
		}
		return sb.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
