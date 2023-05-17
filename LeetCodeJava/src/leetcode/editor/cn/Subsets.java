/**
 * <p>给你一个整数数组 <code>nums</code> ，数组中的元素 <strong>互不相同</strong> 。返回该数组所有可能的子集（幂集）。</p>
 *
 * <p>解集 <strong>不能</strong> 包含重复的子集。你可以按 <strong>任意顺序</strong> 返回解集。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0]
 * <strong>输出：</strong>[[],[0]]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 10</code></li>
 * <li><code>-10 <= nums[i] <= 10</code></li>
 * <li><code>nums</code> 中的所有元素 <strong>互不相同</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>位运算</li><li>数组</li><li>回溯</li></div></div><br><div><li>👍 1872</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 78
 * 子集
 * @author wangweizhou
 * @date 2022-12-09 21:49:30
 */

public class Subsets {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new Subsets().new Solution();
        List<List<Integer>> lists = solution.subsets(new int[]{1, 2});

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 所谓子集就是从一个集合中选出若干元素。如果集合中包含n个元素，那么生成子集可以分为n步，每一步从集合中取出一个数字，
        // 此时面临两个选择，将该数字添加到子集中或不将该数字添加到子集中。

        // 解法1：回溯法
        public List<List<Integer>> subsets(int[] nums) {
        	List<List<Integer>> lists=new ArrayList<>();
        	if(nums==null||nums.length==0){
        		return lists;
        	}
        	subsetsFunc(nums,0,new LinkedList<>(),lists);
        	return lists;
        }


        // 递归函数subsetsFunc一共有4个参数。第1个参数是数组nums，它包含输入集合的所有数字。可以逐一从集合中取出一个数字并选择是否将该数字添加到子集中。
        // 第2个参数index是当前取出的数字在数组nums中的下标。第3个参数subset是当前子集，而第4个参数result是所有已经生成的子集。
        // 每当从数组nums中取出一个下标为index的数字时，都要考虑是否将该数字添加到子集subset中。
        // 首先需要考虑不将该数字添加到子集的情形。
        // 由于不打算将该数字添加到子集中，因此不对子集进行任何操作，只需要调用递归函数subsetsFunc处理数组nums中的下一个数字（下标增加1）就可以。
        // 接着考虑将下标为index的数字添加到子集subset的情形。在将该数字添加到子集之后，接下来调用递归函数处理数组nums中的下一个数字（下标增加1）。
        // 等递归函数执行完成之后，函数subsetsFunc也执行完成，接下来将回到前一个数字的函数调用处继续执行。
        // 如果参考深度遍历形成的树形结构，那么此时将回溯到父节点，以便尝试父节点的其他选项。
        // 在回溯到父节点之前，应该清除已经对子集状态进行的修改。此前在子集subset中添加了一个数字，此时应该将它删除。
        // 当index等于数组nums的长度时，表示数组中的所有数字都已经处理过，因此已经生成了一个子集，于是将子集subset添加到result中。
        // 需要注意的是，在result中添加的是subset的一个拷贝，而不是subset本身。
        // 这是因为接下来还需要修改subset以便得到其他的子集，同时避免已经添加到result中的子集被修改。在result中添加subset的拷贝可以避免不必要的修改。


        // 递归函数subsetsFunc第1个参数是数组nums，它包含输入集合的所有数字。可以逐一从该集合中取出一个数字并选择是否将该数字添加到子集中。
        // 第2个参数index是当前取出的数字在数组nums中的下标。第3个参数subset是当前子集，而第4个参数result是所有已经生成的子集。

        // 注意这里的第三个参数subset，也就是子集集合使用的是LinkedList来实现的，这样才能清除最后一个修改的状态
        private void subsetsFunc(int[] nums, int index, LinkedList<Integer> subset,List<List<Integer>> lists){
        	if(index==nums.length){
        		// 数组索引从0开始，当数组索引等于数组长度时，说明已经遍历完了数组中的元素【也就是数组最后一个元素是否添加到集合中已经讨论过了】，并且数组下标已经越界了。
        		// 当index等于数组nums的长度时，表示数组中的所有数字都已经处理过，因此已经生成了一个子集，于是将子集subset添加到result中。
        		// 需要注意的是，在result中添加的是subset的一个拷贝，而不是subset本身。
        		// 这是因为接下来还需要修改subset以便得到其他的子集，同时避免已经添加到result中的子集被修改。在result中添加subset的拷贝可以避免不必要的修改。
        		lists.add(new LinkedList<>(subset));
        	}else if(index<nums.length){
        		// 每当从数组nums中取出一个下标为index的数字时，都要考虑是否将该数字添加到子集subset中。首先需要考虑不将该数字添加到子集的情形。
        		// 由于不打算将该数字添加到子集中，因此不对子集subset进行任何操作，只需要调用递归函数helper处理数组nums中的下一个数字（下标增加1）就可以。
        		subsetsFunc(nums,index+1,subset,lists);
        		// 考虑将该数字添加到子集的情形。将下标为index的数字添加到子集subset的情形。
        		// 在将该数字添加到子集之后，接下来调用递归函数处理数组nums中的下一个数字（下标增加1）。
        		subset.add(nums[index]);
        		subsetsFunc(nums,index+1,subset,lists);
        		// 接下来将回到前一个数字的函数调用处继续执行。那么此时将回溯到父节点，以便尝试父节点的其他选项。
                // 在回溯到父节点之前，应该清除已经对子集状态进行的修改。此前在子集subset中添加了一个数字，此时应该将它删除。
        		subset.removeLast();
        	}
        }




        //// 写法2：下面是先考虑选，然后再考虑不选
        //public List<List<Integer>> subsets(int[] nums) {
        //    List<List<Integer>> lists = new ArrayList<>();
        //    if (nums == null || nums.length == 0) {
        //        return lists;
        //    }
        //    subSetFunc(nums, 0, new LinkedList<>(), lists);
        //    return lists;
        //}
        //
        //
        //private void subSetFunc(int[] nums, int index, LinkedList<Integer> list, List<List<Integer>> lists) {
        //    //if(index==nums.length){
        //    //	lists.add(new ArrayList<>(list));
        //    //	return;
        //    //}else if(index<nums.length)  {
        //    //	list.add(nums[index]);
        //    //	subSetFunc(nums,index+1,list,lists);
        //    //	list.removeLast();
        //    //	subSetFunc(nums,index+1,list,lists);
        //    //}
        //
        //    if (index == nums.length) {
        //        lists.add(new ArrayList<>(list));
        //        return;
        //    }
        //    // 深度遍历树，每遇到一个新的元素就将该元素添加到子集中，并递归下一个元素。
        //    list.add(nums[index]);
        //    subSetFunc(nums, index + 1, list, lists);
        //    list.removeLast();// 回溯，清除状态
        //    subSetFunc(nums, index + 1, list, lists);// 因为这个是不选当前元素，所以不需要将该元素添加到子集中，也不需要清除状态
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
