/**
 * <p>给你二叉树的根结点 <code>root</code> ，请你将它展开为一个单链表：</p>
 *
 * <ul>
 * <li>展开后的单链表应该同样使用 <code>TreeNode</code> ，其中 <code>right</code> 子指针指向链表中下一个结点，而左子指针始终为 <code>null</code> 。</li>
 * <li>展开后的单链表应该与二叉树
 * <a href="https://baike.baidu.com/item/%E5%85%88%E5%BA%8F%E9%81%8D%E5%8E%86/6442839?fr=aladdin" target="_blank"><strong>先序遍历</strong></a> 顺序相同。</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/01/14/flaten.jpg" style="width: 500px; height: 226px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,2,5,3,4,null,6]
 * <strong>输出：</strong>[1,null,2,null,3,null,4,null,5,null,6]
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
 * <strong>输入：</strong>root = [0]
 * <strong>输出：</strong>[0]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中结点数在范围 <code>[0, 2000]</code> 内</li>
 * <li><code>-100 <= Node.val <= 100</code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>你可以使用原地算法（<code>O(1)</code> 额外空间）展开这棵树吗？</p>
 * <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>链表</li><li>二叉树</li></div></div><br><div
 * ><li>👍 1290</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 114
 * 二叉树展开为链表
 *
 * @author wangweizhou
 * @date 2022-09-06 16:52:54
 */

public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new FlattenBinaryTreeToLinkedList().new Solution();
        ////创建需要的结点
        TreeNode rootnode = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        //TreeNode node7 = new TreeNode(7);


        // 手动创建二叉树
        rootnode.left = node2;
        rootnode.right = node3;
        node2.left = node4;
        node2.right = node5;
        //node3.left=node6;
        node3.right = node6;
        solution.flatten(rootnode);

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

        //// 解法1：
        //// 1.将当前节点的右子树连接到当前节点左子树的最右的节点的右子树上，
        //// 2.然后将当前节点的左子树连接到当前节点的右子树上，
        //// 3.将当前左子树置空，当前节点后移至当前节点的右子节点，一直重复上边的过程，直到新的右子树为 null

        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            while (root != null) {
                if (root.left != null) {// 左子树不为空
                    // 左子树不为空，找左子树最右边的节点
                    TreeNode lRight = root.left;// lRight 为左子树最右边的一个
                    while (lRight.right != null) {// 循环遍历找到左子树最右边的节点
                        lRight = lRight.right;
                    }
                    // 内层while循环结束就找到以root为根节点的左子树最右边的节点lRight
                    lRight.right = root.right;// 将root的右子树接到root的左子树的最右边节点的右子节点上
                    root.right = root.left;// 将root的左子树插入到root的右子树的地方
                    root.left = null;//将根节点root的左子树置空
                }
                root = root.right;// 根节点root后移到其右孩子

                // 下面是上面的简化
                //if (root.left != null) {// 左子树不为空
                //    // 左子树不为空，找左子树最右边的节点
                //    TreeNode lRight = root.left;// lRight 为左子树最右边的一个
                //    while (lRight.right != null) {// 循环遍历找到左子树最右边的节点
                //        lRight = lRight.right;
                //    }
                //    // 内层while循环结束就找到以root为根节点的左子树最右边的节点lRight
                //    lRight.right = root.right;// 将root的右子树接到root的左子树的最右边节点的右子节点上
                //    root.right = root.left;// 将root的左子树插入到root的右子树的地方
                //    root.left = null;//将根节点root的左子树置空
                //    root = root.right;// 根节点root后移到其右孩子
                //}else {
                //    root = root.right;// 根节点root后移到其右孩子
                //}
            }
        }




        //// 解法3： 写法1：DFS后续遍历变形   右子树-左子树-根节点
        //// 使用【右子树-左子树-根节点】遍历找到二叉树最右边的一个节点，并将该节点每次作为右子树连接到新的父节点上面

        //private TreeNode prev = null;// 全局变量表示上一个遍历的节点
        //public void flatten(TreeNode root) {
        //    if (root == null){
		//		return;
		//	}
        //    flatten(root.right);
        //    flatten(root.left);
        //    root.right = prev;// 将上一个节点连接到当前节点的右子树上
        //    root.left = null;// 将当前节点的左子树置空
        //    prev = root;// 更新上一个已经访问的节点
        //}





        //// 解法3：  写法2 DFS 后续遍历变形   右子树-左子树-根节点
        //
        //public void flatten(TreeNode root) {
        //    if (root == null) {
        //        return;
        //    }
        //    Deque<TreeNode> stack=new LinkedList<>();
        //    TreeNode node = root;
        //    TreeNode prev = null;// 上一个访问的节点
        //    while (node != null || !stack.isEmpty()) {
        //        while (node != null) {
        //            stack.push(node); // 添加根节点
        //            node = node.right; // 递归添加右节点
        //        }
        //        // 循环结束栈顶是当前子树的最右的节点
        //        node = stack.pop(); // 弹出栈顶元素，说明栈顶元素已经访问到最右的节点了
        //
        //        // 根节点【当前节点】不存在左节点或者左节点已经访问过的情况下，访问根节点，
        //        if (node.left == null || node.left == prev) {
        //            node.right = prev;// 将上一个访问的节点连接到根节点的右子树上
        //            node.left = null;// 根节点【当前节点】的左子树置空
        //            prev = node;// 更新上一个已经访问的节点
        //            node = null;// 将原根节点【当前节点】置空，不然就形成死循环了
        //
        //        } else {// 根节点左子树非空，访问左子树
        //            // 访问左子树要保留原父节点，所以弹出的父节点要再次入栈
        //            stack.push(node);
        //            node = node.left; // 左节点还没有访问过就先访问左节点
        //        }
        //    }
        //}






        /*
        // 解法1：前序遍历+记忆化存储  写法1  递归
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            List<TreeNode> list = new ArrayList<TreeNode>();
            preorderTraversal(root, list);
            // 新建二叉树 从根节点开始建立二叉树
            int size = list.size();
            for (int i = 1; i < size; i++) {
                TreeNode prev = list.get(i - 1), curr = list.get(i);
                prev.left = null;
                prev.right = curr;
            }
        }

        // DFS 前序递归并存储节点
        private void preorderTraversal(TreeNode root, List<TreeNode> list) {
            if (root == null) {
                return;
            }
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
        */





        /*
        // 解法1：迭代前序遍历+记忆化存储  写法2
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            List<TreeNode> lists=new ArrayList<>();
            Deque<TreeNode> stack=new LinkedList<>();
            TreeNode node=root;
            while(node!=null||!stack.isEmpty()){
                while(node!=null){
                    lists.add(node);
                    stack.push(node);
                    node=node.left;
                }
                node=stack.pop();
                node=node.right;
            }
            // 建立新树
            int size = lists.size();
            TreeNode newRoot=lists.get(0);
            for (int i = 1; i < size; i++) {
                TreeNode right=lists.get(i);
                newRoot.left=null;
                newRoot.right=right;
                newRoot=newRoot.right;
            }
        }
        */



    }
//leetcode submit region end(Prohibit modification and deletion)

}
