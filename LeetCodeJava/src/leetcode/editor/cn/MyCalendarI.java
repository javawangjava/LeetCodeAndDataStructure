/**
 * <p>实现一个 <code>MyCalendar</code> 类来存放你的日程安排。如果要添加的日程安排不会造成 <strong>重复预订</strong> ，则可以存储这个新的日程安排。</p>
 *
 * <p>当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 <strong>重复预订</strong> 。</p>
 *
 * <p>日程可以用一对整数 <code>start</code> 和 <code>end</code> 表示，这里的时间是半开区间，即 <code>[start, end)</code>, 实数&nbsp;
 * <code>x</code> 的范围为， &nbsp;<code>start &lt;= x &lt; end</code> 。</p>
 *
 * <p>实现 <code>MyCalendar</code> 类：</p>
 *
 * <ul>
 * <li><code>MyCalendar()</code> 初始化日历对象。</li>
 * <li><code>boolean book(int start, int end)</code> 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 <code>true</code> 。否则，返回
 * <code>false</code>&nbsp;并且不要将该日程安排添加到日历中。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * <strong>输出：</strong>
 * [null, true, false, true]
 *
 * <strong>解释：</strong>
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15 已经被另一个日程安排预订了。
 * myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于 20 ，且不包含时间 20 。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= start &lt; end &lt;= 10<sup>9</sup></code></li>
 * <li>每个测试用例，调用 <code>book</code> 方法的次数最多不超过 <code>1000</code> 次。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>设计</li><li>线段树</li><li>二分查找</li><li>有序集合</li></div></div><br><div><li>👍
 * 237</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 729
 * 我的日程安排表 I
 *
 * @author wangweizhou
 * @date 2022-11-16 20:27:40
 */

public class MyCalendarI {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new MyCalendarI().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MyCalendar {

        // 添加到日程表中的每个事项都占用一个时间段。根据题目的要求，两个事项不能占用同一个时间段，也就是说，事项对应的时间区间不能重叠。
        // 当需要插入一个新的事项时，就需要遍历日程表中已有事项占用的时间区间。

        // 优化查找：注意本题目的设定是每一个事项是等长的。如果待添加的事项占用的时间区间是[m，n），就需要找出开始时间小于m的所有事项中开始最晚的一个，以及开始时间大于m的所有事项中开始最早的一个。
        // 如果待添加的事项和这两个事项都没有重叠，那么该事项可以添加在日程表中。
        // 因此，需要高效地根据开始时间查找时间区间。可以根据时间区间的开始时间进行排序，通常排序之后能够优化查找的效率。
        // 如果用一个排序的动态数组（在Java中为ArrayList）来保存日程表中的时间区间，那么查找的时间复杂度是O（logn），但排序数组中插入新的时间区间的时间复杂度是O（n）。
        // 同样，也可以利用二叉搜索树来优化查找的效率。如果把时间区间存储到搜索二叉树中，那么在二叉搜索树中进行查找、插入和删除操作的时间复杂度都是O（logn）。
        // Java提供了TreeMap和TreeSet这两种二叉搜索树的数据结构，可以选择合适的类型来解决问题。
        // 由于每个时间区间都有开始时间和结束时间，也就是说，树的每个节点需要保存两个数字。一个简单的办法是用TreeMap。
        // 在TreeMap中，每个节点是一个映射，可以把时间区间的开始时间作为映射的键，把结束时间作为映射的值。


        // 解法1：注意本题目的设定是每一个事项是等长的。
        // 把日程表中的时间区间保存到一个名叫events的TreeMap中。
        // 每当添加一个新的开始时间为start、结束时间为end的时间区间时，就调用函数floorEntry查找日程表中开始时间小于start的最后一个时间区间，
        // 如果该时间区间的结束时间大于start，则表明该时间区间与待添加的时间区间重叠。
        // 接着调用函数ceilingEntry查找日程表中开始时间大于start的第1个时间区间，如果该时间区间的开始时间比end还要早，则表明这两个时间区间重叠。

        // 本题使用实现平衡的二叉排序树。Java实现的二叉排序树：TreeSet和TreeMap。每个区间有开始时间和结束时间两个元素，也就是每一个时间段要保存两个元素。
        // 在二叉排序树TreeMap中，每个节点是一个映射，可以把时间区间的开始时间作为映射的键，把结束时间作为映射的值。

        //private TreeMap<Integer, Integer> events;// 二叉排序树TreeMap: 时间区间的开始时间作为映射的键，把结束时间作为映射的值。
        //public MyCalendar() {// 构造器初始化对象
        //    events = new TreeMap<>();
        //}
        //
        //// 要遍历事项，所以应该获取的是键值对Entry。
        //public boolean book(int start, int end) {
        //    //  Map.Entry<K,V> floorEntry(K key) 返回小于或等于给定键的最大键关联的键 - 值映射，如果没有此键，则 null 。 也就是（-无穷，key]中的最大值对应的键值对
        //    // 要找出开始时间小于start的所有事项中开始最晚的一个
        //    Map.Entry<Integer, Integer> event = events.floorEntry(start);// 查找日程表中开始时间早于当前事项开始时间start的最后一个事项
        //    if (event != null && event.getValue() > start) {// 开始时间早于当前事项的最后一个事项的结束时间大于当前事项的开始时间，有重叠
        //        return false;
        //    }
        //
        //    // Map.Entry<K,V> ceilingEntry(K key) 返回与大于或等于给定键的最小键关联的键 - 值映射，如果没有此键，则 null 。也就是[key,+无穷)中的最小值对应的键值对
        //    // 要找出开始时间大于start的所有事项中开始最晚的一个
        //    event = events.ceilingEntry(start);// 查找日程表中开始时间迟于当前事项的第一个事项
        //    if (event != null && event.getKey() < end) {// 开始时间迟于当前事项的第一个时间区间的开始时间小于当前事项的结束时间，有重叠
        //        return false;
        //    }
        //    // 进行到这里，表明没有重叠，可以加入
        //    events.put(start, end);
        //    return true;
        //}



        //// 解法2：直接遍历
        //// 我们记录下所有已经预订的课程安排区间，当我们预订新的区间 [start,end)时，此时检查当前已经预订的每个日程安排是否与新日程安排冲突。
        //// 若不冲突，则可以添加新的日程安排。
        //// 对于两个区间[s1,e1） 和[s2,e2） 如果二者没有交集, 则此时应当满足s1>=e2 或者 s2>= e1,这就意味着如果满足 s1<e2 并且 s2<e1, 则两者会产生交集。这里使用的逆否命题

        List<int[]> booked;
        public MyCalendar() {
            booked = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for (int[] arr : booked) {// 注意这里要将[start,end)与已经插入的所有时间区间进行比较，只有待插入的时间区间不与已经插入的所有时间段重合时才可以将待插入时间段插入到集合中
                int left = arr[0];
                int right = arr[1];
                //if (end<=left || right<=start) {// 正面好理解，但是代码冗余，逆否命题转换
                //}else {
                //    return false;
                //}
                if (left < end && start < right) {
                    return false;
                }
            }
            booked.add(new int[]{start, end});
            return true;
        }



    }


/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
