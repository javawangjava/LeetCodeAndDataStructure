/**
 * <p>一个整型数组 <code>nums</code> 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>nums = [4,1,4,6]
 * <strong>输出：</strong>[1,6] 或 [6,1]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>nums = [1,2,10,4,1,4,3,3]
 * <strong>输出：</strong>[2,10] 或 [10,2]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= nums.length &lt;= 10000</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 * <div><div>Related Topics</div><div><li>位运算</li><li>数组</li></div></div><br><div><li>👍 715</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 56 - I
 * 数组中数字出现的次数
 *
 * @author wangweizhou
 * @date 2022-09-25 11:22:31
 */

public class ShuZuZhongShuZiChuXianDeCiShuLcof {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ShuZuZhongShuZiChuXianDeCiShuLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 异或运算的一个性质：任何一个数字异或它自己都等于0。
        // 也就是说，如果我们从头到尾依次异或数组中的每个数字，那么最终的结果刚好是那个只出现一次的数字，因为那些成对出现两次的数字全部在异或中抵消了。
        // 我们试着把原数组分成两个子数组，使得每个子数组包含一个只出现一次的数字，而其他数字都成对出现两次。
        // 如果能够这样拆分成两个数组，那么我们就可以按照前面的办法分别找出两个只出现一次的数字了。


        // 我们还是从头到尾依次异或数组中的每个数字，那么最终得到的结果就是两个只出现一次的数字的异或结果，因为其他数字都出现了两次，在异或中全部抵消了。
        // 由于这两个数字肯定不一样，那么异或的结果肯定不为0，也就是说，在这个结果数字的二进制表示中至少有一位为1。
        // 我们在结果数字中找到第一个为1的位的位置，记为第n位。
        // 现在我们以第n位是不是1为标准把原数组中的数字分成两个子数组，第一个子数组中每个数字的第n位都是1，而第二个子数组中每个数字的第n位都是0。
        // 由于我们分组的标准是数字中的某一位是1还是0，那么出现了两次的数字肯定被分配到同一个子数组。
        // 因为两个相同的数字的任意一位都是相同的，我们不可能把两个相同的数字分配到两个子数组中去，于是我们已经把原数组分成了两个子数组，每个子数组都包含一个只出现一次的数字，而其他数字都出现了两次。



        // 解法2: 异或
        // 前置知识：  1^0 = 1   1^1 = 0   0^0 = 0。
        // 因为相同的数字异或为0，任何数字与0异或结果是其本身。
        // 所以遍历异或整个数组最后得到的结果就是两个只出现一次的数字异或的结果。
        // 为啥叫异或，就是如果不同就返回1，相同就返回0，所以如果有两个数相同  010111 ^ 010111 = 0 异或就为0。

        public int[] singleNumbers(int[] nums) {
            if (nums == null || nums.length == 0) {
                return nums;
            }
            int resXOR = 0;// 数组中所有元素的异或结果
            for (int num : nums) {// 遍历数组，异或数组中的每一个数字，那么最终得到的结果就是两个只出现一次的数字的异或结果
                resXOR ^= num;
            }
            int indexBit1 = 1;// indexBit1用来确定异或结果的二进制的第index位是1。
            // 因为两个只出现一次的数字不相等，所以两个数的异或结果肯定不是0。在异或结果的二进制中找到第一个为1的位置，记为第n位。
            while ((resXOR & indexBit1) == 0) {
                // 将数字1向左移动i次，那么就是二进制形式的第(31-i)位。其实在这里我们并不关心二进制的那一位是1。这只是作为后续分类的标准。
                indexBit1 <<= 1;
            }
            int bit1 = 0;
            int bit0 = 0;
            // 由于我们分组的标准是数字中的某一位是1还是0，那么出现了两次的数字肯定被分配到同一个子数组。
            // 现在我们以第n位【indexBit1所在的位】是不是1为标准把原数组中的数字分成两个子数组，第一个子数组中每个数字的第n位都是1，而第二个子数组中每个数字的第n位都是0。
            for (int num : nums) {
                // 这里只能用位与运算是0还是非0来判断， 1^0 = 1   1^1 = 0   0^0 = 0。
                // 因为indexBit1这个数的二进制只有一位【倒数第indexBit1】是1，其他二进制位都是0，
                // 所以num & indexBit1) != 0意思就是倒数第indexBit1【其实理解为indexBit1位就可以，具体是不是倒数第indexBit1其实不是关键】是1。
                // 因为位与运算结果至少有一位是1，那么位与运算结果肯定不是0。
                if ((num & indexBit1) != 0) {// 子数组中每个数字的第n位都是1
                    bit1 ^= num;
                } else {// 子数组中每个数字的第n位都是0。
                    bit0 ^= num;
                }
            }
            return new int[]{bit1, bit0};
        }





        //// 解法1：哈希表
        //public int[] singleNumbers(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return nums;
        //    }
        //    Set<Integer> set = new HashSet<>();
        //    int len = nums.length;
        //    for (int i = 0; i < len; i++) {
        //        if (set.contains(nums[i])) {
        //            set.remove(nums[i]);
        //        } else {
        //            set.add(nums[i]);
        //        }
        //    }
        //    int[] res = new int[2];
        //    int index = 0;
        //    for (Integer num : set) {
        //        res[index] = num;
        //        index++;
        //    }
        //    //Iterator<Integer> iter=set.iterator();
        //    //while(iter.hasNext()){
        //    //	res[index]=iter.next();
        //    //}
        //    return res;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
