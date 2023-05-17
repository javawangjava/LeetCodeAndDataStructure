/**
 * <p>给你两个版本号 <code>version1</code> 和 <code>version2</code> ，请你比较它们。</p>
 *
 * <p>版本号由一个或多个修订号组成，各修订号由一个 <code>'.'</code> 连接。每个修订号由 <strong>多位数字</strong> 组成，可能包含 <strong>前导零</strong>
 * 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，<code>2.5.33</code> 和 <code>0.1</code>
 * 都是有效的版本号。</p>
 *
 * <p>比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 <strong>忽略任何前导零后的整数值</strong> 。也就是说，修订号 <code>1</code> 和修订号
 * <code>001</code> <strong>相等 </strong>。如果版本号没有指定某个下标处的修订号，则该修订号视为 <code>0</code> 。例如，版本 <code>1.0</code> 小于版本
 * <code>1.1</code> ，因为它们下标为 <code>0</code> 的修订号相同，而下标为 <code>1</code> 的修订号分别为 <code>0</code> 和 <code>1</code>
 * ，<code>0 &lt; 1</code> 。</p>
 *
 * <p>返回规则如下：</p>
 *
 * <ul>
 * <li>如果&nbsp;<code><em>version1&nbsp;</em>&gt;&nbsp;<em>version2</em></code>&nbsp;返回&nbsp;<code>1</code>，</li>
 * <li>如果&nbsp;<code><em>version1&nbsp;</em>&lt;&nbsp;<em>version2</em></code> 返回 <code>-1</code>，</li>
 * <li>除此之外返回 <code>0</code>。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>version1 = "1.01", version2 = "1.001"
 * <strong>输出：</strong>0
 * <strong>解释：</strong>忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>version1 = "1.0", version2 = "1.0.0"
 * <strong>输出：</strong>0
 * <strong>解释：</strong>version1 没有指定下标为 2 的修订号，即视为 "0"
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>version1 = "0.1", version2 = "1.1"
 * <strong>输出：</strong>-1
 * <strong>解释：</strong>version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是 "1" 。0 &lt; 1，所以 version1 &lt; version2
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= version1.length, version2.length &lt;= 500</code></li>
 * <li><code>version1</code> 和 <code>version2</code> 仅包含数字和 <code>'.'</code></li>
 * <li><code>version1</code> 和 <code>version2</code> 都是 <strong>有效版本号</strong></li>
 * <li><code>version1</code> 和 <code>version2</code> 的所有修订号都可以存储在 <strong>32 位整数</strong> 中</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 306</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 165
 * 比较版本号
 * @author wangweizhou
 * @date 2022-07-20 18:41:45
 */

public class CompareVersionNumbers {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new CompareVersionNumbers().new Solution();
        String s1="1.01";
        String s2="1.001";
        String s3="1.12.123";
        int ans=solution.compareVersion(s1,s3);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：双指针   自己实现分割字符串
        // 将版本号按照点号分割成修订号，然后从左到右比较两个版本号的相同下标的修订号。在比较修订号时，需要将字符串转换成整数进行比较

        public int compareVersion(String version1, String version2) {
            if(version1==null||version2==null||version1.length()==0||version2.length()==0){
                return 0;
            }

            int length1 = version1.length();
            int length2 = version2.length();
            int curr1 = 0, curr2 = 0;
            while (curr1 < length1 || curr2 < length2) {//直到两个字符串都结束
                int num1 = 0;
                while (curr1 < length1 && version1.charAt(curr1) != '.') {// 截取转换两个分割点之间的数字,并转换为整数
                    //num1 = num1 * 10 + version1.charAt(curr1); //这个有错，version1.charAt(curr1)这个是对应的万国码的数值，不是字符本身对应的数值
                    num1 = num1 * 10 + (version1.charAt(curr1) - '0');// 注意这里version1.charAt(curr1) - '0':把字符转换成数值
                    curr1++;
                }
                curr1++;//上面循环结束时，curr1就指向了分割点。跳过分割点 。所以不用像下面这么写
                //if(curr1<length1&&version1.charAt(curr1)=='.'){
                //    curr1++;
                //}

                int num2 = 0;
                while (curr2 < length2 && version2.charAt(curr2) != '.') {// 截取转换两个分割点之间的数字,并转换为整数
                    num2 = num2 * 10 + (version2.charAt(curr2) - '0');
                    curr2++;
                }
                curr2++;

                // 从左到右比较两个版本号的相同下标的修订号，修订号不同就可以结束，不用比较后续修订号了。
                // 两个修订号相同，那就继续比较下一个修订号
                if (num1 > num2) {
                    return 1;
                } else if (num1 < num2) {
                    return -1;
                }
            }
            return 0;//版本号相同,这个只能是把字符串遍历完了之后才能确定
        }




        /*

        // 解法2：字符串分割
        // 也就是比较每连续两个“.”中间的数字的大小就可以
        // 将字符串以“.”分割成字符串数组，然后将每个字符数组元素转换成数字，逐个比较数字的大小

        // Integer
        //static Integer valueOf(String s) 返回表示指定的 int值的 Integer实例。
        //static int parseInt(String s) 将字符串参数解析为带符号的十进制整数。

        public int compareVersion(String version1, String version2) {
            if(version1==null||version2==null||version1.length()==0||version2.length()==0){
                return 0;
            }
            // 分割字符串形成字符串数组
            String[] s1=version1.split("\\.");// 因为"." 、"\"、“|”是特殊字符，需要转义，"\\." 、"\\\"、“\\|”
            String[] s2=version2.split("\\.");

            // 遍历比较对应位置的子串对应的数字的大小
            for (int i = 0; i < s1.length||i<s2.length; i++) {
                int num1=0,num2=0;// 每两个分隔符中间的数默认是0
                if(i<s1.length){//遍历字符串数组，将每一个数组元素转换成数字
                    num1=Integer.valueOf(s1[i]);
                }
                if(i<s2.length){
                    num2=Integer.valueOf(s2[i]);
                }
                // 一旦出现大小差异就直接返回，后续不需要再比较
                if(num1>num2){
                    return 1;
                }else if(num1<num2){
                    return -1;
                }
            }
            return 0;//版本号相同,这个只能是把字符串遍历完了之后才能确定
        }

        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
