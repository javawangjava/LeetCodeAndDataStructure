/**
 * <p>以数组 <code>intervals</code> 表示若干个区间的集合，其中单个区间为 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>
 * 。请你合并所有重叠的区间，并返回&nbsp;<em>一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间</em>&nbsp;。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>intervals = [[1,3],[2,6],[8,10],[15,18]]
 * <strong>输出：</strong>[[1,6],[8,10],[15,18]]
 * <strong>解释：</strong>区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * </pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>intervals = [[1,4],[4,5]]
 * <strong>输出：</strong>[[1,5]]
 * <strong>解释：</strong>区间 [1,4] 和 [4,5] 可被视为重叠区间。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>intervals[i].length == 2</code></li>
 * <li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>排序</li></div></div><br><div><li>👍 1532</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import javax.swing.*;
import java.util.*;

/**
 * 56
 * 合并区间
 *
 * @author wangweizhou
 * @date 2022-06-29 10:47:29
 */

public class MergeIntervals {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new MergeIntervals().new Solution();
        int[][] intervals = {{15, 20}, {25, 30}, {5, 10}, {5, 8}};
        int[][] mergeds = solution.merge(intervals);
        for (int[] interval : intervals) {
            System.out.println(Arrays.toString(interval));
        }
        for (int[] merged : mergeds) {
            System.out.println(Arrays.toString(merged));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length == 0) {
                return new int[0][];
            }
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] == o2[0]) {// 当时间区间的开始时间相同，按照结束时间升序排序
                        return o1[1] - o2[1];
                    } else {
                        return o1[0] - o2[0];// 当时间区间开始时间不同，按照开始时间升序排列
                    }
                }
            });
            int len = intervals.length;
            List<int[]> lists = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                int left = intervals[i][0];
                int right = intervals[i][1];
                while (i < len - 1 && intervals[i + 1][0] <= right) {
                    right = Math.max(right, intervals[i + 1][1]);
                    i++;
                }
                lists.add(new int[]{left, right});
            }
            return lists.toArray(new int[0][]);
        }



        // 首先需要考虑两个区间在什么情况下才能被合并。
        // 如果区间1的起始位置小于或者等于区间2的起始位置，并且区间1的结束位置大于或等于区间2的起始位置，那么两个区间中间有重叠部分，
        // 它们能够被合并，合并之后的区间的起始位置是区间1的起始位置【因为是排序后，起始位置选小的】，合并之后的区间的结束位置是两个区间的结束位置的较大者【结束位置选大的】。
        // 反之，如果区间3的起始位置小于区间4的起始位置，并且区间3的结束位置也小于区间4的起始位置，那么两个区间没有重叠部分，它们不能被合并。
        // 如果先将所有区间按照起始位置排序，那么只需要比较相邻两个区间的结束位置就能知道它们是否重叠。
        // 如果它们重叠就将它们合并，然后判断合并的区间是否和下一个区间重叠。重复这个过程，直到所有重叠的区间都合并为止。

        // 注意每个区间是不定长的区间，所以假定的排序规则：1，当开始时间不同时，按照开始时间升序；2，当开始时间相同时，按照结束时间升序
        // 二维数组的第一位是区间的开始时间，第二位是区间的结束时间


        //// 写法2： 匿名内部类排序
        //public int[][] merge(int[][] intervals) {
        //    if (intervals == null || intervals.length == 0) {// 判空
        //        return new int[0][];// 返回空数组
        //    }
        //    // static <T> void sort(T[] a, Comparator<? super T> c) 根据指定比较器引发的顺序对指定的对象数组进行排序。
        //    // Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // lambda表达式
        //    // 定义排序规则：匿名内部类实现
        //    // 两个数组先按照起点升序，若起点相同则终点升序。
        //    // 二维数组的每一个一维元素的第一个元素就是区间的起始位置，第二个元素就是区间的结束位置。
        //    // 先将所有区间按照起始位置排序
        //
        //    //Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);// lambda表达式
        //    Arrays.sort(intervals, new Comparator<int[]>() {
        //        @Override
        //        public int compare(int[] o1, int[] o2) {
        //            if (o1[0] == o2[0]) {// 当时间区间的开始时间相同，按照结束时间升序排序
        //                return o1[1] - o2[1];
        //            } else {
        //                return o1[0] - o2[0];// 当时间区间开始时间不同，按照开始时间升序排列
        //            }
        //        }
        //    });
        //    ArrayList<int[]> lists = new ArrayList<>();// 因为不知道结果是多少位，所以用变长数组来表示，元素是一维数组。最后再转换为数组形式就可以
        //    int index = 0;// 变量i用来遍历区间intervals
        //    while (index < intervals.length) {
        //        int left = intervals[index][0];// 待合并区间的第一个区间的左边界
        //        int right = intervals[index][1];// 待合并区间的第一个区间的右边界
        //        // 先将所有区间按照起始位置排序，然后只需要比较相邻两个区间的结束位置就能知道它们是否重叠。
        //        // 如果它们重叠就将它们合并，然后判断合并的区间是否和下一个区间重叠。重复这个过程，直到所有重叠的区间都合并为止。
        //        // 当前区间的右边界>=下一个区间的左边界，则区间重合,一次将以left为起点能重合的数组全部合并。
        //        // 【因为要和下一个区间进行比较，所以这里需要判断，放置下标越界】。
        //        while (index < intervals.length - 1 && intervals[index + 1][0] <= right) {
        //            right = Math.max(right, intervals[index + 1][1]);// 更新右边界，
        //            index++;
        //        }
        //        // 上面内层while循环结束，（i+1）要么越界，要么（i+1）所在的区间和前面的区间没有交集
        //        // 执行完上面时，以intervals[i][0]开始的可以合并的区间都已经合并了，这时要将已经合并的区间添加到结果集合中
        //        // 将更新的区间放进res里面，也就是从起点left开始合并后的最长的数组放进res中。
        //        lists.add(new int[]{left, right});
        //        // （i+1）要么越界，要么（i+1）所在的区间和前面的区间没有交集，下一个待合并区间就是以（i+1）所在的区间开始
        //        index++;
        //    }
        //    //<T> T[] toArray(T[] a) 以适当的顺序返回包含此列表中所有元素的数组（从第一个元素到最后一个元素）; 返回数组的运行时类型是指定数组的运行时类型。
        //    return lists.toArray(new int[0][]);// 以数组的第一维的顺序返回
        //    // 下面是将可变数组转换为二维数组
        //    //int[][] ans=new int[lists.size()][2];
        //    //for (int i = 0; i < lists.size(); i++) {
        //    //    ans[i]=lists.get(i);
        //    //}
        //    //return ans;
        //}




        //// 解法1：排序  写法1  匿名内部类排序
        //public int[][] merge(int[][] intervals) {
        //    if (intervals == null || intervals.length == 0) {// 判空
        //        return new int[0][];// 返回空数组
        //    }
        //    // 因为不知道结果是多少位，所以用变长数组来表示，元素是一维数组。最后再转换为数组形式就可以。
        //    ArrayList<int[]> lists = new ArrayList<>();
        //    //static <T> void sort(T[] a, Comparator<? super T> c) 根据指定比较器引发的顺序对指定的对象数组进行排序。
        //    //Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // lambda表达式
        //
        //    // 定义排序规则：匿名内部类实现
        //    // 两个数组先按照起点升序，若起点相同则终点升序。
        //    // 二维数组的每一个一维元素的第一个元素就是区间的起始位置，第二个元素就是区间的结束位置。
        //    // 先将所有区间按照起始位置排序
        //    Arrays.sort(intervals, new Comparator<int[]>() {
        //        @Override
        //        //o1[i]里面的i指的是按照每一行的第i列进行排序
        //        public int compare(int[] o1, int[] o2) {
        //            if (o1[0] == o2[0]) {// 当时间区间的开始时间相同，按照结束时间升序排序
        //                return o1[1] - o2[1];
        //            } else {
        //                return o1[0] - o2[0];// 当时间区间开始时间不同，按照开始时间升序排列
        //            }
        //        }
        //    });
        //
        //    int i = 0;// 变量i用来遍历区间intervals
        //    while (i < intervals.length) {
        //        int left = intervals[i][0];// 待合并区间的第一个区间的左边界
        //        int right = intervals[i][1];// 待合并区间的第一个区间的右边界
        //        // 先将所有区间按照起始位置排序，然后只需要比较相邻两个区间的结束位置就能知道它们是否重叠。
        //        // 如果它们重叠就将它们合并，然后判断合并的区间是否和下一个区间重叠。重复这个过程，直到所有重叠的区间都合并为止。
        //        // 当前区间的右边界>=下一个区间的左边界，则区间重合,一次将以left为起点能重合的数组全部合并
        //        int next = i + 1;// 变量next表示排在区间intervals[i]之后的可能和intervals[i]有重合的区间
        //        while (next < intervals.length && intervals[next][0] <= right) {
        //            right = Math.max(right, intervals[next][1]);// 更新右边界，
        //            next++;
        //        }
        //        // 上面内层while循环结束，next要么越界，要么next所在的区间和前面的区间没有交集
        //        // 执行完上面时，以intervals[i][0]开始的可以合并的区间都已经合并了，这时要将已经合并的区间添加到结果集合中
        //        lists.add(new int[]{left, right});
        //        // 下一个待合并区间就是以next所在的区间开始
        //        i = next;
        //    }
        //    return lists.toArray(new int[0][]);// 以数组的第一维的顺序返回
        //}





        //// 解法2：排序  写法3  lambda排序
        //public int[][] merge(int[][] intervals) {
        //    if (intervals == null || intervals.length == 0) {// 判空
        //        return new int[0][];// 返回空数组
        //    }
        //    ArrayList<int[]> res = new ArrayList<>();// 因为不知道结果是多少位，所以用变长数组来表示，元素是一维数组。最后再转换为数组形式就可以
        //    //static <T> void sort(T[] a, Comparator<? super T> c) 根据指定比较器引发的顺序对指定的对象数组进行排序。
        //    //Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // lambda表达式
        //    // 先将所有区间按照起始位置排序
        //    Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        //    int i = 0;// 变量i用来遍历区间intervals
        //    while (i < intervals.length) {
        //        // 变量temp表示合并后的区间，intervals[i][0]区间左端点，intervals[i][1]区间右端点
        //        int[] temp = new int[]{intervals[i][0], intervals[i][1]};
        //        // 先将所有区间按照起始位置排序，然后只需要比较相邻两个区间的结束位置就能知道它们是否重叠。
        //        // 如果它们重叠就将它们合并，然后判断合并的区间是否和下一个区间重叠。重复这个过程，直到所有重叠的区间都合并为止。
        //        while (i + 1 < intervals.length && intervals[i + 1][0] <= temp[1]) {// 合并完
        //            temp[1] = Math.max(temp[1], intervals[i + 1][1]);
        //            i++;
        //        }
        //        // 上面内层while循环结束，next要么越界，要么next所在的区间和前面的区间没有交集
        //        // 执行完上面时，以intervals[i][0]开始的可以合并的区间都已经合并了，这时要将已经合并的区间添加到结果集合中
        //        res.add(temp);
        //        // 下一个待合并区间就是以next所在的区间开始
        //        i++;
        //    }
        //    return res.toArray(new int[0][]);// 以数组的第一维的顺序返回
        //}




    }
//leetcode submit region end(Prohibit modification and deletion)

}
