/**
 * <p>输入数字 <code>n</code>，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre><strong>输入:</strong> n = 1
 * <strong>输出:</strong> [1,2,3,4,5,6,7,8,9]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p>说明：</p>
 *
 * <ul>
 * <li>用返回一个整数列表来代替打印</li>
 * <li>n 为正整数</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>数学</li></div></div><br><div><li>👍 253</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 17
 * 打印从1到最大的n位数
 * @author wangweizhou
 * @date 2022-09-13 19:55:13
 */

public class DaYinCong1daoZuiDaDeNweiShuLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new DaYinCong1daoZuiDaDeNweiShuLcof().new Solution();
        //int[] nums = solution.printNumbers(1);
        //for (int num : nums) {
        //    System.out.println(num);
        //}
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 这个问题需要表达一个大数。最常用也是最容易的方法是用字符串或者数组表达大数。
        // 在用字符串表示数字的时候，最直观的方法就是字符串里每个字符都是'0'~'9'之间的某一个字符，用来表示数字中的一位。
        // 因为数字最大是n位的，因此我们需要一个长度为+1的字符串。当实际数字不够n位的时候，在字符串的前半部分补0。
        // 首先把字符串中的每一个数字都初始化为0，然后每一次为字符串表示的数字加1，再打印出来。
        // 因此，我们只需要做两件事：一是在字符串表达的数字上模拟加法：二是把字符串表达的数字打印出来。


        // 这个大数处理除了最基本的思路其他都没有做出来
        //	解法1：数组模拟大数加法：nums[0]是数的最高位，nums[nums.length]是数组的最低位。
        //public int[] printNumbers(int n) {
        //    if (n <= 0) {
        //        return null;
        //    }
        //    int[] nums = new int[n + 1];// java中int数组的初始值是0
        //    // nums[0]是n+1位数的最高位，若最高位为1，那么就已经遍历完了1~最大的n位数
        //    int index=0;
        //    int[] ans=new int[n];
        //    while (nums[0] != 1) { //判断是否结束
        //        nums[index]=increment(nums);
        //    }
        //    return nums;
        //}
        //
        //
        //private int increment(int[] nums){
        //    boolean isOverflow=false;
        //    int iTakeOver = 0;
        //    int n=nums.length-1;
        //    int i=0;
        //    for (i = n; i >= 0; i--) {
        //        int iSum = nums[i] + iTakeOver;
        //        if (i == n) {
        //            iSum++;
        //        }
        //        if (iSum >= 10) {
        //            if (i == 0) {
        //                isOverflow=true;
        //                //nums[0] = 1;
        //            } else {
        //                iTakeOver = 1;
        //                nums[i] = iSum-10;
        //            }
        //        } else {
        //            nums[i] = iSum;
        //            break;
        //        }
        //    }
        //    return nums[i];
        //}



        ////	解法1：数组模拟大数加法：nums[0]是数的最高位，nums[nums.length]是数组的最低位，
        //public int[] printNumbers(int n) {
        //	if(n<=0){
        //		return null;
        //	}
        //	int[] nums = new int[n+1];// java中int数组的初始值是0
        //	// nums[0]是n+1位数的最高位，若最高位为1，那么就已经遍历完了1~最大的n位数
        //	while (nums[0] != 1){ //判断是否结束
        //		//每次从最后一位开始加1，也就是数的低位加1
        //		for(int i = n; i >= 0; i--){
        //			int result = nums[i]  + 1;// 当前位的和
        //			//判断进位
        //			if(result > 9){// 当前位加1之后大于9，那么表示要进位，当前位是0.
        //				nums[i] = 0;
        //			}else {
        //				nums[i] = result ;
        //				break;
        //			}
        //		}
        //	}
        //	return nums;
        //}








/*
	// 解法1：这种解法没有考虑大数处理
    public int[] printNumbers(int n) {
		if(n<=0){
			return null;
		}
		int count=1;
		for (int i = 1; i <= n; i++) {
			count*=10;
		}
		int[] nums=new int[count-1];
		for (int i = 0; i < count-1; i++) {
			nums[i]=i+1;
		}
		return nums;
    }
	*/

    }
//leetcode submit region end(Prohibit modification and deletion)

}
