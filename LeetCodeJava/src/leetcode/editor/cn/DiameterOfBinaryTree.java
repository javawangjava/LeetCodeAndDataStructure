/**
 * <p>给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 :</strong><br>
 * 给定二叉树</p>
 *
 * <pre>          1
 * / \
 * 2   3
 * / \
 * 4   5
 * </pre>
 *
 * <p>返回&nbsp;<strong>3</strong>, 它的长度是路径 [4,2,1,3] 或者&nbsp;[5,2,1,3]。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>注意：</strong>两结点之间的路径长度是以它们之间边的数目表示。</p>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 1132</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 543
 * 二叉树的直径
 * @author wangweizhou
 * @date 2022-08-18 15:42:02
 */

public class DiameterOfBinaryTree {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new DiameterOfBinaryTree().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    class Solution {

        //// 注意题干中的深度定义是：两结点之间的路径长度是以它们之间边的数目表示。 边的数目=（节点数-1）。
        //// 一条路径的长度为该路径经过的节点数减一。所以求直径（即求路径长度的最大值）等效于求路径经过节点数的最大值减一。


        // 解法1：写法2 深度遍历
        public int diameterOfBinaryTree(TreeNode root) {
            if (root == null) {// 判空
                return 0;
            }
            int[] maxNodeCounts = {0};
            getDepth(root, maxNodeCounts);
            // 注意题干中的深度定义是：两结点之间的路径长度是以它们之间边的数目表示。 边的数目=（节点数-1）
            return maxNodeCounts[0] - 1;
        }


        // 后续递归遍历        求以node为根节点的二叉树的深度，注意下面depth（）中深度定义是用节点数目定义的
        private int getDepth(TreeNode root, int[] maxNodeCounts) {
            if (root == null) {
                return 0;
            }
            int leftDepth = getDepth(root.left, maxNodeCounts);//左子树深度
            int rightDepth = getDepth(root.right, maxNodeCounts);
            // 遍历完当前节点的左子树和右子树，获取左子树和右子树深度之后，那么通过当前节点和该节点的左右子树的路径的节点数是（leftDepth + rightDepth + 1）
            // 更新当前二叉树中的最长路径的节点数
            maxNodeCounts[0] = Math.max(maxNodeCounts[0], leftDepth + rightDepth + 1);
            return Math.max(leftDepth, rightDepth) + 1;// 通过从当前节点出发的子树的深度等于左右子树深度最大值+1。
        }




        //// 解法1：写法1 深度遍历
        //
        //int maxNodeCounts;// 截止目前某条路径节点的最大值
        //public int diameterOfBinaryTree(TreeNode root) {
        //	if(root==null){
        //		return 0;
        //	}
        //	maxNodeCounts =1;
        //	depth(root);
        //	// 注意题干中的深度定义是：两结点之间的路径长度是以它们之间边的数目表示。 边的数目=（节点数-1）
        //	return maxNodeCounts -1;
        //}
        //
        //
        //
        ////后续递归遍历        求以node为根节点的二叉树的深度，注意下面depth（）中深度定义是用节点数目定义的
        //private int depth(TreeNode node){
        //	if(node==null){
        //		return 0;
        //	}
        //	int leftDepth=depth(node.left);//左子树深度
        //	int rightDepth=depth(node.right);
        //	// 处理当前节点
        //	// 通过节点node 左右节点数：leftDepth+rightDepth+1= (leftDepth+1)+(rightDepth+1)-1
        //	maxNodeCounts =Math.max(maxNodeCounts,leftDepth+rightDepth+1);// 更新当前二叉树中的最长路径的节点数
        //	return Math.max(leftDepth,rightDepth)+1;//处理当前节点【根节点】的深度。当前节点的深度为（左右子树的的最大值+1）
        //}
        //


    }
//leetcode submit region end(Prohibit modification and deletion)

}
