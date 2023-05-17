/**
 * <p>给定一个整数数组&nbsp;<code>temperatures</code>&nbsp;，表示每天的温度，返回一个数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;
 * <code>answer[i]</code>&nbsp;是指对于第 <code>i</code> 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用&nbsp;<code>0</code> 来代替。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> <code>temperatures</code> = [73,74,75,71,69,72,76,73]
 * <strong>输出:</strong>&nbsp;[1,1,4,2,1,1,0,0]
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> temperatures = [30,40,50,60]
 * <strong>输出:</strong>&nbsp;[1,1,1,0]
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> temperatures = [30,60,90]
 * <strong>输出: </strong>[1,1,0]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;=&nbsp;temperatures.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>30 &lt;=&nbsp;temperatures[i]&nbsp;&lt;= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>单调栈</li></div></div><br><div><li>👍 1259</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 739
 * 每日温度
 *
 * @author wangweizhou
 * @date 2022-08-18 06:41:19
 */

public class DailyTemperatures {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new DailyTemperatures().new Solution();
        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};
        //int[] nums = {73, 74, 7, 7, 6, 7, 7, 7};
        int[] ans = solution.dailyTemperatures(nums);
        for (int a : ans) {
            System.out.print(a + ",");
        }
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 解法3：
        //// 单调栈：保存在栈中的温度（通过数组的下标可以得到温度）从下到上是递减排序的。
        //// 这是因为如果当前温度比位于栈顶的温度高，这时候就找到了比栈顶元素大的下一个位置的元素，那么位于栈顶的温度将出栈，所以每次入栈时当前温度一定比位于栈顶的温度低或相同。
        //
        //// 从数组中读出某一天的温度，并且将其与之前的温度进行比较。将后进入数据容器中的温度先拿出来比较，符合“后入先出”。
        //// 解决这个问题的思路总结起来就是用一个栈保存每天的温度在数组中的下标。
        //// 1.每次从数组中读取一个温度，然后将其与栈中保存的温度（根据下标可以得到温度）进行比较。
        //// 2.如果当前温度比位于栈顶的温度高，那么就能知道位于栈顶那一天需要等待几天才会出现更高的温度，这时候就找到了题目要求的比当前位置温度高的下一个位置的温度。
        //// 2.1然后栈顶元素出栈1次，将当前温度与下一个位于栈顶的温度进行比较。
        //// 2.2如果栈中已经没有比当前温度低的温度，则将当前温度在数组中的下标入栈。


        public int[] dailyTemperatures(int[] temperatures) {
            if (temperatures == null || temperatures.length == 0) {// 判空
                return new int[0];
            }
            int len = temperatures.length;
            int[] result = new int[len];

            // 因为这里要找出位置差，所以栈中保存的是数组的下标，通过数组下标就可以得到数组元素。
            Deque<Integer> stack = new LinkedList<>();// 保存在栈中的温度（通过数组的下标可以得到温度）是递减排序的。
            for (int i = 0; i < len; i++) {
                // 栈不空且当前位置的温度大于栈顶元素位置温度，
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    // 如果当前温度比位于栈顶的温度高，那么就能知道位于栈顶那一天需要等待几天才会出现更高的温度。
                    // 获取栈顶元素的下标，那么栈顶元素之后下一个更高温度是在第（i - prevIndex）天后的位置。
                    // 这里要循环将所有栈顶元素比当前位置元素温度低的栈顶元素出栈，直到栈为空或者栈顶温度大于等于当前位置的温度。
                    int prevIndex = stack.pop();// 栈顶元素出栈1次
                    result[prevIndex] = i - prevIndex; // 位于栈顶那一天需要等待几天才会出现更高的温度。
                }
                // 执行到这里栈为空或者栈顶元素的温度大于等于当前的温度，则直接将当前温度【用位置i表示】 进栈
                stack.push(i);
            }
            return result;
        }


        //// 解法2： 优化 针对每个温度值 向后进行依次搜索 ，找到比当前温度更高的值。
        //// result[i] 是指对于第 i 天，下一个更高温度出现在第i天之后的第result[i]天。
        //// 结果数组result的下标表示第i天，数组值result[i]表示第i天之后的下一个更高温度出现在第i天之后的第result[i]天。

        //public int[] dailyTemperatures(int[] temperatures) {
        //    if (temperatures == null || temperatures.length == 0) {// 判空
        //        return new int[0];
        //    }
        //    int len = temperatures.length;
        //    int[] res = new int[len];
        //    // 结果数组的最后一位一定是0，后面没有数了，所以不可能有比temperatures[length-1]更高的温度。java中int类型的默认值是0。
        //    // 外层循环从右向左遍历[0，len - 2]，内层循环从左向右遍历[i+1，len-1]
        //    // 从右向左遍历对于指定的位置，找到下一位较大的数就停止，每个特定的位置只需要一位
        //    for (int i = len - 1; i >= 0; i--) {
        //        for (int j = i + 1; j < len; ) {// 从位置i之后开始找
        //            if (temperatures[i] < temperatures[j]) {// temperatures[i] < temperatures[j],在下标j处找到比下标i处更大的数了。
        //                // 题目只需要找到当前位置下一个更高温度出现的位置。所以只要找到下一个更高温度出现的位置就可以结束内层循环了。
        //                res[i] = j - i;// 位置差
        //                break;
        //            } else {
        //                // temperatures[i] >= temperatures[j]：[i,j]之间没有比位置i更高的温度。
        //                if (res[j] == 0) {// result[j]为0，在j位置之后不存在比temperatures[j]更高的温度，那么也就没有比temperatures[i]更高的温度。
        //                    // result[j] == 0：在[j,len-1]就不存在比temperatures[j]更高的温度，那么也就没有比temperatures[i]更高的温度。
        //                    // 两个结合在一起就可以确定[i,len-1]中没有比temperatures[i]温度更高的温度了，结束内层循环。
        //                    res[i] = 0;// 记为0，
        //                    break;
        //                } else {
        //                    // result[j]不为0，在[j,len-1]存在比temperatures[j]更大的数，那么直接跳到比temperatures[j]更大的那个数再进行查找
        //                    // 那么[temperatures[j]+result[j]，len-1]中可能存在比temperatures[i]温度更高的温度了。
        //                    j += res[j];
        //                }
        //            }
        //        }
        //    }
        //    return res;
        //}


        //// 解法1：暴力解法 耗时太长  双循环
        //public int[] dailyTemperatures(int[] temperatures) {
        //    if (temperatures == null || temperatures.length == 0) {
        //        return null;
        //    }
        //    int len = temperatures.length;
        //    int[] res = new int[len];
        //    for (int i = 0; i < len; i++) {
        //        for (int j = i + 1; j < len; j++) {
        //            if (temperatures[j] > temperatures[i]) {
        //                res[i] = j - i;
        //                break;
        //            }
        //        }
        //    }
        //    return res;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
