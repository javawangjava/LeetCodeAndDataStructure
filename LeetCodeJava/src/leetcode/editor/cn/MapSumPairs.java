/**
 * <p>设计一个 map ，满足以下几点:</p>
 *
 * <ul>
 * <li>字符串表示键，整数表示值</li>
 * <li>返回具有前缀等于给定字符串的键的值的总和</li>
 * </ul>
 *
 * <p>实现一个 <code>MapSum</code> 类：</p>
 *
 * <ul>
 * <li><code>MapSum()</code> 初始化 <code>MapSum</code> 对象</li>
 * <li><code>void insert(String key, int val)</code> 插入 <code>key-val</code> 键值对，字符串表示键 <code>key</code> ，整数表示值
 * <code>val</code> 。如果键 <code>key</code> 已经存在，那么原来的键值对&nbsp;<code>key-value</code>&nbsp;将被替代成新的键值对。</li>
 * <li><code>int sum(string prefix)</code> 返回所有以该前缀 <code>prefix</code> 开头的键 <code>key</code> 的值的总和。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * <strong>输出：</strong>
 * [null, null, 3, null, 5]
 *
 * <strong>解释：</strong>
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // 返回 3 (<u>ap</u>ple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // 返回 5 (<u>ap</u>ple + <u>ap</u>p = 3 + 2 = 5)
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= key.length, prefix.length &lt;= 50</code></li>
 * <li><code>key</code> 和 <code>prefix</code> 仅由小写英文字母组成</li>
 * <li><code>1 &lt;= val &lt;= 1000</code></li>
 * <li>最多调用 <code>50</code> 次 <code>insert</code> 和 <code>sum</code></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>设计</li><li>字典树</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍
 * 225</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;

/**
 * 677
 * 键值映射
 * @author wangweizhou
 * @date 2023-01-10 15:19:39
 */
public class MapSumPairs {
    public static void main(String[] args) {
        //测试代码
        // Solution solution = new MapSumPairs().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MapSum {


        // 解法2： 前缀树
        // 既然需要根据字符串的前缀进行查找，就可以使用前缀树。
        private TrieNode root;
        // MapSum() 初始化 MapSum 对象
        public MapSum() {// 构造器初始化根节点
            root = new TrieNode();
        }


        // 其实就是在前缀树中插入某一个支路路径的修改
        // 考虑MapSum的成员函数insert。在前缀树中添加字符串的过程和之前类似，唯一和之前不同的是，
        // 当到达字符串最后一个字符对应的节点时，将该节点的value字段的值设为字符串的值。
        // void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。
        // 如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
        public void insert(String key, int val) {
            TrieNode node = root;
            for (int i = 0; i < key.length(); i++) {
                char ch = key.charAt(i);
                if (node.children[ch - 'a'] == null) {
                    // node.children[ch - 'a'] == null 表示当前节点的第ch-'a'子节点为空，这时候字符串key还没有遍历完，则新建该孩子节点
                    node.children[ch - 'a'] = new TrieNode();
                }
                node = node.children[ch - 'a'];
            }
            // 如果一个节点对应一个字符串的最后一个字符，那么该节点的整数字段的值就设为字符串的值；
            node.value = val;// 这里遍历完了当前路径key之后，将最后一个节点的值设置为字符串的值
        }



        // 当输入一个前缀在前缀树中查找时，可以在前缀树中逐个查找和前缀中每个字符对应的节点。
        // 如果当扫描到字符串的每个字符时前缀树中已经没有节点与之对应，那么前缀树中没有以该前缀开头的字符串，直接返回0。
        // 如果一直到字符串的最后一个字符前缀树中都有节点与其对应，那么前缀树中存在若干以该前缀开头的字符串。
        // 在前缀树中查找前缀的所有字符之后，就处在的节点对应前缀的最后一个字符，以该前缀开头的所有字符的后序字符对应的节点都在当前所处节点的子树中，
        // 可以遍历整个子树找出所有以前缀开头的字符串。
        // 在找到前缀prefix最后一个字符对应的节点之后，上述代码调用函数getSum递归地遍历该节点的整个子树，
        // 以便找出所有以前缀开头的字符串并累加它们的值。

        // int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
        // 这个其实就是找出前缀的改编
        public int sum(String prefix) {
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); i++) {// 遍历前缀的每一个字符，在前缀树中寻找是否存在当前前缀的路径
                char ch = prefix.charAt(i);
                if (node.children[ch - 'a'] == null) {// 前缀树中不含有当前前缀表示的路径
                    // node.children[ch - 'a'] == null 表示当前节点的第ch-'a'子节点为空，这时候字符串prefix还没有遍历完，则表示没有前缀是prefix的字符串
                    return 0;
                }
                node = node.children[ch - 'a'];
            }
            // 前面遍历完了前缀prefix，这时候node是前缀字符串的最后一个字符
            return getSum(node);
        }



        // 就是深度遍历
        // 递归遍历求以node为根节点的前缀树的所有路径的值之和
        // 因为这种方法定义的只有在路径结束的最后一个节点的val才有值，其他中间节点的数值都是0。所以在沿着路径前进时可以累加每一个节点的数值val。
        private int getSum(TrieNode node) {
            if (node == null) {
                return 0;
            }
            int result = node.value;
            for (TrieNode child : node.children) {// 递归遍历以node为根节点的所有路径的值，然后累加
                result += getSum(child);
            }
            return result;
        }



        // 数据域和方法一样根据需要来选择实现
        // 首先定义前缀树节点的数据结构。由于每个字符串对应一个数值，因此需要在节点中增加一个整数字段。
        // 如果一个节点对应一个字符串的最后一个字符，那么该节点的整数字段的值就设为字符串的值；
        // 如果一个节点对应字符串的其他字符，那么该节点的整数字段将被设为0。
        // 由于这个题目只关注字符串对应的值之和，这些值已经在节点中的整数字段得以体现，因此节点中没有必要包含一个布尔变量标识节点是否对应字符串的最后一个字符。
        class TrieNode {
            public TrieNode[] children;
            // 因此需要在节点中增加一个整数字段。
            // 如果一个节点对应一个字符串的最后一个字符，那么该节点的整数字段的值就设为字符串的值；
            // 如果一个节点对应字符串的其他字符，那么该节点的整数字段将被设为0。
            public int value;// 由于每个字符串对应一个数值，因此需要在节点中增加一个整数字段。
            public TrieNode() {
                children = new TrieNode[26];
            }
        }







        //// 解法1：暴力扫描
        //// 我们将所有的 key-val 键值进行存储，每次需要搜索给定的前缀 prefix 时，我们依次搜索所有的键值。
        //// 如果键值包含给定的前缀，则我们将其val 进行相加，返回所有符合要求的 val 的和。
        //
        //Map<String, Integer> map;// 哈希表存储所有的键值对
        //public MapSum() {//
        //    map = new HashMap<>();
        //}
        //
        //
        //// void insert(String key, int val)
        //// 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
        //public void insert(String key, int val) {//
        //    map.put(key,val);
        //}
        //
        //
        //// int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
        //public int sum(String prefix) {
        //    int res = 0;
        //    for (String s : map.keySet()) {
        //        if (s.startsWith(prefix)) {
        //            res += map.get(s);
        //        }
        //    }
        //    return res;
        //}
        //





        //// 解法3：
        //// 依题分析，前缀想到用字典树，但是字典树不能表示重复元素更改val，就需要一个哈希表
        //// 所以，MapSum的数据结构应该是TreeNode和HashMap
        //
        //TreeNode root ;
        //HashMap<String,Integer> map;
        //public MapSum() {// 构造器初始化
        //    root = new TreeNode();
        //    map = new HashMap<>();
        //}
        //
        //
        //// void insert(String key, int val)
        //// 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
        //// 注意下面这种写法是路径key的每一个节点的val域都是值val。所以每一有路径通过某一个节点，那么该节点的val值就应用加上该路径key对应的val。
        //public void insert(String key, int val) {
        //    //巧妙写法，不用判断这个key是存在还是不存在的
        //    //如果存在，最后的val只需要加上差值即可；
        //    //如果不存在,map.getOrDefault(key)出来的值为0，差值为val-0=val
        //    int delta = val - map.getOrDefault(key,0);// 这一个就是方便处理key已经存在的，通过补差值实现。
        //    map.put(key,val);//新添或者重置map中的值(注意此时只是重置了map中的key-val，map作用只是为了记录是否重复)
        //    TreeNode node = root;
        //    //新添或者重置TreeNode
        //    for(Character c : key.toCharArray()){
        //        if(node.sonNode[c-'a']==null){
        //            node.sonNode[c-'a'] = new TreeNode();
        //        }
        //        node = node.sonNode[c-'a'];
        //        node.val += delta;  //结合刚才的注释，就知道为啥这里要加delta
        //    }
        //}
        //
        //
        //
        ////
        //public int sum(String prefix) {
        //    TreeNode node = root;
        //    for(Character c : prefix.toCharArray()){// 遍历前缀字符串
        //        if(node.sonNode[c-'a']== null){// 当前节点
        //            return 0;
        //        }
        //        node= node.sonNode[c-'a'];
        //    }
        //    //上述遍历完之后，node节点就是当前树路径拼出来的prefix的节点，直接返回这个节点的val即可
        //    return node.val;
        //}
        //
        //
        //// 字典树
        //class TreeNode{
        //    //每个字母下可以有26个字母，也就是每个节点下有26个分支
        //    TreeNode[] sonNode = new TreeNode[26];
        //    int val = 0;
        //}


    }

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
