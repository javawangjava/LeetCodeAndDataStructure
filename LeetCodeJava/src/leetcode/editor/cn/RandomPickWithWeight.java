/**
 * <p>给你一个 <strong>下标从 0 开始</strong> 的正整数数组&nbsp;<code>w</code> ，其中&nbsp;<code>w[i]</code> 代表第 <code>i</code>
 * 个下标的权重。</p>
 *
 * <p>请你实现一个函数&nbsp;<code>pickIndex</code>&nbsp;，它可以 <strong>随机地</strong> 从范围 <code>[0, w.length - 1]</code> 内（含
 * <code>0</code> 和 <code>w.length - 1</code>）选出并返回一个下标。选取下标 <code>i</code>&nbsp;的 <strong>概率</strong> 为 <code>w[i] /
 * sum(w)</code> 。</p>
 *
 * <ol>
 * </ol>
 *
 * <ul>
 * <li>例如，对于 <code>w = [1, 3]</code>，挑选下标 <code>0</code> 的概率为 <code>1 / (1 + 3)&nbsp;= 0.25</code> （即，25%），而选取下标
 * <code>1</code> 的概率为 <code>3 / (1 + 3)&nbsp;= 0.75</code>（即，<code>75%</code>）。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * <strong>输出：</strong>
 * [null,0]
 * <strong>解释：</strong>
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * <strong>输出：</strong>
 * [null,1,1,1,1,0]
 * <strong>解释：</strong>
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
 *
 * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * 诸若此类。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= w.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= w[i] &lt;= 10<sup>5</sup></code></li>
 * <li><code>pickIndex</code>&nbsp;将被调用不超过 <code>10<sup>4</sup></code>&nbsp;次</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>二分查找</li><li>前缀和</li><li>随机化</li></div></div><br><div><li>👍
 * 275</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Random;

/**
 * 528
 * 按权重随机选择
 *
 * @author wangweizhou
 * @date 2022-11-22 09:42:43
 */
public class RandomPickWithWeight {

    public static void main(String[] args) {
        //测试代码
        int[] w = {1, 2, 3, 4};
        Solution solution = new RandomPickWithWeight().new Solution(w);
        for (int i = 0; i < 100; i++) {
            int ans = solution.pickIndex();
            System.out.println(ans);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 既然要随机，那么这里就使用随机数，这里就类似转换为几何概型的之和。将权重比例转换为等比例之和。

        // 权重和数组是升序的：从权重和数组中找出第一个大于随机数flag的数字。
        // 第一个大于等于随机数flag的数满足的条件：一是这个数本身要大于flag;二是如果它前面有数字，那么前一个数字要小于等于数字flag。

        // 首先考虑如何根据权重比例计算选择下标的概率。
        // 先把权重数组中的所有权重全部加起来得到权重之和，然后用每个权重除以权重之和就能得到每个下标被选择的概率。
        // 例如，如果权重数组为[1，2，3，4]，那么权重之和是10。
        // 由于下标0对应的权重是1，那么选择0的概率是10%（1/10）。以此类推，选择下标1、2和3的概率分别为20%、30%和40%。
        // 接着考虑如何根据权重比例随机选择一个下标。还是以权重数组[1，2，3，4]为例。先按照等概率生成0到9之间的一个整数p。
        // 整数0～9一共有10个数字，按等概率生成的p是0～9【权重之和是10】任意一个数字的概率都是10%。
        // 如果p是0就选择0，即选择0的概率是10%；如果p为1或2就选择1，即选择1的概率是20%；如果p为3、4或5就选择2，即选择2的概率是30%；如果p为6、7、8或9就选择3，即选择3的概率是40%。
        // 可以创建另一个和权重数组的长度一样的数组sums，新数组的第i个数值sums[i]是权重数组中前i个数字之和。【累加和数组】
        // 有了这个数组sums就能很方便地根据等概率随机生成的数字p按照权重比例选择下标。
        // 例如，累加权重数组[1，2，3，4]中的权重得到的数组sums为[1，3，6，10]。
        // 有了这个累加权重的数组之后，如果0到9之间的随机数p＜1，那么选择0；如果1≤p＜3，那么选择1；如果3≤p＜6，那么选择2；如果6≤p＜10，那么选择3。


        // 也就是说，随机生成p之后，先顺序扫描累加权重数组sums找到第1个大于p的值，然后选择它对应的下标。
        // 例如，如果数组sums是[1，3，6，10]，当p=5时，数组中的6是第1个大于5的数字，此时选择6对应的下标2。当p=6时，数组中的10是第1个大于6的数字，此时选择10对应的下标3。
        // 如果数组sums的长度是n，则按照这种思路每次随机选择下标的时间复杂度是O（n）。


        // 值得注意的是，累加权重数组sums是递增排序的，需要在数组中找到第1个大于随机数p的数字，因此这也是一个在排序数组中查找的问题，可以尝试用二分查找算法解决。
        // 数组中第1个大于p的数字满足两个条件：一是这个数字本身要大于p，二是如果它前面有数字那么前一个数字要小于或等于p。
        // 从数组中选取位于中间的数字，如果这个数字小于p，那么第1个大于p的数字一定在它的后面，接下来只需要查找它的后半部分。
        // 如果这个数字大于p，那么检查它是否有前一个数字，如果有则再比较前一个数字和p的大小。
        // 如果它的确是第1个大于p的值，那么就找到了符合要求的数字；否则第1个大于p的数一定在它的前面，接下来只需要在数组的前半部分查找。


        // 解法1：
        // 核心就是将权重数组转换为累加权重数组，然后将权重数组下标和累加权重和数组区间对应。
        private int[] sums;// 权重和数组
        public Solution(int[] w) {// 构造器初始化
            sums = new int[w.length];
            // 创建一个与权重数组一样长的权重和数组，其实就是累加和数组
            sums[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                sums[i] = sums[i - 1] + w[i];
            }
        }


        // 找出权重和数组中第一个大于等于随机数的位置
        // 本质上找第一个大于等于随机数的位置，找到该位置这样就能确定属于那个权重和区间，接着找到对应的下标
         public int pickIndex() {
            // 按照权重比例选择下标
            Random random = new Random();
            int randomValue = random.nextInt(sums[sums.length - 1]);// 产生[0,权重和数组最大值]之间的随机数
            int left = 0;
            int right = sums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                //
                if (sums[mid] > randomValue) {// mid位置的数字大于randomValue
                    if (mid == 0 || sums[mid - 1] <= randomValue) {// 如果mid是第一个位置或者mid的前一个位置数字小于等于flag。那么就找到了mid。
                        return mid;
                    }
                    // 执行到这里，mid不是第一个位置并且mid的前一个位置数字大于flag。那么说明mid肯定不是第一个大于等于flag的位置
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }


    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
