/**
 * <p>给定一个可包含重复数字的序列 <code>nums</code> ，<em><strong>按任意顺序</strong></em> 返回所有不重复的全排列。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,1,2]
 * <strong>输出：</strong>
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 8</code></li>
 * <li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 1146</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 47
 * 全排列 II
 *
 * @author wangweizhou
 * @date 2022-08-01 22:20:01
 */

// 对比46
public class PermutationsIi {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new PermutationsIi().new Solution();
        int[] nums={1,1,2};
        solution.permuteUnique(nums);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 解法1：回溯法+哈希表去重   参见46
        //// 如果集合中有重复的数字，那么交换集合中重复的数字得到的全排列是同一个全排列。
        //// 当处理到全排列的第i个数字时，如果已经将某个值为m的数字交换为排列的第i个数字，那么再遇到其他值为m的数字就跳过。
		//
        //public List<List<Integer>> permuteUnique(int[] nums) {
        //	List<List<Integer>> lists = new ArrayList<>();// 使用一个动态数组lists保存所有可能的全排列
        //	if (nums == null || nums.length == 0) {// 判空
        //		return lists;
        //	}
        //	permuteUniqueFunc(nums,0,lists);
        //	return lists;
        //}
		//
		//
        //private void permuteUniqueFunc(int[] nums, int start, List<List<Integer>> lists){
        //	if(start ==nums.length){// 数组下标从0开始，当数组的遍历指针等于数组长度，说明已经完成了所有步骤，
        //		// 当下标start等于数组nums的长度时，排列的每个数字都已经产生了，nums中保存了一个完整的全排列，于是将全排列复制一份并添加到返回值result中。最终result中包含所有的全排列。
        //		List<Integer> permutation = new LinkedList<>();
        //		for (int num : nums) {// 将数组转化为集合
        //			permutation.add(num);
        //		}
        //		lists.add(permutation);
        //	}else{
        //		// 当函数permuteFunc生成排列的下标为start的数字时，下标从0到start-1的数字都已经选定，
        //		// 但数组nums中下标从start到n-1的数字（假设数组的长度为n）都有可能放到排列的下标为start的位置，因此函数helper中有一个for循环逐一用下标为start的数字交换它后面的数字。
        //		// 这个for循环包含下标为start的数字本身，这是因为它自己也能放在排列下标为start的位置。
        //		// for循环就是将数组nums中下标从start到n-1的数字（假设数组的长度为n）逐个放置到下标start的位置
		//
        //        // 使用了一个HashSet，用来保存已经交换到排列下标为start的位置的所有值。只有当一个数值之前没有被交换到第start位时才做交换，否则直接跳过。
        //		Set<Integer> set=new HashSet<>();
        //		for (int i = start; i < nums.length; i++) {
        //			// 只有当一个数值nums[i]之前没有被交换到第index位时才做交换，否则直接跳过。
        //			if(!set.contains(nums[i])){
        //				set.add(nums[i]);// 将交换至第index位上的元素添加到哈希表中，
        //				// 进入到这里就要选取第index位上的元素
        //				swap(nums, start, i);// 选定index位置的元素为原来下标为j的位置的元素
        //				// 交换之后接着调用递归函数生成排列中下标为i+1的数字。
        //				permuteUniqueFunc(nums, start +1,lists);
        //				//由于之前已经交换了数组中的两个数字，修改了排列的状态，在函数退出之前需要清除对排列状态的修改，因此再次交换之前交换的两个数字。
        //				swap(nums, start, i);
        //			}
        //		}
        //	}
        //}
		//
		//
        //// 交换数组中两元素
        //private void swap(int[] nums, int i,int j){
        //	if(i!=j){
        //		int temp=nums[i];
        //		nums[i]=nums[j];
        //		nums[j]=temp;
        //	}
        //}






        // 解法2：回溯+标记数组+按字典顺序排序
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> lists = new ArrayList<>();
            if (nums == null || nums.length == 0) {// 判空
                return lists;
            }
            Arrays.sort(nums);// 先按字典序排序
            boolean[] isVisited = new boolean[nums.length];
            permuteFunc(nums, isVisited, new ArrayList<>(), lists);
            return lists;
        }



        // 第3个参数list表示一种全排列，第4个参数lists表示所有全排列，
        private void permuteFunc(int[] nums, boolean[] isVisited, List<Integer> path, List<List<Integer>> lists) {
            if (path.size() == nums.length) {// 当集合path中已经选择完了数组中的所有元素，即一种全排列完成了
                lists.add(new ArrayList<>(path));
                return;
            }

            // 因为是全排列，所以下一个位置的元素可以从没有使用过的元素中选，所以下面的循环每次从数组nums的第一个元素开始选取
            for (int i = 0; i < nums.length; i++) {//遍历所有元素选取⼀个加⼊
                if (isVisited[i]) {// 如果nums[i]已经访问过了，则跳过
                    continue;
                }

                //当前的元素num[i]与同⼀层的前⼀个元素num[i-1]相同且当前位置（i-1）的元素已经深度遍历过了
                // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
                // 写 !isVisited[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
                if (i > 0 && nums[i] == nums[i - 1] && !isVisited[i - 1]) {
                    continue;
                }

                // 将当前元素添加到path中，并将该元素设置为已访问
                path.add(nums[i]);
                isVisited[i] = true;
                permuteFunc(nums, isVisited, path, lists);// 递归
                // 回溯  修改最后一次所作的修改。移出path中最后添加的一个元素，并将该元素的状态设置为未访问
                path.remove(path.size() - 1);// 移除list中最后一个元素
                isVisited[i] = false;// 将list中最后一个元素设置为未使用过
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
