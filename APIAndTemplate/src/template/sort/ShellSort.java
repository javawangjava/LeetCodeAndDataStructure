package template.sort;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {9,7,8,3,5,2,1,6};
        //int[] arr = {10};
        int[] arr1 = shellSort1(arr);
        if (arr1 == null) {
            System.out.println("数组为空引用");
        } else {
            for (int arri : arr1) {
                System.out.print(arri + " ");
            }
        }

        System.out.println("====================");

        int[] arr2 = shellSort2(arr);
        if (arr2 == null) {
            System.out.println("数组为空引用");
        } else {
            for (int arri : arr2) {
                System.out.print(arri + " ");
            }
        }
    }



    // 希尔排序是插入排序的改进版本，非稳定排序算法
    // 直接插入排序步长是1.
    // 希尔排序是设置不同的步长，在原序列中挑选出间隔步长的子序列【arr[i],arr[i+d],arr[i+2d],】，在步长不变的情况下，将每一个子序列进行排序。
    // 之后步长不断减小为1。排序速度取决于步长的设定。
    // 方式1和方式2思路一样，实现细节有点不一样

    // 方式1：
    public static int[] shellSort1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int length = arr.length;
        for (int step = length / 2; step >= 1; step /= 2) {// 设置步长
            // 下面循环对子序列进行直接插入排序 。子序列[k,k+step,k+2*step,k+3*step,...]。
            // 将子序列简化。设定每个子序列从小到大，子序列左边有序，右边无序。有序部分[0,i-1]，无序部分[i,length-1]
            for (int i = 0; i < length; i++) {
                int temp = arr[i];//arr[i]无序序列的第一个，temp记录待插入的元素。后面移动的话可能会覆盖这个位置，所以要先记忆保留。
                int j = i;
                // 从子序列的左侧有序子序列的最右边开始比较，找到待插入位置
                while (j > temp && temp < arr[j-temp]) {
                    arr[j] = arr[j-temp];// 将比待插入数大的元素右移
                    j -= step;
                }
                // 上面while循环结束条件，j<temp,||temp >= arr[j-temp],结束的时候,j其实就是待插入位置但是要保证j位置不能越界。
                if(j!=i){
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }



    // 方式2：
    public static int[] shellSort2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int length = arr.length;

        for (int step = length / 2; step >= 1; step /= 2) {// 设置步长
            // 下面循环对子序列进行直接插入排序 。子序列[k,k+step,k+2*step,k+3*step,...]。
            // 将子序列简化。设定每个子序列从小到大，子序列左边有序，右边无序。有序部分[0,i-1]，无序部分[i,length-1]
            for (int i = step; i < length; i++) {
                int temp= arr[i];// arr[i]无序序列的第一个，temp记录待插入的元素，
                int j = i - step;// j的开始元素是有序序列的最后一个
                // 从子序列的左侧有序子序列的最右边开始比较，找到待插入位置
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];// 将比待插入数大的元素右移
                    j -= step;
                }
                // 上面while循环结束时j < 0 && arr[j] <= temp,j最终位置是比待插入数小的第一个位置
                arr[j + step] = temp;
            }
        }
        return arr;
    }


}
