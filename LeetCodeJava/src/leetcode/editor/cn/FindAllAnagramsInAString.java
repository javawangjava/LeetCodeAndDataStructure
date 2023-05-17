/**
 * <p>给定两个字符串&nbsp;<code>s</code>&nbsp;和 <code>p</code>，找到&nbsp;<code>s</code><strong>&nbsp;</strong>中所有&nbsp;
 * <code>p</code><strong>&nbsp;</strong>的&nbsp;<strong>异位词&nbsp;</strong>的子串，返回这些子串的起始索引。不考虑答案输出的顺序。</p>
 *
 * <p><strong>异位词 </strong>指由相同字母重排列形成的字符串（包括相同的字符串）。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "cbaebabacd", p = "abc"
 * <strong>输出: </strong>[0,6]
 * <strong>解释:</strong>
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * </pre>
 *
 * <p><strong>&nbsp;示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "abab", p = "ab"
 * <strong>输出: </strong>[0,1,2]
 * <strong>解释:</strong>
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length, p.length &lt;= 3 * 10<sup>4</sup></code></li>
 * <li><code>s</code>&nbsp;和&nbsp;<code>p</code>&nbsp;仅包含小写字母</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 969</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 438
 * 找到字符串中所有字母异位词
 *
 * @author wangweizhou
 * @date 2022-08-13 00:08:50
 */

public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new FindAllAnagramsInAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 思路：
        // 变位词：原字符串的一种全排列，可能相同，可能不相同
        // 变位词与字母及字母的出现次数有关，那么就统计字符串中包含的字母及每个字母出现的次数。
        // 由变位词的定义可知，变位词具有以下几个特点。首先，一组变位词的长度一定相同；其次，组成变位词的字母集合一定相同，并且每个字母出现的次数也相同。
        // 而变位词与字母及字母出现的次数有关，那么就应该统计字符串中包含的字母及每个字母出现的次数。
        // 如果一个哈希表的键是字母，而哈希表中的值是对应字母出现的次数，那么这样一个哈希表很适合用来统计字符串中每个字母出现的次数。
        // 由于这个题目强调字符串中只包含英文小写字母，而英文小写字母的个数是确定的，一共26个，因此可以用数组模拟一个简单的哈希表。
        // 数组的下标0对应字母'a'，它的值对应字母'a'出现的次数。数组的下标1对应字母'b'，它的值对应字母'b'出现的次数。
        // 以此类推，数组的下标25对应字母'z'，它的值对应字母'z'出现的次数。


        // 解法1 ：长度可变的滑动窗口  要掌握
        // s1移入counts的元素的对应值（-1），s2移入counts的元素的对应值（+1），s2移出counts的元素的对应值（-1）。前后需要对应。
        // 在保证 counts的值不为正的情况下，去考察是否存在一个区间，其长度恰好为 len1
        // 变位词与字母及字母的出现次数有关，那么就统计字符串中包含的字母及每个字母出现的次数。

        //解题思路：
        // 1.初始时，仅统计s1中的字符，则counts的值均不为正，且元素值之和为 -len1。
        // 2.然后用两个指针 left 和 right 表示考察的区间 [left,right]。right 每向右移动一次，就统计一次进入区间的字符 x，进入区间的字符的 counts 值（+1）。
        // 为保证 counts 的值不为正，也就是保持counts数组元素恒小于等于0。
        // 2.1若此时counts[x]>0，则向右移动左指针，离开区间的字符的 counts 值（-1）直到 counts[x]≤0。
        // 因为一旦出现counts[temp] > 0，那么说明temp所指向的元素不是s1中的元素或者该元素的个数比s1中对应的元素个数多，则长度可变的滑动窗口中不可能是所求的子串
        // 3.注意到 [left,right] 的长度每增加 1，counts 的元素值之和就增加 1。当 [left,right] 的长度恰好为 n 时，就意味着counts 的元素值之和为 0。
        // 由于counts的值不为正，元素值之和为0 就意味着所有元素均为 0，这样我们就找到了一个目标子串。


        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> list = new ArrayList<>();
            if (s == null || p == null || s.length() == 0 || p.length() == 0 || s.length() < p.length()) {
                return list;
            }
            int len1 = p.length();
            int len2 = s.length();
            int[] counts = new int[26];

            //s1移入counts的元素的对应值（-1），初始时，仅统计s1中的字符，则counts的值均不为正，每个元素对应值为非正值
            for (int i = 0; i < len1; i++) {
                counts[p.charAt(i) - 'a']--;
            }

            //滑动窗口 [left,right]，滑动窗口长度始终小于等于len1长度
            int left = 0;
            for (int right = 0; right < len2; right++) {
                //s移入counts的元素的对应值（+1）
                counts[s.charAt(right) - 'a']++;//将s中的元素逐个加入到counts数组中
                // 因为一旦出现counts[temp] > 0，那么说明temp所指向的元素不是s1中的元素或者该元素的个数比s1中对应的元素个数多，则长度可变的滑动窗口中不可能是所求的子串
                while (counts[s.charAt(right) - 'a'] > 0) {//若此时counts[x]>0，则向右移动左指针，减少离开区间的字符的 counts 值直到
					// counts[temp]≤0
                    //先移除元素再左指针右移
                    counts[s.charAt(left) - 'a']--;//向右移动左指针,即移出动态窗口的最左侧元素
                    left++;
                }

                //当 [left,right] 的长度恰好为 len1 时，就意味着counts 的元素值之和为 0
                if (len1 == right - left + 1) {
                    list.add(left);
                }
            }
            return list;
        }




        /*

     	//// 解法2：固定长度的滑动窗口  判断 s2 是否包含 s1 的排列
        //// 在保证区间长度为 len1 的情况下，去考察s2是否存在一个区间使得 counts的值全为 0。
        //// 通过指针移动，固定长度等于len1的滑动窗口右移，右边进入一个元素，左边出一个元素，每更新一个位置则检查一次滑动窗口中的元素，那么s2中右边移进的元素会在左边移出的时候抵消。
        //// 注意在移动过程中，若碰到子串，那么整个滑动窗口中都是子串的一个全排列，那么在之前移进又移出会抵消掉。
        //// 滑动窗口中的元素会和s1中移入的抵消掉。若没有碰到子串，哈希表中肯定有元素值不为0；
        //
        //// 对于s1中字符进入哈希表时对应值（+1）；对于s2中字符进入哈希表时对应值（-1），s2中字符移出哈希表时对应值（+1），前后对应，核心是s1和s2进入时数值的加减相反。

        public List<Integer> findAnagrams(String s, String p) {

            List<Integer> list = new ArrayList<>();
            if (s == null || p == null || s.length() == 0 || p.length() == 0) {//判空
                return list;
            }
            if (p.length() > s.length()) {//
                return list;
            }
            int len1 = s.length();
            int len2 = p.length();
            int[] counts = new int[26];//数组模拟哈希表

            // 将s1和s2都加入到计数数组counts中
            // 对于s1中字符进入哈希表时对应值加1；对于s2中字符进入哈希表时对应值减1，s2中字符移出哈希表时对应值加1
            for (int i = 0; i < len2; i++) {
                counts[p.charAt(i) - 'a']++;
                counts[s.charAt(i) - 'a']--;
            }
            //循环结束，滑动窗口的长度确定了
            if (areAllZeros(counts)) {//s1和s2都进入到哈希表中，检查是否每个元素都是0
                list.add(0);
            }

             // 下面for循环的right指向滑动窗口的最右侧的指针，指向滑动窗口的最右侧元素
            // 因为长度固定（right - len1）指向滑动窗口的最左侧的指针。
            for (int right = len2; right < len1; right++) {
                //根据这个循环的设定，每次进入循环体的时候，
                // 注意这里：先将长度为len2的滑动窗口的长度临时（+1），右端点加入新的元素。
                // 因为右端点right相对于前一个滑动窗口的右端点，先把右指针后移一位（+1），所以移出左端点恢复定长时左端点表示为（right-len2)。计算左端点再不需要额外（+1）
                counts[s.charAt(right) - 'a']--;
                counts[s.charAt(right-len2)-'a']++;

                if(areAllZeros(counts)){//每次滑动窗口变化一次就判断一次
                    //注意一下和上面的不同闭区间[left,right]  区间长度（right-left+1）
                    //这里区间已经完成了移出操作，区间长度变为原来的len2。那么right-left+1=len2，移项后得到左端点
                    list.add(right-len2+1);//注意这里的左边界
                }
            }
            return list;
        }

        //判断数组元素是否全是0
        private boolean areAllZeros(int[] counts) {
            for (int count : counts) {
                if (count != 0) {
                    return false;
                }
            }
            return true;
        }

        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
