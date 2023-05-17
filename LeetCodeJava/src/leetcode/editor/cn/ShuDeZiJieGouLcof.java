/**
 * /**
 * <p>输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)</p>
 *
 * <p>B是A的子结构， 即 A中有出现和B相同的结构和节点值。</p>
 *
 * <p>例如:<br>
 * 给定的树 A:</p>
 *
 * <p><code>&nbsp; &nbsp; &nbsp;3<br>
 * &nbsp; &nbsp; / \<br>
 * &nbsp; &nbsp;4 &nbsp; 5<br>
 * &nbsp; / \<br>
 * &nbsp;1 &nbsp; 2</code><br>
 * 给定的树 B：</p>
 *
 * <p><code>&nbsp; &nbsp;4&nbsp;<br>
 * &nbsp; /<br>
 * &nbsp;1</code><br>
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>A = [1,2,3], B = [3,1]
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>A = [3,4,5,1,2], B = [4,1]
 * <strong>输出：</strong>true</pre>
 *
 * <p><strong>限制：</strong></p>
 *
 * <p><code>0 &lt;= 节点个数 &lt;= 10000</code></p>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 637</li><li>👎
 * 0</li></div>
 */


package leetcode.editor.cn;

/**
 * 剑指 Offer 26
 * 树的子结构
 *
 * @author wangweizhou
 * @date 2022-09-22 18:48:48
 */

public class ShuDeZiJieGouLcof {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new ShuDeZiJieGouLcof().new Solution();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        TreeNode node7 = new TreeNode(1);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(3);
        TreeNode node10 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;


        node6.left = node7;
        node7.left = node8;
        boolean bool = solution.isSubStructure(node1, node7);
        System.out.println(bool);

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

    class Solution {


        // 分成两步：
        // 第一步在树A中查找与树B的根节点的值一样的节点，这实际上就是树的遍历。对二叉树这种数据结构熟悉的读者自然知道可以用递归的方法去遍历，也可以用循环的方法去遍历。
        // 第二步是判断树A中以R为根节点的子树是不是和树B具有相同的结构。同样，我们也可以用递归的思路来考虑：
        // 如果节点R的值和树B的根节点不相同，则以R为根节点的子树和树B肯定不具有相同的节点；
        // 如果它们的值相同，则递归地判断它们各自的左右节点的值是不是相同。递归的终止条件是我们到达了树A或者树B的叶节点。


        // 解法1： 写法1 DFS+递归
        // isSubStructure()的定义:判断tree2是否为tree1的子结构  前序遍历  存在性问题，只需要存在一个，所以这里使用了有返回值的方法
        public boolean isSubStructure(TreeNode tree1, TreeNode tree2) {
            // 题干约定空树不是任意一个树的子结构
            if (tree1 == null || tree2 == null) { // 若A与B其中一个为空,立即返回false。
                return false;
            }
            TreeNode node=tree1;// 二叉树tree1的遍历指针
            // 这里要注意有返回值的DFS的处理
            // 在树tree1中查找与根节点tree2的值一样的节点node，这实际上就是树的遍历
            boolean flag = false;// 标记tree2是否为tree1的子结构
            if (node.val == tree2.val) {// 如果在二叉树tree1中找到与二叉树tree2的根节点值相同的节点
                flag = doesTree1HaveTree2(node, tree2);// 判断以tree2为根节点的二叉树是否为以node为根节点的二叉树子结构
            }
            if (!flag) {
                flag = isSubStructure(node.left, tree2);// 递归左子树，判断tree2是否为node.left的子结构
            }
            if (!flag) {
                flag = isSubStructure(node.right, tree2);// 递归右子树，判断tree2是否为node.right的子结构
            }
            return flag;
        }




        // 判断二叉树A以root1为根节点的子树是不是和以root2为根节点的二叉树B具有相同的结构。
        // 如果树A的根节点root1的值和树B的根节点root2的值不相同，则树B肯定不是以root1为根节点的子树的子结构；
        // 如果它们的值相同，则递归地判断它们各自的左右节点的值是不是相同。递归的终止条件是我们到达了树A或者树B的叶节点。

        // doesTree1HaveTree2：判断以root2为根节点的二叉树是否为以root1为根节点的二叉树的子结构【局部】
        private boolean doesTree1HaveTree2(TreeNode root1, TreeNode root2) {
            if (root2 == null) { // 当节点tree2为空，说明tree2已经完成了匹配（越过了叶子节点）,tree2 为tree1 的子结构
                return true;
            }
            // 之后就是节点tree2不为空
            //if (root2 != null&&root1 == null) {// 若tree1 走完了，tree2 没走完,说明查找完毕,tree2 不是tree1 的子结构
            if (root1 == null) {// 若tree1 走完了，tree2 没走完,说明查找完毕,tree2 不是tree1 的子结构
                return false;
            }
            // 当A与B当前节点值相等,若要判断B为A的子结构，还需要判断B的左子树是否为A的左子树的子结构 && B的右子树是否为A的右子树的子结构
            if (root1.val == root2.val) {// 当当前节点相等时，必须判断左右子树是不是子结构
                return doesTree1HaveTree2(root1.left, root2.left) && doesTree1HaveTree2(root1.right, root2.right);
            } else {// 当A与B对应的节点值不相等,则B不是A的子结构
                return false;
            }
        }





        // 迭代实现
        // isSubStructure判断以tree2为根节点的二叉树是否是以tree1为根节点的二叉树的子结构
        //public boolean isSubStructure(TreeNode tree1, TreeNode tree2) {
        //    // 题干约定空树不是任意一个树的子结构
        //    if (tree1 == null || tree2 == null) { // 若A与B其中一个为空,立即返回false。
        //        return false;
        //    }
        //    Deque<TreeNode> stack = new LinkedList<>();
        //    TreeNode node = tree1;
        //    stack.push(node);
        //    while (!stack.isEmpty()) {
        //        node = stack.pop();// 处理当前节点
        //        if (node.val == tree2.val) {// 在二叉树tree1中找到与二叉树tree2的根节点的值相同的节点
        //            if (doesTree1HaveTree2(node, tree2)) {// 判断二叉树tree1中以node为根节点的子树是否和以tree2为根节点的子树结构相同
        //                return true;
        //            }
        //        }
        //        // 根节点完了之后，左右节点哪一个先进栈都可以
        //        if (node.right != null) {
        //            stack.push(node.right);
        //        }
        //        if (node.left != null) {
        //            stack.push(node.left);
        //        }
        //    }
        //    return false;
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
