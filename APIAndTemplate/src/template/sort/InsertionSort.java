package template.sort;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {9,8,7};
        //int[] arr = {10};
        int[] arr1 = insertionSort1(arr);
        if (arr1 == null) {
            System.out.println("数组为空引用");
        } else {
            for (int arri : arr1) {
                System.out.print(arri + " ");
            }
        }
        System.out.println("===============");
        int[] arr2 = insertionSort1(arr);
        if (arr2 == null) {
            System.out.println("数组为空引用");
        } else {
            for (int arri : arr2) {
                System.out.print(arri + " ");
            }
        }
    }



    // 类似打扑克牌，每次新加入一个牌形成有序排列。
    // 直接插入排序，从无序序列中逐个拿出来一个放入有序序列相应的位置上。

    // 方式1：从小到大。整个数组左边有序右边无序。 有序部分[0,i-1],无序部分[i,length-1]。
    // 将无序序列的第一个拿出来和有序数数组从右向左逐一比较，并放入相应的位置。
    public static int[] insertionSort1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int length = arr.length;

        // n个数要进行（n-1）次插入。下面这个是[0,i-1]有序，[i,length-1]无序
        for (int i = 0; i < length; i++) {
            // arr[i]无序序列的第一个，
            int temp = arr[i];//temp记录待插入的元素，因为后面这个位置i可能会被占用，所以这里采用记忆数组元素的方法

            int j = i;
            // 从左侧有序序列的最右边开始比较，找到待插入位置。
            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];// 将比待插入数大的元素右移
                j--;
            }

            if (j != i) {// 待插入的元素比有序序列的最大值小，插入相应位置
                arr[j] = temp;
            }
        }
        return arr;
    }




    // 方式2：从小到大。整个数组左边无序右边有序。 无序部分[0,i],有序部分[i+1,length-1]。
    // 将无序序列的第一个拿出来和有序数数组从右向左逐一比较，并放入相应的位置。
    public static int[] insertionSort2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int length = arr.length;

        // n个数要进行（n-1）次插入。下面设定无序部分[0,i],有序部分[i+1,length-1]。
        for (int i = length - 1; i >= 1; i--) {
            int temp = arr[i];//temp是要待插入的元素
            int j = i;
            while (j + 1 < length && arr[j] < temp) {
                arr[j] = arr[j + 1];
                j++;
            }
            if (j != i) {
                arr[j] = temp;
            }

        }
        return arr;
    }


}
