/**
 * <p>给定一个数组 <code>A[0,1,…,n-1]</code>，请构建一个数组 <code>B[0,1,…,n-1]</code>，其中 <code>B[i]</code> 的值是数组 <code>A</code>
 * 中除了下标 <code>i</code> 以外的元素的积, 即 <code>B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]</code>。不能使用除法。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> [1,2,3,4,5]
 * <strong>输出:</strong> [120,60,40,30,24]</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>所有元素乘积之和不会溢出 32 位整数</li>
 * <li><code>a.length <= 100000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>前缀和</li></div></div><br><div><li>👍 270</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 66
 * 构建乘积数组
 *
 * @author wangweizhou
 * @date 2022-09-24 23:46:24
 */

public class GouJianChengJiShuZuLcof {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new GouJianChengJiShuZuLcof().new Solution();
        int[] nums = {1, 2, 3, 4, 5};
        int[] ans = solution.constructArr(nums);
        if (ans.length == 0) {
            System.out.println("长度为0");
        }
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + ",");
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 解法2：解法1优化空间复杂度 +动态数组
        //public int[] constructArr(int[] nums) {
        //	if(nums ==null|| nums.length==0){
        //		return new int[0];
        //	}
        //	int len= nums.length;
        //	int[] left=new int[len];// 定义left[i]表示下标i左侧的数组元素乘积
        //	int[] ans=new int[len];
        //	left[0]=1;
        //	for (int i = 1; i < len; i++) {
        //		// 定义ans[i]表示下标i左侧的数组元素乘积
        //		left[i]=left[i-1]* nums[i-1];
        //	}
        //
        //	int right=1;// right表示当前元素右边的元素乘积
        //	for (int i = len-1; i >=0 ; i--) {
        //		ans[i]=left[i]*right;
        //		right=right*nums[i];
        //	}
        //	return ans;
        //}
        //


        //// 解法1：从下标i分成左右乘积列表
        //// B[i] 的值是数组 A 中除了下标 i 以外的元素的积。意思就是数组A中[0,i)和(i,len-1]的元素的乘积。那么将数组以i为分界线，分成左右两部分。
        //public int[] constructArr(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return new int[0];
        //    }
        //    int len = nums.length;
        //    int[] left = new int[len];// 定义left[i]表示下标i左侧[0,i)的数组元素乘积
        //    int[] right = new int[len];// 定义right[i]表示下标i右侧(i,len-1]的数组元素乘积
        //    int[] ans = new int[len];
        //    left[0] = 1;// 下标0左侧没有元素，这里认为下标0左侧元素的乘积是1
        //    for (int i = 1; i < len; i++) {
        //        left[i] = left[i - 1] * nums[i - 1];
        //    }
        //
        //    right[len - 1] = 1;// 下标len-1右侧没有元素，这里认为下标len-1右侧元素乘积是1
        //    for (int i = len - 2; i >= 0; i--) {
        //        right[i] = right[i + 1] * nums[i + 1];
        //    }
        //
        //    // 计算结果数组
        //    for (int i = 0; i < len; i++) {
        //        ans[i] = left[i] * right[i];
        //    }
        //    return ans;
        //}



        // 下面这个处理成闭区间[0,left] 和区间[right,len-1]
        public int[] constructArr(int[] nums) {
            if (nums == null || nums.length == 0) {
                return new int[0];
            }
            int len = nums.length;
            int[] left = new int[len];// [0,left]的累乘积
            int[] right = new int[len];// [right,len-1]的累乘积
            left[0] = nums[0];
            right[len - 1] = nums[len - 1];
            for (int i = 1; i < len; i++) {
                left[i] = left[i - 1] * nums[i];
            }
            for (int i = len - 2; i >= 0; i--) {
                right[i] = right[i + 1] * nums[i];
            }
            int[] ans = new int[len];
            ans[0] = right[1];
            ans[len - 1] = left[len - 2];
            for (int i = 1; i < len - 1; i++) {
                ans[i] = left[i - 1] * right[i + 1];
            }
            return ans;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
