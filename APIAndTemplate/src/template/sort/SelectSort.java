package template.sort;

public class SelectSort {

    public static void main(String[] args) {
        //int[] arr = {10, 4, 9, 25, 30, 2, 25, 9};
        int[] arr = {10};
        int[] arr1 = selectSort1(arr);
        if(arr1==null){
            System.out.println("数组为空引用");
        }else{
            for (int arri : arr1) {
                System.out.print(arri + " ");
            }
        }
        System.out.println("================");
        int[] arr2 = selectSort2(arr);
        if(arr2==null){
            System.out.println("数组为空引用");
        }else{
            for (int arri : arr2) {
                System.out.print(arri + " ");
            }
        }

    }

    // 选择排序：从小到大。
    // 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
    // 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。


    // 方式1：从小到大。整个数组左边有序右边无序。 有序部分[0,i-1],无序部分[i,length-1]。
    // 每次从无序数组中找出最小的元素，然后将该元素与无序部分的第一个数【有序部分下一个】进行交换。
    // 对于数组记录数组索引比数组元素要方便。
    public static int[] selectSort1(int[] arr){
        if(arr==null||arr.length==0){
            return arr;
        }
        int length=arr.length;

        // 整个数组左边有序右边无序。 有序部分[0,i-1],无序部分[i,length-1]。
        // n个数要进行（n-1）轮比较
        for (int i = 0; i < length-1; i++) {
            int minIndex=i;// minIndex记录无序部分的最小值下标
            // 找出未排序数组中的最小值索引。
            for (int j = i+1; j < length; j++) {
                // 当当前元素比记录的最小值小，那么更新最小值
                if(arr[minIndex]>arr[j]){
                    minIndex=j;
                }
            }

            // 判断无序序列最小值是否是刚开始设定的位置，不是就交换。是的话不用交换。
            if(minIndex!=i){
                int temp=arr[i];
                arr[i]=arr[minIndex];
                arr[minIndex]=temp;
            }
        }
        return arr;
    }


    // 方式2：从小到大。整个数组左边无序右边有序。 无序部分[0,i],有序部分[i+1,length-1]。
    public static int[] selectSort2(int[] arr) {
        if(arr==null||arr.length==0){
            return arr;
        }
        int length=arr.length;

        // 整个数组左边无序右边有序。 无序部分[0,i],有序部分[i+1,length-1]。
        for (int i = length-1; i >=0; i--) {
            int maxIndex=i;//maxIndex记录无序序列的最大下标
            // 找出左边无序序列中的最大值索引
            for (int j = 0; j <i ; j++) {
                // 当当前元素比记录的最大值大，更新最大值
                if(arr[j]>arr[maxIndex]){
                    maxIndex=j;
                }
            }

            // 判断无序序列的最大值是否是刚开始假定的位置，不是就交换刚开始假定为位置和无序序列最大值的位置。
            if(maxIndex!=i){
                int temp=arr[i];
                arr[i]=arr[maxIndex];
                arr[maxIndex]=temp;
            }
        }
        return arr;
    }
}
