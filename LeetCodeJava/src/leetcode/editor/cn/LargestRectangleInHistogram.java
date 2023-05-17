/**
 * <p>给定 <em>n</em> 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。</p>
 *
 * <p>求在该柱状图中，能够勾勒出来的矩形的最大面积。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg" /></p>
 *
 * <pre>
 * <strong>输入：</strong>heights = [2,1,5,6,2,3]
 * <strong>输出：</strong>10
 * <strong>解释：</strong>最大的矩形为图中红色区域，面积为 10
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/01/04/histogram-1.jpg" /></p>
 *
 * <pre>
 * <strong>输入：</strong> heights = [2,4]
 * <b>输出：</b> 4</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= heights.length <=10<sup>5</sup></code></li>
 * <li><code>0 <= heights[i] <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>单调栈</li></div></div><br><div><li>👍 2237</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 84
 * 柱状图中最大的矩形
 *
 * @author wangweizhou
 * @date 2022-11-15 16:46:23
 */

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new LargestRectangleInHistogram().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 矩形的面积等于宽乘以高，因此只要能确定每个矩形的宽和高，就能计算它的面积。
        // 如果直方图中一个矩形从下标为i的柱子开始，到下标为j的柱子结束，那么这两根柱子之间的矩形（含两端的柱子）的宽是j-i+1。
        // 处理双闭区间的长度[left，right]:right-left+1；
        // 处理双开区间的长度（left，right）:right-left-1；
        // 处理左闭右开或者左开右闭的区间的长度[left，right),(left，right]:right-left。

        //
        // 单调栈：用一个栈保存直方图的柱子，栈中保存的柱子高度是递增排序的【注意单调栈中保存的是柱子下标】。给柱子两侧增加两个位置：下标为-1和下标为height.length的位置柱子高度为0。
        // 参数是数组：矩形的宽度需要知道矩形左右两个边界的下标，这样才能获得矩形的宽度。矩形的高可以通过数组下标获得矩形的高。
        // 以某根柱子为顶的最大矩形，一定是从该柱子向两侧延伸直到遇到比它矮的柱子，那么这个最大矩形的高是该柱子的高，最大矩形的宽是两侧比它矮的柱子中间的间隔。
        // 如果当前扫描到的柱子的高小于位于栈顶的柱子的高，那么将位于栈顶的柱子的下标出栈，并且计算以该位于栈顶的柱子为顶的最大矩形面积。
        // 由于保存在栈中的柱子的高度是递增排序的，因此栈中位于栈顶前面的一根柱子一定比位于栈顶的柱子矮，于是很容易就能找到位于栈顶的柱子两侧比它矮的柱子。
        // 为了方便计算矩形的宽度，栈中保存的是柱子在数组中的下标，可以根据下标得到柱子的高度。
        // 注意以栈顶元素为高的矩形的高和宽的获取必须先获取高，然后才能获取矩形的左边界进而获得矩形的宽。

        // 这种解法的基本思想是确保保存在栈中的直方图的柱子的高度是递增排序的。假设从左到右逐一扫描数组中的每根柱子。
        // 如果当前柱子的高度大于位于栈顶的柱子的高度，那么将该柱子的下标入栈；否则，将位于栈顶的柱子的下标出栈，并且计算以位于栈顶的柱子为顶的最大矩形面积。


        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length == 0) {
                return 0;
            }
            int len=heights.length;
            Deque<Integer> stack=new LinkedList<>();
            int maxArea=0;
            stack.push(-1);
            for (int i = 0; i < len; i++) {
                while (stack.peek()!=-1&heights[stack.peek()]>=heights[i]){
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




        //// 解法1：获取直方图中矩形的最大面积
        //public int largestRectangleArea(int[] heights) {
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





        //// 解法2：分治法  超时栈溢出
        //// 这个直方图的最大矩形有3种可能。第1种是矩形通过这根最矮的柱子。
        //// 第2种是矩形的起始柱子和终止柱子都在最矮的柱子的左侧。
        //// 第3种是矩形的起始柱子和终止柱子都在最矮的柱子的右侧。
        //// 第2种和第3种从本质上来说和求整个直方图的最大矩形面积是同一个问题，可以调用递归函数解决。
        //
        //// 计算[start,minIndex),[minIndex+1,end)和通过minIndex时的面积
        //
        //public int largestRectangleArea(int[] heights) {
        //    if (heights == null || heights.length == 0) {
        //        return 0;
        //    }
        //    return getArea(heights, 0, heights.length);
        //}
        //
        //
        //// 计算[start,end)的最大面积
        //private int getArea(int[] heights, int start, int end) {
        //    if (start == end) {// 实际区间是空的
        //        return 0;
        //    }
        //    if (start + 1 == end) {// 只有一个格子
        //        return heights[start];
        //    }
        //
        //    // 遍历数组寻找[start,end)中最低高度的矩形
        //    int minIndex = start;
        //    for (int i = start + 1; i < end; i++) {// 遍历[start，end)找出矩形中最低的矩形
        //        if (heights[i] < heights[minIndex]) {
        //            minIndex = i;
        //        }
        //    }
        //
        //    // 注意函数getArea(int[] heights, int start, int end) 区间是左闭右开。
        //    int area = (end - start) * heights[minIndex];// 通过最低柱子的长方形面积
        //    int leftArea = getArea(heights, start, minIndex);// 最低柱子左边长方形的最大面积
        //    int rightArea = getArea(heights, minIndex + 1, end);// 最低柱子右边长方形的最大面积
        //    // 最大面积为三个面积中的最大值
        //    area = Math.max(area, leftArea);
        //    return Math.max(area, rightArea);
        //}




        //// 解法1：暴力循环  超时
        // 如果能逐一找出直方图中所有的矩形并比较它们的面积，就能得到最大矩形面积。
        // 变量min记录从下标为i的柱子到下标为j的柱子的最矮的柱子的高度，它是这两根柱子之间的矩形的最高的高度。

        //public int largestRectangleArea(int[] heights) {
        //    if (heights == null || heights.length == 0) {
        //        return 0;
        //    }
        //    int maxArea=0;
        //    int length=heights.length;
        //    for (int i = 0; i < length; i++) {
        //        int minHeight=heights[i];
        //        for (int j = i; j <length ; j++) {
        //            minHeight=Math.min(minHeight,heights[j]);
        //            int area=minHeight*(j-i+1);
        //            maxArea=Math.max(maxArea,area);
        //        }
        //    }
        //    return maxArea;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
