package template;

public class RemoveDuplicate {

    public void removeDuplicate() {

        //// 去重的三种写法
        // 先后移那么就要和前一个比较。先比较那么就要和后一个比较再后移
        int[] nums = {1, 2, 3, 4};
        int left = 0;
        int right = nums.length - 1;


        //去重写法1：
        left++;//先后移那么就要和前一个比较。先比较那么就要和后一个比较再后移
        while (left < right && nums[left] == nums[left - 1]) {//移动到重复元素的最后一个元素
            left++;
        }
        right--;
        while (left < right && nums[right] == nums[right + 1]) {//去重
            right--;
        }


        //去重写法2：  后移动那么就要和后一个先比较
        //下面去重的作用和上面两个while去重的结果一样
        while (left < right && nums[left] == nums[left + 1]) {//移动到重复元素的最后一个元素
            left++;
        }
        left++;//步骤就是从最后一个重复元素移动到下一个不重复元素的位置

        while (left < right && nums[right] == nums[right - 1]) {//去重
            right--;
        }
        right--;



        //去重写法3：
        //借助临时变量，left指针一定会移动到下一个不同于left的位置

        int leftTemp = nums[left];
        while (left < right && leftTemp == nums[left]) {// leftTemp==nums[left] 第一次是本身等于本身，所以left一定会右移
            left++;
        }
        int rightTemp = nums[right];
        while (left < right && rightTemp == nums[right]) {//rightTemp ==nums[right]
            // 第一次是本身等于本身，right一定会左移
            right--;
        }


    }

}
