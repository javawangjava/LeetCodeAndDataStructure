/**
 * <p>编写一个函数来查找字符串数组中的最长公共前缀。</p>
 *
 * <p>如果不存在公共前缀，返回空字符串&nbsp;<code>""</code>。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>strs = ["flower","flow","flight"]
 * <strong>输出：</strong>"fl"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>strs = ["dog","racecar","car"]
 * <strong>输出：</strong>""
 * <strong>解释：</strong>输入不存在公共前缀。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= strs.length &lt;= 200</code></li>
 * <li><code>0 &lt;= strs[i].length &lt;= 200</code></li>
 * <li><code>strs[i]</code> 仅由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li></div></div><br><div><li>👍 2294</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 14
 * 最长公共前缀
 */

public class LongestCommonPrefix {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LongestCommonPrefix().new Solution();
        //String[] strs={"flower","flow","flight"};
        String[] strs = {"fl", "f", "a"};
        String ans = solution.longestCommonPrefix(strs);
        System.out.println(ans);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：横向扫描，水平比较。
        // 基本思路：两个字符串寻找公共前缀+将公共前缀依次与新加入的字符串求公共前缀
        // 假定字符串数组第一个字符串是最长公共前缀，依次遍历字符串数组中的每个字符串，对于遍历到的字符串，更新最长公共前缀.
        // 外层循环控制的是遍历字符串数组，内层循环控制遍历字符串的每个字符【是遍历某两个字符串的最大公共前缀】，内层循环遍历到两个字符串不相等的时停止

        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {// 当字符串为空字符串或者为空指针时
                return "";
            }
            int length = strs.length;
            String longestCommonPrefix = strs[0];// 初始化最长公共前缀为字符串数组第一个字符串
            for (int i = 1; i < length; i++) {//遍历字符串，更新最长公共前缀。
                // 前i个字符串的公共前缀就是：前(i-1)个字符串的公共前缀和第i个字符串的公共前缀
                longestCommonPrefix = commonPrefix(longestCommonPrefix, strs[i]);
                //当在遍历字符串数组过程中，公共前缀longestCommonPrefix长度变为0时，结束遍历，公共前缀为空字符串
                if (longestCommonPrefix.length() == 0) {//长度等于0，也就是前缀是空字符串“”，注意不是空引用
                    return "";
                    //break;
                }
            }
            return longestCommonPrefix;
        }


        // 得到两个字符串的最大公共前缀   方法2
        private String commonPrefix(String str1, String str2) {
            if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
                return "";
            }
            int length = Math.min(str1.length(), str2.length());// 两个字符串的公共前缀长度不会超过两个字符串的较小长度
            int index = 0;// 数组遍历指针
            // 依次比较两个字符串中的字符，相等且没有越界则后移
            // 当循环结束时，index指向不相等的那个位置，或者共有前缀就是最短的字符串，这时候index越界
            while (index < length && str1.charAt(index) == str2.charAt(index)) {// 指针不越界，且指针指向的两个字符串的字符相等
                index++;//记忆下标比记忆字符串简单
            }
            return str1.substring(0, index);//String substring(int beginIndex, int endIndex)
            //返回一个字符串，该字符串是此字符串的子字符串。 左闭右开
        }





    /*
        // 求两个字符串的最大公共前缀   写法3  这里使用可变字符串
        private String commonPrefix(String str1,String str2){
            if(str1==null||str2==null||str1.length()==0||str2.length()==0){
                return "";
            }
            int length=Math.min(str1.length(),str2.length());//公共前缀长度最大是两个字符串的最小长度
            StringBuilder sb=new StringBuilder();
            for (int i = 0; i < length; i++) {
                if(str1.charAt(i)!=str2.charAt(i)){
                    break;
                }
                sb.append(str1.charAt(i));
            }
            return sb.toString();
        }
*/



        /*
       // 求两个字符串的最大公共前缀   写法5
        // 求前缀这时候可以通过记录下标，最后调用substring()来完成
        private String commonPrefix(String str1, String str2){
            if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
                return "";
            }
            int len =Math.min(str1.length(),str2.length());
            int index=0;
            while (index< len){
                if(str1.charAt(index)==str2.charAt(index)){
                    index++;
                }else{// str1.charAt(i) != str2.charAt(i)
                    break;//一旦当字符串中某个字符不相等时，说明公共前缀截止
                }
            }
            return str1.substring(0,index);
        }

        */




        /*

        //	解法2：纵向扫描，垂直比较
        // 意思就是拿每个字符串的第i个元素进行比较，相同的话比较第（i+1）个元素，不相同的话，那公共前缀就到第i个元素结束

        //	纵向扫描时，从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，
        //	如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。
        //	外层循环控制的是遍历单个字符串，内层循环控制的是遍历字符数组，当内层循环遍历不同字符串时，出现与第一个字符串字符不相等的就停止遍历
        //  最长公共前缀长度肯定不会超过任一字符串的长度

        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {//当字符串为空字符串或者为空指针时
                return "";
            }
            // 公共前缀长度一定小于等于字符串的每一个字符串长度。交集小于等于取交的集合
            int length = strs[0].length();//初始化为字符数组的第一个字符串【元素】的长度
             // 最外层遍历字符串的第i个字母
            for (int i = 0; i < length; i++) {
                char ch=strs[0].charAt(i); //ch表示字符数组中第一个字符串【元素】的第i个字符

                // 内层依次遍历字符数组中的字符串
                for (int j = 0; j < strs.length; j++) { //j遍历字符数组，
                    // 公共前缀的结束条件：1.字符数组中的某个字符串已经越界一位，2.某一个字符串的第i个字符不等于第一个字符串的第i个字符
                    // i == strs[j].length()表示遍历字符串strs[j]已经遍历完了并且越界一位。
                    // strs[j].charAt(i) != ch表示字符串strs[j]的第i个字符与ch不相等
                    if(i==strs[j].length()||strs[j].charAt(i)!=ch){
                        return strs[0].substring(0,i);
                    }
                }
            }
            return strs[0];
        }
        */


    }

//leetcode submit region end(Prohibit modification and deletion)

}
