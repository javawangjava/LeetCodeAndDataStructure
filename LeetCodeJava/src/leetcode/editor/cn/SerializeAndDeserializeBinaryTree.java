/**
 * <p>序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。</p>
 *
 * <p>请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。</p>
 *
 * <p><strong>提示: </strong>输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 <a href="/faq/#binary-tree">LeetCode 序列化二叉树的格式</a>
 * 。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/serdeser.jpg" style="width: 442px; height: 324px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,2,3,null,null,4,5]
 * <strong>输出：</strong>[1,2,3,null,null,4,5]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1,2]
 * <strong>输出：</strong>[1,2]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中结点数在范围 <code>[0, 10<sup>4</sup>]</code> 内</li>
 * <li><code>-1000 <= Node.val <= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>设计</li><li>字符串</li><li>二叉树</li
 * ></div></div><br><div><li>👍 928</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 297
 * 二叉树的序列化与反序列化
 *
 * @author wangweizhou
 * @date 2022-07-26 20:13:31
 */

public class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        //测试代码
        Codec solution = new SerializeAndDeserializeBinaryTree().new Codec();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    public class Codec {


        // 实际上，如果二叉树的序列化是从根节点开始的，那么相应的反序列化在根节点的数值读出来的时候就可以开始了。
        // 因此，我们可以根据前序遍历的顺序来序列化二叉树，因为前序遍历是从根节点开始的。
        // 在遍历二叉树碰到nullptr指针时，这些nullptr指针序列化为一个特殊的字符（如'S）。另外，节点的数值之间要用一个特殊字符（如） 隔开。


        // 先考虑如何将二叉树序列化为一个字符串。需要逐个遍历二叉树的每个节点，每遍历到一个节点就将节点的值序列化到字符串中。
        // 以前序遍历的顺序遍历二叉树最适合序列化。如果采用前序遍历的顺序，那么二叉树的根节点最先序列化到字符串中，然后是左子树，最后是右子树。
        // 这样做的好处是在反序列化时最方便，从字符串中读出的第1个数值一定是根节点的值。
        // 实际上，只把节点的值序列化到字符串中是不够的。首先，要用一个分隔符（如逗号）把不同的节点分隔开。
        // 其次，还要考虑如何才能在反序列化的时候构建不同结构的二叉树。
        // 尽管null节点通常没有在图上画出来，但它们对树的结构是至关重要的。因此，应该把null节点序列化成一个特殊的字符串。如果把null节点序列化成"＃"。
        // 如果节点是null则返回特殊字符串"＃"；否则生成一个字符串，它的开头是节点的值，然后是左子树序列化的结果，最后是右子树序列化的结果，因此这是按照前序遍历的顺序递归地序列化整棵二叉树。


        //// 解法3：前序遍历的递归写法
        //// 序列化：将二叉树转换为字符数组  前序遍历
        //public String serialize(TreeNode root) {
        //    if (root == null) {// 若当前节点 root 为空，则直接返回空列表 "#" ；不会遍历当前节点root的左右子树
        //        return "#";
        //    }
        //    // 引用类型变量在每一层递归的时候都会传递，
        //    String leftStr = serialize(root.left);// 递归当前节点 root 的左子树的序列化结果
        //    String rightStr = serialize(root.right);// 递归当前节点 root 的右子树的序列化结果
        //    // 字符串拼接
        //    return (root.val) + "," + leftStr + "," + rightStr;// 按  根,左,右  拼接字符串
        //}
        //
        //
        //
        //// 反序列化：将一个字符串转换为二叉树   由字符串建立二叉树
        //// 接着考虑反序列化。由于把二叉树序列化成一个以逗号作为分隔符的字符串，因此可以根据分隔符把字符串分隔成若干子字符串，每个子字符串对应二叉树的一个节点。
        //// 如果一个节点为null，那么它和"＃"对应；否则这个节点将和一个表示它的值的子字符串对应。
        //// 如果用前序遍历序列化二叉树，那么分隔后的第1个字符串对应的就是二叉树的根节点，因此可以先根据这个字符串构建出二叉树的根节点。
        //// 然后先后反序列化二叉树的左子树和右子树。在反序列化它的左子树和右子树时可以采用类似的方法，也就是说，可以调用递归函数解决反序列化子树的问题。
        //
        //// 字符串数组nodeStrs保存分隔之后的所有节点对应的字符串，可以根据数组中的每个字符串逐一构建二叉树的每个节点。
        //// 递归函数dfs的每次执行都会从字符串数组中取出一个字符串并以此反序列化出一个节点（如果取出的字符串是"＃"，则返回null节点）。
        //
        //// 我们需要一个下标去扫描字符串数组nodeStrs中的每个字符串。
        //// 通常用一个整数值来表示数组的下标，但在上述代码中却定义了一个长度为1的整数数组i。
        //// 这是因为递归函数dfs每反序列化一个节点时下标就会增加1，并且函数的调用者需要知道下标增加了。
        //// 如果函数dfs的第2个参数i是整数类型，那么即使在函数体内修改i的值，修改之后的值也不能传递给它的调用者。
        //// 但把i定义为整数数组之后，可以修改整数数组中的数字，修改之后的数值就能传给它的调用者。
        //
        //// 前序遍历
        //public TreeNode deserialize(String data) {
        //    if(data==null||data.length()==0){
        //        return null;
        //    }
        //    String[] nodeStrs = data.split(",");// 分割字符串成字符数组
        //    int[] i = {0};// 字符串遍历指针，因为基本数据类型只是复制值传递，递归的时候不能前后影响，所以这里使用引用类型的数组来传递参数
        //    return deserializeFunc(nodeStrs, i);
        //}
        //
        //
        //// 前序遍历 递归
        //private TreeNode deserializeFunc(String[] strs, int[] i) {
        //    String str = strs[i[0]];// 获得当前位置的字符串
        //    i[0]++;// 字符串遍历指针后移
        //    if (str.equals("#")) {// 字符串中字符是#，返回null节点
        //        return null;
        //    }
        //    TreeNode node = new TreeNode(Integer.valueOf(str));// 非空节点创建节点
        //    node.left = deserializeFunc(strs, i);// 递归构建左子树
        //    node.right = deserializeFunc(strs, i);// 递归构建右子树
        //    return node;// 返回当前构建好的root
        //}





        //// 解法3：前序遍历的递归写法  写法2
        //public String serialize(TreeNode root) {
        //    if(root==null){
        //        return "#";
        //    }
        //    StringBuilder res=new StringBuilder();
        //    serializeFunc(root,res);
        //    return res.toString();
        //}
        //
        //private void serializeFunc(TreeNode root,StringBuilder str){
        //    if(root==null){
        //        str.append("#");
        //        return;
        //    }
        //    str.append(root.val).append(",");
        //    serializeFunc(root.left,str);
        //    serializeFunc(root.right,str);
        //}
        //
        //
        //public TreeNode deserialize(String data) {
        //    if(data==null||data.length()==0||data=="#"){
        //        return null;
        //    }
        //    int[] index={0};
        //    char[] charArr =data.toCharArray();
        //    return deserializeFunc(charArr,index);
        //}
        //
        //
        //private TreeNode deserializeFunc(char[] charArr, int[] index){
        //
        //    if(charArr[index[0]]=='#'){
        //        index[0]++;
        //        return null;
        //    }
        //    int val=0;
        //    if(index[0]<charArr.length&&charArr[index[0]]=='-'){
        //        index[0]++;
        //        while (index[0]<charArr.length&&charArr[index[0]]!=','){
        //            val=val*10+(charArr[index[0]]-'0');
        //            index[0]++;
        //        }
        //        val=-val;
        //    }else if(charArr[index[0]]!='-') {
        //        while (index[0]<charArr.length&&charArr[index[0]]!=','){
        //            val=val*10+(charArr[index[0]]-'0');
        //            index[0]++;
        //        }
        //    }
        //    index[0]++;
        //    TreeNode node=new TreeNode(val);
        //    node.left=deserializeFunc(charArr,index);
        //    node.right=deserializeFunc(charArr,index);
        //    return node;
        //}






        //// ⽅法1：前序遍历（推荐使⽤）
        //// 序列化：将二叉树转换为字符数组
        //public String serialize(TreeNode root) {
        //    if (root == null) {// 处理空树
        //        return "#";
        //    }
        //    StringBuilder res = new StringBuilder();// 可变字符串
        //    serializeFunc(root, res);
        //    return res.toString();// 把res转换成String
        //}
        //
        //
        //// 处理序列化的功能函数（递归）  这里使用可变字符串
        //private void serializeFunc(TreeNode root, StringBuilder str) {
        //    if (root == null) {// 如果指针为空，表示左⼦节点或右⼦节点为空，⽤#表示
        //        str.append('#');
        //        return;
        //    }
        //    // 此后的指针root不空，
        //    // 处理当前节点，运行到这里，当前节点不空，那么就是字符串后面添加当前节点的数值和分隔符”，“。
        //    str.append(root.val).append(",");// 后缀当前节点root和分隔符”，“
        //    // 递归遍历左子树和右子树
        //    serializeFunc(root.left, str);// 递归当前节点 root 的左子树的序列化
        //    serializeFunc(root.right, str);// 递归当前节点 root 的右子树的序列化
        //}
        //
        //
        //
        //private int index = 0; // 序列的下标
        //// 反序列化：将一个字符串转换为二叉树
        //public TreeNode deserialize(String data) {
        //    if (data == "#") {//空序列对应空树
        //        return null;
        //    }
        //    TreeNode res = deserializeFunc(data);
        //    return res;
        //}
        //
        //
        //
        //// 处理反序列化的功能函数（递归） 下面是自己写将字符串转换为数字的方法
        //private TreeNode deserializeFunc(String str) {
        //    // 处理当前节点
        //    // 到达叶节点时，构建完毕，返回继续构建⽗节点
        //    if (str.charAt(index) == '#') {// 当前遍历到的元素是”#“，表明当前节点是空节点，
        //        index++;// 这时候遍历指针index要后移一位，
        //        return null;// 空节点返回null。
        //    }
        //
        //    // 运行到这里，当前节点不是空节点，当前节点右数据。将当前节点的数字转换，当遇到分隔符”，“，表示当前节点的数字结束。
        //    int val = 0;
        //    // 字符串有可能是负数，所以要单独处理符号位和数字位
        //    // 首先判断节点字符串开始的符号
        //    if (str.charAt(index) == '-') {// 遇到负数。 符号为负，首先右移一位，然后再处理数字部分，然后再乘以（-1）。
        //        index++;// 遇到负数，遍历指针后移一位，进入处理后续数字位
        //        //遇到分隔符或者结尾。获取整个整数
        //        //while (str.charAt(index) != ',' && index != str.length()) {
        //        while (str.charAt(index) != ',') {
        //            // 将字符转换位数字：str.charAt(index) - '0'。
        //            val = val * 10 + (str.charAt(index) - '0');// 将字符转换为整数
        //            index++;
        //        }
        //        val = -val;// 负数添符号
        //    } else if (str.charAt(index) != '-') {// 遇到正数。处理正数
        //        // 获取整个整数
        //        //while (str.charAt(index) != ',' && index != str.length()) {
        //        while (str.charAt(index) != ',' ) {
        //            val = val * 10 + (str.charAt(index) - '0');
        //            index++;
        //        }
        //    }
        //
        //    // 上面if-else结束时，指针index遍历到分隔符”，“所以指针要后移一位，跳过分隔符，进入下一个数据域
        //    index++;
        //    TreeNode root = new TreeNode(val);// 以遍历到的数值创建节点作为父节点
        //    // 下面编译器报恒错，所以修改了
        //    //if (index == str.length()) {// 序列到底了，构建完成
        //    //    return root;
        //    //} else {
        //    //    index++;
        //    //}
        //
        //    // 反序列化与序列化⼀致，都是先序
        //    root.left = deserializeFunc(str);// 递归遍历建立当前节点的左子树
        //    root.right = deserializeFunc(str);
        //    return root;
        //}






        // 解法2：广度优先遍历，因为是序列化，所以需要空节点来占位
        // 题目要求的序列化和反序列化是可逆操作。因此，序列化的字符串应携带完整的二叉树信息。
        // 为完整表示二叉树，考虑将叶节点下的 null 也记录。在此基础上，对于列表中任意某节点 node ，其左子节点 node.left 和右子节点 node.right 在序列中的位置都是唯一确定的。

        public String serialize(TreeNode root) {
            if(root == null) {// 若 root 为空，则直接返回空列表 "[]" ；
                return "[]";
            }
            StringBuilder res = new StringBuilder("[");// 可变字符串记录序列化结果
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();// 节点出队，记为 node ；
                if(node != null) {// 若 node 不为空
                    res.append(node.val + ",");//  打印字符串 node.val
                    queue.add(node.left);// 将左、右子节点加入 queue ，这里不需要判空，因为是序列化需要使用空节点占位
                    queue.add(node.right);
                } else {// （若 node 为空）：序列化为”null“；
                    res.append("null,");
                }
            }
            // 当 queue 为空时跳出；遍历完了整个二叉树，最后一个数字后面多了一个逗号
            res.deleteCharAt(res.length() - 1);// 删除最后一个逗号
            res.append("]");// 末尾添加中括号
            return res.toString();
        }




        // 反序列化，广度遍历，根左右
        public TreeNode deserialize(String data) {
            if(data.equals("[]")) {// 空树
                return null;
            }
            // 字符串去掉首尾两端的中括号，然后再用“，”分割
            String[] vals = data.substring(1, data.length() - 1).split(",");
            // 执行到这里字符串数组中只有节点的数据域和“null”
            TreeNode root = new TreeNode(Integer.parseInt(vals[0]));// 创建根节点
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int i = 1;// 字符串数组vals的遍历指针
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();// 节点出队，记为 node ；
                if(!vals[i].equals("null")) {// 节点不空，创建当前节点的左子节点，将左子节点入队
                    node.left = new TreeNode(Integer.parseInt(vals[i]));
                    queue.add(node.left);
                }
                i++;

                if(!vals[i].equals("null")) {// 节点不空，创建当前节点的右子节点，将右子节点入队
                    node.right = new TreeNode(Integer.parseInt(vals[i]));
                    queue.add(node.right);
                }
                i++;
            }
            return root;
        }


    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));

//leetcode submit region end(Prohibit modification and deletion)

}
