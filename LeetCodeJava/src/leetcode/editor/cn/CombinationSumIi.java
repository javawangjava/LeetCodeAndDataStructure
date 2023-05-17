/**
 * <p>给定一个候选人编号的集合&nbsp;<code>candidates</code>&nbsp;和一个目标数&nbsp;<code>target</code>&nbsp;，找出&nbsp;
 * <code>candidates</code>&nbsp;中所有可以使数字和为&nbsp;<code>target</code>&nbsp;的组合。</p>
 *
 * <p><code>candidates</code>&nbsp;中的每个数字在每个组合中只能使用&nbsp;<strong>一次</strong>&nbsp;。</p>
 *
 * <p><strong>注意：</strong>解集不能包含重复的组合。&nbsp;</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> candidates =&nbsp;<code>[10,1,2,7,6,1,5]</code>, target =&nbsp;<code>8</code>,
 * <strong>输出:</strong>
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]</pre>
 *
 * <p><strong>示例&nbsp;2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> candidates =&nbsp;[2,5,2,1,2], target =&nbsp;5,
 * <strong>输出:</strong>
 * [
 * [1,2,2],
 * [5]
 * ]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;=&nbsp;candidates.length &lt;= 100</code></li>
 * <li><code>1 &lt;=&nbsp;candidates[i] &lt;= 50</code></li>
 * <li><code>1 &lt;= target &lt;= 30</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 1184</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 40
 * 组合总和 II
 *
 * @author wangweizhou
 * @date 2022-12-10 18:28:28
 */

public class CombinationSumIi {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new CombinationSumIi().new Solution();
        int[] nums={10,1,2,7,6,1,5};
        List<List<Integer>> lists=solution.combinationSum2(nums,8);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：回溯法
        // 避免重复的组合的方法是当在某一步决定跳过某个值为m的数字时，跳过所有值为m的数字。
        // 为了方便跳过后面所有值相同的数字，可以将集合中的所有数字排序，把相同的数字放在一起，这样方便比较数字。
        // 当决定跳过某个值的数字时，可以按顺序扫描后面的数字，直到找到不同的值为止。

        //public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //    List<List<Integer>> lists = new LinkedList<>();
        //    if (candidates == null || candidates.length == 0 ) {
        //        return lists;
        //    }
        //    LinkedList<Integer> combination = new LinkedList<>();
        //    Arrays.sort(candidates);
        //    combinationSumFunc2(candidates, target, 0, combination, lists);
        //    return lists;
        //}
        //
        //
        //private void combinationSumFunc2(int[] nums, int target, int index, LinkedList<Integer> combination,
        //                                 List<List<Integer>> lists) {
        //    if (target == 0) {
        //        lists.add(new LinkedList<>(combination));
        //    } else if (target > 0 && index < nums.length) {// 剪枝，剩余和要大于0，数组没有越界
        //        // 一个选择是跳过这个数字不将该数字添加到组合中，某一步决定跳过某个值为m的数字时，跳过所有值为m的数字
        //        // 当决定跳过数字nums[i]时可以调用函数getNext找到与该数字不同的下一个数字。
        //        combinationSumFunc2(nums, target, getNext(nums, index), combination, lists);
        //        // 另一个选择是将数字nums[index]添加到组合combination中，因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i。
        //        combination.add(nums[index]);
        //        combinationSumFunc2(nums, target - nums[index], index + 1, combination, lists);
        //        combination.removeLast();// 回溯
        //    }
        //}
        //
        //
        //// 找出数组nums中nums[index]后面且不同于nums[index]的第一个数
        //private int getNext(int[] nums, int index) {
        //    int next = index;//next表示index后第一个不同的数字
        //    while (next < nums.length && nums[next] == nums[index]) {
        //        next++;
        //    }
        //    return next;
        //}




        //// 写法2：
        // 一、避免重复答案
        // 为了避免重复的答案，首先我们要做的就是给数组排序，如果说我在同一级递归中，遇到两个相同的数，我们应该只dfs靠前的那一个一次。
        // 原因的话，我们可以这样理解，如果现在遇到下标位idx，idx +1的两个数是相同的，那么对于集合dfs(idx, target) 和 dfs(idx + 1, target)，后者就是前者的一个子集，
        // 所以我们在同一级递归中，对于相同的数，只应该dfs一次，并且是下标最小的那一个。
        // 二、剪枝
        // 剪枝就是基于很直接的思想，例如：前面已经给数组排序了，如果递归的过程中当前值比target大，那么说明后面的值不可能再找出一组元素和为target，所以此时就可以立即结束递归返回。

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            if (candidates == null || candidates.length == 0) {
                return res;
            }
            int len = candidates.length;
            // 关键步骤
            Arrays.sort(candidates); //数组一定要时有序的，才能进行深度递归
            Deque<Integer> path = new ArrayDeque<>(len);
            dfs(candidates, len, 0, target, path, res);
            return res;
        }



        private void dfs(int[] candidates, int len, int begin, int target, Deque<Integer> path, List<List<Integer>>
                res) {
            if (target == 0) {// 找到了一组组合
                res.add(new ArrayList<>(path));// 加入解集
                return;
            }

            // 不产生重复组合怎么限制（剪枝）？
            // 如图，只要限制下一次选择的起点，是基于本次的选择，这样下一次就不会选到本次选择同层左边的数。即通过控制 for 遍历的起点，去掉会产生重复组合的选项。
            // 当没有进入下一层递归时，在一个for循环中，所有被遍历到的数都是属于一个层级的。
            for (int i = begin; i < len; i++) {// 枚举当前可选的数，从index开始
                // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
                if (target < candidates[i] ) {
                    break;
                }

                // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
                // 这个选择就是同一层的选择不将该数字添加到集合中，某一步决定跳过某个值为m的数字时，跳过所有值为m的数字
                // i > begin保证先选一个，candidates[i] == candidates[i - 1]跳过相同的数
                if (i > begin && candidates[i] == candidates[i - 1]) {// 这种写法要先选一个再跳过后面相同的。
                    continue;
                }

                // 另一个选择是将数字nums[index]添加到组合combination中，因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i。
                path.addLast(candidates[i]);//
                // 调试语句 ①
                // System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

                // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
                dfs(candidates, len, i + 1, target - candidates[i], path, res);

                path.removeLast();// 移除该元素，同一层循环遍历下一个位置，这时候就是不选当前位置的元素
                // 调试语句 ②
                // System.out.println("递归之后 => " + path + "，剩余 = " + (target - candidates[i]));
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
