/**
 * <p>给你一个整数数组 <code>nums</code> ，设计算法来打乱一个没有重复元素的数组。打乱后，数组的所有排列应该是&nbsp;<strong>等可能</strong>&nbsp;的。</p>
 *
 * <p>实现 <code>Solution</code> class:</p>
 *
 * <ul>
 * <li><code>Solution(int[] nums)</code> 使用整数数组 <code>nums</code> 初始化对象</li>
 * <li><code>int[] reset()</code> 重设数组到它的初始状态并返回</li>
 * <li><code>int[] shuffle()</code> 返回数组随机打乱后的结果</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入</strong>
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * <strong>输出</strong>
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * <strong>解释</strong>
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
 * solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
 * solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 50</code></li>
 * <li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
 * <li><code>nums</code> 中的所有元素都是 <strong>唯一的</strong></li>
 * <li>最多可以调用 <code>10<sup>4</sup></code> 次 <code>reset</code> 和 <code>shuffle</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>数学</li><li>随机化</li></div></div><br><div><li>👍 285</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 打乱数组
 *
 * @author wangweizhou
 * @date 2022-06-28 14:50:33
 */

public class ShuffleAnArray {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new ShuffleAnArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 方法一：暴力
        // 使用 nums 来存储当前数组，并用 original 来存储数组的初始状态。在需要重设数组到它的初始状态时，只需要将original 复制到nums 并返回即可。

        int[] nums;
        int[] original;

        public Solution(int[] nums) {
            this.nums = nums;
            this.original = new int[nums.length];
            System.arraycopy(nums, 0, original, 0, nums.length);//original 来存储数组的初始状态
        }

        public int[] reset() {//重置数组恢复初始状态，复制original 数组即可
            System.arraycopy(original, 0, nums, 0, nums.length);
            return nums;
        }

       /* public int[] shuffle() {
            int[] shuffled = new int[nums.length];
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; ++i) {//把数组元素存储在List中
                list.add(nums[i]);
            }
            Random random = new Random();
            for (int i = 0; i < nums.length; ++i) {
                int j = random.nextInt(list.size());// 产生数组大小范围内的随机数
                shuffled[i] = list.remove(j);
            }
            System.arraycopy(shuffled, 0, nums, 0, nums.length);
            return nums;
        }*/


        // 方法二：Fisher-Yates 洗牌算法  看的不太懂
        // 考虑通过调整 waiting 的实现方式以优化方法一。
        // 我们可以在移除 waiting 的第 k 个元素时，将第 k 个元素与数组的最后 1 个元素交换，然后移除交换后数组的最后 1个元素，这样我们只需要 O(1)的时间复杂度即可完成移除第 k 个元素的操作。
        // 此时，被移除的交换后数组的最后 1 个元素即为我们根据随机下标获取的元素。
        // 在此基础上，我们也可以不移除最后 1 个元素，而直接将其作为乱序后的结果，并更新待乱序数组的长度，从而实现数组的原地乱序。
        // 因为我们不再需要从数组中移除元素，所以也可以将第 k 个元素与第 1 个元素交换。


        //具体地，实现算法如下：
        //设待原地乱序的数组nums。
        //循环 n 次，在第 i 次循环中（0≤i<n）：
        //在 [i,n) 中随机抽取一个下标 j；
        //将第 i 个元素与第 j 个元素交换。
        //其中数组中的 nums[i，n−1] 的部分为待乱序的数组，其长度为 n-i；
        // nums[0，i−1] 的部分为乱序后的数组，其长度为 i。

        public int[] shuffle() {
            Random random = new Random();
            for (int i = 0; i < nums.length; ++i) {
                // int nextInt()  返回下一个伪随机数，从该随机数生成器的序列中均匀分布 int值。
                // random.nextInt(nums.length - i)产生[0,nums.length - i)的随机数，
                // i + random.nextInt(nums.length - i)产生[i，n）的随机数，因为[0,i-1)中已经填了
                int j = i + random.nextInt(nums.length - i);  // 在 [i,n) 中随机抽取一个下标 j；
                //将第 i 个元素与第 j 个元素交换。因为要是不交换，那么原数组中就少一个有效元素，所以需要交换。
                // 把根据下标产生的随机数进而产生数组的随机数，插入到已经乱序的数组nums[0 .. i−1] 中
                // nums[0，i−1] 的部分为乱序后的数组，其长度为 i。其中数组中的 nums[i，n−1] 的部分为待乱序的数组，其长度为 n-i，其他带乱序的数组元素要从中选取；
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            return nums;
        }


        // 洗牌算法3
        //共有 n 个不同的数，根据每个位置能够选择什么数，共有 n! 种组合。
        //题目要求每次调用 shuffle 时等概率返回某个方案，或者说每个元素都够等概率出现在每个位置中。
        //我们可以使用 KnuthKnuth 洗牌算法，在 O(n) 复杂度内等概率返回某个方案。
        //具体的，我们从前往后尝试填充 [0,n−1] 该填入什么数时，通过随机当前下标与（剩余的）哪个下标进行值交换来实现。
        //对于下标 xx 而言，我们从 [x,n−1] 中随机出一个位置与 x 进行值交换，当所有位置都进行这样的处理后，我们便得到了一个公平的洗牌方案。

/*
        public int[] shuffle() {
            int[] ans = nums.clone();
            for (int i = 0; i < nums.length; i++) {
                Random random = new Random();
                // random.nextInt(nums.length - i)产生[0,nums.length - i)的随机数，
                // i + random.nextInt(nums.length - i)产生[i，n）的随机数
                swap(ans, i, i + random.nextInt(nums.length - i));
            }
            return ans;
        }
        void swap(int[] arr, int i, int j) {
            int c = arr[i];
            arr[i] = arr[j];
            arr[j] = c;
        }*/

    }
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
