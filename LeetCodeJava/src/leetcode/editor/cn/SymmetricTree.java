/**
 * <p>给你一个二叉树的根节点 <code>root</code> ， 检查它是否轴对称。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/symtree1.jpg" style="width: 354px; height: 291px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,2,2,3,4,4,3]
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/symtree2.jpg" style="width: 308px; height: 258px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,2,2,null,3,null,3]
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数目在范围 <code>[1, 1000]</code> 内</li>
 * <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你可以运用递归和迭代两种方法解决这个问题吗？</p>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 1995</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 101
 * 对称二叉树
 *
 * @author wangweizhou
 * @date 2022-07-10 13:57:40
 */

public class SymmetricTree {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new SymmetricTree().new Solution();

        ////创建需要的结点
        TreeNode rootnode = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);

        // 手动创建二叉树
        rootnode.left = node2;
        rootnode.right = node3;
        node2.left=node4;
        node2.right = node5;
        //node3.left = node6;
        //node3.right=node7;

        Solution solution = new SymmetricTree().new Solution();
        boolean bool = solution.isSymmetric(rootnode);
        System.out.println(bool);

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

        // 因为是验证对称二叉树，所以必须将空节点也加入队列中，这样利用空引用占位才能对称，这样才会将叶子节点的左右空子节点添加到队列中。


        // 方法一：DFS 前序递归变形  写法1
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            //调用递归函数，比较左节点，右节点
            return dfsCheck(root, root);
            //return dfsCheck(root.left,root.right);// 和return dfsCheck(root,root)一样，根节点只有一个，所以可以直接验证二叉树的左右子树
        }


        // DFS 前序遍历 检查是否对称   这个是所有对称，递归左右子树必须对称，所以这里必须是左右子树同时成立
        public boolean dfsCheck(TreeNode leftNode, TreeNode rightNode) {
            // 处理当前节点
            // 递归的终止条件是两个节点都为空或者两个节点中有一个为空或者两个节点的值不相等
            if (leftNode == null && rightNode == null) {//可以两个都为空，
                return true;// 这个只是这两个对称位置的点是对称的
            }
            if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {//只有⼀个为空或者节点值不同，必定不对称
                return false;// 这时候就结束递归，向上返回每层的结果都是false
            }
            if(!dfsCheck(leftNode.left, rightNode.right)){// 递归外层不是对称的，结束递归
                return false;
            }
            if(!dfsCheck(leftNode.right, rightNode.left)){// 递归内层不是对称的，结束递归
                return false;
            }
            return true;
        }


        //// 方法一：DFS 前序递归变形  写法1
        //public boolean isSymmetric(TreeNode root) {
        //    if (root == null) {
        //        return true;
        //    }
        //    //调用递归函数，比较左节点，右节点
        //    return dfsCheck(root, root);
        //    //return dfsCheck(root.left,root.right);// 和return dfsCheck(root,root)一样，根节点只有一个，所以可以直接验证二叉树的左右子树
        //}
        //
        //// DFS 前序遍历 检查是否对称
        //public boolean dfsCheck(TreeNode leftNode, TreeNode rightNode) {
        //    // 处理当前节点
        //    // 递归的终止条件是两个节点都为空或者两个节点中有一个为空或者两个节点的值不相等
        //    if (leftNode == null && rightNode == null) {//可以两个都为空，
        //        return true;// 这个只是这两个对称位置的点是对称的
        //    }
        //    if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {//只有⼀个为空或者节点值不同，必定不对称
        //        return false;// 这时候就结束递归，向上返回每层的结果都是false
        //    }
        //    // 递归遍历左右子树		注意是左右子树两个都必须对称
        //    //左节点的左孩子 和 右节点的右孩子以及比较  左节点的右孩子 和 右节点的左孩子
        //    return dfsCheck(leftNode.left, rightNode.right) && dfsCheck(leftNode.right, rightNode.left);
        //}




        //// 方法二：层次遍历变形，调整节点的加入顺序
        //// 因为是验证对称二叉树，所以必须将空节点也加入队列中，这样利用空引用占位才能对称。则会将叶子节点的左右空子节点添加到队列中。
        //// 一个队列这里是将要验证的两个对称位置的节点先后加入队列中， 之后再验证两个位置的元素是否相等
        //
        //public boolean isSymmetric(TreeNode root) {
        //    if (root == null) {// 空链表为对称的
        //        return true;
        //    }
        //
        //    Queue<TreeNode> queue = new LinkedList<>(); //	用队列保存节点
        //    //将根节点的左右孩子放到队列中或者将根节点添加两次到队列中
        //    // 将二叉树的左右孩子放到队列中，
        //    queue.add(root.left);
        //    queue.add(root.right);
        //
        //    //queue.add(root);// 这里将根节点添加两次也可以，那么后面相当于将二叉树存了一个从左向右和从右向左
        //    //queue.add(root);
        //    while (queue.size() > 0) {
        //        TreeNode left = queue.poll();
        //        TreeNode right = queue.poll();
        //
        //        // 递归的终止条件是两个节点都为空或者两个节点中有一个为空或者两个节点的值不相等
        //        if (left == null && right == null) {// 两个节点都为空
        //            continue;// 截止目前对称，继续遍历
        //        }
        //        // 之后两个节点最多有一个为空
        //        if (left == null || right == null || left.val != right.val) {// 两个节点中有一个为空或者两个节点的值不相等
        //            return false;// 循环有一次不对称则整个不对称
        //        }
        //
        //        // 因为是验证对称二叉树，所以必须将空节点也加入队列中，这样利用空引用占位才能对称。则会将叶子节点的左右空子节点添加到队列中。
        //
        //        // 因为是一个单队列，注意层次遍历节点的添加顺序，每次将两个对称位置的节点先后加入队列
        //        // 对称节点：【left.left，right.right】  【left.right，right.left 】
        //        queue.add(left.left);
        //        queue.add(right.right);
        //        // 将左节点的右孩子，右节点的左孩子放入队列
        //        queue.add(left.right);
        //        queue.add(right.left);
        //    }
        //    return true;
        //}




        //// 解法3：层次遍历    两个队列可以一个存储左子树，一个存储右子树。或者两个都存储整个子树。
        //// 因为是验证对称二叉树，所以必须将空节点也加入队列中，那么会将叶子节点的左右空子节点添加到队列中。
        //// 下面这里是两个队列，将对称子树的每一层按照左子树从左向右，右子树从右向左。
        //public boolean isSymmetric(TreeNode root) {
        //    if (root == null) {// 空链表为对称的
        //        return true;
        //    }
        //    // 辅助队列⽤于从根节点两边层次遍历
        //    Queue<TreeNode> queue1 = new LinkedList<>();
        //    Queue<TreeNode> queue2 = new LinkedList<>();
        //    // 注意下面添加的是二叉树的根节点的左右节点，所有两个队列中一个从左向右遍历存储左子树，一个从右向左遍历存储右子树。
        //    queue1.offer(root.left);
        //    queue2.offer(root.right);
        //
        //    //// 注意下面添加的是二叉树的根节点，所以两个队列中存储的是整个二叉树，一个二叉树从左向右，另一个二叉树从右向左、两个都可以。
        //    //queue1.offer(root);
        //    //queue2.offer(root);
        //    // null指针在队列中占个地位，但是不会显示出来。因为会不断添加null进入队列，所以队列不会为空的，所以下面是逻辑或或者逻辑与都可以。
        //    //while (!queue1.isEmpty() || !queue2.isEmpty()) {
        //    while (!queue1.isEmpty() && !queue2.isEmpty()) {
        //        // 两个队列中分别弹出节点，每次都只比较一个节点
        //        TreeNode left = queue1.poll();
        //        TreeNode right = queue2.poll();
        //        if (left == null && right == null) {
        //            continue;// 截止目前对称，继续遍历
        //        }
        //        if (left == null || right == null || left.val != right.val) {//某⼀个为空或者数字不相等则不对称
        //            return false;
        //        }
        //        // 因为是验证对称二叉树，所以必须将空节点也加入队列中，这样利用空引用占位才能对称。则会将叶子节点的左右空子节点添加到队列中。
        //        // 队列1从左往右加⼊队列  // 队列2从右往左加⼊队列
        //        queue1.add(left.left);
        //        queue1.add(left.right);
        //        queue2.add(right.right);
        //        queue2.add(right.left);
        //    }
        //    return true;// 都检验完都是对称的
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
