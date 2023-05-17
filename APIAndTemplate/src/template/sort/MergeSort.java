package template.sort;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {11, 44, 23, 34, 48, 9, 12};
        mergeSort(arr, 0, arr.length - 1);
        //int[] ans = mergeSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }


    // “归并”的含义是将两个或者两个以上的有序表组合成一个新的有序表。
    // 归并排序： 排序数组[left,right]部分
    // 递归形式的2-路归并排序算法是基于分治的。
    // 分解：将含有n个元素的待排序表分解成各含n/2个元素的子表。
    // 合并：合并两个已排序子表得到排序结果

    // 写法1：递归形式的归并排序。归并排序之后保存在原数组中
    // 归并排序： 排序数组[left,right]部分 这个是保存排序的部分，然后将排序的部分放入到原数组对应的位置
    public static void mergeSort(int[] array, int left, int right) {// 双闭区间[left,right]
        if (array == null || array.length <= 1 || left >= right) {// 判空和特殊情况
            return;
        }
        int mid = (left + right) >> 1;// 从中间划分子序列
        mergeSort(array, left, mid);// 对左侧子序列进行递归排序
        mergeSort(array, mid + 1, right);// 对右侧子序列进行递归排序
        mergeSortFunc(array, left, mid, right);// 合并两个有序序列
        //if (left < right) {
        //    mergeSort(array, left, mid);// 对左侧子序列进行递归排序
        //    mergeSort(array, mid + 1, right);// 对右侧子序列进行递归排序
        //    mergeSortFunc(array, left, mid, right);// 合并两个有序序列
        //}
    }


    // merge()的功能是将前后相邻的两个有序表归并为一个有序表的算法。只不过这两个有序表保存在同一顺序表的左右两部分。
    // 合并：将两个有序数组合并，这两个有序数组分别为[left,mid]、[mid+1,right]。
    // 两段有序表A[left,mid]、B[mid+1,right]存放在同一顺序表中相邻的位置上，merge()功能是将两个有序列表合并为一个有序列表。
    // 注意方法没有返回值，所有要将修改的反应在原数组上。
    // 所以下面这个是将合并后的元素先放入临时数组，然后再将临时数组中元素放入原数组array中对应位置。
    private static void mergeSortFunc(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];// 新建一个存储合并后数组的临时数组
        int index = 0;// 临时数组的遍历指针index，注意这里是从下标0开始遍历的。index其实就是合并后数组的遍历指针
        int p1 = left;// 左侧子序列遍历指针
        int p2 = mid + 1;// 右侧子序列遍历指针
        // 把较小的数先移到新数组中
        while (p1 <= mid && p2 <= right) {// 当两个子数组都有元素的时候，遍历比较每个元素大小
            // 合并：合并两个已排序子序列得到排序结果。
            if (array[p1] <= array[p2]) {
                temp[index++] = array[p1++];
            } else {// array[p1] > array[p2]
                temp[index++] = array[p2++];
            }
        }
        // 上面循环执行完毕之后，至少有一个子序列已经遍历完了，也就是下面这两个循环最多只执行一个。
        // 那么就需要将剩余的元素添加到保存合并后序列的数组中
        while (p1 <= mid) { //当左边的数组没有遍历完成时，直接将剩余元素加入到临时数组中
            temp[index++] = array[p1++];
        }
        while (p2 <= right) { //当右边的数组没有遍历完成时，直接将剩余元素加入到临时数组中
            temp[index++] = array[p2++];
        }

        // 方法没有返回值，需要把排序之后的部分替换原数组的对应部分。把最终的排序的结果复制给原数组
        for (index = 0; index < temp.length; index++) {
            array[left + index] = temp[index];
        }
    }




    //// 写法2：递归形式的归并排序。  这个直接使用副本保存，将排序后的保存在原数组中
    // 归并排序： 排序数组[left,right]部分
    //public static void mergeSort2(int[] array, int left, int right) {
    //    if (array == null || array.length <= 1) {
    //        return;
    //    }
    //    int mid =(left+right) >> 1;// 从中间划分子序列
    //    if(left<right){
    //        mergeSort2(array, left, mid);// 对左侧子序列进行递归排序
    //        mergeSort2(array, mid + 1, right);// 对右侧子序列进行递归排序
    //        merge2(array, left, mid, right);//合并两个有序序列
    //    }
    //}
    //
    //
    //// merge()的功能是将前后相邻的两个有序表归并为一个有序表的算法。
    //// 合并：将两个有序数组合并，这两个有序数组分别为[left,mid]、[mid+1,right]。
    //// 两段有序表A[left,mid]、B[mid+1,right]存放在同一顺序表中相邻的位置上，merge()功能是将两个有序列表合并为一个有序列表。
    //// 注意方法没有返回值，所有要将修改的反应在原数组上。所以下面采用的是对原数组的副本进行归并排序，将合并后的数组放入原数组。
    //private static void merge2(int[] arr, int left, int mid, int right) {
    //    int[] temp = Arrays.copyOf(arr,arr.length);// 创建原数组副本
    //    int i = left;// 原数组的遍历指针，注意这里是直接将修改的部分反应在原数组上，所以这里下标初始值是left。
    //    int p1 = left;// 副本数组左侧子序列遍历指针
    //    int p2 = mid + 1;// 副本数组右侧子序列遍历指针
    //    // 把较小的数先移到新数组中
    //    while (p1 <= mid && p2 <= right) {// 两个小容量的有序表都没有遍历完
    //        if(temp[p1] < temp[p2]){
    //            arr[i++] = temp[p1++];
    //        }else{
    //            arr[i++] = temp[p2++];
    //        }
    //    }
    //
    //    // 上面的循环退出后，把剩余的元素依次填入到原数组中，以下两个while只有一个会执行
    //    while (p1 <= mid) { // 把左边剩余的数移入数组
    //        arr[i++] = temp[p1++];
    //    }
    //    while (p2 <= right) { // 把右边边剩余的数移入数组
    //        arr[i++] = temp[p2++];
    //    }
    //}





    // 写法3：
    //// 归并排序需要创建一个和输入数组大小相同的数组，用来保存合并两个排序子数组的结果。
    //// 数组src用来存放合并之前的数字，数组dst用来保存合并之后的数字。
    //// 每次在完成合并所有长度为n的子数组之后开始新一轮合并长度为2n的子数组之前，交换两个数组。
    //// 假设某一时刻准备合并数组src中从下标start开始的两个长度为seg的子数组，第1个子数组的起始下标是start，结束下标是start+seg-1；
    //// 第2个子数组的起始下标是start+seg，结束下标是start+seg*2-1。
    //// 变量i、j是分别指向数组src中两个子数组的下标，它们从左到右扫描两个子数组，变量k是指向数组dst的下标。
    //// 每次从数组src的两个子数组中选择将较小的数字写入数组dst中，最终数组dst中下标从start到start+seg*2-1的子数组就是排序的。

    //public static int[] mergeSort4(int[] nums) {
    //    if (nums == null || nums.length == 0) {
    //        return new int[0];
    //    }
    //    int len = nums.length;
    //    int[] src = nums;// 数组src用来存放合并之前的数字
    //    int[] dst = new int[len];// 数组dst用来保存合并之后的数字。
    //    for (int seg = 1; seg < len; seg += seg) {// seg表示每次归并排序的相邻的子数组的长度，
    //        // 假设某一时刻准备合并数组src中从下标start开始的两个长度为seg的子数组，第1个子数组的起始下标是start，结束下标是start+seg-1；
    //        // 第2个子数组的起始下标是start+seg，结束下标是start+seg*2-1。
    //        // 两个子数组双闭区间[start,start+seg-1]、[start+seg,start+seg*2-1]
    //        // 或者表示成左闭右开区间[start,start+seg)、[start+seg,start+seg*2)。
    //        for (int start = 0; start < len; start += seg * 2) {
    //            int mid = Math.min(start + seg, len);// 第二个子数组的起始下标
    //            int end = Math.min(start + seg * 2, len);// 第二个子数组的结束下标
    //            // 变量i、j是分别指向数组src中两个子数组的下标，它们从左到右扫描两个子数组，变量k是指向数组dst的下标。
    //            int i = start;
    //            int j = mid;
    //            int k = start;
    //            // 每次从数组src的两个子数组中选择将较小的数字写入数组dst中，最终数组dst中下标从start到start+seg*2-1的子数组就是排序的。
    //            //while (i < mid || j < end) {// 数组中选较小的数，那么下标不能越界
    //            //    // 每次归并时，第二个子数组的长度可能比第一个子数组的长度小，两个子数组不一定时相等的。
    //            //    // 第二个子数组下标到达子数组末尾：j == end
    //            //    // 第一个子数组的遍历指针没有到达末尾，且第一个子数组遍历到的元素小于第二个子数组遍历到的元素：i < mid && src[i] < src[j]
    //            //    if (j == end || (i < mid && src[i] < src[j])) {// 这个写法没有下面分成三部分那个好理解
    //            //        dst[k++] = src[i++];
    //            //    } else {
    //            //        dst[k++] = src[j++];
    //            //    }
    //            //}
    //            while (i < mid && j < end) {
    //                if (src[i] < src[j]) {
    //                    dst[k++] = src[i++];
    //                } else {
    //                    dst[k++] = src[j++];
    //                }
    //            }
    //            while (i < mid) {
    //                dst[k++] = src[i++];
    //            }
    //            while (j < end) {
    //                dst[k++] = src[j++];
    //            }
    //
    //        }
    //        // 每次在完成合并所有长度为n的子数组之后开始新一轮合并长度为2n的子数组之前，交换两个数组。完成一次归并，两个数组会交换地位。
    //        int[] temp = src;
    //        src = dst;
    //        dst = temp;
    //    }
    //    return src;
    //}





    //// 写法5：递归实现归并排序   用一个原数组的副本来辅助合并
    //// 归并排序也可以用递归的代码实现。为了排序长度为n的数组，只需要排序两个长度为n/2的子数组，然后合并两个排序的子数组即可。
    //// 排序长度为n/2的子数组和排序长度为n的数组是同一个问题，可以递归调用同一个函数解决。
    //public static int[] mergeSort5(int[] nums) {
    //    if (nums == null || nums.length == 0) {
    //        return new int[0];
    //    }
    //    int[] dst=new int[nums.length];
    //    dst=Arrays.copyOf(nums,nums.length);// 这里是复制原数组的副本
    //    mergeSort5Func(nums,dst,0,nums.length);// 这里调用的是左开右闭的区间
    //    return dst;
    //}
    //
    //
    //// 这里是归并排序数组[start,end)。左闭右开。
    //private static void mergeSort5Func(int[] src, int[] dst, int start, int end){
    //     if(start+1>=end){
    //         return;
    //     }
    //     int mid=(start+end)>>1;
    //// 每次在完成合并所有长度为n的子数组之后开始新一轮合并长度为2n的子数组之前，交换两个数组。完成一次归并，两个数组会交换地位。
    //     mergeSort5Func(dst,src,start,mid);
    //     mergeSort5Func(dst,src,mid,end);
    //
    //     int i=start,j=mid,k=start;
    //     while (i<mid||j<end){// 这个写法没有下面分成三部分那个好理解
    //         if(j==end||(i<mid&&src[i]<src[j])){
    //             dst[k++]=src[i++];
    //         }else {
    //             dst[k++]=src[j++];
    //         }
    //     }
    //}





    //// 写法6：递归实现归并排序   用一个原数组的副本来辅助合并
    //// 归并排序需要创建一个和输入数组大小相同的数组，用来保存合并两个排序子数组的结果。
    //// 数组src用来存放合并之前的数字，数组dst用来保存合并之后的数字。
    //// 每次在完成合并所有长度为n的子数组之后开始新一轮合并长度为2n的子数组之前，交换两个数组。
    //public static int[] mergeSort6(int[] nums) {
    //    if (nums == null || nums.length == 0) {
    //        return new int[0];
    //    }
    //    int[] dst = Arrays.copyOf(nums, nums.length);
    //    mergeSort6Func(nums, dst, 0, nums.length - 1);
    //    return dst;
    //}
    //
    //
    //// 双闭区间[left,right]
    //// 第一个参数src是合并前的参数数组，第二个参数dst是合并后的数组，[left,right]是合并前数组src的子数组。
    //private static void mergeSort6Func(int[] src, int[] dst, int left, int right) {
    //    if (left >= right) {// 只有一个元素或者没有元素
    //        return;
    //    }
    //    int mid = (left + right) >> 1;// 找出区间的中间节点
    //    // 每次在完成合并所有长度为n的子数组之后开始新一轮合并长度为2n的子数组之前，交换两个数组。
    //    mergeSort6Func(dst, src, left, mid);// 对左侧子序列进行递归排序
    //    mergeSort6Func(dst, src, mid + 1, right);// 对右侧子序列进行递归排序
    //    // 下面是合并两个有序序列
    //    int p1 = left;// 左侧子序列遍历指针p1
    //    int p2 = mid + 1;// 右侧子序列遍历指针p2
    //    int k = left;// 合并后数组的遍历指针k，注意这里是从下标left开始遍历的，也就是将合并后的元素直接放入合并后数组的对应位置
    //    while (p1 <= mid && p2 <= right) {// 两个小容量的有序表都没有遍历完，把较小先放入对应的位置
    //        if (src[p1] < src[p2]) {
    //            dst[k++] = src[p1++];
    //        } else {
    //            dst[k++] = src[p2++];
    //        }
    //    }
    //    // 上面的循环退出后，把剩余的元素依次填入到dst中，以下两个while只有一个会执行
    //    while (p1 <= mid) {// 把左边小容量有序表剩余的数移入数组
    //        dst[k++] = src[p1++];
    //    }
    //    while (p2 <= right) {// 把右边小容量有序表剩余的数移入数组
    //        dst[k++] = src[p2++];
    //    }
    //}



}
