/**
 * <p><big><small>给定一个二维矩阵 <code>matrix</code>，</small></big>以下类型的多个请求：</p>
 *
 * <ul>
 * <li><big><small>计算其子矩形范围内元素的总和，该子矩阵的 <strong>左上角</strong> 为 <code>(row1,&nbsp;col1)</code> ，<strong>右下角</strong> 为
 * <code>(row2,&nbsp;col2)</code> 。</small></big></li>
 * </ul>
 *
 * <p>实现 <code>NumMatrix</code> 类：</p>
 *
 * <ul>
 * <li><code>NumMatrix(int[][] matrix)</code>&nbsp;给定整数矩阵 <code>matrix</code> 进行初始化</li>
 * <li><code>int sumRegion(int row1, int col1, int row2, int col2)</code>&nbsp;返回<big><small>
 *     <strong>左上角</strong>
 * </small></big><big><small> <code>(row1,&nbsp;col1)</code>&nbsp;、<strong>右下角</strong>&nbsp;<code>(row2,&nbsp;col2)
 * </code></small></big> 所描述的子矩阵的元素 <strong>总和</strong> 。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img src="https://pic.leetcode-cn.com/1626332422-wUpUHT-image.png" style="width: 200px;" /></p>
 *
 * <pre>
 * <strong>输入:</strong>
 * ["NumMatrix","sumRegion","sumRegion","sumRegion"]
 * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
 * <strong>输出:</strong>
 * [null, 8, 11, 12]
 *
 * <strong>解释:</strong>
 * NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == matrix.length</code></li>
 * <li><code>n == matrix[i].length</code></li>
 * <li><code>1 &lt;= m,&nbsp;n &lt;=&nbsp;200</code><meta charset="UTF-8" /></li>
 * <li><code>-10<sup>5</sup>&nbsp;&lt;= matrix[i][j] &lt;= 10<sup>5</sup></code></li>
 * <li><code>0 &lt;= row1 &lt;= row2 &lt; m</code></li>
 * <li><code>0 &lt;= col1 &lt;= col2 &lt; n</code></li>
 * <li><meta charset="UTF-8" />最多调用 <code>10<sup>4</sup></code> 次&nbsp;<code>sumRegion</code> 方法</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>矩阵</li><li>前缀和</li></div></div><br><div><li>👍
 * 408</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 304
 * 二维区域和检索 - 矩阵不可变
 *
 * @author wangweizhou
 * @date 2022-06-22 23:38:17
 */

public class RangeSumQuery2dImmutable {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new RangeSumQuery2dImmutable().new Solution();
        //NumMatrix solution = new RangeSumQuery2dImmutable().new NumMatrix();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumMatrix {


        // 这个题目提到，对于一个二维矩阵，可能由于输入不同的坐标而反复求不同子矩阵的数字之和，这说明应该优化求和的过程，要尽可能快地实现子矩阵的数字求和。
        // 因此，可以在预处理阶段求出从左上角坐标为（0，0）到每个右下角坐标的子矩阵的数字之和。

        // 首先创建一个和输入矩阵大小相同的辅助矩阵sums，该矩阵中的坐标（i，j）的数值为输入矩阵中从左上角坐标（0，0）到右下角坐标（i，j）的子矩阵的数字之和。
        // 有了这个辅助矩阵sums，再求左上角坐标为（r1，c1）、右下角坐标为（r2，c2）的子矩阵的数字之和就变得比较容易。
        // 该子矩阵的数字之和等于sums[r2][c2]+sums[r1-1][c2]-sums[r2][c1-1]+sums[r1-1][c1-1]。// 割补法


        // 下面分析如何生成辅助矩阵sums，即求得数组中的每个数字sums[i][j]。
        // 按照生成辅助矩阵sums的规则，sums[i][j]的值等于输入矩阵中从左上角坐标为（0，0）到右下角坐标为（i，j）的子矩阵的数字之和。
        // 可以把从左上角坐标为（0，0）到右下角坐标为（i，j）的子矩阵的数字看成由两部分组成。
        // 第1部分是从左上角坐标为（0，0）到右下角坐标为（i-1，j）的子矩阵，该子矩阵的数字之和等于sums[i-1][j]。
        // 第2部分是输入矩阵中第i行中列号从0到j的所有数字。
        // 如果按照从左到右的顺序计算sums[i][j]，则可以逐个累加第i行的数字，从而得到子矩阵第2部分的数字之和。


        // 如果输入矩阵的行数和列数分别是m和n，那么辅助数组sums的行数和列数分别为m+1和n+1，这样只是为了简化代码逻辑。

        //	 解法1：二维前缀和   将一维的前缀和推广到二维前缀和
        // 1. 定义二维前缀和 preNums[i, j]: 第i行第j列格子左上部分所有元素之和(不包含第i行第j列格子元素)【包括不包括前后要处理一致】
        // 2. 前缀和计算公式 preNums[i][j] = preNums[i - 1][j] + preNums[i][j - 1] - preNums[i - 1][j - 1] + preNums[i][j]


        //private int[][] sumMatrix;
        //public NumMatrix(int[][] matrix) {//构造器
        //    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {// 判空
        //        return;
        //    }
        //    int rowLen = matrix.length;// 行数
        //    int colLen = matrix[0].length;// 列数
        //    // sums[i+1][j+1]表示从左上角[0][0]到右下角[i][j]的子矩阵数字之和，也就是行列的下标都右移一位。这样初始化也容易
        //    sumMatrix = new int[rowLen + 1][colLen + 1];
        //    for (int i = 0; i < rowLen; i++) {
        //        for (int j = 0; j < colLen; j++) {
        //            sumMatrix[i + 1][j + 1] =
        //                    sumMatrix[i][j + 1] + sumMatrix[i + 1][j] - sumMatrix[i][j] + matrix[i][j];// 画图，其实这个也是割补，
        //        }
        //    }
        //}
        //
        //
        //public int sumRegion(int row1, int col1, int row2, int col2) {
        //    if (row1 > row2 || col1 > col2) {
        //        return 0;
        //    }
        //    return sumMatrix[row2 + 1][col2 + 1] - sumMatrix[row2 + 1][col1] - sumMatrix[row1][col2 + 1] +
        //    sumMatrix[row1][col1];// 画图
        //}



        private int[][] sums;
        public NumMatrix(int[][] matrix) {//构造器
            int rowLen=matrix.length;
            int colLen=matrix[0].length;
            sums=new int[rowLen+1][colLen+1];
            for (int row = 0; row < rowLen; row++) {
                for (int col = 0; col < colLen; col++) {
                    sums[row+1][col+1]=sums[row][col+1]+sums[row+1][col]-sums[row][col]+matrix[row][col];
                }
            }
        }


        public int sumRegion(int row1, int col1, int row2, int col2) {
            if(row1>row2||col1>col2){
                return 0;
            }
            return sums[row2+1][col2+1]-sums[row1][col2+1]-sums[row2+1][col1]+sums[row1][col1];
        }




        // 写法2：有点问题，这个前缀和没有平移，个别测试用例有问题
        //private int[][] sumsMatrix;
        //public NumMatrix(int[][] matrix) {
        //    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {// 判空
        //        return;
        //    }
        //    int rowLen = matrix.length;
        //    int colLen = matrix[0].length;
        //    sumsMatrix = new int[rowLen][colLen];
        //    sumsMatrix[0][0] = matrix[0][0];
        //    for (int row = 1; row < rowLen; row++) {
        //        sumsMatrix[row][0]=sumsMatrix[row-1][0]+matrix[row][0];
        //    }
        //    for (int col = 1; col < colLen; col++) {
        //        sumsMatrix[0][col]=sumsMatrix[0][col -1]+matrix[0][col];
        //    }
        //    for (int row = 1; row < rowLen; row++) {
        //        for (int col = 1; col < colLen; col++) {
        //            sumsMatrix[row][col] =
        //                    sumsMatrix[row - 1][col] + sumsMatrix[row][col - 1] - sumsMatrix[row - 1][col - 1] + matrix[row][col];
        //        }
        //    }
        //}
        //
        //
        //public int sumRegion(int row1, int col1, int row2, int col2) {
        //    if(row1>row2||col1>col2){
        //        return 0;
        //    }
        //    if(row1>0&&col1>0){
        //        return sumsMatrix[row2][col2]- sumsMatrix[row1-1][col2]-sumsMatrix[row2][col1-1]+sumsMatrix[row1-1][col1-1];
        //    }else if(row1==0){
        //        return sumsMatrix[row2][col2]-sumsMatrix[row2][col1-1];
        //    }else {
        //        return sumsMatrix[row2][col2]- sumsMatrix[row1-1][col2];
        //    }
        //}





        //// 解法2：一维前缀和
        //private int[][] sums;
        //public NumMatrix(int[][] matrix) {//构造器
        //    // 其实这里只要row==0就可以，java中矩阵本质是一维数组
        //    if (matrix.length == 0 || matrix[0].length == 0) {// 处理输入数组为空集
        //        return;
        //    }
        //    int row = matrix.length;//矩阵行数
        //    int column = matrix[0].length;//矩阵列数
        //    sums = new int[row + 1][column + 1];// 创建二维数组来存储数组和
        //    // sums[i+1][j+1]=sums[i+1][j]+columnsum[j]=sums[i][j+1]+rowsum[i+1]
        //    for (int i = 0; i < row; i++) {
        //        int rowSum = 0;
        //        for (int j = 0; j < column; j++) {
        //            // rowSum表示第i行的和。rowSum=matrix[i][0]+matrix[i][1]+...+matrix[i][j]
        //            rowSum += matrix[i][j];//
        //            sums[i + 1][j + 1] = sums[i][j + 1] + rowSum;// rowSum
        //        }
        //    }
        //}
        //
        //public int sumRegion(int row1, int col1, int row2, int col2) {
        //    return sums[row2 + 1][col2 + 1]- sums[row1][col2 + 1] - sums[row2 + 1][col1] + sums[row1][col1];
        //}


    }

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */

//leetcode submit region end(Prohibit modification and deletion)

}
