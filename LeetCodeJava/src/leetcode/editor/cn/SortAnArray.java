/**
给你一个整数数组 nums，请你将该数组升序排列。 

 

 
 

 示例 1： 

 
输入：nums = [5,2,3,1]
输出：[1,2,3,5]
 

 示例 2： 

 
输入：nums = [5,1,1,2,0,0]
输出：[0,0,1,1,2,5]
 

 

 提示： 

 
 1 <= nums.length <= 5 * 10⁴ 
 -5 * 10⁴ <= nums[i] <= 5 * 10⁴ 
 

 Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序 👍 785 👎 0

*/

package leetcode.editor.cn;

import java.util.Random;

/**
 * 912
 * 排序数组
 * @author wangweizhou
 * @date 2023-02-27 17:24:53
 */
public class SortAnArray{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new SortAnArray().new Solution();
		 int[] nums={5,2,3,1};
		 solution.sortArray(nums);

	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


	//// 解法1：调用系统Api。
    //public int[] sortArray(int[] nums) {
	//	if(nums==null||nums.length==0){
	//		return new int[0];
	//	}
	//	Arrays.sort(nums);
	//	return nums;
    //}
	//



	//// 冒泡排序   费时
	//public int[] sortArray(int[] nums) {
	//	if(nums==null||nums.length==0){
	//		return new int[0];
	//	}
	//	int len=nums.length;
	//
	//	for (int i = 1; i < len; i++) {
	//		for (int j = 0; j < len-i; j++) {
	//			if(nums[j]>nums[j+1]){
	//				int temp=nums[j];
	//				nums[j]=nums[j+1];
	//				nums[j+1]=temp;
	//			}
	//		}
	//	}
	//	return nums;
	//}




	//// 计数排序
	//public int[] sortArray(int[] nums) {
	//	if (nums == null || nums.length == 0) {
	//		return new int[0];
	//	}
	//	int max=Integer.MIN_VALUE;
	//	int min=Integer.MAX_VALUE;
	//	for(int num:nums){
	//		min=Math.min(min,num);
	//		max=Math.max(max,num);
	//	}
	//	int[] counts=new int[max-min+1];
	//	for(int num:nums){
	//		counts[num-min]++;
	//	}
	//	int index=0;
	//	for (int i = min; i <=max ; i++) {
	//		while (counts[i-min]>0){
	//			nums[index]=i;
	//			counts[i-min]--;
	//			index++;
	//		}
	//	}
	//	return nums;
	//}



	//// 归并排序
	//public int[] sortArray(int[] nums) {
	//	if (nums == null || nums.length == 0) {
	//		return new int[0];
	//	}
	//	mergeSort(nums,0,nums.length-1);
	//	return nums;
	//}
	//
	//private void mergeSort(int[] nums, int left, int right){
	//	if(nums ==null|| nums.length<1||left>=right){
	//		return;
	//	}
	//	int mid=(left+right)>>1;
	//	mergeSort(nums,left,mid);
	//	mergeSort(nums,mid+1,right);
	//	merge(nums,left,mid,right);
	//}
	//
	//private void merge(int[] nums,int left,int mid,int right){
	//	int[] temp=new int[right-left+1];
	//	int index=0;
	//	int p1=left;
	//	int p2=mid+1;
	//	while (p1<=mid&&p2<=right) {
	//		if (nums[p1] <= nums[p2]) {
	//			temp[index++] = nums[p1++];
	//		} else {
	//			temp[index++] = nums[p2++];
	//		}
	//	}
	//	while (p1<=mid){
	//		temp[index++] = nums[p1++];
	//	}
	//	while (p2<=right){
	//		temp[index++] = nums[p2++];
	//	}
	//	for (int i = 0; i < temp.length; i++) {
	//		nums[left+i]=temp[i];
	//	}
	//}


	// 快排
	public int[] sortArray(int[] nums) {
		if (nums == null || nums.length == 0) {
			return new int[0];
		}
		quickSort(nums,0,nums.length-1);
		return nums;
	}


	private void quickSort(int[] nums,int left,int right){
		if(left>=right){
			return;
		}
		int pivot=partition(nums,left,right);
		quickSort(nums,left,pivot);
		quickSort(nums,pivot+1,right);
	}


	private int partition(int[] nums,int left,int right){
		int random=new Random().nextInt(right-left+1)+left;
		swap(nums,random,right);
		int prevSamll=left-1;
		for (int i = left; i <right ; i++) {
			if(nums[i]<nums[right]){
				prevSamll++;
				swap(nums,prevSamll,i);
			}
		}
		prevSamll++;
		swap(nums,prevSamll,right);
		return prevSamll;
	}


	private void swap(int[] nums,int i,int j){
		if(nums[i]!=nums[j]){
			int temp=nums[i];
			nums[i]=nums[j];
			nums[j]=temp;
		}
	}












}
//leetcode submit region end(Prohibit modification and deletion)

}
