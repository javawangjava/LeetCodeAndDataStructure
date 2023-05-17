/**
 * <p>给你一个字符串 <code>s</code> ，仅反转字符串中的所有元音字母，并返回结果字符串。</p>
 *
 * <p>元音字母包括 <code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code>、<code>'u'</code>，且可能以大小写两种形式出现。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "hello"
 * <strong>输出：</strong>"holle"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "leetcode"
 * <strong>输出：</strong>"leotcede"</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
 * <li><code>s</code> 由 <strong>可打印的 ASCII</strong> 字符组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 253</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * 反转字符串中的元音字母
 *
 * @author wangweizhou
 * @date 2022-06-29 00:41:28
 */
public class ReverseVowelsOfAString {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ReverseVowelsOfAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //解法1：双指针 思路和判断回文的思路对称
        // 双指针从两边开始遍历，当两个指针都指向元音字母时，对调
        //java 中字符串是常量，一旦赋值就不能更改

        public String reverseVowels(String s) {
            if(s==null|| s.length() <= 1){
                return s;
            }
            //java 中字符串是常量，一旦赋值就不能更改，所以转换成字符数组进行处理
            char[] arr=s.toCharArray();
            int length=arr.length;
            int left=0;
            int right=length-1;
            while(left<right){

                // 下面的if语句要求两个都指向元音，所以上面用if语句利用外层while (left < right)也是可以的
                // 从原理上来说使用while (left < right&&!isVowel(ch[left]))更好，设计初衷是left指向下一个元音字母

                while(left<right&&!isVowels(arr[left])){//left移动到下一个元音字符
                // if (!isVowels(arr[left])) {//left移动到下一个元音字符
                    left++;
                }
                while(left<right&&!isVowels(arr[right])){//right移动到下一个元音字符
                //if (!isVowels(arr[right])) {//right移动到下一个元音字符
                    right--;
                }

                // 下面的if语句要求两个都指向元音，所以上面用if语句利用外层while (left < right)也是可以的
                if(left<right&&isVowels(arr[left])&&isVowels(arr[right])){
                    char temp=arr[left];
                    arr[left]=arr[right];
                    arr[right]=temp;
                    left++;
                    right--;
                }
            }
            // 下面两种方式都是将数组转换为字符串
            return String.valueOf(arr);
            //return  new String(arr);
        }


        // 判断字符是否是元音，方式3：比较少匹配制就行比如switch语句，哈希表查询
        private  boolean isVowels(char ch){
            switch (ch){
                case 'a': case 'o': case 'e': case 'i': case 'u':
                case 'A': case 'O': case 'E': case 'I': case 'U': return true;
                default:return false;
            }
        }


        /*
        // 判断字符是否是元音，比较少匹配制就行比如switch语句，哈希表查询

        private  boolean isVowels(char ch){
            String str="aoeiuAOEIU";
            return str.indexOf(ch)>=0;//索引>=0就说明字符串里面有该字符
        }
        */



       /*
        // 判断字符是否是元音，方式2
        private  boolean isVowels(char ch){
            char[] vowels=new char[]{'a','o','e','i','u','A','O','E','I','U'};
            Set<Character> set=new HashSet<>();
            for (char vowel:vowels) {
                set.add(vowel);
            }
            return set.contains(ch);
        }
        */

    }
//leetcode submit region end(Prohibit modification and deletion)

}
