/**
 * <p>某种外星语也使用英文小写字母，但可能顺序 <code>order</code> 不同。字母表的顺序（<code>order</code>）是一些小写字母的排列。</p>
 *
 * <p>给定一组用外星语书写的单词 <code>words</code>，以及其字母表的顺序 <code>order</code>，只有当给定的单词在这种外星语中按字典序排列时，返回 <code>true</code>；否则，返回
 * <code>false</code>。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * <strong>输出：</strong>true
 * <strong>解释：</strong>在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * <strong>输出：</strong>false
 * <strong>解释：</strong>在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * <strong>输出：</strong>false
 * <strong>解释：</strong>当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（<a href="https://baike.baidu.com/item/%E5%AD%97%E5%85%B8%E5%BA%8F" target="_blank">更多信息</a>）。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= words.length <= 100</code></li>
 * <li><code>1 <= words[i].length <= 20</code></li>
 * <li><code>order.length == 26</code></li>
 * <li>在 <code>words[i]</code> 和 <code>order</code> 中的所有字符都是英文小写字母。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 217</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 953
 * 验证外星语词典
 *
 * @author wangweizhou
 * @date 2022-08-28 14:36:34
 */

public class VerifyingAnAlienDictionary {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new VerifyingAnAlienDictionary().new Solution();
        String[] words = {"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        boolean ans = solution.isAlienSorted(words, order);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public boolean isAlienSorted(String[] words, String order) {
            if(words==null||words.length==0||order==null|order.length()==0){
                return true;
            }
            int orderLen=order.length();
            int[] orderMap=new int[orderLen];
            for (int i = 0; i <orderLen ; i++) {
                orderMap[order.charAt(i)-'a']=i;
            }

            for (int i = 0; i <words.length-1 ; i++) {
                if(!getOrder(words[i],words[i+1],orderMap)){
                    return false;
                }
            }
            return true;
        }



        private boolean getOrder(String word1,String word2,int[] orderMap){
            int len1=word1.length();
            int len2=word2.length();
            int index=0;
            while (index<len2&&index<len1){
                char ch1=word1.charAt(index);
                char ch2= word2.charAt(index);
                if(ch1==ch2){
                    index++;
                }else if(ch1!=ch2){
                    if(orderMap[ch1-'a']<orderMap[ch2-'a']){
                        return true;
                    }else {
                        return false;
                    }
                }
            }
            return index==len1;
        }




        // 目前字母表的顺序由一个输入的字符串决定。
        // 在确定单词排序的顺序时，只要找到两个单词的第一个不同的字母在字母表中的先后顺序就可以确定两个单词的先后顺序。
        // 为了方便查找每个字母在字母表中的顺序，可以创建一个哈希表，哈希表的键为字母表的每个字母，而值为字母在字母表中的顺序。
        // 由于字母表中的字母数目是固定的，总共26个，因此可以用一个长度为26的数组来模拟哈希表，数组的下标对应哈希表的键，而数组的值对应哈希表的值。

        // 创建哈希表：哈希表的键key为字母表的每个字母，而值value为字母在字母表中的顺序【字母表中的下标】。
        // 使用数组来模拟哈希表：数组的下标对应哈希表的键，即表示字母表中的每一个字母【order.charAt(i) - 'a'，将字母转换为数组下标】，
        // 数组值对应哈希表的值，即表示字母在字母表中的顺序。


        //// 解法1：数组模拟哈希表
        //public boolean isAlienSorted(String[] words, String order) {
        //    if (words == null || words.length == 0 || order == null || order.length() == 0) {
        //        return false;
        //    }
        //
        //    // 将指定的顺序字符串转换为顺序字符数组
        //    int[] orderArray = new int[order.length()];
        //    // 建立数组来模拟哈希表，数组的下标对应哈希表的键，即表示字母表中的每一个字母，数组值对应哈希表的值，即表示字母在字母表中的顺序
        //    for (int i = 0; i < order.length(); i++) {
        //        // order.charAt(i)表示顺序表的第i个字符，order.charAt(i) - 'a'只是相对与字符'a'而言，将字符转换为对应的数字
        //        orderArray[order.charAt(i) - 'a'] = i;
        //    }
        //
        //    // 比较单词数组中两个相邻的单词
        //    for (int i = 0; i < words.length - 1; i++) {
        //        if (!isSorted(words[i], words[i + 1], orderArray)) {
        //            return false;
        //        }
        //    }
        //    return true;
        //}
        //
        //
        //
        //// 排序： 找出两个单词的第一个不同的字母，然后根据第一个不同的字母在字母的顺序表order中的顺序来确定两个单词的顺序。
        //
        //// 为了判断两个单词是否是按照字母表的顺序排序的，可以扫描两个单词中的字母找出第1个不相同的字母。
        //// 哪个单词的第1个不相同的字母在字母表中的位置靠前，排序的时候它就排在前面。
        //// 如果没有找到不相同的字母，那么短的单词在排序的时候应该排在前面。
        //
        //// 判断两个单词是否按照字母表的顺序排序，判断word1是否在word2之前
        //private boolean isSorted(String word1, String word2, int[] orderArray) {
        //    int i = 0;
        //    for (; i < word1.length() && i < word2.length(); i++) {
        //        char ch1 = word1.charAt(i);// word1单词的第i个字母
        //        char ch2 = word2.charAt(i);
        //        // 比较两个单词的第i个字母在指定顺序表中的先后顺序，数组下标为整数所以要将字符转换为整数
        //        if (orderArray[ch1 - 'a'] < orderArray[ch2 - 'a']) {//
        //            return true;
        //        } else if (orderArray[ch1 - 'a'] > orderArray[ch2 - 'a']) {
        //            return false;
        //        }
        //    }
        //    // 到这里至少有一个字符串已经遍历完了，字符串短的应该在前面。
        //    // 如果i == word1.length()则表明word1比word2短，短的在前面也就是word1在前面,那么返回true。
        //    // 如果i!=word1,length()则表明word2短，则word2在前面，那么返回false.
        //    return i == word1.length();
        //}





        //// 解法1：哈希表 数组模拟哈希表  将自定义规则与已知规则进行映射
        //// 数组模拟哈希表，数组下标是字符，数组值是该字符在火星文中对应的次序
        //// 将数组小标的字符转换为相对于字符'a'的相对位置。只存储26个字母，则用（order.charAt(i) - 'a'）来表示。

        //// words是火星文写的单词，order是约定的火星文顺序
        //public boolean isAlienSorted(String[] words, String order) {
        //    if (words == null || words.length == 0 || order == null || order.length() == 0) {
        //        return false;
        //    }
        //    char[] orderArr=order.toCharArray();// 将指定的顺序字符串转换为顺序字符数组
        //    int[] table = new int[26];//
        //    // 将外星语字典顺序转换为人类顺序，数组下标是火星文的字符，对应数组值是其在对应的外星文中的次序，可以表示先后顺序
        //    for (int i = 0; i < 26; i++) {
        //        table[orderArr[i] - 'a'] = i;
        //    }
        //    // 依次比较字符串数组中的两个字符串
        //    for (int i = 0; i < words.length-1; i++) {
        //        if (!check(words[i], words[i+1], table)) {//
        //            return false;
        //        }
        //    }
        //    return true;
        //}
        //
        //
        //
        //// 根据给定的顺序判断两个字符串的先后顺序
        //private boolean check(String word1, String word2, int[] orderArr) {
        //    int len1 = word1.length();
        //    int len2 = word2.length();
        //    int len= Math.min(len1, len2);
        //    int index = 0;
        //    while (index <len) {
        //        // 逐个比较两个单词中第index个字符在指定的顺序表中的顺序
        //        // // word1.charAt(index) 表示在单词word1中第index个字符， word1.charAt(index) - 'a'将字符转化为数值
        //        int cur1 = word1.charAt(index) - 'a';
        //        int cur2 = word2.charAt(index) - 'a';
        //        if (cur1 != cur2) {// 若index在str1和str2中指定的元素不相同，则判断不同字符在给定顺序中的先后位置
        //            // order[cur1]<order[cur2] 其实就是index指向的元素的在给定顺序中的下标的大小
        //            // order[cur1]<order[cur2] 若为真，则word1<word2，返回真；若为假，则word1>=word2，返回假
        //            return orderArr[cur1]< orderArr[cur2] ;
        //        }
        //        index++;
        //    }
        //    // 到这里至少有一个字符串已经遍历完了，字符串短的应该在前面
        //    if (len1 > len2) {// 字符串1比字符串2长
        //        return false;
        //    } else {// 字符串1比字符串2短或者两个字符串都遍历到末尾了
        //        return true;
        //    }
        //}




        //// 解法1：哈希表+两两比较
        //// 哈希表的key表示字母表中的字母，value表示该字母在字母表中的下标
        //
        //public boolean isAlienSorted(String[] words, String order) {
        //    if (words == null || words.length == 0) {// 判空
        //        return false;
        //    }
        //    Map<Character, Integer> map = new HashMap<>();
        //    // 将顺序表中的元素依次添加到哈希表中
        //    for (int i = 0; i < order.length(); i++) {
        //        map.put(order.charAt(i), i);
        //    }
        //    for (int i = 0; i < words.length - 1; i++) {
        //        if (!isSorted(words[i], words[i + 1], map)) {//依次两两比较
        //            return false;
        //        }
        //    }
        //    return true;
        //}
        //
        //
        //private boolean isSorted(String word1, String word2, Map<Character, Integer> map) {
        //    int minLen = Math.min(word1.length(), word2.length());// 较短的单词长度
        //    int index = 0;
        //    while (index < minLen) {
        //        char ch1=word1.charAt(index);// word1单词的第index个字母
        //        char ch2=word2.charAt(index);
        //        if (map.get(ch1) < map.get(ch2)) {//str1中index指向的元素比str2中index指向的元素靠前
        //            return true;
        //        } else if (map.get(ch1) > map.get(ch2)) {
        //            //str1中index指向的元素比str2中index指向的元素靠后
        //            return false;
        //        }
        //        index++;
        //    }
        //    return index==word1.length();
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
