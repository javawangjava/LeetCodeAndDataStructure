/**
 * <p>给定一个 24 小时制（小时:分钟 <strong>"HH:MM"</strong>）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>timePoints = ["23:59","00:00"]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>timePoints = ["00:00","23:59","00:00"]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= timePoints.length &lt;= 2 * 10<sup>4</sup></code></li>
 * <li><code>timePoints[i]</code> 格式为 <strong>"HH:MM"</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>数学</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍
 * 213</li><li>👎 0</li></div>
 */


package leetcode.editor.cn;

import java.util.*;

/**
 * 539
 * 最小时间差
 *
 * @author wangweizhou
 * @date 2022-08-28 17:42:56
 */

public class MinimumTimeDifference {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new MinimumTimeDifference().new Solution();
        List<String> timePoints = new ArrayList<>();
        timePoints.add("23:59");
        //timePoints.add("00:04");
        //timePoints.add("00:06");
        timePoints.add("00:00");
        int ans = solution.findMinDifference(timePoints);
        System.out.println(ans);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 思路1：
        // 这个题目最直观的解法是求出任意两个时间的间隔，然后比较得出最小的时间差。如果输入n个时间，那么需要计算每个时间与另外n-1个时间的间隔，这种蛮力法需要O（n2）的时间。
        // 上述解法的一个优化方法是把n个时间排序。排序之后只需要计算两两相邻的时间之间的间隔，这样就只需要计算O（n）个时间差。
        // 由于对n个时间进行排序通常需要O（nlogn）的时间，因此这种优化算法的总体时间复杂度是O（nlogn）。
        // 也就是说，在计算最小时间差时，需要把排序之后的第1个时间当作第2天的时间（即加上24小时）与最后一个时间之间的间隔也考虑进去。
        // 排序是为了计算相邻的两个时间的节点，所以用一个表示时间的数组也可以达到这个目的。


        // 思路2：
        // 一天有24小时，即1440分钟。如果用一个长度为1440的数组表示一天的时间，那么数组下标为0的位置对应时间00：00，下标为1的位置对应时间00：01，
        // 以此类推，下标为1439的位置对应23：59。数组中的每个元素是true或false的标识，表示对应的时间是否存在于输入的时间数组中。
        // 有了这个辅助数组，就只需要从头到尾扫描一遍，相邻的两个为true的值表示对应的两个时间在输入时间数组中是相邻的。
        // 由于数组的下标对应的是时间点，因此两个时间之间的时间差就是它们在数组中对应的下标之差。
        // 这个数组模拟了一个键为时间、值为true或false的哈希表。可以用数组模拟哈希表的原因是一天的分钟数是已知的，而且数组的长度为1440，也不算太长。



        ////  解法2：模拟 排序 写法2  先将时间列表转换成时间数组，然后再调用数组进行排序
        //// 注意这一个时间数组只是实际时间数据个数个，并不是1440个。
        //public int findMinDifference(List<String> timePoints) {
        //    if (timePoints == null || timePoints.size() == 0) {// 判空
        //        return -1;
        //    }
        //    // 一天最多只有1440分钟，如果输入的时间数组的长度超过1440，那么至少有两个时间是相同的。时间相同那么最小时间差一定是0。
        //    if (timePoints.size() > 1440) {// 当时间点个数多于1440时，肯定有重合的时间点
        //        return 0;
        //    }
        //    int size = timePoints.size();//
        //    int[] times = new int[size];// 存储时间点的数组times
        //    // 通过遍历将时间点转换成分钟形式然后存储在时间数组中，
        //    for (int i = 0; i < size; i++) {// 遍历链表timePoints
        //        String time = timePoints.get(i);// 获取第i个时间点
        //        int minutes = getMinutes(time);// 将该时间点转换成分钟的形式
        //        times[i] = minutes;// 将分钟形式的时间点保存在时间数组中
        //    }
        //    Arrays.sort(times);//将分钟形式的时间进行排序
        //    int minDiff = Integer.MAX_VALUE;
        //    for (int i = 1; i < size; i++) {// 循环遍历当前时间点与上一个时间点的时间差
        //        minDiff = Math.min(minDiff, times[i] - times[i - 1]);
        //        if (minDiff == 0) {
        //            return minDiff;
        //        }
        //    }
        //    // 在计算最小时间差时，需要把排序之后的第1个时间当作第2天的时间（即加上24小时）与最后一个时间之间的间隔也考虑进去。
        //    // first+minuteFlags.length-last：将第一个时间加1440转换成第二天的同一时间点，然后和最大的时间点做差
        //    minDiff = Math.min(times[0] - times[size - 1] + 1440, minDiff);
        //    return minDiff;
        //}
        //
        //
        //// 将时间点转换成分钟形式  本质都是模拟
        //public int getMinutes(String time) {
        //    return ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60 + (time.charAt(3) - '0') * 10 +
        //            (time.charAt(4) - '0');
        //}






        // 解法1：模拟（哈希表计数） +抽屉原理剪枝  好处就是O(C) 的复杂度使得所能处理的数据范围没有上限
        // 利用当天最多只有 60∗24=1440 个不同的时间点（跨天的话则是双倍），我们可以使用数组充当哈希表进行计数，
        // 同时根据「抽屉原理」，若 timePoints 数量大于 1440，必然有两个相同时间点，用作剪枝。

        public int findMinDifference(List<String> timePoints) {
            if (timePoints == null || timePoints.size() == 0) {// 判空
                return -1;
            }
            // 一天最多只有1440分钟，如果输入的时间数组的长度超过1440，那么至少有两个时间点是相同的。时间相同那么最小时间差一定是0。
            if (timePoints.size() > 1440) {// 当时间点个数多于1440时，肯定有重合的时间点
                return 0;
            }

            // 数组minuteFlags是标记数组，表示对应的时间是否存在于输入的时间数组中。
            // 数组minuteFlags的长度是1440，某个位置的值是true,则表明下标对应的时间出现在输入的时间列表中。
            // 一天有24小时，即1440分钟。如果用一个长度为1440的数组表示一天的时间，那么数组下标为0的位置对应时间00：00，下标为1的位置对应时间00：01，
            // 以此类推，下标为1439的位置对应23：59。数组中的每个元素是true或false的标识，表示对应的时间是否存在于输入的时间数组中。
            boolean[] minuteFlags = new boolean[1440];
            // 遍历参数集合将参数集合中的时间点转换成分钟形式表示的形式
            for (String time : timePoints) {
                int min = getMinutes(time);// 将时间点转换成分钟表示的形式
                // 剪枝：在扫描输入的时间数组时如果发现相同的时间，也可以直接返回最小的时间差，即0.
                if (minuteFlags[min]) {// 遇到相同的时间点，则最小时间差为0，也就是相同的时间点第二次出现。
                    return 0;
                }
                // 数组中的每个元素是true或false的标识，表示对应的时间是否存在于输入的时间数组中。
                minuteFlags[min] = true;// 该时间点第一次出现时，将该时间点标记数组设置为true。
            }
            return findMinDiff(minuteFlags);
        }


        // 将时间点转换成分钟形式  分割字符串和charAt()都可以
        public int getMinutes(String time) {
            return ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60 + (time.charAt(3) - '0') * 10 +
            (time.charAt(4) - '0');
        }
        //// 将时间点转换成分钟形式  本质都是模拟 这里采用分割字符串
        //public int getMinutes2(String time) {
        //    String[] ss = time.split(":");
        //    return Integer.parseInt(ss[0])* 60+ Integer.parseInt(ss[1]);// 分割后的数组第一位是小时，第二位是分钟
        //}


        // 标记数组:数组下标是时间点，数组值是表明是否存在这个时间点
        // 顺序扫描标记数组获取时间点的最大值，最小值和最小时间差。
        // 相邻的两个为true的值表示它们对应输入的两个相邻的时间。比较所有相邻的时间差就能得出最小的时间差。
        // 但是最后要把最小的时间点加上1440表示第二天的同一时间，求出它与最大的时间点的时间差。

        private int findMinDiff(boolean minuteFlags[]) {
            int minDiff = Integer.MAX_VALUE;// minDiff表示参数数组的最小时间差
            int prev = -1;// prev是上一个访问的时间点，默认值设置为负数。那么当prev是非负数时，表示已经有访问过的上一个时间点
            int minTime = Integer.MAX_VALUE;// minTime 表示参数数组最小的时间点
            int maxTime = -1;// maxTime 表示参数数组最大的时间点

            // 循环遍历时间标记数组minuteFlags得到不跨天的情况下的最小时间差
            for (int i = 0; i < minuteFlags.length; i++) {
                if (minuteFlags[i]) {// 当遍历到的时间点为true时，这时就要计算相邻的两个为true的时间之间的时间差
                    if (prev >= 0) {// 表示当前数据点i不是第一个数据时间点,也就是已经有前一个时间点了
                        // 当遇到两个为true的时间点时，则计算相邻的两个时间之间的时间差
                        minDiff = Math.min(i - prev, minDiff);// 更新最小时间差。当前时间点与上一个时间点的时间差：i-prev
                    }
                    prev = i;// 更新上一个访问的时间点
                    // 因为这里使用1440的容量，并不知道最大和最小的时间点，所以这里需要获取参数数组中的最大和最小时间点
                    minTime = Math.min(i, minTime);// 更新时间点里面最小的
                    maxTime = Math.max(i, maxTime);// 更新时间点里面最大的
                }
            }
            // 在计算最小时间差时，需要把排序之后的第1个时间当作第2天的时间（即加上24小时）与最后一个时间之间的间隔也考虑进去。
            // first+minuteFlags.length-last：将第一个时间加1440转换成第二天的同一时间点，然后和最大的时间点做差
            minDiff = Math.min(minTime + minuteFlags.length - maxTime, minDiff);
            return minDiff;
        }





        //// 解法1：模拟（哈希表计数） +抽屉原理剪枝  写法2 合并在一起
        //public int findMinDifference(List<String> timePoints) {
        //    if (timePoints == null || timePoints.size() == 0) {
        //        return -1;
        //    }
        //    if(timePoints.size()>1440){
        //        return 0;
        //    }
        //    boolean[] timeFlags=new boolean[1440];
        //    for (int i = 0; i <timePoints.size() ; i++) {
        //        String time=timePoints.get(i);
        //        int timeIndex =((time.charAt(0)-'0')*10+(time.charAt(1)-'0'))*60+(time.charAt(3)-'0')*10+(time.charAt(4)
        //        -'0');
        //        if(timeFlags[timeIndex]){
        //            return 0;
        //        }
        //        timeFlags[timeIndex]=true;
        //    }
        //    int prev=-1;
        //    int minDiff=Integer.MAX_VALUE;
        //    int minTime=Integer.MAX_VALUE;
        //    int maxTime=Integer.MIN_VALUE;
        //    for (int i = 0; i < timeFlags.length; i++) {
        //        if(timeFlags[i]){
        //            if(prev>=0){
        //                minDiff=Math.min(minDiff,i-prev);
        //            }
        //            prev=i;
        //            minTime=Math.min(minTime,i);
        //            maxTime=Math.max(maxTime,i);
        //        }
        //    }
        //    return Math.min(minDiff,minTime+timeFlags.length-maxTime);
        //}
        //




        ////  解法2：写法2 模拟 排序 写法2  先调用Collections的排序然后转换成分钟数
        //// static <T extends Comparable<? super T>>void sort(List<T> list) 根据其元素的natural ordering ，将指定列表按升序排序。
        //
        //public int findMinDifference(List<String> timePoints) {
        //    if (timePoints == null || timePoints.size() == 0) {
        //        return -1;
        //    }
        //    // 一天最多只有1440分钟，如果输入的时间数组的长度超过1440，那么至少有两个时间是相同的。时间相同那么最小时间差一定是0。
        //    if (timePoints.size() > 1440) {// 当时间点个数多于1440时，肯定有重合的时间点
        //        return 0;
        //    }
        //
        //    // static <T extends Comparable<? super T>> void sort(List<T> list) 指定列表为升序排序，根据其元素的 natural ordering。
        //    Collections.sort(timePoints);// List排序,调用API
        //    int minDiff = Integer.MAX_VALUE;
        //    int t0Minutes = getMinutes(timePoints.get(0));//第一个时间点的分钟数
        //    int preMinutes = t0Minutes;// preMinutes记录上一个时间点的分钟数
        //    for (int i = 1; i < timePoints.size(); ++i) {// 循环遍历得到不跨天的情况下的最小时间差
        //        int minutes = getMinutes(timePoints.get(i));// 获取当前时间点
        //        minDiff = Math.min(minDiff, minutes - preMinutes); // 将相邻时间的时间差和最小时间差进行比较，更新最小时间差
        //        if (minDiff == 0) {// 这里不用时间差为0的语句了。相同时间点的判断在遍历时间数据形成标记数组时已经完成了
        //            return minDiff;
        //        }
        //        preMinutes = minutes;// 更新上一个时间点
        //    }
        //    minDiff = Math.min(minDiff, t0Minutes + 1440 - preMinutes); // 首尾时间的时间差
        //    return minDiff;
        //}
        //
        //
        //// 将时间点转换成分钟形式  本质都是模拟
        //public int getMinutes(String time) {
        //    return ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60 + (time.charAt(3) - '0') * 10 +
        //    (time.charAt(4) -
        //            '0');
        //}





        //// 将时间点转换成分钟形式  分割字符串和charAt()都可以
        //public int getMinutes(String time) {
        //    return ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60 + (time.charAt(3) - '0') * 10 +
        //    (time.charAt(4) -
        //            '0');
        //}

        //// 将时间点转换成分钟形式  本质都是模拟 这里采用分割字符串
        //public int getMinutes2(String time) {
        //    String[] ss = time.split(":");
        //    return Integer.parseInt(ss[0])* 60+ Integer.parseInt(ss[1]);// 分割后的数组第一位是小时，第二位是分钟
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
