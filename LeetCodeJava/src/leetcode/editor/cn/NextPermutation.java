/**
 * <p>整数数组的一个 <strong>排列</strong>&nbsp; 就是将其所有成员以序列或线性顺序排列。</p>
 *
 * <ul>
 * <li>例如，<code>arr = [1,2,3]</code> ，以下这些都可以视作 <code>arr</code> 的排列：<code>[1,2,3]</code>、<code>[1,3,
 * 2]</code>、<code>[3,1,2]</code>、<code>[2,3,1]</code> 。</li>
 * </ul>
 *
 * <p>整数数组的 <strong>下一个排列</strong> 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 <strong>下一个排列</strong>
 * 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。</p>
 *
 * <ul>
 * <li>例如，<code>arr = [1,2,3]</code> 的下一个排列是 <code>[1,3,2]</code> 。</li>
 * <li>类似地，<code>arr = [2,3,1]</code> 的下一个排列是 <code>[3,1,2]</code> 。</li>
 * <li>而 <code>arr = [3,2,1]</code> 的下一个排列是 <code>[1,2,3]</code> ，因为 <code>[3,2,1]</code> 不存在一个字典序更大的排列。</li>
 * </ul>
 *
 * <p>给你一个整数数组 <code>nums</code> ，找出 <code>nums</code> 的下一个排列。</p>
 *
 * <p>必须<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"> 原地 </a>
 * </strong>修改，只允许使用额外常数空间。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>[1,3,2]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,2,1]
 * <strong>输出：</strong>[1,2,3]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,1,5]
 * <strong>输出：</strong>[1,5,1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 100</code></li>
 * <li><code>0 &lt;= nums[i] &lt;= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li></div></div><br><div><li>👍 1758</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 31
 * 下一个排列
 */

public class NextPermutation {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new NextPermutation().new Solution();
         int[] nums={1,2,3,9,5,7,8,6,4};
         solution.nextPermutation(nums);
        for (int num:nums) {
         System.out.print(num+",");
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 字典序排序：找出第一个不同的数，第一个不同的数小的在前，第一个不同的数大的在后。
        //   思路分析：
        //    1.希望下一个数比当前数大。只需要将后面的「大数」与前面的「小数」交换，就能得到一个更大的数
        //    2.希望下一个数增加的幅度尽可能的小：
        //    2.1在尽可能靠右的低位进行交换，需要从后向前查找。
        //    2.2将一个尽可能小的「大数」 与前面的「小数」交换。
        //    2.3将「大数」换到前面后，需要将「大数」后面的所有数重置为升序，升序排列就是最小的排列。


        //标准的“下一个排列”算法可以描述为：
        //1.从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
        //2.在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
        //3.将 A[i] 与 A[k] 交换
        //4.可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
        //5.如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4


        //	 解法1：两遍扫描 写法1
        public void nextPermutation(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return;
            }

            int length = nums.length;
            int right = length - 1;
            int left = length - 2;

            // 从后向前找到第一个nums[left]<nums[left+1],也就是从后向前第一对升序对，
            while(left>=0&&nums[left]>=nums[left+1]){
                left--;
            }

            // 若left<0，则说明整个数组是逆序，那么按照题意就要返回字典排序的最小排列，那么可以把整个数组从降序改成升序
            // 若left>0, 则说明[left+1,leng]是逆序
            if(left>=0){
                //从后向前找到第一个比left指向的数小的元素，从后向前找到尽可能小的大数
                while(nums[left]>=nums[right]){
                    right--;
                }
                swap(nums,left,right);
            }
            //反转降序数组[left,length-1]
            reverse(nums,left+1,length-1);
        }


        // 交换数组中下标为i和j的元素
        private void swap(int[] nums,int i, int j){
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
        }


        // 反转[start，end]
        private void reverse(int[] nums,int start,int end){
            if(nums==null||nums.length==0){
                return;
            }
            int left=start;//数组反转的开始位置
            int right=end;//数组反转的结束位置
            while(left<right){
                swap(nums,left,right);
                left++;
                right--;
            }
        }




     /*


         //	 解法1：两遍扫描  写法2
        public void nextPermutation(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return;
            }
            int length = nums.length;
            int right = length - 1;
            int left = length - 2;
            int temp = length - 1;

            // 从后向前找到第一个nums[left]<nums[right],也就是从后向前第一对升序对
            while (left >= 0 && nums[left] >= nums[right]) {
                left--;
                right--;
            }

            if (left >= 0) {//不是最后排列
                //	在 [left ,end) 从后向前查找第一个满足 A[left] < A[temp] 的 k。A[left], A[temp] 分别就是上文所说的「小数」、「大数」
                while (nums[left] >= nums[temp]) {
                    temp--;
                }
                swap(nums, left, temp);//	将 A[left]与 A[temp] 交换
            }

            //反转数组[right,length-1]
            reverse(nums, right);//可以断定这时 [right,end) 必然是降序，逆置 [right,end)，使其升序
        }


        // 交换数组中下标为i和j的元素，数组是引用，这样才能把修改后的结果传回原数组，当然也可以不用定义方法，调用方法来做
        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }


        // 反转数组[left,right]
        public void reverse(int[] nums, int start) {
            int left = start;//反转的开始位置
            int right = nums.length - 1;//反转的结束位置
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
