/**
 * <p>给定整数数组 <code>nums</code> 和整数 <code>k</code>，请返回数组中第 <code><strong>k</strong></code> 个最大的元素。</p>
 *
 * <p>请注意，你需要找的是数组排序后的第 <code>k</code> 个最大的元素，而不是第 <code>k</code> 个不同的元素。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> <code>[3,2,1,5,6,4] 和</code> k = 2
 * <strong>输出:</strong> 5
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> <code>[3,2,3,1,2,4,5,5,6] 和</code> k = 4
 * <strong>输出:</strong> 4</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示： </strong></p>
 *
 * <ul>
 * <li><code>1 <= k <= nums.length <= 10<sup>4</sup></code></li>
 * <li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>快速选择</li><li>排序</li><li>堆（优先队列）</li></div></div><br
 * ><div><li>👍 1718</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * 215
 * <p>
 * 数组中的第K个最大元素
 *
 * @author wangweizhou
 * @date 2022-06-29 01:27:24
 */

public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new KthLargestElementInAnArray().new Solution();

        /*
        int[] arr = {7,6,5,4,3,2,1};
        int b = solution.findKthLargest(arr, 2);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println(b);*/

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 解法1：将数组利用快排排序之后，根据下标找出第k个
        //public int findKthLargest(int[] nums, int k) {
        //    if (nums == null || nums.length == 0 || nums.length < k) {
        //        return Integer.MAX_VALUE;
        //    }
        //    int length = nums.length;
        //    quickSort(nums, 0, nums.length - 1);// 数组快排
        //    return nums[length - k];// 在长度为n的升序排序数组中，第k大的数字的下标是n-k。
        //}
        //
        //
        //// 快排数组
        //private int[] quickSort(int[] nums, int left, int right) {
        //    if (left < right) {
        //        int pivot = partition(nums, left, right);
        //        quickSort(nums, left, pivot - 1);// 递归中间值左侧元素
        //        quickSort(nums, pivot + 1, right);// 递归中间值右侧元素
        //    }
        //    return nums;
        //}
        //
        //
        //// 从数列中挑出一个元素，称为 "基准"（pivot），所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
        //private int partition(int[] nums, int left, int right) {
        //    int pivot = new Random().nextInt(right - left + 1) + left;// 随机选取[left,right]中的 "基准"（pivot）
        //    swap(nums, pivot, right);// 先将一个随机选中的"基准"（pivot）交换至数组的尾部
        //    int prevSmall = left - 1;// prevSmall指向上一个比"基准"（pivot）小的元素
        //    for (int i = left; i < right; i++) {// 指针i遍历[left,right-1]将遍历到的小于基准"基准"（pivot）放在前面
        //        if (nums[i] < nums[right]) {
        //            prevSmall++;
        //            swap(nums, prevSmall, i);
        //        }
        //    }
        //    // prevSmall指向最后一个比"基准"（pivot）小的位置，将基准交换至合适的位置。即左边比基准小，右边比基准大
        //    prevSmall++;
        //    swap(nums, prevSmall, right);
        //    return prevSmall;// 基准所在位置
        //}
        //
        //
        //// 交换数组中两个元素
        //private void swap(int[] nums, int index1, int index2) {
        //    int temp = nums[index1];
        //    nums[index1] = nums[index2];
        //    nums[index2] = temp;
        //}





        //// 解法2：利用快排选 "基准"（pivot）的方法
        //// 在长度为n的升序排序数组中，第k大的数字的下标是n-k。
        //// 如果函数partition选取的"基准"（pivot）在分区之后的下标正好是(n-k)，分区之后左边的值都比基准值小，右边的值都比基准值大。
        //// 那么即使这个数组不是完全排序的，基准值也是第k个元素。
        //// 如果函数partition选取的基准值在分区之后的下标大于(n-k),那么第k大的数字一定位于基准值的左侧，于是再对基准值左侧的子数组进行分区。
        //// 类似地，如果函数partition选取的基准值在分区之后的下标小于(n-k),那么第k大的数字一定位于基准值的右侧，于是再对基准值右侧的子数组进行分区。
        //// 重复这个过程，直到函数partition的返回值正好是下标为（n-k）的位置。
        //public int findKthLargest(int[] nums, int k) {
        //    if (nums == null || nums.length == 0 || nums.length < k) {
        //        return Integer.MAX_VALUE;
        //    }
        //
        //    int target=nums.length-k;// 在长度为n的升序排序数组中，第k大的数字的下标是n-k。
        //    int start=0;
        //    int end=nums.length-1;
        //
        //    int pivot =partition(nums,start,end);
        //    while(pivot !=target){
        //        if(pivot >target){
        //            end= pivot -1;
        //        }else{
        //            start= pivot +1;
        //        }
        //        pivot =partition(nums,start,end);
        //    }
        //    return nums[pivot];
        //}
        //
        //
        //private int partition(int[] nums,int left,int right){
        //    int pivot =new Random().nextInt(right-left+1)+left;// 随机选取[left,right]中的 "基准"（pivot）
        //    swap(nums, pivot,right);// 先将一个随机选中的"基准"（pivot）交换至数组的尾部
        //    int prevSmall=left-1;// prevSmall指向上一个比"基准"（pivot）小的元素
        //    for (int i = left; i <right ; i++) {// 指针i遍历[left,right-1]将遍历到的小于基准"基准"（pivot）放在前面
        //        if(nums[i]<nums[right]){
        //            prevSmall++;
        //            swap(nums,i,prevSmall);
        //        }
        //    }
        //    // prevSmall指向最后一个比"基准"（pivot）小的位置，将基准交换至合适的位置。即左边比基准小，右边比基准大
        //    prevSmall++;
        //    swap(nums,prevSmall,right);
        //    return prevSmall;// 基准所在位置
        //}
        //
        //
        //private void swap(int[] nums,int index1,int index2){
        //    if(index1!=index2){
        //        int temp=nums[index1];
        //        nums[index1]=nums[index2];
        //        nums[index2]=temp;
        //    }
        //}



        // 最小堆
        // 容量为K的最小堆，在第一次填满最小堆之后，遍历数组将比堆顶大的元素加入到堆中，那么最后堆顶就是第K大元素
        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k > nums.length) {
                return Integer.MIN_VALUE;
            }
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
            for (int i = 0; i < k; i++) {
                minHeap.offer(nums[i]);
            }

            for (int i = k; i < nums.length; i++) {
                if (nums[i] > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(nums[i]);
                }
            }
            return minHeap.peek();
        }



    }
//leetcode submit region end(Prohibit modification and deletion)

}
