/**
 * <p>给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。</p>
 *
 * <p><strong>说明：</strong>本题中，我们将空字符串定义为有效的回文串。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> "A man, a plan, a canal: Panama"
 * <strong>输出:</strong> true
 * <strong>解释：</strong>"amanaplanacanalpanama" 是回文串
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> "race a car"
 * <strong>输出:</strong> false
 * <strong>解释：</strong>"raceacar" 不是回文串
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 2 * 10<sup>5</sup></code></li>
 * <li>字符串 <code>s</code> 由 ASCII 字符组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 540</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 125
 * 验证回文串
 *
 * @author wangweizhou
 * @date 2022-06-25 21:50:17
 */

public class ValidPalindrome {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ValidPalindrome().new Solution();
        //String s = "acbca";
        String s = ".,";
        boolean boo = solution.isPalindrome(s);
        System.out.println(boo);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 结合5,125,647和680一起看

        //// 判断一个字符串是不是回文，常用的方法就是使用双指针。
        //// 可以定义两个指针，一个指针从第1个字符开始从前向后移动，另一个指针从最后一个字符开始从后向前移动。
        //// 如果两个指针指向的字符相同，则同时移动这两个指针以判断它们指向的下一个字符是否相同。这样一直移动下去，直到两个指针相遇。
        //// 由于题目要求只考虑字母和数字字符，如果某个指针指向的字符既不是字母也不是数字，则移动指针跳过该字符。
        //// 同时，由于题目要求忽略大小写，因此需要把所有的字母都转化成小写形式（或大写形式）再做比较。


        //// 解法1： 双指针
        //// 直接原地用双指针从两侧遍历判断,只有左右指针都是数字或者字母时才判断
        //
        //public boolean isPalindrome(String s) {
        //    if (s == null || s.length() <= 1) {// 本题定义空字符串是回文串
        //        return true;
        //    }
        //    int length = s.length();
        //    int left = 0;
        //    int right = length - 1;
        //    //left<right，要是全是符号，会越界
        //    while (left < right) {
        //        //left和right必须是有效字符的对称位置
        //        //确保left指针指向的是数字或者字母。在对比前，左指针要指向字母或者数字，所以要通过循环遍历，if语句只能移动一次
        //        // 调用系统API Character.isLetterOrDigit(char ch)判断字符ch是不是数字或者字母
        //        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
        //            left++;
        //        }
        //        //在对比前，右指针要指向字母或者数字，所以要通过循环遍历，if语句只能移动一次
        //        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
        //            right--;
        //        }
        //        //开始对比，忽略字母的大小写
        //        if (Character.toUpperCase(s.charAt(left)) != Character.toUpperCase(s.charAt(right))) {
        //            return false;
        //        }
        //        // 执行到这里就是对称位置的字符在忽略大小写的情况下对称。
        //        left++;
        //        right--;
        //    }
        //    return true;
        //}




        // 解法2：双指针 每次  双指针每次指向有效的大小写字母，并统一将字母转换为大写字母或者小写字母进行处理
        public boolean isPalindrome(String s) {
            if (s == null || s.length() <= 1) {// 本题定义空字符串是回文串
                return true;
            }
            int len=s.length();
            int left=0;
            int right=len-1;
            while(left<right){
                //left和right必须是有效字符的对称位置
                // 调用系统API Character.isLetterOrDigit(char ch)判断字符ch是不是数字或者字母
                char ch1=s.charAt(left);
                char ch2=s.charAt(right);
                // 下面这三个是3选1，因为每次移动一个指针，只有当左右指针移动到有效字符处才可以判断是否是对称的
                if(!Character.isLetterOrDigit(ch1)){//确保left指针指向的是数字或者字母。
                    left++;
                }else if(!Character.isLetterOrDigit(ch2)){//在对比前，右指针要指向字母或者数字
                    right--;
                }else {//left和right必须是有效字符的对称位置
                    ch1=Character.toUpperCase(ch1); //开始对比，忽略字母的大小写
                    ch2=Character.toUpperCase(ch2);
                    if(ch1!=ch2){
                        return false;
                    }
                    // 执行到这里就是对称位置的字符在忽略大小写的情况下对称。
                    left++;
                    right--;
                }
            }
            return true;
        }




        //// 解法3：筛选原字符串保存+双指针
        //// 遍历一次字符串，并将其中的字母和数字保留保存在另一个字符串中，然后判断另一个字符串是否是回文串
        //
        //public boolean isPalindrome(String s) {
        //    if (s == null || s.length() <= 1) {// 本题定义空字符串是回文串
        //        return true;
        //    }
        //    int length = s.length();
        //    StringBuilder sb = new StringBuilder();
        //    for (int i = 0; i < length; i++) {// 遍历字符串将数字和字母添加到新的可变字符串中
        //        char ch = s.charAt(i);
        //        if (Character.isLetterOrDigit(ch)) {// 筛选字符串中的数字和字符
        //            sb.append(Character.toLowerCase(ch));
        //        }
        //    }
        //
        //    //双指针 从两侧遍历看对应字符是否相等
        //    String str1 = sb.toString();//可变长度的字符串转变为String
        //    int left = 0;
        //    int right = str1.length() - 1;
        //    while (left < right) {
        //        if (str1.charAt(left) != str1.charAt(right)) {
        //            return false;
        //        }
        //        left++;
        //        right--;
        //    }
        //    return true;
        //}




        //// 解法2：筛选原字符串的数字和字符，并将筛选后的字符串反转与筛选后的比较
        //// 遍历一次字符串，并将其中的字母和数字保留保存在另一个字符串中，然后判断另一个字符串是否是回文串
        //// 新建字符串并调用API
        //public boolean isPalindrome(String s) {
        //    if (s == null || s.length() <= 1) {// 本题定义空字符串是回文串
        //        return true;
        //    }
        //    int length = s.length();
        //    StringBuilder sb = new StringBuilder();
        //    for (int i = 0; i < length; i++) {// 遍历字符串将数字和字母添加到新的可变字符串中
        //        char ch = s.charAt(i);
        //        if (Character.isLetterOrDigit(ch)) {// 筛选字符串中的数字和字符
        //            sb.append(Character.toLowerCase(ch));//将字符转换为小写，因为题目不要求区分大小写
        //        }
        //    }
        //    StringBuilder rev = new StringBuilder(sb).reverse();//注意这里是先利用原字符串新建一个字符串，然后再反转
        //    return sb.toString().equals(rev.toString());
        //}
        //
        //// StringBuilder reverse() 导致此字符序列被序列的反向替换。  这个函数是把原字符串原地反转
        //// StringBuilder newRev = new StringBuilder(sb);
        //// StringBuilder rev = newRev.reverse();


    }
//leetcode submit region end(Prohibit modification and deletion)

}
