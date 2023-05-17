/**
 * <p>给定一个未排序的整数数组 <code>nums</code> ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。</p>
 *
 * <p>请你设计并实现时间复杂度为 <code>O(n)</code><em> </em>的算法解决此问题。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [100,4,200,1,3,2]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>最长数字连续序列是 <code>[1, 2, 3, 4]。它的长度为 4。</code></pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,3,7,2,5,8,4,6,0,1]
 * <strong>输出：</strong>9
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= nums.length <= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>并查集</li><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 1389</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 128
 * 最长连续序列
 *
 * @author wangweizhou
 * @date 2022-08-29 14:37:39
 */

public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new LongestConsecutiveSequence().new Solution();
        int[] nums = {100, 4, 200, 1, 3, 2};
        int ans = solution.longestConsecutive(nums);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {



        // 解法2：图搜索   // 图的广度搜索+以图的每一个节点作为起点遍历，获得最长序列
        // 这个题目是关于整数的连续性的。
        // 如果将每个整数看成图中的一个节点，相邻的（数值大小相差1）两个整数有一条边相连，那么这些整数将形成若干子图，每个连续数值序列对应一个子图。
        // 如果将图中的子图看成一个岛屿，也就是相邻的整数组成一个岛屿，那么连续整数的数目就是岛屿的面积，这个题目就变成求最大岛屿的面积。
        // 代码中，每当搜索到整数i时，就判断输入的整数中是否包含i-1和i+1，如果包含就将其添加到队列中以便接下来搜索。
        // 为了方便判断输入的整数中是否包含某个数字，上述代码将所有数字都添加到一个HashSet中。
        // 用HashSet只需要O（1）的时间就能判断一个数字是否存在。

        public int longestConsecutive(int[] nums) {
            if (nums == null || nums.length == 0) {// 判空
                return 0;
            }
            // 为了方便判断输入的整数中是否包含某个数字，将所有数字都添加到一个HashSet中。
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {// 遍历数组，将数组元素添加到哈希表中
                set.add(num);
            }
            int longest = 0;

            //这里使用遍历器来遍历
            //while (!set.isEmpty()) {// 以哈希表中的每一个元素为起点进行广度遍历
            //    Iterator<Integer> iter = set.iterator();// 遍历哈希表这里使用的是迭代器遍历集合
            //    int next=iter.next();
            //    longest = Math.max(longest, bfs(set, next));// 获取集合中以每一个元素开始的最长连续数值的长度，并找出图中最长连续数值的长度
            //}

            for (int i = 0; i < nums.length; i++) {
                if (set.contains(nums[i])) {
                    int count = bfs(set, nums[i]);
                    longest = Math.max(longest, count);
                }
            }
            return longest;
        }



        // 在图中获取以num为起点的最长连续数值的长度。广度优先搜索借助队列来完成
        private int bfs(Set<Integer> set, int num) {
            Queue<Integer> queue = new LinkedList<>();
            // 只要将元素加入到队列中时，就需要将该元素从已访问集合中移出，
            queue.offer(num);// 将当前元素添加到队列中
            set.remove(num);// 将当前元素从哈希表中移出，其实就是类似于已访问，不能再次访问已经遍历过的元素。这个和将该元素标记为已访问效果类似
            int count = 1;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                count++;
                // 每当搜索到整数i时，就判断输入的整数中是否包含i-1和i+1，如果包含就将其添加到队列中以便接下来搜索。
                // 这个就是图的每一个节点的邻接表
                int[] neighbors = new int[]{curr - 1, curr + 1};// 题目要求时相邻连续的最长序列。所以这里要向相邻小的，或者相邻大的方向遍历。
                for (int neighbor : neighbors) {// 遍历与当前元素相邻的元素
                    if (set.contains(neighbor)) {// 这一个就相当于判越界
                        queue.offer(neighbor);
                        set.remove(neighbor);
                        count++;
                    }
                }
            }
            return count;
        }






        ////  解法3：并查集
        //// 在初始化并查集的时候输入数组中的每个整数放入一个子集中，父节点的指针指向它自己。
        //// 然后对于每个整数n，如果存在整数n-1和n+1，则将它们所在的子集合并。
        //// 每个子集的根节点记录它所在子集的元素的数目，在合并子集的时候需要更新合并之后新子集的根节点中子集元素的数目。
        //
        //// 上述代码用哈希表fathers记录每个整数所在子集的父节点，哈希表counts用来记录以某个整数为根节点的子集中整数的数目。
        //// 初始化并查集的时候每个整数的父节点都指向自己，也就是每个子集中只包含一个数字，所以哈希表counts的每个整数对应的值都被初始化为1。
        //// 接下来对于每个整数num，如果存在num-1和num+1，当它们在不同的子图中时将它们所在的子图用函数union合并，并更新合并后子集中元素的数目。
        //// 判断两个整数是否在同一个子集中的方法是用函数findFather得到它们所在子集的根节点并判断根节点是否相同。
        //// 在将所有可能合并的子集合并之后，扫描哈希表就能得到最大子集的大小，即最长连续数值序列的长度。
        //
        //public int longestConsecutive(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return 0;
        //    }
        //    // 哈希表fathers的键是节点，哈希表的值是该节点的对应的根节点
        //    Map<Integer, Integer> fathers = new HashMap<>();// 用哈希表fathers记录每个整数所在子集的父节点
        //    // 哈希表counts的键是子集的根节点，哈希表的值是以该节点为根节点的子集中整数的数目
        //    Map<Integer, Integer> counts = new HashMap<>();// 哈希表counts用来记录以某个整数为根节点的子集中整数的数目。
        //    Set<Integer> all = new HashSet<>();// 数组中所有的数字
        //
        //    for (int num : nums) {// 遍历数组，初始化
        //        fathers.put(num, num); // 在初始化并查集的时候输入数组中的每个整数放入一个子集中，父节点的指针指向它自己。
        //        counts.put(num, 1);// 初始化时数组中的每个整数放入一个子集中，该子集的节点个数是1
        //        all.add(num);// 将数组中所有元素加入到哈希表中，方便以O(1)时间查找是否含有某元素
        //    }
        //
        //    for (int num : nums) {// 遍历数组来合并并查集
        //        // 对于每个整数num，如果存在num-1和num+1，当它们在不同的子图中时将它们所在的子图用函数union合并，并更新合并后子集中元素的数目。
        //        if (all.contains(num + 1)) {//
        //            union(fathers, counts, num, num + 1);
        //        }
        //        if (all.contains(num - 1)) {
        //            union(fathers, counts, num, num - 1);
        //        }
        //    }
        //
        //    int longest = 0;
        //    for (int length : counts.values()) {// 遍历哈希表counts找出节点数目最多的并查集，即找出最长的连续序列
        //       longest = Math.max(longest, length);
        //    }
        //    return longest;
        //}
        //
        //
        //// 我们真正关心的是节点i的根节点是谁而不是它的父节点，因此可以在fathers[i]中存储它的根节点。
        //// 当第1次找节点i的根节点时，还需要沿着指向父节点的边遍历直到找到根节点。一旦找到了它的根节点，就把根节点存放到fathers[i]中。
        //// 函数findFather用来查找一个节点的根节点。一旦得知节点i的根节点，就记录到fathers[i]中，相当于压缩了路径。
        //
        //private int findFather(Map<Integer, Integer> fathers, int i) {
        //    // 哈希表fathers的键是节点，哈希表的值是该节点的对应的根节点
        //    if (fathers.get(i) != i) {
        //        fathers.put(i, findFather(fathers, fathers.get(i)));
        //    }
        //    return fathers.get(i);
        //}
        //
        //
        //// 对于每个整数num，如果存在num-1和num+1，当它们在不同的子图中时将它们所在的子图用函数union合并，并更新合并后子集中元素的数目。
        //// 判断两个整数是否在同一个子集中的方法是用函数findFather得到它们所在子集的根节点并判断根节点是否相同。
        //// 在将所有可能合并的子集合并之后，扫描哈希表就能得到最大子集的大小，即最长连续数值序列的长度。
        //private void union(Map<Integer, Integer> fathers, Map<Integer, Integer> counts, int i, int j) {
        //    int fatherOfI = findFather(fathers, i);
        //    int fatherOfJ = findFather(fathers, j);
        //    if (fatherOfI != fatherOfJ) {// 当两个节点的根节点不同时，即两个节点在不同的子图中时，合并子图
        //
        //        // 将两个集合合并成一个集合，只需要将一个子集对应的树的根节点的指针指向另一个子集对应的树的根节点。
        //        fathers.put(fatherOfI, fatherOfJ);// 将节点i的根节点fatherOfI的指向父节点的指针指向节点j的根节点fatherOfJ
        //        int countOfI = counts.get(fatherOfI);// 获取以fatherOfI为根节点的子图的节点个数
        //        int countOfJ = counts.get(fatherOfJ);
        //        // 因为前面是将根节点fatherOfI的指针指向另一个子集的根节点fatherOfJ，所以这里要更新根节点fatherOfJ对应的树的节点个数
        //        counts.put(fatherOfJ, countOfI + countOfJ);
        //    }
        //}





        // // 解法1：哈希表  必须掌握
        // // 枚举数组中的每个数 x，考虑以其为起点，不断尝试匹配 x+1, x+2, ⋯ 是否存在，假设最长匹配到了 x+y，每次在哈希表中检查是否存在 x−1
        //public int longestConsecutive(int[] nums) {
        //	if(nums == null || nums.length == 0){
        //		return 0;
        //	}
        //	// 建立一个存储所有数的哈希表，同时起到去重功能
        //	Set<Integer> set=new HashSet<>();
        //	for (int i = 0; i < nums.length; i++) {// 遍历将元素放入set集合中
        //		set.add(nums[i]);
        //	}
        //	int maxLen=0;
        //	for (int num:set){	// foreach循环遍历HashSet,
        //		// 下面的这个if语句的意思是对于每一个数，如果有前驱，那么就先跳过，对于一组连续的数，每次从最小的开始遍历。
        //		if(!set.contains(num-1)){// 在遍历前先看当前数有没有前驱，只有当num-1不存在时，才开始向后遍历num+1，num+2，num+3......
        //			int curr =num;
        //			while(set.contains(curr +1)){
        //				curr++;
        //			}
        //			maxLen=Math.max(maxLen,curr-num+1);// [num, cur]之间是连续的，数字有cur - num + 1个
        //		}
        //	}
        //	return maxLen;
        //	//	上述代码虽然有两层循环for+while，但是由于if (!set.contains(cur - 1))判断的存在，每个元素只会被遍历一次，因此时间复杂度也为O(n)
        //}





        ////	 解法2：哈希表记录右边界，这个个HashMap其实一样，这时将左右边界放在一个数对中了
        ////	 哈希表的key是数字num，value是数字num能够连续到达的右边界
        //public int longestConsecutive(int[] nums) {
        //	if (nums == null || nums.length == 0) {
        //		return 0;
        //	}
        //	// key表示num，value表示num最远到达的连续右边界
        //	Map<Integer, Integer> map = new HashMap<>();
        //	// 初始化每个num的右边界为自己
        //	for (int num : nums) {
        //		map.put(num, num);
        //	}
        //	int maxLen = 0;
        //	for (int num : nums) {
        //		// 下面的这个if语句的意思是对于每一个数，如果有前驱，那么就先跳过，对于一组连续的数，每次从最小的开始遍历。
        //		if (!map.containsKey(num - 1)) {//在遍历前先看当前数有没有前驱，只有当num-1不存在时，才开始向后遍历num+1，num+2，num+3......
        //			int right = map.get(num);
        //			while (map.containsKey(right + 1)) {// 遍历得到最远的右边界
        //				right = map.get(right + 1);
        //			}
        //			map.put(num, right);// 更新右边界
        //			maxLen = Math.max(maxLen, right - num + 1);// 更新答案
        //		}
        //	}
        //	return maxLen;
        //}





        ////	解法3：哈希表记录连续区间长度（动态规划）  掌握
        ////  key表示num，value表示num所在连续区间的长度
        ////  当num是第一次出现时：
        ////  （1）分别获取到左相邻数字num-1的连续区间长度left和右相邻数字num+1的连续区间长度right；
        ////  （2）计算得到当前的区间长度为curLen=left+right+1curLen=left+right+1；
        ////  （3）更新最长区间长度ans以及左右边界的区间长度。

        //public int longestConsecutive(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return 0;
        //    }
        //    // key表示num，value表示num所在连续区间的长度
        //    Map<Integer, Integer> map = new HashMap<>();
        //    int ans = 0;
        //    for (int num : nums) {
        //        if (!map.containsKey(num)) {// 当map中不包含num，也就是num第一次出现
        //            int left = map.getOrDefault(num - 1, 0);// left为num-1所在连续区间的长度，更进一步理解为：左连续区间的长度
        //            int right = map.getOrDefault(num + 1, 0);// right为num+1所在连续区间的长度，更进一步理解为：右连续区间的长度
        //
        //            int curLen = left + right + 1; // 当前连续区间的总长度,左右联通了，左右的连续区间长度和当前元素
        //            ans = Math.max(ans, curLen);
        //            map.put(num, 1);// 将num加入map中，表示已经遍历过该值。其对应的value可以为任意值。
        //            // 更新当前连续区间左边界和右边界对应的区间长度
        //            map.put(num - left, curLen);
        //            map.put(num + right, curLen);
        //        }
        //    }
        //    return ans;
        //}



    }

//leetcode submit region end(Prohibit modification and deletion)

}


