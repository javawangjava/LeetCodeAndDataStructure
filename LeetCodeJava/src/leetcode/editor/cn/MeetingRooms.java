/**
 * <p>给定一个会议时间安排的数组 <code>intervals</code> ，每个会议时间都会包括开始和结束的时间 <code>intervals[i] = [start<sub>i</sub>,
 * end<sub>i</sub>]</code> ，请你判断一个人是否能够参加这里面的全部会议。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>intervals = [[0,30],[5,10],[15,20]]
 * <strong>输出</strong>：false
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>intervals = [[7,10],[2,4]]
 * <strong>输出</strong>：true
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= intervals.length <= 10<sup>4</sup></code></li>
 * <li><code>intervals[i].length == 2</code></li>
 * <li><code>0 <= start<sub>i</sub> < end<sub>i</sub> <= 10<sup>6</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>排序</li></div></div><br><div><li>👍 129</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 252
 * 会议室
 * @author wangweizhou
 * @date 2022-09-02 20:51:26
 */

public class MeetingRooms {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MeetingRooms().new Solution();
        int[][] intervals = { {15, 20},{0, 30}, {5, 10},{5, 8}};
        for(int[] interval:intervals){
            System.out.println(Arrays.toString(interval));
        }

        System.out.println(solution.canAttendMeetings(intervals));
        for(int[] interval:intervals){
            System.out.println(Arrays.toString(interval));
        }
    }

//leetcode submit region begin(Prohibit modification and deletion)

    class Solution {


        //	解法1：  排序
        public boolean canAttendMeetings(int[][] intervals) {
            if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {// 判空
                return true;
            }
            int len = intervals.length;
            //Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // lambda表达式

            // 二维数组排序
            Arrays.sort(intervals, new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    //a[i]里面的i指的是按照每一行的第i列进行排序
                    if(a[0]==b[0]){
                        return a[1]-b[1];
                    }else{
                        return a[0] - b[0];
                    }
                }
            });


            // 二维数组按照第一维升序和第二维升序排序之后，
            for (int i = 0; i < len - 1; i++) {
                // 比较前一个的右边界是否在后一个的左边界之前。
                if (intervals[i][1] > intervals[i + 1][0]) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
