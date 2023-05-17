/**
 * <p>给定一个长度为 <code>n</code> 的整数数组 <code>nums</code> ，其中 <code>nums</code> 是范围为 <code>[1，n]</code> 的整数的排列。还提供了一个 2D
 * 整数数组&nbsp;<code>sequences</code>&nbsp;，其中&nbsp;<code>sequences[i]</code>&nbsp;是&nbsp;<code>nums</code>&nbsp;
 * 的子序列。<br /> 检查 <code>nums</code> 是否是唯一的最短&nbsp;<strong>超序列</strong> 。最短 <strong>超序列</strong> 是
 * <strong>长度最短</strong> 的序列，并且所有序列&nbsp;<code>sequences[i]</code>&nbsp;都是它的子序列。对于给定的数组&nbsp;
 * <code>sequences</code>&nbsp;，可能存在多个有效的 <strong>超序列</strong> 。</p>
 *
 * <ul>
 * <li>例如，对于&nbsp;<code>sequences = [[1,2],[1,3]]</code>&nbsp;，有两个最短的 <strong>超序列</strong> ，<code>[1,2,3]</code> 和
 * <code>[1,3,2]</code> 。</li>
 * <li>而对于&nbsp;<code>sequences = [[1,2],[1,3],[1,2,3]]</code>&nbsp;，唯一可能的最短 <strong>超序列</strong> 是 <code>[1,2,
 * 3]</code> 。<code>[1,2,3,4]</code> 是可能的超序列，但不是最短的。</li>
 * </ul>
 *
 * <p><em>如果 <code>nums</code> 是序列的唯一最短 <strong>超序列</strong> ，则返回 <code>true</code> ，否则返回 <code>false</code>
 * 。</em><br /> <strong>子序列</strong> 是一个可以通过从另一个序列中删除一些元素或不删除任何元素，而不改变其余元素的顺序的序列。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3], sequences = [[1,2],[1,3]]
 * <strong>输出：</strong>false
 * <strong>解释：</strong>有两种可能的超序列：[1,2,3]和[1,3,2]。
 * 序列 [1,2] 是[<u><strong>1,2</strong></u>,3]和[<u><strong>1</strong></u>,3,<u><strong>2</strong></u>]的子序列。
 * 序列 [1,3] 是[<u><strong>1</strong></u>,2,<u><strong>3</strong></u>]和[<u><strong>1,3</strong></u>,2]的子序列。
 * 因为 nums 不是唯一最短的超序列，所以返回false。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3], sequences = [[1,2]]
 * <strong>输出：</strong>false
 * <strong>解释：</strong>最短可能的超序列为 [1,2]。
 * 序列 [1,2] 是它的子序列：[<u><strong>1,2</strong></u>]。
 * 因为 nums 不是最短的超序列，所以返回false。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
 * <strong>输出：</strong>true
 * <strong>解释：</strong>最短可能的超序列为[1,2,3]。
 * 序列 [1,2] 是它的一个子序列：[<strong>1,2</strong>,3]。
 * 序列 [1,3] 是它的一个子序列：[<u><strong>1</strong></u>,2,<u><strong>3</strong></u>]。
 * 序列 [2,3] 是它的一个子序列：[1,<u><strong>2,3</strong></u>]。
 * 因为 nums 是唯一最短的超序列，所以返回true。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == nums.length</code></li>
 * <li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
 * <li><code>nums</code>&nbsp;是&nbsp;<code>[1, n]</code>&nbsp;范围内所有整数的排列</li>
 * <li><code>1 &lt;= sequences.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= sequences[i].length &lt;= 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= sum(sequences[i].length) &lt;= 10<sup>5</sup></code></li>
 * <li><code>1 &lt;= sequences[i][j] &lt;= n</code></li>
 * <li><code>sequences</code>&nbsp;的所有数组都是 <strong>唯一 </strong>的</li>
 * <li><code>sequences[i]</code>&nbsp;是&nbsp;<code>nums</code> 的一个子序列</li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>图</li><li>拓扑排序</li><li>数组</li></div></div><br><div><li>👍 68</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 444
 * 序列重建
 *
 * @author wangweizhou
 * @date 2022-12-14 11:44:21
 */

public class SequenceReconstruction {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new SequenceReconstruction().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        //    if (nums == null || nums.length == 0) {
        //        return false;
        //    }
        //    int len = nums.length;
        //    Map<Integer, Set<Integer>> map = new HashMap<>();
        //    int[] inDegree = new int[len+1];
        //    for (List<Integer> seq : sequences) {
        //        for (Integer in : seq) {
        //            if(in<1||in>nums.length){
        //                return false;
        //            }
        //            map.put(in, new HashSet<>());
        //        }
        //    }
        //
        //
        //    for (List<Integer> seq : sequences) {
        //        for (int i = 0; i < seq.size()-1; i++) {
        //            int num1=seq.get(i);
        //            int num2=seq.get(i+1);
        //            if(!map.get(num1).contains(num2)){
        //                map.get(num1).add(num2);
        //                inDegree[num2]++;
        //            }
        //        }
        //    }
        //
        //
        //    Queue<Integer> queue=new LinkedList<>();
        //    for(int i=1;i<inDegree.length;i++){
        //        if(inDegree[i]==0){
        //            queue.offer(i);
        //        }
        //    }
        //    List<Integer> list=new ArrayList<>();
        //    while (queue.size()==1){
        //        int curr=queue.poll();
        //        list.add(curr);
        //        Set<Integer> set=map.get(curr);
        //        for(Integer in:set){
        //            inDegree[in]--;
        //            if(inDegree[in]==0){
        //                queue.offer(in);
        //
        //            }
        //        }
        //    }
        //    int[] ans=new int[list.size()];
        //    for (int i = 0; i < list.size(); i++) {
        //        ans[i]=list.get(i);
        //    }
        //    return Arrays.equals(nums,ans);
        //}


        // 超序列和子序列是两个相对的概念。如果序列A中的所有元素按照先后顺序都在序列B中出现，那么序列A是序列B的子序列，序列B是序列A的超序列。

        // 按照题目的要求，如果在seqs的某个序列中数字i出现在数字j的前面，那么由seqs重建的序列中数字i一定也要出现在数字j的前面。也就是说，重建序列的数字顺序由seqs的所有序列定义。

        // 由于 sequences 中的每个序列都是 nums 的子序列，因此每个序列中的数字顺序都和 nums 中的数字顺序一致。
        // 可以将seqs中每个序列的每个数字看成图中的一个节点，两个相邻的数字之间有一条从前面数字指向后面数字的边。
        // 可以将 sequences 中的所有序列看成有向图，数字 1 到 n 分别表示图中的 n 个结点，每个序列中的相邻数字表示的结点之间存在一条有向边。
        // 如果得到的是有向图的拓扑排序序列，那么任意一条边的起始节点在拓扑排序序列中一定位于终止节点的前面。
        // 因此，由seqs重建的序列就是由seqs构建的有向图的拓扑排序的序列。这个问题就转变成判断一个有向图的拓扑排序序列是否唯一。
        // 为了判断 nums 是不是序列的唯一最短超序列，只需要判断根据 sequences 中的每个序列构造超序列的结果是否唯一。


        // 代码首先根据序列列表seqs构建有向图，有向图以邻接表的形式用HashMap类型的graph保存。
        // 同时，统计每个节点的入度并保存到另一个HashMap类型的inDegrees中【根据有向边计算每个结点的入度】。
        // 接下来对构建的有向图按照广度优先搜索进行拓扑排序。
        // 队列queue中保存的是入度为0的节点。将所有入度为 0 的结点添加到队列中，进行拓扑排序。每次从队列中取出一个节点添加到拓扑排序序列中，
        // 然后将所有与该节点相邻的节点的入度减1（相当于删除所有以该节点为起始节点的边），如果发现有新的入度为0的节点则添加到队列之中。
        // 每一轮拓扑排序时，队列中的元素个数表示可以作为超序列下一个数字的元素个数，根据队列中的元素个数，执行如下操作。
        // 如果队列中的元素个数大于 1，则超序列的下一个数字不唯一，因此 nums 不是唯一的最短超序列，返回 false。
        // 如果队列中的元素个数等于 1，则超序列的下一个数字是队列中唯一的数字。将该数字从队列中取出，将该数字指向的每个数字的入度减 1，并将入度变成 0 的数字添加到队列中。
        // 重复上述过程，直到出现队列中的元素个数不等于 1 的情况。
        // 如果队列中的元素个数大于 1，则 nums 不是唯一的最短超序列，返回 false。
        // 如果队列为空，则完整的拓扑排序结束，nums 是唯一的最短超序列，返回 true。


        // 由于目标是判断图的拓扑排序序列是否唯一，而当某个时刻队列中的节点数目大于1时，就知道此时有多个入度为0的节点，
        // 那么按任意顺序排列这个入度为0的节点都能生成有效的拓扑排序序列，因此拓扑排序的序列不是唯一的。
        // 由此可知，上述代码只在队列的大小为1的时候重复添加入度为0的节点。


        // 解法1：拓扑排序   和269 火星词典 思路类似，只是最后多了一个验证是同一个序列的操作
        // 判断能否由sequences重建nums
        public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
            // 注意参数List<List<Integer>>sequences是集合嵌套
            if (nums == null || nums.length == 0 || sequences == null) {
                return false;
            }
            // 遍历sequences的每一个数字，建立每一个数字的子图和入度表，这时候建立的是空图
            // 有向图以邻接表的形式用HashMap类型的graph保存。哈希表的键是数字，值是该数字的后续数字组成的集合。后续数字是多个，所以这里使用哈希表HashSet来保存
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            // 统计每个节点的入度并保存到另一个HashMap类型的inDegrees中
            Map<Integer, Integer> inDegrees = new HashMap<>();

            for (List<Integer> seq : sequences) {
                // 遍历sequences建立有向图
                // 找出单词列表words中出现的所有字母并做相应的初始化。其实就是建立只有键没有值的空哈希表
                for (int num : seq) {
                    if (num < 1 || num > nums.length) {// 题目指定数字是1~n中的数字，本质就是判断越界
                        return false;
                    }
                    graph.put(num, new HashSet<>());
                    inDegrees.put(num, 0);
                }
            }

            // 遍历sequences建立有向图，这次才是建立实际的有向图
            for (List<Integer> seq : sequences) {
                // 找出每个子序列中的两个相邻的数字，建立从前一个数字指向后一个数字的边。
                for (int i = 0; i < seq.size() - 1; i++) {
                    int num1 = seq.get(i);
                    int num2 = seq.get(i + 1);
                    // 如果num1的邻接表中不含num2【即没有建立从num1到num2的有向边】，那么将num2加入num1的邻接表，num2的入度加1。
                    if (!graph.get(num1).contains(num2)) {
                        graph.get(num1).add(num2);
                        inDegrees.put(num2, inDegrees.get(num2) + 1);
                    }
                }
            }

            // 在构建有向图之后，采用广度优先搜索实现拓扑排序。队列中保存的是入度为0的节点。
            Queue<Integer> queue = new LinkedList<>();
            for (int num : inDegrees.keySet()) {// 遍历保存每个字符入度的集合，将入度为0的字符全部加入队列
                if (inDegrees.get(num) == 0) {
                    queue.add(num);
                }
            }

            List<Integer> newSeq = new LinkedList<>();
            // 由于目标是判断图的拓扑排序序列是否唯一，而当某个时刻队列中的节点数目大于1时，就知道此时有多个入度为0的节点，那么按任意顺序排列这个入度为0的节点都能生成有效的拓扑排序序列，因此拓扑排序的序列不是唯一的。
            // 由此可知，上述代码只在队列的大小为1的时候重复添加入度为0的节点。
            // 每次从队列中取出一个节点添加到拓扑排序序列中，然后将所有与该节点相邻的节点的入度减1（相当于删除所有以该节点为起始节点的边），如果发现有新的入度为0的节点则添加到队列之中。
            while (queue.size() == 1) {
                int num = queue.remove();
                newSeq.add(num);
                for (int next : graph.get(num)) {
                    inDegrees.put(next, inDegrees.get(next) - 1);
                    if (inDegrees.get(next) == 0) {
                        queue.add(next);
                    }
                }
            }
            // 将集合转换为数组，并验证数组是否相等
            int[] result = new int[newSeq.size()];
            //result = newSeq.stream().mapToInt(i -> i).toArray();// 将集合转换为整数型数组
            for (int i = 0; i < newSeq.size(); i++) {
                result[i]=newSeq.get(i);
            }
            return Arrays.equals(result, nums);
        }




        //// 解法2：拓扑排序
        //// 下面这个没有验证拓扑排序是否是重建的唯一序列
        //public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        //    if (nums == null || nums.length == 0 || sequences == null) {
        //        return false;
        //    }
        //    // 有向图以邻接表的形式用HashMap类型的graph保存。哈希表的键是数字，值是该数字的后续数字组成的集合。后续数字是多个，所以这里使用哈希表HashSet来保存
        //    Map<Integer, Set<Integer>> graph = new HashMap<>();
        //    // 因为是数字1~n。所以这里将数组右移一位表示数字的入度，方便处理，但是要注意下标为0的inDegrees[0]默认为0，不能添加到拓扑排序的队列中
        //    int[] inDegrees = new int[nums.length+1];
        //    // 遍历sequences建立有向图并初始化，其实就是建立只有键没有值的空哈希表
        //    for (List<Integer> seq : sequences) {
        //        for (int num : seq) {
        //            if (num < 1 || num > nums.length) {
        //                return false;
        //            }
        //            graph.put(num, new HashSet<>());
        //        }
        //    }
        //
        //    // 遍历sequences建立有向图，这次才是建立实际的有向图，
        //    for (List<Integer> seq : sequences) {
        //        // 找出每个子序列中的两个相邻的数字，建立从前一个数字指向后一个数字的边。
        //        for (int i = 0; i < seq.size() - 1; i++) {
        //            int num1 = seq.get(i);
        //            int num2 = seq.get(i + 1);
        //            // 如果num1的邻接表中不含num2【即没有建立从num1到num2的有向边】，那么将num2加入num1的邻接表，num2的入度加1。
        //            if (!graph.get(num1).contains(num2)) {
        //                graph.get(num1).add(num2);
        //                inDegrees[num2]++;
        //            }
        //        }
        //    }
        //
        //    // 在构建有向图之后，采用广度优先搜索实现拓扑排序。队列中保存的是入度为0的节点。
        //    Queue<Integer> queue = new LinkedList<>();
        //    for (int i = 1; i < inDegrees.length; i++) {// 遍历入度数组，将入度为0的节点加入队列中,注意这里是数字1~n。
        //        if (inDegrees[i] == 0) {
        //            queue.add(i);
        //        }
        //    }
        //
        //    while (!queue.isEmpty()) {
        //        if (queue.size() > 1) { // 如果队列中的元素个数大于 1，则超序列的下一个数字不唯一，因此 nums 不是唯一的最短超序列，返回 false。
        //            return false;
        //        }
        //        int num = queue.poll();
        //        for (int next : graph.get(num)) {// 根据图遍历当前节点num的后续节点
        //            inDegrees[next]--;
        //            if (inDegrees[next] == 0) {
        //                queue.offer(next);
        //            }
        //        }
        //    }
        //    return true;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
