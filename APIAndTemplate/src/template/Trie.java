package template;

// 定义前缀树，前缀树有一个根节点。前缀树的数据域是程序员可以选择的，
// 前缀树是一颗多叉树。前缀树中除根节点外，每个节点表示字符串中的一个字符，字符串由前缀树的路径表示、前缀树的根节点不表示任何字符【即前缀树的节点中其实没有定义保存字符的成员变量】。

class Trie {

    private TrieNode root;// 前缀树的根节点

    public Trie() {// 构造器初始化成员变量
        root = new TrieNode();
    }


    // 在前缀树中添加单词时，首先到达前缀树的根节点，确定根节点是否有一个子节点和单词的第1个字符对应。
    // 如果已经有对应的子节点，则前往该子节点。如果该子节点不存在，则创建一个与第1个字符对应的子节点，并前往该子节点。
    // 接着判断该子节点中是否存在与单词的第2个字符相对应的子节点，并依次类推，将单词其他的字符添加到前缀树中。
    // 当单词的所有字符都添加到前缀树中之后，所在的节点对应的单词的最后一个字符。
    // 为了标识路径到达该节点时已经对应一个完整的单词，需要将该节点的标识符isWord设为true。
    public void insert(String word) {
        TrieNode node = root;// 遍历指针node指向前缀树的根节点,前缀树的遍历从根节点开始
        for (char ch : word.toCharArray()) {// 遍历字符串的每一个字符
            // 节点有成员变量多个子节点，然后多个子节点使用数组来统一表示
            // ch - 'a'：将字符转换为数字，node.children[ch - 'a']表示节点node的第（ch - 'a'）个子节点。
            if (node.children[ch - 'a'] == null) {
                // node.children[ch - 'a'] == null 表示当前节点的第ch-'a'子节点为空，这时候字符串word还没有遍历完，则新建该孩子节点
                // 注意前缀树的节点中没有一个变量【字段】表示节点对应的字符。
                // 这是因为可以通过节点是其父节点的第几个子节点得知它对应的字符，也就没有必要在节点中添加一个变量【字段】。
                // 即通过该子节点是父节点的第几个子节点得知该子节点对应的字符，这里用数组表示子节点个数，那么数组下标就是第几个字符。
                node.children[ch - 'a'] = new TrieNode();// 所以只要子节点不空就可以
            }
            node = node.children[ch - 'a'];// 如果当前节点的值为[ch-'a']的孩子节点存在，则node指向该孩子节点。其实就是指针后移，沿着符合的路径前进。
        }
        // 当所有节点都遍历完成，将当前节点，即最后一个节点的标识符isWord置为true，表示从根节点遍历到该节点的字母组成了一个单词
        node.isWord = true;
    }



    // 从前缀树的根节点开始查找。如果根节点没有一个子节点和字符串的第1个节点相对应，那么前缀树中自然不存在查找的单词，直接返回false。
    // 如果根节点中存在与第1个字符相对应的子节点，则前往该子节点，接着匹配单词的第2个字符。以此类推，直到到达和字符串最后一个字符对应的节点。
    // 如果该节点的isWord的值为true，那么路径到达该节点时正好对应输入的单词，因此前缀树存在输入的单词，可以返回true;否则返回false。
    public boolean search(String word) {
        TrieNode node = root;// 遍历指针node指向前缀树的根节点,前缀树的遍历从根节点开始
        for (char ch : word.toCharArray()) {// 遍历字符串的每一个字符
            // node.children[ch - 'a']表示当前节点的第ch-'a'子节点，
            if (node.children[ch - 'a'] == null) {// 如果当前节点不存在节点值为[ch-'a']的子节点【第几个子节点】，返回false。
                // node.children[ch - 'a'] == null表示当前节点的第ch-'a'子节点为空，这时候字符串word还没有遍历完，那么就是不存在word这个字符串,返回false。
                return false;
            }
            node = node.children[ch - 'a'];// 如果当前节点的值为[ch-'a']的孩子节点存在，则node指向该孩子节点。其实就是指针后移，沿着符合的路径前进。
        }
        // 执行到这里遍历完了整个字符串，遍历指针到达字符串最后一个字符对应的节点。
        // 如果该节点的isWord的值为true，即字符串和前缀是一样的，则找到了对应的前缀；如果为false,则表示没有找到对应的前缀。
        return node.isWord;// 那么路径到达该节点时正好对应输入的单词，因此前缀树存在输入的单词，可以返回true;否则返回false。
        // 下面的if-else与上面的return效果一样
        //if(node.isWord){
        //    return true;
        //}else {
        //    return false;
        //}
    }



    // 函数startWith和search不同，只要前缀树中存在以输入的前缀开头的单词就应该返回true。
    // 当顺着路径逐个匹配输入前缀的字符时，如果某个字符没有节点与之对应，那么可以返回false。
    // 如果一直到前缀的最后一个字符在前缀树中都有节点与之对应，那么说明前缀树中一定存在以该前缀开头的单词。
    // 此时无论当前节点的isWord的值是什么，都应该返回true。
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {// 遍历字符串的每一个字符
            // node.children[ch - 'a']表示当前节点的第ch-'a'子节点，
            if (node.children[ch - 'a'] == null) {
                // 当顺着路径逐个匹配输入前缀的字符时，如果某个字符没有节点与之对应，那么可以返回false。
                // node.children[ch - 'a'] == null表示当前节点的第ch-'a'子节点为空，这时候字符串prefix还没有遍历完，那么就是不存在prefix这个字符串,返回false。
                return false;
            }
            node = node.children[ch - 'a'];
        }
        // 正常遍历完了字符串那么就有以前缀开始的单词。
        return true;
    }



    // 获取前缀树的支路深度,这个要记忆一下
    // length:表示某一个支路的长度【深度】，即某一条从根节点到叶子节点的路径的长度；
    // total：前缀树中从根节点到叶节点的所有路径的长度。即最短遍历路径的长度
    // 叶子节点表示没有子节点
    // 递归遍历每一条从根节点到叶子节点的路径获取每一条路径的长度。
    private void dfs(TrieNode root, int length, int[] total) {
        boolean isLeaf = true;// 标记是否遍历到前缀树的叶子节点
        // 下面循环的开始条件是当前节点有子节点，递归调用时最终越过了叶子节点，进入了空节点，
        // 下面循环不会执行，这时候支路的长度就已经加1了，不需要额外再添加
        for (TrieNode child : root.children) {// 遍历当前节点的所有子节点
            if (child != null) {// 节点root的某一个子节点child不为空，那么节点root不是叶子节点
                isLeaf = false;// 节点root有子节点，那么节点root不是叶子节点
                dfs(child, length + 1, total);// 沿着当前的路径进入下一层，每进入下一层深度加1。
            }
        }
        if (isLeaf) {// 当遍历完了某一条路径到达叶子节点时，则将该支路的长度累加到总长度上
            total[0] += length;
        }
    }



    // 前缀树节点：前缀树节点的数据域和链表的节点【二叉树节点】类似，可以自由定义

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


// 定义前缀树
// 只是遍历字符串的具体写法不一样,这里使用的是charAt()

class Trie1 {

    private TrieNode root;// 前缀树的根节点

    public Trie1() {// 构造器初始化成员变量
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            // word.charAt(i) - 'a'表示26个字母中的第word.charAt(i) - 'a'个
            int c = word.charAt(i) - 'a';
            // 如果当前节点的值为c的孩子节点不存在，则新建该孩子节点并指向它
            if (curr.childNode[c] == null) {
                curr.childNode[c] = new TrieNode();
            }
            curr = curr.childNode[c];
        }
        // 当所有节点都遍历完成，将当前节点，即最后一个节点的标识符isWord置为true，表示从根节点遍历到该节点的字母组成了一个单词
        curr.isWord = true;
    }


    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (curr.childNode[c] == null) {
                return false;
            }
            curr = curr.childNode[c];
        }
        return curr.isWord;
    }


    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int c = prefix.charAt(i) - 'a';
            if (curr.childNode[c] == null) {
                return false;
            }
            curr = curr.childNode[c];
        }
        return true;
    }


    //前缀树节点定义简化：
    //定义前缀树中节点的数据结构：前缀树是一颗多叉树。前缀树中除根节点外，每个节点表示字符串中的一个字符，字符串由前缀树的路径表示、前缀树的根节点不表示任何字符。
    //前缀树中的节点对应字符串中的一个字符。如果只考虑英文小写字母，那么字符可能是'a'到'z'的任意一个，因此前缀树中的节点可能有26个子节点。
    //可以将26个子节点放到一个数组中，数组中的第1个元素是对应字母'a'的子节点，第2个元素是对应字母'b'的子节点，其余的以此类推。
    //注意前缀树的节点中没有一个变量【字段】表示节点对应的字符。这是因为可以通过节点是其父节点的第几个子节点得知它对应的字符，也就没有必要在节点中添加一个变量【字段】。
    //节点中还有一个布尔类型的字段表示到达该节点的路径对应的字符串是否为字典中一个完整的单词。
    class TrieNode {
        boolean isWord;// 该节点的属性---isWord，它是一个标签，用来表示是否是一个完整的单词
        // 类比二叉树的左右子节点。前缀树是多叉树，有多个子节点。
        // 因为本题每个节点只能是字符，共有26个字符，那么每一个节点可能有26个子节点。这里定义成大小为26的数组来存储子节点。引用类型默认值是null。
        Trie1.TrieNode[] childNode = new Trie1.TrieNode[26];// 定义该节点的孩子节点，用一个长度为26的TrieNode[]数组来存储指向下一个孩子节点的指针
    }

    //// 定义前缀树的节点
    //class TrieNode {
    //    // 该节点的属性---isWord，它是一个标签，用来表示是否是一个完整的单词
    //    boolean isWord;
    //    // 定义该节点的孩子节点，用一个长度为26的TrieNode[]数组来存储指向下一个孩子节点的指针
    //    TrieNode[] children;
    //
    //    public TrieNode {// 构造器
    //        // 默认isWord标签为false
    //        isWord = false;
    //        // 默认孩子节点有26个(英文字母有26个)，用一个长度为26的TrieNode[]数组来存储孩子节点的指针
    //        children = new TrieNode[26];
    //    }
    //}
}


class Trie2 {


    // 1.前缀树的创建：插入一个新单词
    public static void insert(TrieNode root, String str) {
        if (root == null || str.length() == 0) {
            return;
        }
        char[] c = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            // 如果该分支不存在，创建一个新节点
            if (root.nextNode[c[i] - 'a'] == null) {
                root.nextNode[c[i] - 'a'] = new TrieNode();
            }
            root = root.nextNode[c[i] - 'a'];
            // prefix表示以该处节点之前的字符串为前缀的单词数量，所以要先将父节点后移至子节点然后再加1。
            root.prefix++;//注意，应该加在后面
        }
        // 遍历完一个单词，以该节点结尾的单词数+1
        root.count++;
    }



    // 2.查询以str开头的单词数量，查询单词str的数量
    // 查找该单词是否存在，如果存在返回数量，不存在返回-1
    public static int search(TrieNode root, String str) {
        if (root == null || str.length() == 0) {
            return -1;
        }
        char[] c = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            //如果该分支不存在，表明该单词不存在
            if (root.nextNode[c[i] - 'a'] == null) {
                return -1;
            }
            //如果存在，则继续向下遍历
            root = root.nextNode[c[i] - 'a'];
        }

        //如果count==0,也说明该单词不存在
        if (root.count == 0) {
            return -1;
        }
        return root.count;
    }



    //查询以str为前缀的单词数量
    public static int searchPrefix(TrieNode root, String str) {
        if (root == null || str.length() == 0) {
            return -1;
        }
        char[] c = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            //如果该分支不存在，表名该单词不存在
            if (root.nextNode[c[i] - 'a'] == null) {
                return -1;
            }
            //如果存在，则继续向下遍历
            root = root.nextNode[c[i] - 'a'];
        }
        return root.prefix;
    }



    static public class TrieNode {
        int count;// 其中count表示以当前字符结尾的单词数量。
        // 这里前缀的意思就是在这个前缀后面至少还有一个字符。
        int prefix;// prefix表示以该处节点之前的字符串为前缀的单词数量。
        TrieNode[] nextNode = new TrieNode[26];

        public TrieNode() {
            count = 0;
            prefix = 0;
        }
    }


}



