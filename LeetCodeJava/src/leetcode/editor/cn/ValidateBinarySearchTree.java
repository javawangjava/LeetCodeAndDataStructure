/**
 * <p>给你一个二叉树的根节点 <code>root</code> ，判断其是否是一个有效的二叉搜索树。</p>
 *
 * <p><strong>有效</strong> 二叉搜索树定义如下：</p>
 *
 * <ul>
 * <li>节点的左子树只包含<strong> 小于 </strong>当前节点的数。</li>
 * <li>节点的右子树只包含 <strong>大于</strong> 当前节点的数。</li>
 * <li>所有左子树和右子树自身必须也是二叉搜索树。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree1.jpg" style="width: 302px; height: 182px;" />
 * <pre>
 * <strong>输入：</strong>root = [2,1,3]
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree2.jpg" style="width: 422px; height: 292px;" />
 * <pre>
 * <strong>输入：</strong>root = [5,1,4,null,null,3,6]
 * <strong>输出：</strong>false
 * <strong>解释：</strong>根节点的值是 5 ，但是右子节点的值是 4 。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数目范围在<code>[1, 10<sup>4</sup>]</code> 内</li>
 * <li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉搜索树</li><li>二叉树</li></div></div><br><div><li>👍
 * 1679</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 98
 * 验证二叉搜索树
 *
 * @author wangweizhou
 * @date 2022-07-25 09:49:53
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new ValidateBinarySearchTree().new Solution();

        ////创建需要的结点
        TreeNode rootnode = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        //TreeNode node4 = new TreeNode(4);
        //TreeNode node5 = new TreeNode(5);
        //TreeNode node6 = new TreeNode(6);
        //TreeNode node7 = new TreeNode(7);

        // 手动创建二叉树
        rootnode.left = node2;
        rootnode.right = node3;
        //node2.left=node4;
        //node2.right=node5;
        //node3.left=node6;
        //node3.right=node7;

        Solution solution = new ValidateBinarySearchTree().new Solution();
        boolean list = solution.isValidBST(rootnode);
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

        // 二叉搜索树的性质：中序遍历是递增的


        //// ⽅法⼀：DFS 中序遍历 递归（推荐使⽤）
        //// 二叉搜索树的中序遍历是递增的
        //long last = Long.MIN_VALUE;// 全局变量，用于记录上一个节点的值，其实也就是截止当前节点之前的最大值
        //public boolean isValidBST(TreeNode root) {
        //    if (root == null) {
        //        return true;
        //    }
        //    if (!isValidBST(root.left)) {//先进⼊左⼦树
        //        return false;
        //    }
        //    if (root.val <= last) {
        //        return false;
        //    }
        //    last = root.val;//更新最值
        //    return isValidBST(root.right);//再进⼊右⼦树
        //}



        ////  ⽅法⼀：DFS 中序遍历 递归 先中序遍历记忆化，然后再检查中序遍历的顺序
        //public boolean isValidBST(TreeNode root) {
        //    if(root==null){
        //        return true;
        //    }
        //    ArrayList<Integer> lists=new ArrayList<>();
        //    inorder(root,lists);
        //    int last=lists.get(0);
        //   for(int i=1;i<lists.size();i++){
        //       if(last>=lists.get(i)){
        //           return false;
        //       }
        //       last=lists.get(i);
        //   }
        //   return true;
        //}
        //
        //
        //private void inorder(TreeNode root, ArrayList<Integer> lists){
        //    if(root==null){
        //        return;
        //    }
        //    inorder(root.left,lists);
        //    lists.add(root.val);
        //    inorder(root.right,lists);
        //}




        // 方法二：中序遍历优化，不采用数组存储，不断更新
        // 在中序遍历的时候实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可。
        // 如果均大于说明这个序列是升序的，整棵树是二叉搜索树，否则不是，

		public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            Deque<TreeNode> stack = new LinkedList<>();//设置栈⽤于遍历
            long last = Long.MIN_VALUE;// 注意这里使用的是Longdelast表示截止目前搜索二叉树中的前一个最大值，其实也就是二叉搜索树上一个节点的值。
            TreeNode node = root;
            while (node != null || !stack.isEmpty()) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
                node = stack.pop();
                // 如果中序遍历得到的节点值小于等于前一个 inorder，说明不是二叉搜索树
                if (node.val <= last) {
                    return false;
                }
                last = node.val;// 更新上一个值
                node = node.right;//进⼊右边
            }
            return true;
        }



        /*
        // 方法二：迭代 中序遍历 +记忆化数组存储
        // 使用数组存储中序遍历二叉树的结果，然后检查数组是否是升序就可以
        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            Deque<TreeNode> stack = new LinkedList<>();//设置栈⽤于遍历
            TreeNode node = root;
            ArrayList<Integer> sort = new ArrayList<>();//记录中序遍历的数组
            //中序遍历
            while (node != null || !stack.isEmpty()) {
                while (node != null) {//直到没有左节点
                    stack.push(node);
                    node = node.left;
                }
                node = stack.pop();
                sort.add(node.val);//处理当前节点，将当前节点加入到数组中
                node = node.right;//进⼊右子树
            }
            for (int i = 0; i < sort.size() - 1; i++) {//遍历中序结果
                if (sort.get(i) >= sort.get(i + 1)) {//⼀旦有降序，则不是搜索树
                    return false;
                }
            }
            return true;
        }
        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
