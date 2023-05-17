package template.sort;

import java.util.Random;

// 快速排序的基本思想是分治法：
// 排序过程如下：在输入数组中共随机选取一个元素作为中间值（pivot）,然后对数组进行分区（partition）,
// 使所有比中间值小的数据移动到数组的左边，所有比中间值大的数据移动到数组的右边。
// 接下来对中间值左右两侧的子数组用相同的步骤进行排序，直到数组中仅有一个数字为止。
public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {4, 1, 5, 6, 7, 8, 3};
        int[] ans = quickSort(nums);
        for (int num : ans) {
            System.out.print(num);
        }
    }


    public static int[] quickSort(int[] nums) {
        if (nums == null || nums.length == 0) {// 判空
            return null;
        }
        return quickSortFunc(nums, 0, nums.length - 1);
    }


    // 快速排序子数组[left,right]
    private static int[] quickSortFunc(int[] nums, int left, int right) {
        if (left < right) {// 区间有效
            int pivot = partition(nums, left, right);// 在输入数组中共随机选取一个元素作为中间值（pivot）,然后对数组进行分区（partition）
            quickSortFunc(nums, left, pivot - 1);// 递归基准值左侧元素
            quickSortFunc(nums, pivot + 1, right);// 递归基准值右侧元素
        }
        return nums;
    }


    // 快速排序的基本思想是分治法：
    // 从数列中挑出一个元素，称为 "基准"（pivot），然后对数组进行分区（partition）,
    // 使所有比"基准"值小的元素移动到在基准前面，使所有比"基准"值大的元素移动到基准的后面（与"基准"值相同的数可以到任一边）。
    // 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
    // 接下来对中间值左右两侧的子数组用相同的步骤进行排序，直到数组中仅有一个数字为止。
    // 返回值是开始时选取的"基准"（pivot）在分区之后的下标

    // 快速排序算法首先随机生成一个下标，并以该下标对应的值作为中间值进行分区。
    // 快速排序也可以不使用随机选取的中间值，而是始终以某一个固定位置的值作为中间值。（比如头节点或者尾节点）。

    private static int partition(int[] nums, int left, int right) {
        if (nums == null || nums.length == 0 || left > right || left < 0 || left >= nums.length || right < 0 ||
        right >=nums.length) {
            return Integer.MIN_VALUE;
        }
        int pivot = new Random().nextInt(right - left + 1) + left;// 随机选取大小在[left,right]中的 "基准"（pivot）
        swap(nums, pivot, right);// 先将随机选中的"基准"（pivot）交换至数组的尾部
        // prevSmall它始终指向已经发现的最后一个小于"基准"值的数字
        int prevSmall = left - 1;// prevSmall指向上一个比"基准"（pivot）小的元素，刚开始时没有比"基准"值小的数字，所以初始值为区间[left,right]的left左侧第一个。
        // 因为这时候已经将"基准"交换至数组nums最后的一个位置right，所以下面只要遍历[left,right-1]的元素即可
        for (int i = left; i < right; i++) {// 指针i遍历[left,right-1]将遍历到的小于"基准"（pivot）的元素放置在前面
            // 当前元素小于基准，将该元素放置在区间[left,right]的前半部分
            if (nums[i] < nums[right]) {
                prevSmall++;// 因为prevSmall指向上一个比"基准"（pivot）小的元素，所以先要后移至新的位置然后再交换
                swap(nums, i, prevSmall);
            }
        }
        // 前面结束之后，prevSmall指向最后一个比"基准"（pivot）小的位置，将基准交换至合适的位置。那么要将prevSmall后移至第一个不小于基准值的位置
        // 即左边比基准小，右边比基准大
        prevSmall++;
        swap(nums, prevSmall, right);
        return prevSmall;// 基准所在位置
    }


    private static void swap(int[] nums, int index1, int index2) {
        if (nums[index1] != nums[index2]) {
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }


}
