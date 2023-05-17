/**
 * <p>给定二叉搜索树（BST）的根节点<meta charset="UTF-8" />&nbsp;<code>root</code>&nbsp;和要插入树中的值<meta charset="UTF-8" />&nbsp;
 * <code>value</code>&nbsp;，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 <strong>保证</strong> ，新值和原始二叉搜索树中的任意节点值都不同。</p>
 *
 * <p><strong>注意</strong>，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 <strong>任意有效的结果</strong> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/05/insertbst.jpg" />
 * <pre>
 * <strong>输入：</strong>root = [4,2,7,1,3], val = 5
 * <strong>输出：</strong>[4,2,7,1,3,5]
 * <strong>解释：</strong>另一个满足题目要求可以通过的树是：
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/05/bst.jpg" />
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [40,20,60,10,30,50,70], val = 25
 * <strong>输出：</strong>[40,20,60,10,30,50,70,null,null,25]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * <strong>输出：</strong>[4,2,7,1,3,5]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中的节点数将在<meta charset="UTF-8" />&nbsp;<code>[0,&nbsp;10<sup>4</sup>]</code>的范围内。<meta charset="UTF-8" /></li>
 * <li><code>-10<sup>8</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>8</sup></code></li>
 * <li>所有值&nbsp;<meta charset="UTF-8" /><code>Node.val</code>&nbsp;是&nbsp;<strong>独一无二</strong>&nbsp;的。</li>
 * <li><code>-10<sup>8</sup>&nbsp;&lt;= val &lt;= 10<sup>8</sup></code></li>
 * <li><strong>保证</strong>&nbsp;<code>val</code>&nbsp;在原始BST中不存在。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>二叉搜索树</li><li>二叉树</li></div></div><br><div><li>👍 336</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 701
 * 二叉搜索树中的插入操作
 * @author wangweizhou
 * @date 2022-07-15 18:37:04
 */
public class InsertIntoABinarySearchTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new InsertIntoABinarySearchTree().new Solution();
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

        // 方法一：模拟
        // 二叉搜索树的性质：对于任意节点 root 而言，左子树（如果存在）上所有节点的值均小于 root.val，
        // 右子树（如果存在）上所有节点的值均大于root.val，且它们都是二叉搜索树。
        // 当将 val 插入到以 root 为根的子树上时，根据 val 与 root.val的大小关系，就可以确定要将 val 插入到哪个子树中。
        // 如果该子树不为空，则问题转化成了将 val 插入到对应子树上。
        // 否则，在此处新建一个以 val 为值的节点，并链接到其父节点 root 上。


        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }
            TreeNode pos = root;
            while (pos != null) {
                if (val < pos.val) { //左子树（如果存在）上所有节点的值均小于 root.val，
                    if (pos.left == null) {//如果该子树不为空，则问题转化成了将val 插入到对应子树上。
                        pos.left = new TreeNode(val);
                        break;
                    } else {
                        pos = pos.left;//向左遍历
                    }
                } else {// 右子树（如果存在）上所有节点的值均大于root.val
                    if (pos.right == null) {//如果该子树不为空，则问题转化成了将val 插入到对应子树上。
                        pos.right = new TreeNode(val);
                        break;
                    } else {
                        pos = pos.right;//向右遍历
                    }
                }
            }
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
