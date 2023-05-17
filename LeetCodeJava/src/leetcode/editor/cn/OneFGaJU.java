/**
 * /**
 * /**
 * <p>给定一个包含 <code>n</code> 个整数的数组&nbsp;<code>nums</code>，判断&nbsp;<code>nums</code>&nbsp;中是否存在三个元素&nbsp;<code>a</code> ，<code>b</code> ，<code>c</code> <em>，</em>使得&nbsp;<code>a + b + c = 0</code> ？请找出所有和为 <code>0</code> 且&nbsp;<strong>不重复&nbsp;</strong>的三元组。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [-1,0,1,2,-1,-4]
 * <strong>输出：</strong>[[-1,-1,2],[-1,0,1]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0]
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= nums.length &lt;= 3000</code></li>
 * <li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><meta charset="UTF-8" />注意：本题与主站 15&nbsp;题相同：<a href="https://leetcode-cn.com/problems/3sum/">https://leetcode-cn.com/problems/3sum/</a></p>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 65</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组中和为 0 的三个数
 */
public class OneFGaJU {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new OneFGaJU().new Solution();
        //int[] nums={-1,0,1,2,-1,-4};
        int[] nums = { 0,0,0};
        List<List<Integer>> ans = solution.threeSum(nums);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //	 解法1：数组升序排列+双指针
        public List<List<Integer>> threeSum(int[] nums) {

            List<List<Integer>> ans = new ArrayList<>();
            if ( nums == null||nums.length < 3 ) {
                return ans;
            }
            Arrays.sort(nums);//数组升序排列
            int length = nums.length;

            for (int i = 0; i < length - 2; i++) {// 枚举 i
                //双指针从i后面的两侧开始
                int left = i + 1;
                int right = length - 1;
                int sum;
                if(nums[i]>0){// 三数之和肯定大于0，剪枝
                   break;
                }
                if(nums[i]+nums[length-2]+nums[length-1]<0){
                    continue;
                }

                // 因为i是指定的第一个指针，判断是否跳过当前这一个，所以要将当前这一个和前一个进行比较，这样才能保证当前第一个元素出现的时候已经算过了，不会遗漏。
                if (i > 0 && nums[i] == nums[i - 1]) {// i 这个需要先算一次，后移之后在判断与前一个是否相等
                    continue;
                }

                while (left < right) {
                    sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {//
                        ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        //已经相等了，那么left指针直接跳过下一个相同的数，直接移动至下一个不同的数
                        while (left < right && nums[left] == nums[left + 1]) {//循环结束left指向最后一个相同的数
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
