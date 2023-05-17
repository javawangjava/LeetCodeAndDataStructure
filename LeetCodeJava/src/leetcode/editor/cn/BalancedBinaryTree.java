/**
 * <p>给定一个二叉树，判断它是否是高度平衡的二叉树。</p>
 *
 * <p>本题中，一棵高度平衡二叉树定义为：</p>
 *
 * <blockquote>
 * <p>一个二叉树<em>每个节点 </em>的左右两个子树的高度差的绝对值不超过 1 。</p>
 * </blockquote>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/06/balance_1.jpg" style="width: 342px; height: 221px;
 * " />
 * <pre>
 * <strong>输入：</strong>root = [3,9,20,null,null,15,7]
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/06/balance_2.jpg" style="width: 452px; height: 301px;
 * " />
 * <pre>
 * <strong>输入：</strong>root = [1,2,2,3,3,null,null,4,4]
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中的节点数在范围 <code>[0, 5000]</code> 内</li>
 * <li><code>-10<sup>4</sup> <= Node.val <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 1083</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 110
 * 平衡二叉树
 *
 * @author wangweizhou
 * @date 2022-07-25 20:16:21
 */

public class BalancedBinaryTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new BalancedBinaryTree().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    class Solution {


        //// ⽅法⼀：⾃顶向下（推荐使⽤）  DFS
        //// 思路是构造一个获取当前节点最大深度的方法 depth(root) ，
        //// 通过比较此子树的左右子树的最大高度差 abs(depth(root.left) - depth(root.right))，来判断此子树是否是二叉平衡树。
        //// 若树的所有子树都平衡时，此树才平衡。

        //public boolean isBalanced(TreeNode root) {
        //    if (root == null) {// 空树为平衡⼆叉树
        //        return true;
        //    }
        //    int leftDepth = getDepth(root.left);
        //    int rightDepth = getDepth(root.right);
        //    //左⼦树深度减去右⼦树相差绝对值⼤于1
        //    if (leftDepth - rightDepth > 1 || rightDepth - leftDepth > 1) {
        //        return false;
        //    }
        //    //同时，左右⼦树还必须是平衡的
        //    return isBalanced(root.left) && isBalanced(root.right);
        //}
        //
        //
        //// 获取当前节点最大深度的方法 depth(root)   DFS
        //public int getDepth(TreeNode node) {//计算该⼦树深度
        //    if (node == null) {//空结点深度为0
        //        return 0;
        //    }
        //    int leftDepth = getDepth(node.left);//递归算左右⼦树的深度
        //    int rightDepth = getDepth(node.right);
        //    return Math.max(leftDepth,rightDepth)+1;// ⼦树最⼤深度加上⾃⼰。调用API快一些
        //    //return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;//⼦树最⼤深度加上⾃⼰
        //}




        // ⽅法⼆：⾃底向上（扩展思路）
        // 如果我们用后序遍历的方式遍历二叉树的每个节点，那么在遍历到一个节点之前我们就已经遍历了它的左、右子树。
        // 只要在遍历每个节点的时候记录它的深度（某一节点的深度等于它到叶节点的路径的长度），我们就可以一边遍历一边判断每个节点是不是平衡的。

        public boolean isBalanced(TreeNode root) {
            if (root == null) {// 空树为平衡⼆叉树
                return true;
            }
            return getdepth(root) != -1;
        }


        // 获取以当前节点root为根节点的二叉树的深度。当返回值是-1时，表示当前根节点的二叉树时不平衡的
        // 思路是对二叉树做先序遍历，从底至顶返回子树最大高度，若判定某子树不是平衡树则 “剪枝” ，直接向上返回。
        // 递归返回值：
        // 当节点 root 左 / 右子树的高度差 < 2 ：则返回以节点 root 为根节点的子树的最大高度，即节点 root 的左右子树中最大高度加 1 （ max(left, right) + 1 ）；
        // 当节点 root 左 / 右子树的高度差 ≥2 ：则返回 -1 ，代表 此子树不是平衡树 。

        public int getdepth(TreeNode root) {
            if (root == null) {// 当越过叶子节点时，返回高度 0 ；
                return 0;
            }
            // 递归计算当前root左右⼦树的⾼度差
            int leftDepth = getdepth(root.left);
            if (leftDepth < 0) {// 当前节点左⼦树不平衡,则该树不平衡
                return -1;
            }
            int rightDepth = getdepth(root.right);
            if (rightDepth < 0) {// 当前节点右⼦树不平衡,则该树不平衡
                return -1;
            }
            // 计算⾼度差
            // 当前左右子树的高度差大于1时，就不是平衡树，则返回-1。若是平衡树，则当前节点的子树高度就是左右子树最大高度+1。
            return Math.abs(leftDepth - rightDepth) > 1 ? -1 : Math.max(leftDepth, rightDepth) + 1;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
