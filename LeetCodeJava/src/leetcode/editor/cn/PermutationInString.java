/**
 * <p>给你两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code> ，写一个函数来判断 <code>s2</code> 是否包含
 * <code>s1</code><strong>&nbsp;</strong>的排列。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p>换句话说，<code>s1</code> 的排列之一是 <code>s2</code> 的 <strong>子串</strong> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s1 = "ab" s2 = "eidbaooo"
 * <strong>输出：</strong>true
 * <strong>解释：</strong>s2 包含 s1 的排列之一 ("ba").
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s1= "ab" s2 = "eidboaoo"
 * <strong>输出：</strong>false
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
 * <div><div>Related Topics</div><div><li>哈希表</li><li>双指针</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍
 * 732</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 567
 * 字符串的排列
 *
 * @author wangweizhou
 * @date 2022-08-02 10:32:49
 */


public class PermutationInString {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new PermutationInString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 思路：
        // 变位词：原字符串的一种全排列，可能相同，可能不相同。
        // 变位词与字母及字母的出现次数有关，那么就统计字符串中包含的字母及每个字母出现的次数。

        // 由变位词的定义可知，变位词具有以下几个特点。首先，一组变位词的长度一定相同；其次，组成变位词的字母集合一定相同，并且每个字母出现的次数也相同。

        // 如果一个哈希表的键key是字母，而哈希表中的值value是对应字母出现的次数。
        // 由于这个题目强调字符串中只包含英文小写字母，而英文小写字母的个数是确定的，一共26个，因此可以用数组模拟一个简单的哈希表。数组下标就是哈希表的键，数组值就是哈希表的值。
        // 数组的下标0对应字母'a'，它的值对应字母'a'出现的次数。数组的下标1对应字母'b'，它的值对应字母'b'出现的次数。
        // 以此类推，数组的下标25对应字母'z'，它的值对应字母'z'出现的次数。


        // 下面在扫描字符串s1时是将该字符对应的计数器加1。在扫描字符串s2时，进入的字符对应的计数器减1，移出的字符对应的计数器加1。
        // 首先扫描字符串s1。每扫描到一个字符，就找到它在哈希表中的位置，并把它对应的值加1。
        // 然后考虑如何判断字符串s2中是否包含字符串s1的变位词。
        // 字符串s1的变位词和字符串s1的长度一样。假设字符串s1的长度是n，下面逐一判断字符串s2中长度为n的子字符串是不是字符串s1的变位词。
        // 判断的办法就是扫描子字符串中的每个字母，把该字母在哈希表中对应的值减1。如果哈希表中的所有值是0，那么该子字符串就是字符串s1的一个变位词。


        // 在字符串s2中可以用双指针来定位一个子字符串，其中一个指针指向子字符串的第1个字符，另一个指针指向子字符串的最后一个字符。
        // 接下来把这两个指针都向右移动1位，每次移动这两个指针时都相当于在原来的子字符串的最右边添加一个新的字符，并且从原来子字符串中删除最左边的字符。
        // 每当在子字符串中添加一个字符时，就把哈希表中对应位置的值减1。同样，每当在子字符串中删除一个字符时，就把哈希表中对应位置的值加1。



        //// 解法2：固定长度的滑动窗口  判断 s2 是否包含 s1 的排列
        //
        // 对于移入和移出的次数设定，这时候只要保证s1和s2的移入可以抵消，s2的移入和移出可以抵消。
        //// 对于s1中字符进入哈希表时对应值（+1）；对于s2中字符进入哈希表时对应值（-1），s2中字符移出哈希表时对应值（+1），前后对应，核心是s1和s2进入时数值的加减相反。

        //// 在保证区间长度为 len1 的情况下，去考察s2是否存在一个区间使得 counts的值全为 0。
        //// 通过指针移动，固定长度等于len1的滑动窗口右移，右边进入一个元素，左边出一个元素，每更新一个位置则检查一次滑动窗口中的元素，那么s2中右边移进的元素会在左边移出的时候抵消。
        //// 注意在移动过程中，若碰到子串，那么整个滑动窗口中都是子串的一个全排列，那么在之前移进又移出会抵消掉。
        //// 滑动窗口中的元素会和s1中移入的抵消掉。若没有碰到子串，哈希表中肯定有元素值不为0；


        public boolean checkInclusion(String s1, String s2) {
            if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || s1.length() > s2.length()) {//
                // 特殊情况处理
                return false;
            }
            // 数组模拟哈希表，数组下标就是哈希表的键key,数组值就是哈希表的值value。
            int[] counts = new int[26];// 数组模拟哈希表来记录字符数量，因为要存储所有的小写英文字母，所以这里数组长度是26。
            int len1 = s1.length();
            int len2 = s2.length();

            // 数组下标表示滑动窗口的范围。创建固定长度的滑动窗口。
            // 对于s1中字符进入哈希表时对应值加1；对于s2中字符进入哈希表时对应值减1，s2中字符移出哈希表时对应值加1。
            for (int i = 0; i < len1; i++) {// 循环遍历完，第一次形成固定长度滑动窗口。这里其实是将两个循环合并了。
                counts[s1.charAt(i) - 'a']++;// s1进入哈希表元素的对应值（+1）
                counts[s2.charAt(i) - 'a']--;// s2进入哈希表元素的对应值（-1）
            }

            // 当s1和s2都第一次进入哈希表中时，若s2 是否包含 s1 的排列，那么s2和s1中的字母和字母个数都相同，s1加数，s2减数，应该全部抵消了，所以全部应该是0。
            if (areAllZero(counts)) {//s1和s2都进入到哈希表中，检查是否每个元素都是0
                return true;
            }

            // 下面for循环的right指向滑动窗口的最右侧的指针【指向滑动窗口的最右侧元素】
            // 因为长度固定（right - len1）指向滑动窗口的最左侧的指针。

            // 通过指针移动，固定长度等于len1的滑动窗口右移，右边进入一个元素，左边出一个元素，每更新一个位置则检查一次滑动窗口中的元素，那么s2中右边移进的元素会在左边移出的时候抵消。
            for (int right = len1; right < len2; right++) {
                counts[s2.charAt(right) - 'a']--;// 当s2中元素进入滑动窗口时对应的哈希表元素的对应值（-1）
                counts[s2.charAt(right - len1) - 'a']++;// (right - len1)表示最早进入滑动窗口的元素。当s2中元素移出滑动窗口时对应的哈希表元素的对应值（-1）。
                if (areAllZero(counts)) {
                    return true;
                }
            }
            return false;
        }



        // 判断数组元素是否全是0，在遍历数组元素碰到元素0就及时判错。
        private boolean areAllZero(int[] counts) {
            if (counts == null || counts.length == 0) {//自己约定的特殊情况处理
                return true;
            }
            for (int count : counts) {
                if (count != 0) {
                    return false;
                }
            }
            return true;
        }





        // 解法4 ：长度可变的滑动窗口  要掌握
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


        //// 对于s1中字符进入哈希表时对应值（-1）；对于s2中字符进入哈希表时对应值（+1），s2中字符移出哈希表时对应值（-1），前后对应，核心是s1和s2进入时数值的加减相反。
        //public boolean checkInclusion(String s1, String s2) {
        //    if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || s1.length() > s2.length()) {//
        //    // 特殊情况处理
        //        return false;
        //    }
        //
        //    // 数组模拟哈希表，数组下标就是哈希表的键key,数组值就是哈希表的值value。
        //    int[] counts = new int[26];// 数组模拟哈希表来记录字符数量，因为要存储所有的小写英文字母，所以这里数组长度是26。
        //    int len1 = s1.length();
        //    int len2 = s2.length();
        //
        //    // s1移入counts的元素的对应值（-1），初始时，仅统计s1中的字符，则counts的值均不为正，且元素值之和为 -n
        //    for (int i = 0; i < len1; ++i) {
        //        --counts[s1.charAt(i) - 'a'];
        //    }
        //
        //    // 变长的滑动窗口 [left,right]，滑动窗口长度始终小于等于len1长度
        //    int left = 0;// left滑动窗口左边界
        //    for (int right = 0; right < len2; ++right) {// right是滑动窗口的右边界，指针right每向右移动一位，滑动窗口向右移动
        //        int curr = s2.charAt(right) - 'a';// right 每向右移动一次，就统计一次进入区间的字符 temp
        //        ++counts[curr];// s2移入counts的元素的对应值（+1）
        //        // 因为一旦出现counts[temp] > 0，那么说明temp所指向的元素不是s1中的元素或者该元素的个数比s1中对应的元素个数多，则长度可变的滑动窗口中不可能是所求的子串
        //        while (counts[curr] > 0) {// 若此时counts[x]>0，则向右移动左指针，减少离开区间的字符的 counts 值直到 counts[temp]≤0
        //            --counts[s2.charAt(left) - 'a'];// 向右移动左指针,即移出动态窗口的最左侧元素
        //            ++left;// 先移除元素再左指针右移
        //        }
        //        // 当 [left,right] 的长度恰好为 len1 时，就意味着counts 的元素值之和为 0
        //        if (right - left + 1 == len1) {
        //            return true;
        //        }
        //    }
        //    return false;
        //}


        //// 和上面的唯一不同
        //// s1移入counts的元素的对应值（+1），s2移入counts的元素的对应值（-1），s2移出counts的元素的对应值（+1）。前后需要对应
        //
        //public boolean checkInclusion(String s1, String s2) {
        //
        //    int[] counts = new int[26];// 数组模拟哈希表来记录字符数量，因为要存储所有的小写英文字母，所以这里数组长度是26。
        //    int len1 = s1.length();
        //    int len2 = s2.length();
        //
        //    // s1移入counts的元素的对应值（-1），初始时，仅统计s1中的字符，则counts的值均不为正，且元素值之和为 -n
        //    for (int i = 0; i < len1; ++i) {
        //        ++counts[s1.charAt(i) - 'a'];
        //    }
        //
        //    int left = 0;
        //    for (int right = 0; right < len2; right++) {
        //        counts[s2.charAt(right) - 'a']--;
        //
        //        while (counts[s2.charAt(right) - 'a'] < 0) {
        //            counts[s2.charAt(left) - 'a']++;
        //            left++;
        //        }
        //        if (right - left + 1 == len1) {
        //            return true;
        //        }
        //    }
        //    return false;
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}


