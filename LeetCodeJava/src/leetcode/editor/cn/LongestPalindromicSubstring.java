/**
 * <p>给你一个字符串 <code>s</code>，找到 <code>s</code> 中最长的回文子串。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "babad"
 * <strong>输出：</strong>"bab"
 * <strong>解释：</strong>"aba" 同样是符合题意的答案。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "cbbd"
 * <strong>输出：</strong>"bb"
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 1000</code></li>
 * <li><code>s</code> 仅由数字和英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 5328</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 5
 * 最长回文子串
 *
 * @author wangweizhou
 * @date 2022-06-14 10:03:28
 */

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LongestPalindromicSubstring().new Solution();
        // 本地调试程序
        String str = "ccc";
        String ans = solution.longestPalindrome(str);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 结合5,125,647和680一起看
        // 解法2：中心扩散法  写法2
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            int len = s.length();
            int maxLen = 0;// 回文子串的最长长度maxLen
            int[] maxSub = new int[2];// 最长回文子串的长度maxSub
            for (int i = 0; i < len; i++) {// 遍历回文子串的每一个元素，以每一个元素为中心获得回文子串的起始区间
                // 遍历数组获取以位置i为中心的回文子串长度， 这一个是没确定中心，要么一个要么两个，同一个位置两种情况都计算取较大的
                int[] odd = centerSpreadInterval(s, i, i);//这个长度是奇数,两个中心位置指向同一个
                int[] even = centerSpreadInterval(s, i, i + 1);//这个长度是偶数，两个中心位置指向两个相邻的位置
                // 更新回文子串的最大长度和对应的最大长度对应的区间
                if (odd[1] - odd[0] + 1 > maxLen) {
                    maxSub = odd;
                    maxLen = odd[1] - odd[0] + 1;
                }
                if (even[1] - even[0] + 1 > maxLen) {
                    maxSub = even;
                    maxLen = even[1] - even[0] + 1;
                }
            }
            return s.substring(maxSub[0], maxSub[1] + 1);
        }



        // centerSpreadInterval（）返回值为以（left,right）为中心的回文子串的长度
        private int[] centerSpreadInterval(String str, int left, int right) {
            while (left >= 0 && right < str.length()) {
                if (str.charAt(left) == str.charAt(right)) {
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            // 上面循环结束，left和right要么越界，要么指向了不相等的两个元素，则实际的回文区间为[left+1,right-1]。
            // 下面这里要处理[left+1,right-1]的下标是否合法。
            if (left + 1 <= right - 1) {
                return new int[]{left + 1, right - 1};
            } else {
                return new int[]{-1, -2};
            }
        }




        //// 解法2：中心扩散法
        //public String longestPalindrome(String s) {
        //    if (s == null || s.length() < 2) {
        //        return s;
        //    }
        //    int len = s.length();
        //    int maxLen = 0;// 回文子串的最大长度
        //    int[] res = new int[2]; // 数组res第一位记录起始位置，第二位记录长度
        //    for (int i = 0; i <len- 1; i++) {
        //        // 遍历数组获取以位置i为中心的回文子串长度， 这一个是没确定中心，要么一个要么两个，同一个位置两种情况都计算取较大的
        //        //这个长度是奇数,两个中心位置指向同一个
        //        int[] odd = centerSpread(s, i, i);// 以位置i为回文子串中心的回文串长度，即回文子串的中心位置只有一个
        //        //这个长度是偶数，两个中心位置指向两个相邻的位置
        //        int[] even = centerSpread(s, i, i + 1);// 以位置i和（i+1）为回文子串中心的回文串长度，即回文子串的中心位置只有一个
        //        // max也是有两个元素的一维数组，第一个元素是回文串的开始位置，第二个元素是回文串的长度
        //        int[] max = odd[1] > even[1] ? odd : even;// 比较以位置i为中心的回文子串的长度
        //        if (max[1] > maxLen) {// 更新最大回文子串长度和回文子串的开始位置和最大长度。
        //            res = max;
        //            maxLen = max[1];
        //        }
        //    }
        //    return s.substring(res[0], res[0] + res[1]);
        //}
        //
        //
        //
        //// centerSpread() 返回值为回文子串的左边界【包含】，和回文子串的长度   参数left和right是回文子串的中心位置
        //private int[] centerSpread(String s, int left, int right) {
        //    int len = s.length();
        //    while (left >= 0 && right < len) {
        //        if (s.charAt(left) == s.charAt(right)) {
        //            left--;
        //            right++;
        //        } else {
        //            break;
        //        }
        //    }
        //    return new int[]{left + 1, right - left - 1};
        //}






        /*
        // 解法1：中心扩散法优化

        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }

            int start = 0;//最长回文子串的开始位置
            int end = 0;//最长回文子串的结束位置

            for (int i = 0; i < s.length(); i++) {
                // 这一个是没确定中心，要么一个要么两个，同一个位置两种情况都计算取较大的
                int length1 = expandAroundCenter(s, i, i);//这个长度是奇数,两个中心位置指向同一个
                int length2 = expandAroundCenter(s, i, i + 1);//这个长度是偶数，两个中心位置指向两个相邻的位置
                int length = Math.max(length1, length2);//子串长度的较大者
                //  更新最长回文子串长度
                if (length > end - start) {
                    //start和end举例发现规律
                    start = i - (length - 1) / 2;
                    end = i + length / 2;
                }
            }
            // API String substring(int beginIndex, int endIndex)
            // beginIndex - 起始索引，包括在内。endIndex - 结束索引，不包括。
            return s.substring(start, end + 1);
        }


        // 从中心位置[left,right]开始进行两侧扩展，计算回文子串长度
        public int expandAroundCenter(String s, int left, int right) {
            while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }

            return right - left - 1;
        }

        */





        /*

        // 解法1：中心扩散法 写法2
        // 中心点即 left 指针和 right 指针初始化指向的地方，可能是一个也可能是两个
        // 为什么有 2 * len - 1 个中心点？
        // aba 有5个中心点，分别是 a、b、a、ab、ba
        // abba 有7个中心点，分别是 a、b、b、a、ab、bb、ba

        // 数学归纳：字符串中心点的个数（2 * s.length() - 1）
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }

            int left=0;
            int right=0;
            int maxLen=1;
            int start=0;
            int end=0;
            for (int center = 0; center < 2*s.length()-1; center++) {
                left=center/2;
                right=left+center%2;
                while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
                    if((maxLen<right-left+1)){//更新最大长度，开始位置，结束位置
                        maxLen=right-left+1;
                        start=left;
                        end=right;
                    }
                    left--;
                    right++;
                }
            }
            return s.substring(start,end+1);
        }
        */




      /*
      // 解法3：双循环遍历
      //时间复杂度为 O（n³）,容易超时。
      // 获取每一个子字符串，判定是否为回文字符串并及时更新最大回文子串
        public String longestPalindrome(String s) {
            if (s == null || s.length() <2) {
                return s;
            }
            String ans = "";
            int max = 0;//回文子串的最长值
            int length = s.length();
            int left = 0;
            // String substring(int beginIndex, int endIndex)
            // beginIndex - 起始索引，包括在内。endIndex - 结束索引，不包括。
            for (int i = 0; i < length; i++) {//所有这一个不包含最后一个字符
                for (int j = i + 1; j < length + 1; j++) {
                    String subString = s.substring(i, j);
                    if (isPalindrome(subString) && (j - i + 1) > max) {
                        //是回文子串并且子串的长度大于已有子串的长度
                        //更新子串，更新最长回文子串长度
                        //left=i;
                        //max=j-i+1;
                        ans = s.substring(i, j);
                        max = subString.length();
                    }
                }
            }
            return ans;
        }


        public boolean isPalindrome(String s) {
            int length = s.length();
            for (int i = 0; i < length / 2; i++) {//是不是回文子串只需要判断前半截，偶数个是前一半，奇数个是中间数的前一半
                // public char charAt(int index)
                //返回指定索引处的char值。 指数范围为0至length() - 1 。 序列的第一个char值位于索引0 ，下一个位于索引1 ，依此类推，就像数组索引一样
                if (s.charAt(i) != s.charAt(length - 1 - i)) {
                    return false;
                }
            }
            return true;
        }
        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
