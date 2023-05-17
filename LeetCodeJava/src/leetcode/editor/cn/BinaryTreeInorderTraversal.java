/**
 * <p>给定一个二叉树的根节点 <code>root</code> ，返回 <em>它的 <strong>中序</strong>&nbsp;遍历</em> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg" style="height: 200px; width: 125px;
 * " />
 * <pre>
 * <strong>输入：</strong>root = [1,null,2,3]
 * <strong>输出：</strong>[1,3,2]
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
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数目在范围 <code>[0, 100]</code> 内</li>
 * <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶:</strong>&nbsp;递归算法很简单，你可以通过迭代算法完成吗？</p>
 * <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 1480</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 94
 * 二叉树的中序遍历
 *
 * @author wangweizhou
 * @date 2022-07-08 00:39:02
 */

public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new BinaryTreeInorderTraversal().new Solution();

        //创建需要的结点
        TreeNode rootnode = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        // 手动创建二叉树
        rootnode.left = node2;
        rootnode.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        List<Integer> list = solution.inorderTraversal(rootnode);
        System.out.println(list);


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


        //从根节点开始不断往左，第一个被访问的肯定是最左边的节点，然后访问最左边的节点的右子树，最后向上回到父问题。
        // 方法2：二叉树的中序遍历  迭代解法写法1  从上到下扫描
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> lists = new ArrayList<>();
            if (root == null) {
                return lists;
            }
            Deque<TreeNode> stack = new LinkedList<>();//创建ArrayLists对象stack来模拟栈
            TreeNode node = root;// node是二叉树的遍历指针
            // 因为中序遍历都是以每个节点左子节点优先访问，所以在循环遍历时将当前节点不断入栈，并不断访问该节点的左子树
            // 内层while循环就是通过循环将以node为根节点的左子树的最左的一个节点压入栈。【这是一个循环过程】
            while (!stack.isEmpty() || node != null) {//当树节点不为空或栈中有节点时
                // 内层while循环就是通过循环将以curr为根节点的左子树的最左的一个节点压入栈
                while (node != null) {//每次找到以node为根节点的子树的最左节点
                    stack.push(node);
                    node = node.left;
                }
                // 上面循环结束时，node最终指向刚开始时以node为根节点的左子树的最左侧叶子节点的左指针域【最左侧叶子节点的左指针域其实是空指针】
                node = stack.pop();//弹出栈顶元素，其实就是返回上一层的根节点。其实就是上面循环结束时空指针的父节点。
                lists.add(node.val);// 处理当前节点
                node=node.right;// 通过当前节点【根节点】去访问当前节点右子树。
            }
            return lists;
        }








 /*

        // 中序遍历  解法1：递归   递归写法2
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();//创建ArrayLists对象res来存储结果
            inorder(root, res);
            return res;
        }


        public void inorder(TreeNode node, List<Integer> res) {
            if (node == null) {
                return;
            }
            inorder(node.left, res);// 递归调用左子树
            res.add(node.val);// 处理父节点
            inorder(node.right, res);// 递归调用右子树
        }
        */




/*
        // 中序遍历  递归写法3
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> lists = new ArrayList<>();
            if (root == null) {
                return lists;
            }
            return inorder(root,lists);
        }

        private List<Integer> inorder(TreeNode root,List<Integer> lists){
            if (root == null) {
                return lists;
            }
            inorder(root.left,lists);
            lists.add(root.val);
            inorder(root.right,lists);
            return lists;
        }

        */




/*

        // 中序遍历  递归写法2
        List<Integer> lists = new ArrayList<>();
        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) {
                return lists;
            }
            inorderTraversal(root.left);
            lists.add(root.val);
            inorderTraversal(root.right);
            return lists;
        }

*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
