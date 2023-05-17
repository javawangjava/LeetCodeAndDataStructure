/**
 * <p>在英语中，我们有一个叫做&nbsp;<code>词根</code>(root) 的概念，可以词根<strong>后面</strong>添加其他一些词组成另一个较长的单词——我们称这个词为&nbsp;
 * <code>继承词</code>(successor)。例如，词根<code>an</code>，跟随着单词&nbsp;<code>other</code>(其他)，可以形成新的单词&nbsp;
 * <code>another</code>(另一个)。</p>
 *
 * <p>现在，给定一个由许多<strong>词根</strong>组成的词典 <code>dictionary</code> 和一个用空格分隔单词形成的句子
 * <code>sentence</code>。你需要将句子中的所有<strong>继承词</strong>用<strong>词根</strong>替换掉。如果<strong>继承词</strong>有许多可以形成它的<strong
 * >词根</strong>，则用<strong>最短</strong>的词根替换它。</p>
 *
 * <p>你需要输出替换之后的句子。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * <strong>输出：</strong>"the cat was rat by the bat"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * <strong>输出：</strong>"a a b c"
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= dictionary.length&nbsp;&lt;= 1000</code></li>
 * <li><code>1 &lt;= dictionary[i].length &lt;= 100</code></li>
 * <li><code>dictionary[i]</code>&nbsp;仅由小写字母组成。</li>
 * <li><code>1 &lt;= sentence.length &lt;= 10^6</code></li>
 * <li><code>sentence</code>&nbsp;仅由小写字母和空格组成。</li>
 * <li><code>sentence</code> 中单词的总量在范围 <code>[1, 1000]</code> 内。</li>
 * <li><code>sentence</code> 中每个单词的长度在范围 <code>[1, 1000]</code> 内。</li>
 * <li><code>sentence</code> 中单词之间由一个空格隔开。</li>
 * <li><code>sentence</code>&nbsp;没有前导或尾随空格。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 * <div><div>Related Topics</div><div><li>字典树</li><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍
 * 263</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.List;

/**
 * 648
 * 单词替换
 *
 * @author wangweizhou
 * @date 2022-11-20 22:27:35
 */

public class ReplaceWords {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ReplaceWords().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 用前缀树解决问题通常分为两步，第1步是创建前缀树，第2步是在前缀树中查找。
        //// 创建前缀树的过程就是将字典中的单词逐个添加到前缀树中。
        //// 解法1： 分割字符串+查找字符串前缀+替换字符串前缀
        //// 最后考虑如何替换句子中的单词。通常，英语使用空格作为分隔符，因此可以根据空格将句子分隔成若干单词。
        //// 可以从前缀树中查找分隔出来的每个单词的前缀，如果找到了单词的前缀，则用前缀替换该单词。


        public String replaceWords(List<String> dictionary, String sentence) {
            if (dictionary == null || dictionary.size() == 0 || sentence == null) {
                return null;
            }
            TrieNode root = buildTrie(dictionary);// 以词根字典dictionary创建前缀树
            String[] words = sentence.split(" ");// 根据空格将字符串sentence分隔成若干单词
            // 从前缀树中查找分割出来的每个单词的前缀，如果找到了单词的前缀，则用前缀替换该单词
            for (int i = 0; i < words.length; i++) {
                String prefix = findPrefix(root, words[i]);// 从前缀树中查找分割出来的每个单词words[i]的前缀，
                if (!prefix.isEmpty()) {// 如果找到了单词words[i]的前缀，则用前缀替换该单词
                    words[i] = prefix;
                }
            }
            //// 下面这种会报错
            //for (String str : words) {
            //    String prefix = findPrefix(root, str);
            //    if (!prefix.isEmpty()) {
            //        str = prefix;
            //    }
            //}
            return String.join(" ", words);// 以空格为分隔符将字符数组连接成字符串
            // static String join(CharSequence delimiter, CharSequence... elements)
            // 返回由CharSequence... elements元素组成的以delimiter为分隔符的字符串
        }



        // 在前缀树中查找单词word的前缀
        // 题目要求用前缀替换句子中的单词，因此需要找出单词的前缀。
        // 由于已经用函数buildTrie将所有单词的前缀都添加到前缀树中，
        // 因此接下来在前缀树中查找单词的前缀，即从前缀树的根节点出发，逐个判断节点是否有子节点与单词的字符对应。
        // 如果在查找过程中遇到一个isWord标记为true的节点，那么就找到了单词的前缀。
        // 其实就是判断每个单词是否以前缀树中的前缀开始。

        private String findPrefix(TrieNode root, String word) {
            TrieNode node = root;// node为前缀树的遍历指针
            StringBuilder builder = new StringBuilder();// 保存前缀树中已经遍历过的节点，其实就是临时保存可能的前缀。
            for (char ch : word.toCharArray()) {
                // node.childNode[ch - 'a'] == null：如果当前节点不存在第[ch-'a']的子节点【第几个子节点】。
                // 或者 node.isWord：字符串没有遍历完但是前缀树的前缀已经遍历完了。
                if (node.children[ch - 'a'] == null || node.isWord) {// 其实也就是已经重合的路径走完了，但是单词word还没有遍历完。
                    break;
                }
                // 前面if里面有break,会结束循环，后续相当于else选项，也就是当前节点node有节点值为[ch-'a']的子节点。
                builder.append(ch);// 当前字符可能是前缀的一部分。将重合的字符添加到变长字符串中。
                node = node.children[ch - 'a'];// 指针沿着路径后移。
            }
            // 上面的for()循环结束的3种情况：
            // 1、单词遍历完了每一个字母，但是没有找到前缀；
            // 2、单词没有遍历完每一个字母，但是在前缀树中没有路径与之对应【node.children[ch - 'a'] == null】；
            // 3、单词没有遍历完，但是在前缀树中已经找到了前缀，也就是遍历路径的过程中遇到了isWord是true。

            // 路径到达的该节点是否是字典中的单词【前缀】，只要在前缀树中搜寻前缀结束时，当前节点node的isWord是true的时候才说明找到了带查找单词word的前缀。
            // 注意这里false的时候，返回的是空字符串，所以再调用方法时检查返回值是否为空字符串相对应。
            // 找到前缀需要在单词没有遍历完的情况下，在前缀树的某一个路径中遇到了node.isWord是true的情况。
            return node.isWord ? builder.toString() : "";
            // 下面的if-else和上面的return语句一样
            //if(node.isWord){
            //	return builder.toString();
            //}else {
            //	return "";
            //}

        }


        // 创建前缀树：将字典中的单词逐个添加到前缀树中，就是将前缀树插入字符串的方法改成循环
        private TrieNode buildTrie(List<String> dict) {
            TrieNode root = new TrieNode();// 创建前缀树的根节点
            for (String word : dict) {// 循环遍历字典中的单词
                // 将当前字符串插入前缀树中
                TrieNode node = root;// node是遍历节点
                for (char ch : word.toCharArray()) {
                    if (node.children[ch - 'a'] == null) {
                        node.children[ch - 'a'] = new TrieNode();
                    }
                    node = node.children[ch - 'a'];
                }
                // 当所有节点都遍历完成，将当前节点，即最后一个节点的标识符isWord置为true，表示从根节点遍历到该节点的字母组成了一个单词
                node.isWord = true;
            }
            return root;
        }


        class TrieNode {
            boolean isWord = false;// 该节点的属性---isWord，它是一个标签，用来表示是否是一个完整的单词
            // 类比二叉树的左右子节点。前缀树是多叉树，有多个子节点。
            // 因为本题每个节点只能是字符，共有26个字符，那么每一个节点可能有26个子节点。这里定义成大小为26的数组来存储子节点。引用类型默认值是null。
            TrieNode[] children = new TrieNode[26];// 定义该节点的孩子节点，用一个长度为26的TrieNode[]数组来存储指向下一个孩子节点的指针
        }

    }

//leetcode submit region end(Prohibit modification and deletion)

}
