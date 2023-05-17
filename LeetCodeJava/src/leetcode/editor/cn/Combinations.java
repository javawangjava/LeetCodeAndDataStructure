/**
 * <p>给定两个整数 <code>n</code> 和 <code>k</code>，返回范围 <code>[1, n]</code> 中所有可能的 <code>k</code> 个数的组合。</p>
 *
 * <p>你可以按 <strong>任何顺序</strong> 返回答案。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 4, k = 2
 * <strong>输出：</strong>
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1, k = 1
 * <strong>输出：</strong>[[1]]</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= n <= 20</code></li>
 * <li><code>1 <= k <= n</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>回溯</li></div></div><br><div><li>👍 1225</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77
 * 组合
 * @author wangweizhou
 * @date 2022-12-10 15:12:02
 */

public class Combinations {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new Combinations().new Solution();
        List<List<Integer>> lists = solution.combine(3, 2);

        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                System.out.print(lists.get(i).get(j));
            }
            System.out.println("===============");
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法1： 回溯法
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> lists = new LinkedList<>();
            if (k < 1 || n < k) {
                return lists;
            }
            LinkedList<Integer> combination = new LinkedList<>();
            combineFunc(n, k, 1, combination, lists);
            return lists;
        }


        //  当遇到第index个元素时，可以选择该元素也可以不选择该元素。
        // 从1~n中选k个数，index是1~n中的第index个数
        private void combineFunc(int n, int k, int index, LinkedList<Integer> combination, List<List<Integer>> lists) {
            // (n - index + 1)剩余可供选择的数的数目。
            if (combination.size() + (n - index + 1) < k) {// 剪枝，剩余可选长度不够选时，结束
                return;
            }
            if (combination.size() == k) {
                lists.add(new LinkedList<>(combination));
            } else if (index <= n) {
                // 不打算将当前元素添加到组合中，因为这里没有选当前元素，所以这个后面也就没有与此对应的清除当前状态
                combineFunc(n, k, index + 1, combination, lists);

                // 打算将当前元素添加到组合中
                combination.add(index);
                combineFunc(n, k, index + 1, combination, lists);
                //在回溯到父节点之前，应该清除已经对子集状态进行的修改。
                combination.removeLast();

            }
        }


        // 这里是先选当前元素，然后再不选当前元素
        //  当遇到第index个元素时，可以选择该元素也可以不选择该元素。
        //private void combineFunc(int n,int k,int index,LinkedList<Integer> combination,List<List<Integer>> lists){
        //	if(combination.size()+(n-index+1)<k){// 剪枝，剩余可选长度不够选时，结束
        //		return;
        //	}
        //	if (combination.size()==k){
        //		lists.add(new LinkedList<>(combination));
        //	}else if(index<=n){
        //
        //		// 打算将当前元素添加到组合中
        //		combination.add(index);
        //		combineFunc(n,k,index+1,combination,lists);
        //		//在回溯到父节点之前，应该清除已经对子集状态进行的修改。
        //		combination.removeLast();
        //
        //		// 不打算将当前元素添加到组合中
        //		combineFunc(n,k,index+1,combination,lists);
        //
        //	}
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
