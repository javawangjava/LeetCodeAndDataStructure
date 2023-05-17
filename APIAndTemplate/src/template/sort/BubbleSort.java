package template.sort;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {10, 4, 9, 25, 30, 2,25,9};
        int[] arr2 = BubbleSort.bubbleSort2(arr);

        if(arr2==null){
            System.out.println("数组为空引用");
        }else{
            for (int arri : arr2) {
                System.out.print(arri + " ");
            }
        }
        System.out.println("\n" + "================");
        int[] arr1 = BubbleSort.bubbleSort1(arr);
        if(arr1==null){
            System.out.println("数组为空引用");
        }else{
            for (int arri : arr1) {
                System.out.print(arri + " ");
            }
        }

    }


    // 冒泡排序：从小到大排序，
    // 排序规则：每一轮通过相邻元素的比较，把小的往左边冒（或把大的往右边沉），n个元素经过n-1轮完成最终的排序。

    // 方式一：把大的往右沉，整个数组左边无序右边有序。相邻的两个数中右边的总是大于左边的。
    // 注意每一轮的比较都是从左边无序数组的第一位开始，到有序部分前截止。找出无序数组中最大的加入有序数组。
    public static int[] bubbleSort1(int[] arr){
        if(arr==null||arr.length==0){
            return arr;
        }
        int length=arr.length;
        for (int i = 1; i < length; i++) {// n个数进行（n-1）轮比较，外层循环变量i表示循环的轮数
            // 内层循环找出左边无序数组的最大值。[0，length-i)是无序的，[length-i,len-1]是有序的
            for (int j = 0; j < length-i; j++) {
                // 保证两个相邻数中右边的数大于左边的数值，相邻的两个数大的向后移动。
                // 如果左边的元素比右边的元素大，交换
                if(arr[j]>arr[j+1]){// 注意这里是当前元素和后一个元素进行比较，所以要注意数组下标是否越界。
                    // 和前面的length-i对应，不会保证越界
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        return arr;
    }



    // 方式二：把小的往左边冒，整个数组左边有序右边无序。相邻两个数中总是左边的小
    // 注意，每一轮都是从最右边往左边比较
    public static int[] bubbleSort2(int[] arr){
        if(arr==null||arr.length==0){
            return arr;
        }
        int length=arr.length;
        // n个数进行（n-1）轮比较，外层循环变量i表示循环的轮数
        for (int i = 1; i <length; i++) {
            // 内层循环是找出右边无序数组的最小值，[0，i)是有序的，[i,len-1]是无序的
            for (int j = length-1; j >=i ; j--) {
                // 保证两个相邻数中左边的数小于右边的数值
                // 如果左边的数大于右边的数。交换
                if(arr[j-1]>arr[j]){
                    int temp=arr[j-1];
                    arr[j-1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        return arr;
    }


}
