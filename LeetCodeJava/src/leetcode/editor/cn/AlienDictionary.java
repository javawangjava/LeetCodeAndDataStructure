/**
 * <p>现有一种使用英语字母的火星语言，这门语言的字母顺序与英语顺序不同。</p>
 *
 * <p>给你一个字符串列表 <code>words</code> ，作为这门语言的词典，<code>words</code> 中的字符串已经 <strong>按这门新语言的字母顺序进行了排序</strong> 。</p>
 *
 * <p>请你根据该词典还原出此语言中已知的字母顺序，并 <strong>按字母递增顺序</strong> 排列。若不存在合法字母顺序，返回 <code>""</code> 。若存在多种可能的合法字母顺序，返回其中
 * <strong>任意一种</strong> 顺序即可。</p>
 *
 * <p>字符串 <code>s</code> <strong>字典顺序小于</strong> 字符串 <code>t</code> 有两种情况：</p>
 *
 * <ul>
 * <li>在第一个不同字母处，如果 <code>s</code> 中的字母在这门外星语言的字母顺序中位于 <code>t</code> 中字母之前，那么&nbsp;<code>s</code> 的字典顺序小于
 * <code>t</code> 。</li>
 * <li>如果前面 <code>min(s.length, t.length)</code> 字母都相同，那么 <code>s.length &lt; t.length</code> 时，<code>s</code>
 * 的字典顺序也小于 <code>t</code> 。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>words = ["wrt","wrf","er","ett","rftt"]
 * <strong>输出：</strong>"wertf"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>words = ["z","x"]
 * <strong>输出：</strong>"zx"
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>words = ["z","x","z"]
 * <strong>输出：</strong>""
 * <strong>解释：</strong>不存在合法字母顺序，因此返回 <span><code>"" 。</code></span>
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= words.length &lt;= 100</code></li>
 * <li><code>1 &lt;= words[i].length &lt;= 100</code></li>
 * <li><code>words[i]</code> 仅由小写英文字母组成</li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>图</li><li>拓扑排序</li><li>数组</li><li>字符串</li
 * ></div></div><br><div><li>👍 256</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 269
 * 火星词典
 *
 * @author wangweizhou
 * @date 2022-12-13 11:55:22
 */

public class AlienDictionary {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new AlienDictionary().new Solution();
        //String[] strs = {"z", "x", "z"};
        String[] strs = {"wrt","wrf","er","ett","rftt"};
        String ans = solution.alienOrder(strs);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 排序规则： 排序顺序由第一个不同的字母决定。

        // 排序逻辑：
        // 在排序的单词列表["ac"，"ab"，"bc"，"zc"，"zb"]中，一共出现了4个字母，即'a'、'b'、'c'和'z'。需要根据单词的顺序确定这个4个字母的顺序。
        // 由于"ac"排在"ab"的前面，因此字母'c'应该排在字母'b'的前面（即'c'＜'b'）。
        // 这是因为这两个单词的第1个字母相同，第2个字母不同，那么它们的第2个字母的顺序确定了两个单词的顺序。
        // 接下来两个相邻的单词是"ab"和"bc"，它们的第1个字母就不同，那么它们的顺序由它们的第1个字母确定，所以'a'＜'b'。
        // 类似地，可以根据"bc"排在"zc"的前面得知'b'＜'z'，根据"zc"排在"zb"的前面得知'c'＜'b'。

        // 由比较排序的单词列表中两两相邻的单词可知'c'＜'b'、'a'＜'b'和'b'＜'z'，现在需要找出一个包含4个字母的字母序列满足已知的3个字母的大小顺序。
        // 这看起来就是一个关于拓扑排序的问题，可以将每个字母看成图中的一个节点。
        // 如果已知两个字母的大小关系，那么图中就有一条从较小的字母指向较大的字母的边。
        // 根据字母的大小关系'c'＜'b'、'a'＜'b'和'b'＜'z'构建出的有向图如图15.19所示，该有向图有两个拓扑排序序列，"acbz"和"cabz"，相应地输入的单词列表就有两个可能的字母顺序。


        // 如果能够得出该有向图的拓扑排序序列，那么任意一条边的起始节点（较小的字母）在拓扑排序序列中一定出现在终止节点（较大的字母）的前面。
        // 因此，这个问题实质上一个关于拓扑排序的问题。这里是对字母建图和拓扑排序。
        // 遍历字符串数组中每一个字符串的每一个字母，建立每一个字母的邻接表和入度表。
        // 图用HashMap类型的变量graph以邻接表的形式表示。与某节点相邻的节点（即比某字母大的字母）用一个HashSet保存。
        // HashMap类型的变量inDegrees保存每个节点的入度。代码一开始找出单词列表words中出现的所有字母并做相应的初始化。


        // 接下来比较单词列表words中两两相邻的单词，从头找出第1组不同的两个字母，在图中添加一条从较小的字母（ch1）指向较大的字母（ch2）的边。
        // 这里有一类特殊的输入需要特别注意。如果排在后面的单词是排在前面的单词的前缀，那么无论什么样的字母顺序都是不可能的。
        // 例如，如果排序的单词列表是["abc"，"ab"]，不管是什么样的字母顺序，"abc"都不可能排在"ab"的前面，因此这是一个无效的输入，此时可以直接返回空字符串表示无效的字母顺序。
        // 在构建有向图之后，采用广度优先搜索实现拓扑排序。队列中保存的是入度为0的节点。
        // 每次从队列中取出一个节点，将该节点添加到拓扑排序序列中（即字母顺序序列），然后找到比该字母大的字母并将它们节点的入度减1，这相当于删除一条从较小的字母指向较大的字母的边。
        // 如果发现新的入度为0的节点，则将其添加到队列中。
        // 重复这个过程直到队列为空，此时要么图中所有节点都已经访问完毕，已经得到了完整的拓扑排序序列；要么剩下的还没有搜索到的节点形成一个环，已经不存在入度为0的节点。

        //
        //public String alienOrder(String[] words) {
        //    if (words == null || words.length == 0) {
        //        return "";
        //    }
        //    // 创建每一个字母的邻接表和入度表，即建立每一个子图和入度表。注意这里是空表
        //    Map<Character, Set<Character>> map = new HashMap<>();
        //    Map<Character, Integer> inDegrees = new HashMap<>();
        //    for (String word : words) {
        //        for (char ch : word.toCharArray()) {
        //            map.put(ch, new HashSet<>());
        //            inDegrees.put(ch, 0);
        //        }
        //    }
        //
        //    // 遍历单词的每一个字母，建立实际的邻接表和入读表。双层循环，外层循环单词，内层循环字母
        //    for (int i = 1; i < words.length; i++) {
        //        String word1 = words[i - 1];
        //        String word2 = words[i];
        //        if(word1.startsWith(word2)&&!word1.equals(word2)){// 注意这里后面的单词不能是后面单词的前缀
        //            return "";
        //        }
        //
        //        for (int j = 0; j < word1.length() && j < word2.length(); j++) {
        //            char ch1 = word1.charAt(j);
        //            char ch2 = word2.charAt(j);
        //            if(ch1!=ch2){
        //                if(!map.get(ch1).contains(ch2)){
        //                    map.get(ch1).add(ch2);
        //                    inDegrees.put(ch2,inDegrees.get(ch2)+1);
        //                }
        //                break;
        //            }
        //        }
        //    }
        //
        //    // 利用队列对图进行拓扑排序，将入度为0的节点加入队列，并将该节点的后续节点的入度减1。
        //    Queue<Character> queue=new LinkedList<>();
        //    for (Character inDegree:inDegrees.keySet()) {
        //        if(inDegrees.get(inDegree)==0){
        //            queue.offer(inDegree);
        //        }
        //    }
        //
        //    StringBuilder sb=new StringBuilder();
        //    while (queue.size()>0){
        //        char ch=queue.poll();
        //        sb.append(ch);
        //        for(char next:map.get(ch)){
        //            inDegrees.put(next,inDegrees.get(next)-1);
        //            if(inDegrees.get(next)==0){
        //                queue.offer(next);
        //            }
        //        }
        //    }
        //    return sb.length()==map.size()?sb.toString():"";
        //}




        //// 解法1：有向图的拓扑排序   没有图那么首先要自己建图，然后再拓扑排序
        public String alienOrder(String[] words) {
            if (words == null || words.length == 0) {// 判空
                return "";
            }
            // 根据单词表每个单词的后面相邻的单词可能有多个,所以这里用哈希表来表示邻接表。键是单词中的字母，值是该字母的后面的字母。
            // 图用HashMap类型的变量graph以邻接表的形式表示。与某节点Character相邻的节点（即比某字母大的字母）用一个HashSet保存。
            Map<Character, Set<Character>> graph = new HashMap<>();

            // HashMap类型的变量inDegrees保存每个节点的入度。键是单词中的字母，值是该字母的入度
            Map<Character, Integer> inDegrees = new HashMap<>();//

            // 找出单词列表words中出现的所有字母并建立表示图和入度的哈希表，这时候是空表。这里注意图是字母的图
            for (String word : words) {
                for (char ch : word.toCharArray()) {
                    graph.put(ch, new HashSet<>());
                    inDegrees.put(ch, 0);
                }
            }
            // 比较单词列表words中两两相邻的单词，从头找出第1组不同的两个字母，在图中添加一条从较小的字母（ch1）指向较大的字母（ch2）的边。
            for (int i = 1; i < words.length; i++) {
                // 单词列表中两两相邻的单词，w1在前，w2在后。
                String w1 = words[i - 1];
                String w2 = words[i];
                // 如果排在后面的单词是排在前面的单词的前缀，那么无论什么样的字母顺序都是不可能的。
                if (w1.startsWith(w2) && !w1.equals(w2)) {// 这里设定只能是前缀，意思就是w2只能是w1前面的部分不能是整个w1。
                    return "";
                }

                // 比较单词列表words中两两相邻的单词，从头找出第1组不同的两个字母，在图中添加一条从较小的字母（ch1）指向较大的字母（ch2）的边。
                for (int j = 0; j < w1.length() && j < w2.length(); j++) {
                    // 分别获取两个单词中第j个字母
                    char ch1 = w1.charAt(j);// 字符1在前，字符2在后
                    char ch2 = w2.charAt(j);
                    if (ch1 != ch2) {// 两个字母不同且前面一个字母的相邻字母中已经添加了后一个字母
                        // 两个单词中出现第一个不相等的字母，因为w1在前，w2在后，所以出现那第一个不同的时候，w1中的字母应该在w2中字母的前面。
                        // 如果ch1的邻接表中不含ch2【即没有建立从ch1到ch2的有向边】，那么将ch2加入ch1的邻接表，ch2的入度加1。
                        if (!graph.get(ch1).contains(ch2)) {
                            graph.get(ch1).add(ch2);
                            inDegrees.put(ch2, inDegrees.get(ch2) + 1);
                        }
                        break;// 两个单词找出第一个不同的字母之后，就没必要比较后面的单词了
                    }
                }
            }

            // 截至到这里，构建完了有向图。在构建有向图之后，采用广度优先搜索实现拓扑排序。队列中保存的是入度为0的节点。
            Queue<Character> queue = new LinkedList<>();
            for (char ch : inDegrees.keySet()) {// 遍历保存每个字符入度的集合，将入度为0的字符全部加入队列
                if (inDegrees.get(ch) == 0) {
                    queue.add(ch);
                }
            }

            StringBuilder sb = new StringBuilder();// sb表示拓扑排序序列
            // 每次从队列中取出一个节点，将该节点添加到拓扑排序序列中（即字母顺序序列），
            // 然后找到比该字母大的字母并将它们节点的入度减1，这相当于删除一条从较小的字母指向较大的字母的边。
            // 如果发现新的入度为0的节点，则将其添加到队列中。重复这个过程直到队列为空，此时要么图中所有节点都已经访问完毕，已经得到了完整的拓扑排序序列；要么剩下的还没有搜索到的节点形成一个环，已经不存在入度为0的节点。
            while (!queue.isEmpty()) {
                char ch = queue.remove();// 每次从队列中取出入度为0的一个节点
                sb.append(ch);// 将取出的字母加入排序序列中
                // 遍历当前字母ch的所有后续字母
                for (char next : graph.get(ch)) {
                    inDegrees.put(next, inDegrees.get(next) - 1);// 找到该字母ch的后续字母next并将它们的节点的入度减1，这相当于删除两个字母之间的有向边。
                    if (inDegrees.get(next) == 0) {// 如果发现新的入度为0的节点，则将其添加到队列中
                        queue.add(next);
                    }
                }
            }
            return sb.length() == graph.size() ? sb.toString() : "";
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
