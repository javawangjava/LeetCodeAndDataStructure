/**
 * <p>给定一个字符串 <code>s</code> ，请你找出其中不含有重复字符的&nbsp;<strong>最长子串&nbsp;</strong>的长度。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "abcabcbb"
 * <strong>输出: </strong>3
 * <strong>解释:</strong> 因为无重复字符的最长子串是 <code>"abc"，所以其</code>长度为 3。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "bbbbb"
 * <strong>输出: </strong>1
 * <strong>解释: </strong>因为无重复字符的最长子串是 <code>"b"</code>，所以其长度为 1。
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "pwwkew"
 * <strong>输出: </strong>3
 * <strong>解释: </strong>因为无重复字符的最长子串是&nbsp;<code>"wke"</code>，所以其长度为 3。
 * &nbsp;    请注意，你的答案必须是 <strong>子串 </strong>的长度，<code>"pwke"</code>&nbsp;是一个<em>子序列，</em>不是子串。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
 * <li><code>s</code>&nbsp;由英文字母、数字、符号和空格组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍
 * 7683</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 3
 * 无重复字符的最长子串
 *
 * @author wangweizhou
 * @date 2022-06-10 17:06:13
 */

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        String s = "abcabcbb";
        int ans = solution.lengthOfLongestSubstring(s);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)


    class Solution {


        // 此处还是用一个哈希表统计子字符串中字符出现的次数。
        // 如果一个子字符串中不含重复的字符，那么每个字符都只出现一次，它们在哈希表中对应的值为1。
        // 没有在子字符串中出现的其他字符对应的值都是0。也就是说，如果子字符串中不含重复字符，那么它对应的哈希表中没有比1大的值。

        // 下面仍然用两个指针来定位一个子字符串，其中第1个指针指向子字符串的第1个字符，第2个指针指向子字符串的最后一个字符。
        // 接下来分析如何移动这两个指针。
        // 如果两个指针之间的子字符串不包含重复的字符，由于目标是找出最长的子字符串，因此可以向右移动第2个指针，在子字符串的最右边增加新的字符，
        // 并将该新加入的字符添加到哈希表中，同时更新最长的子字符串。

        // 然后判断新的字符在子字符串中有没有重复出现。
        // 如果还是没有重复的字符，则继续向右移动第2个指针，在子字符串中添加新的字符。
        // 如果两个指针之间的子字符串中包含重复的字符，则可以向右移动第1个指针，删除子字符串中最左边的字符。
        // 如果删除最左边的字符之后仍然包含重复的字符，则继续向右移动第1个指针删除最左边的字符。
        // 如果删除最左边的字符之后不再包含重复的字符，就可以向右移动第2个指针，在子字符串的右边添加新的字符。


        //// 解法3：滑动窗口+数组模拟哈希表  用哈希表来统计滑动窗口中字符的出现次数
        //// 数组下标就是哈希表的键key,数组值就是哈希表的值value。数组模拟的是set型哈希表。
        //// 只要窗口右移就需要及时更新最大长度。

        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {//判空
                return 0;
            }
            int[] counts=new int[256];
            int len=s.length();//length表示字符串长度
            int maxLen = 0;//保存满足当前要求的子串长度的最大值
            // 由于这里初始值的设置，这里可以将right指向的位置看作待加入滑动窗口的位置【也就是认为当right指向的元素次数没有+1时，表示该元素还没有加入到滑动窗口中】
            // left 表示已经加入到滑动窗口中的左边界。或者理解为指向待移入或者移出的的指针。
            int left = 0;//left指向滑动窗口最左端的元素
            int right = 0;//right指向滑动窗口最右端的元素
            // 长度可变的滑动窗口[left,right]  counts数组中数组值>0的就是滑动窗口中的元素
            while(right<len){
                // 因为set型的哈希表一次只能移动一个位置
                if(counts[s.charAt(right)]>0){// 判断滑动窗口中是否含有right指向的待加入滑动窗口中的元素，counts[s.charAt(right)]>0表示哈希表中已经含有right指向的元素，
                    // 那么这时候right指向的待加入元素不需要加入到滑动窗口中
                    // 因为前面left初始值是0，这时候也就约定left指向滑动窗口的左边界，那么这时候要先将left指向的元素的次数减1，然后将left指针右移。
                    counts[s.charAt(left)]--;// 移出最早进入滑动窗口的元素,移出元素后，长度变短，所以没有必要更新最大长度
                    left++;
                }else {// 哈希表中没有该元素，将该元素添加到哈希表中，更新最大长度
                    // 按照初始值约定，right 指向的元素待加入滑动窗口中的元素，当right指向的元素在滑动窗口中出现的次数为0，那么将right指向的元素在滑动窗口中出现的次数加1，
                    // 更新滑动窗口长度，然后再移动right指向下一个待加入的元素的位置
                    counts[s.charAt(right)]++;
                    maxLen=Math.max(maxLen,right-left+1);
                    right++;
                }
            }
            return maxLen;
        }




        //* 解法2：滑动窗口+哈希表set  优化解法一
        //* 字符串如果处理成左闭右开或者左开右闭型，则字符串长度是（最大-最小）；字符串如果处理成双闭型，则字符串长度是（最大-最小+1）。
        //* 在遍历字符串时遇到相同的字符，将滑动窗口的左边界逐次右移一个单位，直至滑动窗口中没有右指针指向的相同元素。

        //// [left,right]是长度可变的滑动窗口，滑动窗口中只保存没有重复字符的子串。
        //// 哈希表set中的元素是和滑动窗口同步的。
        //
        //public int lengthOfLongestSubstring(String s) {
        //    if (s == null || s.length() == 0) {//判空
        //        return 0;
        //    }
        //    int length = s.length();//length表示字符串长度
        //    int maxLen = 0;//保存满足当前要求的子串长度的最大值
        //    Set<Character> set = new HashSet<>();//创建HashSet来存储滑动窗口中的元素
        //    int left = 0, right = 0;//滑动窗口的左右边界
        //    // 本题的set其实就是和滑动窗口对应的，滑动窗口中有什么那么set中就有什么。滑动窗口和set是同步变化的
        //    // 每次不是左边界移动就是右边界移动，所以不会形成死循环
        //    while (right < length) {//左边界一定不会超过右边界，所有没有必要判断左边界了
        //        if (!set.contains(s.charAt(right))) {// set集合中不含有right指向的字符，也就是滑动窗口中不含有right指向的字符，加入滑动窗口和set中，更新最大长度。
        //            set.add(s.charAt(right));// 将新元素加入到滑动窗口和set集合中
        //            //滑动窗口中一旦加入新的元素就更新最长子串的最大值。
        //            maxLen = Math.max(maxLen, right - left + 1);//新最大值是原有最大值和新的滑动窗口的最大值
        //            right++;//滑动窗口最右端指针后移，滑动窗口中加入新的元素
        //        } else {
        //            set.remove(s.charAt(left));//当set中有重复元素时，一直移出，直至set中没有重复元素，
        //            left++;//left++滑动窗口中移出left指向的元素
        //        }
        //    }
        //    return maxLen;
        //}



        ////* 解法3：滑动窗口+哈希表map   左右指针形成长度可变的滑动窗口
        ////* 字符串如果处理成左闭右开或者左开右闭型，则字符串长度是（最大-最小）；字符串如果处理成双闭型，则字符串长度是（最大-最小+1）。
        ////*
        ////* 左右指针形成长度可变的滑动窗口，滑动窗口中只保存没有重复字符的子串。
        ////* 若滑动窗口的右指针移动时加入新的元素进入滑动窗口，则需要更新右边界。
        ////* 注意set和map的不同处理
        ////* set无序不可重复，所以set通过添加移除来保持保存的是最新的无重复的元素。而且左边界每次只能移动一位。
        ////* map可以保存键值对，通过value来保存字符的最新的位置，这样左边界可以直接移动到目标位置
        //
        //// [left,right]是长度可变的滑动窗口，滑动窗口中只保存没有重复字符的子串。
        //// 哈希表map表示字符串中每一个不同字符的最新的位置。
        //
        //public int lengthOfLongestSubstring(String s) {
        //    if (s == null || s.length() == 0) {// 判空
        //        return 0;
        //    }
        //    int length = s.length();// length表示字符串长度
        //    int maxLen = 0;// 保存满足当前要求的子串长度的最大值
        //    Map<Character, Integer> map = new HashMap<>();  // map 的key是用来保存字符，value保存字符在字符串中对应的下标也就是位置
        //    // [left,right]是长度可变的滑动窗口，滑动窗口中只保存没有重复字符的子串。
        //    int left = 0;//left指向滑动窗口最左端的元素
        //    int right = 0;//right指向滑动窗口最右端的元素
        //    // 因为滑动窗口的左指针可以一次移动到目标位置，每次循环都有新元素进入滑动窗口，所以需要更新最大长度和map中元素
        //    while (right < length) {//这个就不需要判断左边界了，根据程序，左边界一定在右边界左边或者相等。
        //        char ch = s.charAt(right);
        //        // 先判断map中有没有right指向的元素，这时候right指向的元素还没有加入map集合中
        //        if (map.containsKey(ch)) {// 若map中已经添加该元素了，那么更新滑动窗口的左边界。左边界应该取下面两种情况的较大的。
        //            // 哈希表map表示字符串中每一个不同字符的最新的位置，这时候滑动窗口中不一定含有该字符。
        //            // 情况1：若滑动窗口中没有该元素，也就是说明该元素在滑动窗口左边界的左边，那么滑动窗口的左边界不变，
        //            // 情况2：若滑动窗口中有该元素，那么滑动窗口的左边界就要移动到charAt(right)所指向的元素的下一位
        //            left = Math.max(1 + map.get(ch), left);
        //        }
        //        // left移动到最新的位置，这时候[left,right]中就没有重复的元素了。执行到这里滑动窗口的左右边界已经确定了
        //        maxLen = Math.max(maxLen, right - left + 1);//更新子串的最大长度
        //        map.put(s.charAt(right), right);// 若是第一次遇到的新字符就加入，若是第二次遇到的那么就更新value也就是更新位置。
        //        right++;//滑动窗口最右端指针后移
        //    }
        //    return maxLen;
        //}






         // 解法一：通过两个循环穷举所有的子串，然后再判读子串中有无重复的字符，不断刷新满足要求的子串长度

        //public int lengthOfLongestSubstring(String s) {
        //    if(s==null||s.length()==0){
        //        return 0;
        //    }
        //    int length = s.length();//length表示字符串长度
        //    int max = 0;//保存满足当前要求的子串长度的最大值
        //    //通过双循环遍历字符串的所有子字符串
        //    for (int i = 0; i < length; i++) {
        //        for (int j = i + 1; j < length + 1; j++) {//子串是左闭右开，
        //            if(!allUniques(s,i,j)){
        //                break;//当子串[i,j)有重复数字时，那么子串[i,j后)一定有重复数字，结束内层循环，子串的开始位置后移
        //            }else{
        //                max=Math.max(max,j-i);//保存当前子串[i,j)与已有子串长度的最大值，因为子串不包含右边界，所以长度是j-i.
        //            }
        //            //if(allUniques(s,i,j)){
        //            //    max=Math.max(max,j-i);//保存当前子串[i,j)与已有子串长度的最大值，
        //            //}
        //        }
        //    }
        //    return max;
        //}
        //
        //
        ////allUniques (String s,int start, int end) 用于判断字符串s中是否有重复字符
        //public boolean allUniques (String s,int start, int end){
        //    Set<Character> set = new HashSet<>();//创建HashSet来存储不含重复字符的子串
        //    for (int i = start; i < end; i++) {
        //        Character ch = s.charAt(i);
        //        if (set.contains(ch)) {  //判断字符是否在set中
        //            return false;
        //        } else {
        //            set.add(ch);//字符不在set中，将该字符加入到set中
        //        }
        //    }
        //    return true;
        //}





        //// 解法4：滑动窗口
        //public int lengthOfLongestSubstring(String s) {
        //    if (s == null || s.length() == 0) {//判空
        //        return 0;
        //    }
        //    int[] counts=new int[256];
        //    int right=0;
        //    int left=-1;
        //    int len=s.length();
        //    int longest =1;
        //    for (; right < len; right++) {
        //        counts[s.charAt(right)]++;
        //        while (hasGreatThan1(counts)){
        //            left++;
        //            counts[s.charAt(left)]--;
        //        }
        //        longest =Math.max(right-left, longest);
        //    }
        //    return longest;
        //}
        //
        //
        //private boolean hasGreatThan1(int[] counts){
        //    for (int count:counts) {
        //        if(count>1){
        //            return true;
        //        }
        //    }
        //    return false;
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
