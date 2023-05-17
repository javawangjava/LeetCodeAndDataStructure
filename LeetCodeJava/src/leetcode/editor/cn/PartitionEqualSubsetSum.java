/**
 * <p>给你一个 <strong>只包含正整数 </strong>的 <strong>非空 </strong>数组 <code>nums</code> 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,5,11,5]
 * <strong>输出：</strong>true
 * <strong>解释：</strong>数组可以分割成 [1, 5, 5] 和 [11] 。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3,5]
 * <strong>输出：</strong>false
 * <strong>解释：</strong>数组不能分割成两个元素和相等的子集。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 200</code></li>
 * <li><code>1 <= nums[i] <= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1569</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 416
 * 分割等和子集
 *
 * @author wangweizhou
 * @date 2022-12-02 15:55:38
 */

public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new PartitionEqualSubsetSum().new Solution();
        int[] nums={3,4,1};
        System.out.println(solution.canPartition(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //	如果能够将数组中的数字分成和相等的两部分，那么数组中所有数字的和（记为sum）应该是一个偶数。
        //	也可以换一个角度来描述这个问题：能否从数组中选出若干数字，使它们的和等于sum/2（将sum/2记为t）。

        // 应用动态规划的关键在于确定动态转移方程。
        // 可以用函数f（i，j）表示能否从前i个物品（物品标号分别为0，1，…，i-1）中选择若干物品放满容量为j的背包。
        // 如果总共有n个物品，背包的容量为t，那么f（n，t）就是问题的解。
        // 当判断能否从前i个物品中选择若干物品放满容量为j的背包时，对标号为i-1的物品有两个选择。
        // 如果 j≥nums[i-1]，则对于当前的数字 nums[i-1]，可以选取也可以不选取。
        // 一个选择是将标号为i-1的物品放入背包中，如果能从前i-1个物品（物品标号分别为0，1，…，i-2）中选择若干物品放满容量为j-nums[i-1]的背包（即f（i-1，j-nums[i-1]）为true），
        // 那么f（i，j）就为true。如果选取 nums[i-1]，则 dp[i][j]=dp[i−1][j−nums[i-1]]。
        // 另一个选择是不将标号为i-1的物品放入背包中，则f（i，j）=f（i-1，j）。 如果从前i-1个物品中选择若干物品放满容量为j的背包（即f（i-1，j）为true），那么f（i，j）也为true。
        // 如果不选取nums[i-1]，则 dp[i][j]=dp[i−1][j]；
        // 如果 j<nums[i-1]，则在选取的数字的和等于 j 的情况下无法选取当前的数字 nums[i-1]，因此有 dp[i][j]=dp[i−1][j]。


        // 当j等于0时，即背包的容量为0，不论有多少个物品，只要什么物品都不选择，就能使选中的物品的总重量为0，因此f（i，0）都为true。
        // 当i等于0时，即物品的数量为0，肯定无法用0个物品来放满容量大于0的背包，因此当j大于0时f（0，j）都为false。
        // 因为状态转移方程有f(-1,j)或者f(i,-1)，所以将状态方程统一右移一个单位。


        // 状态定义：可以用函数f（i，j）表示能否从前i个物品（物品标号分别为0，1，…，i-1）中选择若干物品放满容量为j的背包。
        //// 解法1: 动态规划  写法1   存在型问题，只要找到一个就可以
        public boolean canPartition(int[] nums) {
            if (nums == null || nums.length < 2) {// 如果 n<2，则不可能将数组分割成元素和相等的两个子集，因此直接返回 false。
                return false;
            }
            int sum = 0;
            for (int num : nums) {// 求数组和
                sum += num;
            }
            if (sum % 2 == 1) {// 数组和是奇数，则不存在
                return false;
            }
            return subsetSum(nums, sum / 2);
        }


        // 可以用函数f（i，j）表示能否从前i个物品（物品标号分别为0，1，…，i-1）中选择若干物品放满容量为j的背包。
        // 因为状态转移方程有f(-1,j)或者f(i,-1)，所以将状态方程统一右移一个单位。
        // 因为参数是数组，数组下标从0到i，已经有（i+1）个元素。
        // dp[i+1][j+1]表示从[0,i]个物品中选择若干物品放满容量为j的背包。
        private boolean subsetSum(int[] nums, int target) {
            boolean[][] dp = new boolean[nums.length + 1][target + 1];
            // 当j等于0时，即背包的容量为0，不论有多少个物品，只要什么物品都不选择，就能使选中的物品的总重量为0，因此f（i，0）都为true。
            for (int i = 0; i <= nums.length; i++) {// 先用一个for循环将数组dp的第1列（j等于0）的值都设为true。
                dp[i][0] = true;
            }

            // 由于Java语言的boolean类型的默认值为false，因此省略了将数组dp的第1行的其他值（i等于0、j大于0）设为false的代码。
            for (int j = 1; j <=target; j++) {// i=0，表示没有物品可提供给选择来装入背包
            	dp[0][j]=false;
            }

            for (int i = 1; i <= nums.length; i++) {
                for (int j = 1; j <= target; j++) {
                    // 对标号为i-1的物品有两个选择。选择添加不添加该元素的时候，首先先要判断剩余的空间可以装当前元素吗？
                    if (j >= nums[i - 1]) {// 如果 j≥nums[i]，则对于当前的数字 nums[i]，可以选取也可以不选取，两种情况只要有一个为 true，就有 dp[i][j]=true。
                        dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                    } else {// 如果 j<nums[i]，则在选取的数字的和等于 j 的情况下无法选取当前的数字 nums[i]，因此有 dp[i][j]=dp[i−1][j]。
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[nums.length][target];
        }






        //// 解法1: 动态规划  写法2 这个写法和上面一样，但是不是很好理解
        //public boolean canPartition(int[] nums) {
        //    if (nums == null || nums.length <2) {// 如果 n<2，则不可能将数组分割成元素和相等的两个子集，因此直接返回 false。
        //        return false;
        //    }
        //    int sum = 0;
        //    for (int num : nums) {// 求数组和
        //        sum += num;
        //    }
        //    if (sum % 2 == 1) {// 数组和是奇数，则不存在
        //        return false;
        //    }
        //    return subsetSum(nums, sum / 2);
        //}
        //
        //
        //// 可以用函数f（i，j）表示能否从前i个物品（物品标号分别为0，1，…，i-1）中选择若干物品放满容量为j的背包。
        //// 因为状态转移方程有f(-1,j)或者f(i,-1)，所以将状态方程统一右移一个单位。
        //// 因为参数是数组，数组下标从0到i，已经有（i+1）个元素。
        //// dp[i+1][j+1]表示从[0,i]个物品中选择若干物品放满容量为j的背包。
        //private boolean subsetSum(int[] nums, int target) {
        //    boolean[][] dp = new boolean[nums.length + 1][target + 1];
        //    // 当j等于0时，即背包的容量为0，不论有多少个物品，只要什么物品都不选择，就能使选中的物品的总重量为0，因此f（i，0）都为true。
        //    for (int i = 0; i <= nums.length; i++) {// 先用一个for循环将数组dp的第1列（j等于0）的值都设为true。
        //        dp[i][0] = true;
        //    }
        //    // 由于Java语言的boolean类型的默认值为false，因此省略了将数组dp的第1行的其他值（i等于0、j大于0）设为false的代码。
        //    //for (int j = 1; j <=nums.length ; j++) {// i=0，表示没有物品可提供给选择来装入背包
        //    //	dp[0][j]=false;
        //    //}
        //    for (int i = 1; i <= nums.length; i++) {
        //        for (int j = 1; j <= target; j++) {
        //
        //            // 当遍历到第i个【标号为i-1】物品时，对于该物品有两种选择：
        //            // 情况1：不将标号为i-1的物品放入背包中，则f（i，j）=f（i-1，j）。
        //            //如果从前i-1个物品中选择若干物品放满容量为j的背包（即f（i-1，j）为true），那么f（i，j）也为true。
        //            // 如果不能从前i-1个物品中选择若干物品放满容量为j的背包（即f（i-1，j）为false），那么f（i，j）也为false。
        //            // 情况2：将标号为i-1的物品放入背包中,那么此时需要包中剩余的容量大于标号为（i-1）的物品的重量，则f（i，j）=f（i-1，j - nums[i - 1]）。
        //            // 如果能从前i-1个物品（物品标号分别为0，1，…，i-2）中选择若干物品放满容量为j-nums[i-1]的背包（即f（i-1，j-nums[i-1]）为true），那么f（i，j）就为true。
        //            // 如果不能从前i-1个物品（物品标号分别为0，1，…，i-2）中选择若干物品放满容量为j-nums[i-1]的背包（即f（i-1，j-nums[i-1]）为false），那么f（i，j）就为false。
        //
        //            dp[i][j] = dp[i - 1][j];// 一个选择是不将标号为i-1的物品放入背包中，则f（i，j）=f（i-1，j）
        //            // 因为前面已经执行了 dp[i][j] = dp[i - 1][j];。
        //            // 若dp[i][j]=true,那就表示已经装满了容量为j的袋子，那么!dp[i][j]=false,那么就不能将标号为i-1的物品放入背包中，下面的if就不在执行。
        //            // 若dp[i][j]=false,那就表示在标号[0,i-1]的物品中选择的若干物品没有放满容量的为j的背包，则!dp[i][j]=true。
        //            // j>=nums[i-1]表示背包的剩余容量大于nums[i-1]，那么可以选择将nums[i-1]放入背包。
        //            if (!dp[i][j] && j >= nums[i - 1]) {
        //                // 将nums[i-1]的元素放入背包中
        //                dp[i][j] = dp[i - 1][j - nums[i - 1]];
        //            }
        //        }
        //    }
        //    return dp[nums.length][target];
        //}






        //// 解法1: 动态规划+优化空间
        //// 可以优化空间效率。需要注意的是，上述代码在计算f（i，j）时，只需要用到行号为i-1的值，因此保存表格中的两行就可以。可以创建一个只有两行的数组dp，f（i，j）保存在“dp[i%2][j]”中。
        //
        //// 还可以再进一步优化空间效率。如果f（i，j）和f（i-1，j）可以保存到数组的同一个位置，那么只需要一个一维数组。
        //// 如果按照从左到右的顺序填充表格，f（i-1，j）在计算完f（i，j）之后还可能在计算右边其他值时被用到，那么不能用f（i，j）替换f（i-1，j）。
        //// 但是如果按照从右到左的顺序填充表格，f（i-1，j）在计算完f（i，j）之后就再也不会被用到，f（i-1，j）被f（i，j）替换掉不会引起任何问题。
        //// 优化空间效率之后，代码中的f（i，j）和f（i-1，j）都保存在“dp[j]”中。
        //// 上述代码看起来只考虑了当选择下标为i-1的数字时f（i，j）等于f（i-1，j-nums[i-1]）的场景。
        //// 这是因为当不选择下标为i-1的数字时，f（i，j）等于f（i-1，j），而f（i，j）和f（i-1，j）都保存在“dp[j]”中，写成代码就是“dp[j]=dp[j]”，这一行代码被省略了。
        //
        //private boolean subsetSum(int[] nums,int target){
        //	boolean[] dp=new boolean[target+1];
        //	// 由于Java语言的boolean类型的默认值为false，因此省略了将数组dp的第1行的其他值（i等于0、j大于0）设为false的代码。
        //	// 当j等于0时，即背包的容量为0，不论有多少个物品，只要什么物品都不选择，就能使选中的物品的总重量为0，因此f（i，0）都为true。
        //    dp[0]=true;// 将数组dp的第1列（j等于0）的值都设为true。
        //
        //	for (int i = 1; i <=nums.length ; i++) {
        //        // 但是如果按照从右到左的顺序填充表格，f（i-1，j）在计算完f（i，j）之后就再也不会被用到，f（i-1，j）被f（i，j）替换掉不会引起任何问题。
        //		for (int j = target; j>0 ; j--) {
        //            // 优化空间效率之后，代码中的f（i，j）和f（i-1，j）都保存在“dp[j]”中。
        //            // 上述代码看起来只考虑了当选择下标为i-1的数字时f（i，j）等于f（i-1，j-nums[i-1]）的场景。
        //            // 这是因为当不选择下标为i-1的数字时，f（i，j）等于f（i-1，j），
        //            // 而f（i，j）和f（i-1，j）都保存在“dp[j]”中，写成代码就是“dp[j]=dp[j]”，这一行代码被省略了。
        //            // dp[j]为false,就说明没有找到等和子集
        //            if(!dp[j]&&j>=nums[i-1]){
        //				dp[j]=dp[j-nums[i-1]];
        //			}
        //		}
        //	}
        //	return dp[target];
        //}





        //// 解法1：递归
        //public boolean canPartition(int[] nums) {
        //	if(nums==null||nums.length==0){
        //		return false;
        //	}
        //	int sum=0;
        //	for(int num:nums){// 求数组和
        //		sum+=num;
        //	}
        //	if(sum%2==1){// 数组和是奇数，则不存在
        //		return false;
        //	}
        //	return subsetSum(nums,sum/2);
        //}
        //
        //
        //private boolean subsetSum(int[] nums,int target){
        //	Boolean[][] dp=new Boolean[nums.length+1][target+1];
        //	return helper(nums,dp,nums.length,target);
        //}
        //
        //
        //private boolean helper(int[] nums,Boolean[][] dp,int i,int j){
        //	if(dp[i][j]==null){
        //		if(j==0){// 目标重量为0，那么不选任何物品就可以
        //			dp[i][j]=true;
        //		}else if(i==0){// 物品总数为0
        //			dp[i][j]=false;
        //		}
        //	}else{
        //		dp[i][j]=helper(nums,dp,i-1,j);// 不放入nums[i]
        //		if(!dp[i][j]&&j>=nums[i-1]){// 没装满
        //			dp[i][j]=helper(nums,dp,i-1,j-nums[i-1]);// 放入nums[i]
        //		}
        //	}
        //	return dp[i][j];
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
