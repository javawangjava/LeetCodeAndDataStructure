package template.sort;

// 计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
// 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。
// 由于用来计数的数组C的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1），这使得计数排序对于数据范围很大的数组，需要大量时间和内存。

// 计数排序的基本思想是先统计数组中每个整数在数组中的出现的次数，然后按照从小到大的顺序将每一个整数按照它出现的次数填到数组中。

public class CountingSort {
    public static void main(String[] args) {

    }


    // counts数组是计数器，用来保存数组待排序数组nums中的元素出现次数。其实就是数组实现哈希表，数组下标就是键，数组值就是对应的值。
    public static int[] countingSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        // 遍历数组找到待排序数组的最大值和最小值
        int min = Integer.MAX_VALUE;// 注意这里将最小值初始化为整数的最大值
        int max = Integer.MIN_VALUE;// 注意这里将最大值初始化为整数的最小值
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // 创建计数器数组，并统计待排序数组中元素的出现次数。计数器保存的是[min,max]范围的元素的出现次数。
        int[] counts = new int[max - min + 1];// 这里为了节省空间，使用了偏移数组。数组下标从0开始，但是数组nums中的元素是从min开始的，所以有偏移
        for (int num : nums) {// num和（num-min）和counts[num-min]一一对应，
            // 数组下标从0开始，所以这里（num-min）相当于做了平移，映射关系是一样的。直接使用num而不是（num-min）可能会越界
            counts[num - min]++;// 记录数组中每一个值为num的元素的出现次数
        }

        int index = 0;// 重建数组的遍历指针,重建数组要从下标为0的部分开始重建
        // 遍历计数器数组元素，根据计数器来重建数组。排序数组中num和（num-min）和counts[num-min]一一对应
        for (int num = min; num <= max; num++) {// 遍历[min,max]中的每一个元素
            while (counts[num - min] > 0) {// 计数器表示与num对应的元素还有
                nums[index] = num;
                index++;
                counts[num - min]--;
            }
        }
        return nums;
    }



}
