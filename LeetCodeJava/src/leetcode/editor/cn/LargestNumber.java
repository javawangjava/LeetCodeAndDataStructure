/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * <p>
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [10,2]
 * 输出："210"
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10⁹
 * <p>
 * <p>
 * Related Topics 贪心 数组 字符串 排序 👍 1086 👎 0
 */

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 179
 * 最大数
 * @author wangweizhou
 * @date 2023-02-27 16:02:52
 */
public class LargestNumber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LargestNumber().new Solution();
        int[] nums = {0, 0};
        String str = solution.largestNumber(nums);
        System.out.println(str);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 通过字符串拼接来定义两个字符串的大小关系，然后进行拼接
        public String largestNumber(int[] nums) {
            if (nums == null || nums.length == 0) {
                return null;
            }
            int len = nums.length;

            // 创建一个字符串数组，并将整数数组转换为字符串数组
            String[] strs = new String[len];
            for (int i = 0; i < nums.length; i++) {
                strs[i] = String.valueOf(nums[i]);
            }

            //Arrays.sort(strs,(o1,o2)->(o2+o1).compareTo(o1+o2));// Lambda表达式实现排序
            // 使用匿名内部类按照字典序对拼接的字符串进行排序
            Arrays.sort(strs, new Comparator<String>() {// 在对字符串进行排序时自定义排序规则
                @Override
                public int compare(String o1, String o2) {
                    return (o2 + o1).compareTo(o1 + o2);// 字符串可以进行拼接
                }
            });

            // 求最大数，那么如果一个数的最高位就是0，则该数就是0。
            if (strs[0].equals("0")) {// 字符串是引用类型变量，使用a.equals(b)进行判断
                return "0";
            }

            // 将字符串数组拼接形成可变字符串，然后可变字符串转换为字符串
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                sb.append(strs[i]);
            }
            return sb.toString();
        }





    }
//leetcode submit region end(Prohibit modification and deletion)

}
