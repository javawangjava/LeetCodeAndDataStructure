/**
 * <p>给定一个二进制数组 <code>nums</code> , 找到含有相同数量的 <code>0</code> 和 <code>1</code> 的最长连续子数组，并返回该子数组的长度。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [0,1]
 * <strong>输出:</strong> 2
 * <strong>说明:</strong> [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。</pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [0,1,0]
 * <strong>输出:</strong> 2
 * <strong>说明:</strong> [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
 * <li><code>nums[i]</code> 不是 <code>0</code> 就是 <code>1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>前缀和</li></div></div><br><div><li>👍 566</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 525
 * 连续数组
 *
 * @author wangweizhou
 * @date 2022-06-22 20:21:56
 */

public class ContiguousArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ContiguousArray().new Solution();
        int[] nums = {0, 1, 0, 1, 1, 0, 0, 1};
        //int[] nums = {2,3,1,2,4,3};
        int a = solution.findMaxLength(nums);
        System.out.println(a);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 参见560 和为 K 的子数组

        public int findMaxLength(int[] nums) {
            if (nums == null || nums.length == 0) {//
                return 0;
            }
            int len = nums.length;
            int maxLen = 0;
            int[] sums = new int[len + 1];
            for (int i = 0; i < len; i++) {
                sums[i + 1] = sums[i] + (nums[i]==1?1:-1);
            }
            Map<Integer,Integer> map=new HashMap<>();
            map.put(sums[0],-1);
            for (int i = 0; i < len; i++) {
                if(map.containsKey(sums[i+1])){
                    maxLen=Math.max(maxLen,i-map.get(sums[i+1]));
                }else {
                    map.put(sums[i+1],i);
                }
            }
            return maxLen;
        }


        // 首先把输入数组中所有的0都替换成-1，那么题目就变成求包含相同数目的-1和1的最长子数组的长度。
        // 在一个只包含数字1和-1的数组中，如果子数组中-1和1的数目相同，那么子数组的所有数字之和就是0，因此这个题目就变成求数字之和为0的最长子数组的长度。
        // 和前面的解法类似，可以在扫描数组时累加已经扫描过的数字之和。
        // 如果数组中前i个数字之和为m，前j个数字（j>i）之和也为m，那么从第i+1个数字到第j个数字的子数组的数字之和为0，这个和为0的子数组的长度是j-i。
        // 如果扫描到数组的第j个数字并累加得到前j个数字之和m，那么就需要知道是否存在一个i（i＜j）使数组中前i个数字之和也为m。
        // 可以把数组从第1个数字开始到当前扫描的数字累加之和保存到一个哈希表中。
        // 由于我们的目标是求出数字之和为0的最长子数组的长度，因此还需要知道第1次出现累加之和为m时扫描到的数字的下标。
        // 因此，哈希表的键是从第1个数字开始累加到当前扫描到的数字之和，而值是当前扫描的数字的下标。
        // 因为要求子数组的最大长度，所以哈希表中只要保存相同前缀和第一个出现的位置。

        // 后面在哈希表中添加前缀和数组和对应下标的时候只要是一一线性对应的就可以。



        ////   解法2：前缀和数组+哈希表  写法2 注意写法2和写法1的不同在于对于添加到哈希表中的处理不同   这里前缀和平移了
        ////   由于「0 和 1 的数量相同」等价于「1 的数量减去 0 的数量等于 0」。
        ////   可以将数组中的 0 视作 −1，则原问题转换成「求最长的连续子数组，其元素和为 0」。
        ////   进一步转化为如果数组中前i项的和是m,那么就需要知道是否存在j(j<i)使数组中前j项的和也是m。
        ////   建立哈希表map， map的key存放的是不同的前缀和，map的value存放的是对应前缀和第一次出现时对应的下标。
        ////   因此当后续出现的前缀和有相同的数值时，根据当前下标和哈希表中存储的下标计算得到的子数组长度是以当前下标结尾的子数组中满足有相同数量的 0 和 1 的最长子数组的长度。然后再跟新最大子数组长度

        //public int findMaxLength(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return 0;
        //    }
        //    int len=nums.length;
        //    int maxLen =0;
        //    // sums[i+1]表示截止到下标i【包含i】的前项和，不包含下标(i+1)。类似于哨兵节点，是为了方便处理第一项sums[0]=0;
        //    int[] sums=new int[len+1];
        //    // 数组下标0之前没有任何元素，那么就认为下标0之前的前缀和为0。
        //    for (int i = 0; i < len; i++) {
        //        sums[i+1]=sums[i]+(nums[i]==1?1:-1);
        //    }
        //    // 本题是求子数组的最大长度，边加入map边更新最大长度
        //    // 建立哈希表map， map的key存放的是不同的前缀和，map的value存放的是对应前缀和第一次出现时对应的下标。
        //    // 因此当后续出现的前缀和有相同的数值时，根据当前下标和哈希表中存储的下标计算得到的子数组长度是以当前下标结尾的子数组中满足有相同数量的 0 和 1 的最长子数组的长度。然后再跟新最大子数组长度
        //    Map<Integer,Integer> map=new HashMap<>();
        //    // 注意下面将前缀和添加到哈希表中时的操作
        //    for (int i = 0; i < len+1; i++) {
        //        // 下面是判断哈希表中有没有前缀和sums[i]。特别细节的模拟得到规律
        //        // 判断的是哈希表中有没有sums[i]，所以向哈希表中添加的也是sums[i]，更新长度也是减去sums[i]。
        //        // 获得长度的元素要和向哈希表中添加的键值对相对应。
        //        if(map.containsKey(sums[i])){// 哈希表中有前缀和等于sums[i]的元素，计算当前的长度并更新最大长度
        //            maxLen =Math.max(maxLen,i-map.get(sums[i]));
        //        }else{// 哈希表中不包含和当前前缀和一样的键值对，将当前前缀和和对应的元素数组的结束指针加入哈希表
        //            map.put(sums[i],i);// 这里是将（sums[i],和i）放入哈希表，
        //            // 因为前缀和平移了，对于双闭区间[left,right] ，sums[left]在哈希表中其实是对应的位置是（left-1)。
        //            // 这时候right-left+1=right-sums[left]
        //        }
        //    }
        //    return maxLen;
        //}





        //// 解法1：前缀和数组+哈希表  写法1   前缀和使用后移一位的更方便
        //public int findMaxLength(int[] nums) {
        //    if (nums == null || nums.length == 0) {//
        //        return 0;
        //    }
        //    int len = nums.length;
        //    int maxLen = 0;
        //
        //    // 前缀和数组：sums[i+1]表示截止到元素nums[i]【包含nums[i]】的前项和【前缀和数组下标为i+1，元素数组的数组下标是1。但是前缀和是原元素数组前（i+1）项的元素和】。
        //    // 即截止到下标i【包含i】的前项和【一共（i+1）项】。sums[i+1]可以理解为数组nums的前（i+1）项的和。
        //    // 这种处理方式前缀和数组下标比原元素数组的下标大1。实际的前缀和数组下标从1开始。
        //    int[] sums = new int[len + 1];
        //    for (int i = 0; i < len; i++) {
        //        sums[i + 1] = sums[i] + (nums[i] == 0 ? -1 : 1);
        //    }
        //
        //    // 本题是求子数组的最大长度，边加入map边更新最大长度
        //    // 建立哈希表，哈希表的键key是截至数组元素nums[i]的不同前缀和，
        //    // 值value是截至数组元素nums[i]的前缀和第一次出现时对应的原元素数组最后一个元素出现的位置。
        //    // 因此当后续出现的前缀和有相同的数值时，根据当前下标和哈希表中存储的下标计算得到的子数组长度是以当前下标结尾的子数组中满足有相同数量的 0 和 1 的最长子数组的长度。然后再跟新最大子数组长度
        //    Map<Integer, Integer> map = new HashMap<>();
        //    map.put(sums[0], -1);// 上述前缀和的处理方式：前缀和数组下标比原元素数组的下标大1。
        //    for (int i = 0; i < len; i++) {
        //        // 下面是判断哈希表中有没有前缀和sums[i+1]，所以向哈希表中添加的也是sums[i+1]，更新长度也是减去sums[i+1]。
        //        // 获得长度的元素要和向哈希表中添加的键值对相对应。
        //        if (map.containsKey(sums[i + 1])) {// 哈希表中包含和当前前缀和sums[i + 1]一样的键值对
        //            // 注意sums[i + 1]表示的是以nums[i]为结尾的子数组的前缀和，注意这里的下标模拟得到
        //            maxLen = Math.max(maxLen, i - map.get(sums[i + 1]));// 更新最大长度
        //        } else {// 哈希表中不包含和当前前缀和一样的键值对，将当前前缀和和对应的元素数组的结束指针加入哈希表
        //            map.put(sums[i + 1], i);
        //        }
        //    }
        //    return maxLen;
        //}




        //// 解法1：前缀和数组+哈希表  写法1的改进 将两个循环合并
        //public int findMaxLength(int[] nums) {
        //    if (nums == null || nums.length == 0) {//
        //        return 0;
        //    }
        //    int len = nums.length;
        //    int maxLen = 0;
        //    int[] sums = new int[len + 1];
        //    Map<Integer, Integer> map = new HashMap<>();
        //    map.put(sums[0], -1);
        //    for (int i = 0; i < len; i++) {
        //        sums[i + 1] = sums[i] + (nums[i] == 0 ? -1 : 1);
        //        if (map.containsKey(sums[i + 1])) {
        //            maxLen = Math.max(maxLen, i - map.get(sums[i + 1]));
        //        } else {
        //            map.put(sums[i + 1], i);
        //        }
        //    }
        //    return maxLen;
        //}





        ////  解法3：前缀和数组+哈希表   写法1 注意这里的前缀和处理和前面的不同  这里前缀和没有平移
        //public int findMaxLength(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return 0;
        //    }
        //    int maxLength = 0;
        //    int len = nums.length;
        //    int[] sums = new int[len];// sums[i]表示截止到元素nums[i]【包含i】的前i项和。即截止到下标i【包含i】的前项和
        //    sums[0] = (nums[0]== 1 ? 1 : -1);// 初始化sums[0]
        //    for (int i = 1; i < len; i++) {
        //        sums[i]=sums[i-1]+(nums[i] == 1 ? 1 : -1);
        //    }
        //    // 建立哈希表map， map的key存放的是不同的前缀和，map的value存放的是前缀和第一次出现时数组的下标。
        //    // 在哈希表中添加前缀和数组和对应下标的时候只要是一一线性对应的就可以。
        //    Map<Integer, Integer> map = new HashMap<>();
        //    // 初始化map，这里相当于动态规划的初始状态特殊处理。
        //    map.put(0, -1);// 数组下标0之前没有任何数字，所以认为数组下标0之前的前项和为0，对应的前项和为0第一次出现时的下标为-1。
        //    for (int i = 0; i < len; i++) {
        //        if (map.containsKey(sums[i])) {
        //            maxLength = Math.max(maxLength, i - map.get(sums[i]));
        //        } else {
        //            map.put(sums[i], i);
        //        }
        //    }
        //    return maxLength;
        //}




        /*
        // 解法4：写法1  前缀和+滚动数组+哈希表
        public int findMaxLength(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int maxLength = 0;
            int length = nums.length;
            int sum = 0;//表示前i项和
            // map的key存放的是不同的前缀和，map的value存放的是对应前缀和第一次出现时对应的下标，则当后续出现的前缀和有相同的数值时，就可以得到其长度，然后再跟新最大子数组长度。
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0,-1);//在下标为0的数组元素前面没有数，则可以认为数组下标为0之前的前缀和为0，对应下标为-1。这样方便计算前缀和等于0的数组长度
            for (int i = 0; i < length; i++) {
                //因为数组中只有0和1, 把0看作-1。sum += (nums[i]==0?-1:1);
                sum += nums[i] == 0 ? -1 : 1;
                if (map.containsKey(sum)) {//前缀和出现相同的数值，计算当前的长度并更新最大长度
                    maxLength = Math.max(maxLength, i - map.get(sum));//更新长度
                } else {//当出现新的前缀和时，将其添加进map中
                    map.put(sum, i);
                }
            }
            return maxLength;
        }
        */



        /*
        // 解法2：暴力循环 超时
        public int findMaxLength(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int len = nums.length;
            int maxLength = 0;
            for (int i = 0; i < len; i++) {
                int sum = 0;
                for (int j = i; j < len; j++) {
                    sum += nums[j];
                    // 因为只有0和1,所以数组长度等于和的二倍
                    if (2 * sum == j - i + 1) {
                        maxLength = Math.max(maxLength, j - i + 1);
                    }
                }
            }
            return maxLength;
        }
        */



    /*
        // 前缀和数组记忆化超时
        public int findMaxLength(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int maxLength = 0;
            int len = nums.length;
            // 注意这里sums[n]表示从第0项到第n项的前项和。前后细节对应
            int[] sums=new int[len];
            sums[0]=nums[0];
            for (int i = 1; i < len; i++) {
                sums[i]=sums[i-1]+nums[i];
            }
            for (int left = 0; left < len; left++) {
                for (int right = left; right < len; right++) {
                    if((sums[right]-sums[left]+nums[left])*2==right-left+1){// 双闭区间[left,right]
                        maxLength=Math.max(maxLength,right-left+1);
                    }
                }
            }
            return maxLength;
        }
    */



    }
//leetcode submit region end(Prohibit modification and deletion)

}
