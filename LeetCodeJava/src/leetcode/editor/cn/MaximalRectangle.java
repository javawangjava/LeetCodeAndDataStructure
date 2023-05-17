/**
 * <p>给定一个仅包含&nbsp;<code>0</code> 和 <code>1</code> 、大小为 <code>rows x cols</code> 的二维二进制矩阵，找出只包含 <code>1</code>
 * 的最大矩形，并返回其面积。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/14/maximal.jpg" style="width: 402px; height: 322px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * <strong>输出：</strong>6
 * <strong>解释：</strong>最大矩形如上图所示。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>matrix = []
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>matrix = [["0"]]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>matrix = [["1"]]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>matrix = [["0","0"]]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>rows == matrix.length</code></li>
 * <li><code>cols == matrix[0].length</code></li>
 * <li><code>1 &lt;= row, cols &lt;= 200</code></li>
 * <li><code>matrix[i][j]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>动态规划</li><li>矩阵</li><li>单调栈</li></div></div><br><div
 * ><li>👍 1357</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 85
 * 最大矩形
 *
 * @author wangweizhou
 * @date 2022-08-29 23:49:24
 */

public class MaximalRectangle {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MaximalRectangle().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 如果能从矩阵中找出直方图，那么就能通过计算直方图中的最大矩形面积来计算矩阵中的最大矩形面积。
        // 直方图是由排列在同一基线上的相邻柱子组成的图形。由于题目要求矩形中只包含数字1，因此将矩阵中上下相邻的值为1的格子看成直方图中的柱子。
        // 在将矩阵转换成多个直方图之后，就可以计算并比较每个直方图的最大矩形面积，所有直方图中的最大矩形也是整个矩阵中的最大矩形。


        // 数组heights用来记录以某一行作为基线的直方图的每根柱子的高度。
        // 如果矩阵中某个格子的值为0，那么它所在的柱子的高度为0；
        // 如果矩阵中某个格子的值为1，那么它所在的柱子的高度是以上一行作为基线的直方图同一位置的柱子的高度加1。
        // 在得到一个直方图中所有柱子的高度之后，就可以用解决面试题39的方法求得直方图中的最大矩形面积。


        // 解法1： 单调栈+将矩阵转化为直方图

        //public int maximalRectangle(char[][] matrix) {
        //    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        //        return 0;
        //    }
        //    int rowLen = matrix.length;// 行数
        //    int colLen = matrix[0].length;// 列数
        //    int[] heights = new int[colLen];// 每一行的直方图的高度数组。注意这里相当于用了一个循环数组来覆盖
        //    int maxArea = 0;// 最大矩形面积
        //    for (int row = 0; row < rowLen; row++) {// 遍历每一行
        //        for (int col = 0; col < colLen; col++) {// 遍历每一列
        //            // 如果矩阵中某个格子的值为1，那么它所在的柱子的高度是以上一行作为基线的直方图同一位置的柱子的高度加1。
        //            if (matrix[row][col] == '1') {
        //                heights[col]++;
        //            } else {
        //                // 如果矩阵中某个格子的值为0，那么它所在的柱子的高度为0；
        //                heights[col] = 0;
        //            }
        //        }
        //        // 获取截至当前行的直方图中矩形的最大面积
        //        maxArea = Math.max(maxArea, largestRectangleArea(heights));
        //    }
        //    return maxArea;
        //}


        //// 获取直方图中矩形的最大面积
        //private int largestRectangleArea(int[] heights) {
        //    if (heights == null || heights.length == 0) {
        //        return 0;
        //    }
        //    Deque<Integer> stack = new LinkedList<>();// 保存在栈中的直方图的柱子的高度是递增排序的。
        //    // 下标为-1和下标为height.length的位置柱子高度为0。
        //    stack.push(-1);// 这里是为了标识栈中没有参数数组的元素了。所以后续使用栈顶元素是（-1）来检查栈中是否有参数数组的的元素，
        //    // 不然若栈为空时要单独判断，这样代码就复杂了
        //    int maxArea = 0;
        //    for (int i = 0; i < heights.length; i++) {
        //        // 以某根柱子为顶的最大矩形，一定是从该柱子向两侧延伸直到遇到比它矮的柱子，这个最大矩形的高是该柱子的高，最大矩形的宽是两侧比它矮的柱子中间的间隔。
        //        // 如果当前柱子的高度小于位于栈顶的柱子的高度，将位于栈顶的柱子的下标出栈，并且计算以该位于栈顶的柱子为顶【刚出栈的柱子】的最大矩形面积。
        //        // 下面是个循环，所以会计算出以heights[i]为顶的最大矩形面积。
        //        // stack.peek()！=-1表示栈中有参数数组的元素；heights[stack.peek()] >= heights[i]表示栈顶元素大于等于当前元素,注意这里只是访问栈顶元素。
        //        while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
        //            // 注意以栈顶元素为高的矩形的高和宽的获取必须先获取高，然后才能获取矩形的左边界进而获得矩形的宽。
        //            int height = heights[stack.pop()];// 高为栈顶高度heights[stack.peek()]，注意这里是弹出了栈顶元素，而且必须弹出，不然没办法获取次栈顶元素。
        //            // 注意下面这个宽度：i - stack.peek() - 1。这时候最大矩形的左右边界（stack.peek()，i）。
        //            // 因为前面栈顶元素已经弹出了，那么这时候stack.peek()是当while循环条件中栈顶元素没有弹出时，栈中比栈顶元素小的次栈顶元素【栈顶元素左边的次栈顶元素】。
        //            // 也就是区间(栈中位于栈顶元素前面的一根柱子,i)
        //            int width = i - stack.peek() - 1;// 以栈顶柱子为高的矩形宽度
        //            maxArea = Math.max(maxArea, height * width);// 更新最大面积
        //        }
        //        // 执行到这里：stack.peek()==-1||heights[stack.peek()]<heights[i]
        //        // stack.peek()==-1表示栈中没有参数数组的元素；heights[stack.peek()]<heights[i]表示栈顶元素小于当前元素。
        //        stack.push(i);// 栈中的柱子高度升序排列
        //    }
        //
        //    // 执行到这里整个参数数组已经遍历完了，但是栈中可能还有元素，这时候栈中剩余的柱子高度就是升序，而且结束位置的下标为参数数组最大下标【即（height.length-1）位置】
        //    // 那么右边界就是height.length的位置。
        //    while (stack.peek() != -1) {
        //        int height = heights[stack.pop()];
        //        // 这里heights.length可以认为就是在数组heights最右侧外面添加的一个高度为0的格子。假想的，方便处理
        //        int width = heights.length - stack.peek() - 1;
        //        maxArea = Math.max(maxArea, height * width);
        //    }
        //    return maxArea;
        //}


        public int maximalRectangle(char[][] matrix) {
            if(matrix==null||matrix.length==0||matrix[0].length==0){
                return 0;
            }
            int rowLen=matrix.length;
            int colLen=matrix[0].length;
            int[] heights=new int[colLen];
            int maxArea=0;
            for (int row = 0; row < rowLen; row++) {
                for (int col = 0; col < colLen; col++) {
                    if(matrix[row][col]=='1'){
                        heights[col]++;
                    }else {
                        heights[col]=0;
                    }
                }
                maxArea=Math.max(maxArea,maxRectangleArea(heights));
            }
            return maxArea;
        }

        private int maxRectangleArea(int[] heights){
            if(heights==null||heights.length==0){
                return 0;
            }
            int len=heights.length;
            Deque<Integer> stack=new LinkedList<>();
            stack.push(-1);
            int maxArea=0;
            for (int i = 0; i < len; i++) {
                while (stack.peek()!=-1&&heights[stack.peek()]>=heights[i]){
                    int height=heights[stack.pop()];
                    int width=i-stack.peek()-1;
                    maxArea=Math.max(maxArea,width*height);
                }
                stack.push(i);
            }
            while (stack.peek()!=-1){
                int height=heights[stack.pop()];
                int width=len-stack.peek()-1;
                maxArea=Math.max(maxArea,width*height);
            }
            return maxArea;
        }





    }
//leetcode submit region end(Prohibit modification and deletion)

}
