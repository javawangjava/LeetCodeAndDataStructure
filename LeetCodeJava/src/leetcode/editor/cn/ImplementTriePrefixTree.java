/**
 * <p><strong><a href="https://baike.baidu.com/item/字典树/9825209?fr=aladdin" target="_blank">Trie</a>
 * </strong>（发音类似 "try"）或者说 <strong>前缀树</strong> 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。</p>
 *
 * <p>请你实现 Trie 类：</p>
 *
 * <ul>
 * <li><code>Trie()</code> 初始化前缀树对象。</li>
 * <li><code>void insert(String word)</code> 向前缀树中插入字符串 <code>word</code> 。</li>
 * <li><code>boolean search(String word)</code> 如果字符串 <code>word</code> 在前缀树中，返回 <code>true</code>（即，在检索之前已经插入）；否则，返回
 * <code>false</code> 。</li>
 * <li><code>boolean startsWith(String prefix)</code> 如果之前已经插入的字符串 <code>word</code> 的前缀之一为 <code>prefix</code> ，返回
 * <code>true</code> ；否则，返回 <code>false</code> 。</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入</strong>
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * <strong>输出</strong>
 * [null, null, true, false, true, null, true]
 *
 * <strong>解释</strong>
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= word.length, prefix.length <= 2000</code></li>
 * <li><code>word</code> 和 <code>prefix</code> 仅由小写英文字母组成</li>
 * <li><code>insert</code>、<code>search</code> 和 <code>startsWith</code> 调用次数 <strong>总计</strong> 不超过 <code>3 *
 * 10<sup>4</sup></code> 次</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>设计</li><li>字典树</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍
 * 1335</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


/**
 * 208
 * 实现 Trie (前缀树)
 *
 * @author wangweizhou
 * @date 2022-11-20 16:46:03
 */

public class ImplementTriePrefixTree {

    public static void main(String[] args) {
        //测试代码
        //Solution solution = new ImplementTriePrefixTree().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    // 定义前缀树，前缀树有一个根节点。前缀树的数据域是程序员可以选择的，
    // 前缀树是一颗多叉树。前缀树中除根节点外，每个节点表示字符串中的一个字符，字符串由前缀树的路径表示、前缀树的根节点不表示任何字符【即前缀树的节点中其实没有定义保存字符的成员变量】。

    class Trie {


        // 解法1：前缀树 写法1
        private TrieNode root;// 前缀树的根节点
        public Trie() {// 构造器初始化成员变量
            root = new TrieNode();
        }

        // 在前缀树中添加单词时，首先到达前缀树的根节点，确定根节点是否有一个子节点和单词的第1个字符对应。如果已经有对应的子节点，则前往该子节点。
        // 如果该子节点不存在，则创建一个与第1个字符对应的子节点，并前往该子节点。
        // 接着判断该子节点中是否存在与单词的第2个字符相对应的子节点，并依次类推，将单词其他的字符添加到前缀树中。
        // 当单词的所有字符都添加到前缀树中之后，所在的节点对应的单词的最后一个字符。
        // 为了标识路径到达该节点时已经对应一个完整的单词，需要将该节点的标识符isWord设为true。
        public void insert(String word) {
            TrieNode node = root;// 遍历指针node指向前缀树的根节点,前缀树的遍历从根节点开始
            for (char ch : word.toCharArray()) {// 遍历字符串的每一个字符
                // ch - 'a'：将字符转换为数字，node.children[ch - 'a']表示节点node的第（ch - 'a'）个子节点。
                if (node.children[ch - 'a'] == null) {// 如果当前节点不存在节点值为[ch-'a']的子节点【第几个子节点】，则新建该孩子节点并指向它
                    // 注意前缀树的节点中没有一个变量【字段】表示节点对应的字符。
                    // 这是因为可以通过节点是其父节点的第几个子节点得知它对应的字符，也就没有必要在节点中添加一个变量【字段】。
                    // 即通过该子节点是父节点的第几个子节点得知该子节点对应的字符，这里用数组表示子节点个数，那么数组下标就是第几个字符。
                    node.children[ch - 'a'] = new TrieNode();// 所以只要子节点不空就可以
                }
                node = node.children[ch - 'a'];// 如果当前节点的值为[ch-'a']的孩子节点存在，则node指向该孩子节点。其实就是指针后移，沿着符合的路径前进。
            }
            // 当所有路径上的节点都遍历完成，将当前节点，即最后一个节点的标识符isWord置为true，表示从根节点遍历到该节点的字母组成了一个单词
            node.isWord = true;
        }



        // 从前缀树的根节点开始查找。如果根节点没有一个子节点和字符串的第1个节点相对应，那么前缀树中自然不存在查找的单词，直接返回false。
        // 如果根节点中存在与第1个字符相对应的子节点，则前往该子节点，接着匹配单词的第2个字符。以此类推，直到到达和字符串最后一个字符对应的节点。
        // 如果该节点的isWord的值为true，那么路径到达该节点时正好对应输入的单词，因此前缀树存在输入的单词，可以返回true;否则返回false。
        public boolean search(String word) {
            TrieNode node = root;// 遍历指针node指向前缀树的根节点,前缀树的遍历从根节点开始
            for (char ch : word.toCharArray()) {// 遍历字符串的每一个字符
                if (node.children[ch - 'a'] == null) {// 如果当前节点不存在节点值为[ch-'a']的子节点【第几个子节点】，返回false。
                    return false;
                }
                node = node.children[ch - 'a'];// 如果当前节点的值为[ch-'a']的孩子节点存在，则node指向该孩子节点。其实就是指针后移，沿着符合的路径前进。
            }
            // 执行到这里遍历完了整个字符串，遍历指针到达字符串最后一个字符对应的节点。
            // 如果该节点的isWord的值为true，即字符串和前缀是一样的，则找到了对应的前缀；如果为false,则表示没有找到对应的前缀。
            return node.isWord;// 那么路径到达该节点时正好对应输入的单词，因此前缀树存在输入的单词，可以返回true;否则返回false。
        }



        // 函数startWith和search不同，只要前缀树中存在以输入的前缀开头的单词就应该返回true。
        // 当顺着路径逐个匹配输入前缀的字符时，如果某个字符没有节点与之对应，那么可以返回false。
        // 如果一直到前缀的最后一个字符在前缀树中都有节点与之对应，那么说明前缀树中一定存在以该前缀开头的单词。
        // 此时无论当前节点的isWord的值是什么，都应该返回true。
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char ch : prefix.toCharArray()) {// 遍历字符串的每一个字符
                if (node.children[ch - 'a'] == null) {// 当顺着路径逐个匹配输入前缀的字符时，如果某个字符没有节点与之对应，那么可以返回false。
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            // 正常遍历完了字符串那么就有以前缀开始的单词。
            return true;
        }




        //// 解法1：前缀树 写法2  只是遍历字符串的具体写法不一样
        //private TrieNode root;// 前缀树的根节点
        //public Trie() {
        //    root=new TrieNode();
        //}
        //
        //public void insert(String word) {
        //    TrieNode node=root;
        //    for (int i = 0; i < word.length(); i++) {
        //        int index=word.charAt(i)-'a';
        //        if(node.children[index]==null){// 如果当前节点的第（word.charAt(i)-'a'）的孩子节点不存在，则新建该孩子节点并指向它。
        //            node.children[index]=new TrieNode();
        //            node=node.children[index];
        //        }else {
        //            node=node.children[index];
        //        }
        //    }
        //    // 当所有节点都遍历完成，将当前节点，即最后一个节点的标识符isWord置为true，表示从根节点遍历到该节点的字母组成了一个单词
        //    node.isWord=true;
        //}
        //
        //
        //public boolean search(String word) {
        //    TrieNode node=root;
        //    for (int i = 0; i < word.length(); i++) {
        //        int index=word.charAt(i)-'a';
        //        if(node.children[index]==null){
        //            return false;
        //        }else {
        //            node=node.children[index];
        //        }
        //    }
        //    return node.isWord;
        //}
        //
        //
        //public boolean startsWith(String prefix) {
        //    TrieNode node=root;
        //    for (int i = 0; i < prefix.length(); i++) {
        //        int index=prefix.charAt(i)-'a';
        //        if(node.children[index]==null){
        //            return false;
        //        }else {
        //            node=node.children[index];
        //        }
        //    }
        //    return true;
        //}


        //// 前缀树节点定义简化：
        //// 定义前缀树中节点的数据结构：前缀树是一颗多叉树。到底有多少分支看需求。
        //// 前缀树中除根节点外，每个节点表示字符串中的一个字符，字符串由前缀树的路径表示、前缀树的根节点不表示任何字符。
        //// 前缀树中的节点对应字符串中的一个字符。如果只考虑英文小写字母，那么字符可能是'a'到'z'的任意一个，因此前缀树中的节点可能有26个子节点。
        //// 可以将26个子节点放到一个数组中，数组中的第1个元素是对应字母'a'的子节点，第2个元素是对应字母'b'的子节点，其余的以此类推。
        //// 即通过该子节点是父节点的第几个子节点得知该子节点对应的字符，这里用数组表示子节点个数，那么数组下标就是第几个字符。
        //// 注意前缀树的节点中没有一个变量【字段】表示节点对应的字符。这是因为可以通过节点是其父节点的第几个子节点得知它对应的字符，也就没有必要在节点中添加一个变量【字段】。
        //// 在通常情况下，需要在TrieNode中包含一个布尔字段isWord，表示到达该节点的路径对应的字符串是否为字典中一个完整的单词。

        class TrieNode {
            // 定义成员变量
            boolean isWord = false;// 该节点的属性---isWord，它是一个标签，用来表示是否是一个完整的单词
            // 类比二叉树的左右子节点。前缀树是多叉树，有多个子节点。
            // 因为本题每个节点只能是字符，共有26个字符，那么每一个节点可能有26个子节点。这里定义成大小为26的数组来存储子节点。引用类型默认值是null。
            TrieNode[] children = new TrieNode[26];// 定义该节点的孩子节点，用一个长度为26的TrieNode[]数组来存储指向下一个孩子节点的指针
            // 类比二叉树，children[5]表示求当前节点TrieNode的第4个子节点
        }


        //// 定义前缀树的节点
        //class TrieNode {
        //	// 该节点的属性---isWord，它是一个标签，用来表示是否是一个完整的单词
        //	boolean isWord;
        //	// 定义该节点的孩子节点，用一个长度为26的TrieNode[]数组来存储指向下一个孩子节点的指针
        //	TrieNode[] children;
        //
        //	public TrieNode {// 构造器
        //		// 默认isWord标签为false
        //		isWord = false;
        //		// 默认孩子节点有26个(英文字母有26个)，用一个长度为26的TrieNode[]数组来存储孩子节点的指针
        //		children = new TrieNode[26];
        //	}
        //}


    }


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
