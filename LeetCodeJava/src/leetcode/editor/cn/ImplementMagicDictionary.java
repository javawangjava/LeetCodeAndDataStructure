/**
 * <p>设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 <strong>互不相同</strong> 。
 * 如果给出一个单词，请判定能否只将这个单词中<strong>一个</strong>字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。</p>
 *
 * <p>实现 <code>MagicDictionary</code> 类：</p>
 *
 * <ul>
 * <li><code>MagicDictionary()</code> 初始化对象</li>
 * <li><code>void buildDict(String[] dictionary)</code> 使用字符串数组 <code>dictionary</code>
 * 设定该数据结构，<code>dictionary</code> 中的字符串互不相同</li>
 * <li><code>bool search(String searchWord)</code> 给定一个字符串 <code>searchWord</code> ，判定能否只将字符串中<strong> 一个
 * </strong>字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
 * </ul>
 *
 * <p> </p>
 *
 * <div class="top-view__1vxA">
 * <div class="original__bRMd">
 * <div>
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入</strong>
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * <strong>输出</strong>
 * [null, null, false, true, false, false]
 *
 * <strong>解释</strong>
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // 返回 False
 * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
 * magicDictionary.search("hell"); // 返回 False
 * magicDictionary.search("leetcoded"); // 返回 False
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= dictionary.length <= 100</code></li>
 * <li><code>1 <= dictionary[i].length <= 100</code></li>
 * <li><code>dictionary[i]</code> 仅由小写英文字母组成</li>
 * <li><code>dictionary</code> 中的所有字符串 <strong>互不相同</strong></li>
 * <li><code>1 <= searchWord.length <= 100</code></li>
 * <li><code>searchWord</code> 仅由小写英文字母组成</li>
 * <li><code>buildDict</code> 仅在 <code>search</code> 之前调用一次</li>
 * <li>最多调用 <code>100</code> 次 <code>search</code></li>
 * </ul>
 * </div>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>设计</li><li>字典树</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍
 * 203</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 676
 * 实现一个魔法字典
 *
 * @author wangweizhou
 * @date 2022-11-21 19:12:33
 */

public class ImplementMagicDictionary {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new ImplementMagicDictionary().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MagicDictionary {


        //// 将每个完整的单词保存到哈希表中并不能解决这个问题。
        //// 这是因为字符串的哈希值是由整个字符串决定的，修改字符串中任意一个字符之后，字符串的哈希值和原来字符串的哈希值没有任何关系。
        //// 因此，如果用哈希表保存字典中的所有单词，就没有办法找出只修改一个字符的字符串。
        //// 除了哈希表，还可以将单词保存到前缀树中，然后在前缀树中查找只修改一个字符的字符串。
        //// 前缀树节点的数据结构和之前一样，每个节点都有26个子节点，还有一个布尔类型的字段isWord表示该节点是否对应字符串的最后一个字符。
        //// 创建前缀树的过程也与面试题63大同小异，此处不再详细讨论。


        TrieNode root;// 前缀树的根节点
        public MagicDictionary() {// 构造器初始化实例变量根节点
            root = new TrieNode();
        }


        // 创建前缀树，就是将字典单词全部插入前缀树中
        public void buildDict(String[] dictionary) {
            if (dictionary == null || dictionary.length == 0) {
                return;
            }
            for (String word : dictionary) {// 遍历字符数组的元素
                TrieNode node = root;// 遍历指针node指向前缀树的根节点,前缀树的遍历从根节点开始
                for (char ch : word.toCharArray()) {// 遍历字符串
                    if (node.childNode[ch - 'a'] == null) {// 如果当前节点的值为[ch-'a']的子节点为不存在，则新建该孩子节点并指向它
                        //注意前缀树的节点中没有一个变量【字段】表示节点对应的字符。这是因为可以通过节点是其父节点的第几个子节点得知它对应的字符，也就没有必要在节点中添加一个变量【字段】。
                        node.childNode[ch - 'a'] = new TrieNode();// 所以只要子节点不空就可以
                    }
                    node = node.childNode[ch - 'a'];// 如果当前节点的值为[ch-'a']的孩子节点存在，则node指向该孩子节点。其实就是指针后移，沿着符合的路径前进。
                }
                // 当所有节点都遍历完成，将当前节点，即最后一个节点的标识符isWord置为true，表示从根节点遍历到该节点的字母组成了一个单词
                node.isWord = true;
            }
        }



        // 讨论如何在前缀树中查找只修改一个字符的字符串。
        // 可以根据深度优先的顺序搜索前缀树的每条路径。
        // 如果到达的节点与字符串中的字符不匹配，则表示此时修改了字符串中的一个字符以匹配前缀树中的路径。
        // 如果到达对应字符串最后一个字符对应的节点时,该节点的isWord字段的值为true，而且此时正好修改了字符串中的一个字符，
        // 那么就找到了修改字符串中一个字符对应的路径，符合题目的条件，可以返回true。

        public boolean search(String searchWord) {
            if(searchWord==null||searchWord.length()==0){
                return false;
            }
            return searchFunc(root, searchWord, 0, 0);
        }



        //// 递归实现深度搜索
        // searchWord:待匹配的字符串；index:是遍历单词的字符指针；editCount:计数器表示字典中单词和searchWord中不同字母的个数。
        private boolean searchFunc(TrieNode root, String searchWord, int index, int editCount) {
            if (root == null) {
                return false;
            }
            // root.isWord：前缀树中对应字符串路径的最后一个字符的isWord字段的值为true。即遍历到了字符串路径的最后一个字符。
            // index == word.length()：越过单词的最后一个字符，也就是遍历完了字符串。editCount == 1：此时正好修改了字符串的一个字符。
            // 恰好遍历完了字符串，恰好对应的路径的最后一个节点的isWord是true，恰好该条路径上的修改的字符数只有一个。
            // 当遍历完某一个字符串时，恰好到达了该字符串对应的路径的最后一个节点【对应路径的最后一个节点的isWord字段的值为true】，
            // 而且此时整个路径中正好修改了字符串中的一个字符，那么就找到了修改字符串中一个字符对应的路径，符合题目的条件，可以返回true。
            if (root.isWord && index == searchWord.length() && editCount == 1) {// 就是到达了前缀树某一支路和单词的末尾，同时该路径上修改的字符只有一个
                return true;
            }
            // 当没有遍历完单词并且截至当前位置已经修改的字符个数<= 1，
            // j是子树中的对应的字符。前缀树的第几个子节点就表示是第几个字符。
            if (index < searchWord.length() && editCount <= 1) {// 要遍历单词的每一个字符然后再在前缀树中去查找
                boolean found = false;// 标识符表示是否找到符合条件的单词。如果进入到到这里，表明还没有找到符合题干要求的。
                // j表示当前节点的的26个子节点中的第j个节点【也就是26个英文字母中的第j个字母】；
                // 这里要将searchWord的第index个字符逐个与当前节点root的26个子节点进行匹配，找到合适的路径
                for (int j = 0; j < 26 && !found; j++) {
                    // 当前缀树中到达的节点和字符串中的字符匹配时，待修改的字符个数不变。当不匹配时，待修改的字符个数加1。
                    // j == searchWord.charAt(index) - 'a' ? 节点root是否含有第（searchWord.charAt(index) - 'a'）个子节点。
                    int next = (j == searchWord.charAt(index) - 'a' ? editCount : editCount + 1);// 注意这里使用的是新变量next,传入下一层
                    found = searchFunc(root.childNode[j], searchWord, index + 1, next);// 深度遍历单词的下一个位置字符,所以这里要(index + 1)。
                }
                return found;// 只有找到了就中途返回found
            }
            return false;// 没找到
        }



        // 前缀树节点定义简化：
        class TrieNode {
            boolean isWord;// 该节点的属性---isWord，它是一个标签，用来表示是否是一个完整的单词
            // 类比二叉树的左右子节点。前缀树是多叉树，有多个子节点。
            // 因为本题每个节点只能是字符，共有26个字符，那么每一个节点可能有26个子节点。这里定义成大小为26的数组来存储子节点。引用类型默认值是null。
            TrieNode[] childNode = new TrieNode[26];// 定义该节点的孩子节点，用一个长度为26的TrieNode[]数组来存储指向下一个孩子节点的指针
        }


}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */

//leetcode submit region end(Prohibit modification and deletion)

}
