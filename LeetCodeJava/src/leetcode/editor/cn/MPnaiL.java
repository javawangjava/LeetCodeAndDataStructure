/**
 * <p>给定两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code>，写一个函数来判断 <code>s2</code> 是否包含
 * <code>s1</code><strong>&nbsp;</strong>的某个变位词。</p>
 *
 * <p>换句话说，第一个字符串的排列之一是第二个字符串的 <strong>子串</strong> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s1 = &quot;ab&quot; s2 = &quot;eidbaooo&quot;
 * <strong>输出: </strong>True
 * <strong>解释:</strong> s2 包含 s1 的排列之一 (&quot;ba&quot;).
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s1= &quot;ab&quot; s2 = &quot;eidboaoo&quot;
 * <strong>输出:</strong> False
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s1.length, s2.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>s1</code> 和 <code>s2</code> 仅包含小写字母</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><meta charset="UTF-8" />注意：本题与主站 567&nbsp;题相同：&nbsp;
 * <a href="https://leetcode-cn.com/problems/permutation-in-string/">https://leetcode-cn.com/problems/permutation-in-string/</a></p>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>双指针</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍
 * 49</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 *
 * 字符串中的变位词
 *
 * @author wangweizhou
 * @date 2022-06-23 01:28:23
 */

public class MPnaiL {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MPnaiL().new Solution();
        String str1 = "ab";
        String str2 = "eidbaooo";
        boolean a = solution.checkInclusion(str1, str2);
        System.out.println(a);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 变位词：原字符串的一种全排列，可能相同，可能不相同
        // 变位词与字母及字母的出现次数有关，那么就统计字符串中包含的字母及每个字母出现的次数
        // 如果一个哈希表的键是字母，而哈希表中的值对应字母出现的次数
        // 因为题目中只有小写英文字母，所以可以用数组模拟一个简答的哈希表，数组下标表示对应字母，数组值表示对应字母出现的次数
        // 数组的下标0对应字母‘a’,它的值对应字母‘a’出现的次数。数组的下标1对应字母‘b’,它的值对应字母‘b’出现的次数。依次类推。


        // 解法4 ：长度可变的滑动窗口  要掌握 在保证 counts 的值不为正的情况下，去考察是否存在一个区间，其长度恰好为 len1
        // s1移入counts的元素的对应值（-1），s2移入counts的元素的对应值（+1），s2移出counts的元素的对应值（-1）。前后需要对应
        // 变位词与字母及字母的出现次数有关，那么就统计字符串中包含的字母及每个字母出现的次数。

        //解题思路：
        // 1.初始时，仅统计s1中的字符，则counts的值均不为正，且元素值之和为 -len1。
        // 2.然后用两个指针 left 和 right 表示考察的区间 [left,right]。right 每向右移动一次，就统计一次进入区间的字符 x，进入区间的字符的 counts 值（+1）。为保证 counts
        // 的值不为正，也就是保持counts数组元素恒小于等于0。
        // 2.1若此时counts[x]>0，则向右移动左指针，离开区间的字符的 counts 值（-1）直到 counts[x]≤0。
        // 3.注意到 [left,right] 的长度每增加 1，counts 的元素值之和就增加 1。当 [left,right] 的长度恰好为 n 时，就意味着counts 的元素值之和为 0。由于counts
        // 的值不为正，元素值之和为
        // 0 就意味着所有元素均为 0，这样我们就找到了一个目标子串。


        public boolean checkInclusion(String s1, String s2) {
            if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {//特殊情况处理
                return false;
            }
            if (s1.length() > s2.length()) {//s1比s2长
                return false;
            }
            int len1 = s1.length(), len2 = s2.length();
            int[] counts = new int[26];//哈希表来记录字符数量

            //s1移入counts的元素的对应值（-1），初始时，仅统计s1中的字符，则counts的值均不为正，且元素值之和为 -n
            for (int i = 0; i < len1; ++i) {
                --counts[s1.charAt(i) - 'a'];
            }

            int left = 0;
            //滑动窗口 [left,right]，滑动窗口长度始终小于等于len1长度
            for (int right = 0; right < len2; ++right) {
                int temp = s2.charAt(right) - 'a';//right 每向右移动一次，就统计一次进入区间的字符 temp
                //s2移入counts的元素的对应值（+1）
                ++counts[temp];
                while (counts[temp] > 0) {//若此时counts[x]>0，则向右移动左指针，减少离开区间的字符的 counts 值直到 counts[temp]≤0
                    --counts[s2.charAt(left) - 'a'];//向右移动左指针,即移出动态窗口的最左侧元素
                    ++left;//先移除元素再左指针右移
                }
                //当 [left,right] 的长度恰好为 len1 时，就意味着counts 的元素值之和为 0
                if (right - left + 1 == len1) {
                    return true;
                }
            }
            return false;
        }






       /*
        // 解法2： 固定长度的滑动窗口  要掌握
        // 在保证区间长度为 len1 的情况下，去考察是否存在一个区间使得 counts的值全为 0
        // 对于s1中字符进入哈希表时对应值（+1）；对于s2中字符进入哈希表时对应值（-1），s2中字符移出哈希表时对应值（+1），前后对应，核心是s1和s2进入时数值的加减相反。

        public  boolean checkInclusion(String s1, String s2) {
            if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {//特殊情况处理
                return false;
            }
            if (s1.length() > s2.length()) {
                return false;
            }

            int[] counts = new int[26];//哈希表来记录字符数量
            int len1 = s1.length();
            int len2 = s2.length();
            //将s1和s2都加入到计数数组counts中
            // 对于s1中字符进入哈希表时对应值加1；对于s2中字符进入哈希表时对应值减1，s2中字符移出哈希表时对应值加1
            for (int i = 0; i < len1; i++) {
                counts[s1.charAt(i) - 'a']++;//s1进入哈希表元素的对应值（+1）
                counts[s2.charAt(i) - 'a']--;//s2进入哈希表元素的对应值（-1）
            }
            if (areAllZero(counts)) {//s1和s2都进入到哈希表中，检查是否每个元素都是0
                return true;
            }
            //通过指针移动，固定长度的滑动窗口右移，
            //下面for循环的i指向滑动窗口的最右侧的指针，指向子字符串的最后一个字符
            //（i - len1）指向滑动窗口的最左侧的指针，指向子字符串的第一个字符
            for (int i = len1 ; i < len2; i++) {
                 //根据这个循环的设定，每次进入循环体的时候，
                // 注意这里：先将长度为len2的滑动窗口的长度临时（+1），右端点加入新的元素。
                // 因为右端点right相对于前一个滑动窗口的右端点，先把右指针后移一位（+1），所以移出左端点恢复定长时左端点表示为（right-len2)。计算左端点再不需要额外（+1）
                counts[s2.charAt(i) - 'a']--;////s2进入哈希表元素的对应值（-1）
                counts[s2.charAt(i - len1) - 'a']++;//s2移出哈希表元素的对应值（-1）。滑动窗口最左边的移出
                if (areAllZero(counts)) {
                    return true;
                }
            }
            return false;
        }

        //判断数组元素是否全是0
        private boolean areAllZero(int[] counts) {
            for (int count : counts) {
                if (count != 0) {
                    return false;
                }
            }
            return true;
        }

*/







        /*

        // 解法3： 逻辑上没有24好理解

        // 变位词与字母及字母的出现次数有关，那么就统计字符串中包含的字母及每个字母出现的次数
        // 如果一个哈希表的键是字母，而哈希表中的值对应字母出现的次数
        // 因为题目中只有小写英文字母，所以可以用数组模拟一个简答的哈希表，数组下标表示对应字母，数组值表示对应字母出现的次数
        // 数组的下标0对应字母‘a’,它的值对应字母‘a’出现的次数。数组的下标1对应字母‘b’,它的值对应字母‘b’出现的次数。依次类推。
        // 对于s1中字符进入哈希表时对应值（+1）；对于s2中字符进入哈希表时对应值（-1），s2中字符移出哈希表时对应值（+1），前后对应，核心是s1和s2进入时数值的加减相反。

        // 字符串s2中是否有s1的变位词
        public boolean checkInclusion(String s1, String s2) {
            if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
                return false;
            }
            if(s1.length()>s2.length()){
                return false;
            }
            char[] str1 = s1.toCharArray();
            char[] str2 = s2.toCharArray();
            int len1 = s1.length();//
            int len2 = s2.length();
            int[] charCount = new int[26]; // 【总欠账表】：s1的词频表
            for (char c : str1) { // 统计s1的词频
                charCount[c - 'a']++;
            }
            int l = 0, r = 0; // 滑动窗口左右边界
            // 依次尝试固定以s2中的每一个位置l作为左端点开始的len1长度的子串s2[l ... l+len1)是否是s1的排列，
            while (l <= len2 - len1) { // 因为要保证变位词的长度等于len1，所以固定左端点只需要尝试到len2-len1即可
                // 右边界s2[r]字符进入窗口【还账】
                // r < l + len1保证滑动窗口长度不超过len1。charCount[str2[r] - 'a'] >= 1表示右端点对应值大于等于1
                while (r < l + len1 && charCount[str2[r] - 'a'] >= 1) {
                    charCount[str2[r] - 'a']--; // 右边界s2[r]字符进入窗口，对应值（-1）
                    r++;
                }
                if (r == l + len1) return true;
                // 左边界s2[l]字符出窗口【赊账】，l++，开始尝试固定下一个位置做左端点
                charCount[str2[l] - 'a']++; // 左边界s2[l]字符出窗口，对应值（+1）
                l++;
            }
            return false; // 所有的左端点均尝试还账失败，不可能再有答案了
        }
        */




            /*

        // 解法1：固定长度的动态窗口滑动+两个数组    超时
        // 固定长度的动态窗口滑动+两个数组
        public  boolean checkInclusion(String s1, String s2) {
            if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
                return false;
            }
            int len1 = s1.length();
            int len2 = s2.length();
            if (len1 > len2 ) {   // s1比s2长则s1必不可能是s2子串
                return false;
            }
            // 窗口大小固定为len1进行滑动。
            // 记录当前窗口下两个字符串的字符分布：
            // 其中count1在首次填充后不再更改，仅用作对照。
            int[] count1=new int[26];
            int[] count2=new int[26];

            int right=0;
            // 同时填充两个字符表，其中count1填充完毕，count2只填充了前面一部分。
            // s1和s2进度滑动窗口都是元素对应的数值（+1），s2移出滑动窗口都是元素对应的数值（-1）
            while(right<len1){
                count1[s1.charAt(right)-'a']++;
                count2[s2.charAt(right)-'a']++;
                right++;
            }

            if(Arrays.equals(count1,count2)){// 起始状态先判断一次
                return true;
            }
            // len1长窗口在s2上向右滑动。
            while(right<len2){
                // 更新抛弃的左端点和新加入的右端点。
                // s1和s2进度滑动窗口都是元素对应的数值（+1），s2移出滑动窗口都是元素对应的数值（-1）
                count2[s2.charAt(right)-'a']++;
                count2[s2.charAt(right-len1)-'a']--;
                // 判断当前状态下，s2窗口中的字符是否和s1的字符种类和数量都一致。
                // Arrays.equals() 是通过遍历判断的，自己写for也一样。

                if(Arrays.equals(count1,count2)){
                    return true;
                }
            }
            return false; // 到这里都没出现配对的情况，说明必不可能了。
        }
        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
