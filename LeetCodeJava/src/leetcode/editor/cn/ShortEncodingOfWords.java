/**
 * <p>单词数组&nbsp;<code>words</code> 的 <strong>有效编码</strong> 由任意助记字符串 <code>s</code> 和下标数组 <code>indices</code>
 * 组成，且满足：</p>
 *
 * <ul>
 * <li><code>words.length == indices.length</code></li>
 * <li>助记字符串 <code>s</code> 以 <code>'#'</code> 字符结尾</li>
 * <li>对于每个下标 <code>indices[i]</code> ，<code>s</code> 的一个从 <code>indices[i]</code> 开始、到下一个 <code>'#'</code> 字符结束（但不包括
 * <code>'#'</code>）的 <strong>子字符串</strong> 恰好与 <code>words[i]</code> 相等</li>
 * </ul>
 *
 * <p>给你一个单词数组&nbsp;<code>words</code> ，返回成功对 <code>words</code> 进行编码的最小助记字符串 <code>s</code> 的长度 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>words = ["time", "me", "bell"]
 * <strong>输出：</strong>10
 * <strong>解释：</strong>一组有效编码为 s = <span><code>"time#bell#" 和 indices = [0, 2, 5</code></span>] 。
 * words[0] = "time" ，s 开始于 indices[0] = 0 到下一个 '#' 结束的子字符串，如加粗部分所示 "<strong>time</strong>#bell#"
 * words[1] = "me" ，s 开始于 indices[1] = 2 到下一个 '#' 结束的子字符串，如加粗部分所示 "ti<strong>me</strong>#bell#"
 * words[2] = "bell" ，s 开始于 indices[2] = 5 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#<strong>bell</strong>#"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>words = ["t"]
 * <strong>输出：</strong>2
 * <strong>解释：</strong>一组有效编码为 s = "t#" 和 indices = [0] 。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= words.length &lt;= 2000</code></li>
 * <li><code>1 &lt;= words[i].length &lt;= 7</code></li>
 * <li><code>words[i]</code> 仅由小写字母组成</li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>字典树</li><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍
 * 297</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 820
 * 单词的压缩编码
 *
 * @author wangweizhou
 * @date 2023-01-09 21:19:25
 */

public class ShortEncodingOfWords {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ShortEncodingOfWords().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 如果一个单词A是另一个单词B的后缀，那么单词A在编码字符串中就不需要单独出现，这是因为单词A可以通过在单词B中偏移下标得到。
        //// 前缀树是一种常见的数据结构，它能够很方便地表达一个字符串是另一个字符树串的前缀。
        //// 这个题目是关于字符串的后缀。
        //// 要把字符串的后缀转换成前缀也比较直观：如果一个字符串A是另一个字符串B的后缀，分别反转字符串A和B得到A'和B'，那么A'是B'的前缀。
        //
        //// 如果一个字符串是另一个字符串的前缀，那么在前缀树中短字符串对应的路径是长字符串对应的路径的一部分。
        //// 由于作为前缀的单词在最短编码中不单独出现，因此在计算最短编码的长度时前缀单词的长度不用考虑，而且它在前缀树中对应的路径的长度也不需要考虑。
        //// 因此，只需要统计前缀树中从根节点到叶节点的所有路径的长度。
        //// 如果两个单词共享部分前缀，但一个字符串不是另一个字符串的子字符串，那么公共前缀部分在编码中将会出现，在前缀树中统计路径长度时也会重复统计。
        //
        //// 由于在最短编码之中出现的每个单词之后都有一个字符'＃'，因此计算长度时出现的每个单词的长度都要加1。
        //// 在前缀树中统计路径长度时，可以统计从根节点到每个叶节点的路径的长度。
        //// 前缀树的根节点并不对应单词的任何字符，在统计路径时将根节点包括进去相当于将单词的长度加1。
        //// 通常用深度优先遍历的算法统计路径的长度。



        // 解法1：前缀树  反转字符串建立前缀树+从根节点遍历到每一个路径的叶子节点的长度
        public int minimumLengthEncoding(String[] words) {
            if (words == null || words.length == 0) {
                return 0;
            }
            TrieNode root = buildTrie(words);// 逆序创建前缀树
            int[] total = {0};// 前缀树中从根节点到叶节点的所有路径的长度。一个元素的数组用来传递参数，表示最短的单词编码长度
            // 前缀树的根节点不保存数据，所以从根节点的子节点开始遍历，所以这里给length加1。这个根节点刚好相当于编码末尾的“#”
            dfs(root, 1, total);
            return total[0];
        }



        // 根据参数数组words创建逆序的前缀树：如果一个字符串A是另一个字符串B的后缀，分别反转字符串A和B得到A'和B'，那么A'是B'的前缀。
        private TrieNode buildTrie(String[] words) {
            TrieNode root = new TrieNode();// 创建前缀树的根节点
            for (String word : words) {// 遍历字符串数组的每一个元素
                TrieNode node = root;// 前缀树节点的遍历指针
                // 逆序遍历字符串word建立前缀树的某一个支路
                for (int i = word.length() - 1; i >= 0; i--) {
                    char ch = word.charAt(i);
                    if (node.children[ch - 'a'] == null) {
                        node.children[ch - 'a'] = new TrieNode();
                    }
                    node = node.children[ch - 'a'];
                }
            }
            return root;
        }



        // 获取前缀树的支路深度,这个要记忆一下
        // length:表示某一个支路的长度【深度】；total：前缀树中从根节点到叶节点的所有路径的长度。即最短遍历路径的长度。
        // 叶子节点表示没有子节点
        // 递归遍历每一条从根节点到叶子节点的路径获取每一条路径的长度。
        // 因为一个节点可能有若干叶子节点，而且只要该节点有一个叶子节点就说明该节点不是叶子节点。那么若该节点有子节点时，说明该节点不是叶子节点比较容易。
        //
        private void dfs(TrieNode root, int length, int[] total) {
            // 处理当前节点，假设当前节点root是前缀树的叶子节点
            boolean isLeaf = true;// 标记是否遍历到前缀树的叶子节点，
            // 下面循环的开始条件是当前节点有子节点，递归调用时最终越过了叶子节点，进入了空节点，下面循环不会执行，这时候支路的长度就已经加1了，不需要额外再添加
            // 如果当前节点有至少一个子节点，那么该节点就不是叶子节点，这时候要以该子节点为开始节点深度遍历下一层。
            for (TrieNode child : root.children) {// 遍历当前节点的所有子节点
                if (child != null) {// 节点root的某一个子节点child不为空，那么节点root不是叶子节点
                    isLeaf = false;// 节点root有子节点，那么节点root不是叶子节点
                    dfs(child, length + 1, total);// 沿着当前的路径进入下一层
                }
            }
            if (isLeaf) {// 当遍历完了某一个路径，则将该支路的长度累加到总长度上
                total[0] += length;
            }
        }




        class TrieNode {
            // 由于这个题目只关注前缀树的所有从根节点到叶节点的路径的长度，并不需要查找单词，因此并不需要知道哪些节点对应一个单词的最后一个字符，
            // 代码中表示前缀树节点的类型TrieNode中没有字段isWord。
            public TrieNode[] children;
            public TrieNode() {
                children = new TrieNode[26];
            }
        }





        //
        //// 解法2：利用排序将字符串进行降序排列，将长字符串对应的路径统计到结果中
        //public int minimumLengthEncoding(String[] words) {
        //    int len = 0;
        //    Trie trie = new Trie();
        //    // 先对单词列表根据单词长度由长到短排序，
        //    // static <T> void sort(T[] a, Comparator<? super T> c) 根据指定的比较器所指定的顺序对指定的对象数组进行排序。
        //    // 下面的比较器使用lambda表达式来实现，实现的是降序排列。
        //    // 因为是降序排列，那么在只会统计前缀树中短字符串对应的路径长字符串，不需要统计短字符串对应的路径。
        //    Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
        //    // 遍历字符串数组将单词插入trie，返回该单词增加的编码长度
        //    for (String word: words) {
        //        len += trie.insert(word);
        //    }
        //    return len;
        //}
        //
        //// 定义tire
        //class Trie {// 前缀树定义
        //    TrieNode root;// 前缀树的根节点
        //    public Trie() {// 构造器初始化前缀树的根节点
        //        root = new TrieNode();
        //    }
        //    public int insert(String word) {// 建立前缀树
        //        TrieNode cur = root;// 前缀树的遍历指针
        //        boolean isNew = false;// 标识当前支路是否是没有遍历过的支路
        //        // 倒着插入单词
        //        for (int i = word.length() - 1; i >= 0; i--) {
        //            int c = word.charAt(i) - 'a';
        //            if (cur.children[c] == null) {// 当前记得的子节点是空，那么就需要建立该节点的下一个新的节点，就是进入的新支路
        //                isNew = true; // 是新单词
        //                cur.children[c] = new TrieNode();
        //            }
        //            cur = cur.children[c];// 进入支路的下一个节点
        //        }
        //        // 如果是新单词的话编码长度增加新单词的长度+1，否则不变。 因为每一个单词后面后缀有#号，所以要额外加1。
        //        return isNew? word.length() + 1: 0;
        //    }
        //}
        //
        //
        //class TrieNode {// 前缀树的节点定义
        //    char val;
        //    TrieNode[] children = new TrieNode[26];
        //    public TrieNode() {}
        //}





        //// 解法3：逆序排序
        //public int minimumLengthEncoding(String[] words) {
        //    if (words == null || words.length == 0) {
        //        return 0;
        //    }
        //    int len = words.length;
        //
        //    // 反转字符串数组中的每个单词
        //    String[] reversed_words = new String[len];
        //    for (int i = 0; i < len; i++) {
        //        // 将字符串word[i]反转：这里先将字符串转换为可变字符串然后再反转，然后再将反转后的可变字符串转换成字符串
        //        reversed_words[i] = new StringBuilder(words[i]).reverse().toString();
        //    }
        //    // 字典序排序  这里是默认的排序方式：升序排列
        //    Arrays.sort(reversed_words);
        //
        //    int ans = 0;
        //    for (int i = 0; i < len; i++) {
        //        // 判断后一个字符串是否以前一个字符串开始，即前一个字符串是否是后一个字符串的前缀
        //        if (i + 1 < len && reversed_words[i + 1].startsWith(reversed_words[i])) {
        //            // 当前单词是下一个单词的前缀，丢弃
        //        } else {
        //            // 当前字符串不是后一个字符串的前缀，那么当前字符串就是最短编码之中的一个单词，然后在每个单词后面都要加“#”。
        //            ans += reversed_words[i].length() + 1; // 单词加上一个 '#' 的长度
        //        }
        //    }
        //    return ans;
        //}






        //// 解法2：逆序排序  自定义排序没怎么看懂
        //public int minimumLengthEncoding(String[] words) {
        //    if (words == null || words.length == 0) {
        //        return 0;
        //    }
        //    int len = words.length;
        //
        //    Comparator<String> cmp = (s1, s2) -> {
        //        int N1 = s1.length();
        //        int N2 = s2.length();
        //        for (int i = 0; i < Math.min(N1, N2); i++) {
        //            char c1 = s1.charAt(N1 - 1 - i);
        //            char c2 = s2.charAt(N2 - 1 - i);
        //            int c = Character.compare(c1, c2);
        //            if (c != 0) {
        //                return c;
        //            }
        //        }
        //        return Integer.compare(N1, N2);
        //    };
        //    // 逆序字典序排序
        //    Arrays.sort(words, cmp);
        //
        //    int res = 0;
        //    for (int i = 0; i < len; i++) {
        //        if (i + 1 < len && words[i + 1].endsWith(words[i])) {
        //            // 当前单词是下一个单词的后缀，丢弃
        //        } else {
        //            res += words[i].length() + 1; // 单词加上一个 '#' 的长度
        //        }
        //    }
        //    return res;
        //
        //}



    }

//leetcode submit region end(Prohibit modification and deletion)

}
