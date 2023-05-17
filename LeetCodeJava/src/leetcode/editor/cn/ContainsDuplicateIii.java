/**
 * <p>给你一个整数数组 <code>nums</code> 和两个整数 <code>k</code> 和 <code>t</code> 。请你判断是否存在 <b>两个不同下标</b> <code>i</code> 和
 * <code>j</code>，使得 <code>abs(nums[i] - nums[j]) <= t</code> ，同时又满足 <code>abs(i - j) <= k</code><em> </em>。</p>
 *
 * <p>如果存在则返回 <code>true</code>，不存在返回 <code>false</code>。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3,1], k<em> </em>= 3, t = 0
 * <strong>输出：</strong>true</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,0,1,1], k<em> </em>=<em> </em>1, t = 2
 * <strong>输出：</strong>true</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,5,9,1,5,9], k = 2, t = 3
 * <strong>输出：</strong>false</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= nums.length <= 2 * 10<sup>4</sup></code></li>
 * <li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
 * <li><code>0 <= k <= 10<sup>4</sup></code></li>
 * <li><code>0 <= t <= 2<sup>31</sup> - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>桶排序</li><li>有序集合</li><li>排序</li><li>滑动窗口</li></div></div><br
 * ><div><li>👍 649</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 220
 * 存在重复元素 III
 *
 * @author wangweizhou
 * @date 2022-09-05 21:11:55
 */

public class ContainsDuplicateIii {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ContainsDuplicateIii().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 解法2：存在性问题:只要找到一个就可以  二叉排序树实现TreeSet

        //// 逐一扫描数组中的每一个数字，对每一个数字nums[i],需要逐一检查在它面前的k个数字是否存在从nums[i]-t到nums[i]+t范围内的数字。
        //// 通过排序简化搜索。可以从小到大排序，找出当前数字的前k个数字中最大的和最小的数字，然后检查最大的和最小的是否是nums[i]-t到nums[i]+t范围内的数字。
        //
        //// 继续简化，对于每个数字nums[i]，应该先从它前面的k个数字中找出小于或等于nums[i]的最大的数字，如果这个数字与nums[i]的差的绝对值不大于t，
        //// 即【nums[i] - t <= lower <= nums[i]】 <= (long) nums[i] + t；那么就找到了一组符合条件的两个数字j。
        //// 否则，再从它前面的k个数字中找出大于或等于nums[i]的最小的数字，比nums[i]大的数不需要再考虑下限nums[i] - t。
        //// 如果这个数字与nums[i]的差的绝对值不大于t，即nums[i] - t <= 【nums[i] <= upper <= (long) nums[i] + t】；就找到了一组符合条件的两个数字。
        //
        //// 需要从一个大小为k的数据容器中找出小于或等于某个数字的最大值及大于或等于某个数字的最小值，这正是TreeSet或TreeMap适用的场景。
        //// 因为这个容器只需要保存数字，所以可以用TreeSet来保存每个数字nums[i]前面的k个数字。
        //
        //// Math.abs((long)nums[i]-nums[j])<=t&&Math.abs(i-j)<=k
        //// Math.abs((long)nums[i]-nums[j])<=t：研究nums[j]的范围 nums[i]-t<=nums[j]<=nums[i]+t；
        //// 研究nums[i]的范围：nums[j]-t<=nums[i]<=nums[j]+t。
        //// Math.abs(i-j)<=k：研究j的范围i-k<=j<=i+k;研究i的范围j-k<=i<=j+k。


        //// 使用有序集合来维护大小为k的滑动窗口。存在性问题:只要找到一个就可以。滑动窗口

        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (nums == null) {
                return false;
            }

            int length = nums.length;
            TreeSet<Long> set = new TreeSet<>();// 二叉搜索树，long为了防止溢出
            // 遍历每个元素，滑动窗口包含了nums[i]的前k个元素，检查是否落在了[nums[i] - t,nums[i] + t]的区间内
            for (int i = 0; i < length; i++) {

                // E floor(E e) 返回此set中小于或等于给定元素的最大元素，如果没有这样的元素，则 null 。
                Long lower = set.floor((long) nums[i]);// 获得小于等于nums[i]的最大元素，即小于等于nums[i]的元素中最接近nums[i]的元素。
                // (long) nums[i] - t <= lower：就是 【nums[i] - t <= lower <= nums[i]】 <=(long) nums[i] + t
                if (lower != null && (long) nums[i] - t <= lower) {// 因为是排序的，只需要检查单边。大于区间下限，则在区间内部
                    return true;
                }

                // E ceiling(E e) 返回此set中大于或等于给定元素的最小元素，如果没有这样的元素，则 null 。
                Long upper = set.ceiling((long) nums[i]);// 获得大于等于nums[i]的最小元素，即大于等于nums[i]的元素中最接近nums[i]的元素。
                // nums[i]<=upper <= (long) nums[i] + t：nums[i] - t <= 【nums[i] <= upper <= (long) nums[i] + t】
                if (upper != null && upper <= (long) nums[i] + t) {// 因为是排序的，只需要检查单边。小于区间上限，则在区间内部
                    return true;
                }

                // Math.abs(i-j)<=k：研究j的范围i-k<=j<=i+k。
                // TreeSet来模拟滑动窗口
                // 将当前数加到 set 集合中，并移除下标范围不在 [max(0, i - k), i) 的数（维持滑动窗口大小为 k）
                set.add((long) nums[i]);
                // i < k时，滑动窗口还没有满，这时候滑动窗口中可以进入新的元素；
                // i >= k时，滑动窗口已经装满了元素，每进入一个新的元素，就将最早进入的元素移出滑动窗口。
                if (i >= k) {
                    set.remove((long) nums[i - k]);
                }
            }
            return false;
        }






        //// 解法1：暴力循环
        // 可以逐一扫描数组中的每个数字。
        // 对于每个数字nums[i]，需要逐一检查在它前面的k个数字是否存在从nums[i]-t到nums[i]+t的范围内的数字。如果存在，则返回true。

        //public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //	if(nums==null){
        //		return false;
        //	}
        //	int len=nums.length;
        //	for (int i = 0; i < len; i++) {
        //		for (int j = i+1; j < len; j++) {
        //			// 注意数组元素的范围，这里为了处理越界将数组元素转换成long型
        //			if(Math.abs((long)nums[i]-nums[j])<=t&&Math.abs(i-j)<=k){
        //				return true;
        //			}
        //		}
        //	}
        //	return false;
        //}




        //// 解法3：桶排序   没看懂
        //// 由于这个题目关心的是差的绝对值小于或等于t的数字，因此可以将数字放入若干大小为t+1的桶中。例如，将从0到t的数字放入编号为0的桶中，
        //// 从t+1到2t+1的数字放入编号为1的桶中。其他数字以此类推。这样做的好处是如果两个数字被放入同一个桶中，那么它们的差的绝对值一定小于或等于t。
        //
        //// 还是逐一扫描数组中的数字。如果当前扫描到数字num，那么它将放入编号为id的桶中。如果这个桶中之前已经有数字，那么就找到两个差的绝对值小于或等于t的数字。
        //// 如果桶中之前没有数字，则再判断编号为id-1和id-2的这两个相邻的桶中是否存在与num的差的绝对值小于或等于t的数字。
        //// 因为其他桶中的数字与num的差的绝对值一定大于t，所以不需要判断其他的桶中是否有符合条件的数字。
        //
        //// buckets是一个HashMap，用来表示大小为t+1的用来装数字的桶，它的键表示桶的编号。
        //// 由于这个题目的每个桶中只能装一个数字，因此buckets的值是装在桶中的一个数字。函数getBucketID用来确定每个数字应该放入的桶的编号。
        //
        //public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //    if (nums == null) {
        //        return false;
        //    }
        //    // 因为一个桶有两个元素就会返回true，因此一个桶只有一个元素，可以用哈希表的一条key-value表示桶
        //    Map<Integer, Integer> buckets = new HashMap<>();
        //    int bucketSize = t + 1;// 桶的大小为t+1，允许最大元素和最小元素之差为t
        //    int len = nums.length;
        //    for (int i = 0; i < len; i++) {
        //        int num = nums[i];
        //        int id = getBucketID(num, bucketSize);
        //        // 【nums[i] - t <= lower <= nums[i]】 <=(long) nums[i] + t
        //        // 桶里已有元素x，nums[i]和x同属一个桶，值符合范围
        //        // 只保留下标 i 之前的 k 个元素，因此下标也符合范围
        //        // 桶有两个元素就会返回，因此一个桶只有一个元素
        //        if (buckets.containsKey(id)) {
        //            return true;
        //        }
        //        // 前一个桶有一个元素，并且值的范围符合要求
        //        if (buckets.containsKey(id - 1) && buckets.get(id - 1) + t >= num) {
        //            return true;
        //        }
        //        // 后一个桶有一个元素，并且值的范围符合要求
        //        if (buckets.containsKey(id + 1) && buckets.get(id + 1) -t <= num) {
        //            return true;
        //        }
        //
        //        buckets.put(id, num);//没有和nums[i]匹配的元素，把nums[i]加入自己的桶里
        //        if (i >= k) {// 下标范围[i-k+1, i]，从nums[i-k]所在桶移除元素
        //            buckets.remove(getBucketID(nums[i - k], bucketSize));
        //        }
        //    }
        //    return false;
        //}
        //
        //
        //// 为什么 size 需要对 t 进行 +1 操作
        ////目的是为了确保绝对值小于等于 t 的数能够落到一个桶中。举个🌰，假设 [0,1,2,3]，t = 3，显然四个数都应该落在一个桶。
        ////如果不对 t 进行 +1 操作的话，那么 [0,1,2] 和 [3] 会被落到不同的桶中，那么为了能让他们落到同一桶中，我们需要对 t 进行加一操作。
        ////这样我们的数轴就能被分割成：
        ////0 1 2 3 | 4 5 6 7 | 8 9 10 11 | 12 …
        ////总结一下，size = t + 1 的本质是因为差值为 t 两个数在数轴上相隔距离为 t + 1，它们需要被落到同一个桶中。
        ////当有了 size 之后，对于正数部分我们则有 idx = nums[i] / size。
        ////如何理解负数部分的逻辑？
        ////由于我们处理正数的时候，处理了 0，因此我们负数部分是从 -1 开始的。
        ////而且此时我们有 t = 3 和 size = t + 1 = 4。考虑🌰，[-4 -3 -2 -1]（它们应该落在一个桶中）
        ////如果直接复用 idx = nums[i] / size 的话，[-4] 和 [-3,-2,-1] 会被分到不同的桶中。
        ////根本原因是我们处理整数的时候，已经分掉了 0。
        ////因此这时候我们需要先对 nums[i] 进行 +1 操作（即将负数部分在数轴上进行整体右移），即得到 (nums[i] +1) / size。
        ////这样就可以将负数部分与正数部分一样，可以被正常分割了。
        ////但是由于 0 号桶已经被使用了，我们需要再在此基础上再 -1，相当于桶数轴（idx）往左移，即得到 (nums[i] +1) / size - 1
        //private int getBucketID(int num, int bucketSize) {
        //    // 非负数区间，如[0, t] 会被归到 id=0;其余的区间，如[(n-1)t+1, nt+1]，每t+1个元素会被归到 id = n-1
        //    if(num>=0){
        //        return num / bucketSize;
        //    }else {
        //        // 负数区间，如[-t, -1] 会被归到 id=-1;
        //        // 其余的区间，如[-(n+1)t-1, -nt-1]，每t+1个元素会被归到 id = -(n+1).
        //        return (num + 1) / bucketSize - 1;
        //    }
        //    //return num >= 0 ? num / bucketSize : (num + 1) / bucketSize - 1;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
