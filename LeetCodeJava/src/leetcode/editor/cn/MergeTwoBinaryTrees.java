/**
 * <p>给你两棵二叉树： <code>root1</code> 和 <code>root2</code> 。</p>
 *
 * <p>想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，<strong
 * >不为</strong> null 的节点将直接作为新二叉树的节点。</p>
 *
 * <p>返回合并后的二叉树。</p>
 *
 * <p><strong>注意:</strong> 合并过程必须从两个树的根节点开始。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/05/merge.jpg" style="height: 163px; width: 600px;" />
 * <pre>
 * <strong>输入：</strong>root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * <strong>输出：</strong>[3,4,5,5,4,null,7]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root1 = [1], root2 = [1,2]
 * <strong>输出：</strong>[2,2]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>两棵树中的节点数目在范围 <code>[0, 2000]</code> 内</li>
 * <li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 1024</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 617
 * 合并二叉树
 *
 * @author wangweizhou
 * @date 2022-07-23 21:55:53
 */


public class MergeTwoBinaryTrees {
    public static void main(String[] args) {

        //测试代码
        Solution solution = new MergeTwoBinaryTrees().new Solution();

        //创建需要的结点
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        TreeNode node9 = new TreeNode(5);


        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(7);

        // 手动创建二叉树
        node1.left = node2;
        node1.right = node3;
        node2.left = node9;

        node4.left = node5;
        node4.right = node6;
        node5.right = node7;
        node6.right = node8;

        solution.mergeTrees(node1, node4);

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


        // ⽅法⼀：递归前序遍历   合并过程必须从两个树的根节点开始。
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            //若⼀个节点为空，则返回另⼀个，两个都为null⾃然返回null
            if (root1 == null) {
                return root2;
            }
            if (root2 == null) {
                return root1;
            }

            //根左右的⽅式递归， 创建新二叉树
            TreeNode head = new TreeNode(root1.val + root2.val);// 处理当前节点
            head.left = mergeTrees(root1.left, root2.left);// 合并根节点的左子树
            head.right = mergeTrees(root1.right, root2.right);// 合并根节点的右子树
            return head;
        }




        //
        //// ⽅法二：BFS  注意这里合并二叉树的时候，要需要将空节点加入队列，这样才能占位，区分左右节点，但是在计算时要注意空节点的处理
        //public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        //    // 若只有⼀个节点则返回另⼀个，两个都为null⾃然返回null
        //    if (root1 == null) {
        //        return root2;
        //    }
        //    if (root2 == null) {
        //        return root1;
        //    }
        //
        //    TreeNode head = new TreeNode(root1.val + root2.val);// 创建新二叉树的根节点
        //    Queue<TreeNode> queue = new LinkedList<>();// 存储合并后的二叉树的层次遍历节点
        //    // 分别存两棵树的层次遍历节点
        //    Queue<TreeNode> queue1 = new LinkedList<>();
        //    Queue<TreeNode> queue2 = new LinkedList<>();
        //    // 三个根节点分别入栈
        //    queue.add(head);
        //    queue1.add(root1);
        //    queue2.add(root2);
        //
        //    // 注意这里是合并二叉树，所以和验证对称一样，要使用二叉树的空节点占位
        //    while(queue1.size()>0&&queue2.size()>0){// !queue1.isEmpty() && !queue2.isEmpty()
        //        TreeNode node=queue.poll();
        //        TreeNode node1=queue1.poll();
        //        TreeNode node2=queue2.poll();
        //
        //        // 处理当前节点的左右子节点，分别合并左右节点
        //        if(node1.left!=null||node2.left!=null){//两个左节点至少有一个存在
        //            if(node1.left!=null&&node2.left!=null){//两个左节点都存在，创建新节点
        //                TreeNode left=new TreeNode(node1.left.val+node2.left.val);//合并左节点
        //                node.left=left;//左节点连接到父节点上
        //                queue.add(node.left);//新节点⼊队列
        //                queue1.add(node1.left);
        //                queue2.add(node2.left);
        //            }else if(node1.left!=null){//节点1的左节点不为空，节点2的左节点为空，将节点1的左节点连接到新建的树上
        //                node.left=node1.left;
        //            }else{
        //                node.left=node2.left;
        //            }
        //        }
        //
        //
        //        if(node1.right!=null||node2.right!=null){
        //            if(node1.right!=null&&node2.right!=null){
        //                TreeNode right=new TreeNode(node1.right.val+node2.right.val);
        //                node.right=right;
        //                queue.add(node.right);
        //                queue1.add(node1.right);
        //                queue2.add(node2.right);
        //            }else if(node1.right!=null){
        //                node.right=node1.right;
        //            }else{
        //                node.right=node2.right;
        //            }
        //        }
        //    }
        //    return head;
        //}




    }
//leetcode submit region end(Prohibit modification and deletion)

}
