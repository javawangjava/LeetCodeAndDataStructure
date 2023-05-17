/**
 * <p>给你两个数组，<code>arr1</code> 和&nbsp;<code>arr2</code>，<code>arr2</code>&nbsp;中的元素各不相同，<code>arr2</code>
 * 中的每个元素都出现在&nbsp;<code>arr1</code>&nbsp;中。</p>
 *
 * <p>对 <code>arr1</code>&nbsp;中的元素进行排序，使 <code>arr1</code> 中项的相对顺序和&nbsp;<code>arr2</code>&nbsp;中的相对顺序相同。未在&nbsp;
 * <code>arr2</code>&nbsp;中出现过的元素需要按照升序放在&nbsp;<code>arr1</code>&nbsp;的末尾。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * <strong>输出：</strong>[2,2,2,1,4,3,3,9,6,7,19]
 * </pre>
 *
 * <p><strong>示例 &nbsp;2:</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
 * <strong>输出：</strong>[22,28,8,6,17,44]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= arr1.length, arr2.length &lt;= 1000</code></li>
 * <li><code>0 &lt;= arr1[i], arr2[i] &lt;= 1000</code></li>
 * <li><code>arr2</code>&nbsp;中的元素&nbsp;<code>arr2[i]</code>&nbsp;&nbsp;<strong>各不相同</strong>&nbsp;</li>
 * <li><code>arr2</code> 中的每个元素&nbsp;<code>arr2[i]</code>&nbsp;都出现在&nbsp;<code>arr1</code>&nbsp;中</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>计数排序</li><li>排序</li></div></div><br><div><li>👍
 * 244</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 1122
 * 数组的相对排序
 * @author wangweizhou
 * @date 2022-11-22 17:07:44
 */

public class RelativeSortArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new RelativeSortArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 计数排序是一种线性时间的整数排序算法。如果数组的长度为n，整数范围（数组中最大整数与最小整数的差值）为k，
        // 对于k远小于n的场景（如对某公司所有员工的年龄排序），那么计数排序的时间复杂度优于其他基于比较的排序算法（如归并排序、快速排序等）。

        // 计数排序  记录出现次数之后重建数组
        // 用数组作为计数器：数组下标就是数组中的元素的数值，数组值是该元素对应的出现次数。其实就是用数组模拟哈希表。键是数字，值是该数字的出现次数.
        // 计数排序的基本思想是先统计待排序数组中每个整数在数组中出现的次数。然后按照从小到大的顺序将每一个整数按照它出现的次数添加到数组中

        //// 解法1：计数排序
        //public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //	// 这里没必要使用长度为1001的数组，只要计数器数组比数组arr1中最大数大就可以。只需要记录【0，数组元素最大值】就可以。要是下限使用arr1的最小值的话就需要偏移
        //	int max=0;
        //	for(int arr:arr1){// 找出数组arr1中最大值
        //		max=Math.max(arr,max);
        //	}
        //	// counts数组类比哈希表。键是数组arr1中的元素，值是对应的元素的出现次数。
        //	int[] counts=new int[max+1];// counts数组用来表示数组arr1中每个元素出现的次数，没出现的数字次数就是0。元素太多就使用数组元素作为多个计数器。
        //	// 遍历数组arr1统计每一个数字的出现次数
        //	for (int num:arr1) {// 这里num是数组arr1数组的元素，遍历arr1数组来统计每一个元素的出现次数
        //		counts[num]++;
        //	}
        //	// 遍历数组arr2，根据arr2中元素的顺序重建arr1和arr2中重复的的元素
        //	int i=0;// 重建数组的遍历指针，初始化为0。
        //	for (int num:arr2) {// num是arr2中的元素
        //		// 按照元素num在arr2中顺序和在arr1中该元素的出现次数重建arr1。
        //		while(counts[num]>0){
        //			arr1[i++]=num;
        //			counts[num]--;
        //		}
        //	}
        //	// 重建arr1中的不重复元素，因为不知到重复数在那个位置结束，可以从下标0开始。因为重复的元素已经重建，
        //	// 如果数组arr1中的数字在数组arr2中没有出现，那么将这些数字按递增的顺序排在后面。所以下面是遍历计数器数组，计数器数组的下标就是升序排列的
        //	for(int num=0;num<counts.length;num++){// 遍历计数器数组来重建在arr1中出现但是没在arr2中出现的元素
        //		// 这里num是计数器数组的下标，计数器数组的下标num可能是数组arr1中的元素
        //		while(counts[num]>0){// 重复数字已经处理过了，个数为0，不会进入内层循环。所以内层循环只会处理不重叠的元素
        //			arr1[i++]=num;
        //			counts[num]--;
        //		}
        //	}
        //	return arr1;
        //}




        //// 解法2：这时计数数组只是处于最大值和最小值之间就可以
        //public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //    // 定义max,min是数组待排序数组arr1的最大值和最小值。
        //    int max = Integer.MIN_VALUE;
        //    int min = Integer.MAX_VALUE;
        //    for (int num : arr1) { // 遍历数组arr1找到待排序数组的最大值和最小值
        //        min = Math.min(min, num);
        //        max = Math.max(max, num);
        //    }
        //    // 创建计数器数组counts，并统计待排序数组arr1中元素的出现次数。计数器保存的是[min,max]范围的元素的出现次数。
        //    int[] counts = new int[max - min + 1];// 这里为了节省空间，使用了偏移数组。数组下标从0开始，但是数组nums中的元素是从min开始的，所以有偏移
        //    for (int num : arr1) {// num和（num-min）和counts[num-min]一一对应，
        //        // 数组下标从0开始，所以这里（num-min）相当于做了平移，映射关系是一样的。直接使用num而不是（num-min）可能会越界
        //        counts[num - min]++;
        //    }
        //    int index = 0;// 重建数组的遍历指针,重建数组要从下标为0的部分开始重建
        //    // 请将数组arr1中的数字按照数组arr2中的数字的相对顺序排序。
        //    for (int num : arr2) {// 遍历arr2中的元素，按照arr2中的元素顺序来重建数组
        //        while (counts[num - min] > 0) {// 当arr2中某元素的出现次数大于0时
        //            arr1[index] = num;
        //            index++;
        //            counts[num - min]--;
        //        }
        //    }
        //    // 如果数组arr1中的数字在数组arr2中没有出现，那么将这些数字按递增的顺序排在后面。
        //    // 所以下面是遍历计数器数组，计数器数组的下标就是升序排列的
        //    for (int num = min; num <= max; num++) {// 这里num是计数器数组的下标，计数器数组的下标num可能是数组arr1中的元素。数组arr1中元素的范围在[min,max]之间
        //        while (counts[num - min] > 0) {// 当计数器数组对应的元素大于0时，则表明数组arr1中的数字在数组arr2中没有出现
        //            arr1[index] = num;
        //            index++;
        //            counts[num - min]--;
        //        }
        //    }
        //    return arr1;
        //}



        //// 解法2：写法2 这时计数数组只是处于最大值和最小值之间就可以
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            int max=Integer.MIN_VALUE;
            int min=Integer.MAX_VALUE;
            for (int i = 0; i < arr1.length; i++) {
                min=Math.min(min,arr1[i]);
                max=Math.max(max,arr1[i]);
            }
            int[] counts=new int[max-min+1];
            for (int i = 0; i < arr1.length ; i++) {
                counts[arr1[i]-min]++;
            }
            int index=0;
            for (int i = 0; i < arr2.length; i++) {
                while (counts[arr2[i]-min]>0){
                    arr1[index]=arr2[i];
                    counts[arr2[i]-min]--;
                    index++;
                }
            }
            for (int i = min; i <=max ; i++) {
                while (counts[i-min]>0){
                    arr1[index]=i;
                    counts[i-min]--;
                    index++;
                }
            }
            return arr1;
        }




        //// 解法2：计数排序
        //// 请将数组arr1中的数字按照数组arr2中的数字的相对顺序排序。
        //// 如果数组arr1中的数字在数组arr2中没有出现，那么将这些数字按递增的顺序排在后面。
        //public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //	// 由于这个题目中的数字在0到1000的范围内，代码用来统计每个数字出现次数的辅助数组counts的长度为1001，
        //	// 计数数组用来统计数组arr1中每一个元素的出现次数,其实就是数组模拟哈希表，数组的下标就是arr1中元素，对应的数组值就是该元素的出现次数
        //	int[] counts=new int[1001];
        //	for(int num:arr1){// 这里num是数组arr1数组的元素，遍历arr1数组来统计每一个元素的出现次数
        //		counts[num]++;
        //	}
        //	int i=0;// 重建数组的遍历指针，初始化为0。
        //	// 请将数组arr1中的数字按照数组arr2中的数字的相对顺序排序。
        //	for(int num:arr2){// 遍历arr2中的元素，按照arr2中的元素顺序来重建数组
        //		while(counts[num]>0){// 当arr2中某元素的出现次数大于0时，
        //			arr1[i]=num;
        //			i++;
        //			counts[num]--;
        //		}
        //	}
        //
        //	// 如果数组arr1中的数字在数组arr2中没有出现，那么将这些数字按递增的顺序排在后面。所以下面是遍历计数器数组，计数器数组的下标就是升序排列的
        //	for(int num=0;num<counts.length;num++){// 遍历计数器数组来重建在arr1中出现但是没在arr2中出现的元素，因为这里不知道数组arr1的元素范围，所以从0开始到计数器长度结束
        //		// 这里num是计数器数组的下标，计数器数组的下标num可能是数组arr1中的元素
        //		while (counts[num]>0){
        //			arr1[i++]=num;
        //			counts[num]--;
        //		}
        //	}
        //	return arr1;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
