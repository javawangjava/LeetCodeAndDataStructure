/**
 * <p>给你一个整数数组 <code>nums</code> 和一个整数&nbsp;<code>k</code> ，请你统计并返回 <em>该数组中和为&nbsp;<code>k</code><strong>&nbsp;
 * </strong>的子数组的个数&nbsp;</em>。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,1,1], k = 2
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3], k = 3
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
 * <li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
 * <li><code>-10<sup>7</sup> &lt;= k &lt;= 10<sup>7</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>前缀和</li></div></div><br><div><li>👍 1542</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 560
 * 和为 K 的子数组
 *
 * @author wangweizhou
 * @date 2022-06-22 20:18:30
 */

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new SubarraySumEqualsK().new Solution();
        int[] nums = {1, 2, 3};
        //int[] nums = {2,3,1,2,4,3};
        int a = solution.subarraySum(nums, 3);
        System.out.println(a);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {



        public int subarraySum(int[] nums, int k) {
            if(nums==null||nums.length==0){
                return 0;
            }
            int len=nums.length;
            int[] sums=new int[len+1];
            int count=0;
            for (int i = 0; i < len ; i++) {
                sums[i+1]=sums[i]+nums[i];
            }
            Map<Integer,Integer> map=new HashMap<>();
            map.put(sums[0],1);
            for (int i = 0; i < len; i++) {
                if(map.containsKey(sums[i+1]-k)){
                    count+=map.get(sums[i+1]-k);
                }else {
                    map.put(sums[i+1],map.getOrDefault(sums[i+1],0 )+1);
                }
            }
            return count;
        }






        // 参见 525

        //// 解法2：前缀和数组【记忆化】+哈希表优化  写法3
        //// 因为滑动窗口左边界不知道，是利用哈希表来确定的，类似与左开右闭。
        //public int subarraySum(int[] nums, int k) {
        //    if (nums == null || nums.length == 0) {
        //        return 0;
        //    }
        //    int len = nums.length;
        //    int count = 0;// 满足条件的子数组个数
        //    int[] sums = new int[len + 1];// sums[i+1]表示截止到元素nums[i]【包含i】的前i项和。即截止到下标i【包含i】的前项和，则sums[0]==0。
        //    Map<Integer, Integer> map = new HashMap<>();   // 哈希表map的键key是前缀和的，value是对应的前缀和的出现次数
        //    // sums[0]==0。下标为0的位置前面没有元素，所以前缀和为 0，个数为 1。等价于map.put(0, 1)。把这一个就理解为类似动态优化的初始状态
        //    map.put(sums[0], 1);
        //    for (int i = 0; i < len; i++) {// 获得前缀和数组
        //        sums[i + 1] = sums[i] + nums[i];
        //    }
        //    for (int i = 0; i < len; i++) {// 指针i每移动一位，滑动窗口中就新加入一个元素
        //        // 滑动窗口中每加入一个元素nums[i]，就判断nums[i]之前的子数组中是否有前缀和为（sums[i+1] - k）的子序列
        //        if (map.containsKey(sums[i + 1] - k)) {
        //            count = count + map.get(sums[i + 1] - k);
        //        }
        //        // 将[0,i]的前缀和加入到哈希表中
        //        map.put(sums[i + 1], map.getOrDefault(sums[i + 1], 0) + 1);
        //    }
        //    return count;
        //}




        //
        // 在求一个长度为i的子数组的数字之和时，应该把该子数组看成在长度为i-1的子数组的基础上添加一个新的数字。
        // 如果之前已经求出了长度为i-1的子数组的数字之和，那么只要再加上新添加的数字就能得出长度为i的子数组的数字之和，只需要一次加法，因此需要O（1）的时间。优化之后总的时间复杂度是O（n2）。
        // 下面换一种思路，在从头到尾逐个扫描数组中的数字时求出前i个数字之和，并且将和保存下来。数组的前i个数字之和记为x。
        // 如果存在一个j（j＜i），数组的前j个数字之和为x-k，那么数组中从第i+1个数字开始到第j个数字结束的子数组之和为k。
        // 这个题目需要计算和为k的子数组的个数。
        // 当扫描到数组的第i个数字并求得前i个数字之和是x时，需要知道在i之前存在多少个j并且前j个数字之和等于x-k。
        // 所以，对每个i，不但要保存前i个数字之和，还要保存每个和出现的次数。分析到这里就会知道我们需要一个哈希表，哈希表的键是前i个数字之和，值为每个和出现的次数。


        // 解法2：前缀和数组【记忆化】+哈希表优化  写法1
        // 在滑动窗口[0,right]中找合适的子数组[left,right]使得子数组和等于k。
        // sums[i+1]表示截止到元素nums[i]【包含i】的前i项和。即截止到下标i【包含i】的前项和。则sums[0]==0。
        // sums[i+1]-sums[j]=k表示元素nums[j-1]到元素nums[i]的子数组和,则区间[j,i]的元素和为k,注意，j和i可以重合。则sums[i+1]-k=sums[j]。
        // 注意在下标0之前没有元素，那么可以认为下标0之前的所有元素的和为0，出现次数为1。

        //public int subarraySum(int[] nums, int k) {
        //    if (nums == null || nums.length == 0) {
        //        return 0;
        //    }
        //    int length = nums.length;
        //    int count = 0;//满足条件的子数组个数
        //    Map<Integer, Integer> map = new HashMap<>();   // 哈希表map的键key是前缀和的，value是对应的前缀和的出现次数
        //    int[] sums=new int[length+1];// sums[i+1]表示截止到元素nums[i]【包含i】的前i项和。即截止到下标i【包含i】的前项和，则sums[0]==0。
        //    map.put(sums[0], 1);// sums[0]==0。下标为0的位置前面没有元素，所以前缀和为 0，个数为 1。等价于map.put(0, 1)。
        //
        //    // sums[i+1]表示截止到下标i【包含i】的前项和。为了方便从第一项开始计算，不然就得单独处理第一项
        //    for (int i = 0; i < length; i++) {// 指针i每移动一位，滑动窗口中就新加入一个元素
        //        // 滑动窗口中每加入一个元素，就更新前缀和，然后计算在当前位置之前有没有前缀和为（sums[i+1] - k）的子序列，然后再将更新后的前缀和添加到哈希表中
        //        sums[i+1]=sums[i]+nums[i];
        //        // 需要计算sums[i+1]之前的子数组中前缀和为sums[i+1] - k的子数组个数。
        //        count += map.getOrDefault(sums[i+1] - k, 0);
        //        map.put(sums[i+1], map.getOrDefault(sums[i+1], 0) + 1);//将前缀和sums[i+1]加入map
        //    }
        //    return count;
        //}





        //// 解法2：前缀和+哈希表优化+滚动数组  写法1优化为动态数组
        //// 哈希表map: key：截止到元素nums[i]也就是截止到数组下标i的前缀和，value：key 对应的前缀和的个数
        //// 在滑动窗口[0,right]中找合适的子数组[left,right]使得子数组和等于k
        //// sums[i+1]-sums[j]=k，则区间[j,i]的元素和为k,注意，j和i可以重合。则sums[i+1]-k=sums[j]

        //public int subarraySum(int[] nums, int k) {
        //    if (nums == null || nums.length == 0) {
        //        return 0;
        //    }
        //    int length = nums.length;
        //    int count = 0;// 满足条件的子数组个数
        //    int sum = 0;// 动态数组，每次计算前缀和时只需要前一个位置的前缀和
        //
        //    // 哈希表map: key：截止到元素nums[i]也就是截止到数组下标i的前缀和，value：key 对应的前缀和的个数
        //    Map<Integer, Integer> map = new HashMap<>();
        //    map.put(sum, 1);//对于下标为0的元素，前面没有元素，则记其前缀和为0，个数为1
        //
        //    for (int i = 0; i < length; i++) {// 指针i每移动一位，滑动窗口中就新加入一个元素
        //        // 滑动窗口中每加入一个元素，就更新前缀和，然后计算在当前位置之前有没有前缀和为（sums[i+1] - k）的子序列，然后再将更新后的前缀和添加到哈希表中
        //        sum += nums[i];//sum表示累加到nums[i]的和，sum表示截止到下标i的和。
        //        count += map.getOrDefault(sum - k, 0);
        //        map.put(sum, map.getOrDefault(sum, 0) + 1);//将第i项的前缀和加入map
        //    }
        //    return count;
        //}





        /*

        //解法1：前缀和数组+双指针形成动态窗口 写法3
        public int subarraySum(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            int count = 0;//满足条件的子数组个数
            // sumsTo[i+1]表示截止到元素nums[i]的前i项和，即截止到下标i的前项和
            int[] sumsTo = new int[length + 1];
            sumsTo[0] = 0;//对于下标为 0 的元素，前面没有元素，sumsTo[0] = 0，
            // 计算前缀和数组
            for (int i = 0; i < length; i++) {
                sumsTo[i + 1] = sumsTo[i] + nums[i];
            }

            // 双指针来确定区间和，注意下标和代码细节前后匹配
            // 在[left,len-1]中找找合适的子数组[left,right]使得子数组和等于k
            // sumsTo[i+1]表示截止到元素nums[i]【包含i不包含（i+1)】的前i项和
            for (int left = 0;left < length; left++) {
                for (int right = left; right < length; right++) {
                    // sumsTo[right + 1] - sumsTo[left] 就是区间和[left,right],注意右端点下标偏移
                    if (sumsTo[right + 1] - sumsTo[left] == k) {
                        count++;
                    }
                }
            }
            return count;
        }
        */




        /*
        //解法1：前缀和数组+双指针形成动态窗口 写法4
        // sums[i]表示截止到元素nums[i]【包含i】的前i项和。即截止到下标i【包含i】的前项和
        public int subarraySum(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int len=nums.length;
            int count=0;//满足条件的子数组个数
            int[] sums=new int[len];
            sums[0]=nums[0];
            // sums[i]表示截止到元素nums[i]【包含i】的前i项和。即截止到下标i【包含i】的前项和
            for (int i = 1; i < len; i++) {
                sums[i]=sums[i-1]+nums[i];
            }
           // 在[left,len-1]中找找合适的子数组[left,right]使得子数组和等于k
            for (int left = 0; left < len; left++) {
                for (int right = left; right < len; right++) {
                    // sums[right]-sums[left]则表示(left,right].所以区间和要加上nums[left]。这样就形成了双闭区间
                    if(sums[right]-sums[left]+nums[left]==k){
                        count++;
                    }
                }
            }
            return count;
        }
        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
