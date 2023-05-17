/**
 * <p>给定一个二叉树，找出其最大深度。</p>
 *
 * <p>二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。</p>
 *
 * <p><strong>说明:</strong>&nbsp;叶子节点是指没有子节点的节点。</p>
 *
 * <p><strong>示例：</strong><br>
 * 给定二叉树 <code>[3,9,20,null,null,15,7]</code>，</p>
 *
 * <pre>    3
 * / \
 * 9  20
 * /  \
 * 15   7</pre>
 *
 * <p>返回它的最大深度&nbsp;3 。</p>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 1285</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 104
 * 二叉树的最大深度
 *
 * @author wangweizhou
 * @date 2022-07-10 11:40:58
 */

public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MaximumDepthOfBinaryTree().new Solution();

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


        // 解法1：层次遍历
        // 因为要看二叉树的层数，也就是研究数据节点组成，那么就要看实际的数据节点， 所有遍历进入队列的只能是数据节点，所以在向队列中添加元素的时候要注意判空
        // step1:因为每层都是按照从左到右开始访问的，那自然记录的子节点也是从左到右，那我们从队列出来的时候也是从左到右，完美契合。
        // step2：在刚刚进入某一层的时候，队列中的元素个数就是当前层的节点数。比如第一层，根节点先入队，队列中只有一个节点，对应第一层只有一个节点，
        //第一层访问结束后，它的子节点刚好都加入了队列，此时队列中的元素个数就是下一层的节点数。因此遍历的时候，每层开始统计该层个数，然后遍历相应节点数，精准进入下一层。
        // step3:遍历完一层就可以节点深度就可以加1，

        //public int maxDepth(TreeNode root) {
        //    if (root == null) {// 如果是空
        //        return 0;
        //    }
        //    Queue<TreeNode> queue = new LinkedList<>(); //队列存储，进⾏层次遍历
        //    int deepth = 0;// 二叉树深度
        //    // 将根节点放入队列中，然后不断遍历队列
        //    queue.add(root);
        //    while (!queue.isEmpty()) {
        //        int levelSize = queue.size();// 获取当前队列的长度，这个长度相当于当前这一层的节点个数
        //        // 因先进入的是根节点，故每层结点多少，队列大小就是多少
        //        for (int i = 0; i < levelSize; i++) {
        //            TreeNode node = queue.poll();//队首元素出队
        //            //因为遍历进入队列的必须是数据节点，所以要判空
        //            if (node.left != null) {
        //                queue.add(node.left);
        //            }
        //            if (node.right != null) {
        //                queue.add(node.right);
        //            }
        //        }
        //        deepth++;//遍历完一层就把层数加1,
        //    }
        //    return deepth;
        //}





        //// 如果一棵树只有一个节点，那么它的深度为1。
        //// 如果根节点只有左子树而没有右子树，那么树的深度应该是其左子树的深度加1；
        //// 同样，如果根节点只有右子树而没有左子树，那么树的深度应该是其右子树的深度加1。
        //// 如果既有右子树又有左子树，那么该树的深度就是其左、右子树深度的较大值再加1。
        //
        //// 方法2：深度优先搜索  DFS 递归
        //// step1:终止条件：当进入叶子节点后，再进入叶子节点的子节点，即为空，没有深度可言，返回0.
        //// step2:返回值：每一级按照上述公式，返回两边子树深度的最大值加上本级的深度，即加1
        //// step3：本级任务：每一级的任务就是进入左右子树，求左右子树的深度。


        // 方法maxDepth就是返回以root为根节点的子树的深度
        public int maxDepth(TreeNode root) {
            if (root == null) {//空结点没有深度
                return 0;
            }
            // 之后根节点不空
            int leftHeight = maxDepth(root.left); //左子树最大深度
            int rightHeight = maxDepth(root.right); //右子树最大深度
            return Math.max(leftHeight, rightHeight) + 1;//以root为根节点的树的深度为左右⼦树深度最大值+1
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
