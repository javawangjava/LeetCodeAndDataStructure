/**
 * <p>输入一个正整数 <code>target</code> ，输出所有和为 <code>target</code> 的连续正整数序列（至少含有两个数）。</p>
 *
 * <p>序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>target = 9
 * <strong>输出：</strong>[[2,3,4],[4,5]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>target = 15
 * <strong>输出：</strong>[[1,2,3,4,5],[4,5,6],[7,8]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= target &lt;= 10^5</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 * <div><div>Related Topics</div><div><li>数学</li><li>双指针</li><li>枚举</li></div></div><br><div><li>👍 480</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 57 - II
 * 和为s的连续正数序列
 *
 * @author wangweizhou
 * @date 2022-09-14 00:36:16
 */

public class HeWeiSdeLianXuZhengShuXuLieLcof {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new HeWeiSdeLianXuZhengShuXuLieLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 设连续正整数序列的左边界 i 和右边界 j ，则可构建滑动窗口从左向右滑动。
        // 循环中，每轮判断滑动窗口内元素和与目标值 target 的大小关系，若相等则记录结果，
        // 若大于 target 则移动左边界 i （以减小窗口内的元素和）；若小于 target 则移动右边界 j （以增大窗口内的元素和）。
        // [1,n]中找出连续子序列的和为target。



        // 解法1：写法3 滑动窗口
        // 注意：滑动窗口右边界右移添加元素进入子序列，和滑动窗口左边界右移从子序列中移出元素时，先移动还是先做和还是先做差，这个与前面的初始值设置的逻辑有关。
        public int[][] findContinuousSequence(int target) {
            if (target <= 2) {// 至少含有两个数，那么最小和是3。
                return new int[0][];
            }
            List<int[]> lists = new ArrayList<>();// 不知道结果二维数组的大小，所以这里使用可变数组来保存，然后再转为二维数组
            // 根据题意，滑动窗口中至少有两个数，那么初始化滑动窗口的左右边界。
            int left = 1;
            int right = 2;
            int mid = (1 + target) / 2;// 找出[1,target]的中位数【可能略小】,主要是用在后面用于剪枝
            int subSum = left + right;
            //while (left < target) {//
            while (left < mid) {// 题干要求子序列至少两个数,若子序列中左边界等于mid，那么子序列的和一定大于目标值target，那么这里相当于剪枝
                if (subSum == target) {// 当子序列的和为目标值，将子序列记录。并将滑动窗口左边界右移
                    int[] subArr = new int[right - left + 1];// 创建子序列数组，并将子序列[left,right]添加到子序列数组arr中
                    for (int i = left; i <= right; i++) {
                        subArr[i - left] = i;
                    }
                    lists.add(subArr);// 将子序列添加到结果序列中
                    // 子序列的和等于目标值，之后要将左边界移出，累加和要减去左边界，并将左边界右移,这里需要移动滑动窗口，使得子序列的和发生变化。
                    subSum -= left;
                    left++;
                } else if (subSum < target) {// 子序列的累加和小于目标值，那么要将右边界移出，累加和要加上右边界，并将右边界右移。
                    // 滑动窗口移入右边界元素，然后子序列和再累加该元素
                    right++;
                    subSum += right;// 右边界移入，将新加入的元素累加
                } else {// 子序列的累加和大于目标值，那么要将左边界移出，累加和要减去左边界，并将左边界右移
                    // 子序列和先减去左边界元素，然后再移出该元素，
                    subSum -= left;
                    left++;
                }
            }
            return lists.toArray(new int[lists.size()][]);
        }





        //// 解法1：写法2 滑动窗口
        //public int[][] findContinuousSequence(int target) {
        //    if (target <= 2) {
        //        return null;
        //    }
        //    List<int[]> lists = new LinkedList<>();
        //    int left = 1;
        //    int right = 2;
        //    int mid = (1 + target) / 2;
        //    int currSum = left + right;
        //    while (left < mid) {// 题干要求子序列至少两个数,若子序列中左边界等于mid，那么子序列的和一定大于目标值target
        //        if (currSum == target) {// 当子序列的和为目标值
        //            int[] arr = new int[right - left + 1];// 创建子序列数组，并将子序列添加到子序列数组中
        //            for (int i = left; i <= right; i++) {
        //                arr[i - left] = i;
        //            }
        //            lists.add(arr);// 将子序列添加到结果序列中
        //        }
        //
        //        // 当子序列的和大于目标值时，
        //        while (currSum > target && left < mid) {
        //            currSum -= left;// 将滑动窗口最左侧的数移出
        //            left++;// 左指针右移
        //            if (currSum == target) {// 当子序列的和为目标值
        //                int[] arr = new int[right - left + 1];
        //                for (int i = left; i <= right; i++) {
        //                    arr[i - left] = i;
        //                }
        //                lists.add(arr);
        //            }
        //        }
        //        // 上面结束时，子序列的和小于目标值时。所以滑动窗口右边界右移，并将有边界元素加入滑动窗口
        //        right++;
        //        currSum += right;
        //    }
        //    return lists.toArray(new int[lists.size()][]);
        //}




        ////	解法1：滑动窗口
        //public int[][] findContinuousSequence(int target) {
        //	if(target<=2){
        //		return null;
        //	}
        //	int left = 1;
        //	int right = 2;
        //	List<int[]> res = new ArrayList<>();
        //	while (left < right) {
        //		int sum = (left + right) * (right - left + 1) / 2;
        //		if (sum == target){// 连续数组和为target,
        //			int[] arr = new int[right - left + 1];
        //			for (int k = left; k <= right; k++) {
        //				arr[k - left] = k;
        //			}
        //			res.add(arr);
        //			left++;
        //		}
        //		else if (sum < target) {
        //			right++;
        //		}
        //		else {
        //			left++;
        //		}
        //	}
        //	return res.toArray(new int[res.size()][]);
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
