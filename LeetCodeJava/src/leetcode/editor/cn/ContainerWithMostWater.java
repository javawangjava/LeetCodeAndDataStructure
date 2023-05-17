/**
 * <p>给定一个长度为 <code>n</code> 的整数数组&nbsp;<code>height</code>&nbsp;。有&nbsp;<code>n</code>&nbsp;条垂线，第 <code>i</code>
 * 条线的两个端点是&nbsp;<code>(i, 0)</code>&nbsp;和&nbsp;<code>(i, height[i])</code>&nbsp;。</p>
 *
 * <p>找出其中的两条线，使得它们与&nbsp;<code>x</code>&nbsp;轴共同构成的容器可以容纳最多的水。</p>
 *
 * <p>返回容器可以储存的最大水量。</p>
 *
 * <p><strong>说明：</strong>你不能倾斜容器。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs
 * .com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg" /></p>
 *
 * <pre>
 * <strong>输入：</strong>[1,8,6,2,5,4,8,3,7]
 * <strong>输出：</strong>49
 * <strong>解释：</strong>图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为&nbsp;49。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>height = [1,1]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == height.length</code></li>
 * <li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
 * <li><code>0 &lt;= height[i] &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>双指针</li></div></div><br><div><li>👍 3563</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 11
 * 盛最多水的容器
 */

public class ContainerWithMostWater {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ContainerWithMostWater().new Solution();
        //int[] height={1,8,6,2,5,4,8,3,7};
        int[] height = {1, 1};
        int ans = solution.maxArea(height);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 解法2：相向双指针
        //// 假定最外侧两个柱子组成的水域面积最大，然后移动两个柱子中较低的一根，不断更新最大面积。
        //// 若向内移动短板 ，水槽的短板 min(h[i],h[j]) 可能变大，因此下个水槽的面积可能增大 。
        //// 若向内移动长板 ，水槽的短板 min(h[i],h[j]) 不变或变小，因此下个水槽的面积 一定变小 。
        //
        //public int maxArea(int[] height) {
        //    if (height == null || height.length == 0) {// 判空
        //        return -1;
        //    }
        //    int len = height.length;// 数组长度
        //    int max = 0;// 最大容量
        //    // 相向双指针
        //    int left = 0;
        //    int right = len - 1;
        //    while (left < right) {
        //        // 木桶容量由较低的板的高度决定。这里容量是内部的，所以可以使用4线3格来理解。所以虽然是双闭区间[left,right]，但是能装水的格子是（right-left）。
        //        int area = (right - left) * Math.min(height[left], height[right]);
        //        max = Math.max(area, max);// 这个的底层就是三元运算符 //max = (max >= area) ? max : area;//和上面调max是一样的
        //        // 移动两个柱子中较低的一根，因为只有移动两根柱子中较低的一根，那么容器的高度才可能增加，宽度减小，这时候容量可以增大。
        //        if (height[right] > height[left]) {
        //            left++;
        //        } else {
        //            right--;
        //        }
        //    }
        //    return max;
        //}



        public int maxArea(int[] height) {
            if(height==null||height.length==0){
                return 0;
            }
            int len=height.length;
            int maxArea=0;
            int left=0;
            int right=len-1;
            while (left<right){
                int area=(right-left)*Math.min(height[right],height[left]);
                maxArea=Math.max(maxArea,area);
                if(height[left]<height[right]){
                    left++;
                }else {
                    right--;
                }
            }
            return maxArea;
        }




       /*

      //解法1：通过双循环遍历所有的两根柱子 不断更新最大值

        public int maxArea(int[] height) {
            if(height==null||height.length==0){
                return -1;
            }
            int length=height.length;
            int max=0;//水池最大面积
            for (int i = 0; i < length; i++) {
                for (int j = i+1; j < length; j++) {
                    // 水池的高度是较低的板决定的
                    int area=(j-i)*Math.min(height[i],height[j]);//水池面积
                    max=Math.max(area,max);
                }
            }
            return max;
        }
*/

    }

//leetcode submit region end(Prohibit modification and deletion)

}
