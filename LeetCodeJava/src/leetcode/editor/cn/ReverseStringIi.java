/**
 * <p>给定一个字符串 <code>s</code> 和一个整数 <code>k</code>，从字符串开头算起，每计数至 <code>2k</code> 个字符，就反转这 <code>2k</code> 字符中的前
 * <code>k</code> 个字符。</p>
 *
 * <ul>
 * <li>如果剩余字符少于 <code>k</code> 个，则将剩余字符全部反转。</li>
 * <li>如果剩余字符小于 <code>2k</code> 但大于或等于 <code>k</code> 个，则反转前 <code>k</code> 个字符，其余字符保持原样。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "abcdefg", k = 2
 * <strong>输出：</strong>"bacdfeg"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "abcd", k = 2
 * <strong>输出：</strong>"bacd"
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>s</code> 仅由小写英文组成</li>
 * <li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 319</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 541
 * 反转字符串 II
 *
 * @author wangweizhou
 * @date 2022-07-02 00:27:07
 */
public class ReverseStringIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ReverseStringIi().new Solution();
        String a = "abcdefg";
        String ans = solution.reverseStr(a, 2);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 方法1：
        /*
        public String reverseStr(String s, int k) {
            if (s == null || s.length() == 0) {
                return s;
            }
            char[] ch = s.toCharArray();
            int length = ch.length;

            for (int i = 0; i < length / (2 * k); i++) {
                int left = i * 2 * k;
                int right = i * 2 * k + k - 1;
                while (left < right) {
                    swap(ch, left, right);
                    left++;
                    right--;
                }
            }
            int rest = length - 2 * k * (length /(2 * k));
            if (rest < k) {
                int left = 2 * k * (length / (2 * k));
                int right = length - 1;
                while (left < right) {
                    swap(ch, left, right);
                    left++;
                    right--;
                }
            }
            if (rest >= k && rest < 2 * k) {
                int left = 2 * k * (length / (2 * k));
                int right = left + k - 1;
                while (left < right) {
                    swap(ch, left, right);
                    left++;
                    right--;
                }
            }
            return String.valueOf(ch);
        }

        private void swap(char[] ch, int i, int j) {
            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
        }*/



        // 方法2：
        public String reverseStr(String s, int k) {
            int n = s.length();
            char[] arr = s.toCharArray();
            for (int i = 0; i < n; i += 2 * k) {//每2k个反转前面k个，
                // 除最后一轮外，i+k<n.最后一轮都有可能
                reverse(arr, i, Math.min(i + k, n) - 1);//Math.min(i + k, n)就是对剩余字符的处理
            }
            return new String(arr);
        }

        public void reverse(char[] arr, int left, int right) {
            while (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
