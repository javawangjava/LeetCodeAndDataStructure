/**
 * <p>编写一个高效的算法来搜索&nbsp;<code><em>m</em>&nbsp;x&nbsp;<em>n</em></code>&nbsp;矩阵 <code>matrix</code> 中的一个目标值
 * <code>target</code> 。该矩阵具有以下特性：</p>
 *
 * <ul>
 * <li>每行的元素从左到右升序排列。</li>
 * <li>每列的元素从上到下升序排列。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><b>示例 1：</b></p>
 * <img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/searchgrid2.jpg" />
 * <pre>
 * <b>输入：</b>matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * <b>输出：</b>true
 * </pre>
 *
 * <p><b>示例 2：</b></p>
 * <img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/searchgrid.jpg" />
 * <pre>
 * <b>输入：</b>matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * <b>输出：</b>false
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == matrix.length</code></li>
 * <li><code>n == matrix[i].length</code></li>
 * <li><code>1 &lt;= n, m &lt;= 300</code></li>
 * <li><code>-10<sup>9</sup>&nbsp;&lt;= matrix[i][j] &lt;= 10<sup>9</sup></code></li>
 * <li>每行的所有元素从左到右升序排列</li>
 * <li>每列的所有元素从上到下升序排列</li>
 * <li><code>-10<sup>9</sup>&nbsp;&lt;= target &lt;= 10<sup>9</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>分治</li><li>矩阵</li></div></div><br><div><li>👍
 * 1099</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 240
 * 搜索二维矩阵 II
 *
 * @author wangweizhou
 * @date 2022-08-19 16:01:50
 */

public class SearchA2dMatrixIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new SearchA2dMatrixIi().new Solution();
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23,
                26, 30}};
        boolean ans=solution.searchMatrix(matrix, 20);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法2： 右上到左下
        // 从右上角开始搜索当前位置与左下角的矩形或者从左下角开始搜索当前位置与右上角的矩形
        // 若从右上角开始搜索当前位置与左下角的矩形
        // 把 target 和当前值比较。如果 target 的值大于当前值，那么就向下走。
        // 如果 target 的值小于当前值，那么就向左走。如果相等的话，直接返回 true 。

        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            int rowLen = matrix.length;
            int colLen = matrix[0].length;
            int row = 0;
            int col = colLen - 1;
            // 从右上向左下，行数越来越大，列数越来越小，所以行数上限，列数下限
            while (row < rowLen && 0 <= col) {// 每次减掉一行或者一列，所以这里写成单层循环
                if (matrix[row][col] == target) {
                    return true;
                } else if (matrix[row][col] > target) {// 如果 target 的值小于当前值，那么就向左走
                    //每一列的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 y 列的元素都是严格大于 target 的，因此我们可以将它们全部忽略，即将 y 减少 1
                    col--;
                } else {// 如果 target 的值大于当前值，那么就向下走
                    //每一行的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 x 行的元素都是严格小于 target 的，因此我们可以将它们全部忽略，即将 x 增加 1。
                    row++;
                }
            }
            return false;
        }





        //// 解法2： 写法2，对角线扫描  左下到右上
        //public boolean searchMatrix(int[][] matrix, int target) {
        //    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        //        return false;
        //    }
        //    int rowLen = matrix.length;
        //    int colLen = matrix[0].length;
        //    int row = rowLen - 1;
        //    int col = 0;
        //    // 从左下到右上
        //    while (row >= 0 && col < colLen) {
        //        if (matrix[row][col] == target) {
        //            return true;
        //        } else if (matrix[row][col] < target) {
        //            col++;
        //        } else {
        //            row--;
        //        }
        //    }
        //    return false;
        //}





	/*

	//	解法1：对矩阵的每一行进行二分查找
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		int rowLen=matrix.length;
		int colLen=matrix[0].length;
		for(int row=0;row<rowLen;row++){
			if (matrix[row][0] > target) {// 某一行的第一个元素大于了 target ，当前行和后边的所有行都不用考虑了，直接返回 false
				break;
			}
			if(matrix[row][colLen - 1] < target){//某一行的最后一个元素小于了 target ，当前行就不用考虑了，换下一行
				continue;
			}
			if(binarySearch(matrix[row],target)){
				return true;
			}
		}
		return false;
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
