/**
<p>编写一个高效的算法来判断 <code>m x n</code> 矩阵中，是否存在一个目标值。该矩阵具有如下特性：</p>

<ul>
	<li>每行中的整数从左到右按升序排列。</li>
	<li>每行的第一个整数大于前一行的最后一个整数。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/05/mat.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/mat2.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= m, n <= 100</code></li>
	<li><code>-10<sup>4</sup> <= matrix[i][j], target <= 10<sup>4</sup></code></li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>矩阵</li></div></div><br><div><li>👍 706</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 74
 * 搜索二维矩阵
 * @author wangweizhou
 * @date 2022-08-31 14:06:56
 */

public class SearchA2dMatrix{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new SearchA2dMatrix().new Solution();
		 //int[][] matrix={{1,3,5,7},{10,11,16,20},{23,30,34,60}};
		 int[][] matrix={{1},{3}};
		 boolean ans=solution.searchMatrix(matrix,3);
		 System.out.println(ans);

	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


	/*

	//	 解法1：一次二分查找
	// 将整个二维数组看做一个一维数组使用二分查找,这个的中间下标要数学归纳法找规律
    public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix==null||matrix.length==0||matrix[0].length==0){
			return false;
		}

		int rowLen=matrix.length;// 行数
		int colLen=matrix[0].length;// 列数
		int left=0;
		int right=rowLen*colLen-1;
		while(left<=right){// 因为最后要找到，那么两个指针指向同一个元素之后要判断
			int mid=(left+right)/2;
			// 中间元素在二维数组中的表示，数学归纳法 mid/colLen向下取整，数组下标从0开始，刚好得到行数，mid%colLen得到余数，得到列数
			int midVal=matrix[mid/colLen][mid%colLen];
			if(midVal<target){
				left=mid+1;
			}else if(midVal>target){
				right=mid-1;
			}else{
				return true;
			}
		}
		return false;
    }
	*/




	//// 解法3: 坐标轴法搜索二维矩阵
	//// 二维数组从左往右递增，从上往下递增，所以从左上到右下，或者右下到左上搜索二维数组
	//// 本解法采用的是从左上到右下去搜索， 行数会增大，列数会减小
	//public boolean searchMatrix(int[][] matrix, int target) {
	//	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
	//		return false;
	//	}
	//	int rowLen = matrix.length;
	//	int colLen = matrix[0].length;
	//	int row = 0;
	//	int col = colLen - 1;
	//	// 从右上向左下，行数越来越大，列数越来越小，所以行数上限，列数下限
	//	while (row < rowLen && 0 <= col) {// 每次减掉一行或者一列，所以这里写成单层循环
	//		if (matrix[row][col] == target) {
	//			return true;
	//		} else if (matrix[row][col] > target) {// 如果 target 的值小于当前值，那么就向左走
	//			//每一列的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 y 列的元素都是严格大于 target 的，因此我们可以将它们全部忽略，即将 y 减少 1
	//			col--;
	//		} else {// 如果 target 的值大于当前值，那么就向下走
	//			//每一行的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 x 行的元素都是严格小于 target 的，因此我们可以将它们全部忽略，即将 x 增加 1。
	//			row++;
	//		}
	//	}
	//	return false;
	//}





	//
	// 两次二分查找  一次二分查找目标值可能的所在行，确定所在行之后二分查找目标值是否在所在行即可
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix==null||matrix.length==0){
			return false;
		}
		int rowLen=matrix.length;
		int colLen=matrix[0].length;

		int left=0;
		int right=rowLen-1;
		while (left<=right){
			int mid=(left+right)>>1;
			if(matrix[mid][0]==target){
				return true;
			}else if(matrix[mid][0]>target){
				right=mid-1;
			}else if(matrix[mid][0]<target){
				if(target<=matrix[mid][colLen-1]){
					int[] nums=new int[colLen];
					for (int i = 0; i < colLen; i++) {
						nums[i]=matrix[mid][i];
					}
					return findTarget(nums,target);
				}else {
					left=mid+1;
				}
			}
		}
		return false;
	}

	private boolean findTarget(int[] nums,int target){
		int left=0;
		int right=nums.length-1;
		while (left<=right){
			int mid=(left+right)>>1;
			if(nums[mid]==target){
				return true;
			}else if(nums[mid]>target){
				right=mid-1;
			}else {
				left=mid+1;
			}
		}
		return false;
	}



	/*
	// 解法2： 两次二分查找    一次二分查找确定所在的行，一次二分查找在所在行二分查找
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {// 判空
			return false;
		}
		int rowIndex=binarySearchColumn(matrix,target);// 查找所在行
		return binarySearch(matrix[rowIndex],target);// 在确定的所在行查找
	}


	// 在二维数组中确定目标值所在的行，搜索二维数组的第一列
	private int binarySearchColumn(int[][]matrix,int target){
		int left=0;
		int right=matrix.length-1;
		while(left<right){// 若left==right时，目标行就是left或者right
			int mid=(left+right)/2;
			if(matrix[mid][0]==target){//该元素就是目标值，直接找到所在行
				return mid;
			}else if(matrix[mid][0]>target){// 该元素大于目标值，一定在[left,mid-1]行里面找
				right=mid-1;
			}else if(matrix[mid][0]<target){// 该元素小于目标值，一定在[mid,,right]行里面继续找
				if(mid+1<=right&&matrix[mid+1][0]>target){// 若下一行第一个大于目标值，那目标行就是这一行
					left=mid;
					break;
				}else if(mid+1<=right&&matrix[mid+1][0]<=target){//若下一行第一个小于目标值，那么在[mid+1,right]里面继续找
					left=mid+1;
				}
			}
		}
		return left;
	}


	private boolean binarySearch(int[] nums,int target){
		if(nums==null||nums.length==0){
			return false;
		}
		int left=0;
		int right=nums.length-1;
		while(left<=right){
			int mid=(left+right)/2;
			if(nums[mid]==target){
				return true;
			}else if(nums[mid]>target){
				right=mid-1;
			}else{
				left=mid+1;
			}
		}
		return false;
	}
*/




}
//leetcode submit region end(Prohibit modification and deletion)

}

