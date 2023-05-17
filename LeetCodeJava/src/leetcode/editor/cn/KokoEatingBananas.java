/**
 * <p>珂珂喜欢吃香蕉。这里有 <code>n</code> 堆香蕉，第 <code>i</code> 堆中有&nbsp;<code>piles[i]</code>&nbsp;根香蕉。警卫已经离开了，将在
 * <code>h</code> 小时后回来。</p>
 *
 * <p>珂珂可以决定她吃香蕉的速度 <code>k</code> （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 <code>k</code> 根。如果这堆香蕉少于 <code>k</code>
 * 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。&nbsp;&nbsp;</p>
 *
 * <p>珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。</p>
 *
 * <p>返回她可以在 <code>h</code> 小时内吃掉所有香蕉的最小速度 <code>k</code>（<code>k</code> 为整数）。</p>
 *
 * <p>&nbsp;</p>
 *
 * <ul>
 * </ul>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>piles = [3,6,7,11], h = 8
 * <strong>输出：</strong>4
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>piles = [30,11,23,4,20], h = 5
 * <strong>输出：</strong>30
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>piles = [30,11,23,4,20], h = 6
 * <strong>输出：</strong>23
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= piles.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>piles.length &lt;= h &lt;= 10<sup>9</sup></code></li>
 * <li><code>1 &lt;= piles[i] &lt;= 10<sup>9</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 420</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


/**
 * 875
 * 爱吃香蕉的珂珂
 *
 * @author wangweizhou
 * @date 2022-08-30 18:40:34
 */

public class KokoEatingBananas {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new KokoEatingBananas().new Solution();
        int[] piles = {3, 6, 7, 11};
        //int[] piles = {30,11,23,4,20};
        int ans = solution.minEatingSpeed(piles, 8);
        int ans2 = solution.getHours(piles, 6);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 知道它吃香蕉的速度的范围。显然，它每小时至少要吃1根香蕉。
        // 由于它1小时内只吃一堆香蕉，因此它每小时吃香蕉数目的上限是最大一堆香蕉的数目，记为max根，再大的话没有必要。

        // 狒狒吃香蕉的速度应该在最小值1根和最大值max根的范围内。
        // 在1～max根取中间值mid根，求出按照每小时吃mid根香蕉的速度吃完所有香蕉的时间。
        // 如果需要的时间多于H小时，则意味着它应该吃得更快一些，因此狒狒吃香蕉的速度应该在mid+1根到max根这个范围内。
        // 如果需要的时间少于或等于H小时，那么先判断mid根是不是最慢的速度。判断的办法是计算如果按照每小时吃mid-1根香蕉的速度需要多久吃完。
        // 如果按照每小时吃mid-1根香蕉的速度需要的时间也小于或等于H小时，就意味着每小时mid根香蕉不是能在H小时吃完所有香蕉的最慢的速度，因此狒狒吃香蕉的速度应该在1根到mid-1根之间。
        // 如果按照每小时mid-1根香蕉的速度吃完所有香蕉需要的时间大于H小时，这意味着mid根就是能在H小时内吃完所有香蕉的最慢速度。
        // 整个过程其实就是在1根到max根之间做二分查找。


        // 解法2：二分查找
        public int minEatingSpeed(int[] piles, int H) {
            if (piles == null || piles.length == 0 || H < piles.length) {
                return -1;
            }
            int maxSpeed = 0;
            for (int i = 0; i < piles.length; i++) {
                // 由于它1小时内只吃一堆香蕉，因此它每小时吃香蕉数目的上限是最大一堆香蕉的数目，记为max根，再大的话没有必要。
                maxSpeed = Math.max(maxSpeed, piles[i]);
            }
            if (H == piles.length) {// 堆数等于小时数
                return maxSpeed;
            }
            int low = 1;
            int high = maxSpeed;
            while (low <= high) {
                int midSpeed = (low + high) >> 1;
                int hours = getHours(piles, midSpeed);
                if (hours <= H) {// hours<=H耗时少或者刚好，说明速度快，更快的也就不符合要求。说明[mid+1,high]不合要求
                    // 因为getHours（int[] piles,int speed）第二个参数不为0，所以这里(mid-1)要单独处理(mid-1==0)的情况
                    if (midSpeed == 1 || getHours(piles, midSpeed - 1) > H) {// midSpeed如果等于1
                        return midSpeed;
                    } else {
                        high = midSpeed - 1;
                    }
                } else {
                    low = midSpeed + 1;
                }
            }
            return -1;
        }


        // 注意speed必须大于0。
        private int getHours(int[] piles, int speed) {
            int hours = 0;
            for (int i = 0; i < piles.length; i++) {
                // 因为在每个小时内只能吃一堆香蕉，所以向上取整，
                hours += (piles[i] - 1) / speed + 1;// 运算次数少，数学归纳法得到的

                // 下面这个运算次数多，这个好理解，有余数就额外加1个小时；没余数就刚好。
                //time += (piles[i] % speed == 0 ? piles[i] / speed : piles[i] / speed + 1);
            }
            return hours;
        }





        ////解法1：二分查找 查找耗时小于等于给定时间的最大值
        ////求最小速度，那么速度小于最小值，不合题意；速度大于等于最小值，符合题意.
        //public int minEatingSpeed(int[] piles, int H) {
        //    if (piles == null || piles.length == 0 || H < piles.length) {
        //        return 0;
        //    }
        //    int maxSpeed = 0;
        //    for (int pile : piles) {// 遍历数组，找出数组元素最大值，就是最大速度。那就要在总耗时小于等于给定时间里面找
        //        maxSpeed = maxSpeed > pile ? maxSpeed : pile;
        //    }
        //    if (H == piles.length) {// 堆数等于小时数
        //        return maxSpeed;
        //    }
        //    int low = 1;
        //    int high = maxSpeed;
        //    // 循环结束条件，两个指针指向同一个位置，所以返回left和right都可以
        //    while (low < high) {
        //        int mid = (low + high) / 2;
        //        int time = timeSum(piles, mid);
        //        if (time > H) {// 耗时太多，说明速度太慢了,说明[low,mid]不合要求
        //            low = mid + 1;
        //        } else {// time<=H耗时少或者刚好，说明速度快，更快的也就不符合要求。说明[mid+1,high]不合要求
        //            high = mid;
        //        }
        //    }
        //    return high;
        //}
        //
        //
        //
        //// 计算在指定速度时，吃完香蕉的时间和，数学归纳出来的
        //private int getHours(int[] piles, int speed) {
        //    int time = 0;
        //    for (int i = 0; i < piles.length; i++) {
        //        // 因为在每个小时内只能吃一堆香蕉，所以向上取整，
        //        time += (piles[i] - 1) / speed + 1;// 运算次数少，数学归纳法得到的
        //        // 下面这个运算次数多，这个好理解，有余数就额外加1个小时；没余数就刚好。
        //        //time += (piles[i] % speed == 0 ? piles[i] / speed : piles[i] / speed + 1);
        //    }
        //    return time;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
