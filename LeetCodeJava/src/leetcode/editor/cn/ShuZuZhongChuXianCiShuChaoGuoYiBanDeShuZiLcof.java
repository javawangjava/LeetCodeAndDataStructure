/**
 * <p>数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p>你可以假设数组是非空的，并且给定的数组总是存在多数元素。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1:</strong></p>
 *
 * <pre><strong>输入:</strong> [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * <strong>输出:</strong> 2</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <p><code>1 &lt;= 数组长度 &lt;= 50000</code></p>
 *
 * <p>&nbsp;</p>
 *
 * <p>注意：本题与主站 169 题相同：
 * <a href="https://leetcode-cn.com/problems/majority-element/">https://leetcode-cn.com/problems/majority-element/</a>
 * </p>
 *
 * <p>&nbsp;</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>分治</li><li>计数</li><li>排序</li></div></div><br><div
 * ><li>👍 320</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 39
 * 数组中出现次数超过一半的数字
 * @author wangweizhou
 * @date 2022-09-24 11:58:54
 */
public class ShuZuZhongChuXianCiShuChaoGuoYiBanDeShuZiLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ShuZuZhongChuXianCiShuChaoGuoYiBanDeShuZiLcof().new Solution();
        int[] nums = {2,1,1,2};
        int ans = solution.majorityElement(nums);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 基于快排算法的后面看
        // 哈希表



        // 解法2：投票法  这个只能处理一定有多数元素的，

        // 数组中有一个数字出现的次数超过数组长度的一半，也就是说它出现的次数比其他所有数字出现次数的和还要多。
        //因此，我们可以考虑在遍历数组的时候保存两个值：一个是数组中的一个数字：另一个是次数。
        //当我们遍历到下一个数字的时候，如果下一个数字和我们之前保存的数字相同，则次数加1；
        //如果下一个数字和我们之前保存的数字不同，则次数减1。
        //如果次数为零，那么我们需要保存下一个数字，并把次数设为1。
        //由于我们要找的数字出现的次数比其他所有数字出现的次数之和还要多，那么要找的数字肯定是最后一次把次数设为1时对应的数字。

        // 因为候选人的得票总数大于一半，所以肯定会最终站在舞台上
        // 群众投票选举候选人上台，只有当候选人票数大于1时可以继续留在舞台上，候选人的票数等于0时，更换新的候选人。
        // 假定每个候选人的票数都是1。也就是当选取一个新的候选人时，设定票数为1.
        // 开始时随机选取一个候选人，设置票数为1
        public int majorityElement(int[] nums) {
            if (nums == null || nums.length == 0) {// 这里约定空数组或者空引用时返回min
                return Integer.MIN_VALUE;
            }
            int len = nums.length;
            int candiate = nums[0];// 群众第一次投票选举的候选人candiate上台
            int count = 1;// 初始化候选人票数
            for (int i = 1; i < len; i++) {
                if (count == 0) {// 当候选人的票数为0，那么就下台换当前群众选的新一个候选人上台
                    candiate = nums[i];
                    count = 1;
                } else if (nums[i] == candiate) {// 当前群众投票选的人就是当前候选人时，则候选人票数+1
                    count++;
                } else if (nums[i] != candiate) {// 当前群众投票选的人不是当前候选人时，则候选人票数-1
                    count--;
                }
            }
            if(checkValid(nums,candiate)){
                return candiate;
            }else{
                return Integer.MIN_VALUE;
            }
        }

        // 检查target是否是数组的众数
        private boolean checkValid(int[] nums,int target){
            if(nums==null||nums.length==0){
                return false;
            }
            int count=0;
            for(int num:nums){
                if(num==target){
                    count++;
                }
            }
            if(count*2>nums.length){
                return true;
            }
            return false;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
