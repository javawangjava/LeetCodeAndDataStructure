/**
 * /**
 * <p>给你一个 <strong>无重复元素</strong> 的整数数组&nbsp;<code>candidates</code> 和一个目标整数&nbsp;<code>target</code>&nbsp;，找出&nbsp;
 * <code>candidates</code>&nbsp;中可以使数字和为目标数&nbsp;<code>target</code> 的 所有<em>&nbsp;</em><strong>不同组合</strong>
 * ，并以列表形式返回。你可以按 <strong>任意顺序</strong> 返回这些组合。</p>
 *
 * <p><code>candidates</code> 中的 <strong>同一个</strong> 数字可以 <strong>无限制重复被选取</strong>
 * 。如果至少一个数字的被选数量不同，则两种组合是不同的。&nbsp;</p>
 *
 * <p>对于给定的输入，保证和为&nbsp;<code>target</code> 的不同组合数少于 <code>150</code> 个。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>candidates = <code>[2,3,6,7], </code>target = <code>7</code>
 * <strong>输出：</strong>[[2,2,3],[7]]
 * <strong>解释：</strong>
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。</pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>candidates = [2,3,5]<code>, </code>target = 8
 * <strong>输出: </strong>[[2,2,2,2],[2,3,3],[3,5]]</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>candidates = <code>[2], </code>target = 1
 * <strong>输出: </strong>[]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= candidates.length &lt;= 30</code></li>
 * <li><code>2 &lt;= candidates[i] &lt;= 40</code></li>
 * <li><code>candidates</code> 的所有元素 <strong>互不相同</strong></li>
 * <li><code>1 &lt;= target &lt;= 40</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 2278</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 39
 * 组合总和
 *
 * @author wangweizhou
 * @date 2022-12-10 18:27:20
 */
public class CombinationSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new CombinationSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 解法1：回溯法
        //// 能够用回溯法解决的问题都能够分成若干步来解决，每一步都面临若干选择。
        //// 对于从集合中选取数字组成组合的问题而言，集合中有多少个数字，解决这个问题就需要多少步。
        //// 每一步都从集合中取出一个下标为i的数字，此时面临两个选择。
        //// 一个选择是跳过这个数字不将该数字添加到组合中，那么这一步实际上什么都不做，接下来处理下标为i+1的数字。
        //// 另一个选择是将数字添加到组合中，由于一个数字可以重复在组合中出现，也就是说，下一步可能再次选择同一个数字，因此下一步仍然处理下标为i的数字。
        //
        //// 解决这个问题的代码和之前的代码大同小异，最主要的不同在于当选择将数组nums下标为i的数字添加到组合combination中之后，
        //// 由于nums[i]这个数字可能在组合中重复出现，因此递归调用函数helper时第3个参数传入的值仍然是i，这个参数没有变化，下一步仍然处理数组nums下标为i的数字。
        //// 上述代码中的target是组合combination中元素之和的目标值。
        //// 每当在组合中添加一个数字时，就从target中减去这个数字。当target等于0时，组合中的所有元素之和正好等于target，因此也就找到了一个符合条件的组合。
        //// 应用回溯法解决问题时如果有可能应尽可能剪枝以优化时间效率。
        //// 由于题目明确指出数组中的所有数字都是正整数，因此当组合中已有数字之和已经大于目标值时（递归函数helper的参数target的值小于0时）就没有必要再考虑数组中还没有处理的数字，
        //// 因为再在组合中添加任意正整数元素之后和会更大，一定找不到新的符合条件的组合，也就没必要再继续尝试。这是函数helper中else if的条件中补充了一个target大于0的判断条件的原因。
        //
        //public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //    List<List<Integer>> lists = new LinkedList<>();
        //    if (candidates == null || candidates.length == 0 || target < 1) {
        //        return lists;
        //    }
        //    LinkedList<Integer> combination = new LinkedList<>();
        //    combinationSumFunc(candidates, target, 0, combination, lists);
        //    return lists;
        //}
        //
        //
        //
        //// 每一步都从集合中取出一个下标为i的数字，此时面临两个选择。一个选择是跳过这个数字不将该数字添加到组合中，那么这一步实际上什么都不做，接下来处理下标为i+1的数字。
        //// 另一个选择是将数组nums下标为i的数字添加到组合combination中之后，由于nums[i]这个数字可能在组合中重复出现，也就是说，下一步可能再次选择同一个数字，因此下一步仍然处理下标为i的数字。
        //// 注意下面时采用做差的思路写的，不是利用求和相等的思路写的。
        //// 代码中的target是组合combination中元素之和的目标值。每当在组合中添加一个数字时，就从target中减去这个数字。
        //// 当target等于0时，组合中的所有元素之和正好等于target，因此也就找到了一个符合条件的组合。
        //
        //private void combinationSumFunc(int[] nums, int target, int index, LinkedList<Integer> combination,
        //                                List<List<Integer>> lists) {
        //    if (target == 0) {
        //        lists.add(new LinkedList<>(combination));
        //    } else if (target > 0 && index < nums.length) {
        //        // 一个选择是跳过这个数字nums[index]不将该数字添加到组合combination中，
        //        // 这时下一步就不能选择当前元素，所以下一步处理的坐标为（i+1），由于没有添加当前元素，目标和仍为target。
        //        combinationSumFunc(nums, target, index + 1, combination, lists);
        //        // 另一个选择是将数字nums[index]添加到组合combination中，由于一个数字可以重复在组合combination中出现，
        //        // 也就是说，下一步可能再次选择同一个数字，因此下一步仍然处理下标为i的数字，由于已经添加了当前元素，目标和为target - nums[index]。
        //        combination.add(nums[index]);
        //        combinationSumFunc(nums, target - nums[index], index, combination, lists);
        //        combination.removeLast();// 回溯
        //    }
        //}
        //
        //
        ////  这里是先选择当前元素然后再不选当前元素
        ////private void combinationSumFunc(int[] nums, int target, int index, LinkedList<Integer> list, List<List<Integer>> lists){
        ////    if(target==0){
        ////        lists.add(new LinkedList<>(list));
        ////        return;
        ////    }else if(target>0&&index<nums.length){
        ////        list.add(nums[index]);
        ////        combinationSumFunc(nums,target-nums[index],index,list,lists);
        ////        list.removeLast();
        ////        combinationSumFunc(nums,target,index+1,list,lists);
        ////    }
        ////}
        //
        //



        // 解法2：回溯算法+剪枝
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> lists=new ArrayList<>();
            if(candidates==null||candidates.length==0){
                return lists;
            }
            combination(candidates,target,candidates.length,0,new LinkedList<>(),lists);
            return lists;
        }


        private void combination(int[] nums,int target,int len,int begin,LinkedList<Integer> path,
                                 List<List<Integer>> lists){
            if(target==0){
                lists.add(new LinkedList<>(path));
                return;
            }
            for (int i = begin; i < len; i++) {
                if(target<nums[i]){
                    continue;
                }
                path.add(nums[i]);
                combination(nums,target-nums[i],len,i,path,lists);
                path.removeLast();
            }
        }




    }
//leetcode submit region end(Prohibit modification and deletion)

}
