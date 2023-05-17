/**
 * <p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，请你返回其中出现频率前 <code>k</code> 高的元素。你可以按 <strong>任意顺序</strong>
 * 返回答案。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>nums = [1,1,1,2,2,3], k = 2
 * <strong>输出: </strong>[1,2]
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>nums = [1], k = 1
 * <strong>输出: </strong>[1]</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
 * <li><code>k</code> 的取值范围是 <code>[1, 数组中不相同的元素的个数]</code></li>
 * <li>题目数据保证答案唯一，换句话说，数组中前 <code>k</code> 个高频元素的集合是唯一的</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>你所设计算法的时间复杂度 <strong>必须</strong> 优于 <code>O(n log n)</code> ，其中 <code>n</code><em>
 *
 * </em>是数组大小。</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>分治</li><li>桶排序</li><li>计数</li><li>快速选择</li><li
 * >排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 1251</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 347
 * 前 K 个高频元素
 * @author wangweizhou
 * @date 2022-07-06 18:15:51
 */


public class TopKFrequentElements {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new TopKFrequentElements().new Solution();

        int[] nums = {1, 2, 2, 1, 3, 1};
        int[] ans = solution.topKFrequent(nums, 2);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 这个题目的输入是一个数组，哈希表可以用来统计数组中数字出现的频率，哈希表的键是数组中出现的数字，而值是数字出现的频率。
        // 接下来找出出现频率最高的k个数字。可以用一个最小堆存储频率最高的k个数字，堆中的每个元素是数组中的数字及其在数组中出现的次数。
        // 由于比较的是数字的频率，因此设置最小堆比较元素的规则，以便让频率最低的数字位于堆的顶部。
        // 在用哈希表统计完数组中每个数字的频率之后，再逐一扫描哈希表中每个从数字到频率的映射，以便找出频率最高的k个数字。
        // 如果最小堆中元素的数目小于k，则直接将从数字到频率的映射添加到最小堆中。
        // 如果最小堆中已经有k个元素，那么比较待添加数字的频率和位于堆顶的数字的频率。
        // 如果待添加的数字的频率低于或等于堆顶的数字的频率，那么堆中的k个数字的频率都比待添加的数字的频率高，它不可能是k个频率最高的数字中的一个，可以忽略。
        // 如果待添加的数字的频率高于堆顶的数字的频率，那么删除堆顶的数字（最小堆中频率最低的数字），并将待添加的数字添加到最小堆中。
        // 按照上述规则在最小堆中添加数字，就可以确保最小堆中元素的数目不超过k，里面保存的是出现频率最高的k个数字，并且这k个数字中频率最低的数字位于堆顶。
        // 用最小堆来存储频率最高的k个数字。注意堆中保存的是数组中出现的数字及其在数组中出现的次数。也就是堆中元素保存的是哈希表中的键值对。


        ////	解法1：哈希表+最小堆
        //public int[] topKFrequent(int[] nums, int k) {
        //    if (nums == null) {
        //        return null;
        //    }
        //    // 哈希表可以用来统计数组中数字出现的频率，哈希表的键是数组中出现的数字，而值是数字出现的频率。
        //    Map<Integer, Integer> numToCount = new HashMap<>();
        //    for (int num : nums) {// 遍历数组将数组元素保存到哈希表中
        //        numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
        //    }
        //
        //    // 最小堆中保存哈希表的键值对Map.Entry。堆中元素根据频数排序：要定义排序方式，Lambda表达式实现。
        //    // java默认是最小堆，因为键值对是两个元素，且是按照键值对元素的第二个元素来排序的，所以要定义一下排序规则
        //    PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
        //            (e1, e2) -> e1.getValue() - e2.getValue()// Lambda表达式，升序排列
        //    );
        //
        //    // 遍历哈希表中的键值对元素，将满足条件的键值对添加到堆中
        //    for (Map.Entry<Integer, Integer> entry : numToCount.entrySet()) {
        //        if (minHeap.size() < k) {// 当堆中元素数目小于k个，将当前键值对直接加入堆中
        //            minHeap.add(entry);
        //        } else {// 当堆中元素已经有K个
        //            if (entry.getValue() > minHeap.peek().getValue()) {// 如果当前键值对的值大于堆顶元素键值对的值，也就是新遍历元素的出现次数大于堆顶元素的出现次数
        //                minHeap.poll();// 移出堆顶元素
        //                minHeap.add(entry);// 将新的键值对元素添加到堆中
        //            }
        //        }
        //    }
        //
        //    // 将堆中元素的键【即出现频率最高的元素】保存到结果数组中
        //    int[] ans = new int[k];
        //    int index = 0;
        //    for (Map.Entry<Integer, Integer> entry : minHeap) {// 增强for遍历哈希表的键值对
        //        ans[index] = entry.getKey();
        //        index++;
        //    }
        //    return ans;
        //}




        ////	解法1：写法2 哈希表+最小堆
        //public int[] topKFrequent(int[] nums, int k) {
        //    if (nums == null) {
        //        return null;
        //    }
        //    // 哈希表可以用来统计数组中数字出现的频率，哈希表的键是数组中出现的数字，而值是数字出现的频率。
        //    Map<Integer, Integer> numToCount = new HashMap<>();
        //    for (int num : nums) {// 遍历数组将数组元素保存到哈希表中
        //        numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
        //    }
        //
        //    // 最小堆中每一个元素保存两个数据，在这里使用数组传递元素。
        //    // 这里使用数组来模拟哈希表的键值对。int[] 的第一个元素代表数组中出现的数字，第二个元素代表了该数字出现的频率。
        //    // 哈希表的键是数组中出现的数字，而值是该数字出现的频率。
        //    PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>(new Comparator<int[]>() {
        //        public int compare(int[] m, int[] n) {
        //            return m[1] - n[1];
        //        }
        //    });
        //
        //
        //    // 遍历哈希表中的键值对元素，将满足条件的键值对添加到堆中
        //    for (Map.Entry<Integer, Integer> entry : numToCount.entrySet()) {
        //        if (minHeap.size() < k) {// 当堆中元素数目小于k个，将当前键值对直接加入堆中
        //            minHeap.add(new int[]{entry.getKey(), entry.getValue()});
        //        } else {// 当堆中元素已经有K个
        //            // minHeap.peek() 是最小堆的堆顶元素，最小堆的元素的第2个变量是该数字出现的次数 minHeap.peek()[1]。
        //            if (entry.getValue() > minHeap.peek()[1]) {// 如果当前键值对的值大于堆顶元素键值对的值，也就是新遍历元素的出现次数大于堆顶元素的出现次数
        //                minHeap.poll();// 移出堆顶元素
        //                minHeap.add(new int[]{entry.getKey(), entry.getValue()});// 将新的键值对元素添加到堆中
        //            }
        //        }
        //    }
        //
        //    // 将堆中元素的键【即出现频率最高的元素】保存到结果数组中
        //    int[] ans = new int[k];
        //    for (int i = 0; i < k; ++i) {
        //        ans[i] = minHeap.poll()[0];
        //    }
        //    return ans;
        //}


        public int[] topKFrequent(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return new int[0];
            }
            Map<Integer, Integer> counts = new HashMap<>();
            for (int num : nums) {
                counts.put(num, counts.getOrDefault(num, 0) + 1);
            }
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });

            for(Map.Entry<Integer,Integer> entry:counts.entrySet()){
                if(minHeap.size()<k){
                    minHeap.offer(new int[]{entry.getKey(),entry.getValue()});
                }else {
                    if(entry.getValue()>minHeap.peek()[1]){
                        minHeap.poll();
                        minHeap.offer(new int[]{entry.getKey(),entry.getValue()});
                    }
                }
            }
            int[] ans=new int[k];
            for (int i = 0; i < k; i++) {
                ans[i]=minHeap.poll()[0];
            }
            return ans;
        }



    }
//leetcode submit region end(Prohibit modification and deletion)

}
