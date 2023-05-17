/**
 * <p>给定两个字符串 <code><em>s</em></code> 和 <code><em>t</em></code> ，编写一个函数来判断 <code><em>t</em></code> 是否是
 * <code><em>s</em></code> 的字母异位词。</p>
 *
 * <p><strong>注意：</strong>若 <code><em>s</em></code> 和 <code><em>t</em></code><em> </em>中每个字符出现的次数都相同，则称
 * <code><em>s</em></code> 和 <code><em>t</em></code><em> </em>互为字母异位词。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> <em>s</em> = "anagram", <em>t</em> = "nagaram"
 * <strong>输出:</strong> true
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> <em>s</em> = "rat", <em>t</em> = "car"
 * <strong>输出: </strong>false</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length, t.length <= 5 * 10<sup>4</sup></code></li>
 * <li><code>s</code> 和 <code>t</code> 仅包含小写字母</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶: </strong>如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？</p>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 609</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 242
 * 有效的字母异位词
 *
 * @author wangweizhou
 * @date 2022-06-25 16:58:55
 */
public class ValidAnagram {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ValidAnagram().new Solution();
        String str1 = "asd";
        String str2 = "eas";
        boolean b = solution.isAnagram(str1, str2);
        System.out.println(b);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 由于变位词与字符出现的次数相关，因此可以用一个哈希表来存储每个字符出现的次数。哈希表的键是字符，而值是对应字符出现的次数。
        // 由于英文小写字母只有26个，因此可以用一个数组来模拟哈希表。


        ////	解法2：哈希表 写法1 数组模拟实现哈希表：数组的下表和哈希表的的键相对应，数组的值和哈希表的值相对应
        ////	t 是 s 的异位词等价于「两个字符串中字符出现的种类和次数均相等」。
        ////	数组下标表示26个字母：t.charAt(i)-'a'。
        ////	对应的数组值表示相应字母的出现次数：counts[t.charAt(i)-'a']。
        //
        //public boolean isAnagram(String s, String t) {
        //    if (s == null && t == null) {
        //        return true;
        //    }
        //    if ((s == null && t != null) || (s != null && t == null)) {
        //        return false;
        //    }
        //    if (s.length() != t.length()) {
        //        return false;
        //    }
        //
        //    int[] counts = new int[26];
        //    // 遍历字符串s并记录字符串中字符出现的次数
        //    for (int i = 0; i < s.length(); i++) {
        //        //  s.charAt(i)-'a'就是26个字母的下标，counts[s.charAt(i)-'a']就是数组值
        //        counts[s.charAt(i) - 'a']++;//遍历到一个就在对应数组位置+1
        //    }
        //    // 遍历字符串t并将字符串t中出现的字符次数-1
        //    for (int i = 0; i < t.length(); i++) {
        //        // 先减再判断需要小于0，小于零才说明存在字符的出现次数不一样
        //        counts[t.charAt(i) - 'a']--;
        //        if (counts[t.charAt(i) - 'a'] < 0) {
        //            return false;
        //        }
        //    }
        //    return true;
        //}



        // 解法2：哈希表 写法2 数组模拟实现哈希表：数组的下表和哈希表的的键相对应，数组的值和哈希表的值相对应
        public boolean isAnagram(String s, String t) {
            if (s == null && t == null) {
                return true;
            }
            if ((s == null && t != null) || (s != null && t == null) || s.length() != t.length()) {
                return false;
            }
            int[] counts = new int[26];
            for (char ch : s.toCharArray()) {
                counts[ch - 'a']++;
            }

            for (char ch : t.toCharArray()) {
                // 注意这里是先判断然后再次数相减，如果次数等于0，然后再减一次的话，那么就表明该字符的出现次数不一样
                if (counts[ch - 'a'] == 0) {
                    return false;
                }
                counts[ch - 'a']--;
            }
            return true;
        }




        ////	解法3：哈希表 HashMap实现
        ////	用哈希表维护对应字符的频次即可
        //public boolean isAnagram(String s, String t) {
        //    if(s==null&&t==null){
        //        return true;
        //    }
        //    if((s==null&&t!=null)||(s!=null&&t==null)||s.length()!=t.length()){
        //        return false;
        //    }
        //    Map<Character, Integer> map = new HashMap<>();
        //    // 遍历字符串s并记录字符串中字符出现的次数
        //    for (int i = 0; i < s.length(); i++) {
        //        char ch = s.charAt(i);
        //        map.put(ch, map.getOrDefault(ch, 0) + 1);//遍历到一个就在对应数组位置+1
        //    }
        //    // 遍历字符串t并将字符串t中出现的字符次数-1
        //    for (int i = 0; i < t.length(); i++) {
        //        char ch = t.charAt(i);
        //        map.put(ch, map.getOrDefault(ch, 0) - 1);
        //        if (map.get(ch) < 0) {
        //            return false;
        //        }
        //    }
        //    return true;
        //}





        //// 解法1：排序
        //// t 是 s 的异位词等价于「两个字符串排序后相等」。   字母异位词排序之后是相同的。
        //// 因此我们可以对字符串 s 和 t 分别排序，看排序后的字符串是否相等即可判断。
        //// 此外，如果 s 和 t 的长度不同，t 必然不是 s 的异位词。
        //public boolean isAnagram(String s, String t) {
        //    if ((s == null && t != null) || (s != null && t == null)) {
        //        return false;
        //    }
        //    if (s.length() != t.length()) {
        //        return false;
        //    }
        //    char[] str1 = s.toCharArray();
        //    char[] str2 = s.toCharArray();
        //    Arrays.sort(str1);
        //    Arrays.sort(str2);
        //    return Arrays.equals(str1, str2);
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
