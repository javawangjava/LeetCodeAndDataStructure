/**
 * <p>在一个数组 <code>nums</code> 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>nums = [3,4,3,3]
 * <strong>输出：</strong>4
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>nums = [9,1,7,9,7,9,7]
 * <strong>输出：</strong>1</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10000</code></li>
 * <li><code>1 &lt;= nums[i] &lt; 2^31</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <div><div>Related Topics</div><div><li>位运算</li><li>数组</li></div></div><br><div><li>👍 404</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 56 - II
 * 数组中数字出现的次数 II
 *
 * @author wangweizhou
 * @date 2022-12-26 17:40:08
 */

public class ShuZuZhongShuZiChuXianDeCiShuIiLcof {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ShuZuZhongShuZiChuXianDeCiShuIiLcof().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 解法2：哈希表  哈希表的键key是数组元素，value是数组元素对应的次数
        //public int singleNumber(int[] nums) {
        //    if (nums == null || nums.length == 0) {// 判空
        //        return Integer.MIN_VALUE;
        //    }
        //    Map<Integer, Integer> map = new HashMap<>();
        //    int len = nums.length;
        //    for (int i = 0; i < len; i++) {// 遍历数组元素将数组元素添加到哈希表中
        //        map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        //    }
        //    int ans = 0;
        //
        //    //// 方式1：使用 Iterator 遍历 HashMap EntrySet    Entry是键值对
        //    //Iterator<Map.Entry<Integer,Integer>> iter=map.entrySet().iterator();// 迭代器
        //    //while(iter.hasNext()){
        //    //	Map.Entry<Integer,Integer> entry=iter.next();// 获得键值对
        //    //	if(entry.getValue()==1){
        //    //		ans= entry.getKey();
        //    //		break;
        //    //	}
        //    //}
        //
        //    //// 方式2：使用 For-each 循环遍历HashMap  EntrySet
        //    //for(Map.Entry<Integer,Integer> entry:map.entrySet()){
        //    //    if(entry.getValue()==1){
        //    //        ans=entry.getKey();
        //    //    }
        //    //}
        //
        //
        //
        //    //// 方式3：使用 Iterator 遍历 HashMap KeySet    KeySet：键的集合
        //    //Iterator<Integer> iter2 = map.keySet().iterator();
        //    //while (iter2.hasNext()) {
        //    //    Integer key = iter2.next();
        //    //    if (map.get(key) == 1) {
        //    //        ans=key;
        //    //        break;
        //    //    }
        //    //}
        //
        //
        //    // 方式4：ForEach KeySet
        //    for (Integer key:map.keySet()){
        //        if(key==1){
        //            ans=key;
        //        }
        //    }
        //    return ans;
        //}


        //// 解法1：位运算  位与运算：1&1=1；1&0=0；0&0=0。
        // 由于两个相同的数字的异或结果是0，我们把数组中所有数字异或的结果就是那个唯一只出现一次的数字可借这种思路不能解决这里的问题，因为三个相同的数字的异或结果还是该数字。
        // 尽管我们这里不能应用异或运算，我们还是可以沿用位运算的思路。

        // 如果一个数字出现三次，那么它的二进制表示的每一位（0或者1）也出现三次。
        // 如果把所有出现三次的数字的二进制表示的每一位都分别加起来，那么每一位的和都能被3整除。
        // 我们把数组中所有数字的二进制表示的每一位都加起来。如果某一位的和能被3整除，那么那个只出现一次的数字二进制表示中对应的那一位是0；否则就是1。


        //public int singleNumber(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return Integer.MIN_VALUE;
        //    }
        //    int len = nums.length;
        //    int[] bitSums = new int[32];// 数字的二进制形式每一数位的和数组
        //    for (int i = 0; i < len; i++) {// 遍历数组中每一个数字
        //        for (int j = 0; j < 32; j++) {// 遍历每一个数组的二进制形式
        //            // nums[i]每次右移j位，这样第j位就是二进制形式的最右侧一位，与1位与运算，那么就可以得到二进制的第j位。
        //            int jbit = (nums[i] >> j) & 1;// 二进制表示形式从数组最左侧开始,从二进制的最低位开始
        //            if (jbit == 1) {// 当前数字nums[i]二进制的第jBit位是1时，那么二进制数组的第j个元素值加1.
        //                bitSums[31-j]++;
        //            }
        //        }
        //    }
        //
        //    // 根据二进制形式每一位的数组来重建唯一出现一次的数字
        //    int ans=0;
        //    for (int i = 0; i < 32; i++) {
        //        // 当bitSum[i]对3取余时，这时取余结果就是二进制形式当前位的数字
        //        // 每次先左移一位，空出待确定的第i位。
        //        ans=ans<<1;
        //        ans+=bitSums[i]%3;
        //    }
        //    return ans;
        //}




        // 解法1：位运算  写法2
        public int singleNumber(int[] nums) {
            if (nums == null || nums.length == 0) {
                return Integer.MIN_VALUE;
            }
            int len = nums.length;
            int[] bitSums = new int[32];// 数字的二进制形式每一数位的和数组
            for (int i = 0; i < len; i++) {// 遍历数组中每一个数字
                for (int j = 0; j < 32; j++) {// 遍历每一个数组的二进制形式
                    // nums[i]每次右移j位，这样第j位就是二进制形式的最右侧一位，与1位与运算，那么就可以得到二进制的第j位。
                    // 当前数字nums[i]二进制的第j位是1时，那么二进制数组的第j个元素值加1。若是0则加0。
                    bitSums[31 - j] += (nums[i] >> j) & 1;// 注意这里二进制数组形式的高位和低位。
                }
            }

            // 根据二进制形式每一位的数组来重建唯一出现一次的数字
            int ans = 0;
            for (int i = 0; i < 32; i++) {
                // 当bitSum[i]对3取余时，这时取余结果就是二进制形式当前位的数字
                // 每次先左移一位，空出待确定的第i位。
                ans <<= 1;
                ans += bitSums[i] % 3;// 获取结果二进制形式的第i位。ans|=bitSums[i]%3
            }
            return ans;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
