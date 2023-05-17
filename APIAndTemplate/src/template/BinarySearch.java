package template;

public class BinarySearch {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int a = binarySearch(arr, 10);
        System.out.println(a);

    }

    //二分查找
    public static int binarySearch(int[] a, int key) {
        return binarySearch0(a, 0, a.length, key);
    }


    // 二分查找算法总是先拿数组中间的数字和k作比较。 二分查找数字k。
    // 如果中间的数字比k大，那么k只有可能出现在数组的前半段，下一轮我们只在数组的前半段查找就可以了。
    // 如果中间的数字比k小，那么k只有可能出现在数组的后半段，下一轮我们只在数组的后半段查找就可以了。
    // 如果中间的数字和k相等，那么k就是要查找的元素。

    private static int binarySearch0(int[] a, int fromIndex, int toIndex, int key) {

        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;//无符号右移比出发快
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        //没找到时返回的是 (-(insertion point) - 1),insertion point指比第一个大于key的位置
        return -low - 1;  // key not found.
    }



    // 我们先分析如何用二分查找算法在数组中找到第一个k。二分查找算法总是先拿数组中间的数字和k作比较。
    // 如果中间的数字比k大，那么k只有可能出现在数组的前半段，下一轮我们只在数组的前半段查找就可以了。
    // 如果中间的数字比k小，那么k只有可能出现在数组的后半段，下一轮我们只在数组的后半段查找就可以了。
    // 如果中间的数字和k相等呢？我们先判断这个数字是不是第一个k。
    // 如果中间数字的前面一个数字不是k,那么此时中间的数字刚好就是第一个k:如果中间数字的前面一个数字也是k,那么第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。
    // 在函数GetFirstK中， 1 如果数组中不包含数字k， 那么返回-1； 如果数组中包含至少 个k， 那么返回第 一 个k在数组中的下标。
    private int getFirstTarget(int[] nums, int left, int right, int target) {
        if (nums == null || nums.length == 0 || left > right) {
            return -1;
        }
        int middleIndex = (left + right) >> 1;
        if (nums[middleIndex] == target) {// 如果中间的数字和k相等呢？我们先判断这个数字是不是第一个k。
            // 如果中间数字的前面一个数字不是k,那么此时中间的数字刚好就是第一个k:如果中间数字的前面一个数字也是k,那么第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。
            if ((middleIndex > 0 && nums[middleIndex - 1] != target) || middleIndex == 0) {
                return middleIndex;
            } else {
                right = middleIndex - 1;
            }
        } else if (nums[middleIndex] > target) {// 如果中间的数字比k大，那么k只有可能出现在数组的前半段，下一轮我们只在数组的前半段查找就可以了。
            right = middleIndex - 1;
        } else {// 如果中间的数字比k小，那么k只有可能出现在数组的后半段，下一轮我们只在数组的后半段查找就可以了。
            left = middleIndex + 1;
        }
        return getFirstTarget(nums, left, right, target);
    }


    // 我们可以用同样的思路在排序数组中找到最后一个k。
    // 如果中间数字比大，那么只能出现在数组的前半段。如果中间数字比小，那么k只能出现在数组的后半段。
    // 如果中间数字等于k呢？我们需要判断这个k是不是最后一个k，也就是中间数字的下一个数字是不是也等于k。
    // 如果下一个数字不是k，则中间数字就是最后一个k：否则下一轮我们还是要在数组的后半段中去查找。

    private int getLastTarget(int[] nums, int left, int right, int target) {
        if (nums == null || nums.length == 0 || left > right) {
            return -1;
        }
        int middleIndex = (left + right) >> 1;
        if (nums[middleIndex] == target) {// 如果中间的数字和k相等呢？我们先判断这个数字是不是第一个k。
            // 如果中间数字的前面一个数字不是k,那么此时中间的数字刚好就是第一个k:如果中间数字的前面一个数字也是k,那么第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。
            if ((middleIndex < nums.length - 1 && nums[middleIndex + 1] != target) || middleIndex == nums.length - 1) {
                return middleIndex;
            } else {
                left = middleIndex + 1;
            }
        } else if (nums[middleIndex] > target) {// 如果中间的数字比k大，那么k只有可能出现在数组的前半段，下一轮我们只在数组的前半段查找就可以了。
            right = middleIndex - 1;
        } else {// 如果中间的数字比k小，那么k只有可能出现在数组的后半段，下一轮我们只在数组的后半段查找就可以了。
            left = middleIndex + 1;
        }
        return getLastTarget(nums, left, right, target);
    }


    // 我们先分析如何用二分查找算法在数组中找到第一个大于等于k。 写法1
    // 二分查找算法总是先拿数组中间的数字和k作比较。
    // 如果中间的数字比k小，那么k只有可能出现在数组的后半段【后半段不会包含中间位置】，下一轮我们只在数组的后半段查找就可以了。

    // 如果中间的数字大于等于k，我们先判断这个数字是不是第一个大于等于k的数字。
    // 如果中间数字的前面一个数字不是大于等于k,那么此时中间的数字刚好就是第一个大于等于k；
    // 如果中间数字的前面一个数字也是大于等于k,那么第一个大于等于k肯定在数组的前半段【前半段不包含中间位置】，下一轮我们仍然需要在数组的前半段查找。

    // 查找第一个大于等于target的位置  这一个从逻辑上更好记忆
    // 有没有等号和到底返回哪一个值可以用数学归纳法，通过不同的实例来归纳总结。

    public int searchInsert3(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (left <= right) {// [left,right] 至少一个元素,mid是向下取整，所以循环必须能够结束，左右边界必须移动或者找到之后要结束循环
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {// 找到大于等于target的位置
                // 判断该位置是不是数组中第一个位置，或者前一个位置的元素小于target。
                if (mid == 0 || nums[mid - 1] < target) {// (mid-1)要判断是否越界
                    return mid;
                }
                // 下面这个其实就是else选项，前一个位置的元素等于target,那么只能在左半段寻找
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return nums.length;
    }



    // 查找第一个大于等于target的位置  写法2
    // 有没有等号和到底返回哪一个值可以用数学归纳法，通过不同的实例来归纳总结。
    public int searchInsert1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;// java的除法是舍弃小数部分，向下取整，所以left<=mid<=right。
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {//不包含mid,包含
                left = mid + 1;
            } else {// nums[mid]>target  不包含mid,包含mid-1
                right = mid - 1;
            }
        }
        // 当数组中没有与target相等的元素，while循环结束时，left指向大于mid指向的数字的第一个位置
        return left;//查找结束如果没有相等值则返回 left，该值为插入位置
    }


    // 查找第一个大于等于target的位置
    // 有没有等号和到底返回哪一个值可以用数学归纳法，通过不同的实例来归纳总结。
    public int searchInsert2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int length = nums.length;
        int left = 0;
        int right = length - 1;


        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }



// 在一个长度为n的数组中查找一个数字，如果逐一扫描数组中的每个数字，那么需要O（n）的时间。
// 如果数组是排序的（通常按照递增的顺序排序），那么可以采用二分查找算法进行优化。可以取出位于数组中间的数字并和目标数字比较。
// 如果中间数字正好等于目标数字，那么就找到了目标数字。
// 如果中间数字大于目标数字，那么只需要查找数组的前半部分，这是因为数组是排序的，后半部分的数字都大于或等于中间数字，所以一定都大于目标数字，也就没有必要再在后半部分查找。
// 如果中间数字小于目标数字，那么接下来只需要查找数组的后半部分，这是因为排序数组的前半部分的数字都小于或等于中间数字，所以一定都小于目标数字，也就没有必要再在前半部分查找。


// 如果排序数组nums中包含目标数字target，那么返回target在数组中的下标；否则返回-1。
// 函数search总是在下标为left和right之间的子数组中查找。
// left是查找范围子数组最左边的下标，初始化为0，right是查找范围子数组最右边的下标，初始化为数组的最后一个下标，因此最初的查找范围为整个数组。
// 当left等于right时，查找范围是长度为1的子数组。长度为1的子数组仍然是一个有效的查找范围，
// 但当left大于right时这两个下标就不能形成一个有效的查找返回，因此while循环的条件是left小于或等于right。
// 在由left和right确定的查找范围内，可以找到位于它们中间的下标mid。
// 如果中间数字刚好等于目标数字target，那么可以返回中间数字的下标mid。
// 如果中间数字大于目标数字，那么只需要在当前查找范围的前半部分查找。
// 由于此时下标为mid的中间数字不等于目标数字，因此可以将right指向当前中间数字的前一个数字，即下标为mid-1的位置，
// 下一轮查找范围（还是下标从left到right的子数组）就是当前查找范围的前半部分。中间数字小于目标数字的情形则刚好相反，接下来只需要在当前查找范围的后半部分查找。
// 因为下标为mid的中间数字不等于目标数字，所以可以将left指向当前中间数字的下一个数字，即下标为mid+1的位置，
// 下一轮查找范围（还是下标从left到right的子数组）就是当前查找范围的后半部分。

// 在数组中查找一个数字原本是一件非常直观的事情，逐一扫描数组中的数字即可。
// 如果面试题强调数组是排序的，要求在排序数组中查找符合某个条件的数字，那么面试官可能是希望应聘者能够应用二分查找算法优化查找的时间效率。
// 数组既可能是整体排序的，也可能是分段排序的，但一旦题目是关于排序数组并且还有查找操作，那么二分查找算法总是值得尝试的。


    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
