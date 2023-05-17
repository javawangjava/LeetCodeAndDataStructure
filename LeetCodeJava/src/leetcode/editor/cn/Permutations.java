/**
 * <p>给定一个不含重复数字的数组 <code>nums</code> ，返回其 <em>所有可能的全排列</em> 。你可以 <strong>按任意顺序</strong> 返回答案。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,1]
 * <strong>输出：</strong>[[0,1],[1,0]]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1]
 * <strong>输出：</strong>[[1]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 6</code></li>
 * <li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
 * <li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 2133</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 46
 * 全排列
 *
 * @author wangweizhou
 * @date 2022-07-28 09:49:45
 */

public class Permutations {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new Permutations().new Solution();

        int[] nums = {1, 2, 3};
        List<List<Integer>> ans = solution.permute(nums);
        System.out.println(ans);

    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 如果输入的集合中有n个元素，那么生成一个全排列需要n步。当生成排列的第1个数字时会面临n个选项，即n个数字都有可能成为排列的第1个数字。
        // 生成排列的第1个数字之后接下来生成第2个数字，此时面临n-1个选项，即剩下的n-1个数字都有可能成为第2个数字。
        // 然后以此类推，直到生成最后一个数字，此时只剩下1个数字，也就只有1个选项。


        // 这个问题可以看作有 n 个排列成一行的空格，我们需要从左往右依此填入题目给定的 n 个数，每个数只能使用一次。可以使用标记数组来处理填过的数。
        // 我们可以将题目给定的 n 个数的数组 nums 划分成左右两个部分，左边的表示已经填过的数，右边表示待填的数，我们在回溯的时候只要动态维护这个数组即可。
        // 具体来说，假设我们已经填到第 first 个位置，那么 nums 数组中 [0,first−1]是已填过的数的集合，[first,n−1] 是待填的数的集合。
        // 我们肯定是尝试用 [first,n−1] 里的数去填first 个数，假设待填的数的下标为 i，那么填完以后我们将第 i 个数和第 first 个数交换，
        // 即能使得在填第 first+1 个数的时候 nums 数组的 [0,first] 部分为已填过的数，[first+1,n−1] 为待填的数，回溯的时候交换回来即能完成撤销操作。


        //// 解法2：回溯
        //public List<List<Integer>> permute(int[] nums) {
        //    List<List<Integer>> lists = new ArrayList<>();// 使用一个动态数组lists保存所有可能的全排列
        //    if (nums == null || nums.length == 0) {//判空
        //        return lists;
        //    }
        //    permuteFunc(nums, 0, lists);
        //    return lists;
        //}
        //
        //
        //// 通常用递归的代码实现回溯法。代码中的递归函数permuteFunc生成输入数组nums的所有全排列，在函数执行过程中数组nums保存着当前排列的状态。
        //// 当函数permuteFunc生成排列的下标为start的数字时，下标从0到start-1的数字都已经选定，但数组nums中下标从start到n-1的数字（假设数组的长度为n）都有可能放到排列的下标为start的位置，
        //// 因此函数helper中有一个for循环逐一用下标为start的数字交换它后面的数字。这个for循环包含下标为start的数字本身，这是因为它自己也能放在排列下标为start的位置。
        //// 交换之后接着调用递归函数生成排列中下标为start+1的数字。
        //// 由于之前已经交换了数组中的两个数字，修改了排列的状态，在函数退出之前需要清除对排列状态的修改，因此再次交换之前交换的两个数字。
        //// 当下标start等于数组nums的长度时，排列的每个数字都已经产生了，nums中保存了一个完整的全排列，于是将全排列复制一份并添加到返回值result中。
        //// 最终result中包含所有的全排列。
        //
        //// 第一个参数nums保存着当前排列的状态，index表示在当前排列中的当前位置，lists表示所有排列
        //
        //// permuteFunc从数组nums中选定排列的第start个元素
        //private void permuteFunc(int[] nums, int start, List<List<Integer>> lists) {
        //    if (start == nums.length) {// 数组下标从0开始，当数组的遍历指针等于数组长度，说明已经选完了参数nums中的所有元素
        //        // 当下标start等于数组nums的长度时，排列的每个数字都已经产生了，nums中保存了一个完整的全排列，于是将全排列复制一份并添加到返回值result中。最终result中包含所有的全排列。
        //        List<Integer> permutation = new LinkedList<>();
        //        for (int num : nums) {// 将数组转化为集合
        //            permutation.add(num);
        //        }
        //        lists.add(permutation);
        //        return;
        //    } else {
        //        // 当函数permuteFunc生成排列的下标为start的数字时，下标从0到start-1的数字都已经选定，
        //        // 但数组nums中下标从start到n-1的数字（假设数组的长度为n）都有可能放到排列的下标为start的位置，因此函数helper中有一个for循环逐一用下标为start的数字交换它后面的数字。
        //        // 这个for循环包含下标为start的数字本身，这是因为它自己也能放在排列下标为start的位置。
        //        // for循环就是将数组nums中下标从start到n-1的数字（假设数组的长度为n）逐个放置到下标start的位置
        //        for (int i = start; i < nums.length; i++) {
        //            // 进入到这里就要选取第index位上的元素
        //            swap(nums, start, i);// 选定index位置的元素为原来下标为i的位置的元素
        //            // 交换之后接着调用递归函数生成排列中下标为i+1的数字。
        //            permuteFunc(nums, start + 1, lists);
        //            //由于之前已经交换了数组中的两个数字，修改了排列的状态，在函数退出之前需要清除对排列状态的修改，因此再次交换之前交换的两个数字。
        //            swap(nums, start, i);
        //        }
        //    }
        //}
        //
        //
        //// 交换数组中两元素
        //private void swap(int[] nums, int i, int j) {
        //    if (i != j) {
        //        int temp = nums[i];
        //        nums[i] = nums[j];
        //        nums[j] = temp;
        //    }
        //}






        // 解法1：回溯+标记数组  写法1：ArrayList或者List实现List
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> lists = new ArrayList<>();// 使用一个动态数组lists保存所有可能的全排列
            if (nums == null || nums.length == 0) {// 判空
                return lists;
            }
            boolean[] isVisited = new boolean[nums.length];// isVisited用来标记数组元素是否访问过
            permuteFunc(nums, isVisited, new ArrayList<>(), lists);
            return lists;
        }


        // 注意这里时全排列，所以选定第i个元素时，可以从没有访问过的剩余元素中选取
        private void permuteFunc(int[] nums, boolean[] isVisited, List<Integer> path, List<List<Integer>> lists) {
            if (path.size() == nums.length) {  //当path中元素个数等于数组中元素个数，也就是将数组中所有数都添加到path中
                // 注意！此处需要重新new一个List实现，然后进行赋值
                // 因为不重新构造List进行赋值，那么在回溯过程中，由于引用的存在，依旧会将list的元素进行移除，这样最终得到的
                lists.add(new ArrayList<>(path));
                //lists.add(path);//注意这里就错了，回溯的时候会修改已经添加到lists中的list
                return;
            }
            for (int i = 0; i < nums.length; i++) { // i是用来遍历标记数组和nums数组
                // 从nums数组中遇到没有访问的元素，则添加该元素
                if (!isVisited[i]) {// 当标记数组的元素是false,也就是数组中nums[i]元素没有被访问
                    path.add(nums[i]);// 将没有访问过的当前数字添加到path中
                    isVisited[i] = true;// 并将已经访问过的元素标记为已访问
                    permuteFunc(nums, isVisited, path, lists);// 再次进行backtrack
                    //回溯,移除list中最后添加的一个元素，并将移除的最后一个元素的状态设置为false
                    path.remove(path.get(path.size() - 1));
                    isVisited[i] = false;
                }
            }
        }




        //
        ////// 解法1：回溯+标记数组  写法2：LinkedList实现List
        //public List<List<Integer>> permute(int[] nums) {
        //    List<List<Integer>> lists=new ArrayList<>();// 使用一个动态数组lists保存所有可能的全排列
        //    if(nums==null||nums.length==0){// 判空
        //        return lists;
        //    }
        //    boolean[] isVisited=new boolean[nums.length];// isVisited用来标记数组元素是否访问过
        //    permuteFunc(nums,isVisited,new LinkedList<>(),lists);
        //    return lists;
        //}
        //
        //
        //// 动态数组path用来存储一种全排列，
        ////注意这里是用LinkedList实现的。可以和后面的移除最后一个元素对应。//list.removeLast();//LinkedList中 E removeLast() 从此列表中删除并返回最后一个元素。
        //private void permuteFunc(int[] nums,boolean[] isVisited,LinkedList<Integer> path,List<List<Integer>> lists){
        //    if(nums.length==path.size()){// 当path中元素个数等于数组中元素个数，也就是将数组中所有数都添加到path中
        //        // 注意！此处需要重新new一个List实现，然后进行赋值
        //        // 因为不重新构造List进行赋值，那么在回溯过程中，由于引用的存在，依旧会将list的元素进行移除，这样最终得到的
        //        lists.add(new LinkedList<>(path));
        //        //lists.add(path);//注意这里就错了，回溯的时候会修改已经添加到lists中的list
        //        return;
        //    }
        //
        //    for (int i = 0; i < nums.length; i++) {// i是用来遍历标记数组和nums数组
        //        // 从nums数组中遇到没有访问的元素，则添加该元素
        //        if(!isVisited[i]){// 当标记数组的元素是false,也就是数组中nums[i]元素没有被访问
        //            path.add(nums[i]);// 将没有访问过的当前数字添加到path中
        //            isVisited[i]=true;// 并将已经访问过的元素标记为已访问
        //            permuteFunc(nums,isVisited,path,lists);// 再次进行backtrack
        //            //回溯,移除list中最后添加的一个元素，并将移除的最后一个元素的状态设置为false
        //            path.removeLast();
        //            isVisited[i]=false;
        //        }
        //    }
        //}



        //// 解法1：回溯+哈希表标记数组元素是否已经访问  写法3：哈希表标记+回溯
        //public List<List<Integer>> permute(int[] nums) {
        //    // 定义返回结果集
        //    List<List<Integer>> lists=new ArrayList<>();
        //    if(nums==null||nums.length==0){//判空
        //        return lists;
        //    }
        //    List<Integer> list=new ArrayList<>();
        //    // 初始化HashMap，key为num或者nums[i]，value为该数字是否已使用，
        //    // value为false表示该数字未被使用，value为true表示该数字已经被使用
        //    Map<Integer,Boolean> isVisited=new HashMap<>();
        //    for(int num:nums){
        //        isVisited.put(num,false);
        //    }
        //    backtrace(lists,list,isVisited,nums);
        //    return lists;
        //}
        //
        //
        //
        //private void backtrace(List<List<Integer>> lists,List<Integer> list,Map<Integer,Boolean> isVisited,int[] nums){
        //    // 判断List是否已经达到满足答案的长度
        //    if(list.size()==nums.length){
        //        // 注意！此处需要重新new一个List实现，然后进行赋值
        //        // 因为不重新构造List进行赋值，那么在回溯过程中，由于引用的存在，依旧会将list的元素进行移除，这样最终得到的结果就是为[]
        //        lists.add(new ArrayList<>(list));
        //        return;
        //    }
        //    // 开始遍历数组数字
        //    for (int i = 0; i < nums.length; i++) {
        //        // 去哈希表中检查当前数字是否已使用
        //        // false表示该数字未被使用
        //        if(!isVisited.get(nums[i])){
        //            list.add(nums[i]);// 将当前数字添加到list中
        //            isVisited.put(nums[i],true);// 更新哈希表中关于该数字的使用状态
        //            backtrace(lists,list,isVisited,nums);// 再次进行backtrack，以当前数字为基础进行排列
        //            // 当结束上面的backtrack时，说明基于当前数字进行的回溯已经结束
        //            // 移除list中的元素
        //            // 因为List存放的是数字，所以不能直接使用num进行删除，否则调用的是另外一个重载方法
        //            list.remove(list.size()-1);
        //            isVisited.put(nums[i],false);
        //        }
        //    }
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
