/**
 * <p>给你一棵二叉搜索树的<meta charset="UTF-8" />&nbsp;<code>root</code>&nbsp;，请你 <strong>按中序遍历</strong>
 * 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/17/ex1.jpg" style="height: 350px; width: 600px;" />
 * <pre>
 * <strong>输入：</strong>root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * <strong>输出：</strong>[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/17/ex2.jpg" style="height: 114px; width: 300px;" />
 * <pre>
 * <strong>输入：</strong>root = [5,1,7]
 * <strong>输出：</strong>[1,null,5,null,7]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数的取值范围是 <code>[1, 100]</code></li>
 * <li><code>0 &lt;= Node.val &lt;= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>二叉搜索树</li><li>二叉树</li></div></div><br
 * ><div><li>👍 300</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 897
 * 递增顺序搜索树
 *
 * @author wangweizhou
 * @date 2022-09-05 14:08:09
 */

public class IncreasingOrderSearchTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new IncreasingOrderSearchTree().new Solution();
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


        // 展平的二叉树：依旧是二叉搜索树，而且它除叶节点外每个节点都只有右子节点。
        // 由于二叉搜索树中右子节点大于或等于它的父节点，因此调整之后的二叉搜索树从根节点开始顺着指向右子节点的指针向下经过的节点的值将是递增排序的。
        // 这就容易让人联想到二叉树的中序遍历，只是在这里每遍历到一个节点要把前一个节点的指向右子节点的指针指向它。
        // 只是对二叉树中序遍历的迭代代码稍做修改。变量prev表示前一个遍历到的节点。
        // 在遍历到当前节点cur时，把变量prev的右子节点的指针指向cur，并将cur指向左子节点的指针设为null。
        // 展平之后的二叉搜索树的根节点是值最小的节点，因此也是中序遍历第1个被遍历到的节点。
        // 在上述代码中，变量first就是第1个被遍历到的节点，在展平之后就是二叉搜索树的根节点，因此将它作为函数的返回值。


        // 解法1：写法1：调整中序遍历：遍历到每一个节点时要把前一个节点指向右子节点的指针指向它。
        public TreeNode increasingBST(TreeNode root) {
            if (root == null) {
                return null;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            TreeNode node = root;
            TreeNode prev = null;// 遍历过的上一个节点
            TreeNode first = null;// 二叉搜索树最左边的一个节点，即最小的节点
            while (node != null || !stack.isEmpty()) {
                // 沿着二叉树左子节点一直向下，找到最左的一个节点
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
                // 处理当前节点
                node = stack.pop();
                // 判断上一个节点是否为空，或者first节点为空，目的是找到二叉树最左侧的节点，也就是最小的节点，即调整后二叉树的根节点。
                // 这里prev == null或者 first == null 都可以，本质上是检查是否是第一次来处理当前节点
                if (prev == null) {// prev == null。即上一个节点是空，那么这时候就当前节点就是二叉搜索树的最小的节点，即第一个节点
                    first = node;
                    //curr.left = null;// 当前节点的左指针置空
                    //prev = curr;// 上一个节点指向当前节点
                } else {// 当前节点不是二叉搜索树最左边的节点
                    prev.right = node;
                    //curr.left = null;// 当前节点的左指针置空
                    //prev = curr;// 上一个节点指向当前节点
                }
                //// 只要在处理当前节点时，需要将当前节点的左子树置空，也就是从原二叉树上断开
                node.left = null;// 当前节点的左指针置空
                prev = node;// 上一个节点指向当前节点

                node = node.right;// 遍历右子树

            }
            return first;
        }





        //// 解法2：递归解法
        //// 全局变量在每次的递归或者不同的方法中时都是同一个值。
        //private TreeNode first = null;// first 表示是二叉搜索树中最小的元素，也就是最左侧的元素
        //private TreeNode prev = null;// prev 表示二叉搜索树中上一个遍历的元素
        //public TreeNode increasingBST(TreeNode root) {
        //    if (root == null) {
        //        return root;
        //    }
        //    inorderDfs(root);
        //    return first;
        //}
        //
        //
        //// 中序遍历   二叉搜索树的中序遍历是递增的，所以使用中序遍历
        //private void inorderDfs(TreeNode root) {
        //    if (root == null) {
        //        return;
        //    }
        //    inorderDfs(root.left);
        //    // 处理当前节点
        //    if (first == null) {// 如果还没有找到最小的元素
        //        first = root;// 更新最小的元素，这个是展平后的二叉树的根节点
        //        //prev = root;// 更新上一个遍历过的节点
        //        //root.left = null;// 将当前节点的左子树置空
        //    } else {// 已经找到了最小的元素
        //        prev.right = root;// 将当前节点连接到上一个节点的右指针上
        //        //prev = root;// 更新上一个遍历过的节点
        //        //root.left = null;// 将当前节点的左子树置空
        //    }
        //    prev = root;// 更新上一个遍历过的节点
        //    root.left = null;// 将当前节点的左子树置空
        //    inorderDfs(root.right);
        //}





        //// 解法2：写法2：中序遍历修改  递归实现
        //private TreeNode curr;// 全局变量：用来遍历新建的二叉树,全局变量在每次的递归或者不同的方法中时都是同一个值。
        //public TreeNode increasingBST(TreeNode root) {
        //	if (root == null) {
        //		return root;
        //	}
        //	TreeNode dummy=new TreeNode(-1);// 新建二叉树的根哨兵节点
        //	curr=dummy;
        //	inorder(root);
        //	return dummy.right;
        //}
        //
        //
        //// 二叉搜索树的中序遍历是递增的，所以使用中序遍历
        //private void inorder(TreeNode node){// node是原二叉树的遍历节点
        //	if(node==null){
        //		return;
        //	}
        //	inorder(node.left);// 递归左子树
        //	// 处理当前节点
        //	node.left=null;// 将原二叉树当前节点的左子树置空
        //	curr.right=node;// 将当前节点连接到新建的二叉树的右子树上
        //	// 新树的遍历节点后移
        //	curr=curr.right;// 新树的遍历节点后移，这个也可以//curr=node; // 新树的遍历节点后移
        //	inorder(node.right);// 递归右子树
        //}




        //// 解法3：中序遍历存储节点数据+遍历lists创建新的树
        //public TreeNode increasingBST(TreeNode root) {
        //	if(root==null){
        //		return root;
        //	}
        //	List<Integer> lists=new ArrayList<>();// 存储二叉树节点的链表
        //	inorder(root,lists);
        //
        //	// 遍历存储二叉树节点的链表重建只有右子节点的二叉树
        //	TreeNode dummy=new TreeNode(-1);
        //	TreeNode node=dummy;
        //	for(int list:lists){
        //		node.right=new TreeNode(list);
        //		node=node.right;
        //	}
        //	return dummy.right;
        //}
        //
        //
        //// 二叉排序树的中序遍历 将二叉排序树的中序遍历节点存储到链表中
        //private void inorder(TreeNode node, List<Integer> lists){
        //	if(node==null){
        //		return;
        //	}
        //	inorder(node.left,lists);
        //	lists.add(node.val);
        //	inorder(node.right,lists);
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
