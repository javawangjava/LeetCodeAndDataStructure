/**
 * <p>在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre><strong>输入</strong>: [7,5,6,4]
 * <strong>输出</strong>: 5</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <p><code>0 &lt;= 数组长度 &lt;= 50000</code></p>
 * <div><div>Related Topics</div><div><li>树状数组</li><li>线段树</li><li>数组</li><li>二分查找</li><li>分治</li><li>有序集合</li><li
 * >归并排序</li></div></div><br><div><li>👍 797</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 51
 * 数组中的逆序对
 *
 * @author wangweizhou
 * @date 2022-07-20 09:17:04
 */

public class ShuZuZhongDeNiXuDuiLcof {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ShuZuZhongDeNiXuDuiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 「归并排序」与「逆序对」是息息相关的。归并排序体现了 “分而治之” 的算法思想，具体为：
        //分： 不断将数组从中点位置划分开（即二分法），将整个数组的排序问题转化为子数组的排序问题；
        //治： 划分到子数组长度为 1 时，开始向上合并，不断将 较短排序数组 合并为 较长排序数组，直至合并至原数组时完成排序；
        //合并阶段 本质上是 合并两个排序数组 的过程，而每当遇到 左子数组当前元素 > 右子数组当前元素 时，意味着 「左子数组当前元素 至 末尾元素」 与 「右子数组当前元素」 构成了若干 「逆序对」 。


        // merge_sort() 归并排序与逆序对统计：
        //终止条件： 当 l≥r 时，代表子数组长度为 1 ，此时终止划分；
        //递归划分： 计算数组中点 m ，递归划分左子数组 merge_sort(l, m) 和右子数组 merge_sort(m + 1, r) ；
        //合并与逆序对统计：
        //暂存数组 nums 闭区间 [i,r] 内的元素至辅助数组 tmp ；
        //循环合并： 设置双指针 i , j 分别指向左 / 右子数组的首元素；
        //当 i=m+1 时： 代表左子数组已合并完，因此添加右子数组当前元素 tmp[j] ，并执行 j=j+1 ；
        //否则，当 j=r+1 时： 代表右子数组已合并完，因此添加左子数组当前元素 tmp[i] ，并执行 i=i+1 ；
        //否则，当 tmp[i]≤tmp[j] 时： 添加左子数组当前元素 tmp[i] ，并执行 i=i+1；
        //否则（即 tmp[i]>tmp[j]）时： 添加右子数组当前元素 tmp[j] ，并执行 j=j+1 ；此时构成 m−i+1个「逆序对」，统计添加至 resres ；
        //返回值： 返回直至目前的逆序对总数 resres ；

        //reversePairs() 主函数：
        //初始化： 辅助数组 tmp ，用于合并阶段暂存元素；
        //返回值： 执行归并排序 merge_sort() ，并返回逆序对总数即可；


        // 解法2：归并排序
        // 利用归并排序解答，在合并的时候，当左边的大于右边，就计算逆序数。
        // 计算公式； mid-left+1
        // 定义一个全局的计数器变量

        //int count;// 全局变量    //定义一个计数器，记下每次合并中存在的逆序数。
        //public int reversePairs(int[] nums) {
        //    if (nums == null || nums.length == 0) {//
        //        return 0;
        //    }
        //    count = 0;// 逆序对数
        //    mergeSort(nums, 0, nums.length - 1);
        //    return count;
        //}
        //
        //
        //// 归并排序
        //private void mergeSort(int[] nums, int left, int right) {
        //    //当只有一个节点的时候，直接返回，退出递归
        //    if (nums == null || nums.length == 0 || left >= right) {
        //        return;
        //    }
        //    int mid = (left + right) / 2;
        //    mergeSort(nums, left, mid);// 对左侧子序列进行递归排序
        //    mergeSort(nums, mid + 1, right);// 对右侧子序列进行递归排序
        //    merge(nums, left, mid, right);// 合并两个有序序列
        //}
        //
        //
        //// 合并
        //private void merge(int[] nums, int left, int mid, int right) {
        //    if (nums == null || nums.length == 0) {
        //        return;
        //    }
        //    int[] temp = new int[right - left + 1];// 定义一个临时数组来保存合并后的数组
        //    int index = 0;// 定义一个指针index ，指向临时数组的第一个元素。index其实就是合并后数组的遍历指针
        //    int p1 = left;// 左侧子序列遍历指针
        //    int p2 = mid + 1;// 右侧子序列遍历指针
        //    // 合并两个排序数组的思路
        //    while (p1 <= mid && p2 <= right) {// 当两个子数组都有元素的时候，遍历比较每个元素大小
        //        // 合并：合并两个已排序子序列得到排序结果。
        //        if (nums[p1] <= nums[p2]) {// 比较两个数组的元素，取较小的元素加入到临时数组中，并将两个指针指向下一个元素
        //            temp[index++] = nums[p1++];
        //        } else {// nums[p1] > nums[p2]
        //            temp[index++] = nums[p2++];
        //            // 因为归并排序是合并两个有序子序列，
        //            // 那么若左边数组的某一个元素nums[p1]大于右边数组的某一个元素nums[p2]，那么左边数组该元素nums[p1]后面的元素都和右边数组的元素nums[p2]构成逆序。
        //            // 则这时候的逆序数为（mid - p1 + 1）。左边nums[p1]及后面的元素：[p1,mid]
        //            //定义一个计数器，记下每次合并中存在的逆序数。
        //            count += mid - p1 + 1;
        //        }
        //    }
        //    // 上面循环执行完毕之后，至少有一个子序列已经遍历完了，也就是下面这两个循环最多只执行一个。
        //    // 那么就需要将剩余的元素添加到保存合并后序列的数组中
        //    while (p1 <= mid) {//当左边的数组没有遍历完成时，直接将剩余元素加入到临时数组中
        //        temp[index++] = nums[p1++];
        //    }
        //    while (p2 <= right) {//当右边的数组没有遍历完成时，直接将剩余元素加入到临时数组中
        //        temp[index++] = nums[p2++];
        //    }
        //    //将新数组中的元素，覆盖nums旧数组中对应位置的元素。
        //    //此时数组的元素已经是有序的
        //    for (int k = 0; k < temp.length; k++) {
        //        nums[left + k] = temp[k];
        //    }
        //}



        // 解法2：归并排序  写法2 这里使用只有一个元素的数组来传递参数
        public int reversePairs(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int[] counts = new int[1];
            mergesort(nums, 0, nums.length - 1, counts);
            return counts[0];
        }


        private void mergesort(int[] nums, int left, int right, int[] counts) {
            if (nums == null || nums.length == 0 || left >= right) {
                return;
            }
            int mid = (left + right) >> 1;
            mergesort(nums, left, mid, counts);
            mergesort(nums, mid + 1, right, counts);
            merge(nums, left, mid, right, counts);
        }


        private void merge(int[] nums, int left, int mid, int right, int[] counts) {
            int[] temp = new int[right - left + 1];
            int index = 0;
            int p1 = left;
            int p2 = mid + 1;
            while (p1 <= mid && p2 <= right) {
                if (nums[p1] <= nums[p2]) {
                    temp[index] = nums[p1];
                    index++;
                    p1++;
                } else {
                    temp[index] = nums[p2];
                    index++;
                    p2++;
                    counts[0] += mid - p1 + 1;
                }
            }

            while (p1 <= mid) {
                temp[index] = nums[p1];
                index++;
                p1++;
            }
            while (p2 <= right) {
                temp[index] = nums[p2];
                index++;
                p2++;
            }

            for (int i = 0; i < temp.length; i++) {
                nums[left + i] = temp[i];
            }
        }


        //// 方法1：暴力循环超时了
        //public int reversePairs(int[] nums) {
        //
        //	if(nums==null){
        //		return 0;
        //	}
        //	int length=nums.length;
        //	int count=0;
        //	for (int i = 0; i < length; i++) {
        //		for (int j = i+1; j <length ; j++) {
        //			if(nums[i]>nums[j]){
        //				count++;
        //			}
        //		}
        //	}
        //	return count;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
