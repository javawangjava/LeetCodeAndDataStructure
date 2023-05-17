package template.sort;

public class MaxHeapSort {
    public static void main(String[] args) {
        int[] arr = {10, 4, 9, 25, 30, 2, 25, 9};
        //int[] arr = {10};
        int[] arr1 = maxHeapSort(arr);
        if (arr1 == null) {
            System.out.println("数组为空引用");
        } else {
            for (int arri : arr1) {
                System.out.print(arri + " ");
            }
        }

    }




    public static int[] maxHeapSort(int arr[]) {
        int len = arr.length - 1;
        for (int i = len; i > 0; i--) {
            maxHeap(arr, i);
            //交换 跟节点root 与 最后一个子节点i 的位置
            swap(arr, 0, i);
            //i--无序数组尺寸减少了
        }
        return arr;
    }

    /**构建一个大顶堆（完全二叉树 ）
     * 从  最后一个非叶子节点  开始，若父节点小于子节点，则互换他们两的位置。然后依次从右至左，从下到上进行！
     * 最后一个非叶子节点，它的叶子节点 必定包括了最后一个（叶子）节点，所以 最后一个非叶子节点是 a[（n+1）/2-1]

     * @param arr
     * @param lastIndex 这个数组的最后一个元素
     */
    static void maxHeap(int arr[], int lastIndex) {
        for (int i = (lastIndex + 1) / 2 - 1; i >= 0; i--) {
            //反正 堆排序不稳定，先比较父与左子，大则交换；与右子同理。（不care 左子与右子位置是否变了！）
            if (i * 2 + 1 <= lastIndex && arr[i] < arr[i * 2 + 1]) {
                swap(arr, i, i * 2 + 1);
            }
            if (i * 2 + 2 <= lastIndex && arr[i] < arr[i * 2 + 2]) {
                swap(arr, i, i * 2 + 2);
            }
        }
    }

    private  static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }




    //// 堆排序，后面有时间再研究
    //public int[] sort(int[] arr) {
    //    int len = arr.length;
    //    buildMaxHeap(arr, len);
    //
    //    for (int i = len - 1; i > 0; i--) {
    //        swap(arr, 0, i);
    //        len--;
    //        heapify(arr, 0, len);
    //    }
    //    return arr;
    //}
    //
    //private void buildMaxHeap(int[] arr, int len) {
    //    for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
    //        heapify(arr, i, len);
    //    }
    //}
    //
    //private void heapify(int[] arr, int i, int len) {
    //    int left = 2 * i + 1;
    //    int right = 2 * i + 2;
    //    int largest = i;
    //
    //    if (left < len && arr[left] > arr[largest]) {
    //        largest = left;
    //    }
    //
    //    if (right < len && arr[right] > arr[largest]) {
    //        largest = right;
    //    }
    //
    //    if (largest != i) {
    //        swap(arr, i, largest);
    //        heapify(arr, largest, len);
    //    }
    //}
    //
    //// 交换数组元素
    //private void swap(int[] arr, int i, int j) {
    //    int temp = arr[i];
    //    arr[i] = arr[j];
    //    arr[j] = temp;
    //}

}
